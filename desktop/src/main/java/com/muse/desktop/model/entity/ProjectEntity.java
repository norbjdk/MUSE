package com.muse.desktop.model.entity;

import com.norbjdk.model.score.ScorePartwise;

import java.time.LocalDateTime;

public class ProjectEntity {
    private String name;
    private String composer;
    private String album;
    private LocalDateTime latestUpdate;
    private ScorePartwise scorePartwise;

    public ProjectEntity() {
        latestUpdate = LocalDateTime.now();
    }

    public enum Template {
        TREBLE, BASS, GRAND
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public LocalDateTime getLatestUpdate() {
        return latestUpdate;
    }

    public void setLatestUpdate(LocalDateTime latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    public ScorePartwise getScorePartwise() {
        return scorePartwise;
    }

    public void setScorePartwise(ScorePartwise scorePartwise) {
        this.scorePartwise = scorePartwise;
    }
}

