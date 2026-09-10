```java
package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DoubleMetaphone_handleC_Test {
    
    private DoubleMetaphone doubleMetaphone;
    private DoubleMetaphone.DoubleMetaphoneResult result;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the DoubleMetaphone instance and result before each test
        doubleMetaphone = new DoubleMetaphone();
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources or reset states after each test, if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }

    @ParameterizedTest
    @MethodSource("handleCTestCases")
    void testHandleC(String value, int index, char expectedPrimary, int expectedIndex) {
        // When
        index = doubleMetaphone.handleC(value, result, index);

        // Then
        assertEquals(expectedPrimary, result.getPrimary().charAt(0)); // Check expected primary character
        assertEquals(expectedIndex, index); // Check that the index is updated correctly
    }

    // Method source for parameterized tests
    static Object[][] handleCTestCases() {
        return new Object[][] {
            { "CAESAR", 0, 'S', 2 }, // Test for 'CAESAR'
            { "CHICKEN", 0, 'S', 2 }, // Test for 'CHICKEN'
            { "CZERNY", 0, 'S', 2 }, // Test for 'CZERNY'
            { "Focaccia", 0, 'X', 3 }, // Test for 'Focaccia'
            { "McClelland", 1, 'K', 2 }, // Test for 'McClelland'
            { "CK", 0, 'K', 2 }, // Test for 'CK'
            { "CIELO", 0, 'S', 2 }, // Test for 'CIELO'
            { "C", 0, 'K', 1 }, // Test for single character 'C'
            { "CICADA", 0, 'S', 2 }, // Test for 'CICADA'
            { "CABBAGE", 0, 'K', 2 }, // Test for 'CABBAGE'
            { "CICERO", 0, 'S', 2 }, // Test for 'CICERO'
            { "CATCH", 0, 'K', 2 }, // Test for 'CATCH'
            { "C", 0, 'K', 1 }, // Test for single character 'C' with max length 1
            { "CLOTH", 0, 'K', 1 }, // Test for 'CLOTH'
            { "CANDY", 0, 'K', 1 }, // Test for 'CANDY'
            { "", 0, '\0', 0 }, // Test for empty string (will throw exception)
            { "C", 0, '\0', 0 }, // Test for 'C' with max length 0 (will throw exception)
            { "C", 1, '\0', 0 } // Test for index out of bounds
        };
    }

    @Test
    void testHandleC_emptyString() {
        // Test for empty input string
        assertThrows(IndexOutOfBoundsException.class, () -> {
            doubleMetaphone.handleC("", result, 0);
        });
    }

    @Test
    void testHandleC_singleCharacterWithZeroMaxLength() {
        // Test for single character 'C' with max length of 0
        result = doubleMetaphone.new DoubleMetaphoneResult(0);
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            doubleMetaphone.handleC("C", result, 0);
        });
    }

    @Test
    void testHandleC_indexOutOfBounds() {
        // Test for index out of bounds for input string 'C'
        assertThrows(IndexOutOfBoundsException.class, () -> {
            doubleMetaphone.handleC("C", result, 1);
        });
    }
}
```