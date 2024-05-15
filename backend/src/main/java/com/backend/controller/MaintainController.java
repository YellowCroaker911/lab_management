package com.backend.controller;

import com.backend.model.dto.maintain.MaintainCompleteDTO;
import com.backend.model.dto.maintain.MaintainStartDTO;
import com.backend.model.dto.maintain.MaintainAlterDTO;
import com.backend.model.dto.maintain.MaintainImportDTO;
import com.backend.model.entity.Maintain;
import com.backend.service.service.MaintainService;
import com.backend.utils.response.ResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;
import java.util.List;

@Validated
@RestController
@RequestMapping("/maintain")
public class MaintainController {
    @Autowired
    MaintainService maintainService;

    // 导入维修记录
    @PostMapping("/importMaintain")
    public ResponseData<Object> importMaintain(@RequestBody @Validated MaintainImportDTO maintainImportDTO) {
        Maintain maintain = new Maintain();
        BeanUtils.copyProperties(maintainImportDTO, maintain);
        maintainService.add(maintain);
        return ResponseData.success(null, null);
    }

    // 移除维修记录
    @PostMapping("/removeMaintain")
    public ResponseData<Object> removeMaintain(@RequestBody @NotNull(message = "维修记录id不能为空") Long id) {
        maintainService.delete(id);
        return ResponseData.success(null, null);
    }

    // 修改维修记录信息
    @PostMapping("/alterMaintain")
    public ResponseData<Object> alterMaintain(@RequestBody @Validated MaintainAlterDTO maintainAlterDTO) {
        Maintain maintain = new Maintain();
        BeanUtils.copyProperties(maintainAlterDTO, maintain);
        maintainService.update(maintain);
        return ResponseData.success(null, null);
    }

    // 开始维修
    @PostMapping("/startMaintain")
    public ResponseData<Object> startMaintain(@RequestBody @Validated MaintainStartDTO maintainStartDTO) {
        Maintain maintain = new Maintain();
        BeanUtils.copyProperties(maintainStartDTO, maintain);
        maintainService.update(maintain);
        return ResponseData.success(null, null);
    }

    // 完成维修
    @PostMapping("/completeMaintain")
    public ResponseData<Object> completeMaintain(@RequestBody @Validated MaintainCompleteDTO maintainCompleteDTO) {
        Maintain maintain = new Maintain();
        BeanUtils.copyProperties(maintainCompleteDTO, maintain);
        maintainService.update(maintain);
        return ResponseData.success(null, null);
    }

    // 根据ID获取维修记录
    @GetMapping("/getMaintainById")
    public ResponseData<Object> getMaintainById(@RequestBody @NotNull(message = "维修记录id不能为空") Long id) {
        Maintain maintain = maintainService.get(id);
        return ResponseData.success(maintain, null);
    }

    // 获取所有维修记录
    @GetMapping("/getAllMaintains")
    public ResponseData<Object> getAllMaintains() {
        List<Maintain> maintains = maintainService.getAll();
        return ResponseData.success(maintains, null);
    }

    // 根据教师id获取维修记录
    @GetMapping("/getMaintainsByTeacherId")
    public ResponseData<Object> getMaintainsByTeacherId(@RequestParam @NotNull(message = "教师id不能为空") Long teacherId) {
        List<Maintain> maintains = maintainService.getByTeacherId(teacherId);
        return ResponseData.success(maintains, null);
    }

    // 根据实验室id获取维修记录
    @GetMapping("/getMaintainsByLabId")
    public ResponseData<Object> getMaintainsByLabId(@RequestParam @NotNull(message = "实验室id不能为空") Long labId) {
        List<Maintain> maintains = maintainService.getByLabId(labId);
        return ResponseData.success(maintains, null);
    }

    // 根据维修状态获取维修记录
    @GetMapping("/getMaintainsByStatus")
    public ResponseData<Object> getMaintainsByStatus(@RequestParam @NotNull(message = "维修状态不能为空")
                                                     @Min(value = 0, message = "维修状态必须在{0,1,2}内")
                                                     @Max(value = 2, message = "维修状态必须在{0,1,2}内")
                                                     Integer status) {
        List<Maintain> maintains = maintainService.getByStatus(status);
        return ResponseData.success(maintains, null);
    }

    // 根据实验员id获取维修记录
    @GetMapping("/getMaintainsByLabAdminId")
    public ResponseData<Object> getMaintainsByLabAdminId(@RequestParam @NotNull(message = "实验员id不能为空") Long labAdminId) {
        List<Maintain> maintains = maintainService.getByLabAdminId(labAdminId);
        return ResponseData.success(maintains, null);
    }

}
