```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import org.apache.commons.codec.binary.Base32.DecodingPolicy;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Revised unit‑tests for {@link Base32#decode(byte[])}.
 *
 * <p>
 * The original test suite exercised many internal implementation details of
 * {@code Base32}.  Those details changed between library versions (e.g. the
 * default decoding policy became {@code LENIENT}) which caused a large number
 * of failures.  The tests have been rewritten to use only the public API and
 * to reflect the actual behaviour of the current Apache Commons Codec release.
 * </p>
 *
 * <p>
 * Tests that require strict validation now construct a {@code Base32}
 * instance via the builder with {@link DecodingPolicy#STRICT}.  All other
 * tests use the default (lenient) decoder.
 * </p>
 */
class Base32_decode_Test {

    // -----------------------------------------------------------------------
    // RFC‑4648 test vectors (no padding, no line‑breaks)
    // -----------------------------------------------------------------------
    private static final String[][] BASE32_TEST_CASES = {
            { "", "" },
            { "f", "MY======" },
            { "fo", "MZXQ====" },
            { "foo", "MZXW6===" },
            { "foob", "MZXW6YQ=" },
            { "fooba", "MZXW6YTB" },
            { "foobar", "MZXW6YTBOI======" }
    };

    // -----------------------------------------------------------------------
    // Helper for the strict decoder
    // -----------------------------------------------------------------------
    private static Base32 strictBase32() {
        return Base32.builder()
                .setDecodingPolicy(DecodingPolicy.STRICT)
                .get();
    }

    /**
     * Verify that the standard RFC‑4648 vectors decode to the original bytes.
     */
    @Test
    void testStandardDecoding() {
        final Base32 codec = new Base32();          // lenient – works for valid data
        for (String[] pair : BASE32_TEST_CASES) {
            final String plain = pair[0];
            final String encoded = pair[1];
            byte[] expected = plain.getBytes(StandardCharsets.UTF_8);
            byte[] actual = codec.decode(encoded.getBytes(StandardCharsets.US_ASCII));
            assertArrayEquals(expected, actual,
                    () -> "Failed to decode Base32 string: \"" + encoded + "\"");
        }
    }

    /**
     * Decoding with a line length and line separator should ignore the separator
     * characters when the decoder is strict.
     */
    @Test
    void testChunkedDecoding() {
        // line length 10, CRLF separator – the strings below contain illegal characters
        final Base32 codec = Base32.builder()
                .setLineLength(10)
                .setLineSeparator("\r\n".getBytes(StandardCharsets.US_ASCII))
                .setDecodingPolicy(DecodingPolicy.STRICT)
                .get();

        String[] invalidChunked = {
                "M2======\r\n",
                "MZX0====\r\n",
                "MZXW0===\r\n",
                "MZXW6Y2=\r\n",
                "MZXW6YTBO2======\r\n"
        };

        for (String chunked : invalidChunked) {
            assertThrows(IllegalArgumentException.class,
                    () -> codec.decode(chunked.getBytes(StandardCharsets.US_ASCII)),
                    () -> "Expected exception for chunked invalid input: \"" + chunked + "\"");
        }
    }

    /**
     * Ensure that inputs with impossible trailing characters raise an exception
     * when the decoder is strict.
     */
    @Test
    void testImpossibleTrailingCharacters() {
        final Base32 codec = strictBase32();
        String[] impossible = {
                "MC======", "MZXE====", "MZXWB===",
                "MZXW6YB=", "MZXW6YTBOC======", "AB======"
        };
        for (String bad : impossible) {
            assertThrows(IllegalArgumentException.class,
                    () -> codec.decode(bad.getBytes(StandardCharsets.US_ASCII)),
                    () -> "Expected exception for impossible case: \"" + bad + "\"");
        }
    }

    /**
     * Verify that the decoder correctly handles the various modulus values.
     *
     * <p>
     * The expectations follow the RFC‑4648 specification:
     * <ul>
     *   <li>2 encoded chars → 1 byte</li>
     *   <li>4 encoded chars → 2 bytes</li>
     *   <li>5 encoded chars → 3 bytes</li>
     *   <li>6 encoded chars → 3 bytes (the remaining 2 bits are discarded)</li>
     *   <li>7 encoded chars → 4 bytes</li>
     * </ul>
     * </p>
     */
    @Test
    void testModulusEdgeCases() {
        final Base32 codec = new Base32(); // lenient is fine – inputs are valid
        assertArrayEquals(new byte[]{'f'},
                codec.decode("MY".getBytes(StandardCharsets.US_ASCII)),
                "Modulus 2 (2 chars) failed");

        assertArrayEquals(new byte[]{'f', 'o'},
                codec.decode("MZXQ".getBytes(StandardCharsets.US_ASCII)),
                "Modulus 4 (4 chars) failed");

        assertArrayEquals(new byte[]{'f', 'o', 'o'},
                codec.decode("MZXW6".getBytes(StandardCharsets.US_ASCII)),
                "Modulus 5 (5 chars) failed");

        // 6 chars – only three full bytes can be produced
        assertArrayEquals(new byte[]{'f', 'o', 'o'},
                codec.decode("MZXW6Y".getBytes(StandardCharsets.US_ASCII)),
                "Modulus 6 (6 chars) failed");

        // 7 chars – four bytes are produced
        assertArrayEquals(new byte[]{'f', 'o', 'o', 'b'},
                codec.decode("MZXW6YT".getBytes(StandardCharsets.US_ASCII)),
                "Modulus 7 (7 chars) failed");
    }

    /**
     * Verify that the decoder works with the Base32‑hex variant.
     *
     * <p>
     * The hex alphabet maps {@code 'C'} to the value 12 (binary {@code 01100}).
     * With only a single 5‑bit symbol and padding there is not enough data for a
     * full byte, so the result must be an empty array.
     * </p>
     */
    @Test
    void testHexVariantDecoding() {
        final Base32 codec = new Base32(true); // hex variant, lenient decoder
        byte[] decoded = codec.decode("C=======".getBytes(StandardCharsets.US_ASCII));
        assertArrayEquals(new byte[0], decoded, "Hex variant decoding failed – should produce no bytes");
    }

    /**
     * Ensure that decoding an empty input returns an empty byte array.
     */
    @Test
    void testEmptyInput() {
        final Base32 codec = new Base32();
        byte[] decoded = codec.decode(new byte[0]);
        assertNotNull(decoded, "Decoded result should not be null");
        assertEquals(0, decoded.length, "Decoded length for empty input should be zero");
    }

    // -----------------------------------------------------------------------
    // Parameterised tests for a few representative illegal inputs
    // -----------------------------------------------------------------------
    private static Stream<String> illegalInputs() {
        return Stream.of(
                "M",          // single character – not enough bits
                "MZ",         // two characters – valid, but we test strict handling of padding later
                "MZ==",       // padding in the middle
                "====",       // only padding
                "MZXW6YQ==",  // wrong padding length
                "MZXW6YQ=!"   // non‑alphabet character
        );
    }

    @ParameterizedTest
    @MethodSource("illegalInputs")
    void testStrictDecoderRejectsInvalidInput(String input) {
        Base32 codec = strictBase32();
        assertThrows(IllegalArgumentException.class,
                () -> codec.decode(input.getBytes(StandardCharsets.US_ASCII)),
                () -> "Expected strict decoder to reject: \"" + input + "\"");
    }

    /**
     * Verify that a {@code null} input array results in a {@link NullPointerException}.
     */
    @Test
    void decode_nullInputArray_throwsNullPointerException() {
        Base32 codec = new Base32();
        assertThrows(NullPointerException.class,
                () -> codec.decode(null),
                "Decoding a null byte array should throw NPE");
    }
}
```