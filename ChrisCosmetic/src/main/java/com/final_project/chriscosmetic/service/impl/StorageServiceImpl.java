package com.final_project.chriscosmetic.service.impl;

import com.final_project.chriscosmetic.exception.SaveUploadFileException;
import com.final_project.chriscosmetic.service.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {

    private static final String PRODUCT_IMAGE_PATH_STR = "./product-images/";
    private static final Path PRODUCT_IMAGE_PATH = Paths.get(PRODUCT_IMAGE_PATH_STR);

    @Override
    public String saveProductImage(MultipartFile file) {
        String fileName = UUID.randomUUID() + FilenameUtils.EXTENSION_SEPARATOR_STR + FilenameUtils.getExtension(file.getOriginalFilename());

        try{
            if (!Files.exists(PRODUCT_IMAGE_PATH)) {
                Files.createDirectories(PRODUCT_IMAGE_PATH);
            }

            InputStream inputStream = file.getInputStream();
            Path filePath = PRODUCT_IMAGE_PATH.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new SaveUploadFileException("Could not save uploaded file: " + fileName);
        }

        return fileName;
    }

    @Override
    public void deleteProductImage(String fileName) {
        Path filePath = PRODUCT_IMAGE_PATH.resolve(fileName);
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
