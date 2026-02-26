package com.muse.desktop.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.muse.desktop.model.dto.internal.DocumentMetadata;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DocumentListPersistence {

    private final String filePath;
    private final ObjectMapper mapper;

    public DocumentListPersistence(String appDataDir) {
        this.filePath = Paths.get(appDataDir, "projects.json").toString();
        this.mapper = new ObjectMapper();

        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public List<DocumentMetadata> load() {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return mapper.readValue(file, new TypeReference<List<DocumentMetadata>>() {});
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void save(List<DocumentMetadata> documents) {
        try {
            File tempFile = new File(filePath + ".tmp");
            mapper.writeValue(tempFile, documents);

            File targetFile = new File(filePath);
            if (targetFile.exists()) {
                targetFile.delete();
            }
            Files.move(tempFile.toPath(), targetFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
