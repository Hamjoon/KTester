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
Here is the code of focal method, `encode(byte[], int, int, Context)`, with the focal class `Base32_encode_Test`:
```Java
package org.apache.commons.codec.binary;
import java.util.Objects;
import org.apache.commons.codec.CodecPolicy;
public class Base32 extends BaseNCodec {
    private static final int BYTES_PER_ENCODED_BLOCK = 8;
    private static final int BYTES_PER_UNENCODED_BLOCK = 5;
    private static final int MASK_5BITS = 0x1f;
    private final int encodeSize;
    private final byte[] encodeTable;
    private final byte[] lineSeparator;
    @Override
    void encode(final byte[] input, int inPos, final int inAvail, final Context context) {
        // package protected for access from I/O streams
        if (context.eof) {
            return;
        }
        // inAvail < 0 is how we're informed of EOF in the underlying data we're
        // encoding.
        if (inAvail < 0) {
            context.eof = true;
            if ((0 == context.modulus) && (lineLength == 0)) {
                return;// no leftovers to process and not using chunking
    
            }
            final byte[] buffer = ensureBufferSize(encodeSize, context);
            final int savedPos = context.pos;
            switch (context.modulus) {
                // % 5
                case 0 :
                    break;
                case 1 :
                    // Only 1 octet; take top 5 bits then remainder
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 3)) & MASK_5BITS];// 8-1*5 = 3
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea << 2)) & MASK_5BITS];// 5-3=2
    
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    break;
                case 2 :
                    // 2 octets = 16 bits to use
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 11)) & MASK_5BITS];// 16-1*5 = 11
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 6)) & MASK_5BITS];// 16-2*5 = 6
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 1)) & MASK_5BITS];// 16-3*5 = 1
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea << 4)) & MASK_5BITS];// 5-1 = 4
    
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    break;
                case 3 :
                    // 3 octets = 24 bits to use
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 19)) & MASK_5BITS];// 24-1*5 = 19
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 14)) & MASK_5BITS];// 24-2*5 = 14
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 9)) & MASK_5BITS];// 24-3*5 = 9
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 4)) & MASK_5BITS];// 24-4*5 = 4
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea << 1)) & MASK_5BITS];// 5-4 = 1
    
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    break;
                case 4 :
                    // 4 octets = 32 bits to use
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 27)) & MASK_5BITS];// 32-1*5 = 27
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 22)) & MASK_5BITS];// 32-2*5 = 22
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 17)) & MASK_5BITS];// 32-3*5 = 17
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 12)) & MASK_5BITS];// 32-4*5 = 12
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 7)) & MASK_5BITS];// 32-5*5 = 7
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 2)) & MASK_5BITS];// 32-6*5 = 2
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea << 3)) & MASK_5BITS];// 5-2 = 3
    
                    buffer[context.pos++] = pad;
                    break;
                default :
                    throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
            context.currentLinePos += context.pos - savedPos;// keep track of current line position
    
            // if currentPos == 0 we are at the start of a line, so don't add CRLF
            if ((lineLength > 0) && (context.currentLinePos > 0)) {
                // add chunk separator if required
                System.arraycopy(lineSeparator, 0, buffer, context.pos, lineSeparator.length);
                context.pos += lineSeparator.length;
            }
        } else {
            for (int i = 0; i < inAvail; i++) {
                final byte[] buffer = ensureBufferSize(encodeSize, context);
                context.modulus = (context.modulus + 1) % BYTES_PER_UNENCODED_BLOCK;
                int b = input[inPos++];
                if (b < 0) {
                    b += 256;
                }
                context.lbitWorkArea = (context.lbitWorkArea << 8) + b;// BITS_PER_BYTE
    
                if (0 == context.modulus) {
                    // we have enough bytes to create our output
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 35)) & MASK_5BITS];
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 30)) & MASK_5BITS];
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 25)) & MASK_5BITS];
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 20)) & MASK_5BITS];
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 15)) & MASK_5BITS];
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 10)) & MASK_5BITS];
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 5)) & MASK_5BITS];
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea)) & MASK_5BITS];
                    context.currentLinePos += BYTES_PER_ENCODED_BLOCK;
                    if ((lineLength > 0) && (lineLength <= context.currentLinePos)) {
                        System.arraycopy(lineSeparator, 0, buffer, context.pos, lineSeparator.length);
                        context.pos += lineSeparator.length;
                        context.currentLinePos = 0;
                    }
                }
            }
        }
    }
public static Builder builder();
    @Override
void decode(final byte[] input, int inPos, final int inAvail, final Context context);
    byte[] getLineSeparator();
    @Override
public boolean isInAlphabet(final byte octet);
}
```


