package com.codecool.textAnalyser;
import java.util.Iterator;

public interface IterableText {

    Iterator<String> charIterator();
    Iterator<String> wordIterator();
}
