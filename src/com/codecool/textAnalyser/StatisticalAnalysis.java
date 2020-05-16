package com.codecool.textAnalyser;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class StatisticalAnalysis {

    private final Iterator<String> iterator;
    public StatisticalAnalysis(Iterator<String> iterator) {
        this.iterator = iterator;
    }

    public int countOf(String... words) {
        int numberOfOccurrences = 0;
        while (iterator.hasNext()) {
            if (Arrays.asList(words).contains(iterator.next())) {
                numberOfOccurrences++;
            }
        }
        return numberOfOccurrences;
    }

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
