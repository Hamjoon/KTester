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
        // Any global setup can be done here if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        doubleMetaphone = new DoubleMetaphone();
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
    }

    @AfterEach
    void teardownAfterEach() {
        // Any cleanup after each test can be done here if needed
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here if needed
    }

    @Test
    void testHandleS_SpecialCases() {
        // Given
        String input = "SUGAR";
        int index = 0;
        boolean slavoGermanic = false;

        // When
        index = doubleMetaphone.handleS(input, result, index, slavoGermanic);

        // Then
        assertEquals('X', result.getPrimary().charAt(0));
        assertEquals('S', result.getAlternate().charAt(0));
    }

    @Test
    void testHandleS_GermanicCases() {
        // Given
        String input = "SHOEK";
        int index = 0;
        boolean slavoGermanic = true;

        // When
        index = doubleMetaphone.handleS(input, result, index, slavoGermanic);

        // Then
        assertEquals('S', result.getPrimary().charAt(0));
        assertTrue(result.getAlternate().isEmpty());
    }

    @Test
    void testHandleS_ItalianCases() {
        // Given
        String input = "SIO";
        int index = 0;
        boolean slavoGermanic = false;

        // When
        index = doubleMetaphone.handleS(input, result, index, slavoGermanic);

        // Then
        assertEquals('S', result.getPrimary().charAt(0));
        assertEquals('X', result.getAlternate().charAt(0));
    }

    @Test
    void testHandleS_NonSpecialCases() {
        // Given
        String input = "SMITH";
        int index = 0;
        boolean slavoGermanic = false;

        // When
        index = doubleMetaphone.handleS(input, result, index, slavoGermanic);

        // Then
        assertEquals('S', result.getPrimary().charAt(0));
        assertTrue(result.getAlternate().isEmpty());
    }

    @Test
    void testHandleS_SpecialCaseIsl() {
        // Given
        String input = "ISLAND";
        int index = 0;
        boolean slavoGermanic = false;

        // When
        index = doubleMetaphone.handleS(input, result, index, slavoGermanic);

        // Then
        assertEquals(1, index); // The index should increment by 1
    }

    @Test
    void testHandleS_SpecialCaseSionWithSlavoGermanic() {
        // Given
        String input = "SION";
        int index = 0;
        boolean slavoGermanic = true;

        // When
        index = doubleMetaphone.handleS(input, result, index, slavoGermanic);

        // Then
        assertEquals('S', result.getPrimary().charAt(0));
        assertTrue(result.getAlternate().isEmpty());
    }

    @Test
    void testHandleS_SpecialCaseSia() {
        // Given
        String input = "SIA";
        int index = 0;
        boolean slavoGermanic = false;

        // When
        index = doubleMetaphone.handleS(input, result, index, slavoGermanic);

        // Then
        assertEquals('S', result.getPrimary().charAt(0));
        assertEquals('X', result.getAlternate().charAt(0));
    }

    @Test
    void testHandleS_EmptyString() {
        // Given
        String input = "";
        int index = 0;
        boolean slavoGermanic = false;

        // When & Then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            doubleMetaphone.handleS(input, result, index, slavoGermanic);
        });
    }

    @Test
    void testHandleS_NegativeIndex() {
        // Given
        String input = "SHARK";
        int index = -1;
        boolean slavoGermanic = false;

        // When & Then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            doubleMetaphone.handleS(input, result, index, slavoGermanic);
        });
    }

    @Test
    void testHandleS_IndexTooHigh() {
        // Given
        String input = "S";
        int index = 2; // Out of bounds
        boolean slavoGermanic = false;

        // When & Then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            doubleMetaphone.handleS(input, result, index, slavoGermanic);
        });
    }
}
```