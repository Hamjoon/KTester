```java
package org.apache.commons.codec.binary;

import org.apache.commons.codec.binary.BaseNCodec.Context;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Base32_encode_Test {

    private Base32 codec;
    private Context context;

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources needed
    }

    @BeforeEach
    void setupBeforeEach() {
        // default codec (no line breaks, standard alphabet)
        codec = new Base32();
        context = new BaseNCodec.Context();
    }

    @AfterEach
    void teardownAfterEach() {
        // Nothing to clean up per test
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup required
    }

    /**
     * Encode a short byte array in one call and compare with the public
     * {@code encode(byte[])} convenience method.
     */
    @Test
    void testEncodeWholeArray() {
        byte[] input = "foo".getBytes(); // standard test vector
        // expected result using the public API (which internally uses encode(...))
        byte[] expected = codec.encode(input);

        // reset context for manual encode
        context = new Context();
        codec.encode(input, 0, input.length, context);
        // signal EOF
        codec.encode(input, 0, -1, context);

        byte[] actual = new byte[expected.length];
        codec.readResults(actual, 0, actual.length, context);
        assertArrayEquals(expected, actual, "Encoded bytes should match the public API output");
    }

    /**
     * Encode the same data one byte at a time, mimicking stream usage.
     */
    @Test
    void testEncodeByteByByte() {
        byte[] input = "foobar".getBytes();
        byte[] expected = codec.encode(input);

        context = new Context();
        for (int i = 0; i < input.length; i++) {
            codec.encode(input, i, 1, context);
        }
        // signal EOF
        codec.encode(input, 0, -1, context);

        byte[] actual = new byte[expected.length];
        codec.readResults(actual, 0, actual.length, context);
        assertArrayEquals(expected, actual, "Byte‑by‑byte encoding must produce the same result");
    }

    /**
     * Verify that line breaks are inserted when a line length is configured.
     */
    @Test
    void testEncodeWithLineLength() {
        // line length 8 forces a CRLF after every 8 encoded chars
        Base32 lineCodec = new Base32(8);
        Context lineContext = new Context();

        byte[] input = "The quick brown fox jumps over the lazy dog".getBytes();
        byte[] expected = lineCodec.encode(input); // includes CRLFs

        // manual encode using the same codec
        for (int i = 0; i < input.length; i++) {
            lineCodec.encode(input, i, 1, lineContext);
        }
        lineCodec.encode(input, 0, -1, lineContext);

        byte[] actual = new byte[expected.length];
        lineCodec.readResults(actual, 0, actual.length, lineContext);
        assertArrayEquals(expected, actual, "Encoded output should contain CRLF line separators");
    }

    /**
     * Encode using the Base32hex variant and compare with the known RFC 4648
     * Base32hex output for the same input.
     */
    @Test
    void testEncodeHexVariant() {
        Base32 hexCodec = new Base32(true); // hex alphabet
        Context hexContext = new Context();

        byte[] input = "foo".getBytes();
        // Expected Base32hex encoding of "foo" is "CPNMU===" (RFC 4648 example)
        byte[] expected = "CPNMU===".getBytes();

        // manual encode
        hexCodec.encode(input, 0, input.length, hexContext);
        hexCodec.encode(input, 0, -1, hexContext);

        byte[] actual = new byte[expected.length];
        hexCodec.readResults(actual, 0, actual.length, hexContext);
        assertArrayEquals(expected, actual, "Hex variant encoding does not match RFC example");
    }

    /**
     * Ensure that once EOF has been signalled further calls to {@code encode}
     * have no effect.
     */
    @Test
    void testEncodeAfterEofIsNoOp() {
        byte[] input = "test".getBytes();
        codec.encode(input, 0, input.length, context);
        codec.encode(input, 0, -1, context); // set EOF

        // Capture state after EOF
        int posAfterEof = context.pos;
        byte[] buffer = new byte[codec.getEncodedLength(input)];
        codec.readResults(buffer, 0, buffer.length, context);

        // Call encode again after EOF – should not change context or output
        codec.encode(input, 0, 1, context);
        assertEquals(posAfterEof, context.pos, "Context position must not change after EOF");
    }
}
```