package com.backend.model.dto.lab;
import lombok.Data;
import javax.validation.constraints.*;

@Data
public class LabImportDTO {
    /**
     * 实验员id
     */
    @NotNull(message="实验员id不能为空")
    private Long labAdminId;
    /**
     * 实验室类别
     */
    @NotNull(message="实验室类别不能为空")
    @Min(value = 0, message = "实验室类别必须在{0,1,2}内")
    @Max(value = 2, message = "实验室类别色必须在{0,1,2}内")
    private Integer type;
    private String name;
    /**
     * 实验室地点，约定用3位数字表示
     */
    @NotNull(message="实验室地点不能为空")
    @Pattern(regexp = "^\\d{3}$",message = "实验室地点为3位数字")
    private String location;
    /**
     * 设备数量
     */
    @NotNull(message="设备数量不能为空")
    private Integer equipmentNum;
}
