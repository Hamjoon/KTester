package com.google.gson.internal;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Primitives_wrap_Test {

    // Define any necessary fields here
    private Class<?> inputClass;

    private Class<?> expectedClass;

    @Test
    void testWrap_int() {
        // Given
        inputClass = int.class;
        expectedClass = Integer.class;
        // When
        Class<?> wrappedClass = Primitives.wrap(inputClass);
        // Then
        assertEquals(expectedClass, wrappedClass);
    }

    @Test
    void testWrap_float() {
        // Given
        inputClass = float.class;
        expectedClass = Float.class;
        // When
        Class<?> wrappedClass = Primitives.wrap(inputClass);
        // Then
        assertEquals(expectedClass, wrappedClass);
    }

    @Test
    void testWrap_byte() {
        // Given
        inputClass = byte.class;
        expectedClass = Byte.class;
        // When
        Class<?> wrappedClass = Primitives.wrap(inputClass);
        // Then
        assertEquals(expectedClass, wrappedClass);
    }

    @Test
    void testWrap_double() {
        // Given
        inputClass = double.class;
        expectedClass = Double.class;
        // When
        Class<?> wrappedClass = Primitives.wrap(inputClass);
        // Then
        assertEquals(expectedClass, wrappedClass);
    }

    @Test
    void testWrap_long() {
        // Given
        inputClass = long.class;
        expectedClass = Long.class;
        // When
        Class<?> wrappedClass = Primitives.wrap(inputClass);
        // Then
        assertEquals(expectedClass, wrappedClass);
    }

    @Test
    void testWrap_char() {
        // Given
        inputClass = char.class;
        expectedClass = Character.class;
        // When
        Class<?> wrappedClass = Primitives.wrap(inputClass);
        // Then
        assertEquals(expectedClass, wrappedClass);
    }

    @Test
    void testWrap_boolean() {
        // Given
        inputClass = boolean.class;
        expectedClass = Boolean.class;
        // When
        Class<?> wrappedClass = Primitives.wrap(inputClass);
        // Then
        assertEquals(expectedClass, wrappedClass);
    }

    @Test
    void testWrap_short() {
        // Given
        inputClass = short.class;
        expectedClass = Short.class;
        // When
        Class<?> wrappedClass = Primitives.wrap(inputClass);
        // Then
        assertEquals(expectedClass, wrappedClass);
    }

    @Test
    void testWrap_void() {
        // Given
        inputClass = void.class;
        expectedClass = Void.class;
        // When
        Class<?> wrappedClass = Primitives.wrap(inputClass);
        // Then
        assertEquals(expectedClass, wrappedClass);
    }

    @Test
    void testWrap_NonPrimitive() {
        // Given
        inputClass = String.class;
        expectedClass = String.class;
        // When
        Class<?> wrappedClass = Primitives.wrap(inputClass);
        // Then
        assertEquals(expectedClass, wrappedClass);
    }

    @Test
    void testWrap_Null() {
        // Given
        inputClass = null;
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            Primitives.wrap(inputClass);
        });
    }

    @Test
    void testWrap_Array() {
        // Given
        // Assuming Array is a placeholder for an actual array class
        inputClass = Array.class;
        // When
        Class<?> wrappedClass = Primitives.wrap(inputClass);
        // Then
        assertEquals(inputClass, wrappedClass);
    }

    @Test
    void testWrap_Map() {
        // Given
        inputClass = Map.class;
        // When
        Class<?> wrappedClass = Primitives.wrap(inputClass);
        // Then
        assertEquals(inputClass, wrappedClass);
    }

    @Test
    void testWrap_List() {
        // Given
        inputClass = List.class;
        // When
        Class<?> wrappedClass = Primitives.wrap(inputClass);
        // Then
        assertEquals(inputClass, wrappedClass);
    }
}
