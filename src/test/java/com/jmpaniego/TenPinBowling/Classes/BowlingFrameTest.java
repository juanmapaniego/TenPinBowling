package com.jmpaniego.TenPinBowling.Classes;

import com.jmpaniego.TenPinBowling.Utils.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.naming.CompositeName;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BowlingFrameTest {

    @Test
    void isSpareTest(){
        BowlingFrame bowlingFrame = new BowlingFrame();
        assertDoesNotThrow(() -> bowlingFrame.setRoll("7"));
        assertDoesNotThrow(() -> bowlingFrame.setRoll("3"));
        assertTrue(bowlingFrame.isSpare());

        assertTrue(bowlingFrame.isComplete());
    }

    @Test
    void isStrikeTest(){
        BowlingFrame bowlingFrame = new BowlingFrame();
        assertDoesNotThrow(() -> bowlingFrame.setRoll("10"));
        assertTrue(bowlingFrame.isStrike());

        assertTrue(bowlingFrame.isComplete());
    }

    @Test
    @DisplayName("Not valid Test")
    void notValidFrameTest(){
        BowlingFrame bowlingFrame = new BowlingFrame();
        bowlingFrame.setFrameNumber(Constants.FIRST_FRAME);
        assertThrows(RuntimeException.class, () -> bowlingFrame.setRoll("11"), "Not Valid frame, must throw" );

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