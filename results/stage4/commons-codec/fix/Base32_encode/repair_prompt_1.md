


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
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.BaseNCodec.Context;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class Base32_encode_Test {

    private Base32 base32;

    private Context context;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize Base32 instance and Context before each test
        base32 = new Base32();
        context = new Context();
    }

    private byte[] getBuffer(Context context) {
        // Assuming context.buffer is a field in the Context class that holds the encoded result
        // Adjust as necessary based on actual implementation
        return Arrays.copyOfRange(context.buffer, 0, context.pos);
    }

    @Test
    void testEncodeEmptyArray() {
        byte[] input = new byte[0];
        // Expected result for empty input
        byte[] expected = new byte[0];
        base32.encode(input, 0, input.length, context);
        // Check the context's buffer for the result
        assertArrayEquals(expected, getBuffer(context));
    }

    @Test
    void testEncodeSingleByte() {
        byte[] input = new byte[] { (byte) 'f' };
        // Expected Base32 result
        byte[] expected = "MY======".getBytes();
        base32.encode(input, 0, input.length, context);
        assertArrayEquals(expected, getBuffer(context));
    }

    @Test
    void testEncodeEOFHandling() {
        context.eof = false;
        base32.encode(new byte[0], 0, -1, context);
        assertTrue(context.eof, "context.eof should be true");
        context.eof = false;
        context.modulus = 0;
        base32.encode(new byte[0], 0, -1, context);
        context.eof = false;
        base32.encode(new byte[] { 1, 2, 3, 4, 5 }, 0, 0, context);
        assertFalse(context.eof, "context.eof remains false");
    }

    @Test
    void testEncodeProcessingBasedOnModulus() {
        context.modulus = 1;
        byte[] input1 = new byte[] { 0, 0, 0, 0, 0 };
        base32.encode(input1, 0, 5, context);
        context.modulus = 2;
        base32.encode(input1, 0, 5, context);
        context.modulus = 3;
        base32.encode(input1, 0, 5, context);
        context.modulus = 4;
        base32.encode(input1, 0, 5, context);
        context.modulus = 0;
        byte[] input2 = new byte[] { 1, 2, 3, 4, 5 };
        base32.encode(input2, 0, 5, context);
        byte[] input3 = new byte[] { 1, 2, 3 };
        base32.encode(input3, 0, 3, context);
    }

    @Test
    void testEncodeLineSeparatorHandling() {
        context.currentLinePos = 8;
        byte[] input = new byte[] { 0, 0, 0, 0, 0 };
        base32.encode(input, 0, 5, context);
        context.currentLinePos = 0;
        base32.encode(input, 0, 5, context);
        context.currentLinePos = 8;
        byte[] input2 = new byte[] { 1, 2, 3, 4, 5 };
        base32.encode(input2, 0, 5, context);
        context.currentLinePos = 7;
        base32.encode(input2, 0, 5, context);
    }

    @Test
    void testEncodeExceptionHandling() {
        context.modulus = 5;
        assertThrows(IllegalStateException.class, () -> {
            base32.encode(new byte[] { 0, 0, 0, 0, 0 }, 0, 5, context);
        });
        context.modulus = -1;
        assertThrows(IllegalStateException.class, () -> {
            base32.encode(new byte[] { 0, 0, 0, 0, 0 }, 0, 5, context);
        });
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
│     ├─ testEncodeSingleByte() ✘ array lengths differ, expected: <8> but was: <0>
│     ├─ testEncodeEmptyArray() ✘ Cannot read the array length because "original" is null
│     ├─ testEncodeExceptionHandling() ✘ Expected java.lang.IllegalStateException to be thrown, but nothing was thrown.
│     ├─ testEncodeEOFHandling() ✔
│     ├─ testEncodeProcessingBasedOnModulus() ✔
│     └─ testEncodeLineSeparatorHandling() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (3):
  JUnit Jupiter:Base32_encode_Test:testEncodeSingleByte()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_encode_Test', methodName = 'testEncodeSingleByte', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: array lengths differ, expected: <8> but was: <0>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertArrayEquals.assertArraysHaveSameLength(AssertArrayEquals.java:428)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:205)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:63)
       org.junit.jupiter.api.AssertArrayEquals.assertArrayEquals(AssertArrayEquals.java:59)
       org.junit.jupiter.api.Assertions.assertArrayEquals(Assertions.java:1229)
       org.apache.commons.codec.binary.Base32_encode_Test.testEncodeSingleByte(Base32_encode_Test.java:49)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       [...]
  JUnit Jupiter:Base32_encode_Test:testEncodeEmptyArray()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_encode_Test', methodName = 'testEncodeEmptyArray', methodParameterTypes = '']
    => java.lang.NullPointerException: Cannot read the array length because "original" is null
       java.base/java.util.Arrays.copyOfRange(Arrays.java:3823)
       org.apache.commons.codec.binary.Base32_encode_Test.getBuffer(Base32_encode_Test.java:30)
       org.apache.commons.codec.binary.Base32_encode_Test.testEncodeEmptyArray(Base32_encode_Test.java:40)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
       org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
       [...]
  JUnit Jupiter:Base32_encode_Test:testEncodeExceptionHandling()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_encode_Test', methodName = 'testEncodeExceptionHandling', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.IllegalStateException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.binary.Base32_encode_Test.testEncodeExceptionHandling(Base32_encode_Test.java:100)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]

Test run finished after 407 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[         6 tests found           ]
[         0 tests skipped         ]
[         6 tests started         ]
[         0 tests aborted         ]
[         3 tests successful      ]
[         3 tests failed          ]


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
		boolean org.apache.commons.codec.binary.BaseNCodec.Context.eof;
		int org.apache.commons.codec.binary.BaseNCodec.Context.currentLinePos;
		int org.apache.commons.codec.binary.BaseNCodec.Context.pos;
		int org.apache.commons.codec.binary.BaseNCodec.Context.modulus;
		long org.apache.commons.codec.binary.BaseNCodec.Context.lbitWorkArea;
	
class `org.apache.commons.codec.binary.BaseNCodec`:
	dependent fields:
		int org.apache.commons.codec.binary.BaseNCodec.lineLength;
		byte org.apache.commons.codec.binary.BaseNCodec.pad;
	
class `org.apache.commons.codec.binary.Base32`:
	dependent fields:
		byte[] org.apache.commons.codec.binary.Base32.lineSeparator;
		int org.apache.commons.codec.binary.Base32.MASK_5BITS;
		byte[] org.apache.commons.codec.binary.Base32.encodeTable;
		int org.apache.commons.codec.binary.Base32.BYTES_PER_ENCODED_BLOCK;
		int org.apache.commons.codec.binary.Base32.encodeSize;
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
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`;
		method ` void encode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`;
class `org.apache.commons.codec.digest.XXHash32`:
	related functions:
		method `public void update(byte[], int, int)` returns `void`, related with `org.apache.commons.codec.binary.Base32.encode(byte[], int, int, Context)`;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
