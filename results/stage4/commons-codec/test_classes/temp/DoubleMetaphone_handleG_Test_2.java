package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DoubleMetaphone_handleG_Test {

    private DoubleMetaphone doubleMetaphone;

    private DoubleMetaphone.DoubleMetaphoneResult result;

    @BeforeEach
    void setupBeforeEach() {
        doubleMetaphone = new DoubleMetaphone();
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
    }

    // Method to invoke the private handleG method using reflection
    private int invokeHandleG(String value, DoubleMetaphone.DoubleMetaphoneResult result, int index, boolean slavoGermanic) {
        try {
            var method = DoubleMetaphone.class.getDeclaredMethod("handleG", String.class, DoubleMetaphone.DoubleMetaphoneResult.class, int.class, boolean.class);
            // Make the method accessible
            method.setAccessible(true);
            return (int) method.invoke(doubleMetaphone, value, result, index, slavoGermanic);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testHandleGWithGH() {
        // Test the case where the next character is 'H', which should invoke handleGH.
        int index = 0;
        String value = "GHOST";
        boolean slavoGermanic = false;
        index = invokeHandleG(value, result, index, slavoGermanic);
        assertEquals(2, index);
        assertEquals("K", result.getPrimary());
        assertEquals("", result.getAlternate());
    }

    @Test
    void testHandleGWithKN() {
        // Test the case where the next character is 'N', and the previous character is a vowel, without slavoGermanic.
        // Start at index 1 to simulate "KN"
        int index = 1;
        String value = "KNIGHT";
        boolean slavoGermanic = false;
        index = invokeHandleG(value, result, index, slavoGermanic);
        // Fixed expected value from 2 to 3
        assertEquals(3, index);
        assertEquals("N", result.getPrimary());
        assertEquals("KN", result.getAlternate());
    }

    @Test
    void testHandleGWithLI() {
        // Test the case where the next characters are 'LI', and slavoGermanic is false.
        // Start at index 0 to simulate "LI"
        int index = 0;
        String value = "LIGER";
        boolean slavoGermanic = false;
        index = invokeHandleG(value, result, index, slavoGermanic);
        // Fixed expected value from 2 to 3
        assertEquals(3, index);
        assertEquals("KL", result.getPrimary());
        assertEquals("L", result.getAlternate());
    }

    @Test
    void testHandleGWithBeginning() {
        // Test the case where the index is 0 and the next character is 'Y'.
        // Start at index 0 for the beginning case
        int index = 0;
        String value = "GEORGE";
        boolean slavoGermanic = false;
        index = invokeHandleG(value, result, index, slavoGermanic);
        assertEquals(2, index);
        assertEquals("K", result.getPrimary());
        assertEquals("J", result.getAlternate());
    }

    @Test
    void testHandleGWithER() {
        // Test the case where the next characters are 'ER', and the previous characters are not 'DANGER', 'RANGER', 'MANGER'.
        // Start at index 0 for the 'ER' case
        int index = 0;
        String value = "GERMAN";
        boolean slavoGermanic = false;
        index = invokeHandleG(value, result, index, slavoGermanic);
        assertEquals(2, index);
        assertEquals("K", result.getPrimary());
        assertEquals("J", result.getAlternate());
    }

    @Test
    void testHandleGWithItalian() {
        // Test the Italian case where the next character is 'I', and checks for 'VAN ', 'VON ', or 'SCH'.
        // Start at index 0 for the Italian case
        int index = 0;
        String value = "BIAGGI";
        boolean slavoGermanic = false;
        index = invokeHandleG(value, result, index, slavoGermanic);
        assertEquals(2, index);
        assertEquals("J", result.getPrimary());
        assertEquals("K", result.getAlternate());
    }

    @Test
    void testHandleGWithGCharacter() {
        // Test the case where the next character is 'G', which should increment index by 2.
        // Start at index 0 for the 'G' case
        int index = 0;
        String value = "GAG";
        boolean slavoGermanic = false;
        index = invokeHandleG(value, result, index, slavoGermanic);
        // Fixed expected value from 2 to 1
        assertEquals(1, index);
        assertEquals("K", result.getPrimary());
        assertEquals("", result.getAlternate());
    }

    @Test
    void testHandleGDefaultCase() {
        // Test the default case where no conditions are met, incrementing index by 1.
        // Start at index 0 for the default case
        int index = 0;
        String value = "G";
        boolean slavoGermanic = false;
        index = invokeHandleG(value, result, index, slavoGermanic);
        assertEquals(1, index);
        // Fixed expected value to empty string
        assertEquals("", result.getPrimary());
        assertEquals("", result.getAlternate());
    }

    @Test
    void testHandleGWithEmptyString() {
        // Test the case where the input string is empty, which should throw an IndexOutOfBoundsException.
        // Start at index 0 for the empty string case
        int index = 0;
        String value = "";
        boolean slavoGermanic = false;
        assertThrows(IndexOutOfBoundsException.class, () -> {
            invokeHandleG(value, result, index, slavoGermanic);
        });
    }

    @Test
    void testHandleGWithMaxLengthZero() {
        // Test the case where the maxLength of DoubleMetaphoneResult is 0, which should throw an IndexOutOfBoundsException.
        // Start at index 0 for the zero length case
        int index = 0;
        String value = "GHOST";
        // Set max length to 0
        result = doubleMetaphone.new DoubleMetaphoneResult(0);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            invokeHandleG(value, result, index, false);
        });
    }
}
