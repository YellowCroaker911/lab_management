package com.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.model.entity.Course;
import com.backend.service.service.CourseService;
import com.backend.service.mapper.CourseMapper;
import org.springframework.stereotype.Service;

/**
* @author Pfeistorch
* @description 针对表【course(课程)】的数据库操作Service实现
* @createDate 2024-04-15 23:15:47
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

}




