```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the package‑private {@code Base64.decode(byte[],int,int,Context)} method.
 * The tests cover normal decoding, error handling for null arguments, index errors,
 * and the various {@code modulus} branches that trigger validation exceptions.
 *
 * The test class lives in the same package as {@code Base64}, therefore the
 * method can be invoked directly without reflection.
 */
@ExtendWith(MockitoExtension.class)
class Base64_decode_Test {

    private Base64 base64;
    private BaseNCodec.Context ctx;

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Default strict‑decoding instance (no line‑length, no line‑separator,
        // standard padding character).
        base64 = new Base64();
        ctx = new BaseNCodec.Context();
    }

    @AfterEach
    void teardownAfterEach() {
        base64 = null;
        ctx = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global resources to release.
    }

    /* --------------------------------------------------------------
     *  Existing happy‑path tests (kept unchanged)
     * -------------------------------------------------------------- */

    /**
     * Simple happy‑path test: decode the Base64 string {@code "AQID"} which represents
     * the three bytes {@code {1,2,3}}. No padding is present, therefore {@code eof}
     * remains {@code false} and the decoded bytes are stored in {@code ctx.buffer}.
     */
    @Test
    void decode_withoutPadding_returnsExactBytes() {
        byte[] input = "AQID".getBytes(StandardCharsets.US_ASCII);
        base64.decode(input, 0, input.length, ctx);

        assertFalse(ctx.eof, "EOF should be false when no padding is seen");
        assertEquals(3, ctx.pos, "Exactly three bytes should have been decoded");
        assertArrayEquals(new byte[]{1, 2, 3},
                extractResult(ctx),
                "Decoded bytes must match the original payload");
    }

    /**
     * Decoding with a single padding character. The input {@code "AQ=="} corresponds to
     * a single byte {@code 0x01}. After decoding the context must indicate EOF and
     * contain exactly one byte.
     */
    @Test
    void decode_withSinglePadding_returnsOneByte() {
        byte[] input = "AQ==".getBytes(StandardCharsets.US_ASCII);
        base64.decode(input, 0, input.length, ctx);

        assertTrue(ctx.eof, "EOF should be true after encountering padding");
        assertEquals(1, ctx.pos, "Only one byte should be produced");
        assertArrayEquals(new byte[]{1}, extractResult(ctx));
    }

    /**
     * Decoding with two padding characters (modulus 3). The Base64 string {@code "AQI="}
     * decodes to the two bytes {@code {1,2}}. The method reaches the {@code case 3}
     * branch in the trailing‑bits handling logic.
     */
    @Test
    void decode_withTwoPadding_returnsTwoBytes() {
        byte[] input = "AQI=".getBytes(StandardCharsets.US_ASCII);
        base64.decode(input, 0, input.length, ctx);

        assertTrue(ctx.eof, "EOF should be true after the padding character");
        assertEquals(2, ctx.pos, "Two bytes should be produced");
        assertArrayEquals(new byte[]{1, 2}, extractResult(ctx));
    }

    /**
     * Decoding a string that leaves a modulus of {@code 1} (a single valid Base64
     * character followed by padding). According to the implementation this should
     * trigger {@code validateTrailingCharacter()}, which throws an
     * {@link IllegalArgumentException}.
     */
    @Test
    void decode_modulusOne_throwsException() {
        byte[] input = "A===".getBytes(StandardCharsets.US_ASCII);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> base64.decode(input, 0, input.length, ctx),
                "Modulus 1 should cause an IllegalArgumentException");
        assertTrue(ex.getMessage().contains("Trailing"), "Exception message should mention trailing bits");
    }

    /* --------------------------------------------------------------
     *  New tests derived from the supplied JSON test cases
     * -------------------------------------------------------------- */

    /**
     * Passing {@code null} as the input array must result in a {@link NullPointerException}
     * because the implementation attempts to read {@code input[inPos]}.
     */
    @Test
    void testDecode_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> base64.decode(null, 0, 0, new BaseNCodec.Context()),
                "Null input array should cause NullPointerException");
    }

    /**
     * Passing {@code null} as the {@link BaseNCodec.Context} must also raise a
     * {@link NullPointerException} when the method accesses context fields.
     */
    @Test
    void testDecode_nullContext_throwsNullPointerException() {
        byte[] empty = new byte[0];
        assertThrows(NullPointerException.class,
                () -> base64.decode(empty, 0, 0, null),
                "Null context should cause NullPointerException");
    }

    /**
     * When {@code inPos} points beyond the end of {@code input}, the first array
     * access triggers an {@link ArrayIndexOutOfBoundsException}.
     */
    @Test
    void testDecode_invalidIndices_throwsArrayIndexOutOfBoundsException() {
        byte[] input = new byte[]{0x41, 0x42}; // 'A','B'
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> base64.decode(input, 2, 1, new BaseNCodec.Context()),
                "Reading beyond the input array should throw ArrayIndexOutOfBoundsException");
    }

    /**
     * If {@code context.modulus} holds a value outside the range 0‑3 when EOF handling
     * occurs, the decoder throws an {@link IllegalStateException}.
     */
    @Test
    void testDecode_modulusInvalid_throwsIllegalStateException() {
        BaseNCodec.Context badCtx = new BaseNCodec.Context();
        badCtx.modulus = 5;               // illegal value
        // invoke with inAvail = -1 to force EOF processing
        assertThrows(IllegalStateException.class,
                () -> base64.decode(new byte[0], 0, -1, badCtx),
                "Illegal modulus should cause IllegalStateException");
    }

    /**
     * EOF with {@code modulus == 1} triggers {@code validateTrailingCharacter()},
     * which throws {@link IllegalArgumentException}.
     */
    @Test
    void testDecode_modulusOne_throwsIllegalArgumentException() {
        BaseNCodec.Context ctxOne = new BaseNCodec.Context();
        ctxOne.modulus = 1;
        // inAvail = -1 forces EOF handling
        assertThrows(IllegalArgumentException.class,
                () -> base64.decode(new byte[0], 0, -1, ctxOne),
                "Modulus 1 at EOF should cause IllegalArgumentException");
    }

    /**
     * EOF with {@code modulus == 2} calls {@code validateCharacter(MASK_4BITS, ctx)}.
     * Supplying a work area whose low‑4 bits are non‑zero causes an
     * {@link IllegalArgumentException}.
     */
    @Test
    void testDecode_modulusTwo_invalidMask_throwsIllegalArgumentException() {
        BaseNCodec.Context ctxTwo = new BaseNCodec.Context();
        ctxTwo.modulus = 2;
        ctxTwo.ibitWorkArea = 0xF; // low 4 bits set -> invalid
        assertThrows(IllegalArgumentException.class,
                () -> base64.decode(new byte[0], 0, -1, ctxTwo),
                "Invalid low‑4‑bit mask with modulus 2 should cause IllegalArgumentException");
    }

    /**
     * EOF with {@code modulus == 3} calls {@code validateCharacter(MASK_2BITS, ctx)}.
     * Supplying a work area whose low‑2 bits are non‑zero causes an
     * {@link IllegalArgumentException}.
     */
    @Test
    void testDecode_modulusThree_invalidMask_throwsIllegalArgumentException() {
        BaseNCodec.Context ctxThree = new BaseNCodec.Context();
        ctxThree.modulus = 3;
        ctxThree.ibitWorkArea = 0x3; // low 2 bits set -> invalid
        assertThrows(IllegalArgumentException.class,
                () -> base64.decode(new byte[0], 0, -1, ctxThree),
                "Invalid low‑2‑bit mask with modulus 3 should cause IllegalArgumentException");
    }

    /**
     * Helper that extracts the decoded bytes from the context's internal buffer.
     * The {@code BaseNCodec.Context} class stores the output in {@code buffer}
     * starting at index {@code 0} up to {@code pos - 1}.
     */
    private static byte[] extractResult(BaseNCodec.Context context) {
        byte[] result = new byte[context.pos];
        System.arraycopy(context.buffer, 0, result, 0, context.pos);
        return result;
    }
}
```