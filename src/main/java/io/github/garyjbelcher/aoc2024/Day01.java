package io.github.garyjbelcher.aoc2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day01 {

    public static void main(String[] args) throws IOException {

        List<String> input = Files.readAllLines(Paths.get("src/main/resources/day1.txt"));

        System.out.println("What is the total distance between your lists? " + solvePart1(input));
        System.out.println("What is their similarity score? " + solvePart2(input));
    }

    private static int solvePart1(List<String> input) {

        List<Integer> colA = new ArrayList<>();
        List<Integer> colB = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            String[] split = input.get(i).split("\\s+");
            colA.add(Integer.valueOf(split[0]));
            colB.add(Integer.valueOf(split[1]));
        }

        colA.sort(Integer::compareTo);
        colB.sort(Integer::compareTo);

        int distance = 0;

        for (int i = 0; i < colA.size(); i++) {
            distance += Math.abs(colA.get(i) - colB.get(i));
        }

        return distance;
    }

    private static int solvePart2(List<String> input) {

        List<Integer> colA = new ArrayList<>();
        List<Integer> colB = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            String[] split = input.get(i).split("\\s+");
            colA.add(Integer.valueOf(split[0]));
            colB.add(Integer.valueOf(split[1]));
        }

        int similarity = 0;

        for (int i = 0; i < colA.size(); i++) {
            final int index = i;
            similarity += colA.get(i) * colB.stream().filter(x -> x.equals(colA.get(index))).count();
        }

        return similarity;
    }
}
