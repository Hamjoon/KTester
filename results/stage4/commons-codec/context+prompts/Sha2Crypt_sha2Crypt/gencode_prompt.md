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
Here is the code of focal method, `sha2Crypt(byte[], String, String, int, String)`, with the focal class `Sha2Crypt`:
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
    private static final int ROUNDS_DEFAULT = 5000;
    private static final int ROUNDS_MAX = 999999999;
    private static final int ROUNDS_MIN = 1000;
    private static final String ROUNDS_PREFIX = "rounds=";
    static final String SHA256_PREFIX = "$5$";
    static final String SHA512_PREFIX = "$6$";
    private static final Pattern SALT_PATTERN = Pattern.compile("^\\$([56])\\$(rounds=(\\d+)\\$)?([\\.\\/a-zA-Z0-9]{1,16}).*");
    private static String sha2Crypt(final byte[] keyBytes, final String salt, final String saltPrefix, final int blocksize, final String algorithm) {
        final int keyLen = keyBytes.length;
        // Extracts effective salt and the number of rounds from the given salt.
        int rounds = ROUNDS_DEFAULT;
        boolean roundsCustom = false;
        if (salt == null) {
            throw new IllegalArgumentException("Salt must not be null");
        }
        final Matcher m = SALT_PATTERN.matcher(salt);
        if (!m.find()) {
            throw new IllegalArgumentException("Invalid salt value: " + salt);
        }
        if (m.group(3) != null) {
            rounds = Integer.parseInt(m.group(3));
            rounds = Math.max(ROUNDS_MIN, Math.min(ROUNDS_MAX, rounds));
            roundsCustom = true;
        }
        final String saltString = m.group(4);
        final byte[] saltBytes = saltString.getBytes(StandardCharsets.UTF_8);
        final int saltLen = saltBytes.length;
        // 1. start digest A
        // Prepare for the real work.
        MessageDigest ctx = DigestUtils.getDigest(algorithm);
        // 2. the password string is added to digest A
        /* Add the key string. */
        ctx.update(keyBytes);
        // 3. the salt string is added to digest A. This is just the salt string
        // itself without the enclosing '$', without the magic salt_prefix $5$ and
        // $6$ respectively and without the rounds=<N> specification.
        // 
        // NB: the MD5 algorithm did add the $1$ salt_prefix. This is not deemed
        // necessary since it is a constant string and does not add security
        // and /possibly/ allows a plain text attack. Since the rounds=<N>
        // specification should never be added this would also create an
        // inconsistency.
        /* The last part is the salt string. This must be at most 16 characters and it ends at the first `$' character
        (for compatibility with existing implementations).
         */
        ctx.update(saltBytes);
        // 4. start digest B
        /* Compute alternate sha512 sum with input KEY, SALT, and KEY. The final result will be added to the first
        context.
         */
        MessageDigest altCtx = DigestUtils.getDigest(algorithm);
        // 5. add the password to digest B
        /* Add key. */
        altCtx.update(keyBytes);
        // 6. add the salt string to digest B
        /* Add salt. */
        altCtx.update(saltBytes);
        // 7. add the password again to digest B
        /* Add key again. */
        altCtx.update(keyBytes);
        // 8. finish digest B
        /* Now get result of this (32 bytes) and add it to the other context. */
        byte[] altResult = altCtx.digest();
        // 9. For each block of 32 or 64 bytes in the password string (excluding
        // the terminating NUL in the C representation), add digest B to digest A
        /* Add for any character in the key one byte of the alternate sum. */
        /* (Remark: the C code comment seems wrong for key length > 32!) */
        int cnt = keyBytes.length;
        while (cnt > blocksize) {
            ctx.update(altResult, 0, blocksize);
            cnt -= blocksize;
        } 
        // 10. For the remaining N bytes of the password string add the first
        // N bytes of digest B to digest A
        ctx.update(altResult, 0, cnt);
        // 11. For each bit of the binary representation of the length of the
        // password string up to and including the highest 1-digit, starting
        // from to the lowest bit position (numeric value 1):
        // 
        // a) for a 1-digit add digest B to digest A
        // 
        // b) for a 0-digit add the password string
        // 
        // NB: this step differs significantly from the MD5 algorithm. It
        // adds more randomness.
        /* Take the binary representation of the length of the key and for every 1 add the alternate sum, for every 0
        the key.
         */
        cnt = keyBytes.length;
        while (cnt > 0) {
            if ((cnt & 1) != 0) {
                ctx.update(altResult, 0, blocksize);
            } else {
                ctx.update(keyBytes);
            }
            cnt >>= 1;
        } 
        // 12. finish digest A
        /* Create intermediate result. */
        altResult = ctx.digest();
        // 13. start digest DP
        /* Start computation of P byte sequence. */
        altCtx = DigestUtils.getDigest(algorithm);
        // 14. for every byte in the password (excluding the terminating NUL byte
        // in the C representation of the string)
        // 
        // add the password to digest DP
        /* For every character in the password add the entire password. */
        for (int i = 1; i <= keyLen; i++) {
            altCtx.update(keyBytes);
        }
        // 15. finish digest DP
        /* Finish the digest. */
        byte[] tempResult = altCtx.digest();
        // 16. produce byte sequence P of the same length as the password where
        // 
        // a) for each block of 32 or 64 bytes of length of the password string
        // the entire digest DP is used
        // 
        // b) for the remaining N (up to 31 or 63) bytes use the first N
        // bytes of digest DP
        /* Create byte sequence P. */
        final byte[] pBytes = new byte[keyLen];
        int cp = 0;
        while (cp < (keyLen - blocksize)) {
            System.arraycopy(tempResult, 0, pBytes, cp, blocksize);
            cp += blocksize;
        } 
        System.arraycopy(tempResult, 0, pBytes, cp, keyLen - cp);
        // 17. start digest DS
        /* Start computation of S byte sequence. */
        altCtx = DigestUtils.getDigest(algorithm);
        // 18. repeat the following 16+A[0] times, where A[0] represents the first
        // byte in digest A interpreted as an 8-bit unsigned value
        // 
        // add the salt to digest DS
        /* For every character in the password add the entire password. */
        for (int i = 1; i <= (16 + (altResult[0] & 0xff)); i++) {
            altCtx.update(saltBytes);
        }
        // 19. finish digest DS
        /* Finish the digest. */
        tempResult = altCtx.digest();
        // 20. produce byte sequence S of the same length as the salt string where
        // 
        // a) for each block of 32 or 64 bytes of length of the salt string
        // the entire digest DS is used
        // 
        // b) for the remaining N (up to 31 or 63) bytes use the first N
        // bytes of digest DS
        /* Create byte sequence S. */
        // Remark: The salt is limited to 16 chars, how does this make sense?
        final byte[] sBytes = new byte[saltLen];
        cp = 0;
        while (cp < (saltLen - blocksize)) {
            System.arraycopy(tempResult, 0, sBytes, cp, blocksize);
            cp += blocksize;
        } 
        System.arraycopy(tempResult, 0, sBytes, cp, saltLen - cp);
        // 21. repeat a loop according to the number specified in the rounds=<N>
        // specification in the salt (or the default value if none is
        // present). Each round is numbered, starting with 0 and up to N-1.
        // 
        // The loop uses a digest as input. In the first round it is the
        // digest produced in step 12. In the latter steps it is the digest
        // produced in step 21.h. The following text uses the notation
        // "digest A/C" to describe this behavior.
        /* Repeatedly run the collected hash value through sha512 to burn CPU cycles. */
        for (int i = 0; i <= (rounds - 1); i++) {
            // a) start digest C
            /* New context. */
            ctx = DigestUtils.getDigest(algorithm);
            // b) for odd round numbers add the byte sequence P to digest C
            // c) for even round numbers add digest A/C
            /* Add key or last result. */
            if ((i & 1) != 0) {
                ctx.update(pBytes, 0, keyLen);
            } else {
                ctx.update(altResult, 0, blocksize);
            }
            // d) for all round numbers not divisible by 3 add the byte sequence S
            /* Add salt for numbers not divisible by 3. */
            if ((i % 3) != 0) {
                ctx.update(sBytes, 0, saltLen);
            }
            // e) for all round numbers not divisible by 7 add the byte sequence P
            /* Add key for numbers not divisible by 7. */
            if ((i % 7) != 0) {
                ctx.update(pBytes, 0, keyLen);
            }
            // f) for odd round numbers add digest A/C
            // g) for even round numbers add the byte sequence P
            /* Add key or last result. */
            if ((i & 1) != 0) {
                ctx.update(altResult, 0, blocksize);
            } else {
                ctx.update(pBytes, 0, keyLen);
            }
            // h) finish digest C.
            /* Create intermediate result. */
            altResult = ctx.digest();
        }
        // 22. Produce the output string. This is an ASCII string of the maximum
        // size specified above, consisting of multiple pieces:
        // 
        // a) the salt salt_prefix, $5$ or $6$ respectively
        // 
        // b) the rounds=<N> specification, if one was present in the input
        // salt string. A trailing '$' is added in this case to separate
        // the rounds specification from the following text.
        // 
        // c) the salt string truncated to 16 characters
        // 
        // d) a '$' character
        /* Now we can construct the result string. It consists of three parts. */
        final StringBuilder buffer = new StringBuilder(saltPrefix);
        if (roundsCustom) {
            buffer.append(ROUNDS_PREFIX);
            buffer.append(rounds);
            buffer.append("$");
        }
        buffer.append(saltString);
        buffer.append("$");
        // e) the base-64 encoded final C digest. The encoding used is as
        // follows:
        // [...]
        // 
        // Each group of three bytes from the digest produces four
        // characters as output:
        // 
        // 1. character: the six low bits of the first byte
        // 2. character: the two high bits of the first byte and the
        // four low bytes from the second byte
        // 3. character: the four high bytes from the second byte and
        // the two low bits from the third byte
        // 4. character: the six high bits from the third byte
        // 
        // The groups of three bytes are as follows (in this sequence).
        // These are the indices into the byte array containing the
        // digest, starting with index 0. For the last group there are
        // not enough bytes left in the digest and the value zero is used
        // in its place. This group also produces only three or two
        // characters as output for SHA-512 and SHA-512 respectively.
        // This was just a safeguard in the C implementation:
        // int buflen = salt_prefix.length() - 1 + ROUNDS_PREFIX.length() + 9 + 1 + salt_string.length() + 1 + 86 + 1;
        if (blocksize == 32) {
            B64.b64from24bit(altResult[0], altResult[10], altResult[20], 4, buffer);
            B64.b64from24bit(altResult[21], altResult[1], altResult[11], 4, buffer);
            B64.b64from24bit(altResult[12], altResult[22], altResult[2], 4, buffer);
            B64.b64from24bit(altResult[3], altResult[13], altResult[23], 4, buffer);
            B64.b64from24bit(altResult[24], altResult[4], altResult[14], 4, buffer);
            B64.b64from24bit(altResult[15], altResult[25], altResult[5], 4, buffer);
            B64.b64from24bit(altResult[6], altResult[16], altResult[26], 4, buffer);
            B64.b64from24bit(altResult[27], altResult[7], altResult[17], 4, buffer);
            B64.b64from24bit(altResult[18], altResult[28], altResult[8], 4, buffer);
            B64.b64from24bit(altResult[9], altResult[19], altResult[29], 4, buffer);
            B64.b64from24bit(((byte) (0)), altResult[31], altResult[30], 3, buffer);
        } else {
            B64.b64from24bit(altResult[0], altResult[21], altResult[42], 4, buffer);
            B64.b64from24bit(altResult[22], altResult[43], altResult[1], 4, buffer);
            B64.b64from24bit(altResult[44], altResult[2], altResult[23], 4, buffer);
            B64.b64from24bit(altResult[3], altResult[24], altResult[45], 4, buffer);
            B64.b64from24bit(altResult[25], altResult[46], altResult[4], 4, buffer);
            B64.b64from24bit(altResult[47], altResult[5], altResult[26], 4, buffer);
            B64.b64from24bit(altResult[6], altResult[27], altResult[48], 4, buffer);
            B64.b64from24bit(altResult[28], altResult[49], altResult[7], 4, buffer);
            B64.b64from24bit(altResult[50], altResult[8], altResult[29], 4, buffer);
            B64.b64from24bit(altResult[9], altResult[30], altResult[51], 4, buffer);
            B64.b64from24bit(altResult[31], altResult[52], altResult[10], 4, buffer);
            B64.b64from24bit(altResult[53], altResult[11], altResult[32], 4, buffer);
            B64.b64from24bit(altResult[12], altResult[33], altResult[54], 4, buffer);
            B64.b64from24bit(altResult[34], altResult[55], altResult[13], 4, buffer);
            B64.b64from24bit(altResult[56], altResult[14], altResult[35], 4, buffer);
            B64.b64from24bit(altResult[15], altResult[36], altResult[57], 4, buffer);
            B64.b64from24bit(altResult[37], altResult[58], altResult[16], 4, buffer);
            B64.b64from24bit(altResult[59], altResult[17], altResult[38], 4, buffer);
            B64.b64from24bit(altResult[18], altResult[39], altResult[60], 4, buffer);
            B64.b64from24bit(altResult[40], altResult[61], altResult[19], 4, buffer);
            B64.b64from24bit(altResult[62], altResult[20], altResult[41], 4, buffer);
            B64.b64from24bit(((byte) (0)), ((byte) (0)), altResult[63], 2, buffer);
        }
        /* Clear the buffer for the intermediate result so that people attaching to processes or reading core dumps
        cannot get any information.
         */
        // Is there a better way to do this with the JVM?
        Arrays.fill(tempResult, ((byte) (0)));
        Arrays.fill(pBytes, ((byte) (0)));
        Arrays.fill(sBytes, ((byte) (0)));
        ctx.reset();
        altCtx.reset();
        Arrays.fill(keyBytes, ((byte) (0)));
        Arrays.fill(saltBytes, ((byte) (0)));
        return buffer.toString();
    }
