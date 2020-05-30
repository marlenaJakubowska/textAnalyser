package com.codecool.textAnalyser.iterators;

import com.codecool.textAnalyser.models.FileContent;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class WordIterator extends FileReader implements Iterator<String> {

    private final FileContent fileContent;
    private final List<String> words;
    private int index;

    public WordIterator(FileContent fileContent) {
        this.fileContent = fileContent;
        this.words = getWordsFromFile();
    }

    private List<String> getWordsFromFile() {
        String content = getFileContent(this.fileContent.getFileName());
        String[] words = content.trim().split("\\s+"); // every whitespace
        //using stream instead of for loops
        return Arrays.stream(words).map(String::toLowerCase).collect(Collectors.toList());
    }

    public List<String> getWords() {
        return words;
    }

    @Override
    public boolean hasNext() {
        if (index == words.size() -1) {
            index = 0;
            return false;
        }
        return true;
    }

    @Override
    public String next() {
        if (this.hasNext()){
            return words.get(index++);
        }
        return null;
    }

}
