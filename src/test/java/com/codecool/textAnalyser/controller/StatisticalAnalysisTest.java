package com.codecool.textAnalyser.controller;

import com.codecool.textAnalyser.iterators.CharIterator;
import com.codecool.textAnalyser.models.FileContent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatisticalAnalysisTest {

    private StatisticalAnalysis statisticalAnalysis;

    @Test
    void checkCorrectDictionarySize() {
        String[] args = {"test.txt"};
        FileContent fileContent = new FileContent(args[0]);
        CharIterator charIterator = new CharIterator(fileContent);
        statisticalAnalysis = new StatisticalAnalysis(charIterator);
        assertEquals(8, statisticalAnalysis.dictionarySize());
    }

    @Test
    void checkIncorrectDictionarySize() {
        String[] args = {"test.txt"};
        FileContent fileContent = new FileContent(args[0]);
        CharIterator charIterator = new CharIterator(fileContent);
        statisticalAnalysis = new StatisticalAnalysis(charIterator);
        assertNotEquals(5, statisticalAnalysis.dictionarySize());
    }
}