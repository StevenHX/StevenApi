package com.steven.demo01.exception;

import com.steven.demo01.entity.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @author Joetao
 * 异常处理类
 * controller层异常无法捕获处理，需要自己处理
 * Created at 2018/8/27.
 */
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    /**
     * 处理所有自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public CommonResult handleCustomException(CustomException e){
        return e.getResult();
    }
    /**
     * 处理参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return CommonResult.error(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage(),"");
    }
}