package com.jmpaniego.TenPinBowling.Utils;

import com.jmpaniego.TenPinBowling.Classes.BowlingPlayer;

import java.io.IOException;
import java.util.List;

public interface IParser {
    void readFile(String path) throws IOException;
    List<BowlingPlayer> getPlayers() throws IOException;
}
