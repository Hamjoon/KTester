```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Base64#isInAlphabet(byte)}.
 *
 * <p>
 * The test suite covers:
 * <ul>
 *   <li>normal alphabet membership for standard and URL‑safe instances,</li>
 *   <li>boundary conditions (negative values, values beyond the decode table, padding character, etc.),</li>
 *   <li>behaviour when the internal {@code decodeTable} is deliberately set to {@code null}
 *        (expected {@link NullPointerException}).</li>
 * </ul>
 * </p>
 */
@ExtendWith(MockitoExtension.class) // Mockito not required here but kept from the template
class Base64_isInAlphabet_Test {

    /** Instance under test – created fresh for each test method. */
    private Base64 base64;

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Default constructor builds the standard decode table.
        base64 = new Base64();
    }

    @AfterEach
    void teardownAfterEach() {
        base64 = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup required.
    }

    /**
     * Parameterised test that exercises a variety of octet values taken from the
     * supplied JSON test matrix.  Each row contains:
     * <ul>
     *   <li>the octet as an {@code int} (will be cast to {@code byte}),</li>
     *   <li>the expected boolean result,</li>
     *   <li>a short description useful for debugging failures.</li>
     * </ul>
     */
    @ParameterizedTest(name = "{index}: octet={0} ⇒ expected={1} ({2})")
    @MethodSource("provideIsInAlphabetCases")
    void testIsInAlphabet_various(int octet, boolean expected, String description) {
        byte b = (byte) octet; // cast respects Java's signed byte semantics
        if (expected) {
            assertTrue(base64.isInAlphabet(b),
                    () -> "Expected TRUE for octet " + octet + " – " + description);
        } else {
            assertFalse(base64.isInAlphabet(b),
                    () -> "Expected FALSE for octet " + octet + " – " + description);
        }
    }

    /**
     * Supplies the test data for {@link #testIsInAlphabet_various(int, boolean, String)}.
     *
     * The data mirrors the JSON test cases (group “Base64.isInAlphabet”).  The
     * decode table used by the default {@link Base64} instance is the standard
     * RFC‑4648 table, therefore the expected outcomes correspond to the official
     * Base64 alphabet.
     */
    private static Stream<Arguments> provideIsInAlphabetCases() {
        return Stream.of(
                // negative octet – first guard (octet >= 0) fails
                Arguments.of(-5, false, "Octet is negative; should short‑circuit to false"),
                // within range but entry is -1 (e.g., a digit not in alphabet)
                Arguments.of(10, false,
                        "Octet 10 is within table but maps to -1, therefore false"),
                // octet >= decodeTable.length (standard length 128)
                Arguments.of(200, false,
                        "Octet far beyond decode table length; should be false"),
                // valid alphabet character (index 3 corresponds to 'D')
                Arguments.of(3, true,
                        "Octet 3 is part of the alphabet (maps to 'D')"),
                // NUL byte – not part of the alphabet
                Arguments.of(0, false, "Octet 0 (NUL) is not in the alphabet"),
                // 'A' – first upper‑case character in Base64 alphabet
                Arguments.of(65, true, "Octet 65 ('A') is in the alphabet"),
                // '@' – not a Base64 character
                Arguments.of(64, false, "Octet 64 ('@') is not in the alphabet"),
                // 'z' – lower‑case character, valid
                Arguments.of(122, true, "Octet 122 ('z') is in the alphabet"),
                // '+' – standard special symbol
                Arguments.of(43, true, "Octet 43 ('+') is in the alphabet"),
                // DEL (127) – not part of alphabet
                Arguments.of(127, false,
                        "Octet 127 (DEL) is not in the alphabet (entry is -1)"),
                // Minimum byte value – negative, fails first guard
                Arguments.of(-128, false,
                        "Octet -128 is negative; should be false"),
                // -1 (0xFF) – negative when interpreted as signed byte
                Arguments.of(-1, false,
                        "Octet -1 (0xFF) is negative; should be false")
        );
    }

    /**
     * Ensures that the padding character '=' is not considered part of the alphabet.
     */
    @Test
    void testIsInAlphabet_paddingCharacter() {
        assertFalse(base64.isInAlphabet((byte) '='), "Padding character '=' must not be in alphabet");
    }

    /**
     * Validates that a URL‑safe {@link Base64} instance adapts its alphabet
     * correctly (replaces '+' and '/' with '-' and '_').
     */
    @Test
    void testIsInAlphabet_urlSafeInstance() {
        Base64 urlSafe = Base64.builder()
                               .setUrlSafe(true)
                               .get();

        // URL‑safe specific characters.
        assertTrue(urlSafe.isInAlphabet((byte) '-'), "URL‑safe '-' should be in alphabet");
        assertTrue(urlSafe.isInAlphabet((byte) '_'), "URL‑safe '_' should be in alphabet");

        // Standard characters that are *not* part of the URL‑safe alphabet.
        assertFalse(urlSafe.isInAlphabet((byte) '+'), "Standard '+' must not be in URL‑safe alphabet");
        assertFalse(urlSafe.isInAlphabet((byte) '/'), "Standard '/' must not be in URL‑safe alphabet");
    }

    /**
     * Simulates a corrupted internal state where {@code decodeTable} is {@code null}.
     * The method should throw a {@link NullPointerException} when trying to access
     * {@code decodeTable.length}.
     */
    @Test
    void testIsInAlphabet_nullDecodeTable() throws Exception {
        // Use reflection to inject a null decode table.
        Field tableField = Base64.class.getDeclaredField("decodeTable");
        tableField.setAccessible(true);
        tableField.set(base64, null);

        // Any call now triggers NPE because the method accesses decodeTable.length.
        assertThrows(NullPointerException.class,
                () -> base64.isInAlphabet((byte) 0),
                "With a null decodeTable, isInAlphabet should throw NullPointerException");
    }
}
```