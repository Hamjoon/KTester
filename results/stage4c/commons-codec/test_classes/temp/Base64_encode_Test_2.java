package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.apache.commons.codec.binary.BaseNCodec.Context;

@ExtendWith(MockitoExtension.class)
class Base64_encode_Test {

    private Base64 base64;

    private BaseNCodec.Context context;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize Base64 object and Context before each test
        base64 = new Base64();
        context = new BaseNCodec.Context();
    }

    @Test
    void testEncode() {
        // Given: Initializing input data
        byte[] input = "Test".getBytes();
        int inPos = 0;
        int inAvail = input.length;
        // When: Encoding the input
        base64.encode(input, inPos, inAvail, context);
        // Ensure EOF is handled
        base64.encode(input, inPos, -1, context);
        // Then: Verify the expected outcome
        assertNotNull(context);
    }

    @Test
    void testEncode_EmptyInputEOF() {
        byte[] input = new byte[] {};
        int inPos = 0;
        int inAvail = -1;
        base64.encode(input, inPos, inAvail, context);
        assertTrue(context.eof);
        assertEquals(0, context.pos);
    }

    @Test
    void testEncode_3ByteInput() {
        byte[] input = new byte[] { 0, 1, 2 };
        int inPos = 0;
        int inAvail = 3;
        base64.encode(input, inPos, inAvail, context);
        // Ensure EOF is handled
        base64.encode(input, inPos, -1, context);
        assertEquals(4, context.pos);
        assertEquals(4, context.currentLinePos);
    }

    @Test
    void testEncode_2ByteInput() {
        byte[] input = new byte[] { 0, 1 };
        int inPos = 0;
        int inAvail = 2;
        base64.encode(input, inPos, inAvail, context);
        // Ensure EOF is handled
        base64.encode(input, inPos, -1, context);
        assertEquals(4, context.pos);
        assertEquals(4, context.currentLinePos);
    }

    @Test
    void testEncode_1ByteInput() {
        byte[] input = new byte[] { 0 };
        int inPos = 0;
        int inAvail = 1;
        base64.encode(input, inPos, inAvail, context);
        // Ensure EOF is handled
        base64.encode(input, inPos, -1, context);
        assertEquals(4, context.pos);
        assertEquals(4, context.currentLinePos);
    }

    @Test
    void testEncode_4ByteInput() {
        byte[] input = new byte[] { 0, 1, 2, 3 };
        int inPos = 0;
        int inAvail = 4;
        base64.encode(input, inPos, inAvail, context);
        // Ensure EOF is handled
        base64.encode(input, inPos, -1, context);
        assertEquals(8, context.pos);
        assertEquals(8, context.currentLinePos);
    }

    @Test
    void testEncode_NegativeBytes() {
        byte[] input = new byte[] { -1, -2, -3 };
        int inPos = 0;
        int inAvail = 3;
        base64.encode(input, inPos, inAvail, context);
        // Ensure EOF is handled
        base64.encode(input, inPos, -1, context);
        assertEquals(4, context.pos);
        assertEquals(4, context.currentLinePos);
    }

    @Test
    void testEncode_LeftoverBytesEOF() {
        byte[] input = new byte[] { 0, 1, 2 };
        int inPos = 0;
        int inAvail = -1;
        base64.encode(input, inPos, inAvail, context);
        assertTrue(context.eof);
        assertEquals(4, context.pos);
    }

    @Test
    void testEncode_6ByteInput() {
        byte[] input = new byte[] { 0, 1, 2, 3, 4, 5 };
        int inPos = 0;
        int inAvail = 6;
        base64.encode(input, inPos, inAvail, context);
        // Ensure EOF is handled
        base64.encode(input, inPos, -1, context);
        assertEquals(8, context.pos);
        assertEquals(8, context.currentLinePos);
    }

    @Test
    void testEncode_10ByteInput() {
        byte[] input = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int inPos = 0;
        int inAvail = 10;
        base64.encode(input, inPos, inAvail, context);
        // Ensure EOF is handled
        base64.encode(input, inPos, -1, context);
        assertEquals(16, context.pos);
        assertEquals(16, context.currentLinePos);
    }

    @Test
    void testEncode_MixedValues() {
        byte[] input = new byte[] { 127, -128, 0, 1, -1, 2, -2, 3 };
        int inPos = 0;
        int inAvail = 8;
        base64.encode(input, inPos, inAvail, context);
        // Ensure EOF is handled
        base64.encode(input, inPos, -1, context);
        assertEquals(12, context.pos);
        assertEquals(12, context.currentLinePos);
    }

    @Test
    void testEncode_16ByteInput() {
        byte[] input = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        int inPos = 0;
        int inAvail = 16;
        base64.encode(input, inPos, inAvail, context);
        // Ensure EOF is handled
        base64.encode(input, inPos, -1, context);
        assertEquals(24, context.pos);
        assertEquals(24, context.currentLinePos);
    }

    @Test
    void testEncode_32ByteInput() {
        byte[] input = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 };
        int inPos = 0;
        int inAvail = 32;
        base64.encode(input, inPos, inAvail, context);
        // Ensure EOF is handled
        base64.encode(input, inPos, -1, context);
        assertEquals(44, context.pos);
        assertEquals(44, context.currentLinePos);
    }

    @Test
    void testEncode_NullContext() {
        byte[] input = new byte[] { 0, 1, 2 };
        int inPos = 0;
        int inAvail = 3;
        assertThrows(NullPointerException.class, () -> base64.encode(input, inPos, inAvail, null));
    }

    @Test
    void testEncode_NullInput() {
        int inPos = 0;
        int inAvail = 3;
        assertThrows(NullPointerException.class, () -> base64.encode(null, inPos, inAvail, context));
    }

    @Test
    void testEncode_NegativeInPos() {
        byte[] input = new byte[] { 0, 1, 2 };
        int inPos = -1;
        int inAvail = 3;
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> base64.encode(input, inPos, inAvail, context));
    }

    @Test
    void testEncode_InPosGreaterThanLength() {
        byte[] input = new byte[] { 0, 1, 2 };
        int inPos = 4;
        int inAvail = 3;
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> base64.encode(input, inPos, inAvail, context));
    }

    @Test
    void testEncode_InAvailGreaterThanLengthFromInPos() {
        byte[] input = new byte[] { 0, 1, 2 };
        int inPos = 0;
        int inAvail = 4;
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> base64.encode(input, inPos, inAvail, context));
    }
}
