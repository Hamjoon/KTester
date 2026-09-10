You are a professional Java software testing expert. Your task is to generate high-quality unit test code for a focal method, using **JUnit5** and **Mockito** libraries. 

You'll be provided with:
1. The focal method to test.
2. The target class's structure (fields and methods).
3. A pre-defined test class framework with `@Before`/`@After` methods.
4. Test cases in JSON format.
5. Any relevant context.

**Requirements:**

Code Quality:
- Include clear comments explaining each test scenario.
- Ensure code is clean, understandable, and maintainable.
- Do not directly access private methods or fields. **Use reflection if the focal method is private.**
- Include all necessary imports; code must be free of syntax errors.

**Test Structure:**
- Follow the given test class framework; do not modify `@Before`/`@After` methods.
- Create one test function per test case group, ensuring all cases are covered.
- Use parameterized tests where appropriate.
- Reinitialize variables inside test functions if `@Setup` values don't fit.
- Properly mock and set up dependencies.

@input{focal method and target class}
Here is the code of focal method, `encodeQuotedPrintable(BitSet, byte[], boolean)`, with the focal class `QuotedPrintableCodec`:
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

@input{initial test class framework}
```Java
<initial_class>
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
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
 

api document of method encodeQuotedPrintable(BitSet, byte[], boolean): 

     * Encodes an array of bytes into an array of quoted-printable 7-bit characters. Unsafe characters are escaped.
     * <p>
     * Depending on the selection of the {@code strict} parameter, this function either implements the full ruleset
     * or only a subset of quoted-printable encoding specification (rule #1 and rule #2) as defined in
     * RFC 1521 and is suitable for encoding binary data and unformatted text.
     * </p>
     *
     * @param printable
     *            bitset of characters deemed quoted-printable
     * @param bytes
     *            array of bytes to be encoded
     * @param strict
     *            if {@code true} the full ruleset is used, otherwise only rule #1 and rule #2
     * @return array of bytes containing quoted-printable data
     * @since 1.10
     

return type: 
byte[]

dependent classes: 
class `org.apache.commons.codec.net.QuotedPrintableCodec`:
	dependent fields:
		byte org.apache.commons.codec.net.QuotedPrintableCodec.CR;
		java.util.BitSet org.apache.commons.codec.net.QuotedPrintableCodec.PRINTABLE_CHARS;
		byte org.apache.commons.codec.net.QuotedPrintableCodec.LF;
		int org.apache.commons.codec.net.QuotedPrintableCodec.MIN_BYTES;
		byte org.apache.commons.codec.net.QuotedPrintableCodec.ESCAPE_CHAR;
		int org.apache.commons.codec.net.QuotedPrintableCodec.SAFE_LENGTH;
	related functions:
		method `private static int encodeByte(int, boolean, ByteArrayOutputStream)` returns `int`, related with `org.apache.commons.codec.net.QuotedPrintableCodec.encodeQuotedPrintable(BitSet, byte[], boolean)`, api document: 
     * Encodes a byte in the buffer.
     *
     * @param b
     *            byte to write
     * @param encode
     *            indicates whether the octet shall be encoded
     * @param buffer
     *            the buffer to write to
     * @return the number of bytes that have been written to the buffer
     ;
		method `public static final byte[] decodeQuotedPrintable(byte[])` returns `byte[]`, related with `org.apache.commons.codec.net.QuotedPrintableCodec.encodeQuotedPrintable(BitSet, byte[], boolean)`, api document: 
     * Decodes an array quoted-printable characters into an array of original bytes. Escaped characters are converted
     * back to their original representation.
     * <p>
     * This function fully implements the quoted-printable encoding specification (rule #1 through rule #5) as
     * defined in RFC 1521.
     * </p>
     *
     * @param bytes
     *            array of quoted-printable characters
     * @return array of original bytes
     * @throws DecoderException
     *             Thrown if quoted-printable decoding is unsuccessful
     ;
		method `private static final int encodeQuotedPrintable(int, ByteArrayOutputStream)` returns `int`, related with `org.apache.commons.codec.net.QuotedPrintableCodec.encodeQuotedPrintable(BitSet, byte[], boolean)`, api document: 
     * Encodes byte into its quoted-printable representation.
     *
     * @param b
     *            byte to encode
     * @param buffer
     *            the buffer to write to
     * @return The number of bytes written to the {@code buffer}
     ;
class `org.apache.commons.codec.net.URLCodec`:
	related functions:
		method `public static final byte[] decodeUrl(byte[])` returns `byte[]`, related with `org.apache.commons.codec.net.QuotedPrintableCodec.encodeQuotedPrintable(BitSet, byte[], boolean)`, api document: 
     * Decodes an array of URL safe 7-bit characters into an array of original bytes. Escaped characters are converted
     * back to their original representation.
     *
     * @param bytes
     *            array of URL safe characters
     * @return array of original bytes
     * @throws DecoderException
     *             Thrown if URL decoding is unsuccessful
     ;
		method `public static final byte[] encodeUrl(BitSet, byte[])` returns `byte[]`, related with `org.apache.commons.codec.net.QuotedPrintableCodec.encodeQuotedPrintable(BitSet, byte[], boolean)`, api document: 
     * Encodes an array of bytes into an array of URL safe 7-bit characters. Unsafe characters are escaped.
     *
     * @param urlsafe
     *            bitset of characters deemed URL safe
     * @param bytes
     *            array of bytes to convert to URL safe characters
     * @return array of bytes containing URL safe characters
     ;
class `org.apache.commons.codec.net.PercentCodec`:
	related functions:
		method `private boolean canEncode(byte)` returns `boolean`, related with `org.apache.commons.codec.net.QuotedPrintableCodec.encodeQuotedPrintable(BitSet, byte[], boolean)`;
class `org.apache.commons.codec.binary.Base64`:
	related functions:
		method ` static byte[] toIntegerBytes(BigInteger)` returns `byte[]`, related with `org.apache.commons.codec.net.QuotedPrintableCodec.encodeQuotedPrintable(BitSet, byte[], boolean)`, api document: 
     * Returns a byte-array representation of a {@code BigInteger} without sign bit.
     *
     * @param bigInt
     *            {@code BigInteger} to be converted
     * @return a byte array representation of the BigInteger parameter
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.