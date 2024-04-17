package com.backend.service.service;

import com.backend.model.entity.Maintain;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Pfeistorch
* @description 针对表【repair(维修)】的数据库操作Service
* @createDate 2024-04-15 23:15:43
*/
public interface MaintainService extends IService<Maintain> {
    void add(Maintain maintain);
    void delete(Long id);
    void update(Maintain maintain);
    Maintain get(Long id);
    List<Maintain> getAll();
    List<Maintain> getByTeacherId(Long teacherId);
    List<Maintain> getByLabId(Long labId);
    List<Maintain> getByStatus(Integer status);
    List<Maintain> getByLabAdminId(Long labAdminId);

}
