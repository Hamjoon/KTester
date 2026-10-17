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
Here is the code of focal method, `isInAlphabet(byte)`, with the focal class `Base32`:
```Java
package org.apache.commons.codec.binary;
import java.util.Objects;
import org.apache.commons.codec.CodecPolicy;
public class Base32 extends BaseNCodec {
    private final byte[] decodeTable;
    @Override
    public boolean isInAlphabet(final byte octet) {
        return ((octet >= 0) && (octet < decodeTable.length)) && (decodeTable[octet] != (-1));
    }
public static Builder builder();
    @Override
void decode(final byte[] input, int inPos, final int inAvail, final Context context);
    @Override
void encode(final byte[] input, int inPos, final int inAvail, final Context context);
    byte[] getLineSeparator();
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
 

api document of method isInAlphabet(byte): 

     * Returns whether or not the {@code octet} is in the Base32 alphabet.
     *
     * @param octet The value to test
     * @return {@code true} if the value is defined in the Base32 alphabet {@code false} otherwise.
     

return type: 
boolean

dependent classes: 
class `org.apache.commons.codec.binary.Base32`:
	dependent fields:
		byte[] org.apache.commons.codec.binary.Base32.decodeTable;
	related functions:
		method `public boolean isInAlphabet(byte)` returns `boolean`, related with `org.apache.commons.codec.binary.Base32.isInAlphabet(byte)`, api document: 
     * Returns whether or not the {@code octet} is in the Base32 alphabet.
     *
     * @param octet The value to test
     * @return {@code true} if the value is defined in the Base32 alphabet {@code false} otherwise.
     ;
class `org.apache.commons.codec.binary.Base64`:
	related functions:
		method ` static byte[] toIntegerBytes(BigInteger)` returns `byte[]`, related with `org.apache.commons.codec.binary.Base32.isInAlphabet(byte)`, api document: 
     * Returns a byte-array representation of a {@code BigInteger} without sign bit.
     *
     * @param bigInt
     *            {@code BigInteger} to be converted
     * @return a byte array representation of the BigInteger parameter
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.