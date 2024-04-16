package com.backend.service.mapper;

import com.backend.model.entity.Semester;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Pfeistorch
* @description 针对表【semester(学年)】的数据库操作Mapper
* @createDate 2024-04-16 21:27:15
* @Entity com.backend.model.entity.Semester
*/
@Mapper
public interface SemesterMapper extends BaseMapper<Semester> {

}




