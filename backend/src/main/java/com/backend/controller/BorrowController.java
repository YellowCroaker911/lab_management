package com.backend.controller;

import com.backend.model.dto.borrow.BorrowAdmitDTO;
import com.backend.model.dto.borrow.BorrowAlterDTO;
import com.backend.model.dto.borrow.BorrowCompleteDTO;
import com.backend.model.dto.borrow.BorrowImportDTO;
import com.backend.model.entity.Borrow;
import com.backend.service.service.BorrowService;
import com.backend.utils.response.ResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;
import java.util.List;

@Validated
@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    BorrowService borrowService;

    // 导入借用记录
    @PostMapping("/importBorrow")
    public ResponseData<Object> importBorrow(@RequestBody @Validated BorrowImportDTO borrowImportDTO) {
        Borrow borrow = new Borrow();
        BeanUtils.copyProperties(borrowImportDTO, borrow);
        borrowService.add(borrow);
        return ResponseData.success(null, null);
    }

    // 移除借用记录
    @PostMapping("/removeBorrow")
    public ResponseData<Object> removeBorrow(@RequestParam @NotNull(message = "借用记录id不能为空") Long id) {
        borrowService.delete(id);
        return ResponseData.success(null, null);
    }

    // 修改借用记录信息
    @PostMapping("/alterBorrow")
    public ResponseData<Object> alterBorrow(@RequestBody @Validated BorrowAlterDTO borrowAlterDTO) {
        Borrow borrow = new Borrow();
        BeanUtils.copyProperties(borrowAlterDTO, borrow);
        borrowService.update(borrow);
        return ResponseData.success(null, null);
    }

    // 审核借用记录
    @PostMapping("/admitBorrow")
    public ResponseData<Object> admitBorrow(@RequestBody@Validated BorrowAdmitDTO borrowAdmitDTO) {
        Borrow borrow = new Borrow();
        BeanUtils.copyProperties(borrowAdmitDTO, borrow);
        borrowService.update(borrow);
        return ResponseData.success(null, null);
    }

    // 确认使用完毕
    @PostMapping("/completeBorrow")
    public ResponseData<Object> completeBorrow(@RequestBody @Validated BorrowCompleteDTO borrowCompleteDTO) {
        Borrow borrow = new Borrow();
        BeanUtils.copyProperties(borrowCompleteDTO, borrow);
        borrowService.update(borrow);
        return ResponseData.success(null, null);
    }

    // 根据ID获取借用记录
    @GetMapping("/getBorrowById")
    public ResponseData<Object> getBorrowById(@RequestParam @NotNull(message = "借用记录id不能为空") Long id) {
        Borrow borrow = borrowService.get(id);
        return ResponseData.success(borrow, null);
    }

    // 获取所有借用记录
    @GetMapping("/getAllBorrows")
    public ResponseData<Object> getAllBorrows() {
        List<Borrow> borrows = borrowService.getAll();
        return ResponseData.success(borrows, null);
    }

    // 根据学生id获取借用记录
    @GetMapping("/getBorrowsByStudentId")
    public ResponseData<Object> getBorrowsByTeacherId(@RequestParam @NotNull(message = "学生id不能为空") Long studentId){
        List<Borrow> borrows = borrowService.getByStudentId(studentId);
        return ResponseData.success(borrows, null);
    }

    // 根据实验室id获取借用记录
    @GetMapping("/getBorrowsByLabId")
    public ResponseData<Object> getBorrowsByLabId(@RequestParam @NotNull(message = "实验室id不能为空") Long labId){
        List<Borrow> borrows = borrowService.getByLabId(labId);
        return ResponseData.success(borrows, null);
    }

    // 根据学期获取借用记录
    @GetMapping("/getBorrowsBySemester")
    public ResponseData<Object> getBorrowsBySemester(@RequestParam @NotBlank(message = "学期不能为空")
                                                     @Pattern(regexp = "^\\d{4}-\\d{4}-(1|2)$",message = "学期格式错误") String semester){
        List<Borrow> borrows = borrowService.getBySemester(semester);
        return ResponseData.success(borrows, null);
    }

    // 根据星期获取借用记录
    @GetMapping("/getBorrowsByWeek")
    public ResponseData<Object> getBorrowsByWeek(@RequestParam @NotBlank(message = "星期不能为空")
                                                 @Pattern(regexp = "^(?:[1-9]|1[0-9]|20)$",message = "星期格式错误") String week){
        List<Borrow> borrows = borrowService.getByWeek(week);
        return ResponseData.success(borrows, null);
    }

    // 根据申请状态获取借用记录
    @GetMapping("/getBorrowsByStatus")
    public ResponseData<Object> getBorrowsByStatus(@RequestParam @NotNull(message = "申请状态不能为空")
                                                   @Min(value = 0, message = "申请状态必须在{0,1,2,3}内")
                                                   @Max(value = 3, message = "申请状态必须在{0,1,2,3}内")
                                                   Integer status){
        List<Borrow> borrows = borrowService.getByStatus(status);
        return ResponseData.success(borrows, null);
    }
}