@input{context information}

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
 

constructors for class `org.apache.commons.codec.binary.Base32`: 
params: 
body:
```java
    public Base32() {
        this(false);
    }

```
params: 
body:
```java
    public Base32(final boolean useHex) {
        this(0, null, useHex, PAD_DEFAULT);
    }

```
params: 
body:
```java
    public Base32(final boolean useHex, final byte padding) {
        this(0, null, useHex, padding);
    }

```
params: 
body:
```java
    public Base32(final byte pad) {
        this(false, pad);
    }

```
params: 
body:
```java
    public Base32(final int lineLength) {
        this(lineLength, CHUNK_SEPARATOR);
    }

```
params: 
body:
```java
    public Base32(final int lineLength, final byte[] lineSeparator) {
        this(lineLength, lineSeparator, false, PAD_DEFAULT);
    }

```
params: 
body:
```java
    public Base32(final int lineLength, final byte[] lineSeparator, final boolean useHex) {
        this(lineLength, lineSeparator, useHex, PAD_DEFAULT);
    }

```
params: 
body:
```java
    public Base32(final int lineLength, final byte[] lineSeparator, final boolean useHex, final byte padding) {
        this(lineLength, lineSeparator, useHex, padding, DECODING_POLICY_DEFAULT);
    }

```
params: org.apache.commons.codec.CodecPolicy decodingPolicy 
body:
```java
    public Base32(final int lineLength, final byte[] lineSeparator, final boolean useHex, final byte padding, final CodecPolicy decodingPolicy) {
        this(lineLength, lineSeparator, useHex ? HEX_ENCODE_TABLE : ENCODE_TABLE, padding, decodingPolicy);
    }

```
params: org.apache.commons.codec.CodecPolicy decodingPolicy 
body:
```java
    private Base32(final int lineLength, final byte[] lineSeparator, final byte[] encodeTable, final byte padding, final CodecPolicy decodingPolicy) {
        super(BYTES_PER_UNENCODED_BLOCK, BYTES_PER_ENCODED_BLOCK, lineLength, toLength(lineSeparator), padding, decodingPolicy);
        Objects.requireNonNull(encodeTable, "encodeTable");
        this.encodeTable = encodeTable;
        this.decodeTable = encodeTable == HEX_ENCODE_TABLE ? HEX_DECODE_TABLE : DECODE_TABLE;
        if (lineLength > 0) {
            if (lineSeparator == null) {
                throw new IllegalArgumentException("lineLength " + lineLength + " > 0, but lineSeparator is null");
            }
            final byte[] lineSeparatorCopy = lineSeparator.clone();
            // Must be done after initializing the tables
            if (containsAlphabetOrPad(lineSeparatorCopy)) {
                final String sep = StringUtils.newStringUtf8(lineSeparatorCopy);
                throw new IllegalArgumentException("lineSeparator must not contain Base32 characters: [" + sep + "]");
            }
            this.encodeSize = BYTES_PER_ENCODED_BLOCK + lineSeparatorCopy.length;
            this.lineSeparator = lineSeparatorCopy;
        } else {
            this.encodeSize = BYTES_PER_ENCODED_BLOCK;
            this.lineSeparator = null;
        }
        if (isInAlphabet(padding) || Character.isWhitespace(padding)) {
            throw new IllegalArgumentException("pad must not be in alphabet or whitespace");
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
class `org.apache.commons.codec.binary.BaseNCodec.Context`:
api document : 
     * Holds thread context so classes can be thread-safe.
     *
     * This class is not itself thread-safe; each thread must allocate its own copy.
     
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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

public class Base32Test {

    private static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;

    /** RFC 4648. */
    // @formatter:off
    private static final String [][] BASE32_TEST_CASES = {
        { ""       , "" },
        { "f"      , "MY======" },
        { "fo"     , "MZXQ====" },
        { "foo"    , "MZXW6===" },
        { "foob"   , "MZXW6YQ=" },
        { "fooba"  , "MZXW6YTB" },
        { "foobar" , "MZXW6YTBOI======" }
    };
    // @formatter:on

    /**
     * Example test cases with valid characters but impossible combinations of
     * trailing characters (i.e. cannot be created during encoding).
     */
    // @formatter:off
    static final String[] BASE32_IMPOSSIBLE_CASES = {
        "MC======",
        "MZXE====",
        "MZXWB===",
        "MZXW6YB=",
        "MZXW6YTBOC======",
        "AB======"
        };
    // @formatter:on

    // @formatter:off
    private static final String[] BASE32_IMPOSSIBLE_CASES_CHUNKED = {
        "M2======\r\n",
        "MZX0====\r\n",
        "MZXW0===\r\n",
        "MZXW6Y2=\r\n",
        "MZXW6YTBO2======\r\n"
    };
    // @formatter:on

    // @formatter:off
    private static final String[] BASE32HEX_IMPOSSIBLE_CASES = {
        "C2======",
        "CPN4====",
        "CPNM1===",
        "CPNMUO1=",
        "CPNMUOJ1E2======"
    };
    // @formatter:on

    /**
     * Copy of the standard base-32 encoding table. Used to test decoding the final
     * character of encoded bytes.
     */
    // @formatter:off
    private static final byte[] ENCODE_TABLE = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '2', '3', '4', '5', '6', '7'
    };
    // @formatter:on
......
    @Test
    public void testRandomBytes() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32();
            final byte[][] b = BaseNTestData.randomData(codec, i);
            assertEquals(b[1].length, codec.getEncodedLength(b[0]), i + " " + codec.lineLength);
            // assertEquals(b[0], codec.decode(b[1]));
        }
    }

    @Test
    public void testRandomBytesChunked() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(10);
            final byte[][] b = BaseNTestData.randomData(codec, i);
            assertEquals(b[1].length, codec.getEncodedLength(b[0]), i + " " + codec.lineLength);
            // assertEquals(b[0], codec.decode(b[1]));
        }
    }

    @Test
    public void testRandomBytesHex() {
        for (int i = 0; i < 20; i++) {
            final Base32 codec = new Base32(true);
            final byte[][] b = BaseNTestData.randomData(codec, i);
            assertEquals(b[1].length, codec.getEncodedLength(b[0]), i + " " + codec.lineLength);
            // assertEquals(b[0], codec.decode(b[1]));
        }
    }

    @Test
    public void testSingleCharEncoding() {
        for (int i = 0; i < 20; i++) {
            Base32 codec = new Base32();
            final BaseNCodec.Context context = new BaseNCodec.Context();
            final byte[] unencoded = new byte[i];
            final byte[] allInOne = codec.encode(unencoded);
            codec = new Base32();
            for (int j = 0; j < unencoded.length; j++) {
                codec.encode(unencoded, j, 1, context);
            }
            codec.encode(unencoded, 0, -1, context);
            final byte[] singly = new byte[allInOne.length];
            codec.readResults(singly, 0, 100, context);
            if (!Arrays.equals(allInOne, singly)) {
                fail();
            }
        }
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
class Base32_encode_Test {
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