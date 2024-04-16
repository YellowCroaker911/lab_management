package com.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.model.entity.Lab;
import com.backend.service.service.LabService;
import com.backend.service.mapper.LabMapper;
import org.springframework.stereotype.Service;

/**
* @author Pfeistorch
* @description 针对表【lab(实验室)】的数据库操作Service实现
* @createDate 2024-04-15 23:15:45
*/
@Service
public class LabServiceImpl extends ServiceImpl<LabMapper, Lab>
    implements LabService{

}




