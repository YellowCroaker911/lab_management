package com.backend.model.dto.session;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class SessionImportDTO {
    /**
     * 节次，格式为num1-num2（表示节次为num1-num2节）
     */
    @NotEmpty(message = "节次不能为空")
    @Pattern(regexp = "\\d+-\\d+",message = "节次格式错误")
    private String session;
}
