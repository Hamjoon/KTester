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
Here is the code of focal method, `sha256Crypt(byte[])`, with the focal class `Sha2Crypt`:
```Java
package org.apache.commons.codec.digest;
import java.util.regex.Pattern;
import java.util.Random;
import java.util.regex.Matcher;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.Arrays;
public class Sha2Crypt {
    static final String SHA256_PREFIX = "$5$";
    static final String SHA512_PREFIX = "$6$";
    public static String sha256Crypt(final byte[] keyBytes) {
        return sha256Crypt(keyBytes, null);
    }
public static String sha256Crypt(final byte[] keyBytes, String salt);
    public static String sha256Crypt(final byte[] keyBytes, String salt, final Random random);
    public static String sha512Crypt(final byte[] keyBytes);
    public static String sha512Crypt(final byte[] keyBytes, String salt);
    public static String sha512Crypt(final byte[] keyBytes, String salt, final Random random);
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

api document of class org.apache.commons.codec.digest.Sha2Crypt: 

 * SHA2-based UNIX crypt implementation.
 * <p>
 * Based on the C implementation released into the Public Domain by Ulrich Drepper &lt;drepper@redhat.com&gt;
 * http://www.akkadia.org/drepper/SHA-crypt.txt
 * </p>
 * <p>
 * Conversion to Kotlin and from there to Java in 2012 by Christian Hammers &lt;ch@lathspell.de&gt; and likewise put
 * into the Public Domain.
 * </p>
 * <p>
 * This class is immutable and thread-safe.
 * </p>
 *
 * @since 1.7
 

api document of method sha256Crypt(byte[]): 

     * Generates a libc crypt() compatible "$5$" hash value with random salt.
     * <p>
     * See {@link Crypt#crypt(String, String)} for details.
     * </p>
     * <p>
     * A salt is generated for you using {@link SecureRandom}.
     * </p>
     *
     * @param keyBytes
     *            plaintext to hash. Each array element is set to {@code 0} before returning.
     * @return complete hash value
     * @throws IllegalArgumentException
     *             when a {@link java.security.NoSuchAlgorithmException} is caught.
     

dependent classes: 
class `org.apache.commons.codec.digest.Sha2Crypt`:
	related functions:
		method `public static String sha256Crypt(byte[])` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha256Crypt(byte[])`, api document: 
     * Generates a libc crypt() compatible "$5$" hash value with random salt.
     * <p>
     * See {@link Crypt#crypt(String, String)} for details.
     * </p>
     * <p>
     * A salt is generated for you using {@link SecureRandom}.
     * </p>
     *
     * @param keyBytes
     *            plaintext to hash. Each array element is set to {@code 0} before returning.
     * @return complete hash value
     * @throws IllegalArgumentException
     *             when a {@link java.security.NoSuchAlgorithmException} is caught.
     ;
class `org.apache.commons.codec.digest.Crypt`:
	related functions:
		method `public static String crypt(byte[], String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha256Crypt(byte[])`, api document: 
     * Encrypts a password in a crypt(3) compatible way.
     * <p>
     * If no salt is provided, a random salt and the default algorithm (currently SHA-512) will be used. See
     * {@link #crypt(String, String)} for details.
     * </p>
     *
     * @param keyBytes
     *            plaintext password
     * @param salt
     *            real salt value without prefix or "rounds=". The salt may be null,
     *            in which case a salt is generated for you using {@link SecureRandom}.
     * @return hash value
     * @throws IllegalArgumentException
     *             if the salt does not match the allowed pattern
     * @throws IllegalArgumentException
     *             when a {@link java.security.NoSuchAlgorithmException} is caught.
     ;
class `org.apache.commons.codec.binary.Base16`:
	related functions:
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha256Crypt(byte[])`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.