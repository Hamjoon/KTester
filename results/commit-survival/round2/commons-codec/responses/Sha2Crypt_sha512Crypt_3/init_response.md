```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Sha2Crypt_sha512Crypt_3_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources needed for these tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // No per‑test initialisation required
    }

    @AfterEach
    void teardownAfterEach() {
        // No per‑test cleanup required
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global cleanup required
    }

    @Test
    void testSha512CryptWithProvidedSalt() {
        // GIVEN
        byte[] keyBytes = "secretPassword".getBytes(StandardCharsets.UTF_8);
        String salt = "$6$myCustomSalt$";
        Random random = new SecureRandom(); // random is not used because salt is supplied

        // WHEN
        String result = Sha2Crypt.sha512Crypt(keyBytes, salt, random);

        // THEN
        assertNotNull(result, "Result should not be null");
        assertTrue(result.startsWith(salt),
                () -> "Result should start with the provided salt. Expected prefix: " + salt + " but was: " + result);
        // The result must also contain the SHA‑512 prefix "$6$"
        assertTrue(result.contains(Sha2Crypt.SHA512_PREFIX),
                () -> "Result should contain the SHA‑512 prefix \"" + Sha2Crypt.SHA512_PREFIX + "\"");
    }

    @Test
    void testSha512CryptWithNullSaltGeneratesPrefix() {
        // GIVEN
        byte[] keyBytes = "anotherSecret".getBytes(StandardCharsets.UTF_8);
        String salt = null;
        Random random = new SecureRandom(); // used only if salt is null

        // WHEN
        String result = Sha2Crypt.sha512Crypt(keyBytes, salt, random);

        // THEN
        assertNotNull(result, "Result should not be null");
        // When salt is null the method must prepend the SHA‑512 prefix "$6$"
        assertTrue(result.startsWith(Sha2Crypt.SHA512_PREFIX),
                () -> "Result should start with the SHA‑512 prefix when salt is null. Expected prefix: "
                        + Sha2Crypt.SHA512_PREFIX + " but was: " + result);
    }
}
```