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
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class Sha2Crypt_sha2Crypt_Test {

    private static final String VALID_SALT = "$5$rounds=5000$abcdefghijklmnopqrstuv$";

    private static final byte[] VALID_KEY = "password".getBytes();

    private static final String SHA256_PREFIX = "$5$";

    private static final String SHA512_PREFIX = "$6$";

    private static final int SHA256_BLOCKSIZE = 32;

    private static final int SHA512_BLOCKSIZE = 64;

    private String invokeSha2Crypt(byte[] key, String salt, String prefix, int blockSize, String algorithm) throws Exception {
        Method method = Sha2Crypt.class.getDeclaredMethod("sha2Crypt", byte[].class, String.class, String.class, int.class, String.class);
        // Make the private method accessible
        method.setAccessible(true);
        return (String) method.invoke(null, key, salt, prefix, blockSize, algorithm);
    }

    @Test
    public void testSha2Crypt_ValidInput_SHA256() {
        String result = invokeSha2Crypt(VALID_KEY, VALID_SALT, SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidInput_SHA512() {
        String result = invokeSha2Crypt(VALID_KEY, VALID_SALT, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_NullSalt_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invokeSha2Crypt(VALID_KEY, null, SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        });
        assertEquals("Salt must not be null", exception.getMessage());
    }

    @Test
    public void testSha2Crypt_InvalidSalt_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invokeSha2Crypt(VALID_KEY, "invalid_salt", SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        });
        assertTrue(exception.getMessage().contains("Invalid salt value"));
    }

    @Test
    public void testSha2Crypt_InvalidSaltFormat_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invokeSha2Crypt(VALID_KEY, "invalidSaltFormat", SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-512");
        });
        assertTrue(exception.getMessage().contains("Invalid salt value"));
    }

    @Test
    public void testSha2Crypt_ValidSaltWithCustomRounds() {
        String salt = "$6$rounds=1500$validSalt";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithDefaultRounds() {
        String salt = "$5$validSalt";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_RoundsExceedingMaximum() {
        String salt = "$6$rounds=1000000000$validSalt";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_RoundsBelowMinimum() {
        String salt = "$6$rounds=500$validSalt";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithDifferentBlockSizes() {
        String salt = "$5$validSalt";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA512_PREFIX, 64, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithMinimumRounds() {
        String salt = "$6$rounds=1000$validSalt";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithMaximumRounds() {
        String salt = "$5$rounds=999999999$validSalt";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithEdgeCaseBlockSize() {
        String salt = "$6$validSalt";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA512_PREFIX, 1, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_ValidSaltWithDifferentAlgorithms() {
        String salt = "$5$validSalt";
        String result1 = invokeSha2Crypt(VALID_KEY, salt, SHA256_PREFIX, SHA256_BLOCKSIZE, "SHA-256");
        assertNotNull(result1);
        String result2 = invokeSha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA256_BLOCKSIZE, "SHA-1");
        assertNotNull(result2);
    }

    @Test
    public void testSha2Crypt_EmptySalt() {
        String salt = "$5$$";
        String result = invokeSha2Crypt(VALID_KEY, salt, SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        assertNotNull(result);
    }

    @Test
    public void testSha2Crypt_InvalidSaltWithCustomRounds() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            invokeSha2Crypt(VALID_KEY, "$6$rounds=abc$validSalt", SHA512_PREFIX, SHA512_BLOCKSIZE, "SHA-512");
        });
        assertTrue(exception.getMessage().contains("Invalid salt value"));
    }
}
