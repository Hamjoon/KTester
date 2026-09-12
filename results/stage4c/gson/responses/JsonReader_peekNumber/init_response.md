```java
package com.google.gson.stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class JsonReader_peekNumber_Test {

    private JsonReader jsonReader;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize global resources if any
    }

    @BeforeEach
    void setupBeforeEach() {
        // Setup necessary before each test
        Reader reader = new StringReader("123");
        jsonReader = new JsonReader(reader);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Cleanup global resources if any
    }

    @Test
    void testPeekNumber() throws IOException {
        // Given
        // The setup already initializes a JsonReader with "123"

        // When
        int result = jsonReader.peekNumber();

        // Then
        assertEquals(JsonReader.PEEKED_LONG, result);
    }

    @Test
    void testPeekNumberWithInvalidInput() throws IOException {
        // Given
        Reader invalidReader = new StringReader("abc");
        jsonReader = new JsonReader(invalidReader);

        // When
        int result = jsonReader.peekNumber();

        // Then
        assertEquals(JsonReader.PEEKED_NONE, result);
    }
}
```