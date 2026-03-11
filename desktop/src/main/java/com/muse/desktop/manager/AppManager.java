package com.muse.desktop.manager;

import com.muse.desktop.model.dto.internal.OpenProjectRequest;
import com.muse.desktop.model.dto.internal.ViewRequest;
import com.muse.desktop.model.event.ChangeViewRequestedEvent;
import com.muse.desktop.model.event.OpenProjectRequestedEvent;
import com.muse.desktop.service.EventBus;


public class AppManager {
    private static AppManager instance;
    private final ViewManager viewManager;
    private final ProjectManager projectManager;
    private AppManager () {
        viewManager = ViewManager.getInstance();
        projectManager = ProjectManager.getInstance();

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

    public void openProject(OpenProjectRequest request) {
        projectManager.openProject(request);
    }


    private void setupListeners() {
        EventBus.getInstance().subscribe(ChangeViewRequestedEvent.class, event -> {
            changeView(event.getRequest());
        });
        EventBus.getInstance().subscribe(OpenProjectRequestedEvent.class, event -> {
            openProject(event.getRequest());
        });
    }
}
