package com.norbjdk.manager;

import com.norbjdk.event.EventBus;
import com.norbjdk.io.parser.MusicXMLParser;
import com.norbjdk.io.writer.MusicXMLWriter;
import com.norbjdk.model.common.MXML;

import java.io.File;

public class JMXMLManager {
    private static JMXMLManager instance;
    private final EventBus eventBus;

    private MusicXMLParser musicXMLParser;
    private MusicXMLWriter musicXMLWriter;

    private static File mxmlFile;
    private static MXML mxmlData;

    private JMXMLManager() {
        mxmlData = new MXML();

        musicXMLParser = new MusicXMLParser(mxmlData);

        eventBus = EventBus.getInstance();
    }

    public static JMXMLManager getInstance() {
        if (instance == null) {
            instance = new JMXMLManager();
        }

        return instance;
    }

    public void openFile(String path) {
        mxmlFile = musicXMLParser.readFile(path, false);
    }

    public void saveFile() {}

    public MXML getMxmlData() {
        return mxmlData;
    }

    public File getMxmlFile() {
        return mxmlFile;
    }

    public static void main(String [] args) {
        JMXMLManager manager = new JMXMLManager();
        manager.openFile("/Users/norbjdk/Downloads/template3.musicxml");

        System.out.println(manager.getMxmlData().getScorePartwise().getWorkTitle());
        System.out.println(manager.getMxmlData().getScorePartwise().getCreator());

        manager.getMxmlData().getScorePartwise().getPartList().getScoreParts().forEach(scorePart -> {
            System.out.println(scorePart.getPartName());
            System.out.println(scorePart.getPartAbbreviation());
            System.out.println(scorePart.getScoreInstrument().getInstrumentName());
            System.out.println(scorePart.getScoreInstrument().getInstrumentSound());
        });

        System.out.println("Size: " + manager.getMxmlData().getScorePartwise().getParts().getFirst().getMeasures().size());
    }
}
