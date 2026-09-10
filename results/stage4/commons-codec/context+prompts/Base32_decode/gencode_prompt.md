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
Here is the code of focal method, `decode(byte[], int, int, Context)`, with the focal class `Base32`:
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
 

api document of method decode(byte[], int, int, Context): 

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
     

dependent classes: 
class `org.apache.commons.codec.binary.BaseNCodec.Context`:
	dependent fields:
		int org.apache.commons.codec.binary.BaseNCodec.Context.modulus;
		boolean org.apache.commons.codec.binary.BaseNCodec.Context.eof;
		int org.apache.commons.codec.binary.BaseNCodec.Context.pos;
		long org.apache.commons.codec.binary.BaseNCodec.Context.lbitWorkArea;
	related functions:
		method `public String toString()` returns `java.lang.String`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`, api document: 
         * Returns a String useful for debugging (especially within a debugger.)
         *
         * @return a String useful for debugging.
         ;
class `org.apache.commons.codec.binary.BaseNCodec`:
	dependent fields:
		byte org.apache.commons.codec.binary.BaseNCodec.pad;
		int org.apache.commons.codec.binary.BaseNCodec.MASK_8BITS;
	
class `org.apache.commons.codec.binary.Base32`:
	dependent fields:
		long org.apache.commons.codec.binary.Base32.MASK_4BITS;
		long org.apache.commons.codec.binary.Base32.MASK_1BITS;
		int org.apache.commons.codec.binary.Base32.BITS_PER_ENCODED_BYTE;
		byte[] org.apache.commons.codec.binary.Base32.decodeTable;
		int org.apache.commons.codec.binary.Base32.encodeSize;
		long org.apache.commons.codec.binary.Base32.MASK_2BITS;
		int org.apache.commons.codec.binary.Base32.BYTES_PER_ENCODED_BLOCK;
		long org.apache.commons.codec.binary.Base32.MASK_3BITS;
	related functions:
		method `public boolean isInAlphabet(byte)` returns `boolean`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`, api document: 
     * Returns whether or not the {@code octet} is in the Base32 alphabet.
     *
     * @param octet The value to test
     * @return {@code true} if the value is defined in the Base32 alphabet {@code false} otherwise.
     ;
		method ` void encode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`, api document: 
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
class `org.apache.commons.codec.binary.Base64`:
	related functions:
		method ` void encode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`, api document: 
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
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`, api document: 
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
class `org.apache.commons.codec.binary.Base16`:
	related functions:
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`;
		method ` void encode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`;
class `org.apache.commons.codec.binary.StringUtils`:
	related functions:
		method `private static IllegalStateException newIllegalStateException(String, UnsupportedEncodingException)` returns `java.lang.IllegalStateException`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`;
class `org.apache.commons.codec.digest.MurmurHash3.IncrementalHash32x86`:
	related functions:
		method `public final void add(byte[], int, int)` returns `void`, related with `org.apache.commons.codec.binary.Base32.decode(byte[], int, int, Context)`, api document: 
         * Adds the byte array to the current incremental hash.
         *
         * @param data The input byte array
         * @param offset The offset of data
         * @param length The length of array
         ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.