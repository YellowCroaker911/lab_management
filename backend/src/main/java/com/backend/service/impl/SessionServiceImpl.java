package com.backend.service.impl;

import com.backend.utils.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.model.entity.Session;
import com.backend.service.service.SessionService;
import com.backend.service.mapper.SessionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Pfeistorch
* @description 针对表【session(节次)】的数据库操作Service实现
* @createDate 2024-04-16 21:27:17
*/
@Service
public class SessionServiceImpl extends ServiceImpl<SessionMapper, Session>
    implements SessionService{

    @Autowired
    SessionMapper sessionMapper;

    @Override
    public void add(Session session) {
        QueryWrapper<Session> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("session", session.getSession());
        List<Session> sessions = sessionMapper.selectList(queryWrapper);
        if (!sessions.isEmpty()) {
            throw new BusinessException("节次已存在");
        }
        sessionMapper.insert(session);
    }

    @Override
    public void delete(Long id) {
        sessionMapper.deleteById(id);
    }

    @Override
    public void update(Session Session) {
        Session qSession = sessionMapper.selectById(Session.getId());
        if (qSession == null) {
            throw new BusinessException("索引不存在");
        }
        BeanUtils.copyProperties(Session, qSession);
        sessionMapper.updateById(qSession);
    }

    @Override
    public Session get(Long id) {
        return sessionMapper.selectById(id);
    }

    @Override
    public List<Session> getAll() {
        return sessionMapper.selectList(null);
    }

}




