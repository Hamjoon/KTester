```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Md5Crypt_apr1Crypt_5_Test {

    /** Pattern that matches a valid APR1‑crypt string. */
    private static final Pattern APR1_PATTERN =
            Pattern.compile("^\\$apr1\\$[a-zA-Z0-9./]{0,8}\\$.+$");

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources needed for these tests.
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
     * When the supplied salt is {@code null} the method should delegate to
     * {@link Md5Crypt#md5Crypt(byte[], String, String)} with a {@code null}
     * salt value.  The resulting hash must start with the APR1 prefix.
     */
    @Test
    void apr1Crypt_nullSalt_startsWithApr1Prefix() {
        byte[] key = "secret".getBytes(StandardCharsets.UTF_8);
        String result = Md5Crypt.apr1Crypt(key, null);
        assertNotNull(result);
        assertTrue(APR1_PATTERN.matcher(result).matches(),
                "Result must match APR1 pattern, got: " + result);
    }

    /**
     * When the supplied salt does not start with {@code $apr1$} the method must
     * prepend the prefix before delegating to {@code md5Crypt}. The final
     * hash must be identical to calling {@code md5Crypt} directly with the
     * prefixed salt.
     */
    @Test
    void apr1Crypt_saltWithoutPrefix_prependsPrefix() {
        byte[] key = "password".getBytes(StandardCharsets.UTF_8);
        String rawSalt = "mysalt";                     // no "$apr1$" prefix
        String expected = Md5Crypt.md5Crypt(key,
                Md5Crypt.APR1_PREFIX + rawSalt,
                Md5Crypt.APR1_PREFIX);
        String actual = Md5Crypt.apr1Crypt(key, rawSalt);
        assertEquals(expected, actual,
                "apr1Crypt should prepend APR1 prefix when missing");
    }

    /**
     * When the supplied salt already contains the APR1 prefix the method must
     * forward it unchanged. The result must be identical to a direct call to
     * {@code md5Crypt} with the same arguments.
     */
    @Test
    void apr1Crypt_saltWithPrefix_isUsedAsIs() {
        byte[] key = "another".getBytes(StandardCharsets.UTF_8);
        String salted = Md5Crypt.APR1_PREFIX + "abc123";
        String expected = Md5Crypt.md5Crypt(key, salted, Md5Crypt.APR1_PREFIX);
        String actual = Md5Crypt.apr1Crypt(key, salted);
        assertEquals(expected, actual,
                "apr1Crypt must not double‑prepend the APR1 prefix");
    }

    /**
     * An empty salt string is illegal for the underlying {@code md5Crypt}
     * implementation and should result in an {@link IllegalArgumentException}.
     */
    @Test
    void apr1Crypt_emptySalt_throwsIllegalArgumentException() {
        byte[] key = "test".getBytes(StandardCharsets.UTF_8);
        assertThrows(IllegalArgumentException.class,
                () -> Md5Crypt.apr1Crypt(key, ""),
                "Empty salt should cause IllegalArgumentException");
    }

    /**
     * Verify that the overload which generates a random salt works and still
     * produces a valid APR1‑crypt string.
     */
    @Test
    void apr1Crypt_randomSalt_producesValidHash() {
        byte[] key = "random".getBytes(StandardCharsets.UTF_8);
        // Use a deterministic Random to make the test repeatable.
        Random deterministic = new Random(0L);
        String result = Md5Crypt.apr1Crypt(key, deterministic);
        assertTrue(APR1_PATTERN.matcher(result).matches(),
                "Result with random salt must match APR1 pattern");
    }
}
```