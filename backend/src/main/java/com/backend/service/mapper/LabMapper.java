package com.backend.service.mapper;

import com.backend.model.entity.Lab;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Pfeistorch
* @description 针对表【lab(实验室)】的数据库操作Mapper
* @createDate 2024-04-15 23:15:45
* @Entity com.backend.model.entity.Lab
*/
@Mapper
public interface LabMapper extends BaseMapper<Lab> {

}




