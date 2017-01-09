package info.qumeric.hw.task4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class SquareNumbers {
    public static void main(String[] args) {
        try (PrintWriter writer = new PrintWriter("out.txt", "UTF-8")) {
            Files.lines(Paths.get("numbers.txt"))
                    .map(SquareNumbers::parseLine)
                    .map(maybe -> maybe.map(x -> x*x))
                    .forEach(maybe -> writer.write((maybe.isPresent() ? maybe.get().toString() : "null") + "\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Maybe<Integer> parseLine(String line) {
        try {
            return Maybe.just(Integer.parseInt(line));
        } catch (NumberFormatException e) {
            return Maybe.nothing();
        }
    }
}