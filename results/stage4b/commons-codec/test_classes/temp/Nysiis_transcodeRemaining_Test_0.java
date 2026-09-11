package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Nysiis_transcodeRemaining_Test {

    private Nysiis nysiis;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Nysiis object before each test
        nysiis = new Nysiis();
    }

    @Test
    void testTranscodeRemaining() {
        // Using reflection to access the private method transcodeRemaining
        var method = Nysiis.class.getDeclaredMethod("transcodeRemaining", char.class, char.class, char.class, char.class);
        method.setAccessible(true);
        // Test cases for transcodeRemaining
        // EV -> AF
        assertArrayEquals(new char[] { 'A', 'F' }, (char[]) method.invoke(nysiis, 'A', 'E', 'V', 'X'));
        // A -> A
        assertArrayEquals(new char[] { 'A' }, (char[]) method.invoke(nysiis, 'A', 'A', 'B', 'C'));
        // Q -> G
        assertArrayEquals(new char[] { 'G' }, (char[]) method.invoke(nysiis, 'A', 'Q', 'B', 'C'));
        // Z -> S
        assertArrayEquals(new char[] { 'S' }, (char[]) method.invoke(nysiis, 'A', 'Z', 'B', 'C'));
        // M -> N
        assertArrayEquals(new char[] { 'N' }, (char[]) method.invoke(nysiis, 'A', 'M', 'B', 'C'));
        // K -> C
        assertArrayEquals(new char[] { 'C' }, (char[]) method.invoke(nysiis, 'A', 'K', 'B', 'C'));
        // KN -> NN
        assertArrayEquals(new char[] { 'N', 'N' }, (char[]) method.invoke(nysiis, 'A', 'K', 'N', 'C'));
        // SCH -> SSS
        assertArrayEquals(new char[] { 'S', 'S', 'S' }, (char[]) method.invoke(nysiis, 'S', 'S', 'C', 'H'));
        // PH -> FF
        assertArrayEquals(new char[] { 'F', 'F' }, (char[]) method.invoke(nysiis, 'A', 'P', 'H', 'C'));
        // H -> prev (non-vowel)
        assertArrayEquals(new char[] { 'B' }, (char[]) method.invoke(nysiis, 'B', 'H', 'A', 'C'));
        // H -> prev (vowel)
        assertArrayEquals(new char[] { 'A' }, (char[]) method.invoke(nysiis, 'A', 'H', 'A', 'C'));
        // W -> prev (vowel)
        assertArrayEquals(new char[] { 'A' }, (char[]) method.invoke(nysiis, 'A', 'W', 'A', 'C'));
        // W -> curr (non-vowel)
        assertArrayEquals(new char[] { 'W' }, (char[]) method.invoke(nysiis, 'B', 'W', 'A', 'C'));
        // H -> prev (vowel)
        assertArrayEquals(new char[] { 'A' }, (char[]) method.invoke(nysiis, 'A', 'H', 'B', 'C'));
        // H -> prev (non-vowel)
        assertArrayEquals(new char[] { 'B' }, (char[]) method.invoke(nysiis, 'B', 'H', 'C', 'D'));
        // W -> prev (vowel)
        assertArrayEquals(new char[] { 'E' }, (char[]) method.invoke(nysiis, 'E', 'W', 'A', 'C'));
        // W -> curr (non-vowel)
        assertArrayEquals(new char[] { 'W' }, (char[]) method.invoke(nysiis, 'B', 'W', 'C', 'D'));
        // E -> A
        assertArrayEquals(new char[] { 'A' }, (char[]) method.invoke(nysiis, 'A', 'E', 'A', 'B'));
        // Non-special character
        assertArrayEquals(new char[] { 'X' }, (char[]) method.invoke(nysiis, 'A', 'X', 'Y', 'Z'));
        // H -> prev (vowel)
        assertArrayEquals(new char[] { 'A' }, (char[]) method.invoke(nysiis, 'A', 'H', 'C', 'D'));
    }
}
