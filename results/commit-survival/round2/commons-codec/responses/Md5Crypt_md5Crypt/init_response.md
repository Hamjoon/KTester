```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Md5Crypt_md5Crypt_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // No per‑test initialisation required.
    }

    @AfterEach
    void teardownAfterEach() {
        // No per‑test cleanup required.
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup required.
    }

    /**
     * Verify that a known password/salt/prefix combination produces the expected hash.
     */
    @Test
    void testKnownVector() {
        byte[] key = "secret".getBytes(StandardCharsets.UTF_8);
        String salt = "$1$1234";               // prefix + salt
        String prefix = Md5Crypt.MD5_PREFIX;   // "$1$"
        Random rnd = new SecureRandom();       // not used because salt is supplied

        String result = Md5Crypt.md5Crypt(key, salt, prefix, rnd);
        assertEquals("$1$1234$ImZYBLmYC.rbBKg9ERxX70", result);
    }

    /**
     * When the salt argument is {@code null} the method must generate a random 8‑character salt.
     * The result should match the expected pattern.
     */
    @Test
    void testRandomSaltGeneratedWhenSaltIsNull() {
        byte[] key = "password".getBytes(StandardCharsets.UTF_8);
        String prefix = Md5Crypt.MD5_PREFIX; // "$1$"
        // Use a deterministic Random so the test is repeatable.
        Random deterministicRandom = new SecureRandom(new byte[]{0x01, 0x02, 0x03, 0x04});

        String result = Md5Crypt.md5Crypt(key, null, prefix, deterministicRandom);
        // Pattern: $1$<8‑base64‑chars>$<hash>
        Pattern p = Pattern.compile("^\\$1\\$[./A-Za-z0-9]{1,8}\\$[./A-Za-z0-9]{22}$");
        assertTrue(p.matcher(result).matches(),
                "Result does not match the expected MD5‑crypt format: " + result);
    }

    /**
     * An invalid salt (missing the required prefix) must cause an IllegalArgumentException.
     */
    @Test
    void testInvalidSaltThrowsException() {
        byte[] key = "secret".getBytes(StandardCharsets.UTF_8);
        String badSalt = "noPrefix123";
        String prefix = Md5Crypt.MD5_PREFIX; // "$1$"
        Random rnd = new SecureRandom();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> Md5Crypt.md5Crypt(key, badSalt, prefix, rnd));
        assertTrue(ex.getMessage().contains("Invalid salt value"));
    }

    /**
     * Passing a {@code null} key array must result in a NullPointerException.
     */
    @Test
    void testNullKeyBytesThrowsNPE() {
        assertThrows(NullPointerException.class,
                () -> Md5Crypt.md5Crypt((byte[]) null, "$1$foo", Md5Crypt.MD5_PREFIX, new SecureRandom()));
    }

    /**
     * The implementation must zero‑out the password byte array after processing.
     */
    @Test
    void testZeroOutInput() {
        byte[] password = new byte[64];
        for (int i = 0; i < password.length; i++) {
            password[i] = (byte) 'X';
        }
        byte[] copy = password.clone();

        Md5Crypt.md5Crypt(password, "$1$abcd", Md5Crypt.MD5_PREFIX, new SecureRandom());

        // The original array must be all zeros.
        assertArrayEquals(new byte[password.length], password,
                "Password array should be cleared after md5Crypt execution");

        // Ensure the method actually used the original content (sanity check).
        assertFalse(java.util.Arrays.equals(password, copy),
                "Password array should have changed from its original content");
    }

    /**
     * Verify that the Apache APR1 variant works with a different prefix.
     */
    @Test
    void testApr1Prefix() {
        byte[] key = "apache".getBytes(StandardCharsets.UTF_8);
        String prefix = Md5Crypt.APR1_PREFIX; // "$apr1$"
        String salt = "$apr1$mySalt";         // prefix + salt
        Random rnd = new SecureRandom();

        String result = Md5Crypt.md5Crypt(key, salt, prefix, rnd);
        // Result must start with the supplied prefix and salt.
        assertTrue(result.startsWith("$apr1$mySalt$"),
                "Result should start with the APR1 prefix and provided salt");
        // Basic format validation.
        Pattern p = Pattern.compile("^\\$apr1\\$[./A-Za-z0-9]{1,8}\\$[./A-Za-z0-9]{22}$");
        assertTrue(p.matcher(result).matches(),
                "Result does not match the expected APR1‑crypt format: " + result);
    }
}
```