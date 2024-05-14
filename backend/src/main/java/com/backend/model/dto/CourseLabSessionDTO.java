package com.backend.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class CourseLabSessionDTO {
    /**
     * 学期，格式为year1-year2-num（year1-year2为学年，num为1或2）
     */
    @NotEmpty(message = "学期不能为空")
    @Pattern(regexp = "^\\d{4}-\\d{4}-(1|2)$",message = "学期格式错误")
    private String semester;

    /**
     * 周，1-20
     */
    @NotEmpty(message = "周次不能为空")
    @Pattern(regexp = "^(?:[1-9]|1[0-9]|20)$",message = "周次格式错误")
    private String week;
}
