package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageService {

    @Value("${upload.path}")
    private String path;

    public void uploadFile(MultipartFile file) {
        if (file.isEmpty()) {

            throw new StorageException("Failed to store empty file");
        }
        try {
            String fileName = file.getOriginalFilename();
            InputStream is = file.getInputStream();

            Files.copy(is, Paths.get("C:\\Users\\pc\\IdeaProjects\\ConnectMysql1\\src\\main\\java\\com\\example\\demo\\images\\" + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {

            String msg = String.format("Failed to store file %f", file.getName());

            throw new StorageException(msg, e);
        }
    }
}
