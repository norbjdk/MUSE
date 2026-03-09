package com.norbjdk.core;

import com.norbjdk.manager.JMXMLManager;
import com.norbjdk.model.common.MXML;

import java.io.File;

public class JMXML {
    private final JMXMLManager jmxmlManager;

    public JMXML() {
        jmxmlManager = JMXMLManager.getInstance();
    }

    public void open(File file) {
        jmxmlManager.openFile(file.getAbsolutePath());
    }

    public void open(String filePath) {
        jmxmlManager.openFile(filePath);
    }

    public MXML getMXMLData() {
        return jmxmlManager.getMxmlData();
    }
}
