


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
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
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.lang.reflect.*;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@ExtendWith(MockitoExtension.class)
class Base32_decode_Test {

    // @formatter:off
    private static final String[][] BASE32_TEST_CASES = { { "", "" }, { "f", "MY======" }, { "fo", "MZXQ====" }, { "foo", "MZXW6===" }, { "foob", "MZXW6YQ=" }, { "fooba", "MZXW6YTB" }, { "foobar", "MZXW6YTBOI======" } };

    // @formatter:off
    private static final String[] IMPOSSIBLE_CASES = { "MC======", "MZXE====", "MZXWB===", "MZXW6YB=", "MZXW6YTBOC======", "AB======" };

    // @formatter:off
    private static final String[] IMPOSSIBLE_CASES_CHUNKED = { "M2======\r\n", "MZX0====\r\n", "MZXW0===\r\n", "MZXW6Y2=\r\n", "MZXW6YTBO2======\r\n" };

    /**
     * Helper to create a fresh {@code BaseNCodec.Context} instance via reflection.
     */
    private static Object newContext() throws Exception {
        Class<?> ctxCls = Class.forName("org.apache.commons.codec.binary.BaseNCodec$Context");
        Constructor<?> ctor = ctxCls.getDeclaredConstructor();
        ctor.setAccessible(true);
        return ctor.newInstance();
    }

    /**
     * Helper to set a field value on the context object (including private fields).
     */
    private static void setContextField(Object ctx, String name, Object value) throws Exception {
        Field f = ctx.getClass().getDeclaredField(name);
        f.setAccessible(true);
        f.set(ctx, value);
    }

    /**
     * Helper to get a field value from the context object.
     */
    private static Object getContextField(Object ctx, String name) throws Exception {
        Field f = ctx.getClass().getDeclaredField(name);
        f.setAccessible(true);
        return f.get(ctx);
    }

    /**
     * Invoke the package‑protected {@code decode} method via reflection.
     */
    private static void invokeDecode(Base32 codec, byte[] input, int inPos, int inAvail, Object ctx) throws Exception {
        Method m = Base32.class.getDeclaredMethod("decode", byte[].class, int.class, int.class, Class.forName("org.apache.commons.codec.binary.BaseNCodec$Context"));
        m.setAccessible(true);
        m.invoke(codec, input, inPos, inAvail, ctx);
    }

    /**
     * Retrieve the internal output buffer after decoding.
     */
    private static byte[] getDecodedBytes(Object ctx) throws Exception {
        byte[] buffer = (byte[]) getContextField(ctx, "buffer");
        int pos = (int) getContextField(ctx, "pos");
        if (buffer == null) {
            return new byte[0];
        }
        byte[] out = new byte[pos];
        System.arraycopy(buffer, 0, out, 0, pos);
        return out;
    }

    private static Stream<Arguments> trailingCases() {
        return Stream.of(// modulus 1 – should throw IllegalArgumentException via validateTrailingCharacters()
        Arguments.of(1, 0x1FL, IllegalArgumentException.class, null), // modulus 2 – should output one byte
        Arguments.of(2, 0x3FL, null, new byte[] { (byte) ((0x3FL >> 2) & 0xFF) }), // modulus 3 – throws IllegalArgumentException
        Arguments.of(3, 0x7FL, IllegalArgumentException.class, null), // modulus 4 – output two bytes after validation
        Arguments.of(4, 0xFFFFL, null, new byte[] { (byte) ((0xFFFFL >> 8) & 0xFF), (byte) (0xFFFFL & 0xFF) }), // modulus 5 – output three bytes after validation
        Arguments.of(5, 0x1FFFFFL, null, new byte[] { (byte) ((0x1FFFFFL >> 16) & 0xFF), (byte) ((0x1FFFFFL >> 8) & 0xFF), (byte) (0x1FFFFFL & 0xFF) }), // modulus 6 – throws IllegalArgumentException
        Arguments.of(6, 0x3FFFFFL, IllegalArgumentException.class, null), // modulus 7 – output four bytes after validation
        Arguments.of(7, 0x7FFFFFL, null, new byte[] { (byte) ((0x7FFFFFL >> 24) & 0xFF), (byte) ((0x7FFFFFL >> 16) & 0xFF), (byte) ((0x7FFFFFL >> 8) & 0xFF), (byte) (0x7FFFFFL & 0xFF) }), // modulus 8 – IllegalStateException (impossible)
        Arguments.of(8, 0xFFFFFFFFL, IllegalStateException.class, null));
    }

    /**
     * Verify that the standard RFC‑4648 vectors decode to the original bytes.
     */
    @Test
    void testStandardDecoding() {
        // default (no line length, no separator)
        final Base32 codec = new Base32();
        for (String[] pair : BASE32_TEST_CASES) {
            final String plain = pair[0];
            final String encoded = pair[1];
            byte[] expected = plain.getBytes(StandardCharsets.UTF_8);
            byte[] actual = codec.decode(encoded.getBytes(StandardCharsets.US_ASCII));
            assertArrayEquals(expected, actual, () -> "Failed to decode Base32 string: \"" + encoded + "\"");
        }
    }

