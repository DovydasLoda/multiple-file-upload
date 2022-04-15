package com.task.backend.service;

import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Service;

@Service
public class ZipStorageService extends AbstractStorage implements CompressedStorageService {

    @Override
    public File compressFiles(List<String> files) throws IOException {
        File zipfile = this.getRoot().resolve(getCompressedFileName(files)).toFile();
        byte[] buf = new byte[1024];
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
        for (int i = 0; i < files.size(); i++) {
            InputStream in = this.load(files.get(i)).getInputStream();
            out.putNextEntry(new ZipEntry(files.get(i)));
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.closeEntry();
            in.close();
        }
        out.close();
        return zipfile;
    }

    @Override
    public String getCompressedFileName(List<String> files) {
        return "archive-" + files.size() + ".zip";
    }
}
