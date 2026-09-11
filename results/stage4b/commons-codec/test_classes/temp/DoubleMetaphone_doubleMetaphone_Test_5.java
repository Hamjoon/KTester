package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DoubleMetaphone_doubleMetaphone_Test {

    private DoubleMetaphone doubleMetaphone;

    @BeforeEach
    void setupBeforeEach() {
        doubleMetaphone = new DoubleMetaphone();
    }

    @Test
    void testDoubleMetaphoneWithValidInput() {
        String input = "example";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertNotNull(result);
        // Add more assertions based on expected output
    }

    @Test
    void testDoubleMetaphoneWithNullInput() {
        String input = null;
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertNull(result);
    }

    @Test
    void testDoubleMetaphoneWithAlternate() {
        String input = "example";
        boolean alternate = true;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertNotNull(result);
        // Add more assertions based on expected output
    }

    @Test
    void testDoubleMetaphoneWithEmptyInput() {
        String input = "";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("", result);
    }

    @Test
    void testDoubleMetaphoneBoundaryCases() {
        String input = "A";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertNotNull(result);
        // Assuming single vowel returns itself
        assertEquals("A", result);
    }

    @Test
    void testDoubleMetaphoneWithRegularName() {
        String input = "Smith";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("S530", result);
    }

    @Test
    void testDoubleMetaphoneWithRegularNameAlternate() {
        String input = "Smith";
        boolean alternate = true;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("S530", result);
    }

    @Test
    void testDoubleMetaphoneWithApostropheInName() {
        String input = "O'Connor";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("A245", result);
    }

    @Test
    void testDoubleMetaphoneWithApostropheInNameAlternate() {
        String input = "O'Connor";
        boolean alternate = true;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("A245", result);
    }

    @Test
    void testDoubleMetaphoneWithBach() {
        String input = "Bach";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("P200", result);
    }

    @Test
    void testDoubleMetaphoneWithBachAlternate() {
        String input = "Bach";
        boolean alternate = true;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("P200", result);
    }

    @Test
    void testDoubleMetaphoneWithXavier() {
        String input = "Xavier";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("S140", result);
    }

    @Test
    void testDoubleMetaphoneWithXavierAlternate() {
        String input = "Xavier";
        boolean alternate = true;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("S140", result);
    }

    @Test
    void testDoubleMetaphoneWithKnox() {
        String input = "Knox";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("K520", result);
    }

    @Test
    void testDoubleMetaphoneWithKnoxAlternate() {
        String input = "Knox";
        boolean alternate = true;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("K520", result);
    }

    @Test
    void testDoubleMetaphoneWithSingleVowel() {
        String input = "A";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("A", result);
    }

    @Test
    void testDoubleMetaphoneWithSingleVowelAlternate() {
        String input = "A";
        boolean alternate = true;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("A", result);
    }

    @Test
    void testDoubleMetaphoneWithSingleConsonant() {
        String input = "B";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("P", result);
    }

    @Test
    void testDoubleMetaphoneWithSingleConsonantAlternate() {
        String input = "B";
        boolean alternate = true;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("P", result);
    }

    @Test
    void testDoubleMetaphoneWithAardvark() {
        String input = "Aardvark";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("A634", result);
    }

    @Test
    void testDoubleMetaphoneWithAardvarkAlternate() {
        String input = "Aardvark";
        boolean alternate = true;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("A634", result);
    }

    @Test
    void testDoubleMetaphoneWithSchmidt() {
        String input = "Schmidt";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("S353", result);
    }

    @Test
    void testDoubleMetaphoneWithSchmidtAlternate() {
        String input = "Schmidt";
        boolean alternate = true;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("S353", result);
    }

    @Test
    void testDoubleMetaphoneWithOReilly() {
        String input = "O'Reilly";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("A643", result);
    }

    @Test
    void testDoubleMetaphoneWithOReillyAlternate() {
        String input = "O'Reilly";
        boolean alternate = true;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("A643", result);
    }

    @Test
    void testDoubleMetaphoneWithEmptyString() {
        String input = "";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("", result);
    }

    @Test
    void testDoubleMetaphoneWithEmptyStringAlternate() {
        String input = "";
        boolean alternate = true;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("", result);
    }

    @Test
    void testDoubleMetaphoneWithWhitespace() {
        String input = " ";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("", result);
    }

    @Test
    void testDoubleMetaphoneWithWhitespaceAlternate() {
        String input = "  ";
        boolean alternate = true;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("", result);
    }

    @Test
    void testDoubleMetaphoneWithNumericCharacters() {
        String input = "12345";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("", result);
    }

    @Test
    void testDoubleMetaphoneWithNumericCharactersAlternate() {
        String input = "12345";
        boolean alternate = true;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("", result);
    }

    @Test
    void testDoubleMetaphoneWithSpecialCharacters() {
        String input = "!!@#$%";
        boolean alternate = false;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("", result);
    }

    @Test
    void testDoubleMetaphoneWithSpecialCharactersAlternate() {
        String input = "!!@#$%";
        boolean alternate = true;
        String result = doubleMetaphone.doubleMetaphone(input, alternate);
        assertEquals("", result);
    }
}
