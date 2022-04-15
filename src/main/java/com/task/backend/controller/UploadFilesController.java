package com.task.backend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.task.backend.service.UploadRecordsService;
import com.task.backend.service.ZipStorageService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

@RestController
@RequestMapping("/")
public class UploadFilesController {

    @Autowired
    private ZipStorageService zipService;

    @Autowired
    private UploadRecordsService upService;

    @PostMapping("/upload")
    public ResponseEntity<Resource> saveAndCompress(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) throws IOException {
        this.upService.logUploadRecordUsage(request.getRemoteAddr());

        List<String> fileNames = new ArrayList<>();
        Arrays.asList(files).stream().forEach(file -> {
            try {
                zipService.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileNames.add(file.getOriginalFilename());
        });

        String outputFile = zipService.getCompressedFileName(fileNames);
        zipService.compressFiles(fileNames);
        
        return this.returnCompressedFile(outputFile);
    }
    
    private ResponseEntity<Resource> returnCompressedFile(String filename) {
        Resource file = zipService.load(filename);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
            .body(file);
    }
}
