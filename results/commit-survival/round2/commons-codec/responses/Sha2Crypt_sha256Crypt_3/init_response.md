```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Sha2Crypt_sha256Crypt_3_Test {

    /** Random instance used for the tests – a SecureRandom gives deterministic behaviour
     *  for the purpose of these unit‑tests (the actual random values are not verified). */
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
}
```