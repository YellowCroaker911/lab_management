package com.backend.utils.exception;

import com.backend.utils.response.ReturnCodes;
import lombok.Getter;


@Getter
public class BusinessException extends RuntimeException{
    private final String description;
    public BusinessException(String description){
        this.description = description;
    }
}
