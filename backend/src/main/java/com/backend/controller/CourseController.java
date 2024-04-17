package com.backend.controller;

import com.backend.model.dto.course.CourseAdmitDTO;
import com.backend.model.dto.course.CourseImportDTO;
import com.backend.model.dto.course.CourseAlterDTO;
import com.backend.model.entity.Course;
import com.backend.service.service.CourseService;
import com.backend.utils.response.ResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;
import java.util.List;

@Validated
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    // 导入课程
    @PostMapping("/importCourse")
    public ResponseData<Object> importCourse(@RequestBody @Validated CourseImportDTO courseImportDTO) {
        Course course = new Course();
        BeanUtils.copyProperties(courseImportDTO, course);
        courseService.add(course);
        return ResponseData.success(null, null);
    }

    // 移除课程
    @PostMapping("/removeCourse")
    public ResponseData<Object> removeCourse(@RequestParam @NotNull(message = "课程id不能为空") Long id) {
        courseService.delete(id);
        return ResponseData.success(null, null);
    }

    // 修改课程信息
    @PostMapping("/alterCourse")
    public ResponseData<Object> alterCourse(@RequestBody @Validated CourseAlterDTO courseAlterDTO) {
        Course course = new Course();
        BeanUtils.copyProperties(courseAlterDTO, course);
        courseService.update(course);
        return ResponseData.success(null, null);
    }

    // 审核通过课程
    @PostMapping("/admitCourse")
    public ResponseData<Object> admitCourse(@RequestParam @Validated CourseAdmitDTO courseAdmitDTO) {
        Course course = new Course();
        BeanUtils.copyProperties(courseAdmitDTO, course);
        course.setStatus(1);
        courseService.update(course);
        return ResponseData.success(null, null);
    }

    // 根据ID获取课程
    @GetMapping("/getCourseById")
    public ResponseData<Object> getCourseById(@RequestParam @NotNull(message = "课程id不能为空") Long id) {
        Course course = courseService.get(id);
        return ResponseData.success(course, null);
    }

    // 获取所有课程
    @GetMapping("/getAllCourses")
    public ResponseData<Object> getAllCourses() {
        List<Course> courses = courseService.getAll();
        return ResponseData.success(courses, null);
    }

    // 根据教师id获取课程
    @GetMapping("/getCoursesByTeacherId")
    public ResponseData<Object> getCoursesByTeacherId(@RequestParam @NotNull(message = "教师id不能为空") Long teacherId){
        List<Course> courses = courseService.getByTeacherId(teacherId);
        return ResponseData.success(courses, null);
    }

    // 根据实验室id获取课程
    @GetMapping("/getCoursesByLabId")
    public ResponseData<Object> getCoursesByLabId(@RequestParam @NotNull(message = "实验室id不能为空") Long labId){
        List<Course> courses = courseService.getByLabId(labId);
        return ResponseData.success(courses, null);
    }

    // 根据需求实验室类型获取课程
    @GetMapping("/getCoursesByType")
    public ResponseData<Object> getCoursesByType(@RequestParam @NotNull(message = "需求实验室类型不能为空")
                                              @Min(value = 0, message = "实验室类型必须在{0,1,2}内")
                                              @Max(value = 2, message = "实验室类型必须在{0,1,2}内") Integer type){
        List<Course> courses = courseService.getByType(type);
        return ResponseData.success(courses, null);
    }

    // 根据名称前缀获取课程
    @GetMapping("/getCoursesByNamePrefix")
    public ResponseData<Object> getCoursesByNamePrefix(@RequestParam @NotBlank(message = "名称前缀不能为空") String namePrefix){
        List<Course> courses = courseService.getByNamePrefix(namePrefix);
        return ResponseData.success(courses, null);
    }

    // 根据学期获取课程
    @GetMapping("/getCoursesBySemester")
    public ResponseData<Object> getCoursesBySemester(@RequestParam @NotBlank(message = "学期不能为空")
                                                  @Pattern(regexp = "^\\d{4}-\\d{4}-(1|2)$",message = "学期格式错误") String semester){
        List<Course> courses = courseService.getBySemester(semester);
        return ResponseData.success(courses, null);
    }

    // 根据星期获取课程
    @GetMapping("/getCoursesByWeek")
    public ResponseData<Object> getCoursesByWeek(@RequestParam @NotBlank(message = "星期不能为空")
                                                     @Pattern(regexp = "^(?:[1-9]|1[0-9]|20)$",message = "星期格式错误") String week){
        List<Course> courses = courseService.getByWeek(week);
        return ResponseData.success(courses, null);
    }

    // 根据排课状态获取课程
    @GetMapping("/getCoursesByStatus")
    public ResponseData<Object> getCoursesByStatus(@RequestParam @NotNull(message = "排课状态不能为空")
                                                @Min(value = 0, message = "排课状态必须在{0,1}内")
                                                @Max(value = 1, message = "排课状态必须在{0,1}内")
                                                Integer status){
        List<Course> courses = courseService.getByStatus(status);
        return ResponseData.success(courses, null);
    }

}
