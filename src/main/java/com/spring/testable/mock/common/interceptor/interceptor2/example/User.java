package com.spring.testable.mock.common.interceptor.interceptor2.example;


import com.spring.testable.mock.common.interceptor.interceptor2.annotation.Desensitization;
import com.spring.testable.mock.common.interceptor.interceptor2.enum1.DesensitionType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author leo
 */
@Data
@Accessors(chain = true)
public class User {

    private String userName;

    /**
     * 身份证（aes 数据库加密）
     */
    private String identityNo;

    /**
     * 真实姓名（aes 数据库加密）
     */
    private String realName;

    /**
     * 手机号（aes 数据库加密）
     */
    private String mobile;

    /**
     * 身份证号
     */
    @Desensitization(type = DesensitionType.ID_CARD)
    private String idcode;

/**
 * 此时在mapper中，指定paramType=User resultType=User 便可实现脱离业务层，基于mybatis拦截器的加解密操作
 */
}
