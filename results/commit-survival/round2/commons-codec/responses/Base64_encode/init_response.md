```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Unit tests for the package‑private {@code encode(byte[],int,int,Context)} method
 * of {@link Base64}. The tests exercise normal encoding, line‑chunking,
 * URL‑safe mode and handling of signed bytes.
 *
 * The test class resides in the same package as {@code Base64} so it can
 * access the protected {@link Base64.Context} class and the package‑private
 * {@code encode} method without using reflection.
 */
@ExtendWith(MockitoExtension.class)
class Base64_encode_Test {

    /** Instance used for standard (no‑chunk, non‑URL‑safe) encoding. */
    private static Base64 standardCodec;

    /** Instance used for chunked encoding (line length = 4, CRLF separator). */
    private static Base64 chunkedCodec;

    /** Instance used for URL‑safe encoding (no padding). */
    private static Base64 urlSafeCodec;

    /** Fresh context for each test. */
    private Base64.Context ctx;

    @BeforeAll
    static void setupBeforeAll() {
        // standard codec – default constructor (no line breaks, standard alphabet)
        standardCodec = new Base64();

        // chunked codec – line length 4, line separator "\r\n"
        chunkedCodec = new Base64(4, new byte[] { '\r', '\n' });

        // URL‑safe codec – no line breaks, URL‑safe alphabet, no padding
        urlSafeCodec = new Base64(0, null, true);
    }

    @BeforeEach
    void setupBeforeEach() {
        // a new Context for every test ensures isolation
        ctx = new Base64.Context();
    }

    @AfterEach
    void teardownAfterEach() {
        // help GC – not strictly required but keeps the pattern clean
        ctx = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        standardCodec = null;
        chunkedCodec = null;
        urlSafeCodec = null;
    }

    /**
     * Helper that runs the low‑level {@code encode} method and returns the
     * produced bytes as a trimmed array (up to {@code ctx.pos}).
     */
    private static byte[] encodeAndFlush(Base64 codec, byte[] input, Base64.Context context) {
        // feed the whole input
        codec.encode(input, 0, input.length, context);
        // signal EOF so that any remaining buffered bytes are emitted
        codec.encode(new byte[0], 0, -1, context);
        // copy only the valid portion of the internal buffer
        return Arrays.copyOf(context.buffer, context.pos);
    }

    @Test
    void encodeSimpleString_standardAlphabet() {
        byte[] data = "foo".getBytes(StandardCharsets.UTF_8);
        byte[] result = encodeAndFlush(standardCodec, data, ctx);
        assertEquals("Zm9v", new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    void encodeWithChunking_lineSeparatorInserted() {
        // Input longer than one chunk (4 bytes per line). The expected result
        // contains CRLF after every 4 encoded characters.
        byte[] data = "The quick brown fox".getBytes(StandardCharsets.US_ASCII);
        byte[] result = encodeAndFlush(chunkedCodec, data, ctx);
        // Manually build the expected string with CRLF every 4 characters
        String expected = "VGhl\r\nIHF1\r\naWNr\r\nIGJy\r\nb3du\r\nIGZv\r\neA==";
        assertEquals(expected, new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    void encodeUrlSafe_noPaddingAndUrlChars() {
        // The classic UUID example used in the original test suite.
        byte[] uuid = new byte[] {
                (byte)0x9f, (byte)0xad, (byte)0x0c, (byte)0x5f,
                (byte)0x9a, (byte)0x5d, (byte)0x6b, (byte)0x0f,
                (byte)0x1b, (byte)0x2e, (byte)0x4a, (byte)0x8c,
                (byte)0x8e, (byte)0x2c, (byte)0x5c, (byte)0x6c
        };
        byte[] result = encodeAndFlush(urlSafeCodec, uuid, ctx);
        // URL‑safe Base64 uses '-' and '_' instead of '+' and '/' and omits padding.
        String expected = "n60MX5pdaw8bLkqMjixcbA";
        assertEquals(expected, new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    void encodeHandlesSignedBytesCorrectly() {
        // Bytes with the high bit set (negative when interpreted as signed)
        byte[] data = new byte[] { (byte)0xFF, (byte)0xEE, (byte)0xDD };
        byte[] result = encodeAndFlush(standardCodec, data, ctx);
        // Expected Base64 for 0xFF,0xEE,0xDD is "/+7d"
        assertEquals("/+7d", new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    void encodeEmptyInputProducesEmptyResult() {
        byte[] data = new byte[0];
        byte[] result = encodeAndFlush(standardCodec, data, ctx);
        assertEquals(0, result.length);
    }

    @Test
    void encodeNullInputThrowsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            standardCodec.encode(null, 0, 0, ctx);
        });
    }

    @Test
    void encodeWithNegativeInAvailSignalsEofOnlyOnce() {
        // Call encode with -1 directly – should set eof flag and emit nothing
        // because no data has been buffered.
        standardCodec.encode(new byte[0], 0, -1, ctx);
        assertTrue(ctx.eof);
        assertEquals(0, ctx.pos);
    }
}
```