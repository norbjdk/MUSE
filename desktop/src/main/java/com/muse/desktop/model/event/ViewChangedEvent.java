package com.muse.desktop.model.event;

import com.muse.desktop.model.dto.internal.ViewResponse;

public class ViewChangedEvent {
    private final ViewResponse response;

    public ViewChangedEvent(ViewResponse response) {
        this.response = response;
    }

    public ViewResponse getResponse() {
        return response;
    }
}
