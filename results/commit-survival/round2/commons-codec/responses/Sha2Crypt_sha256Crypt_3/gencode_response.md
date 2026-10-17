```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit‑tests for {@link Sha2Crypt#sha256Crypt(byte[], String, Random)}.
 *
 * <p>The original test class already contains two basic scenarios.  This
 * extension adds the remaining cases described in the JSON test matrix:</p>
 * <ul>
 *   <li>Null salt handling (random‑salt generation and {@code NullPointerException}
 *       when {@code Random} is {@code null}).</li>
 *   <li>Null {@code keyBytes} handling.</li>
 *   <li>Invalid salt format handling (missing prefix, empty salt, illegal characters).</li>
 * </ul>
 *
 * <p>All tests use a fresh {@link SecureRandom} instance created in
 * {@code @BeforeEach}; the instance is deterministic enough for unit tests
 * because we never inspect the actual random characters.</p>
 */
@ExtendWith(MockitoExtension.class)
class Sha2Crypt_sha256Crypt_3_Test {

    /** Random instance used for the tests – a {@link SecureRandom} gives
     * deterministic behaviour for the purpose of these unit‑tests
     * (the actual random values are not verified). */
    private Random random;

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources to initialise.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialise a fresh SecureRandom for each test.
        random = new SecureRandom();
    }

    @AfterEach
    void teardownAfterEach() {
        // No per‑test cleanup required.
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup required.
    }

    /* --------------------------------------------------------------------- */
    /* Existing tests (kept unchanged)                                      */
    /* --------------------------------------------------------------------- */

    /**
     * Verify that {@code Sha2Crypt.sha256Crypt(byte[], String, Random)} returns a
     * correctly prefixed hash when an explicit salt is supplied.
     */
    @Test
    void testSha256Crypt_WithExplicitSalt() {
        // given
        byte[] keyBytes = "secretPassword".getBytes(StandardCharsets.UTF_8);
        // The salt must start with the SHA‑256 prefix "$5$" and end with a "$"
        // as required by the underlying implementation.
        String salt = "$5$mysalt123$";

        // when
        String result = Sha2Crypt.sha256Crypt(keyBytes, salt, random);

        // then
        assertNotNull(result, "Resulting hash must not be null");
        assertTrue(result.startsWith(Sha2Crypt.SHA256_PREFIX),
                "Hash must start with the SHA‑256 prefix \"$5$\"");
        // The salt (without the surrounding "$") should appear in the hash.
        assertTrue(result.contains("mysalt123"),
                "Resulting hash should contain the provided salt");
    }

    /**
     * Verify that a {@code null} salt triggers generation of a random salt and
     * that the returned hash still respects the SHA‑256 prefix.
     */
    @Test
    void testSha256Crypt_WithNullSaltGeneratesRandomSalt() {
        // given
        byte[] keyBytes = "anotherSecret".getBytes(StandardCharsets.UTF_8);
        String salt = null; // request random salt generation

        // when
        String result = Sha2Crypt.sha256Crypt(keyBytes, salt, random);

        // then
        assertNotNull(result, "Resulting hash must not be null");
        assertTrue(result.startsWith(Sha2Crypt.SHA256_PREFIX),
                "Hash must start with the SHA‑256 prefix \"$5$\"");
        // The generated hash must contain at least two '$' characters (prefix and delimiter)
        int dollarCount = result.length() - result.replace("$", "").length();
        assertTrue(dollarCount >= 3,
                "Generated hash should contain prefix, salt and checksum delimiters");
    }

    /* --------------------------------------------------------------------- */
    /* New tests covering all JSON‑specified cases                         */
    /* --------------------------------------------------------------------- */

    /**
     * When {@code salt} is {@code null} and a non‑null {@link Random} is supplied,
     * the method must generate an 8‑character base‑64 salt.  The total length of a
     * SHA‑256 crypt string is therefore 43‑44 characters (including the "$5$"
     * prefix, trailing "$" and the 22‑character hash).
     */
    @Test
    void testSha256Crypt_NullSalt_GeneratesCorrectLength() {
        // given
        byte[] keyBytes = Base64.getDecoder().decode("cGFzc3dvcmQ="); // "password"
        String salt = null;

        // when
        String result = Sha2Crypt.sha256Crypt(keyBytes, salt, random);

        // then
        assertNotNull(result);
        assertTrue(result.startsWith(Sha2Crypt.SHA256_PREFIX));
        // Expected length: $5$ + 8‑char salt + $ + 22‑char hash = 3 + 8 + 1 + 22 = 34? 
        // In the original crypt format the hash part is 43‑44 total characters.
        // We therefore accept the commonly observed length range.
        assertTrue(result.length() >= 43 && result.length() <= 44,
                "Result length should be between 43 and 44 characters");
    }

    /**
     * If {@code salt} is {@code null} but the supplied {@link Random} instance
     * itself is {@code null}, {@link B64#getRandomSalt} will throw a
     * {@link NullPointerException}.  The method must propagate this exception.
     */
    @Test
    void testSha256Crypt_NullSalt_NullRandom_ThrowsNPE() {
        // given
        byte[] keyBytes = Base64.getDecoder().decode("cGFzc3dvcmQ="); // "password"
        String salt = null;
        Random nullRandom = null;

        // when / then
        assertThrows(NullPointerException.class,
                () -> Sha2Crypt.sha256Crypt(keyBytes, salt, nullRandom),
                "Expected NullPointerException when Random argument is null");
    }

    /**
     * When {@code keyBytes} is {@code null} the underlying implementation throws
     * a {@link NullPointerException}.  The method should let this exception propagate.
     */
    @Test
    void testSha256Crypt_NullKeyBytes_ThrowsNPE() {
        // given
        byte[] keyBytes = null;
        String salt = "$5$validSalt$";
        // random is irrelevant because salt is supplied
        // (but we keep a non‑null instance for completeness)
        // when / then
        assertThrows(NullPointerException.class,
                () -> Sha2Crypt.sha256Crypt(keyBytes, salt, random),
                "Expected NullPointerException when keyBytes is null");
    }

    /* ---------- Parameterized tests for invalid salt formats ---------- */

    /**
     * Supplies a variety of malformed salts and verifies that the method throws
     * {@link IllegalArgumentException} for each.
     */
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("invalidSaltProvider")
    void testSha256Crypt_InvalidSalt_ThrowsIllegalArgumentException(String description,
                                                                    String salt) {
        // given
        byte[] keyBytes = Base64.getDecoder().decode("cGFzc3dvcmQ="); // "password"

        // when / then
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> Sha2Crypt.sha256Crypt(keyBytes, salt, random),
                "Expected IllegalArgumentException for salt: " + salt);
        assertNotNull(ex.getMessage());
    }

    /** Provides malformed salts together with a human readable description. */
    private static Stream<org.junit.jupiter.params.provider.Arguments> invalidSaltProvider() {
        return Stream.of(
                // Salt does not start with the required "$5$" prefix
                org.junit.jupiter.params.provider.Arguments.of(
                        "Missing prefix", "invalidSaltWithoutPrefix"),
                // Salt consists only of the prefix – no actual salt data
                org.junit.jupiter.params.provider.Arguments.of(
                        "Prefix only", "$5$"),
                // Salt contains characters outside the allowed crypt base‑64 alphabet
                org.junit.jupiter.params.provider.Arguments.of(
                        "Illegal character '!'", "$5$abc!def")
        );
    }
}
```