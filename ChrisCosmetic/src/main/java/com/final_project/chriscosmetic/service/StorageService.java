package com.final_project.chriscosmetic.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String saveProductImage(MultipartFile file);

    void deleteProductImage(String fileName);
}
