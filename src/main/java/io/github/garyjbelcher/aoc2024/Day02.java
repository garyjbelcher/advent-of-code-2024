package io.github.garyjbelcher.aoc2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day02 {

    public static void main(String[] args) throws IOException {

        List<String> input = Files.readAllLines(Paths.get("src/main/resources/day2.txt"));

        System.out.println("How many reports are safe? " + solvePart1(input));
        System.out.println("How many reports are now safe? " + solvePart2(input));
    }

    private static boolean isDecreasingAndSafe(List<Integer> levels) {

        for (int i = 0; i < levels.size(); i++) {
            if (i != 0) {
                int current = levels.get(i);
                int previous = levels.get(i - 1);
                if (current >= previous || Math.abs(current - previous) > 3) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isIncreasingAndSafe(List<Integer> levels) {

        for (int i = 0; i < levels.size(); i++) {
            if (i != 0) {
                int current = levels.get(i);
                int previous = levels.get(i - 1);
                if (current <= previous || Math.abs(current - previous) > 3) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int solvePart1(List<String> input) {

        long safe = input.stream().filter(row -> {

            List<Integer> levels = Arrays.asList(row.split("\\s+")).stream().map(Integer::parseInt).toList();

            return isDecreasingAndSafe(levels) || isIncreasingAndSafe(levels);

        }).count();

        return (int) safe;
    }

    private static int solvePart2(List<String> input) {

        long safe = input.stream().filter(row -> {

            List<Integer> levels = Arrays.asList(row.split("\\s+")).stream().map(Integer::parseInt).toList();

            if (isDecreasingAndSafe(levels)) {
                return true;
            } else {
                for (int i = 0; i < levels.size(); i++) {
                    ArrayList<Integer> retry = new ArrayList<>(levels);
                    retry.remove(i);
                    if (isDecreasingAndSafe(retry)) {
                        return true;
                    }
                }
            }

            if (isIncreasingAndSafe(levels)) {
                return true;
            } else {
                for (int i = 0; i < levels.size(); i++) {
                    ArrayList<Integer> retry = new ArrayList<>(levels);
                    retry.remove(i);
                    if (isIncreasingAndSafe(retry)) {
                        return true;
                    }
                }
            }

            return false;

        }).count();

        return (int) safe;
    }
}
