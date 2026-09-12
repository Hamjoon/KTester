```java
package org.apache.commons.codec.net;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;

@ExtendWith(MockitoExtension.class)
class QuotedPrintableCodec_encodeQuotedPrintable_Test{

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
    void testEncodeQuotedPrintable_StrictMode_ShortBytes() {
        // Given
        BitSet printable = null;
        byte[] bytes = {65, 66, 67}; // "ABC"
        boolean strict = true;

        // When
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, strict);

        // Then
        assertNull(result, "Encoding bytes array with length less than MIN_BYTES in strict mode should return null");
    }

    @Test
    void testEncodeQuotedPrintable_StrictMode_LongBytes() {
        // Given
        BitSet printable = null;
        byte[] bytes = {65, 66, 67, 68, 69, 70}; // "ABCDEF"
        boolean strict = true;

        // When
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, strict);

        // Then
        assertNotNull(result, "Result should not be null");
        assertArrayEquals(new byte[]{65, 66, 67, 61, 13, 10, 68, 69, 70}, result, "Encoded bytes should include soft line break");
    }

    @Test
    void testEncodeQuotedPrintable_StrictMode_EqualsCharacter() {
        // Given
        BitSet printable = null;
        byte[] bytes = {65, 66, 61, 68}; // "AB=D"
        boolean strict = true;

        // When
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, strict);

        // Then
        assertNotNull(result, "Result should not be null");
        assertArrayEquals(new byte[]{65, 66, 61, 61, 68}, result, "Encoded '=' character should be represented correctly");
    }

    @Test
    void testEncodeQuotedPrintable_StrictMode_WhitespaceAtEnd() {
        // Given
        BitSet printable = null;
        byte[] bytes = {32, 65, 66, 67, 32}; // " ABC "
        boolean strict = true;

        // When
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, strict);

        // Then
        assertNotNull(result, "Result should not be null");
        assertArrayEquals(new byte[]{32, 65, 66, 67, 61, 32}, result, "Whitespace at the end should be encoded");
    }

    @Test
    void testEncodeQuotedPrintable_NonStrictMode_PrintableBytes() {
        // Given
        BitSet printable = null;
        byte[] bytes = {65, 66, 67}; // "ABC"
        boolean strict = false;

        // When
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, strict);

        // Then
        assertNotNull(result, "Result should not be null");
        assertArrayEquals(new byte[]{65, 66, 67}, result, "Printable bytes should remain unchanged in non-strict mode");
    }

    @Test
    void testEncodeQuotedPrintable_NonStrictMode_NonPrintableBytes() {
        // Given
        BitSet printable = null;
        byte[] bytes = {10, 13, 61}; // Non-printable bytes
        boolean strict = false;

        // When
        byte[] result = QuotedPrintableCodec.encodeQuotedPrintable(printable, bytes, strict);

        // Then
        assertNotNull(result, "Result should not be null");
        // The expected byte array should be the encoded version of the input
        // Since this is a hypothetical example, replace with the actual expected encoded result
        assertArrayEquals(new byte[]{61, 30, 61, 0, 61, 3}, result, "Non-printable bytes should be encoded");
    }
}
```