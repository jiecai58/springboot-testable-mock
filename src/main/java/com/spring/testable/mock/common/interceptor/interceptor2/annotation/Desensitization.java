package com.spring.testable.mock.common.interceptor.interceptor2.annotation;

import com.spring.testable.mock.common.interceptor.interceptor2.enum1.DesensitionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author leo
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Desensitization {
    DesensitionType type();

    String[] attach() default "";

}
