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

    @ParameterizedTest
    @MethodSource("decodeValidInputProvider")
    void testDecodeValidInput(byte[] input, int inPos, int inAvail, int expectedPos) {
        base32.decode(input, inPos, inAvail, context);
        assertEquals(expectedPos, context.pos, "Context position should be updated correctly.");
    }

    @ParameterizedTest
    @MethodSource("decodePaddingInputProvider")
    void testDecodeWithPadding(byte[] input, int inPos, int inAvail, boolean expectedEof) {
        base32.decode(input, inPos, inAvail, context);
        assertEquals(expectedEof, context.eof, "Context EOF should be updated correctly.");
    }

    @ParameterizedTest
    @MethodSource("decodeInvalidInputProvider")
    void testDecodeInvalidInput(byte[] input, int inPos, int inAvail) {
        assertThrows(IllegalStateException.class, () -> {
            base32.decode(input, inPos, inAvail, context);
        }, "Exception should be raised for invalid input.");
    }

    // Provide test cases for valid input
    static Stream<Arguments> decodeValidInputProvider() {
        return Stream.of(
            Arguments.of(new byte[]{0, 1, 2, 3, 4, 5, 6, 7}, 0, 8, 5), // Valid input
            Arguments.of(new byte[]{0, 1, 2, 3, 4, 5, 6, 61}, 0, 8, 5) // Valid input with padding
        );
    }

    // Provide test cases for padding input
    static Stream<Arguments> decodePaddingInputProvider() {
        return Stream.of(
            Arguments.of(new byte[]{0, 1, 2, 3, 4, 5, 6, 61}, 0, 8, true), // Ends with padding character '='
            Arguments.of(new byte[]{0, 1, 2, 3, 4, 5, 6, 7}, 0, 8, false) // No padding
        );
    }

    // Provide test cases for invalid input
    static Stream<Arguments> decodeInvalidInputProvider() {
        return Stream.of(
            Arguments.of(new byte[]{0, 1, 2, 3, 4, 5, 6, 8}, 0, 8), // Invalid character (8)
            Arguments.of(new byte[]{0, 1, 2, 3, 4, 5, 6, 61}, 0, 8) // Invalid trailing character
        );
    }

    // Additional test cases can be added here as needed
}
```