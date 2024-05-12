package com.backend.model.dto.session;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class SessionAlterDTO {
    /**
     * 节次id
     */
    @NotNull(message = "节次id不能为空")
    private Long id;
}
