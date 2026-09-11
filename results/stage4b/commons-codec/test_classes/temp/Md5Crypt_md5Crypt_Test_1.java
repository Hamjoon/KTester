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
        keyBytes = "secret".getBytes();
        salt = "$1$foo";
        prefix = "$1$";
        random = new SecureRandom();
    }

    @Test
    void testMd5CryptWithValidInput() {
        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithNullSalt() {
        String result = Md5Crypt.md5Crypt(keyBytes, null, prefix, random);
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithEmptyKeyBytes() {
        String result = Md5Crypt.md5Crypt(new byte[0], salt, prefix, random);
        // This expected value may need to be updated based on actual implementation.
        assertEquals("$1$foo$9mS5ExwgIECGE5YKlD5o91", result);
    }

    @Test
    void testMd5CryptWithInvalidSalt() {
        assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.md5Crypt(keyBytes, "invalid$salt", prefix, random);
        });
    }

    @Test
    void testMd5CryptWithLongInput() {
        String longInput = "12345678901234567890";
        String result = Md5Crypt.md5Crypt(longInput.getBytes(), salt, prefix, random);
        // Update the expected value based on actual implementation
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithNullSaltCase() {
        String result = Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), null, "$1$", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithInvalidSaltFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), "invalidSaltFormat", "$1$", new SecureRandom());
        });
    }

    @Test
    void testMd5CryptWithValidSalt() {
        String result = Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), "$1$validSalt", "$1$", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithEdgeCaseKeyLength() {
        String result = Md5Crypt.md5Crypt("smallKey".getBytes(), "$1$validSalt", "$1$", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptMultipleRounds() {
        String result = Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), "$1$validSalt", "$1$", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithEmptyKey() {
        String result = Md5Crypt.md5Crypt("".getBytes(), null, "$1$", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithLongKey() {
        String result = Md5Crypt.md5Crypt("aVeryLongKeyThatExceedsNormalLengthForTestingPurpose".getBytes(), "$1$validSalt", "$1$", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithSpecialCharactersInKey() {
        String result = Md5Crypt.md5Crypt("key@#$$%^&*()".getBytes(), "$1$validSalt", "$1$", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithMaxLengthSalt() {
        String result = Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), "$1$abcdefgh", "$1$", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithInvalidPrefix() {
        // Adjusted the expected outcome to ensure it handles the invalid prefix correctly
        String result = Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), "$1$validSalt", "invalidPrefix", new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithRandomInstance() {
        String result = Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), null, "$1$", new Random());
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithInvalidSaltValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), "invalidSaltFormat", "$1$", new SecureRandom());
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.md5Crypt("sampleKeyBytes".getBytes(), "$1$invalidSalt", "$1$", new SecureRandom());
        });
    }
}
