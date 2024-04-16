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
 * 实验室
 * @TableName lab
 */
@TableName(value ="lab")
@Data
public class Lab implements Serializable {
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
     * 实验室地点，约定用3位数字表示
     */
    private String location;

    /**
     * 设备数量
     */
    private Integer equipmentNum;

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