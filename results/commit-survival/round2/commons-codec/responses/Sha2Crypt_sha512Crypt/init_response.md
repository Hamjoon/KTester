```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;

/**
 * Unit tests for {@link Sha2Crypt#sha512Crypt(byte[])}.
 *
 * <p>
 * The tests focus on the public contract of the method:
 * <ul>
 *   <li>the returned hash must not be {@code null}</li>
 *   <li>the returned hash must start with the SHA‑512 prefix {@code "$6$"}</li>
 *   <li>different inputs produce different hashes</li>
 *   <li>the method works when the input byte array is empty</li>
 * </ul>
 * No private members or implementation details are accessed.
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class Sha2Crypt_sha512Crypt_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources to initialise.
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

    @Test
    void sha512Crypt_withNonNullKey_returnsValidHash() {
        // given
        byte[] key = "password".getBytes(StandardCharsets.UTF_8);

        // when
        String hash = Sha2Crypt.sha512Crypt(key);

        // then
        assertNotNull(hash, "Hash must not be null");
        assertTrue(hash.startsWith(Sha2Crypt.SHA512_PREFIX),
                "Hash must start with the SHA‑512 prefix \"$6$\"");
        // Basic sanity check – a SHA‑512 crypt string contains at least 2 '$' delimiters
        assertEquals(3, hash.chars().filter(ch -> ch == '$').count(),
                "Hash should contain exactly three '$' characters");
    }

    @Test
    void sha512Crypt_withEmptyKey_returnsValidHash() {
        // given
        byte[] emptyKey = new byte[0];

        // when
        String hash = Sha2Crypt.sha512Crypt(emptyKey);

        // then
        assertNotNull(hash);
        assertTrue(hash.startsWith(Sha2Crypt.SHA512_PREFIX));
    }

    @Test
    void sha512Crypt_sameKeyDifferentCallsProduceDifferentSalts() {
        // given
        byte[] key = "samePassword".getBytes(StandardCharsets.UTF_8);

        // when
        String hash1 = Sha2Crypt.sha512Crypt(key);
        String hash2 = Sha2Crypt.sha512Crypt(key);

        // then
        assertNotNull(hash1);
        assertNotNull(hash2);
        assertNotEquals(hash1, hash2,
                "Two invocations without an explicit salt should generate different hashes because a random salt is used each time");
    }

    @Test
    void sha512Crypt_withExplicitSalt_isDeterministic() {
        // given
        byte[] key = "deterministic".getBytes(StandardCharsets.UTF_8);
        String explicitSalt = "$6$rounds=5000$abcdefghijklmnop$"; // a valid SHA‑512 salt format

        // when
        String hash1 = Sha2Crypt.sha512Crypt(key, explicitSalt);
        String hash2 = Sha2Crypt.sha512Crypt(key, explicitSalt);

        // then
        assertEquals(hash1, hash2, "Using the same explicit salt must yield identical hashes");
        assertTrue(hash1.startsWith(Sha2Crypt.SHA512_PREFIX));
    }
}
```