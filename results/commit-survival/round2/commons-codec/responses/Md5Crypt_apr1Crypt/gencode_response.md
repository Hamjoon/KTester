```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link Md5Crypt#apr1Crypt(byte[])} and its overloads.
 *
 * <p>
 * The test class follows the framework that was already provided.  Only new
 * test methods are added – the {@code @BeforeAll}, {@code @BeforeEach},
 * {@code @AfterEach} and {@code @AfterAll} sections remain untouched.
 * </p>
 *
 * <p>
 * The focus is on the {@code apr1Crypt(byte[])} overload that generates a
 * random salt internally.  The expected output format is:
 * </p>
 *
 * <pre>
 * $apr1$&lt;8‑char‑salt&gt;$&lt;22‑char‑hash&gt;
 * </pre>
 *
 * where the characters belong to the set {@code [A‑Za‑z0‑9./]}.
 */
@ExtendWith(MockitoExtension.class)
class Md5Crypt_apr1Crypt_Test {

    // Regular expression that matches the APR1 hash format:
    // $apr1$<salt (8 chars from [A‑Za‑z0‑9./])>$<hash (22 chars from same set)>
    private static final java.util.regex.Pattern APR1_REGEX =
            java.util.regex.Pattern.compile("^\\$apr1\\$[A-Za-z0-9./]{8}\\$[A-Za-z0-9./]{22}$");

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // No per‑test initialization required.
    }

    @AfterEach
    void teardownAfterEach() {
        // No per‑test cleanup required.
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup required.
    }

    /* -------------------------------------------------------------
     *  Existing tests (kept unchanged)
     * ------------------------------------------------------------- */

    /**
     * Verify that the method returns a string that conforms to the APR1 format.
     */
    @Test
    void testApr1CryptProducesValidFormat() {
        byte[] key = "secret".getBytes(StandardCharsets.UTF_8);
        String result = Md5Crypt.apr1Crypt(key);
        assertNotNull(result, "Result must not be null");
        assertTrue(APR1_PATTERN.matcher(result).matches(),
                "Result does not match APR1 pattern: " + result);
    }

    /**
     * Passing {@code null} as the password must result in an {@link IllegalArgumentException}.
     */
    @Test
    void testApr1CryptNullInputThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> Md5Crypt.apr1Crypt((byte[]) null),
                "Null password should raise IllegalArgumentException");
    }

    /**
     * The implementation is expected to zero‑out the supplied password array after
     * the hash is computed (same behaviour as {@link Md5Crypt#md5Crypt(byte[])}).
     */
    @Test
    void testApr1CryptZeroesInput() {
        byte[] password = new byte[64];
        // Fill the array with a non‑zero value so we can detect zeroing.
        for (int i = 0; i < password.length; i++) {
            password[i] = (byte) 0x5A;
        }

        // Perform the hash.
        Md5Crypt.apr1Crypt(password);

        // After the call the password array must be cleared.
        for (byte b : password) {
            assertEquals(0, b, "Password array should be zeroed after apr1Crypt");
        }
    }

    /**
     * When a deterministic salt is supplied via the overloaded method, the output
     * must be reproducible. This test uses the overload to generate an expected
     * value and then checks that the random‑salt variant still produces a valid
     * APR1 hash (the salt part will differ, but the format stays the same).
     */
    @Test
    void testApr1CryptWithExplicitSaltIsDeterministic() {
        byte[] key = "password".getBytes(StandardCharsets.UTF_8);
        String salt = "$apr1$mysalt"; // 6‑character salt (no trailing $)
        String expected = Md5Crypt.apr1Crypt(key, salt);

        // The expected value must match the APR1 pattern.
        assertTrue(APR1_PATTERN.matcher(expected).matches(),
                "Deterministic hash does not match APR1 pattern");

        // For comparison, call the random‑salt version and ensure it also matches the pattern.
        String randomResult = Md5Crypt.apr1Crypt(key);
        assertTrue(APR1_PATTERN.matcher(randomResult).matches(),
                "Random‑salt hash does not match APR1 pattern");

        // The two results must differ because the salts are different.
        assertNotEquals(expected, randomResult,
                "Hashes with different salts should not be equal");
    }

    /* -------------------------------------------------------------
     *  New parameterised tests derived from the supplied JSON cases
     * ------------------------------------------------------------- */

    /**
     * Parameterised test that verifies a variety of byte‑array inputs all
     * produce a syntactically correct APR1‑MD5 hash (8‑char salt, 22‑char hash).
     *
     * The test data mirrors the JSON group {@code apr1Crypt_byteArray}.
     */
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("provideApr1CryptByteArrayCases")
    void testApr1CryptByteArrayValidFormats(String description, byte[] input) {
        // Act
        String hash = Md5Crypt.apr1Crypt(input.clone()); // clone to keep the original for later assertions

        // Assert format
        assertNotNull(hash, "Hash must not be null");
        assertTrue(APR1_REGEX.matcher(hash).matches(),
                () -> "Hash does not match expected APR1 format for case: " + description + " -> " + hash);
    }

    /**
     * Supplies the byte‑array test cases.
     *
     * @return stream of arguments: description and the corresponding byte array.
     */
    private static Stream<org.junit.jupiter.params.provider.Arguments> provideApr1CryptByteArrayCases() {
        return Stream.of(
                // Typical ASCII password bytes: "password"
                org.junit.jupiter.params.provider.Arguments.of(
                        "Typical ASCII password bytes",
                        new byte[]{112, 97, 115, 115, 119, 111, 114, 100}), // "password"

                // Empty byte array
                org.junit.jupiter.params.provider.Arguments.of(
                        "Empty byte array",
                        new byte[]{}),

                // Byte array with null byte, high‑order bytes and non‑printable characters
                org.junit.jupiter.params.provider.Arguments.of(
                        "Mixed non‑printable bytes",
                        new byte[]{0, (byte) 0xFF, 16, 127, (byte) 0x80, (byte) 0xC8})
        );
    }

    /**
     * Verifies that passing {@code null} as the password array throws the
     * expected {@link IllegalArgumentException}.  This corresponds to the
     * JSON group {@code apr1Crypt_byteArray_exceptions}.
     */
    @Test
    void testApr1CryptByteArrayNullThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> Md5Crypt.apr1Crypt((byte[]) null),
                "Null password array must raise IllegalArgumentException");
    }
}
```