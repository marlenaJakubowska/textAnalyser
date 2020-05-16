package com.codecool.textAnalyser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CharIterator implements Iterator<String> {
    private final List<String> characters;
    private final FileContent fileContent;
    private int index;

    public CharIterator(FileContent fileContent) {
        this.fileContent = fileContent;
        this.characters = getCharactersFromFile();
    }

    private List<String> getCharactersFromFile() {
        String content = getFileContent(this.fileContent.getFileName());
        List<String> result = new ArrayList<>();
        for (char character : content.toCharArray()) {
            if (Character.isLetter(character)) {
                result.add(Character.toString(character).toLowerCase());
            }
        }
        return result;
    }

    public String getFileContent(String fileName) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    @Override
    public boolean hasNext() {
        if (index == characters.size() - 1) {
            index = 0;
            return false;
        }
        return true;
    }

    @Override
    public String next() {
        if (this.hasNext()) {
            return characters.get(index++);
        }
        return null;
    }
}
