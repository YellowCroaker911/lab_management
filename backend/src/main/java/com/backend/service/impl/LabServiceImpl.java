package com.backend.service.impl;

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
    implements LabService{

    @Autowired
    LabMapper labMapper;

    @Override
    public void add(Lab lab) {
        QueryWrapper<Lab> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("location", lab.getLocation());
        List<Lab> labs = labMapper.selectList(queryWrapper);
        if (!labs.isEmpty()) {
            throw new BusinessException("实验室地点已存在");
        }
        Lab qLab = new Lab();
        BeanUtils.copyProperties(lab, qLab);
        labMapper.insert(qLab);
    }

    @Override
    public void delete(Long id) {
        labMapper.deleteById(id);
    }

    @Override
    public void update(Lab lab) {
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
    public List<Lab> getByLabAdminId(Long lab_admin_id) {
        QueryWrapper<Lab> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lab_admin_id",lab_admin_id);
        return labMapper.selectList(queryWrapper);
    }

    @Override
    public List<Lab> getByType(Integer type) {
        QueryWrapper<Lab> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",type);
        return labMapper.selectList(queryWrapper);
    }

    @Override
    public List<Lab> getByNamePrefix(String namePrefix) {
        QueryWrapper<Lab> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",namePrefix);
        return labMapper.selectList(queryWrapper);
    }

    @Override
    public List<Lab> getByLocationPrefix(String locationPrefix) {
        QueryWrapper<Lab> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("location",locationPrefix);
        return labMapper.selectList(queryWrapper);
    }

    @Override
    public List<Lab> getByLeastEquipmentNum(Integer equipmentNum) {
        QueryWrapper<Lab> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("equipment_num",equipmentNum);
        return labMapper.selectList(queryWrapper);
    }
}




