package com.norbjdk.jmxml.model.music;

public class ScoreInstrument extends MusicModel {
    private String instrumentName;
    private String instrumentSound;

    public ScoreInstrument() {}

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public String getInstrumentSound() {
        return instrumentSound;
    }

    public void setInstrumentSound(String instrumentSound) {
        this.instrumentSound = instrumentSound;
    }
}
