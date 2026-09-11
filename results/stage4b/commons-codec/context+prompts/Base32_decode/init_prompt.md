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
Here is the code of focal method, `decode(byte[], int, int, Context)`, with the focal class `Base32_decode_Test`:
```Java
package org.apache.commons.codec.binary;
import java.util.Objects;
import org.apache.commons.codec.CodecPolicy;
public class Base32 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 5;
    private static final int BYTES_PER_ENCODED_BLOCK = 8;
    private static final long MASK_4BITS = 0xfL;
    private static final long MASK_3BITS = 0x7L;
    private static final long MASK_2BITS = 0x3L;
    private static final long MASK_1BITS = 0x1L;
    private final byte[] decodeTable;
    private final int encodeSize;
    @Override
    void decode(final byte[] input, int inPos, final int inAvail, final Context context) {
        // package protected for access from I/O streams
        if (context.eof) {
            return;
        }
        if (inAvail < 0) {
            context.eof = true;
        }
        final int decodeSize = this.encodeSize - 1;
        for (int i = 0; i < inAvail; i++) {
            final byte b = input[inPos++];
            if (b == pad) {
                // We're done.
                context.eof = true;
                break;
            }
            final byte[] buffer = ensureBufferSize(decodeSize, context);
            if ((b >= 0) && (b < this.decodeTable.length)) {
                final int result = this.decodeTable[b];
                if (result >= 0) {
                    context.modulus = (context.modulus + 1) % BYTES_PER_ENCODED_BLOCK;
                    // collect decoded bytes
                    context.lbitWorkArea = (context.lbitWorkArea << BITS_PER_ENCODED_BYTE) + result;
                    if (context.modulus == 0) {
                        // we can output the 5 bytes
                        buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 32) & MASK_8BITS));
                        buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 24) & MASK_8BITS));
                        buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 16) & MASK_8BITS));
                        buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 8) & MASK_8BITS));
                        buffer[context.pos++] = ((byte) (context.lbitWorkArea & MASK_8BITS));
                    }
                }
            }
        }
        // Two forms of EOF as far as Base32 decoder is concerned: actual
        // EOF (-1) and first time '=' character is encountered in stream.
        // This approach makes the '=' padding characters completely optional.
        if (context.eof && (context.modulus > 0)) {
            // if modulus == 0, nothing to do
            final byte[] buffer = ensureBufferSize(decodeSize, context);
            // We ignore partial bytes, i.e. only multiples of 8 count.
            // Any combination not part of a valid encoding is either partially decoded
            // or will raise an exception. Possible trailing characters are 2, 4, 5, 7.
            // It is not possible to encode with 1, 3, 6 trailing characters.
            // For backwards compatibility 3 & 6 chars are decoded anyway rather than discarded.
            // See the encode(byte[]) method EOF section.
            switch (context.modulus) {
                // case 0 : // impossible, as excluded above
                case 1 :
                    // 5 bits - either ignore entirely, or raise an exception
                    validateTrailingCharacters();
                case 2 :
                    // 10 bits, drop 2 and output one byte
                    validateCharacter(MASK_2BITS, context);
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 2) & MASK_8BITS));
                    break;
                case 3 :
                    // 15 bits, drop 7 and output 1 byte, or raise an exception
                    validateTrailingCharacters();
                    // Not possible from a valid encoding but decode anyway
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 7) & MASK_8BITS));
                    break;
                case 4 :
                    // 20 bits = 2*8 + 4
                    validateCharacter(MASK_4BITS, context);
                    context.lbitWorkArea = context.lbitWorkArea >> 4;// drop 4 bits
    
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 8) & MASK_8BITS));
                    buffer[context.pos++] = ((byte) (context.lbitWorkArea & MASK_8BITS));
                    break;
                case 5 :
                    // 25 bits = 3*8 + 1
                    validateCharacter(MASK_1BITS, context);
                    context.lbitWorkArea = context.lbitWorkArea >> 1;
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 16) & MASK_8BITS));
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 8) & MASK_8BITS));
                    buffer[context.pos++] = ((byte) (context.lbitWorkArea & MASK_8BITS));
                    break;
                case 6 :
                    // 30 bits = 3*8 + 6, or raise an exception
                    validateTrailingCharacters();
                    // Not possible from a valid encoding but decode anyway
                    context.lbitWorkArea = context.lbitWorkArea >> 6;
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 16) & MASK_8BITS));
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 8) & MASK_8BITS));
                    buffer[context.pos++] = ((byte) (context.lbitWorkArea & MASK_8BITS));
                    break;
                case 7 :
                    // 35 bits = 4*8 +3
                    validateCharacter(MASK_3BITS, context);
                    context.lbitWorkArea = context.lbitWorkArea >> 3;
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 24) & MASK_8BITS));
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 16) & MASK_8BITS));
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 8) & MASK_8BITS));
                    buffer[context.pos++] = ((byte) (context.lbitWorkArea & MASK_8BITS));
                    break;
                default :
                    // modulus can be 0-7, and we excluded 0,1 already
                    throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
        }
    }
public static Builder builder();
    @Override
void encode(final byte[] input, int inPos, final int inAvail, final Context context);
    byte[] getLineSeparator();
    @Override
public boolean isInAlphabet(final byte octet);
    private void validateCharacter(final long emptyBitsMask, final Context context);
    private void validateTrailingCharacters();
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
class Base32_decode_Test {
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