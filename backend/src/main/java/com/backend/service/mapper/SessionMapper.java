package com.backend.service.mapper;

import com.backend.model.entity.Session;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Pfeistorch
* @description 针对表【session(节次)】的数据库操作Mapper
* @createDate 2024-04-16 21:27:17
* @Entity com.backend.model.entity.Session
*/
@Mapper
public interface SessionMapper extends BaseMapper<Session> {

}




