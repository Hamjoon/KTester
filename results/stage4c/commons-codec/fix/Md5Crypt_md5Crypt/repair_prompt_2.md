


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.security.SecureRandom;
import java.util.Random;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

class Md5Crypt_md5Crypt_Test {

    private Random random;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the random object before each test
        random = new SecureRandom();
    }

    @Test
    void testMd5CryptWithValidInput() {
        // given
        byte[] keyBytes = "secret".getBytes(StandardCharsets.UTF_8);
        String salt = "$1$1234";
        String prefix = Md5Crypt.MD5_PREFIX;
        // when
        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        // then
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithNullSalt() {
        // given
        byte[] keyBytes = "secret".getBytes(StandardCharsets.UTF_8);
        String prefix = Md5Crypt.MD5_PREFIX;
        // when
        String result = Md5Crypt.md5Crypt(keyBytes, null, prefix, random);
        // then
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithEmptySalt() {
        // given
        byte[] keyBytes = "secret".getBytes(StandardCharsets.UTF_8);
        String prefix = Md5Crypt.MD5_PREFIX;
        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            Md5Crypt.md5Crypt(keyBytes, "", prefix, random);
        });
    }

    @Test
    void testMd5CryptWithLongKeyBytes() {
        // given
        byte[] keyBytes = new byte[200];
        Arrays.fill(keyBytes, (byte) 'A');
        String salt = "$1$1234";
        String prefix = Md5Crypt.MD5_PREFIX;
        // when
        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        // then
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
        // Ensure input is zeroed out
        assertArrayEquals(new byte[200], keyBytes);
    }

    @Test
    void testMd5CryptWithNullKeyBytes() {
        // given
        String salt = "$1$abcde";
        String prefix = Md5Crypt.MD5_PREFIX;
        // then
        assertThrows(NullPointerException.class, () -> {
            // when
            Md5Crypt.md5Crypt(null, salt, prefix, random);
        });
    }

    @Test
    void testMd5CryptWithNullPrefix() {
        // given
        byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        String salt = "$1$abcde";
        // then
        assertThrows(NullPointerException.class, () -> {
            // when
            Md5Crypt.md5Crypt(keyBytes, salt, null, random);
        });
    }

    @Test
    void testMd5CryptWithNullRandom() {
        // given
        byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        String salt = "$1$abcde";
        String prefix = Md5Crypt.MD5_PREFIX;
        // then
        assertThrows(NullPointerException.class, () -> {
            // when
            Md5Crypt.md5Crypt(keyBytes, salt, prefix, null);
        });
    }

    @Test
    void testMd5CryptWithInvalidSalt() {
        // given
        byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        String salt = "$1$invalid_salt";
        String prefix = Md5Crypt.MD5_PREFIX;
        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        });
    }

    @Test
    void testMd5CryptWithApr1Prefix() {
        // given
        byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        String salt = "$apr1$abcdefg";
        String prefix = Md5Crypt.APR1_PREFIX;
        // when
        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        // then
        assertNotNull(result);
        assertTrue(result.startsWith(Md5Crypt.APR1_PREFIX));
    }

    @Test
    void testMd5CryptWithRandomSaltGeneration() {
        // given
        byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        String prefix = Md5Crypt.APR1_PREFIX;
        // when
        String result = Md5Crypt.md5Crypt(keyBytes, null, prefix, random);
        // then
        assertNotNull(result);
        assertTrue(result.startsWith(Md5Crypt.APR1_PREFIX));
    }

    @Test
    void testMd5CryptWithShortValidSalt() {
        // given
        byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        String salt = "$1$abc";
        String prefix = Md5Crypt.MD5_PREFIX;
        // when
        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        // then
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithMaxLengthSalt() {
        // given
        byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        String salt = "$1$abcdefgh";
        String prefix = Md5Crypt.MD5_PREFIX;
        // when
        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        // then
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithEmptyKeyBytes() {
        // given
        byte[] keyBytes = new byte[0];
        String salt = "$1$abcde";
        String prefix = Md5Crypt.MD5_PREFIX;
        // when
        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        // then
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithApr1SaltUsingMd5Prefix() {
        // given
        byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        String salt = "$apr1$abcde";
        String prefix = Md5Crypt.MD5_PREFIX;
        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        });
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8

╷
├─ JUnit Jupiter ✔
│  └─ Md5Crypt_md5Crypt_Test ✔
│     ├─ testMd5CryptWithNullRandom() ✘ Expected java.lang.NullPointerException to be thrown, but nothing was thrown.
│     ├─ testMd5CryptWithLongKeyBytes() ✔
│     ├─ testMd5CryptWithMaxLengthSalt() ✔
│     ├─ testMd5CryptWithApr1SaltUsingMd5Prefix() ✔
│     ├─ testMd5CryptWithInvalidSalt() ✘ Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
│     ├─ testMd5CryptWithRandomSaltGeneration() ✔
│     ├─ testMd5CryptWithValidInput() ✔
│     ├─ testMd5CryptWithEmptySalt() ✔
│     ├─ testMd5CryptWithNullKeyBytes() ✔
│     ├─ testMd5CryptWithShortValidSalt() ✔
│     ├─ testMd5CryptWithEmptyKeyBytes() ✔
│     ├─ testMd5CryptWithNullSalt() ✔
│     ├─ testMd5CryptWithApr1Prefix() ✔
│     └─ testMd5CryptWithNullPrefix() ✔
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (2):
  JUnit Jupiter:Md5Crypt_md5Crypt_Test:testMd5CryptWithNullRandom()
    MethodSource [className = 'org.apache.commons.codec.digest.Md5Crypt_md5Crypt_Test', methodName = 'testMd5CryptWithNullRandom', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.NullPointerException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.digest.Md5Crypt_md5Crypt_Test.testMd5CryptWithNullRandom(Md5Crypt_md5Crypt_Test.java:104)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]
  JUnit Jupiter:Md5Crypt_md5Crypt_Test:testMd5CryptWithInvalidSalt()
    MethodSource [className = 'org.apache.commons.codec.digest.Md5Crypt_md5Crypt_Test', methodName = 'testMd5CryptWithInvalidSalt', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
       org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
       org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
       org.apache.commons.codec.digest.Md5Crypt_md5Crypt_Test.testMd5CryptWithInvalidSalt(Md5Crypt_md5Crypt_Test.java:117)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:728)
       [...]

Test run finished after 75 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        14 tests found           ]
[         0 tests skipped         ]
[        14 tests started         ]
[         0 tests aborted         ]
[        12 tests successful      ]
[         2 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

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



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
