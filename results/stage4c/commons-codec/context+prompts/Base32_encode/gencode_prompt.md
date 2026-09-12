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
Here is the code of focal method, `encode(byte[], int, int, Context)`, with the focal class `Base32`:
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



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.