


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package org.apache.commons.csv;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// Add other imports if necessary
class CSVFormat_toString_Test {

    private CSVFormat csvFormat;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize CSVFormat instance before each test
        csvFormat = CSVFormat.DEFAULT;
    }

    @Test
    void testToString_DefaultFormat() {
        // Given: A default CSVFormat instance
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertNotNull(result);
        assertTrue(result.contains("Delimiter=<,>"));
        assertTrue(result.contains("SkipHeaderRecord:false"));
    }

    @Test
    void testToString_INFORMIX_UNLOAD_CSVFormat() {
        // Given: An INFORMIX_UNLOAD_CSV CSVFormat instance
        csvFormat = CSVFormat.INFORMIX_UNLOAD_CSV;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:false", result);
    }

    @Test
    void testToString_POSTGRESQL_CSVFormat() {
        // Given: A POSTGRESQL_CSV CSVFormat instance
        csvFormat = CSVFormat.POSTGRESQL_CSV;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> QuoteChar=<\"> QuoteMode=<ALL_NON_NULL> RecordSeparator=<\\n> SkipHeaderRecord:false NullString=<>", result);
    }

    @Test
    void testToString_EXCELFormat() {
        // Given: An EXCEL CSVFormat instance
        csvFormat = CSVFormat.EXCEL;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\r\\n> SkipHeaderRecord:false HeaderComments:null Header:null", result);
    }

    @Test
    void testToString_INFORMIX_UNLOADFormat() {
        // Given: An INFORMIX_UNLOAD CSVFormat instance
        csvFormat = CSVFormat.INFORMIX_UNLOAD;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<|> Escape=<\\> QuoteChar=<\"> RecordSeparator=<\\n> SkipHeaderRecord:false", result);
    }

    @Test
    void testToString_POSTGRESQL_TEXTFormat() {
        // Given: A POSTGRESQL_TEXT CSVFormat instance
        csvFormat = CSVFormat.POSTGRESQL_TEXT;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<\\t> Escape=<\\> RecordSeparator=<\\n> SkipHeaderRecord:false NullString=<NULL>", result);
    }

    @Test
    void testToString_ORACLEFormat() {
        // Given: An ORACLE CSVFormat instance
        csvFormat = CSVFormat.ORACLE;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> Escape=<\\> QuoteChar=<\"> NullString=<NULL> RecordSeparator=<system.lineSeparator()> SkipHeaderRecord:false", result);
    }

    @Test
    void testToString_MONGODB_TSVFormat() {
        // Given: A MONGODB_TSV CSVFormat instance
        csvFormat = CSVFormat.MONGODB_TSV;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<\\t> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:false", result);
    }

    @Test
    void testToString_MYSQLFormat() {
        // Given: A MYSQL CSVFormat instance
        csvFormat = CSVFormat.MYSQL;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<\\t> Escape=<\\> QuoteMode=<ALL_NON_NULL> RecordSeparator=<\\n> SkipHeaderRecord:false NullString=<NULL>", result);
    }

    @Test
    void testToString_RFC4180Format() {
        // Given: An RFC4180 CSVFormat instance
        csvFormat = CSVFormat.RFC4180;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> QuoteChar=<\"> RecordSeparator=<\\r\\n> SkipHeaderRecord:false", result);
    }

    @Test
    void testToString_TDFFormat() {
        // Given: A TDF CSVFormat instance
        csvFormat = CSVFormat.TDF;
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<\\t> RecordSeparator=<\\r\\n> SkipHeaderRecord:false SurroundingSpaces:ignored", result);
    }

    @Test
    void testToString_CustomFormatWithHeaderCommentsAndHeaders() {
        // Given: A custom CSVFormat instance with headerComments and headers set
        csvFormat = CSVFormat.DEFAULT.builder().setHeaderComments("Comment 1", "Comment 2").setHeader("Header1", "Header2").build();
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:false HeaderComments:[\"Comment 1\", \"Comment 2\"] Header:[\"Header1\", \"Header2\"]", result);
    }

    @Test
    void testToString_CustomFormatWithSkipHeaderRecordTrue() {
        // Given: A custom CSVFormat instance with skipHeaderRecord set to true
        csvFormat = CSVFormat.DEFAULT.builder().setSkipHeaderRecord(true).build();
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:true", result);
    }

    @Test
    void testToString_CustomFormatWithSemicolonDelimiter() {
        // Given: A custom CSVFormat instance with semicolon delimiter and single quote as quote character
        csvFormat = CSVFormat.DEFAULT.builder().setDelimiter(';').setQuote('\'').setRecordSeparator("\r\n").build();
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<;> Escape=<\\> QuoteChar=<'>' QuoteMode=<MINIMAL> RecordSeparator=<\\r\\n> SkipHeaderRecord:false", result);
    }

    @Test
    void testToString_CustomFormatWithQuoteModeNone() {
        // Given: A custom CSVFormat instance with quoteMode set to NONE
        csvFormat = CSVFormat.DEFAULT.builder().setQuoteMode(QuoteMode.NONE).build();
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> QuoteChar=<\"> QuoteMode=<NONE> RecordSeparator=<\\n> SkipHeaderRecord:false", result);
    }

    @Test
    void testToString_CustomFormatWithPipeDelimiterAndCarriageReturn() {
        // Given: A custom CSVFormat instance with pipe delimiter, carriage return as record separator, and all fields quoted
        csvFormat = CSVFormat.DEFAULT.builder().setDelimiter('|').setRecordSeparator("\r").setQuoteMode(QuoteMode.ALL).build();
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<|> Escape=<\\> QuoteChar=<\"> QuoteMode=<ALL> RecordSeparator=<\\r> SkipHeaderRecord:false", result);
    }

    @Test
    void testToString_CustomFormatWithIgnoreHeaderCase() {
        // Given: A custom CSVFormat instance where ignoreHeaderCase is true
        csvFormat = CSVFormat.DEFAULT.builder().setIgnoreHeaderCase(true).build();
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<,> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:false IgnoreHeaderCase:ignored", result);
    }

    @Test
    void testToString_CustomFormatWithIgnoreEmptyLines() {
        // Given: A custom CSVFormat instance with tab delimiter and ignore empty lines set to true
        csvFormat = CSVFormat.DEFAULT.builder().setDelimiter('\t').setIgnoreEmptyLines(true).setRecordSeparator("\r\n").build();
        // When: Calling toString
        String result = csvFormat.toString();
        // Then: Verify the string representation is as expected
        assertEquals("Delimiter=<\\t> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\r\\n> SkipHeaderRecord:false EmptyLines:ignored", result);
    }
}

