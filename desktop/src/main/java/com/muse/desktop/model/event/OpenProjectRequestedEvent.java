package com.muse.desktop.model.event;

import com.muse.desktop.model.dto.internal.OpenProjectRequest;

public class OpenProjectRequestedEvent {
    private final OpenProjectRequest request;

    public OpenProjectRequestedEvent(OpenProjectRequest request) {
        this.request = request;
    }

    public OpenProjectRequest getRequest() {
        return request;
    }
}
