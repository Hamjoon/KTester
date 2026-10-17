```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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

    /**
     * Zero should be encoded as an empty byte array because its bit length is 0.
     */
    @Test
    void testZero() {
        final BigInteger zero = BigInteger.ZERO;
        final byte[] result = Base64.toIntegerBytes(zero);
        assertNotNull(result);
        assertEquals(0, result.length, "Zero should produce an empty byte array");
    }

    /**
     * Positive value whose bit length is not a multiple of 8.
     * The method should return the raw {@code toByteArray()} representation.
     */
    @Test
    void testPositiveNonByteAligned() {
        // 0x123 = 291 decimal, bit length = 9 (not a multiple of 8)
        final BigInteger value = new BigInteger("123", 16);
        final byte[] expected = value.toByteArray(); // should be {0x01, 0x23}
        final byte[] actual = Base64.toIntegerBytes(value);
        assertArrayEquals(expected, actual, "Non‑aligned positive value should be returned unchanged");
    }

    /**
     * Positive value whose bit length *is* a multiple of 8.
     * The sign byte added by {@code BigInteger.toByteArray()} must be stripped.
     */
    @Test
    void testPositiveByteAligned() {
        // 0xFF = 255 decimal, bit length = 8 (multiple of 8)
        final BigInteger value = new BigInteger("FF", 16);
        // toByteArray() yields {0x00, (byte)0xFF} because of the sign bit
        final byte[] expected = new byte[] { (byte) 0xFF };
        final byte[] actual = Base64.toIntegerBytes(value);
        assertArrayEquals(expected, actual, "Byte‑aligned positive value should have sign byte removed");
    }

    /**
     * Negative value – the method is expected to return the raw byte representation
     * (the sign byte is part of the magnitude representation).
     */
    @Test
    void testNegativeValue() {
        // -1 has a magnitude of 1 (bit length = 1)
        final BigInteger value = BigInteger.valueOf(-1);
        final byte[] expected = value.toByteArray(); // should be { (byte)0xFF }
        final byte[] actual = Base64.toIntegerBytes(value);
        assertArrayEquals(expected, actual, "Negative values should be returned unchanged");
    }

    /**
     * Very large integer that requires padding to the next byte boundary.
     * Verify that the method pads with leading zeros correctly.
     */
    @Test
    void testLargeIntegerWithPadding() {
        // Construct a number with a bit length of 15 (e.g., 0x7FFF)
        final BigInteger value = new BigInteger("7FFF", 16);
        // bitLength = 15, rounded up to 16 bits => 2 bytes expected
        // toByteArray() for this positive value yields {0x7F, (byte)0xFF}
        final byte[] expected = new byte[] { 0x7F, (byte) 0xFF };
        final byte[] actual = Base64.toIntegerBytes(value);
        assertArrayEquals(expected, actual, "Large integer should be padded to the next full byte");
    }

    /**
     * Passing {@code null} should result in a {@link NullPointerException}.
     */
    @Test
    void testNullArgument() {
        assertThrows(NullPointerException.class, () -> Base64.toIntegerBytes(null));
    }
}
```