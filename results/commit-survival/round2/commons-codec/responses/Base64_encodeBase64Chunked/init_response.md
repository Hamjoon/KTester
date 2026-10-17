```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Base64_encodeBase64Chunked_Test {

    /* --------------------------------------------------------------------- */
    /*  Lifecycle hooks – nothing special is required for the static method */
    /* --------------------------------------------------------------------- */
    @BeforeAll
    static void setupBeforeAll() {
        // No global resources to initialise.
    }

    @BeforeEach
    void setupBeforeEach() {
        // No per‑test initialisation required.
    }

    @AfterEach
    void teardownAfterEach() {
        // No per‑test cleanup required.
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup required.
    }

    /* --------------------------------------------------------------------- */
    /*  Tests for Base64.encodeBase64Chunked(byte[])                         */
    /* --------------------------------------------------------------------- */

    /**
     * Verify that a {@code null} argument returns {@code null}.
     */
    @Test
    void encodeBase64Chunked_nullInput_returnsNull() {
        assertNull(Base64.encodeBase64Chunked(null), "Encoding null should return null");
    }

    /**
     * Verify that an empty byte array returns an empty byte array.
     */
    @Test
    void encodeBase64Chunked_emptyInput_returnsEmptyArray() {
        byte[] result = Base64.encodeBase64Chunked(new byte[0]);
        assertNotNull(result, "Result must not be null");
        assertEquals(0, result.length, "Result length must be 0 for empty input");
    }

    /**
     * The chunked version should be equivalent to calling {@code encodeBase64(..., true)}.
     */
    @Test
    void encodeBase64Chunked_equivalentToEncodeBase64True() {
        byte[] data = "The quick brown fox jumps over the lazy dog".getBytes(StandardCharsets.UTF_8);
        byte[] expected = Base64.encodeBase64(data, true);
        byte[] actual   = Base64.encodeBase64Chunked(data);
        assertArrayEquals(expected, actual, "Chunked encoding should delegate to encodeBase64(..., true)");
    }

    /**
     * When the encoded output exceeds the default line length (76 characters),
     * a CRLF line separator must be inserted.
     *
     * 57 bytes → 76 Base64 characters (no line break)
     * 58 bytes → 78 Base64 characters (one line break after 76 chars)
     */
    @Test
    void encodeBase64Chunked_insertsLineSeparatorWhenNeeded() {
        // 58 bytes of deterministic data
        byte[] data = new byte[58];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }

        byte[] encoded = Base64.encodeBase64Chunked(data);
        String encodedStr = new String(encoded, StandardCharsets.US_ASCII);

        // Verify that a CRLF appears exactly once
        int firstBreak = encodedStr.indexOf("\r\n");
        assertTrue(firstBreak > 0, "CRLF line separator should be present for >76 encoded chars");

        // Verify that the break occurs after the 76th character
        assertEquals(76, firstBreak, "Line separator must be after 76 Base64 characters");

        // Ensure there is only one line break (total length should be 78 + 2 = 80)
        assertEquals(80, encoded.length, "Encoded length should be 78 data chars + 2 for CRLF");
    }

    /**
     * Encode a large payload and ensure that every 76‑character block is followed
     * by the default CRLF separator.
     */
    @Test
    void encodeBase64Chunked_multipleLineSeparators() {
        // 300 bytes → 400 Base64 characters (since 300 / 3 * 4 = 400)
        byte[] data = new byte[300];
        Arrays.fill(data, (byte) 'A'); // simple repeatable content

        byte[] encoded = Base64.encodeBase64Chunked(data);
        String encodedStr = new String(encoded, StandardCharsets.US_ASCII);

        // Expected number of line breaks: floor(400 / 76) = 5
        int expectedBreaks = 400 / 76; // integer division -> 5
        int actualBreaks = (int) encodedStr.chars().filter(ch -> ch == '\r').count();

        assertEquals(expectedBreaks, actualBreaks,
                "Number of CRLF separators should match the number of full 76‑char blocks");

        // Verify that no line exceeds 76 characters (excluding the CRLF)
        String[] lines = encodedStr.split("\r\n");
        for (String line : lines) {
            assertTrue(line.length() <= 76,
                    "Each line of chunked output must be at most 76 characters");
        }
    }
}
```