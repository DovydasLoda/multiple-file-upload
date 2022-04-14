package com.task.backend.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface CompressedStorageService {
    public File compressFiles(List<String> files) throws IOException;
    
    public String getCompressedFileName(List<String> files);
}
