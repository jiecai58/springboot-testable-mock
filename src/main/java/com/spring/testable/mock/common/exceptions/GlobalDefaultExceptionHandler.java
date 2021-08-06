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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public BaseResponse<?> exceptionHandler(HttpServletRequest req, HttpServletResponse response, Exception e) {
        e.printStackTrace();
        if(e instanceof BusinessException){
            log.error("Exception: from:{} uri:{} code:{} msg:{}", req.getRemoteHost(), req.getRequestURI(), ((BusinessException) e).getCode(), e.getMessage());
            return new BaseResponse(((BusinessException) e).getCode(), e.getMessage());
        }else if(e instanceof MethodArgumentNotValidException){
            return new BaseResponse(ErrorCode.ERROR_SYSTEM.getCode(),methodArgumentNotValidExceptionHandler((MethodArgumentNotValidException)e));
        }else if(e instanceof MethodArgumentTypeMismatchException){
            return new BaseResponse(ErrorCode.ERROR_SYSTEM.getCode(), ((MethodArgumentTypeMismatchException) e).getParameter().getParameterName());
        }else if(e instanceof MissingServletRequestParameterException){
            return new BaseResponse(ErrorCode.ERROR_SYSTEM.getCode(), ((MissingServletRequestParameterException) e).getParameterName());
        }
        return new BaseResponse(ErrorCode.ERROR_SYSTEM.getCode(),optimizationTips(e));
    }

    private String optimizationTips(Exception e){
        String message = e.getMessage();
        if (null == message || message.equals("")) {
            message = ErrorCode.ERROR_SYSTEM.getMessage();
        }
        //优化友好的页面信息提示
        String[] messages = message.split(System.getProperty("line.separator", "/n"));
        if (messages != null && messages.length > 0) {
            message = messages[0];
        }
        return message;
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
