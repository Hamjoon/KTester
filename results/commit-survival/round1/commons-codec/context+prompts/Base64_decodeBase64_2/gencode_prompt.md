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
Here is the code of focal method, `decodeBase64(String)`, with the focal class `Base64`:
```Java
package org.apache.commons.codec.binary;
import java.math.BigInteger;
import java.util.Objects;
import org.apache.commons.codec.CodecPolicy;
import java.util.Arrays;
public class Base64 extends BaseNCodec {
    
    public static byte[] decodeBase64(final String base64String) {
        return new Base64().decode(base64String);
    }
public static Builder builder();
    @Override
void decode(final byte[] input, int inPos, final int inAvail, final Context context);
    public static byte[] decodeBase64(final byte[] base64Data);
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
    @Override
protected boolean isInAlphabet(final byte octet);
    public boolean isUrlSafe();
    static byte[] toIntegerBytes(final BigInteger bigInt);
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
 

api document of method decodeBase64(String): 

     * Decodes a Base64 String into octets.
     * <p>
     * <b>Note:</b> this method seamlessly handles data encoded in URL-safe or normal mode.
     * </p>
     *
     * @param base64String
     *            String containing Base64 data
     * @return Array containing decoded data.
     * @since 1.4
     

return type: 
byte[]

dependent classes: 
class `org.apache.commons.codec.binary.Base64`:
	related functions:
		method `public static byte[] decodeBase64(byte[])` returns `byte[]`, related with `org.apache.commons.codec.binary.Base64.decodeBase64(String)`, api document: 
     * Decodes Base64 data into octets.
     * <p>
     * <b>Note:</b> this method seamlessly handles data encoded in URL-safe or normal mode.
     * </p>
     *
     * @param base64Data
     *            Byte array containing Base64 data
     * @return Array containing decoded data.
     ;
		method `public static byte[] decodeBase64(String)` returns `byte[]`, related with `org.apache.commons.codec.binary.Base64.decodeBase64(String)`, api document: 
     * Decodes a Base64 String into octets.
     * <p>
     * <b>Note:</b> this method seamlessly handles data encoded in URL-safe or normal mode.
     * </p>
     *
     * @param base64String
     *            String containing Base64 data
     * @return Array containing decoded data.
     * @since 1.4
     ;
class `org.apache.commons.codec.binary.BaseNCodec`:
	related functions:
		method `public Object decode(Object)` returns `java.lang.Object`, related with `org.apache.commons.codec.binary.Base64.decodeBase64(String)`, api document: 
     * Decodes an Object using the Base-N algorithm. This method is provided in order to satisfy the requirements of
     * the Decoder interface, and will throw a DecoderException if the supplied object is not of type byte[] or String.
     *
     * @param obj
     *            Object to decode
     * @return An object (of type byte[]) containing the binary data which corresponds to the byte[] or String
     *         supplied.
     * @throws DecoderException
     *             if the parameter supplied is not of type byte[]
     ;
class `org.apache.commons.codec.binary.Base16`:
	related functions:
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base64.decodeBase64(String)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.