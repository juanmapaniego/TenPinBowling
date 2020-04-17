package com.jmpaniego.TenPinBowling;

import com.jmpaniego.TenPinBowling.Classes.BowlingFrame;
import com.jmpaniego.TenPinBowling.Classes.BowlingPlayer;
import com.jmpaniego.TenPinBowling.Utils.IParser;
import com.jmpaniego.TenPinBowling.Utils.Parser;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class TenPinBowling {
    public static void main(String[] args) {
        String filename = "/home/jmpaniego/lines.txt";
        /*try {
            /*Files.lines(Paths.get(filename)).
                    map(l -> l.split(" ")[0]).
                    distinct().
                    forEach(System.out::println);*/
            /*Map<Object, List<Object>> filePlayers = Files.lines(Paths.get(filename)).
                    collect(Collectors.groupingBy(
                                l->l.split(" ")[0],
                                Collectors.mapping(l->l.split(" ")[1],Collectors.toList())
                            )
                    );
            List<BowlingPlayer> bowlingplayers = new ArrayList<>();
            BowlingPlayer player;
            for (Map.Entry<Object, List<Object>> entry : filePlayers.entrySet()) {
                player = new BowlingPlayer();
                player.setPlayerName(entry.getKey().toString());

                System.out.println(entry.getValue().size());
                //entry.getValue().stream().forEach(p -> System.out.println("\t"+p));
                bowlingplayers.add(player);
            }
        }catch (Exception e){
            e.printStackTrace();
        }*/
        try{
            IParser parser = new Parser();
            parser.readFile(filename);
            for(BowlingPlayer player : parser.getPlayers()){
                System.out.println(player.getPlayerName());
                System.out.println(player.getFrames().size());
                player.getFrames().stream().forEach(System.out::println);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.print("Error");
        }

    }
}
