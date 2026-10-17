


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
            // make effectively final for the lambda
            final int len = length;
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
        // Expected: "MZXW6YTB\r\nOI======"
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
        // Cast the char to byte to satisfy the builder's setPadding(byte) signature
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
        // h
        0x68, // e
        0x65, // l
        0x6c, // l
        0x6c, // o
        0x6f, // 1
        0x31, // 2
        0x32, // 3
        0x33, // 4
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

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ Base32_encode_Test ✔
│     ├─ encodeEofLeftoverOneByte() ✔
│     ├─ encodeBase32HexVariant() ✔
│     ├─ encodeFullBlockNoSeparator() ✘ EOF flag should stay false after normal block processing ==> expected: <false> but was: <true>
│     ├─ encodeNullContext() ✔
│     ├─ encodeEofLeftoverFourBytes() ✔
│     ├─ encodeInvalidModulusThrows() ✔
│     ├─ encodeEofHandling() ✔
│     ├─ encodeEofEarlyReturn() ✔
│     ├─ encodeSingleChar() ✔
│     ├─ encodeInputArrayIndexOutOfBounds() ✔
│     ├─ encodeEofLeftoverThreeBytes() ✔
│     ├─ encodeWithChunking() ✘ array lengths differ, expected: <18> but was: <20>
│     ├─ encodeNegativeBytes() ✘ array contents differ at index [2], expected: <55> but was: <88>
│     ├─ encodeEofLeftoverTwoBytes() ✔
│     ├─ encodeFullBlockWithChunking() ✔
│     ├─ encodeNoSeparatorWhenLineLengthNotReached() ✘ array contents differ at index [8], expected: <71> but was: <10>
│     ├─ encodeNullInputArray() ✔
│     ├─ encodeNullLineSeparatorThrows() ✘ Cannot read the array length because "this.lineSeparator" is null
│     ├─ encodeCustomPadding() ✔
│     ├─ encodeRfcVectors() ✔
│     ├─ encodeIncrementallyMatchesBulk() ✔
│     ├─ encodeNegativeModulusThrows() ✔
│     └─ encodeEmpty() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (5):
  JUnit Jupiter:Base32_encode_Test:encodeFullBlockNoSeparator()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_encode_Test', methodName = 'encodeFullBlockNoSeparator', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: EOF flag should stay false after normal block processing ==> expected: <false> but was: <true>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertFalse.failNotFalse(AssertFalse.java:63)
       org.junit.jupiter.api.AssertFalse.assertFalse(AssertFalse.java:36)
       org.junit.jupiter.api.Assertions.assertFalse(Assertions.java:239)
       org.apache.commons.codec.binary.Base32_encode_Test.encodeFullBlockNoSeparator(Base32_encode_Test.java:190)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       [...]
  JUnit Jupiter:Base32_encode_Test:encodeWithChunking()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_encode_Test', methodName = 'encodeWithChunking', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: array lengths differ, expected: <18> but was: <20>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertArrayEquals.assertArraysHaveSameLength(AssertArrayEquals.java:428)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:205)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:63)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:59)
       org.junit.jupiter.api.Assertions.assertArrayEquals(Assertions.java:1229)
       org.apache.commons.codec.binary.Base32_encode_Test.encodeWithChunking(Base32_encode_Test.java:107)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Base32_encode_Test:encodeNegativeBytes()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_encode_Test', methodName = 'encodeNegativeBytes', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: array contents differ at index [2], expected: <55> but was: <88>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertArrayEquals.failArraysNotEqual(AssertArrayEquals.java:440)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:209)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:63)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:59)
       org.junit.jupiter.api.Assertions.assertArrayEquals(Assertions.java:1229)
       org.apache.commons.codec.binary.Base32_encode_Test.encodeNegativeBytes(Base32_encode_Test.java:298)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Base32_encode_Test:encodeNoSeparatorWhenLineLengthNotReached()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_encode_Test', methodName = 'encodeNoSeparatorWhenLineLengthNotReached', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: array contents differ at index [8], expected: <71> but was: <10>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertArrayEquals.failArraysNotEqual(AssertArrayEquals.java:440)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:209)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:63)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:59)
       org.junit.jupiter.api.Assertions.assertArrayEquals(Assertions.java:1229)
       org.apache.commons.codec.binary.Base32_encode_Test.encodeNoSeparatorWhenLineLengthNotReached(Base32_encode_Test.java:324)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Base32_encode_Test:encodeNullLineSeparatorThrows()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_encode_Test', methodName = 'encodeNullLineSeparatorThrows', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot read the array length because "this.lineSeparator" is null
       org.apache.commons.codec.binary.Base32.encode(Base32.java:585)
       org.apache.commons.codec.binary.Base32_encode_Test.encodeNullLineSeparatorThrows(Base32_encode_Test.java:391)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:156)
       [...]

