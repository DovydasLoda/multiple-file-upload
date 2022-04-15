package com.task.backend.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

public class AbstractStorage implements SimpleStorage {
    private final Path root = Paths.get("uploads");

    private void checkStorageExistance() {
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

    public Path getRoot() {
        return this.root;
    }

    @Override
    public Resource load(String fileName) {
        Path file = this.getRoot().resolve(fileName);
        Resource resource;
        try {
            resource = new UrlResource(file.toUri());
            return resource;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(MultipartFile file) throws IOException {
        this.checkStorageExistance();
        Files.copy(file.getInputStream(), this.getRoot().resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
    }

}
