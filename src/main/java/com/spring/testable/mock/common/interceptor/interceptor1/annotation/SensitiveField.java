package com.spring.testable.mock.common.interceptor.interceptor1.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解敏感信息类中敏感字段的注解
 * @author caijie
 */
@Inherited
@Target({ElementType.FIELD })

@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveField {
}
