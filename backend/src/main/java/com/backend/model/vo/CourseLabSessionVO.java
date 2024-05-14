package com.backend.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CourseLabSessionVO {
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
     * 课程名称
     */
    @NotBlank(message = "课程名称不能为空")
    private String name;

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
     * 实验室id，默认只是占位，已通过后（status=1）分配
     */
    @NotNull(message = "实验室id不能为空")
    private Long labId;

    /**
     * 实验员id
     */
    @NotNull(message="实验员id不能为空")
    private Long labAdminId;

    /**
     * 实验室类别，0-软件，1-硬件，2-网络
     */
    @NotNull(message="实验室类别不能为空")
    @Min(value = 0, message = "实验室类别必须在{0,1,2}内")
    @Max(value = 2, message = "实验室类别色必须在{0,1,2}内")
    private Integer type;

    /**
     * 实验室名称
     */
    private String labName;

    /**
     * 实验室编号，约定用3位数字表示
     */
    @NotNull(message="实验室编号不能为空")
    @Pattern(regexp = "^\\d{3}$",message = "实验室编号为3位数字")
    private String number;

    /**
     * 设备数量
     */
    @NotNull(message="设备数量不能为空")
    private Integer equipmentNum;

    /**
     * 节次，格式为num1-num2（表示节次为num1-num2节）
     */
    @NotEmpty(message = "节次不能为空")
    @Pattern(regexp = "\\d+-\\d+",message = "节次格式错误")
    private String session;
}
