package com.backend.controller;

import com.backend.model.dto.lab.LabAlterDTO;
import com.backend.model.dto.lab.LabImportDTO;
import com.backend.model.entity.Lab;
import com.backend.service.service.LabService;
import com.backend.utils.response.ResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/lab")
public class LabController {
    @Autowired
    LabService labService;

    // 导入实验室
    @PostMapping("/importLab")
    public ResponseData<Object> importLab(@RequestBody @Validated LabImportDTO labImportDTO) {
        Lab lab = new Lab();
        BeanUtils.copyProperties(labImportDTO, lab);
        labService.add(lab);
        return ResponseData.success(null, null);
    }

    // 移除实验室
    @PostMapping("/removeLab")
    public ResponseData<Object> removeLab(@RequestParam @NotNull(message = "实验室id不能为空") Long id) {
        labService.delete(id);
        return ResponseData.success(null, null);
    }

    // 修改实验室信息
    @PostMapping("/alterLab")
    public ResponseData<Object> alterLab(@RequestBody @Validated LabAlterDTO labAlterDTO) {
        Lab lab = new Lab();
        BeanUtils.copyProperties(labAlterDTO, lab);
        labService.update(lab);
        return ResponseData.success(null, null);
    }

    // 根据ID获取实验室
    @GetMapping("/getLabById")
    public ResponseData<Object> getLabById(@RequestParam @NotNull(message = "实验室id不能为空") Long id) {
        Lab lab = labService.get(id);
        return ResponseData.success(lab, null);
    }

    // 获取所有实验室
    @GetMapping("/getAllLabs")
    public ResponseData<Object> getAllLabs() {
        List<Lab> labs = labService.getAll();
        return ResponseData.success(labs, null);
    }

    // 根据实验员id获取实验室
    @GetMapping("/getLabsByLabAdminId")
    public ResponseData<Object> getLabsByLabAdminId(@RequestParam @NotNull(message = "实验员id不能为空") Long lab_admin_id) {
        List<Lab> labs = labService.getByLabAdminId(lab_admin_id);
        return ResponseData.success(labs, null);
    }

    // 根据类型获取实验室
    @GetMapping("/getLabsByType")
    public ResponseData<Object> getLabsByType(@RequestParam @NotNull(message = "实验室类型不能为空")
                                              @Min(value = 0, message = "实验室类型必须在{0,1,2}内")
                                              @Max(value = 2, message = "实验室类型必须在{0,1,2}内") Integer type) {
        List<Lab> labs = labService.getByType(type);
        return ResponseData.success(labs, null);
    }

    // 根据名称前缀获取实验室
    @GetMapping("/getLabsByNamePrefix")
    public ResponseData<Object> getLabsByNamePrefix(@RequestParam @NotEmpty(message = "名称前缀不能为空") String namePrefix) {
        List<Lab> labs = labService.getByNamePrefix(namePrefix);
        return ResponseData.success(labs, null);
    }

    // 根据实验室编号前缀获取实验室
    @GetMapping("/getLabsByNumberPrefix")
    public ResponseData<Object> getLabsByNumberPrefix(@RequestParam @NotEmpty(message = "实验室编号前缀不能为空") String numberPrefix) {
        List<Lab> labs = labService.getByNumberPrefix(numberPrefix);
        return ResponseData.success(labs, null);
    }

    // 根据最少设备数量获取实验室
    @GetMapping("/getLabsByLeastEquipmentNum")
    public ResponseData<Object> getLabsByLeastEquipmentNum(@RequestParam @NotNull(message = "最少设备数量不能为空") Integer equipmentNum) {
        List<Lab> labs = labService.getByLeastEquipmentNum(equipmentNum);
        return ResponseData.success(labs, null);
    }
}
