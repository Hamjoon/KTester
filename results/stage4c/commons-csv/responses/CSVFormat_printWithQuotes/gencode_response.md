```java
package org.apache.commons.csv;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.StringWriter;

@ExtendWith(MockitoExtension.class)
class CSVFormat_printWithQuotes_Test{

    private CSVFormat csvFormat;
    private StringWriter stringWriter;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize resources that are shared across all tests
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize resources for each test
        csvFormat = CSVFormat.DEFAULT;
        stringWriter = new StringWriter();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources after each test
        stringWriter = null;
        csvFormat = null;
    }

    @AfterAll
    static void teardownAfterAll() {
        // Cleanup resources shared across all tests
    }

    @Test
    void testPrintWithQuotes_numericObject() throws IOException {
        // Given a numeric object and minimal quote mode
        Object object = 123;
        CharSequence charSeq = "123";
        Appendable out = stringWriter;
        boolean newRecord = true;

        // When
        csvFormat.printWithQuotes(object, charSeq, out, newRecord);

        // Then
        assertEquals("123", stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_stringWithQuote() throws IOException {
        // Given a string containing a quote
        Object object = "\"Hello, World!\"";
        CharSequence charSeq = "\"Hello, World!\"";
        Appendable out = stringWriter;
        boolean newRecord = false;

        // When
        csvFormat.printWithQuotes(object, charSeq, out, newRecord);

        // Then
        assertEquals("\"\"\"Hello, World!\"\"\"", stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_emptyStringNewRecord() throws IOException {
        // Given an empty string and new record
        Object object = null;
        CharSequence charSeq = "";
        Appendable out = stringWriter;
        boolean newRecord = true;

        // When
        csvFormat.printWithQuotes(object, charSeq, out, newRecord);

        // Then
        assertEquals("\"\"", stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_stringWithDelimiter() throws IOException {
        // Given a string containing a delimiter
        Object object = "abc,def";
        CharSequence charSeq = "abc,def";
        Appendable out = stringWriter;
        boolean newRecord = false;

        // When
        csvFormat.printWithQuotes(object, charSeq, out, newRecord);

        // Then
        assertEquals("\"abc,def\"", stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_simpleString() throws IOException {
        // Given a simple string without special characters
        Object object = "abc";
        CharSequence charSeq = "abc";
        Appendable out = stringWriter;
        boolean newRecord = false;

        // When
        csvFormat.printWithQuotes(object, charSeq, out, newRecord);

        // Then
        assertEquals("abc", stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_stringWithNewline() throws IOException {
        // Given a string containing a newline character
        Object object = "abc\ndef";
        CharSequence charSeq = "abc\ndef";
        Appendable out = stringWriter;
        boolean newRecord = false;

        // When
        csvFormat.printWithQuotes(object, charSeq, out, newRecord);

        // Then
        assertEquals("\"abc\ndef\"", stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_stringWithSpaces() throws IOException {
        // Given a string containing only spaces
        Object object = "  ";
        CharSequence charSeq = "  ";
        Appendable out = stringWriter;
        boolean newRecord = false;

        // When
        csvFormat.printWithQuotes(object, charSeq, out, newRecord);

        // Then
        assertEquals("\"  \"", stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_nullObject() throws IOException {
        // Given null object and charSeq
        Object object = "null";
        CharSequence charSeq = "null";
        Appendable out = stringWriter;
        boolean newRecord = true;

        // When
        csvFormat.printWithQuotes(object, charSeq, out, newRecord);

        // Then
        assertEquals("\"\"", stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_floatingPointNumber() throws IOException {
        // Given a floating-point number
        Object object = "123.456";
        CharSequence charSeq = "123.456";
        Appendable out = stringWriter;
        boolean newRecord = false;

        // When
        csvFormat.printWithQuotes(object, charSeq, out, newRecord);

        // Then
        assertEquals("123.456", stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_stringStartingWithCommentChar() throws IOException {
        // Given a string starting with a comment character
        Object object = "abc#def";
        CharSequence charSeq = "abc#def";
        Appendable out = stringWriter;
        boolean newRecord = false;

        // When
        csvFormat.printWithQuotes(object, charSeq, out, newRecord);

        // Then
        assertEquals("\"abc#def\"", stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_stringWithInternalQuote() throws IOException {
        // Given a string containing a quote character
        Object object = "abc\"def";
        CharSequence charSeq = "abc\"def";
        Appendable out = stringWriter;
        boolean newRecord = false;

        // When
        csvFormat.printWithQuotes(object, charSeq, out, newRecord);

        // Then
        assertEquals("\"abc\"\"def\"", stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_stringWithEscapeChar() throws IOException {
        // Given a string containing an escape character
        Object object = "abc\\def";
        CharSequence charSeq = "abc\\def";
        Appendable out = stringWriter;
        boolean newRecord = false;

        // When
        csvFormat.printWithQuotes(object, charSeq, out, newRecord);

        // Then
        assertEquals("\"abc\\def\"", stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_emptyStringNotNewRecord() throws IOException {
        // Given an empty string and not a new record
        Object object = "";
        CharSequence charSeq = "";
        Appendable out = stringWriter;
        boolean newRecord = false;

        // When
        csvFormat.printWithQuotes(object, charSeq, out, newRecord);

        // Then
        assertEquals("", stringWriter.toString());
    }

    @Test
    void testPrintWithQuotes_nullCharSequence() {
        // Given a null CharSequence
        Object object = "test";
        CharSequence charSeq = null;
        Appendable out = stringWriter;
        boolean newRecord = true;

        // Then
        assertThrows(NullPointerException.class, () -> {
            // When
            csvFormat.printWithQuotes(object, charSeq, out, newRecord);
        });
    }

    @Test
    void testPrintWithQuotes_nullAppendable() {
        // Given a null Appendable
        Object object = "test";
        CharSequence charSeq = "test";
        Appendable out = null;
        boolean newRecord = true;

        // Then
        assertThrows(NullPointerException.class, () -> {
            // When
            csvFormat.printWithQuotes(object, charSeq, out, newRecord);
        });
    }

    @Test
    void testPrintWithQuotes_appendableThrowsIOException() {
        // Given an Appendable that throws IOException
        Object object = "test";
        CharSequence charSeq = "test";
        Appendable out = mock(Appendable.class);
        boolean newRecord = true;

        // Setup the mock to throw IOException
        try {
            doThrow(IOException.class).when(out).append(any(CharSequence.class), anyInt(), anyInt());
        } catch (IOException e) {
            // This block is intentionally left blank
        }

        // Then
        assertThrows(IOException.class, () -> {
            // When
            csvFormat.printWithQuotes(object, charSeq, out, newRecord);
        });
    }
}
```