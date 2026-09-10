package org.jdom2.output.support;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.jdom2.output.support.FormatStack;
import org.jdom2.Content;
import org.jdom2.Text;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.CDATA;
import org.jdom2.EntityRef;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WalkerTRIM_analyzeMultiText_Test {

    private WalkerTRIM walkerTRIM;

    private FormatStack formatStack;

    private MultiText multiText;

    private List<Content> contentList;

    @BeforeEach
    void setupBeforeEach() {
        // Create a Format object (assuming a default one exists)
        // Assuming a no-arg constructor exists
        formatStack = new FormatStack();
        walkerTRIM = new WalkerTRIM(new ArrayList<>(), formatStack, true);
        // Instantiate MultiText directly
        multiText = new WalkerTRIM.MultiText();
        contentList = new ArrayList<>();
    }

    @Test
    void testAnalyzeMultiText() {
        // Given: Set up the test conditions
        walkerTRIM.analyzeMultiText(multiText, 0, contentList.size());
        // Then: Assert the expected outcomes
    }

    @Test
    void testAnalyzeMultiText_EmptyContent() {
        walkerTRIM.analyzeMultiText(multiText, 0, 0);
        assertEquals(0, multiText.size());
    }

    @Test
    void testAnalyzeMultiText_TextContent() {
        contentList.add(new Text("Hello"));
        walkerTRIM.analyzeMultiText(multiText, 0, 1);
        assertEquals(1, multiText.size());
        assertEquals("Hello", multiText.getText(0));
    }

    @Test
    void testAnalyzeMultiText_CDATAContent() {
        contentList.add(new CDATA("CDATA Content"));
        walkerTRIM.analyzeMultiText(multiText, 0, 1);
        assertEquals(1, multiText.size());
        assertEquals("CDATA Content", multiText.getCDATA(0));
    }

    @Test
    void testAnalyzeMultiText_MixedContent() {
        contentList.add(new Text("Text Content"));
        contentList.add(new CDATA("CDATA Content"));
        contentList.add(new EntityRef("EntityRef"));
        walkerTRIM.analyzeMultiText(multiText, 0, 3);
        assertEquals(3, multiText.size());
        assertEquals("Text Content", multiText.getText(0));
        assertEquals("CDATA Content", multiText.getCDATA(1));
        assertEquals("EntityRef", multiText.getRaw(2));
    }

    @Test
    void testAnalyzeMultiText_WhitespaceContent() {
        contentList.add(new Text("   "));
        walkerTRIM.analyzeMultiText(multiText, 0, 1);
        assertEquals(0, multiText.size());
    }

    @Test
    void testAnalyzeMultiText_OffsetBeyondSize() {
        walkerTRIM.analyzeMultiText(multiText, 10, 1);
        assertEquals(0, multiText.size());
    }

    @Test
    void testAnalyzeMultiText_NullContent() {
        contentList.add(null);
        assertThrows(NullPointerException.class, () -> {
            walkerTRIM.analyzeMultiText(multiText, 0, 1);
        });
    }

    @Test
    void testAnalyzeMultiText_NegativeOffset() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            walkerTRIM.analyzeMultiText(multiText, -1, 1);
        });
    }

    @Test
    void testAnalyzeMultiText_LengthExceedsAvailableContents() {
        contentList.add(new Text("Valid Text"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            walkerTRIM.analyzeMultiText(multiText, 0, 100);
        });
    }

    @Test
    void testAnalyzeMultiText_EmptyContentList() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            walkerTRIM.analyzeMultiText(multiText, 0, 1);
        });
    }
}
