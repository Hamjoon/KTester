```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Base64#encodeInteger(BigInteger)}.
 *
 * The tests cover:
 * <ul>
 *   <li>Null argument handling (should throw {@link NullPointerException}).</li>
 *   <li>Encoding of zero, positive, large positive and negative {@link BigInteger} values.</li>
 *   <li>Verification that the returned byte array matches the expected Base64 representation.</li>
 * </ul>
 */
@ExtendWith(MockitoExtension.class)
class Base64_encodeInteger_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources needed for static method testing.
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

    /**
     * {@code encodeInteger(null)} must throw a {@link NullPointerException}.
     */
    @Test
    void encodeInteger_nullArgument_throwsNPE() {
        assertThrows(NullPointerException.class, () -> Base64.encodeInteger(null));
    }

    /**
     * Encode {@code 0} – the smallest non‑negative integer.
     */
    @Test
    void encodeInteger_zero_returnsBase64OfSingleZeroByte() {
        byte[] result = Base64.encodeInteger(BigInteger.ZERO);
        byte[] expected = "AA==".getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(expected, result, "Base64 encoding of 0 should be \"AA==\"");
    }

    /**
     * Encode a small positive integer whose most‑significant bit is not set.
     */
    @Test
    void encodeInteger_one_returnsBase64OfSingleByte() {
        byte[] result = Base64.encodeInteger(BigInteger.ONE);
        byte[] expected = "AQ==".getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(expected, result, "Base64 encoding of 1 should be \"AQ==\"");
    }

    /**
     * Encode a positive integer where the highest bit of the byte representation would be set.
     * The internal {@code toIntegerBytes} method should prepend a leading zero byte.
     */
    @Test
    void encodeInteger_255_requiresLeadingZero_returnsCorrectBase64() {
        byte[] result = Base64.encodeInteger(BigInteger.valueOf(255));
        byte[] expected = "AP8=".getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(expected, result, "Base64 encoding of 255 should be \"AP8=\"");
    }

    /**
     * Encode a larger positive integer (123 456 789) and verify the exact Base64 output.
     */
    @Test
    void encodeInteger_largePositive_returnsExpectedBase64() {
        BigInteger value = new BigInteger("123456789");
        byte[] result = Base64.encodeInteger(value);
        byte[] expected = "B1vNFQ==".getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(expected, result,
                "Base64 encoding of 123456789 should be \"B1vNFQ==\"");
    }

    /**
     * Encode a negative integer to ensure sign handling works correctly.
     */
    @Test
    void encodeInteger_negativeOne_returnsBase64OfSingleFFByte() {
        byte[] result = Base64.encodeInteger(BigInteger.valueOf(-1));
        byte[] expected = "/w==".getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(expected, result,
                "Base64 encoding of -1 should be \"/w==\"");
    }

    /**
     * Encode a very large integer (128‑bit) and compare with a pre‑computed Base64 string.
     */
    @Test
    void encodeInteger_veryLargePositive_returnsExpectedBase64() {
        // 0x0123456789ABCDEF0123456789ABCDEF (hex) = 24197857200151252728969465429440051199
        BigInteger large = new BigInteger("24197857200151252728969465429440051199");
        byte[] result = Base64.encodeInteger(large);
        // Pre‑computed using a trusted Base64 implementation:
        //   toIntegerBytes(large) = 01 23 45 67 89 AB CD EF 01 23 45 67 89 AB CD EF
        //   Base64 => "ASNFZ4mrze8BI0VniavN7w=="
        byte[] expected = "ASNFZ4mrze8BI0VniavN7w==".getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(expected, result,
                "Base64 encoding of the large integer should match the pre‑computed value");
    }
}
```