package com.backend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * 借用
 * @TableName borrow
 */
@TableName(value ="borrow")
@Data
public class Borrow implements Serializable {
    /**
     * 借用记录id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 学生id
     */
    @NotNull(message = "学生id不能为空")
    private Long studentId;

    /**
     * 实验室id，默认只是占位，已通过后（status=1）分配
     */
    @NotNull(message = "实验室id不能为空")
    private Long labId;

    /**
     * 借用原因
     */
    private String reason;

    /**
     * 学期，格式为year1-year2-num（year1-year2为学年，num为1或2）
     */
    @NotEmpty(message = "学期不能为空")
    @Pattern(regexp = "^\\d{4}-\\d{4}-(1|2)$",message = "学期格式错误")
    private String semester;

    /**
     * 周，1-20
     */
    @NotEmpty(message = "周次不能为空")
    @Pattern(regexp = "^(?:[1-9]|1[0-9]|20)$",message = "周次格式错误")
    private String week;

    /**
     * 节次，格式为day num1-num2（表示星期day，节次为num1-num2节）
     */
    @NotEmpty(message = "节次不能为空")
    @Pattern(regexp = "\\d+ \\d+-\\d+",message = "节次格式错误")
    private String session;

    /**
     * 审核状态，0-未审核，1-通过，2-驳回，3-使用完毕
     */
    @NotNull(message = "审核状态不能为空")
    @Min(value = 0, message = "审核状态必须在{0,1,2,3}内")
    @Max(value = 3, message = "审核状态必须在{0,1,2,3}内")
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    /**
     * 是否删除
     */
//    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}