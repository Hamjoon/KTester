
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Base32_encode_Test {

    private Base32 codec;

    private BaseNCodec.Context context;

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
            byte[] data = new byte[length];
            for (int i = 0; i < length; i++) {
                // deterministic data
                data[i] = (byte) i;
            }
            // Bulk encoding
            byte[] bulk = codec.encode(data);
            // Incremental encoding using the package‑protected encode method
            // fresh instance
            Base32 incrementalCodec = new Base32();
            BaseNCodec.Context ctx = new BaseNCodec.Context();
            for (int i = 0; i < data.length; i++) {
                incrementalCodec.encode(data, i, 1, ctx);
            }
            // Signal EOF
            incrementalCodec.encode(data, 0, -1, ctx);
            byte[] incremental = new byte[bulk.length];
            incrementalCodec.readResults(incremental, 0, incremental.length, ctx);
            assertArrayEquals(bulk, incremental, () -> "Incremental encoding differed for length " + length);
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
        String expected = "MZXW6YTB\r\nOI======";
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
        Base32 customPad = Base32.builder().setPadding('*').get();
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
        byte[] result = new byte[8];
        int read = codec.readResults(result, 0, result.length, ctx);
        assertTrue(read > 0, "Expected some output after EOF");
        // Trim the array to the actual length read
        byte[] trimmed = Arrays.copyOf(result, read);
        // Expected Base32 for "a" is "ME======"
        assertArrayEquals("ME======".getBytes(StandardCharsets.US_ASCII), trimmed);
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
        byte[] out = new byte[8];
        int read = codec.readResults(out, 0, out.length, context);
        assertEquals(8, read, "Exactly one Base32 block should be emitted");
        assertArrayEquals("NBSWY3DP".getBytes(StandardCharsets.US_ASCII), Arrays.copyOf(out, read));
        assertFalse(context.eof, "EOF flag should stay false after normal block processing");
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
        byte[] out = new byte[10];
        int read = chunked.readResults(out, 0, out.length, context);
        assertEquals(10, read, "Expected 8 chars + CRLF");
        assertArrayEquals("NBSWY3DP\r\n".getBytes(StandardCharsets.US_ASCII), Arrays.copyOf(out, read));
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
        byte[] out = new byte[8];
        int read = codec.readResults(out, 0, out.length, context);
        assertEquals(8, read);
        assertArrayEquals("ME======".getBytes(StandardCharsets.US_ASCII), Arrays.copyOf(out, read));
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
        byte[] out = new byte[8];
        int read = codec.readResults(out, 0, out.length, context);
        assertEquals(8, read);
        assertArrayEquals("MFRA====".getBytes(StandardCharsets.US_ASCII), Arrays.copyOf(out, read));
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
        byte[] out = new byte[8];
        int read = codec.readResults(out, 0, out.length, context);
        assertEquals(8, read);
        assertArrayEquals("MFRGG===".getBytes(StandardCharsets.US_ASCII), Arrays.copyOf(out, read));
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
        byte[] out = new byte[8];
        int read = codec.readResults(out, 0, out.length, context);
        assertEquals(8, read);
        assertArrayEquals("MFRGGZA=".getBytes(StandardCharsets.US_ASCII), Arrays.copyOf(out, read));
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
        byte[] out = new byte[8];
        int read = codec.readResults(out, 0, out.length, context);
        assertEquals(8, read);
        assertArrayEquals("7777777Z".getBytes(StandardCharsets.US_ASCII), Arrays.copyOf(out, read));
    }

    /**
     * Line separator not added when currentLinePos never reaches lineLength.
     */
    @Test
    void encodeNoSeparatorWhenLineLengthNotReached() {
        Base32 chunked = new Base32(12, "\n".getBytes(StandardCharsets.US_ASCII));
        byte[] input = new byte[] { // "hello"
        0x68, // "hello"
        0x65, // "hello"
        0x6c, // "hello"
        0x6c, // "hello"
        0x6f, // "1234"
        0x31, // "1234"
        0x32, // "1234"
        0x33, // "1234"
        0x34 };
        chunked.encode(input, 0, input.length, context);
        // EOF
        chunked.encode(input, 0, -1, context);
        byte[] out = new byte[16];
        int read = chunked.readResults(out, 0, out.length, context);
        assertEquals(16, read);
        assertArrayEquals("NBSWY3DPGEZDGNBV".getBytes(StandardCharsets.US_ASCII), Arrays.copyOf(out, read));
        // currentLinePos should be 16 (no reset) because separator never inserted
        assertEquals(16, context.currentLinePos);
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
        broken.encode(input, 0, input.length, context);
        // EOF triggers the insertion of the line separator (because currentLinePos will be 8)
        assertThrows(NullPointerException.class, () -> broken.encode(input, 0, -1, context), "Null lineSeparator should cause NPE when the encoder attempts to copy it");
    }
}
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/org/apache/commons/codec/binary/Base32_encode_Test.java:127: error: incompatible types: possible lossy conversion from char to byte
        Base32 customPad = Base32.builder().setPadding('*').get();
                                                       ^
