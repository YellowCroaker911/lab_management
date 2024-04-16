package com.backend.utils.exception;


import com.backend.utils.response.ResponseData;
import com.backend.utils.response.ReturnCodes;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.security.SignatureException;
import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 自定义业务逻辑出错
    @ExceptionHandler(BusinessException.class)
    public ResponseData<Object> customExceptionHandler(BusinessException e){
        log.info("自定义业务逻辑错误: " + e);
        return ResponseData.fail(ReturnCodes.BUSINESS_FAIL,"自定义业务逻辑错误: " + e.getDescription());
    }

    // 认证出错
    @ExceptionHandler(AuthenticationException.class)
    public ResponseData<Object> jwtSignatureExceptionHandler(AuthenticationException e){
        log.info("用户名或密码错误: " + e);
        return ResponseData.fail(ReturnCodes.AUTHENTICATION_FAIL, "用户名或密码错误: " + e.getMessage());
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseData<Object> jwtSignatureExceptionHandler(SignatureException e){
        log.info("JWT-token错误: " + e);
        return ResponseData.fail(ReturnCodes.AUTHENTICATION_FAIL, "JWT-token错误: " + e.getMessage());
    }


    // 数据传递出错
    @ExceptionHandler(JsonMappingException.class)
    public ResponseData<Object> mismatchedInputExceptionHandler(MismatchedInputException e) {
        log.info("Json映射错误: " + e);
        return ResponseData.fail(ReturnCodes.DATA_TRANSMISSION_FAIL, "Json映射错误: " + e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseData<Object> argumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.info("数据校验错误: " + e);
        return ResponseData.fail(ReturnCodes.DATA_TRANSMISSION_FAIL, "数据校验错误: " + e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseData<Object> constraintViolationExceptionHandler(ConstraintViolationException e) {
        log.info("数据校验错误: " + e);
        return ResponseData.fail(ReturnCodes.DATA_TRANSMISSION_FAIL, "数据校验错误: " + e.getMessage());
    }

    // 数据库出错
    @ExceptionHandler(SQLException.class)
    public ResponseData<Object> SQLExceptionHandler(SQLException e) {
        log.info("数据库错误: " + e);
        return ResponseData.fail(ReturnCodes.DATABASE_FAIL, "数据库错误: " + e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseData<Object> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException e) {
        log.info("数据库操作违反完整性约束错误: " + e);
        return ResponseData.fail(ReturnCodes.DATABASE_FAIL, "数据库操作违反完整性约束错误: " + e.getMessage());
    }


    /******通用异常捕获*******/
    // 从安全角度来说，其他系统错误的信息不应该返回
    @ExceptionHandler(RuntimeException.class)
    public ResponseData<Object> RuntimeExceptionHandler(RuntimeException e){
        log.error("运行时错误: " + e);
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseData.fail(ReturnCodes.SYSTEM_FAIL, "运行时错误: " + e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseData<Object> RestExceptionHandler(Throwable e){
        log.error("其他错误: " + e);
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseData.fail(ReturnCodes.SYSTEM_FAIL, "其他错误: " + e.getMessage());
    }
}