```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8

╷
├─ JUnit Jupiter ✔
│  └─ CSVFormat_toString_Test ✔
│     ├─ testToString_DefaultFormat() ✔
│     ├─ testToString_POSTGRESQL_TEXTFormat() ✘ expected: <Delimiter=<\t> Escape=<\> RecordSeparator=<\n> SkipHeaderRecord:false NullString=<NULL>> but was: <Delimiter=<	> Escape=<\> QuoteMode=<ALL_NON_NULL> NullString=<\N> RecordSeparator=<
│     │        > SkipHeaderRecord:false>
│     ├─ testToString_CustomFormatWithIgnoreHeaderCase() ✘ expected: <Delimiter=<,> QuoteChar=<"> QuoteMode=<MINIMAL> RecordSeparator=<\n> SkipHeaderRecord:false IgnoreHeaderCase:ignored> but was: <Delimiter=<,> QuoteChar=<"> RecordSeparator=<
│     │        > EmptyLines:ignored IgnoreHeaderCase:ignored SkipHeaderRecord:false>
│     ├─ testToString_CustomFormatWithQuoteModeNone() ✘ No quotes mode set but no escape character is set
│     ├─ testToString_RFC4180Format() ✘ expected: <Delimiter=<,> QuoteChar=<"> RecordSeparator=<\r\n> SkipHeaderRecord:false> but was: <Delimiter=<,> QuoteChar=<"> RecordSeparator=<
│     │        > SkipHeaderRecord:false>
│     ├─ testToString_ORACLEFormat() ✘ expected: <Delimiter=<,> Escape=<\> QuoteChar=<"> NullString=<NULL> RecordSeparator=<system.lineSeparator()> SkipHeaderRecord:false> but was: <Delimiter=<,> Escape=<\> QuoteChar=<"> QuoteMode=<MINIMAL> NullString=<\N> RecordSeparator=<
│     │        > SkipHeaderRecord:false>
│     ├─ testToString_INFORMIX_UNLOADFormat() ✘ expected: <Delimiter=<|> Escape=<\> QuoteChar=<"> RecordSeparator=<\n> SkipHeaderRecord:false> but was: <Delimiter=<|> Escape=<\> QuoteChar=<"> RecordSeparator=<
│     │        > EmptyLines:ignored SkipHeaderRecord:false>
│     ├─ testToString_TDFFormat() ✘ expected: <Delimiter=<\t> RecordSeparator=<\r\n> SkipHeaderRecord:false SurroundingSpaces:ignored> but was: <Delimiter=<	> QuoteChar=<"> RecordSeparator=<
│     │        > EmptyLines:ignored SurroundingSpaces:ignored SkipHeaderRecord:false>
│     ├─ testToString_CustomFormatWithIgnoreEmptyLines() ✘ expected: <Delimiter=<\t> Escape=<\> QuoteChar=<"> QuoteMode=<MINIMAL> RecordSeparator=<\r\n> SkipHeaderRecord:false EmptyLines:ignored> but was: <Delimiter=<	> QuoteChar=<"> RecordSeparator=<
│     │        > EmptyLines:ignored SkipHeaderRecord:false>
│     ├─ testToString_MYSQLFormat() ✘ expected: <Delimiter=<\t> Escape=<\> QuoteMode=<ALL_NON_NULL> RecordSeparator=<\n> SkipHeaderRecord:false NullString=<NULL>> but was: <Delimiter=<	> Escape=<\> QuoteMode=<ALL_NON_NULL> NullString=<\N> RecordSeparator=<
│     │        > SkipHeaderRecord:false>
│     ├─ testToString_CustomFormatWithHeaderCommentsAndHeaders() ✘ expected: <Delimiter=<,> Escape=<\> QuoteChar=<"> QuoteMode=<MINIMAL> RecordSeparator=<\n> SkipHeaderRecord:false HeaderComments:["Comment 1", "Comment 2"] Header:["Header1", "Header2"]> but was: <Delimiter=<,> QuoteChar=<"> RecordSeparator=<
│     │        > EmptyLines:ignored SkipHeaderRecord:false HeaderComments:[Comment 1, Comment 2] Header:[Header1, Header2]>
│     ├─ testToString_CustomFormatWithSemicolonDelimiter() ✘ expected: <Delimiter=<;> Escape=<\> QuoteChar=<'>' QuoteMode=<MINIMAL> RecordSeparator=<\r\n> SkipHeaderRecord:false> but was: <Delimiter=<;> QuoteChar=<'> RecordSeparator=<
│     │        > EmptyLines:ignored SkipHeaderRecord:false>
│     ├─ testToString_EXCELFormat() ✘ expected: <Delimiter=<,> QuoteChar=<"> QuoteMode=<MINIMAL> RecordSeparator=<\r\n> SkipHeaderRecord:false HeaderComments:null Header:null> but was: <Delimiter=<,> QuoteChar=<"> RecordSeparator=<
│     │        > SkipHeaderRecord:false>
│     ├─ testToString_INFORMIX_UNLOAD_CSVFormat() ✘ expected: <Delimiter=<,> Escape=<\> QuoteChar=<"> QuoteMode=<MINIMAL> RecordSeparator=<\n> SkipHeaderRecord:false> but was: <Delimiter=<,> QuoteChar=<"> RecordSeparator=<
│     │        > EmptyLines:ignored SkipHeaderRecord:false>
│     ├─ testToString_POSTGRESQL_CSVFormat() ✘ expected: <Delimiter=<,> QuoteChar=<"> QuoteMode=<ALL_NON_NULL> RecordSeparator=<\n> SkipHeaderRecord:false NullString=<>> but was: <Delimiter=<,> QuoteChar=<"> QuoteMode=<ALL_NON_NULL> NullString=<> RecordSeparator=<
│     │        > SkipHeaderRecord:false>
│     ├─ testToString_CustomFormatWithSkipHeaderRecordTrue() ✘ expected: <Delimiter=<,> QuoteChar=<"> QuoteMode=<MINIMAL> RecordSeparator=<\n> SkipHeaderRecord:true> but was: <Delimiter=<,> QuoteChar=<"> RecordSeparator=<
│     │        > EmptyLines:ignored SkipHeaderRecord:true>
│     ├─ testToString_MONGODB_TSVFormat() ✘ expected: <Delimiter=<\t> Escape=<\> QuoteChar=<"> QuoteMode=<MINIMAL> RecordSeparator=<\n> SkipHeaderRecord:false> but was: <Delimiter=<	> Escape=<"> QuoteChar=<"> QuoteMode=<MINIMAL> RecordSeparator=<
│     │        > EmptyLines:ignored SkipHeaderRecord:false>
│     └─ testToString_CustomFormatWithPipeDelimiterAndCarriageReturn() ✘ expected: <Delimiter=<|> Escape=<\> QuoteChar=<"> QuoteMode=<ALL> RecordSeparator=<\r> SkipHeaderRecord:false> but was: <Delimiter=<|> QuoteChar=<"> QuoteMode=<ALL> RecordSeparator=<
│              > EmptyLines:ignored SkipHeaderRecord:false>
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Failures (17):
  JUnit Jupiter:CSVFormat_toString_Test:testToString_POSTGRESQL_TEXTFormat()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_POSTGRESQL_TEXTFormat', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Delimiter=<\t> Escape=<\> RecordSeparator=<\n> SkipHeaderRecord:false NullString=<NULL>> but was: <Delimiter=<	> Escape=<\> QuoteMode=<ALL_NON_NULL> NullString=<\N> RecordSeparator=<
> SkipHeaderRecord:false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_POSTGRESQL_TEXTFormat(CSVFormat_toString_Test.java:75)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:CSVFormat_toString_Test:testToString_CustomFormatWithIgnoreHeaderCase()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_CustomFormatWithIgnoreHeaderCase', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Delimiter=<,> QuoteChar=<"> QuoteMode=<MINIMAL> RecordSeparator=<\n> SkipHeaderRecord:false IgnoreHeaderCase:ignored> but was: <Delimiter=<,> QuoteChar=<"> RecordSeparator=<
> EmptyLines:ignored IgnoreHeaderCase:ignored SkipHeaderRecord:false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_CustomFormatWithIgnoreHeaderCase(CSVFormat_toString_Test.java:185)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:CSVFormat_toString_Test:testToString_CustomFormatWithQuoteModeNone()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_CustomFormatWithQuoteModeNone', methodParameterTypes = '']
    => java.lang.IllegalArgumentException: No quotes mode set but no escape character is set
       org.apache.commons.csv.CSVFormat.validate(CSVFormat.java:2281)
       org.apache.commons.csv.CSVFormat.<init>(CSVFormat.java:1349)
       org.apache.commons.csv.CSVFormat.<init>(CSVFormat.java:181)
       org.apache.commons.csv.CSVFormat$Builder.build(CSVFormat.java:275)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_CustomFormatWithQuoteModeNone(CSVFormat_toString_Test.java:161)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       java.base/java.lang.reflect.Method.invoke(Method.java:569)
       org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:727)
       [...]
  JUnit Jupiter:CSVFormat_toString_Test:testToString_RFC4180Format()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_RFC4180Format', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Delimiter=<,> QuoteChar=<"> RecordSeparator=<\r\n> SkipHeaderRecord:false> but was: <Delimiter=<,> QuoteChar=<"> RecordSeparator=<
> SkipHeaderRecord:false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_RFC4180Format(CSVFormat_toString_Test.java:115)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:CSVFormat_toString_Test:testToString_ORACLEFormat()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_ORACLEFormat', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Delimiter=<,> Escape=<\> QuoteChar=<"> NullString=<NULL> RecordSeparator=<system.lineSeparator()> SkipHeaderRecord:false> but was: <Delimiter=<,> Escape=<\> QuoteChar=<"> QuoteMode=<MINIMAL> NullString=<\N> RecordSeparator=<
> SkipHeaderRecord:false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_ORACLEFormat(CSVFormat_toString_Test.java:85)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:CSVFormat_toString_Test:testToString_INFORMIX_UNLOADFormat()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_INFORMIX_UNLOADFormat', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Delimiter=<|> Escape=<\> QuoteChar=<"> RecordSeparator=<\n> SkipHeaderRecord:false> but was: <Delimiter=<|> Escape=<\> QuoteChar=<"> RecordSeparator=<
> EmptyLines:ignored SkipHeaderRecord:false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_INFORMIX_UNLOADFormat(CSVFormat_toString_Test.java:65)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:CSVFormat_toString_Test:testToString_TDFFormat()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_TDFFormat', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Delimiter=<\t> RecordSeparator=<\r\n> SkipHeaderRecord:false SurroundingSpaces:ignored> but was: <Delimiter=<	> QuoteChar=<"> RecordSeparator=<
> EmptyLines:ignored SurroundingSpaces:ignored SkipHeaderRecord:false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_TDFFormat(CSVFormat_toString_Test.java:125)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:CSVFormat_toString_Test:testToString_CustomFormatWithIgnoreEmptyLines()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_CustomFormatWithIgnoreEmptyLines', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Delimiter=<\t> Escape=<\> QuoteChar=<"> QuoteMode=<MINIMAL> RecordSeparator=<\r\n> SkipHeaderRecord:false EmptyLines:ignored> but was: <Delimiter=<	> QuoteChar=<"> RecordSeparator=<
> EmptyLines:ignored SkipHeaderRecord:false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_CustomFormatWithIgnoreEmptyLines(CSVFormat_toString_Test.java:195)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:CSVFormat_toString_Test:testToString_MYSQLFormat()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_MYSQLFormat', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Delimiter=<\t> Escape=<\> QuoteMode=<ALL_NON_NULL> RecordSeparator=<\n> SkipHeaderRecord:false NullString=<NULL>> but was: <Delimiter=<	> Escape=<\> QuoteMode=<ALL_NON_NULL> NullString=<\N> RecordSeparator=<
> SkipHeaderRecord:false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_MYSQLFormat(CSVFormat_toString_Test.java:105)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:CSVFormat_toString_Test:testToString_CustomFormatWithHeaderCommentsAndHeaders()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_CustomFormatWithHeaderCommentsAndHeaders', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Delimiter=<,> Escape=<\> QuoteChar=<"> QuoteMode=<MINIMAL> RecordSeparator=<\n> SkipHeaderRecord:false HeaderComments:["Comment 1", "Comment 2"] Header:["Header1", "Header2"]> but was: <Delimiter=<,> QuoteChar=<"> RecordSeparator=<
> EmptyLines:ignored SkipHeaderRecord:false HeaderComments:[Comment 1, Comment 2] Header:[Header1, Header2]>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_CustomFormatWithHeaderCommentsAndHeaders(CSVFormat_toString_Test.java:135)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:CSVFormat_toString_Test:testToString_CustomFormatWithSemicolonDelimiter()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_CustomFormatWithSemicolonDelimiter', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Delimiter=<;> Escape=<\> QuoteChar=<'>' QuoteMode=<MINIMAL> RecordSeparator=<\r\n> SkipHeaderRecord:false> but was: <Delimiter=<;> QuoteChar=<'> RecordSeparator=<
> EmptyLines:ignored SkipHeaderRecord:false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_CustomFormatWithSemicolonDelimiter(CSVFormat_toString_Test.java:155)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:CSVFormat_toString_Test:testToString_EXCELFormat()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_EXCELFormat', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Delimiter=<,> QuoteChar=<"> QuoteMode=<MINIMAL> RecordSeparator=<\r\n> SkipHeaderRecord:false HeaderComments:null Header:null> but was: <Delimiter=<,> QuoteChar=<"> RecordSeparator=<
> SkipHeaderRecord:false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_EXCELFormat(CSVFormat_toString_Test.java:55)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:CSVFormat_toString_Test:testToString_INFORMIX_UNLOAD_CSVFormat()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_INFORMIX_UNLOAD_CSVFormat', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Delimiter=<,> Escape=<\> QuoteChar=<"> QuoteMode=<MINIMAL> RecordSeparator=<\n> SkipHeaderRecord:false> but was: <Delimiter=<,> QuoteChar=<"> RecordSeparator=<
> EmptyLines:ignored SkipHeaderRecord:false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_INFORMIX_UNLOAD_CSVFormat(CSVFormat_toString_Test.java:35)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:CSVFormat_toString_Test:testToString_POSTGRESQL_CSVFormat()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_POSTGRESQL_CSVFormat', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Delimiter=<,> QuoteChar=<"> QuoteMode=<ALL_NON_NULL> RecordSeparator=<\n> SkipHeaderRecord:false NullString=<>> but was: <Delimiter=<,> QuoteChar=<"> QuoteMode=<ALL_NON_NULL> NullString=<> RecordSeparator=<
> SkipHeaderRecord:false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_POSTGRESQL_CSVFormat(CSVFormat_toString_Test.java:45)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:CSVFormat_toString_Test:testToString_CustomFormatWithSkipHeaderRecordTrue()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_CustomFormatWithSkipHeaderRecordTrue', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Delimiter=<,> QuoteChar=<"> QuoteMode=<MINIMAL> RecordSeparator=<\n> SkipHeaderRecord:true> but was: <Delimiter=<,> QuoteChar=<"> RecordSeparator=<
> EmptyLines:ignored SkipHeaderRecord:true>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_CustomFormatWithSkipHeaderRecordTrue(CSVFormat_toString_Test.java:145)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:CSVFormat_toString_Test:testToString_MONGODB_TSVFormat()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_MONGODB_TSVFormat', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Delimiter=<\t> Escape=<\> QuoteChar=<"> QuoteMode=<MINIMAL> RecordSeparator=<\n> SkipHeaderRecord:false> but was: <Delimiter=<	> Escape=<"> QuoteChar=<"> QuoteMode=<MINIMAL> RecordSeparator=<
> EmptyLines:ignored SkipHeaderRecord:false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_MONGODB_TSVFormat(CSVFormat_toString_Test.java:95)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]
  JUnit Jupiter:CSVFormat_toString_Test:testToString_CustomFormatWithPipeDelimiterAndCarriageReturn()
    MethodSource [className = 'org.apache.commons.csv.CSVFormat_toString_Test', methodName = 'testToString_CustomFormatWithPipeDelimiterAndCarriageReturn', methodParameterTypes = '']
    => org.opentest4j.AssertionFailedError: expected: <Delimiter=<|> Escape=<\> QuoteChar=<"> QuoteMode=<ALL> RecordSeparator=<\r> SkipHeaderRecord:false> but was: <Delimiter=<|> QuoteChar=<"> QuoteMode=<ALL> RecordSeparator=<
> EmptyLines:ignored SkipHeaderRecord:false>
       org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:151)
       org.junit.jupiter.api.AssertionFailureBuilder.buildAndThrow(AssertionFailureBuilder.java:132)
       org.junit.jupiter.api.AssertEquals.failNotEqual(AssertEquals.java:197)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:182)
       org.junit.jupiter.api.AssertEquals.assertEquals(AssertEquals.java:177)
       org.junit.jupiter.api.Assertions.assertEquals(Assertions.java:1142)
       org.apache.commons.csv.CSVFormat_toString_Test.testToString_CustomFormatWithPipeDelimiterAndCarriageReturn(CSVFormat_toString_Test.java:175)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
       java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
       java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
       [...]

Test run finished after 47 ms
[         4 containers found      ]
[         0 containers skipped    ]
[         4 containers started    ]
[         0 containers aborted    ]
[         4 containers successful ]
[         0 containers failed     ]
[        18 tests found           ]
[         0 tests skipped         ]
[        18 tests started         ]
[         0 tests aborted         ]
[         1 tests successful      ]
[        17 tests failed          ]


```


