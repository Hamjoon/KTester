package org.jdom2.input.stax;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DTDParser_formatInternal_Test {

    private DTDParser dtdParser;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize DTDParser or any other necessary setup before each test
        // This will not work as DTDParser has a private constructor
        dtdParser = new DTDParser();
        // Instead, we will directly test the static method
    }

    // Helper method to invoke the private static method formatInternal using reflection
    private String invokeFormatInternal(String internal) {
        try {
            var method = DTDParser.class.getDeclaredMethod("formatInternal", String.class);
            // Make it accessible
            method.setAccessible(true);
            // Invoke the method with null for static methods
            return (String) method.invoke(null, internal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testFormatInternal_WithValidInput() {
        // Given
        String internal = "<!ENTITY foo 'bar' > <!ENTITY baz 'qux' >";
        // When
        String formatted = DTDParser.formatInternal(internal);
        // Then
        // Expected output based on the formatting logic
        String expected = "  <!ENTITY foo 'bar'>\n  <!ENTITY baz 'qux'>\n";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithLeadingWhitespace() {
        // Given
        String internal = "    <!ENTITY foo 'bar' >    ";
        // When
        String formatted = DTDParser.formatInternal(internal);
        // Then
        // Expected output based on the formatting logic
        String expected = "  <!ENTITY foo 'bar'>\n";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithNoInput() {
        // Given
        String internal = "";
        // When
        String formatted = DTDParser.formatInternal(internal);
        // Then
        // Expected output for empty input
        String expected = "";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithOnlyWhitespace() {
        // Given
        String internal = "   ";
        // When
        String formatted = invokeFormatInternal(internal);
        // Then
        // Expected output for only whitespace input
        String expected = "\n";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithSingleQuotedString() {
        // Given
        String internal = "'quoted text'";
        // When
        String formatted = invokeFormatInternal(internal);
        // Then
        // Expected output should be the same quoted string
        String expected = "'quoted text'";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithTextSurroundingDeclaration() {
        // Given
        String internal = "text < declaration > more text";
        // When
        String formatted = invokeFormatInternal(internal);
        // Then
        // Expected output with correct formatting
        String expected = "text  \ndeclaration\nmore text";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithTrailingWhitespaceAfterDeclaration() {
        // Given
        String internal = "text < declaration >  ";
        // When
        String formatted = invokeFormatInternal(internal);
        // Then
        // Expected output without trailing whitespace
        String expected = "text  \ndeclaration\n";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithMultipleDeclarations() {
        // Given
        String internal = "text < declaration > more text < another >";
        // When
        String formatted = invokeFormatInternal(internal);
        // Then
        // Expected output with multiple declarations
        String expected = "text  \ndeclaration\nmore text  \nanother\n";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithQuotedStringBetweenDeclarations() {
        // Given
        String internal = "text <declaration> 'quoted text' <another>";
        // When
        String formatted = invokeFormatInternal(internal);
        // Then
        // Expected output preserving quoted string
        String expected = "text  \ndeclaration\n 'quoted text'  \nanother\n";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithDeclarationAtStartAndTrailingWhitespace() {
        // Given
        String internal = "<declaration> 'quoted text' <another> ";
        // When
        String formatted = invokeFormatInternal(internal);
        // Then
        // Expected output handling declaration and whitespace
        String expected = "  \ndeclaration\n 'quoted text'  \nanother\n";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithSingleDeclarationOnly() {
        // Given
        String internal = "<declaration>";
        // When
        String formatted = invokeFormatInternal(internal);
        // Then
        // Expected output for single declaration
        String expected = "  \ndeclaration\n";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithTextSurroundingDeclarationWithoutTrailingWhitespace() {
        // Given
        String internal = "text <declaration> text";
        // When
        String formatted = invokeFormatInternal(internal);
        // Then
        // Expected output without additional newline
        String expected = "text  \ndeclaration\ntext";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithQuotedTextBeforeAndAfterDeclaration() {
        // Given
        String internal = "'quoted text' <declaration> 'more quoted text'";
        // When
        String formatted = invokeFormatInternal(internal);
        // Then
        // Expected output preserving quoted texts
        String expected = "'quoted text'  \ndeclaration\n 'more quoted text'";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithMultipleDeclarationsWithoutSurroundingText() {
        // Given
        String internal = "text <declaration> <another>";
        // When
        String formatted = invokeFormatInternal(internal);
        // Then
        // Expected output handling multiple declarations
        String expected = "text  \ndeclaration\n  \nanother\n";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithMultipleDeclarationsAndTrailingWhitespace() {
        // Given
        String internal = "text <declaration>   <another>   ";
        // When
        String formatted = invokeFormatInternal(internal);
        // Then
        // Expected output handling whitespace correctly
        String expected = "text  \ndeclaration\n  \nanother\n";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithDeclarationAndQuotedTextFollowedByMoreText() {
        // Given
        String internal = "text <declaration> 'quoted text' <another> text";
        // When
        String formatted = invokeFormatInternal(internal);
        // Then
        // Expected output preserving quoted text
        String expected = "text  \ndeclaration\n 'quoted text'  \nanother\ntext";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithEmptyStringInput() {
        // Given
        String internal = "";
        // When
        String formatted = invokeFormatInternal(internal);
        // Then
        // Expected output for empty input
        String expected = "";
        assertEquals(expected, formatted);
    }

    @Test
    void testFormatInternal_WithIncompleteDeclarationEndingWithLessThan() {
        // Given
        String internal = "text <declaration> text <";
        // When
        assertThrows(JDOMException.class, () -> invokeFormatInternal(internal), "Expected JDOMException for malformed input");
    }

    @Test
    void testFormatInternal_WithMalformedDeclarationEndingWithGreaterThan() {
        // Given
        String internal = "text <declaration> text >";
        // When
        assertThrows(JDOMException.class, () -> invokeFormatInternal(internal), "Expected JDOMException for malformed input");
    }

    @Test
    void testFormatInternal_WithNestedDeclarations() {
        // Given
        String internal = "text <declaration> text <another declaration>";
        // When
        assertThrows(JDOMException.class, () -> invokeFormatInternal(internal), "Expected JDOMException for malformed input");
    }

    @Test
    void testFormatInternal_WithDeclarationFollowedByQuotedStringAndAnotherDeclaration() {
        // Given
        String internal = "text <declaration> 'quoted text' <another declaration> text";
        // When
        assertThrows(JDOMException.class, () -> invokeFormatInternal(internal), "Expected JDOMException for malformed input");
    }
}
