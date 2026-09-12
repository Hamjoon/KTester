
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Random;
import java.security.MessageDigest;
// Add other imports if necessary
import java.lang.reflect.Method;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class Sha2Crypt_sha2Crypt_Test {

    private static final int SHA256_BLOCKSIZE = 32;

    private static final int SHA512_BLOCKSIZE = 64;

    @Mock
    private Random random;

    @Test
    void testSha256CryptWithSalt() {
        byte[] keyBytes = "password".getBytes();
        String salt = "$5$rounds=5000$saltstring";
        String result = Sha2Crypt.sha256Crypt(keyBytes, salt);
        assertNotNull(result);
        assertTrue(result.startsWith("$5$"));
    }

    @Test
    void testSha256CryptWithNullSalt() {
        byte[] keyBytes = "password".getBytes();
        String salt = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Sha2Crypt.sha256Crypt(keyBytes, salt);
        });
        assertEquals("Salt must not be null", exception.getMessage());
    }

    @Test
    void testSha512CryptWithSalt() {
        byte[] keyBytes = "password".getBytes();
        String salt = "$6$rounds=5000$saltstring";
        String result = Sha2Crypt.sha512Crypt(keyBytes, salt);
        assertNotNull(result);
        assertTrue(result.startsWith("$6$"));
    }

    @Test
    void testSha512CryptWithNullSalt() {
        byte[] keyBytes = "password".getBytes();
        String salt = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Sha2Crypt.sha512Crypt(keyBytes, salt);
        });
        assertEquals("Salt must not be null", exception.getMessage());
    }

    @Test
    void testSha2CryptMethod() throws Exception {
        // Access the private method using reflection
        Method sha2CryptMethod = Sha2Crypt.class.getDeclaredMethod("sha2Crypt", byte[].class, String.class, String.class, int.class, String.class);
        sha2CryptMethod.setAccessible(true);
        // Test case where the salt is null, expecting an IllegalArgumentException to be thrown.
        byte[] keyBytes = new byte[] { 1, 2, 3 };
        String salt = null;
        String saltPrefix = "$5$";
        int blocksize = SHA256_BLOCKSIZE;
        String algorithm = "SHA-256";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sha2CryptMethod.invoke(null, keyBytes, salt, saltPrefix, blocksize, algorithm);
        });
        assertEquals("Salt must not be null", exception.getMessage());
        // Test case with a valid salt format and rounds set to the minimum (1000), expecting a valid hash output.
        salt = "$5$rounds=1000$abcd";
        String result = (String) sha2CryptMethod.invoke(null, keyBytes, salt, saltPrefix, blocksize, algorithm);
        assertNotNull(result);
        assertTrue(result.startsWith("$5$"));
        // Test case with a valid salt format and rounds set to the maximum (999999999), expecting a valid hash output.
        salt = "$5$rounds=999999999$abcd";
        result = (String) sha2CryptMethod.invoke(null, keyBytes, salt, saltPrefix, blocksize, algorithm);
        assertNotNull(result);
        assertTrue(result.startsWith("$5$"));
        // Test case with a valid salt format and default rounds (5000), expecting a valid hash output.
        salt = "$5$rounds=5000$abcd";
        result = (String) sha2CryptMethod.invoke(null, keyBytes, salt, saltPrefix, blocksize, algorithm);
        assertNotNull(result);
        assertTrue(result.startsWith("$5$"));
        // Test case with a valid salt format without rounds specification, expecting a valid hash output with default rounds.
        salt = "$5$abcd";
        result = (String) sha2CryptMethod.invoke(null, keyBytes, salt, saltPrefix, blocksize, algorithm);
        assertNotNull(result);
        assertTrue(result.startsWith("$5$"));
        // Test case with an invalid salt format, expecting an IllegalArgumentException to be thrown.
        salt = "invalidSalt";
        exception = assertThrows(IllegalArgumentException.class, () -> {
            sha2CryptMethod.invoke(null, keyBytes, salt, saltPrefix, blocksize, algorithm);
        });
        assertEquals("Invalid salt value: " + salt, exception.getMessage());
    }

    @Test
    void testSha2CryptMethodAdditional() throws Exception {
        // Access the private method using reflection
        Method sha2CryptMethod = Sha2Crypt.class.getDeclaredMethod("sha2Crypt", byte[].class, String.class, String.class, int.class, String.class);
        sha2CryptMethod.setAccessible(true);
        // SHA-512 with minimum rounds (1000) and valid salt format, expecting a valid hash output.
        byte[] keyBytes = new byte[] { 1, 2, 3 };
        String salt = "$6$rounds=1000$abcd";
        String saltPrefix = "$6$";
        int blocksize = SHA512_BLOCKSIZE;
        String algorithm = "SHA-512";
        String result = (String) sha2CryptMethod.invoke(null, keyBytes, salt, saltPrefix, blocksize, algorithm);
        assertNotNull(result);
        assertTrue(result.startsWith("$6$"));
        // SHA-512 with maximum rounds (999999999) and valid salt format, expecting a valid hash output.
        salt = "$6$rounds=999999999$abcd";
        result = (String) sha2CryptMethod.invoke(null, keyBytes, salt, saltPrefix, blocksize, algorithm);
        assertNotNull(result);
        assertTrue(result.startsWith("$6$"));
        // SHA-512 with default rounds (5000) and valid salt format, expecting a valid hash output.
        salt = "$6$rounds=5000$abcd";
        result = (String) sha2CryptMethod.invoke(null, keyBytes, salt, saltPrefix, blocksize, algorithm);
        assertNotNull(result);
        assertTrue(result.startsWith("$6$"));
        // SHA-512 with valid salt format without rounds specification, expecting a valid hash output with default rounds.
        salt = "$6$abcd";
        result = (String) sha2CryptMethod.invoke(null, keyBytes, salt, saltPrefix, blocksize, algorithm);
        assertNotNull(result);
        assertTrue(result.startsWith("$6$"));
        // Test case with a valid salt prefix and rounds but no actual salt, expecting a valid hash output with default rounds.
        salt = "$5$rounds=5000$";
        saltPrefix = "$5$";
        blocksize = SHA256_BLOCKSIZE;
        algorithm = "SHA-256";
        result = (String) sha2CryptMethod.invoke(null, keyBytes, salt, saltPrefix, blocksize, algorithm);
        assertNotNull(result);
        assertTrue(result.startsWith("$5$"));
        // Test case with empty keyBytes, expecting a valid hash output.
        keyBytes = new byte[] {};
        salt = "$5$abcd";
        result = (String) sha2CryptMethod.invoke(null, keyBytes, salt, saltPrefix, blocksize, algorithm);
        assertNotNull(result);
        assertTrue(result.startsWith("$5$"));
    }
}
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/org/apache/commons/codec/digest/Sha2Crypt_sha2Crypt_Test.java:75: error: local variables referenced from a lambda expression must be final or effectively final
            sha2CryptMethod.invoke(null, keyBytes, salt, saltPrefix, blocksize, algorithm);
                                                   ^
