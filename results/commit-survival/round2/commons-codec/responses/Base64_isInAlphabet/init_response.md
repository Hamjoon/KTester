```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // Mockito not used but kept from template
class Base64_isInAlphabet_Test {

    /** Instance under test */
    private Base64 base64;

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // Use the default constructor – it builds the standard decode table.
        base64 = new Base64();
    }

    @AfterEach
    void teardownAfterEach() {
        base64 = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup required
    }

    /**
     * Verify that a byte representing a character that belongs to the Base64 alphabet
     * returns {@code true}.
     */
    @Test
    void testIsInAlphabet_validAlphabetCharacters() {
        // 'A' (65) and 'z' (122) are both part of the standard Base64 alphabet.
        assertTrue(base64.isInAlphabet((byte) 'A'), "Upper‑case A should be in alphabet");
        assertTrue(base64.isInAlphabet((byte) 'z'), "Lower‑case z should be in alphabet");
        // Digit '0' (48) is also in the alphabet.
        assertTrue(base64.isInAlphabet((byte) '0'), "Digit 0 should be in alphabet");
        // '+' (43) and '/' (47) are the two special symbols.
        assertTrue(base64.isInAlphabet((byte) '+'), "Plus sign should be in alphabet");
        assertTrue(base64.isInAlphabet((byte) '/'), "Slash should be in alphabet");
    }

    /**
     * Verify that the padding character '=' is **not** considered part of the alphabet.
     */
    @Test
    void testIsInAlphabet_paddingCharacter() {
        assertFalse(base64.isInAlphabet((byte) '='), "Padding character '=' must not be in alphabet");
    }

    /**
     * Verify that a negative byte value (i.e., a signed byte less than zero) is rejected.
     */
    @Test
    void testIsInAlphabet_negativeByte() {
        // (byte) -1 is 0xFF, which is negative when interpreted as a signed byte.
        assertFalse(base64.isInAlphabet((byte) -1), "Negative byte values must not be in alphabet");
    }

    /**
     * Verify that a byte whose unsigned value is greater than the decode table length
     * is rejected. The standard decode table length is 128; the value 127 is the highest
     * index that exists, but its entry is {@code -1} (non‑alphabet). Using 127 therefore
     * tests the “out‑of‑alphabet but in‑range” path.
     */
    @Test
    void testIsInAlphabet_outOfRangeOrNonAlphabetByte() {
        // 127 is the highest index in the standard decode table and maps to -1.
        assertFalse(base64.isInAlphabet((byte) 127), "Byte 127 should not be in alphabet (decode table entry is -1)");

        // 0x80 as a signed byte becomes -128, which fails the >=0 check.
        assertFalse(base64.isInAlphabet((byte) 0x80), "Byte 0x80 (signed -128) must be rejected by the >=0 guard");
    }

    /**
     * Verify that a custom Base64 instance created via the builder with a non‑standard
     * encode table still correctly reports alphabet membership for its own table.
     * This indirectly tests that the constructor correctly builds a matching decode table.
     */
    @Test
    void testIsInAlphabet_customBuilder() {
        // Create a URL‑safe instance – its encode table differs, but the alphabet check should still work.
        Base64 urlSafe = Base64.builder()
                               .setUrlSafe(true)
                               .get();

        // In URL‑safe mode, '-' and '_' replace '+' and '/'.
        assertTrue(urlSafe.isInAlphabet((byte) '-'), "URL‑safe '-' should be in alphabet");
        assertTrue(urlSafe.isInAlphabet((byte) '_'), "URL‑safe '_' should be in alphabet");

        // The standard '+' and '/' are no longer part of the URL‑safe alphabet.
        assertFalse(urlSafe.isInAlphabet((byte) '+'), "Standard '+' must not be in URL‑safe alphabet");
        assertFalse(urlSafe.isInAlphabet((byte) '/'), "Standard '/' must not be in URL‑safe alphabet");
    }
}
```