    /**
     * Decoding with a line length and line separator should ignore the separator characters.
     */
    @Test
    void testChunkedDecoding() {
        // line length 10, CRLF separator – matches the strings in IMPOSSIBLE_CASES_CHUNKED
        final Base32 codec = new Base32(10, "\r\n".getBytes(StandardCharsets.US_ASCII));
        for (String chunked : IMPOSSIBLE_CASES_CHUNKED) {
            // The chunked strings are *invalid* – they should raise an exception.
            assertThrows(IllegalArgumentException.class, () -> codec.decode(chunked.getBytes(StandardCharsets.US_ASCII)), () -> "Expected exception for chunked invalid input: \"" + chunked + "\"");
        }
    }

    /**
     * Ensure that inputs with impossible trailing characters raise an exception.
     */
    @Test
    void testImpossibleTrailingCharacters() {
        final Base32 codec = new Base32();
        for (String bad : IMPOSSIBLE_CASES) {
            assertThrows(IllegalArgumentException.class, () -> codec.decode(bad.getBytes(StandardCharsets.US_ASCII)), () -> "Expected exception for impossible case: \"" + bad + "\"");
        }
    }

    /**
     * Verify that the decoder correctly handles the various modulus values (2,4,5,6,7).
     */
    @Test
    void testModulusEdgeCases() {
        final Base32 codec = new Base32();
        // Modulus 2 – 2 encoded chars -> 1 decoded byte (e.g. "MY")
        assertArrayEquals(new byte[] { 'f' }, codec.decode("MY".getBytes(StandardCharsets.US_ASCII)), "Modulus 2 (2 chars) failed");
        // Modulus 4 – 4 encoded chars -> 2 decoded bytes (e.g. "MZXQ")
        assertArrayEquals(new byte[] { 'f', 'o' }, codec.decode("MZXQ".getBytes(StandardCharsets.US_ASCII)), "Modulus 4 (4 chars) failed");
        // Modulus 5 – 5 encoded chars -> 3 decoded bytes (e.g. "MZXW6")
        assertArrayEquals(new byte[] { 'f', 'o', 'o' }, codec.decode("MZXW6".getBytes(StandardCharsets.US_ASCII)), "Modulus 5 (5 chars) failed");
        // Modulus 6 – 6 encoded chars -> 3 decoded bytes (e.g. "MZXW6Y")
        assertArrayEquals(new byte[] { 'f', 'o', 'o', 'b' }, codec.decode("MZXW6Y".getBytes(StandardCharsets.US_ASCII)), "Modulus 6 (6 chars) failed");
        // Modulus 7 – 7 encoded chars -> 4 decoded bytes (e.g. "MZXW6YT")
        assertArrayEquals(new byte[] { 'f', 'o', 'o', 'b', 'a' }, codec.decode("MZXW6YT".getBytes(StandardCharsets.US_ASCII)), "Modulus 7 (7 chars) failed");
    }

    /**
     * Verify that the decoder works with the Base32‑hex variant.
     */
    @Test
    void testHexVariantDecoding() {
        // Base32‑hex uses a different alphabet; the string "C" corresponds to byte 0.
        final Base32 codec = new Base32(true);
        // "C" is the first character of the hex alphabet, representing the 5‑bit value 0.
        // With padding we need 8 chars to get a full byte; "C=======" yields a single zero byte.
        byte[] decoded = codec.decode("C=======".getBytes(StandardCharsets.US_ASCII));
        assertArrayEquals(new byte[] { 0 }, decoded, "Hex variant decoding failed");
    }

    /**
     * Ensure that decoding an empty input returns an empty byte array and does not set EOF prematurely.
     */
    @Test
    void testEmptyInput() {
        final Base32 codec = new Base32();
        byte[] decoded = codec.decode(new byte[0]);
        assertNotNull(decoded, "Decoded result should not be null");
        assertEquals(0, decoded.length, "Decoded length for empty input should be zero");
    }

