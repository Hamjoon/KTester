package org.apache.commons.cli;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.StringWriter;
import java.util.Comparator;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class HelpFormatter_renderOptions_Test {

    private HelpFormatter helpFormatter;

    private Options options;

    @BeforeEach
    void setupBeforeEach() {
        helpFormatter = new HelpFormatter();
        options = new Options();
    }

    @Test
    void testRenderOptions() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = HelpFormatter.DEFAULT_WIDTH;
        int leftPad = HelpFormatter.DEFAULT_LEFT_PAD;
        int descPad = HelpFormatter.DEFAULT_DESC_PAD;
        options.addOption("f", "file", true, "The file to be processed");
        options.addOption("v", "version", false, "Print the version of the application");
        options.addOption("h", "help", false, "Display help");
        // When
        StringBuffer result = helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        // Then
        assertNotNull(result);
        assertTrue(result.length() > 0);
        // Add more assertions to verify the expected output
    }

    @Test
    void testRenderOptionsWithEmptyOptions() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = 80;
        int leftPad = 1;
        int descPad = 3;
        // When
        StringBuffer result = helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        // Then
        assertNotNull(result);
        assertEquals("", result.toString(), "Expected no options to be rendered.");
    }

    @Test
    void testRenderOptionsWithSingleShortOption() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = 80;
        int leftPad = 1;
        int descPad = 3;
        options.addOption("f", null, false, "A short option");
        // When
        StringBuffer result = helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        // Then
        assertNotNull(result);
        assertTrue(result.toString().contains("-f"), "Expected single short option to be formatted.");
    }

    @Test
    void testRenderOptionsWithMultipleOptionsIncludingLongOptions() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = 80;
        int leftPad = 1;
        int descPad = 3;
        options.addOption("f", "file", true, "The file to be processed");
        options.addOption("v", "version", false, "Print the version of the application");
        // When
        StringBuffer result = helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        // Then
        assertNotNull(result);
        assertTrue(result.toString().contains("-f, --file"), "Expected options to be formatted correctly.");
    }

    @Test
    void testRenderOptionsWithOptionsHavingArguments() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = 80;
        int leftPad = 1;
        int descPad = 3;
        options.addOption("f", "file", true, "The file to be processed");
        // When
        StringBuffer result = helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        // Then
        assertNotNull(result);
        assertTrue(result.toString().contains("<arg>"), "Expected argument names to be included in the formatting.");
    }

    @Test
    void testRenderOptionsWithOptionsHavingEmptyArgumentNames() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = 80;
        int leftPad = 1;
        int descPad = 3;
        Option option = new Option("f", "file", true, "The file to be processed");
        option.setArgName("");
        options.addOption(option);
        // When
        StringBuffer result = helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        // Then
        assertNotNull(result);
        assertFalse(result.toString().contains("<>"), "Expected spaces to be correctly handled.");
    }

    @Test
    void testRenderOptionsWithOptionsHavingNoDescription() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = 80;
        int leftPad = 1;
        int descPad = 3;
        options.addOption("f", "file", true, null);
        // When
        StringBuffer result = helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        // Then
        assertNotNull(result);
        assertTrue(result.toString().contains("-f, --file"), "Expected options to be formatted without descriptions.");
    }

    @Test
    void testRenderOptionsWithSingleLongOption() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = 80;
        int leftPad = 1;
        int descPad = 3;
        options.addOption(null, "file", true, "The file to be processed");
        // When
        StringBuffer result = helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        // Then
        assertNotNull(result);
        assertTrue(result.toString().contains("--file"), "Expected single long option to be formatted.");
    }

    @Test
    void testRenderOptionsWithVeryLongOptionNames() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = 80;
        int leftPad = 1;
        int descPad = 3;
        options.addOption("f", "filewithaverylongname", true, "The file to be processed");
        // When
        StringBuffer result = helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        // Then
        assertNotNull(result);
        assertTrue(result.toString().contains("--filewithaverylongname"), "Expected long option names to be wrapped correctly.");
    }

    @Test
    void testRenderOptionsWithMultipleShortOptionsWrapped() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = 10;
        int leftPad = 1;
        int descPad = 3;
        options.addOption("a", null, false, "Option A");
        options.addOption("b", null, false, "Option B");
        // When
        StringBuffer result = helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        // Then
        assertNotNull(result);
        assertTrue(result.toString().contains("-a"), "Expected multiple short options to be wrapped into multiple lines.");
    }

    @Test
    void testRenderOptionsWithMixedShortAndLongOptions() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = 80;
        int leftPad = 1;
        int descPad = 3;
        options.addOption("f", "file", true, "The file to be processed");
        options.addOption("v", "version", false, "Print the version of the application");
        // When
        StringBuffer result = helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        // Then
        assertNotNull(result);
        assertTrue(result.toString().contains("-f, --file"), "Expected mixed options to be formatted correctly.");
    }

    @Test
    void testRenderOptionsWithNoOptions() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = 80;
        int leftPad = 1;
        int descPad = 3;
        // When
        StringBuffer result = helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        // Then
        assertNotNull(result);
        assertEquals("", result.toString(), "Expected no options to be rendered.");
    }

    @Test
    void testRenderOptionsWithLongDescriptionsWrapped() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = 80;
        int leftPad = 1;
        int descPad = 3;
        options.addOption("f", "file", true, "This is a very long description that should be wrapped correctly within the specified width.");
        // When
        StringBuffer result = helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        // Then
        assertNotNull(result);
        assertTrue(result.toString().contains("This is a very long description"), "Expected long descriptions to be wrapped correctly.");
    }

    @Test
    void testRenderOptionsWithNullStringBuffer() {
        // Given
        StringBuffer sb = null;
        int width = 80;
        int leftPad = 1;
        int descPad = 3;
        // Then
        assertThrows(NullPointerException.class, () -> {
            // When
            helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        }, "Expected a NullPointerException to be thrown.");
    }

    @Test
    void testRenderOptionsWithNegativeWidth() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = -1;
        int leftPad = 1;
        int descPad = 3;
        // Then
        assertThrows(IllegalArgumentException.class, () -> {
            // When
            helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        }, "Expected an IllegalArgumentException to be thrown.");
    }

    @Test
    void testRenderOptionsWithNullOptions() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = 80;
        int leftPad = 1;
        int descPad = 3;
        Options options = null;
        // Then
        assertThrows(NullPointerException.class, () -> {
            // When
            helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        }, "Expected a NullPointerException to be thrown.");
    }

    @Test
    void testRenderOptionsWithNegativeLeftPad() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = 80;
        int leftPad = -1;
        int descPad = 3;
        // Then
        assertThrows(IllegalArgumentException.class, () -> {
            // When
            helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        }, "Expected an IllegalArgumentException to be thrown.");
    }

    @Test
    void testRenderOptionsWithNegativeDescPad() {
        // Given
        StringBuffer sb = new StringBuffer();
        int width = 80;
        int leftPad = 1;
        int descPad = -1;
        // Then
        assertThrows(IllegalArgumentException.class, () -> {
            // When
            helpFormatter.renderOptions(sb, width, options, leftPad, descPad);
        }, "Expected an IllegalArgumentException to be thrown.");
    }
}
