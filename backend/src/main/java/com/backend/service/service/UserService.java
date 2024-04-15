package com.backend.service.service;

import com.backend.model.entity.User;
import com.backend.utils.response.ResponseData;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Pfeistorch
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-04-15 19:37:53
*/
public interface UserService extends IService<User> {
    void imPort(String userName,String password,int role);
    String getJwtToken(String userName,String password);
    User getLoginUser();
}
