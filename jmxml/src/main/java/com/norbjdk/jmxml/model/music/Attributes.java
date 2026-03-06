package com.norbjdk.jmxml.model.music;

import java.util.ArrayList;
import java.util.List;

public class Attributes extends MusicModel {
    private int divisions;
    private String key;
    private Time time;
    private int staves;
    private List<Clef> clefs;

    public Attributes() {
        clefs = new ArrayList<>();
    }

    public int getDivisions() {
        return divisions;
    }

    public void setDivisions(int divisions) {
        this.divisions = divisions;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getStaves() {
        return staves;
    }

    public void setStaves(int staves) {
        this.staves = staves;
    }

    public List<Clef> getClefs() {
        return clefs;
    }

    public void setClefs(List<Clef> clefs) {
        this.clefs = clefs;
    }
}
