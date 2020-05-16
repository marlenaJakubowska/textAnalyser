package com.codecool.textAnalyser;

import java.io.File;

public class Engine {
    private final String[] args;
    private StatisticalAnalysis characterAnalysis;
    private StatisticalAnalysis wordAnalysis;
    //private final View view;


    public Engine(String[] args) { //add view
        this.args = args;
    }

    public void startAnalysis() {
        if (!areArgsProvided(args)) {
            System.out.println("no arguments provided");
            return;
        }
        for (String fileName : args) {
            checkForFile(fileName);
            analyseFile(fileName);
        }
    }

    private static boolean areArgsProvided(String[] args) {
        return args.length > 0;
    }

    private void checkForFile(String fileName) {
        File file = new File(fileName);
        if (!(file.exists() && !file.isDirectory())) {
            throw new IllegalArgumentException(fileName + " does not exist in given directory");
        }
    }

    private void analyseFile(String fileName) {
        FileContent textToAnalyse = new FileContent(fileName);
        WordIterator wordIterator = new WordIterator(textToAnalyse);
        CharIterator charIterator = new CharIterator(textToAnalyse);

        characterAnalysis = new StatisticalAnalysis(charIterator);
        wordAnalysis = new StatisticalAnalysis(wordIterator);
    }



}
