package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.security.SecureRandom;

@ExtendWith(MockitoExtension.class)
class Md5Crypt_md5Crypt_Test {

    private byte[] keyBytes;

    private String salt;

    private String prefix;

    private SecureRandom secureRandom;

    @BeforeEach
    void setupBeforeEach() {
        // Example key
        keyBytes = "secret".getBytes();
        // Example salt
        salt = "$1$foo";
        // Example prefix
        prefix = "$1$";
        // Initializing SecureRandom
        secureRandom = new SecureRandom();
    }

    private static Stream<Arguments> provideNullSaltTestCases() {
        return Stream.of(Arguments.of("sampleKey", "^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    private static Stream<Arguments> provideInvalidSaltFormatTestCases() {
        return Stream.of(Arguments.of("sampleKey", "invalidSaltFormat", "Invalid salt value: invalidSaltFormat"));
    }

    private static Stream<Arguments> provideValidSaltTestCases() {
        return Stream.of(Arguments.of("sampleKey", "$1$saltValue", "^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    private static Stream<Arguments> provideDifferentPrefixesTestCases() {
        return Stream.of(Arguments.of("sampleKey", "$apr1$saltValue", "$apr1$", "^\\$apr1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithValidInput() {
        String result = Md5Crypt.md5Crypt(keyBytes, salt, prefix, secureRandom);
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithNullSalt() {
        String result = Md5Crypt.md5Crypt(keyBytes, null, prefix, secureRandom);
        assertNotNull(result);
        assertTrue(result.matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    void testMd5CryptWithEmptyKey() {
        String result = Md5Crypt.md5Crypt(new byte[0], salt, prefix, secureRandom);
        assertEquals("$1$foo$9mS5ExwgIECGE5YKlD5o91", result);
    }

    @Test
    void testMd5CryptWithInvalidSalt() {
        assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.md5Crypt(keyBytes, "invalid$salt", prefix, secureRandom);
        });
    }

    @Test
    void testMd5CryptWithNullKey() {
        assertThrows(NullPointerException.class, () -> {
            Md5Crypt.md5Crypt(null, salt, prefix, secureRandom);
        });
    }

    @ParameterizedTest
    @MethodSource("provideNullSaltTestCases")
    void testMd5CryptWithNullSaltParameterized(String key, String expectedPattern) {
        String result = Md5Crypt.md5Crypt(key.getBytes(), null, prefix, new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches(expectedPattern));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidSaltFormatTestCases")
    void testMd5CryptWithInvalidSaltFormatParameterized(String key, String salt, String expectedMessage) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Md5Crypt.md5Crypt(key.getBytes(), salt, prefix, new SecureRandom());
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideValidSaltTestCases")
    void testMd5CryptWithValidSaltParameterized(String key, String salt, String expectedPattern) {
        String result = Md5Crypt.md5Crypt(key.getBytes(), salt, prefix, new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches(expectedPattern));
    }

    @ParameterizedTest
    @MethodSource("provideDifferentPrefixesTestCases")
    void testMd5CryptWithDifferentPrefixesParameterized(String key, String salt, String prefix, String expectedPattern) {
        String result = Md5Crypt.md5Crypt(key.getBytes(), salt, prefix, new SecureRandom());
        assertNotNull(result);
        assertTrue(result.matches(expectedPattern));
    }
}
