package com.task.backend;

import com.task.backend.entity.UploadRecord;
import com.task.backend.repository.UploadRecordRepository;
import com.task.backend.service.UploadRecordsService;
import com.task.backend.utils.DateUtils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UploadRecordsRepositoryTests {
    private final String oldIp = "192.168.1.100";

	@Autowired
	UploadRecordRepository repository;

	@Test
    public void should_find_no_statistics_if_repository_is_empty() {
        Iterable<UploadRecord> data = repository.findAll();
        assertThat(data).isEmpty();
    }

    @Test
    public void should_store_and_find_a_statistic() {
        UploadRecord record = new UploadRecord();
        record.setIp(oldIp);
        repository.save(record);

        assertThat(record).hasFieldOrPropertyWithValue("ip", oldIp);
        assertThat(record).hasFieldOrPropertyWithValue("createdOn", DateUtils.getCurrentDate());
        assertThat(record).hasFieldOrPropertyWithValue("usageCount", record.getUsageCount());

        UploadRecord newRecord = repository.findByIpAndCreatedOn(oldIp, DateUtils.getCurrentDate());
        assertThat(newRecord).hasFieldOrPropertyWithValue("ip", oldIp);
        assertThat(newRecord).hasFieldOrPropertyWithValue("createdOn", DateUtils.getCurrentDate());
        assertThat(newRecord).hasFieldOrPropertyWithValue("usageCount", newRecord.getUsageCount());
    }
}
