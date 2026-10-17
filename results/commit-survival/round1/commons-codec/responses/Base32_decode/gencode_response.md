```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the package‑protected {@code Base32.decode(byte[],int,int,Context)} method.
 *
 * <p>The tests cover the exhaustive list of scenarios described in the supplied JSON
 * test‑case specification, including early‑exit conditions, full‑block decoding,
 * trailing‑character handling, padding, whitespace, invalid characters and null‑argument
 * handling.</p>
 *
 * <p>Because {@code Context} is a protected static inner class of {@code BaseNCodec},
 * it is instantiated via reflection.  The {@code buffer} field inside the context is also
 * accessed reflectively to verify the produced output.</p>
 */
@ExtendWith(MockitoExtension.class)
class Base32_decode_InternalTest {

    /** Helper to create a fresh {@code BaseNCodec.Context} instance via reflection. */
    private static Object newContext() throws Exception {
        Class<?> ctxCls = Class.forName("org.apache.commons.codec.binary.BaseNCodec$Context");
        Constructor<?> ctor = ctxCls.getDeclaredConstructor();
        ctor.setAccessible(true);
        return ctor.newInstance();
    }

    /** Helper to set a field value on the context object (including private fields). */
    private static void setContextField(Object ctx, String name, Object value) throws Exception {
        Field f = ctx.getClass().getDeclaredField(name);
        f.setAccessible(true);
        f.set(ctx, value);
    }

    /** Helper to get a field value from the context object. */
    private static Object getContextField(Object ctx, String name) throws Exception {
        Field f = ctx.getClass().getDeclaredField(name);
        f.setAccessible(true);
        return f.get(ctx);
    }

    /** Invoke the package‑protected {@code decode} method via reflection. */
    private static void invokeDecode(Base32 codec, byte[] input, int inPos, int inAvail, Object ctx) throws Exception {
        Method m = Base32.class.getDeclaredMethod("decode", byte[].class, int.class, int.class,
                Class.forName("org.apache.commons.codec.binary.BaseNCodec$Context"));
        m.setAccessible(true);
        m.invoke(codec, input, inPos, inAvail, ctx);
    }

    /** Retrieve the internal output buffer after decoding. */
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

    /* --------------------------------------------------------------------- */
    /*                     Early‑exit condition tests                        */
    /* --------------------------------------------------------------------- */

    @Test
    void earlyExit_whenContextAlreadyEof_returnsImmediately() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", true);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 0);
        setContextField(ctx, "lbitWorkArea", 0L);

        byte[] input = new byte[]{0x41}; // arbitrary
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

        byte[] input = new byte[]{0x41, 0x42};
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

        byte[] input = new byte[]{'='};
        invokeDecode(codec, input, 0, 1, ctx);

        assertTrue((boolean) getContextField(ctx, "eof"));
        assertEquals(0, (int) getContextField(ctx, "pos"));
    }

    /* --------------------------------------------------------------------- */
    /*                     Full‑block decoding (modulus 0)                    */
    /* --------------------------------------------------------------------- */

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
        assertArrayEquals(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00},
                getDecodedBytes(ctx), "Exact output not verified – focus is on length and state");
    }

    /* --------------------------------------------------------------------- */
    /*                     Trailing‑character handling                        */
    /* --------------------------------------------------------------------- */

    private static Stream<Arguments> trailingCases() {
        return Stream.of(
                // modulus 1 – should throw IllegalArgumentException via validateTrailingCharacters()
                Arguments.of(1, 0x1FL, IllegalArgumentException.class, null),
                // modulus 2 – should output one byte
                Arguments.of(2, 0x3FL, null, new byte[]{(byte) ((0x3FL >> 2) & 0xFF)}),
                // modulus 3 – throws IllegalArgumentException
                Arguments.of(3, 0x7FL, IllegalArgumentException.class, null),
                // modulus 4 – output two bytes after validation
                Arguments.of(4, 0xFFFFL, null, new byte[]{(byte) ((0xFFFFL >> 8) & 0xFF), (byte) (0xFFFFL & 0xFF)}),
                // modulus 5 – output three bytes after validation
                Arguments.of(5, 0x1FFFFFL, null, new byte[]{
                        (byte) ((0x1FFFFFL >> 16) & 0xFF),
                        (byte) ((0x1FFFFFL >> 8) & 0xFF),
                        (byte) (0x1FFFFFL & 0xFF)}),
                // modulus 6 – throws IllegalArgumentException
                Arguments.of(6, 0x3FFFFFL, IllegalArgumentException.class, null),
                // modulus 7 – output four bytes after validation
                Arguments.of(7, 0x7FFFFFL, null, new byte[]{
                        (byte) ((0x7FFFFFL >> 24) & 0xFF),
                        (byte) ((0x7FFFFFL >> 16) & 0xFF),
                        (byte) ((0x7FFFFFL >> 8) & 0xFF),
                        (byte) (0x7FFFFFL & 0xFF)}),
                // modulus 8 – IllegalStateException (impossible)
                Arguments.of(8, 0xFFFFFFFFL, IllegalStateException.class, null)
        );
    }

    @ParameterizedTest
    @MethodSource("trailingCases")
    void trailingCharacterHandling(int modulus, long lbitWorkArea,
                                   Class<? extends Throwable> expectedException,
                                   byte[] expectedOutput) throws Exception {
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
            assertThrows(expectedException, exec,
                    "Expected exception for modulus " + modulus);
        } else {
            invokeDecode(codec, empty, 0, 0, ctx);
            assertArrayEquals(expectedOutput, getDecodedBytes(ctx),
                    "Incorrect output for modulus " + modulus);
        }
    }

    /* --------------------------------------------------------------------- */
    /*                     No‑trailing processing (modulus 0)                */
    /* --------------------------------------------------------------------- */

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

    /* --------------------------------------------------------------------- */
    /*                     Invalid character handling                         */
    /* --------------------------------------------------------------------- */

    @Test
    void invalidCharacterIsIgnoredAndDoesNotAffectModulus() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", false);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 0);
        setContextField(ctx, "lbitWorkArea", 0L);

        byte[] input = new byte[]{'A', (byte) 0xFF, 'B'}; // 0xFF is not in the alphabet
        invokeDecode(codec, input, 0, input.length, ctx);

        // Only the two valid characters are processed – they do not yet form a full block,
        // therefore no bytes should be emitted and modulus should be 2.
        assertEquals(2, (int) getContextField(ctx, "modulus"));
        assertEquals(0, (int) getContextField(ctx, "pos"));
    }

    /* --------------------------------------------------------------------- */
    /*                     Partial block with padding character                */
    /* --------------------------------------------------------------------- */

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

    /* --------------------------------------------------------------------- */
    /*                     EOF signalled by negative inAvail with pending bits */
    /* --------------------------------------------------------------------- */

    @Test
    void negativeInAvailWithPendingBitsTriggersTrailingSwitch() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", false);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 5);
        setContextField(ctx, "lbitWorkArea", 0x1FFFFFL); // 25 bits

        // No further input, but signal EOF with inAvail = -1
        invokeDecode(codec, new byte[0], 0, -1, ctx);

        assertTrue((boolean) getContextField(ctx, "eof"));
        assertEquals(3, (int) getContextField(ctx, "pos"));
        byte[] out = getDecodedBytes(ctx);
        assertArrayEquals(new byte[]{
                (byte) ((0x1FFFFFL >> 16) & 0xFF),
                (byte) ((0x1FFFFFL >> 8) & 0xFF),
                (byte) (0x1FFFFFL & 0xFF)
        }, out);
    }

    /* --------------------------------------------------------------------- */
    /*                     Extra padding characters after first '='          */
    /* --------------------------------------------------------------------- */

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

    /* --------------------------------------------------------------------- */
    /*                     Whitespace characters are ignored                  */
    /* --------------------------------------------------------------------- */

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

    /* --------------------------------------------------------------------- */
    /*                     validateCharacter failures for various moduli      */
    /* --------------------------------------------------------------------- */

    @Test
    void validateCharacterFailsWhenLowBitsNotZero_modulus2() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", true);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 2);
        setContextField(ctx, "lbitWorkArea", 0x3L); // low 2 bits are not zero

        assertThrows(IllegalArgumentException.class,
                () -> invokeDecode(codec, new byte[0], 0, 0, ctx));
    }

    @Test
    void validateCharacterFailsWhenLowBitsNotZero_modulus4() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", true);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 4);
        setContextField(ctx, "lbitWorkArea", 0xABCDEFL); // low 4 bits non‑zero

        assertThrows(IllegalArgumentException.class,
                () -> invokeDecode(codec, new byte[0], 0, 0, ctx));
    }

    @Test
    void validateCharacterFailsWhenLowBitsNotZero_modulus5() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", true);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 5);
        setContextField(ctx, "lbitWorkArea", 0x1FFFFFL); // low 1 bit is 1

        assertThrows(IllegalArgumentException.class,
                () -> invokeDecode(codec, new byte[0], 0, 0, ctx));
    }

    @Test
    void validateCharacterFailsWhenLowBitsNotZero_modulus7() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", true);
        setContextField(ctx, "pos", 0);
        setContextField(ctx, "modulus", 7);
        setContextField(ctx, "lbitWorkArea", 0x7L); // low 3 bits are not zero

        assertThrows(IllegalArgumentException.class,
                () -> invokeDecode(codec, new byte[0], 0, 0, ctx));
    }

    /* --------------------------------------------------------------------- */
    /*                     Null argument handling                              */
    /* --------------------------------------------------------------------- */

    @Test
    void decode_nullInputArray_throwsNullPointerException() throws Exception {
        Base32 codec = new Base32();
        Object ctx = newContext();
        setContextField(ctx, "eof", false);
        assertThrows(NullPointerException.class,
                () -> invokeDecode(codec, null, 0, 1, ctx));
    }

    @Test
    void decode_nullContext_throwsNullPointerException() throws Exception {
        Base32 codec = new Base32();
        byte[] input = "A".getBytes(StandardCharsets.US_ASCII);
        assertThrows(NullPointerException.class,
                () -> invokeDecode(codec, input, 0, 1, null));
    }
}
```