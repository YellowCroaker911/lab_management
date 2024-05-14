package com.backend.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AvailableLabSessionDTO {
    /**
     * 需求实验室类别，0-软件，1-硬件，2-网络
     */
    @NotNull(message = "需求实验室类别不能为空")
    @Min(value = 0, message = "实验室类别必须在{0,1,2}内")
    @Max(value = 2, message = "实验室类别色必须在{0,1,2}内")
    private Integer type;

    /**
     * 学期，格式为year1-year2-num（year1-year2为学年，num为1或2）
     */
    @NotEmpty(message = "学期不能为空")
    @Pattern(regexp = "^\\d{4}-\\d{4}-(1|2)$",message = "学期格式错误")
    private String semester;

    /**
     * 起始周
     */
    @NotEmpty(message = "起始周不能为空")
    @Pattern(regexp = "^(?:[1-9]|1[0-9]|20)$",message = "起始周格式错误")
    private String startingWeek;

    /**
     * 结束周
     */
    @NotEmpty(message = "结束周不能为空")
    @Pattern(regexp = "^(?:[1-9]|1[0-9]|20)$",message = "结束周格式错误")
    private String endingWeek;

    /**
     * 学生人数
     */
    @NotNull(message = "学生人数不能为空")
    private Integer studentNum;
}
