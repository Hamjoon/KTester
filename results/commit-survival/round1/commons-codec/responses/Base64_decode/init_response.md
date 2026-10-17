```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests that exercise the package‑private {@code Base64.decode(byte[],int,int,Context)}
 * method. The tests are written in the same package as the class under test so that the
 * method can be called directly (no reflection required). The {@link BaseNCodec.Context}
 * object is used to capture the decoded output that the method writes into its internal
 * buffer.
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
        // Use the default constructor – it creates a strict‑decoding instance with
        // no line‑length, no line‑separator and the standard padding character.
        base64 = new Base64();
        ctx = new BaseNCodec.Context();
    }

    @AfterEach
    void teardownAfterEach() {
        // Nothing to clean up per‑test.
        base64 = null;
        ctx = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global resources to release.
    }

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