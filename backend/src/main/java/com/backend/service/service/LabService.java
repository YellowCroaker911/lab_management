package com.backend.service.service;

import com.backend.model.entity.Lab;
import com.backend.model.entity.User;
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
    List<Lab> getByLabAdminId(Long lab_admin_id);
    List<Lab> getByType(Integer type);
    List<Lab> getByNamePrefix(String namePrefix);
    List<Lab> getByLocationPrefix(String locationPrefix);
    List<Lab> getByLeastEquipmentNum(Integer equipmentNum);
}
