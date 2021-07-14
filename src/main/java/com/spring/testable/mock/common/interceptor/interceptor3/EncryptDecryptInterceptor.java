/**
 * Copyright 2020 Inc.
 **/
package com.spring.testable.mock.common.interceptor.interceptor3;


import com.spring.testable.mock.common.interceptor.interceptor3.annotation.EncryptDecryptClass;
import lombok.extern.slf4j.Slf4j;
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

import java.util.Properties;

/**
 * 加解密插件
 * @author maoyz0621 on 2020/11/3
 * @version v1.0
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
@Slf4j
public class EncryptDecryptInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("============= 加解密拦截器处理 =============");

        Object[] args = invocation.getArgs();
        MappedStatement statement = (MappedStatement) args[0];
        // 获取sql语句的类型
        String methodName = invocation.getMethod().getName();

        // sql语句中放入的参数
        Object parameter = args[1];

        // 如果是查询操作，并且返回值不是敏感实体类对象，并且传入参数不为空，就直接调用执行方法，返回这个方法的返回值
        // 方法中可以判断这个返回值是否是多条数据，如果有数据，就代表着是select 操作，没有就代表是update insert delete

        if (statement.getResultMaps().size() > 0) {
            // 获取返回值的类属性
            Class<?> type = statement.getResultMaps().get(0).getType();
            // 是否有注解
            if (!type.isAnnotationPresent(EncryptDecryptClass.class)) {
                // 直接执行语句并返回
                return invocation.proceed();
            }
        }

        // 如果该参数为空，就不进行判断敏感实体类，直接执行sql
        // 并且
        // 如果判断该参数带有敏感实体类的注解，才对这个实体类进行扫描查看是否有加密解密的注解

        if (parameter != null) {
            // 如果有就扫描是否是更新操作和是否有加密注解
            // 如果是更新或者插入时，就对数据进行加密后保存在数据库
            if (StringUtils.equalsIgnoreCase("update", methodName) || StringUtils.equalsIgnoreCase("insert", methodName)) {
                {

                }
                // 对参数内含注解的字段进行加密
            }
        }

        // 继续执行sql语句,调用当前拦截的执行方法
        Object returnValue = invocation.proceed();
        return null;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}