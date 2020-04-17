package com.jmpaniego.TenPinBowling.Utils;

import com.jmpaniego.TenPinBowling.Classes.BowlingFrame;
import com.jmpaniego.TenPinBowling.Classes.BowlingPlayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser implements IParser {
    private Stream<String> lines;

    @Override
    public void readFile(String path) throws IOException {
        lines = Files.lines(Paths.get(path));
    }

    private Stream<String> getLines(){
        return lines;
    }

    /*@Override
    public List<BowlingPlayer> getPlayers() {
        try {
            List<BowlingPlayer> players = lines.map(l -> l.split(Constants.ROW_SEPARATOR)[0]).
                    distinct().
                    map(p -> new BowlingPlayer(p)).
                    collect(Collectors.toList());
            for(BowlingPlayer actualPlayer: players){
                System.out.println(actualPlayer.getPlayerName());
                lines.filter(l -> (l.split(Constants.ROW_SEPARATOR)[0]).equals(actualPlayer.getPlayerName())).
                        forEach(p -> System.out.println("\t"+p));
            }
            return players;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }*/

    @Override
    public List<BowlingPlayer> getPlayers(){
        Map<String, List<String>> filePlayers = lines.
                collect(Collectors.groupingBy(
                        l->l.split(Constants.ROW_SEPARATOR)[0],
                        Collectors.mapping(l->l.split(Constants.ROW_SEPARATOR)[1],Collectors.toList())
                        )
                );

        List<BowlingPlayer> bowlingplayers = filePlayers.
                keySet().
                stream().
                map(p -> new BowlingPlayer(p.toString())).
                collect(Collectors.toList());

        for(BowlingPlayer actualPlayer: bowlingplayers){
            filePlayers.
                    get(actualPlayer.getPlayerName()).
                    stream().
                    forEach(r -> {
                        if(actualPlayer.getFrames() == null)
                            actualPlayer.setFrames(new ArrayList<>());
                        short point = r.equals("F")?0:Short.parseShort(r);
                        BowlingFrame last = null;
                        short frameNumber;
                        if(actualPlayer.getFrames().size() != 0) {
                            last = actualPlayer.getFrames().get(actualPlayer.getFrames().size() - 1);
                            frameNumber = (short) (last.getFrameNumber() + 1);
                        }else{
                            frameNumber = Constants.FIRST_FRAME;
                        }
                        if((last != null) && (!last.isComplete())) {
                            last.setRoll(point);
                        }else{
                            actualPlayer.getFrames().add(new BowlingFrame(frameNumber,point));
                        }
                    });
        }

        return bowlingplayers;

    }
}
