package com.backend.service.impl;

import com.backend.service.utils.UserDetailsImpl;
import com.backend.utils.JwtUtil;
import com.backend.utils.exception.BusinessException;
import com.backend.utils.response.ReturnCodes;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.model.entity.User;
import com.backend.service.service.UserService;
import com.backend.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Pfeistorch
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-04-15 19:37:53
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    @Override
    public void imPort(String userName, String password, int role) {
        User user = new User();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            throw new BusinessException("用户名已存在");
        }
        user.setUsername(userName);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        userMapper.insert(user);
    }

    @Override
    public String getJwtToken(String userName, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();
        User user = loginUser.getUser();
        return JwtUtil.createJWT(user.getId().toString());
    }

    @Override
    public User getLoginUser() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails.getUser();
    }
}




