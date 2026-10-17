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

    /*** -------------------- Lifecycle hooks (currently no shared state) -------------------- ***/

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // No per‑test initialization required.
    }

    @AfterEach
    void teardownAfterEach() {
        // No per‑test cleanup required.
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup required.
    }

    /*** -------------------- Tests for md5Crypt(byte[], String, String, Random) -------------------- ***/

    @Test
    void md5Crypt_WithExplicitSaltAndPrefix_ProducesExpectedStructure() {
        // GIVEN
        final byte[] key = "secret".getBytes(StandardCharsets.UTF_8);
        final String salt = "$1$abcd";           // prefix + 4‑character salt
        final String prefix = Md5Crypt.MD5_PREFIX; // "$1$"
        final Random random = new SecureRandom(); // not used because salt is non‑null

        // WHEN
        final String result = Md5Crypt.md5Crypt(key, salt, prefix, random);

        // THEN
        // Result must start with the supplied prefix and salt (without the leading "$1$" part of the salt string)
        assertTrue(result.startsWith("$1$abcd$"),
                "Result should start with the supplied prefix and salt");
        // The rest of the hash must be a Base64‑like string of length 22 (MD5‑crypt output)
        final String hashPart = result.substring("$1$abcd$".length());
        assertEquals(22, hashPart.length(),
                "Hash part length should be 22 characters");
        assertTrue(Pattern.matches("[./A-Za-z0-9]{22}", hashPart),
                "Hash part should contain only the allowed characters");
    }

    @Test
    void md5Crypt_WithNullSalt_GeneratesRandomSaltAndMatchesPattern() {
        // GIVEN
        final byte[] key = "secret".getBytes(StandardCharsets.UTF_8);
        final String salt = null;               // trigger random salt generation
        final String prefix = Md5Crypt.MD5_PREFIX; // "$1$"
        final Random random = new SecureRandom(); // true randomness – we only check format

        // WHEN
        final String result = Md5Crypt.md5Crypt(key, salt, prefix, random);

        // THEN
        // The result must match the generic MD5‑crypt format: $1$<1‑8 chars>$<22 chars>
        final String regex = "^\\$1\\$[./A-Za-z0-9]{1,8}\\$[./A-Za-z0-9]{22}$";
        assertTrue(Pattern.matches(regex, result),
                "Result should follow the MD5‑crypt format with a random salt");
    }

    @Test
    void md5Crypt_WithInvalidSalt_ThrowsIllegalArgumentException() {
        // GIVEN
        final byte[] key = "secret".getBytes(StandardCharsets.UTF_8);
        // Salt does not start with the required prefix "$1$"
        final String badSalt = "badSaltValue";
        final String prefix = Md5Crypt.MD5_PREFIX;
        final Random random = new SecureRandom();

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class,
                () -> Md5Crypt.md5Crypt(key, badSalt, prefix, random),
                "An illegal salt value should cause IllegalArgumentException");
    }

    @Test
    void md5Crypt_ZeroesOutPasswordArrayAfterProcessing() {
        // GIVEN
        final byte[] key = new byte[64];
        // Fill with a known pattern
        for (int i = 0; i < key.length; i++) {
            key[i] = (byte) i;
        }
        final String salt = "$1$xyz";
        final String prefix = Md5Crypt.MD5_PREFIX;
        final Random random = new SecureRandom();

        // WHEN
        Md5Crypt.md5Crypt(key, salt, prefix, random);

        // THEN
        // After the call the original array must be cleared (all zeros)
        for (byte b : key) {
            assertEquals(0, b, "Password byte array must be cleared after md5Crypt");
        }
    }
}
```