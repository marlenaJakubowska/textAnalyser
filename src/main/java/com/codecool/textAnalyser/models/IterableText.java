package com.codecool.textAnalyser.models;
import java.util.Iterator;

public interface IterableText {

    Iterator<String> charIterator();
    Iterator<String> wordIterator();
}
