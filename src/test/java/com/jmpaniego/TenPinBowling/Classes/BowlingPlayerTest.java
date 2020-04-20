package com.jmpaniego.TenPinBowling.Classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingPlayerTest {

    @Test
    void getFramesTest() {
        BowlingPlayer bowlingPlayer = new BowlingPlayer();
        assertNotNull(bowlingPlayer.getFrames());
    }
}