package com.codecool.textAnalyser.controller;

import com.codecool.textAnalyser.models.FileContent;
import com.codecool.textAnalyser.iterators.CharIterator;
import com.codecool.textAnalyser.iterators.WordIterator;
import com.codecool.textAnalyser.view.View;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private final String[] args;
    private StatisticalAnalysis characterAnalysis;
    private StatisticalAnalysis wordAnalysis;
    private final View view;
    private long startTime;


    public Controller(String[] args, View view) {
        this.args = args;
        this.view = view;
    }

    public void startAnalysis() {
        if (!areArgsProvided(args)) {
            view.printNoArgsMsg();
            return;
        }
        this.startTime = System.nanoTime();
        for (String fileName : args) {
            checkForFile(fileName);
            analyseFile(fileName);
        }
        displayTime();
    }

    private void displayTime() {
        long longTime = System.nanoTime() - startTime;
        double applicationRunTime = (double) longTime / 1000000000.0;
        view.printTime(applicationRunTime);
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
        viewFileName(fileName);
        analyseInOrder();
    }

    private void viewFileName(String fileName) {
        String formattedName = String.format("==%s==", fileName);
        view.printString(formattedName, "");
    }
    private void analyseInOrder() {
        viewAllCharactersCount();
        viewAllWordsCount();
        viewDictionarySize();
        viewMostUsedWords();
        viewLoveCount();
        viewHateCount();
        viewMusicCount();
        viewVowelsPercentage();
        viewAERatio();
        viewPercentageForEachLetter();
    }

    private void viewAllCharactersCount() {
        view.printInt(characterAnalysis.size(), "Char count: ");
    }

    private void viewAllWordsCount() {
        view.printInt(wordAnalysis.size(), "Word count: ");
    }

    private void viewDictionarySize() {
        view.printInt(wordAnalysis.dictionarySize(), "Dict size: ");
    }

    private void viewMostUsedWords() {
        double onePercent = 0.01;
        int numberOfWords = wordAnalysis.size();
        Double onePercentWords = onePercent * numberOfWords;
        view.printSet(wordAnalysis.occurMoreThan(onePercentWords.intValue()),
                "Most used words (>1%):");
    }
    private void viewLoveCount() {
        view.printInt(wordAnalysis.countOf("love"), "'love' count: ");
    }

    private void viewHateCount() {
        view.printInt(wordAnalysis.countOf("hate"), "'hate' count: ");
    }

    private void viewMusicCount() {
        view.printInt(wordAnalysis.countOf("music"), "'music' count: ");
    }

    private void viewVowelsPercentage() {
        String[] vowelArray = "aeiou".split("");
        int vowelCount = characterAnalysis.countOf(vowelArray);
        int charactersCount = characterAnalysis.size();
        int vowelsPercentage = charactersCount / vowelCount;
        view.printInt(vowelsPercentage, "Vowels %: ");
    }

    private void viewAERatio(){
        int aCount = characterAnalysis.countOf("a");
        int eCount = characterAnalysis.countOf("e");
        view.printDouble((double) aCount/eCount, "A : E ratio: ");
    }

    private void viewPercentageForEachLetter() {
        String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("");
        int numberOfLetters = characterAnalysis.size();
        Map<String, Double> lettersPercentages = new HashMap<>();
        for (String letter : alphabet) {
            int currentLetterCount = characterAnalysis.countOf(letter);
            Double percentageOfLetter = (double) (currentLetterCount * 100) / numberOfLetters;
            lettersPercentages.put(letter, percentageOfLetter);
        }
        view.printDoubleMap(lettersPercentages, "Use of each letter in %: \n");
    }

}
