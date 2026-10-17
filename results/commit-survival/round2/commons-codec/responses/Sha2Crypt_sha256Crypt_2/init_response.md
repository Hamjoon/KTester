```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link Sha2Crypt#sha256Crypt(byte[], String)}.
 *
 * <p>
 * The tests verify that:
 * <ul>
 *   <li>Providing a concrete salt yields a result that contains the same salt and the correct
 *       SHA‑256 prefix.</li>
 *   <li>Providing {@code null} as the salt generates a result that starts with the SHA‑256
 *       prefix and contains a randomly generated salt (the exact value cannot be predicted).</li>
 * </ul>
 * No private members of {@link Sha2Crypt} are accessed.
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class Sha2Crypt_sha256Crypt_2_Test {

    /** Sample password used in the tests. */
    private static final byte[] PASSWORD = "password".getBytes(StandardCharsets.UTF_8);

    /** A deterministic salt used for the deterministic test case. */
    private static final String DETERMINISTIC_SALT = "$5$mysalt";

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
     * Verify that when a concrete salt is supplied the result contains that salt and the
     * expected SHA‑256 prefix.
     */
    @Test
    void testSha256Crypt_WithExplicitSalt() {
        // Act
        String result = Sha2Crypt.sha256Crypt(PASSWORD, DETERMINISTIC_SALT);

        // Assert
        assertNotNull(result, "Result must not be null");
        // The result must start with the SHA‑256 prefix "$5$"
        assertTrue(result.startsWith(Sha2Crypt.SHA256_PREFIX),
                "Result should start with the SHA‑256 prefix");
        // The salt we supplied (without the prefix) must appear after the prefix and before the next '$'
        // Expected format: $5$mysalt$<hash>
        String expectedPrefix = DETERMINISTIC_SALT + "$";
        assertTrue(result.startsWith(expectedPrefix),
                "Result should contain the supplied salt: expected prefix '" + expectedPrefix + "'");
        // The hash part must be non‑empty
        String[] parts = result.split("\\$");
        assertEquals(4, parts.length,
                "Result should have four '$'‑separated parts: prefix, salt, hash");
        assertFalse(parts[3].isEmpty(), "Hash part must not be empty");
    }

    /**
     * Verify that when {@code null} is passed as the salt the method generates a salt
     * and returns a properly formatted crypt string.
     */
    @Test
    void testSha256Crypt_WithNullSalt_GeneratesRandomSalt() {
        // Act
        String result = Sha2Crypt.sha256Crypt(PASSWORD, null);

        // Assert
        assertNotNull(result, "Result must not be null");
        // Must start with the SHA‑256 prefix
        assertTrue(result.startsWith(Sha2Crypt.SHA256_PREFIX),
                "Result should start with the SHA‑256 prefix");
        // The result should contain three '$' characters (prefix, salt, hash)
        int dollarCount = result.length() - result.replace("$", "").length();
        assertEquals(3, dollarCount,
                "Result should contain exactly three '$' characters");
        // Extract the salt part (the part between the first and second '$')
        String[] parts = result.split("\\$");
        // parts[0] is empty because the string starts with '$'
        // parts[1] is the prefix "5"
        // parts[2] is the generated salt
        // parts[3] is the hash
        assertEquals(4, parts.length,
                "Result should have four '$'‑separated parts");
        String generatedSalt = parts[2];
        assertNotNull(generatedSalt, "Generated salt must not be null");
        assertFalse(generatedSalt.isEmpty(), "Generated salt must not be empty");
        // The hash part must also be non‑empty
        assertFalse(parts[3].isEmpty(), "Hash part must not be empty");
    }
}
```