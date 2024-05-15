package com.backend.service.service;

import com.backend.model.dto.lab.LabAvaliableGetDTO;
import com.backend.model.entity.Course;
import com.backend.model.entity.Lab;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Pfeistorch
* @description 针对表【lab(实验室)】的数据库操作Service
* @createDate 2024-04-15 23:15:45
*/
public interface LabService extends IService<Lab> {
    void add(Lab lab);
    void delete(Long id);
    void update(Lab lab);
    Lab get(Long id);
    List<Lab> getAll();
    List<Lab> getByLabAdminId(Long labAdminId);
    List<Lab> getByType(Integer type);
    List<Lab> getByNamePrefix(String namePrefix);
    List<Lab> getByNumberPrefix(String numberPrefix);
    List<Lab> getByLeastEquipmentNum(Integer equipmentNum);
    List<Lab> getAvailable(LabAvaliableGetDTO labAvaliableGetDTO);
}
