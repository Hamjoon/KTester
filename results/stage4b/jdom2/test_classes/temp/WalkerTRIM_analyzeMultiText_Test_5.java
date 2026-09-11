package org.jdom2.output.support;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.jdom2.Content;
import org.jdom2.output.support.FormatStack;
import org.jdom2.output.support.WalkerTRIM;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Text;
import org.jdom2.output.support.AbstractFormattedWalker.Trim;
import org.mockito.junit.jupiter.MockitoExtension;
import org.jdom2.CDATA;
import org.jdom2.EntityRef;

@ExtendWith(MockitoExtension.class)
class WalkerTRIM_analyzeMultiText_Test {

    private WalkerTRIM walkerTRIM;

    private FormatStack formatStack;

    private List<Content> contentList;

    private WalkerTRIM.MultiText multiText;

    @BeforeEach
    void setupBeforeEach() {
        contentList = new ArrayList<>();
        // Initialize FormatStack
        // Initialize FormatStack
//         formatStack = new FormatStack();
        walkerTRIM = new WalkerTRIM(contentList, formatStack, true);
        // Initialize MultiText
        // Initialize MultiText
//         multiText = walkerTRIM.createMultiText();
    }

    @AfterEach
    void teardownAfterEach() {
        contentList.clear();
    }

    @Test
    void testAnalyzeMultiText_invalidOffset() {
        int offset = -1;
        int len = 5;
        assertThrows(IndexOutOfBoundsException.class, () -> {
            walkerTRIM.analyzeMultiText(multiText, offset, len);
        });
    }

    @Test
    void testAnalyzeMultiText_invalidLength() {
        int offset = 0;
        int len = -5;
        assertThrows(IllegalArgumentException.class, () -> {
            walkerTRIM.analyzeMultiText(multiText, offset, len);
        });
    }

    @Test
    void testAnalyzeMultiText_zeroLength_invalidOffset() {
        int offset = 100;
        int len = 0;
        assertThrows(IndexOutOfBoundsException.class, () -> {
            walkerTRIM.analyzeMultiText(multiText, offset, len);
        });
    }

    @Test
    void testAnalyzeMultiText_emptyContent() {
        int offset = 0;
        int len = 5;
        assertThrows(IndexOutOfBoundsException.class, () -> {
            walkerTRIM.analyzeMultiText(multiText, offset, len);
        });
    }
}