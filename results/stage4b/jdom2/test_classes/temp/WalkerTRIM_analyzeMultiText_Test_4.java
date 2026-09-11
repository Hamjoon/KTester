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
//         formatStack = new FormatStack();
        walkerTRIM = new WalkerTRIM(contentList, formatStack, true);
        // Initialize MultiText
//         multiText = walkerTRIM.createMultiText();
    }

    @AfterEach
    void teardownAfterEach() {
        contentList.clear();
    }

//     @Test
//     void testAnalyzeMultiText() {
//         int offset = 0;
//         int len = 5;
//         // Add Content objects to contentList for testing
//         contentList.add(new Text("Sample Text 1"));
//         contentList.add(new Text("Sample Text 2"));
//         contentList.add(new Text("Sample Text 3"));
//         contentList.add(new Text("Sample Text 4"));
//         contentList.add(new Text("Sample Text 5"));
//         walkerTRIM.analyzeMultiText(multiText, offset, len);
//         // Add assertions to validate the behavior of analyzeMultiText
//         assertEquals(len, multiText.getTextCount(), "mtext should contain non-whitespace text.");
//     }

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

//     @Test
//     void testAnalyzeMultiText_zeroLength() {
//         int offset = 0;
//         int len = 0;
//         walkerTRIM.analyzeMultiText(multiText, offset, len);
//         assertEquals(0, multiText.getTextCount(), "mtext should have no changes with zero length.");
//     }

//     @Test
//     void testAnalyzeMultiText_allWhitespaceText() {
//         int offset = 0;
//         int len = 5;
//         for (int i = 0; i < len; i++) {
//             contentList.add(new Text("   "));
//         }
//         walkerTRIM.analyzeMultiText(multiText, offset, len);
//         assertEquals(0, multiText.getTextCount(), "mtext should not contain any text after processing all whitespace.");
//     }

//     @Test
//     void testAnalyzeMultiText_nonWhitespaceText() {
//         int offset = 0;
//         int len = 5;
//         for (int i = 0; i < len; i++) {
//             contentList.add(new Text("Text " + i));
//         }
//         walkerTRIM.analyzeMultiText(multiText, offset, len);
//         assertEquals(len, multiText.getTextCount(), "mtext should contain non-whitespace text.");
//     }

//     @Test
//     void testAnalyzeMultiText_mixedContent() {
//         int offset = 0;
//         int len = 5;
//         contentList.add(new Text("Text 1"));
//         contentList.add(new CDATA("CDATA 1"));
//         contentList.add(new Text("   "));
//         contentList.add(new EntityRef("EntityRef1"));
//         contentList.add(new Text("Text 2"));
//         walkerTRIM.analyzeMultiText(multiText, offset, len);
//         assertEquals(3, multiText.getTextCount(), "mtext should contain text and CDATA, with whitespace ignored.");
//     }

//     @Test
//     void testAnalyzeMultiText_lengthOfOne() {
//         int offset = 0;
//         int len = 1;
//         contentList.add(new Text("Text 1"));
//         walkerTRIM.analyzeMultiText(multiText, offset, len);
//         assertEquals(1, multiText.getTextCount(), "mtext should contain trimmed Text.");
//     }

//     @Test
//     void testAnalyzeMultiText_lengthOfTwo() {
//         int offset = 0;
//         int len = 2;
//         contentList.add(new Text("Text 1"));
//         contentList.add(new Text("Text 2"));
//         walkerTRIM.analyzeMultiText(multiText, offset, len);
//         assertEquals(2, multiText.getTextCount(), "mtext should contain left trimmed and right trimmed Text.");
//     }

//     @Test
//     void testAnalyzeMultiText_lengthOfThree() {
//         int offset = 0;
//         int len = 3;
//         contentList.add(new Text("Text 1"));
//         contentList.add(new CDATA("CDATA 1"));
//         contentList.add(new Text("Text 2"));
//         walkerTRIM.analyzeMultiText(multiText, offset, len);
//         assertEquals(3, multiText.getTextCount(), "mtext should contain left trimmed Text, raw Content, and right trimmed Text.");
//     }

//     @Test
//     void testAnalyzeMultiText_onlyCDATA() {
//         int offset = 0;
//         int len = 3;
//         contentList.add(new CDATA("CDATA 1"));
//         contentList.add(new CDATA("CDATA 2"));
//         contentList.add(new CDATA("CDATA 3"));
//         walkerTRIM.analyzeMultiText(multiText, offset, len);
//         assertEquals(3, multiText.getCDATACount(), "mtext should contain CDATA without any trimming.");
//     }
}