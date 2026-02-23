package com.muse.desktop.manager;

import com.muse.desktop.model.dto.internal.ViewRequest;
import com.muse.desktop.model.dto.internal.ViewResponse;
import com.muse.desktop.model.event.ViewChangedEvent;
import com.muse.desktop.model.ui.ViewContainer;
import com.muse.desktop.model.ui.ViewName;
import com.muse.desktop.service.EventBus;
import com.muse.desktop.view.CollectionView;
import com.muse.desktop.view.HomeView;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ViewManager {
    private static ViewManager instance;
    private final Map<ViewName, ViewContainer> views;
    private ViewContainer currentView;

    private ViewManager() {
        views = new ConcurrentHashMap<>();

        initViews();
    }

    protected static ViewManager getInstance() {
        if (instance == null) {
            instance = new ViewManager();
        }
        return instance;
    }

    protected <T extends ViewContainer> void changeView(ViewRequest request) {
        @SuppressWarnings("unchecked")
        final T newView = (T) views.get(request.getViewName());

        if (newView != null) {
            currentView = newView;

            final ViewResponse<T> response = new ViewResponse<>(newView);

            EventBus.getInstance().publish(new ViewChangedEvent(response));
        }
    }

    private void initViews() {
        addView(ViewName.COLLECTION, new CollectionView());
        addView(ViewName.HOME, new HomeView());
    }

    private <T extends ViewContainer> void addView(ViewName name, T view) {
        if (view != null && !views.containsKey(name)) {
            views.put(name, view);
        }
    }

    private void removeView(ViewName name) {
        views.remove(name);
    }
}
