```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@code Base64.toIntegerBytes(BigInteger)}.
 *
 * <p>
 * The method converts a {@link BigInteger} to a byte array without the sign‑bit.
 * The test suite covers the following scenarios (derived from the specification
 * and the JSON test‑case description):
 * <ul>
 *   <li>Zero value</li>
 *   <li>Positive values that are not byte‑aligned (bit length not a multiple of 8)</li>
 *   <li>Positive values that are byte‑aligned (sign byte must be stripped)</li>
 *   <li>Negative values that are not byte‑aligned (no sign‑bit removal)</li>
 *   <li>Negative values that are byte‑aligned (sign byte must be stripped)</li>
 *   <li>Positive values that need a leading sign byte (e.g. 128)</li>
 *   <li>Null argument (should raise {@link NullPointerException})</li>
 * </ul>
 * </p>
 *
 * The test class resides in the same package as {@code Base64}, therefore the
 * package‑private static method can be accessed directly without reflection.
 */
@ExtendWith(MockitoExtension.class)
class Base64_toIntegerBytes_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources needed for these tests
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

    /* --------------------------------------------------------------------- */
    /*                     Individual test cases (one per group)            */
    /* --------------------------------------------------------------------- */

    /**
     * Zero should be encoded as an empty byte array because its bit length is 0.
     */
    @Test
    void testZeroValue() {
        final BigInteger zero = BigInteger.ZERO;
        final byte[] result = Base64.toIntegerBytes(zero);
        assertNotNull(result, "Result must not be null");
        assertEquals(0, result.length, "Zero should produce an empty byte array");
    }

    /**
     * Positive value whose bit length is not a multiple of 8.
     * The method should return the raw {@code toByteArray()} representation.
     */
    @Test
    void testNonByteAlignedPositiveInteger() {
        // 291 decimal == 0x0123, bit length = 9 (not a multiple of 8)
        final BigInteger value = new BigInteger("123", 16);
        final byte[] expected = value.toByteArray(); // {0x01, 0x23}
        final byte[] actual = Base64.toIntegerBytes(value);
        assertArrayEquals(expected, actual,
                "Non‑aligned positive value should be returned unchanged");
    }

    /**
     * Positive value whose bit length is exactly a multiple of 8.
     * The leading sign byte added by {@code BigInteger.toByteArray()} must be stripped.
     */
    @Test
    void testByteAlignedPositiveInteger() {
        // 255 decimal == 0xFF, bit length = 8
        final BigInteger value = new BigInteger("FF", 16);
        // toByteArray() yields {0x00, (byte)0xFF}
        final byte[] expected = new byte[]{(byte) 0xFF};
        final byte[] actual = Base64.toIntegerBytes(value);
        assertArrayEquals(expected, actual,
                "Byte‑aligned positive value should have sign byte removed");
    }

    /**
     * Positive value that is byte‑aligned but whose most‑significant bit is 1.
     * This forces {@code BigInteger} to emit a leading zero sign byte which must be stripped.
     */
    @Test
    void testPositiveByteAlignedWithLeadingSignByte128() {
        // 128 decimal == 0x80, bit length = 8
        final BigInteger value = new BigInteger("80", 16);
        // toByteArray() yields {0x00, (byte)0x80}
        final byte[] expected = new byte[]{(byte) 0x80}; // signed -128
        final byte[] actual = Base64.toIntegerBytes(value);
        assertArrayEquals(expected, actual,
                "Byte‑aligned positive value with leading sign byte should be stripped");
    }

    /**
     * Negative value whose magnitude needs 7 bits (not byte‑aligned).
     * The method should return the raw {@code toByteArray()} representation.
     */
    @Test
    void testNonByteAlignedNegativeInteger() {
        // -128 decimal requires 7 bits
        final BigInteger value = BigInteger.valueOf(-128);
        final byte[] expected = value.toByteArray(); // { (byte)0x80 }
        final byte[] actual = Base64.toIntegerBytes(value);
        assertArrayEquals(expected, actual,
                "Non‑aligned negative value should be returned unchanged");
    }

    /**
     * Negative value that is exactly byte‑aligned.
     * The sign byte added by {@code BigInteger.toByteArray()} must be stripped.
     */
    @Test
    void testByteAlignedNegativeInteger() {
        // -255 decimal -> magnitude 255 (0xFF), bit length = 8
        final BigInteger value = new BigInteger("-255");
        // toByteArray() yields { (byte)0xFF, (byte)0x01 }
        final byte[] expected = new byte[]{1}; // after stripping sign byte
        final byte[] actual = Base64.toIntegerBytes(value);
        assertArrayEquals(expected, actual,
                "Byte‑aligned negative value should have its leading sign byte removed");
    }

    /**
     * Passing {@code null} should result in a {@link NullPointerException}.
     */
    @Test
    void testNullArgument() {
        assertThrows(NullPointerException.class,
                () -> Base64.toIntegerBytes(null),
                "Null argument must trigger NullPointerException");
    }

    /* --------------------------------------------------------------------- */
    /*                Parameterized test covering a few more cases           */
    /* --------------------------------------------------------------------- */

    /**
     * Parameterized test that exercises a handful of additional numbers,
     * confirming that the method behaves consistently for a range of inputs.
     */
    @ParameterizedTest(name = "{index} ⇒ bigInt={0}, expected={1}")
    @MethodSource("provideIntegersForParameterizedTest")
    void testVariousIntegers(BigInteger input, byte[] expected) {
        byte[] actual = Base64.toIntegerBytes(input);
        assertArrayEquals(expected, actual,
                () -> "Failed for input: " + input);
    }

    private static Stream<Arguments> provideIntegersForParameterizedTest() {
        return Stream.of(
                // Zero (already covered, kept for completeness)
                Arguments.of(BigInteger.ZERO, new byte[0]),
                // Positive non‑aligned (291)
                Arguments.of(new BigInteger("123", 16), new byte[]{0x01, 0x23}),
                // Positive byte‑aligned (255)
                Arguments.of(new BigInteger("FF", 16), new byte[]{(byte) 0xFF}),
                // Positive 128 (requires sign‑byte stripping)
                Arguments.of(new BigInteger("80", 16), new byte[]{(byte) 0x80}),
                // Negative non‑aligned (-128)
                Arguments.of(BigInteger.valueOf(-128), new byte[]{(byte) 0x80}),
                // Negative byte‑aligned (-255)
                Arguments.of(new BigInteger("-255"), new byte[]{1})
        );
    }
}
```