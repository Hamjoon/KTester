```java
package org.apache.commons.codec.net;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;

@ExtendWith(MockitoExtension.class)
class QuotedPrintableCodec_encodeQuotedPrintable_Test {

    private QuotedPrintableCodec codec;

    @BeforeAll
    static void setupBeforeAll() {
        // Setup that needs to be done once before all tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the codec object before each test
        codec = new QuotedPrintableCodec();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test, if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Cleanup that needs to be done once after all tests
    }

    @Test
    void testEncodeQuotedPrintable_NullBytes() {
        // Given
        BitSet printable = null;
        byte[] bytes = null;
        boolean strict = false;

        // When
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, strict);

        // Then
        assertNull(result, "Encoding null bytes should return null");
    }

    @Test
    void testEncodeQuotedPrintable_EmptyBytes() {
        // Given
        BitSet printable = null;
        byte[] bytes = new byte[0];
        boolean strict = false;

        // When
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, strict);

        // Then
        assertArrayEquals(new byte[0], result, "Encoding empty bytes should return an empty array");
    }

    @Test
    void testEncodeQuotedPrintable_StrictMode() {
        // Given
        BitSet printable = null;
        byte[] bytes = "Hello World".getBytes(StandardCharsets.UTF_8);
        boolean strict = true;

        // When
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, strict);

        // Then
        assertNotNull(result, "Result should not be null");
        // Additional assertions can be added to verify the correctness of the output
    }

    @Test
    void testEncodeQuotedPrintable_NonStrictMode() {
        // Given
        BitSet printable = null;
        byte[] bytes = "Hello World".getBytes(StandardCharsets.UTF_8);
        boolean strict = false;

        // When
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, strict);

        // Then
        assertNotNull(result, "Result should not be null");
        // Additional assertions can be added to verify the correctness of the output
    }
}
```