package com.backend.service.impl;

import com.backend.model.entity.*;
import com.backend.service.mapper.*;
import com.backend.utils.exception.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.service.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @author Pfeistorch
 * @description 针对表【course(课程)】的数据库操作Service实现
 * @createDate 2024-04-15 23:15:47
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
        implements CourseService {
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

    @Override
    public void add(Course course) {
        User user = userMapper.selectById(course.getTeacherId());
        if (user.getRole() != 2) {
            throw new BusinessException("教师id错误");
        }

        QueryWrapper<Semester> qw1 = new QueryWrapper<>();
        qw1.eq("semester", course.getSemester());
        Semester semester = semesterMapper.selectOne(qw1);
        if (semester == null) {
            throw new BusinessException("学期不存在");
        }
        if (parseInt(semester.getWeek()) < parseInt(course.getEndingWeek())) {
            throw new BusinessException("结束周超过学期最大周数");
        }

        QueryWrapper<Session> qw2 = new QueryWrapper<>();
        qw2.eq("session", course.getSession());
        Session session = sessionMapper.selectOne(qw2);
        if (session == null) {
            throw new BusinessException("节次不存在");
        }

        QueryWrapper<Course> qw3 = new QueryWrapper<>();
        qw3.eq("teacher_id", course.getTeacherId())
                .eq("name", course.getName())
                .eq("semester", course.getSemester())
                .eq("session", course.getSession())
                .eq("clazz", course.getClazz());
        List<Course> courses = courseMapper.selectList(qw3);
        if (!courses.isEmpty()) {
            throw new BusinessException("课程记录已存在");
        }

        courseMapper.insert(course);
    }

    @Override
    public void delete(Long id) {
        courseMapper.deleteById(id);
    }

    @Override
    public void update(Course course) {

        if (course.getTeacherId() != null) {
            User user = userMapper.selectById(course.getTeacherId());
            if (user.getRole() != 2) {
                throw new BusinessException("教师id错误");
            }
        }

        if (course.getLabId() != null) {
            if (course.getStatus() != null && course.getStatus() == 1) {
                Lab lab = labMapper.selectById(course.getLabId());
                if (lab == null) {
                    throw new BusinessException("实验室id错误");
                }
                if (!lab.getType().equals(course.getType())) {
                    throw new BusinessException("实验室类型与课程需求类型不匹配");
                }
                if (lab.getEquipmentNum() < course.getStudentNum()) {
                    throw new BusinessException("实验室设备数少于课程学生人数");
                }
            }
        }

        if (course.getSemester() != null) {
            QueryWrapper<Semester> qw1 = new QueryWrapper<>();
            qw1.eq("semester", course.getSemester());
            Semester semester = semesterMapper.selectOne(qw1);
            if (semester == null) {
                throw new BusinessException("学期不存在");
            }
            if (course.getEndingWeek() != null) {
                if (parseInt(semester.getWeek()) < parseInt(course.getEndingWeek())) {
                    throw new BusinessException("结束周超过学期最大周数");
                }
            }
        }

        if (course.getSession() != null) {
            QueryWrapper<Session> qw2 = new QueryWrapper<>();
            qw2.eq("session", course.getSession());
            Session session = sessionMapper.selectOne(qw2);
            if (session == null) {
                throw new BusinessException("节次不存在");
            }
        }

        Course qCourse = courseMapper.selectById(course.getId());
        if (qCourse == null) {
            throw new BusinessException("索引不存在");
        }

        if (qCourse.getStatus() == 1) {
            throw new BusinessException("已排课，无法修改");
        }

        QueryWrapper<Course> qw3 = new QueryWrapper<>();
        qw3.ne("id", qCourse.getId())
                .eq("teacher_id", course.getTeacherId())
                .eq("name", course.getName())
                .eq("semester", course.getSemester())
                .eq("session", course.getSession())
                .eq("clazz", course.getClazz());
        List<Course> courses = courseMapper.selectList(qw3);
        if (!courses.isEmpty()) {
            throw new BusinessException("课程记录已存在");
        }

        BeanUtils.copyProperties(course, qCourse);
        courseMapper.updateById(qCourse);
    }

    @Override
    public Course get(Long id) {
        return courseMapper.selectById(id);
    }

    @Override
    public List<Course> getAll() {
        return courseMapper.selectList(null);
    }

    @Override
    public List<Course> getByTeacherId(Long teacherId) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", teacherId);
        return courseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Course> getByLabId(Long labId) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lab_id", labId);
        return courseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Course> getByType(Integer type) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        return courseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Course> getByNamePrefix(String namePrefix) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", namePrefix);
        return courseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Course> getBySemester(String semester) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("semester", semester);
        return courseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Course> getByWeek(String week) {
        return courseMapper.getByWeek(week);
    }

    @Override
    public List<Course> getByStatus(Integer status) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", status);
        return courseMapper.selectList(queryWrapper);
    }
}
