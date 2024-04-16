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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
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
        implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    @Override
    public void add(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            throw new BusinessException("用户名已存在");
        }
        if (user.getRole() == 0) {
            if (user.getName() != null || user.getMajor() != null || user.getClazz() != null || user.getTitle() != null) {
                throw new BusinessException("管理员角色错误字段赋值");
            }
        } else if (user.getRole() == 1) {
            if (user.getTitle() != null) {
                throw new BusinessException("学生角色错误字段赋值");
            }
        } else if (user.getRole() == 2) {
            if (user.getName() != null || user.getMajor() != null || user.getClazz() != null) {
                throw new BusinessException("教师角色错误字段赋值");
            }
        } else if (user.getRole() == 3) {
            if (user.getName() != null || user.getMajor() != null || user.getClazz() != null) {
                throw new BusinessException("实验员角色错误字段赋值");
            }
        }
        User qUser = new User();
        BeanUtils.copyProperties(user, qUser);
        qUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(qUser);
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public User get(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> getAll(Integer role) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (role == null) {
            return userMapper.selectList(null);
        } else {
            queryWrapper.eq("role", role);
            return userMapper.selectList(queryWrapper);
        }
    }

    @Override
    public List<User> getByNamePrefix(String namePrefix) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", namePrefix);
        return userMapper.selectList(queryWrapper);
    }


    @Override
    public User getLoginUser() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails.getUser();
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
    public void alter(User user) {
        User qUser = userMapper.selectById(user.getId());
        BeanUtils.copyProperties(user, qUser);
        if (user.getPassword() != null) {
            qUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userMapper.updateById(qUser);
    }
}




