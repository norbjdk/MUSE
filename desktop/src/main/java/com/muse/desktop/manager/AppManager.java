package com.muse.desktop.manager;

import com.muse.desktop.model.dto.internal.ViewRequest;
import com.muse.desktop.model.event.ChangeViewRequestedEvent;
import com.muse.desktop.service.EventBus;


public class AppManager {
    private static AppManager instance;
    private final ViewManager viewManager;
    private AppManager () {
        viewManager = ViewManager.getInstance();

        setupListeners();
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

    private void setupListeners() {
        EventBus.getInstance().subscribe(ChangeViewRequestedEvent.class, event -> {
            changeView(event.getRequest());
        });
    }
}
