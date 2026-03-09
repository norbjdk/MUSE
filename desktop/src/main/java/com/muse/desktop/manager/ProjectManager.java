package com.muse.desktop.manager;

import com.norbjdk.core.JMXML;

public class ProjectManager {
    private static ProjectManager instance;
    private final JMXML jmxml;

    private ProjectManager() {
        jmxml = new JMXML();
    }

    public static ProjectManager getInstance() {
        if (instance == null) {
            instance = new ProjectManager();
        }

        return instance;
    }
}
