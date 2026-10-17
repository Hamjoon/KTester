```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Md5Crypt_apr1Crypt_3_Test {

    // ----------------------------------------------------------------------
    // Test data
    // ----------------------------------------------------------------------
    private static final String KEY = "secret";
    private static final String SALT = "abcd1234";
    private static final Pattern APR1_PATTERN = Pattern.compile("^\\$apr1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$");

    // ----------------------------------------------------------------------
    // Lifecycle hooks – kept empty because the class under test has no
    // external dependencies that need to be prepared or cleaned up.
    // ----------------------------------------------------------------------
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

    // ----------------------------------------------------------------------
    // Tests for Md5Crypt.apr1Crypt(String, String)
    // ----------------------------------------------------------------------

    @Test
    void apr1CryptWithValidSaltProducesExpectedPrefixAndPattern() {
        String result = Md5Crypt.apr1Crypt(KEY, SALT);
        assertNotNull(result, "Result must not be null");
        assertTrue(result.startsWith(Md5Crypt.APR1_PREFIX),
                "Result must start with the APR1 prefix");
        assertTrue(APR1_PATTERN.matcher(result).matches(),
                "Result must match the APR1 hash pattern");
    }

    @Test
    void apr1CryptStringOverloadMatchesByteOverload() {
        // The string overload should delegate to the byte overload and produce
        // exactly the same hash for the same salt.
        String fromString = Md5Crypt.apr1Crypt(KEY, SALT);
        byte[] keyBytes = KEY.getBytes(StandardCharsets.UTF_8);
        String fromBytes = Md5Crypt.apr1Crypt(keyBytes, SALT);
        assertEquals(fromString, fromBytes,
                "String and byte overloads must produce identical results");
    }

    @Test
    void apr1CryptWithNullSaltGeneratesRandomSaltButKeepsPattern() {
        // When salt is null the implementation generates a random salt.
        String result = Md5Crypt.apr1Crypt(KEY, null);
        assertNotNull(result);
        assertTrue(APR1_PATTERN.matcher(result).matches(),
                "Result with null salt must still match the APR1 pattern");
    }

    @Test
    void apr1CryptWithEmptySaltThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> Md5Crypt.apr1Crypt(KEY, ""),
                "Empty salt should trigger IllegalArgumentException");
    }

    @Test
    void apr1CryptWithNullKeyThrowsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> Md5Crypt.apr1Crypt((String) null, SALT),
                "Null key string should trigger NullPointerException");
    }

    @Test
    void apr1CryptZeroesInputByteArray() {
        // Verify that the byte‑array overload zeroes the password buffer.
        byte[] buffer = new byte[200];
        Arrays.fill(buffer, (byte) 'A');
        Md5Crypt.apr1Crypt(buffer, SALT);
        assertArrayEquals(new byte[buffer.length], buffer,
                "Input buffer must be zeroed after apr1Crypt execution");
    }
}
```