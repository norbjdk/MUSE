package com.norbjdk.io.parser;

import com.norbjdk.model.common.MXML;
import com.norbjdk.model.score.ScorePartwise;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;

import java.io.*;

public class MusicXMLParser {
    private final Builder builder;

    private final StringBuilder stringData;
    private final MXML mxml;

    private File mxmlFile;

    public MusicXMLParser(MXML mxml) {
        this.mxml = mxml;

        builder = new Builder();
        stringData = new StringBuilder();
    }

    public File readFile(String filePath, boolean writeOutData) {
        try {
            mxmlFile = new File(filePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (writeOutData) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringData.append(line).append("\n");
                }
                System.out.println(stringData);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Could not find the file: " + filePath, e);
            } catch (IOException e) {
                throw new RuntimeException("Error during loading the file: " + filePath, e);
            }
        }

        readMetaData(getScorePartwise(), mxmlFile);

        return mxmlFile;
    }

    private void readMetaData(final ScorePartwise scorePartwise, File file) {
        try {
            final Document document = builder.build(file);
            final Element root = document.getRootElement();

            // Work title area

            final Element work = root.getFirstChildElement("work");
            if (isNullElement(work)) {
                final Element title = work.getFirstChildElement("work-title");
                if (isNullElement(title)) {
                    scorePartwise.setWorkTitle(title.getValue());
                }
            }

            // Creator area

            final Element identification = root.getFirstChildElement("identification");
            if (isNullElement(identification)) {
                final Element creator = identification.getFirstChildElement("creator");
                if (isNullElement(creator)) {
                    scorePartwise.setCreator(creator.getValue());
                }
            }

        } catch (ParsingException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MXML getMxml() {
        return mxml;
    }

    public ScorePartwise getScorePartwise() {
        return mxml.getScorePartwise();
    }

    private static boolean isNullElement(Element element) {
        return element != null;
    }
}
