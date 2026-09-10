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

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the DoubleMetaphone instance and result before each test
        doubleMetaphone = new DoubleMetaphone();
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
    }

    // Method source for parameterized tests
    static Object[][] handleCTestCases() {
        return new Object[][] { // Test for 'CAESAR'
        { "CAESAR", 0, 'S', 2 }, // Test for 'CHICKEN'
        { "CHICKEN", 0, 'S', 2 }, // Test for 'CZERNY'
        { "CZERNY", 0, 'S', 2 }, // Test for 'Focaccia'
        { "Focaccia", 0, 'X', 3 }, // Test for 'McClelland'
        { "McClelland", 1, 'K', 2 }, // Test for 'CK'
        { "CK", 0, 'K', 2 }, // Test for 'CIELO'
        { "CIELO", 0, 'S', 2 }, // Test for single character 'C'
        { "C", 0, 'K', 1 }, // Test for 'CICADA'
        { "CICADA", 0, 'S', 2 }, // Test for 'CABBAGE'
        { "CABBAGE", 0, 'K', 2 }, // Test for 'CICERO'
        { "CICERO", 0, 'S', 2 }, // Test for 'CATCH'
        { "CATCH", 0, 'K', 2 }, // Test for single character 'C' with max length 1
        { "C", 0, 'K', 1 }, // Test for 'CLOTH'
        { "CLOTH", 0, 'K', 1 }, // Test for 'CANDY'
        { "CANDY", 0, 'K', 1 }, // Test for empty string (will throw exception)
        { "", 0, '\0', 0 }, // Test for 'C' with max length 0 (will throw exception)
        { "C", 0, '\0', 0 }, // Test for index out of bounds
        { "C", 1, '\0', 0 } };
    }

    @Test
    void testHandleC() {
        // Given
        // Example input string
        String input = "CICERO";
        // Starting index
        int index = 0;
        // When
        index = doubleMetaphone.handleC(input, result, index);
        // Then
        // Assuming the first character is expected to be 'S'
        assertEquals('S', result.getPrimary().charAt(0));
        // Check that the index is updated correctly
        assertEquals(2, index);
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
