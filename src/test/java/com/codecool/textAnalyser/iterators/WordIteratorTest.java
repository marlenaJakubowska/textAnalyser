package com.codecool.textAnalyser.iterators;

import com.codecool.textAnalyser.controller.Controller;
import com.codecool.textAnalyser.models.FileContent;
import com.codecool.textAnalyser.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class WordIteratorTest {

    private static OutputStream outputStream;
    private WordIterator wordIterator;

    @BeforeEach
    void initializeComponents() {
        outputStream = new ByteArrayOutputStream();
        String[] args = {"test.txt"};
        FileContent fileContent = new FileContent(args[0]);
        PrintStream ps = new PrintStream(outputStream);
        View view = new View(ps);
        Controller ctrl = new Controller(args, view);
        ctrl.startAnalysis();
        wordIterator = new WordIterator(fileContent);
    }

    @Test
    void checkCorrectNumberOfWords() {
        assertEquals(13, wordIterator.getWords().size());
    }

    @Test
    void checkIncorrectNumberOfWords() {
        assertNotEquals(14, wordIterator.getWords().size());
    }
}