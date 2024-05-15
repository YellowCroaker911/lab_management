package com.backend.service.service;

import com.backend.model.dto.CourseLabSessionDTO;
import com.backend.model.vo.CourseLabSessionVO;

import java.util.List;

public interface ComplexQueryService {

    List<CourseLabSessionVO> getCourseLabSession(CourseLabSessionDTO courseLabSessionDTO);
}
