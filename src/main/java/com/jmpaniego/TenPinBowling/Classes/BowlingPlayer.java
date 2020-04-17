package com.jmpaniego.TenPinBowling.Classes;

import java.util.List;

public class BowlingPlayer {
    private String playerName;
    private List<BowlingFrame> frames;
    private Integer finalScore;

    public BowlingPlayer() {
    }

    public BowlingPlayer(String playerName) {
        this.setPlayerName(playerName);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<BowlingFrame> getFrames() {
        return frames;
    }

    public void setFrames(List<BowlingFrame> frames) {
        this.frames = frames;
    }

    public Integer getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Integer finalScore) {
        this.finalScore = finalScore;
    }
}
