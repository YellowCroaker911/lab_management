package com.backend.utils.exception;

import lombok.Getter;


@Getter
public class BusinessException extends RuntimeException{
    private final String description;
    public BusinessException(String description){
        this.description = description;
    }
}
