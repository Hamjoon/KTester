```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.codec.CodecPolicy;

/**
 * Unit tests for {@link Base64#decode(byte[], int, int, BaseNCodec.Context)}.
 *
 * The tests exercise normal decoding, handling of optional padding,
 * strict‑mode validation of illegal trailing bits and proper EOF handling.
 */
@ExtendWith(MockitoExtension.class)
class Base64_decode_Test {

    /** Codec with default (lenient) policy. */
    private Base64 lenientCodec;

    /** Codec with strict policy – used to verify validation of illegal trailing bits. */
    private Base64 strictCodec;

    /** Re‑used context for each test. */
    private BaseNCodec.Context context;

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required.
    }

    @BeforeEach
    void setupBeforeEach() {
        // default (lenient) codec
        lenientCodec = new Base64();

        // strict codec – required for the “impossible trailing bits” tests
        strictCodec = new Base64(0, null, false, CodecPolicy.STRICT);

        // fresh context for every test case
        context = new BaseNCodec.Context();
    }

    @AfterEach
    void teardownAfterEach() {
        // Nothing to clean up per‑test.
        lenientCodec = null;
        strictCodec = null;
        context = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global teardown needed.
    }

    /**
     * Simple happy‑path test: decode a Base64 string with padding.
     * Expected result is the ASCII bytes for “Hello”.
     */
    @Test
    void decode_WithPadding_ReturnsExpectedBytes() {
        final byte[] input = "SGVsbG8=".getBytes(StandardCharsets.US_ASCII);
        lenientCodec.decode(input, 0, input.length, context);

        // The decoder writes into the context's internal buffer.
        final byte[] actual = Arrays.copyOf(context.buffer, context.pos);
        final byte[] expected = "Hello".getBytes(StandardCharsets.US_ASCII);
        assertArrayEquals(expected, actual);
    }

    /**
     * Verify that the decoder works when the padding character is omitted.
     * RFC 2045 permits the final “=” to be absent.
     */
    @Test
    void decode_WithoutPadding_ReturnsExpectedBytes() {
        final byte[] input = "SGVsbG8".getBytes(StandardCharsets.US_ASCII); // same as above, no '='
        lenientCodec.decode(input, 0, input.length, context);

        final byte[] actual = Arrays.copyOf(context.buffer, context.pos);
        final byte[] expected = "Hello".getBytes(StandardCharsets.US_ASCII);
        assertArrayEquals(expected, actual);
    }

    /**
     * In strict decoding mode, illegal trailing bits must cause a validation
     * exception. The string “AB” is one of the documented impossible cases.
     */
    @Test
    void decode_StrictMode_InvalidTrailingBits_ThrowsException() {
        final byte[] input = "AB".getBytes(StandardCharsets.US_ASCII);
        // The strict codec should reject the input; the underlying method
        // throws IllegalArgumentException via validateTrailingCharacter().
        assertThrows(IllegalArgumentException.class,
                () -> strictCodec.decode(input, 0, input.length, context));
    }

    /**
     * When the caller signals end‑of‑stream with a negative {@code inAvail},
     * the decoder must set {@code context.eof} and stop further processing.
     */
    @Test
    void decode_NegativeInAvail_SetsEofFlag() {
        final byte[] input = "SGVsbG8=".getBytes(StandardCharsets.US_ASCII);
        // Normal decode of the first part
        lenientCodec.decode(input, 0, input.length, context);
        // Signal EOF
        lenientCodec.decode(input, 0, -1, context);

        assertTrue(context.eof, "Context should be marked as EOF after negative inAvail");
    }

    /**
     * Verify that the decoder correctly processes a multi‑block input and
     * respects the internal buffer growth logic.
     */
    @Test
    void decode_MultiBlockInput_BufferGrowsAsNeeded() {
        // Encode a relatively long string using the public API to obtain a valid Base64 payload.
        final String original = "The quick brown fox jumps over the lazy dog. "
                + "Pack my box with five dozen liquor jugs.";
        final byte[] encoded = Base64.encodeBase64(original.getBytes(StandardCharsets.UTF_8));

        // Decode using the low‑level method under test.
        lenientCodec.decode(encoded, 0, encoded.length, context);
        final byte[] decoded = Arrays.copyOf(context.buffer, context.pos);
        assertArrayEquals(original.getBytes(StandardCharsets.UTF_8), decoded);
    }
}
```