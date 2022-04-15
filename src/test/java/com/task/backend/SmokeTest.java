package com.task.backend;

import static org.assertj.core.api.Assertions.assertThat;

import com.task.backend.controller.UploadFilesController;
import com.task.backend.controller.UploadStatisticsController;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private UploadStatisticsController controller;

    @Autowired
    private UploadFilesController uploadController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
        assertThat(uploadController).isNotNull();
    }
}
