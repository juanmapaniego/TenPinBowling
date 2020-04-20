package com.jmpaniego.TenPinBowling.Utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Printer implements IPrinter{
    private PrintStream originalPrintStream;
    private PrintStream printStream;

    public Printer() {
        originalPrintStream = System.out;
    }

    @Override
    public PrintStream getPrintStream() {
        return printStream;
    }

    @Override
    public void setPrintStream(String filename) throws FileNotFoundException, SecurityException {
        FileOutputStream file = new FileOutputStream(filename);
        this.printStream = new PrintStream(file);
    }

    @Override
    public void writeHeader(){
        StringBuilder header = new StringBuilder("Frame");
        header.append(Constants.ROW_SEPARATOR);
        header.append(Constants.ROW_SEPARATOR);
        header.append(
                IntStream.rangeClosed(Constants.FIRST_FRAME,Constants.MAX_FRAMES).
                        mapToObj(f -> ((Integer)f).toString()).
                        collect(Collectors.joining(Constants.ROW_SEPARATOR + Constants.ROW_SEPARATOR))
        );
        System.out.println(header.toString());
    }

    @Override
    public void writeList(List<?> list){
        list.stream().forEach(System.out::println);
    }
}
