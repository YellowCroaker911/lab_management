package com.backend.model.dto.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserResetPasswordDTO {
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Long id;
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = "^.{6,20}$",message = "密码必须在6到20位之间")
    private String password;
}
