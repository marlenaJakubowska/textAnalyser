package com.codecool.textAnalyser.view;

import java.util.Map;
import java.util.Set;
import java.io.PrintStream;

public class View {
    private final PrintStream printStream;

    public View(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printDoubleMap(Map<String, Double> map, String title) {
        printStream.print(title);
        for (Map.Entry<String, Double> element : map.entrySet()) {
            printStream.println(String.format("%S : %.2f, ", element.getKey(), element.getValue()));
        }
        printStream.println();
    }

    public void printDouble(Double number, String title) {
        printStream.print(title);
        printStream.println(String.format("%.2f", number));
    }

    public void printInt(Integer number, String title) {
        printStream.print(title);
        printStream.println(number);
    }

    public void printString(String string, String title) {
        printStream.print(title);
        printStream.println(string);
    }

    public void printSet(Set<String> set, String title) {
        printStream.print(title);
        for (String element : set) {
            printStream.print(element + ", ");
        }
        printStream.println();
    }

    public void printNoArgsMsg() {
        printStream.println("no arguments provided");
    }

    public void printTime(double applicationRunTime) {
        printStream.print("Benchmark time: " + applicationRunTime);
    }
}