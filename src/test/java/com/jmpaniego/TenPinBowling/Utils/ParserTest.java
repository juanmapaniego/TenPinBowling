package com.jmpaniego.TenPinBowling.Utils;

import org.junit.jupiter.api.*;

import java.io.IOException;

class ParserTest {

    @Test
    void readFile() {
        IParser parser = new Parser();
        Assertions.assertThrows(IOException.class,
                () ->  parser.readFile(""),
                "IOException should throw by empty file path");
    }
}