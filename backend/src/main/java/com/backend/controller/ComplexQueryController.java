package com.backend.controller;

import com.backend.model.dto.CourseLabSessionDTO;
import com.backend.model.vo.CourseLabSessionVO;
import com.backend.service.service.ComplexQueryService;
import com.backend.utils.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/query")
public class ComplexQueryController {

    @Autowired
    ComplexQueryService complexQueryService;

    // 获取某一周课表
    @PostMapping("/getCourseLabSession")
    public ResponseData<Object> getCourseLabSession(@RequestBody @Validated CourseLabSessionDTO courseLabSessionDTO) {
        List<CourseLabSessionVO> courseLabSessionVOS = complexQueryService.getCourseLabSession(courseLabSessionDTO);
        return ResponseData.success(courseLabSessionVOS, null);
    }
}
