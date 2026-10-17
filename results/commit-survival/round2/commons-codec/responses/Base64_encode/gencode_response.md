```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Unit tests for the package‑private {@code encode(byte[],int,int,Context)} method
 * of {@link Base64}. The tests cover:
 * <ul>
 *   <li>standard encoding of 0‑3 bytes (including padding)</li>
 *   <li>URL‑safe mode (no padding)</li>
 *   <li>line‑chunked encoding with default and custom separators</li>
 *   <li>streaming use‑case where data arrives in several calls</li>
 *   <li>handling of signed bytes and empty / null inputs</li>
 * </ul>
 *
 * The test class resides in the same package so it can access the
 * protected {@link Base64.Context} class and the package‑private
 * {@code encode} method without reflection.
 */
@ExtendWith(MockitoExtension.class)
class Base64_encode_Test {

    /** Standard (no‑chunk, non‑URL‑safe) codec. */
    private static Base64 standardCodec;

    /** Chunked codec – line length 4, CRLF separator (used for some tests). */
    private static Base64 chunkedCodec;

    /** URL‑safe codec – no line breaks, no padding. */
    private static Base64 urlSafeCodec;

    /** Fresh context for each test. */
    private Base64.Context ctx;

    @BeforeAll
    static void setupBeforeAll() {
        // default constructor → no line breaks, standard alphabet
        standardCodec = new Base64();

        // line length 4, line separator "\r\n"
        chunkedCodec = new Base64(4, new byte[]{'\r', '\n'});

        // URL‑safe, no line breaks
        urlSafeCodec = new Base64(0, null, true);
    }

    @BeforeEach
    void setupBeforeEach() {
        ctx = new Base64.Context(); // isolate each test
    }

    @AfterEach
    void teardownAfterEach() {
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
     * produced bytes up to {@code ctx.pos}.
     */
    private static byte[] encodeAndFlush(Base64 codec, byte[] input, Base64.Context context) {
        // feed the data
        codec.encode(input, 0, input.length, context);
        // signal EOF so that any remaining buffered bytes are emitted
        codec.encode(new byte[0], 0, -1, context);
        // copy only the valid portion of the internal buffer
        return Arrays.copyOf(context.buffer, context.pos);
    }

    /* --------------------------------------------------------------------- */
    /*  Existing tests from the original framework (kept unchanged)         */
    /* --------------------------------------------------------------------- */

    @Test
    void encodeSimpleString_standardAlphabet() {
        byte[] data = "foo".getBytes(StandardCharsets.UTF_8);
        byte[] result = encodeAndFlush(standardCodec, data, ctx);
        Assertions.assertEquals("Zm9v", new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    void encodeWithChunking_lineSeparatorInserted() {
        byte[] data = "The quick brown fox".getBytes(StandardCharsets.US_ASCII);
        byte[] result = encodeAndFlush(chunkedCodec, data, ctx);
        String expected = "VGhl\r\nIHF1\r\naWNr\r\nIGJy\r\nb3du\r\nIGZv\r\neA==";
        Assertions.assertEquals(expected, new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    void encodeUrlSafe_noPaddingAndUrlChars() {
        byte[] uuid = new byte[]{
                (byte) 0x9f, (byte) 0xad, (byte) 0x0c, (byte) 0x5f,
                (byte) 0x9a, (byte) 0x5d, (byte) 0x6b, (byte) 0x0f,
                (byte) 0x1b, (byte) 0x2e, (byte) 0x4a, (byte) 0x8c,
                (byte) 0x8e, (byte) 0x2c, (byte) 0x5c, (byte) 0x6c
        };
        byte[] result = encodeAndFlush(urlSafeCodec, uuid, ctx);
        String expected = "n60MX5pdaw8bLkqMjixcbA";
        Assertions.assertEquals(expected, new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    void encodeHandlesSignedBytesCorrectly() {
        byte[] data = new byte[]{(byte) 0xFF, (byte) 0xEE, (byte) 0xDD};
        byte[] result = encodeAndFlush(standardCodec, data, ctx);
        Assertions.assertEquals("/+7d", new String(result, StandardCharsets.US_ASCII));
    }

    @Test
    void encodeEmptyInputProducesEmptyResult() {
        byte[] data = new byte[0];
        byte[] result = encodeAndFlush(standardCodec, data, ctx);
        Assertions.assertEquals(0, result.length);
    }

    @Test
    void encodeNullInputThrowsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            standardCodec.encode(null, 0, 0, ctx);
        });
    }

    @Test
    void encodeWithNegativeInAvailSignalsEofOnlyOnce() {
        standardCodec.encode(new byte[0], 0, -1, ctx);
        Assertions.assertTrue(ctx.eof);
        Assertions.assertEquals(0, ctx.pos);
    }

    /* --------------------------------------------------------------------- */
    /*  New tests generated from the supplied JSON test cases               */
    /* --------------------------------------------------------------------- */

    /** Parameter source for single‑byte standard encoding. */
    private static Stream<Arguments> singleByteProvider() {
        return Stream.of(
                Arguments.of(new byte[]{(byte) 0xFF}, new byte[]{47, 119, 61, 61}) // "/w=="
        );
    }

    @ParameterizedTest(name = "single byte 0xFF → {1}")
    @MethodSource("singleByteProvider")
    void encodeSingleByteStandard(byte[] input, byte[] expected) {
        byte[] result = encodeAndFlush(standardCodec, input, ctx);
        Assertions.assertArrayEquals(expected, result);
    }

    /** Parameter source for two‑byte standard encoding. */
    private static Stream<Arguments> twoByteProvider() {
        return Stream.of(
                Arguments.of(new byte[]{(byte) 0xFF, (byte) 0xEE}, new byte[]{47, 43, 52, 61}) // "/+4="
        );
    }

    @ParameterizedTest(name = "two bytes 0xFF,0xEE → {1}")
    @MethodSource("twoByteProvider")
    void encodeTwoBytesStandard(byte[] input, byte[] expected) {
        byte[] result = encodeAndFlush(standardCodec, input, ctx);
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    void encodeFullBlockStandard() {
        // "Man" → 0x4D 0x61 0x6E
        byte[] input = new byte[]{77, 97, 110};
        byte[] expected = new byte[]{84, 87, 70, 117}; // "TWFu"
        byte[] result = encodeAndFlush(standardCodec, input, ctx);
        Assertions.assertArrayEquals(expected, result);
    }

    /** URL‑safe single‑byte encoding (no padding). */
    @Test
    void encodeSingleByteUrlSafe() {
        byte[] input = new byte[]{(byte) 0xFF};
        byte[] expected = new byte[]{47, 119}; // "/w"
        byte[] result = encodeAndFlush(urlSafeCodec, input, ctx);
        Assertions.assertArrayEquals(expected, result);
    }

    /** URL‑safe two‑byte encoding (single padding omitted). */
    @Test
    void encodeTwoBytesUrlSafe() {
        byte[] input = new byte[]{(byte) 0xFF, (byte) 0xEE};
        byte[] expected = new byte[]{47, 43, 52}; // "/+4"
        byte[] result = encodeAndFlush(urlSafeCodec, input, ctx);
        Assertions.assertArrayEquals(expected, result);
    }

    /** Chunked encoding of 256 sequential bytes – compare with the public helper. */
    @Test
    void encodeChunkedLargeSequentialData() {
        // generate 0x00 .. 0xFF
        byte[] data = new byte[256];
        for (int i = 0; i < 256; i++) {
            data[i] = (byte) i;
        }

        byte[] result = encodeAndFlush(chunkedCodec, data, ctx);
        // The public static method already implements the same chunking rules.
        byte[] expected = Base64.encodeBase64Chunked(data);
        Assertions.assertArrayEquals(expected, result);
    }

    /** Simulate streaming: first 2 bytes, then 1 byte, then EOF. */
    @Test
    void encodeStreamingPartialBlocks() {
        // first chunk – two bytes
        byte[] first = new byte[]{0x01, 0x02};
        standardCodec.encode(first, 0, first.length, ctx);

        // second chunk – one byte, completes the 3‑byte block
        byte[] second = new byte[]{0x03};
        standardCodec.encode(second, 0, second.length, ctx);

        // signal EOF to flush any leftover (there should be none)
        standardCodec.encode(new byte[0], 0, -1, ctx);

        byte[] result = Arrays.copyOf(ctx.buffer, ctx.pos);
        byte[] expected = "AQID".getBytes(StandardCharsets.US_ASCII);
        Assertions.assertArrayEquals(expected, result);
    }

    /** Custom line separator (LF) with a line length of 4. */
    @Test
    void encodeCustomLineSeparator() {
        // Build a codec with lineLength=4 and lineSeparator="\n"
        Base64 customCodec = Base64.builder()
                .setLineLength(4)
                .setLineSeparator('\n')
                .get();

        byte[] data = "ABCDEF".getBytes(StandardCharsets.US_ASCII); // two full blocks
        byte[] result = encodeAndFlush(customCodec, data, ctx);
        String actual = new String(result, StandardCharsets.US_ASCII);
        // Expected: "QUJD\nREU="
        Assertions.assertEquals("QUJD\nREU=", actual);
    }
}
```