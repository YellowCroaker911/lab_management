package com.backend.model.dto;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class UserAlterPasswordDTO {
    @NotNull(message = "用户id不能为空")
    private Long id;
    @NotEmpty(message = "密码不能为空")
    //    @Pattern(regexp = "^.{6,20}$",message = "密码必须在6到20位之间")
    private String password;
}
