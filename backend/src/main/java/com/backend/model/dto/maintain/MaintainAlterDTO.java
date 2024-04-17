package com.backend.model.dto.maintain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MaintainAlterDTO {
    /**
     * 维修记录id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
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
