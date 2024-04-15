package com.backend.service.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.backend.service.mapper.UserMapper;
import com.backend.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// https://www.cnblogs.com/tyleaf/p/16919093.html

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new UserDetailsImpl(user);
    }
}
