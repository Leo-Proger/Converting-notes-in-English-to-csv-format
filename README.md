# English notes converter

## Description

If you write notes of your English lessons and want to remember words better, but you are too lazy to create flash
cards, then this program will help you with it.

This program extracts words and related information (transcription, translation, examples and their
translations) from a markdown file and converts them to CSV format (semicolon-separated).

The output is saved as a `.txt` file (can be changed to `.csv` in `Main.java`), which you can import into your English
learning application.

## Requirements

- Java 21

## How to use

1. Download sources and unzip
2. Navigate to dir:
    ```bash
    cd English-notes-converter-master
    ```
3. Compile `.java` files:
    ```bash
    javac -d target src\main\java\com\github\Leo_Proger\*.java
    ``` 
4. Run the program:
    ```bash
    java -cp target com.github.Leo_Proger.Main <your_dir>
    ```
   Example: `java -cp target com.github.Leo_Proger.Main C:\English_Lessons`

## Input format

Lines in the `.md` file should have the following format (excluding headings):

`- word (transcription) - translate. _Example 1._ Example translate 1. _Example 2?_ Example translate 2? _Example 3!_ Example translate 3!`

Notes:

- `(transcription)` and examples are optional
- Each example in English must:
    - Be highlighted with underscores (markdown italics)
    - Have a translation
- Punctuation in examples and translations should match

## Example

Input `.md` file:

```markdown
# Lesson 1

- lots of something - много чего-то
- to be in trouble (ин трАбл) - быть в беде. _Were you in trouble?_ Ты был в беде?
```

Output `.txt` file in CSV format (semicolon-separated):

```markdown
"lots of something";"много чего-то"
"to be in trouble";"ин трАбл";"быть в беде";"Were you in trouble?";"Ты был в беде?"
```

## Output formats

The program uses one of these formats:

1. "word";"translation"
2. "word";"transcription";"translation"
3. "word";"translation";"example";"example_translation"
4. "word";"transcription";"translation";"example";"example_translation";"example2";"example2_translation"...

These formats are compatible with the "ReWord: Learn English Language" app.

## Known limitations

- Only processes specifically formatted markdown files
- Output format is tailored for ReWord app
- No GUI, command-line only

## Note

This is a personal tool that I've decided to share. It contains some specific functionality I need. Feel free to fork
and modify for your needs.



