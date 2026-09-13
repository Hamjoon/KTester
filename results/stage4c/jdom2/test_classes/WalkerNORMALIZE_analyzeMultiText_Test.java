package org.jdom2.output.support;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.jdom2.Content;
import java.util.List;
import java.util.ArrayList;
import org.jdom2.Content.CType;
import org.jdom2.output.Format;
// Correct import for MultiText
import org.jdom2.output.support.AbstractFormattedWalker.MultiText;
// Correct import for Trim
import org.jdom2.output.support.AbstractFormattedWalker.Trim;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class WalkerNORMALIZE_analyzeMultiText_Test {

    private WalkerNORMALIZE walker;

    private MultiText mtext;

    private List<Content> contentList;

    private FormatStack formatStack;

    @BeforeEach
    void setupBeforeEach() {
        // Mock or initialize the required objects
        contentList = new ArrayList<>();
        Format format = mock(Format.class);
        formatStack = new FormatStack(format);
        walker = new WalkerNORMALIZE(contentList, formatStack, true);
        mtext = mock(MultiText.class);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources if needed
        contentList.clear();
    }

    // Helper method to create Content mock
    private Content createContentMock(CType type, String value) {
        Content content = mock(Content.class, RETURNS_DEEP_STUBS);
        when(content.getCType()).thenReturn(type);
        when(content.getValue()).thenReturn(value);
        return content;
    }

    // Test with Text Content that contains only XML whitespace
    @Test
    void testWithTextContentContainingOnlyXMLWhitespace() {
        // Arrange
        Content textContent = createContentMock(CType.Text, "   ");
        contentList.add(textContent);
        // Act
        walker.analyzeMultiText(mtext, 0, 1);
        // Assert
        verify(mtext, never()).appendText(any(), anyString());
    }

    // Test with Text Content containing non-whitespace text
    @Test
    void testWithTextContentContainingNonWhitespaceText() {
        // Arrange
        Content textContent = createContentMock(CType.Text, "Hello");
        contentList.add(textContent);
        // Act
        walker.analyzeMultiText(mtext, 0, 1);
        // Assert
        verify(mtext).appendText(eq(Trim.COMPACT), eq("Hello"));
    }

    // Test with CDATA Content that contains only XML whitespace
    @Test
    void testWithCDATAContentContainingOnlyXMLWhitespace() {
        // Arrange
        Content cdataContent = createContentMock(CType.CDATA, "   ");
        contentList.add(cdataContent);
        // Act
        walker.analyzeMultiText(mtext, 0, 1);
        // Assert
        verify(mtext, never()).appendCDATA(any(), anyString());
    }

    // Test with CDATA Content containing non-whitespace text
    @Test
    void testWithCDATAContentContainingNonWhitespaceText() {
        // Arrange
        Content cdataContent = createContentMock(CType.CDATA, "World");
        contentList.add(cdataContent);
        // Act
        walker.analyzeMultiText(mtext, 0, 1);
        // Assert
        verify(mtext).appendCDATA(eq(Trim.COMPACT), eq("World"));
    }

    // Test with EntityRef Content
    @Test
    void testWithEntityRefContent() {
        // Arrange
        Content entityRefContent = createContentMock(CType.EntityRef, "&entity;");
        contentList.add(entityRefContent);
        // Act
        walker.analyzeMultiText(mtext, 0, 1);
        // Assert
        verify(mtext).appendRaw(eq(entityRefContent));
    }

    // Test with a mix of Text, CDATA, and EntityRef Content
    @Test
    void testWithMixedContentTypes() {
        // Arrange
        Content textContent = createContentMock(CType.Text, "Hello");
        Content cdataContent = createContentMock(CType.CDATA, "World");
        Content entityRefContent = createContentMock(CType.EntityRef, "&entity;");
        contentList.addAll(Arrays.asList(textContent, cdataContent, entityRefContent));
        // Act
        walker.analyzeMultiText(mtext, 0, 3);
        // Assert
        verify(mtext).appendText(eq(Trim.COMPACT), eq("Hello"));
        verify(mtext).appendText(eq(Trim.NONE), eq(" "));
        verify(mtext).appendCDATA(eq(Trim.COMPACT), eq("World"));
        verify(mtext).appendText(eq(Trim.NONE), eq(" "));
        verify(mtext).appendRaw(eq(entityRefContent));
    }

    // Test with Text Content starting and ending with whitespace
    @Test
    void testWithTextContentStartingAndEndingWithWhitespace() {
        // Arrange
        Content textContent = createContentMock(CType.Text, "  Hello  ");
        contentList.add(textContent);
        // Act
        walker.analyzeMultiText(mtext, 0, 1);
        // Assert
        verify(mtext).appendText(eq(Trim.COMPACT), eq("  Hello  "));
    }

    // Test with multiple Text Contents with interspersed whitespace
    @Test
    void testWithMultipleTextContentsWithInterspersedWhitespace() {
        // Arrange
        Content textContent1 = createContentMock(CType.Text, "Hello");
        Content textContent2 = createContentMock(CType.Text, "   ");
        Content textContent3 = createContentMock(CType.Text, "World");
        contentList.addAll(Arrays.asList(textContent1, textContent2, textContent3));
        // Act
        walker.analyzeMultiText(mtext, 0, 3);
        // Assert
        verify(mtext).appendText(eq(Trim.COMPACT), eq("Hello"));
        verify(mtext).appendText(eq(Trim.NONE), eq(" "));
        verify(mtext).appendText(eq(Trim.COMPACT), eq("World"));
    }

    // Test with CDATA Content starting and ending with whitespace
    @Test
    void testWithCDATAContentStartingAndEndingWithWhitespace() {
        // Arrange
        Content cdataContent = createContentMock(CType.CDATA, "  World  ");
        contentList.add(cdataContent);
        // Act
        walker.analyzeMultiText(mtext, 0, 1);
        // Assert
        verify(mtext).appendCDATA(eq(Trim.COMPACT), eq("  World  "));
    }

    // Test with mixed Content types starting with whitespace
    @Test
    void testWithMixedContentTypesWithLeadingWhitespace() {
        // Arrange
        Content textContent = createContentMock(CType.Text, " ");
        Content cdataContent = createContentMock(CType.CDATA, "Hello");
        Content entityRefContent = createContentMock(CType.EntityRef, "&entity;");
        contentList.addAll(Arrays.asList(textContent, cdataContent, entityRefContent));
        // Act
        walker.analyzeMultiText(mtext, 0, 3);
        // Assert
        verify(mtext).appendText(eq(Trim.COMPACT), eq(" "));
        verify(mtext).appendCDATA(eq(Trim.COMPACT), eq("Hello"));
        verify(mtext).appendText(eq(Trim.NONE), eq(" "));
        verify(mtext).appendRaw(eq(entityRefContent));
    }

    // Test with Text Content containing special XML characters
    @Test
    void testWithTextContentContainingSpecialXMLCharacters() {
        // Arrange
        Content textContent = createContentMock(CType.Text, "<tag>");
        contentList.add(textContent);
        // Act
        walker.analyzeMultiText(mtext, 0, 1);
        // Assert
        verify(mtext).appendText(eq(Trim.COMPACT), eq("<tag>"));
    }

    // Test with no Content
    @Test
    void testWithNoContent() {
        // Act
        walker.analyzeMultiText(mtext, 0, 0);
        // Assert
        verify(mtext, never()).appendText(any(), anyString());
        verify(mtext, never()).appendCDATA(any(), anyString());
        verify(mtext, never()).appendRaw(any());
    }

    // Test with offset beyond the size of the Content list
    @Test
    void testWithOffsetBeyondListSize() {
        // Arrange
        Content textContent = createContentMock(CType.Text, "Hello");
        contentList.add(textContent);
        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> walker.analyzeMultiText(mtext, 10, 1));
    }

    // Test with len extending beyond the size of the Content list
    @Test
    void testWithLenExtendingBeyondListSize() {
        // Arrange
        Content textContent = createContentMock(CType.Text, "Hello");
        contentList.add(textContent);
        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> walker.analyzeMultiText(mtext, 0, 10));
    }

    // Test with null Content in the MultiText instance
    @Test
    void testWithNullContent() {
        // Arrange
        contentList.add(null);
        // Act & Assert
        assertThrows(NullPointerException.class, () -> walker.analyzeMultiText(mtext, 0, 1));
    }
}
