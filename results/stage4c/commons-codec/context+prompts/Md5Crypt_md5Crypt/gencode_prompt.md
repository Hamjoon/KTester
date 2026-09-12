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
Here is the code of focal method, `md5Crypt(byte[], String, String, Random)`, with the focal class `Md5Crypt`:
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
    private static final int BLOCKSIZE = 16;
    static final String MD5_PREFIX = "$1$";
    private static final int ROUNDS = 1000;
    public static String md5Crypt(final byte[] keyBytes, final String salt, final String prefix, final Random random) {
        final int keyLen = keyBytes.length;
        // Extract the real salt from the given string which can be a complete hash string.
        final String saltString;
        if (salt == null) {
            saltString = B64.getRandomSalt(8, random);
        } else {
            final Pattern p = Pattern.compile(("^" + prefix.replace("$", "\\$")) + "([\\.\\/a-zA-Z0-9]{1,8}).*");
            final Matcher m = p.matcher(salt);
            if (!m.find()) {
                throw new IllegalArgumentException("Invalid salt value: " + salt);
            }
            saltString = m.group(1);
        }
        final byte[] saltBytes = saltString.getBytes(StandardCharsets.UTF_8);
        final MessageDigest ctx = DigestUtils.getMd5Digest();
        /* The password first, since that is what is most unknown */
        ctx.update(keyBytes);
        /* Then our magic string */
        ctx.update(prefix.getBytes(StandardCharsets.UTF_8));
        /* Then the raw salt */
        ctx.update(saltBytes);
        /* Then just as many characters of the MD5(pw,salt,pw) */
        MessageDigest ctx1 = DigestUtils.getMd5Digest();
        ctx1.update(keyBytes);
        ctx1.update(saltBytes);
        ctx1.update(keyBytes);
        byte[] finalb = ctx1.digest();
        int ii = keyLen;
        while (ii > 0) {
            ctx.update(finalb, 0, Math.min(ii, 16));
            ii -= 16;
        } 
        /* Don't leave anything around in JVM they could use. */
        Arrays.fill(finalb, ((byte) (0)));
        /* Then something really weird... */
        ii = keyLen;
        final int j = 0;
        while (ii > 0) {
            if ((ii & 1) == 1) {
                ctx.update(finalb[j]);
            } else {
                ctx.update(keyBytes[j]);
            }
            ii >>= 1;
        } 
        /* Now make the output string */
        final StringBuilder passwd = new StringBuilder((prefix + saltString) + "$");
        finalb = ctx.digest();
        /* and now, just to make sure things don't run too fast On a 60 Mhz Pentium this takes 34 milliseconds, so you
        would need 30 seconds to build a 1000 entry dictionary...
         */
        for (int i = 0; i < ROUNDS; i++) {
            ctx1 = DigestUtils.getMd5Digest();
            if ((i & 1) != 0) {
                ctx1.update(keyBytes);
            } else {
                ctx1.update(finalb, 0, BLOCKSIZE);
            }
            if ((i % 3) != 0) {
                ctx1.update(saltBytes);
            }
            if ((i % 7) != 0) {
                ctx1.update(keyBytes);
            }
            if ((i & 1) != 0) {
                ctx1.update(finalb, 0, BLOCKSIZE);
            } else {
                ctx1.update(keyBytes);
            }
            finalb = ctx1.digest();
        }
        // The following was nearly identical to the Sha2Crypt code.
        // Again, the buflen is not really needed.
        // int buflen = MD5_PREFIX.length() - 1 + salt_string.length() + 1 + BLOCKSIZE + 1;
        B64.b64from24bit(finalb[0], finalb[6], finalb[12], 4, passwd);
        B64.b64from24bit(finalb[1], finalb[7], finalb[13], 4, passwd);
        B64.b64from24bit(finalb[2], finalb[8], finalb[14], 4, passwd);
        B64.b64from24bit(finalb[3], finalb[9], finalb[15], 4, passwd);
        B64.b64from24bit(finalb[4], finalb[10], finalb[5], 4, passwd);
        B64.b64from24bit(((byte) (0)), ((byte) (0)), finalb[11], 2, passwd);
        /* Don't leave anything around in JVM they could use. */
        // Is there a better way to do this with the JVM?
        ctx.reset();
        ctx1.reset();
        Arrays.fill(keyBytes, ((byte) (0)));
        Arrays.fill(saltBytes, ((byte) (0)));
        Arrays.fill(finalb, ((byte) (0)));
        return passwd.toString();
    }
