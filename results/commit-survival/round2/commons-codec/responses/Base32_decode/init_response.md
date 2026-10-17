```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Base32#decode(byte[], int, int, BaseNCodec.Context)} method.
 * The tests exercise the public {@link Base32#decode(byte[])} API, which internally
 * delegates to the package‑protected {@code decode(...)} implementation.
 */
@ExtendWith(MockitoExtension.class) // kept to satisfy the provided template
class Base32_decode_Test {

    /** Charset used for converting between {@code String} and {@code byte[]}. */
    private static final Charset UTF8 = StandardCharsets.UTF_8;

    /** Test vectors taken from RFC‑4648 (plain Base32). */
    private static final String[][] BASE32_TEST_CASES = {
            {"", ""},
            {"f", "MY======"},
            {"fo", "MZXQ===="},
            {"foo", "MZXW6==="},
            {"foob", "MZXW6YQ="},
            {"fooba", "MZXW6YTB"},
            {"foobar", "MZXW6YTBOI======"}
    };

    /** Line separator used for chunked encoding tests. */
    private static final byte[] CRLF = "\r\n".getBytes(UTF8);

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources needed.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Nothing to initialise per‑test at the moment.
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
     * Verify that decoding the RFC‑4648 examples yields the original byte arrays.
     */
    @Test
    void testDecodeStandardVectors() {
        for (String[] pair : BASE32_TEST_CASES) {
            String original = pair[0];
            String encoded = pair[1];

            Base32 codec = new Base32();                       // default (no line breaks, no hex)
            byte[] decoded = codec.decode(encoded.getBytes(UTF8));

            assertArrayEquals(original.getBytes(UTF8), decoded,
                    () -> "Failed decoding \"" + encoded + "\"");
        }
    }

    /**
     * Ensure that the decoder correctly handles line‑separator characters when a
     * line length is configured. The encoded data contains CRLF after every
     * 10 characters (the same line length used in the original test suite).
     */
    @Test
    void testDecodeWithChunkedInput() {
        // Encode a longer string with line length = 10 so that CRLFs are inserted.
        String longPlain = "The quick brown fox jumps over the lazy dog.";
        Base32 encoder = new Base32(10); // line length = 10, default CRLF separator
        byte[] encoded = encoder.encode(longPlain.getBytes(UTF8));

        // Decoder must be able to handle the same chunked representation.
        Base32 decoder = new Base32(10);
        byte[] decoded = decoder.decode(encoded);

        assertArrayEquals(longPlain.getBytes(UTF8), decoded,
                "Chunked Base32 decoding did not produce the original bytes");
    }

    /**
     * Verify that the hex variant of Base32 can decode data that was encoded with
     * the same variant. The test uses a small payload to keep the expected value
     * readable.
     */
    @Test
    void testDecodeHexVariant() {
        String plain = "Base32Hex";
        Base32 hexEncoder = new Base32(true); // useHex = true
        byte[] encoded = hexEncoder.encode(plain.getBytes(UTF8));

        Base32 hexDecoder = new Base32(true);
        byte[] decoded = hexDecoder.decode(encoded);

        assertArrayEquals(plain.getBytes(UTF8), decoded,
                "Hex variant decoding failed");
    }

    /**
     * Decoding should stop at the first padding character ('='). Anything after
     * the padding must be ignored. This test feeds an encoded string that contains
     * valid data followed by extra characters after the padding.
     */
    @Test
    void testDecodeStopsAtFirstPadding() {
        // "MZXW6===" decodes to "foo". Append garbage after the padding.
        String encodedWithGarbage = "MZXW6===XYZ";
        Base32 codec = new Base32();

        byte[] decoded = codec.decode(encodedWithGarbage.getBytes(UTF8));

        assertArrayEquals("foo".getBytes(UTF8), decoded,
                "Decoder did not stop at the first padding character");
    }

    /**
     * The decoder should gracefully handle {@code null} input by returning {@code null}.
     */
    @Test
    void testDecodeNullInput() {
        Base32 codec = new Base32();
        assertNull(codec.decode((byte[]) null), "Decoding null should return null");
    }

    /**
     * Verify that an impossible trailing‑character combination (e.g. a single
     * non‑padding character at the end) does not corrupt previously decoded data.
     * According to the implementation such cases are ignored after validation.
     */
    @Test
    void testDecodeImpossibleTrailingCharactersAreIgnored() {
        // "MC======" is an impossible combination (only 2 bits of data).
        String impossible = "MC======";
        Base32 codec = new Base32();

        // The method should not throw; it should simply return an empty array.
        byte[] result = codec.decode(impossible.getBytes(UTF8));

        assertEquals(0, result.length,
                "Impossible trailing characters should result in an empty output");
    }
}
```