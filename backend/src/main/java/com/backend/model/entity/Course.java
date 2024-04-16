package com.backend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

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
    private Long teacherId;

    /**
     * 实验室id，默认只是占位，已通过后（status=1）分配
     */
    private Long labId;

    /**
     * 需求实验室类别，0-软件，1-硬件，2-网络
     */
    private Integer type;

    /**
     * 学期，格式为year1-year2-num（year1-year2为学年，num为1或2）
     */
    private String semester;

    /**
     * 起始周
     */
    private String startingWeek;

    /**
     * 结束周
     */
    private String endingWeek;

    /**
     * 节次，格式为num1-num2（表示节次为num1-num2节）
     */
    private String session;

    /**
     * 学生人数
     */
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