package com.norbjdk.jmxml.model.music;

import java.util.ArrayList;
import java.util.List;

public class Part extends MusicModel {
    private List<Measure> measures;

    public Part() {
        measures = new ArrayList<>();
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }
}
