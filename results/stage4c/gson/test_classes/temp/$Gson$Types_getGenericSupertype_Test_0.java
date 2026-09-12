package com.google.gson.internal;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Type;
import static org.mockito.Mockito.*;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class $Gson$Types_getGenericSupertype_Test {

    @Test
    void testGetGenericSupertypeForSameType() {
        // Given
        Type context = String.class;
        Class<?> rawType = String.class;
        Class<?> supertype = String.class;
        // When
        Type result = $Gson$Types.getGenericSupertype(context, rawType, supertype);
        // Then
        assertEquals(context, result);
    }

    @Test
    void testGetGenericSupertypeForInterface() {
        // Given
        Type context = ArrayList.class;
        Class<?> rawType = ArrayList.class;
        Class<?> supertype = List.class;
        // When
        Type result = $Gson$Types.getGenericSupertype(context, rawType, supertype);
        // Then
        assertEquals(List.class, result);
    }

    @Test
    void testGetGenericSupertypeForSuperclass() {
        // Given
        Type context = HashMap.class;
        Class<?> rawType = HashMap.class;
        Class<?> supertype = Map.class;
        // When
        Type result = $Gson$Types.getGenericSupertype(context, rawType, supertype);
        // Then
        assertEquals(Map.class, result);
    }

    @Test
    void testGetGenericSupertypeForPropertiesClass() {
        // Given
        Type context = Properties.class;
        Class<?> rawType = Properties.class;
        Class<?> supertype = Map.class;
        // When
        Type result = $Gson$Types.getGenericSupertype(context, rawType, supertype);
        // Then
        assertEquals(Map.class, result);
    }

    @Test
    void testGetGenericSupertypeForMarkerInterface() {
        // Given
        Type context = HashMap.class;
        Class<?> rawType = HashMap.class;
        Class<?> supertype = Serializable.class;
        // When
        Type result = $Gson$Types.getGenericSupertype(context, rawType, supertype);
        // Then
        assertEquals(Serializable.class, result);
    }

    @Test
    void testGetGenericSupertypeForUnrelatedInterface() {
        // Given
        Type context = HashMap.class;
        Class<?> rawType = HashMap.class;
        Class<?> supertype = RandomAccess.class;
        // When
        Type result = $Gson$Types.getGenericSupertype(context, rawType, supertype);
        // Then
        assertEquals(RandomAccess.class, result);
    }

    @Test
    void testGetGenericSupertypeWithNullContext() {
        // Given
        Type context = null;
        Class<?> rawType = ArrayList.class;
        Class<?> supertype = List.class;
        // Then
        assertThrows(NullPointerException.class, () -> {
            // When
            $Gson$Types.getGenericSupertype(context, rawType, supertype);
        });
    }

    @Test
    void testGetGenericSupertypeWithNullRawType() {
        // Given
        Type context = ArrayList.class;
        Class<?> rawType = null;
        Class<?> supertype = List.class;
        // Then
        assertThrows(NullPointerException.class, () -> {
            // When
            $Gson$Types.getGenericSupertype(context, rawType, supertype);
        });
    }

    @Test
    void testGetGenericSupertypeWithNullSupertype() {
        // Given
        Type context = ArrayList.class;
        Class<?> rawType = ArrayList.class;
        Class<?> supertype = null;
        // Then
        assertThrows(NullPointerException.class, () -> {
            // When
            $Gson$Types.getGenericSupertype(context, rawType, supertype);
        });
    }

    @Test
    void testGetGenericSupertypeForUnrelatedSupertype() {
        // Given
        Type context = ArrayList.class;
        Class<?> rawType = ArrayList.class;
        Class<?> supertype = HashMap.class;
        // When
        Type result = $Gson$Types.getGenericSupertype(context, rawType, supertype);
        // Then
        assertEquals(supertype, result);
    }
}
