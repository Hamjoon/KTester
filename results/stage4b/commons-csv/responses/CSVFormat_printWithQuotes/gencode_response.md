```java
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
    private StringWriter stringWriter;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize CSVFormat instance and other dependencies
        csvFormat = CSVFormat.DEFAULT; // or any specific format you want to test
        stringWriter = new StringWriter();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
        stringWriter = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }

    @Test
    void testPrintWithQuotes_QuoteMode_ALL() throws Exception {
        // Given
        Object objectToPrint = "Sample Object";
        CharSequence charSequence = "\"Sample String\"";
        boolean newRecord = true;

        // When
        invokePrintWithQuotes(objectToPrint, charSequence, stringWriter, newRecord);

        // Then
        String expectedOutput = "\"Sample String\""; // Expected output with quotes
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_QuoteMode_ALL_NON_NULL() throws Exception {
        // Given
        Object objectToPrint = "Sample Object";
        CharSequence charSequence = "\"Sample String\"";
        boolean newRecord = true;

        // When
        invokePrintWithQuotes(objectToPrint, charSequence, stringWriter, newRecord);

        // Then
        String expectedOutput = "\"Sample String\""; // Expected output with quotes
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_QuoteMode_NON_NUMERIC() throws Exception {
        // Given a non-numeric object
        Object objectToPrint = "Sample Object";
        CharSequence charSequence = "\"Sample String\"";
        boolean newRecord = true;

        // When
        invokePrintWithQuotes(objectToPrint, charSequence, stringWriter, newRecord);

        // Then
        String expectedOutput = "\"Sample String\""; // Expected output with quotes
        assertEquals(expectedOutput, stringWriter.toString());

        // Given a numeric object
        objectToPrint = 123;
        charSequence = "123";
        
        // When
        stringWriter.getBuffer().setLength(0); // Reset the writer
        invokePrintWithQuotes(objectToPrint, charSequence, stringWriter, newRecord);

        // Then
        expectedOutput = "123"; // Expected output without quotes
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_QuoteMode_NONE() throws Exception {
        // Given
        Object objectToPrint = "Sample Object";
        CharSequence charSequence = "Sample String";
        boolean newRecord = true;

        // When
        invokePrintWithQuotes(objectToPrint, charSequence, stringWriter, newRecord);

        // Then
        String expectedOutput = "Sample String"; // Expected output without quotes
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_QuoteMode_MINIMAL() throws Exception {
        // Test with empty charSeq
        Object objectToPrint = "Sample Object";
        CharSequence charSequence = "";
        boolean newRecord = true;

        // When
        invokePrintWithQuotes(objectToPrint, charSequence, stringWriter, newRecord);

        // Then
        String expectedOutput = "\"\""; // Expected output quoting the empty string
        assertEquals(expectedOutput, stringWriter.toString());

        // Test with no special characters
        charSequence = "Sample String";
        newRecord = false;

        // When
        stringWriter.getBuffer().setLength(0); // Reset the writer
        invokePrintWithQuotes(objectToPrint, charSequence, stringWriter, newRecord);

        // Then
        expectedOutput = "Sample String"; // Expected output without quotes
        assertEquals(expectedOutput, stringWriter.toString());

        // Test with new line character
        charSequence = "\nNew Line";

        // When
        stringWriter.getBuffer().setLength(0); // Reset the writer
        invokePrintWithQuotes(objectToPrint, charSequence, stringWriter, newRecord);

        // Then
        expectedOutput = "\"\\nNew Line\""; // Expected output with quotes
        assertEquals(expectedOutput, stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_ExceptionHandling() throws Exception {
        // Test when the Appendable out is null
        Object objectToPrint = "Sample Object";
        CharSequence charSequence = "Sample String";
        boolean newRecord = true;

        // When & Then
        assertThrows(IOException.class, () -> {
            invokePrintWithQuotes(objectToPrint, charSequence, null, newRecord);
        });

        // Test when the Appendable out throws IOException during append
        stringWriter = mock(StringWriter.class);
        doThrow(new IOException()).when(stringWriter).append(any(CharSequence.class));

        // When & Then
        assertThrows(IOException.class, () -> {
            invokePrintWithQuotes(objectToPrint, charSequence, stringWriter, newRecord);
        });
    }

    private void invokePrintWithQuotes(Object object, CharSequence charSeq, Appendable out, boolean newRecord) throws Exception {
        Method method = CSVFormat.class.getDeclaredMethod("printWithQuotes", Object.class, CharSequence.class, Appendable.class, boolean.class);
        method.setAccessible(true);
        method.invoke(csvFormat, object, charSeq, out, newRecord);
    }
}
```