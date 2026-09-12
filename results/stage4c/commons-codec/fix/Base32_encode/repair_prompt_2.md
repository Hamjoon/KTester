


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
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.apache.commons.codec.CodecPolicy;
import java.util.Arrays;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class Base32_encode_Test {

    private Base32 base32;

    private BaseNCodec.Context context;

    @BeforeEach
    void setupBeforeEach() {
        base32 = new Base32();
        context = new BaseNCodec.Context();
        // Ensure buffer is initialized
        context.buffer = new byte[1024];
    }

    @AfterEach
    void teardownAfterEach() {
        base32 = null;
        context = null;
    }

    @Test
    void testEncodeWithEmptyInput() {
        byte[] input = new byte[0];
        byte[] expectedOutput = new byte[0];
        base32.encode(input, 0, input.length, context);
        // EOF
        base32.encode(null, 0, -1, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    void testEncodeWithSingleByteInput() {
        byte[] input = new byte[] { (byte) 'f' };
        byte[] expectedOutput = "MY======".getBytes();
        base32.encode(input, 0, input.length, context);
        // EOF
        base32.encode(null, 0, -1, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    void testEncodeWithMultipleBytesInput() {
        byte[] input = "foobar".getBytes();
        byte[] expectedOutput = "MZXW6YTBOI======".getBytes();
        base32.encode(input, 0, input.length, context);
        // EOF
        base32.encode(null, 0, -1, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    void testEOFConditionWithNoLeftoversAndNoChunking() {
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        base32.encode(null, 0, -1, context);
        assertTrue(context.eof);
        assertEquals(0, context.pos);
    }

    @Test
    void testEOFConditionWithModulus1() {
        context.eof = false;
        context.modulus = 1;
        context.pos = 0;
        base32.encode(null, 0, -1, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        assertTrue(context.eof);
        assertEquals(8, context.pos);
    }

    @Test
    void testEOFConditionWithModulus2() {
        context.eof = false;
        context.modulus = 2;
        context.pos = 0;
        base32.encode(null, 0, -1, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        assertTrue(context.eof);
        assertEquals(8, context.pos);
    }

    @Test
    void testEOFConditionWithModulus3() {
        context.eof = false;
        context.modulus = 3;
        context.pos = 0;
        base32.encode(null, 0, -1, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        assertTrue(context.eof);
        assertEquals(8, context.pos);
    }

    @Test
    void testEOFConditionWithModulus4() {
        context.eof = false;
        context.modulus = 4;
        context.pos = 0;
        base32.encode(null, 0, -1, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        assertTrue(context.eof);
        assertEquals(8, context.pos);
    }

    @Test
    void testNormalProcessingWithFullBlockOf5Bytes() {
        byte[] input = "hello".getBytes();
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        base32.encode(input, 0, input.length, context);
        // EOF
        base32.encode(null, 0, -1, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        assertEquals(8, context.pos);
        assertEquals(0, context.modulus);
    }

    @Test
    void testProcessingWithPartialBlockOfBytes() {
        byte[] input = "abc".getBytes();
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        base32.encode(input, 0, input.length, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        assertEquals(3, context.modulus);
    }

    @Test
    void testNormalProcessingWithTwoFullBlocks() {
        byte[] input = "hellohello".getBytes();
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        base32.encode(input, 0, input.length, context);
        // EOF
        base32.encode(null, 0, -1, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        assertEquals(16, context.pos);
        assertEquals(0, context.modulus);
    }

    @Test
    void testProcessingWithOneFullBlockAndOneAdditionalByte() {
        byte[] input = "helloa".getBytes();
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        base32.encode(input, 0, input.length, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        assertEquals(1, context.modulus);
    }

    @Test
    void testProcessingWithOneFullBlockAndTwoAdditionalBytes() {
        byte[] input = "helloa".getBytes();
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        base32.encode(input, 0, input.length, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        assertEquals(1, context.modulus);
    }

    @Test
    void testProcessingWithOneFullBlockAndThreeAdditionalBytes() {
        byte[] input = "helloabc".getBytes();
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        base32.encode(input, 0, input.length, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        assertEquals(3, context.modulus);
    }

    @Test
    void testProcessingWithOneFullBlockAndFourAdditionalBytes() {
        byte[] input = "helloabcd".getBytes();
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        base32.encode(input, 0, input.length, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        assertEquals(4, context.modulus);
    }

    @Test
    void testScenarioWithImpossibleModulus() {
        byte[] input = "hello".getBytes();
        context.eof = false;
        context.modulus = 5;
        context.pos = 0;
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            base32.encode(input, 0, input.length, context);
        });
        assertEquals("Impossible modulus 5", exception.getMessage());
    }

    @Test
    void testScenarioWithNullInputAndZeroInAvail() {
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        assertThrows(NullPointerException.class, () -> {
            base32.encode(null, 0, 0, context);
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
│     ├─ testProcessingWithOneFullBlockAndOneAdditionalByte() ✔
│     ├─ testEncodeWithEmptyInput() ✔
│     ├─ testProcessingWithOneFullBlockAndFourAdditionalBytes() ✔
│     ├─ testProcessingWithPartialBlockOfBytes() ✔
│     ├─ testEncodeWithSingleByteInput() ✔
│     ├─ testNormalProcessingWithFullBlockOf5Bytes() ✔
│     ├─ testEOFConditionWithNoLeftoversAndNoChunking() ✔
│     ├─ testEOFConditionWithModulus1() ✔
│     ├─ testEOFConditionWithModulus2() ✔
│     ├─ testEOFConditionWithModulus3() ✔
│     ├─ testEOFConditionWithModulus4() ✔
│     ├─ testScenarioWithNullInputAndZeroInAvail() ✘ Expected java.lang.NullPointerException to be thrown, but nothing was thrown.
│     ├─ testProcessingWithOneFullBlockAndTwoAdditionalBytes() ✔
│     ├─ testNormalProcessingWithTwoFullBlocks() ✔
│     ├─ testEncodeWithMultipleBytesInput() ✔
│     ├─ testScenarioWithImpossibleModulus() ✘ Expected java.lang.IllegalStateException to be thrown, but nothing was thrown.
│     └─ testProcessingWithOneFullBlockAndThreeAdditionalBytes() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (2):
  JUnit Jupiter:Base32_encode_Test:testScenarioWithNullInputAndZeroInAvail()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_encode_Test', methodName = 'testScenarioWithNullInputAndZeroInAvail', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.NullPointerException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.binary.Base32_encode_Test.testScenarioWithNullInputAndZeroInAvail(Base32_encode_Test.java:234)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:Base32_encode_Test:testScenarioWithImpossibleModulus()
    MethodSource [className = 'org.apache.commons.codec.binary.Base32_encode_Test', methodName = 'testScenarioWithImpossibleModulus', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.IllegalStateException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.binary.Base32_encode_Test.testScenarioWithImpossibleModulus(Base32_encode_Test.java:223)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]

Test run finished after 405 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        17 tests found           ]
[         0 tests skipped         ]
[        17 tests started         ]
[         0 tests aborted         ]
[        15 tests successful      ]
[         2 tests failed          ]


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
		int org.apache.commons.codec.binary.BaseNCodec.Context.modulus;
		int org.apache.commons.codec.binary.BaseNCodec.Context.pos;
		long org.apache.commons.codec.binary.BaseNCodec.Context.lbitWorkArea;
		int org.apache.commons.codec.binary.BaseNCodec.Context.currentLinePos;
		boolean org.apache.commons.codec.binary.BaseNCodec.Context.eof;
	
class `org.apache.commons.codec.binary.BaseNCodec`:
	dependent fields:
		byte org.apache.commons.codec.binary.BaseNCodec.pad;
		int org.apache.commons.codec.binary.BaseNCodec.lineLength;
	
class `org.apache.commons.codec.binary.Base32`:
	dependent fields:
		int org.apache.commons.codec.binary.Base32.MASK_5BITS;
		int org.apache.commons.codec.binary.Base32.BYTES_PER_UNENCODED_BLOCK;
		byte[] org.apache.commons.codec.binary.Base32.encodeTable;
		byte[] org.apache.commons.codec.binary.Base32.lineSeparator;
		int org.apache.commons.codec.binary.Base32.BYTES_PER_ENCODED_BLOCK;
		int org.apache.commons.codec.binary.Base32.encodeSize;
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