public static String sha256Crypt(final byte[] keyBytes);
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
 

api document of method sha2Crypt(byte[], String, String, int, String): 

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
     

dependent classes: 
class `org.apache.commons.codec.digest.Sha2Crypt`:
	dependent fields:
		int org.apache.commons.codec.digest.Sha2Crypt.ROUNDS_MIN;
		java.util.regex.Pattern org.apache.commons.codec.digest.Sha2Crypt.SALT_PATTERN;
		java.lang.String org.apache.commons.codec.digest.Sha2Crypt.ROUNDS_PREFIX;
		int org.apache.commons.codec.digest.Sha2Crypt.ROUNDS_DEFAULT;
		int org.apache.commons.codec.digest.Sha2Crypt.ROUNDS_MAX;
	
class `org.apache.commons.codec.digest.Md5Crypt`:
	related functions:
		method `public static String md5Crypt(byte[], String, String, Random)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha2Crypt(byte[], String, String, int, String)`, api document: 
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
class `org.apache.commons.codec.digest.UnixCrypt`:
	related functions:
		method `public static String crypt(byte[], String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha2Crypt(byte[], String, String, int, String)`, api document: 
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
class `org.apache.commons.codec.language.DaitchMokotoffSoundex`:
	related functions:
		method `public String soundex(String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha2Crypt(byte[], String, String, int, String)`, api document: 
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
class `org.apache.commons.codec.language.Nysiis`:
	related functions:
		method `public String nysiis(String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha2Crypt(byte[], String, String, int, String)`, api document: 
     * Retrieves the NYSIIS code for a given String object.
     *
     * @param str
     *            String to encode using the NYSIIS algorithm
     * @return A NYSIIS code for the String supplied
     ;
class `org.apache.commons.codec.digest.Crypt`:
	related functions:
		method `public static String crypt(String, String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha2Crypt(byte[], String, String, int, String)`, api document: 
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



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.