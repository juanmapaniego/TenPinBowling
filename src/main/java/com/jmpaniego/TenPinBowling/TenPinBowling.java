package com.jmpaniego.TenPinBowling;

import com.jmpaniego.TenPinBowling.Classes.BowlingPlayer;
import com.jmpaniego.TenPinBowling.Utils.*;
import java.util.List;


public class TenPinBowling {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("Cannot execute without arg");
            System.out.println("\tSample: *.jar arg");
            System.exit(1);
        }
        String filename = args[0];
        try{
            IParser parser = new Parser();
            parser.readFile(filename);
            List<BowlingPlayer> bowlingPlayers = parser.getPlayers();
            IPrinter printer = new Printer();
            printer.writeHeader();
            printer.writeList(bowlingPlayers);
        }catch (Exception e){
            System.out.println("Error ");
            System.out.println("\t" + e.getMessage());
        }
    }
}
