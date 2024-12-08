package io.github.garyjbelcher.aoc2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {

    public static void main(String[] args) throws IOException {

        List<String> input = Files.readAllLines(Paths.get("src/main/resources/day3.txt"));

        System.out.println("What do you get if you add up all of the results of the multiplications? " + solvePart1(input));
        System.out.println("What do you get if you add up all of the results of just the enabled multiplications?? " + solvePart2(input));
    }

    private static int solvePart1(List<String> input) {

        int sum = 0;

        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        for (String row : input) {

            Matcher matcher = pattern.matcher(row);

            while (matcher.find()) {
                sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
            }
        }

        return sum;
    }

    private static int solvePart2(List<String> input) {

        int sum = 0;

        StringBuilder sb = new StringBuilder();

        for (String row : input) {
            sb.append(row);
        }

        while (sb.indexOf("don't()") != -1) {
            int start = sb.indexOf("don't()");
            int end = sb.indexOf("do()", start) != -1 ? sb.indexOf("do()", start) + 4 : sb.length();
            sb.delete(start, end);
        }

        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(sb.toString());

        while (matcher.find()) {
            sum += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
        }

        return sum;
    }
}
