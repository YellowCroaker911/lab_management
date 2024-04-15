package com.backend.service.mapper;

import com.backend.model.entity.Repair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Pfeistorch
* @description 针对表【repair(维修)】的数据库操作Mapper
* @createDate 2024-04-15 23:15:43
* @Entity com.backend.model.entity.Repair
*/
@Mapper
public interface RepairMapper extends BaseMapper<Repair> {

}




