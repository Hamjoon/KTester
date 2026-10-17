You are tasked with creating a framework for a test class in Java. Now analyze the following information carefully:

1. The method that needs to be tested and its parameters.
2. The constructor of the target class and its parameters.
3. Any dependencies or external resources used by the target class.

Based on this analysis, complete the test class template in the provided structure:

1. Initialize necessary fields and dependencies within setupBeforeAll() and setupBeforeEach().
2. Handle any required cleanup in teardownAfterEach() and teardownAfterAll().
3. Make sure not to invoke any private methods or fields of the target class and dependent class.
4. Refer to the "given" phase in the existing test class for guidance.
5. Add necessary import statements and ensure there are no syntax errors in the generated code.

@input{focal method and target class}
Here is the code of focal method, `md5Crypt(byte[])`, with the focal class `Md5Crypt_md5Crypt_2_Test`:
```Java
package org.apache.commons.codec.digest;
import java.util.regex.Pattern;
import java.util.Random;
import java.util.regex.Matcher;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.util.Arrays;
public class Md5Crypt {
    static final String APR1_PREFIX = "$apr1$";
    static final String MD5_PREFIX = "$1$";
    public static String md5Crypt(final byte[] keyBytes) {
        return md5Crypt(keyBytes, MD5_PREFIX + B64.getRandomSalt(8));
    }
public static String apr1Crypt(final byte[] keyBytes);
    public static String apr1Crypt(final byte[] keyBytes, String salt);
    public static String apr1Crypt(final byte[] keyBytes, final Random random);
    public static String apr1Crypt(final String keyBytes);
    public static String apr1Crypt(final String keyBytes, final String salt);
    public static String md5Crypt(final byte[] keyBytes, final String salt);
    public static String md5Crypt(final byte[] keyBytes, final String salt, final String prefix);
    public static String md5Crypt(final byte[] keyBytes, final String salt, final String prefix, final Random random);
    public static String md5Crypt(final byte[] keyBytes, final Random random);
}
```


@input{context information}

api document of class org.apache.commons.codec.digest.Md5Crypt: 

 * The libc crypt() "$1$" and Apache "$apr1$" MD5-based hash algorithm.
 * <p>
 * Based on the public domain ("beer-ware") C implementation from Poul-Henning Kamp which was found at: <a
 * href="http://www.freebsd.org/cgi/cvsweb.cgi/src/lib/libcrypt/crypt-md5.c?rev=1.1;content-type=text%2Fplain">
 * crypt-md5.c @ freebsd.org</a>
 * </p>
 * <p>
 * Source:
 * </p>
 * <pre>
 * $FreeBSD: src/lib/libcrypt/crypt-md5.c,v 1.1 1999/01/21 13:50:09 brandon Exp $
 * </pre>
 * <p>
 * Conversion to Kotlin and from there to Java in 2012.
 * </p>
 * <p>
 * The C style comments are from the original C code, the ones with "//" from the port.
 * </p>
 * <p>
 * This class is immutable and thread-safe.
 * </p>
 *
 * @since 1.7
 

existing test class: 
```java
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.codec.digest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;

public class Md5CryptTest {

    @Test
    public void testCtor() {
        assertNotNull(new Md5Crypt()); // for code-coverage
    }

    @Test
    public void testMd5CryptBytes() {
        // An empty Bytearray equals an empty String
        assertEquals("$1$foo$9mS5ExwgIECGE5YKlD5o91", Crypt.crypt(new byte[0], "$1$foo"));
        // UTF-8 stores \u00e4 "a with dieresis" as two bytes 0xc3 0xa4.
        assertEquals("$1$./$52agTEQZs877L9jyJnCNZ1", Crypt.crypt("t\u00e4st", "$1$./$"));
        // ISO-8859-1 stores "a with dieresis" as single byte 0xe4.
        assertEquals("$1$./$J2UbKzGe0Cpe63WZAt6p//", Crypt.crypt("t\u00e4st".getBytes(StandardCharsets.ISO_8859_1), "$1$./$"));
    }

    @Test
    public void testMd5CryptExplicitCall() {
        assertTrue(Md5Crypt.md5Crypt("secret".getBytes()).matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
        assertTrue(Md5Crypt.md5Crypt("secret".getBytes(), (String) null).matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    public void testMd5CryptExplicitCallWithThreadLocalRandom() {
        final ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        assertTrue(Md5Crypt.md5Crypt("secret".getBytes(), threadLocalRandom).matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
        assertTrue(Md5Crypt.md5Crypt("secret".getBytes(), (String) null).matches("^\\$1\\$[a-zA-Z0-9./]{0,8}\\$.{1,}$"));
    }

    @Test
    public void testMd5CryptLongInput() {
        assertEquals("$1$1234$MoxekaNNUgfPRVqoeYjCD/", Crypt.crypt("12345678901234567890", "$1$1234"));
    }

    @Test
    public void testMd5CryptNullData() {
        assertThrows(NullPointerException.class, () -> Md5Crypt.md5Crypt((byte[]) null));
    }

    @Test
    public void testMd5CryptStrings() {
        // empty data
        assertEquals("$1$foo$9mS5ExwgIECGE5YKlD5o91", Crypt.crypt("", "$1$foo"));
        // salt gets cut at dollar sign
        assertEquals("$1$1234$ImZYBLmYC.rbBKg9ERxX70", Crypt.crypt("secret", "$1$1234"));
        assertEquals("$1$1234$ImZYBLmYC.rbBKg9ERxX70", Crypt.crypt("secret", "$1$1234$567"));
        assertEquals("$1$1234$ImZYBLmYC.rbBKg9ERxX70", Crypt.crypt("secret", "$1$1234$567$890"));
        // salt gets cut at maximum length
        assertEquals("$1$12345678$hj0uLpdidjPhbMMZeno8X/", Crypt.crypt("secret", "$1$1234567890123456"));
        assertEquals("$1$12345678$hj0uLpdidjPhbMMZeno8X/", Crypt.crypt("secret", "$1$123456789012345678"));
    }

    @Test
    public void testMd5CryptWithEmptySalt() {
        assertThrows(IllegalArgumentException.class, () -> Md5Crypt.md5Crypt("secret".getBytes(), ""));
    }

    @Test
    public void testZeroOutInput() {
        final byte[] buffer = new byte[200];
        Arrays.fill(buffer, (byte) 'A');
        Md5Crypt.md5Crypt(buffer);
        // input password is 0-filled on return
        assertArrayEquals(new byte[buffer.length], buffer);
    }

}

```



@input{test class template}
```java
package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class Md5Crypt_md5Crypt_2_Test {
    @BeforeAll
    static void setupBeforeAll() {
    }

    @BeforeEach
    void setupBeforeEach() {
    }

    @AfterEach
    void teardownAfterEach() {
    }

    @AfterAll
    static void teardownAfterAll() {
    }
}
```

@output{initial test class}: now consider the invoke examples output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.