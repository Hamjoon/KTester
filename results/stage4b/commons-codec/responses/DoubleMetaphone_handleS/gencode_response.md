```java
package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DoubleMetaphone_handleS_Test {
    private DoubleMetaphone doubleMetaphone;
    private DoubleMetaphone.DoubleMetaphoneResult result;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can go here, if necessary
    }

    @BeforeEach
    void setupBeforeEach() {
        doubleMetaphone = new DoubleMetaphone();
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
    }

    @AfterEach
    void teardownAfterEach() {
        // Any cleanup after each test can go here, if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can go here, if necessary
    }

    @Test
    void testHandleSSpecialCases() {
        // Test the special case for 'island' which should increment index to 2.
        assertEquals(2, invokeHandleS("island", result, 1, false));
        assertEquals("S", result.getPrimary());

        // Test the special case for 'sugar' which should append 'X' and 'S' and increment index to 1.
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
        assertEquals(1, invokeHandleS("sugar", result, 0, false));
        assertEquals("X", result.getPrimary());

        // Test the special case for 'isle' which should increment index to 2.
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
        assertEquals(2, invokeHandleS("isle", result, 1, false));
    }

    @Test
    void testHandleSWithSH() {
        // Test handling 'SH' followed by non-germanic suffix, should append 'X' and increment index to 2.
        assertEquals(2, invokeHandleS("shelter", result, 0, false));
        assertEquals("X", result.getPrimary());

        // Test handling 'SH' followed by a Germanic suffix, should append 'S' and increment index to 2.
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
        assertEquals(2, invokeHandleS("sholz", result, 0, true));
        assertEquals("S", result.getPrimary());

        // Test handling 'SH' followed by a non-germanic suffix, should append 'X' and increment index to 2.
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
        assertEquals(2, invokeHandleS("shiny", result, 0, false));
        assertEquals("X", result.getPrimary());

        // Test handling 'SH' followed by a Germanic suffix, should append 'S' and increment index to 2.
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
        assertEquals(2, invokeHandleS("shmidt", result, 0, true));
        assertEquals("S", result.getPrimary());
    }

    @Test
    void testHandleSItalianAndArmenianCases() {
        // Test handling 'SIO' without slavoGermanic, should append 'S' and 'X' and increment index to 3.
        assertEquals(3, invokeHandleS("sio", result, 0, false));
        assertEquals("S", result.getPrimary());
        assertEquals("X", result.getAlternate());

        // Test handling 'SIAN' with slavoGermanic, should append 'S' and increment index to 3.
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
        assertEquals(3, invokeHandleS("sian", result, 0, true));
        assertEquals("S", result.getPrimary());

        // Test handling 'SIAN' without slavoGermanic, should append 'S' and 'X' and increment index to 3.
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
        assertEquals(3, invokeHandleS("sian", result, 1, false));
        assertEquals("S", result.getPrimary());
        assertEquals("X", result.getAlternate());
    }

    @Test
    void testHandleSGermanAndAnglicisations() {
        // Test handling 'S' followed by 'M' (as in 'smith'), should append 'S' and increment index to 1.
        assertEquals(1, invokeHandleS("smith", result, 0, false));
        assertEquals("S", result.getPrimary());

        // Test handling 'SZ' leading to 'Z' (as in 'szmidt'), should append 'S' and increment index to 2.
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
        assertEquals(2, invokeHandleS("szmidt", result, 0, false));
        assertEquals("S", result.getPrimary());

        // Test handling 'S' followed by 'N' (as in 'snider'), should append 'S' and increment index to 1.
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
        assertEquals(1, invokeHandleS("snider", result, 0, false));
        assertEquals("S", result.getPrimary());
    }

    @Test
    void testHandleSHandlingSC() {
        // Test handling 'SC' which should call handleSC and increment index to 2.
        assertEquals(2, invokeHandleS("school", result, 0, false));

        // Test handling 'SC' which should call handleSC and increment index to 2.
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
        assertEquals(2, invokeHandleS("scary", result, 0, false));
    }

    @Test
    void testHandleSDefaultCase() {
        // Test default case when no specific conditions are met, should append 'S' and increment index to 1.
        assertEquals(1, invokeHandleS("test", result, 0, false));
        assertEquals("S", result.getPrimary());

        // Test default case at the end of the string, should append alternate 'S' and increment index to 5.
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
        assertEquals(5, invokeHandleS("tests", result, 4, false));
        assertEquals("S", result.getAlternate());

        // Test default case when the input is a single 'S', should append 'S' and increment index to 1.
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
        assertEquals(1, invokeHandleS("s", result, 0, false));
        assertEquals("S", result.getPrimary());

        // Test default case at the end of the string, should append alternate 'S' and increment index to 5.
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
        assertEquals(5, invokeHandleS("tests", result, 3, false));
        assertEquals("S", result.getAlternate());
    }

    @Test
    void testHandleSExceptionScenarios() {
        // Test with null value for 'value', should throw NullPointerException.
        assertThrows(NullPointerException.class, () -> {
            invokeHandleS(null, result, 0, false);
        });

        // Test with null result, should throw NullPointerException.
        assertThrows(NullPointerException.class, () -> {
            invokeHandleS("test", null, 0, false);
        });

        // Test with negative index, should throw IndexOutOfBoundsException.
        assertThrows(IndexOutOfBoundsException.class, () -> {
            invokeHandleS("test", result, -1, false);
        });

        // Test with index greater than the length of the string, should throw IndexOutOfBoundsException.
        assertThrows(IndexOutOfBoundsException.class, () -> {
            invokeHandleS("test", result, 10, false);
        });
    }

    private int invokeHandleS(String value, DoubleMetaphone.DoubleMetaphoneResult result, int index, boolean slavoGermanic) {
        // Use reflection to invoke the private handleS method
        try {
            var method = DoubleMetaphone.class.getDeclaredMethod("handleS", String.class, DoubleMetaphone.DoubleMetaphoneResult.class, int.class, boolean.class);
            method.setAccessible(true);
            return (int) method.invoke(doubleMetaphone, value, result, index, slavoGermanic);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```