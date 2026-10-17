


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
class Base64_encode_Test {

    /**
     * Instance used for the majority of tests (no line‑chunking, standard alphabet).
     */
    private Base64 base64;

    /**
     * Context object required by the package‑private {@code encode} method.
     */
    private BaseNCodec.Context ctx;

    @BeforeEach
    void setupBeforeEach() {
        // Default Base64 – no line length, standard alphabet, padding enabled.
        base64 = new Base64();
        ctx = new BaseNCodec.Context();
    }

    @AfterEach
    void teardownAfterEach() {
        // Help GC and avoid accidental reuse between tests.
        base64 = null;
        ctx = null;
    }

    /**
     * Helper that runs the internal {@code encode} method on {@code data}
     * and returns the bytes that were written to the context.
     *
     * @param data   data to encode
     * @return encoded bytes
     */
    private byte[] runEncode(byte[] data) {
        // Feed the whole input in one call.
        base64.encode(data, 0, data.length, ctx);
        // Signal EOF so the encoder can flush any remaining bytes/padding.
        base64.encode(new byte[0], 0, -1, ctx);
        // The context buffer may be larger than the actual output; copy only the valid part.
        byte[] result = new byte[ctx.pos];
        System.arraycopy(ctx.buffer, 0, result, 0, ctx.pos);
        return result;
    }

    @Test
    void encode_fullBlock_noPadding() {
        // "Man" -> "TWFu"
        byte[] input = "Man".getBytes();
        // public API for reference
        byte[] expected = Base64.encodeBase64(input);
        byte[] actual = runEncode(input);
        assertArrayEquals(expected, actual, "Encoding of a full 3‑byte block should match public API");
    }

    @Test
    void encode_oneByteRemainder() {
        // "M" -> "TQ=="
        byte[] input = "M".getBytes();
        byte[] expected = Base64.encodeBase64(input);
        byte[] actual = runEncode(input);
        assertArrayEquals(expected, actual, "Encoding with a 1‑byte remainder must include two padding chars");
    }

    @Test
    void encode_twoByteRemainder() {
        // "Ma" -> "TWE="
        byte[] input = "Ma".getBytes();
        byte[] expected = Base64.encodeBase64(input);
        byte[] actual = runEncode(input);
        assertArrayEquals(expected, actual, "Encoding with a 2‑byte remainder must include one padding char");
    }

    @Test
    void encode_multipleBlocksWithChunking() {
        // Use a line length of 8 characters (i.e. two encoded blocks) and LF as separator.
        Base64 chunked = new Base64(8, new byte[] { '\n' });
        BaseNCodec.Context chunkCtx = new BaseNCodec.Context();
        // 12 bytes => 16 Base64 chars; with line length 8 we expect two lines.
        // 12 bytes
        byte[] input = "HelloWorld!!".getBytes();
        chunked.encode(input, 0, input.length, chunkCtx);
        chunked.encode(new byte[0], 0, -1, chunkCtx);
        byte[] actual = new byte[chunkCtx.pos];
        System.arraycopy(chunkCtx.buffer, 0, actual, 0, chunkCtx.pos);
        // Build the expected string using the public API and then insert LF after every 8 chars.
        String full = new String(Base64.encodeBase64(input));
        StringBuilder expectedBuilder = new StringBuilder();
        for (int i = 0; i < full.length(); i += 8) {
            int end = Math.min(i + 8, full.length());
            expectedBuilder.append(full, i, end);
            if (end < full.length()) {
                expectedBuilder.append('\n');
            }
        }
        byte[] expected = expectedBuilder.toString().getBytes();
        assertArrayEquals(expected, actual, "Chunked encoding must insert line separators at the configured length");
    }

    @Test
    void encode_emptyInput() {
        byte[] input = new byte[0];
        byte[] expected = Base64.encodeBase64(input);
        byte[] actual = runEncode(input);
        assertArrayEquals(expected, actual, "Encoding an empty array should yield an empty result");
    }

    @Test
    void encode_nullInputThrows() {
        assertThrows(NullPointerException.class, () -> base64.encode(null, 0, 0, ctx));
    }

    @Test
    void testEncode_NullInputArray() {
        // When the input array is null the method should throw NPE.
        assertThrows(NullPointerException.class, () -> base64.encode(null, 0, 5, new BaseNCodec.Context()));
    }

