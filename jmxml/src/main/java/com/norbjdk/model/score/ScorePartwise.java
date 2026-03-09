package com.norbjdk.model.score;

public class ScorePartwise {
    private String workTitle;
    private String creator;
    private PartList partList;

    public ScorePartwise() {}

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
}
