package com.norbjdk.jmxml.model.music;

public class Clef extends MusicModel{
    private String sign;
    private int line;

    public Clef() {}

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}
