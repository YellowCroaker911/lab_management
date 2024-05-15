package com.backend.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class AvailableLabSessionVO {
    /**
     * 实验室id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 实验员id
     */
    private Long labAdminId;

    /**
     * 实验室类别，0-软件，1-硬件，2-网络
     */
    private Integer type;

    /**
     * 实验室名称
     */
    private String name;

    /**
     * 实验室编号，约定用3位数字表示
     */
    private String number;

    /**
     * 设备数量
     */
    private Integer equipmentNum;

    /**
     * 节次，格式为day num1-num2（表示星期day，节次为num1-num2节）
     */
    @NotEmpty(message = "节次不能为空")
    @Pattern(regexp = "\\d+ \\d+-\\d+",message = "节次格式错误")
    private String session;
}
