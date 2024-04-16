package com.backend.service.service;

import com.backend.model.entity.Course;
import com.backend.model.entity.Lab;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Pfeistorch
* @description 针对表【course(课程)】的数据库操作Service
* @createDate 2024-04-15 23:15:47
*/
public interface CourseService extends IService<Course> {
    void add(Course course);
    void delete(Long id);
    void update(Course course);
    Course get(Long id);
    List<Course> getAll();
    List<Course> getByTeacherId(Long teacherId);
    List<Course> getByLabId(Long labId);
    List<Course> getByType(Integer type);
    List<Course> getByNamePrefix(String namePrefix);
    List<Course> getBySemester(String semester);
    List<Course> getByStatus(Integer status);
}
