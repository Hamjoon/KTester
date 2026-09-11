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
    private static final String SALT = "$5$rounds=5000$abcdefgh";
    private static final byte[] KEY_BYTES = "password".getBytes();
    private static final String SHA256_PREFIX = "$5$";
    private static final String SHA512_PREFIX = "$6$";
    private static final int SHA256_BLOCKSIZE = 32;
    private static final int SHA512_BLOCKSIZE = 64;

    @BeforeAll
    static void setupBeforeAll() {
        // Any required setup before all tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // Any required setup before each test
    }

    @AfterEach
    void teardownAfterEach() {
        // Any required cleanup after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any required cleanup after all tests
    }

    @Test
    void testSha256CryptWithSalt() {
        String result = Sha2Crypt.sha256Crypt(KEY_BYTES, SALT);
        assertNotNull(result);
        // Additional assertions can be made based on expected output
    }

    @Test
    void testSha256CryptWithSaltAndRandom() {
        Random random = new Random();
        String result = Sha2Crypt.sha256Crypt(KEY_BYTES, SALT, random);
        assertNotNull(result);
        // Additional assertions can be made based on expected output
    }

    @Test
    void testSha512CryptWithSalt() {
        String result = Sha2Crypt.sha512Crypt(KEY_BYTES, SALT);
        assertNotNull(result);
        // Additional assertions can be made based on expected output
    }
}
```