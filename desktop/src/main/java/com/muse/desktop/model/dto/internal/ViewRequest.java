package com.muse.desktop.model.dto.internal;

import com.muse.desktop.model.ui.ViewName;

public class ViewRequest {
    private final ViewName viewName;

    public ViewRequest(ViewName viewName) {
        this.viewName = viewName;
    }

    public ViewName getViewName() {
        return viewName;
    }
}
