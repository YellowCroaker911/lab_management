package com.backend.model.vo;

import lombok.Data;

@Data
public class UserLoginVO {
    /**
     * JWT-token
     */
    private String jwtToken;
}
