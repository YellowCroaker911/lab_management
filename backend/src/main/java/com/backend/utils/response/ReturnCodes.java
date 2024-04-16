package com.backend.utils.response;
import lombok.Getter;

@Getter
public enum ReturnCodes {

    SUCCESS(100,"成功"),
    BUSINESS_FAIL(101,"自定义业务逻辑出错"),
    AUTHENTICATION_FAIL(101,"认证出错"),
    DATA_TRANSMISSION_FAIL(102, "数据传递出错"),
    DATABASE_FAIL(103,"数据库出错"),
    SYSTEM_FAIL(104, "其他系统错误");
    private final int code;         // 状态码
    private final String message;   // 状态码信息
    ReturnCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
