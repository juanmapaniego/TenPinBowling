package com.jmpaniego.TenPinBowling;

import com.jmpaniego.TenPinBowling.Classes.BowlingPlayer;
import com.jmpaniego.TenPinBowling.Utils.IParser;
import com.jmpaniego.TenPinBowling.Utils.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TenPinBowlingIT {
    @Test
    void twoPlayerTest(){
        String resourceName = "2_player.txt";
        ClassLoader classLoader = getClass().getClassLoader();

        IParser parser = new Parser();
        try{
            parser.readFile(classLoader.getResource(resourceName).getFile());
            List<BowlingPlayer> bowlingPlayerList = parser.getPlayers();
            assertEquals(2, bowlingPlayerList.size());

            assertEquals(10, bowlingPlayerList.get(0).getFrames().size());


        }catch (IOException e){
            fail(e.getMessage());
        }

    }

    @Test
    void perfectTest(){
        String resourceName = "perfect.txt";
        ClassLoader classLoader = getClass().getClassLoader();

        IParser parser = new Parser();
        try{
            parser.readFile(classLoader.getResource(resourceName).getFile());
            List<BowlingPlayer> bowlingPlayerList = parser.getPlayers();
            assertEquals(2, bowlingPlayerList.size());

            assertEquals(10, bowlingPlayerList.get(0).getFrames().size());
        }catch (IOException e){
            fail(e.getMessage());
        }

    }
}