package com.muse.desktop.model.dto.internal;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class DocumentMetadata {
    @JsonProperty
    private String filePath;
    @JsonProperty
    private String title;
    @JsonProperty
    private LocalDateTime lastModified;

    public DocumentMetadata() {}

    public DocumentMetadata(String filePath, String title, LocalDateTime lastModified) {
        this.filePath = filePath;
        this.title = title;
        this.lastModified = lastModified;
    }

    public String getFilePath() {

        return filePath;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }
}
