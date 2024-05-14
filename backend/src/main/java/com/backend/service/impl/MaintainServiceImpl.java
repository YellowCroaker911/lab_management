package com.backend.service.impl;

import com.backend.model.entity.*;
import com.backend.service.mapper.*;
import com.backend.utils.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.service.service.MaintainService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @author Pfeistorch
 * @description 针对表【repair(维修)】的数据库操作Service实现
 * @createDate 2024-04-15 23:15:43
 */
@Service
public class MaintainServiceImpl extends ServiceImpl<MaintainMapper, Maintain>
        implements MaintainService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    LabMapper labMapper;

    @Autowired
    SemesterMapper semesterMapper;

    @Autowired
    SessionMapper sessionMapper;

    @Autowired
    MaintainMapper maintainMapper;

    @Override
    public void add(Maintain maintain) {
        User user = userMapper.selectById(maintain.getTeacherId());
        if (user.getRole() != 2) {
            throw new BusinessException("教师id错误");
        }

        List<Maintain> maintains = maintainMapper.getSame(maintain.getTeacherId(), maintain.getLabId(), new Date());
        if (!maintains.isEmpty()) {
            throw new BusinessException("当天维修记录已存在");
        }

        maintainMapper.insert(maintain);
    }

    @Override
    public void delete(Long id) {
        maintainMapper.deleteById(id);
    }

    @Override
    public void update(Maintain maintain) {

        List<Maintain> maintains = maintainMapper.getSame(maintain.getTeacherId(), maintain.getLabId(), new Date());
        if (!maintains.isEmpty()) {
            throw new BusinessException("当天维修记录已存在");
        }

        Maintain qMaintain = maintainMapper.selectById(maintain.getId());
        if (qMaintain == null) {
            throw new BusinessException("索引不存在");
        }

        if (qMaintain.getStatus() == 1) {
            if (maintain.getStatus() != 2) {
                throw new BusinessException("已正在维修，无法修改");
            }
        }
        if (qMaintain.getStatus() == 2) {
            if (maintain.getStatus() != 2) {
                throw new BusinessException("已维修完毕，无法修改");
            }
        }

        BeanUtils.copyProperties(maintain, qMaintain);
        maintainMapper.updateById(qMaintain);
    }

    @Override
    public Maintain get(Long id) {
        return maintainMapper.selectById(id);
    }

    @Override
    public List<Maintain> getAll() {
        return maintainMapper.selectList(null);
    }

    @Override
    public List<Maintain> getByTeacherId(Long teacherId) {
        QueryWrapper<Maintain> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", teacherId);
        return maintainMapper.selectList(queryWrapper);
    }

    @Override
    public List<Maintain> getByLabId(Long labId) {
        QueryWrapper<Maintain> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lab_id", labId);
        return maintainMapper.selectList(queryWrapper);
    }

    @Override
    public List<Maintain> getByStatus(Integer status) {
        QueryWrapper<Maintain> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", status);
        return maintainMapper.selectList(queryWrapper);
    }

    @Override
    public List<Maintain> getByLabAdminId(Long labAdminId) {
        return maintainMapper.getByLabAdminId(labAdminId);
    }
}




