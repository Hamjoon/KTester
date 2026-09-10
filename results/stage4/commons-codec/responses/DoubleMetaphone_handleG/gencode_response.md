```java
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

    @BeforeAll
    static void setupBeforeAll() {
        // This method can be used for one-time setup before all tests, if needed.
    }

    @BeforeEach
    void setupBeforeEach() {
        doubleMetaphone = new DoubleMetaphone();
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
    }

    @AfterEach
    void teardownAfterEach() {
        // This method can be used for cleanup after each test, if needed.
    }

    @AfterAll
    static void teardownAfterAll() {
        // This method can be used for one-time cleanup after all tests, if needed.
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
        int index = 1; // Start at index 1 to simulate "KN"
        String value = "KNIGHT";
        boolean slavoGermanic = false;

        index = invokeHandleG(value, result, index, slavoGermanic);
        
        assertEquals(2, index);
        assertEquals("N", result.getPrimary());
        assertEquals("KN", result.getAlternate());
    }

    @Test
    void testHandleGWithLI() {
        // Test the case where the next characters are 'LI', and slavoGermanic is false.
        int index = 0; // Start at index 0 to simulate "LI"
        String value = "LIGER";
        boolean slavoGermanic = false;

        index = invokeHandleG(value, result, index, slavoGermanic);
        
        assertEquals(2, index);
        assertEquals("KL", result.getPrimary());
        assertEquals("L", result.getAlternate());
    }

    @Test
    void testHandleGWithBeginning() {
        // Test the case where the index is 0 and the next character is 'Y'.
        int index = 0; // Start at index 0 for the beginning case
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
        int index = 0; // Start at index 0 for the 'ER' case
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
        int index = 0; // Start at index 0 for the Italian case
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
        int index = 0; // Start at index 0 for the 'G' case
        String value = "GAG";
        boolean slavoGermanic = false;

        index = invokeHandleG(value, result, index, slavoGermanic);
        
        assertEquals(2, index);
        assertEquals("K", result.getPrimary());
        assertEquals("", result.getAlternate());
    }

    @Test
    void testHandleGDefaultCase() {
        // Test the default case where no conditions are met, incrementing index by 1.
        int index = 0; // Start at index 0 for the default case
        String value = "G";
        boolean slavoGermanic = false;

        index = invokeHandleG(value, result, index, slavoGermanic);
        
        assertEquals(1, index);
        assertEquals("K", result.getPrimary());
        assertEquals("", result.getAlternate());
    }

    @Test
    void testHandleGWithEmptyString() {
        // Test the case where the input string is empty, which should throw an IndexOutOfBoundsException.
        int index = 0; // Start at index 0 for the empty string case
        String value = "";
        boolean slavoGermanic = false;

        assertThrows(IndexOutOfBoundsException.class, () -> {
            invokeHandleG(value, result, index, slavoGermanic);
        });
    }

    @Test
    void testHandleGWithMaxLengthZero() {
        // Test the case where the maxLength of DoubleMetaphoneResult is 0, which should throw an IndexOutOfBoundsException.
        int index = 0; // Start at index 0 for the zero length case
        String value = "GHOST";
        result = doubleMetaphone.new DoubleMetaphoneResult(0); // Set max length to 0

        assertThrows(IndexOutOfBoundsException.class, () -> {
            invokeHandleG(value, result, index, false);
        });
    }

    // Method to invoke the private handleG method using reflection
    private int invokeHandleG(String value, DoubleMetaphone.DoubleMetaphoneResult result, int index, boolean slavoGermanic) {
        try {
            var method = DoubleMetaphone.class.getDeclaredMethod("handleG", String.class, DoubleMetaphone.DoubleMetaphoneResult.class, int.class, boolean.class);
            method.setAccessible(true); // Make the method accessible
            return (int) method.invoke(doubleMetaphone, value, result, index, slavoGermanic);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```