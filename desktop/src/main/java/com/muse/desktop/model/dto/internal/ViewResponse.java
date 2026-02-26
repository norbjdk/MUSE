package com.muse.desktop.model.dto.internal;

import com.muse.desktop.model.ui.ViewContainer;
import javafx.scene.Node;

public class ViewResponse<T extends ViewContainer> {
    private final T view;

    public ViewResponse(T view) {
        this.view = view;
    }

    public T getView() {
        return view;
    }
}
