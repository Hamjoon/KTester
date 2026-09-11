package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.nio.charset.StandardCharsets;

@ExtendWith(MockitoExtension.class)
class Base32_encode_Test {

    private Base32 base32;

    private BaseNCodec.Context context;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize Base32 with default parameters
        base32 = new Base32();
        // Initialize Context
        context = new BaseNCodec.Context();
    }

    @Test
    void testEncodeWithValidInput() {
        // Given
        byte[] input = "Hello".getBytes(StandardCharsets.UTF_8);
        int inPos = 0;
        int inAvail = input.length;
        // When
        base32.encode(input, inPos, inAvail, context);
        // Then
        // Add assertions to validate the encoding result
        // Example: assertEquals(expectedEncodedValue, actualEncodedValue);
        // Assuming expectedEncodedValue is known
        // Assuming buffer holds the result
        byte[] encoded = context.buffer;
        // Example expected value for "Hello"
        String expectedEncodedValue = "NBSWY3DP";
        assertEquals(expectedEncodedValue, new String(encoded, 0, context.pos, StandardCharsets.UTF_8));
    }

    @Test
    void testEncodeWithEmptyInput() {
        // Given
        byte[] input = new byte[0];
        int inPos = 0;
        int inAvail = input.length;
        // When
        base32.encode(input, inPos, inAvail, context);
        // Then
        assertEquals(0, context.pos, "Buffer should remain empty for empty input.");
    }

    @Test
    void testEncodeWithEOF() {
        // Given
        byte[] input = "World".getBytes(StandardCharsets.UTF_8);
        int inPos = 0;
        int inAvail = input.length;
        // When
        base32.encode(input, inPos, inAvail, context);
        // Simulate EOF
        base32.encode(input, inPos, -1, context);
        // Then
        // Add assertions to validate the encoding result
        // Assuming buffer holds the result
        byte[] encoded = context.buffer;
        // Example expected value for "World"
        String expectedEncodedValue = "IFBEGRCF";
        assertEquals(expectedEncodedValue, new String(encoded, 0, context.pos, StandardCharsets.UTF_8));
    }

    @Test
    void testEncodeWithEOFScenarios() {
        // Test case: Verify that the method returns immediately when context.eof is true
        // Simulate EOF
        context.eof = true;
        byte[] input = "Test".getBytes(StandardCharsets.UTF_8);
        int inPos = 0;
        int inAvail = input.length;
        // When
        base32.encode(input, inPos, inAvail, context);
        // Then: No exception should be thrown, and method should return immediately
        // Test case: Test that when inAvail is -1, the context.eof is set to true
        // Reset
        context.eof = false;
        // Simulate EOF
        inAvail = -1;
        base32.encode(input, inPos, inAvail, context);
        // Then: Verify that context.eof is now true
        assertTrue(context.eof, "context.eof should be set to true after EOF is processed.");
    }

    @Test
    void testEncodeWithLineLengthScenarios() {
        // Test case: Verify that no line separator is added when lineLength is 0
        // Set line length using the builder method
        base32 = Base32.builder().setLineLength(0).get();
        // Simulate current line position
        context.currentLinePos = 1;
        byte[] input = "Test".getBytes(StandardCharsets.UTF_8);
        int inPos = 0;
        int inAvail = input.length;
        // When
        base32.encode(input, inPos, inAvail, context);
        // Then: No exception should be thrown, and no line separator should be added
        assertEquals(4, context.pos, "Buffer position should match input length without line separator.");
        // Test case: Verify that a line separator is added when context.currentLinePos equals lineLength
        // Set line length using the builder method
        base32 = Base32.builder().setLineLength(4).get();
        // Simulate line position matching line length
        context.currentLinePos = 4;
        base32.encode(input, inPos, inAvail, context);
        // Then: Validate the buffer for line separator addition (buffer state needs to be checked via reflection)
        assertTrue(context.buffer[context.pos - 1] == '\n', "Line separator should be added.");
    }

    @Test
    void testEncodeWithValidInputScenarios() {
        // Test case: Verify that the method encodes a valid byte array input correctly
        byte[] input = new byte[] { 0, 1, 2, 3, 4 };
        int inPos = 0;
        int inAvail = input.length;
        // When
        base32.encode(input, inPos, inAvail, context);
        // Then: Validate the encoding result (buffer state needs to be checked via reflection)
        // Example expected value
        String expectedEncodedValue = "AE======";
        assertEquals(expectedEncodedValue, new String(context.buffer, 0, context.pos, StandardCharsets.UTF_8));
        // Test case: Verify that the method encodes a different valid byte array input correctly
        input = new byte[] { 10, 20, 30, 40, 50 };
        base32.encode(input, inPos, inAvail, context);
        // Example expected value
        expectedEncodedValue = "MFRGGZDF";
        assertEquals(expectedEncodedValue, new String(context.buffer, 0, context.pos, StandardCharsets.UTF_8));
        // Test case: Verify that the method encodes a small valid byte array input correctly
        input = new byte[] { 1, 2, 3 };
        inAvail = input.length;
        base32.encode(input, inPos, inAvail, context);
        // Example expected value
        expectedEncodedValue = "AEB======";
        assertEquals(expectedEncodedValue, new String(context.buffer, 0, context.pos, StandardCharsets.UTF_8));
    }

    @Test
    void testEncodeWithInvalidContextScenarios() {
        // Test case: Test that a NullPointerException is thrown when the context parameter is null
        assertThrows(NullPointerException.class, () -> {
            base32.encode(new byte[] { 1, 2, 3 }, 0, 3, null);
        });
    }

    @Test
    void testEncodeWithBufferSizeScenarios() {
        // Test case: Test that an ArrayIndexOutOfBoundsException is thrown when the buffer size is insufficient
        // Input size is 1
        byte[] input = new byte[] { 1 };
        context = new BaseNCodec.Context();
        // Set buffer size to 0 to simulate insufficient buffer
        context.pos = 0;
        // Ensure buffer is empty
        context.buffer = new byte[0];
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            base32.encode(input, 0, 1, context);
        });
    }
}
