package com.backend.service.service;

import com.backend.model.entity.Borrow;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Pfeistorch
* @description 针对表【borrow(借用)】的数据库操作Service
* @createDate 2024-04-15 23:15:49
*/
public interface BorrowService extends IService<Borrow> {
    void add(Borrow borrow);
    void delete(Long id);
    void update(Borrow borrow);
    Borrow get(Long id);
    List<Borrow> getAll();
    List<Borrow> getByStudentId(Long studentId);
    List<Borrow> getByLabId(Long labId);
    List<Borrow> getBySemester(String semester);
    List<Borrow> getByWeek(String week);
    List<Borrow> getByStatus(Integer status);
}
