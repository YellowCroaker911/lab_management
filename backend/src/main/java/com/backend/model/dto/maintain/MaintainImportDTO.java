package com.backend.model.dto.maintain;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MaintainImportDTO {

    /**
     * 教师id
     */
    @NotNull(message = "教师id不能为空")
    private Long teacherId;

    /**
     * 实验室id
     */
    @NotNull(message = "实验室id不能为空")
    private Long labId;

    /**
     * 故障描述
     */
    private String faultDescription;
}
