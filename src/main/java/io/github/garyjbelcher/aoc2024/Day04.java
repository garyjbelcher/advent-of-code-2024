package io.github.garyjbelcher.aoc2024;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day04 {

    static final String MAS = "MAS";
    static final String XMAS = "XMAS";

    public static void main(String[] args) throws IOException {

        List<String> input = Files.readAllLines(Paths.get("src/main/resources/day4.txt"));

        System.out.println("How many times does XMAS appear? " + solvePart1(input));
        System.out.println("How many times does an X-MAS appear? " + solvePart2(input));
    }

    public static int searchHorizontal(List<String> input) {

        int count = 0;

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length() - 3; j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(input.get(i).charAt(j));
                sb.append(input.get(i).charAt(j + 1));
                sb.append(input.get(i).charAt(j + 2));
                sb.append(input.get(i).charAt(j + 3));
                if (sb.toString().equals(XMAS)
                        || sb.reverse().toString().equals(XMAS)) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int searchVertical(List<String> input) {

        int count = 0;

        for (int i = 0; i < input.size() - 3; i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(input.get(i).charAt(j));
                sb.append(input.get(i + 1).charAt(j));
                sb.append(input.get(i + 2).charAt(j));
                sb.append(input.get(i + 3).charAt(j));
                if (sb.toString().equals(XMAS)
                        || sb.reverse().toString().equals(XMAS)) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int searchDiagonal(List<String> input) {

        int count = 0;

        for (int i = 0; i < input.size() - 3; i++) {
            for (int j = 0; j < input.get(i).length() - 3; j++) {
                StringBuilder sbTopLeftBottomRight = new StringBuilder();
                sbTopLeftBottomRight.append(input.get(i).charAt(j));
                sbTopLeftBottomRight.append(input.get(i + 1).charAt(j + 1));
                sbTopLeftBottomRight.append(input.get(i + 2).charAt(j + 2));
                sbTopLeftBottomRight.append(input.get(i + 3).charAt(j + 3));
                if (sbTopLeftBottomRight.toString().equals(XMAS)
                        || sbTopLeftBottomRight.reverse().toString().equals(XMAS)) {
                    count++;
                }
                StringBuilder sbTopRightBottomLeft = new StringBuilder();
                sbTopRightBottomLeft.append(input.get(i).charAt(j + 3));
                sbTopRightBottomLeft.append(input.get(i + 1).charAt(j + 2));
                sbTopRightBottomLeft.append(input.get(i + 2).charAt(j + 1));
                sbTopRightBottomLeft.append(input.get(i + 3).charAt(j));
                if (sbTopRightBottomLeft.toString().equals(XMAS)
                        || sbTopRightBottomLeft.reverse().toString().equals(XMAS)) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int searchDiagonal2(List<String> input) {

        int count = 0;

        for (int i = 0; i < input.size() - 2; i++) {
            for (int j = 0; j < input.get(i).length() - 2; j++) {
                StringBuilder sbTopLeftBottomRight = new StringBuilder();
                sbTopLeftBottomRight.append(input.get(i).charAt(j));
                sbTopLeftBottomRight.append(input.get(i + 1).charAt(j + 1));
                sbTopLeftBottomRight.append(input.get(i + 2).charAt(j + 2));
                StringBuilder sbTopRightBottomLeft = new StringBuilder();
                sbTopRightBottomLeft.append(input.get(i).charAt(j + 2));
                sbTopRightBottomLeft.append(input.get(i + 1).charAt(j + 1));
                sbTopRightBottomLeft.append(input.get(i + 2).charAt(j));
                if ((sbTopRightBottomLeft.toString().equals(MAS)
                        || sbTopRightBottomLeft.reverse().toString().equals(MAS))
                        && (sbTopLeftBottomRight.toString().equals(MAS)
                        || sbTopLeftBottomRight.reverse().toString().equals(MAS))) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int solvePart1(List<String> input) {
        return searchDiagonal(input) + searchHorizontal(input) + searchVertical(input);
    }

    public static int solvePart2(List<String> input) {
        return searchDiagonal2(input);
    }
}
