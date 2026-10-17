```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Additional unit‑tests for {@link Md5Crypt#md5Crypt(byte[], String)} that are generated
 * from the JSON test‑case specification.
 *
 * <p>These tests use {@code @ParameterizedTest} together with {@code MethodSource}
 * to keep the test code compact while still covering every scenario described in the
 * specification.</p>
 *
 * <p>All tests follow the same contract:</p>
 * <ul>
 *   <li>When {@code expectedException} is non‑null the method must throw that exception.</li>
 *   <li>Otherwise the returned hash must match the {@code expectedRegex} pattern.</li>
 *   <li>The password byte array must be cleared to zeroes after the call (covered by a single test
 *       in the original file).</li>
 * </ul>
 */
@ExtendWith(MockitoExtension.class)
class Md5Crypt_md5Crypt_4_Test {

    /** Regular expression that matches any valid MD5‑crypt string produced by this class. */
    private static final Pattern MD5_CRYPT_PATTERN = Pattern.compile("^\\$1\\$[A-Za-z0-9./]{0,8}\\$.+$");

    /** A deterministic {@link Random} used for the overload that accepts a {@link Random}. */
    private static Random deterministicRandom;

    @BeforeAll
    static void setupBeforeAll() {
        // Fixed‑seed Random makes generated salts reproducible.
        deterministicRandom = new Random(0L);
    }

    @BeforeEach
    void setupBeforeEach() { /* no per‑test state needed */ }

    @AfterEach
    void teardownAfterEach() { /* no cleanup needed */ }

    @AfterAll
    static void teardownAfterAll() { /* nothing to release */ }

    /* --------------------------------------------------------------------- */
    /* Original (non‑parameterised) tests that were already supplied.      */
    /* --------------------------------------------------------------------- */

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
        java.util.Arrays.fill(password, (byte) 'X');
        byte[] originalCopy = password.clone();

        Md5Crypt.md5Crypt(password, "$1$abcd1234");

        byte[] expectedZeroes = new byte[originalCopy.length];
        assertArrayEquals(expectedZeroes, password,
                "Password array should be zero‑filled after md5Crypt");
    }

    @Test
    void testMd5CryptWithRandomSaltGeneratedViaRandomOverload() {
        byte[] password = "randomPass".getBytes(StandardCharsets.UTF_8);
        String result = Md5Crypt.md5Crypt(password, deterministicRandom);
        assertNotNull(result);
        assertTrue(MD5_CRYPT_PATTERN.matcher(result).matches(),
                "Result generated with Random does not match expected format");
    }

    /* --------------------------------------------------------------------- */
    /* Parameterised tests generated from the JSON specification.          */
    /* --------------------------------------------------------------------- */

    /** Simple holder for a single test case. */
    private static final class Md5Case {
        final String description;
        final byte[] keyBytes;      // may be null
        final String salt;          // may be null
        final String expectedRegex; // used when no exception is expected
        final Class<? extends Throwable> expectedException; // null if success expected

        Md5Case(String description, byte[] keyBytes, String salt,
                String expectedRegex, Class<? extends Throwable> expectedException) {
            this.description = description;
            this.keyBytes = keyBytes;
            this.salt = salt;
            this.expectedRegex = expectedRegex;
            this.expectedException = expectedException;
        }
    }

    /* --------------------  Valid salt handling -------------------- */

    private static Stream<Md5Case> validSaltHandlingProvider() {
        return Stream.of(
                // Typical case – short valid salt
                new Md5Case(
                        "Typical case – non‑null key and a valid short salt (1‑8 allowed characters).",
                        toBytes("password123"),
                        "abcd",
                        "^\\$1\\$abcd\\$[./0-9A-Za-z]{22}$",
                        null),

                // Salt longer than 8 characters – truncates
                new Md5Case(
                        "Salt longer than 8 characters – only the first 8 characters are used.",
                        toBytes("secret"),
                        "abcdefghijk",
                        "^\\$1\\$abcdefgh\\$[./0-9A-Za-z]{22}$",
                        null),

                // Salt already contains the MD5 prefix – strip it
                new Md5Case(
                        "Salt already contains the MD5 prefix. The implementation must strip the leading \"$1$\" before using the remaining part \"xyz\" as the salt.",
                        toBytes("admin"),
                        "$1$xyz",
                        "^\\$1\\$xyz\\$[./0-9A-Za-z]{22}$",
                        null),

                // Null salt triggers random 8‑character salt
                new Md5Case(
                        "Null salt triggers generation of a random 8‑character salt.",
                        toBytes("randomKey"),
                        null,
                        "^\\$1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$",
                        null)
        );
    }

    @ParameterizedTest(name = "[Valid] {index}: {0}")
    @MethodSource("validSaltHandlingProvider")
    void testValidSaltHandling(Md5Case tc) {
        if (tc.expectedException != null) {
            assertThrows(tc.expectedException,
                    () -> Md5Crypt.md5Crypt(tc.keyBytes, tc.salt),
                    tc.description);
        } else {
            String hash = Md5Crypt.md5Crypt(tc.keyBytes, tc.salt);
            assertNotNull(hash, tc.description);
            assertTrue(Pattern.compile(tc.expectedRegex).matcher(hash).matches(),
                    () -> tc.description + " – result: " + hash);
        }
    }

    /* --------------------  Edge case salt handling -------------------- */

    private static Stream<Md5Case> edgeCaseSaltHandlingProvider() {
        return Stream.of(
                // Empty salt string – treated as missing → random salt
                new Md5Case(
                        "Empty salt string should be treated as missing; the method must generate a random 8‑character salt.",
                        toBytes("password123"),
                        "",
                        "^\\$1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$",
                        null),

                // Salt consisting solely of the MD5 prefix – generate random
                new Md5Case(
                        "Salt consisting solely of the MD5 prefix must be stripped, resulting in a generated random 8‑character salt.",
                        toBytes("password123"),
                        "$1$",
                        "^\\$1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$",
                        null),

                // Salt with leading and trailing '$' – inner value is used
                new Md5Case(
                        "Salt with leading and trailing '$' characters should be parsed to the inner value \"abcd\".",
                        toBytes("password123"),
                        "$abcd$",
                        "^\\$1\\$abcd\\$[./0-9A-Za-z]{22}$",
                        null)
        );
    }

    @ParameterizedTest(name = "[Edge] {index}: {0}")
    @MethodSource("edgeCaseSaltHandlingProvider")
    void testEdgeCaseSaltHandling(Md5Case tc) {
        if (tc.expectedException != null) {
            assertThrows(tc.expectedException,
                    () -> Md5Crypt.md5Crypt(tc.keyBytes, tc.salt),
                    tc.description);
        } else {
            String hash = Md5Crypt.md5Crypt(tc.keyBytes, tc.salt);
            assertNotNull(hash, tc.description);
            assertTrue(Pattern.compile(tc.expectedRegex).matcher(hash).matches(),
                    () -> tc.description + " – result: " + hash);
        }
    }

    /* --------------------  Invalid character handling -------------------- */

    private static Stream<Md5Case> invalidCharHandlingProvider() {
        return Stream.of(
                // Salt contains an invalid character '!' – parsing stops before it
                new Md5Case(
                        "Salt contains an invalid character ('!'); parsing stops before it, so only \"ab\" is used.",
                        toBytes("secret"),
                        "ab!cd",
                        "^\\$1\\$ab\\$[./0-9A-Za-z]{22}$",
                        null),

                // Salt composed entirely of invalid characters – random salt generated
                new Md5Case(
                        "Salt composed entirely of invalid characters results in no usable characters; a random 8‑character salt must be generated.",
                        toBytes("secret"),
                        "!!!",
                        "^\\$1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$",
                        null)
        );
    }

    @ParameterizedTest(name = "[InvalidChar] {index}: {0}")
    @MethodSource("invalidCharHandlingProvider")
    void testInvalidCharacterHandling(Md5Case tc) {
        if (tc.expectedException != null) {
            assertThrows(tc.expectedException,
                    () -> Md5Crypt.md5Crypt(tc.keyBytes, tc.salt),
                    tc.description);
        } else {
            String hash = Md5Crypt.md5Crypt(tc.keyBytes, tc.salt);
            assertNotNull(hash, tc.description);
            assertTrue(Pattern.compile(tc.expectedRegex).matcher(hash).matches(),
                    () -> tc.description + " – result: " + hash);
        }
    }

    /* --------------------  Key byte edge cases -------------------- */

    private static Stream<Md5Case> keyByteEdgeCasesProvider() {
        return Stream.of(
                // Null keyBytes → NullPointerException
                new Md5Case(
                        "Passing a null keyBytes array should cause a NullPointerException as the method requires a non‑null password.",
                        null,
                        "salt123",
                        null,
                        NullPointerException.class),

                // Empty password, valid salt
                new Md5Case(
                        "An empty password (zero‑length byte array) is valid; the hash must still be produced using the supplied salt.",
                        new byte[0],
                        "salt123",
                        "^\\$1\\$salt123\\$[./0-9A-Za-z]{22}$",
                        null),

                // Empty password, null salt → random salt
                new Md5Case(
                        "Empty password with a null salt – the method should generate a random 8‑character salt and produce a valid hash.",
                        new byte[0],
                        null,
                        "^\\$1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$",
                        null),

                // Very long password, normal salt
                new Md5Case(
                        "Very long password input should be handled correctly; the resulting hash must still conform to the expected format.",
                        toBytes("aVeryLongPasswordThatExceedsTypicalLengthsAndContainsVariousCharacters123!@#"),
                        "longsalt",
                        "^\\$1\\$longsalt\\$[./0-9A-Za-z]{22}$",
                        null)
        );
    }

    @ParameterizedTest(name = "[KeyEdge] {index}: {0}")
    @MethodSource("keyByteEdgeCasesProvider")
    void testKeyByteEdgeCases(Md5Case tc) {
        if (tc.expectedException != null) {
            assertThrows(tc.expectedException,
                    () -> Md5Crypt.md5Crypt(tc.keyBytes, tc.salt),
                    tc.description);
        } else {
            String hash = Md5Crypt.md5Crypt(tc.keyBytes, tc.salt);
            assertNotNull(hash, tc.description);
            assertTrue(Pattern.compile(tc.expectedRegex).matcher(hash).matches(),
                    () -> tc.description + " – result: " + hash);
        }
    }

    /* --------------------------------------------------------------------- */
    /* Helper methods                                                       */
    /* --------------------------------------------------------------------- */

    /** Convert a UTF‑8 string into a byte array – used for test data creation. */
    private static byte[] toBytes(String s) {
        return s == null ? null : s.getBytes(StandardCharsets.UTF_8);
    }
}
```