package com.github.Leo_Proger;

import java.nio.file.Path;

public class Main {
    //                                                                                                                      |                     EXAMPLE_PATTERN                      |
    public final static String MAIN_PATTERN = "^- ([a-zA-Z/() ]+)(\\([а-яА-Я/() ]+\\))? - ([а-яА-Яa-zA-Z/\\d()\\-*,. ]+)(\\.( _([a-zA-Z\\-()/\\d' ]+[?!.])_ ([а-яА-Я()\\-/\\d ]+[?!.]?))+)?$";
    public final static String EXAMPLE_PATTERN = " _([a-zA-Z\\-()/\\d' ]+[?!.])_ ([а-яА-Я()\\-/\\d ]+[?!.]?)";

    public static String testString = "- would - бы (частица). _How would he react to it?_ Как бы он отреагировал на это? _How would you do it?_ Как бы ты это сделал? _I wouldn't do it._ Я бы не сделал это";

    public static Path diroctoryPath = Path.of("X:\\English");

    public static void main(String[] args) {
        MarkdownProcessor markdownProcessor = new MarkdownProcessor();

        markdownProcessor.processMdFiles(diroctoryPath);

        for (VocabularyEntry entry : markdownProcessor.getVocabularyEntries()) {
            System.out.println(entry);
        }
    }
}