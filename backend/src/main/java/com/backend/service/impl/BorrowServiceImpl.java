package com.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.model.entity.Borrow;
import com.backend.service.service.BorrowService;
import com.backend.service.mapper.BorrowMapper;
import org.springframework.stereotype.Service;

/**
* @author Pfeistorch
* @description 针对表【borrow(借用)】的数据库操作Service实现
* @createDate 2024-04-15 23:15:49
*/
@Service
public class BorrowServiceImpl extends ServiceImpl<BorrowMapper, Borrow>
    implements BorrowService{

}




