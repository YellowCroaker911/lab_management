package com.backend.service.mapper;

import com.backend.model.entity.Maintain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author Pfeistorch
* @description 针对表【maintain(维修)】的数据库操作Mapper
* @createDate 2024-04-15 23:15:43
* @Entity com.backend.model.entity.Repair
*/
@Mapper
public interface MaintainMapper extends BaseMapper<Maintain> {
    List<Maintain> getSame(@Param("teacherId") Long teacherId,@Param("labId") Long labId,@Param("date") Date date);

    List<Maintain> getByLabAdminId(@Param("labAdminId") Long labAdminId);
}




