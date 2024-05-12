package com.backend.model.dto.semester;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SemesterAlterDTO {
    /**
     * 学期id
     */
    @NotNull(message = "学期id不能为空")
    private Long id;

    /**
     * 周，18-20
     */
    @NotEmpty(message = "学期周数不能为空")
    @Pattern(regexp = "^(18|19|20)$",message = "学期周数格式错误")
    private String week;

    /**
     * 当前学期状态，0-否，1-是
     */
    @NotNull(message = "当前学期状态不能为空")
    @Min(value = 0, message = "当前学期状态必须在{0,1}内")
    @Max(value = 1, message = "当前学期状态必须在{0,1}内")
    private Integer status;
}
