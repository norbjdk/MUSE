package com.norbjdk.io.parser;

import com.norbjdk.model.common.MXML;
import com.norbjdk.model.score.PartList;
import com.norbjdk.model.score.ScoreInstrument;
import com.norbjdk.model.score.ScorePart;
import com.norbjdk.model.score.ScorePartwise;
import nu.xom.*;

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

        readMetaData(getScorePartwise());
        readPartList(getScorePartwise());

        return mxmlFile;
    }

    private void readMetaData(final ScorePartwise scorePartwise) {
        try {
            final Document document = builder.build(mxmlFile);
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

    private void readPartList(final ScorePartwise scorePartwise) {
        if (scorePartwise.getPartList() == null) {
            scorePartwise.setPartList(new PartList());
        }

        try {
            final Document document = builder.build(mxmlFile);
            final Element root = document.getRootElement();

            final Element partList = root.getFirstChildElement("part-list");
            if (isNullElement(partList)) {
                final Elements scoreParts = partList.getChildElements();
                if (scoreParts.size() <= 0) return;

                for (Element element : scoreParts) {
                    final ScorePart scorePart = new ScorePart();
                    final ScoreInstrument scoreInstrument = new ScoreInstrument();

                    // Part name area

                    final Element partName = element.getFirstChildElement("part-name");
                    if (isNullElement(partName)) {
                        scorePart.setPartName(partName.getValue());
                    }

                    // Part abbreviation area

                    final Element partAbbreviation = element.getFirstChildElement("part-abbreviation");
                    if (isNullElement(partAbbreviation)) {
                        scorePart.setPartAbbreviation(partAbbreviation.getValue());
                    }

                    // Score instrument area

                    final Element scoreInstr = element.getFirstChildElement("score-instrument");
                    if (isNullElement(scoreInstr)) {
                        final Element instrumentName = scoreInstr.getFirstChildElement("instrument-name");
                        final Element instrumentSound = scoreInstr.getFirstChildElement("instrument-sound");

                        if (isNullElement(instrumentName) && isNullElement(instrumentSound)) {
                            scoreInstrument.setInstrumentName(instrumentName.getValue());
                            scoreInstrument.setInstrumentSound(instrumentSound.getValue());
                        }
                    }

                    scorePart.setScoreInstrument(scoreInstrument);
                    scorePartwise.getPartList().getScoreParts().add(scorePart);
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
