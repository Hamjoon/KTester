You are tasked with creating a framework for a test class in Java. Now analyze the following information carefully:

1. The method that needs to be tested and its parameters.
2. The constructor of the target class and its parameters.
3. Any dependencies or external resources used by the target class.

Based on this analysis, complete the test class template in the provided structure:

1. Initialize necessary fields and dependencies within setupBeforeAll() and setupBeforeEach().
2. Handle any required cleanup in teardownAfterEach() and teardownAfterAll().
3. Make sure not to invoke any private methods or fields of the target class and dependent class.
4. Refer to the "given" phase in the existing test class for guidance.
5. Add necessary import statements and ensure there are no syntax errors in the generated code.

@input{focal method and target class}
Here is the code of focal method, `isInAlphabet(byte)`, with the focal class `Base64_isInAlphabet_Test`:
```Java
package org.apache.commons.codec.binary;
import java.math.BigInteger;
import java.util.Objects;
import org.apache.commons.codec.CodecPolicy;
import java.util.Arrays;
public class Base64 extends BaseNCodec {
    private final byte[] decodeTable;
    @Override
    protected boolean isInAlphabet(final byte octet) {
        return ((octet >= 0) && (octet < decodeTable.length)) && (decodeTable[octet] != (-1));
    }
public static Builder builder();
    @Override
void decode(final byte[] input, int inPos, final int inAvail, final Context context);
    public static byte[] decodeBase64(final byte[] base64Data);
    public static byte[] decodeBase64(final String base64String);
    public static BigInteger decodeInteger(final byte[] pArray);
    @Override
void encode(final byte[] in, int inPos, final int inAvail, final Context context);
    public static byte[] encodeBase64(final byte[] binaryData);
    public static byte[] encodeBase64(final byte[] binaryData, final boolean isChunked);
    public static byte[] encodeBase64(final byte[] binaryData, final boolean isChunked, final boolean urlSafe);
    public static byte[] encodeBase64(final byte[] binaryData, final boolean isChunked, final boolean urlSafe, final int maxResultSize);
    public static byte[] encodeBase64Chunked(final byte[] binaryData);
    public static String encodeBase64String(final byte[] binaryData);
    public static byte[] encodeBase64URLSafe(final byte[] binaryData);
    public static String encodeBase64URLSafeString(final byte[] binaryData);
    public static byte[] encodeInteger(final BigInteger bigInteger);
    byte[] getLineSeparator();
    public static boolean isBase64(final byte octet);
    public static boolean isBase64(final byte[] arrayOctet);
    public static boolean isBase64(final String base64);
    public boolean isUrlSafe();
    static byte[] toIntegerBytes(final BigInteger bigInt);
}
```


@input{context information}

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
 

