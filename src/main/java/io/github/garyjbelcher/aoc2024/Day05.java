package io.github.garyjbelcher.aoc2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day05 {

    public static void main(String[] args) throws IOException {
        
        List<String> input = Files.readAllLines(Paths.get("src/main/resources/day5.txt"));

        Set<String> rules = input.stream()
                .filter(row -> row.contains("|"))
                .collect(Collectors.toSet());

        List<String[]> updates = input.stream()
                .filter(row -> !row.isEmpty())
                .filter(row -> !row.contains("|"))
                .map(row -> row.split(","))
                .collect(Collectors.toList());

        System.out.println("What do you get if you add up the middle page number from those correctly-ordered updates? " + solvePart1(rules, updates));
        System.out.println("What do you get if you add up the middle page numbers after correctly ordering just those updates? " + solvePart2(rules, updates));
    }

    public static boolean isCorrectOrder(Set<String> rules, String[] update) {

        String[] copyOfUpdate = update.clone();
        Arrays.sort(copyOfUpdate, (a, b) -> rules.contains(b + "|" + a) ? 0 : -1);

        // The update is in the correct order if the sorted copy is the same.
        return Arrays.equals(update, copyOfUpdate);
    }

    public static int solvePart1(Set<String> rules, List<String[]> updates) {

        int sum = updates.stream()
                .filter(update -> isCorrectOrder(rules, update))
                .mapToInt(update -> Integer.valueOf(update[update.length / 2])).sum();

        return sum;
    }

    public static int solvePart2(Set<String> rules, List<String[]> updates) {

        int sum = updates.stream()
                .filter(update -> !isCorrectOrder(rules, update))
                .map(update -> {
                    Arrays.sort(update, (a, b) -> rules.contains(b + "|" + a) ? 0 : -1);
                    return update;
                })
                .mapToInt(update -> Integer.valueOf(update[update.length / 2])).sum();

        return sum;
    }
}
