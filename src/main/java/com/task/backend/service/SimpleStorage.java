package com.task.backend.service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface SimpleStorage {
    public void save(MultipartFile file) throws IOException;

    public Resource load(String fileName);
}
