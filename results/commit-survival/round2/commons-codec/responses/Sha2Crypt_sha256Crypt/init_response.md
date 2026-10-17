```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Sha2Crypt_sha256Crypt_Test {

    /** Sample password used for the tests. */
    private static byte[] passwordBytes;

    /** Regular expression that matches a valid SHA‑256 crypt string. */
    private static final Pattern SHA256_CRYPT_PATTERN =
            Pattern.compile("^\\$5\\$(?:[^$]{0,16})\\$[./0-9A-Za-z]{43}$");

    @BeforeAll
    static void setupBeforeAll() {
        // Initialise a deterministic password for all tests.
        passwordBytes = "SecretPassword".getBytes(StandardCharsets.UTF_8);
    }

    @BeforeEach
    void setupBeforeEach() {
        // No per‑test initialisation required at the moment.
    }

    @AfterEach
    void teardownAfterEach() {
        // No per‑test cleanup required at the moment.
    }

    @AfterAll
    static void teardownAfterAll() {
        // Release static resources if any (none here).
        passwordBytes = null;
    }

    @Test
    void sha256Crypt_withoutSalt_returnsValidHash() {
        // GIVEN a password byte array (already prepared in @BeforeAll)

        // WHEN the SHA‑256 crypt method is invoked without an explicit salt
        String result = Sha2Crypt.sha256Crypt(passwordBytes);

        // THEN the result should be a non‑null string that follows the expected format
        assertNotNull(result, "Result must not be null");
        assertTrue(result.startsWith(Sha2Crypt.SHA256_PREFIX),
                "Result must start with the SHA‑256 prefix \"$5$\"");
        assertTrue(SHA256_CRYPT_PATTERN.matcher(result).matches(),
                "Result does not match the expected SHA‑256 crypt pattern: " + result);
    }

    @Test
    void sha256Crypt_withExplicitSalt_returnsHashContainingSalt() {
        // GIVEN a password and a deterministic salt value
        String salt = "mySalt123";

        // WHEN the SHA‑256 crypt method is invoked with the explicit salt
        String result = Sha2Crypt.sha256Crypt(passwordBytes, salt);

        // THEN the result should embed the supplied salt and be correctly formatted
        assertNotNull(result, "Result must not be null");
        assertTrue(result.startsWith(Sha2Crypt.SHA256_PREFIX + salt + "$"),
                "Result must contain the supplied salt after the prefix");
        assertTrue(SHA256_CRYPT_PATTERN.matcher(result).matches(),
                "Result does not match the expected SHA‑256 crypt pattern: " + result);
    }

    @Test
    void sha256Crypt_nullPassword_throwsNullPointerException() {
        // GIVEN a null password byte array
        byte[] nullPassword = null;

        // WHEN the method is invoked, THEN a NullPointerException is expected
        assertThrows(NullPointerException.class,
                () -> Sha2Crypt.sha256Crypt(nullPassword),
                "Calling sha256Crypt with a null password should throw NullPointerException");
    }
}
```