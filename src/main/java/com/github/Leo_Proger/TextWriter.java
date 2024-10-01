package com.github.Leo_Proger;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TextWriter {

    public static void writeToFile(List<VocabularyEntry> entries, Path outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputFile.toFile()),
                StandardCharsets.UTF_8
        ))) {
            for (VocabularyEntry entry : entries) {
                writer.write(convertEntryToLine(entry));
                writer.write('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertEntryToLine(VocabularyEntry entry) {
        List<String> line = new ArrayList<>();

        if ((!entry.transcription().isBlank() && !entry.examples().isEmpty()) || (entry.transcription().isBlank() && entry.examples().size() > 1)) {
            line.add(entry.englishWord());
            line.add(entry.transcription());
            line.add(entry.translation());

            for (int i = 0; i < entry.examples().size(); i++) {
                line.add(entry.examples().get(i));
                line.add(entry.exampleTranslates().get(i));
            }
        } else if (entry.transcription().isBlank() && !entry.examples().isEmpty()) {
            line.add(entry.englishWord());
            line.add(entry.translation());
            line.add(entry.examples().getFirst());
            line.add(entry.exampleTranslates().getFirst());
        } else if (!entry.transcription().isBlank()) {
            line.add(entry.englishWord());
            line.add(entry.transcription());
            line.add(entry.translation());
        } else {
            line.add(entry.englishWord());
            line.add(entry.translation());
        }

        return "\"" + String.join("\";\"", line) + "\"";
    }
}
