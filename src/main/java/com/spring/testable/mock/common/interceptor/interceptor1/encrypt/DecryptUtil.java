package com.spring.testable.mock.common.interceptor.interceptor1.encrypt;

/**
 * @author caijie
 */
public interface DecryptUtil {

    /**
     * 解密
     *
     * @param result resultType的实例
     * @return T
     * @throws IllegalAccessException 字段不可访问异常
     */
    <T> T decrypt(T result) throws Exception;
}
