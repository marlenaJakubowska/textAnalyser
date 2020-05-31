package com.codecool.textAnalyser.controller;

import com.codecool.textAnalyser.iterators.CharIterator;
import com.codecool.textAnalyser.models.FileContent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StatisticalAnalysisTest {

    private StatisticalAnalysis statisticalAnalysis;

    @BeforeEach
    void initialize() {
        String[] args = {"test.txt"};
        FileContent fileContent = new FileContent(args[0]);
        CharIterator charIterator = new CharIterator(fileContent);
        statisticalAnalysis = new StatisticalAnalysis(charIterator);
    }
    
    @Test
    void checkCorrectDictionarySize() {
        assertEquals(8, statisticalAnalysis.dictionarySize());
    }

    @Test
    void checkIncorrectDictionarySize() {
        assertNotEquals(5, statisticalAnalysis.dictionarySize());
    }

    @Test
    void checkCorrectCountOfALetter() {
        assertEquals(10, statisticalAnalysis.countOf("t"));
        assertEquals(5, statisticalAnalysis.countOf("e"));
        assertEquals(9, statisticalAnalysis.countOf("s"));
    }

    @Test
    void checkIncorrectCountOfALetter() {
        assertNotEquals(9, statisticalAnalysis.countOf("t"));
        assertNotEquals(4, statisticalAnalysis.countOf("e"));
        assertNotEquals(2, statisticalAnalysis.countOf("s"));
    }

    @Test
    void checkCorrectSize() {
        assertEquals(39, statisticalAnalysis.size());
    }

    @Test
    void checkIncorrectSize() {
        assertNotEquals(38, statisticalAnalysis.size());
    }

    @Test
    void checkCorrectOccurMoreThan() {
        Set<String> set = new HashSet<>(Arrays.asList("t"));
        assertEquals(set, statisticalAnalysis.occurMoreThan(9));
    }

    @Test
    void checkinCorrectOccurMoreThan() {
        Set<String> set = new HashSet<>(Arrays.asList("t"));
        assertNotEquals(set, statisticalAnalysis.occurMoreThan(8));
    }
}