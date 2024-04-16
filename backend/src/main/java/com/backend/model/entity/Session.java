package com.backend.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 节次
 * @TableName session
 */
@TableName(value ="session")
@Data
public class Session implements Serializable {
    /**
     * 节次id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 节次，格式为num1-num2（表示节次为num1-num2节）
     */
    private String session;

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