package com.backend.model.dto.user;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class UserAlterPasswordDTO {
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Long id;

    /**
     * 旧密码
     */
    @NotEmpty(message = "旧密码不能为空")
    @Pattern(regexp = "^.{6,20}$",message = "旧密码必须在6到20位之间")
    private String oldPassword;

    /**
     * 密码
     */
    @NotEmpty(message = "新密码不能为空")
    @Pattern(regexp = "^.{6,20}$",message = "新密码必须在6到20位之间")
    private String Password;
}
