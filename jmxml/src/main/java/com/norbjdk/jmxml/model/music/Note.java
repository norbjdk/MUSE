package com.norbjdk.jmxml.model.music;

import com.norbjdk.jmxml.annotation.DefineNote;
import com.norbjdk.jmxml.exception.AnnotationException;

public class Note extends MusicModel{
    private Pitch pitch;
    private int duration;
    private int voice;
    private String half;
    private String stem;
    private int staff;

    public Note() {
        if (!this.getClass().isAnnotationPresent(DefineNote.class)) {
            throw new AnnotationException("You need to add @UseNote to class: " + this.getClass().getSimpleName());
        }
        DefineNote config = this.getClass().getAnnotation(DefineNote.class);

        this.pitch = new Pitch();
        this.pitch.setOctave(config.octave());
        this.pitch.setStep(config.step());

        this.duration = config.duration();
        this.voice = config.voice();
        this.half = config.half();
        this.stem = config.stem();
        this.staff = config.staff();
    }

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    public String getHalf() {
        return half;
    }

    public void setHalf(String half) {
        this.half = half;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public int getStaff() {
        return staff;
    }

    public void setStaff(int staff) {
        this.staff = staff;
    }
}
