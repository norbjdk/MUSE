package com.norbjdk.io.parser;

import com.norbjdk.model.common.MXML;
import com.norbjdk.model.score.*;
import nu.xom.*;

import java.io.*;
import java.util.ArrayList;

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
        readParts(getScorePartwise());

        return mxmlFile;
    }

    private void readMetaData(final ScorePartwise scorePartwise) {
        try {
            final Document document = builder.build(mxmlFile);
            final Element root = document.getRootElement();

            // Work title area

            final Element work = root.getFirstChildElement("work");
            if (isNotNullElement(work)) {
                final Element title = work.getFirstChildElement("work-title");
                if (isNotNullElement(title)) {
                    scorePartwise.setWorkTitle(title.getValue());
                }
            }

            // Creator area

            final Element identification = root.getFirstChildElement("identification");
            if (isNotNullElement(identification)) {
                final Element creator = identification.getFirstChildElement("creator");
                if (isNotNullElement(creator)) {
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
            if (isNotNullElement(partList)) {
                final Elements scoreParts = partList.getChildElements();
                if (scoreParts.size() <= 0) return;

                for (Element element : scoreParts) {
                    final ScorePart scorePart = new ScorePart();
                    final ScoreInstrument scoreInstrument = new ScoreInstrument();

                    // Part name area

                    final Element partName = element.getFirstChildElement("part-name");
                    if (isNotNullElement(partName)) {
                        scorePart.setPartName(partName.getValue());
                    }

                    // Part abbreviation area

                    final Element partAbbreviation = element.getFirstChildElement("part-abbreviation");
                    if (isNotNullElement(partAbbreviation)) {
                        scorePart.setPartAbbreviation(partAbbreviation.getValue());
                    }

                    // Score instrument area

                    final Element scoreInstr = element.getFirstChildElement("score-instrument");
                    if (isNotNullElement(scoreInstr)) {
                        final Element instrumentName = scoreInstr.getFirstChildElement("instrument-name");
                        final Element instrumentSound = scoreInstr.getFirstChildElement("instrument-sound");

                        if (isNotNullElement(instrumentName) && isNotNullElement(instrumentSound)) {
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

    private void readParts(final ScorePartwise scorePartwise) {
        if (scorePartwise.getParts() == null) {
            scorePartwise.setParts(new ArrayList<>());
        }

        try {
            final Document document = builder.build(mxmlFile);
            final Element root = document.getRootElement();

            final Elements parts = root.getChildElements("part");
            if (parts.size() <= 0) return;
            for (Element element : parts) {
                final Part part = new Part();
                final Elements measures = element.getChildElements("measure");
                for (Element measure: measures) {
                    part.getMeasures().add(readMeasure(measure));
                }
                scorePartwise.getParts().add(part);
            }

        } catch (ParsingException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Measure readMeasure(final Element measure) {
        final Measure result = new Measure();

        if (isNotNullElement(measure)) {
            final Element attributes = measure.getFirstChildElement("attributes");
            if (isNotNullElement(attributes)) {
                final Element divisions = attributes.getFirstChildElement("divisions");
                final Element staves = attributes.getFirstChildElement("staves");
                final Element fifths = attributes.getFirstChildElement("key").getFirstChildElement("fifths");
                final Element beats = attributes.getFirstChildElement("time").getFirstChildElement("beats");
                final Element beatType = attributes.getFirstChildElement("time").getFirstChildElement("beat-type");
                final Element sign = attributes.getFirstChildElement("clef").getFirstChildElement("sign");
                final Element line = attributes.getFirstChildElement("clef").getFirstChildElement("line");

                if (isNotNullElement(divisions)
                    && isNotNullElement(staves)
                    && isNotNullElement(fifths)
                    && isNotNullElement(beats)
                    && isNotNullElement(beatType)
                    && isNotNullElement(sign)
                    && isNotNullElement(line))
                {
                    final Attributes measureAttributes = new Attributes
                            .Builder()
                            .setDivisions(Integer.parseInt(divisions.getValue()))
                            .setFifths(Integer.parseInt(fifths.getValue()))
                            .whatTime(
                                    Integer.parseInt(beats.getValue()),
                                    Integer.parseInt(beatType.getValue())
                            )
                            .setStaves(Integer.parseInt(staves.getValue()))
                            .whatClef(
                                    sign.getValue().charAt(0),
                                    Integer.parseInt(line.getValue())
                            ).build();
                    result.setAttributes(measureAttributes);

                    final Elements notes = measure.getChildElements("note");
                    if (notes.size() > 0) {
                        for (Element element : notes) {
                            result.addNote(readNote(element));
                        }
                    }
                }
            }
        }
        return result;
    }

    private Note readNote(final Element note) {
        if (isNotNullElement(note)) {
            final Element rest = note.getFirstChildElement("rest");
            final Element step = note.getFirstChildElement("step");
            final Element alter = note.getFirstChildElement("alter");
            final Element octave = note.getFirstChildElement("octave");
            final Element duration = note.getFirstChildElement("duration");
            final Element voice = note.getFirstChildElement("voice");
            final Element type = note.getFirstChildElement("type");
            final Element stem = note.getFirstChildElement("stem");
            final Element staff = note.getFirstChildElement("staff");

            if (isNotNullElement(rest)
                && isNotNullElement(step)
                && isNotNullElement(alter)
                && isNotNullElement(octave)
                && isNotNullElement(duration)
                && isNotNullElement(voice)
                && isNotNullElement(type)
                && isNotNullElement(stem)
                && isNotNullElement(staff)
            ) {
                return new Note
                        .Builder()
                        .isRest(Boolean.parseBoolean(rest.getValue()))
                        .whichStep(step.getValue().charAt(0))
                        .setAlter(Integer.parseInt(alter.getValue()))
                        .whichOctave(Integer.parseInt(octave.getValue()))
                        .setDuration(Integer.parseInt(duration.getValue()))
                        .whichVoice(Integer.parseInt(voice.getValue()))
                        .whatType(type.getValue())
                        .stemDirection(stem.getValue())
                        .whichStaff(Integer.parseInt(staff.getValue()))
                        .build();
            }
        }
        return null;
    }

    public MXML getMxml() {
        return mxml;
    }

    public ScorePartwise getScorePartwise() {
        return mxml.getScorePartwise();
    }

    private static boolean isNotNullElement(Element element) {
        return element != null;
    }
}
