package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Nysiis_transcodeRemaining_Test {

    private Nysiis nysiis;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize Nysiis object before each test
        nysiis = new Nysiis();
    }

    private char[] invokeTranscodeRemaining(char prev, char curr, char next, char aNext) {
        // Use reflection to invoke the private method transcodeRemaining
        try {
            java.lang.reflect.Method method = Nysiis.class.getDeclaredMethod("transcodeRemaining", char.class, char.class, char.class, char.class);
            method.setAccessible(true);
            return (char[]) method.invoke(nysiis, prev, curr, next, aNext);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testTranscodeRemaining_EV() {
        // given
        char prev = 'A';
        char curr = 'E';
        char next = 'V';
        char aNext = 'X';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'A', 'F' }, result);
    }

    @Test
    void testTranscodeRemaining_Vowel() {
        // given
        char prev = 'X';
        char curr = 'E';
        char next = 'X';
        char aNext = 'X';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'A' }, result);
    }

    @Test
    void testTranscodeRemaining_Q() {
        // given
        char prev = 'B';
        char curr = 'Q';
        char next = 'U';
        char aNext = 'E';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'G' }, result);
    }

    @Test
    void testTranscodeRemaining_Z() {
        // given
        char prev = 'B';
        char curr = 'Z';
        char next = 'O';
        char aNext = 'E';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'S' }, result);
    }

    @Test
    void testTranscodeRemaining_M() {
        // given
        char prev = 'B';
        char curr = 'M';
        char next = 'O';
        char aNext = 'E';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'N' }, result);
    }

    @Test
    void testTranscodeRemaining_KN() {
        // given
        char prev = 'B';
        char curr = 'K';
        char next = 'N';
        char aNext = 'O';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'N', 'N' }, result);
    }

    @Test
    void testTranscodeRemaining_K() {
        // given
        char prev = 'B';
        char curr = 'K';
        char next = 'A';
        char aNext = 'T';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'C' }, result);
    }

    @Test
    void testTranscodeRemaining_SCH() {
        // given
        char prev = 'B';
        char curr = 'S';
        char next = 'C';
        char aNext = 'H';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'S', 'S', 'S' }, result);
    }

    @Test
    void testTranscodeRemaining_PH() {
        // given
        char prev = 'B';
        char curr = 'P';
        char next = 'H';
        char aNext = 'O';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'F', 'F' }, result);
    }

    @Test
    void testTranscodeRemaining_H_prevNonVowel() {
        // given
        char prev = 'B';
        char curr = 'H';
        char next = 'A';
        char aNext = 'T';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'B' }, result);
    }

    @Test
    void testTranscodeRemaining_H_nextNonVowel() {
        // given
        char prev = 'A';
        char curr = 'H';
        char next = 'B';
        char aNext = 'T';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'A' }, result);
    }

    @Test
    void testTranscodeRemaining_W_prevVowel() {
        // given
        char prev = 'A';
        char curr = 'W';
        char next = 'B';
        char aNext = 'T';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'A' }, result);
    }

    @Test
    void testTranscodeRemaining_Default() {
        // given
        char prev = 'B';
        char curr = 'X';
        char next = 'Y';
        char aNext = 'Z';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'X' }, result);
    }

    @Test
    void testTranscodeRemaining_Vowel_I() {
        // given
        char prev = 'B';
        char curr = 'I';
        char next = 'O';
        char aNext = 'U';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'A' }, result);
    }

    @Test
    void testTranscodeRemaining_Vowel_O() {
        // given
        char prev = 'B';
        char curr = 'O';
        char next = 'U';
        char aNext = 'E';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'A' }, result);
    }

    @Test
    void testTranscodeRemaining_Vowel_U() {
        // given
        char prev = 'B';
        char curr = 'U';
        char next = 'E';
        char aNext = 'I';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'A' }, result);
    }

    @Test
    void testTranscodeRemaining_H_prevAndNextVowel() {
        // given
        char prev = 'A';
        char curr = 'H';
        char next = 'E';
        char aNext = 'T';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'A' }, result);
    }

    @Test
    void testTranscodeRemaining_W_prevNonVowel() {
        // given
        char prev = 'B';
        char curr = 'W';
        char next = 'O';
        char aNext = 'R';
        // when
        char[] result = invokeTranscodeRemaining(prev, curr, next, aNext);
        // then
        assertArrayEquals(new char[] { 'W' }, result);
    }
}
