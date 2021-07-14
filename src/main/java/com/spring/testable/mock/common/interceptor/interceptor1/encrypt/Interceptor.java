package com.spring.testable.mock.common.interceptor.interceptor1.encrypt;

import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;

import java.util.Properties;

/**
 * @author leo
 */
public interface Interceptor {

    //核心拦截逻辑
    Object intercept(Invocation invocation) throws Throwable;

    //拦截器链
    default Object plugin(Object target) {
        return Plugin.wrap(target, (org.apache.ibatis.plugin.Interceptor) this);
    }

    //自定义配置文件操作
    default void setProperties(Properties properties) { }

}
