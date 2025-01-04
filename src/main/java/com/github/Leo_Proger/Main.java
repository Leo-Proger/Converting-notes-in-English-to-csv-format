package com.github.Leo_Proger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public final static String MAIN_PATTERN = "^- ([a-zA-Z/()\\- ]+)(\\([а-яА-ЯёЁ/(), ]+\\))? - ([а-яА-ЯёЁa-zA-Z/\\d()\\-*,. ]+)(\\.( _([a-zA-Z\\-()/\\d'\\.,!? ]+[?!.])_ ([а-яА-ЯёЁ()\\-/\\d\\.,!? ]+[?!.]?))+)?$";
    public final static String EXAMPLE_PATTERN = "_([a-zA-Z\\-()/\\d'\\.,!? ]+[?!.])_ ([а-яА-ЯёЁ()\\-/\\d\\.,!? ]+[?!.]?)";

    // You can change file extension to .csv or .txt
    public static FileType fileType = FileType.CSV;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <input-directory>");
            new Scanner(System.in).nextLine();
            System.exit(1);
        }
        Path inputPath = Path.of(args[0]);

        if (!Files.exists(inputPath)) {
            System.err.println("Input directory does not exist: " + inputPath);
            new Scanner(System.in).nextLine();
            System.exit(1);
        }
        Path outputFile = Path.of(inputPath.toString(), LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) + fileType.getExtension());

        MarkdownProcessor markdownProcessor = new MarkdownProcessor();
        markdownProcessor.processFiles(inputPath);

        TextWriter.writeToFile(markdownProcessor.getVocabularyEntries(), outputFile);

        if (!markdownProcessor.getErrorLines().isEmpty()) {
            System.err.println("Failed to process the lines:\n");
            markdownProcessor.getErrorLines().forEach(System.err::println);
        }
        System.out.println("\nThe program was completed.");
        new Scanner(System.in).nextLine();
    }
}