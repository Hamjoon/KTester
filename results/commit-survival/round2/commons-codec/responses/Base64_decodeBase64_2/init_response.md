```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Base64 as JdkBase64; // for building expected values

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Base64_decodeBase64_2_Test {

    /** Sample plain‑text used in several tests. */
    private static final String FOX_TEXT = "The quick brown fox jumped over the lazy dogs.";

    /** Its Base64 representation (standard, not URL‑safe). */
    private static final String FOX_BASE64 = "VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wZWQgb3ZlciB0aGUgbGF6eSBkb2dzLg==";

    /** A Base64 string that contains URL‑safe characters (‘-’, ‘_’) and no padding. */
    private static final String URL_SAFE_BASE64 = "VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wZWQgb3ZlciB0aGUgbGF6eSBkb2dzLg";

    /** A Base64 string that mixes valid characters with obvious noise. */
    private static final String NOISY_BASE64 = "!!!VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wZWQgb3ZlciB0aGUgbGF6eSBkb2dzLg==???";

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests.
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

    /**
     * Verify that a well‑formed Base64 string is decoded to the original byte array.
     */
    @Test
    void testDecodeValidBase64String() {
        byte[] expected = FOX_TEXT.getBytes(StandardCharsets.UTF_8);
        byte[] actual = Base64.decodeBase64(FOX_BASE64);
        assertArrayEquals(expected, actual, "Decoded bytes should match original UTF‑8 bytes");
    }

    /**
     * Verify that an empty string yields an empty byte array.
     */
    @Test
    void testDecodeEmptyString() {
        byte[] actual = Base64.decodeBase64("");
        assertNotNull(actual, "Result should not be null for empty input");
        assertEquals(0, actual.length, "Resulting array should be empty");
    }

    /**
     * Verify that a {@code null} argument results in {@code null}.
     * The static helper delegates to {@link BaseNCodec#decode(String)} which returns {@code null}
     * for a {@code null} input.
     */
    @Test
    void testDecodeNullString() {
        assertNull(Base64.decodeBase64((String) null), "Decoding null should return null");
    }

    /**
     * Verify that the static method tolerates non‑Base64 characters (lenient mode).
     * Noise characters are ignored and the remaining valid payload is decoded.
     */
    @Test
    void testDecodeLenientIgnoresNoise() {
        byte[] expected = FOX_TEXT.getBytes(StandardCharsets.UTF_8);
        byte[] actual = Base64.decodeBase64(NOISY_BASE64);
        assertArrayEquals(expected, actual, "Noise characters should be ignored in lenient mode");
    }

    /**
     * Verify that URL‑safe Base64 strings (no padding, using ‘-’ and ‘_’) are decoded correctly.
     */
    @Test
    void testDecodeUrlSafeString() {
        byte[] expected = FOX_TEXT.getBytes(StandardCharsets.UTF_8);
        byte[] actual = Base64.decodeBase64(URL_SAFE_BASE64);
        assertArrayEquals(expected, actual, "URL‑safe Base64 should decode to the original bytes");
    }

    /**
     * Verify that decoding a string with illegal padding does not throw but returns the best‑effort result.
     * The underlying codec is lenient, so excess padding is ignored.
     */
    @Test
    void testDecodeWithIllegalPadding() {
        String withExtraPadding = FOX_BASE64 + "====";
        byte[] expected = FOX_TEXT.getBytes(StandardCharsets.UTF_8);
        byte[] actual = Base64.decodeBase64(withExtraPadding);
        assertArrayEquals(expected, actual, "Extra padding should be ignored in lenient mode");
    }
}
```