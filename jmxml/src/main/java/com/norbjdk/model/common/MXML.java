package com.norbjdk.model.common;

import com.norbjdk.model.score.ScorePartwise;

public class MXML {
    private ScorePartwise scorePartwise;

    public MXML() {
        scorePartwise = new ScorePartwise();
    }

    public ScorePartwise getScorePartwise() {
        return scorePartwise;
    }

    public void setScorePartwise(ScorePartwise scorePartwise) {
        this.scorePartwise = scorePartwise;
    }
}
