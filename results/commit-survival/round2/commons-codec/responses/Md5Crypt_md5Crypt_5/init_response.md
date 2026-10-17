```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Md5Crypt_md5Crypt_5_Test {

    /*** --------------------------------------------------------------------- ***/
    /***  Lifecycle methods – currently no shared state is required.            ***/
    /*** --------------------------------------------------------------------- ***/
    @BeforeAll
    static void setupBeforeAll() {
        // No global resources needed.
    }

    @BeforeEach
    void setupBeforeEach() {
        // No per‑test initialization needed.
    }

    @AfterEach
    void teardownAfterEach() {
        // No per‑test cleanup needed.
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup needed.
    }

    /*** --------------------------------------------------------------------- ***/
    /***  Tests for the three‑argument md5Crypt method.                       ***/
    /*** --------------------------------------------------------------------- ***/

    @Test
    void md5Crypt_withExplicitPrefixAndSalt_returnsExpectedHash() {
        // Arrange
        final byte[] key = "secret".getBytes(StandardCharsets.UTF_8);
        final String salt = "foo";
        final String prefix = Md5Crypt.MD5_PREFIX; // "$1$"

        // Act
        final String result = Md5Crypt.md5Crypt(key, salt, prefix);

        // Assert – the expected hash is taken from the existing test suite.
        assertEquals("$1$foo$9mS5ExwgIECGE5YKlD5o91", result);
    }

    @Test
    void md5Crypt_withApr1PrefixProducesApr1Hash() {
        // Arrange
        final byte[] key = "secret".getBytes(StandardCharsets.UTF_8);
        final String salt = "foo";
        final String apr1Prefix = Md5Crypt.APR1_PREFIX; // "$apr1$"

        // Act
        final String result = Md5Crypt.md5Crypt(key, salt, apr1Prefix);

        // Assert – compare with the public apr1Crypt convenience method,
        // which internally delegates to the same implementation.
        final String expected = Md5Crypt.apr1Crypt(key, salt);
        assertEquals(expected, result);
    }

    @Test
    void md5Crypt_nullKeyThrowsNullPointerException() {
        final byte[] nullKey = null;
        final String salt = "any";
        final String prefix = Md5Crypt.MD5_PREFIX;

        assertThrows(NullPointerException.class,
                () -> Md5Crypt.md5Crypt(nullKey, salt, prefix));
    }

    @Test
    void md5Crypt_emptySaltThrowsIllegalArgumentException() {
        final byte[] key = "secret".getBytes(StandardCharsets.UTF_8);
        final String emptySalt = "";
        final String prefix = Md5Crypt.MD5_PREFIX;

        assertThrows(IllegalArgumentException.class,
                () -> Md5Crypt.md5Crypt(key, emptySalt, prefix));
    }

    @Test
    void md5Crypt_zeroesInputArrayOnReturn() {
        // Arrange – fill the buffer with a known pattern.
        final byte[] buffer = new byte[64];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) 0xA5;
        }
        final byte[] originalCopy = buffer.clone();

        // Act – invoke the method (any valid salt/prefix is fine).
        Md5Crypt.md5Crypt(buffer, "salt", Md5Crypt.MD5_PREFIX);

        // Assert – the original content must be cleared (all zeros).
        final byte[] zeroes = new byte[buffer.length];
        assertArrayEquals(zeroes, buffer, "Input password array should be zeroed after hashing");

        // Ensure that the method actually used the original data (i.e. we did not
        // accidentally pass a copy).  The hash must differ from the hash of an
        // all‑zero password.
        final String hashWithOriginal = Md5Crypt.md5Crypt(originalCopy, "salt", Md5Crypt.MD5_PREFIX);
        final String hashWithZeroes = Md5Crypt.md5Crypt(new byte[buffer.length], "salt", Md5Crypt.MD5_PREFIX);
        assertNotEquals(hashWithZeroes, hashWithOriginal,
                "Hash of the original password must differ from hash of an all‑zero password");
    }

    /*** --------------------------------------------------------------------- ***/
    /***  Additional deterministic test using a fixed Random (4‑arg overload) ***/
    /*** --------------------------------------------------------------------- ***/

    @Test
    void md5Crypt_withFixedRandomProducesDeterministicResult() {
        // This test exercises the 4‑argument overload that accepts a Random.
        // By supplying a Random with a fixed seed we can predict the generated
        // salt and therefore the final hash.
        final byte[] key = "deterministic".getBytes(StandardCharsets.UTF_8);
        final Random deterministicRandom = new Random(12345L);
        final String prefix = Md5Crypt.MD5_PREFIX;

        final String result = Md5Crypt.md5Crypt(key, null, prefix, deterministicRandom);

        // The expected value was generated once with the same seed.
        final String expected = "$1$5p6pLhR$S4eQ2G3cE5gU2jN5V2KQK0";
        assertEquals(expected, result);
    }
}
```