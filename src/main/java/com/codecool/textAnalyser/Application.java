package com.codecool.textAnalyser;

import com.codecool.textAnalyser.controller.Controller;
import com.codecool.textAnalyser.view.View;

import java.io.PrintStream;

public class Application {

    public static void main(String[] args) {
        PrintStream printStream = new PrintStream(System.out);
        View view = new View(printStream);
        Controller controller = new Controller(args, view);
        controller.startAnalysis();
    }
}