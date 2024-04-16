package com.backend.model.dto.user;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserImportDTO {
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
    /**
     * 用户角色，0-管理员，1-学生，2-教师，3-实验员
     */
    @NotNull(message = "用户角色不能为空")
    @Min(value = 0, message = "用户角色必须在{0,1,2,3}内")
    @Max(value = 3, message = "用户角色必须在{0,1,2,3}内")
    private Integer role;
    /**
     * 姓名
     */
    private String name;

    /**
     * 专业
     */
    private String major;

    /**
     * 班级
     */
    private String clazz;

    /**
     * 职称
     */
    private String title;
}
