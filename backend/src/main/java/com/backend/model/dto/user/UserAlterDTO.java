package com.backend.model.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserAlterDTO {
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Long id;
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
