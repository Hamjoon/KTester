```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Additional unit tests for {@link Base32#encode(byte[], int, int, BaseNCodec.Context)}.
 *
 * These tests cover the edge‑case scenarios described in the JSON test case list.
 * They are placed in the same package as {@link Base32} so that the
 * package‑protected {@code encode(...)} method can be invoked directly.
 */
@ExtendWith(MockitoExtension.class)
class Base32_encode_Test {

    private Base32 codec;
    private BaseNCodec.Context context;

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required.
    }

    @BeforeEach
    void setupBeforeEach() {
        codec = new Base32();                 // default (no line breaks, standard alphabet)
        context = new BaseNCodec.Context(); // fresh context for each test
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

    /* --------------------------------------------------------------------- *
     * Existing tests from the original file (kept unchanged)                *
     * --------------------------------------------------------------------- */

    @Test
    void encodeEmpty() {
        byte[] input = new byte[0];
        byte[] encoded = codec.encode(input);
        assertEquals(0, encoded.length, "Empty input should produce empty output");
    }

    @Test
    void encodeSingleChar() {
        byte[] input = "f".getBytes(StandardCharsets.US_ASCII);
        byte[] encoded = codec.encode(input);
        assertArrayEquals("MY======".getBytes(StandardCharsets.US_ASCII), encoded);
    }

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

    @Test
    void encodeIncrementallyMatchesBulk() {
        for (int length = 0; length <= 20; length++) {
            byte[] data = new byte[length];
            for (int i = 0; i < length; i++) {
                data[i] = (byte) i;
            }

            byte[] bulk = codec.encode(data);

            Base32 incrementalCodec = new Base32();
            BaseNCodec.Context ctx = new BaseNCodec.Context();
            for (int i = 0; i < data.length; i++) {
                incrementalCodec.encode(data, i, 1, ctx);
            }
            incrementalCodec.encode(data, 0, -1, ctx);

            byte[] incremental = new byte[bulk.length];
            incrementalCodec.readResults(incremental, 0, incremental.length, ctx);

            assertArrayEquals(bulk, incremental,
                    () -> "Incremental encoding differed for length " + length);
        }
    }

    @Test
    void encodeWithChunking() {
        Base32 chunked = new Base32(8, "\r\n".getBytes(StandardCharsets.US_ASCII));
        byte[] input = "foobar".getBytes(StandardCharsets.US_ASCII);
        byte[] encoded = chunked.encode(input);
        String expected = "MZXW6YTB\r\nOI======";
        assertArrayEquals(expected.getBytes(StandardCharsets.US_ASCII), encoded);
    }

    @Test
    void encodeBase32HexVariant() {
        Base32 hexCodec = new Base32(true);
        byte[] input = "f".getBytes(StandardCharsets.US_ASCII);
        byte[] encoded = hexCodec.encode(input);
        assertArrayEquals("CO======".getBytes(StandardCharsets.US_ASCII), encoded);
    }

    @Test
    void encodeCustomPadding() {
        Base32 customPad = Base32.builder()
                .setPadding('*')
                .get();
        byte[] input = "fo".getBytes(StandardCharsets.US_ASCII);
        byte[] encoded = customPad.encode(input);
        assertArrayEquals("MZXQ****".getBytes(StandardCharsets.US_ASCII), encoded);
    }

    @Test
    void encodeEofHandling() {
        Base32 codec = new Base32();
        BaseNCodec.Context ctx = new BaseNCodec.Context();

        byte[] oneByte = { 'a' };
        codec.encode(oneByte, 0, 1, ctx);
        codec.encode(oneByte, 0, -1, ctx);

        byte[] result = new byte[8];
        int read = codec.readResults(result, 0, result.length, ctx);
        assertTrue(read > 0, "Expected some output after EOF");
        byte[] trimmed = Arrays.copyOf(result, read);
        assertArrayEquals("ME======".getBytes(StandardCharsets.US_ASCII), trimmed);
    }

    /* --------------------------------------------------------------------- *
     * New tests derived from the JSON test‑case specification               *
     * --------------------------------------------------------------------- */

    /** EOF early‑return – when the context already signals EOF the method must exit. */
    @Test
    void encodeEofEarlyReturn() {
        context.eof = true;               // simulate prior EOF
        byte[] input = new byte[]{0x61, 0x62};
        codec.encode(input, 0, -1, context); // should return immediately

        // No bytes should have been written; pos stays 0 and eof stays true.
        assertEquals(0, context.pos, "No output should be produced when eof is already true");
        assertTrue(context.eof, "EOF flag must remain true");
    }

    /** Full block encoding without a line separator – 5 bytes produce exactly 8 chars. */
    @Test
    void encodeFullBlockNoSeparator() {
        byte[] input = new byte[]{0x68, 0x65, 0x6c, 0x6c, 0x6f}; // "hello"
        codec.encode(input, 0, input.length, context);
        // Signal EOF to flush (no leftover in this case)
        codec.encode(input, 0, -1, context);

        byte[] out = new byte[8];
        int read = codec.readResults(out, 0, out.length, context);
        assertEquals(8, read, "Exactly one Base32 block should be emitted");
        assertArrayEquals("NBSWY3DP".getBytes(StandardCharsets.US_ASCII), Arrays.copyOf(out, read));
        assertFalse(context.eof, "EOF flag should stay false after normal block processing");
        assertEquals(0, context.modulus, "Modulus must be 0 after a full block");
        assertEquals(8, context.pos, "Context position should be at the end of the block");
    }

    /** Full block encoding with line separator (chunking). */
    @Test
    void encodeFullBlockWithChunking() {
        Base32 chunked = new Base32(8, "\r\n".getBytes(StandardCharsets.US_ASCII));
        byte[] input = new byte[]{0x68, 0x65, 0x6c, 0x6c, 0x6f}; // "hello"
        chunked.encode(input, 0, input.length, context);
        chunked.encode(input, 0, -1, context); // EOF to trigger possible separator

        byte[] out = new byte[10]; // 8 chars + CRLF
        int read = chunked.readResults(out, 0, out.length, context);
        assertEquals(10, read, "Expected 8 chars + CRLF");
        assertArrayEquals("NBSWY3DP\r\n".getBytes(StandardCharsets.US_ASCII), Arrays.copyOf(out, read));
        assertEquals(0, context.currentLinePos, "Line position should reset after separator");
    }

    /** EOF handling – leftover 1 byte (modulus 1). */
    @Test
    void encodeEofLeftoverOneByte() {
        byte[] input = new byte[]{0x61}; // 'a'
        codec.encode(input, 0, 1, context);
        codec.encode(input, 0, -1, context); // EOF

        byte[] out = new byte[8];
        int read = codec.readResults(out, 0, out.length, context);
        assertEquals(8, read);
        assertArrayEquals("ME======".getBytes(StandardCharsets.US_ASCII), Arrays.copyOf(out, read));
        assertTrue(context.eof);
        assertEquals(1, context.modulus, "Modulus should remain 1 after EOF processing of 1 byte");
    }

    /** EOF handling – leftover 2 bytes (modulus 2). */
    @Test
    void encodeEofLeftoverTwoBytes() {
        byte[] input = new byte[]{0x61, 0x62}; // "ab"
        codec.encode(input, 0, 2, context);
        codec.encode(input, 0, -1, context); // EOF

        byte[] out = new byte[8];
        int read = codec.readResults(out, 0, out.length, context);
        assertEquals(8, read);
        assertArrayEquals("MFRA====".getBytes(StandardCharsets.US_ASCII), Arrays.copyOf(out, read));
        assertTrue(context.eof);
        assertEquals(2, context.modulus);
    }

    /** EOF handling – leftover 3 bytes (modulus 3). */
    @Test
    void encodeEofLeftoverThreeBytes() {
        byte[] input = new byte[]{0x61, 0x62, 0x63}; // "abc"
        codec.encode(input, 0, 3, context);
        codec.encode(input, 0, -1, context); // EOF

        byte[] out = new byte[8];
        int read = codec.readResults(out, 0, out.length, context);
        assertEquals(8, read);
        assertArrayEquals("MFRGG===".getBytes(StandardCharsets.US_ASCII), Arrays.copyOf(out, read));
        assertTrue(context.eof);
        assertEquals(3, context.modulus);
    }

    /** EOF handling – leftover 4 bytes (modulus 4). */
    @Test
    void encodeEofLeftoverFourBytes() {
        byte[] input = new byte[]{0x61, 0x62, 0x63, 0x64}; // "abcd"
        codec.encode(input, 0, 4, context);
        codec.encode(input, 0, -1, context); // EOF

        byte[] out = new byte[8];
        int read = codec.readResults(out, 0, out.length, context);
        assertEquals(8, read);
        assertArrayEquals("MFRGGZA=".getBytes(StandardCharsets.US_ASCII), Arrays.copyOf(out, read));
        assertTrue(context.eof);
        assertEquals(4, context.modulus);
    }

    /** Negative byte handling – ensure bytes are treated as unsigned. */
    @Test
    void encodeNegativeBytes() {
        byte[] input = new byte[]{
                (byte) 0xFF, (byte) 0xEE, (byte) 0xDD, (byte) 0xCC, (byte) 0xBB
        };
        codec.encode(input, 0, input.length, context);
        codec.encode(input, 0, -1, context); // EOF (no leftover)

        byte[] out = new byte[8];
        int read = codec.readResults(out, 0, out.length, context);
        assertEquals(8, read);
        assertArrayEquals("7777777Z".getBytes(StandardCharsets.US_ASCII), Arrays.copyOf(out, read));
    }

    /** Line separator not added when currentLinePos never reaches lineLength. */
    @Test
    void encodeNoSeparatorWhenLineLengthNotReached() {
        Base32 chunked = new Base32(12, "\n".getBytes(StandardCharsets.US_ASCII));
        byte[] input = new byte[]{
                0x68, 0x65, 0x6c, 0x6c, 0x6f, // "hello"
                0x31, 0x32, 0x33, 0x34          // "1234"
        };
        chunked.encode(input, 0, input.length, context);
        chunked.encode(input, 0, -1, context); // EOF

        byte[] out = new byte[16];
        int read = chunked.readResults(out, 0, out.length, context);
        assertEquals(16, read);
        assertArrayEquals("NBSWY3DPGEZDGNBV".getBytes(StandardCharsets.US_ASCII),
                Arrays.copyOf(out, read));
        // currentLinePos should be 16 (no reset) because separator never inserted
        assertEquals(16, context.currentLinePos);
    }

    /** Null input array should throw NullPointerException. */
    @Test
    void encodeNullInputArray() {
        assertThrows(NullPointerException.class,
                () -> codec.encode(null, 0, 1, context),
                "Passing a null input array must throw NPE");
    }

    /** Null context should throw NullPointerException. */
    @Test
    void encodeNullContext() {
        byte[] input = new byte[]{0x61};
        assertThrows(NullPointerException.class,
                () -> codec.encode(input, 0, 1, null),
                "Passing a null Context must throw NPE");
    }

    /** Invalid modulus (>=5) during EOF handling should raise IllegalStateException. */
    @Test
    void encodeInvalidModulusThrows() {
        context.modulus = 5; // illegal value
        byte[] input = new byte[]{0x61};
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> codec.encode(input, 0, -1, context),
                "Illegal modulus should trigger IllegalStateException");
        assertTrue(ex.getMessage().contains("Impossible modulus"));
    }

    /** Negative modulus during EOF handling should also raise IllegalStateException. */
    @Test
    void encodeNegativeModulusThrows() {
        context.modulus = -2;
        byte[] input = new byte[]{0x61};
        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> codec.encode(input, 0, -1, context),
                "Negative modulus should trigger IllegalStateException");
        assertTrue(ex.getMessage().contains("Impossible modulus"));
    }

    /** Input array index out of bounds should throw ArrayIndexOutOfBoundsException. */
    @Test
    void encodeInputArrayIndexOutOfBounds() {
        byte[] input = new byte[]{0x01, 0x02};
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> codec.encode(input, 0, 5, context),
                "Reading beyond the input array length must throw AIOOBE");
    }

    /** Null line separator should cause NullPointerException when a separator is required. */
    @Test
    void encodeNullLineSeparatorThrows() throws Exception {
        // Construct a Base32 with a non‑null lineLength but then force lineSeparator to null via reflection.
        Base32 broken = new Base32(8, "\r\n".getBytes(StandardCharsets.US_ASCII));
        java.lang.reflect.Field sepField = Base32.class.getDeclaredField("lineSeparator");
        sepField.setAccessible(true);
        sepField.set(broken, null); // now lineSeparator is null

        byte[] input = new byte[]{0x68, 0x65, 0x6c, 0x6c, 0x6f}; // "hello"
        broken.encode(input, 0, input.length, context);
        // EOF triggers the insertion of the line separator (because currentLinePos will be 8)
        assertThrows(NullPointerException.class,
                () -> broken.encode(input, 0, -1, context),
                "Null lineSeparator should cause NPE when the encoder attempts to copy it");
    }
}
```