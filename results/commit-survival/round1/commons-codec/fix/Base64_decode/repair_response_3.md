```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.apache.commons.codec.binary.Base64.DecodingPolicy;

/**
 * Tests for the strict‑decoding behaviour of {@link Base64}.
 *
 * <p>
 * The default {@code Base64} decoder works in {@code LENIENT} mode and therefore
 * tolerates many malformed inputs.  For these tests we need the decoder to be
 * {@code STRICT} so that it validates padding, trailing bits and the internal
 * {@code Context.modulus} value.  The {@code Base64.builder()} API is used to
 * obtain a strict decoder instance.
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class Base64_decode_Test {

    private Base64 base64;
    private BaseNCodec.Context ctx;

    @BeforeEach
    void setupBeforeEach() {
        // Obtain a strict decoder (validates padding, trailing bits, etc.).
        base64 = Base64.builder()
                .setDecodingPolicy(DecodingPolicy.STRICT)
                .get();
        ctx = new BaseNCodec.Context();
    }

    @AfterEach
    void teardownAfterEach() {
        base64 = null;
        ctx = null;
    }

    /**
     * Helper that extracts the decoded bytes from the context's internal buffer.
     * The {@code BaseNCodec.Context} class stores the output in {@code buffer}
     * starting at index {@code 0} up to {@code pos - 1}.
     */
    private static byte[] extractResult(BaseNCodec.Context context) {
        final byte[] result = new byte[context.pos];
        System.arraycopy(context.buffer, 0, result, 0, context.pos);
        return result;
    }

    @Test
    void decode_withoutPadding_returnsExactBytes() {
        final byte[] input = "AQID".getBytes(StandardCharsets.US_ASCII);
        base64.decode(input, 0, input.length, ctx);
        assertFalse(ctx.eof, "EOF should be false when no padding is seen");
        assertEquals(3, ctx.pos, "Exactly three bytes should have been decoded");
        assertArrayEquals(new byte[] { 1, 2, 3 }, extractResult(ctx),
                "Decoded bytes must match the original payload");
    }

    @Test
    void decode_withSinglePadding_returnsOneByte() {
        final byte[] input = "AQ==".getBytes(StandardCharsets.US_ASCII);
        base64.decode(input, 0, input.length, ctx);
        assertTrue(ctx.eof, "EOF should be true after encountering padding");
        assertEquals(1, ctx.pos, "Only one byte should be produced");
        assertArrayEquals(new byte[] { 1 }, extractResult(ctx));
    }

    @Test
    void decode_withTwoPadding_returnsTwoBytes() {
        final byte[] input = "AQI=".getBytes(StandardCharsets.US_ASCII);
        base64.decode(input, 0, input.length, ctx);
        assertTrue(ctx.eof, "EOF should be true after the padding character");
        assertEquals(2, ctx.pos, "Two bytes should be produced");
        assertArrayEquals(new byte[] { 1, 2 }, extractResult(ctx));
    }

    @Test
    void decode_modulusOne_throwsException() {
        final byte[] input = "A===".getBytes(StandardCharsets.US_ASCII);
        final IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> base64.decode(input, 0, input.length, ctx),
                "Modulus 1 should cause an IllegalArgumentException");
        assertTrue(ex.getMessage().contains("Trailing"),
                "Exception message should mention trailing bits");
    }

    @Test
    void testDecode_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> base64.decode(null, 0, 0, new BaseNCodec.Context()),
                "Null input array should cause NullPointerException");
    }

    @Test
    void testDecode_nullContext_throwsNullPointerException() {
        final byte[] empty = new byte[0];
        assertThrows(NullPointerException.class,
                () -> base64.decode(empty, 0, 0, null),
                "Null context should cause NullPointerException");
    }

    @Test
    void testDecode_invalidIndices_throwsArrayIndexOutOfBoundsException() {
        // 'A','B'
        final byte[] input = new byte[] { 0x41, 0x42 };
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> base64.decode(input, 2, 1, new BaseNCodec.Context()),
                "Reading beyond the input array should throw ArrayIndexOutOfBoundsException");
    }

    @Test
    void testDecode_modulusInvalid_throwsIllegalStateException() {
        final BaseNCodec.Context badCtx = new BaseNCodec.Context();
        // illegal value
        badCtx.modulus = 5;
        assertThrows(IllegalStateException.class,
                () -> base64.decode(new byte[0], 0, -1, badCtx),
                "Illegal modulus should cause IllegalStateException");
    }

    @Test
    void testDecode_modulusOne_throwsIllegalArgumentException() {
        final BaseNCodec.Context ctxOne = new BaseNCodec.Context();
        ctxOne.modulus = 1;
        assertThrows(IllegalArgumentException.class,
                () -> base64.decode(new byte[0], 0, -1, ctxOne),
                "Modulus 1 at EOF should cause IllegalArgumentException");
    }

    @Test
    void testDecode_modulusTwo_invalidMask_throwsIllegalArgumentException() {
        final BaseNCodec.Context ctxTwo = new BaseNCodec.Context();
        ctxTwo.modulus = 2;
        // low 4 bits set – invalid
        ctxTwo.ibitWorkArea = 0xF;
        assertThrows(IllegalArgumentException.class,
                () -> base64.decode(new byte[0], 0, -1, ctxTwo),
                "Invalid low‑4‑bit mask with modulus 2 should cause IllegalArgumentException");
    }

    @Test
    void testDecode_modulusThree_invalidMask_throwsIllegalArgumentException() {
        final BaseNCodec.Context ctxThree = new BaseNCodec.Context();
        ctxThree.modulus = 3;
        // low 2 bits set – invalid
        ctxThree.ibitWorkArea = 0x3;
        assertThrows(IllegalArgumentException.class,
                () -> base64.decode(new byte[0], 0, -1, ctxThree),
                "Invalid low‑2‑bit mask with modulus 3 should cause IllegalArgumentException");
    }
}
```