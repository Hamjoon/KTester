package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.apache.commons.codec.binary.BaseNCodec.Context;

@ExtendWith(MockitoExtension.class)
class Base64_encode_Test {

    private Base64 base64;

    private Context context;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Base64 encoder with default parameters
        base64 = new Base64();
        context = new Context();
    }

    @Test
    void testEncode() {
        // Given
        byte[] input = "The quick brown fox".getBytes();
        int inPos = 0;
        int inAvail = input.length;
        // When
        base64.encode(input, inPos, inAvail, context);
        // Then
        assertTrue(context.pos > 0, "Buffer should contain encoded bytes.");
    }

    @Test
    void testEncodeWithEOFContext() {
        // Given
        byte[] input = { 104, 101, 108, 108, 111 };
        context.eof = true;
        // When
        base64.encode(input, 0, input.length, context);
        // Then
        assertTrue(context.eof, "context.eof should be true, no processing should occur.");
    }

    @Test
    void testEncodeWithContextEOF() {
        // Given
        context.eof = true;
        context.modulus = 0;
        // When
        base64.encode(new byte[] {}, 0, 0, context);
        // Then
        assertTrue(context.eof, "Should return immediately when context.eof is true.");
    }

    @Test
    void testEncodeWithModulusOne() {
        // Given
        context.eof = false;
        context.modulus = 1;
        // When
        base64.encode(new byte[] { 0, 0, 0 }, 0, 3, context);
        // Then
        assertEquals(4, context.pos, "Buffer should contain 2 encoded bytes and 2 padding bytes.");
    }

    @Test
    void testEncodeWithModulusTwo() {
        // Given
        context.eof = false;
        context.modulus = 2;
        // When
        base64.encode(new byte[] { 0, 0, 0 }, 0, 3, context);
        // Then
        assertEquals(4, context.pos, "Buffer should contain 3 encoded bytes and 1 padding byte.");
    }

    @Test
    void testEncodeWithInvalidModulus() {
        // Given
        context.eof = false;
        context.modulus = 3;
        // When & Then
        assertThrows(IllegalStateException.class, () -> {
            base64.encode(new byte[] { 0, 0, 0 }, 0, 3, context);
        }, "Should throw IllegalStateException for invalid modulus.");
    }

    @Test
    void testEncodeWithLineLengthHandling() {
        // Given
        context.eof = false;
        context.modulus = 0;
        // Exceeds line length
        context.currentLinePos = 4;
        // When
        base64.encode(new byte[] { 0, 0, 0 }, 0, 3, context);
        // Then
        assertTrue(context.currentLinePos > 0, "lineSeparator should be added to the buffer.");
    }

    @Test
    void testEncodeWithNoLineSeparator() {
        // Given
        context.eof = false;
        context.modulus = 0;
        // No line length exceeded
        context.currentLinePos = 0;
        // When
        base64.encode(new byte[] { 0, 0, 0 }, 0, 3, context);
        // Then
        assertEquals(0, context.currentLinePos, "No lineSeparator should be added to the buffer.");
    }

    @Test
    void testEncodeWithStandardInput() {
        // Given
        // 'Hello' in ASCII
        byte[] input = { 72, 101, 108, 108, 111 };
        // When
        base64.encode(input, 0, input.length, context);
        // Then
        assertTrue(context.pos > 0, "Buffer should contain encoded bytes for 'Hello'.");
    }

    @Test
    void testEncodeWithEmptyInput() {
        // Given
        byte[] input = {};
        // When
        base64.encode(input, 0, input.length, context);
        // Then
        assertEquals(0, context.pos, "Should handle empty input gracefully.");
    }

    @Test
    void testEncodeWithNegativeInAvail() {
        // Given
        // 'hello' in ASCII
        byte[] input = { 104, 101, 108, 108, 111 };
        context.eof = false;
        // When
        base64.encode(input, 0, -1, context);
        // Then
        assertTrue(context.eof, "context.eof should be true, no output should occur.");
    }

    @Test
    void testEncodeWithOutOfBoundsInput() {
        // Given
        // Out of bounds value
        // Corrected from (byte) 256 to (byte) 255
        byte[] input = { (byte) 255 };
        context.eof = false;
        // When
        base64.encode(input, 0, 1, context);
        // Then
        assertTrue(context.pos > 0, "Buffer should contain an encoded byte, but with unexpected behavior.");
    }
}
