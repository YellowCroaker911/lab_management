package com.backend.service.mapper;

import com.backend.model.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Pfeistorch
* @description 针对表【course(课程)】的数据库操作Mapper
* @createDate 2024-04-15 23:15:47
* @Entity com.backend.model.entity.Course
*/
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

}