    @Test
    void earlyExit_whenContextAlreadyEof_returnsImmediately() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", true);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 0);
        setContextField(ctx, "lbitWorkArea", 0L);
        // arbitrary
        byte[] input = new byte[] { 0x41 };
        invokeDecode(codec, input, 0, 1, ctx);
        assertTrue((boolean) getContextField(ctx, "eof"));
        assertEquals(0, (int) getContextField(ctx, "pos"));
    }

    @Test
    void earlyExit_whenNegativeInAvail_setsEofAndStopsProcessing() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", false);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 0);
        setContextField(ctx, "lbitWorkArea", 0L);
        byte[] input = new byte[] { 0x41, 0x42 };
        invokeDecode(codec, input, 0, -5, ctx);
        assertTrue((boolean) getContextField(ctx, "eof"));
        assertEquals(0, (int) getContextField(ctx, "pos"));
    }

    @Test
    void earlyExit_whenPaddingCharacterEncountered_setsEofAndStops() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", false);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 0);
        setContextField(ctx, "lbitWorkArea", 0L);
        byte[] input = new byte[] { '=' };
        invokeDecode(codec, input, 0, 1, ctx);
        assertTrue((boolean) getContextField(ctx, "eof"));
        assertEquals(0, (int) getContextField(ctx, "pos"));
    }

    @Test
    void fullBlockDecoding_emitsFiveBytesAndLeavesEofFalse() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", false);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 0);
        setContextField(ctx, "lbitWorkArea", 0L);
        // "ABCDEFGH" are valid Base32 symbols that form a full block.
        byte[] input = "ABCDEFGH".getBytes(StandardCharsets.US_ASCII);
        invokeDecode(codec, input, 0, input.length, ctx);
        // After processing 8 symbols, modulus should be 0 and 5 bytes written.
        assertFalse((boolean) getContextField(ctx, "eof"));
        assertEquals(5, (int) getContextField(ctx, "pos"));
        assertArrayEquals(new byte[] { (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 }, getDecodedBytes(ctx), "Exact output not verified – focus is on length and state");
    }

    @ParameterizedTest
    @MethodSource("trailingCases")
    void trailingCharacterHandling(int modulus, long lbitWorkArea, Class<? extends Throwable> expectedException, byte[] expectedOutput) throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", true);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", modulus);
        setContextField(ctx, "lbitWorkArea", lbitWorkArea);
        // No input bytes – we only want the trailing‑character switch to run.
        byte[] empty = new byte[0];
        if (expectedException != null) {
            Executable exec = () -> invokeDecode(codec, empty, 0, 0, ctx);
            assertThrows(expectedException, exec, "Expected exception for modulus " + modulus);
        } else {
            invokeDecode(codec, empty, 0, 0, ctx);
            assertArrayEquals(expectedOutput, getDecodedBytes(ctx), "Incorrect output for modulus " + modulus);
        }
    }

    @Test
    void noTrailingWhenEofTrueAndModulusZeroWritesNoAdditionalBytes() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", true);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 0);
        setContextField(ctx, "lbitWorkArea", 0L);
        // Provide a full block that will be emitted inside the loop.
        byte[] input = "ABCDEFGH".getBytes(StandardCharsets.US_ASCII);
        invokeDecode(codec, input, 0, input.length, ctx);
        // After the loop, because modulus == 0, the trailing switch must not add bytes.
        assertEquals(5, (int) getContextField(ctx, "pos"));
    }

    @Test
    void invalidCharacterIsIgnoredAndDoesNotAffectModulus() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", false);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 0);
        setContextField(ctx, "lbitWorkArea", 0L);
        // 0xFF is not in the alphabet
        byte[] input = new byte[] { 'A', (byte) 0xFF, 'B' };
        invokeDecode(codec, input, 0, input.length, ctx);
        // Only the two valid characters are processed – they do not yet form a full block,
        // therefore no bytes should be emitted and modulus should be 2.
        assertEquals(2, (int) getContextField(ctx, "modulus"));
        assertEquals(0, (int) getContextField(ctx, "pos"));
    }

    @Test
    void partialBlockWithPaddingEmitsTwoBytesAndSetsEof() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", false);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 0);
        setContextField(ctx, "lbitWorkArea", 0L);
        // "ABCD=" – four valid symbols then padding.
        byte[] input = "ABCD=".getBytes(StandardCharsets.US_ASCII);
        invokeDecode(codec, input, 0, input.length, ctx);
        // After processing the four symbols, modulus should be 4 and two bytes emitted.
        assertTrue((boolean) getContextField(ctx, "eof"));
        assertEquals(4, (int) getContextField(ctx, "modulus"));
        assertEquals(2, (int) getContextField(ctx, "pos"));
    }

    @Test
    void negativeInAvailWithPendingBitsTriggersTrailingSwitch() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", false);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 5);
        // 25 bits
        setContextField(ctx, "lbitWorkArea", 0x1FFFFFL);
        // No further input, but signal EOF with inAvail = -1
        invokeDecode(codec, new byte[0], 0, -1, ctx);
        assertTrue((boolean) getContextField(ctx, "eof"));
        assertEquals(3, (int) getContextField(ctx, "pos"));
        byte[] out = getDecodedBytes(ctx);
        assertArrayEquals(new byte[] { (byte) ((0x1FFFFFL >> 16) & 0xFF), (byte) ((0x1FFFFFL >> 8) & 0xFF), (byte) (0x1FFFFFL & 0xFF) }, out);
    }

    @Test
    void extraPaddingCharactersAreIgnoredAfterFirstEquals() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", false);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 0);
        setContextField(ctx, "lbitWorkArea", 0L);
        // Eight valid symbols followed by several padding chars.
        byte[] input = "ABCDEFGH====".getBytes(StandardCharsets.US_ASCII);
        invokeDecode(codec, input, 0, input.length, ctx);
        // Loop should stop at the first '=', so only the first eight symbols are decoded.
        assertTrue((boolean) getContextField(ctx, "eof"));
        assertEquals(5, (int) getContextField(ctx, "pos"));
    }

    @Test
    void whitespaceIsSkippedDuringDecoding() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", false);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 0);
        setContextField(ctx, "lbitWorkArea", 0L);
        // Input contains spaces and a newline; they should be ignored.
        byte[] input = "A B\nCDEFGH".getBytes(StandardCharsets.US_ASCII);
        invokeDecode(codec, input, 0, input.length, ctx);
        // Same result as decoding "ABCDEFGH".
        assertEquals(5, (int) getContextField(ctx, "pos"));
    }

    @Test
    void validateCharacterFailsWhenLowBitsNotZero_modulus2() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", true);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 2);
        // low 2 bits are not zero
        setContextField(ctx, "lbitWorkArea", 0x3L);
        assertThrows(IllegalArgumentException.class, () -> invokeDecode(codec, new byte[0], 0, 0, ctx));
    }

    @Test
    void validateCharacterFailsWhenLowBitsNotZero_modulus4() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", true);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 4);
        // low 4 bits non‑zero
        setContextField(ctx, "lbitWorkArea", 0xABCDEFL);
        assertThrows(IllegalArgumentException.class, () -> invokeDecode(codec, new byte[0], 0, 0, ctx));
    }

    @Test
    void validateCharacterFailsWhenLowBitsNotZero_modulus5() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", true);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 5);
        // low 1 bit is 1
        setContextField(ctx, "lbitWorkArea", 0x1FFFFFL);
        assertThrows(IllegalArgumentException.class, () -> invokeDecode(codec, new byte[0], 0, 0, ctx));
    }

    @Test
    void validateCharacterFailsWhenLowBitsNotZero_modulus7() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", true);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 7);
        // low 3 bits are not zero
        setContextField(ctx, "lbitWorkArea", 0x7L);
        assertThrows(IllegalArgumentException.class, () -> invokeDecode(codec, new byte[0], 0, 0, ctx));
    }

    @Test
    void decode_nullInputArray_throwsNullPointerException() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", false);
        assertThrows(NullPointerException.class, () -> invokeDecode(codec, null, 0, 1, ctx));
    }

    @Test
    void decode_nullContext_throwsNullPointerException() throws Exception {
        Base32 codec = new Base32();
        byte[] input = "A".getBytes(StandardCharsets.US_ASCII);
        assertThrows(NullPointerException.class, () -> invokeDecode(codec, input, 0, 1, null));
    }
}
```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ Base32_decode_Test ✔
│     ├─ testModulusEdgeCases() ✘ Modulus 6 (6 chars) failed ==> array lengths differ, expected: <4> but was: <3>
│     ├─ invalidCharacterIsIgnoredAndDoesNotAffectModulus() ✔
│     ├─ validateCharacterFailsWhenLowBitsNotZero_modulus2() ✘ Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
│     ├─ validateCharacterFailsWhenLowBitsNotZero_modulus4() ✘ Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
│     ├─ validateCharacterFailsWhenLowBitsNotZero_modulus5() ✘ Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
│     ├─ validateCharacterFailsWhenLowBitsNotZero_modulus7() ✘ Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
│     ├─ testEmptyInput() ✔
│     ├─ decode_nullInputArray_throwsNullPointerException() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ earlyExit_whenPaddingCharacterEncountered_setsEofAndStops() ✔
│     ├─ trailingCharacterHandling(int, long, Class, byte[]) ✔
│     │  ├─ [1] 1, 31, class java.lang.IllegalArgumentException, null ✘ Expected exception for modulus 1 ==> Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
│     │  ├─ [2] 2, 63, null, [15] ✘ Incorrect output for modulus 2 ==> array lengths differ, expected: <1> but was: <0>
│     │  ├─ [3] 3, 127, class java.lang.IllegalArgumentException, null ✘ Expected exception for modulus 3 ==> Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
│     │  ├─ [4] 4, 65535, null, [-1, -1] ✘ Incorrect output for modulus 4 ==> array lengths differ, expected: <2> but was: <0>
│     │  ├─ [5] 5, 2097151, null, [31, -1, -1] ✘ Incorrect output for modulus 5 ==> array lengths differ, expected: <3> but was: <0>
│     │  ├─ [6] 6, 4194303, class java.lang.IllegalArgumentException, null ✘ Expected exception for modulus 6 ==> Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
│     │  ├─ [7] 7, 8388607, null, [0, 127, -1, -1] ✘ Incorrect output for modulus 7 ==> array lengths differ, expected: <4> but was: <0>
│     │  └─ [8] 8, 4294967295, class java.lang.IllegalStateException, null ✘ Expected exception for modulus 8 ==> Expected java.lang.IllegalStateException to be thrown, but nothing was thrown.
│     ├─ testHexVariantDecoding() ✘ Hex variant decoding failed ==> array contents differ at index [0], expected: <0> but was: <3>
│     ├─ decode_nullContext_throwsNullPointerException() ✘ Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ whitespaceIsSkippedDuringDecoding() ✔
│     ├─ earlyExit_whenNegativeInAvail_setsEofAndStopsProcessing() ✔
│     ├─ testStandardDecoding() ✔
│     ├─ fullBlockDecoding_emitsFiveBytesAndLeavesEofFalse() ✘ Exact output not verified – focus is on length and state ==> array contents differ at index [1], expected: <0> but was: <68>
│     ├─ noTrailingWhenEofTrueAndModulusZeroWritesNoAdditionalBytes() ✘ expected: <5> but was: <0>
│     ├─ negativeInAvailWithPendingBitsTriggersTrailingSwitch() ✘ array contents differ at index [0], expected: <31> but was: <15>
│     ├─ earlyExit_whenContextAlreadyEof_returnsImmediately() ✔
│     ├─ partialBlockWithPaddingEmitsTwoBytesAndSetsEof() ✔
│     ├─ testImpossibleTrailingCharacters() ✘ Expected exception for impossible case: "MC======" ==> Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
│     ├─ extraPaddingCharactersAreIgnoredAfterFirstEquals() ✔
│     └─ testChunkedDecoding() ✘ Expected exception for chunked invalid input: "M2======
│              " ==> Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (21):
  JUnit Jupiter:Base32_decode_Test:testModulusEdgeCases()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'testModulusEdgeCases', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Modulus 6 (6 chars) failed ==> array lengths differ, expected: <4> but was: <3>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertArrayEquals.assertArraysHaveSameLength(AssertArrayEquals.java:428)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:205)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:63)
       org.junit.jupiter.api.Assertions.assertArrayEquals(Assertions.java:1238)
       org.apache.commons.codec.binary.Base32_decode_Test.testModulusEdgeCases(Base32_decode_Test.java:144)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Base32_decode_Test:validateCharacterFailsWhenLowBitsNotZero_modulus2()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'validateCharacterFailsWhenLowBitsNotZero_modulus2', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.binary.Base32_decode_Test.validateCharacterFailsWhenLowBitsNotZero_modulus2(Base32_decode_Test.java:359)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:Base32_decode_Test:validateCharacterFailsWhenLowBitsNotZero_modulus4()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'validateCharacterFailsWhenLowBitsNotZero_modulus4', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.binary.Base32_decode_Test.validateCharacterFailsWhenLowBitsNotZero_modulus4(Base32_decode_Test.java:371)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:Base32_decode_Test:validateCharacterFailsWhenLowBitsNotZero_modulus5()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'validateCharacterFailsWhenLowBitsNotZero_modulus5', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.binary.Base32_decode_Test.validateCharacterFailsWhenLowBitsNotZero_modulus5(Base32_decode_Test.java:383)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:Base32_decode_Test:validateCharacterFailsWhenLowBitsNotZero_modulus7()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'validateCharacterFailsWhenLowBitsNotZero_modulus7', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.binary.Base32_decode_Test.validateCharacterFailsWhenLowBitsNotZero_modulus7(Base32_decode_Test.java:395)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:Base32_decode_Test:decode_nullInputArray_throwsNullPointerException()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'decode_nullInputArray_throwsNullPointerException', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.binary.Base32_decode_Test.decode_nullInputArray_throwsNullPointerException(Base32_decode_Test.java:403)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.codec.binary.Base32_decode_Test.invokeDecode(Base32_decode_Test.java:62)
       [...]
     Caused by: java.lang.NullPointerException: Cannot load from byte/boolean array because "input" is null
       org.apache.commons.codec.binary.Base32.decode(Base32.java:404)
       [...]
  JUnit Jupiter:Base32_decode_Test:trailingCharacterHandling(int, long, Class, byte[]):[1] 1, 31, class java.lang.IllegalArgumentException, null
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'trailingCharacterHandling', methodParameterTypes = 'int, long, java.lang.Class, [B']
    => org.opentest4j.AssertionFailedError: Expected exception for modulus 1 ==> Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:39)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3131)
       org.apache.commons.codec.binary.Base32_decode_Test.trailingCharacterHandling(Base32_decode_Test.java:246)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:Base32_decode_Test:trailingCharacterHandling(int, long, Class, byte[]):[2] 2, 63, null, [15]
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'trailingCharacterHandling', methodParameterTypes = 'int, long, java.lang.Class, [B']
    => org.opentest4j.AssertionFailedError: Incorrect output for modulus 2 ==> array lengths differ, expected: <1> but was: <0>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertArrayEquals.assertArraysHaveSameLength(AssertArrayEquals.java:428)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:205)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:63)
       org.junit.jupiter.api.Assertions.assertArrayEquals(Assertions.java:1238)
       org.apache.commons.codec.binary.Base32_decode_Test.trailingCharacterHandling(Base32_decode_Test.java:249)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Base32_decode_Test:trailingCharacterHandling(int, long, Class, byte[]):[3] 3, 127, class java.lang.IllegalArgumentException, null
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'trailingCharacterHandling', methodParameterTypes = 'int, long, java.lang.Class, [B']
    => org.opentest4j.AssertionFailedError: Expected exception for modulus 3 ==> Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:39)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3131)
       org.apache.commons.codec.binary.Base32_decode_Test.trailingCharacterHandling(Base32_decode_Test.java:246)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:Base32_decode_Test:trailingCharacterHandling(int, long, Class, byte[]):[4] 4, 65535, null, [-1, -1]
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'trailingCharacterHandling', methodParameterTypes = 'int, long, java.lang.Class, [B']
    => org.opentest4j.AssertionFailedError: Incorrect output for modulus 4 ==> array lengths differ, expected: <2> but was: <0>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertArrayEquals.assertArraysHaveSameLength(AssertArrayEquals.java:428)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:205)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:63)
       org.junit.jupiter.api.Assertions.assertArrayEquals(Assertions.java:1238)
       org.apache.commons.codec.binary.Base32_decode_Test.trailingCharacterHandling(Base32_decode_Test.java:249)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Base32_decode_Test:trailingCharacterHandling(int, long, Class, byte[]):[5] 5, 2097151, null, [31, -1, -1]
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'trailingCharacterHandling', methodParameterTypes = 'int, long, java.lang.Class, [B']
    => org.opentest4j.AssertionFailedError: Incorrect output for modulus 5 ==> array lengths differ, expected: <3> but was: <0>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertArrayEquals.assertArraysHaveSameLength(AssertArrayEquals.java:428)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:205)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:63)
       org.junit.jupiter.api.Assertions.assertArrayEquals(Assertions.java:1238)
       org.apache.commons.codec.binary.Base32_decode_Test.trailingCharacterHandling(Base32_decode_Test.java:249)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Base32_decode_Test:trailingCharacterHandling(int, long, Class, byte[]):[6] 6, 4194303, class java.lang.IllegalArgumentException, null
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'trailingCharacterHandling', methodParameterTypes = 'int, long, java.lang.Class, [B']
    => org.opentest4j.AssertionFailedError: Expected exception for modulus 6 ==> Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:39)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3131)
       org.apache.commons.codec.binary.Base32_decode_Test.trailingCharacterHandling(Base32_decode_Test.java:246)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:Base32_decode_Test:trailingCharacterHandling(int, long, Class, byte[]):[7] 7, 8388607, null, [0, 127, -1, -1]
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'trailingCharacterHandling', methodParameterTypes = 'int, long, java.lang.Class, [B']
    => org.opentest4j.AssertionFailedError: Incorrect output for modulus 7 ==> array lengths differ, expected: <4> but was: <0>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertArrayEquals.assertArraysHaveSameLength(AssertArrayEquals.java:428)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:205)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:63)
       org.junit.jupiter.api.Assertions.assertArrayEquals(Assertions.java:1238)
       org.apache.commons.codec.binary.Base32_decode_Test.trailingCharacterHandling(Base32_decode_Test.java:249)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Base32_decode_Test:trailingCharacterHandling(int, long, Class, byte[]):[8] 8, 4294967295, class java.lang.IllegalStateException, null
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'trailingCharacterHandling', methodParameterTypes = 'int, long, java.lang.Class, [B']
    => org.opentest4j.AssertionFailedError: Expected exception for modulus 8 ==> Expected java.lang.IllegalStateException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:39)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3131)
       org.apache.commons.codec.binary.Base32_decode_Test.trailingCharacterHandling(Base32_decode_Test.java:246)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:Base32_decode_Test:testHexVariantDecoding()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'testHexVariantDecoding', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Hex variant decoding failed ==> array contents differ at index [0], expected: <0> but was: <3>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertArrayEquals.failArraysNotEqual(AssertArrayEquals.java:440)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:209)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:63)
       org.junit.jupiter.api.Assertions.assertArrayEquals(Assertions.java:1238)
       org.apache.commons.codec.binary.Base32_decode_Test.testHexVariantDecoding(Base32_decode_Test.java:159)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Base32_decode_Test:decode_nullContext_throwsNullPointerException()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'decode_nullContext_throwsNullPointerException', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.NullPointerException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.binary.Base32_decode_Test.decode_nullContext_throwsNullPointerException(Base32_decode_Test.java:410)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.codec.binary.Base32_decode_Test.invokeDecode(Base32_decode_Test.java:62)
       [...]
     Caused by: java.lang.NullPointerException: Cannot read field "eof" because "context" is null
       org.apache.commons.codec.binary.Base32.decode(Base32.java:396)
       [...]
  JUnit Jupiter:Base32_decode_Test:fullBlockDecoding_emitsFiveBytesAndLeavesEofFalse()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'fullBlockDecoding_emitsFiveBytesAndLeavesEofFalse', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Exact output not verified – focus is on length and state ==> array contents differ at index [1], expected: <0> but was: <68>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertArrayEquals.failArraysNotEqual(AssertArrayEquals.java:440)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:209)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:63)
       org.junit.jupiter.api.Assertions.assertArrayEquals(Assertions.java:1238)
       org.apache.commons.codec.binary.Base32_decode_Test.fullBlockDecoding_emitsFiveBytesAndLeavesEofFalse(Base32_decode_Test.java:230)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Base32_decode_Test:noTrailingWhenEofTrueAndModulusZeroWritesNoAdditionalBytes()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'noTrailingWhenEofTrueAndModulusZeroWritesNoAdditionalBytes', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <5> but was: <0>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:150)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:145)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:531)
       org.apache.commons.codec.binary.Base32_decode_Test.noTrailingWhenEofTrueAndModulusZeroWritesNoAdditionalBytes(Base32_decode_Test.java:265)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Base32_decode_Test:negativeInAvailWithPendingBitsTriggersTrailingSwitch()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'negativeInAvailWithPendingBitsTriggersTrailingSwitch', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: array contents differ at index [0], expected: <31> but was: <15>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertArrayEquals.failArraysNotEqual(AssertArrayEquals.java:440)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:209)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:63)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:59)
       org.junit.jupiter.api.Assertions.assertArrayEquals(Assertions.java:1229)
       org.apache.commons.codec.binary.Base32_decode_Test.negativeInAvailWithPendingBitsTriggersTrailingSwitch(Base32_decode_Test.java:316)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Base32_decode_Test:testImpossibleTrailingCharacters()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'testImpossibleTrailingCharacters', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected exception for impossible case: "MC======" ==> Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:45)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3149)
       org.apache.commons.codec.binary.Base32_decode_Test.testImpossibleTrailingCharacters(Base32_decode_Test.java:127)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:Base32_decode_Test:testChunkedDecoding()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_decode_Test', methodName = 'testChunkedDecoding', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected exception for chunked invalid input: "M2======
" ==> Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:45)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3149)
       org.apache.commons.codec.binary.Base32_decode_Test.testChunkedDecoding(Base32_decode_Test.java:116)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]

Test run finished after 413 ms
[         5 containers found      ]
[         0 containers skipped    ]
[         5 containers started    ]
[         0 containers aborted    ]
[         5 containers successful ]
[         0 containers failed     ]
[        30 tests found           ]
[         0 tests skipped         ]
[        30 tests started         ]
[         0 tests aborted         ]
[         9 tests successful      ]
[        21 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class org.apache.commons.codec.binary.Base32: 

 * Provides Base32 encoding and decoding as defined by <a href="http://www.ietf.org/rfc/rfc4648.txt">RFC 4648</a>.
 *
 * <p>
 * The class can be parameterized in the following manner with various constructors:
 * </p>
 * <ul>
 * <li>Whether to use the "base32hex" variant instead of the default "base32"</li>
 * <li>Line length: Default 76. Line length that aren't multiples of 8 will still essentially end up being multiples of 8 in the encoded data.
 * <li>Line separator: Default is CRLF ("\r\n")</li>
 * </ul>
 * <p>
 * This class operates directly on byte streams, and not character streams.
 * </p>
 * <p>
 * This class is thread-safe.
 * </p>
 * <p>
 * You can configure instances with the {@link Builder}.
 * </p>
 * <pre>
 * Base32 base32 = Base32.builder()
 *   .setDecodingPolicy(DecodingPolicy.LENIENT) // default is lenient
 *   .setEncodeTable(customEncodeTable)
 *   .setLineLength(0)                          // default is none
 *   .setLineSeparator('\r', '\n')              // default is CR LF
 *   .setPadding('=')                           // default is =
 *   .get()
 * </pre>
 *
 * @see <a href="http://www.ietf.org/rfc/rfc4648.txt">RFC 4648</a>
 * @since 1.5
 

api document of method decode(byte[], int, int, Context): 

     * <p>
     * Decodes all of the provided data, starting at inPos, for inAvail bytes. Should be called at least twice: once with the data to decode, and once with
     * inAvail set to "-1" to alert decoder that EOF has been reached. The "-1" call is not necessary when decoding, but it doesn't hurt, either.
     * </p>
     * <p>
     * Ignores all non-Base32 characters. This is how chunked (e.g. 76 character) data is handled, since CR and LF are silently ignored, but has implications
     * for other bytes, too. This method subscribes to the garbage-in, garbage-out philosophy: it will not check the provided data for validity.
     * </p>
     * <p>
     * Output is written to {@link org.apache.commons.codec.binary.BaseNCodec.Context#buffer Context#buffer} as 8-bit octets, using
     * {@link org.apache.commons.codec.binary.BaseNCodec.Context#pos Context#pos} as the buffer position
     * </p>
     *
     * @param input   byte[] array of ASCII data to Base32 decode.
     * @param inPos   Position to start reading data from.
     * @param inAvail Amount of bytes available from input for decoding.
     * @param context the context to be used
     

dependent classes: 
class `org.apache.commons.codec.binary.BaseNCodec.Context`:
	dependent fields:
		long org.apache.commons.codec.binary.BaseNCodec.Context.lbitWorkArea;
		int org.apache.commons.codec.binary.BaseNCodec.Context.pos;
		int org.apache.commons.codec.binary.BaseNCodec.Context.modulus;
		boolean org.apache.commons.codec.binary.BaseNCodec.Context.eof;
	related functions:
		method `public String toString()` returns `java.lang.String`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`, api document: 
         * Returns a String useful for debugging (especially within a debugger.)
         *
         * @return a String useful for debugging.
         ;
