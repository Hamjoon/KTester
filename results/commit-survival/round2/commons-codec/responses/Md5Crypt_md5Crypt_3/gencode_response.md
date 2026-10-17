```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Additional unit tests for {@link Md5Crypt#md5Crypt(byte[], Random)} covering the
 * test cases described in the JSON specification.
 *
 * <p>All tests use a fresh password byte array per invocation because the method
 * overwrites (zeros) the supplied array before returning.</p>
 */
@ExtendWith(MockitoExtension.class)
class Md5Crypt_md5Crypt_3_Test {

    private static final Pattern MD5_CRYPT_PATTERN = Pattern.compile("^\\$1\\$([A-Za-z0-9./]{8})\\$(.+)$");
    private byte[] passwordBytes;

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // a reproducible default password – individual tests replace this with their own data
        passwordBytes = "secret".getBytes(StandardCharsets.UTF_8);
    }

    @AfterEach
    void teardownAfterEach() {
        // Ensure the password buffer is cleared after each test to avoid side‑effects
        if (passwordBytes != null) {
            Arrays.fill(passwordBytes, (byte) 0);
        }
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup required
    }

    // -------------------------------------------------------------------------
    // Existing tests (kept unchanged)
    // -------------------------------------------------------------------------

    @Test
    void testMd5CryptWithRandomProducesValidHash() {
        Random deterministicRandom = new Random(0L);
        String result = Md5Crypt.md5Crypt(passwordBytes.clone(), deterministicRandom);

        assertNotNull(result, "Resulting hash must not be null");

        Matcher m = MD5_CRYPT_PATTERN.matcher(result);
        assertTrue(m.matches(),
                "Hash should match pattern $1$<8‑char‑salt>$<hash>, but was: " + result);

        String salt = m.group(1);
        assertEquals(8, salt.length(),
                "Generated salt length should be 8 characters");
    }

    @Test
    void testMd5CryptNullPasswordThrows() {
        Random rnd = new Random();
        assertThrows(NullPointerException.class,
                () -> Md5Crypt.md5Crypt((byte[]) null, rnd));
    }

    @Test
    void testMd5CryptZeroesInputBuffer() {
        byte[] buffer = new byte[64];
        Arrays.fill(buffer, (byte) 'A');
        byte[] original = buffer.clone();

        Md5Crypt.md5Crypt(buffer, new Random());

        assertArrayEquals(new byte[buffer.length], buffer,
                "Password buffer should be zeroed after md5Crypt execution");

        String hash = Md5Crypt.md5Crypt(original, new Random());
        assertNotNull(hash);
        assertTrue(hash.matches("^\\$1\\$[A-Za-z0-9./]{0,8}\\$.+$"));
    }

    @Test
    void testMd5CryptNullRandomThrows() {
        assertThrows(NullPointerException.class,
                () -> Md5Crypt.md5Crypt(passwordBytes.clone(), (Random) null));
    }

    // -------------------------------------------------------------------------
    // New tests derived from the JSON test cases
    // -------------------------------------------------------------------------

    /**
     * Helper that validates the returned string matches the MD5‑crypt pattern
     * and that the extracted salt is exactly eight characters long.
     */
    private static Matcher assertValidMd5CryptFormat(String hash) {
        assertNotNull(hash, "Hash must not be null");
        Matcher m = MD5_CRYPT_PATTERN.matcher(hash);
        assertTrue(m.matches(), "Hash should match MD5‑crypt pattern, but was: " + hash);
        assertEquals(8, m.group(1).length(), "Salt length should be 8 characters");
        return m;
    }

    // ---------- Parameterized tests for several simple input variations ----------
    static Stream<Arguments> passwordAndRandomProvider() {
        return Stream.of(
                // Special Characters in Password
                Arguments.of(
                        "p@ßw0rd!\n\t".getBytes(StandardCharsets.UTF_8),
                        new Random(987654321L)
                ),
                // Empty Password Input
                Arguments.of(
                        "".getBytes(StandardCharsets.UTF_8),
                        new Random(0L)
                ),
                // Large Password Input (1 KB of zero bytes)
                Arguments.of(
                        new byte[1024],
                        new Random(555L)
                ),
                // SecureRandom Instance as Random
                Arguments.of(
                        "secure".getBytes(StandardCharsets.UTF_8),
                        new SecureRandom()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("passwordAndRandomProvider")
    void testMd5CryptVariousInputsProducesValidHash(byte[] keyBytes, Random random) {
        // Clone because the method zeroes the array
        String hash = Md5Crypt.md5Crypt(keyBytes.clone(), random);
        Matcher m = assertValidMd5CryptFormat(hash);
        // Additional sanity check: the hash part (group 2) must be non‑empty
        assertFalse(m.group(2).isEmpty(), "Hash part should not be empty");
    }

    // ---------- Deterministic salt generation (seed 42) ----------
    @Test
    void testMd5CryptDeterministicSaltAndHash() {
        byte[] key = "password".getBytes(StandardCharsets.UTF_8);
        Random rnd = new Random(42L); // fixed seed

        String result = Md5Crypt.md5Crypt(key.clone(), rnd);
        // Expected value taken from the specification
        String expected = "$1$eJ5rYjG/$c7U8lU6hVh1KcK3J6JQKk0";

        assertEquals(expected, result,
                "With a deterministic Random seed the hash must match the expected value.");
    }

    // ---------- Different Random seeds must produce different salts ----------
    @Test
    void testMd5CryptDifferentSeedsProduceDifferentSalts() {
        byte[] key = "samePass".getBytes(StandardCharsets.UTF_8);

        String hash1 = Md5Crypt.md5Crypt(key.clone(), new Random(1L));
        String hash2 = Md5Crypt.md5Crypt(key.clone(), new Random(2L));

        Matcher m1 = MD5_CRYPT_PATTERN.matcher(hash1);
        Matcher m2 = MD5_CRYPT_PATTERN.matcher(hash2);
        assertTrue(m1.matches() && m2.matches(),
                "Both results must match the MD5‑crypt pattern");

        String salt1 = m1.group(1);
        String salt2 = m2.group(1);

        assertNotEquals(salt1, salt2,
                "Salts generated from different Random seeds must differ");
        assertNotEquals(hash1, hash2,
                "Full hash strings must differ when salts differ");
    }

    // ---------- Null password handling (explicit test for this overload) ----------
    @Test
    void testMd5CryptNullPasswordThrows_NullPasswordInputGroup() {
        Random rnd = new Random(123L);
        assertThrows(NullPointerException.class,
                () -> Md5Crypt.md5Crypt((byte[]) null, rnd),
                "Passing null as password should throw NullPointerException");
    }

    // ---------- Null Random handling (explicit test for this overload) ----------
    @Test
    void testMd5CryptNullRandomThrows_NullRandomParameterGroup() {
        byte[] key = "noprand".getBytes(StandardCharsets.UTF_8);
        assertThrows(NullPointerException.class,
                () -> Md5Crypt.md5Crypt(key.clone(), (Random) null),
                "Passing null Random should throw NullPointerException");
    }
}
```