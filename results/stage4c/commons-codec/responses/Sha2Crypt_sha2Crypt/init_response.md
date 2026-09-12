```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Random;
import java.security.MessageDigest;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class Sha2Crypt_sha2Crypt_Test {

    private static final int SHA256_BLOCKSIZE = 32;
    private static final int SHA512_BLOCKSIZE = 64;

    @Mock
    private Random random;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize any static resources if necessary
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize necessary fields and dependencies before each test
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Cleanup any static resources if necessary
    }

    @Test
    void testSha256CryptWithSalt() {
        byte[] keyBytes = "password".getBytes();
        String salt = "$5$rounds=5000$saltstring";

        String result = Sha2Crypt.sha256Crypt(keyBytes, salt);

        assertNotNull(result);
        assertTrue(result.startsWith("$5$"));
    }

    @Test
    void testSha256CryptWithNullSalt() {
        byte[] keyBytes = "password".getBytes();
        String salt = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Sha2Crypt.sha256Crypt(keyBytes, salt);
        });

        assertEquals("Salt must not be null", exception.getMessage());
    }

    @Test
    void testSha512CryptWithSalt() {
        byte[] keyBytes = "password".getBytes();
        String salt = "$6$rounds=5000$saltstring";

        String result = Sha2Crypt.sha512Crypt(keyBytes, salt);

        assertNotNull(result);
        assertTrue(result.startsWith("$6$"));
    }

    @Test
    void testSha512CryptWithNullSalt() {
        byte[] keyBytes = "password".getBytes();
        String salt = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Sha2Crypt.sha512Crypt(keyBytes, salt);
        });

        assertEquals("Salt must not be null", exception.getMessage());
    }
}
```