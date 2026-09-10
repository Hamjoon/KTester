As a professional Java software testing expert, your task is to generate test cases for a focal method.

Guidelines:
1. Analyze the method's functionality and identify possible input combinations including normal cases and boundary conditions.
2. Review the provided existing test cases. Avoid duplication and create new test cases for untested input combinations.

Context Provided:
1. The focal method that needs testing.
2. Relevant code and metadata for the class containing the method, including fields and other methods.
3. The existing test cases (in JSON format) to help avoid redundancy.

Output Instructions:
- Organize the test cases by the name of the test function (group).
- Each test case must include:
    - input: A list of parameter names and values. Use an empty list `[]` if no input is required.
    - expected: The return value or expected observable behavior.
    - description: A clear and concise explanation of the scenario and the intent of the test case.

Maintain the following strict JSON format:
```json
[
    {
        "group": "test name",
        "cases": [
            {
                "input": [
                    {
                        "parameter": "param name",
                        "value": "param value"
                    }
                ],
                "expected": "expected exception or behavior",
                "description": "test scenario description"
            }
        ]
    },
    {...}
]
```

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

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: