package com.codecool.textAnalyser.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileContentTest {

    @Test
    void getFileNameTest() {
        String fileName = "test.txt";
        FileContent fileContent = new FileContent(fileName);
        assertEquals("test.txt", fileContent.getFileName());
    }

    @Test
    void checkIfWordIteratorCreates(){
        String fileName = "test.txt";
        FileContent fileContent = new FileContent(fileName);
        assertEquals("WordIterator", fileContent.wordIterator().getClass().getSimpleName());
    }

    @Test
    void checkIfCharacterIteratorCreates(){
        String fileName = "test.txt";
        FileContent fileContent = new FileContent(fileName);
        assertEquals("CharIterator", fileContent.charIterator().getClass().getSimpleName());
    }

}