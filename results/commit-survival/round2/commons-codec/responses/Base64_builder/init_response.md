```java
package org.apache.commons.codec.binary;

import org.apache.commons.codec.CodecPolicy;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Base64.Builder builder()}.
 *
 * <p>
 * The tests verify that the {@code builder()} method returns a usable builder,
 * that the default configuration produced by the builder matches the default
 * {@link Base64} constructor, and that custom configuration supplied via the
 * builder is honoured.
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class Base64_builder_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required.
    }

    @BeforeEach
    void setupBeforeEach() {
        // No per‑test initialisation required.
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
     * The {@code builder()} method must return a non‑null {@link Base64.Builder}
     * instance.
     */
    @Test
    void testBuilderIsNotNull() {
        Base64.Builder builder = Base64.builder();
        assertNotNull(builder, "Base64.builder() should not return null");
        assertTrue(builder instanceof Base64.Builder,
                "Base64.builder() should return an instance of Base64.Builder");
    }

    /**
     * A builder without any customisation must produce a {@link Base64}
     * instance that behaves like the default constructor (lenient decoding,
     * not URL‑safe, standard padding character '=').
     */
    @Test
    void testBuilderDefaultConfiguration() {
        Base64 codecFromBuilder = Base64.builder().get();
        Base64 codecFromDefaultCtor = new Base64(); // default constructor

        // Both instances should be lenient and not URL‑safe.
        assertFalse(codecFromBuilder.isUrlSafe(), "Default builder should produce a non‑URL‑safe codec");
        assertFalse(codecFromDefaultCtor.isUrlSafe(), "Default ctor should produce a non‑URL‑safe codec");

        assertEquals(CodecPolicy.LENIENT, codecFromBuilder.getCodecPolicy(),
                "Default builder should use LENIENT decoding policy");
        assertEquals(CodecPolicy.LENIENT, codecFromDefaultCtor.getCodecPolicy(),
                "Default ctor should use LENIENT decoding policy");

        // Encode a known payload and compare with the static convenience method.
        byte[] payload = "Apache".getBytes();
        byte[] expected = Base64.encodeBase64(payload);
        byte[] actual = codecFromBuilder.encode(payload);
        assertArrayEquals(expected, actual, "Encoding with builder‑created codec must match static method");
    }

    /**
     * Verify that custom settings supplied via the builder are honoured.
     * The test customises:
     * <ul>
     *   <li>URL‑safe mode</li>
     *   <li>Line length and line separator</li>
     *   <li>Padding character</li>
     *   <li>Decoding policy (STRICT)</li>
     * </ul>
     */
    @Test
    void testBuilderCustomConfiguration() {
        // Build a Base64 instance with a non‑standard configuration.
        Base64 codec = Base64.builder()
                .setUrlSafe(true)                         // URL‑safe mode
                .setLineLength(4)                         // short line length to force breaks
                .setLineSeparator('\r', '\n')             // CRLF separator
                .setPadding('#')                          // custom padding character
                .setDecodingPolicy(CodecPolicy.STRICT)    // strict decoding
                .get();

        // Verify the configuration via the public API.
        assertTrue(codec.isUrlSafe(), "Builder should have enabled URL‑safe mode");
        assertEquals(CodecPolicy.STRICT, codec.getCodecPolicy(),
                "Builder should have set strict decoding policy");

        // -----------------------------------------------------------------
        // 1. Padding character – encode a single byte which requires padding.
        //    Standard Base64 for "a" is "YQ==". With padding '#', we expect "YQ##".
        // -----------------------------------------------------------------
        byte[] singleByte = "a".getBytes();
        byte[] encoded = codec.encode(singleByte);
        String encodedStr = new String(encoded);
        assertEquals("YQ##", encodedStr, "Custom padding character should be used");

        // -----------------------------------------------------------------
        // 2. Line length & separator – encode data that exceeds 4 characters.
        //    Use "foobar" (6 bytes) which yields 8 Base64 chars: "Zm9vYmFy".
        //    With lineLength=4 and CRLF separator we expect:
        //        "Zm9v\r\nYmFy"
        // -----------------------------------------------------------------
        byte[] data = "foobar".getBytes();
        byte[] encodedWithBreaks = codec.encode(data);
        String expectedWithBreaks = "Zm9v\r\nYmFy";
        assertEquals(expectedWithBreaks, new String(encodedWithBreaks),
                "Line length and separator should be applied");

        // -----------------------------------------------------------------
        // 3. URL‑safe mode – the encoded output must use '-' and '_' instead of
        //    '+' and '/'.
        //    Encode a byte array that contains '+' and '/' in its standard
        //    representation. The bytes {0xfb, 0xef} encode to "+-7/" in standard
        //    Base64; in URL‑safe mode they become "-_7_".
        // -----------------------------------------------------------------
        byte[] urlSensitive = new byte[] {(byte) 0xfb, (byte) 0xef};
        byte[] urlEncoded = codec.encode(urlSensitive);
        String urlEncodedStr = new String(urlEncoded);
        assertFalse(urlEncodedStr.contains("+") || urlEncodedStr.contains("/"),
                "URL‑safe mode must not contain '+' or '/' characters");
    }
}
```