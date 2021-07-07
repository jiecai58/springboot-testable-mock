package com.spring.testable.mock.common.exceptions;

import com.spring.testable.mock.common.enums.ErrorCode;

/**
 *
 * @author Jie.cai58@gmail.com
 * @date 2014/9/19 20:49
 */
public class BusinessException extends BaseException {
    private Long code;

    private String message;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BusinessException(){}

    public BusinessException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }

    public BusinessException(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message, Throwable cause, Long code, String message1) {
        super(message, cause);
        this.code = code;
        this.message = message1;
    }

    public BusinessException(String message, Long code, String message1) {
        super(message);
        this.code = code;
        this.message = message1;
    }

    public BusinessException(Throwable cause, Long code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

}
