package com.google.gson.internal;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

@ExtendWith(MockitoExtension.class)
class $Gson$Types_getGenericSupertype_Test {

    private Type context;

    private Class<?> rawType;

    private Class<?> supertype;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the parameters for the test cases
        context = new ArrayList<String>() {
        }.getClass().getGenericSuperclass();
        rawType = ArrayList.class;
        supertype = List.class;
    }

    private Type invokeGetGenericSupertype(Type context, Class<?> rawType, Class<?> supertype) {
        try {
            // Use reflection to invoke the private static method getGenericSupertype
            var method = $Gson$Types.class.getDeclaredMethod("getGenericSupertype", Type.class, Class.class, Class.class);
            // Bypass the access restriction
            method.setAccessible(true);
            return (Type) method.invoke(null, context, rawType, supertype);
        } catch (Exception e) {
            if (e.getCause() instanceof NullPointerException) {
                throw (NullPointerException) e.getCause();
            }
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetGenericSupertype() {
        // Given
        Type expectedSupertype = context;
        // When
        Type actualSupertype = invokeGetGenericSupertype(context, rawType, supertype);
        // Then
        assertEquals(expectedSupertype, actualSupertype);
    }

    @Test
    void testGetGenericSupertype_withSameRawType() {
        Type expected = String.class;
        Type actual = invokeGetGenericSupertype(String.class, Object.class, String.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withInterfaceImplementedByRawType() {
        Type expected = List.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, List.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withSuperinterfaceOfRawType() {
        Type expected = Collection.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, Collection.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withSuperclassOfRawType() {
        Type expected = Object.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, Object.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withIndirectSuperinterface() {
        Type expected = Serializable.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, Serializable.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withSameContextAndRawType() {
        Type expected = String.class;
        Type actual = invokeGetGenericSupertype(String.class, String.class, String.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withUnrelatedSupertype() {
        Type expected = Integer.class;
        Type actual = invokeGetGenericSupertype(String.class, String.class, Integer.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withNullContext() {
        Type expected = List.class;
        Type actual = invokeGetGenericSupertype(null, ArrayList.class, List.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withNullRawType() {
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(String.class, null, String.class));
    }

    @Test
    void testGetGenericSupertype_withNullSupertype() {
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(String.class, Object.class, null));
    }

    @Test
    void testGetGenericSupertype_withLinkedList() {
        Type expected = List.class;
        Type actual = invokeGetGenericSupertype(String.class, LinkedList.class, List.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withHashMap() {
        Type expected = Map.class;
        Type actual = invokeGetGenericSupertype(String.class, HashMap.class, Map.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withObjectSuperclass() {
        Type expected = Object.class;
        Type actual = invokeGetGenericSupertype(String.class, HashMap.class, Object.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withIndirectCloneable() {
        Type expected = Cloneable.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, Cloneable.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withRunnable() {
        Type expected = Runnable.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, Runnable.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withNumberSupertype() {
        Type expected = Number.class;
        Type actual = invokeGetGenericSupertype(Integer.class, ArrayList.class, Number.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withNullContextAndRawType() {
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(null, null, String.class));
    }

    @Test
    void testGetGenericSupertype_withAllNullParameters() {
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(null, null, null));
    }
}
