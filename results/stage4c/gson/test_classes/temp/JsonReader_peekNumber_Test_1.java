package com.google.gson.stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class JsonReader_peekNumber_Test {

    private JsonReader jsonReader;

    @BeforeEach
    void setupBeforeEach() {
        // Setup necessary before each test
        Reader reader = new StringReader("123");
        jsonReader = new JsonReader(reader);
    }

    private int invokePeekNumber() throws Exception {
        Method peekNumberMethod = JsonReader.class.getDeclaredMethod("peekNumber");
        peekNumberMethod.setAccessible(true);
        return (int) peekNumberMethod.invoke(jsonReader);
    }

    private int getConstantValue(String fieldName) throws Exception {
        Field field = JsonReader.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.getInt(null);
    }

    @Test
    void testPeekNumber() throws IOException {
        // Given
        // The setup already initializes a JsonReader with "123"
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_LONG"), result);
    }

    @Test
    void testPeekNumberWithInvalidInput() throws IOException {
        // Given
        Reader invalidReader = new StringReader("abc");
        jsonReader = new JsonReader(invalidReader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumberWithSimpleInteger() throws IOException {
        // Given
        Reader reader = new StringReader("123");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_LONG"), result);
    }

    @Test
    void testPeekNumberWithNegativeInteger() throws IOException {
        // Given
        Reader reader = new StringReader("-123");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_LONG"), result);
    }

    @Test
    void testPeekNumberWithDecimalNumber() throws IOException {
        // Given
        Reader reader = new StringReader("123.456");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NUMBER"), result);
    }

    @Test
    void testPeekNumberWithScientificNotation() throws IOException {
        // Given
        Reader reader = new StringReader("1e10");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NUMBER"), result);
    }

    @Test
    void testPeekNumberWithScientificNotationNegativeExponent() throws IOException {
        // Given
        Reader reader = new StringReader("1.2e-3");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NUMBER"), result);
    }

    @Test
    void testPeekNumberWithLeadingZero() throws IOException {
        // Given
        Reader reader = new StringReader("0123");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumberWithNegativeZero() throws IOException {
        // Given
        Reader reader = new StringReader("-0");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_LONG"), result);
    }

    @Test
    void testPeekNumberWithIncompleteScientificNotation() throws IOException {
        // Given
        Reader reader = new StringReader("123e");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumberWithTrailingDecimalPoint() throws IOException {
        // Given
        Reader reader = new StringReader("123.");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumberWithIncompleteExponent() throws IOException {
        // Given
        Reader reader = new StringReader("123e+");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumberWithMaxLongValue() throws IOException {
        // Given
        Reader reader = new StringReader("9223372036854775807");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_LONG"), result);
    }

    @Test
    void testPeekNumberWithMinLongValue() throws IOException {
        // Given
        Reader reader = new StringReader("-9223372036854775808");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_LONG"), result);
    }

    @Test
    void testPeekNumberWithMaxDoubleValue() throws IOException {
        // Given
        Reader reader = new StringReader("1.7976931348623157e308");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NUMBER"), result);
    }

    @Test
    void testPeekNumberWithNegativeMaxDoubleValue() throws IOException {
        // Given
        Reader reader = new StringReader("-1.7976931348623157e308");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NUMBER"), result);
    }

    @Test
    void testPeekNumberWithVerySmallScientificNotation() throws IOException {
        // Given
        Reader reader = new StringReader("1e-308");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NUMBER"), result);
    }

    @Test
    void testPeekNumberWithVerySmallNegativeScientificNotation() throws IOException {
        // Given
        Reader reader = new StringReader("-1e-308");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NUMBER"), result);
    }

    @Test
    void testPeekNumberWithVerySmallDecimal() throws IOException {
        // Given
        Reader reader = new StringReader("0.000000000000000000000000000000000000000000001");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NUMBER"), result);
    }

    @Test
    void testPeekNumberWithExceedingLongValue() throws IOException {
        // Given
        Reader reader = new StringReader("123456789012345678901234567890");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumberWithNumberFollowedByLetters() throws IOException {
        // Given
        Reader reader = new StringReader("123abc");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumberWithNegativeDecimalPoint() throws IOException {
        // Given
        Reader reader = new StringReader("-.5");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumberWithEmptyBuffer() throws IOException {
        // Given
        Reader reader = new StringReader("");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumberWithSingleNegativeSign() throws IOException {
        // Given
        Reader reader = new StringReader("-");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumberWithSinglePositiveSign() throws IOException {
        // Given
        Reader reader = new StringReader("+");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumberWithSingleECharacter() throws IOException {
        // Given
        Reader reader = new StringReader("e");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumberWithSingleDecimalPoint() throws IOException {
        // Given
        Reader reader = new StringReader(".");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumberWithIncompleteNegativeExponent() throws IOException {
        // Given
        Reader reader = new StringReader("9e-");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumberWithIncompletePositiveExponent() throws IOException {
        // Given
        Reader reader = new StringReader("9e+");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumberWithJustOverMaxLongValue() throws IOException {
        // Given
        Reader reader = new StringReader("9223372036854775808");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }

    @Test
    void testPeekNumberWithJustBelowMinLongValue() throws IOException {
        // Given
        Reader reader = new StringReader("-9223372036854775809");
        jsonReader = new JsonReader(reader);
        // When
        int result = invokePeekNumber();
        // Then
        assertEquals(getConstantValue("PEEKED_NONE"), result);
    }
}
