```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit‑tests for {@link Md5Crypt#md5Crypt(byte[], String)}.
 *
 * <p>The tests focus on the contract of the public method:
 * <ul>
 *   <li>correct handling of normal inputs (including salt handling rules)</li>
 *   <li>validation of illegal arguments</li>
 *   <li>zero‑out of the password byte array after the operation</li>
 *   <li>behaviour when {@code null} is supplied</li>
 * </ul>
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class Md5Crypt_md5Crypt_4_Test {

    /** Regular expression that matches any valid MD5‑crypt string produced by this class. */
    private static final Pattern MD5_CRYPT_PATTERN = Pattern.compile("^\\$1\\$[A-Za-z0-9./]{0,8}\\$.+$");

    /** A deterministic {@link Random} that will be used for tests that need a random source. */
    private static Random deterministicRandom;

    @BeforeAll
    static void setupBeforeAll() {
        // Use a fixed‑seed Random so that generated salts are reproducible.
        deterministicRandom = new Random(0L);
    }

    @BeforeEach
    void setupBeforeEach() {
        // No per‑test state needed at the moment.
    }

    @AfterEach
    void teardownAfterEach() {
        // No per‑test cleanup required.
    }

    @AfterAll
    static void teardownAfterAll() {
        // Nothing to release.
    }

    @Test
    void testMd5CryptWithValidSaltProducesValidFormat() {
        byte[] password = "secret".getBytes(StandardCharsets.UTF_8);
        String salt = "$1$abcd1234"; // valid 8‑character salt (prefix included)
        String result = Md5Crypt.md5Crypt(password, salt);
        assertNotNull(result);
        assertTrue(MD5_CRYPT_PATTERN.matcher(result).matches(),
                "Result does not match MD5‑crypt format: " + result);
    }

    @Test
    void testMd5CryptSaltTruncationAtDollarSign() {
        byte[] password = "secret".getBytes(StandardCharsets.UTF_8);
        // Salt contains an extra '$' – everything after the first additional '$' must be ignored.
        String salt = "$1$abcd$extra$stuff";
        String expected = Md5Crypt.md5Crypt(password, "$1$abcd"); // what the implementation should actually use
        String actual   = Md5Crypt.md5Crypt(password, salt);
        assertEquals(expected, actual);
    }

    @Test
    void testMd5CryptSaltLengthLimit() {
        byte[] password = "secret".getBytes(StandardCharsets.UTF_8);
        // Provide a salt longer than 8 characters; only the first 8 must be used.
        String longSalt = "$1$abcdefghijklmnop";
        String expected = Md5Crypt.md5Crypt(password, "$1$abcdefgh");
        String actual   = Md5Crypt.md5Crypt(password, longSalt);
        assertEquals(expected, actual);
    }

    @Test
    void testMd5CryptEmptySaltThrows() {
        byte[] password = "secret".getBytes(StandardCharsets.UTF_8);
        assertThrows(IllegalArgumentException.class,
                () -> Md5Crypt.md5Crypt(password, ""),
                "Empty salt string must result in IllegalArgumentException");
    }

    @Test
    void testMd5CryptNullKeyBytesThrows() {
        assertThrows(NullPointerException.class,
                () -> Md5Crypt.md5Crypt((byte[]) null, "$1$foo"));
    }

    @Test
    void testMd5CryptZeroOutsInputArray() {
        byte[] password = new byte[32];
        Arrays.fill(password, (byte) 'X');
        // Keep a copy for comparison after the call.
        byte[] originalCopy = password.clone();

        Md5Crypt.md5Crypt(password, "$1$abcd1234");

        // The original contents must be cleared (all zeros) after the method returns.
        byte[] expectedZeroes = new byte[originalCopy.length];
        assertArrayEquals(expectedZeroes, password, "Password array should be zero‑filled after md5Crypt");
    }

    @Test
    void testMd5CryptWithRandomSaltGeneratedViaRandomOverload() {
        // The overload that accepts a Random eventually delegates to the
        // (byte[], String) variant after building a salt string.
        byte[] password = "randomPass".getBytes(StandardCharsets.UTF_8);
        String result = Md5Crypt.md5Crypt(password, deterministicRandom);
        assertNotNull(result);
        assertTrue(MD5_CRYPT_PATTERN.matcher(result).matches(),
                "Result generated with Random does not match expected format");
    }
}
```