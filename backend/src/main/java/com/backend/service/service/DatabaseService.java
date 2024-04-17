package com.backend.service.service;

import com.backend.service.utils.FileDetails;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface DatabaseService {
    void backup();
    void recovery(String path);
    void downloadBackUpFiles(HttpServletResponse response, String path) throws IOException;
    List<FileDetails> getAllBackUpFiles();


}
