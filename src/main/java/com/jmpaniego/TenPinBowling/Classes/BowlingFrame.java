package com.jmpaniego.TenPinBowling.Classes;

import com.jmpaniego.TenPinBowling.Utils.Constants;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BowlingFrame {
    private short frameNumber;
    private short rollNumber;
    private int[] roll;
    private boolean complete;
    private Integer actualScore;

    public BowlingFrame() {
        complete = false;
        this.roll = new int[]{0, 0, 0};
        this.rollNumber = 0;
    }

    public BowlingFrame(short frameNumber, short point) {
        this.frameNumber = frameNumber;
        this.roll = new int[]{0, 0, 0};
        this.rollNumber = 0;
        this.setRoll(point);
    }

    public short getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(short frameNumber) {
        this.frameNumber = frameNumber;
    }

    public Integer getActualScore() {
        return actualScore;
    }

    public void setActualScore(Integer actualScore) {
        this.actualScore = actualScore;
    }

    private int sumPoints(int[] points) {
        return Arrays.stream(points).reduce(0, (a, b) -> a + b);
    }


    public boolean isStrike() {
        return roll[0] == 10;
    }

    public boolean isSpare() {
        return (roll[0] + roll[1]) == 10;
    }

    public boolean isComplete() {
        return this.complete;
    }


    public void setRoll(int point){
        this.roll[this.rollNumber++] = point;
        this.setComplete();
    }

    public int getRoll(int rollNumber){
        return this.roll[rollNumber];
    }

    private void setComplete() {
        if(sumPoints(roll) == 10 && this.frameNumber != Constants.MAX_FRAMES){
            complete = true;
        }else{
            if((rollNumber >= 2) && (this.frameNumber != Constants.MAX_FRAMES))
                complete = true;
            else
                complete=false;
        }
    }


    @Override
    public String toString() {
        return "BowlingFrame{" +
                "frameNumber=" + frameNumber +
                ", roll1=" + roll[0] +
                ", roll2=" + roll[1] +
                ", extra=" + roll[2] +
                ", complete=" + complete +
                ", actualScore=" + actualScore +
                '}';
    }
}
