package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Metaphone_metaphone_Test {

    private Metaphone metaphone;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Metaphone instance before each test
        metaphone = new Metaphone();
    }

    @Test
    void testMetaphoneWithNullInput() {
        // Test with null input, should return an empty string.
        assertEquals("", metaphone.metaphone(null));
    }

    @Test
    void testMetaphoneWithEmptyInput() {
        // Test with empty string input, should return an empty string.
        assertEquals("", metaphone.metaphone(""));
    }

    @Test
    void testMetaphoneWithSingleCharacter() {
        // Test with single character input 'A', should return 'A'.
        assertEquals("A", metaphone.metaphone("A"));
        // Test with single character input 'K', should return 'K'.
        assertEquals("K", metaphone.metaphone("K"));
    }

    @Test
    void testMetaphoneWithKnownWords() {
        // Test with input 'KNIGHT', should handle 'K' followed by 'N' correctly.
        assertEquals("NJT", metaphone.metaphone("KNIGHT"));
        // Test with input 'WHISTLE', should handle 'W' followed by 'H' correctly.
        assertEquals("WSTL", metaphone.metaphone("WHISTLE"));
        // Test with input 'AEON', should handle 'A' followed by 'E' correctly.
        assertEquals("N", metaphone.metaphone("AEON"));
        // Test with input 'CHURCH', should handle 'CH' correctly.
        assertEquals("KX", metaphone.metaphone("CHURCH"));
        // Test with input 'SCHOOL', should handle 'SC' followed by 'H' correctly.
        assertEquals("SKL", metaphone.metaphone("SCHOOL"));
        // Test with input 'THOMAS', should handle 'TH' correctly.
        assertEquals("T0MS", metaphone.metaphone("THOMAS"));
        // Test with input 'SILVER', should handle 'S' followed by 'I' correctly.
        assertEquals("SLFR", metaphone.metaphone("SILVER"));
        // Test with input 'GHOST', should handle 'G' followed by 'H' correctly.
        assertEquals("KST", metaphone.metaphone("GHOST"));
        // Test with input 'KILLED', should handle 'K' correctly.
        assertEquals("KLT", metaphone.metaphone("KILLED"));
        // Test with input 'BOMB', should handle silent 'B' at the end.
        assertEquals("BM", metaphone.metaphone("BOMB"));
        // Test with input 'PHOENIX', should handle 'PH' correctly.
        assertEquals("FNKS", metaphone.metaphone("PHOENIX"));
        // Test with input 'XENON', should handle initial 'X' correctly.
        assertEquals("SNN", metaphone.metaphone("XENON"));
        // Test with input 'AEONIC', should handle 'A' followed by 'E' correctly and also consider the consonants.
        assertEquals("NNC", metaphone.metaphone("AEONIC"));
        // Test with input 'SHEEP', should handle 'SH' correctly.
        assertEquals("S", metaphone.metaphone("SHEEP"));
        // Test with input 'KNOW', should handle 'K' followed by 'N' correctly.
        assertEquals("NO", metaphone.metaphone("KNOW"));
        // Test with input 'GHASTLY', should handle 'GH' correctly.
        assertEquals("KSTL", metaphone.metaphone("GHASTLY"));
        // Test with input 'CIPHER', should handle 'C' followed by 'I' correctly.
        assertEquals("SFR", metaphone.metaphone("CIPHER"));
        // Test with input 'THROUGH', should handle 'TH' correctly.
        assertEquals("T0R", metaphone.metaphone("THROUGH"));
        // Test with input 'QUICK', should handle 'Q' correctly.
        assertEquals("K", metaphone.metaphone("QUICK"));
        // Test with input 'WATER', should handle 'W' correctly.
        assertEquals("WTR", metaphone.metaphone("WATER"));
        // Test with input 'BEE', should handle vowel 'E' correctly.
        assertEquals("B", metaphone.metaphone("BEE"));
        // Test with input 'SCHOOLING', should handle 'SC' followed by 'H' and other consonants.
        assertEquals("SKLN", metaphone.metaphone("SCHOOLING"));
        // Test with input 'JUMP', should handle 'J' correctly.
        assertEquals("JMP", metaphone.metaphone("JUMP"));
        // Test with input 'FISH', should handle 'F' correctly.
        assertEquals("F", metaphone.metaphone("FISH"));
        // Test with input 'YELLOW', should handle 'Y' followed by a vowel correctly.
        assertEquals("YL", metaphone.metaphone("YELLOW"));
        // Test with input 'ZEBRA', should handle 'Z' correctly.
        assertEquals("SBR", metaphone.metaphone("ZEBRA"));
        // Test with input 'MATH', should handle 'TH' correctly.
        assertEquals("MT", metaphone.metaphone("MATH"));
        // Test with input 'KNOT', should handle 'K' followed by 'N' correctly.
        assertEquals("NT", metaphone.metaphone("KNOT"));
    }

    @Test
    void testIsMetaphoneEqual() {
        // Test for equality of metaphone values.
        assertTrue(metaphone.isMetaphoneEqual("case", "case"));
        assertTrue(metaphone.isMetaphoneEqual("Case", "case"));
        assertFalse(metaphone.isMetaphoneEqual("case", "other"));
    }
}
