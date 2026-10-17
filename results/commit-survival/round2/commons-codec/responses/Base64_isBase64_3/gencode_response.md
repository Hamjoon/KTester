```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Base64#isBase64(byte[])}.
 *
 * <p>
 * The test cases are driven from the JSON specification supplied with the
 * kata. Each logical group of inputs is exercised by a dedicated
 * {@code @ParameterizedTest} (or a single {@code @Test} where only one case
 * exists).  The tests verify that:
 *
 * <ul>
 *   <li>All characters from the standard Base64 alphabet are accepted.</li>
 *   <li>Whitespace (space, tab, CR, LF) is ignored.</li>
 *   <li>Padding character {@code '='} is accepted.</li>
 *   <li>Any non‑Base64, non‑whitespace byte causes a {@code false} result.</li>
 *   <li>An empty array is considered valid.</li>
 *   <li>A {@code null} argument results in {@link NullPointerException}.</li>
 *   <li>High‑bit (negative) bytes are treated as invalid.</li>
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

    /* --------------------------------------------------------------------- */
    /* Helper to convert a String (ASCII) to the byte[] expected by the SUT. */
    private static byte[] toAsciiBytes(String s) {
        return s.getBytes(StandardCharsets.US_ASCII);
    }

    /* --------------------------------------------------------------------- */
    /* --------------------------- Parameterised groups -------------------- */

    @ParameterizedTest(name = "[{index}] {2}")
    @MethodSource("validBase64Provider")
    void testValidBase64Characters(String input, boolean expected, String description) {
        assertEquals(expected, Base64.isBase64(toAsciiBytes(input)), description);
    }

    static Stream<Arguments> validBase64Provider() {
        return Stream.of(
                Arguments.of("TWFu", true,
                        "All bytes are valid Base64 characters (\"Man\" encoded as \"TWFu\")."),
                Arguments.of(
                        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/",
                        true,
                        "Array contains every character from the standard Base64 alphabet.")
        );
    }

    @Test
    void testEmptyArrayIsValid() {
        // An empty array contains no illegal characters, therefore it is considered valid.
        assertTrue(Base64.isBase64(new byte[0]), "Empty byte array should be considered valid Base64");
    }

    @ParameterizedTest(name = "[{index}] whitespace only")
    @ValueSource(strings = {" ", "\t", "\r", "\n", " \t\r\n  \n"})
    void testWhitespaceOnlyIsValid(String ws) {
        // Whitespace characters are ignored by the implementation.
        assertTrue(Base64.isBase64(toAsciiBytes(ws)),
                "Array consisting solely of whitespace should be reported as valid");
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @MethodSource("interleavedWhitespaceProvider")
    void testValidBase64WithInterleavedWhitespace(String input, boolean expected, String description) {
        assertEquals(expected, Base64.isBase64(toAsciiBytes(input)), description);
    }

    static Stream<Arguments> interleavedWhitespaceProvider() {
        return Stream.of(
                Arguments.of("T W F u\n", true,
                        "Valid Base64 \"TWFu\" with spaces and newline interleaved")
        );
    }

    @Test
    void testPaddingCharacterIsAccepted() {
        // Padding character '=' is part of the Base64 spec and considered valid.
        assertTrue(Base64.isBase64(toAsciiBytes("TQ==")),
                "Base64 padding character '=' should be accepted");
    }

    @ParameterizedTest(name = "[{index}] {2}")
    @MethodSource("invalidCharacterProvider")
    void testInvalidBase64Bytes(String input, boolean expected, String description) {
        assertEquals(expected, Base64.isBase64(toAsciiBytes(input)), description);
    }

    static Stream<Arguments> invalidCharacterProvider() {
        return Stream.of(
                Arguments.of("SGV!sbG8=", false,
                        "Byte '!' is not part of the Base64 alphabet nor whitespace"),
                Arguments.of("AB CD!EF", false,
                        "Mix of valid Base64, whitespace and invalid '!' should yield false")
        );
    }

    @Test
    void testNullArrayThrowsNPE() {
        // The method does not guard against null, so a NPE is expected.
        assertThrows(NullPointerException.class, () -> Base64.isBase64(null),
                "Calling isBase64 with null should throw NullPointerException");
    }

    @Test
    void testHighBitNegativeByteIsInvalid() {
        // 0xFF (‑1 as signed byte) is not whitespace and not a Base64 character.
        byte[] highBit = new byte[]{(byte) 0xFF};
        assertFalse(Base64.isBase64(highBit),
                "High‑bit (negative) byte 0xFF should be considered invalid");
    }
}
```