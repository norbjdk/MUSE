package com.norbjdk.jmxml.model.music;

public class Pitch extends MusicModel {
    private char step;
    private int octave;

    public Pitch() {}

    public char getStep() {
        return step;
    }

    public void setStep(char step) {
        this.step = step;
    }

    public int getOctave() {
        return octave;
    }

    public void setOctave(int octave) {
        this.octave = octave;
    }
}
