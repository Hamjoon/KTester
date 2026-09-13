package org.jdom2.output.support;

import org.jdom2.Content;
import org.jdom2.Text;
import org.jdom2.Verifier;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import org.jdom2.output.support.AbstractFormattedWalker.MultiText;
import org.jdom2.output.support.AbstractFormattedWalker.Trim;

@ExtendWith(MockitoExtension.class)
class WalkerTRIM_analyzeMultiText_Test {

    private WalkerTRIM walkerTRIM;

    private MultiText mockMultiText;

    private List<Content> mockContent;

    private FormatStack mockFormatStack;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize mocks and the object under test
        mockMultiText = mock(MultiText.class);
        mockContent = mock(List.class);
        mockFormatStack = mock(FormatStack.class);
        walkerTRIM = new WalkerTRIM(mockContent, mockFormatStack, true);
    }

    @Test
    void testAnalyzeMultiText() {
        // Given
        int offset = 0;
        int len = 5;
        // When
        walkerTRIM.analyzeMultiText(mockMultiText, offset, len);
        // Then
        // Verify interactions or state changes
    }

    @Test
    void testAnalyzeMultiText_ZeroLength() {
        // Given
        int offset = 0;
        int len = 0;
        // When
        walkerTRIM.analyzeMultiText(mockMultiText, offset, len);
        // Then
        verifyNoInteractions(mockMultiText);
    }

    @Test
    void testAnalyzeMultiText_SingleWhitespaceText() {
        // Given
        int offset = 0;
        int len = 1;
        Content whitespaceText = mock(Text.class);
        when(whitespaceText.getValue()).thenReturn("   ");
        when(mockContent.get(offset)).thenReturn(whitespaceText);
        // When
        walkerTRIM.analyzeMultiText(mockMultiText, offset, len);
        // Then
        verifyNoInteractions(mockMultiText);
    }

    @Test
    void testAnalyzeMultiText_SingleNonWhitespaceText() {
        // Given
        int offset = 0;
        int len = 1;
        Content nonWhitespaceText = mock(Text.class);
        when(nonWhitespaceText.getValue()).thenReturn("content");
        when(mockContent.get(offset)).thenReturn(nonWhitespaceText);
        // When
        walkerTRIM.analyzeMultiText(mockMultiText, offset, len);
        // Then
        verify(mockMultiText).appendText(Trim.BOTH, "content");
    }

    @Test
    void testAnalyzeMultiText_TextAndCDATA() {
        // Given
        int offset = 0;
        int len = 2;
        Content textContent = mock(Text.class);
        Content cdataContent = mock(Content.class);
        when(textContent.getValue()).thenReturn("text");
        when(cdataContent.getValue()).thenReturn("cdata");
        when(textContent.getCType()).thenReturn(Content.CType.Text);
        when(cdataContent.getCType()).thenReturn(Content.CType.CDATA);
        when(mockContent.get(offset)).thenReturn(textContent);
        when(mockContent.get(offset + 1)).thenReturn(cdataContent);
        // When
        walkerTRIM.analyzeMultiText(mockMultiText, offset, len);
        // Then
        verify(mockMultiText).appendText(Trim.LEFT, "text");
        verify(mockMultiText).appendCDATA(Trim.RIGHT, "cdata");
    }

    @Test
    void testAnalyzeMultiText_EntityRefContent() {
        // Given
        int offset = 0;
        int len = 1;
        Content entityRefContent = mock(Content.class);
        when(entityRefContent.getCType()).thenReturn(Content.CType.EntityRef);
        when(mockContent.get(offset)).thenReturn(entityRefContent);
        // When
        walkerTRIM.analyzeMultiText(mockMultiText, offset, len);
        // Then
        verify(mockMultiText).appendRaw(entityRefContent);
    }

    @Test
    void testAnalyzeMultiText_MixedContents() {
        // Given
        int offset = 0;
        int len = 3;
        Content textContent = mock(Text.class);
        Content cdataContent = mock(Content.class);
        Content entityRefContent = mock(Content.class);
        when(textContent.getValue()).thenReturn("text");
        when(cdataContent.getValue()).thenReturn("cdata");
        when(textContent.getCType()).thenReturn(Content.CType.Text);
        when(cdataContent.getCType()).thenReturn(Content.CType.CDATA);
        when(entityRefContent.getCType()).thenReturn(Content.CType.EntityRef);
        when(mockContent.get(offset)).thenReturn(textContent);
        when(mockContent.get(offset + 1)).thenReturn(cdataContent);
        when(mockContent.get(offset + 2)).thenReturn(entityRefContent);
        // When
        walkerTRIM.analyzeMultiText(mockMultiText, offset, len);
        // Then
        verify(mockMultiText).appendText(Trim.LEFT, "text");
        verify(mockMultiText).appendCDATA(Trim.NONE, "cdata");
        verify(mockMultiText).appendRaw(entityRefContent);
    }

    @Test
    void testAnalyzeMultiText_NullMultiText() {
        // Given
        int offset = 0;
        int len = 1;
        // Expect NullPointerException
        assertThrows(NullPointerException.class, () -> {
            walkerTRIM.analyzeMultiText(null, offset, len);
        });
    }

    @Test
    void testAnalyzeMultiText_NegativeOffset() {
        // Given
        int offset = -1;
        int len = 1;
        // Expect IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> {
            walkerTRIM.analyzeMultiText(mockMultiText, offset, len);
        });
    }

    @Test
    void testAnalyzeMultiText_NegativeLength() {
        // Given
        int offset = 0;
        int len = -1;
        // Expect IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> {
            walkerTRIM.analyzeMultiText(mockMultiText, offset, len);
        });
    }

    @Test
    void testAnalyzeMultiText_OffsetBeyondSize() {
        // Given
        int offset = 10;
        int len = 1;
        when(mockContent.size()).thenReturn(5);
        // Expect IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> {
            walkerTRIM.analyzeMultiText(mockMultiText, offset, len);
        });
    }

    @Test
    void testAnalyzeMultiText_LengthExceedsSize() {
        // Given
        int offset = 0;
        int len = 10;
        when(mockContent.size()).thenReturn(5);
        // Expect IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> {
            walkerTRIM.analyzeMultiText(mockMultiText, offset, len);
        });
    }
}
