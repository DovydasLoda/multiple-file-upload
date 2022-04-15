package com.task.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@EnableWebMvc
public class FileUploadTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wContext;

/**
 * TODO: fix test
 * When trying to start test I get:
 * java.lang.IllegalStateException: Failed to load ApplicationContext
 */
    @Test
    public void whenFileUploaded_thenVerifyStatus() throws Exception {

       DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wContext);
       this.mockMvc = builder.build();

        MockMultipartFile file1 = new MockMultipartFile(
                "file1",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes());

        MockMultipartFile file2 = new MockMultipartFile(
                "file2",
                "hello2.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World 2!".getBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(wContext).build();

        mockMvc.perform(multipart("/upload")
             .file(file1)
             .file(file2))
             .andExpect(status().isOk());
    }
}
