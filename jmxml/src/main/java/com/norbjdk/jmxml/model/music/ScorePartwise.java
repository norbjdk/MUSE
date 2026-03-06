package com.norbjdk.jmxml.model.music;

import java.util.ArrayList;
import java.util.List;

public class ScorePartwise extends MusicModel {
    private String workTitle;
    private String creator;
    private PartList partList;
    private List<Part> parts;

    public ScorePartwise() {
        parts = new ArrayList<>();
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public PartList getPartList() {
        return partList;
    }

    public void setPartList(PartList partList) {
        this.partList = partList;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
