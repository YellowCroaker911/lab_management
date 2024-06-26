package com.backend.service.utils;

import com.backend.utils.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileDetailsService {

    public List<FileDetails> getAllFiles(String filePath) {
        Path path = Paths.get(filePath);
        try {
            return Files.list(path)
                    .filter(Files::isRegularFile)
                    .map(this::toFileDetailsVO)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new BusinessException("读文件错误");
        }
    }

    private FileDetails toFileDetailsVO(Path path) {
        FileDetails fileDetails = new FileDetails();
        fileDetails.setFileName(path.getFileName().toString());
        fileDetails.setFilePath(path.toString());
        try {
            FileTime lastModifiedTime = Files.getLastModifiedTime(path);
            Instant instant = lastModifiedTime.toInstant();
            // 如果你需要java.util.Date类型的时间戳
            Date date = Date.from(instant);
            fileDetails.setTimeStamp(String.valueOf(date.getTime()));

            // 或者，如果你需要保留为Instant类型（推荐，因为Java 8的日期时间API更强大）
            // fileDetails.setTimeStamp(instant);

        } catch (IOException e) {
            // 处理异常，例如记录日志或抛出自定义异常
            throw new BusinessException("无法获取文件的最后修改时间");
        }
        return fileDetails;
    }
}
