package com.backend.model.dto.borrow;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class BorrowImportDTO {
    /**
     * 学生id
     */
    @NotNull(message = "学生id不能为空")
    private Long studentId;

    /**
     * 实验室id，默认只是占位，已通过后（status=1）分配
     */
    @NotNull(message = "实验室id不能为空")
    private Long labId;

    /**
     * 借用原因
     */
    private String reason;

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

    /**
     * 节次，格式为num1-num2（表示节次为num1-num2节）
     */
    @NotEmpty(message = "节次不能为空")
    @Pattern(regexp = "\\d+-\\d+",message = "节次格式错误")
    private String session;
}
