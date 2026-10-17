```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)   // kept from the template; not strictly required
class Base32_isInAlphabet_Test {

    /** Instance under test – created fresh for each test method. */
    private Base32 base32;

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources needed for these tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Use the default constructor (standard Base32 alphabet).
        base32 = new Base32();
    }

    @AfterEach
    void teardownAfterEach() {
        // Nothing to clean up – the Base32 instance will be garbage‑collected.
        base32 = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global teardown required.
    }

    /**
     * A byte that is part of the standard Base32 alphabet should return {@code true}.
     * The character {@code 'A'} (0x41) is the first symbol in the alphabet.
     */
    @Test
    void shouldReturnTrueForValidAlphabetByte() {
        byte valid = (byte) 'A'; // 0x41
        assertTrue(base32.isInAlphabet(valid),
                "'A' should be recognised as being in the Base32 alphabet");
    }

    /**
     * A byte that is not part of the Base32 alphabet should return {@code false}.
     * The character {@code '0'} (zero) is not a valid Base32 symbol.
     */
    @Test
    void shouldReturnFalseForInvalidAlphabetByte() {
        byte invalid = (byte) '0'; // 0x30
        assertFalse(base32.isInAlphabet(invalid),
                "'0' should NOT be recognised as being in the Base32 alphabet");
    }

    /**
     * Negative byte values are outside the valid range (the method checks {@code octet >= 0}).
     */
    @Test
    void shouldReturnFalseForNegativeByte() {
        byte negative = (byte) -1; // 0xFF
        assertFalse(base32.isInAlphabet(negative),
                "Negative byte values must be reported as not in the alphabet");
    }

    /**
     * A byte that is within the table bounds but maps to {@code -1} in the decode table
     * (e.g., a whitespace character) must return {@code false}.
     */
    @Test
    void shouldReturnFalseForWhitespaceByte() {
        byte whitespace = (byte) ' '; // space character, 0x20
        assertFalse(base32.isInAlphabet(whitespace),
                "Whitespace characters must be reported as not in the alphabet");
    }

    /**
     * Verify that the method works correctly when the Base32 instance is configured
     * to use the "hex" variant. The hex alphabet contains different symbols, so a
     * character that is valid in the standard alphabet may be invalid in the hex
     * variant (and vice‑versa).
     */
    @Test
    void shouldRespectHexVariantAlphabet() {
        Base32 hexBase32 = new Base32(true); // useHex = true
        // In the hex variant, 'A' is still valid, but 'Z' is not.
        assertTrue(hexBase32.isInAlphabet((byte) 'A'));
        assertFalse(hexBase32.isInAlphabet((byte) 'Z'));
    }
}
```