


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class Sha2Crypt_sha2Crypt_Test {

    private static final String VALID_SALT = "$5$rounds=5000$abcdefghijklmnopqrstuv$";

    private static final byte[] VALID_KEY = "password".getBytes();

    private static final String SHA256_PREFIX = "$5$";

    private static final String SHA512_PREFIX = "$6$";

    private static final int SHA256_BLOCKSIZE = 32;

    private static final int SHA512_BLOCKSIZE = 64;

    private String invokeSha2Crypt(byte[] key, String salt, String prefix, int blockSize, String algorithm) throws Exception {
        Method method = Sha2Crypt.class.getDeclaredMethod("sha2Crypt", byte[].class, String.class, String.class, int.class, String.class);
        // Make the private method accessible
        method.setAccessible(true);
        return (String) method.invoke(null, key, salt, prefix, blockSize, algorithm);
    }

    @Test
    public void testSha2Crypt_ValidInput_SHA256() throws Exception {
        String result = invokeSha2Crypt(VALID_KEY, VALID_SALT, SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidInput_SHA512() throws Exception {
        String result = invokeSha2Crypt(VALID_KEY, VALID_SALT, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_NullSalt_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invokeSha2Crypt(VALID_KEY, null, SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        });
        assertEquals("Salt must not be null", exception.getCause().getMessage());
    }

    @Test
    public void testSha2Crypt_InvalidSalt_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invokeSha2Crypt(VALID_KEY, "invalid_salt", SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        });
        assertTrue(exception.getCause().getMessage().contains("Invalid salt value"));
    }

    @Test
    public void testSha2Crypt_InvalidSaltFormat_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invokeSha2Crypt(VALID_KEY, "invalidSaltFormat", SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-512");
        });
        assertTrue(exception.getCause().getMessage().contains("Invalid salt value"));
    }

    @Test
    public void testSha2Crypt_ValidSaltWithCustomRounds() throws Exception {
        String salt = "$6$rounds=1500$validSalt";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithDefaultRounds() throws Exception {
        String salt = "$5$validSalt";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_RoundsExceedingMaximum() throws Exception {
        String salt = "$6$rounds=1000000000$validSalt";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_RoundsBelowMinimum() throws Exception {
        String salt = "$6$rounds=500$validSalt";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithDifferentBlockSizes() throws Exception {
        String salt = "$5$validSalt";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA512_PREFIX, 64, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithMinimumRounds() throws Exception {
        String salt = "$6$rounds=1000$validSalt";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithMaximumRounds() throws Exception {
        String salt = "$5$rounds=999999999$validSalt";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithEdgeCaseBlockSize() throws Exception {
        String salt = "$6$validSalt";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA512_PREFIX, 1, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithDifferentAlgorithms() throws Exception {
        String salt = "$5$validSalt";
        String result1 = invokeSha2Crypt(VALID_KEY, salt, SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        assertNotNull(result1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invokeSha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA256_BLOCKSIZE, "SHA-1");
        });
        assertTrue(exception.getMessage().contains("Invalid algorithm"));
    }

    @Test
    public void testSha2Crypt_EmptySalt() throws Exception {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invokeSha2Crypt(VALID_KEY, "$5$$", SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        });
        assertTrue(exception.getMessage().contains("Invalid salt value"));
    }

    @Test
    public void testSha2Crypt_InvalidSaltWithCustomRounds() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invokeSha2Crypt(VALID_KEY, "$6$rounds=abc$validSalt", SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        });
        assertTrue(exception.getMessage().contains("Invalid salt value"));
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended

╷
├─ JUnit Jupiter ✔
│  └─ Sha2Crypt_sha2Crypt_Test ✔
│     ├─ testSha2Crypt_ValidSaltWithDifferentAlgorithms() ✘ Unexpected exception type thrown, expected: <java.lang.IllegalArgumentException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testSha2Crypt_InvalidSalt_ThrowsException() ✘ Unexpected exception type thrown, expected: <java.lang.IllegalArgumentException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testSha2Crypt_ValidSaltWithMaximumRounds() 113762 ms ✔
│     ├─ testSha2Crypt_ValidSaltWithDefaultRounds() ✔
│     ├─ testSha2Crypt_EmptySalt() ✘ Unexpected exception type thrown, expected: <java.lang.IllegalArgumentException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testSha2Crypt_InvalidSaltFormat_ThrowsException() ✘ Unexpected exception type thrown, expected: <java.lang.IllegalArgumentException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testSha2Crypt_ValidSaltWithEdgeCaseBlockSize() ✔
│     ├─ testSha2Crypt_InvalidSaltWithCustomRounds() ✘ Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
│     ├─ testSha2Crypt_ValidInput_SHA256() ✔
│     ├─ testSha2Crypt_ValidInput_SHA512() ✔
│     ├─ testSha2Crypt_ValidSaltWithDifferentBlockSizes() ✔
│     ├─ testSha2Crypt_NullSalt_ThrowsException() ✘ Unexpected exception type thrown, expected: <java.lang.IllegalArgumentException> but was: <java.lang.reflect.InvocationTargetException>
│     ├─ testSha2Crypt_ValidSaltWithMinimumRounds() ✔
│     ├─ testSha2Crypt_RoundsBelowMinimum() ✔
│     ├─ testSha2Crypt_RoundsExceedingMaximum() 328257 ms ✔
│     └─ testSha2Crypt_ValidSaltWithCustomRounds() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (6):
  JUnit Jupiter:Sha2Crypt_sha2Crypt_Test:testSha2Crypt_ValidSaltWithDifferentAlgorithms()
    MethodSource [className = 'org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test', methodName = 'testSha2Crypt_ValidSaltWithDifferentAlgorithms', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.IllegalArgumentException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test.testSha2Crypt_ValidSaltWithDifferentAlgorithms(Sha2Crypt_sha2Crypt_Test.java:133)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test.invokeSha2Crypt(Sha2Crypt_sha2Crypt_Test.java:33)
       [...]
     Caused by: java.lang.IllegalArgumentException: Input buffer too short
       java.base/java.security.MessageDigest.update(MessageDigest.java:365)
       org.apache.commons.codec.digest.Sha2Crypt.sha2Crypt(Sha2Crypt.java:286)
       [...]
  JUnit Jupiter:Sha2Crypt_sha2Crypt_Test:testSha2Crypt_InvalidSalt_ThrowsException()
    MethodSource [className = 'org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test', methodName = 'testSha2Crypt_InvalidSalt_ThrowsException', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.IllegalArgumentException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test.testSha2Crypt_InvalidSalt_ThrowsException(Sha2Crypt_sha2Crypt_Test.java:58)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test.invokeSha2Crypt(Sha2Crypt_sha2Crypt_Test.java:33)
       [...]
     Caused by: java.lang.IllegalArgumentException: Invalid salt value: invalid_salt
       org.apache.commons.codec.digest.Sha2Crypt.sha2Crypt(Sha2Crypt.java:184)
       [...]
  JUnit Jupiter:Sha2Crypt_sha2Crypt_Test:testSha2Crypt_EmptySalt()
    MethodSource [className = 'org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test', methodName = 'testSha2Crypt_EmptySalt', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.IllegalArgumentException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test.testSha2Crypt_EmptySalt(Sha2Crypt_sha2Crypt_Test.java:141)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test.invokeSha2Crypt(Sha2Crypt_sha2Crypt_Test.java:33)
       [...]
     Caused by: java.lang.IllegalArgumentException: Invalid salt value: $5$$
       org.apache.commons.codec.digest.Sha2Crypt.sha2Crypt(Sha2Crypt.java:184)
       [...]
  JUnit Jupiter:Sha2Crypt_sha2Crypt_Test:testSha2Crypt_InvalidSaltFormat_ThrowsException()
    MethodSource [className = 'org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test', methodName = 'testSha2Crypt_InvalidSaltFormat_ThrowsException', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.IllegalArgumentException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test.testSha2Crypt_InvalidSaltFormat_ThrowsException(Sha2Crypt_sha2Crypt_Test.java:66)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test.invokeSha2Crypt(Sha2Crypt_sha2Crypt_Test.java:33)
       [...]
     Caused by: java.lang.IllegalArgumentException: Invalid salt value: invalidSaltFormat
       org.apache.commons.codec.digest.Sha2Crypt.sha2Crypt(Sha2Crypt.java:184)
       [...]
  JUnit Jupiter:Sha2Crypt_sha2Crypt_Test:testSha2Crypt_InvalidSaltWithCustomRounds()
    MethodSource [className = 'org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test', methodName = 'testSha2Crypt_InvalidSaltWithCustomRounds', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test.testSha2Crypt_InvalidSaltWithCustomRounds(Sha2Crypt_sha2Crypt_Test.java:149)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:Sha2Crypt_sha2Crypt_Test:testSha2Crypt_NullSalt_ThrowsException()
    MethodSource [className = 'org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test', methodName = 'testSha2Crypt_NullSalt_ThrowsException', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Unexpected exception type thrown, expected: <java.lang.IllegalArgumentException> but was: <java.lang.reflect.InvocationTargetException>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:67)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test.testSha2Crypt_NullSalt_ThrowsException(Sha2Crypt_sha2Crypt_Test.java:50)
       [...]
     Caused by: java.lang.reflect.InvocationTargetException
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.apache.commons.codec.digest.Sha2Crypt_sha2Crypt_Test.invokeSha2Crypt(Sha2Crypt_sha2Crypt_Test.java:33)
       [...]
     Caused by: java.lang.IllegalArgumentException: Salt must not be null
       org.apache.commons.codec.digest.Sha2Crypt.sha2Crypt(Sha2Crypt.java:179)
       [...]

Test run finished after 442533 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        16 tests found           ]
[         0 tests skipped         ]
[        16 tests started         ]
[         0 tests aborted         ]
[        10 tests successful      ]
[         6 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

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



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
