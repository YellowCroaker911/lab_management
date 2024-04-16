package com.backend.model.dto.user;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserImportDTO {
    @NotEmpty(message = "用户名不能为空")
//    @Pattern(regexp = "^[a-zA-Z0-9]{1,20}$",message = "用户名只能包含英文字符和数字，不能超过20位")
    private String username;
    @NotEmpty(message = "密码不能为空")
//    @Pattern(regexp = "^.{6,20}$",message = "密码必须在6到20位之间")
    private String password;
    @NotNull(message = "用户角色不能为空")
    @Min(value = 0, message = "用户角色必须在{0,1,2,3}内")
    @Max(value = 3, message = "用户角色必须在{0,1,2,3}内")
    private Integer role;

    private String name;
    private String major;
    private String clazz;
    private String title;
}
