package com.norbjdk.jmxml.model.music;

import java.util.ArrayList;
import java.util.List;

public class Measure extends MusicModel {
    private Attributes attributes;
    private List<Note> notes;

    public Measure() {
        notes = new ArrayList<>();
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
