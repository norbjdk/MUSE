package com.norbjdk.jmxml.model.music;

public class Time extends MusicModel {
    private int beats;
    private int beatType;

    public Time() {}

    public int getBeats() {
        return beats;
    }

    public void setBeats(int beats) {
        this.beats = beats;
    }

    public int getBeatType() {
        return beatType;
    }

    public void setBeatType(int beatType) {
        this.beatType = beatType;
    }
}
