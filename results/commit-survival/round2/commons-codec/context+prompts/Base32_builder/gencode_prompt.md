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
Here is the code of focal method, `builder()`, with the focal class `Base32`:
```Java
package org.apache.commons.codec.binary;
import java.util.Objects;
import org.apache.commons.codec.CodecPolicy;
public class Base32 extends BaseNCodec {
    
    public static Builder builder() {
        return new Builder();
    }
@Override
void decode(final byte[] input, int inPos, final int inAvail, final Context context);
    @Override
void encode(final byte[] input, int inPos, final int inAvail, final Context context);
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
 

api document of method builder(): 

     * Creates a new Builder.
     *
     * @return a new Builder.
     * @since 1.17.0
     

return type: 
org.apache.commons.codec.binary.Base32.Builder

dependent classes: 
class `org.apache.commons.codec.binary.Base32`:
	related functions:
		method `public static Builder builder()` returns `org.apache.commons.codec.binary.Base32.Builder`, related with `org.apache.commons.codec.binary.Base32.builder()`, api document: 
     * Creates a new Builder.
     *
     * @return a new Builder.
     * @since 1.17.0
     ;
class `org.apache.commons.codec.binary.Base16`:
	related functions:
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.binary.Base32.builder()`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.