```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Tests for the {@link Base32#encode(byte[], int, int, BaseNCodec.Context)} method.
 * The tests use the public API (encode(byte[]), encode(byte[],int,int) and
 * {@link BaseNCodec#readResults(byte[], int, int, BaseNCodec.Context)}) together
 * with the package‑protected {@code encode(...)} method, which is accessible because
 * this test class resides in the same package.
 */
@ExtendWith(MockitoExtension.class)
class Base32_encode_Test {

    private Base32 codec;
    private BaseNCodec.Context context;

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // default codec (standard Base32, no line breaks)
        codec = new Base32();
        context = new BaseNCodec.Context();
    }

    @AfterEach
    void teardownAfterEach() {
        codec = null;
        context = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup required.
    }

    /**
     * Verify that encoding an empty byte array yields an empty result.
     */
    @Test
    void encodeEmpty() {
        byte[] input = new byte[0];
        byte[] encoded = codec.encode(input);
        assertEquals(0, encoded.length, "Empty input should produce empty output");
    }

    /**
     * Encode a single‑character string and compare with the RFC‑4648 example.
     */
    @Test
    void encodeSingleChar() {
        byte[] input = "f".getBytes(StandardCharsets.US_ASCII);
        byte[] encoded = codec.encode(input);
        assertArrayEquals("MY======".getBytes(StandardCharsets.US_ASCII), encoded);
    }

    /**
     * Encode the full set of RFC‑4648 test vectors and compare the results.
     */
    @Test
    void encodeRfcVectors() {
        String[][] vectors = {
                {"", ""},
                {"f", "MY======"},
                {"fo", "MZXQ===="},
                {"foo", "MZXW6==="},
                {"foob", "MZXW6YQ="},
                {"fooba", "MZXW6YTB"},
                {"foobar", "MZXW6YTBOI======"}
        };
        for (String[] pair : vectors) {
            byte[] input = pair[0].getBytes(StandardCharsets.US_ASCII);
            byte[] expected = pair[1].getBytes(StandardCharsets.US_ASCII);
            assertArrayEquals(expected, codec.encode(input),
                    () -> "Encoding of \"" + pair[0] + "\" did not match expected");
        }
    }

    /**
     * Verify that the incremental (single‑byte) encoding path produces the same
     * result as the bulk {@code encode(byte[])} method.
     */
    @Test
    void encodeIncrementallyMatchesBulk() {
        for (int length = 0; length <= 20; length++) {
            byte[] data = new byte[length];
            for (int i = 0; i < length; i++) {
                data[i] = (byte) i; // deterministic data
            }

            // Bulk encoding
            byte[] bulk = codec.encode(data);

            // Incremental encoding using the package‑protected encode method
            Base32 incrementalCodec = new Base32(); // fresh instance
            BaseNCodec.Context ctx = new BaseNCodec.Context();
            for (int i = 0; i < data.length; i++) {
                incrementalCodec.encode(data, i, 1, ctx);
            }
            // Signal EOF
            incrementalCodec.encode(data, 0, -1, ctx);

            byte[] incremental = new byte[bulk.length];
            incrementalCodec.readResults(incremental, 0, incremental.length, ctx);

            assertArrayEquals(bulk, incremental,
                    () -> "Incremental encoding differed for length " + length);
        }
    }

    /**
     * Test encoding with a non‑zero line length (chunked output). The expected
     * output contains CRLF after each 8‑character block.
     */
    @Test
    void encodeWithChunking() {
        // line length 8, CRLF as separator (the defaults used by Base32)
        Base32 chunked = new Base32(8, "\r\n".getBytes(StandardCharsets.US_ASCII));
        byte[] input = "foobar".getBytes(StandardCharsets.US_ASCII);
        byte[] encoded = chunked.encode(input);
        // Expected: "MZXW6YTBOI======" split after 8 chars + CRLF
        String expected = "MZXW6YTB\r\nOI======" ;
        assertArrayEquals(expected.getBytes(StandardCharsets.US_ASCII), encoded);
    }

    /**
     * Verify the Base32Hex variant produces the correct alphabet.
     */
    @Test
    void encodeBase32HexVariant() {
        Base32 hexCodec = new Base32(true); // useHex = true
        // The RFC‑4648 Base32Hex encoding of "f" is "CO======"
        byte[] input = "f".getBytes(StandardCharsets.US_ASCII);
        byte[] encoded = hexCodec.encode(input);
        assertArrayEquals("CO======".getBytes(StandardCharsets.US_ASCII), encoded);
    }

    /**
     * Ensure that providing a custom padding character works and that the
     * padding character is not part of the alphabet.
     */
    @Test
    void encodeCustomPadding() {
        Base32 customPad = Base32.builder()
                .setPadding('*')
                .get();
        byte[] input = "fo".getBytes(StandardCharsets.US_ASCII);
        byte[] encoded = customPad.encode(input);
        // Standard Base32 for "fo" is "MZXQ====", replace '=' with '*'
        assertArrayEquals("MZXQ****".getBytes(StandardCharsets.US_ASCII), encoded);
    }

    /**
     * Verify that calling encode with a negative {@code inAvail} correctly
     * finalises the stream (EOF handling). This is exercised indirectly by the
     * incremental test but we add an explicit check for clarity.
     */
    @Test
    void encodeEofHandling() {
        Base32 codec = new Base32();
        BaseNCodec.Context ctx = new BaseNCodec.Context();

        // Feed a single byte
        byte[] oneByte = { 'a' };
        codec.encode(oneByte, 0, 1, ctx);
        // Signal EOF
        codec.encode(oneByte, 0, -1, ctx);

        byte[] result = new byte[8]; // maximum possible output for 1 byte
        int read = codec.readResults(result, 0, result.length, ctx);
        assertTrue(read > 0, "Expected some output after EOF");
        // Trim the array to the actual length read
        byte[] trimmed = Arrays.copyOf(result, read);
        // Expected Base32 for "a" is "ME======"
        assertArrayEquals("ME======".getBytes(StandardCharsets.US_ASCII), trimmed);
    }
}
```