package com.backend.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 学年
 * @TableName semester
 */
@TableName(value ="semester")
@Data
public class Semester implements Serializable {
    /**
     * 学年id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 学期，格式为year1-year2-num（year1-year2为学年，num为1或2）
     */
    private String semester;

    /**
     * 周，18-20
     */
    private String week;

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