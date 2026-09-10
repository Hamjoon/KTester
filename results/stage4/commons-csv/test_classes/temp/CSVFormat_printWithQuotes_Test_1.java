package org.apache.commons.csv;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.StringWriter;
import java.io.IOException;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class CSVFormat_printWithQuotes_Test {

    private CSVFormat csvFormat;

    private StringWriter output;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the CSVFormat instance using the default format
        csvFormat = CSVFormat.DEFAULT;
        output = new StringWriter();
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources after each test if necessary
        // Allow for garbage collection
        output = null;
    }

    // Helper method to invoke the private method using reflection
    private void invokePrintWithQuotes(Object object, CharSequence charSeq, Appendable out, boolean newRecord) throws Exception {
        Method method = CSVFormat.class.getDeclaredMethod("printWithQuotes", Object.class, CharSequence.class, Appendable.class, boolean.class);
        method.setAccessible(true);
        method.invoke(csvFormat, object, charSeq, out, newRecord);
    }

    // Helper method to set the QuoteMode
    private void setQuoteMode(QuoteMode quoteMode) throws Exception {
        Method method = CSVFormat.class.getDeclaredMethod("setQuoteMode", QuoteMode.class);
        method.setAccessible(true);
        method.invoke(csvFormat, quoteMode);
    }

    @Test
    void testPrintWithQuotes_NullValue() throws IOException {
        // Given
        Object value = null;
        CharSequence charSeq = "value";
        boolean newRecord = true;
        // When
        invokePrintWithQuotes(value, charSeq, output, newRecord);
        // Then
        // Expecting value in quotes for null object with ALL mode
        assertEquals("\"value\"", output.toString());
    }

    @Test
    void testPrintWithQuotes_EmptyString() throws IOException {
        // Given
        CharSequence charSeq = "";
        boolean newRecord = true;
        // When
        // Use reflection to call the method
        invokePrintWithQuotes("test", charSeq, output, newRecord);
        // Then
        // Expecting empty quotes
        assertEquals("\"\"", output.toString());
    }

    @Test
    void testPrintWithQuotes_SingleQuote() throws IOException {
        // Given
        CharSequence charSeq = "Hello, World!";
        boolean newRecord = true;
        // When
        // Use reflection to call the method
        invokePrintWithQuotes("test", charSeq, output, newRecord);
        // Then
        // Expecting value in quotes
        assertEquals("\"Hello, World!\"", output.toString());
    }

    @Test
    void testPrintWithQuotes_SpecialCharacters() throws IOException {
        // Given
        CharSequence charSeq = "Line1\nLine2";
        boolean newRecord = true;
        // When
        // Use reflection to call the method
        invokePrintWithQuotes("test", charSeq, output, newRecord);
        // Then
        // Expecting special characters to be escaped
        assertEquals("\"Line1\\nLine2\"", output.toString());
    }

    @Test
    void testPrintWithQuotes_DelimiterCharacter() throws IOException {
        // Given
        CharSequence charSeq = "Value,With,Commas";
        boolean newRecord = true;
        // When
        // Use reflection to call the method
        invokePrintWithQuotes("test", charSeq, output, newRecord);
        // Then
        // Expecting value in quotes due to commas
        assertEquals("\"Value,With,Commas\"", output.toString());
    }

    @Test
    void testPrintWithQuotes_QuoteMode_ALL() throws Exception {
        // Given
        CharSequence charSeq = "value";
        boolean newRecord = true;
        // Set QuoteMode to ALL
        setQuoteMode(QuoteMode.ALL);
        // When
        invokePrintWithQuotes(null, charSeq, output, newRecord);
        // Then
        // Expecting value in quotes
        assertEquals("\"value\"", output.toString());
    }

    @Test
    void testPrintWithQuotes_QuoteMode_NONE() throws Exception {
        // Given
        CharSequence charSeq = "value";
        boolean newRecord = false;
        // Set QuoteMode to NONE
        setQuoteMode(QuoteMode.NONE);
        // When
        invokePrintWithQuotes(null, charSeq, output, newRecord);
        // Then
        // Expecting original value without quotes
        assertEquals("value", output.toString());
    }

    @Test
    void testPrintWithQuotes_QuoteMode_MINIMAL_EmptyInput() throws Exception {
        // Given
        CharSequence charSeq = "";
        boolean newRecord = true;
        // Set QuoteMode to MINIMAL
        setQuoteMode(QuoteMode.MINIMAL);
        // When
        invokePrintWithQuotes(null, charSeq, output, newRecord);
        // Then
        // Expecting empty quotes
        assertEquals("\"\"", output.toString());
    }

    @Test
    void testPrintWithQuotes_QuoteMode_MINIMAL_SpecialCharacters() throws Exception {
        // Given
        CharSequence charSeq = "\nvalue";
        boolean newRecord = true;
        // Set QuoteMode to MINIMAL
        setQuoteMode(QuoteMode.MINIMAL);
        // When
        invokePrintWithQuotes(null, charSeq, output, newRecord);
        // Then
        // Expecting value in quotes
        assertEquals("\"\\nvalue\"", output.toString());
    }

    @Test
    void testPrintWithQuotes_QuoteMode_NON_NUMERIC() throws Exception {
        // Given
        CharSequence charSeq = "123";
        boolean newRecord = false;
        // Set QuoteMode to NON_NUMERIC
        setQuoteMode(QuoteMode.NON_NUMERIC);
        // When
        invokePrintWithQuotes(new Object(), charSeq, output, newRecord);
        // Then
        // Expecting original value without quotes for numeric
        assertEquals("123", output.toString());
    }

    @Test
    void testPrintWithQuotes_QuoteMode_NON_NUMERIC_NonNumeric() throws Exception {
        // Given
        CharSequence charSeq = "text";
        boolean newRecord = false;
        // Set QuoteMode to NON_NUMERIC
        setQuoteMode(QuoteMode.NON_NUMERIC);
        // When
        invokePrintWithQuotes(new Object(), charSeq, output, newRecord);
        // Then
        // Expecting non-numeric value in quotes
        assertEquals("\"text\"", output.toString());
    }
}
