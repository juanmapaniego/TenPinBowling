package com.jmpaniego.TenPinBowling.Utils;

import com.jmpaniego.TenPinBowling.Classes.BowlingFrame;
import com.jmpaniego.TenPinBowling.Classes.BowlingPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser implements IParser {
    private List<String> lines;

    @Override
    public void readFile(String path) throws IOException {
        File f = new File(path);
        if(!f.exists())
            throw new FileNotFoundException("File do not exist");
        if(f.isDirectory())
            throw new FileNotFoundException("Path is a directory");
        lines = Files.lines(Paths.get(path)).collect(Collectors.toList());
        if(getLines().count() < Constants.MINIMUM_FILE_LINES) {
            /*
            The file is malformed, too short for data of 1 player game
             */
            throw new IOException("Malformed file: few lines than expected");
        }
    }

    private Stream<String> getLines(){
        return lines.stream();
    }

    @Override
    public List<BowlingPlayer> getPlayers() throws Exception{
        Map<String, List<String>> filePlayers = getMapFile();

        List<BowlingPlayer> bowlingPlayers = getNames(filePlayers);

        for(BowlingPlayer actualPlayer: bowlingPlayers){
            filePlayers.
                    get(actualPlayer.getPlayerName()).
                    stream().
                    forEach(r -> {
                        //short point = r.equals(Constants.FOUL)?0:Short.parseShort(r);
                        try{
                            BowlingFrame last = null;
                            short frameNumber;
                            if(actualPlayer.getFrames().size() != 0) {
                                last = actualPlayer.getFrames().get(actualPlayer.getFrames().size() - 1);
                                frameNumber = (short) (last.getFrameNumber() + 1);
                            }else{
                                frameNumber = Constants.FIRST_FRAME;
                            }
                            if((last != null) && (!last.isComplete())) {
                                last.setRoll(r);
                            }else{
                                actualPlayer.getFrames().add(new BowlingFrame(frameNumber,r));
                            }
                        }catch (RuntimeException e){
                            throw e;
                        }
                    });
        }

        updateScore(bowlingPlayers);

        return bowlingPlayers;
    }

    private List<BowlingPlayer> getNames( Map<String, List<String>> filePlayers){
        return filePlayers.
                keySet().
                stream().
                map(p -> new BowlingPlayer(p.toString())).
                collect(Collectors.toList());
    }

    private Map<String, List<String>> getMapFile(){
       return getLines().
                collect(Collectors.groupingBy(
                        l->l.split(Constants.ROW_SEPARATOR)[0],
                        Collectors.mapping(l->l.split(Constants.ROW_SEPARATOR)[1],Collectors.toList())
                        )
                );
    }

    private void updateScore(List<BowlingPlayer> bowlingPlayers) throws IOException{
        BowlingFrame prevFrame = null;

        for(BowlingPlayer actualPlayer: bowlingPlayers){
            if(actualPlayer.getFrames().size()!=10)
                throw new IOException("Malformed file: Less than 10 frames");
            prevFrame = null;
            Collections.sort(actualPlayer.getFrames(),Collections.reverseOrder());
            for(BowlingFrame frame: actualPlayer.getFrames()){
                if(prevFrame!=null){
                    if(frame.isStrike())
                        frame.setSpecialRoll(prevFrame.getRollAsString(0));
                }
                if(frame.getFrameNumber() != Constants.MAX_FRAMES ){
                    if(frame.isStrike()) {
                        int aux_ind = prevFrame.isStrike()?2:1;
                        frame.updateActualScore(prevFrame.getRoll(Constants.FIRST_ROLL) + prevFrame.getRoll(aux_ind));
                    }else {
                        if (frame.isSpare()) {
                            frame.updateActualScore(prevFrame.getRoll(Constants.FIRST_ROLL));
                        }
                    }
                }
                prevFrame = frame;
            }
            Collections.sort(actualPlayer.getFrames());

            int acumulated = 0;
            for(BowlingFrame actualFrame: actualPlayer.getFrames()){
                actualFrame.setAcumulatedScore(acumulated);
                acumulated = actualFrame.getAcumulatedScore();
            }
            actualPlayer.setFinalScore(acumulated);
        }
    }
}
