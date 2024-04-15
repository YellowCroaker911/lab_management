package com.backend.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserImportDTO {
    @NotBlank(message = "用户名不能为空")
//    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$",message = "用户名只能包含英文字符和数字，不能超过20位")
    private String username;
    @NotBlank(message = "密码不能为空")
//    @Pattern(regexp = "^.{6,20}$",message = "密码必须在6到20位之间")
    private String password;
    @NotBlank(message = "用户角色不能为空")
//    @Pattern(regexp = "^[01]$",message = "用户角色必须在{0,1,2,3}内")
    private String role;
}
