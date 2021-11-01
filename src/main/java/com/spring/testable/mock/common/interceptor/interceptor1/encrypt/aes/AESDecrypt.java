package com.spring.testable.mock.common.interceptor.interceptor1.encrypt.aes;

import com.spring.testable.mock.common.AESType;
import com.spring.testable.mock.common.AESUtil;
import com.spring.testable.mock.common.EncodeType;
import com.spring.testable.mock.common.TypeConvert;
import com.spring.testable.mock.common.interceptor.interceptor1.annotation.SensitiveField;
import com.spring.testable.mock.common.interceptor.interceptor1.encrypt.DecryptUtil;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author leo
 */
@Service
public class AESDecrypt implements DecryptUtil {

    /**
     * 解密
     *
     * @param result resultType的实例
     * @return T
     * @throws IllegalAccessException 字段不可访问异常
     */
    @Override
    public <T> T decrypt(T result) throws Exception {
        //取出resultType的类
        Class<?> resultClass = result.getClass();
        Field[] declaredFields = resultClass.getDeclaredFields();
        for (Field field : declaredFields) {
            //取出所有被EncryptDecryptField注解的字段
            SensitiveField sensitiveField = field.getAnnotation(SensitiveField.class);
            if (!Objects.isNull(sensitiveField)) {
                field.setAccessible(true);
                Object object = field.get(result);
                //只支持String的解密
                if (object instanceof String) {
                    //ECB/PKCS5Padding
                    byte[] decodeByte = AESUtil.decodeByte(TypeConvert.base64String2ByteFun((String) object), AESUtil.KEY, null, AESType.AES_128, EncodeType.AES_DEFAULT);
                    //对注解的字段进行逐一解密
                    field.set(result, new String(decodeByte, AESUtil.CHARSET));
                }
            }
        }
        return result;
    }
}
