package com.capstone.kuhako.services;

import com.capstone.kuhako.models.FileDB;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileStorageService {
    FileDB store(String base64Data, String fileName, String contentType) throws IOException;

    FileDB getFile(String id);

    Stream<FileDB> getAllFiles();

    FileDB getByFilename(String filename);
}
