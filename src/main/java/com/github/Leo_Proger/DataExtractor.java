package com.github.Leo_Proger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.Leo_Proger.Main.EXAMPLE_PATTERN;
import static com.github.Leo_Proger.Main.MAIN_PATTERN;

public class DataExtractor {

    /**
     * Extracts vocabulary data from a string and creates a VocabularyEntry object.
     *
     * @param string A formatted string with Markdown markup containing vocabulary data in the following format:
     *               english word or phrase (transcription in Russian) - translation in Russian. _Example 1._ Example translation in Russian 1. _Example 2!_ Example translation in Russian 2! etc.
     * @return VocabularyEntry object containing parsed data:
     * - English word/phrase
     * - Transcription in Russian (if provided)
     * - Translation in Russian
     * - List of examples (if provided)
     * - List of example translations in Russian (if provided)
     * @throws InvalidDataException if the input string doesn't match the expected format
     * @see VocabularyEntry
     */
    public static VocabularyEntry extractData(String string) {
        String englishWord;
        String transcription;
        String translate;
        List<String> examples = new ArrayList<>();
        List<String> exampleTranslates = new ArrayList<>();

        Pattern pattern = Pattern.compile(MAIN_PATTERN);
        Matcher matcher = pattern.matcher(string);

        if (matcher.find()) {
            // Group 1 - english word / phrase
            englishWord = matcher.group(1).strip().replace("*", "");

            // Group 2 - transcription (if exist)
            String transcriptionMatch = matcher.group(2);
            transcription = transcriptionMatch == null ? "" :
                    stripChar(stripChar(transcriptionMatch.strip().replace("*", ""), '('), ')');

            // Group 3 - translation
            translate = matcher.group(3).strip().replace("*", "");

            // Group 6 and 7 - examples and their translations
            String examplesGroup = matcher.group(4);
            if (examplesGroup != null) {
                Pattern examplePattern = Pattern.compile(EXAMPLE_PATTERN);
                Matcher exampleMatcher = examplePattern.matcher(examplesGroup);

                while (exampleMatcher.find()) {
                    examples.add(
                            stripChar(exampleMatcher.group(1).strip(), '.')
                    );
                    exampleTranslates.add(
                            stripChar(exampleMatcher.group(2).strip(), '.')
                    );
                }
            }
        } else {
            throw new InvalidDataException("Invalid data format");
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
     * Removes a specified character from both the beginning and end of a string.
     *
     * @param string string to remove the character from; if null or empty, returns unchanged
     * @param ch     character to be removed from both sides of the string
     * @return string with the specified character removed from both sides
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
