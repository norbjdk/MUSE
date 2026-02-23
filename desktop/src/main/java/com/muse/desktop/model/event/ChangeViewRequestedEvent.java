package com.muse.desktop.model.event;

import com.muse.desktop.model.dto.internal.ViewRequest;

public class ChangeViewRequestedEvent {
    private final ViewRequest request;

    public ChangeViewRequestedEvent(ViewRequest request) {
        this.request = request;
    }

    public ViewRequest getRequest() {
        return request;
    }
}
