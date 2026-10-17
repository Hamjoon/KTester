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
Here is the code of focal method, `apr1Crypt(String)`, with the focal class `Md5Crypt`:
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
    public static String apr1Crypt(final String keyBytes) {
        return apr1Crypt(keyBytes.getBytes(StandardCharsets.UTF_8));
    }
public static String apr1Crypt(final byte[] keyBytes);
    public static String apr1Crypt(final byte[] keyBytes, String salt);
    public static String apr1Crypt(final byte[] keyBytes, final Random random);
    public static String apr1Crypt(final String keyBytes, final String salt);
    public static String md5Crypt(final byte[] keyBytes);
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
 

api document of method apr1Crypt(String): 

     * See {@link #apr1Crypt(String, String)} for details.
     * <p>
     * A salt is generated for you using {@link SecureRandom}.
     * </p>
     *
     * @param keyBytes
     *            plaintext string to hash. Each array element is set to {@code 0} before returning.
     * @return the hash value
     * @throws IllegalArgumentException
     *             when a {@link java.security.NoSuchAlgorithmException} is caught.
     * @see #apr1Crypt(byte[], String)
     

dependent classes: 
class `org.apache.commons.codec.digest.Crypt`:
	related functions:
		method `public static String crypt(String, String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Md5Crypt.apr1Crypt(String)`, api document: 
     * Encrypts a password in a crypt(3) compatible way.
     * <p>
     * The exact algorithm depends on the format of the salt string:
     * </p>
     * <ul>
     * <li>SHA-512 salts start with {@code $6$} and are up to 16 chars long.
     * <li>SHA-256 salts start with {@code $5$} and are up to 16 chars long
     * <li>MD5 salts start with {@code $1$} and are up to 8 chars long
     * <li>DES, the traditional UnixCrypt algorithm is used with only 2 chars
     * <li>Only the first 8 chars of the passwords are used in the DES algorithm!
     * </ul>
     * <p>
     * The magic strings {@code "$apr1$"} and {@code "$2a$"} are not recognized by this method as its output should be
     * identical with that of the libc implementation.
     * </p>
     * <p>
     * The rest of the salt string is drawn from the set {@code [a-zA-Z0-9./]} and is cut at the maximum length of if a
     * {@code "$"} sign is encountered. It is therefore valid to enter a complete hash value as salt to e.g. verify a
     * password with:
     * </p>
     * <pre>
     * storedPwd.equals(crypt(enteredPwd, storedPwd))
     * </pre>
     * <p>
     * The resulting string starts with the marker string ({@code $n$}), where n is the same as the input salt.
     * The salt is then appended, followed by a {@code "$"} sign.
     * This is followed by the actual hash value.
     * For DES the string only contains the salt and actual hash.
     * The total length is dependent on the algorithm used:
     * </p>
     * <ul>
     * <li>SHA-512: 106 chars
     * <li>SHA-256: 63 chars
     * <li>MD5: 34 chars
     * <li>DES: 13 chars
     * </ul>
     * <p>
     * Example:
     * </p>
     * <pre>
     *      crypt("secret", "$1$xxxx") =&gt; "$1$xxxx$aMkevjfEIpa35Bh3G4bAc."
     *      crypt("secret", "xx") =&gt; "xxWAum7tHdIUw"
     * </pre>
     * <p>
     * This method comes in a variation that accepts a byte[] array to support input strings that are not encoded in
     * UTF-8 but e.g. in ISO-8859-1 where equal characters result in different byte values.
     * </p>
     *
     * @see "The man page of the libc crypt (3) function."
     * @param key
     *            plaintext password as entered by the used
     * @param salt
     *            real salt value without prefix or "rounds=". The salt may be null, in which case a
     *            salt is generated for you using {@link SecureRandom}
     * @return hash value, i.e. encrypted password including the salt string
     * @throws IllegalArgumentException
     *             if the salt does not match the allowed pattern
     * @throws IllegalArgumentException
     *             when a {@link java.security.NoSuchAlgorithmException} is caught. *
     ;
class `org.apache.commons.codec.binary.StringUtils`:
	related functions:
		method `private static byte[] getBytes(String, Charset)` returns `byte[]`, related with `org.apache.commons.codec.digest.Md5Crypt.apr1Crypt(String)`, api document: 
     * Calls {@link String#getBytes(Charset)}
     *
     * @param string
     *            The string to encode (if null, return null).
     * @param charset
     *            The {@link Charset} to encode the {@code String}
     * @return the encoded bytes
     ;
		method `private static ByteBuffer getByteBuffer(String, Charset)` returns `java.nio.ByteBuffer`, related with `org.apache.commons.codec.digest.Md5Crypt.apr1Crypt(String)`, api document: 
     * Calls {@link String#getBytes(Charset)}
     *
     * @param string
     *            The string to encode (if null, return null).
     * @param charset
     *            The {@link Charset} to encode the {@code String}
     * @return the encoded bytes
     ;
		method `public static ByteBuffer getByteBufferUtf8(String)` returns `java.nio.ByteBuffer`, related with `org.apache.commons.codec.digest.Md5Crypt.apr1Crypt(String)`, api document: 
     * Encodes the given string into a byte buffer using the UTF-8 charset, storing the result into a new byte
     * array.
     *
     * @param string
     *            the String to encode, may be {@code null}
     * @return encoded bytes, or {@code null} if the input string was {@code null}
     * @throws NullPointerException
     *             Thrown if {@link StandardCharsets#UTF_8} is not initialized, which should never happen since it is
     *             required by the Java platform specification.
     * @see Charset
     * @see #getBytesUnchecked(String, String)
     * @since 1.11
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.