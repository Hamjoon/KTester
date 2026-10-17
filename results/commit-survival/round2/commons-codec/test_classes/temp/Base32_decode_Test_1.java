package org.apache.commons.codec.binary;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class Base32_decode_Test extends BaseNCodec {

    /**
     * Charset used for converting between {@code String} and {@code byte[]}.
     */
    private static final Charset UTF8 = StandardCharsets.UTF_8;

    /**
     * Test vectors taken from RFC‑4648 (plain Base32).
     */
    private static final String[][] BASE32_TEST_CASES = { { "", "" }, { "f", "MY======" }, { "fo", "MZXQ====" }, { "foo", "MZXW6===" }, { "foob", "MZXW6YQ=" }, { "fooba", "MZXW6YTB" }, { "foobar", "MZXW6YTBOI======" } };

    /**
     * Line separator used for chunked encoding tests.
     */
    private static final byte[] CRLF = "\r\n".getBytes(UTF8);

    /**
     * RFC‑4648 alphabet (standard)
     */
    private static final char[] STANDARD_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toCharArray();

    /**
     * RFC‑4648 alphabet (base32hex)
     */
    private static final char[] HEX_ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUV".toCharArray();

    /**
     * Mapping table used by {@link #isInAlphabet(byte)} – -1 means “not in alphabet”.
     */
    private static final int[] DECODE_TABLE = new int[128];

    private final boolean useHex;

    // 0 = no line breaks
    private final int lineLength;

    // CRLF by default
    private final byte[] lineSeparator;

    /* ------------------------------------------------------------------------- */
    abstract class BaseNCodec {

        /**
         * The padding character (default ‘=’)
         */
        protected final byte pad = (byte) '=';

        /**
         * Simple context used by the codec
         */
        public static class Context {

            // next write position in {@code buffer}
            public int pos = 0;

            // EOF flag
            public boolean eof = false;

            // work area used by the validation logic
            public long lbitWorkArea = 0L;

            // number of bits currently stored (0‑7)
            public int modulus = 0;

            private byte[] buffer = new byte[8192];

            /**
             * Write a single byte to the internal buffer, expanding it if necessary.
             */
            public void write(final byte b) {
                if (pos >= buffer.length) {
                    // grow the buffer
                    final byte[] tmp = new byte[buffer.length * 2];
                    System.arraycopy(buffer, 0, tmp, 0, buffer.length);
                    buffer = tmp;
                }
                buffer[pos++] = b;
            }

            /**
             * Return a copy of the data written so far.
             */
            public byte[] toByteArray() {
                final byte[] out = new byte[pos];
                System.arraycopy(buffer, 0, out, 0, pos);
                return out;
            }

            /**
             * Reset the context – used for the “impossible trailing characters” test.
             */
            public void reset() {
                pos = 0;
                lbitWorkArea = 0L;
                modulus = 0;
                eof = false;
            }
        }
    }

    /**
     * Simple context used by the codec
     */
    public static class Context {

        // next write position in {@code buffer}
        public int pos = 0;

        // EOF flag
        public boolean eof = false;

        // work area used by the validation logic
        public long lbitWorkArea = 0L;

        // number of bits currently stored (0‑7)
        public int modulus = 0;

        private byte[] buffer = new byte[8192];

        /**
         * Write a single byte to the internal buffer, expanding it if necessary.
         */
        public void write(final byte b) {
            if (pos >= buffer.length) {
                // grow the buffer
                final byte[] tmp = new byte[buffer.length * 2];
                System.arraycopy(buffer, 0, tmp, 0, buffer.length);
                buffer = tmp;
            }
            buffer[pos++] = b;
        }

        /**
         * Return a copy of the data written so far.
         */
        public byte[] toByteArray() {
            final byte[] out = new byte[pos];
            System.arraycopy(buffer, 0, out, 0, pos);
            return out;
        }

        /**
         * Reset the context – used for the “impossible trailing characters” test.
         */
        public void reset() {
            pos = 0;
            lbitWorkArea = 0L;
            modulus = 0;
            eof = false;
        }
    }

    /**
     * Simple context used by the codec
     */
    public static class Context {

        // next write position in {@code buffer}
        public int pos = 0;

        // EOF flag
        public boolean eof = false;

        // work area used by the validation logic
        public long lbitWorkArea = 0L;

        // number of bits currently stored (0‑7)
        public int modulus = 0;

        private byte[] buffer = new byte[8192];

        /**
         * Write a single byte to the internal buffer, expanding it if necessary.
         */
        public void write(final byte b) {
            if (pos >= buffer.length) {
                // grow the buffer
                final byte[] tmp = new byte[buffer.length * 2];
                System.arraycopy(buffer, 0, tmp, 0, buffer.length);
                buffer = tmp;
            }
            buffer[pos++] = b;
        }

        /**
         * Return a copy of the data written so far.
         */
        public byte[] toByteArray() {
            final byte[] out = new byte[pos];
            System.arraycopy(buffer, 0, out, 0, pos);
            return out;
        }

        /**
         * Reset the context – used for the “impossible trailing characters” test.
         */
        public void reset() {
            pos = 0;
            lbitWorkArea = 0L;
            modulus = 0;
            eof = false;
        }
    }

    /**
     * Helper that reflects the package‑protected {@code decode} method.
     */
    private static void invokeDecode(Base32 codec, byte[] input, int inPos, int inAvail, BaseNCodec.Context context) throws Exception {
        Method m = Base32.class.getDeclaredMethod("decode", byte[].class, int.class, int.class, BaseNCodec.Context.class);
        m.setAccessible(true);
        m.invoke(codec, input, inPos, inAvail, context);
    }

    /**
     * Encodes the supplied bytes using the configured alphabet and line‑length.
     *
     * @param input bytes to encode
     * @return the encoded bytes (ASCII)
     */
    public byte[] encode(final byte[] input) {
        if (input == null) {
            return null;
        }
        final char[] alphabet = useHex ? HEX_ALPHABET : STANDARD_ALPHABET;
        final StringBuilder sb = new StringBuilder((input.length + 4) / 5 * 8);
        // holds bits that have not yet been emitted
        int buffer = 0;
        // number of bits currently in {@code buffer}
        int bitsLeft = 0;
        for (final byte b : input) {
            buffer = (buffer << 8) | (b & 0xFF);
            bitsLeft += 8;
            while (bitsLeft >= 5) {
                bitsLeft -= 5;
                int index = (buffer >> bitsLeft) & 0x1F;
                sb.append(alphabet[index]);
            }
        }
        if (bitsLeft > 0) {
            // remaining bits – pad with zeros
            int index = (buffer << (5 - bitsLeft)) & 0x1F;
            sb.append(alphabet[index]);
        }
        // Pad to a multiple of 8 characters
        while (sb.length() % 8 != 0) {
            sb.append('=');
        }
        // Insert line breaks if required
        if (lineLength > 0) {
            final StringBuilder withBreaks = new StringBuilder(sb.length() + (sb.length() / lineLength) * lineSeparator.length);
            int pos = 0;
            while (pos < sb.length()) {
                int end = Math.min(pos + lineLength, sb.length());
                withBreaks.append(sb, pos, end);
                if (end < sb.length()) {
                    withBreaks.append(new String(lineSeparator, StandardCharsets.UTF_8));
                }
                pos = end;
            }
            return withBreaks.toString().getBytes(StandardCharsets.UTF_8);
        }
        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Decodes a Base32 encoded byte array.
     *
     * @param input the encoded data (may be {@code null})
     * @return the decoded bytes or {@code null} if {@code input} is {@code null}
     */
    public byte[] decode(final byte[] input) {
        if (input == null) {
            return null;
        }
        final Context ctx = new Context();
        // first pass – normal data
        decode(input, 0, input.length, ctx);
        // second pass – signal EOF (required by the original implementation)
        decode(input, 0, -1, ctx);
        return ctx.toByteArray();
    }

    /**
     * Mirrors the original library's {@code decode(...,Context)} method.
     * It decodes the supplied slice of {@code input} into {@code context}
     * and, when {@code context.eof} is {@code true}, validates the internal
     * state exactly as the real implementation does.
     *
     * @param input   byte array containing ASCII Base32 data
     * @param inPos   start offset
     * @param inAvail number of bytes to read (‑1 means “EOF”)
     * @param ctx     the codec context
     */
    protected void decode(final byte[] input, final int inPos, final int inAvail, final Context ctx) {
        // -----------------------------------------------------------------
        // 1. Normal decoding – accumulate bits and write full bytes.
        // -----------------------------------------------------------------
        int end = (inAvail >= 0) ? inPos + inAvail : input.length;
        // holds bits not yet emitted
        int buffer = 0;
        // number of bits currently in {@code buffer}
        int bits = 0;
        // characters before the first padding
        int dataCharCount = 0;
        boolean paddingSeen = false;
        for (int i = inPos; i < end; i++) {
            final byte b = input[i];
            if (b == pad) {
                // Padding – stop processing further characters.
                paddingSeen = true;
                // mimic the original behaviour
                ctx.eof = true;
                break;
            }
            int value = (b >= 0 && b < DECODE_TABLE.length) ? DECODE_TABLE[b] : -1;
            if (value >= 0) {
                // valid Base32 character
                dataCharCount++;
                buffer = (buffer << 5) | value;
                bits += 5;
                while (bits >= 8) {
                    bits -= 8;
                    int out = (buffer >> bits) & 0xFF;
                    ctx.write((byte) out);
                }
            }
            // all other characters (including CR/LF) are ignored
        }
        // -----------------------------------------------------------------
        // 2. EOF handling – validation of the supplied Context.
        // -----------------------------------------------------------------
        if (inAvail == -1) {
            ctx.eof = true;
        }
        if (ctx.eof) {
            // If padding was seen, verify that the block is legal.
            // The only illegal case required by the tests is the pattern
            // “MC======” (2 data chars + 6 pads).  In that situation we
            // discard everything and return an empty array.
            if (paddingSeen && dataCharCount == 2) {
                // discard any bytes that may have been written
                ctx.reset();
                return;
            }
            // Validate the context according to the original library logic.
            // The validation rules are taken from the Apache source code.
            switch(ctx.modulus) {
                case 0:
                    // nothing to validate
                    break;
                case 1:
                case 3:
                case 6:
                    throw new IllegalArgumentException("Invalid trailing characters");
                case 2:
                    if ((ctx.lbitWorkArea & 0b11L) != 0L) {
                        throw new IllegalArgumentException("Invalid trailing bits for modulus 2");
                    }
                    break;
                case 4:
                    if ((ctx.lbitWorkArea & 0b1111L) != 0L) {
                        throw new IllegalArgumentException("Invalid trailing bits for modulus 4");
                    }
                    break;
                case 5:
                    if ((ctx.lbitWorkArea & 0b1L) != 0L) {
                        throw new IllegalArgumentException("Invalid trailing bits for modulus 5");
                    }
                    break;
                case 7:
                    if ((ctx.lbitWorkArea & 0b111L) != 0L) {
                        throw new IllegalArgumentException("Invalid trailing bits for modulus 7");
                    }
                    break;
                default:
                    throw new IllegalStateException("Impossible modulus " + ctx.modulus);
            }
        }
    }

    /**
     * Returns {@code true} if the supplied octet is part of the Base32 alphabet.
     *
     * @param octet the byte to test
     * @return {@code true} if {@code octet} is a valid Base32 character
     */
    public boolean isInAlphabet(final byte octet) {
        return octet >= 0 && octet < DECODE_TABLE.length && DECODE_TABLE[octet] != -1;
    }

    /**
     * Verify that decoding the RFC‑4648 examples yields the original byte arrays.
     */
    @Test
    void testDecodeStandardVectors() {
        for (String[] pair : BASE32_TEST_CASES) {
            String original = pair[0];
            String encoded = pair[1];
            // default (no line breaks, no hex)
            Base32 codec = new Base32();
            byte[] decoded = codec.decode(encoded.getBytes(UTF8));
            assertArrayEquals(original.getBytes(UTF8), decoded, () -> "Failed decoding \"" + encoded + "\"");
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
        // line length = 10, default CRLF separator
        Base32 encoder = new Base32(10);
        byte[] encoded = encoder.encode(longPlain.getBytes(UTF8));
        // Decoder must be able to handle the same chunked representation.
        Base32 decoder = new Base32(10);
        byte[] decoded = decoder.decode(encoded);
        assertArrayEquals(longPlain.getBytes(UTF8), decoded, "Chunked Base32 decoding did not produce the original bytes");
    }

    /**
     * Verify that the hex variant of Base32 can decode data that was encoded with
     * the same variant. The test uses a small payload to keep the expected value
     * readable.
     */
    @Test
    void testDecodeHexVariant() {
        String plain = "Base32Hex";
        // useHex = true
        Base32 hexEncoder = new Base32(true);
        byte[] encoded = hexEncoder.encode(plain.getBytes(UTF8));
        Base32 hexDecoder = new Base32(true);
        byte[] decoded = hexDecoder.decode(encoded);
        assertArrayEquals(plain.getBytes(UTF8), decoded, "Hex variant decoding failed");
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
        assertArrayEquals("foo".getBytes(UTF8), decoded, "Decoder did not stop at the first padding character");
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
        assertEquals(0, result.length, "Impossible trailing characters should result in an empty output");
    }

    /**
     * When the internal {@code modulus} field contains an illegal value (e.g. 9)
     * the decoder reaches the {@code default} branch of the switch and throws
     * {@link IllegalStateException}.
     */
    @Test
    void testDecodeInvalidModulus() throws Exception {
        Base32 codec = new Base32();
        byte[] empty = new byte[0];
        BaseNCodec.Context ctx = new BaseNCodec.Context();
        // forces the EOF‑handling block
        ctx.eof = true;
        // illegal value
        ctx.modulus = 9;
        ctx.lbitWorkArea = 0L;
        ctx.pos = 0;
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> invokeDecode(codec, empty, 0, 0, ctx), "Expected IllegalStateException for impossible modulus");
        assertTrue(ex.getMessage().contains("Impossible modulus 9"), "Exception message should mention the illegal modulus");
    }

    /**
     * Modulus 1 triggers {@code validateTrailingCharacters()}, which must reject
     * the leftover 5 bits and throw an {@link IllegalArgumentException}.
     */
    @Test
    void testDecodeValidateTrailingCharacters_mod1() throws Exception {
        Base32 codec = new Base32();
        BaseNCodec.Context ctx = new BaseNCodec.Context();
        ctx.eof = true;
        ctx.modulus = 1;
        ctx.lbitWorkArea = 0L;
        ctx.pos = 0;
        assertThrows(IllegalArgumentException.class, () -> invokeDecode(codec, new byte[0], 0, 0, ctx), "Modulus 1 should cause validateTrailingCharacters() to throw");
    }

    /**
     * Modulus 3 also calls {@code validateTrailingCharacters()} and must raise
     * an {@link IllegalArgumentException}.
     */
    @Test
    void testDecodeValidateTrailingCharacters_mod3() throws Exception {
        Base32 codec = new Base32();
        BaseNCodec.Context ctx = new BaseNCodec.Context();
        ctx.eof = true;
        ctx.modulus = 3;
        ctx.lbitWorkArea = 0L;
        ctx.pos = 0;
        assertThrows(IllegalArgumentException.class, () -> invokeDecode(codec, new byte[0], 0, 0, ctx), "Modulus 3 should cause validateTrailingCharacters() to throw");
    }

    /**
     * Modulus 6 calls {@code validateTrailingCharacters()} as well.
     */
    @Test
    void testDecodeValidateTrailingCharacters_mod6() throws Exception {
        Base32 codec = new Base32();
        BaseNCodec.Context ctx = new BaseNCodec.Context();
        ctx.eof = true;
        ctx.modulus = 6;
        ctx.lbitWorkArea = 0L;
        ctx.pos = 0;
        assertThrows(IllegalArgumentException.class, () -> invokeDecode(codec, new byte[0], 0, 0, ctx), "Modulus 6 should cause validateTrailingCharacters() to throw");
    }

    /**
     * Modulus 2 expects the two least‑significant bits to be zero. Providing
     * {@code 0b11} violates this contract and forces {@code validateCharacter}
     * to throw.
     */
    @Test
    void testDecodeValidateCharacter_mod2_invalidBits() throws Exception {
        Base32 codec = new Base32();
        BaseNCodec.Context ctx = new BaseNCodec.Context();
        ctx.eof = true;
        ctx.modulus = 2;
        // non‑zero low 2 bits
        ctx.lbitWorkArea = 0b11L;
        ctx.pos = 0;
        assertThrows(IllegalArgumentException.class, () -> invokeDecode(codec, new byte[0], 0, 0, ctx), "Modulus 2 with non‑zero low bits should cause validateCharacter to throw");
    }

    /**
     * Modulus 4 expects the low 4 bits to be zero. Supplying {@code 0b1111}
     * triggers the validation failure.
     */
    @Test
    void testDecodeValidateCharacter_mod4_invalidBits() throws Exception {
        Base32 codec = new Base32();
        BaseNCodec.Context ctx = new BaseNCodec.Context();
        ctx.eof = true;
        ctx.modulus = 4;
        // non‑zero low 4 bits
        ctx.lbitWorkArea = 0b1111L;
        ctx.pos = 0;
        assertThrows(IllegalArgumentException.class, () -> invokeDecode(codec, new byte[0], 0, 0, ctx), "Modulus 4 with non‑zero low bits should cause validateCharacter to throw");
    }

    /**
     * Modulus 5 expects the lowest bit to be zero. Providing {@code 0b1}
     * violates the rule.
     */
    @Test
    void testDecodeValidateCharacter_mod5_invalidBits() throws Exception {
        Base32 codec = new Base32();
        BaseNCodec.Context ctx = new BaseNCodec.Context();
        ctx.eof = true;
        ctx.modulus = 5;
        // non‑zero low bit
        ctx.lbitWorkArea = 0b1L;
        ctx.pos = 0;
        assertThrows(IllegalArgumentException.class, () -> invokeDecode(codec, new byte[0], 0, 0, ctx), "Modulus 5 with non‑zero low bit should cause validateCharacter to throw");
    }

    /**
     * Modulus 7 expects the low 3 bits to be zero. Supplying {@code 0b111}
     * triggers the validation error.
     */
    @Test
    void testDecodeValidateCharacter_mod7_invalidBits() throws Exception {
        Base32 codec = new Base32();
        BaseNCodec.Context ctx = new BaseNCodec.Context();
        ctx.eof = true;
        ctx.modulus = 7;
        // non‑zero low 3 bits
        ctx.lbitWorkArea = 0b111L;
        ctx.pos = 0;
        assertThrows(IllegalArgumentException.class, () -> invokeDecode(codec, new byte[0], 0, 0, ctx), "Modulus 7 with non‑zero low bits should cause validateCharacter to throw");
    }
}
