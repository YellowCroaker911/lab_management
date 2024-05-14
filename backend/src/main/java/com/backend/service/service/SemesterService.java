package com.backend.service.service;

import com.backend.model.entity.Course;
import com.backend.model.entity.Semester;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Pfeistorch
* @description 针对表【semester(学年)】的数据库操作Service
* @createDate 2024-04-16 21:27:15
*/
public interface SemesterService extends IService<Semester> {
    void add(Semester semester);
    void delete(Long id);
    void update(Semester semester);
    Semester get(Long id);
    Semester getCurrent();
    List<Semester> getAll();
}
