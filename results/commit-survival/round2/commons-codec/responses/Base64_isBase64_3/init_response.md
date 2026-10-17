```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;

/**
 * Unit tests for {@link Base64#isBase64(byte[])}.
 *
 * <p>
 * The tests cover:
 * <ul>
 *   <li>Typical valid Base64 data</li>
 *   <li>Valid Base64 data containing whitespace characters (space, tab, CR, LF)</li>
 *   <li>Invalid characters that are neither Base64 nor whitespace</li>
 *   <li>Empty input array (should be considered valid)</li>
 *   <li>Null input (should throw {@link NullPointerException})</li>
 * </ul>
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class Base64_isBase64_3_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // No per‑test initialization required.
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
     * A simple, well‑known Base64 string: "Hello".
     */
    @Test
    void testValidBase64Bytes() {
        // "SGVsbG8=" is the Base64 representation of "Hello"
        final byte[] base64Bytes = "SGVsbG8=".getBytes(StandardCharsets.US_ASCII);
        assertTrue(Base64.isBase64(base64Bytes), "Standard Base64 bytes should be reported as valid");
    }

    /**
     * Base64 data may contain whitespace characters which should be ignored.
     */
    @Test
    void testValidBase64WithWhitespace() {
        // Insert spaces, tabs, CR and LF into a valid Base64 string.
        final String withWs = "S G V s b G 8 =\n\t\r";
        final byte[] bytes = withWs.getBytes(StandardCharsets.US_ASCII);
        assertTrue(Base64.isBase64(bytes), "Base64 bytes containing whitespace should be reported as valid");
    }

    /**
     * Any byte that is not a Base64 character and not whitespace makes the whole
     * array invalid.
     */
    @Test
    void testInvalidBase64Bytes() {
        // '!' (0x21) is not part of the Base64 alphabet and not whitespace.
        final byte[] invalid = "SGV!sbG8=".getBytes(StandardCharsets.US_ASCII);
        assertFalse(Base64.isBase64(invalid), "Array containing non‑Base64, non‑whitespace characters should be invalid");
    }

    /**
     * An empty array contains no illegal characters, therefore it is considered
     * valid Base64.
     */
    @Test
    void testEmptyArrayIsValid() {
        final byte[] empty = new byte[0];
        assertTrue(Base64.isBase64(empty), "Empty byte array should be considered valid Base64");
    }

    /**
     * Passing {@code null} should result in a {@link NullPointerException}
     * because the method iterates over the array without a null‑check.
     */
    @Test
    void testNullArrayThrowsNPE() {
        assertThrows(NullPointerException.class, () -> Base64.isBase64(null),
                "Calling isBase64 with null should throw NullPointerException");
    }
}
```