Test run finished after 377 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        23 tests found           ]
[         0 tests skipped         ]
[        23 tests started         ]
[         0 tests aborted         ]
[        18 tests successful      ]
[         5 tests failed          ]


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
 

api document of method encode(byte[], int, int, Context): 

     * <p>
     * Encodes all of the provided data, starting at inPos, for inAvail bytes. Must be called at least twice: once with the data to encode, and once with
     * inAvail set to "-1" to alert encoder that EOF has been reached, so flush last remaining bytes (if not multiple of 5).
     * </p>
     *
     * @param input   byte[] array of binary data to Base32 encode.
     * @param inPos   Position to start reading data from.
     * @param inAvail Amount of bytes available from input for encoding.
     * @param context the context to be used
     

dependent classes: 
class `org.apache.commons.codec.binary.BaseNCodec.Context`:
	dependent fields:
		int org.apache.commons.codec.binary.BaseNCodec.Context.currentLinePos;
		long org.apache.commons.codec.binary.BaseNCodec.Context.lbitWorkArea;
		int org.apache.commons.codec.binary.BaseNCodec.Context.pos;
		int org.apache.commons.codec.binary.BaseNCodec.Context.modulus;
		boolean org.apache.commons.codec.binary.BaseNCodec.Context.eof;
	
class `org.apache.commons.codec.binary.BaseNCodec`:
	dependent fields:
		byte org.apache.commons.codec.binary.BaseNCodec.pad;
		int org.apache.commons.codec.binary.BaseNCodec.lineLength;
	
class `org.apache.commons.codec.binary.Base32`:
	dependent fields:
		byte[] org.apache.commons.codec.binary.Base32.lineSeparator;
		int org.apache.commons.codec.binary.Base32.encodeSize;
		int org.apache.commons.codec.binary.Base32.MASK_5BITS;
		int org.apache.commons.codec.binary.Base32.BYTES_PER_ENCODED_BLOCK;
		byte[] org.apache.commons.codec.binary.Base32.encodeTable;
		int org.apache.commons.codec.binary.Base32.BYTES_PER_UNENCODED_BLOCK;
	related functions:
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`, api document: 
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
     ;
class `org.apache.commons.codec.binary.Base64`:
	related functions:
		method ` void encode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`, api document: 
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
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`, api document: 
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
class `org.apache.commons.codec.language.ColognePhonetic.CologneInputBuffer`:
	related functions:
		method `protected char[] copyData(int, int)` returns `char[]`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`;
class `org.apache.commons.codec.binary.StringUtils`:
	related functions:
		method `private static IllegalStateException newIllegalStateException(String, UnsupportedEncodingException)` returns `java.lang.IllegalStateException`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`;
class `org.apache.commons.codec.binary.Base16`:
	related functions:
		method ` void encode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`;
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`;
class `org.apache.commons.codec.digest.XXHash32`:
	related functions:
		method `public void update(byte[], int, int)` returns `void`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
