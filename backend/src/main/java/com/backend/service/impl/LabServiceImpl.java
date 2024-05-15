package com.backend.service.impl;

import com.backend.model.dto.lab.LabAvaliableGetDTO;
import com.backend.model.entity.Course;
import com.backend.model.entity.User;
import com.backend.service.mapper.UserMapper;
import com.backend.utils.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.model.entity.Lab;
import com.backend.service.service.LabService;
import com.backend.service.mapper.LabMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Pfeistorch
 * @description 针对表【lab(实验室)】的数据库操作Service实现
 * @createDate 2024-04-15 23:15:45
 */
@Service
public class LabServiceImpl extends ServiceImpl<LabMapper, Lab>
        implements LabService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    LabMapper labMapper;

    @Override
    public void add(Lab lab) {
        QueryWrapper<Lab> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("number", lab.getNumber());
        List<Lab> labs = labMapper.selectList(queryWrapper);
        if (!labs.isEmpty()) {
            throw new BusinessException("实验室地点已存在");
        }
        User user = userMapper.selectById(lab.getLabAdminId());
        if (user.getRole() != 3) {
            throw new BusinessException("实验员id错误");
        }
        labMapper.insert(lab);
    }

    @Override
    public void delete(Long id) {
        labMapper.deleteById(id);
    }

    @Override
    public void update(Lab lab) {
        User user = userMapper.selectById(lab.getLabAdminId());
        if (user.getRole() != 3) {
            throw new BusinessException("实验员id错误");
        }
        Lab qLab = labMapper.selectById(lab.getId());
        if (qLab == null) {
            throw new BusinessException("索引不存在");
        }
        BeanUtils.copyProperties(lab, qLab);
        labMapper.updateById(qLab);
    }

    @Override
    public Lab get(Long id) {
        return labMapper.selectById(id);
    }

    @Override
    public List<Lab> getAll() {
        return labMapper.selectList(null);
    }

    @Override
    public List<Lab> getByLabAdminId(Long labAdminId) {
        QueryWrapper<Lab> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lab_admin_id", labAdminId);
        return labMapper.selectList(queryWrapper);
    }

    @Override
    public List<Lab> getByType(Integer type) {
        QueryWrapper<Lab> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        return labMapper.selectList(queryWrapper);
    }

    @Override
    public List<Lab> getByNamePrefix(String namePrefix) {
        QueryWrapper<Lab> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", namePrefix);
        return labMapper.selectList(queryWrapper);
    }

    @Override
    public List<Lab> getByNumberPrefix(String numberPrefix) {
        QueryWrapper<Lab> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("number", numberPrefix);
        return labMapper.selectList(queryWrapper);
    }

    @Override
    public List<Lab> getByLeastEquipmentNum(Integer equipmentNum) {
        QueryWrapper<Lab> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("equipment_num", equipmentNum);
        return labMapper.selectList(queryWrapper);
    }

    @Override
    public List<Lab> getAvailable(LabAvaliableGetDTO labAvaliableGetDTO) {
        return labMapper.getAvailable(labAvaliableGetDTO.getType(), labAvaliableGetDTO.getSemester(),
                labAvaliableGetDTO.getStartingWeek(), labAvaliableGetDTO.getEndingWeek(),
                labAvaliableGetDTO.getSession(), labAvaliableGetDTO.getStudentNum());
    }
}




