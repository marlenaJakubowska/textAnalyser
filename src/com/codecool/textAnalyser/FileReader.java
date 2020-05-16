package com.codecool.textAnalyser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class FileReader {

    public String getFileContent(String fileName) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
