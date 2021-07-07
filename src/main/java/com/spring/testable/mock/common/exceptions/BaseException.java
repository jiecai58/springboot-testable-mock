package com.spring.testable.mock.common.exceptions;


import com.spring.testable.mock.common.enums.ErrorCode;

/**
 *
 * @author Jie.cai58@gmail.com
 * @date 2014/9/19 21:15
 */
public class BaseException extends RuntimeException {
    private Long errorCode;

    public BaseException() {
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
    }

    public BaseException(Long code, String message) {
        super(message);
        this.errorCode = code;
    }

    public Long getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }
}

