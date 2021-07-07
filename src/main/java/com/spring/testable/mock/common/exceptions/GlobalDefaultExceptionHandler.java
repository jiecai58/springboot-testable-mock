package com.spring.testable.mock.common.exceptions;

import com.spring.testable.mock.common.enums.ErrorCode;
import com.spring.testable.mock.common.object.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 *
 * @author Jie.cai58@gmail.com
 * @date 2014/9/19 20:51
 */
@ControllerAdvice
@Slf4j
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse<?> exceptionHandler(Exception e) {
        e.printStackTrace();
        if(e instanceof BusinessException){
            return new BaseResponse(((BusinessException) e).getCode(), e.getMessage());
        }else if(e instanceof MethodArgumentNotValidException){
            return new BaseResponse(ErrorCode.ERROR_SYSTEM.getCode(),methodArgumentNotValidExceptionHandler((MethodArgumentNotValidException)e));
        }else if(e instanceof MethodArgumentTypeMismatchException){
            return new BaseResponse(ErrorCode.ERROR_SYSTEM.getCode(), ((MethodArgumentTypeMismatchException) e).getParameter().getParameterName());
        }else if(e instanceof MissingServletRequestParameterException){
            return new BaseResponse(ErrorCode.ERROR_SYSTEM.getCode(), ((MissingServletRequestParameterException) e).getParameterName());
        }
        return new BaseResponse(ErrorCode.ERROR_SYSTEM.getCode(),e.getMessage()!=null?e.getMessage():ErrorCode.ERROR_SYSTEM.getMessage());
    }

    private String methodArgumentNotValidExceptionHandler( MethodArgumentNotValidException e) {
        String p = e.getParameter().getParameterName();
        BindingResult bindingResult = e.getBindingResult();
        String fieldErrorMessage = "";
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                sb.append(((FieldError) objectError).getField() + ":").append(objectError.getDefaultMessage()).append("; ");
            }
            fieldErrorMessage = sb.toString();
        }
        return  "param error: " + p + ", " + fieldErrorMessage;
    }
}
