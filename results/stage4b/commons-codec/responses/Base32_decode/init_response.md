```java
package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.nio.charset.StandardCharsets;

@ExtendWith(MockitoExtension.class)
class Base32_decode_Test {

    private Base32 base32;
    private BaseNCodec.Context context;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize Base32 codec and Context before each test
        base32 = new Base32(); // Use default constructor
        context = new BaseNCodec.Context(); // Initialize context
    }

    @AfterEach
    void teardownAfterEach() {
        // Any cleanup after each test can be done here
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here if needed
    }

    @Test
    void testDecodeValidInput() {
        byte[] input = "MY======".getBytes(StandardCharsets.UTF_8);
        byte[] expectedOutput = "f".getBytes(StandardCharsets.UTF_8);
        
        base32.decode(input, 0, input.length, context);
        
        // Assuming you have a method to read results from context to output
        byte[] output = new byte[expectedOutput.length];
        context.pos = 0; // Reset position
        base32.readResults(output, 0, output.length, context);
        
        assertArrayEquals(expectedOutput, output);
    }

    @Test
    void testDecodeWithPadding() {
        byte[] input = "MZXW6YTB".getBytes(StandardCharsets.UTF_8);
        byte[] expectedOutput = "fooba".getBytes(StandardCharsets.UTF_8);
        
        base32.decode(input, 0, input.length, context);
        
        byte[] output = new byte[expectedOutput.length];
        context.pos = 0; // Reset position
        base32.readResults(output, 0, output.length, context);
        
        assertArrayEquals(expectedOutput, output);
    }

    @Test
    void testDecodeInvalidInput() {
        byte[] input = "MC======".getBytes(StandardCharsets.UTF_8);
        
        assertThrows(IllegalStateException.class, () -> {
            base32.decode(input, 0, input.length, context);
        });
    }

    // Additional test cases can be added here
}
```