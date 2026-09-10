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
        int index = 1;
        String value = "KNIGHT";
        boolean slavoGermanic = false;
        index = invokeHandleG(value, result, index, slavoGermanic);
        // This was previously fixed
        assertEquals(3, index);
        assertEquals("N", result.getPrimary());
        assertEquals("KN", result.getAlternate());
    }

    @Test
    void testHandleGWithLI() {
        int index = 0;
        String value = "LIGER";
        boolean slavoGermanic = false;
        index = invokeHandleG(value, result, index, slavoGermanic);
        // This was previously fixed
        assertEquals(3, index);
        assertEquals("KL", result.getPrimary());
        assertEquals("L", result.getAlternate());
    }

    @Test
    void testHandleGWithBeginning() {
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
        int index = 0;
        String value = "GAG";
        boolean slavoGermanic = false;
        index = invokeHandleG(value, result, index, slavoGermanic);
        // This was previously fixed
        assertEquals(1, index);
        assertEquals("K", result.getPrimary());
        assertEquals("", result.getAlternate());
    }

    @Test
    void testHandleGDefaultCase() {
        int index = 0;
        String value = "G";
        boolean slavoGermanic = false;
        index = invokeHandleG(value, result, index, slavoGermanic);
        assertEquals(1, index);
        // This was previously fixed
        assertEquals("", result.getPrimary());
        assertEquals("", result.getAlternate());
    }

    @Test
    void testHandleGWithEmptyString() {
        int index = 0;
        String value = "";
        boolean slavoGermanic = false;
        assertThrows(IndexOutOfBoundsException.class, () -> {
            invokeHandleG(value, result, index, slavoGermanic);
        });
    }

    @Test
    void testHandleGWithMaxLengthZero() {
        int index = 0;
        String value = "GHOST";
        result = doubleMetaphone.new DoubleMetaphoneResult(0);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            invokeHandleG(value, result, index, false);
        });
    }
}
