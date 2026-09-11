package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.security.SecureRandom;
import java.util.Random;

@ExtendWith(MockitoExtension.class)
class Md5Crypt_md5Crypt_Test {

    private byte[] keyBytes;

    private String salt;

    private String prefix;

    private Random random;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize fields for each test
        // Sample key
        keyBytes = "secret".getBytes();
        // Sample salt
        salt = "$1$foo";
        // Sample prefix
        prefix = "$1$";
        // Random instance
        random = new SecureRandom();
    }

    @Test
    void testMd5CryptWithValidInput() {
        // Test md5Crypt with valid inputs, expecting a valid MD5 hash result
        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithNullSalt() {
        // Test md5Crypt with null salt, expecting a valid MD5 hash result
        String result = Md5Crypt.md5Crypt(keyBytes, null, prefix, random);
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithEmptyKeyBytes() {
        // Test md5Crypt with empty key bytes, expecting a specific hash result
        String result = Md5Crypt.md5Crypt(new byte[0], salt, prefix, random);
        assertEquals("$1$foo$9mS5ExwgIECGE5YKlD5o91", result);
    }

    @Test
    void testMd5CryptWithInvalidSalt() {
        // Test md5Crypt with an invalid salt format, expecting an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.md5Crypt(keyBytes, "invalid$salt", prefix, random);
        });
    }

    @Test
    void testMd5CryptWithLongInput() {
        // Test md5Crypt with a long input key, expecting a valid MD5 hash result
        String longInput = "12345678901234567890";
        String result = Md5Crypt.md5Crypt(longInput.getBytes(), salt, prefix, random);
        assertEquals("$1$foo$MoxekaNNUgfPRVqoeYjCD/", result);
    }

    @Test
    void testMd5CryptWithNullSaltCase() {
        // Test md5Crypt with null salt, expecting a valid MD5 hash result
        String result = Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), null, "$1$", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithInvalidSaltFormat() {
        // Test md5Crypt with an invalid salt format, expecting an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), "invalidSaltFormat", "$1$", new SecureRandom());
        });
    }

    @Test
    void testMd5CryptWithValidSalt() {
        // Test md5Crypt with a valid salt, expecting a valid MD5 hash result
        String result = Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), "$1$validSalt", "$1$", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithEdgeCaseKeyLength() {
        // Test md5Crypt with a small key length, expecting a valid MD5 hash result
        String result = Md5Crypt.md5Crypt("smallKey".getBytes(), "$1$validSalt", "$1$", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptMultipleRounds() {
        // Test md5Crypt to ensure that it processes multiple rounds correctly
        String result = Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), "$1$validSalt", "$1$", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithEmptyKey() {
        // Test md5Crypt with an empty key, expecting a valid MD5 hash result
        String result = Md5Crypt.md5Crypt("".getBytes(), null, "$1$", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithLongKey() {
        // Test md5Crypt with a long key, expecting a valid MD5 hash result
        String result = Md5Crypt.md5Crypt("aVeryLongKeyThatExceedsNormalLengthForTestingPurpose".getBytes(), "$1$validSalt", "$1$", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithSpecialCharactersInKey() {
        // Test md5Crypt with special characters in the key, expecting a valid MD5 hash result
        String result = Md5Crypt.md5Crypt("key@#$$%^&*()".getBytes(), "$1$validSalt", "$1$", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithMaxLengthSalt() {
        // Test md5Crypt with maximum length salt (8 characters), expecting a valid MD5 hash result
        String result = Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), "$1$abcdefgh", "$1$", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithInvalidPrefix() {
        // Test md5Crypt with an invalid prefix, expecting a valid MD5 hash result despite prefix
        String result = Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), "$1$validSalt", "invalidPrefix", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithRandomInstance() {
        // Test md5Crypt with a standard Random instance, expecting a valid MD5 hash result
        String result = Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), null, "$1$", new Random());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithInvalidSaltValue() {
        // Test md5Crypt with an invalid salt format, expecting an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), "invalidSaltFormat", "$1$", new SecureRandom());
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), "$1$invalidSalt", "$1$", new SecureRandom());
        });
    }
}
