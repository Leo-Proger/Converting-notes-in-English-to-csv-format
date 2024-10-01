package com.github.Leo_Proger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MarkdownProcessor {

    private final List<VocabularyEntry> vocabularyEntries = new ArrayList<>();

    public void processFiles(Path directoryPath) {
        try (Stream<Path> paths = Files.walk(directoryPath)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".md"))
                    .forEach(this::processFile);
        } catch (IOException e) {
            System.err.println("Directory reading failed: " + e.getMessage());
        }
    }

    private void processFile(Path filePath) {
        try {
            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                if (line.replace("-", "").strip().isBlank()) continue;
                if (line.strip().startsWith("# Important rules")) break;
                if (line.strip().startsWith("#")) continue;

                vocabularyEntries.add(DataExtractor.extractData(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<VocabularyEntry> getVocabularyEntries() {
        return vocabularyEntries;
    }
}
