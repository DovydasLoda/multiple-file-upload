package com.task.backend.repository;

import com.task.backend.entity.UploadRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRecordRepository extends JpaRepository<UploadRecord, Long> {
    public UploadRecord findByIp(String ip);
    
    public UploadRecord findByIpAndCreatedOn(String ip, String createdOn);
}