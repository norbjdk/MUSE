package com.muse.desktop.manager;

import com.muse.desktop.model.dto.internal.OpenProjectRequest;
import com.norbjdk.core.JMXML;

public class ProjectManager {
    private static ProjectManager instance;
    private JMXML jmxml;

    private ProjectManager() {

    }

    public static ProjectManager getInstance() {
        if (instance == null) {
            instance = new ProjectManager();
        }

        return instance;
    }

    public void openProject(OpenProjectRequest request) {
        //jmxml.open(request.getFile());
    }
}
