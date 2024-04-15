package com.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.model.entity.Repair;
import com.backend.service.service.RepairService;
import com.backend.service.mapper.RepairMapper;
import org.springframework.stereotype.Service;

/**
* @author Pfeistorch
* @description 针对表【repair(维修)】的数据库操作Service实现
* @createDate 2024-04-15 23:15:43
*/
@Service
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair>
    implements RepairService{

}




