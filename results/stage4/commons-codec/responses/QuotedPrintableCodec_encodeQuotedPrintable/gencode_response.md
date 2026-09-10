```java
package org.apache.commons.codec.net;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import java.util.BitSet;
import java.io.UnsupportedEncodingException;

@ExtendWith(MockitoExtension.class)
class QuotedPrintableCodec_encodeQuotedPrintable_Test {

    private QuotedPrintableCodec codec;
    private BitSet printable;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the QuotedPrintableCodec before each test
        codec = new QuotedPrintableCodec();
        printable = new BitSet(256);
        // Set up printable characters if needed
        for (int i = 32; i < 127; i++) { // ASCII printable characters
            printable.set(i);
        }
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if needed
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }

    @Test
    void testEncodeQuotedPrintableWithNullInput() {
        // Test case when both printable and bytes are null; should return null.
        byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, null, false);
        assertNull(encoded, "Encoding null bytes with null BitSet should return null");
    }

    @Test
    void testEncodeQuotedPrintableWithEmptyByteArray() {
        // Test case with an empty byte array and strict mode; should return null.
        byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, new byte[]{}, true);
        assertNull(encoded, "Encoding empty byte array in strict mode should return null");
    }

    @Test
    void testEncodeQuotedPrintableWithShortByteArrayInStrictMode() {
        // Test case with a byte array shorter than MIN_BYTES in strict mode; should return null.
        byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, new byte[]{1, 2}, true);
        assertNull(encoded, "Encoding byte array shorter than MIN_BYTES in strict mode should return null");
    }

    @Test
    void testEncodeQuotedPrintableWithValidEncodingInStrictMode() {
        // Test case with a valid byte array in strict mode; should return the same byte array as all are printable.
        byte[] input = new byte[]{65, 66, 67, 68, 69, 70}; // A, B, C, D, E, F
        byte[] expected = new byte[]{65, 66, 67, 68, 69, 70}; // Expecting same as input
        byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, input, true);
        assertArrayEquals(expected, encoded, "Encoding valid byte array in strict mode failed");
    }

    @Test
    void testEncodeQuotedPrintableWithNonPrintableCharacters() {
        // Test case with non-printable characters; should return encoded bytes.
        byte[] input = new byte[]{9, 10, 11, 12, 13, 14}; // Non-printable characters
        byte[] expected = new byte[]{61, 9, 10, 61, 12, 61, 13, 14}; // Expected encoded output
        byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, input, true);
        assertArrayEquals(expected, encoded, "Encoding non-printable characters failed");
    }

    @Test
    void testEncodeQuotedPrintableWithSoftLineBreaks() {
        // Test case to check soft line breaks after exceeding SAFE_LENGTH; should return encoded bytes with line breaks.
        byte[] input = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80}; // A to P
        byte[] expected = new byte[]{65, 66, 67, 68, 69, 70, 61, 13, 10, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80}; // Expecting soft line break
        byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, input, true);
        assertArrayEquals(expected, encoded, "Encoding with soft line breaks failed");
    }

    @Test
    void testEncodeQuotedPrintableWithNonPrintableCharactersInNonStrictMode() {
        // Test case with non-printable byte in non-strict mode; should return bytes with the non-printable byte encoded.
        byte[] input = new byte[]{65, 66, 67, 255}; // A, B, C, and a non-printable character
        byte[] expected = new byte[]{65, 66, 67, 61, 255}; // Expecting last byte to be encoded
        byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, input, false);
        assertArrayEquals(expected, encoded, "Encoding with non-printable characters in non-strict mode failed");
    }

    @Test
    void testEncodeQuotedPrintableWithBoundaryCaseMinimumBytes() {
        // Test case with a byte array exactly equal to MIN_BYTES in strict mode; should return the same byte array as all are printable.
        byte[] input = new byte[]{65, 66, 67}; // A, B, C
        byte[] expected = new byte[]{65, 66, 67}; // Expecting same as input
        byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, input, true);
        assertArrayEquals(expected, encoded, "Encoding with minimum bytes in strict mode failed");
    }

    @Test
    void testEncodeQuotedPrintableHandlingTrailingWhitespaceInStrictMode() {
        // Test case with trailing whitespace in strict mode; should return encoded bytes for trailing whitespace.
        byte[] input = new byte[]{65, 66, 67, 32, 32}; // A, B, C, space, space
        byte[] expected = new byte[]{65, 66, 67, 61, 32, 61, 32}; // Expecting spaces to be encoded
        byte[] encoded = QuotedPrintableCodec.encodeQuotedPrintable(null, input, true);
        assertArrayEquals(expected, encoded, "Encoding with trailing whitespace in strict mode failed");
    }

    @Test
    void testEncodeQuotedPrintableHandlingUnsupportedCharsetException() {
        // Test case when an unsupported charset is specified; should throw UnsupportedEncodingException.
        // Since charset handling is not explicitly tested in this method, we will skip this test.
    }

    @Test
    void testEncodeQuotedPrintableHandlingIllegalCharsetNameException() {
        // Test case when an illegal charset name is specified; should throw IllegalCharsetNameException.
        // Since charset handling is not explicitly tested in this method, we will skip this test.
    }

    @Test
    void testEncodeQuotedPrintableHandlingDecoderException() {
        // Test case when decoding results in an invalid byte; should throw DecoderException.
        // Since this method does not involve decoding, we will skip this test.
    }
}
```