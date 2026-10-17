```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Base64#encodeBase64URLSafeString(byte[])}.
 *
 * The tests cover the scenarios described in the supplied JSON test matrix:
 * <ul>
 *   <li>Null input – should throw {@link NullPointerException}.</li>
 *   <li>Empty input – should return an empty {@link String} (not {@code null}).</li>
 *   <li>Standard encoding without padding.</li>
 *   <li>Padding removal for 1‑ and 2‑byte inputs.</li>
 *   <li>URL‑safe character replacement ('+' → '-', '/' → '_').</li>
 *   <li>Input immutability – the source byte array must not be altered.</li>
 *   <li>No padding character ('=') appears in the result.</li>
 *   <li>Large input length verification (no line breaks, expected length).</li>
 * </ul>
 */
@ExtendWith(MockitoExtension.class)
class Base64_encodeBase64URLSafeString_Test {

    /* --------------------------------------------------------------------- */
    /*  Lifecycle hooks – kept for completeness; no state is needed          */
    /* --------------------------------------------------------------------- */
    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for static Base64 methods.
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
    /*  Test data providers                                                   */
    /* --------------------------------------------------------------------- */

    /**
     * Provider for normal (non‑null) test cases.
     * Each {@code Arguments} contains: input byte array, expected string,
     * and a description used only for readability.
     */
    static Stream<org.junit.jupiter.params.provider.Arguments> normalCases() {
        return Stream.of(
                // Empty input
                org.junit.jupiter.params.provider.Arguments.of(
                        new byte[0],
                        "",
                        "Empty array → empty string"
                ),
                // Standard encoding without padding ("abc")
                org.junit.jupiter.params.provider.Arguments.of(
                        new byte[]{'a', 'b', 'c'},
                        "YWJj",
                        "3‑byte input without padding"
                ),
                // Padding removal – single byte "a"
                org.junit.jupiter.params.provider.Arguments.of(
                        new byte[]{'a'},
                        "YQ",
                        "Single byte, padding removed"
                ),
                // Padding removal – two bytes "ab"
                org.junit.jupiter.params.provider.Arguments.of(
                        new byte[]{'a', 'b'},
                        "YWIt",
                        "Two bytes, padding removed"
                ),
                // URL‑safe replacement – bytes 0xFB,0xEF,0xFF ("++//" → "--__")
                org.junit.jupiter.params.provider.Arguments.of(
                        new byte[]{(byte) 0xFB, (byte) 0xEF, (byte) 0xFF},
                        "--__",
                        "Three bytes containing '+' and '/'"
                ),
                // Single byte 0xFF → "/w==" → "_w"
                org.junit.jupiter.params.provider.Arguments.of(
                        new byte[]{(byte) 0xFF},
                        "_w",
                        "Single byte 0xFF, URL‑safe conversion"
                ),
                // Single byte 0xFA → "+g==" → "-g"
                org.junit.jupiter.params.provider.Arguments.of(
                        new byte[]{(byte) 0xFA},
                        "-g",
                        "Single byte 0xFA, URL‑safe conversion"
                ),
                // Two bytes 0xFF,0xFF → "//8=" → "__8"
                org.junit.jupiter.params.provider.Arguments.of(
                        new byte[]{(byte) 0xFF, (byte) 0xFF},
                        "__8",
                        "Two bytes 0xFF 0xFF, URL‑safe conversion"
                ),
                // No padding presence – four bytes
                org.junit.jupiter.params.provider.Arguments.of(
                        new byte[]{1, 2, 3, 4},
                        "AQIDBA",
                        "Four bytes, padding stripped"
                ),
                // Known UUID bytes from the original test suite
                org.junit.jupiter.params.provider.Arguments.of(
                        new byte[]{
                                (byte) 0x92, (byte) 0xED, (byte) 0x8D, (byte) 0x03,
                                (byte) 0x19, (byte) 0xE4, (byte) 0x49, (byte) 0x33,
                                (byte) 0x99, (byte) 0x55, (byte) 0x86, (byte) 0xFB,
                                (byte) 0x67, (byte) 0x40, (byte) 0x4D, (byte) 0x37
                        },
                        "lO2NAxnkSTOZVg-2dATTcA",
                        "UUID‑style bytes containing URL‑unsafe characters"
                )
        );
    }

    /**
     * Provider for the large‑input length verification case.
     */
    static Stream<org.junit.jupiter.params.provider.Arguments> largeInputCase() {
        byte[] data = new byte[32];
        for (int i = 0; i < 32; i++) {
            data[i] = (byte) i;
        }
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(data, 44,
                        "32 sequential bytes → length ((32+2)/3)*4 = 44")
        );
    }

    /* --------------------------------------------------------------------- */
    /*  Test methods                                                         */
    /* --------------------------------------------------------------------- */

    /**
     * Verifies that a {@code null} argument leads to a {@link NullPointerException}
     * because the underlying {@code StringUtils.newStringUsAscii(null)} call cannot
     * handle a {@code null} byte array.
     */
    @Test
    void encodeBase64URLSafeString_nullInput_throwsNPE() {
        assertThrows(NullPointerException.class,
                () -> Base64.encodeBase64URLSafeString(null),
                "Passing null should throw NullPointerException");
    }

    /**
     * Runs through the normal cases defined in {@link #normalCases()}.
     * Each case checks that the returned string matches the expected URL‑safe
     * Base64 representation.
     */
    @ParameterizedTest(name = "{2}")
    @MethodSource("normalCases")
    void encodeBase64URLSafeString_variousInputs(byte[] input, String expected, String description) {
        String actual = Base64.encodeBase64URLSafeString(input);
        assertEquals(expected, actual, description);
    }

    /**
     * Confirms that the method does not modify the supplied byte array.
     * The test uses a fresh copy of the original data, calls the method,
     * and then checks the source array for unexpected changes.
     */
    @Test
    void encodeBase64URLSafeString_inputArrayIsImmutable() {
        byte[] original = new byte[]{1, 2, 3};
        byte[] copy = original.clone();

        // Invoke the method – result is irrelevant for this test
        Base64.encodeBase64URLSafeString(original);

        assertArrayEquals(copy, original, "The input byte array must remain unchanged");
    }

    /**
     * Ensures that the returned string never contains the padding character '='.
     */
    @Test
    void encodeBase64URLSafeString_resultContainsNoPadding() {
        byte[] data = new byte[]{1, 2, 3, 4}; // produces padding in ordinary Base64
        String result = Base64.encodeBase64URLSafeString(data);
        assertFalse(result.contains("="), "URL‑safe output must not contain padding '=' characters");
    }

    /**
     * Verifies the length of the encoded result for a 32‑byte input.
     * Expected length is ((32 + 2) / 3) * 4 = 44 characters, and the output
     * must be a single line (no CR/LF characters).
     */
    @ParameterizedTest(name = "{2}")
    @MethodSource("largeInputCase")
    void encodeBase64URLSafeString_largeInputLength(byte[] input, int expectedLength, String description) {
        String result = Base64.encodeBase64URLSafeString(input);
        assertEquals(expectedLength, result.length(),
                "Encoded length does not match expected for large input");
        assertFalse(result.contains("\r") || result.contains("\n"),
                "Result must not contain line breaks");
    }
}
```