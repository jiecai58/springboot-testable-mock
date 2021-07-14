/**
 * Copyright 2020 Inc.
 **/
package com.spring.testable.mock.common.interceptor.interceptor3.encrypt;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * @author maoyz0621 on 2020/11/2
 * @version v1.0
 */
@Service
public class EncryptDecryptImpl implements IEncryptDecrypt {

    @Override
    public <T> T encrypt(Field[] declaredFields, T parameterObject) throws IllegalAccessException {
        return EncryptDecryptUtils.encrypt(declaredFields, parameterObject);
    }

    @Override
    public <T> T decrypt(T result) throws IllegalAccessException {
        return EncryptDecryptUtils.decrypt(result);
    }
}