And you can use the following related information to assist you in your analysis and correction:

api document of class org.apache.commons.csv.CSVFormat: 

 * Specifies the format of a CSV file for parsing and writing.
 *
 * <h2>Using predefined formats</h2>
 *
 * <p>
 * You can use one of the predefined formats:
 * </p>
 *
 * <ul>
 * <li>{@link #DEFAULT}</li>
 * <li>{@link #EXCEL}</li>
 * <li>{@link #INFORMIX_UNLOAD}</li>
 * <li>{@link #INFORMIX_UNLOAD_CSV}</li>
 * <li>{@link #MYSQL}</li>
 * <li>{@link #RFC4180}</li>
 * <li>{@link #ORACLE}</li>
 * <li>{@link #POSTGRESQL_CSV}</li>
 * <li>{@link #POSTGRESQL_TEXT}</li>
 * <li>{@link #TDF}</li>
 * </ul>
 *
 * <p>
 * For example:
 * </p>
 *
 * <pre>
 * CSVParser parser = CSVFormat.EXCEL.parse(reader);
 * </pre>
 *
 * <p>
 * The {@link CSVParser} provides static methods to parse other input types, for example:
 * </p>
 *
 * <pre>
 * CSVParser parser = CSVParser.parse(file, StandardCharsets.US_ASCII, CSVFormat.EXCEL);
 * </pre>
 *
 * <h2>Defining formats</h2>
 *
 * <p>
 * You can extend a format by calling the {@code set} methods. For example:
 * </p>
 *
 * <pre>
 * CSVFormat.EXCEL.withNullString(&quot;N/A&quot;).withIgnoreSurroundingSpaces(true);
 * </pre>
 *
 * <h2>Defining column names</h2>
 *
 * <p>
 * To define the column names you want to use to access records, write:
 * </p>
 *
 * <pre>
 * CSVFormat.EXCEL.withHeader(&quot;Col1&quot;, &quot;Col2&quot;, &quot;Col3&quot;);
 * </pre>
 *
 * <p>
 * Calling {@link Builder#setHeader(String...)} lets you use the given names to address values in a {@link CSVRecord}, and assumes that your CSV source does not
 * contain a first record that also defines column names.
 *
 * If it does, then you are overriding this metadata with your names and you should skip the first record by calling
 * {@link Builder#setSkipHeaderRecord(boolean)} with {@code true}.
 * </p>
 *
 * <h2>Parsing</h2>
 *
 * <p>
 * You can use a format directly to parse a reader. For example, to parse an Excel file with columns header, write:
 * </p>
 *
 * <pre>
 * Reader in = ...;
 * CSVFormat.EXCEL.withHeader(&quot;Col1&quot;, &quot;Col2&quot;, &quot;Col3&quot;).parse(in);
 * </pre>
 *
 * <p>
 * For other input types, like resources, files, and URLs, use the static methods on {@link CSVParser}.
 * </p>
 *
 * <h2>Referencing columns safely</h2>
 *
 * <p>
 * If your source contains a header record, you can simplify your code and safely reference columns, by using {@link Builder#setHeader(String...)} with no
 * arguments:
 * </p>
 *
 * <pre>
 * CSVFormat.EXCEL.withHeader();
 * </pre>
 *
 * <p>
 * This causes the parser to read the first record and use its values as column names.
 *
 * Then, call one of the {@link CSVRecord} get method that takes a String column name argument:
 * </p>
 *
 * <pre>
 * String value = record.get(&quot;Col1&quot;);
 * </pre>
 *
 * <p>
 * This makes your code impervious to changes in column order in the CSV file.
 * </p>
 *
 * <h2>Serialization</h2>
 * <p>
 *   This class implements the {@link Serializable} interface with the following caveats:
 * </p>
 * <ul>
 *   <li>This class will no longer implement Serializable in 2.0.</li>
 *   <li>Serialization is not supported from one version to the next.</li>
 * </ul>
 * <p>
 *   The {@code serialVersionUID} values are:
 * </p>
 * <ul>
 *   <li>Version 1.10.0: {@code 2L}</li>
 *   <li>Version 1.9.0 through 1.0: {@code 1L}</li>
 * </ul>
 *
 * <h2>Notes</h2>
 * <p>
 * This class is immutable.
 * </p>
 * <p>
 * Not all settings are used for both parsing and writing.
 * </p>
 

dependent classes: 
class `org.apache.commons.csv.CSVFormat`:
	dependent fields:
		java.lang.Character org.apache.commons.csv.CSVFormat.commentMarker;
		java.lang.String org.apache.commons.csv.CSVFormat.delimiter;
		org.apache.commons.csv.QuoteMode org.apache.commons.csv.CSVFormat.quoteMode;
		java.lang.Character org.apache.commons.csv.CSVFormat.quoteCharacter;
		java.lang.String[] org.apache.commons.csv.CSVFormat.headers;
		java.lang.String org.apache.commons.csv.CSVFormat.nullString;
		java.lang.String org.apache.commons.csv.CSVFormat.recordSeparator;
		boolean org.apache.commons.csv.CSVFormat.skipHeaderRecord;
		java.lang.String[] org.apache.commons.csv.CSVFormat.headerComments;
		java.lang.Character org.apache.commons.csv.CSVFormat.escapeCharacter;
	related functions:
		method `private void validate()` returns `void`, related with `org.apache.commons.csv.CSVFormat.toString()`, api document: 
     * Verifies the validity and consistency of the attributes, and throws an {@link IllegalArgumentException} if necessary.
     * <p>
     * Because an instance can be used for both writing an parsing, not all conditions can be tested here. For example allowMissingColumnNames is only used for
     * parsing, so it cannot be used here.
     * </p>
     *
     * @throws IllegalArgumentException Throw when any attribute is invalid or inconsistent with other attributes.
     ;
		method `public synchronized void print(Object, Appendable, boolean)` returns `void`, related with `org.apache.commons.csv.CSVFormat.toString()`, api document: 
     * Prints the {@code value} as the next value on the line to {@code out}. The value will be escaped or encapsulated as needed. Useful when one wants to
     * avoid creating CSVPrinters. Trims the value if {@link #getTrim()} is true.
     *
     * @param value     value to output.
     * @param out       where to print the value.
     * @param newRecord if this a new record.
     * @throws IOException If an I/O error occurs.
     * @since 1.4
     ;
		method `private void printWithEscapes(Reader, Appendable)` returns `void`, related with `org.apache.commons.csv.CSVFormat.toString()`;
		method `public boolean equals(Object)` returns `boolean`, related with `org.apache.commons.csv.CSVFormat.toString()`;
		method `private synchronized void print(Object, CharSequence, Appendable, boolean)` returns `void`, related with `org.apache.commons.csv.CSVFormat.toString()`;
class `org.apache.commons.csv.ExtendedBufferedReader`:
	related functions:
		method `public String readLine()` returns `java.lang.String`, related with `org.apache.commons.csv.CSVFormat.toString()`, api document: 
     * Gets the next line, dropping the line terminator(s). This method should only be called when processing a
     * comment, otherwise information can be lost.
     * <p>
     * Increments {@link #eolCounter} and updates {@link #position}.
     * </p>
     * <p>
     * Sets {@link #lastChar} to {@link Constants#END_OF_STREAM} at EOF, otherwise the last EOL character.
     * </p>
     *
     * @return the line that was read, or null if reached EOF.
     ;
class `org.apache.commons.csv.CSVParser`:
	related functions:
		method ` CSVRecord nextRecord()` returns `org.apache.commons.csv.CSVRecord`, related with `org.apache.commons.csv.CSVFormat.toString()`, api document: 
     * Parses the next record from the current point in the stream.
     *
     * @return the record as an array of values, or {@code null} if the end of the stream has been reached
     * @throws IOException
     *             on parse error or input read-failure
     ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
