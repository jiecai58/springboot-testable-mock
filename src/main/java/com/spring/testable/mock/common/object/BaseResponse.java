package com.spring.testable.mock.common.object;

import com.spring.testable.mock.common.enums.ErrorCode;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public Long code;

    public String message;

    public T data;


    public BaseResponse(){};
    /**
     * 通用返回
     *
     * @param code
     * @param message
     * @param data
     */
    public BaseResponse(Long code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public BaseResponse(Long code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    /**
     * 返回正常数据
     *
     * @param data
     */
    public BaseResponse(T data) {
        super();
        this.code = ErrorCode.SUCCESS.getCode();
        this.message = ErrorCode.SUCCESS.getMessage();
        this.data = data;
    }


    /**
     * 返回异常数据，错误号在ErrorCode中定义
     *
     * @param e
     * @param data
     */
    public BaseResponse(ErrorCode e, T data) {
        super();
        this.code = e.getCode();
        this.message = e.getMessage();
        this.data = data;
    }
}
