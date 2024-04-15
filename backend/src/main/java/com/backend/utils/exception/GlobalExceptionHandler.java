package com.backend.utils.exception;


import com.backend.utils.response.ResponseData;
import com.backend.utils.response.ReturnCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 通用业务逻辑出错
    @ExceptionHandler(BusinessException.class)
    public ResponseData<Object> customExceptionHandler(BusinessException e){
        log.info("通用业务逻辑错误: " + e);
        return ResponseData.fail(ReturnCodes.BUSINESS_FAIL,"通用业务逻辑错误: " + e.getDescription());
    }

    // 登录出错
    @ExceptionHandler(AuthenticationException.class)
    public ResponseData<Object> jwtSignatureExceptionHandler(AuthenticationException e){
        log.info("用户名或密码错误: " + e);
        return ResponseData.fail(ReturnCodes.LOGIN_FAIL, "用户名或密码错误: " + e.getMessage());
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseData<Object> jwtSignatureExceptionHandler(SignatureException e){
        log.info("JWT-token错误: " + e);
        return ResponseData.fail(ReturnCodes.LOGIN_FAIL, "JWT-token错误: " + e.getMessage());
    }


    // 数据校验出错
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseData<Object> argumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.info("数据校验错误: " + e);
        return ResponseData.fail(ReturnCodes.DATA_VALIDATION_FAIL, "数据校验错误: " + e.getBindingResult().getFieldError().getDefaultMessage());
    }

    //todo:数据库出错


    /******通用异常捕获*******/
    @ExceptionHandler(RuntimeException.class)
    public ResponseData<Object> RuntimeExceptionHandler(RuntimeException e){
        log.error("运行时错误: " + e);
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseData.fail(ReturnCodes.SYSTEM_FAIL, null);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseData<Object> RestExceptionHandler(Throwable e){
        log.error("其他错误: " + e);
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseData.fail(ReturnCodes.SYSTEM_FAIL, null);
    }
}
