```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Tests for {@link Md5Crypt#apr1Crypt(String)}.
 *
 * <p>
 * The method under test delegates to {@code apr1Crypt(byte[])} and therefore
 * generates a random salt when no explicit salt is supplied. The tests focus on
 * the contract of the public API: correct handling of {@code null}, empty
 * strings, format of the returned hash and the fact that two successive calls
 * (with the same password) normally produce different salts.
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class Md5Crypt_apr1Crypt_2_Test {

    /** Regular expression that matches the APR‑1 hash format. */
    private static final Pattern APR1_PATTERN =
            Pattern.compile("^\\$apr1\\$[a-zA-Z0-9./]{0,8}\\$.+$");

    /** Extracts the salt part (the characters between the first two '$'). */
    private static final Pattern SALT_EXTRACTOR =
            Pattern.compile("^\\$apr1\\$([^$]+)\\$.*$");

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources are required for these tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // Nothing to initialise before each test.
    }

    @AfterEach
    void teardownAfterEach() {
        // No per‑test cleanup needed.
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup necessary.
    }

    @Test
    void apr1CryptStringProducesValidHash() {
        String password = "secretPassword";
        String hash = Md5Crypt.apr1Crypt(password);
        assertNotNull(hash, "Hash must not be null");
        assertTrue(APR1_PATTERN.matcher(hash).matches(),
                "Hash should match APR‑1 pattern, but was: " + hash);
    }

    @Test
    void apr1CryptEmptyStringProducesValidHash() {
        String hash = Md5Crypt.apr1Crypt("");
        assertNotNull(hash);
        assertTrue(APR1_PATTERN.matcher(hash).matches(),
                "Empty password should still produce a valid APR‑1 hash");
    }

    @Test
    void apr1CryptNullThrowsNPE() {
        assertThrows(NullPointerException.class,
                () -> Md5Crypt.apr1Crypt(null),
                "Calling apr1Crypt with null must throw NullPointerException");
    }

    @Test
    void apr1CryptGeneratesDifferentSaltsForSamePassword() {
        String password = "samePassword";

        String firstHash = Md5Crypt.apr1Crypt(password);
        String secondHash = Md5Crypt.apr1Crypt(password);

        // Extract the salts from the two hashes.
        String firstSalt = extractSalt(firstHash);
        String secondSalt = extractSalt(secondHash);

        // In the overwhelming majority of cases the salts will differ because a random
        // generator is used. If they happen to be equal, the test is retried once to
        // avoid flaky failures.
        if (firstSalt.equals(secondSalt)) {
            secondHash = Md5Crypt.apr1Crypt(password);
            secondSalt = extractSalt(secondHash);
        }

        assertNotEquals(firstSalt, secondSalt,
                () -> String.format("Two hashes for the same password should have different salts, but both were \"%s\".", firstSalt));
    }

    /**
     * Helper that extracts the salt part from an APR‑1 hash.
     *
     * @param hash the full hash string
     * @return the salt (the characters between the first two '$')
     */
    private static String extractSalt(String hash) {
        Matcher m = SALT_EXTRACTOR.matcher(hash);
        assertTrue(m.matches(), "Hash does not match expected APR‑1 format: " + hash);
        return m.group(1);
    }
}
```