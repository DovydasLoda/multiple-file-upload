package com.task.backend.service;

import java.util.List;

import com.task.backend.entity.UploadRecord;
import com.task.backend.repository.UploadRecordRepository;
import com.task.backend.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadRecordsService {
    @Autowired
    private UploadRecordRepository repository;

    public UploadRecord saveUploadRecord(UploadRecord obj) {
        return this.repository.save(obj);
    }

    public UploadRecord findByIp(String ip) {
        return this.repository.findByIp(ip);
    }

    public UploadRecord findByAndCreatedOn(String ip, String createdOn) {
        return this.repository.findByIpAndCreatedOn(ip, createdOn);
    }

    public List<UploadRecord> getAllRecords() {
        return this.repository.findAll();
    }

    public void logUploadRecordUsage(String ip) {
        UploadRecord oldRecord = this.findByAndCreatedOn(ip, DateUtils.getCurrentDate());
        if (oldRecord == null) {
            oldRecord = new UploadRecord();
            oldRecord.setIp(ip);
        } else {
            oldRecord.setUsageCount(oldRecord.getUsageCount() + 1);
        }
        this.saveUploadRecord(oldRecord);
    }
    
}
