package com.backend.controller;

import com.backend.model.dto.semester.SemesterAlterDTO;
import com.backend.model.dto.semester.SemesterImportDTO;
import com.backend.model.entity.Semester;
import com.backend.service.service.SemesterService;
import com.backend.utils.response.ResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/semester")
public class SemesterController {
    @Autowired
    SemesterService semesterService;

    // 导入学期
    @PostMapping("/importSemester")
    public ResponseData<Object> importSemester(@RequestBody @Validated SemesterImportDTO semesterImportDTO) {
        Semester semester = new Semester();
        BeanUtils.copyProperties(semesterImportDTO, semester);
        semesterService.add(semester);
        return ResponseData.success(null, null);
    }

    // 移除学期
    @PostMapping("/removeSemester")
    public ResponseData<Object> removeSemester(@RequestParam @NotNull(message = "学期id不能为空") Long id) {
        semesterService.delete(id);
        return ResponseData.success(null, null);
    }

    // 修改学期信息
    @PostMapping("/alterSemester")
    public ResponseData<Object> alterSemester(@RequestBody @Validated SemesterAlterDTO semesterAlterDTO) {
        Semester semester = new Semester();
        BeanUtils.copyProperties(semesterAlterDTO, semester);
        semesterService.update(semester);
        return ResponseData.success(null, null);
    }

    // 根据ID获取学期
    @GetMapping("/getSemesterById")
    public ResponseData<Object> getSemesterById(@RequestParam @NotNull(message = "学期id不能为空") Long id) {
        Semester semester = semesterService.get(id);
        return ResponseData.success(semester, null);
    }

    // 获取当前学期
    @GetMapping("/getCurrentSemester")
    public ResponseData<Object> getCurrentSemester() {
        Semester semester = semesterService.getCurrent();
        return ResponseData.success(semester, null);
    }

    // 获取所有学期
    @GetMapping("/getAllSemesters")
    public ResponseData<Object> getAllSemesters() {
        List<Semester> semesters = semesterService.getAll();
        return ResponseData.success(semesters, null);
    }
}
