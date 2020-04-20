package com.jmpaniego.TenPinBowling.Classes;

import com.jmpaniego.TenPinBowling.Utils.Constants;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BowlingFrame implements Comparable<BowlingFrame> {
    private short frameNumber;
    private short rollNumber;
    private String[] roll;
    //private int[] roll;
    private boolean complete;
    private Integer actualScore;
    private Integer acumulatedScore;

    public BowlingFrame() {
        this.actualScore = 0;
        this.complete = false;
        //this.roll = new int[]{0, 0, 0};
        this.roll = new String[]{"0", "0", "0"};
        this.rollNumber = 0;
    }

    public BowlingFrame(short frameNumber, String point) throws RuntimeException{
        this.actualScore = 0;
        this.frameNumber = frameNumber;
        this.roll = new String[]{"0", "0", "0"};
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

    private void updateActualScore(){
        this.actualScore = this.sumPoints();
    }

    public void updateActualScore(int data){
        this.actualScore += data;
    }

    public void setActualScore(Integer actualScore) {
        this.actualScore = actualScore;
    }

    private int sumPoints() {
        //return Arrays.stream(roll).reduce(0, (a, b) -> a + b);
        return IntStream.range(0,roll.length).map(i -> to_number(roll[i])).sum();
    }


    public boolean isStrike() {
        return frameNumber != Constants.MAX_FRAMES && to_number(roll[0]) == 10;
    }

    public boolean isSpare() {
        return (to_number(roll[0]) + to_number(roll[1])) == 10 && to_number(roll[0]) != 10;
    }

    public boolean isComplete() {
        return this.complete;
    }


    /*public void setRoll(int point){
        this.roll[this.rollNumber++] = point;
        this.setComplete();
        this.updateActualScore();
    }*/
    public void setRoll(String point) throws RuntimeException{
        this.roll[this.rollNumber++] = point;
        if(isNotValid())
            throw new RuntimeException("Rolls points are incorrect");
        this.setComplete();
        this.updateActualScore();
    }


    public int getRoll(int rollNumber){
        return to_number(this.roll[rollNumber]);
    }

    public String getRollAsString(int rollNumber){
        return this.roll[rollNumber];
    }

    private void setComplete() {
        if(sumPoints() == 10 && this.frameNumber != Constants.MAX_FRAMES){
            complete = true;
        }else{
            if((rollNumber >= 2) && (this.frameNumber != Constants.MAX_FRAMES))
                complete = true;
            else
                complete=false;
        }
    }

    private boolean isNotValid() {
        if(sumPoints() > 10 && this.frameNumber != Constants.MAX_FRAMES){
            return true;
        }else{
            if(sumPoints() > 30)
                return true;
            return false;
        }
    }

    /*@Override
    public String toString() {
        String res = "";
        if(frameNumber != Constants.MAX_FRAMES) {
            if (this.isStrike()) {
                res += "\tX\t";
            }else{
                res += roll[Constants.FIRST_ROLL]+"\t";
                if(this.isSpare()){
                    res += "/\t";
                }else{
                    res += roll[Constants.SECOND_ROLL]+"\t";
                }
            }
        }else{
            res += roll[Constants.FIRST_ROLL]=="10"?"X\t":roll[Constants.FIRST_ROLL]+"\t";
            res += roll[Constants.SECOND_ROLL]=="10"?"X\t":roll[Constants.SECOND_ROLL]+"\t";
            res += roll[Constants.EXTRA_ROLL]=="10"?"X\t":roll[Constants.EXTRA_ROLL]+"\t";
        }
        return res;

        return "BowlingFrame{" +
                "frameNumber=" + frameNumber +
                ", roll1=" + roll[0] +
                ", roll2=" + roll[1] +
                ", extra=" + roll[2] +
                ", complete=" + complete +
                ", actualScore=" + actualScore +
                '}';
    }*/

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        if(this.frameNumber!= Constants.MAX_FRAMES){
            if(this.isStrike()){
                res.append(Constants.ROW_SEPARATOR);
                res.append("X");
                res.append(Constants.ROW_SEPARATOR);
            }else{
                res.append(roll[Constants.FIRST_ROLL]);
                res.append(Constants.ROW_SEPARATOR);
                if(this.isSpare()){
                    res.append("/");
                }else{
                    res.append(roll[Constants.SECOND_ROLL]);
                }
                res.append(Constants.ROW_SEPARATOR);
            }
        }else {
            if(roll[Constants.FIRST_ROLL].equals("10")) {
                res.append("X");
            }else{
                res.append(roll[Constants.FIRST_ROLL]);
            }
            res.append(Constants.ROW_SEPARATOR);
            if(this.isSpare()){
                res.append("/");
            }else{
                if(roll[Constants.SECOND_ROLL].equals("10")) {
                    res.append("X");
                }else{
                    res.append(roll[Constants.SECOND_ROLL]);
                }
            }
            res.append(Constants.ROW_SEPARATOR);
            if((to_number(roll[Constants.FIRST_ROLL]) + to_number(roll[Constants.SECOND_ROLL])) >= 10){
                if(roll[Constants.EXTRA_ROLL].equals("10")) {
                    res.append("X");
                }else{
                    res.append(roll[Constants.EXTRA_ROLL]);
                }
            }


        }
        return res.toString();
    }


    @Override
    public int compareTo(BowlingFrame aFrame) {
        int r1 = this.getFrameNumber();
        int r2 = aFrame.getFrameNumber();

        return r1-r2;
    }

    /*public void setSpecialRoll(int x){
        this.roll[2] = x;
    }*/

    public void setSpecialRoll(String x){
        this.roll[2] = x;
    }

    public Integer getAcumulatedScore() {
        return acumulatedScore;
    }

    public void setAcumulatedScore(Integer acumulatedScore) {
        this.acumulatedScore = acumulatedScore + actualScore;
    }

    private int to_number(String roll){
        return roll.equals(Constants.FOUL)?0:Integer.parseInt(roll);
    }

}
