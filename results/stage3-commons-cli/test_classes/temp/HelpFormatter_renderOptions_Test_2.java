package org.apache.commons.cli;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Comparator;

@ExtendWith(MockitoExtension.class)
class HelpFormatter_renderOptions_Test {

    private HelpFormatter formatter;

    private Options options;

    private ByteArrayOutputStream out;

    private PrintWriter pw;

    private StringBuffer sb;

    @BeforeEach
    void setupBeforeEach() {
        formatter = new HelpFormatter();
        options = new Options();
        out = new ByteArrayOutputStream();
        pw = new PrintWriter(out);
        sb = new StringBuffer();
    }

    @Test
    void testRenderOptions() {
        // Given
        options.addOption("a", "option-a", false, "Description for option A");
        options.addOption("b", "option-b", true, "Description for option B with an argument");
        // When
        formatter.renderOptions(sb, HelpFormatter.DEFAULT_WIDTH, options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD);
        // Then
        String expectedOutput = "  -a,--option-a    Description for option A" + System.lineSeparator() + "  -b,--option-b <arg>   Description for option B with an argument";
        assertEquals(expectedOutput, sb.toString().trim());
    }

    @Test
    void testRenderOptionsWithSingleValidOption() {
        // Given: A single valid option with short and long forms
        options.addOption("a", "aaa", true, "description");
        // When: Rendering options
        formatter.renderOptions(sb, HelpFormatter.DEFAULT_WIDTH, options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD);
        // Then: The output should match the expected formatted string
        String expectedOutput = "  -a,--aaa <arg>   description";
        assertEquals(expectedOutput, sb.toString().trim());
    }

    @Test
    void testRenderOptionsWithEmptyOptions() {
        // Given: No options provided
        options = new Options();
        // When: Rendering options
        formatter.renderOptions(sb, HelpFormatter.DEFAULT_WIDTH, options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD);
        // Then: The StringBuffer remains empty
        assertEquals("", sb.toString().trim());
    }

    @Test
    void testRenderOptionsWithNoShortForm() {
        // Given: An option with no short form
        options.addOption(null, "aaa", true, "description");
        // When: Rendering options
        formatter.renderOptions(sb, HelpFormatter.DEFAULT_WIDTH, options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD);
        // Then: The output should reflect the long option only
        String expectedOutput = "  --aaa <arg>   description";
        assertEquals(expectedOutput, sb.toString().trim());
    }

//     @Test
//     void testRenderOptionsWithBlankArgumentName() {
//         // Given: An option with a blank argument name
//         // Changed to setArgName("") for an empty argument name
//         options.addOption("a", "aaa", true, "description").setArgName("");
//         // When: Rendering options
//         formatter.renderOptions(sb, HelpFormatter.DEFAULT_WIDTH, options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD);
//         // Then: The output should reflect the blank argument name
//         String expectedOutput = "  -a,--aaa    description";
//         assertEquals(expectedOutput, sb.toString().trim());
//     }

    @Test
    void testRenderOptionsWithMultipleOptions() {
        // Given: Multiple valid options
        options.addOption("a", "aaa", true, "description");
        options.addOption("b", "bbb", false, "another description");
        // When: Rendering options
        formatter.renderOptions(sb, HelpFormatter.DEFAULT_WIDTH, options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD);
        // Then: The output should reflect both options
        String expectedOutput = "  -a,--aaa <arg>   description" + System.lineSeparator() + "  -b,--bbb    another description";
        assertEquals(expectedOutput, sb.toString().trim());
    }

    @Test
    void testRenderOptionsWithCustomOptionComparator() {
        // Given: Options with a custom comparator
        options.addOption("b", "bbb", true, "description");
        options.addOption("a", "aaa", true, "another description");
        formatter.setOptionComparator(Comparator.comparing(Option::getOpt));
        // When: Rendering options
        formatter.renderOptions(sb, HelpFormatter.DEFAULT_WIDTH, options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD);
        // Then: The output should be sorted by the custom comparator
        String expectedOutput = "  -a,--aaa <arg>   another description" + System.lineSeparator() + "  -b,--bbb <arg>   description";
        assertEquals(expectedOutput, sb.toString().trim());
    }

    @Test
    void testRenderOptionsWithOnlyLongForm() {
        // Given: An option that has a long form only
        options.addOption(null, "longOpt", true, "description");
        // When: Rendering options
        formatter.renderOptions(sb, HelpFormatter.DEFAULT_WIDTH, options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD);
        // Then: The output should reflect the long option only
        String expectedOutput = "  --longOpt <arg>   description";
        assertEquals(expectedOutput, sb.toString().trim());
    }

    @Test
    void testRenderOptionsWithNullStringBuffer() {
        // Given: A null StringBuffer
        StringBuffer nullSb = null;
        // When: Attempting to render options with a null StringBuffer
        Exception exception = assertThrows(NullPointerException.class, () -> {
            formatter.renderOptions(nullSb, HelpFormatter.DEFAULT_WIDTH, options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD);
        });
        // Then: A NullPointerException should be thrown
        assertNotNull(exception);
    }
}