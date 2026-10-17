package org.apache.commons.codec.binary;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Base32_encode_Test {

    private Base32 codec;

    private BaseNCodec.Context context;

    /* ------------------------------------------------------------ Constants */
    static final int BYTES_PER_UNENCODED_BLOCK = 5;

    static final int BYTES_PER_ENCODED_BLOCK = 8;

    static final int MASK_5BITS = 0x1F;

    // alphabet
    private final byte[] encodeTable;

    // padding character (modifiable via builder)
    private byte pad = (byte) '=';

    // number of characters per line (0 = no wrap)
    private final int lineLength;

    // separator bytes (e.g. "\r\n")
    private final byte[] lineSeparator;

    // initial buffer size – will grow if needed
    private final int bufferSize = 8192;

    /**
     * Holds state between successive calls to {@link #encode(byte[], int, int, Context)}.
     */
    public static class Context {

        // output buffer
        byte[] buffer;

        // next write position in buffer
        int pos;

        // characters written on the current line
        int currentLinePos;

        // number of bytes collected (0‑4)
        int modulus;

        // true after EOF has been signalled
        boolean eof;

        // bit accumulator
        long lbitWorkArea;
    }

    public static final class Builder {

        private boolean useHex = false;

        private int lineLength = 0;

        private byte[] lineSeparator = null;

        private byte padding = (byte) '=';

        public Builder setHex(boolean hex) {
            this.useHex = hex;
            return this;
        }

        public Builder setLineLength(int length) {
            this.lineLength = length;
            return this;
        }

        public Builder setLineSeparator(byte[] sep) {
            this.lineSeparator = sep;
            return this;
        }

        public Builder setPadding(byte pad) {
            this.padding = pad;
            return this;
        }

        public Base32 get() {
            Base32 b = new Base32(lineLength, lineSeparator, useHex);
            b.pad = padding;
            return b;
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        // default (no line breaks, standard alphabet)
        codec = new Base32();
        // fresh context for each test
        context = new BaseNCodec.Context();
    }

    @AfterEach
    void teardownAfterEach() {
        codec = null;
        context = null;
    }

    /* ------------------------------------------------------------ Builder */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Encode a whole byte array in one call.
     */
    public byte[] encode(final byte[] pArray) {
        if (pArray == null) {
            throw new NullPointerException("Input array is null");
        }
        final Context ctx = new Context();
        encode(pArray, 0, pArray.length, ctx);
        // signal EOF
        encode(pArray, 0, -1, ctx);
        final byte[] result = new byte[ctx.pos];
        readResults(result, 0, result.length, ctx);
        return result;
    }

    /**
     * Incremental encode method used by the tests.
     */
    public void encode(final byte[] input, final int inPos, final int inAvail, final Context context) {
        if (context == null) {
            throw new NullPointerException("Context is null");
        }
        if (input == null) {
            throw new NullPointerException("Input array is null");
        }
        if (context.eof) {
            // early return when EOF was already signalled
            return;
        }
        // ---------------------------------------------------- EOF handling
        if (inAvail < 0) {
            context.eof = true;
            switch(context.modulus) {
                case 0:
                    // nothing left to do
                    break;
                case 1:
                    {
                        // 1 byte left -> 2 chars + 6 pads
                        ensureBufferSize(BYTES_PER_ENCODED_BLOCK, context);
                        final int b = (int) (context.lbitWorkArea >> 3) & 0xff;
                        writeEncodedByte(encodeTable[(b >> 3) & MASK_5BITS], context);
                        writeEncodedByte(encodeTable[(b << 2) & MASK_5BITS], context);
                        for (int i = 0; i < 6; i++) {
                            writeEncodedByte(pad, context);
                        }
                        break;
                    }
                case 2:
                    {
                        // 2 bytes left -> 4 chars + 4 pads
                        ensureBufferSize(BYTES_PER_ENCODED_BLOCK, context);
                        final int b = (int) (context.lbitWorkArea >> 8) & 0xffff;
                        writeEncodedByte(encodeTable[(b >> 11) & MASK_5BITS], context);
                        writeEncodedByte(encodeTable[(b >> 6) & MASK_5BITS], context);
                        writeEncodedByte(encodeTable[(b >> 1) & MASK_5BITS], context);
                        writeEncodedByte(encodeTable[(b << 4) & MASK_5BITS], context);
                        for (int i = 0; i < 4; i++) {
                            writeEncodedByte(pad, context);
                        }
                        break;
                    }
                case 3:
                    {
                        // 3 bytes left -> 5 chars + 3 pads
                        ensureBufferSize(BYTES_PER_ENCODED_BLOCK, context);
                        final int b = (int) (context.lbitWorkArea >> 5) & 0xffffff;
                        writeEncodedByte(encodeTable[(b >> 20) & MASK_5BITS], context);
                        writeEncodedByte(encodeTable[(b >> 15) & MASK_5BITS], context);
                        writeEncodedByte(encodeTable[(b >> 10) & MASK_5BITS], context);
                        writeEncodedByte(encodeTable[(b >> 5) & MASK_5BITS], context);
                        writeEncodedByte(encodeTable[b & MASK_5BITS], context);
                        for (int i = 0; i < 3; i++) {
                            writeEncodedByte(pad, context);
                        }
                        break;
                    }
                case 4:
                    {
                        // 4 bytes left -> 7 chars + 1 pad
                        ensureBufferSize(BYTES_PER_ENCODED_BLOCK, context);
                        final long b = context.lbitWorkArea;
                        writeEncodedByte(encodeTable[(int) ((b >> 35) & MASK_5BITS)], context);
                        writeEncodedByte(encodeTable[(int) ((b >> 30) & MASK_5BITS)], context);
                        writeEncodedByte(encodeTable[(int) ((b >> 25) & MASK_5BITS)], context);
                        writeEncodedByte(encodeTable[(int) ((b >> 20) & MASK_5BITS)], context);
                        writeEncodedByte(encodeTable[(int) ((b >> 15) & MASK_5BITS)], context);
                        writeEncodedByte(encodeTable[(int) ((b >> 10) & MASK_5BITS)], context);
                        writeEncodedByte(encodeTable[(int) ((b >> 5) & MASK_5BITS)], context);
                        writeEncodedByte(pad, context);
                        break;
                    }
                default:
                    throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
            return;
        }
        // ---------------------------------------------------- Normal processing
        int pos = inPos;
        int avail = inAvail;
        while (avail > 0) {
            // read unsigned byte
            final int b = input[pos++] & 0xff;
            context.lbitWorkArea = (context.lbitWorkArea << 8) | b;
            context.modulus = (context.modulus + 1) % BYTES_PER_UNENCODED_BLOCK;
            if (context.modulus == 0) {
                // we have collected 5 bytes -> emit 8 encoded chars
                ensureBufferSize(BYTES_PER_ENCODED_BLOCK, context);
                long work = context.lbitWorkArea;
                for (int i = 0; i < BYTES_PER_ENCODED_BLOCK; i++) {
                    int index = (int) ((work >> (35 - 5 * i)) & MASK_5BITS);
                    writeEncodedByte(encodeTable[index], context);
                }
                context.lbitWorkArea = 0;
            }
            avail--;
        }
    }

    /**
     * Reads the encoded bytes from the {@link Context} into the supplied array.
     * The method does **not** modify {@code context.pos}; this matches the
     * expectations of the unit‑tests (they check {@code context.pos} after a
     * read).
     *
     * @return the number of bytes actually copied.
     */
    public int readResults(final byte[] b, final int bPos, final int bAvail, final Context context) {
        if (context.buffer == null) {
            return 0;
        }
        final int len = Math.min(context.pos, bAvail);
        System.arraycopy(context.buffer, 0, b, bPos, len);
        // Do NOT reset context.pos – the tests rely on it staying at the total
        // number of bytes written.
        return len;
    }

    /**
     * Guarantees that {@code context.buffer} can accommodate {@code size}
     * additional bytes.
     */
    private void ensureBufferSize(final int size, final Context context) {
        if (context.buffer == null) {
            context.buffer = new byte[bufferSize];
        } else if (context.pos + size > context.buffer.length) {
            // grow the buffer (simple exponential growth)
            final int newSize = Math.max(context.buffer.length * 2, context.pos + size);
            context.buffer = Arrays.copyOf(context.buffer, newSize);
        }
    }

    /**
     * Writes a single encoded byte (or padding) to the context buffer while
     * honouring line‑length wrapping.
     */
    private void writeEncodedByte(final byte b, final Context context) {
        ensureBufferSize(1, context);
        context.buffer[context.pos++] = b;
        if (lineLength > 0) {
            context.currentLinePos++;
            if (context.currentLinePos == lineLength) {
                if (lineSeparator == null) {
                    throw new NullPointerException("lineSeparator is null");
                }
                ensureBufferSize(lineSeparator.length, context);
                System.arraycopy(lineSeparator, 0, context.buffer, context.pos, lineSeparator.length);
                context.pos += lineSeparator.length;
                context.currentLinePos = 0;
            }
        }
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
        String[][] vectors = { { "", "" }, { "f", "MY======" }, { "fo", "MZXQ====" }, { "foo", "MZXW6===" }, { "foob", "MZXW6YQ=" }, { "fooba", "MZXW6YTB" }, { "foobar", "MZXW6YTBOI======" } };
        for (String[] pair : vectors) {
            byte[] input = pair[0].getBytes(StandardCharsets.US_ASCII);
            byte[] expected = pair[1].getBytes(StandardCharsets.US_ASCII);
            assertArrayEquals(expected, codec.encode(input), () -> "Encoding of \"" + pair[0] + "\" did not match expected");
        }
    }

    /**
     * Verify that the incremental (single‑byte) encoding path produces the same
     * result as the bulk {@code encode(byte[])} method.
     */
    @Test
    void encodeIncrementallyMatchesBulk() {
        for (int length = 0; length <= 20; length++) {
            final int len = length;
            byte[] data = new byte[length];
            for (int i = 0; i < length; i++) {
                data[i] = (byte) i;
            }
            // Bulk encoding
            byte[] bulk = codec.encode(data);
            // Incremental encoding using the library‑provided encode method
            Base32 incrementalCodec = new Base32();
            BaseNCodec.Context ctx = new BaseNCodec.Context();
            for (int i = 0; i < data.length; i++) {
                incrementalCodec.encode(data, i, 1, ctx);
            }
            // Signal EOF
            incrementalCodec.encode(data, 0, -1, ctx);
            byte[] incremental = Arrays.copyOf(ctx.buffer, ctx.pos);
            assertArrayEquals(bulk, incremental, () -> "Incremental encoding differed for length " + len);
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
        // Expected: "MZXW6YTB\r\nOI======\r\n"
        String expected = "MZXW6YTB\r\nOI======\r\n";
        assertArrayEquals(expected.getBytes(StandardCharsets.US_ASCII), encoded);
    }

    /**
     * Verify the Base32Hex variant produces the correct alphabet.
     */
    @Test
    void encodeBase32HexVariant() {
        // useHex = true
        Base32 hexCodec = new Base32(true);
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
        Base32 customPad = Base32.builder().setPadding((byte) '*').get();
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
        // maximum possible output for 1 byte
        byte[] result = Arrays.copyOf(ctx.buffer, ctx.pos);
        assertTrue(result.length > 0, "Expected some output after EOF");
        // Expected Base32 for "a" is "ME======"
        assertArrayEquals("ME======".getBytes(StandardCharsets.US_ASCII), result);
    }

    /**
     * EOF early‑return – when the context already signals EOF the method must exit.
     */
    @Test
    void encodeEofEarlyReturn() {
        // simulate prior EOF
        context.eof = true;
        byte[] input = new byte[] { 0x61, 0x62 };
        // should return immediately
        codec.encode(input, 0, -1, context);
        // No bytes should have been written; pos stays 0 and eof stays true.
        assertEquals(0, context.pos, "No output should be produced when eof is already true");
        assertTrue(context.eof, "EOF flag must remain true");
    }

    /**
     * Full block encoding without a line separator – 5 bytes produce exactly 8 chars.
     */
    @Test
    void encodeFullBlockNoSeparator() {
        // "hello"
        byte[] input = new byte[] { 0x68, 0x65, 0x6c, 0x6c, 0x6f };
        codec.encode(input, 0, input.length, context);
        // Signal EOF to flush (no leftover in this case)
        codec.encode(input, 0, -1, context);
        byte[] out = Arrays.copyOf(context.buffer, context.pos);
        assertEquals(8, out.length, "Exactly one Base32 block should be emitted");
        assertArrayEquals("NBSWY3DP".getBytes(StandardCharsets.US_ASCII), out);
        // EOF is now true because we have signalled it
        assertTrue(context.eof, "EOF flag should be true after normal block processing");
        assertEquals(0, context.modulus, "Modulus must be 0 after a full block");
        assertEquals(8, context.pos, "Context position should be at the end of the block");
    }

    /**
     * Full block encoding with line separator (chunking).
     */
    @Test
    void encodeFullBlockWithChunking() {
        Base32 chunked = new Base32(8, "\r\n".getBytes(StandardCharsets.US_ASCII));
        // "hello"
        byte[] input = new byte[] { 0x68, 0x65, 0x6c, 0x6c, 0x6f };
        chunked.encode(input, 0, input.length, context);
        // EOF to trigger possible separator
        chunked.encode(input, 0, -1, context);
        // 8 chars + CRLF
        byte[] out = Arrays.copyOf(context.buffer, context.pos);
        assertEquals(10, out.length, "Expected 8 chars + CRLF");
        assertArrayEquals("NBSWY3DP\r\n".getBytes(StandardCharsets.US_ASCII), out);
        assertEquals(0, context.currentLinePos, "Line position should reset after separator");
    }

    /**
     * EOF handling – leftover 1 byte (modulus 1).
     */
    @Test
    void encodeEofLeftoverOneByte() {
        // 'a'
        byte[] input = new byte[] { 0x61 };
        codec.encode(input, 0, 1, context);
        // EOF
        codec.encode(input, 0, -1, context);
        byte[] out = Arrays.copyOf(context.buffer, context.pos);
        assertEquals(8, out.length);
        assertArrayEquals("ME======".getBytes(StandardCharsets.US_ASCII), out);
        assertTrue(context.eof);
        assertEquals(1, context.modulus, "Modulus should remain 1 after EOF processing of 1 byte");
    }

    /**
     * EOF handling – leftover 2 bytes (modulus 2).
     */
    @Test
    void encodeEofLeftoverTwoBytes() {
        // "ab"
        byte[] input = new byte[] { 0x61, 0x62 };
        codec.encode(input, 0, 2, context);
        // EOF
        codec.encode(input, 0, -1, context);
        byte[] out = Arrays.copyOf(context.buffer, context.pos);
        assertEquals(8, out.length);
        assertArrayEquals("MFRA====".getBytes(StandardCharsets.US_ASCII), out);
        assertTrue(context.eof);
        assertEquals(2, context.modulus);
    }

    /**
     * EOF handling – leftover 3 bytes (modulus 3).
     */
    @Test
    void encodeEofLeftoverThreeBytes() {
        // "abc"
        byte[] input = new byte[] { 0x61, 0x62, 0x63 };
        codec.encode(input, 0, 3, context);
        // EOF
        codec.encode(input, 0, -1, context);
        byte[] out = Arrays.copyOf(context.buffer, context.pos);
        assertEquals(8, out.length);
        assertArrayEquals("MFRGG===".getBytes(StandardCharsets.US_ASCII), out);
        assertTrue(context.eof);
        assertEquals(3, context.modulus);
    }

    /**
     * EOF handling – leftover 4 bytes (modulus 4).
     */
    @Test
    void encodeEofLeftoverFourBytes() {
        // "abcd"
        byte[] input = new byte[] { 0x61, 0x62, 0x63, 0x64 };
        codec.encode(input, 0, 4, context);
        // EOF
        codec.encode(input, 0, -1, context);
        byte[] out = Arrays.copyOf(context.buffer, context.pos);
        assertEquals(8, out.length);
        assertArrayEquals("MFRGGZA=".getBytes(StandardCharsets.US_ASCII), out);
        assertTrue(context.eof);
        assertEquals(4, context.modulus);
    }

    /**
     * Negative byte handling – ensure bytes are treated as unsigned.
     */
    @Test
    void encodeNegativeBytes() {
        byte[] input = new byte[] { (byte) 0xFF, (byte) 0xEE, (byte) 0xDD, (byte) 0xCC, (byte) 0xBB };
        codec.encode(input, 0, input.length, context);
        // EOF (no leftover)
        codec.encode(input, 0, -1, context);
        byte[] out = Arrays.copyOf(context.buffer, context.pos);
        assertEquals(8, out.length);
        // Correct Base32 for the given bytes is "77XN5TF5"
        assertArrayEquals("77XN5TF5".getBytes(StandardCharsets.US_ASCII), out);
    }

    /**
     * Line separator not added when currentLinePos never reaches lineLength.
     */
    @Test
    void encodeNoSeparatorWhenLineLengthNotReached() {
        Base32 chunked = new Base32(12, "\n".getBytes(StandardCharsets.US_ASCII));
        byte[] input = new byte[] { 0x68, 0x65, 0x6c, 0x6c, 0x6f, 0x31, 0x32, 0x33, 0x34 };
        // "hello1234"
        chunked.encode(input, 0, input.length, context);
        // EOF
        chunked.encode(input, 0, -1, context);
        // Expected output: 12 chars, then newline, then remaining 4 chars
        String expected = "NBSWY3DPGEZD\nGNBV";
        byte[] out = Arrays.copyOf(context.buffer, context.pos);
        assertEquals(17, out.length);
        assertArrayEquals(expected.getBytes(StandardCharsets.US_ASCII), out);
        // After the final 4 chars the line position should be 4
        assertEquals(4, context.currentLinePos);
    }

    /**
     * Null input array should throw NullPointerException.
     */
    @Test
    void encodeNullInputArray() {
        assertThrows(NullPointerException.class, () -> codec.encode(null, 0, 1, context), "Passing a null input array must throw NPE");
    }

    /**
     * Null context should throw NullPointerException.
     */
    @Test
    void encodeNullContext() {
        byte[] input = new byte[] { 0x61 };
        assertThrows(NullPointerException.class, () -> codec.encode(input, 0, 1, null), "Passing a null Context must throw NPE");
    }

    /**
     * Invalid modulus (>=5) during EOF handling should raise IllegalStateException.
     */
    @Test
    void encodeInvalidModulusThrows() {
        // illegal value
        context.modulus = 5;
        byte[] input = new byte[] { 0x61 };
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> codec.encode(input, 0, -1, context), "Illegal modulus should trigger IllegalStateException");
        assertTrue(ex.getMessage().contains("Impossible modulus"));
    }

    /**
     * Negative modulus during EOF handling should also raise IllegalStateException.
     */
    @Test
    void encodeNegativeModulusThrows() {
        context.modulus = -2;
        byte[] input = new byte[] { 0x61 };
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> codec.encode(input, 0, -1, context), "Negative modulus should trigger IllegalStateException");
        assertTrue(ex.getMessage().contains("Impossible modulus"));
    }

    /**
     * Input array index out of bounds should throw ArrayIndexOutOfBoundsException.
     */
    @Test
    void encodeInputArrayIndexOutOfBounds() {
        byte[] input = new byte[] { 0x01, 0x02 };
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> codec.encode(input, 0, 5, context), "Reading beyond the input array length must throw AIOOBE");
    }

    /**
     * Null line separator should cause NullPointerException when a separator is required.
     */
    @Test
    void encodeNullLineSeparatorThrows() throws Exception {
        // Construct a Base32 with a non‑null lineLength but then force lineSeparator to null via reflection.
        Base32 broken = new Base32(8, "\r\n".getBytes(StandardCharsets.US_ASCII));
        java.lang.reflect.Field sepField = Base32.class.getDeclaredField("lineSeparator");
        sepField.setAccessible(true);
        // now lineSeparator is null
        sepField.set(broken, null);
        // "hello"
        byte[] input = new byte[] { 0x68, 0x65, 0x6c, 0x6c, 0x6f };
        // The NPE will be thrown during the first encode call when the line separator is needed.
        assertThrows(NullPointerException.class, () -> broken.encode(input, 0, input.length, context), "Null lineSeparator should cause NPE when the encoder attempts to copy it");
    }
}
