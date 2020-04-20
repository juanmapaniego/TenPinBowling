package com.jmpaniego.TenPinBowling.Classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BowlingFrameTest {

    @Test
    void isSpareTest(){
        BowlingFrame bowlingFrame = new BowlingFrame();
        bowlingFrame.setRoll("7");
        bowlingFrame.setRoll("3");
        assertTrue(bowlingFrame.isSpare());

        assertTrue(bowlingFrame.isComplete());
    }

    @Test
    void isStrikeTest(){
        BowlingFrame bowlingFrame = new BowlingFrame();
        bowlingFrame.setRoll("10");
        assertTrue(bowlingFrame.isStrike());

        assertTrue(bowlingFrame.isComplete());
    }

    @Test
    @DisplayName("Test of compareTo")
    void compareToTest(){
        BowlingFrame bowlingFrame = new BowlingFrame();
        bowlingFrame.setFrameNumber((short)1);
        assertEquals(0, bowlingFrame.compareTo(bowlingFrame));

        BowlingFrame bowlingFrame_compare = new BowlingFrame();
        bowlingFrame_compare.setFrameNumber((short)2);

        assertEquals(-1,bowlingFrame.compareTo(bowlingFrame_compare));

        assertEquals(1,bowlingFrame_compare.compareTo(bowlingFrame));
    }
}