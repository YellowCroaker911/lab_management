package com.backend.utils.response;
import lombok.Getter;

@Getter
public enum ReturnCodes {

    SUCCESS(100,"成功"),
    BUSINESS_FAIL(101,"业务逻辑出错"),
    LOGIN_FAIL(101,"登录出错"),
    DATA_VALIDATION_FAIL(102, "数据校验出错"),
    DATAVASE_OPERATE_FAIL(103,"数据库操作出错"),
    SYSTEM_FAIL(104, "系统出错");
    private final int code;         // 状态码
    private final String message;   // 状态码信息
    ReturnCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
