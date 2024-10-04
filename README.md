# English notes converter

## Description

If you write notes of your English lessons and want to remember words better, but you are too lazy to create flash
cards? Then this program will help you with it.

This program extracts words and other information related to it (transcription, translation, examples and their
translations) from a markdown file and converts them to CSV format. At the end of the execution, a `.txt` file is
created (can be changed to `.csv` in `Main.java`), where all the found words are converted to CSV format.

## How to use

1. Clone the repository `https://github.com/Leo-Proger/English-notes-converter.git`

You need to specify your path to the folder in `Main.java` file where the markdown files will be located. You also need
to specify the path to the output file, this can also be done in `Main.java`.

## Requirements

A line in the `.md` file should have the following format (headers are skipped):

`- word (transcription) - translate. _Example 1._ Example translate 1. _Example 2?_ Example translate 2? _Example 3!_ Example translate 3!`

Each example in English is highlighted with underscores on both sides, which corresponds to italics in markdown.
`(transcription)` and examples are optional, but each example must have a translation.

The program converts the string into one of the following formats:

- "word1";"translation1"

- "word2";"transcription2";"translation2"

- "word3";"translation3";"example1";"ex.translation1"

- "word4";"transcription4";"translation4";"example1";"ex.translation1";"example2";"ex.translation2" ...

These formats are taken from the app "ReWord: Learn English Language"

Examples of valid strings and their CSV representation:

`- lots of something - много чего-то (можно еще что-нибудь написать)` ->
`"lots of something";"много чего-то (можно еще что-нибудь написать)"`

`- to be in trouble (ин трАбл) - быть в беде. _Were you in trouble?_ Ты был в беде?` ->
`"to be in trouble";"ин трАбл";"быть в беде";"Were you in trouble?";"Ты был в беде?"`

