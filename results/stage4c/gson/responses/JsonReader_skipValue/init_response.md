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

@ExtendWith(MockitoExtension.class)
class JsonReader_skipValue_Test {

    private JsonReader jsonReader;

    @BeforeAll
    static void setupBeforeAll() {
        // Setup resources that are shared across all tests, if any
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JsonReader with a mock or actual Reader
        Reader reader = new StringReader("{\"a\": [\"one\", \"two\", \"three\"], \"b\": 123}");
        jsonReader = new JsonReader(reader);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources specific to each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Cleanup shared resources, if any
    }

    @Test
    void testSkipValue() throws IOException {
        jsonReader.beginObject();
        assertEquals("a", jsonReader.nextName());
        jsonReader.skipValue(); // This is the focal method
        assertEquals("b", jsonReader.nextName());
        assertEquals(123, jsonReader.nextInt());
        jsonReader.endObject();
    }
}
```