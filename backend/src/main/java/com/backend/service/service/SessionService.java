package com.backend.service.service;

import com.backend.model.entity.Semester;
import com.backend.model.entity.Session;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Pfeistorch
* @description 针对表【session(节次)】的数据库操作Service
* @createDate 2024-04-16 21:27:17
*/
public interface SessionService extends IService<Session> {
    void add(Session session);
    void delete(Long id);
    void update(Session session);
    Session get(Long id);
    List<Session> getAll();
}
