package com.task.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.task.backend.utils.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
/**
 * Describes Upload Statistics entity
 */
public class UploadRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ip;
    private Long usageCount;
    private String createdOn;

    public UploadRecord() {
        this.createdOn = DateUtils.getCurrentDate();
        this.usageCount = 1L;
    }
}