    @Test
    void testEncode_InvalidInPos() {
        // inPos beyond the array length should cause an AIOOBE.
        byte[] data = new byte[] { 1, 2, 3 };
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> base64.encode(data, 5, 1, new BaseNCodec.Context()));
    }

    @Test
    void testEncode_NullContext() {
        // Passing a null Context must result in NPE when the method accesses its fields.
        byte[] data = new byte[] { 1, 2, 3 };
        assertThrows(NullPointerException.class, () -> base64.encode(data, 0, 3, null));
    }

    @Test
    void testEncode_InvalidModulusAtEOF() throws Exception {
        // Create a context with an illegal modulus value (e.g., 3) and trigger EOF processing.
        BaseNCodec.Context corruptedContext = new BaseNCodec.Context();
        // Use reflection to set the private field 'modulus' (it's package‑private, so we can set directly)
        Field modulusField = BaseNCodec.Context.class.getDeclaredField("modulus");
        modulusField.setAccessible(true);
        // illegal value
        modulusField.setInt(corruptedContext, 3);
        // The encode call with inAvail < 0 should hit the default branch and throw IllegalStateException.
        assertThrows(IllegalStateException.class, () -> base64.encode(new byte[] { 1, 2, 3 }, 0, -1, corruptedContext));
    }

    @Test
    void testEncode_NullEncodeTable() throws Exception {
        // Force encodeTable to null via reflection.
        // standard instance
        Base64 custom = new Base64();
        Field encodeTableField = Base64.class.getDeclaredField("encodeTable");
        encodeTableField.setAccessible(true);
        encodeTableField.set(custom, null);
        // Any attempt to encode now must hit a NPE when encodeTable is dereferenced.
        assertThrows(NullPointerException.class, () -> custom.encode(new byte[] { 1, 2, 3 }, 0, 3, new BaseNCodec.Context()));
    }

    @Test
    void testEncode_NullLineSeparatorWhenChunked() throws Exception {
        // Create a chunked Base64 (lineLength > 0) and then null out the lineSeparator.
        // lineLength 4 triggers chunking
        Base64 chunked = new Base64(4, new byte[] { '\r', '\n' });
        Field lineSepField = Base64.class.getDeclaredField("lineSeparator");
        lineSepField.setAccessible(true);
        // make lineSeparator null
        lineSepField.set(chunked, null);
        BaseNCodec.Context chunkCtx = new BaseNCodec.Context();
        // Encode a single byte so that after EOF processing we have a non‑zero currentLinePos.
        // 1 byte -> remainder case
        byte[] data = "M".getBytes();
        chunked.encode(data, 0, data.length, chunkCtx);
        // EOF triggers the block that attempts to copy the (null) lineSeparator.
        assertThrows(NullPointerException.class, () -> chunked.encode(new byte[0], 0, -1, chunkCtx));
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ Base64_encode_Test ✔
│     ├─ encode_multipleBlocksWithChunking() ✘ Chunked encoding must insert line separators at the configured length ==> array lengths differ, expected: <17> but was: <18>
│     ├─ encode_nullInputThrows() ✘ Expected java.lang.NullPointerException to be thrown, but nothing was thrown.
│     ├─ testEncode_NullLineSeparatorWhenChunked() ✔
│     ├─ encode_emptyInput() ✘ java.lang.NullPointerException
│     ├─ encode_twoByteRemainder() ✔
│     ├─ testEncode_NullInputArray() ✔
│     ├─ testEncode_NullEncodeTable() ✔
│     ├─ testEncode_InvalidInPos() ✔
│     ├─ testEncode_NullContext() ✔
│     ├─ encode_fullBlock_noPadding() ✔
│     ├─ encode_oneByteRemainder() ✔
│     └─ testEncode_InvalidModulusAtEOF() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (3):
  JUnit Jupiter:Base64_encode_Test:encode_multipleBlocksWithChunking()
    MethodSource [className = 'org.apache.commons.codec.binary.Base64_encode_Test', methodName = 'encode_multipleBlocksWithChunking', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Chunked encoding must insert line separators at the configured length ==> array lengths differ, expected: <17> but was: <18>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertArrayEquals.assertArraysHaveSameLength(AssertArrayEquals.java:428)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:205)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:63)
       org.junit.jupiter.api.Assertions.assertArrayEquals(Assertions.java:1238)
       org.apache.commons.codec.binary.Base64_encode_Test.encode_multipleBlocksWithChunking(Base64_encode_Test.java:105)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:Base64_encode_Test:encode_nullInputThrows()
    MethodSource [className = 'org.apache.commons.codec.binary.Base64_encode_Test', methodName = 'encode_nullInputThrows', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.NullPointerException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.binary.Base64_encode_Test.encode_nullInputThrows(Base64_encode_Test.java:118)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:Base64_encode_Test:encode_emptyInput()
    MethodSource [className = 'org.apache.commons.codec.binary.Base64_encode_Test', methodName = 'encode_emptyInput', methodParameterTypes = '']
    => java.lang.NullPointerException
       java.base/java.lang.System.arraycopy(Native Method)
       org.apache.commons.codec.binary.Base64_encode_Test.runEncode(Base64_encode_Test.java:50)
       org.apache.commons.codec.binary.Base64_encode_Test.encode_emptyInput(Base64_encode_Test.java:112)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       [...]

Test run finished after 372 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        12 tests found           ]
[         0 tests skipped         ]
[        12 tests started         ]
[         0 tests aborted         ]
[         9 tests successful      ]
[         3 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class org.apache.commons.codec.binary.Base64: 

 * Provides Base64 encoding and decoding as defined by <a href="http://www.ietf.org/rfc/rfc2045.txt">RFC 2045</a>.
 *
 * <p>
 * This class implements section <cite>6.8. Base64 Content-Transfer-Encoding</cite> from RFC 2045 <cite>Multipurpose
 * Internet Mail Extensions (MIME) Part One: Format of Internet Message Bodies</cite> by Freed and Borenstein.
 * </p>
 * <p>
 * The class can be parameterized in the following manner with various constructors:
 * </p>
 * <ul>
 * <li>URL-safe mode: Default off.</li>
 * <li>Line length: Default 76. Line length that aren't multiples of 4 will still essentially end up being multiples of
 * 4 in the encoded data.
 * <li>Line separator: Default is CRLF ("\r\n")</li>
 * </ul>
 * <p>
 * The URL-safe parameter is only applied to encode operations. Decoding seamlessly handles both modes.
 * </p>
 * <p>
 * Since this class operates directly on byte streams, and not character streams, it is hard-coded to only
 * encode/decode character encodings which are compatible with the lower 127 ASCII chart (ISO-8859-1, Windows-1252,
 * UTF-8, etc).
 * </p>
 * <p>
 * This class is thread-safe.
 * </p>
 * <p>
 * You can configure instances with the {@link Builder}.
 * </p>
 * <pre>
 * Base64 base64 = Base64.builder()
 *   .setDecodingPolicy(DecodingPolicy.LENIENT) // default is lenient, null resets to default
 *   .setEncodeTable(customEncodeTable)         // default is built in, null resets to default
 *   .setLineLength(0)                          // default is none
 *   .setLineSeparator('\r', '\n')              // default is CR LF, null resets to default
 *   .setPadding('=')                           // default is =
 *   .setUrlSafe(false)                         // default is false
 *   .get()
 * </pre>
 *
 * @see <a href="http://www.ietf.org/rfc/rfc2045.txt">RFC 2045</a>
 * @since 1.0
 

api document of method encode(byte[], int, int, Context): 

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
     

dependent classes: 
class `org.apache.commons.codec.binary.BaseNCodec.Context`:
	dependent fields:
		int org.apache.commons.codec.binary.BaseNCodec.Context.currentLinePos;
		int org.apache.commons.codec.binary.BaseNCodec.Context.pos;
		int org.apache.commons.codec.binary.BaseNCodec.Context.modulus;
		int org.apache.commons.codec.binary.BaseNCodec.Context.ibitWorkArea;
		boolean org.apache.commons.codec.binary.BaseNCodec.Context.eof;
	
class `org.apache.commons.codec.binary.Base64`:
	dependent fields:
		byte[] org.apache.commons.codec.binary.Base64.STANDARD_ENCODE_TABLE;
		int org.apache.commons.codec.binary.Base64.encodeSize;
		byte[] org.apache.commons.codec.binary.Base64.encodeTable;
		int org.apache.commons.codec.binary.Base64.BYTES_PER_ENCODED_BLOCK;
		byte[] org.apache.commons.codec.binary.Base64.lineSeparator;
		int org.apache.commons.codec.binary.Base64.BYTES_PER_UNENCODED_BLOCK;
		int org.apache.commons.codec.binary.Base64.MASK_6BITS;
	related functions:
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base64.encode(byte[], int, int, Context)`, api document: 
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
class `org.apache.commons.codec.binary.BaseNCodec`:
	dependent fields:
		byte org.apache.commons.codec.binary.BaseNCodec.pad;
		int org.apache.commons.codec.binary.BaseNCodec.lineLength;
	
class `org.apache.commons.codec.binary.Base32`:
	related functions:
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base64.encode(byte[], int, int, Context)`, api document: 
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
		method ` void encode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base64.encode(byte[], int, int, Context)`, api document: 
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
class `org.apache.commons.codec.language.ColognePhonetic.CologneInputBuffer`:
	related functions:
		method `protected char[] copyData(int, int)` returns `char[]`, related with `org.apache.commons.codec.binary.Base64.encode(byte[], int, int, Context)`;
class `org.apache.commons.codec.binary.StringUtils`:
	related functions:
		method `private static IllegalStateException newIllegalStateException(String, UnsupportedEncodingException)` returns `java.lang.IllegalStateException`, related with `org.apache.commons.codec.binary.Base64.encode(byte[], int, int, Context)`;
class `org.apache.commons.codec.binary.Base16`:
	related functions:
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base64.encode(byte[], int, int, Context)`;
		method ` void encode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base64.encode(byte[], int, int, Context)`;
class `org.apache.commons.codec.digest.XXHash32`:
	related functions:
		method `public void update(byte[], int, int)` returns `void`, related with `org.apache.commons.codec.binary.Base64.encode(byte[], int, int, Context)`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
