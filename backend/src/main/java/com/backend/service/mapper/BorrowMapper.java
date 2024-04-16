package com.backend.service.mapper;

import com.backend.model.entity.Borrow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Pfeistorch
* @description 针对表【borrow(借用)】的数据库操作Mapper
* @createDate 2024-04-15 23:15:49
* @Entity com.backend.model.entity.Borrow
*/
@Mapper
public interface BorrowMapper extends BaseMapper<Borrow> {

}




