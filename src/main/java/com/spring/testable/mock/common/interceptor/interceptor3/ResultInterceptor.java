/**
 * Copyright 2020 Inc.
 **/
package com.spring.testable.mock.common.interceptor.interceptor3;

import com.spring.testable.mock.common.interceptor.interceptor3.annotation.EncryptDecryptClass;
import com.spring.testable.mock.common.interceptor.interceptor3.encrypt.IEncryptDecrypt;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.CollectionUtils;

import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * @author maoyz0621 on 2020/11/2
 * @version v1.0
 */
@Intercepts(
        {@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = Statement.class)})
@Slf4j
public class ResultInterceptor implements Interceptor {

    @Autowired
    private IEncryptDecrypt encryptDecrypt;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("============= 查询结果拦截器处理 =============");

        Object result = invocation.proceed();
        if (Objects.isNull(result)) {
            return null;
        }

        if (result instanceof Collection) {
            List collection = (List) result;
            if (!CollectionUtils.isEmpty(collection) && needToDecrypt(collection.get(0))) {
                for (Object o : collection) {
                    encryptDecrypt.decrypt(o);
                }
            }
        } else {
            if (needToDecrypt(result)) {
                encryptDecrypt.decrypt(result);
            }
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    private boolean needToDecrypt(Object obj) {
        EncryptDecryptClass annotation = AnnotationUtils.findAnnotation(obj.getClass(), EncryptDecryptClass.class);
        return Objects.nonNull(annotation);
    }
}