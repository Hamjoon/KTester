```java
package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class DoubleMetaphone_handleC_Test {

    private DoubleMetaphone doubleMetaphone;

    private DoubleMetaphone.DoubleMetaphoneResult result;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the DoubleMetaphone instance and result before each test
        doubleMetaphone = new DoubleMetaphone();
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
    }

    // Method source for parameterized tests
    static Object[][] handleCTestCases() {
        return new Object[][] {
            { "CAESAR", 0, 'S', 2 },
            { "CHICKEN", 0, 'S', 2 },
            { "CZERNY", 0, 'S', 2 },
            { "Focaccia", 0, 'X', 3 },
            { "McClelland", 1, 'K', 2 },
            { "CK", 0, 'K', 2 },
            { "CIELO", 0, 'S', 2 },
            { "C", 0, 'K', 1 },
            { "CICADA", 0, 'S', 2 },
            { "CABBAGE", 0, 'K', 2 },
            { "CICERO", 0, 'S', 2 },
            { "CATCH", 0, 'K', 2 },
            { "C", 0, 'K', 1 },
            { "CLOTH", 0, 'K', 1 },
            { "CANDY", 0, 'K', 1 },
            { "", 0, '\0', 0 },
            { "C", 0, '\0', 0 },
            { "C", 1, '\0', 0 }
        };
    }

    private int invokeHandleC(String input, DoubleMetaphone.DoubleMetaphoneResult result, int index) throws Exception {
        Method handleCMethod = DoubleMetaphone.class.getDeclaredMethod("handleC", String.class, DoubleMetaphone.DoubleMetaphoneResult.class, int.class);
        // Make the private method accessible
        handleCMethod.setAccessible(true);
        return (int) handleCMethod.invoke(doubleMetaphone, input, result, index);
    }

    @Test
    void testHandleC() throws Exception {
        // Given
        String input = "CICERO";
        int index = 0;
        // When
        index = invokeHandleC(input, result, index);
        // Then
        assertEquals('S', result.getPrimary().charAt(0));
        assertEquals(2, index);
    }

    @Test
    void testHandleC_emptyString() {
        // Test for empty input string
        assertThrows(IndexOutOfBoundsException.class, () -> {
            invokeHandleC("", result, 0);
        });
    }

    @Test
    void testHandleC_singleCharacterWithZeroMaxLength() {
        // Test for single character 'C' with max length of 0
        result = doubleMetaphone.new DoubleMetaphoneResult(0);
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            invokeHandleC("C", result, 0);
        });
    }

    @Test
    void testHandleC_indexOutOfBounds() {
        // Test for index out of bounds for input string 'C'
        assertThrows(IndexOutOfBoundsException.class, () -> {
            invokeHandleC("C", result, 1);
        });
    }
}
``` 

### Corrections Made:
1. The test cases were set up to expect exceptions that were not being thrown. The `handleC` method needs to properly handle empty strings and cases where the index is out of bounds.
2. The method `invokeHandleC` remains unchanged as it correctly invokes the private method.
3. The test cases are structured to assert the correct exceptions are thrown for edge cases.