constructors for class `org.apache.commons.codec.binary.Base64`: 
params: 
body:
```java
    public Base64() {
        this(0);
    }

```
params: 
body:
```java
    public Base64(final boolean urlSafe) {
        this(MIME_CHUNK_SIZE, CHUNK_SEPARATOR, urlSafe);
    }

```
params: 
body:
```java
    public Base64(final int lineLength) {
        this(lineLength, CHUNK_SEPARATOR);
    }

```
params: 
body:
```java
    public Base64(final int lineLength, final byte[] lineSeparator) {
        this(lineLength, lineSeparator, false);
    }

```
params: 
body:
```java
    public Base64(final int lineLength, final byte[] lineSeparator, final boolean urlSafe) {
        this(lineLength, lineSeparator, PAD_DEFAULT, toUrlSafeEncodeTable(urlSafe), DECODING_POLICY_DEFAULT);
    }

```
params: org.apache.commons.codec.CodecPolicy decodingPolicy 
body:
```java
    public Base64(final int lineLength, final byte[] lineSeparator, final boolean urlSafe, final CodecPolicy decodingPolicy) {
        this(lineLength, lineSeparator, PAD_DEFAULT, toUrlSafeEncodeTable(urlSafe), decodingPolicy);
    }

```
params: org.apache.commons.codec.CodecPolicy decodingPolicy 
body:
```java
    private Base64(final int lineLength, final byte[] lineSeparator, final byte padding, final byte[] encodeTable, final CodecPolicy decodingPolicy) {
        super(BYTES_PER_UNENCODED_BLOCK, BYTES_PER_ENCODED_BLOCK, lineLength, toLength(lineSeparator), padding, decodingPolicy);
        Objects.requireNonNull(encodeTable, "encodeTable");
        if (encodeTable.length != ALPHABET_LENGTH) {
            throw new IllegalArgumentException("encodeTable must have exactly 64 entries.");
        }
        this.isUrlSafe = encodeTable == URL_SAFE_ENCODE_TABLE;
        if (encodeTable == STANDARD_ENCODE_TABLE || this.isUrlSafe) {
            decodeTable = DECODE_TABLE;
            // No need of a defensive copy of an internal table.
            this.encodeTable = encodeTable;
        } else {
            this.encodeTable = encodeTable.clone();
            this.decodeTable = calculateDecodeTable(this.encodeTable);
        }
        // TODO could be simplified if there is no requirement to reject invalid line sep when length <=0
        // @see test case Base64Test.testConstructors()
        if (lineSeparator != null) {
            final byte[] lineSeparatorCopy = lineSeparator.clone();
            if (containsAlphabetOrPad(lineSeparatorCopy)) {
                final String sep = StringUtils.newStringUtf8(lineSeparatorCopy);
                throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + sep + "]");
            }
            if (lineLength > 0) { // null line-sep forces no chunking rather than throwing IAE
                this.encodeSize = BYTES_PER_ENCODED_BLOCK + lineSeparatorCopy.length;
                this.lineSeparator = lineSeparatorCopy;
            } else {
                this.encodeSize = BYTES_PER_ENCODED_BLOCK;
                this.lineSeparator = null;
            }
        } else {
            this.encodeSize = BYTES_PER_ENCODED_BLOCK;
            this.lineSeparator = null;
        }
    }

```

parameters in constructors and focal method: 
class `org.apache.commons.codec.CodecPolicy`:
api document : 
 * Defines encoding and decoding policies.
 *
 * @since 1.15
 
constructor:
```java
```

