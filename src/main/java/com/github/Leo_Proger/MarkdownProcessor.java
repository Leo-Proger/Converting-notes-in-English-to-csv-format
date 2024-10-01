package com.github.Leo_Proger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MarkdownProcessor {

    private final List<VocabularyEntry> vocabularyEntries = new ArrayList<>();
    private final List<String> errorLines = new ArrayList<>();

    public void processFiles(Path directoryPath) {
        try (Stream<Path> paths = Files.walk(directoryPath)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".md"))
                    .forEach(this::processFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processFile(Path filePath) {
        try {
            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                if (line.replace("-", "").strip().isBlank()) continue;
                if (line.strip().startsWith("# Important rules")) break;
                if (line.strip().startsWith("#")) continue;

                try {
                    vocabularyEntries.add(DataExtractor.extractData(line));
                } catch (Exception e) {
                    errorLines.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<VocabularyEntry> getVocabularyEntries() {
        return vocabularyEntries;
    }

    public List<String> getErrorLines() {
        return errorLines;
    }
}
