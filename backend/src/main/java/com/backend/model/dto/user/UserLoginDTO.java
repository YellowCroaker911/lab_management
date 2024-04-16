package com.backend.model.dto.user;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserLoginDTO {
    /**
     * 账号，约定管理员以a开头，学生以s开头，教师以t开头，实验员以l开头
     */
    @NotEmpty(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$",message = "用户名只能包含英文字符和数字，不能超过20位")
    private String username;
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = "^.{6,20}$",message = "密码必须在6到20位之间")
    private String password;
}
