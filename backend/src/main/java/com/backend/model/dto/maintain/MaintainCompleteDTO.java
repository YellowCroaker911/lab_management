package com.backend.model.dto.maintain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class MaintainCompleteDTO {
    /**
     * 维修记录id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 维修状态，0-未维修，1-维修中，2-已维修
     */
    @NotNull(message = "维修状态不能为空")
    @Min(value = 2, message = "维修状态必须在{2}内")
    @Max(value = 2, message = "维修状态必须在{2}内")
    private Integer status;

    /**
     * 维修说明
     */
    private String maintenanceDescription;
}
