package com.codecool.textAnalyser.controller;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;

/**
 * Used to get characters and words analysis
 */

public class StatisticalAnalysis {

    private final Iterator<String> iterator;

    public StatisticalAnalysis(Iterator<String> iterator) {

        this.iterator = iterator;
    }

    /**
     * Counts number of occurrences
     * @param text accepts one or more strings or characters as strings
     * @return returns number of occurrences of a given string/s
     */
    public int countOf(String... text) {
        int numberOfOccurrences = 0;
        while (iterator.hasNext()) {
            if (Arrays.asList(text).contains(iterator.next())) {
                numberOfOccurrences++;
            }
        }
        return numberOfOccurrences;
    }

    /**
     * iterates through a set to count the size of a dictionary
     * @return size of a set dictionary
     */
    public int dictionarySize() {
        Set<String> elements = new HashSet<>();
        while (iterator.hasNext()) {
            elements.add(iterator.next());
        }
        return elements.size();
    }

    public int size() {
        int total = 0;
        while (iterator.hasNext()) {
            iterator.next();
            total++;
        }
        return total;
    }

    /**
     * Counts which string occurs more than a given percentage
     * @param percentage mimium value of percentage of occurrences of a string in a document
     * @return map of Strings and percentage value that is more or equals to given percentage
     */
    public Set<String> occurMoreThan(Integer percentage) {
        Map<String, Integer> map = new HashMap<>();
        while (iterator.hasNext()) {
            String element = iterator.next();
            map.merge(element, 1, Integer::sum);
        }
        //using stream
        return map.entrySet().stream()
                .filter(p -> p.getValue() > percentage)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue))
                .keySet();
    }
}
