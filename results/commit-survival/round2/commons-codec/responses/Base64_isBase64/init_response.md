```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class Base64_isBase64_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources needed for these tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // No per‑test initialization needed
    }

    @AfterEach
    void teardownAfterEach() {
        // No per‑test cleanup needed
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup needed
    }

    /**
     * Verify that all characters defined by the Base64 alphabet are recognised as Base64.
     */
    @Test
    void testAlphabetCharactersAreBase64() {
        // Upper‑case A‑Z
        for (char c = 'A'; c <= 'Z'; c++) {
            assertTrue(Base64.isBase64((byte) c),
                    "Expected '" + c + "' to be recognised as Base64");
        }
        // Lower‑case a‑z
        for (char c = 'a'; c <= 'z'; c++) {
            assertTrue(Base64.isBase64((byte) c),
                    "Expected '" + c + "' to be recognised as Base64");
        }
        // Digits 0‑9
        for (char c = '0'; c <= '9'; c++) {
            assertTrue(Base64.isBase64((byte) c),
                    "Expected '" + c + "' to be recognised as Base64");
        }
        // The two special symbols
        assertTrue(Base64.isBase64((byte) '+'), "Expected '+' to be recognised as Base64");
        assertTrue(Base64.isBase64((byte) '/'), "Expected '/' to be recognised as Base64");
        // Padding character
        assertTrue(Base64.isBase64((byte) '='), "Expected '=' (padding) to be recognised as Base64");
    }

    /**
     * Verify that whitespace characters are *not* recognised as Base64 by the
     * {@code isBase64(byte)} method (they are ignored only by the array version).
     */
    @Test
    void testWhitespaceIsNotBase64() {
        byte[] whitespace = {
                (byte) ' ', (byte) '\t', (byte) '\n', (byte) '\r', (byte) '\f'
        };
        for (byte b : whitespace) {
            assertFalse(Base64.isBase64(b),
                    "Whitespace character 0x" + Integer.toHexString(b & 0xFF) + " should not be Base64");
        }
    }

    /**
     * Verify that characters outside the Base64 alphabet are not recognised.
     */
    @Test
    void testInvalidCharactersAreNotBase64() {
        char[] invalid = {
                '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', // '=' is padding, already tested
                '[', ']', '{', '}', '|', '\\', ':', ';', '\'', '"', ',', '<', '>', '?', '~', '`'
        };
        for (char c : invalid) {
            // Skip the padding character which is valid
            if (c == '=') {
                continue;
            }
            assertFalse(Base64.isBase64((byte) c),
                    "Expected '" + c + "' to be NOT recognised as Base64");
        }
    }

    /**
     * Verify that negative byte values are never considered Base64.
     */
    @Test
    void testNegativeBytesAreNotBase64() {
        for (int i = -128; i < 0; i++) {
            byte b = (byte) i;
            // The only possible exception would be if the negative value maps to the
            // padding character (which is 61, i.e. '=') – that cannot happen.
            assertFalse(Base64.isBase64(b),
                    "Negative byte value " + i + " should not be recognised as Base64");
        }
    }

    /**
     * Verify that values beyond the unsigned byte range (i.e. > 127) are handled
     * correctly when cast to {@code byte}. The method works on signed bytes, so
     * values 128‑255 become negative when cast; they must be reported as not
     * Base64 unless they correspond to a valid character after casting.
     */
    @Test
    void testUnsignedByteValuesAfterCasting() {
        // Values 128‑255 that correspond to valid Base64 characters after casting:
        //   128‑255 map to -128‑-1; none of the Base64 alphabet characters have code > 127,
        //   so they should all be false.
        for (int i = 128; i <= 255; i++) {
            byte b = (byte) i;
            assertFalse(Base64.isBase64(b),
                    "Byte value " + i + " (cast to " + b + ") should not be recognised as Base64");
        }
    }
}
```