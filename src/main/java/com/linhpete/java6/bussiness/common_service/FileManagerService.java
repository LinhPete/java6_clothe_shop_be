package com.linhpete.java6.bussiness.common_service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileManagerService {
    byte[] readFile(String folder, String filename) throws IOException;
    List<String> saveFile(String folder, MultipartFile[] files) throws IOException;
    void deleteFile(String folder, String filename);
    List<String> listFiles(String folder);
}
