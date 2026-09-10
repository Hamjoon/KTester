Based on the provided information and the structure of the existing test class, here's the completed test class template for testing the `sha2Crypt` method in the `Sha2Crypt` class. The test cases will focus on the behavior of the method, especially with valid and invalid inputs.

```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@ExtendWith(MockitoExtension.class)
class Sha2Crypt_sha2Crypt_Test {
    
    private static final String VALID_SALT = "$5$rounds=5000$abcdefghijklmnopqrstuv$";
    private static final byte[] VALID_KEY = "password".getBytes();
    private static final String SHA256_PREFIX = "$5$";
    private static final String SHA512_PREFIX = "$6$";
    private static final int SHA256_BLOCKSIZE = 32;
    private static final int SHA512_BLOCKSIZE = 64;

    @BeforeAll
    static void setupBeforeAll() {
        // Any setup needed before all tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // Any setup needed before each test
    }

    @AfterEach
    void teardownAfterEach() {
        // Any cleanup needed after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any cleanup needed after all tests
    }

    @Test
    public void testSha2Crypt_ValidInput_SHA256() {
        String result = Sha2Crypt.sha2Crypt(VALID_KEY, VALID_SALT, SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        assertNotNull(result);
        // Additional assertions can be added to verify the expected output
    }

    @Test
    public void testSha2Crypt_ValidInput_SHA512() {
        String result = Sha2Crypt.sha2Crypt(VALID_KEY, VALID_SALT, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
        // Additional assertions can be added to verify the expected output
    }

    @Test
    public void testSha2Crypt_NullSalt_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Sha2Crypt.sha2Crypt(VALID_KEY, null, SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        });
        assertEquals("Salt must not be null", exception.getMessage());
    }

    @Test
    public void testSha2Crypt_InvalidSalt_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Sha2Crypt.sha2Crypt(VALID_KEY, "invalid_salt", SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        });
        assertTrue(exception.getMessage().contains("Invalid salt value"));
    }
}
```

### Explanation of the Test Class:
1. **Imports**: The necessary libraries are imported for testing.
2. **Constants**: Constants for valid inputs are defined for use in tests.
3. **Setup and Teardown Methods**: 
   - `setupBeforeAll` and `teardownAfterAll` can be used for any global setup or cleanup if needed.
   - `setupBeforeEach` and `teardownAfterEach` are left empty for now but can be used for per-test setup or cleanup.
4. **Test Methods**:
   - **Valid Input Tests**: Tests for valid inputs to ensure the method returns a non-null value.
   - **Exception Tests**: Tests to ensure that the method throws the appropriate exceptions for null or invalid salt values.

This structure allows for easy expansion with more test cases as needed.