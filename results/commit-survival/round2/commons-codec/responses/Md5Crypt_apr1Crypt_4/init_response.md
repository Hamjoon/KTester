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
class Md5Crypt_apr1Crypt_4_Test {

    /** Fixed seed used for deterministic Random instances */
    private static final long RANDOM_SEED = 0x5a5a5a5aL;

    /** Sample password used in the tests */
    private static final byte[] SAMPLE_KEY = "secretPassword".getBytes(StandardCharsets.UTF_8);

    /** Regular expression that a valid APR1 hash must match */
    private static final Pattern APR1_PATTERN = Pattern.compile("^\\$apr1\\$[a-zA-Z0-9./]{0,8}\\$.+$");

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources to initialise for these tests.
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
     * Verify that {@code apr1Crypt(byte[], Random)} produces the same result as the
     * overload that receives an explicit salt when both are fed with a deterministic
     * {@link Random} instance.
     */
    @Test
    void testApr1CryptWithDeterministicRandomProducesExpectedResult() {
        // Create two Random instances with the same seed – one for generating the expected salt,
        // the other to be passed to the method under test.
        Random saltGenerator = new Random(RANDOM_SEED);
        Random methodRandom = new Random(RANDOM_SEED);

        // Generate the salt exactly as the implementation does.
        String randomSalt = B64.getRandomSalt(8, saltGenerator);
        String expected = Md5Crypt.apr1Crypt(SAMPLE_KEY, Md5Crypt.APR1_PREFIX + randomSalt);

        // Call the method under test.
        String actual = Md5Crypt.apr1Crypt(SAMPLE_KEY, methodRandom);

        assertEquals(expected, actual,
                "apr1Crypt(byte[], Random) should yield the same hash as the explicit‑salt overload");
    }

    /**
     * The returned hash must start with the APR1 prefix and conform to the expected format.
     */
    @Test
    void testApr1CryptResultMatchesPattern() {
        Random rnd = new Random(RANDOM_SEED);
        String hash = Md5Crypt.apr1Crypt(SAMPLE_KEY, rnd);
        assertNotNull(hash, "Hash should never be null");
        assertTrue(APR1_PATTERN.matcher(hash).matches(),
                () -> "Hash does not match APR1 pattern: " + hash);
    }

    /**
     * Passing a {@code null} password array must result in a {@link NullPointerException}.
     */
    @Test
    void testApr1CryptNullKeyThrowsException() {
        Random rnd = new Random(RANDOM_SEED);
        assertThrows(NullPointerException.class,
                () -> Md5Crypt.apr1Crypt((byte[]) null, rnd),
                "Calling apr1Crypt with a null key should throw NullPointerException");
    }

    /**
     * Verify that the method works with an empty password (zero‑length byte array).
     */
    @Test
    void testApr1CryptEmptyKey() {
        Random rnd = new Random(RANDOM_SEED);
        byte[] empty = new byte[0];
        String hash = Md5Crypt.apr1Crypt(empty, rnd);
        assertNotNull(hash);
        assertTrue(APR1_PATTERN.matcher(hash).matches(),
                "Hash for empty key should still match APR1 pattern");
    }
}
```