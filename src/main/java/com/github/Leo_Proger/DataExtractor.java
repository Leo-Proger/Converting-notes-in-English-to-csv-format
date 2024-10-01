package com.github.Leo_Proger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.Leo_Proger.Main.EXAMPLE_PATTERN;
import static com.github.Leo_Proger.Main.MAIN_PATTERN;

public class DataExtractor {

    public static VocabularyEntry extractData(String string) {
        String englishWord = "";
        String transcription = "";
        String translate = "";
        List<String> examples = new ArrayList<>();
        List<String> exampleTranslates = new ArrayList<>();

        Pattern pattern = Pattern.compile(MAIN_PATTERN);
        Matcher matcher = pattern.matcher(string);

        if (matcher.find()) {
            // Group 1 - english word / phrase
            englishWord = matcher.group(1).strip();

            // Group 2 - transcription (if exist)
            String transcriptionMatch = matcher.group(2);
            transcription = transcriptionMatch != null ? stripChar(stripChar(transcriptionMatch.strip(), '('), ')') : "";

            // Group 3 - translation
            translate = matcher.group(3).strip();

            // Group 6 and 7 - examples and their translations
            String examplesGroup = matcher.group(4);
            if (examplesGroup != null) {
                Pattern examplePattern = Pattern.compile(EXAMPLE_PATTERN);
                Matcher exampleMatcher = examplePattern.matcher(examplesGroup);

                while (exampleMatcher.find()) {
                    examples.add(
                            stripChar(exampleMatcher.group(1), '.')
                    );
                    exampleTranslates.add(
                            stripChar(exampleMatcher.group(2), '.')
                    );
                }
            }
        }

        return new VocabularyEntry(
                englishWord,
                transcription,
                translate,
                examples,
                exampleTranslates
        );
    }

    /**
     * Delete a character from two sides of string
     *
     * @param string string to remove the character from
     * @param ch character that is being deleted from string
     * @return string from which the characters on both sides have been removed
     */
    private static String stripChar(String string, char ch) {
        if (string == null || string.isEmpty()) {
            return string;
        }

        int start = 0;
        int end = string.length() - 1;

        // Delete char in the beginning
        while (start < string.length() && string.charAt(start) == ch) {
            start++;
        }

        // Delete char in the end
        while (end > start && string.charAt(end) == ch) {
            end--;
        }

        return string.substring(start, end + 1);
    }
}
