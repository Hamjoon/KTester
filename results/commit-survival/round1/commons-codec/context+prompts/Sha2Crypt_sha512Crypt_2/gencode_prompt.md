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
Here is the code of focal method, `sha512Crypt(byte[], String)`, with the focal class `Sha2Crypt`:
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
    private static final int SHA512_BLOCKSIZE = 64;
    static final String SHA512_PREFIX = "$6$";
    public static String sha512Crypt(final byte[] keyBytes, String salt) {
        if (salt == null) {
            salt = SHA512_PREFIX + B64.getRandomSalt(8);
        }
        return sha2Crypt(keyBytes, salt, SHA512_PREFIX, SHA512_BLOCKSIZE, MessageDigestAlgorithms.SHA_512);
    }
public static String sha256Crypt(final byte[] keyBytes);
    public static String sha256Crypt(final byte[] keyBytes, String salt);
    public static String sha256Crypt(final byte[] keyBytes, String salt, final Random random);
    private static String sha2Crypt(final byte[] keyBytes, final String salt, final String saltPrefix, final int blocksize, final String algorithm);
    public static String sha512Crypt(final byte[] keyBytes);
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
 

api document of method sha512Crypt(byte[], String): 

     * Generates a libc6 crypt() compatible "$6$" hash value.
     * <p>
     * See {@link Crypt#crypt(String, String)} for details.
     * </p>
     * @param keyBytes
     *            plaintext to hash. Each array element is set to {@code 0} before returning.
     * @param salt
     *            real salt value without prefix or "rounds=". The salt may be null, in which case a salt is generated
     *            for you using {@link SecureRandom}; if you want to use a {@link Random} object other than
     *            {@link SecureRandom} then we suggest you provide it using
     *            {@link #sha512Crypt(byte[], String, Random)}.
     * @return complete hash value including salt
     * @throws IllegalArgumentException
     *             if the salt does not match the allowed pattern
     * @throws IllegalArgumentException
     *             when a {@link java.security.NoSuchAlgorithmException} is caught.
     

dependent classes: 
class `org.apache.commons.codec.digest.Sha2Crypt`:
	dependent fields:
		java.lang.String org.apache.commons.codec.digest.Sha2Crypt.SHA512_PREFIX;
		int org.apache.commons.codec.digest.Sha2Crypt.SHA512_BLOCKSIZE;
	related functions:
		method `public static String sha256Crypt(byte[], String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha512Crypt(byte[], String)`, api document: 
     * Generates a libc6 crypt() compatible "$5$" hash value.
     * <p>
     * See {@link Crypt#crypt(String, String)} for details.
     * </p>
     * @param keyBytes
     *            plaintext to hash. Each array element is set to {@code 0} before returning.
     * @param salt
     *            real salt value without prefix or "rounds=". The salt may be null, in which case a salt
     *            is generated for you using {@link SecureRandom}. If one does not want to use {@link SecureRandom},
     *            you can pass your own {@link Random} in {@link #sha256Crypt(byte[], String, Random)}.
     * @return complete hash value including salt
     * @throws IllegalArgumentException
     *             if the salt does not match the allowed pattern
     * @throws IllegalArgumentException
     *             when a {@link java.security.NoSuchAlgorithmException} is caught.
     ;
class `org.apache.commons.codec.digest.MessageDigestAlgorithms`:
	dependent fields:
		java.lang.String org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA_512;
	related functions:
		method `public static String[] values()` returns `java.lang.String[]`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha512Crypt(byte[], String)`, api document: 
     * Gets all constant values defined in this class.
     *
     * @return all constant values defined in this class.
     * @since 1.11
     ;
class `org.apache.commons.codec.digest.Md5Crypt`:
	related functions:
		method `public static String apr1Crypt(byte[])` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha512Crypt(byte[], String)`, api document: 
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
class `org.apache.commons.codec.digest.DigestUtils`:
	related functions:
		method `public static MessageDigest getSha512Digest()` returns `java.security.MessageDigest`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha512Crypt(byte[], String)`, api document: 
     * Gets an SHA-512 digest.
     *
     * @return An SHA-512 digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught, which should never happen because SHA-512 is a built-in algorithm
     * @see MessageDigestAlgorithms#SHA_512
     ;
class `org.apache.commons.codec.digest.Crypt`:
	related functions:
		method `public static String crypt(byte[], String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha512Crypt(byte[], String)`, api document: 
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
		method ` void decode(byte[], int, int, Context)` returns `void`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha512Crypt(byte[], String)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.