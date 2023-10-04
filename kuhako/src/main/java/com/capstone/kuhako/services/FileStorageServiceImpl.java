package com.capstone.kuhako.services;


import java.io.IOException;
import java.util.stream.Stream;

import com.capstone.kuhako.models.FileDB;
import com.capstone.kuhako.repositories.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Base64;


@Service
public class FileStorageServiceImpl implements FileStorageService{
    @Autowired
    private FileDBRepository fileDBRepository;

    @Override
    public FileDB store(String base64Data, String fileName, String contentType) throws IOException {
        byte[] data = Base64.getDecoder().decode(base64Data);
        FileDB fileDB = new FileDB(fileName, contentType, data);
        return fileDBRepository.save(fileDB);
    }

    @Override
    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    @Override
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    @Override
    public FileDB getByFilename(String filename) {
        return fileDBRepository.findByFilename(filename);
    }

}