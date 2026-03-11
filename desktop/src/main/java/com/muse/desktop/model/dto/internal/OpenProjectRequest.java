package com.muse.desktop.model.dto.internal;

import java.io.File;

public class OpenProjectRequest {
    private final File file;

    public OpenProjectRequest(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
