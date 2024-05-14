package com.backend.service.service;

import com.backend.model.dto.AvailableLabSessionDTO;
import com.backend.model.dto.CourseLabSessionDTO;
import com.backend.model.vo.AvailableLabSessionVO;
import com.backend.model.vo.CourseLabSessionVO;

import java.util.List;

public interface ComplexQueryService {
    List<AvailableLabSessionVO> getAvailableLabSession(AvailableLabSessionDTO availableLabSessionDTO);

    List<CourseLabSessionVO> getCourseLabSession(CourseLabSessionDTO courseLabSessionDTO);
}
