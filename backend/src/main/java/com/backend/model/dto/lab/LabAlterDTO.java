package com.backend.model.dto.lab;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class LabAlterDTO {
    @NotNull(message="实验员id不能为空")
    private Long labAdminId;
    @NotNull(message="实验室类别不能为空")
    @Min(value = 0, message = "实验室类别必须在{0,1,2}内")
    @Max(value = 2, message = "实验室类别色必须在{0,1,2}内")
    private Integer type;
    private String name;
    @NotNull(message="设备数量不能为空")
    private Integer equipmentNum;
}
