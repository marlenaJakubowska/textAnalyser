package com.codecool.textAnalyser;
import java.util.Iterator;

public class FileContent implements IterableText{
    private final String fileName;

    public FileContent(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName(){
        return fileName;
    }

//    @Override
//    public Iterator<String> wordIterator(){
//        return new WordIterator(this);
//    }

    @Override
    public Iterator<String> charIterator(){
        return new CharIterator(this);
    }
}
