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
Here is the code of focal method, `md5Crypt(byte[])`, with the focal class `Md5Crypt`:
```Java
package org.apache.commons.codec.digest;
import java.util.regex.Pattern;
import java.util.Random;
import java.util.regex.Matcher;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.util.Arrays;
public class Md5Crypt {
    static final String APR1_PREFIX = "$apr1$";
    static final String MD5_PREFIX = "$1$";
    public static String md5Crypt(final byte[] keyBytes) {
        return md5Crypt(keyBytes, MD5_PREFIX + B64.getRandomSalt(8));
    }
public static String apr1Crypt(final byte[] keyBytes);
    public static String apr1Crypt(final byte[] keyBytes, String salt);
    public static String apr1Crypt(final byte[] keyBytes, final Random random);
    public static String apr1Crypt(final String keyBytes);
    public static String apr1Crypt(final String keyBytes, final String salt);
    public static String md5Crypt(final byte[] keyBytes, final String salt);
    public static String md5Crypt(final byte[] keyBytes, final String salt, final String prefix);
    public static String md5Crypt(final byte[] keyBytes, final String salt, final String prefix, final Random random);
    public static String md5Crypt(final byte[] keyBytes, final Random random);
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

api document of class org.apache.commons.codec.digest.Md5Crypt: 

 * The libc crypt() "$1$" and Apache "$apr1$" MD5-based hash algorithm.
 * <p>
 * Based on the public domain ("beer-ware") C implementation from Poul-Henning Kamp which was found at: <a
 * href="http://www.freebsd.org/cgi/cvsweb.cgi/src/lib/libcrypt/crypt-md5.c?rev=1.1;content-type=text%2Fplain">
 * crypt-md5.c @ freebsd.org</a>
 * </p>
 * <p>
 * Source:
 * </p>
 * <pre>
 * $FreeBSD: src/lib/libcrypt/crypt-md5.c,v 1.1 1999/01/21 13:50:09 brandon Exp $
 * </pre>
 * <p>
 * Conversion to Kotlin and from there to Java in 2012.
 * </p>
 * <p>
 * The C style comments are from the original C code, the ones with "//" from the port.
 * </p>
 * <p>
 * This class is immutable and thread-safe.
 * </p>
 *
 * @since 1.7
 

api document of method md5Crypt(byte[]): 

     * Generates a libc6 crypt() compatible "$1$" hash value.
     * <p>
     * See {@link #md5Crypt(byte[], String)} for details.
     * </p>
     * <p>
     * A salt is generated for you using {@link SecureRandom}.
     * </p>
     * @param keyBytes
     *            plaintext string to hash. Each array element is set to {@code 0} before returning.
     * @return the hash value
     * @throws IllegalArgumentException
     *             when a {@link java.security.NoSuchAlgorithmException} is caught.
     * @see #md5Crypt(byte[], String)
     

dependent classes: 
class `org.apache.commons.codec.digest.Md5Crypt`:
	dependent fields:
		java.lang.String org.apache.commons.codec.digest.Md5Crypt.MD5_PREFIX;
	related functions:
		method `public static String apr1Crypt(byte[])` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byte[])`, api document: 
     * See {@link #apr1Crypt(byte[], String)} for details.
     * <p>
     * A salt is generated for you using {@link SecureRandom}; your own {@link Random} in
     * {@link #apr1Crypt(byte[], Random)}.
     * </p>
     *
     * @param keyBytes plaintext string to hash. Each array element is set to {@code 0} before returning.
     * @return the hash value
     * @throws IllegalArgumentException when a {@link java.security.NoSuchAlgorithmException} is caught. *
     * @see #apr1Crypt(byte[], String)
     ;
		method `public static String md5Crypt(byte[], Random)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byte[])`, api document: 
     * Generates a libc6 crypt() compatible "$1$" hash value.
     * <p>
     * See {@link #md5Crypt(byte[], String)} for details.
     * </p>
     * <p>
     * A salt is generated for you using the instance of {@link Random} you supply.
     * </p>
     * @param keyBytes
     *            plaintext string to hash. Each array element is set to {@code 0} before returning.
     * @param random
     *            the instance of {@link Random} to use for generating the salt.
     *            Consider using {@link SecureRandom} for more secure salts.
     * @return the hash value
     * @throws IllegalArgumentException
     *             when a {@link java.security.NoSuchAlgorithmException} is caught.
     * @see #md5Crypt(byte[], String)
     * @since 1.12
     ;
		method `public static String md5Crypt(byte[], String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byte[])`, api document: 
     * Generates a libc crypt() compatible "$1$" MD5 based hash value.
     * <p>
     * See {@link Crypt#crypt(String, String)} for details. We use {@link SecureRandom} for seed generation by
     * default.
     * </p>
     *
     * @param keyBytes
     *            plaintext string to hash. Each array element is set to {@code 0} before returning.
     * @param salt
     *            salt string including the prefix and optionally garbage at the end. The salt may be null, in which
     *            case a salt is generated for you using {@link SecureRandom}.
     * @return the hash value
     * @throws IllegalArgumentException
     *             if the salt does not match the allowed pattern
     * @throws IllegalArgumentException
     *             when a {@link java.security.NoSuchAlgorithmException} is caught.
     ;
class `org.apache.commons.codec.digest.Crypt`:
	related functions:
		method `public static String crypt(byte[], String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byte[])`, api document: 
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
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byte[])`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.