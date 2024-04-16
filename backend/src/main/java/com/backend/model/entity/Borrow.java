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
    private Long studentId;

    /**
     * 实验室id，默认只是占位，已通过后（status=1）分配
     */
    private Long labId;

    /**
     * 借用原因
     */
    private String reason;

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
     * 审核状态，0-未审核，1-通过，2-驳回
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