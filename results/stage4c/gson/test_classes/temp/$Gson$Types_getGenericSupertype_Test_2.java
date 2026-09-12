package com.google.gson.internal;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Type;
import static org.mockito.Mockito.*;
import java.util.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.Serializable;
import org.junit.jupiter.api.extension.ExtendWith;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class $Gson$Types_getGenericSupertype_Test {

    private Type invokeGetGenericSupertype(Type context, Class<?> rawType, Class<?> supertype) throws Exception {
        if (context == null || rawType == null || supertype == null) {
            throw new NullPointerException("Context, rawType, and supertype must not be null");
        }
        Method method = $Gson$Types.class.getDeclaredMethod("getGenericSupertype", Type.class, Class.class, Class.class);
        method.setAccessible(true);
        return (Type) method.invoke(null, context, rawType, supertype);
    }

    @Test
    void testGetGenericSupertypeForSameType() throws Exception {
        // Given
        Type context = String.class;
        Class<?> rawType = String.class;
        Class<?> supertype = String.class;
        // When
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        // Then
        assertEquals(context, result);
    }

    @Test
    void testGetGenericSupertypeForInterface() throws Exception {
        // Given
        Type context = ArrayList.class;
        Class<?> rawType = ArrayList.class;
        Class<?> supertype = List.class;
        // When
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        // Then
        assertEquals(List.class, ((ParameterizedType) result).getRawType());
    }

    @Test
    void testGetGenericSupertypeForSuperclass() throws Exception {
        // Given
        Type context = HashMap.class;
        Class<?> rawType = HashMap.class;
        Class<?> supertype = Map.class;
        // When
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        // Then
        assertEquals(Map.class, ((ParameterizedType) result).getRawType());
    }

    @Test
    void testGetGenericSupertypeForPropertiesClass() throws Exception {
        // Given
        Type context = Properties.class;
        Class<?> rawType = Properties.class;
        Class<?> supertype = Map.class;
        // When
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        // Then
        assertEquals(Map.class, ((ParameterizedType) result).getRawType());
    }

    @Test
    void testGetGenericSupertypeForMarkerInterface() throws Exception {
        // Given
        Type context = HashMap.class;
        Class<?> rawType = HashMap.class;
        Class<?> supertype = Serializable.class;
        // When
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        // Then
        assertEquals(Serializable.class, result);
    }

    @Test
    void testGetGenericSupertypeForUnrelatedInterface() throws Exception {
        // Given
        Type context = HashMap.class;
        Class<?> rawType = HashMap.class;
        Class<?> supertype = RandomAccess.class;
        // When
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
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
            invokeGetGenericSupertype(context, rawType, supertype);
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
            invokeGetGenericSupertype(context, rawType, supertype);
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
            invokeGetGenericSupertype(context, rawType, supertype);
        });
    }

    @Test
    void testGetGenericSupertypeForUnrelatedSupertype() throws Exception {
        // Given
        Type context = ArrayList.class;
        Class<?> rawType = ArrayList.class;
        Class<?> supertype = HashMap.class;
        // When
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        // Then
        assertEquals(supertype, result);
    }
}
