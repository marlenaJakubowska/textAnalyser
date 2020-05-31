package com.codecool.textAnalyser.controller;

import com.codecool.textAnalyser.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller controller;
    private  OutputStream outputStream;
    private  View view;

    @BeforeEach
    void initializeOutput() {
        outputStream = new ByteArrayOutputStream();
        view = new View(new PrintStream(outputStream));
    }

    @Test
    void fileNotFoundExceptionTest() {
        String[] args = {"nonExistingFile"};
        controller = new Controller(args, view);
        assertThrows(IllegalArgumentException.class, () -> controller.startAnalysis());
    }

    @Test
    void noFileProvidedTest() {
        String[] args = {};
        controller = new Controller(args, view);
        controller.startAnalysis();
        System.out.println(outputStream.toString());
        assertEquals("no arguments provided\n", outputStream.toString());
    }
}