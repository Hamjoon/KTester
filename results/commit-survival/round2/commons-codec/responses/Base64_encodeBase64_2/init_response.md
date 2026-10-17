```java
package org.apache.commons.codec.binary;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link Base64#encodeBase64(byte[], boolean)}.
 *
 * <p>
 * The tests cover:
 * <ul>
 *   <li>Null input handling – the method should return {@code null}.</li>
 *   <li>Empty input – the method should return an empty array.</li>
 *   <li>Non‑chunked encoding – the result must be identical to the three‑argument overload
 *       with {@code urlSafe = false}.</li>
 *   <li>Chunked encoding – the result must contain the default CRLF line separator
 *       (\"\\r\\n\") after every 76 encoded characters and must be identical to the three‑argument
 *       overload with {@code urlSafe = false}.</li>
 * </ul>
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class Base64_encodeBase64_2_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these static tests.
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

    @Test
    void encodeBase64_NullInput_ReturnsNull() {
        assertNull(Base64.encodeBase64(null, false),
                "Encoding a null byte array should return null");
    }

    @Test
    void encodeBase64_EmptyInput_ReturnsEmptyArray() {
        byte[] empty = new byte[0];
        byte[] result = Base64.encodeBase64(empty, false);
        assertNotNull(result, "Result must not be null for empty input");
        assertEquals(0, result.length, "Result array length must be zero for empty input");
    }

    @Test
    void encodeBase64_NonChunked_MatchesThreeArgOverload() {
        byte[] data = "The quick brown fox jumps over the lazy dog".getBytes(StandardCharsets.UTF_8);
        // Expected result using the three‑argument overload (urlSafe = false)
        byte[] expected = Base64.encodeBase64(data, false, false);
        byte[] actual = Base64.encodeBase64(data, false);
        assertArrayEquals(expected, actual, "Non‑chunked encoding should match the three‑argument overload");
    }

    @Test
    void encodeBase64_Chunked_InsertsLineSeparatorsAndMatchesThreeArgOverload() {
        // Create a payload large enough to require more than one line when chunked.
        // 120 bytes → 160 Base64 characters → two line breaks (after 76 chars).
        byte[] data = new byte[120];
        new Random(0x5a5a5a5a).nextBytes(data);

        // Expected result using the three‑argument overload (urlSafe = false)
        byte[] expected = Base64.encodeBase64(data, true, false);
        byte[] actual = Base64.encodeBase64(data, true);
        assertArrayEquals(expected, actual, "Chunked encoding should match the three‑argument overload");

        // Verify that the default CRLF line separator is present.
        String encodedString = new String(actual, StandardCharsets.US_ASCII);
        assertTrue(encodedString.contains("\r\n"),
                "Chunked output must contain the CRLF line separator");
        // Ensure that line breaks occur at the correct interval (every 76 characters).
        // Split on CRLF and verify each line length (except possibly the last).
        String[] lines = encodedString.split("\r\n");
        for (int i = 0; i < lines.length - 1; i++) {
            assertEquals(76, lines[i].length(),
                    "Each chunked line (except the last) must be exactly 76 characters long");
        }
    }
}
```