package com.backend.controller;

import com.backend.model.vo.FileDetailsVO;
import com.backend.service.service.DatabaseService;
import com.backend.service.utils.FileDetails;
import com.backend.utils.response.ResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/database")
public class DatabaseController {
    @Autowired
    DatabaseService databaseService;

    // 数据备份
    @PostMapping("/backupDatabase")
    public ResponseData<Object> backup() {
        databaseService.backup();
        return ResponseData.success(null, null);
    }

    // 数据恢复
    @PostMapping("/recoveryDatabase")
    public ResponseData<Object> recoveryDatabase(@RequestParam @NotNull String path) {
        databaseService.recovery(path);
        return ResponseData.success(null,null);
    }

    // 数据备份文件下载
    @GetMapping("/downloadBackUpFile")
    public ResponseData<Object> downloadBackUpFile(@RequestParam @NotNull String path, HttpServletResponse response) throws IOException {
        databaseService.downloadBackUpFiles(response,path);
        return ResponseData.success(null,null);
    }

    // 获取备份文件夹信息
    @GetMapping("getAllBackUpFiles")
    public ResponseData<Object> getAllBackUpFiles() {
        List<FileDetails> fileDetailsList = databaseService.getAllBackUpFiles();
        List<FileDetailsVO> fileDetailsVOList = new ArrayList<>();
        for (FileDetails fileDetails : fileDetailsList) {
            FileDetailsVO fileDetailsVO = new FileDetailsVO();
            BeanUtils.copyProperties(fileDetails, fileDetailsVO);
            fileDetailsVOList.add(fileDetailsVO);
        }
        return ResponseData.success(fileDetailsVOList, null);
    }


}
