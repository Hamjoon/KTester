```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Base64_isBase64_2_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // No per‑test initialization required
    }

    @AfterEach
    void teardownAfterEach() {
        // No per‑test cleanup required
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup required
    }

    /**
     * A {@code null} argument should be considered *not* Base64.
     */
    @Test
    void testIsBase64_NullInput() {
        assertFalse(Base64.isBase64((String) null),
                "null input should return false");
    }

    /**
     * Empty string is not a valid Base64 representation.
     */
    @Test
    void testIsBase64_EmptyString() {
        assertFalse(Base64.isBase64(""),
                "empty string should return false");
    }

    /**
     * Simple, known‑good Base64 string.
     */
    @Test
    void testIsBase64_ValidSimple() {
        // "Man" → "TWFu"
        assertTrue(Base64.isBase64("TWFu"),
                "\"TWFu\" is a valid Base64 string");
    }

    /**
     * Valid Base64 string that contains whitespace characters.
     * Whitespace is ignored by the codec and therefore considered valid.
     */
    @Test
    void testIsBase64_ValidWithWhitespace() {
        // Insert spaces, line‑feeds and tabs arbitrarily
        String withWhiteSpace = " T W F u \n\r\t";
        assertTrue(Base64.isBase64(withWhiteSpace),
                "Base64 string containing whitespace should be accepted");
    }

    /**
     * String containing characters that are not part of the Base64 alphabet.
     */
    @Test
    void testIsBase64_InvalidCharacters() {
        String invalid = "abc$def==";
        assertFalse(Base64.isBase64(invalid),
                "String containing '$' is not valid Base64");
    }

    /**
     * A string that looks like Base64 but has an illegal padding position.
     * The static {@code isBase64} method only checks character validity,
     * not padding rules, so this should still return {@code true}.
     */
    @Test
    void testIsBase64_ValidPaddingOnlyCheck() {
        // Padding character '=' is allowed anywhere the method checks characters
        String oddPadding = "TWFu===";
        assertTrue(Base64.isBase64(oddPadding),
                "Method only validates characters, so odd padding should be accepted");
    }

    /**
     * Verify that URL‑safe Base64 characters are also recognised as valid.
     */
    @Test
    void testIsBase64_UrlSafeAlphabet() {
        // URL‑safe Base64 uses '-' and '_' instead of '+' and '/'
        String urlSafe = "lO2NAxnkSTOZVg-2dATTcA";
        assertTrue(Base64.isBase64(urlSafe),
                "URL‑safe Base64 characters should be accepted");
    }
}
```