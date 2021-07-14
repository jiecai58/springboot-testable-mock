package com.spring.testable.mock.common.interceptor;

import com.spring.testable.mock.common.interceptor.annotation.Desensitization;
import com.spring.testable.mock.common.interceptor.enum1.DesensitionType;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author caijie
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }), })
public class DesensitizationInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(DesensitizationInterceptor.class);

    private boolean desensitization = false;// 脱敏

    private static final Map<String, DesensitionType> desensitionMap = new LinkedHashMap<>();
    static {
        initDensensitionMap();
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();

        // 如果需要对结果脱敏，则执行
        if (desensitization) {
            // 先对Map进行处理
            if (result != null && result instanceof Map) {
                return this.desensitizationMap(result);
            }

            // 处理集合
            if (result instanceof ArrayList<?>) {
                List<?> list = (ArrayList<?>) result;
                return this.desensitization(list);
            }

            // 处理单个bean
            return this.desensitization(result);
        }

        return result;
    }

    private static void initDensensitionMap() {
        desensitionMap.put("idCode", DesensitionType.ID_CARD);
        desensitionMap.put("idCard", DesensitionType.ID_CARD);
        desensitionMap.put("userIDCard", DesensitionType.ID_CARD);
        desensitionMap.put("userIdCard", DesensitionType.ID_CARD);

        desensitionMap.put("username", DesensitionType.REAL_NAME);
        desensitionMap.put("address", DesensitionType.ADDRESS);
    }

    /*
     * 对map脱敏
     */
    private Object desensitizationMap(Object result) {
        Map mapResult = (Map) result;
        if (MapUtils.isEmpty(mapResult)) {
            return mapResult;
        }

        Set<String> keySet = mapResult.keySet();
        for (String key : keySet) {
            if (desensitionMap.containsKey(key)) {
                DesensitionType desensitionType = desensitionMap.get(key);
                String replacedVal = getReplacedVal(desensitionType, MapUtils.getString(mapResult, key), null);
                mapResult.put(key, replacedVal);
            }
        }
        return result;
    }

    private List desensitization(List list) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }

        Class cls = null;
        for (Object o : list) {
            // 脱敏map，改变引用地址(根据静态配置脱敏)
            if (o != null && o instanceof Map) {
                o = desensitizationMap(o);
                continue;
            }

            // 脱敏bean(根据注解脱敏)
            if (cls == null) {
                cls = o.getClass();
            }
            o = desensitization(o);
        }
        return list;
    }

    @Override
    public Object plugin(Object target) {
        // TODO Spring bean 方式配置时，如果没有配置属性就不会执行下面的 setProperties
        // 方法，就不会初始化，因此考虑在这个方法中做一次判断和初始化
        return Plugin.wrap(target, this);
    }

    /**
     * 用于在Mybatis配置文件中指定一些属性的，注册当前拦截器的时候可以设置一些属性
     */
    @Override
    public void setProperties(Properties properties) {
    }

    private Object desensitization(Object obj) {
        if (obj == null) {
            return obj;
        }
        Class cls = obj.getClass();
        Field[] objFields = cls.getDeclaredFields();
        if (ArrayUtils.isEmpty(objFields)) {
            return obj;
        }

        for (Field field : objFields) {
            if ("serialVersionUID".equals(field.getName())) {
                continue;
            }

            Desensitization desensitization = null;
            if (String.class != field.getType()
                    || (desensitization = field.getAnnotation(Desensitization.class)) == null) {
                continue;
            }

            try {
                field.setAccessible(true);
                String value = field.get(obj) != null ? field.get(obj).toString() : null;
                if (StringUtils.isBlank(value)) {
                    continue;
                }

                value = getReplacedVal(desensitization.type(), value, desensitization.attach());
                field.set(obj, value);
            } catch (Exception ignore) {
                ignore.printStackTrace();
            }
        }

        return obj;
    }

    private String getReplacedVal(DesensitionType type, String value, String[] attachs) {
        List<String> regular = null;
        switch (type) {
            case CUSTOM:
                regular = Arrays.asList(attachs);
                break;
            case TRUNCATE:
                regular = truncateRender(attachs);
                break;
            default:
                regular = Arrays.asList(type.getRegular());
        }

        if (regular != null && regular.size() > 1) {
            String match = regular.get(0);
            String result = regular.get(1);
            if (null != match && result != null && match.length() > 0) {
                value = ((String) value).replaceAll(match, result);
                return value;
            }
        }

        return "";
    }

    private List<String> truncateRender(String[] attachs) {
        List<String> regular = new ArrayList<>();
        if (null != attachs && attachs.length > 1) {
            String rule = attachs[0];
            String size = attachs[1];
            String template, result;
            if ("0".equals(rule)) {
                template = "^(\\S{%s})(\\S+)$";
                result = "$1";
            } else if ("1".equals(rule)) {
                template = "^(\\S+)(\\S{%s})$";
                result = "$2";
            } else {
                return regular;
            }
            try {
                if (Integer.parseInt(size) > 0) {
                    regular.add(0, String.format(template, size));
                    regular.add(1, result);
                }
            } catch (Exception e) {
                logger.warn("ValueDesensitizeFilter truncateRender size {} exception", size);
            }
        }
        return regular;
    }

    public boolean isDesensitization() {
        return desensitization;
    }

    public void setDesensitization(boolean desensitization) {
        this.desensitization = desensitization;
    }

}
