package com.spring.testable.mock.common.interceptor.interceptor1.encrypt.aes;

import com.spring.testable.mock.common.AESType;
import com.spring.testable.mock.common.AESUtil;
import com.spring.testable.mock.common.EncodeType;
import com.spring.testable.mock.common.TypeConvert;
import com.spring.testable.mock.common.interceptor.interceptor1.annotation.SensitiveField;
import com.spring.testable.mock.common.interceptor.interceptor1.encrypt.EncryptUtil;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Objects;

@Component
public class AESEncrypt implements EncryptUtil {

    /**
     * 加密
     *
     * @param declaredFields paramsObject所声明的字段
     * @param paramsObject   mapper中paramsType的实例
     * @return T
     * @throws IllegalAccessException 字段不可访问异常
     */
    @Override
    public <T> T encrypt(Field[] declaredFields, T paramsObject) throws Exception {
        for (Field field : declaredFields) {
            //取出所有被EncryptDecryptField注解的字段
            SensitiveField sensitiveField = field.getAnnotation(SensitiveField.class);
            if (!Objects.isNull(sensitiveField)) {
                field.setAccessible(true);
                Object object = field.get(paramsObject);
                //暂时只实现String类型的加密
                if (object instanceof String) {
                    //ECB/PKCS5Padding
                    byte[] encodeByte = AESUtil.encodeByte((String) object, AESUtil.KEY, null, AESType.AES_128, EncodeType.AES_DEFAULT);
                    //加密  这里我使用自定义的AES加密工具
                    field.set(paramsObject, TypeConvert.byte2Base64StringFun(encodeByte));
                }
            }
        }
        return paramsObject;
    }
}
