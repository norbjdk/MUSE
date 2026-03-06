package com.norbjdk.jmxml.model.music;

public class PartList extends MusicModel {
    private ScorePart scorePart;

    public PartList() {}

    public ScorePart getScorePart() {
        return scorePart;
    }

    public void setScorePart(ScorePart scorePart) {
        this.scorePart = scorePart;
    }
}
