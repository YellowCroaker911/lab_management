package com.backend.service.impl;

import com.backend.model.entity.Lab;
import com.backend.model.entity.User;
import com.backend.utils.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.model.entity.Semester;
import com.backend.service.service.SemesterService;
import com.backend.service.mapper.SemesterMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Pfeistorch
* @description 针对表【semester(学年)】的数据库操作Service实现
* @createDate 2024-04-16 21:27:15
*/
@Service
public class SemesterServiceImpl extends ServiceImpl<SemesterMapper, Semester>
    implements SemesterService{

    @Autowired
    SemesterMapper semesterMapper;

    @Override
    public void add(Semester semester) {
        QueryWrapper<Semester> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("semester", semester.getSemester());
        List<Semester> semesters = semesterMapper.selectList(queryWrapper);
        if (!semesters.isEmpty()) {
            throw new BusinessException("学期已存在");
        }
        semesterMapper.insert(semester);
    }

    @Override
    public void delete(Long id) {
        semesterMapper.deleteById(id);
    }

    @Override
    public void update(Semester semester) {
        Semester qSemester = semesterMapper.selectById(semester.getId());
        if (qSemester == null) {
            throw new BusinessException("索引不存在");
        }
        BeanUtils.copyProperties(semester, qSemester);
        semesterMapper.updateById(qSemester);
    }

    @Override
    public Semester get(Long id) {
        return semesterMapper.selectById(id);
    }

    @Override
    public List<Semester> getAll() {
        return semesterMapper.selectList(null);
    }
}




