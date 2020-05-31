package com.codecool.textAnalyser.controller;

import com.codecool.textAnalyser.models.FileContent;
import com.codecool.textAnalyser.iterators.CharIterator;
import com.codecool.textAnalyser.iterators.WordIterator;
import com.codecool.textAnalyser.view.View;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Controls the flow of the application and has methods used for getting data from StatisticalAnalysis,
 * which are then sent to view to display to the user
 *
 */
public class Controller {
    private final String[] args;
    private StatisticalAnalysis characterAnalysis;
    private StatisticalAnalysis wordAnalysis;
    private final View view;
    private long startTime;

    /**
     * Creates Controller
     * @param args text files to analyse
     * @param view used to print the results
     */
    public Controller(String[] args, View view) {
        this.args = args;
        this.view = view;
    }

    /**
     * Starts analysis
     */
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

    /**
     * counts time of the analysis and sends it to view to print to the user
     */
    private void displayTime() {
        long longTime = System.nanoTime() - startTime;
        double applicationRunTime = (double) longTime / 1000000000.0;
        view.printTime(applicationRunTime);
    }

    /**
     * Checks whether there are files to analyse
     * @param args text files
     * @return returns true if there is at least one file to analyse
     */
    private static boolean areArgsProvided(String[] args) {
        return args.length > 0;
    }

    /**
     * Checks whether given file name is in the directory and catches exception
     * @param fileName name of the text file
     */
    private void checkForFile(String fileName) {
        File file = new File(fileName);
        if (!(file.exists() && !file.isDirectory())) {
            throw new IllegalArgumentException(fileName + " does not exist in given directory");
        }
    }

    /**
     * Creates iterators and prepares text file for analysis. Calls method to analyse the file in order
     * @param fileName name of the text file
     */
    private void analyseFile(String fileName) {
        FileContent textToAnalyse = new FileContent(fileName);
        WordIterator wordIterator = new WordIterator(textToAnalyse);
        CharIterator charIterator = new CharIterator(textToAnalyse);
        characterAnalysis = new StatisticalAnalysis(charIterator);
        wordAnalysis = new StatisticalAnalysis(wordIterator);
        viewFileName(fileName);
        analyseInOrder();
    }

    /**
     * Sends request to view to print the name of a file to the user
     * @param fileName
     */
    private void viewFileName(String fileName) {
        String formattedName = String.format("==%s==", fileName);
        view.printString(formattedName, "");
    }

    /**
     * A method that calls different text analysis methods
     */
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

    /**
     * Shows the count of all characters in the text file
     */
    private void viewAllCharactersCount() {
        view.printInt(characterAnalysis.size(), "Char count: ");
    }

    /**
     * Shows the count of all words in the text file
     */
    private void viewAllWordsCount() {
        view.printInt(wordAnalysis.size(), "Word count: ");
    }

    /**
     * Shows how many different words were used in the text file
     */
    private void viewDictionarySize() {
        view.printInt(wordAnalysis.dictionarySize(), "Dict size: ");
    }

    /**
     * Shows words used more than 1%
     */
    private void viewMostUsedWords() {
        double onePercent = 0.01;
        int numberOfWords = wordAnalysis.size();
        Double onePercentWords = onePercent * numberOfWords;
        view.printSet(wordAnalysis.occurMoreThan(onePercentWords.intValue()),
                "Most used words (>1%):");
    }

    /**
     * Shows count of "love"
     */
    private void viewLoveCount() {
        view.printInt(wordAnalysis.countOf("love"), "'love' count: ");
    }

    /**
     * Shows count of "hate"
     */
    private void viewHateCount() {
        view.printInt(wordAnalysis.countOf("hate"), "'hate' count: ");
    }

    /**
     * Shows count of "music"
     */
    private void viewMusicCount() {
        view.printInt(wordAnalysis.countOf("music"), "'music' count: ");
    }

    /**
     * Shows percentage of vowels
     */
    private void viewVowelsPercentage() {
        String[] vowelArray = "aeiou".split("");
        int vowelCount = characterAnalysis.countOf(vowelArray);
        int charactersCount = characterAnalysis.size();
        int vowelsPercentage = charactersCount / vowelCount;
        view.printInt(vowelsPercentage, "Vowels %: ");
    }

    /**
     * Shows A to E Ratio
     */
    private void viewAERatio(){
        int aCount = characterAnalysis.countOf("a");
        int eCount = characterAnalysis.countOf("e");
        view.printDouble((double) aCount/eCount, "A : E ratio: ");
    }

    /**
     * Shows use percentage for every letter
     */
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
