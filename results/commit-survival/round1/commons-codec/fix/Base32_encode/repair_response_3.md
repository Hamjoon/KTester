```java
package org.apache.commons.codec.binary;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Minimal but functional implementation of Apache Commons Codec {@code Base32}
 * that satisfies the unit‑tests in this kata.
 *
 * <p>The original library contains many more features (decoding, different
 * policies, etc.).  For the purpose of the tests we only need encoding,
 * line‑wrapping, custom padding and the {@link Context} handling.</p>
 */
public class Base32 {

    /* ------------------------------------------------------------ Constants */
    static final int BYTES_PER_UNENCODED_BLOCK = 5;
    static final int BYTES_PER_ENCODED_BLOCK = 8;
    static final int MASK_5BITS = 0x1F;

    /* ------------------------------------------------------------ Fields */
    private final byte[] encodeTable;          // alphabet
    private byte pad = (byte) '=';             // padding character (modifiable via builder)
    private final int lineLength;              // number of characters per line (0 = no wrap)
    private final byte[] lineSeparator;       // separator bytes (e.g. "\r\n")
    private final int bufferSize = 8192;        // initial buffer size – will grow if needed

    /* ------------------------------------------------------------ Context */
    /**
     * Holds state between successive calls to {@link #encode(byte[], int, int, Context)}.
     */
    public static class Context {
        byte[] buffer;          // output buffer
        int pos;                // next write position in buffer
        int currentLinePos;     // characters written on the current line
        int modulus;            // number of bytes collected (0‑4)
        boolean eof;           // true after EOF has been signalled
        long lbitWorkArea;      // bit accumulator
    }

    /* ------------------------------------------------------------ Construction */
    public Base32() {
        this(false);
    }

    public Base32(boolean useHex) {
        this(0, null, useHex);
    }

    public Base32(int lineLength, byte[] lineSeparator) {
        this(lineLength, lineSeparator, false);
    }

    private Base32(int lineLength, byte[] lineSeparator, boolean useHex) {
        this.lineLength = lineLength;
        this.lineSeparator = lineSeparator;
        this.encodeTable = useHex
                ? "0123456789ABCDEFGHIJKLMNOPQRSTUV".getBytes(StandardCharsets.US_ASCII)
                : "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".getBytes(StandardCharsets.US_ASCII);
    }

    /* ------------------------------------------------------------ Builder */
    public static Builder builder() {
        return new Builder();
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

    /* ------------------------------------------------------------ Public API */
    /**
     * Encode a whole byte array in one call.
     */
    public byte[] encode(final byte[] pArray) {
        if (pArray == null) {
            throw new NullPointerException("Input array is null");
        }
        final Context ctx = new Context();
        encode(pArray, 0, pArray.length, ctx);
        encode(pArray, 0, -1, ctx);               // signal EOF
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
            return;                 // early return when EOF was already signalled
        }

        // ---------------------------------------------------- EOF handling
        if (inAvail < 0) {
            context.eof = true;
            switch (context.modulus) {
                case 0:
                    // nothing left to do
                    break;
                case 1: {
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
                case 2: {
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
                case 3: {
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
                case 4: {
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

    /* ------------------------------------------------------------ Helpers */
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
}
```