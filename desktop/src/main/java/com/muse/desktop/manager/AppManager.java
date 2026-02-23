package com.muse.desktop.manager;

import com.muse.desktop.model.dto.internal.ViewRequest;


public class AppManager {
    private static AppManager instance;
    private final ViewManager viewManager;
    private AppManager () {
        viewManager = ViewManager.getInstance();
    }

    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    public void changeView(ViewRequest request) {
        viewManager.changeView(request);
    }
}
