```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Base64#decodeInteger(byte[])}.
 *
 * <p>
 * The tests are derived from a JSON specification that groups inputs by their
 * characteristics (valid Base64, whitespace handling, invalid characters,
 * etc.).  Each group is exercised by a dedicated {@code @ParameterizedTest}
 * method that receives the raw byte array and the expected outcome.
 * </p>
 *
 * <p>
 * The existing deterministic round‑trip test and the basic null/empty/invalid
 * checks are retained from the original scaffold.
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class Base64_decodeInteger_Test {

    private static final java.util.Random RANDOM = new java.util.Random();

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // Nothing to initialise before each test
    }

    @AfterEach
    void teardownAfterEach() {
        // Nothing to clean up after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global teardown needed
    }

    /* --------------------------------------------------------------------- */
    /* Existing deterministic round‑trip test                               */
    /* --------------------------------------------------------------------- */

    /**
     * Verifies that a {@link BigInteger} encoded with {@link Base64#encodeInteger(BigInteger)}
     * can be decoded back to the original value using {@link Base64#decodeInteger(byte[])}.
     */
    @Test
    void testDecodeIntegerRoundTrip() {
        // Test a few deterministic values
        BigInteger[] values = new BigInteger[]{
                BigInteger.ZERO,
                BigInteger.ONE,
                new BigInteger("12345678901234567890"),
                new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF", 16) // large positive value
        };

        // Add a few random positive numbers (128‑bit)
        for (int i = 0; i < 5; i++) {
            byte[] randBytes = new byte[16];
            RANDOM.nextBytes(randBytes);
            // Ensure the sign bit is positive
            randBytes[0] = (byte) (randBytes[0] & 0x7F);
            values = java.util.Arrays.copyOf(values, values.length + 1);
            values[values.length - 1] = new BigInteger(1, randBytes);
        }

        for (BigInteger original : values) {
            byte[] encoded = Base64.encodeInteger(original);
            BigInteger decoded = Base64.decodeInteger(encoded);
            assertEquals(original, decoded,
                    () -> "Round‑trip failed for value: " + original.toString(16));
        }
    }

    /**
     * {@code decodeInteger(null)} should throw a {@link NullPointerException}
     * because the underlying {@link Base64#decodeBase64(byte[])} returns {@code null}
     * and {@link BigInteger#BigInteger(int, byte[])} does not accept a {@code null}
     * magnitude.
     */
    @Test
    void testDecodeIntegerNullInput() {
        assertThrows(NullPointerException.class, () -> Base64.decodeInteger(null));
    }

    /**
     * An empty input array results in an empty decoded byte array.
     * Passing that to {@link BigInteger#BigInteger(int, byte[])} causes a
     * {@link NumberFormatException}.
     */
    @Test
    void testDecodeIntegerEmptyInput() {
        assertThrows(NumberFormatException.class, () -> Base64.decodeInteger(new byte[0]));
    }

    /**
     * Verify that malformed Base64 data leads to a {@link IllegalArgumentException}
     * from the decoder. The exact exception type may differ between
     * Commons‑Codec versions, so we assert that *some* runtime exception is thrown.
     */
    @Test
    void testDecodeIntegerInvalidBase64() {
        // Not a valid Base64 representation (contains illegal characters)
        byte[] invalid = "??!!".getBytes();
        assertThrows(RuntimeException.class, () -> Base64.decodeInteger(invalid));
    }

    /* --------------------------------------------------------------------- */
    /* Parameterized tests derived from the JSON specification                */
    /* --------------------------------------------------------------------- */

    // --------------------------------------------------------------------- //
    // 1. Valid Base64 inputs
    // --------------------------------------------------------------------- //

    @ParameterizedTest(name = "[{index}] valid input -> expected {1}")
    @MethodSource("validBase64Provider")
    void testValidBase64Inputs(byte[] input, String expectedDecimal) {
        BigInteger result = Base64.decodeInteger(input);
        BigInteger expected = new BigInteger(expectedDecimal);
        assertEquals(expected, result,
                () -> "Decoded value does not match for input " + java.util.Arrays.toString(input));
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> validBase64Provider() {
        return Stream.of(
                // "AQID" -> {0x01,0x02,0x03} -> 0x010203 = 66051
                org.junit.jupiter.params.provider.Arguments.arguments(
                        toByteArray(65, 81, 73, 68), "66051"),
                // "AQ==" -> {0x01} -> 1
                org.junit.jupiter.params.provider.Arguments.arguments(
                        toByteArray(65, 81, 61, 61), "1"),
                // "AQID " (trailing space) -> same as "AQID"
                org.junit.jupiter.params.provider.Arguments.arguments(
                        toByteArray(65, 81, 73, 68, 32), "66051"),
                // "AQ\r\nID" (CRLF inside) -> same as "AQID"
                org.junit.jupiter.params.provider.Arguments.arguments(
                        toByteArray(65, 81, 13, 10, 73, 68), "66051")
        );
    }

    // --------------------------------------------------------------------- //
    // 2. Invalid / non‑Base64 characters handling
    // --------------------------------------------------------------------- //

    @ParameterizedTest(name = "[{index}] non‑Base64 chars ignored -> expected {1}")
    @MethodSource("nonBase64CharsProvider")
    void testNonBase64CharactersIgnored(byte[] input, String expectedDecimal) {
        BigInteger result = Base64.decodeInteger(input);
        BigInteger expected = new BigInteger(expectedDecimal);
        assertEquals(expected, result,
                () -> "Result differs when non‑Base64 characters are present");
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> nonBase64CharsProvider() {
        return Stream.of(
                // "!AQID@" -> '!' (33) and '@' (64) are ignored, same as "AQID"
                org.junit.jupiter.params.provider.Arguments.arguments(
                        toByteArray(33, 65, 81, 73, 68, 64), "66051"),
                // "AQID\n\t" -> newline (10) and tab (9) are whitespace, ignored
                org.junit.jupiter.params.provider.Arguments.arguments(
                        toByteArray(65, 81, 73, 68, 10, 9), "66051")
        );
    }

    // --------------------------------------------------------------------- //
    // 3. Whitespace and line‑separator variations
    // --------------------------------------------------------------------- //

    @ParameterizedTest(name = "[{index}] whitespace variations -> expected {1}")
    @MethodSource("whitespaceProvider")
    void testWhitespaceVariations(byte[] input, String expectedDecimal) {
        BigInteger result = Base64.decodeInteger(input);
        BigInteger expected = new BigInteger(expectedDecimal);
        assertEquals(expected, result,
                () -> "Whitespace handling failed for input " + java.util.Arrays.toString(input));
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> whitespaceProvider() {
        return Stream.of(
                // Space between characters
                org.junit.jupiter.params.provider.Arguments.arguments(
                        toByteArray(65, 32, 81, 73, 68), "66051"),
                // Multiple CRLF sequences interleaved
                org.junit.jupiter.params.provider.Arguments.arguments(
                        toByteArray(65, 81, 13, 10, 73, 68, 13, 10), "66051")
        );
    }

    // --------------------------------------------------------------------- //
    // 4. Leading zero bytes
    // --------------------------------------------------------------------- //

    @ParameterizedTest(name = "[{index}] leading zero bytes -> expected {1}")
    @MethodSource("leadingZeroProvider")
    void testLeadingZeroBytes(byte[] input, String expectedDecimal) {
        BigInteger result = Base64.decodeInteger(input);
        BigInteger expected = new BigInteger(expectedDecimal);
        assertEquals(expected, result,
                () -> "Leading zero bytes were not ignored correctly");
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> leadingZeroProvider() {
        return Stream.of(
                // "AAE=" -> bytes {0x00,0x01} -> value 1
                org.junit.jupiter.params.provider.Arguments.arguments(
                        toByteArray(65, 65, 69, 61), "1")
        );
    }

    // --------------------------------------------------------------------- //
    // 5. Large integer values
    // --------------------------------------------------------------------- //

    @ParameterizedTest(name = "[{index}] large integer -> expected {1}")
    @MethodSource("largeIntegerProvider")
    void testLargeIntegerValues(byte[] input, String expectedDecimal) {
        BigInteger result = Base64.decodeInteger(input);
        BigInteger expected = new BigInteger(expectedDecimal);
        assertEquals(expected, result,
                () -> "Large integer decoding failed");
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> largeIntegerProvider() {
        return Stream.of(
                // "/////w==" -> four 0xFF bytes -> 2^32‑1
                org.junit.jupiter.params.provider.Arguments.arguments(
                        toByteArray(47, 47, 47, 47, 47, 119, 61, 61), "4294967295"),
                // 16 bytes of 0xFF -> 2^128‑1
                org.junit.jupiter.params.provider.Arguments.arguments(
                        toByteArray(77, 84, 73, 122, 77, 84, 73, 122,
                                   77, 84, 73, 122, 77, 84, 73, 122,
                                   77, 84, 73, 122, 77, 84, 73, 122,
                                   77, 84, 73, 122, 77, 84, 73, 122), 
                        "340282366920938463463374607431768211455")
        );
    }

    // --------------------------------------------------------------------- //
    // Helper method to convert a var‑args int list into a byte[]
    // --------------------------------------------------------------------- //

    /**
     * Utility that creates a {@code byte[]} from a list of unsigned int values
     * (0‑255).  The method is used to keep the test data readable.
     *
     * @param values unsigned byte values
     * @return a freshly allocated byte array
     */
    private static byte[] toByteArray(int... values) {
        byte[] arr = new byte[values.length];
        for (int i = 0; i < values.length; i++) {
            arr[i] = (byte) (values[i] & 0xFF);
        }
        return arr;
    }
}
```