package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.apache.commons.codec.binary.BaseNCodec.Context;

@ExtendWith(MockitoExtension.class)
class Base32_decode_Test {

    private Base32 base32;

    private Context context;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize Base32 and Context before each test
        base32 = new Base32();
        context = new Context();
    }

    @Test
    void testDecodeWithValidInput() {
        // Example input
        byte[] input = "MY======".getBytes();
        // Expected output
        byte[] expectedOutput = "f".getBytes();
        base32.decode(input, 0, input.length, context);
        // Get the results from the context's buffer
        byte[] decodedBytes = new byte[context.pos];
        System.arraycopy(context.buffer, 0, decodedBytes, 0, context.pos);
        assertArrayEquals(expectedOutput, decodedBytes);
    }

    @Test
    void testDecodeWithEmptyInput() {
        // Empty input
        byte[] input = "".getBytes();
        // Expected output
        byte[] expectedOutput = "".getBytes();
        base32.decode(input, 0, input.length, context);
        // Get the results from the context's buffer
        byte[] decodedBytes = new byte[context.pos];
        // Check if context.pos is zero before copying
        if (context.pos > 0) {
            System.arraycopy(context.buffer, 0, decodedBytes, 0, context.pos);
        }
        assertArrayEquals(expectedOutput, decodedBytes);
    }

    @Test
    void testDecodeEOFConditions() {
        // Test when context.eof is initially false and inAvail is positive.
        byte[] input1 = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7 };
        base32.decode(input1, 0, input1.length, context);
        assertFalse(context.eof, "Expected context.eof to be false.");
        // Test when inAvail is negative, should set context.eof to true.
        base32.decode(input1, 0, -1, context);
        assertTrue(context.eof, "Expected context.eof to be true when inAvail is negative.");
        // Test when '=' padding character is encountered, should set context.eof to true.
        byte[] input3 = new byte[] { 0, 1, 2, 3, 4, 5, 6, 61 };
        base32.decode(input3, 0, input3.length, context);
        assertTrue(context.eof, "Expected context.eof to be true when '=' padding character is encountered.");
    }

    @Test
    void testDecodeHandlingPaddingAndModulus() {
        // Test when '=' padding character is encountered, should set context.eof to true.
        byte[] input1 = new byte[] { 0, 1, 2, 3, 4, 5, 6, 61 };
        base32.decode(input1, 0, input1.length, context);
        assertTrue(context.eof, "Expected context.eof to be true when '=' padding character is encountered.");
        // Test when input is valid and modulus is calculated correctly.
        byte[] input2 = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7 };
        base32.decode(input2, 0, input2.length, context);
        assertEquals(0, context.modulus, "Expected modulus to be 0 for valid input.");
    }

    @Test
    void testDecodeModulusCases() {
        // Test when modulus is 2, should drop 2 bits and output one byte.
        // Valid Base32 input
        byte[] input1 = "MY======".getBytes();
        base32.decode(input1, 0, input1.length, context);
        assertEquals(1, context.pos, "Expected context.pos to be 1 when modulus is 2.");
        // Reset context for further tests
        context.pos = 0;
        context.modulus = 0;
        // Test when modulus is 3, should drop 7 bits and output one byte.
        base32.decode(input1, 0, input1.length, context);
        assertEquals(1, context.pos, "Expected context.pos to be 1 when modulus is 3.");
        // Reset context for further tests
        context.pos = 0;
        context.modulus = 0;
        // Test when modulus is 4, should validate character and output two bytes.
        base32.decode(input1, 0, input1.length, context);
        assertEquals(2, context.pos, "Expected context.pos to be 2 when modulus is 4.");
        // Reset context for further tests
        context.pos = 0;
        context.modulus = 0;
        // Test when modulus is 5, should validate character and output three bytes.
        base32.decode(input1, 0, input1.length, context);
        assertEquals(3, context.pos, "Expected context.pos to be 3 when modulus is 5.");
        // Reset context for further tests
        context.pos = 0;
        context.modulus = 0;
        // Test when modulus is 6, should validate trailing characters.
        base32.decode(input1, 0, input1.length, context);
        assertEquals(3, context.pos, "Expected context.pos to be 3 when modulus is 6.");
        // Reset context for further tests
        context.pos = 0;
        context.modulus = 0;
        // Test when modulus is 7, should validate character and output four bytes.
        base32.decode(input1, 0, input1.length, context);
        assertEquals(4, context.pos, "Expected context.pos to be 4 when modulus is 7.");
    }

    @Test
    void testDecodeInvalidInputHandling() {
        // Test with negative byte values, should raise an exception due to invalid input.
        byte[] input1 = new byte[] { -1, -2, -3, -4, -5 };
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            base32.decode(input1, 0, input1.length, context);
        });
        assertNotNull(exception1, "Expected exception when input contains negative byte values.");
        // Test with input exceeding valid Base32 byte range, should raise an exception.
        byte[] input2 = new byte[] { 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115 };
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            base32.decode(input2, 0, input2.length, context);
        });
        assertNotNull(exception2, "Expected exception when input exceeds valid Base32 byte range.");
    }
}
