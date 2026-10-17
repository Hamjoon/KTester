```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Sha2Crypt_sha512Crypt_2_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources needed for Sha2Crypt
    }

    @BeforeEach
    void setupBeforeEach() {
        // Nothing to initialise before each test
    }

    @AfterEach
    void teardownAfterEach() {
        // Nothing to clean up after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global resources to release
    }

    /**
     * Verify that calling {@code sha512Crypt} with a {@code null} salt generates a
     * result that starts with the SHA‑512 prefix ($6$) and follows the expected
     * crypt format (three '$' separators).
     */
    @Test
    void testSha512CryptWithNullSalt() {
        byte[] key = "password".getBytes(StandardCharsets.UTF_8);
        String result = Sha2Crypt.sha512Crypt(key); // salt == null

        assertNotNull(result, "Result must not be null");
        assertTrue(result.startsWith(Sha2Crypt.SHA512_PREFIX),
                "Result should start with the SHA‑512 prefix \"$6$\"");

        // The crypt string format is $6$salt$hash → exactly three '$' characters
        long dollarCount = result.chars().filter(ch -> ch == '$').count();
        assertEquals(3, dollarCount,
                "Result should contain exactly three '$' separators");
    }

    /**
     * Verify that providing an explicit salt yields a result that embeds that
     * salt (including the prefix) at the beginning of the returned string.
     */
    @Test
    void testSha512CryptWithGivenSalt() {
        byte[] key = "password".getBytes(StandardCharsets.UTF_8);
        String explicitSalt = Sha2Crypt.SHA512_PREFIX + "testsalt"; // e.g. "$6$testsalt"
        String result = Sha2Crypt.sha512Crypt(key, explicitSalt);

        assertNotNull(result, "Result must not be null");
        assertTrue(result.startsWith(explicitSalt),
                "Result should start with the supplied salt");

        // Ensure the format still contains exactly three '$' separators
        long dollarCount = result.chars().filter(ch -> ch == '$').count();
        assertEquals(3, dollarCount,
                "Result should contain exactly three '$' separators");
    }
}
```