package com.codecool.textAnalyser;

import java.io.PrintStream;

public class Application {

    public static void main(String[] arg) {
        String[] args = {"test_dickens_great.txt", "test_malville_moby.txt"};
        PrintStream printStream = new PrintStream(System.out);
        View view = new View(printStream);
        Engine engine = new Engine(args, view);
        engine.startAnalysis();
    }
}