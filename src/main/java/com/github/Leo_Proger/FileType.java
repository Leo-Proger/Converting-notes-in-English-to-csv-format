package com.github.Leo_Proger;

public enum FileType {
    TXT(".txt"), CSV(".csv");

    private final String extension;

    FileType(String s) {
        extension = s;
    }

    public String getExtension() {
        return extension;
    }
}
