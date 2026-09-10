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
Here is the code of focal method, `encodeQuotedPrintable(BitSet, byte[], boolean)`, with the focal class `QuotedPrintableCodec_encodeQuotedPrintable_Test`:
```Java
package org.apache.commons.codec.net;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;
import org.apache.commons.codec.BinaryDecoder;
import java.io.ByteArrayOutputStream;
import java.util.BitSet;
import org.apache.commons.codec.StringDecoder;
import java.nio.charset.IllegalCharsetNameException;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import java.nio.charset.UnsupportedCharsetException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.StringUtils;
public class QuotedPrintableCodec implements BinaryEncoder , BinaryDecoder , StringEncoder , StringDecoder {
    private static final BitSet PRINTABLE_CHARS = new BitSet(256);
    private static final byte ESCAPE_CHAR = '=';
    private static final byte CR = 13;
    private static final byte LF = 10;
    private static final int MIN_BYTES = 3;
    private static final int SAFE_LENGTH = 73;
    public static final byte[] encodeQuotedPrintable(BitSet printable, final byte[] bytes, final boolean strict) {
        if (bytes == null) {
            return null;
        }
        if (printable == null) {
            printable = PRINTABLE_CHARS;
        }
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        final int bytesLength = bytes.length;
        if (strict) {
            if (bytesLength < MIN_BYTES) {
                return null;
            }
            int pos = 1;
            // encode up to buffer.length - 3, the last three octets will be treated
            // separately for simplification of note #3
            for (int i = 0; i < (bytesLength - 3); i++) {
                final int b = getUnsignedOctet(i, bytes);
                if (pos < SAFE_LENGTH) {
                    // up to this length it is safe to add any byte, encoded or not
                    pos += encodeByte(b, !printable.get(b), buffer);
                } else {
                    // rule #3: whitespace at the end of a line *must* be encoded
                    encodeByte(b, (!printable.get(b)) || isWhitespace(b), buffer);
                    // rule #5: soft line break
                    buffer.write(ESCAPE_CHAR);
                    buffer.write(CR);
                    buffer.write(LF);
                    pos = 1;
                }
            }
            // rule #3: whitespace at the end of a line *must* be encoded
            // if we would do a soft break line after this octet, encode whitespace
            int b = getUnsignedOctet(bytesLength - 3, bytes);
            boolean encode = (!printable.get(b)) || (isWhitespace(b) && (pos > (SAFE_LENGTH - 5)));
            pos += encodeByte(b, encode, buffer);
            // note #3: '=' *must not* be the ultimate or penultimate character
            // simplification: if < 6 bytes left, do a soft line break as we may need
            // exactly 6 bytes space for the last 2 bytes
            if (pos > (SAFE_LENGTH - 2)) {
                buffer.write(ESCAPE_CHAR);
                buffer.write(CR);
                buffer.write(LF);
            }
            for (int i = bytesLength - 2; i < bytesLength; i++) {
                b = getUnsignedOctet(i, bytes);
                // rule #3: trailing whitespace shall be encoded
                encode = (!printable.get(b)) || ((i > (bytesLength - 2)) && isWhitespace(b));
                encodeByte(b, encode, buffer);
            }
        } else {
            for (final byte c : bytes) {
                int b = c;
                if (b < 0) {
                    b = 256 + b;
                }
                if (printable.get(b)) {
                    buffer.write(b);
                } else {
                    encodeQuotedPrintable(b, buffer);
                }
            }
        }
        return buffer.toByteArray();
    }
@Override
public byte[] decode(final byte[] bytes) throws DecoderException;
    @Override
public Object decode(final Object obj) throws DecoderException;
    @Override
public String decode(final String sourceStr) throws DecoderException;
    public String decode(final String sourceStr, final String sourceCharset) throws DecoderException, UnsupportedEncodingException;
    public String decode(final String sourceStr, final Charset sourceCharset) throws DecoderException;
    public static final byte[] decodeQuotedPrintable(final byte[] bytes) throws DecoderException;
    @Override
public byte[] encode(final byte[] bytes);
    @Override
public Object encode(final Object obj) throws EncoderException;
    @Override
public String encode(final String sourceStr) throws EncoderException;
    public String encode(final String sourceStr, final String sourceCharset) throws UnsupportedEncodingException;
    public String encode(final String sourceStr, final Charset sourceCharset);
    private static int encodeByte(final int b, final boolean encode, final ByteArrayOutputStream buffer);
    private static final int encodeQuotedPrintable(final int b, final ByteArrayOutputStream buffer);
    public static final byte[] encodeQuotedPrintable(final BitSet printable, final byte[] bytes);
    public Charset getCharset();
    public String getDefaultCharset();
    private static int getUnsignedOctet(final int index, final byte[] bytes);
    private static boolean isWhitespace(final int b);
}
```


