package com.github.Leo_Proger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataExtractorTest {

    @Test
    void testExtractDataWithFullEntry() {
        String input = "- to practice (doing) something (прЭктис) - практиковать (делать) что-то. _We practiced speaking English._ Мы практиковали говорить по-английски. _He sometimes practices this skill._ Он иногда практикует этот навык";

        VocabularyEntry result = DataExtractor.extractData(input);

        assertEquals("to practice (doing) something", result.englishWord());
        assertEquals("прЭктис", result.transcription());
        assertEquals("практиковать (делать) что-то", result.translation());
        assertEquals(2, result.examples().size());
        assertEquals("We practiced speaking English", result.examples().getFirst());
        assertEquals("He sometimes practices this skill", result.examples().get(1));
        assertEquals(2, result.exampleTranslates().size());
        assertEquals("Мы практиковали говорить по-английски", result.exampleTranslates().getFirst());
        assertEquals("Он иногда практикует этот навык", result.exampleTranslates().get(1));
    }

    @Test
    void testExtractDataWithoutTranscription() {
        String input = "- (un)prepared - (не)подготовленный. _The boy was unprepared for the exams._ Мальчик был неподготовлен к экзаменам";

        VocabularyEntry result = DataExtractor.extractData(input);

        assertEquals("(un)prepared", result.englishWord());
        assertEquals("", result.transcription());
        assertEquals("(не)подготовленный", result.translation());
        assertEquals(1, result.examples().size());
        assertEquals("The boy was unprepared for the exams", result.examples().getFirst());
        assertEquals(1, result.exampleTranslates().size());
        assertEquals("Мальчик был неподготовлен к экзаменам", result.exampleTranslates().getFirst());
    }

    @Test
    void testExtractDataWithoutExamples() {
        String input = "- piano (пьяно) - пианино";

        VocabularyEntry result = DataExtractor.extractData(input);

        assertEquals("piano", result.englishWord());
        assertEquals("пьяно", result.transcription());
        assertEquals("пианино", result.translation());
        assertTrue(result.examples().isEmpty());
        assertTrue(result.exampleTranslates().isEmpty());
    }

    @Test
    void testExtractDataWithPhrase() {
        String input = "- to count on someone - рассчитывать на кого-то. _You can count on me._ Ты можешь рассчитывать на меня";

        VocabularyEntry result = DataExtractor.extractData(input);

        assertEquals("to count on someone", result.englishWord());
        assertEquals("", result.transcription());
        assertEquals("рассчитывать на кого-то", result.translation());
        assertEquals(1, result.examples().size());
        assertEquals("You can count on me", result.examples().getFirst());
        assertEquals(1, result.exampleTranslates().size());
        assertEquals("Ты можешь рассчитывать на меня", result.exampleTranslates().getFirst());
    }

    @Test
    void testExtractDataWithoutTranscriptionAndManyExamples() {
        String input = "- could - 1) прошедшая форма can 2) мог бы. _I think he could help us._ Я думаю он мог бы помочь нам. _Could you help me?_ Не могли бы вы помочь мне?";

        VocabularyEntry result = DataExtractor.extractData(input);

        assertEquals("could", result.englishWord());
        assertEquals("", result.transcription());
        assertEquals("1) прошедшая форма can 2) мог бы", result.translation());
        assertEquals(2, result.examples().size());
        assertEquals("I think he could help us", result.examples().getFirst());
        assertEquals(2, result.exampleTranslates().size());
        assertEquals("Я думаю он мог бы помочь нам", result.exampleTranslates().getFirst());
        assertEquals("Could you help me?", result.examples().get(1));
        assertEquals("Не могли бы вы помочь мне?", result.exampleTranslates().get(1));
    }
}