```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;

import java.math.BigInteger;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Base64_decodeInteger_Test {

    private static final Random RANDOM = new Random();

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

    /**
     * Verifies that a {@link BigInteger} encoded with {@link Base64#encodeInteger(BigInteger)}
     * can be decoded back to the original value using {@link Base64#decodeInteger(byte[])}.
     */
    @Test
    void testDecodeIntegerRoundTrip() {
        // Test a few deterministic values
        BigInteger[] values = new BigInteger[] {
                BigInteger.ZERO,
                BigInteger.ONE,
                new BigInteger("12345678901234567890"),
                new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF", 16) // large positive value
        };

        // Add a few random positive numbers
        for (int i = 0; i < 5; i++) {
            // 128‑bit random positive integer
            byte[] randBytes = new byte[16];
            RANDOM.nextBytes(randBytes);
            // Ensure the sign bit is positive
            randBytes[0] = (byte) (randBytes[0] & 0x7F);
            values = java.util.Arrays.copyOf(values, values.length + 1);
            values[values.length - 1] = new BigInteger(1, randBytes);
        }

        for (BigInteger original : values) {
            // Encode the integer to a Base64 byte array
            byte[] encoded = Base64.encodeInteger(original);
            // Decode back to a BigInteger
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
}
```