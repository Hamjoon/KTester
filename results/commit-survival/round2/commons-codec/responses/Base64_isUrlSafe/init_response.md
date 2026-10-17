```java
package org.apache.commons.codec.binary;

import org.apache.commons.codec.CodecPolicy;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Base64_isUrlSafe_Test {

    /** Instances used in the tests */
    private Base64 defaultBase64;          // no‑arg constructor – urlSafe = false
    private Base64 urlSafeFromBool;        // boolean ctor – urlSafe = true
    private Base64 nonUrlSafeFromBool;    // boolean ctor – urlSafe = false
    private Base64 urlSafeFromFullCtor;    // full ctor – urlSafe = true
    private Base64 nonUrlSafeFromFullCtor;// full ctor – urlSafe = false
    private Base64 urlSafeFromPolicyCtor; // full ctor with decoding policy – urlSafe = true
    private Base64 nonUrlSafeFromPolicyCtor;// full ctor with decoding policy – urlSafe = false
    private Base64 urlSafeFromBuilder;    // Builder – urlSafe = true
    private Base64 nonUrlSafeFromBuilder; // Builder – urlSafe = false

    @BeforeAll
    static void setupBeforeAll() {
        // No global resources required for these tests.
    }

    @BeforeEach
    void setupBeforeEach() {
        // 1. Default constructor – urlSafe defaults to false.
        defaultBase64 = new Base64();

        // 2. Boolean constructor.
        urlSafeFromBool = new Base64(true);
        nonUrlSafeFromBool = new Base64(false);

        // 3. Full constructor (lineLength, lineSeparator, urlSafe).
        //    Use a simple line separator; lineLength 0 means “no chunking”.
        byte[] crlf = new byte[]{'\r', '\n'};
        urlSafeFromFullCtor = new Base64(0, crlf, true);
        nonUrlSafeFromFullCtor = new Base64(0, crlf, false);

        // 4. Full constructor with explicit decoding policy.
        urlSafeFromPolicyCtor = new Base64(0, crlf, true, CodecPolicy.LENIENT);
        nonUrlSafeFromPolicyCtor = new Base64(0, crlf, false, CodecPolicy.STRICT);

        // 5. Builder – setUrlSafe(true/false).
        urlSafeFromBuilder = Base64.builder()
                                   .setUrlSafe(true)
                                   .get();
        nonUrlSafeFromBuilder = Base64.builder()
                                       .setUrlSafe(false)
                                       .get();
    }

    @AfterEach
    void teardownAfterEach() {
        // Clear references – helps GC and isolates tests.
        defaultBase64 = null;
        urlSafeFromBool = null;
        nonUrlSafeFromBool = null;
        urlSafeFromFullCtor = null;
        nonUrlSafeFromFullCtor = null;
        urlSafeFromPolicyCtor = null;
        nonUrlSafeFromPolicyCtor = null;
        urlSafeFromBuilder = null;
        nonUrlSafeFromBuilder = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // Nothing to clean up at the class level.
    }

    /* --------------------------------------------------------------------- */
    /*  Tests for the public isUrlSafe() accessor                              */
    /* --------------------------------------------------------------------- */

    @Test
    void testDefaultConstructorIsUrlSafe() {
        assertFalse(defaultBase64.isUrlSafe(),
                "Default constructor should produce a non‑URL‑safe instance");
    }

    @Test
    void testBooleanConstructorTrue() {
        assertTrue(urlSafeFromBool.isUrlSafe(),
                "Constructor Base64(true) must report urlSafe == true");
    }

    @Test
    void testBooleanConstructorFalse() {
        assertFalse(nonUrlSafeFromBool.isUrlSafe(),
                "Constructor Base64(false) must report urlSafe == false");
    }

    @Test
    void testFullConstructorUrlSafeTrue() {
        assertTrue(urlSafeFromFullCtor.isUrlSafe(),
                "Full constructor with urlSafe=true must report true");
    }

    @Test
    void testFullConstructorUrlSafeFalse() {
        assertFalse(nonUrlSafeFromFullCtor.isUrlSafe(),
                "Full constructor with urlSafe=false must report false");
    }

    @Test
    void testFullConstructorWithPolicyUrlSafeTrue() {
        assertTrue(urlSafeFromPolicyCtor.isUrlSafe(),
                "Full constructor (with policy) urlSafe=true must report true");
    }

    @Test
    void testFullConstructorWithPolicyUrlSafeFalse() {
        assertFalse(nonUrlSafeFromPolicyCtor.isUrlSafe(),
                "Full constructor (with policy) urlSafe=false must report false");
    }

    @Test
    void testBuilderUrlSafeTrue() {
        assertTrue(urlSafeFromBuilder.isUrlSafe(),
                "Builder configured with setUrlSafe(true) must produce a url‑safe instance");
    }

    @Test
    void testBuilderUrlSafeFalse() {
        assertFalse(nonUrlSafeFromBuilder.isUrlSafe(),
                "Builder configured with setUrlSafe(false) must produce a non‑url‑safe instance");
    }
}
```