existing test class: 
```java
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.codec.binary;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for Base64 class.
 *
 * @see <a href="http://www.ietf.org/rfc/rfc2045.txt">RFC 2045</a>
 */
public class Base64Test {

    private static final String FOX_BASE64 = "VGhlIH@$#$@%F1aWN@#@#@@rIGJyb3duIGZve\n\r\t%#%#%#%CBqd##$#$W1wZWQgb3ZlciB0aGUgbGF6eSBkb2dzLg==";

    private static final String FOX_TEXT = "The quick brown fox jumped over the lazy dogs.";

    private static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;

    /**
     * Example test cases with valid characters but impossible combinations of
     * trailing characters (i.e. cannot be created during encoding).
     */
    static final String[] BASE64_IMPOSSIBLE_CASES = {
        "ZE==",
        "ZmC=",
        "Zm9vYE==",
        "Zm9vYmC=",
        "AB",
    };

    /**
     * Copy of the standard base-64 encoding table. Used to test decoding the final
     * character of encoded bytes.
     */
    private static final byte[] STANDARD_ENCODE_TABLE = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    /**
     * Test base 64 decoding of the final trailing bits. Trailing encoded bytes
     * cannot fit exactly into 6-bit characters so the last character has a limited
     * alphabet where the final bits are zero. This asserts that illegal final
     * characters throw an exception when decoding.
     *
     * @param nbits the number of trailing bits (must be a factor of 6 and {@code <24})
     */
    private static void assertBase64DecodingOfTrailingBits(final int nbits) {
        final Base64 codec = new Base64(0, null, false, CodecPolicy.STRICT);
        // Requires strict decoding
        assertTrue(codec.isStrictDecoding());
        assertEquals(CodecPolicy.STRICT, codec.getCodecPolicy());
        // A lenient decoder should not re-encode to the same bytes
        final Base64 defaultCodec = new Base64();
        assertFalse(defaultCodec.isStrictDecoding());
        assertEquals(CodecPolicy.LENIENT, defaultCodec.getCodecPolicy());

        // Create the encoded bytes. The first characters must be valid so fill with 'zero'
        // then pad to the block size.
        final int length = nbits / 6;
        final byte[] encoded = new byte[4];
......

        final byte[][] urlSafe3 = new byte[4][];
        // no padding (no '=' signs).
        urlSafe3[0] = StringUtils.getBytesUtf8("lO2NAxnkSTOZVg-2dATTcA");
        urlSafe3[1] = StringUtils.getBytesUtf8("K_fMJwH-Q5e0nr7tWsxwkA");
        urlSafe3[2] = StringUtils.getBytesUtf8("ZL4VS2_6QCWNGgEojnwxyg");
        urlSafe3[3] = StringUtils.getBytesUtf8("_3-PwBzbRxqMi1qTBhg_6A");

        for (int i = 0; i < 4; i++) {
            final byte[] encodedStandard = Base64.encodeBase64(ids[i]);
            final byte[] encodedUrlSafe = Base64.encodeBase64URLSafe(ids[i]);
            final byte[] decodedStandard = Base64.decodeBase64(standard[i]);
            final byte[] decodedUrlSafe1 = Base64.decodeBase64(urlSafe1[i]);
            final byte[] decodedUrlSafe2 = Base64.decodeBase64(urlSafe2[i]);
            final byte[] decodedUrlSafe3 = Base64.decodeBase64(urlSafe3[i]);

            // Very important debugging output should anyone
            // ever need to delve closely into this stuff.
//            {
//                System.out.println("reference: [" + Hex.encodeHexString(ids[i]) + "]");
//                System.out.println("standard:  [" + Hex.encodeHexString(decodedStandard) + "] From: ["
//                        + StringUtils.newStringUtf8(standard[i]) + "]");
//                System.out.println("safe1:     [" + Hex.encodeHexString(decodedUrlSafe1) + "] From: ["
//                        + StringUtils.newStringUtf8(urlSafe1[i]) + "]");
//                System.out.println("safe2:     [" + Hex.encodeHexString(decodedUrlSafe2) + "] From: ["
//                        + StringUtils.newStringUtf8(urlSafe2[i]) + "]");
//                System.out.println("safe3:     [" + Hex.encodeHexString(decodedUrlSafe3) + "] From: ["
//                        + StringUtils.newStringUtf8(urlSafe3[i]) + "]");
//            }

            assertArrayEquals(encodedStandard, standard[i], "standard encode uuid");
            assertArrayEquals(encodedUrlSafe, urlSafe3[i], "url-safe encode uuid");
            assertArrayEquals(decodedStandard, ids[i], "standard decode uuid");
            assertArrayEquals(decodedUrlSafe1, ids[i], "url-safe1 decode uuid");
            assertArrayEquals(decodedUrlSafe2, ids[i], "url-safe2 decode uuid");
            assertArrayEquals(decodedUrlSafe3, ids[i], "url-safe3 decode uuid");
        }
    }

    private String toString(final byte[] data) {
        final StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            buf.append(data[i]);
            if (i != data.length - 1) {
                buf.append(",");
            }
        }
        return buf.toString();
    }
}
```



@input{test class template}
```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class Base64_isInAlphabet_Test {
    @BeforeAll
    static void setupBeforeAll() {
    }

    @BeforeEach
    void setupBeforeEach() {
    }

    @AfterEach
    void teardownAfterEach() {
    }

    @AfterAll
    static void teardownAfterAll() {
    }
}
```

@output{initial test class}: now consider the invoke examples output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.