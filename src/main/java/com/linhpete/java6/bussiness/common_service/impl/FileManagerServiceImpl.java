package com.linhpete.java6.bussiness.common_service.impl;

import com.linhpete.java6.bussiness.common_service.FileManagerService;
import jakarta.servlet.ServletContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileManagerServiceImpl implements FileManagerService {

    ServletContext servletContext;

    private Path getPath(String folder, String fileName){
        File dir = Paths.get(servletContext.getRealPath("/files/"),folder).toFile();
        if(!dir.exists()){
            dir.mkdirs();
        }
        return Paths.get(dir.getAbsolutePath(),fileName);
    }

    @Override
    public byte[] readFile(String folder, String filename) throws IOException {
        Path path = getPath(folder, filename);
        return Files.readAllBytes(path);
    }

    @Override
    public List<String> saveFile(String folder, MultipartFile[] files) throws IOException {
        List<String> fileNames = new ArrayList<>();
        for(MultipartFile file : files){
            String name = System.currentTimeMillis() + file.getOriginalFilename();
            String fileName = Integer.toHexString(name.hashCode()) + name.substring(name.lastIndexOf("."));
            Path path = getPath(folder, name);
            file.transferTo(path);
            fileNames.add(fileName);
        }
        return fileNames;
    }

    @Override
    public void deleteFile(String folder, String filename) {
        Path path = getPath(folder, filename);
        path.toFile().delete();
    }

    @Override
    public List<String> listFiles(String folder) {
        List<String> fileNames = new ArrayList<>();
        File dir = Paths.get(servletContext.getRealPath("/files/"),folder).toFile();
        if(dir.exists()){
            File[] files = dir.listFiles();
            assert files != null;
            for(File file : files){
                fileNames.add(file.getName());
            }
        }
        return fileNames;
    }
}
