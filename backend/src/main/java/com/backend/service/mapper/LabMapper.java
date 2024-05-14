package com.backend.service.mapper;

import com.backend.model.entity.Lab;
import com.backend.model.vo.AvailableLabSessionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Pfeistorch
 * @description 针对表【lab(实验室)】的数据库操作Mapper
 * @createDate 2024-04-15 23:15:45
 * @Entity com.backend.model.entity.Lab
 */
@Mapper
public interface LabMapper extends BaseMapper<Lab> {
    List<AvailableLabSessionVO> getAvailableLabSession(@Param("type") Integer type, @Param("semester") String semester,
                                                       @Param("startingWeek") String startingWeek, @Param("endingWeek") String endingWeek,
                                                       @Param("student_num") Integer studentNum);
}




