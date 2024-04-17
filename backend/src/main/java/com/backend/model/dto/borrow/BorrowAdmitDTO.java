package com.backend.model.dto.borrow;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BorrowAdmitDTO {
    /**
     * 借用记录id
     */
    @NotNull(message = "借用记录id不能为空")
    private Long id;
}
