package org.apache.commons.codec.binary;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@ExtendWith(MockitoExtension.class)
class Base32_decode_Test {

    private Base32 base32;

    private BaseNCodec.Context context;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize Base32 codec and Context before each test
        base32 = new Base32();
        context = new BaseNCodec.Context();
    }

    // Provide test cases for valid input
    static Stream<Arguments> decodeValidInputProvider() {
        return Stream.of(// Valid input
        Arguments.of("MY======", "f"), // Valid input with padding
        Arguments.of("MZXW6YTB", "fooba"));
    }

    // Provide test cases for padding input
    static Stream<Arguments> decodePaddingInputProvider() {
        return // Ends with padding character '='
        Stream.// Ends with padding character '='
        of(// No padding
        Arguments.of(new byte[] { 0, 1, 2, 3, 4, 5, 6, 61 }, 0, 8, true), Arguments.of(new byte[] { 0, 1, 2, 3, 4, 5, 6, 7 }, 0, 8, false));
    }

    // Provide test cases for invalid input
    static Stream<Arguments> decodeInvalidInputProvider() {
        return Stream.of(// Invalid character (8)
        Arguments.of("MC======"), // Invalid trailing character
        Arguments.of("MZXW6YTB8"));
    }

    @Test
    void testDecodeValidInput() {
        byte[] input = inputString.getBytes(StandardCharsets.UTF_8);
        byte[] expectedOutput = expectedOutputString.getBytes(StandardCharsets.UTF_8);
        base32.decode(input, 0, input.length, context);
        byte[] output = new byte[expectedOutput.length];
        // Reset position
        context.pos = 0;
        base32.readResults(output, 0, output.length, context);
        assertArrayEquals(expectedOutput, output);
    }

    @Test
    void testDecodeWithPadding() {
        byte[] input = "MZXW6YTB".getBytes(StandardCharsets.UTF_8);
        byte[] expectedOutput = "fooba".getBytes(StandardCharsets.UTF_8);
        base32.decode(input, 0, input.length, context);
        byte[] output = new byte[expectedOutput.length];
        // Reset position
        context.pos = 0;
        base32.readResults(output, 0, output.length, context);
        assertArrayEquals(expectedOutput, output);
    }

    @Test
    void testDecodeInvalidInput() {
        byte[] input = inputString.getBytes(StandardCharsets.UTF_8);
        assertThrows(IllegalStateException.class, () -> {
            base32.decode(input, 0, input.length, context);
        });
    }
}
