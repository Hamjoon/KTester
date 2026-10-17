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
Here is the code of focal method, `sha256Crypt(byte[], String, Random)`, with the focal class `Sha2Crypt`:
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
    private static final int SHA256_BLOCKSIZE = 32;
    static final String SHA256_PREFIX = "$5$";
    static final String SHA512_PREFIX = "$6$";
    public static String sha256Crypt(final byte[] keyBytes, String salt, final Random random) {
        if (salt == null) {
            salt = SHA256_PREFIX + B64.getRandomSalt(8, random);
        }
        return sha2Crypt(keyBytes, salt, SHA256_PREFIX, SHA256_BLOCKSIZE, MessageDigestAlgorithms.SHA_256);
    }
public static String sha256Crypt(final byte[] keyBytes);
    public static String sha256Crypt(final byte[] keyBytes, String salt);
    private static String sha2Crypt(final byte[] keyBytes, final String salt, final String saltPrefix, final int blocksize, final String algorithm);
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
 

api document of method sha256Crypt(byte[], String, Random): 

     * Generates a libc6 crypt() compatible "$5$" hash value.
     * <p>
     * See {@link Crypt#crypt(String, String)} for details.
     * </p>
     * @param keyBytes
     *            plaintext to hash. Each array element is set to {@code 0} before returning.
     * @param salt
     *            real salt value without prefix or "rounds=".
     * @param random
     *            the instance of {@link Random} to use for generating the salt.
     *            Consider using {@link SecureRandom} for more secure salts.
     * @return complete hash value including salt
     * @throws IllegalArgumentException
     *             if the salt does not match the allowed pattern
     * @throws IllegalArgumentException
     *             when a {@link java.security.NoSuchAlgorithmException} is caught.
     * @since 1.12
     

dependent classes: 
class `org.apache.commons.codec.digest.MessageDigestAlgorithms`:
	dependent fields:
		java.lang.String org.apache.commons.codec.digest.MessageDigestAlgorithms.SHA_256;
	
class `org.apache.commons.codec.digest.Sha2Crypt`:
	dependent fields:
		java.lang.String org.apache.commons.codec.digest.Sha2Crypt.SHA256_PREFIX;
		int org.apache.commons.codec.digest.Sha2Crypt.SHA256_BLOCKSIZE;
	related functions:
		method `public static String sha256Crypt(byte[], String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha256Crypt(byte[], String, Random)`, api document: 
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
class `org.apache.commons.codec.digest.B64`:
	related functions:
		method ` static String getRandomSalt(int)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha256Crypt(byte[], String, Random)`, api document: 
     * Generates a string of random chars from the B64T set.
     * <p>
     * The salt is generated with {@link SecureRandom}.
     * </p>
     *
     * @param num Number of chars to generate.
     * @return a random salt {@link String}.
     ;
class `org.apache.commons.codec.digest.DigestUtils`:
	related functions:
		method `public static MessageDigest getSha256Digest()` returns `java.security.MessageDigest`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha256Crypt(byte[], String, Random)`, api document: 
     * Gets an SHA-256 digest.
     *
     * @return An SHA-256 digest instance.
     * @throws IllegalArgumentException when a {@link NoSuchAlgorithmException} is caught, which should never happen because SHA-256 is a built-in algorithm
     * @see MessageDigestAlgorithms#SHA_256
     ;
class `org.apache.commons.codec.digest.Crypt`:
	related functions:
		method `public static String crypt(byte[], String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha256Crypt(byte[], String, Random)`, api document: 
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
class `org.apache.commons.codec.digest.Md5Crypt`:
	related functions:
		method `public static String md5Crypt(byte[], String, String, Random)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha256Crypt(byte[], String, Random)`, api document: 
     * Generates a libc6 crypt() "$1$" or Apache htpasswd "$apr1$" hash value.
     * <p>
     * See {@link Crypt#crypt(String, String)} or {@link #apr1Crypt(String, String)} for details.
     * </p>
     *
     * @param keyBytes
     *            plaintext string to hash. Each array element is set to {@code 0} before returning.
     * @param salt
     *            real salt value without prefix or "rounds=". The salt may be null, in which case a salt
     *            is generated for you using {@link SecureRandom}.
     * @param prefix
     *            salt prefix
     * @param random
     *            the instance of {@link Random} to use for generating the salt.
     *            Consider using {@link SecureRandom} for more secure salts.
     * @return the hash value
     * @throws IllegalArgumentException
     *             if the salt does not match the allowed pattern
     * @throws IllegalArgumentException
     *             when a {@link java.security.NoSuchAlgorithmException} is caught.
     * @since 1.12
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.