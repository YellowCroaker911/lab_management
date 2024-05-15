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
 * 课程
 * @TableName course
 */
@TableName(value ="course")
@Data
public class Course implements Serializable {
    /**
     * 课程id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 教师id
     */
    @NotNull(message = "教师id不能为空")
    private Long teacherId;

    /**
     * 实验室id，默认只是占位，已通过后（status=1）分配
     */
    @NotNull(message = "实验室id不能为空")
    private Long labId;

    /**
     * 需求实验室类别，0-软件，1-硬件，2-网络
     */
    @NotNull(message = "需求实验室类别不能为空")
    @Min(value = 0, message = "实验室类别必须在{0,1,2}内")
    @Max(value = 2, message = "实验室类别色必须在{0,1,2}内")
    private Integer type;

    /**
     * 课程名称
     */
    @NotBlank(message = "课程名称不能为空")
    private String name;

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
     * 节次，格式为day num1-num2（表示星期day，节次为num1-num2节）
     */
    @NotEmpty(message = "节次不能为空")
    @Pattern(regexp = "\\d+ \\d+-\\d+",message = "节次格式错误")
    private String session;

    /**
     * 学生人数
     */
    @NotNull(message = "学生人数不能为空")
    private Integer studentNum;

    /**
     * 学生专业
     */
    private String major;

    /**
     * 学生班级
     */
    private String clazz;

    /**
     * 排课状态，0-未排课，1-已排课
     */
    @NotNull(message = "排课状态不能为空")
    @Min(value = 0, message = "排课状态必须在{0,1}内")
    @Max(value = 1, message = "排课状态必须在{0,1}内")
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