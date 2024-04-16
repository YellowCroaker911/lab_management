package com.backend.model.dto.course;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CourseAdmitDTO {
    /**
     * 课程id
     */
    @NotNull(message = "课程id不能为空")
    private Long id;
    /**
     * 实验室id，默认只是占位，已通过后（status=1）分配
     */
    @NotNull(message = "实验室id不能为空")
    private Long labId;
}
