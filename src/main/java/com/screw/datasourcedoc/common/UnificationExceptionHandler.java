package com.screw.datasourcedoc.common;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: jiangjs
 * @description:
 * @date: 2021/7/23 15:56
 **/
@RestControllerAdvice
public class UnificationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultJson handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        String objectError = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResultJson.error(400,objectError);
    }
}
