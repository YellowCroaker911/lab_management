package com.backend.service.impl;

import com.backend.service.service.DatabaseService;
import com.backend.service.utils.FileDetails;
import com.backend.service.utils.FileDetailsService;
import com.backend.utils.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.backend.utils.CommonConstant.BACKUP_FOLDER;

@Service
@Slf4j
public class DatabaseServiceImpl implements DatabaseService {

    @Value("${custom.mysql.username}")
    String databaseUser;
    @Value("${custom.mysql.password}")
    String databasePassword;
    @Value("${custom.mysql.host}")
    String databaseHost;
    @Value("${custom.mysql.port}")
    String databasePort;
    @Value("${custom.mysql.database}")
    String databaseTable;

    @Autowired
    FileDetailsService fileDetailsService;

    @Override
    public void backup() {

        Date date = new Date();

        // 定义CMD指令和参数作为数组元素
        String[] command = {
                "mysqldump",
                "-u" + databaseUser,
                "-p" + databasePassword,
                "--host=" + databaseHost,
                "--port=" + databasePort,
                "--databases",
                databaseTable,
                "-r", // 使用 -r 参数指定输出文件，而不是重定向
                BACKUP_FOLDER + "/" + date.toString().replace(" ", "_").replace(":", "_") + ".sql"
        };

        // 使用String.join合并数组元素，元素之间用空格分隔
        String fullCommand = String.join(" ", command);
        System.out.println(fullCommand); // 打印完整的命令字符串

        // 创建ProcessBuilder对象
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true); // 合并标准输出和错误输出

        try {
            // 启动进程
            Process process = processBuilder.start();

            // 读取进程的输出
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // 打印到控制台或根据需要处理
            }

            // 等待进程结束并获取退出值
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("mysqldump process exited with code: " + exitCode);
            } else {
                System.out.println("mysqldump process completed successfully.");
            }
        } catch (IOException | InterruptedException e) {
            throw new BusinessException("数据库备份错误");
        }
    }


    @Override
    @Transactional // 如果需要的话，添加事务注解
    public void recovery(String path) {
//        String database = "club"; // 需要备份的数据库名
//        System.out.println("现在时间是" + new Date());
        Runtime runtime = Runtime.getRuntime();
        try {
            String stmt = "mysql -h " + databaseHost
                    + " -u" + databaseUser
                    + " -p" + databasePassword
                    + " " + databaseTable + "< " + path;
            log.info("还原数据库  " + stmt);
            String[] command = {"cmd", "/c", stmt};
            Process process = runtime.exec(command);
            //若有错误信息则输出
            InputStream errorStream = process.getErrorStream();
//            System.out.println(errorStream);
            // 等待操作
            int processComplete = process.waitFor();
            if (processComplete == 0) {
                log.info("还原成功.");
            } else {
                String result = new BufferedReader(new InputStreamReader(errorStream, "GBK"))
                        .lines().collect(Collectors.joining(System.lineSeparator()));
                log.error(result);
                // 截取返回
                throw new BusinessException(result.substring(0, 50));
            }
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException("还原数据库失败");
        }
    }

    @Override
    public void downloadBackUpFiles(HttpServletResponse response, String path) throws IOException {

        File file = new File(BACKUP_FOLDER + "/" + path);

        response.reset();
        response.setContentType("application/x-download;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("utf-8"), "utf-8"));

        //创建输入流
        InputStream is = Files.newInputStream(file.toPath());
        OutputStream os = response.getOutputStream();

        //利用IOUtils将输入流的内容 复制到输出流
        //org.apache.tomcat.util.http.fileupload.IOUtils
        //项目搭建是自动集成了这个类 直接使用即可
        IOUtils.copy(is, os);
        os.flush();
        is.close();
        os.close();
    }

    @Override
    public List<FileDetails> getAllBackUpFiles() {
        return fileDetailsService.getAllFiles(BACKUP_FOLDER);
    }
}
