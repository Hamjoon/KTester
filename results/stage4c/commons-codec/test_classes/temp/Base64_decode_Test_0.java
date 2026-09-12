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

    @Test
    void testDecodeWithValidInput() {
        // Given
        byte[] input = new byte[] { 0, 1, 2, 3 };
        int inPos = 0;
        int inAvail = 4;
        // When
        base64.decode(input, inPos, inAvail, context);
        // Then
        assertFalse(context.eof, "Expected context.eof to be false with valid input.");
        // Additional assertions can be added here to verify the buffer is filled correctly
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

    @Test
    void testDecodeWithNegativeInAvail() {
        // Given
        byte[] input = new byte[] {};
        int inPos = 0;
        int inAvail = -1;
        // When
        base64.decode(input, inPos, inAvail, context);
        // Then
        assertTrue(context.eof, "Expected context.eof to be true when inAvail is negative.");
    }

    @Test
    void testDecodeWithPaddingCharacter() {
        // Given
        // '=' character
        byte[] input = new byte[] { 61 };
        int inPos = 0;
        int inAvail = 1;
        // When
        base64.decode(input, inPos, inAvail, context);
        // Then
        assertTrue(context.eof, "Expected context.eof to be true when input contains padding character '='.");
    }

    @Test
    void testDecodeWithFullBuffer() {
        // Given
        byte[] input = new byte[] { 5, 6, 7, 8 };
        int inPos = 0;
        int inAvail = 4;
        // When
        base64.decode(input, inPos, inAvail, context);
        // Then
        assertEquals(0, context.modulus, "Expected context.modulus to be 0 after processing full buffer.");
        // Additional assertions can be added here to verify the buffer is filled correctly
    }

    @Test
    void testDecodeWithModulusTwo() {
        // Given
        byte[] input = new byte[] { 5, 6 };
        int inPos = 0;
        int inAvail = 2;
        // When
        base64.decode(input, inPos, inAvail, context);
        // Then
        // Use Mockito to verify private method calls
        Base64 spyBase64 = spy(base64);
        doNothing().when(spyBase64).validateCharacter(anyInt(), any(Context.class));
        spyBase64.decode(input, inPos, inAvail, context);
        verify(spyBase64).validateCharacter(Base64.MASK_4BITS, context);
    }

    @Test
    void testDecodeWithModulusThree() {
        // Given
        byte[] input = new byte[] { 5, 6, 7 };
        int inPos = 0;
        int inAvail = 3;
        // When
        base64.decode(input, inPos, inAvail, context);
        // Then
        // Use Mockito to verify private method calls
        Base64 spyBase64 = spy(base64);
        doNothing().when(spyBase64).validateCharacter(anyInt(), any(Context.class));
        spyBase64.decode(input, inPos, inAvail, context);
        verify(spyBase64).validateCharacter(Base64.MASK_2BITS, context);
    }

    @Test
    void testDecodeWithModulusOne() {
        // Given
        byte[] input = new byte[] { 5 };
        int inPos = 0;
        int inAvail = 1;
        // When
        base64.decode(input, inPos, inAvail, context);
        // Then
        // Use Mockito to verify private method calls
        Base64 spyBase64 = spy(base64);
        doNothing().when(spyBase64).validateTrailingCharacter();
        spyBase64.decode(input, inPos, inAvail, context);
        verify(spyBase64).validateTrailingCharacter();
    }

    @Test
    void testDecodeWithEightBytes() {
        // Given
        byte[] input = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7 };
        int inPos = 0;
        int inAvail = 8;
        // When
        base64.decode(input, inPos, inAvail, context);
        // Then
        assertEquals(0, context.modulus, "Expected context.modulus to be 0 after processing 8 bytes.");
        // Additional assertions can be added here to verify the buffer is filled correctly
    }

    @Test
    void testDecodeWithFiveBytes() {
        // Given
        byte[] input = new byte[] { 5, 6, 7, 8, 9 };
        int inPos = 0;
        int inAvail = 5;
        // When
        base64.decode(input, inPos, inAvail, context);
        // Then
        // Use Mockito to verify private method calls
        Base64 spyBase64 = spy(base64);
        doNothing().when(spyBase64).validateCharacter(anyInt(), any(Context.class));
        spyBase64.decode(input, inPos, inAvail, context);
        verify(spyBase64).validateCharacter(Base64.MASK_4BITS, context);
    }

    @Test
    void testDecodeWithSixBytes() {
        // Given
        byte[] input = new byte[] { 5, 6, 7, 8, 9, 10 };
        int inPos = 0;
        int inAvail = 6;
        // When
        base64.decode(input, inPos, inAvail, context);
        // Then
        // Use Mockito to verify private method calls
        Base64 spyBase64 = spy(base64);
        doNothing().when(spyBase64).validateCharacter(anyInt(), any(Context.class));
        spyBase64.decode(input, inPos, inAvail, context);
        verify(spyBase64).validateCharacter(Base64.MASK_2BITS, context);
    }

    @Test
    void testDecodeWithPaddingAtEnd() {
        // Given
        // '=' characters at the end
        byte[] input = new byte[] { 0, 1, 2, 3, 61, 61 };
        int inPos = 0;
        int inAvail = 6;
        // When
        base64.decode(input, inPos, inAvail, context);
        // Then
        assertTrue(context.eof, "Expected context.eof to be true after processing padding characters.");
    }

    @Test
    void testDecodeWithInvalidByte() {
        // Given
        // Invalid byte outside decodeTable range
        byte[] input = new byte[] { 127 };
        int inPos = 0;
        int inAvail = 1;
        // When
        base64.decode(input, inPos, inAvail, context);
        // Then
        assertFalse(context.eof, "Expected context.eof to be false with invalid byte.");
        // No valid data should be processed
    }

    @Test
    void testDecodeWithByteOutsideRange() {
        // Given
        // Byte value outside the valid range of decodeTable
        byte[] input = new byte[] { 128 };
        int inPos = 0;
        int inAvail = 1;
        // When
        base64.decode(input, inPos, inAvail, context);
        // Then
        assertFalse(context.eof, "Expected context.eof to be false with byte outside valid range.");
        // No valid data should be processed
    }

    @Test
    void testDecodeWithImpossibleModulus() {
        // Given
        // Input that results in a modulus of 4
        byte[] input = new byte[] { 5, 6, 7, 8, 9, 10, 11 };
        int inPos = 0;
        int inAvail = 7;
        // When & Then
        assertThrows(IllegalStateException.class, () -> base64.decode(input, inPos, inAvail, context), "Expected IllegalStateException with message 'Impossible modulus 4'.");
    }
}
