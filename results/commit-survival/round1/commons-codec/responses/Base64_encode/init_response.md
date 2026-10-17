```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Base64_encode_Test {

    /** Instance used for the majority of tests (no line‑chunking, standard alphabet). */
    private Base64 base64;

    /** Context object required by the package‑private {@code encode} method. */
    private BaseNCodec.Context ctx;

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources to initialise.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Default Base64 – no line length, standard alphabet, padding enabled.
        base64 = new Base64();
        ctx = new BaseNCodec.Context();
    }

    @AfterEach
    void teardownAfterEach() {
        // Help GC and avoid accidental reuse between tests.
        base64 = null;
        ctx = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global resources to clean up.
    }

    /**
     * Helper that runs the internal {@code encode} method on {@code data}
     * and returns the bytes that were written to the context.
     *
     * @param data   data to encode
     * @return encoded bytes
     */
    private byte[] runEncode(byte[] data) {
        // Feed the whole input in one call.
        base64.encode(data, 0, data.length, ctx);
        // Signal EOF so the encoder can flush any remaining bytes/padding.
        base64.encode(new byte[0], 0, -1, ctx);

        // The context buffer may be larger than the actual output; copy only the valid part.
        byte[] result = new byte[ctx.pos];
        System.arraycopy(ctx.buffer, 0, result, 0, ctx.pos);
        return result;
    }

    @Test
    void encode_fullBlock_noPadding() {
        // "Man" -> "TWFu"
        byte[] input = "Man".getBytes();
        byte[] expected = Base64.encodeBase64(input); // public API for reference
        byte[] actual = runEncode(input);
        assertArrayEquals(expected, actual, "Encoding of a full 3‑byte block should match public API");
    }

    @Test
    void encode_oneByteRemainder() {
        // "M" -> "TQ=="
        byte[] input = "M".getBytes();
        byte[] expected = Base64.encodeBase64(input);
        byte[] actual = runEncode(input);
        assertArrayEquals(expected, actual, "Encoding with a 1‑byte remainder must include two padding chars");
    }

    @Test
    void encode_twoByteRemainder() {
        // "Ma" -> "TWE="
        byte[] input = "Ma".getBytes();
        byte[] expected = Base64.encodeBase64(input);
        byte[] actual = runEncode(input);
        assertArrayEquals(expected, actual, "Encoding with a 2‑byte remainder must include one padding char");
    }

    @Test
    void encode_multipleBlocksWithChunking() {
        // Use a line length of 8 characters (i.e. two encoded blocks) and LF as separator.
        Base64 chunked = new Base64(8, new byte[]{'\n'});
        BaseNCodec.Context chunkCtx = new BaseNCodec.Context();

        // 12 bytes => 16 Base64 chars; with line length 8 we expect two lines.
        byte[] input = "HelloWorld!!".getBytes(); // 12 bytes
        chunked.encode(input, 0, input.length, chunkCtx);
        chunked.encode(new byte[0], 0, -1, chunkCtx);

        byte[] actual = new byte[chunkCtx.pos];
        System.arraycopy(chunkCtx.buffer, 0, actual, 0, chunkCtx.pos);

        // Build the expected string using the public API and then insert LF after every 8 chars.
        String full = new String(Base64.encodeBase64(input));
        StringBuilder expectedBuilder = new StringBuilder();
        for (int i = 0; i < full.length(); i += 8) {
            int end = Math.min(i + 8, full.length());
            expectedBuilder.append(full, i, end);
            if (end < full.length()) {
                expectedBuilder.append('\n');
            }
        }
        byte[] expected = expectedBuilder.toString().getBytes();

        assertArrayEquals(expected, actual, "Chunked encoding must insert line separators at the configured length");
    }

    @Test
    void encode_emptyInput() {
        byte[] input = new byte[0];
        byte[] expected = Base64.encodeBase64(input);
        byte[] actual = runEncode(input);
        assertArrayEquals(expected, actual, "Encoding an empty array should yield an empty result");
    }

    @Test
    void encode_nullInputThrows() {
        assertThrows(NullPointerException.class, () -> base64.encode(null, 0, 0, ctx));
    }
}
```