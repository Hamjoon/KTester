package org.jdom2.input.stax;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.jdom2.JDOMFactory;
import org.jdom2.DocType;
import org.jdom2.JDOMException;

@ExtendWith(MockitoExtension.class)
class DTDParser_formatInternal_Test {

    private JDOMFactory factory;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the JDOMFactory mock before each test
        factory = mock(JDOMFactory.class);
    }

    // Helper method to invoke the private formatInternal method using reflection
    private String invokeFormatInternal(String input) {
        try {
            java.lang.reflect.Method method = DTDParser.class.getDeclaredMethod("formatInternal", String.class);
            method.setAccessible(true);
            return (String) method.invoke(null, input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testFormatInternalWithValidInput() throws JDOMException {
        // Given
        String input = "<!DOCTYPE example SYSTEM \"example.dtd\" [\n" + "    <!ENTITY example \"Example\">\n" + "]>";
        // Mock the behavior of the factory
        DocType docTypeMock = mock(DocType.class);
        when(factory.docType(anyString(), anyString(), anyString())).thenReturn(docTypeMock);
        // When
        DocType result = DTDParser.parse(input, factory);
        // Then
        assertNotNull(result);
        verify(factory).docType("example", null, "example.dtd");
        verify(docTypeMock).setInternalSubset(anyString());
    }

    @Test
    void testFormatInternalWithEmptyInput() throws JDOMException {
        // Given
        String input = "<!DOCTYPE example>";
        // Mock the behavior of the factory
        DocType docTypeMock = mock(DocType.class);
        when(factory.docType(anyString(), anyString(), anyString())).thenReturn(docTypeMock);
        // When
        DocType result = DTDParser.parse(input, factory);
        // Then
        assertNotNull(result);
        verify(factory).docType("example", null, null);
        verify(docTypeMock, never()).setInternalSubset(anyString());
    }

    @Test
    void testFormatInternalWithWhitespaceInput() {
        // Test with a string containing only whitespace. The output should be a single space.
        String input = "   ";
        String expected = " ";
        // When
        String result = invokeFormatInternal(input);
        // Then
        assertEquals(expected, result);
    }

    @Test
    void testFormatInternalWithNormalString() {
        // Test with a normal string without whitespace or quotes. The output should be the same string.
        String input = "text";
        String expected = "text";
        // When
        String result = invokeFormatInternal(input);
        // Then
        assertEquals(expected, result);
    }

    @Test
    void testFormatInternalWithSingleQuotedText() {
        // Test with a string containing single quotes. The output should match the input as the quotes are preserved.
        String input = "'quoted text'";
        String expected = "'quoted text'";
        // When
        String result = invokeFormatInternal(input);
        // Then
        assertEquals(expected, result);
    }

    @Test
    void testFormatInternalWithDoubleQuotedText() {
        // Test with a string containing double quotes. The output should match the input as the quotes are preserved.
        String input = "\"quoted text\"";
        String expected = "\"quoted text\"";
        // When
        String result = invokeFormatInternal(input);
        // Then
        assertEquals(expected, result);
    }

    @Test
    void testFormatInternalWithDeclaration() {
        // Test with a string containing a declaration. The output should have new lines added after the declaration.
        String input = "<declaration>  </declaration>";
        String expected = "  <declaration>\n  </declaration>\n";
        // When
        String result = invokeFormatInternal(input);
        // Then
        assertEquals(expected, result);
    }

    @Test
    void testFormatInternalWithTagAndText() {
        // Test with a string containing a tag with text. The output should preserve the tag and text while adding new line after the tag.
        String input = "<tag> text </tag>";
        String expected = "  <tag> text </tag>\n";
        // When
        String result = invokeFormatInternal(input);
        // Then
        assertEquals(expected, result);
    }

    @Test
    void testFormatInternalWithTextAroundTag() {
        // Test with a string containing text before and after a tag. The output should preserve the text and format the tag correctly.
        String input = "text <tag> more text </tag> text";
        String expected = "text  <tag> more text </tag>\n text";
        // When
        String result = invokeFormatInternal(input);
        // Then
        assertEquals(expected, result);
    }

    @Test
    void testFormatInternalWithNestedTag() {
        // Test with a nested tag inside a tag. The output should format the nested structure correctly.
        String input = "<tag> text <nested> more text </nested> </tag>";
        String expected = "  <tag> text <nested> more text </nested> </tag>\n";
        // When
        String result = invokeFormatInternal(input);
        // Then
        assertEquals(expected, result);
    }

    @Test
    void testFormatInternalWithEmptyNestedTag() {
        // Test with a tag that contains an empty nested tag. The output should format the nested structure correctly with new line after the tag.
        String input = "<tag> text <nested> </nested> </tag>";
        String expected = "  <tag> text <nested> </nested> </tag>\n";
        // When
        String result = invokeFormatInternal(input);
        // Then
        assertEquals(expected, result);
    }

    @Test
    void testFormatInternalWithQuotedTextInTag() {
        // Test with a tag containing quoted text. The output should preserve the quoted text and format the tag with a new line.
        String input = "<tag> 'quoted text' </tag>";
        String expected = "  <tag> 'quoted text' </tag>\n";
        // When
        String result = invokeFormatInternal(input);
        // Then
        assertEquals(expected, result);
    }

    @Test
    void testFormatInternalWithDoubleQuotedTextInTag() {
        // Test with a tag containing double-quoted text. The output should preserve the double quotes and format the tag with a new line.
        String input = "<tag> \"quoted text\" </tag>";
        String expected = "  <tag> \"quoted text\" </tag>\n";
        // When
        String result = invokeFormatInternal(input);
        // Then
        assertEquals(expected, result);
    }

    @Test
    void testFormatInternalWithTextAroundNestedTags() {
        // Test with a string containing text around nested tags. The output should preserve text and format the nested tags correctly.
        String input = "text <tag> text <tag> nested </tag> </tag> text";
        String expected = "text  <tag> text <tag> nested </tag> </tag>\n text";
        // When
        String result = invokeFormatInternal(input);
        // Then
        assertEquals(expected, result);
    }

    @Test
    void testFormatInternalWithMultipleTags() {
        // Test with multiple tags. The output should format each tag correctly with new lines.
        String input = "<tag> text <tag> more text </tag> </tag> <tag> another </tag>";
        String expected = "  <tag> text <tag> more text </tag> </tag>\n  <tag> another </tag>\n";
        // When
        String result = invokeFormatInternal(input);
        // Then
        assertEquals(expected, result);
    }

    @Test
    void testFormatInternalWithComplexNestedStructure() {
        // Test with a complex nested structure. The output should correctly format the nested tags with appropriate new lines.
        String input = "<tag> text <nested> more text <inner> inner text </inner> </nested> </tag>";
        String expected = "  <tag> text <nested> more text <inner> inner text </inner> </nested> </tag>\n";
        // When
        String result = invokeFormatInternal(input);
        // Then
        assertEquals(expected, result);
    }
}
