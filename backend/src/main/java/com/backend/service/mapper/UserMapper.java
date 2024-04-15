package com.backend.service.mapper;

import com.backend.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Pfeistorch
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2024-04-15 19:37:53
* @Entity com.backend.model.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




