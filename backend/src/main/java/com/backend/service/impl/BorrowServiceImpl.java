package com.backend.service.impl;

import com.backend.model.entity.*;
import com.backend.service.mapper.*;
import com.backend.utils.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.service.service.BorrowService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @author Pfeistorch
 * @description 针对表【borrow(借用)】的数据库操作Service实现
 * @createDate 2024-04-15 23:15:49
 */
@Service
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow>
        implements BorrowService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    LabMapper labMapper;

    @Autowired
    SemesterMapper semesterMapper;

    @Autowired
    SessionMapper sessionMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    BorrowMapper borrowMapper;

    @Override
    public void add(Borrow borrow) {
        User user = userMapper.selectById(borrow.getStudentId());
        if (user.getRole() != 1) {
            throw new BusinessException("学生id错误");
        }

        QueryWrapper<Semester> qw1 = new QueryWrapper<>();
        qw1.eq("semester", borrow.getSemester());
        Semester semester = semesterMapper.selectOne(qw1);
        if (semester == null) {
            throw new BusinessException("学期不存在");
        }
        if (parseInt(semester.getWeek()) < parseInt(borrow.getWeek())) {
            throw new BusinessException("周次超过学期最大周数");
        }

        QueryWrapper<Session> qw2 = new QueryWrapper<>();
        qw2.eq("session", borrow.getSession());
        Session session = sessionMapper.selectOne(qw2);
        if (session == null) {
            throw new BusinessException("节次不存在");
        }

        QueryWrapper<Borrow> qw3 = new QueryWrapper<>();
        qw3.eq("student_id", borrow.getStudentId())
                .eq("lab_id", borrow.getLabId())
                .eq("semester", borrow.getSemester())
                .eq("week", borrow.getWeek())
                .eq("session", borrow.getSession());
        List<Borrow> borrows1 = borrowMapper.selectList(qw3);
        if (!borrows1.isEmpty()) {
            throw new BusinessException("借用记录已存在");
        }

        QueryWrapper<Borrow> qw4 = new QueryWrapper<>();
        qw4.eq("lab_id", borrow.getLabId())
                .eq("semester", borrow.getSemester())
                .eq("week", borrow.getWeek())
                .eq("session", borrow.getSession());
        List<Borrow> borrows2 = borrowMapper.selectList(qw4);
        if (!borrows2.isEmpty()) {
            throw new BusinessException("实验室此时不可用，已有学生借用");
        }

        List<Course> courses = courseMapper.getConflict(borrow.getLabId(),
                borrow.getSemester(), borrow.getWeek(), borrow.getSession());
        if (!courses.isEmpty()) {
            throw new BusinessException("实验室此时不可用，已被用作上课");
        }

        borrowMapper.insert(borrow);
    }

    @Override
    public void delete(Long id) {
        borrowMapper.deleteById(id);
    }

    @Override
    public void update(Borrow borrow) {
        User user = userMapper.selectById(borrow.getStudentId());

        if (user.getRole() != 1) {
            throw new BusinessException("学生id错误");
        }

        if (borrow.getStatus() == 1) {
            Lab lab = labMapper.selectById(borrow.getLabId());
            if (lab == null) {
                throw new BusinessException("实验室id错误");
            }
        }

        QueryWrapper<Semester> qw1 = new QueryWrapper<>();
        qw1.eq("semester", borrow.getSemester());
        Semester semester = semesterMapper.selectOne(qw1);
        if (semester == null) {
            throw new BusinessException("学期不存在");
        }
        if (parseInt(semester.getWeek()) < parseInt(borrow.getWeek())) {
            throw new BusinessException("周次超过学期最大周数");
        }

        QueryWrapper<Session> qw2 = new QueryWrapper<>();
        qw2.eq("session", borrow.getSession());
        Session session = sessionMapper.selectOne(qw2);
        if (session == null) {
            throw new BusinessException("节次不存在");
        }

        QueryWrapper<Borrow> qw3 = new QueryWrapper<>();
        qw3.eq("lab_id", borrow.getLabId())
                .eq("semester", borrow.getSemester())
                .eq("week", borrow.getWeek())
                .eq("session", borrow.getSession());
        List<Borrow> borrows2 = borrowMapper.selectList(qw3);
        if (!borrows2.isEmpty()) {
            throw new BusinessException("实验室此时不可用，已有学生借用");
        }

        List<Course> courses = courseMapper.getConflict(borrow.getLabId(),
                borrow.getSemester(), borrow.getWeek(), borrow.getSession());
        if (!courses.isEmpty()) {
            throw new BusinessException("实验室此时不可用，已被用作上课");
        }

        Borrow qBorrow = borrowMapper.selectById(borrow.getId());
        if (qBorrow == null) {
            throw new BusinessException("索引不存在");
        }

        if (qBorrow.getStatus() == 1) {
            if (borrow.getStatus() == null || borrow.getStatus() != 3) {
                throw new BusinessException("已审核，无法修改");
            }
        }
        if (qBorrow.getStatus() == 2) {
            throw new BusinessException("已驳回，无法修改");
        }
        if (qBorrow.getStatus() == 3) {
            throw new BusinessException("已完成，无法修改");
        }

        QueryWrapper<Borrow> qw4 = new QueryWrapper<>();
        qw4.ne("id", qBorrow.getId())
                .eq("student_id", borrow.getStudentId())
                .eq("lab_id", borrow.getLabId())
                .eq("semester", borrow.getSemester())
                .eq("week", borrow.getWeek())
                .eq("session", borrow.getSession());
        List<Borrow> borrows = borrowMapper.selectList(qw4);
        if (!borrows.isEmpty()) {
            throw new BusinessException("借用记录已存在");
        }

        BeanUtils.copyProperties(borrow, qBorrow);
        borrowMapper.updateById(qBorrow);
    }

    @Override
    public Borrow get(Long id) {
        return borrowMapper.selectById(id);
    }

    @Override
    public List<Borrow> getAll() {
        return borrowMapper.selectList(null);
    }

    @Override
    public List<Borrow> getByStudentId(Long studentId) {
        QueryWrapper<Borrow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", studentId);
        return borrowMapper.selectList(queryWrapper);
    }

    @Override
    public List<Borrow> getByLabId(Long labId) {
        QueryWrapper<Borrow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lab_id", labId);
        return borrowMapper.selectList(queryWrapper);
    }

    @Override
    public List<Borrow> getBySemester(String semester) {
        QueryWrapper<Borrow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("semester", semester);
        return borrowMapper.selectList(queryWrapper);
    }

    @Override
    public List<Borrow> getByWeek(String week) {
        QueryWrapper<Borrow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("week", week);
        return borrowMapper.selectList(queryWrapper);
    }

    @Override
    public List<Borrow> getByStatus(Integer status) {
        QueryWrapper<Borrow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", status);
        return borrowMapper.selectList(queryWrapper);
    }

}




