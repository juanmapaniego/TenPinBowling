package com.jmpaniego.TenPinBowling.Classes;

import com.jmpaniego.TenPinBowling.Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingPlayer {
    private String playerName;
    private List<BowlingFrame> frames;
    private Integer finalScore;

    public BowlingPlayer() {
        this.frames = new ArrayList<>();
    }

    public BowlingPlayer(String playerName) {
        this.frames = new ArrayList<>();
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

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder(this.playerName);
        res.append("\n");

        /* Pinfalls print */
        String pinfalls = "";
        pinfalls += this.getFrames().
                stream().
                map(f -> f.toString()).
                collect(Collectors.joining(""));
        res.append("Pinfalls");
        res.append("\t");
        res.append(pinfalls);
        /* */
        res.append("\n");
        /* Score prints */
        String score = "";
        score += this.getFrames().
                stream().
                map(f -> f.getAcumulatedScore().toString()).
                collect(Collectors.joining(Constants.ROW_SEPARATOR+Constants.ROW_SEPARATOR));
        res.append("Score\t\t");
        res.append(score);
        /* */

        return res.toString();
    }
}
