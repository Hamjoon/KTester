package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.apache.commons.codec.CodecPolicy;
import org.apache.commons.codec.binary.BaseNCodec.Context;
import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
class Base64_encode_Test {

    private Base64 base64Codec;

    private Context context;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Base64 codec with default parameters
        // Default constructor
        this.base64Codec = new Base64();
        // Create a new context for each test
        this.context = new Context();
    }

    @Test
    void testEncode() {
        // Given
        byte[] input = "The quick brown fox".getBytes();
        int inPos = 0;
        int inAvail = input.length;
        // When
        base64Codec.encode(input, inPos, inAvail, context);
        // Then
        // Add assertions to validate the expected output
        // Example: assertEquals(expectedOutput, context.output);
    }

    @Test
    void testEncodeWithContextAtEOF() {
        // Test when context.eof is true, should return without processing.
        context.eof = true;
        byte[] input = { 1, 2, 3 };
        int inPos = 0;
        int inAvail = -1;
        base64Codec.encode(input, inPos, inAvail, context);
        // Verify that context remains unchanged
        assertEquals(0, context.pos);
    }

    @Test
    void testEncodeWithInAvailNegativeAndModulusZero() {
        // Test when inAvail is negative, modulus is 0, and no leftovers, should return without processing.
        byte[] input = {};
        int inPos = 0;
        int inAvail = -1;
        context.eof = false;
        context.modulus = 0;
        base64Codec.encode(input, inPos, inAvail, context);
        // Verify that context remains unchanged
        assertEquals(0, context.pos);
    }

    @Test
    void testEncodeWithInAvailNegativeAndModulusOne() {
        // Test when inAvail is negative, modulus is 1, should encode 2 bits and add padding.
        byte[] input = {};
        int inPos = 0;
        int inAvail = -1;
        context.eof = false;
        context.modulus = 1;
        context.ibitWorkArea = 64;
        base64Codec.encode(input, inPos, inAvail, context);
        // Verify the expected output in context
        assertEquals(2, context.pos);
    }

    @Test
    void testEncodeWithInAvailNegativeAndModulusTwo() {
        // Test when inAvail is negative, modulus is 2, should encode 4 bits and add one padding.
        byte[] input = {};
        int inPos = 0;
        int inAvail = -1;
        context.eof = false;
        context.modulus = 2;
        context.ibitWorkArea = 1024;
        base64Codec.encode(input, inPos, inAvail, context);
        // Verify the expected output in context
        assertEquals(1, context.pos);
    }

    @Test
    void testEncodeWithValidInAvail() {
        // Test encoding with valid inAvail, should process all bytes and update context.
        // "Hello"
        byte[] input = { 72, 101, 108, 108, 111 };
        int inPos = 0;
        int inAvail = 5;
        base64Codec.encode(input, inPos, inAvail, context);
        // Verify the expected output in context
        assertEquals(4, context.pos);
    }

    @Test
    void testEncodeWithLineLengthHandling() {
        // Test encoding with line length, should insert line separator after reaching line length.
        // "Hello"
        byte[] input = { 72, 101, 108, 108, 111 };
        int inPos = 0;
        int inAvail = 5;
        context.currentLinePos = 4;
        base64Codec.encode(input, inPos, inAvail, context);
        // Verify the expected output in context
        // 4 bytes + line separator
        assertEquals(6, context.pos);
    }

    @Test
    void testEncodeWithEmptyInput() {
        // Test with empty input array, should return without processing and keep context unchanged.
        byte[] input = {};
        int inPos = 0;
        int inAvail = 0;
        base64Codec.encode(input, inPos, inAvail, context);
        // Verify that context remains unchanged
        assertEquals(0, context.pos);
    }

    @Test
    void testEncodeWithInAvailOne() {
        // Test with inAvail = 1, should process 1 byte and update context accordingly.
        // "A"
        byte[] input = { 65 };
        int inPos = 0;
        int inAvail = 1;
        base64Codec.encode(input, inPos, inAvail, context);
        // Verify the expected output in context
        assertEquals(2, context.pos);
    }

    @Test
    void testEncodeWithInAvailTwo() {
        // Test with inAvail = 2, should process 2 bytes and update context accordingly.
        // "AB"
        byte[] input = { 65, 66 };
        int inPos = 0;
        int inAvail = 2;
        base64Codec.encode(input, inPos, inAvail, context);
        // Verify the expected output in context
        assertEquals(3, context.pos);
    }

    @Test
    void testEncodeWithInAvailThree() {
        // Test with inAvail = 3, should process all bytes and update context accordingly.
        // "ABC"
        byte[] input = { 65, 66, 67 };
        int inPos = 0;
        int inAvail = 3;
        base64Codec.encode(input, inPos, inAvail, context);
        // Verify the expected output in context
        assertEquals(4, context.pos);
    }

    @Test
    void testEncodeWithContextAtEOFAndNonZeroModulus() {
        // Test with inAvail < 0, context.eof is false, and modulus is 1, should encode 2 bits and add padding.
        byte[] input = { 1, 2, 3 };
        int inPos = 0;
        int inAvail = -1;
        context.eof = false;
        context.modulus = 1;
        context.ibitWorkArea = 64;
        base64Codec.encode(input, inPos, inAvail, context);
        // Verify the expected output in context
        assertEquals(2, context.pos);
    }

    @Test
    void testEncodeWithContextModulusThree() {
        // Test when inAvail is negative, modulus is 3, should throw IllegalStateException.
        byte[] input = {};
        int inPos = 0;
        int inAvail = -1;
        context.eof = false;
        context.modulus = 3;
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            base64Codec.encode(input, inPos, inAvail, context);
        });
        // Verify the exception message
        assertEquals("Impossible modulus 3", exception.getMessage());
    }
}