src/test/java/org/apache/commons/codec/digest/Sha2Crypt_sha2Crypt_Test.java:101: error: local variables referenced from a lambda expression must be final or effectively final
            sha2CryptMethod.invoke(null, keyBytes, salt, saltPrefix, blocksize, algorithm);
                                                   ^
2 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class org.apache.commons.codec.digest.Sha2Crypt': '\n * SHA2-based UNIX crypt implementation.\n * <p>\n * Based on the C implementation released into the Public Domain by Ulrich Drepper &lt;drepper@redhat.com&gt;\n * http://www.akkadia.org/drepper/SHA-crypt.txt\n * </p>\n * <p>\n * Conversion to Kotlin and from there to Java in 2012 by Christian Hammers &lt;ch@lathspell.de&gt; and likewise put\n * into the Public Domain.\n * </p>\n * <p>\n * This class is immutable and thread-safe.\n * </p>\n *\n * @since 1.7\n ', 'api document of method sha2Crypt(byte[], String, String, int, String)': '\n     * Generates a libc6 crypt() compatible "$5$" or "$6$" SHA2 based hash value.\n     * <p>\n     * This is a nearly line by line conversion of the original C function. The numbered comments are from the algorithm\n     * description, the short C-style ones from the original C code and the ones with "Remark" from me.\n     * </p>\n     * <p>\n     * See {@link Crypt#crypt(String, String)} for details.\n     * </p>\n     *\n     * @param keyBytes\n     *            plaintext to hash. Each array element is set to {@code 0} before returning.\n     * @param salt\n     *            real salt value without prefix or "rounds="; may not be null\n     * @param saltPrefix\n     *            either $5$ or $6$\n     * @param blocksize\n     *            a value that differs between $5$ and $6$\n     * @param algorithm\n     *            {@link MessageDigest} algorithm identifier string\n     * @return complete hash value including prefix and salt\n     * @throws IllegalArgumentException\n     *             if the given salt is {@code null} or does not match the allowed pattern\n     * @throws IllegalArgumentException\n     *             when a {@link NoSuchAlgorithmException} is caught\n     * @see MessageDigestAlgorithms\n     ', 'dependent classes': 'class `org.apache.commons.codec.digest.Sha2Crypt`:\n\tdependent fields:\n\t\tint org.apache.commons.codec.digest.Sha2Crypt.ROUNDS_MAX;\n\t\tjava.lang.String org.apache.commons.codec.digest.Sha2Crypt.ROUNDS_PREFIX;\n\t\tjava.util.regex.Pattern org.apache.commons.codec.digest.Sha2Crypt.SALT_PATTERN;\n\t\tint org.apache.commons.codec.digest.Sha2Crypt.ROUNDS_MIN;\n\t\tint org.apache.commons.codec.digest.Sha2Crypt.ROUNDS_DEFAULT;\n\t\nclass `org.apache.commons.codec.digest.Md5Crypt`:\n\trelated functions:\n\t\tmethod `public static String md5Crypt(byte[], String, String, Random)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha2Crypt(byte[], String, String, int, String)`, api document: \n     * Generates a libc6 crypt() "$1$" or Apache htpasswd "$apr1$" hash value.\n     * <p>\n     * See {@link Crypt#crypt(String, String)} or {@link #apr1Crypt(String, String)} for details.\n     * </p>\n     *\n     * @param keyBytes\n     *            plaintext string to hash. Each array element is set to {@code 0} before returning.\n     * @param salt\n     *            real salt value without prefix or "rounds=". The salt may be null, in which case a salt\n     *            is generated for you using {@link SecureRandom}.\n     * @param prefix\n     *            salt prefix\n     * @param random\n     *            the instance of {@link Random} to use for generating the salt.\n     *            Consider using {@link SecureRandom} for more secure salts.\n     * @return the hash value\n     * @throws IllegalArgumentException\n     *             if the salt does not match the allowed pattern\n     * @throws IllegalArgumentException\n     *             when a {@link java.security.NoSuchAlgorithmException} is caught.\n     * @since 1.12\n     ;\nclass `org.apache.commons.codec.digest.UnixCrypt`:\n\trelated functions:\n\t\tmethod `public static String crypt(byte[], String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha2Crypt(byte[], String, String, int, String)`, api document: \n     * Generates a crypt(3) compatible hash using the DES algorithm.\n     * <p>\n     * Using unspecified characters as salt results incompatible hash values.\n     * </p>\n     *\n     * @param original\n     *            plaintext password\n     * @param salt\n     *            a two character string drawn from [a-zA-Z0-9./]. The salt may be null, in which case a salt is\n     *            generated for you using {@link SecureRandom}.\n     * @return a 13 character string starting with the salt string\n     * @throws IllegalArgumentException\n     *             if the salt does not match the allowed pattern\n     ;\nclass `org.apache.commons.codec.language.DaitchMokotoffSoundex`:\n\trelated functions:\n\t\tmethod `public String soundex(String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha2Crypt(byte[], String, String, int, String)`, api document: \n     * Encodes a String using the Daitch-Mokotoff soundex algorithm with branching.\n     * <p>\n     * In case a string is encoded into multiple codes (see branching rules), the result will contain all codes,\n     * separated by \'|\'.\n     * </p>\n     * <p>\n     * Example: the name "AUERBACH" is encoded as both\n     * </p>\n     * <ul>\n     * <li>097400</li>\n     * <li>097500</li>\n     * </ul>\n     * <p>\n     * Thus the result will be "097400|097500".\n     * </p>\n     *\n     * @param source\n     *            A String object to encode\n     * @return A string containing a set of DM Soundex codes corresponding to the String supplied\n     * @throws IllegalArgumentException\n     *             if a character is not mapped\n     ;\nclass `org.apache.commons.codec.language.Nysiis`:\n\trelated functions:\n\t\tmethod `public String nysiis(String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha2Crypt(byte[], String, String, int, String)`, api document: \n     * Retrieves the NYSIIS code for a given String object.\n     *\n     * @param str\n     *            String to encode using the NYSIIS algorithm\n     * @return A NYSIIS code for the String supplied\n     ;\nclass `org.apache.commons.codec.digest.Crypt`:\n\trelated functions:\n\t\tmethod `public static String crypt(String, String)` returns `java.lang.String`, related with `org.apache.commons.codec.digest.Sha2Crypt.sha2Crypt(byte[], String, String, int, String)`, api document: \n     * Encrypts a password in a crypt(3) compatible way.\n     * <p>\n     * The exact algorithm depends on the format of the salt string:\n     * </p>\n     * <ul>\n     * <li>SHA-512 salts start with {@code $6$} and are up to 16 chars long.\n     * <li>SHA-256 salts start with {@code $5$} and are up to 16 chars long\n     * <li>MD5 salts start with {@code $1$} and are up to 8 chars long\n     * <li>DES, the traditional UnixCrypt algorithm is used with only 2 chars\n     * <li>Only the first 8 chars of the passwords are used in the DES algorithm!\n     * </ul>\n     * <p>\n     * The magic strings {@code "$apr1$"} and {@code "$2a$"} are not recognized by this method as its output should be\n     * identical with that of the libc implementation.\n     * </p>\n     * <p>\n     * The rest of the salt string is drawn from the set {@code [a-zA-Z0-9./]} and is cut at the maximum length of if a\n     * {@code "$"} sign is encountered. It is therefore valid to enter a complete hash value as salt to e.g. verify a\n     * password with:\n     * </p>\n     * <pre>\n     * storedPwd.equals(crypt(enteredPwd, storedPwd))\n     * </pre>\n     * <p>\n     * The resulting string starts with the marker string ({@code $n$}), where n is the same as the input salt.\n     * The salt is then appended, followed by a {@code "$"} sign.\n     * This is followed by the actual hash value.\n     * For DES the string only contains the salt and actual hash.\n     * The total length is dependent on the algorithm used:\n     * </p>\n     * <ul>\n     * <li>SHA-512: 106 chars\n     * <li>SHA-256: 63 chars\n     * <li>MD5: 34 chars\n     * <li>DES: 13 chars\n     * </ul>\n     * <p>\n     * Example:\n     * </p>\n     * <pre>\n     *      crypt("secret", "$1$xxxx") =&gt; "$1$xxxx$aMkevjfEIpa35Bh3G4bAc."\n     *      crypt("secret", "xx") =&gt; "xxWAum7tHdIUw"\n     * </pre>\n     * <p>\n     * This method comes in a variation that accepts a byte[] array to support input strings that are not encoded in\n     * UTF-8 but e.g. in ISO-8859-1 where equal characters result in different byte values.\n     * </p>\n     *\n     * @see "The man page of the libc crypt (3) function."\n     * @param key\n     *            plaintext password as entered by the used\n     * @param salt\n     *            real salt value without prefix or "rounds=". The salt may be null, in which case a\n     *            salt is generated for you using {@link SecureRandom}\n     * @return hash value, i.e. encrypted password including the salt string\n     * @throws IllegalArgumentException\n     *             if the salt does not match the allowed pattern\n     * @throws IllegalArgumentException\n     *             when a {@link java.security.NoSuchAlgorithmException} is caught. *\n     ;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