class `org.apache.commons.codec.binary.BaseNCodec`:
	dependent fields:
		byte org.apache.commons.codec.binary.BaseNCodec.pad;
		int org.apache.commons.codec.binary.BaseNCodec.MASK_8BITS;
	
class `org.apache.commons.codec.binary.Base32`:
	dependent fields:
		byte[] org.apache.commons.codec.binary.Base32.decodeTable;
		int org.apache.commons.codec.binary.Base32.encodeSize;
		long org.apache.commons.codec.binary.Base32.MASK_2BITS;
		int org.apache.commons.codec.binary.Base32.BITS_PER_ENCODED_BYTE;
		int org.apache.commons.codec.binary.Base32.BYTES_PER_ENCODED_BLOCK;
		long org.apache.commons.codec.binary.Base32.MASK_1BITS;
		long org.apache.commons.codec.binary.Base32.MASK_4BITS;
		long org.apache.commons.codec.binary.Base32.MASK_3BITS;
	related functions:
		method `public boolean isInAlphabet(byte)` returns `boolean`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`, api document: 
     * Returns whether or not the {@code octet} is in the Base32 alphabet.
     *
     * @param octet The value to test
     * @return {@code true} if the value is defined in the Base32 alphabet {@code false} otherwise.
     ;
		method ` void encode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`, api document: 
     * <p>
     * Encodes all of the provided data, starting at inPos, for inAvail bytes. Must be called at least twice: once with the data to encode, and once with
     * inAvail set to "-1" to alert encoder that EOF has been reached, so flush last remaining bytes (if not multiple of 5).
     * </p>
     *
     * @param input   byte[] array of binary data to Base32 encode.
     * @param inPos   Position to start reading data from.
     * @param inAvail Amount of bytes available from input for encoding.
     * @param context the context to be used
     ;
class `org.apache.commons.codec.binary.Base64`:
	related functions:
		method ` void encode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`, api document: 
     * <p>
     * Encodes all of the provided data, starting at inPos, for inAvail bytes. Must be called at least twice: once with
     * the data to encode, and once with inAvail set to "-1" to alert encoder that EOF has been reached, to flush last
     * remaining bytes (if not multiple of 3).
     * </p>
     * <p><b>Note: no padding is added when encoding using the URL-safe alphabet.</b></p>
     * <p>
     * Thanks to "commons" project in ws.apache.org for the bitwise operations, and general approach.
     * https://svn.apache.org/repos/asf/webservices/commons/trunk/modules/util/
     * </p>
     *
     * @param in
     *            byte[] array of binary data to base64 encode.
     * @param inPos
     *            Position to start reading data from.
     * @param inAvail
     *            Amount of bytes available from input for encoding.
     * @param context
     *            the context to be used
     ;
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`, api document: 
     * <p>
     * Decodes all of the provided data, starting at inPos, for inAvail bytes. Should be called at least twice: once
     * with the data to decode, and once with inAvail set to "-1" to alert decoder that EOF has been reached. The "-1"
     * call is not necessary when decoding, but it doesn't hurt, either.
     * </p>
     * <p>
     * Ignores all non-base64 characters. This is how chunked (e.g. 76 character) data is handled, since CR and LF are
     * silently ignored, but has implications for other bytes, too. This method subscribes to the garbage-in,
     * garbage-out philosophy: it will not check the provided data for validity.
     * </p>
     * <p>
     * Thanks to "commons" project in ws.apache.org for the bitwise operations, and general approach.
     * https://svn.apache.org/repos/asf/webservices/commons/trunk/modules/util/
     * </p>
     *
     * @param input
     *            byte[] array of ASCII data to base64 decode.
     * @param inPos
     *            Position to start reading data from.
     * @param inAvail
     *            Amount of bytes available from input for decoding.
     * @param context
     *            the context to be used
     ;
class `org.apache.commons.codec.binary.Base16`:
	related functions:
		method ` void encode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`;
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`;
class `org.apache.commons.codec.binary.StringUtils`:
	related functions:
		method `private static IllegalStateException newIllegalStateException(String, UnsupportedEncodingException)` returns `java.lang.IllegalStateException`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`;
class `org.apache.commons.codec.digest.MurmurHash3.IncrementalHash32x86`:
	related functions:
		method `public final void add(byte[], int, int)` returns `void`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`, api document: 
         * Adds the byte array to the current incremental hash.
         *
         * @param data The input byte array
         * @param offset The offset of data
         * @param length The length of array
         ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
