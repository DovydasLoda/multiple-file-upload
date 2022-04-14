package com.task.backend.controller;

import java.util.ArrayList;

import com.task.backend.entity.UploadRecord;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UploadStatisticsController {

    @GetMapping("/")
    public ResponseEntity<ArrayList<UploadRecord>> getUsageRecords() {
        return ResponseEntity.ok(new ArrayList<UploadRecord>());
    }
}
