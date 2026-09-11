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
        // This method can be used to set up any static resources if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        doubleMetaphone = new DoubleMetaphone();
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
    }

    @AfterEach
    void teardownAfterEach() {
        // This method can be used to clean up resources after each test if needed
    }

    @AfterAll
    static void teardownAfterAll() {
        // This method can be used to clean up any static resources if needed
    }

    @Test
    void testHandleC_withValidInput() {
        // Given
        String input = "CIC";
        int index = 0;

        // When
        index = doubleMetaphone.handleC(input, result, index);

        // Then
        assertEquals('S', result.getPrimary().charAt(0));
        assertEquals(2, index);
    }

    @Test
    void testHandleC_withSpecialCase() {
        // Given
        String input = "CAESAR";
        int index = 0;

        // When
        index = doubleMetaphone.handleC(input, result, index);

        // Then
        assertEquals('S', result.getPrimary().charAt(0));
        assertEquals(2, index);
    }

    @Test
    void testHandleC_withDoubleCC() {
        // Given
        String input = "ACCIDENT";
        int index = 0;

        // When
        index = doubleMetaphone.handleC(input, result, index);

        // Then
        assertEquals('K', result.getPrimary().charAt(0));
        assertEquals(2, index);
    }

    // Additional test cases can be added here to cover more scenarios for handleC
}
```