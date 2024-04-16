package com.backend.model.dto.semester;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class SemesterAlterDTO {
    /**
     * 周，18-20
     */
    @NotEmpty(message = "学期周数不能为空")
    @Pattern(regexp = "^(18|29|20)$",message = "学期周数格式错误")
    private String week;
}