Note: Some messages have been simplified; recompile with -Xdiags:verbose to get full output
1 error

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class org.apache.commons.codec.binary.Base32': '\n * Provides Base32 encoding and decoding as defined by <a href="http://www.ietf.org/rfc/rfc4648.txt">RFC 4648</a>.\n *\n * <p>\n * The class can be parameterized in the following manner with various constructors:\n * </p>\n * <ul>\n * <li>Whether to use the "base32hex" variant instead of the default "base32"</li>\n * <li>Line length: Default 76. Line length that aren\'t multiples of 8 will still essentially end up being multiples of 8 in the encoded data.\n * <li>Line separator: Default is CRLF ("\\r\\n")</li>\n * </ul>\n * <p>\n * This class operates directly on byte streams, and not character streams.\n * </p>\n * <p>\n * This class is thread-safe.\n * </p>\n * <p>\n * You can configure instances with the {@link Builder}.\n * </p>\n * <pre>\n * Base32 base32 = Base32.builder()\n *   .setDecodingPolicy(DecodingPolicy.LENIENT) // default is lenient\n *   .setEncodeTable(customEncodeTable)\n *   .setLineLength(0)                          // default is none\n *   .setLineSeparator(\'\\r\', \'\\n\')              // default is CR LF\n *   .setPadding(\'=\')                           // default is =\n *   .get()\n * </pre>\n *\n * @see <a href="http://www.ietf.org/rfc/rfc4648.txt">RFC 4648</a>\n * @since 1.5\n ', 'api document of method encode(byte[], int, int, Context)': '\n     * <p>\n     * Encodes all of the provided data, starting at inPos, for inAvail bytes. Must be called at least twice: once with the data to encode, and once with\n     * inAvail set to "-1" to alert encoder that EOF has been reached, so flush last remaining bytes (if not multiple of 5).\n     * </p>\n     *\n     * @param input   byte[] array of binary data to Base32 encode.\n     * @param inPos   Position to start reading data from.\n     * @param inAvail Amount of bytes available from input for encoding.\n     * @param context the context to be used\n     ', 'dependent classes': 'class `org.apache.commons.codec.binary.BaseNCodec.Context`:\n\tdependent fields:\n\t\tint org.apache.commons.codec.binary.BaseNCodec.Context.currentLinePos;\n\t\tlong org.apache.commons.codec.binary.BaseNCodec.Context.lbitWorkArea;\n\t\tint org.apache.commons.codec.binary.BaseNCodec.Context.pos;\n\t\tint org.apache.commons.codec.binary.BaseNCodec.Context.modulus;\n\t\tboolean org.apache.commons.codec.binary.BaseNCodec.Context.eof;\n\t\nclass `org.apache.commons.codec.binary.BaseNCodec`:\n\tdependent fields:\n\t\tbyte org.apache.commons.codec.binary.BaseNCodec.pad;\n\t\tint org.apache.commons.codec.binary.BaseNCodec.lineLength;\n\t\nclass `org.apache.commons.codec.binary.Base32`:\n\tdependent fields:\n\t\tbyte[] org.apache.commons.codec.binary.Base32.lineSeparator;\n\t\tint org.apache.commons.codec.binary.Base32.encodeSize;\n\t\tint org.apache.commons.codec.binary.Base32.MASK_5BITS;\n\t\tint org.apache.commons.codec.binary.Base32.BYTES_PER_ENCODED_BLOCK;\n\t\tbyte[] org.apache.commons.codec.binary.Base32.encodeTable;\n\t\tint org.apache.commons.codec.binary.Base32.BYTES_PER_UNENCODED_BLOCK;\n\trelated functions:\n\t\tmethod ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`, api document: \n     * <p>\n     * Decodes all of the provided data, starting at inPos, for inAvail bytes. Should be called at least twice: once with the data to decode, and once with\n     * inAvail set to "-1" to alert decoder that EOF has been reached. The "-1" call is not necessary when decoding, but it doesn\'t hurt, either.\n     * </p>\n     * <p>\n     * Ignores all non-Base32 characters. This is how chunked (e.g. 76 character) data is handled, since CR and LF are silently ignored, but has implications\n     * for other bytes, too. This method subscribes to the garbage-in, garbage-out philosophy: it will not check the provided data for validity.\n     * </p>\n     * <p>\n     * Output is written to {@link org.apache.commons.codec.binary.BaseNCodec.Context#buffer Context#buffer} as 8-bit octets, using\n     * {@link org.apache.commons.codec.binary.BaseNCodec.Context#pos Context#pos} as the buffer position\n     * </p>\n     *\n     * @param input   byte[] array of ASCII data to Base32 decode.\n     * @param inPos   Position to start reading data from.\n     * @param inAvail Amount of bytes available from input for decoding.\n     * @param context the context to be used\n     ;\nclass `org.apache.commons.codec.binary.Base64`:\n\trelated functions:\n\t\tmethod ` void encode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`, api document: \n     * <p>\n     * Encodes all of the provided data, starting at inPos, for inAvail bytes. Must be called at least twice: once with\n     * the data to encode, and once with inAvail set to "-1" to alert encoder that EOF has been reached, to flush last\n     * remaining bytes (if not multiple of 3).\n     * </p>\n     * <p><b>Note: no padding is added when encoding using the URL-safe alphabet.</b></p>\n     * <p>\n     * Thanks to "commons" project in ws.apache.org for the bitwise operations, and general approach.\n     * https://svn.apache.org/repos/asf/webservices/commons/trunk/modules/util/\n     * </p>\n     *\n     * @param in\n     *            byte[] array of binary data to base64 encode.\n     * @param inPos\n     *            Position to start reading data from.\n     * @param inAvail\n     *            Amount of bytes available from input for encoding.\n     * @param context\n     *            the context to be used\n     ;\n\t\tmethod ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`, api document: \n     * <p>\n     * Decodes all of the provided data, starting at inPos, for inAvail bytes. Should be called at least twice: once\n     * with the data to decode, and once with inAvail set to "-1" to alert decoder that EOF has been reached. The "-1"\n     * call is not necessary when decoding, but it doesn\'t hurt, either.\n     * </p>\n     * <p>\n     * Ignores all non-base64 characters. This is how chunked (e.g. 76 character) data is handled, since CR and LF are\n     * silently ignored, but has implications for other bytes, too. This method subscribes to the garbage-in,\n     * garbage-out philosophy: it will not check the provided data for validity.\n     * </p>\n     * <p>\n     * Thanks to "commons" project in ws.apache.org for the bitwise operations, and general approach.\n     * https://svn.apache.org/repos/asf/webservices/commons/trunk/modules/util/\n     * </p>\n     *\n     * @param input\n     *            byte[] array of ASCII data to base64 decode.\n     * @param inPos\n     *            Position to start reading data from.\n     * @param inAvail\n     *            Amount of bytes available from input for decoding.\n     * @param context\n     *            the context to be used\n     ;\nclass `org.apache.commons.codec.language.ColognePhonetic.CologneInputBuffer`:\n\trelated functions:\n\t\tmethod `protected char[] copyData(int, int)` returns `char[]`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`;\nclass `org.apache.commons.codec.binary.StringUtils`:\n\trelated functions:\n\t\tmethod `private static IllegalStateException newIllegalStateException(String, UnsupportedEncodingException)` returns `java.lang.IllegalStateException`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`;\nclass `org.apache.commons.codec.binary.Base16`:\n\trelated functions:\n\t\tmethod ` void encode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`;\n\t\tmethod ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`;\nclass `org.apache.commons.codec.digest.XXHash32`:\n\trelated functions:\n\t\tmethod `public void update(byte[], int, int)` returns `void`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


