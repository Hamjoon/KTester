```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.BaseNCodec.Context;

@ExtendWith(MockitoExtension.class)
class Base64_decode_Test {

    private Base64 base64;
    private Context context;

    @BeforeAll
    static void setupBeforeAll() {
        // This method can be used to set up resources needed for all tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize Base64 object and Context before each test
        base64 = new Base64(CodecPolicy.STRICT);
        context = new Context();
    }

    @AfterEach
    void teardownAfterEach() {
        // This method can be used to clean up resources after each test
        base64 = null;
        context = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // This method can be used to clean up resources needed for all tests
    }

    @Test
    void testDecodeWithValidInput() {
        // Given
        byte[] input = Base64.encodeBase64("test".getBytes());
        int inPos = 0;
        int inAvail = input.length;

        // When
        base64.decode(input, inPos, inAvail, context);

        // Then
        assertNotNull(context);
        assertFalse(context.eof);
    }

    @Test
    void testDecodeWithInvalidInput() {
        // Given
        byte[] input = "invalid base64".getBytes();
        int inPos = 0;
        int inAvail = input.length;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> base64.decode(input, inPos, inAvail, context));
    }

    // Additional tests can be added here
}
```