@input{context information}

api document of class org.apache.commons.codec.net.QuotedPrintableCodec: 

 * Codec for the Quoted-Printable section of <a href="http://www.ietf.org/rfc/rfc1521.txt">RFC 1521</a>.
 * <p>
 * The Quoted-Printable encoding is intended to represent data that largely consists of octets that correspond to
 * printable characters in the ASCII character set. It encodes the data in such a way that the resulting octets are
 * unlikely to be modified by mail transport. If the data being encoded are mostly ASCII text, the encoded form of the
 * data remains largely recognizable by humans. A body which is entirely ASCII may also be encoded in Quoted-Printable
 * to ensure the integrity of the data should the message pass through a character- translating, and/or line-wrapping
 * gateway.
 * </p>
 * <p>
 * Note:
 * </p>
 * <p>
 * Depending on the selected {@code strict} parameter, this class will implement a different set of rules of the
 * quoted-printable spec:
 * </p>
 * <ul>
 *   <li>{@code strict=false}: only rules #1 and #2 are implemented</li>
 *   <li>{@code strict=true}: all rules #1 through #5 are implemented</li>
 * </ul>
 * <p>
 * Originally, this class only supported the non-strict mode, but the codec in this partial form could already be used
 * for certain applications that do not require quoted-printable line formatting (rules #3, #4, #5), for instance
 * Q codec. The strict mode has been added in 1.10.
 * </p>
 * <p>
 * This class is immutable and thread-safe.
 * </p>
 *
 * @see <a href="http://www.ietf.org/rfc/rfc1521.txt">RFC 1521 MIME (Multipurpose Internet Mail Extensions) Part One:
 *          Mechanisms for Specifying and Describing the Format of Internet Message Bodies </a>
 *
 * @since 1.3
 

constructors for class `org.apache.commons.codec.net.QuotedPrintableCodec`: 
params: 
body:
```java
    public QuotedPrintableCodec() {
        this(StandardCharsets.UTF_8, false);
    }

```
params: 
body:
```java
    public QuotedPrintableCodec(final boolean strict) {
        this(StandardCharsets.UTF_8, strict);
    }

```
params: 
body:
```java
    public QuotedPrintableCodec(final Charset charset) {
        this(charset, false);
    }

```
params: 
body:
```java
    public QuotedPrintableCodec(final Charset charset, final boolean strict) {
        this.charset = charset;
        this.strict = strict;
    }

```
params: 
body:
```java
    public QuotedPrintableCodec(final String charsetName) throws IllegalCharsetNameException, IllegalArgumentException, UnsupportedCharsetException {
        this(Charset.forName(charsetName), false);
    }

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

package org.apache.commons.codec.net;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.jupiter.api.Test;

/**
 * Quoted-printable codec test cases
 */
public class QuotedPrintableCodecTest {

    static final int SWISS_GERMAN_STUFF_UNICODE[] = { 0x47, 0x72, 0xFC, 0x65, 0x7A, 0x69, 0x5F, 0x7A, 0xE4, 0x6D, 0xE4 };

    static final int RUSSIAN_STUFF_UNICODE[] = { 0x412, 0x441, 0x435, 0x43C, 0x5F, 0x43F, 0x440, 0x438, 0x432, 0x435, 0x442 };

    private String constructString(final int[] unicodeChars) {
        final StringBuilder buffer = new StringBuilder();
        if (unicodeChars != null) {
            for (final int unicodeChar : unicodeChars) {
                buffer.append((char) unicodeChar);
            }
        }
        return buffer.toString();
    }

    @Test
    public void testBasicEncodeDecode() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        final String plain = "= Hello there =\r\n";
        final String encoded = qpcodec.encode(plain);
        assertEquals("=3D Hello there =3D=0D=0A", encoded, "Basic quoted-printable encoding test");
        assertEquals(plain, qpcodec.decode(encoded), "Basic quoted-printable decoding test");
    }

    @Test
    public void testDecodeInvalid() {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        assertThrows(DecoderException.class, () -> qpcodec.decode("="));
        assertThrows(DecoderException.class, () -> qpcodec.decode("=A"));
        assertThrows(DecoderException.class, () -> qpcodec.decode("=WW"));
    }

    @Test
    public void testDecodeObjects() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        final String plain = "1+1 =3D 2";
        String decoded = (String) qpcodec.decode((Object) plain);
        assertEquals("1+1 = 2", decoded, "Basic quoted-printable decoding test");

        final byte[] plainBA = plain.getBytes(StandardCharsets.UTF_8);
        final byte[] decodedBA = (byte[]) qpcodec.decode((Object) plainBA);
        decoded = new String(decodedBA);
        assertEquals("1+1 = 2", decoded, "Basic quoted-printable decoding test");

        final Object result = qpcodec.decode((Object) null);
        assertNull(result, "Decoding a null Object should return null");

        assertThrows(DecoderException.class, () -> qpcodec.decode(Double.valueOf(3.0d)), "Trying to url encode a Double object should cause an exception.");
    }

    @Test
    public void testDecodeStringWithNull() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        final String test = null;
        final String result = qpcodec.decode(test, "charset");
        assertNull(result, "Result should be null");
    }

    @Test
    public void testDecodeWithNullArray() throws Exception {
        final byte[] plain = null;
        final byte[] result = QuotedPrintableCodec.decodeQuotedPrintable(plain);
        assertNull(result, "Result should be null");
    }

......
    public void testUltimateSoftBreak() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec(true);

        String plain = "This is a example of a quoted-printable text file. There is no end to it\t";
        String expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=09";

        assertEquals(expected, qpcodec.encode(plain));

        plain = "This is a example of a quoted-printable text file. There is no end to it ";
        expected = "This is a example of a quoted-printable text file. There is no end to i=\r\nt=20";

        assertEquals(expected, qpcodec.encode(plain));

        // whitespace before soft break
        plain = "This is a example of a quoted-printable text file. There is no end to   ";
        expected = "This is a example of a quoted-printable text file. There is no end to=20=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));

        // non-printable character before soft break
        plain = "This is a example of a quoted-printable text file. There is no end to=  ";
        expected = "This is a example of a quoted-printable text file. There is no end to=3D=\r\n =20";

        assertEquals(expected, qpcodec.encode(plain));
    }

    @Test
    public void testUnsafeEncodeDecode() throws Exception {
        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();
        final String plain = "=\r\n";
        final String encoded = qpcodec.encode(plain);
        assertEquals("=3D=0D=0A", encoded, "Unsafe chars quoted-printable encoding test");
        assertEquals(plain, qpcodec.decode(encoded), "Unsafe chars quoted-printable decoding test");
    }

    @Test
    public void testUTF8RoundTrip() throws Exception {

        final String ru_msg = constructString(RUSSIAN_STUFF_UNICODE);
        final String ch_msg = constructString(SWISS_GERMAN_STUFF_UNICODE);

        final QuotedPrintableCodec qpcodec = new QuotedPrintableCodec();

        assertEquals("=D0=92=D1=81=D0=B5=D0=BC_=D0=BF=D1=80=D0=B8=D0=B2=D0=B5=D1=82", qpcodec.encode(ru_msg, CharEncoding.UTF_8));
        assertEquals("Gr=C3=BCezi_z=C3=A4m=C3=A4", qpcodec.encode(ch_msg, CharEncoding.UTF_8));

        assertEquals(ru_msg, qpcodec.decode(qpcodec.encode(ru_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
        assertEquals(ch_msg, qpcodec.decode(qpcodec.encode(ch_msg, CharEncoding.UTF_8), CharEncoding.UTF_8));
    }
}
```

invoke examples of focal method: 
example 1:
```java
    public static final byte[] encodeQuotedPrintable(final BitSet printable, final byte[] bytes) {
        return encodeQuotedPrintable(printable, bytes, false);
    }
```
example 2:
```java
    @Override
    public byte[] encode(final byte[] bytes) {
        return encodeQuotedPrintable(PRINTABLE_CHARS, bytes, strict);
    }
```



@input{test class template}
```java
package org.apache.commons.codec.net;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class QuotedPrintableCodec_encodeQuotedPrintable_Test {
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