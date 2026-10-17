```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive tests for {@link Sha2Crypt#sha256Crypt(byte[], String)}.
 *
 * <p>
 * The tests cover:
 * <ul>
 *   <li>Correct handling of {@code null} salts (random generation).</li>
 *   <li>Explicit salts (plain, with rounds, without prefix).</li>
 *   <li>Boundary conditions such as empty passwords, large inputs, Unicode characters.</li>
 *   <li>Validation of malformed salts – expecting {@link IllegalArgumentException} or {@link NullPointerException}.</li>
 * </ul>
 * The test class re‑uses the existing {@code @BeforeAll/@BeforeEach/@AfterEach/@AfterAll}
 * methods and adds only new test methods.
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

    /* --------------------------------------------------------------------- */
    /* Existing tests – kept unchanged                                         */
    /* --------------------------------------------------------------------- */

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

    /* --------------------------------------------------------------------- */
    /* New parameterised tests                                                */
    /* --------------------------------------------------------------------- */

    /* ---------------------- Null‑salt handling --------------------------- */

    private static Stream<Arguments> nullSaltProvider() {
        return Stream.of(
                Arguments.of("password".getBytes(StandardCharsets.UTF_8), null,
                        "^\\$5\\$[A-Za-z0-9./]{8}\\$[A-Za-z0-9./]{43}$"),
                Arguments.of("".getBytes(StandardCharsets.UTF_8), null,
                        "^\\$5\\$[A-Za-z0-9./]{8}\\$[A-Za-z0-9./]{43}$")
        );
    }

    @ParameterizedTest(name = "[{index}] null‑salt with password length={0}.length")
    @MethodSource("nullSaltProvider")
    void testSha256Crypt_NullSalt_GeneratesValidFormat(byte[] key, String salt, String regex) {
        String result = Sha2Crypt.sha256Crypt(key, salt);
        assertNotNull(result, "Result must not be null");
        assertTrue(Pattern.matches(regex, result),
                () -> "Result does not match expected pattern: " + regex + " -> " + result);
    }

    /* ---------------------- Custom valid salt --------------------------- */

    private static Stream<Arguments> customSaltProvider() {
        return Stream.of(
                // plain salt without trailing '$' – the method will add it
                Arguments.of("password".getBytes(StandardCharsets.UTF_8),
                        "$5$mysalt$", "^\\$5\\$mysalt\\$[A-Za-z0-9./]{43}$")
        );
    }

    @ParameterizedTest(name = "[{index}] custom salt \"{1}\"")
    @MethodSource("customSaltProvider")
    void testSha256Crypt_CustomValidSalt(byte[] key, String salt, String regex) {
        String result = Sha2Crypt.sha256Crypt(key, salt);
        assertNotNull(result);
        assertTrue(Pattern.matches(regex, result),
                () -> "Result does not match expected pattern: " + regex + " -> " + result);
    }

    /* ---------------------- Custom salt with rounds ---------------------- */

    private static Stream<Arguments> customSaltRoundsProvider() {
        return Stream.of(
                Arguments.of("password".getBytes(StandardCharsets.UTF_8),
                        "$5$rounds=12000$mysalt$", "^\\$5\\$rounds=12000\\$mysalt\\$[A-Za-z0-9./]{43}$"),
                Arguments.of("password".getBytes(StandardCharsets.UTF_8),
                        "$5$rounds=5000$mysalt$", "^\\$5\\$rounds=5000\\$mysalt\\$[A-Za-z0-9./]{43}$")
        );
    }

    @ParameterizedTest(name = "[{index}] rounds salt \"{1}\"")
    @MethodSource("customSaltRoundsProvider")
    void testSha256Crypt_CustomSaltWithRounds(byte[] key, String salt, String regex) {
        String result = Sha2Crypt.sha256Crypt(key, salt);
        assertNotNull(result);
        assertTrue(Pattern.matches(regex, result),
                () -> "Result does not match expected pattern: " + regex + " -> " + result);
    }

    /* ---------------------- Salt without prefix -------------------------- */

    private static Stream<Arguments> saltWithoutPrefixProvider() {
        return Stream.of(
                Arguments.of("password".getBytes(StandardCharsets.UTF_8),
                        "mysalt", "^\\$5\\$mysalt\\$[A-Za-z0-9./]{43}$")
        );
    }

    @ParameterizedTest(name = "[{index}] salt without prefix \"{1}\"")
    @MethodSource("saltWithoutPrefixProvider")
    void testSha256Crypt_SaltWithoutPrefix(byte[] key, String salt, String regex) {
        String result = Sha2Crypt.sha256Crypt(key, salt);
        assertNotNull(result);
        assertTrue(Pattern.matches(regex, result),
                () -> "Result does not match expected pattern: " + regex + " -> " + result);
    }

    /* ---------------------- Invalid salt characters ---------------------- */

    @Test
    void testSha256Crypt_InvalidSaltCharacters() {
        String badSalt = "$5$bad*salt$";
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> Sha2Crypt.sha256Crypt(PASSWORD, badSalt));
        assertTrue(ex.getMessage().toLowerCase().contains("invalid"), "Exception message should indicate invalid salt");
    }

    /* ---------------------- Excessively long salt ------------------------ */

    @Test
    void testSha256Crypt_ExcessivelyLongSalt() {
        String longSalt = "$5$abcdefghijklmnopqr$";
        assertThrows(IllegalArgumentException.class,
                () -> Sha2Crypt.sha256Crypt(PASSWORD, longSalt));
    }

    /* ---------------------- Empty salt string ---------------------------- */

    @Test
    void testSha256Crypt_EmptySaltString() {
        assertThrows(IllegalArgumentException.class,
                () -> Sha2Crypt.sha256Crypt(PASSWORD, ""));
    }

    /* ---------------------- Large keyBytes (1 MiB) ----------------------- */

    @Test
    void testSha256Crypt_LargeKeyBytes() {
        byte[] largeKey = new byte[1024 * 1024]; // zero‑filled 1 MiB array
        String salt = "$5$large$";
        String result = Sha2Crypt.sha256Crypt(largeKey, salt);
        assertNotNull(result);
        assertTrue(Pattern.matches("^\\$5\\$large\\$[A-Za-z0-9./]{43}$", result),
                "Result should match the expected pattern for large keyBytes");
    }

    /* ---------------------- Null keyBytes -------------------------------- */

    @Test
    void testSha256Crypt_NullKeyBytes() {
        assertThrows(NullPointerException.class,
                () -> Sha2Crypt.sha256Crypt(null, "$5$nullkey$"));
    }

    /* ---------------------- Unicode password ----------------------------- */

    @Test
    void testSha256Crypt_UnicodePassword() {
        byte[] unicodePwd = "pässwörd".getBytes(StandardCharsets.UTF_8);
        String salt = "$5$unicode$";
        String result = Sha2Crypt.sha256Crypt(unicodePwd, salt);
        assertNotNull(result);
        assertTrue(Pattern.matches("^\\$5\\$unicode\\$[A-Za-z0-9./]{43}$", result),
                "Unicode password should produce a correctly formatted hash");
    }

    /* ---------------------- Invalid rounds (non‑numeric) ----------------- */

    @Test
    void testSha256Crypt_InvalidRoundsNonNumeric() {
        String salt = "$5$rounds=abc$mysalt$";
        assertThrows(IllegalArgumentException.class,
                () -> Sha2Crypt.sha256Crypt(PASSWORD, salt));
    }

    /* ---------------------- Rounds below minimum ------------------------ */

    @Test
    void testSha256Crypt_RoundsBelowMinimum() {
        String salt = "$5$rounds=500$mysalt$";
        assertThrows(IllegalArgumentException.class,
                () -> Sha2Crypt.sha256Crypt(PASSWORD, salt));
    }

    /* ---------------------- Rounds above maximum ------------------------ */

    @Test
    void testSha256Crypt_RoundsAboveMaximum() {
        String salt = "$5$rounds=1000000000$mysalt$";
        assertThrows(IllegalArgumentException.class,
                () -> Sha2Crypt.sha256Crypt(PASSWORD, salt));
    }

    /* ---------------------- Missing trailing delimiter ------------------- */

    @Test
    void testSha256Crypt_MissingTrailingDelimiter() {
        String salt = "$5$mysalt";
        assertThrows(IllegalArgumentException.class,
                () -> Sha2Crypt.sha256Crypt(PASSWORD, salt));
    }

    /* ---------------------- Empty rounds value -------------------------- */

    @Test
    void testSha256Crypt_EmptyRoundsValue() {
        String salt = "$5$rounds=$mysalt$";
        assertThrows(IllegalArgumentException.class,
                () -> Sha2Crypt.sha256Crypt(PASSWORD, salt));
    }

    /* ---------------------- Whitespace in salt -------------------------- */

    @Test
    void testSha256Crypt_WhitespaceInSalt() {
        String salt = "$5$my salt$";
        assertThrows(IllegalArgumentException.class,
                () -> Sha2Crypt.sha256Crypt(PASSWORD, salt));
    }
}
```