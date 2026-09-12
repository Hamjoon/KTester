package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.security.SecureRandom;
import java.util.Random;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

class Md5Crypt_md5Crypt_Test {

    private Random random;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the random object before each test
        random = new SecureRandom();
    }

    @Test
    void testMd5CryptWithValidInput() {
        // given
        byte[] keyBytes = "secret".getBytes(StandardCharsets.UTF_8);
        String salt = "$1$1234";
        String prefix = Md5Crypt.MD5_PREFIX;
        // when
        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        // then
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithNullSalt() {
        // given
        byte[] keyBytes = "secret".getBytes(StandardCharsets.UTF_8);
        String prefix = Md5Crypt.MD5_PREFIX;
        // when
        String result = Md5Crypt.md5Crypt(keyBytes, null, prefix, random);
        // then
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithEmptySalt() {
        // given
        byte[] keyBytes = "secret".getBytes(StandardCharsets.UTF_8);
        String prefix = Md5Crypt.MD5_PREFIX;
        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            Md5Crypt.md5Crypt(keyBytes, "", prefix, random);
        });
    }

    @Test
    void testMd5CryptWithLongKeyBytes() {
        // given
        byte[] keyBytes = new byte[200];
        Arrays.fill(keyBytes, (byte) 'A');
        String salt = "$1$1234";
        String prefix = Md5Crypt.MD5_PREFIX;
        // when
        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        // then
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
        // Ensure input is zeroed out
        assertArrayEquals(new byte[200], keyBytes);
    }

    @Test
    void testMd5CryptWithNullKeyBytes() {
        // given
        String salt = "$1$abcde";
        String prefix = Md5Crypt.MD5_PREFIX;
        // then
        assertThrows(NullPointerException.class, () -> {
            // when
            Md5Crypt.md5Crypt(null, salt, prefix, random);
        });
    }

    @Test
    void testMd5CryptWithNullPrefix() {
        // given
        byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        String salt = "$1$abcde";
        // then
        assertThrows(NullPointerException.class, () -> {
            // when
            Md5Crypt.md5Crypt(keyBytes, salt, null, random);
        });
    }

    @Test
    void testMd5CryptWithNullRandom() {
        // given
        byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        String salt = "$1$abcde";
        String prefix = Md5Crypt.MD5_PREFIX;
        // then
        assertThrows(NullPointerException.class, () -> {
            // when
            Md5Crypt.md5Crypt(keyBytes, salt, prefix, null);
        });
    }

    @Test
    void testMd5CryptWithInvalidSalt() {
        // given
        byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        String salt = "$1$invalid_salt";
        String prefix = Md5Crypt.MD5_PREFIX;
        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        });
    }

    @Test
    void testMd5CryptWithApr1Prefix() {
        // given
        byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        String salt = "$apr1$abcdefg";
        String prefix = Md5Crypt.APR1_PREFIX;
        // when
        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        // then
        assertNotNull(result);
        assertTrue(result.startsWith(Md5Crypt.APR1_PREFIX));
    }

    @Test
    void testMd5CryptWithRandomSaltGeneration() {
        // given
        byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        String prefix = Md5Crypt.APR1_PREFIX;
        // when
        String result = Md5Crypt.md5Crypt(keyBytes, null, prefix, random);
        // then
        assertNotNull(result);
        assertTrue(result.startsWith(Md5Crypt.APR1_PREFIX));
    }

    @Test
    void testMd5CryptWithShortValidSalt() {
        // given
        byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        String salt = "$1$abc";
        String prefix = Md5Crypt.MD5_PREFIX;
        // when
        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        // then
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithMaxLengthSalt() {
        // given
        byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        String salt = "$1$abcdefgh";
        String prefix = Md5Crypt.MD5_PREFIX;
        // when
        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        // then
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithEmptyKeyBytes() {
        // given
        byte[] keyBytes = new byte[0];
        String salt = "$1$abcde";
        String prefix = Md5Crypt.MD5_PREFIX;
        // when
        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        // then
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithApr1SaltUsingMd5Prefix() {
        // given
        byte[] keyBytes = "abc".getBytes(StandardCharsets.UTF_8);
        String salt = "$apr1$abcde";
        String prefix = Md5Crypt.MD5_PREFIX;
        // then
        assertThrows(IllegalArgumentException.class, () -> {
            // when
            Md5Crypt.md5Crypt(keyBytes, salt, prefix, random);
        });
    }
}
