package com.backend.service.impl;

import com.backend.model.dto.CourseLabSessionDTO;
import com.backend.model.vo.CourseLabSessionVO;
import com.backend.service.mapper.CourseMapper;
import com.backend.service.mapper.LabMapper;
import com.backend.service.service.ComplexQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplexQueryServiceImpl implements ComplexQueryService {

    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<CourseLabSessionVO> getCourseLabSession(CourseLabSessionDTO courseLabSessionDTO) {
        return courseMapper.getCourseLabSession(courseLabSessionDTO.getSemester(), courseLabSessionDTO.getWeek());
    }
}
