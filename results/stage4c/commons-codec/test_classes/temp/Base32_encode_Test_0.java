package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.apache.commons.codec.CodecPolicy;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class Base32_encode_Test {

    private Base32 base32;

    private BaseNCodec.Context context;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Base32 instance and Context before each test
        base32 = new Base32();
        context = new BaseNCodec.Context();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources after each test if necessary
        base32 = null;
        context = null;
    }

    @Test
    void testEncodeWithEmptyInput() {
        // Given
        byte[] input = new byte[0];
        byte[] expectedOutput = new byte[0];
        // When
        base32.encode(input, 0, input.length, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        // Then
        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    void testEncodeWithSingleByteInput() {
        // Given
        byte[] input = new byte[] { (byte) 'f' };
        byte[] expectedOutput = "MY======".getBytes();
        // When
        base32.encode(input, 0, input.length, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        // Then
        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    void testEncodeWithMultipleBytesInput() {
        // Given
        byte[] input = "foobar".getBytes();
        byte[] expectedOutput = "MZXW6YTBOI======".getBytes();
        // When
        base32.encode(input, 0, input.length, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        // Then
        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    void testEOFConditionWithNoLeftoversAndNoChunking() {
        // Given
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        // When
        base32.encode(null, 0, -1, context);
        // Then
        assertTrue(context.eof);
        assertEquals(0, context.pos);
    }

    @Test
    void testEOFConditionWithModulus1() {
        // Given
        context.eof = false;
        context.modulus = 1;
        context.pos = 0;
        // When
        base32.encode(null, 0, -1, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        // Then
        assertTrue(context.eof);
        // Expect 8 bytes due to padding
        assertEquals(8, context.pos);
    }

    @Test
    void testEOFConditionWithModulus2() {
        // Given
        context.eof = false;
        context.modulus = 2;
        context.pos = 0;
        // When
        base32.encode(null, 0, -1, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        // Then
        assertTrue(context.eof);
        // Expect 8 bytes due to padding
        assertEquals(8, context.pos);
    }

    @Test
    void testEOFConditionWithModulus3() {
        // Given
        context.eof = false;
        context.modulus = 3;
        context.pos = 0;
        // When
        base32.encode(null, 0, -1, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        // Then
        assertTrue(context.eof);
        // Expect 8 bytes due to padding
        assertEquals(8, context.pos);
    }

    @Test
    void testEOFConditionWithModulus4() {
        // Given
        context.eof = false;
        context.modulus = 4;
        context.pos = 0;
        // When
        base32.encode(null, 0, -1, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        // Then
        assertTrue(context.eof);
        // Expect 8 bytes due to padding
        assertEquals(8, context.pos);
    }

    @Test
    void testNormalProcessingWithFullBlockOf5Bytes() {
        // Given
        byte[] input = "hello".getBytes();
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        // When
        base32.encode(input, 0, input.length, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        // Then
        assertEquals(8, context.pos);
        assertEquals(0, context.modulus);
    }

    @Test
    void testProcessingWithPartialBlockOfBytes() {
        // Given
        byte[] input = "abc".getBytes();
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        // When
        base32.encode(input, 0, input.length, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        // Then
        assertEquals(3, context.modulus);
    }

    @Test
    void testNormalProcessingWithTwoFullBlocks() {
        // Given
        byte[] input = "hellohello".getBytes();
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        // When
        base32.encode(input, 0, input.length, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        // Then
        assertEquals(16, context.pos);
        assertEquals(0, context.modulus);
    }

    @Test
    void testProcessingWithOneFullBlockAndOneAdditionalByte() {
        // Given
        byte[] input = "helloa".getBytes();
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        // When
        base32.encode(input, 0, input.length, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        // Then
        assertEquals(1, context.modulus);
    }

    @Test
    void testProcessingWithOneFullBlockAndTwoAdditionalBytes() {
        // Given
        byte[] input = "helloa".getBytes();
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        // When
        base32.encode(input, 0, input.length, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        // Then
        assertEquals(1, context.modulus);
    }

    @Test
    void testProcessingWithOneFullBlockAndThreeAdditionalBytes() {
        // Given
        byte[] input = "helloabc".getBytes();
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        // When
        base32.encode(input, 0, input.length, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        // Then
        assertEquals(3, context.modulus);
    }

    @Test
    void testProcessingWithOneFullBlockAndFourAdditionalBytes() {
        // Given
        byte[] input = "helloabcd".getBytes();
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        // When
        base32.encode(input, 0, input.length, context);
        byte[] actualOutput = new byte[context.pos];
        System.arraycopy(context.buffer, 0, actualOutput, 0, context.pos);
        // Then
        assertEquals(4, context.modulus);
    }

    @Test
    void testScenarioWithImpossibleModulus() {
        // Given
        byte[] input = "hello".getBytes();
        context.eof = false;
        context.modulus = 5;
        context.pos = 0;
        // When & Then
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            base32.encode(input, 0, input.length, context);
        });
        assertEquals("Impossible modulus 5", exception.getMessage());
    }

    @Test
    void testScenarioWithNullInputAndZeroInAvail() {
        // Given
        context.eof = false;
        context.modulus = 0;
        context.pos = 0;
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            base32.encode(null, 0, 0, context);
        });
    }
}
