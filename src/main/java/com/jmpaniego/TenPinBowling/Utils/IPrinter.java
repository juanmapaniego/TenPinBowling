package com.jmpaniego.TenPinBowling.Utils;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

public interface IPrinter {
    PrintStream getPrintStream();
    void setPrintStream(String filename) throws FileNotFoundException, SecurityException;

    void writeHeader();
    void writeList(List<?> list);
}
