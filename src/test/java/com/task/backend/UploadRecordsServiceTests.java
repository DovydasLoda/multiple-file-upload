package com.task.backend;

import com.task.backend.entity.UploadRecord;
import com.task.backend.repository.UploadRecordRepository;
import com.task.backend.service.UploadRecordsService;
import com.task.backend.utils.DateUtils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UploadRecordsServiceTests {
    private final String oldIp = "192.168.1.100";

	@Autowired
	UploadRecordsService service;

	@Test
    public void should_find_no_statistics() {
        Iterable<UploadRecord> data = service.getAllRecords();
        assertThat(data).isEmpty();
    }

    @Test
    public void should_update_statistic() {
        service.logUploadRecordUsage(oldIp);
        assertThat(service.getAllRecords()).isNotEmpty();

        UploadRecord u = service.getAllRecords().get(0);
        assertThat(u).isNotNull();
        assertThat(u.getUsageCount()).isGreaterThan(0);

        service.logUploadRecordUsage(oldIp);
        u = service.getAllRecords().get(0);
        assertThat(u.getUsageCount()).isEqualTo(2);

    }
}