public static String apr1Crypt(final byte[] keyBytes);
    public static String apr1Crypt(final byte[] keyBytes, String salt);
    public static String apr1Crypt(final byte[] keyBytes, final Random random);
    public static String apr1Crypt(final String keyBytes);
    public static String apr1Crypt(final String keyBytes, final String salt);
    public static String md5Crypt(final byte[] keyBytes);
    public static String md5Crypt(final byte[] keyBytes, final String salt);
    public static String md5Crypt(final byte[] keyBytes, final String salt, final String prefix);
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
 

api document of method md5Crypt(byte[], String, String, Random): 

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
     

dependent classes: 
class `org.apache.commons.codec.digest.Md5Crypt`:
	dependent fields:
		int org.apache.commons.codec.digest.Md5Crypt.ROUNDS;
		int org.apache.commons.codec.digest.Md5Crypt.BLOCKSIZE;
	
class `org.apache.commons.codec.digest.Sha2Crypt`:
	related functions:
		method `private static String sha2Crypt(byte[], String, String, int, String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byte[], String, String, Random)`, api document: 
     * Generates a libc6 crypt() compatible "$5$" or "$6$" SHA2 based hash value.
     * <p>
     * This is a nearly line by line conversion of the original C function. The numbered comments are from the algorithm
     * description, the short C-style ones from the original C code and the ones with "Remark" from me.
     * </p>
     * <p>
     * See {@link Crypt#crypt(String, String)} for details.
     * </p>
     *
     * @param keyBytes
     *            plaintext to hash. Each array element is set to {@code 0} before returning.
     * @param salt
     *            real salt value without prefix or "rounds="; may not be null
     * @param saltPrefix
     *            either $5$ or $6$
     * @param blocksize
     *            a value that differs between $5$ and $6$
     * @param algorithm
     *            {@link MessageDigest} algorithm identifier string
     * @return complete hash value including prefix and salt
     * @throws IllegalArgumentException
     *             if the given salt is {@code null} or does not match the allowed pattern
     * @throws IllegalArgumentException
     *             when a {@link NoSuchAlgorithmException} is caught
     * @see MessageDigestAlgorithms
     ;
class `org.apache.commons.codec.digest.UnixCrypt`:
	related functions:
		method `public static String crypt(byte[], String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byte[], String, String, Random)`, api document: 
     * Generates a crypt(3) compatible hash using the DES algorithm.
     * <p>
     * Using unspecified characters as salt results incompatible hash values.
     * </p>
     *
     * @param original
     *            plaintext password
     * @param salt
     *            a two character string drawn from [a-zA-Z0-9./]. The salt may be null, in which case a salt is
     *            generated for you using {@link SecureRandom}.
     * @return a 13 character string starting with the salt string
     * @throws IllegalArgumentException
     *             if the salt does not match the allowed pattern
     ;
class `org.apache.commons.codec.digest.Crypt`:
	related functions:
		method `public static String crypt(String, String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byte[], String, String, Random)`, api document: 
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
class `org.apache.commons.codec.language.DaitchMokotoffSoundex`:
	related functions:
		method `public String soundex(String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byte[], String, String, Random)`, api document: 
     * Encodes a String using the Daitch-Mokotoff soundex algorithm with branching.
     * <p>
     * In case a string is encoded into multiple codes (see branching rules), the result will contain all codes,
     * separated by '|'.
     * </p>
     * <p>
     * Example: the name "AUERBACH" is encoded as both
     * </p>
     * <ul>
     * <li>097400</li>
     * <li>097500</li>
     * </ul>
     * <p>
     * Thus the result will be "097400|097500".
     * </p>
     *
     * @param source
     *            A String object to encode
     * @return A string containing a set of DM Soundex codes corresponding to the String supplied
     * @throws IllegalArgumentException
     *             if a character is not mapped
     ;
class `org.apache.commons.codec.binary.Base16`:
	related functions:
		method `private int decodeOctet(byte)` returns `int`, related with `org.apache.commons.codec.digest.Md5Crypt.md5Crypt(byte[], String, String, Random)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.