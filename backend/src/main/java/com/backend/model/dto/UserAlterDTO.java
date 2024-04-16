package com.backend.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserAlterDTO {
    @NotNull(message = "用户id不能为空")
    private Long id;
    private String name;
    private String major;
    private String clazz;
    private String title;
}
