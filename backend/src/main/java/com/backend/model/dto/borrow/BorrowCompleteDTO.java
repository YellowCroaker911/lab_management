package com.backend.model.dto.borrow;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class BorrowCompleteDTO {
    /**
     * 借用记录id
     */
    @NotNull(message = "借用记录id不能为空")
    private Long id;

    /**
     * 审核状态，0-未审核，1-通过，2-驳回，3-使用完毕
     */
    @NotNull(message = "审核状态不能为空")
    @Min(value = 3, message = "审核状态必须在{3}内")
    @Max(value = 3, message = "审核状态必须在{3}内")
    private Integer status;
}
