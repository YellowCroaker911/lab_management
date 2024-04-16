package com.backend.controller;

import com.backend.model.dto.session.SessionAlterDTO;
import com.backend.model.dto.session.SessionImportDTO;
import com.backend.model.entity.Session;
import com.backend.service.service.SessionService;
import com.backend.utils.response.ResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    SessionService sessionService;

    // 导入节次
    @PostMapping("/importSession")
    public ResponseData<Object> importSession(@RequestBody @Validated SessionImportDTO sessionImportDTO) {
        Session session = new Session();
        BeanUtils.copyProperties(sessionImportDTO, session);
        sessionService.add(session);
        return ResponseData.success(null, null);
    }

    // 移除节次
    @PostMapping("/removeSession")
    public ResponseData<Object> removeSession(@RequestParam @NotNull(message = "节次id不能为空") Long id) {
        sessionService.delete(id);
        return ResponseData.success(null, null);
    }

    // 修改节次信息
    @PostMapping("/alterSession")
    public ResponseData<Object> alterSession(@RequestBody @Validated SessionAlterDTO sessionAlterDTO) {
        Session session = new Session();
        BeanUtils.copyProperties(sessionAlterDTO, session);
        sessionService.update(session);
        return ResponseData.success(null, null);
    }

    // 根据ID获取节次
    @GetMapping("/getSessionById")
    public ResponseData<Object> getSessionById(@RequestParam @NotNull(message = "节次id不能为空") Long id) {
        Session session = sessionService.get(id);
        return ResponseData.success(session, null);
    }

    // 获取所有节次
    @GetMapping("/getAllSessions")
    public ResponseData<Object> getAllSessions() {
        List<Session> sessions = sessionService.getAll();
        return ResponseData.success(sessions, null);
    }
}
