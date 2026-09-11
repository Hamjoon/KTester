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
    void testHandleSWithSpecialCaseIsland() {
        String input = "island";
        int index = 0;
        boolean slavoGermanic = false;

        index = invokeHandleS(input, result, index, slavoGermanic);

        assertEquals("S", result.getPrimary());
        assertFalse(result.isComplete());
    }

    @Test
    void testHandleSWithSugar() {
        String input = "sugar";
        int index = 0;
        boolean slavoGermanic = false;

        index = invokeHandleS(input, result, index, slavoGermanic);

        assertEquals("X", result.getPrimary());
        assertFalse(result.isComplete());
    }

    @Test
    void testHandleSWithSH() {
        String input = "shiny";
        int index = 0;
        boolean slavoGermanic = false;

        index = invokeHandleS(input, result, index, slavoGermanic);

        assertEquals("X", result.getPrimary());
        assertFalse(result.isComplete());
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