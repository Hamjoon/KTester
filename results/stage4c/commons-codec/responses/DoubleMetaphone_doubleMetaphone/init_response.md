```java
package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class DoubleMetaphone_doubleMetaphone_Test {

    private DoubleMetaphone doubleMetaphone;

    @BeforeAll
    static void setupBeforeAll() {
        // Potential setup for class-wide resources
    }

    @BeforeEach
    void setupBeforeEach() {
        doubleMetaphone = new DoubleMetaphone();
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Potential cleanup for class-wide resources
    }

    @Test
    void testDoubleMetaphonePrimary() {
        // Given
        String input = "jumped";
        boolean alternate = false;

        // When
        String result = doubleMetaphone.doubleMetaphone(input, alternate);

        // Then
        assertEquals("JMPT", result);
    }

    @Test
    void testDoubleMetaphoneAlternate() {
        // Given
        String input = "jumped";
        boolean alternate = true;

        // When
        String result = doubleMetaphone.doubleMetaphone(input, alternate);

        // Then
        assertEquals("AMPT", result);
    }

    @Test
    void testDoubleMetaphoneNullInput() {
        // Given
        String input = null;
        boolean alternate = false;

        // When
        String result = doubleMetaphone.doubleMetaphone(input, alternate);

        // Then
        assertNull(result);
    }
}
```