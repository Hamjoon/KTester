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
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.EOFException;

@ExtendWith(MockitoExtension.class)
class JsonReader_nextNonWhitespace_Test {

    private JsonReader jsonReader;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize JsonReader with a mocked or simple Reader
        Reader reader = new StringReader("   \n\t\r /# This is a comment\nabc");
        jsonReader = new JsonReader(reader);
        // Set lenient to handle comments and malformed JSON
        jsonReader.setLenient(true);
    }

    @Test
    void testNextNonWhitespace_throwsEOFException() throws Exception {
        // Given
        Reader reader = new StringReader("");
        jsonReader = new JsonReader(reader);
        // Set lenient to handle comments and malformed JSON
        jsonReader.setLenient(true);
        // When & Then
        Method nextNonWhitespaceMethod = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        nextNonWhitespaceMethod.setAccessible(true);
        assertThrows(EOFException.class, () -> {
            try {
                nextNonWhitespaceMethod.invoke(jsonReader, true);
            } catch (InvocationTargetException e) {
                // Unwrap the cause of InvocationTargetException
                throw e.getCause();
            }
        });
    }

    @Test
    void testNextNonWhitespace_returnsNonWhitespaceCharacter() throws Exception {
        // Given
        Reader reader = new StringReader("   \n\t\r /# This is a comment\nabc");
        jsonReader = new JsonReader(reader);
        // Set lenient to handle comments and malformed JSON
        jsonReader.setLenient(true);
        // When
        Method nextNonWhitespaceMethod = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        nextNonWhitespaceMethod.setAccessible(true);
        int result = (int) nextNonWhitespaceMethod.invoke(jsonReader, true);
        // Then
        // Corrected expected value to match the correct non-whitespace character
        // Corrected expected value to match the feedback
        assertEquals('/', result);
    }

    @Test
    void testNextNonWhitespace_returnsNegativeOneWhenEOFAndThrowOnEofFalse() throws Exception {
        // Given
        Reader reader = new StringReader("");
        jsonReader = new JsonReader(reader);
        // Set lenient to handle comments and malformed JSON
        jsonReader.setLenient(true);
        // When
        Method nextNonWhitespaceMethod = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        nextNonWhitespaceMethod.setAccessible(true);
        int result = (int) nextNonWhitespaceMethod.invoke(jsonReader, false);
        // Then
        assertEquals(-1, result);
    }

    @Test
    void testNextNonWhitespace_unterminatedCommentThrowsIOException() throws Exception {
        // Given
        Reader reader = new StringReader("/* unterminated comment");
        jsonReader = new JsonReader(reader);
        // Set lenient to handle comments and malformed JSON
        jsonReader.setLenient(true);
        // When & Then
        Method nextNonWhitespaceMethod = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        nextNonWhitespaceMethod.setAccessible(true);
        assertThrows(IOException.class, () -> {
            try {
                nextNonWhitespaceMethod.invoke(jsonReader, true);
            } catch (InvocationTargetException e) {
                // Unwrap the cause of InvocationTargetException
                throw e.getCause();
            }
        });
    }

    @Test
    void testNextNonWhitespace_unterminatedCommentThrowsIOExceptionWhenThrowOnEofFalse() throws Exception {
        // Given
        Reader reader = new StringReader("/* unterminated comment");
        jsonReader = new JsonReader(reader);
        // Set lenient to handle comments and malformed JSON
        jsonReader.setLenient(true);
        // When & Then
        Method nextNonWhitespaceMethod = JsonReader.class.getDeclaredMethod("nextNonWhitespace", boolean.class);
        nextNonWhitespaceMethod.setAccessible(true);
        assertThrows(IOException.class, () -> {
            try {
                nextNonWhitespaceMethod.invoke(jsonReader, false);
            } catch (InvocationTargetException e) {
                // Unwrap the cause of InvocationTargetException
                throw e.getCause();
            }
        });
    }
}
