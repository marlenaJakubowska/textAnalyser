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

class CharIteratorTest {

    private static OutputStream outputStream;
    private CharIterator charIterator;

    @BeforeEach
    void initializeComponents() {
        outputStream = new ByteArrayOutputStream();
        String[] args = {"test.txt"};
        FileContent fileContent = new FileContent(args[0]);
        PrintStream ps = new PrintStream(outputStream);
        View view = new View(ps);
        Controller ctrl = new Controller(args, view);
        ctrl.startAnalysis();
        charIterator = new CharIterator(fileContent);
    }

    @Test
    void checkCorrectNumberOfCharacters() {
        assertEquals(40, charIterator.getCharacters().size());
    }

    @Test
    void checkIncorrectNumberOfCharacters() {
        assertNotEquals(39, charIterator.getCharacters().size());
    }
}