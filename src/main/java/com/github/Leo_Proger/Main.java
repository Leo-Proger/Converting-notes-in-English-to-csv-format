package com.github.Leo_Proger;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    //                                                                                                                      |                     EXAMPLE_PATTERN                      |
    public final static String MAIN_PATTERN = "^- ([a-zA-Z/() ]+)(\\([а-яА-Я/() ]+\\))? - ([а-яА-Яa-zA-Z/\\d()\\-*,. ]+)(\\.( _([a-zA-Z\\-()/\\d' ]+[?!.])_ ([а-яА-Я()\\-/\\d ]+[?!.]?))+)?$";
    public final static String EXAMPLE_PATTERN = " _([a-zA-Z\\-()/\\d' ]+[?!.])_ ([а-яА-Я()\\-/\\d ]+[?!.]?)";

    public static Path inputPath = Path.of("X:\\English");
    // You can change file extension to .csv
    public static Path outputFilepath = Path.of("X:\\English\\" + LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) + ".txt");

    public static void main(String[] args) {
        MarkdownProcessor markdownProcessor = new MarkdownProcessor();
        markdownProcessor.processFiles(inputPath);

        TextWriter.writeToFile(markdownProcessor.getVocabularyEntries(), outputFilepath);

        if (!markdownProcessor.getErrorLines().isEmpty()) {
            markdownProcessor.getErrorLines().forEach(System.err::println);
            new Scanner(System.in).nextLine();
        }
    }
}