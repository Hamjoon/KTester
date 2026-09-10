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
        // Test case to check behavior when salt is null, expecting an IllegalArgumentException.
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

    @Test
    public void testSha2Crypt_InvalidSaltFormat_ThrowsException() {
        // Test case to check behavior when salt format is invalid, expecting an IllegalArgumentException.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Sha2Crypt.sha2Crypt(VALID_KEY, "invalidSaltFormat", SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-512");
        });
        assertTrue(exception.getMessage().contains("Invalid salt value"));
    }

    @Test
    public void testSha2Crypt_ValidSaltWithCustomRounds() {
        // Test case to check behavior with valid salt and custom rounds, expecting a non-null string.
        String salt = "$6$rounds=1500$validSalt";
        String result = Sha2Crypt.sha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithDefaultRounds() {
        // Test case to check behavior with valid salt and default rounds, expecting a non-null string.
        String salt = "$5$validSalt";
        String result = Sha2Crypt.sha2Crypt(VALID_KEY, salt, SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_RoundsExceedingMaximum() {
        // Test case to check behavior with rounds exceeding maximum, expecting rounds to be capped at ROUNDS_MAX.
        String salt = "$6$rounds=1000000000$validSalt";
        String result = Sha2Crypt.sha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
        // Further assertions can be added to verify the expected output
    }

    @Test
    public void testSha2Crypt_RoundsBelowMinimum() {
        // Test case to check behavior with rounds below minimum, expecting rounds to be set to ROUNDS_MIN.
        String salt = "$6$rounds=500$validSalt";
        String result = Sha2Crypt.sha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
        // Further assertions can be added to verify the expected output
    }

    @Test
    public void testSha2Crypt_ValidSaltWithDifferentBlockSizes() {
        // Test case to check behavior with valid salt and a different block size, expecting a non-null string.
        String salt = "$5$validSalt";
        String result = Sha2Crypt.sha2Crypt(VALID_KEY, salt, SHA512_PREFIX, 64, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithMinimumRounds() {
        // Test case to check behavior with valid salt and minimum rounds (1000), expecting a non-null string.
        String salt = "$6$rounds=1000$validSalt";
        String result = Sha2Crypt.sha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithMaximumRounds() {
        // Test case to check behavior with valid salt and maximum rounds (999999999), expecting a non-null string.
        String salt = "$5$rounds=999999999$validSalt";
        String result = Sha2Crypt.sha2Crypt(VALID_KEY, salt, SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithEdgeCaseBlockSize() {
        // Test case to check behavior with valid salt and a block size of 1, expecting a non-null string.
        String salt = "$6$validSalt";
        String result = Sha2Crypt.sha2Crypt(VALID_KEY, salt, SHA512_PREFIX, 1, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithDifferentAlgorithms() {
        // Test case to check behavior with valid salt using SHA-256 algorithm, expecting a non-null string.
        String salt = "$5$validSalt";
        String result1 = Sha2Crypt.sha2Crypt(VALID_KEY, salt, SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        assertNotNull(result1);
        // Test case to check behavior with valid salt using SHA-1 algorithm, expecting a non-null string.
        String result2 = Sha2Crypt.sha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA256_BLOCKSIZE, "SHA-1");
        assertNotNull(result2);
    }

    @Test
    public void testSha2Crypt_EmptySalt() {
        // Test case to check behavior with valid salt containing an empty salt string, expecting a non-null string.
        String salt = "$5$$";
        String result = Sha2Crypt.sha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_InvalidSaltWithCustomRounds() {
        // Test case to check behavior with invalid salt format for custom rounds, expecting an IllegalArgumentException.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Sha2Crypt.sha2Crypt(VALID_KEY, "$6$rounds=abc$validSalt", SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        });
        assertTrue(exception.getMessage().contains("Invalid salt value"));
    }
}
