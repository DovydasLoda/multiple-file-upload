package com.task.backend.controller;

import java.util.ArrayList;
import java.util.List;

import com.task.backend.entity.UploadRecord;
import com.task.backend.service.UploadRecordsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UploadStatisticsController {

    @Autowired UploadRecordsService upService;

    @GetMapping("/")
    public ResponseEntity<List<UploadRecord>> getUsageRecords() {
        return ResponseEntity.ok(this.upService.getAllRecords());
    }
}
