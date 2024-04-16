package com.backend.service.service;

import com.backend.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Pfeistorch
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-04-15 19:37:53
*/
public interface UserService extends IService<User> {
    void add(User user);
    void delete(Long id);
    void update(User user);
    User get(Long id);
    List<User> getAll();
    List<User> getByRole(Integer role);
    List<User> getByNamePrefix(String namePrefix);
    User getLoginUser();
    String getJwtToken(String userName,String password);
}
