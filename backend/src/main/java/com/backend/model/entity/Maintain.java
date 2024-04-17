package com.backend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 维修
 * @TableName maintain
 */
@TableName(value ="maintain")
@Data
public class Maintain implements Serializable {
    /**
     * 维修记录id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 教师id
     */
    @NotNull(message = "教师id不能为空")
    private Long teacherId;

    /**
     * 实验室id
     */
    @NotNull(message = "实验室id不能为空")
    private Long labId;

    /**
     * 故障描述
     */
    private String faultDescription;

    /**
     * 维修说明
     */
    private String maintenanceDescription;

    /**
     * 维修状态，0-未维修，1-维修中，2-已维修
     */
    @NotNull(message = "维修状态不能为空")
    @Min(value = 0, message = "维修状态必须在{0,1,2}内")
    @Max(value = 2, message = "维修状态必须在{0,1,2}内")
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