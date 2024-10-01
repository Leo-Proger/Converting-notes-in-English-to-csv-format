package com.github.Leo_Proger;

import java.util.List;

public record VocabularyEntry(
        String englishWord,
        String transcription,
        String translation,
        List<String> examples,
        List<String> exampleTranslates) {

    @Override
    public String toString() {
        return "VocabularyEntry{" +
                "englishWord='" + englishWord + '\'' +
                ", transcription='" + transcription + '\'' +
                ", translation='" + translation + '\'' +
                ", examples=" + examples +
                ", exampleTranslates=" + exampleTranslates +
                '}';
    }
}