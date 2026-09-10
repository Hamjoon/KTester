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
            return (Type) $Gson$Types.class.getDeclaredMethod("getGenericSupertype", Type.class, Class.class, Class.class).invoke(null, context, rawType, supertype);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetGenericSupertype() {
        // Given
        // This would depend on the actual expected output
        Type expectedSupertype = context;
        // When
        Type actualSupertype = invokeGetGenericSupertype(context, rawType, supertype);
        // Then
        assertEquals(expectedSupertype, actualSupertype);
    }

    @Test
    void testGetGenericSupertype_withSameRawType() {
        // Test when supertype is the same as rawType, expecting to return the context.
        Type expected = String.class;
        Type actual = invokeGetGenericSupertype(String.class, Object.class, String.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withInterfaceImplementedByRawType() {
        // Test when supertype is an interface implemented by rawType, expecting to return the generic interface.
        Type expected = List.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, List.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withSuperinterfaceOfRawType() {
        // Test when supertype is a superinterface of rawType, expecting to return the generic superinterface.
        Type expected = Collection.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, Collection.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withSuperclassOfRawType() {
        // Test when supertype is the superclass of rawType, expecting to return the generic superclass.
        Type expected = Object.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, Object.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withIndirectSuperinterface() {
        // Test when supertype is a superinterface of rawType that is not directly implemented, expecting to return the correct generic superinterface.
        Type expected = Serializable.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, Serializable.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withSameContextAndRawType() {
        // Test when supertype is the same as rawType and context is also the same, expecting to return the context.
        Type expected = String.class;
        Type actual = invokeGetGenericSupertype(String.class, String.class, String.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withUnrelatedSupertype() {
        // Test when supertype is not related to rawType, expecting to return the supertype itself.
        Type expected = Integer.class;
        Type actual = invokeGetGenericSupertype(String.class, String.class, Integer.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withNullContext() {
        // Test when context is null, expecting to return the correct generic interface.
        Type expected = List.class;
        Type actual = invokeGetGenericSupertype(null, ArrayList.class, List.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withNullRawType() {
        // Test when rawType is null, expecting a NullPointerException.
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(String.class, null, String.class));
    }

    @Test
    void testGetGenericSupertype_withNullSupertype() {
        // Test when supertype is null, expecting a NullPointerException.
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(String.class, Object.class, null));
    }

    @Test
    void testGetGenericSupertype_withLinkedList() {
        // Test when supertype is an interface implemented by rawType, expecting to return the generic interface List.
        Type expected = List.class;
        Type actual = invokeGetGenericSupertype(String.class, LinkedList.class, List.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withHashMap() {
        // Test when supertype is a superinterface of rawType, expecting to return the generic superinterface Map.
        Type expected = Map.class;
        Type actual = invokeGetGenericSupertype(String.class, HashMap.class, Map.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withObjectSuperclass() {
        // Test when supertype is the superclass of rawType, expecting to return the generic superclass Object.
        Type expected = Object.class;
        Type actual = invokeGetGenericSupertype(String.class, HashMap.class, Object.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withIndirectCloneable() {
        // Test when supertype is a superinterface of rawType that is not directly implemented, expecting to return the correct generic superinterface Cloneable.
        Type expected = Cloneable.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, Cloneable.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withRunnable() {
        // Test when supertype is not related to rawType, expecting to return the supertype Runnable.
        Type expected = Runnable.class;
        Type actual = invokeGetGenericSupertype(String.class, ArrayList.class, Runnable.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withNumberSupertype() {
        // Test when context is Integer, rawType is ArrayList, and supertype is Number, expecting to return the supertype Number.
        Type expected = Number.class;
        Type actual = invokeGetGenericSupertype(Integer.class, ArrayList.class, Number.class);
        assertEquals(expected, actual);
    }

    @Test
    void testGetGenericSupertype_withNullContextAndRawType() {
        // Test when both context and rawType are null, expecting a NullPointerException.
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(null, null, String.class));
    }

//     @Test
//     void testGetGenericSupertype_withNullRawType() {
//         // Test when rawType is null, expecting a NullPointerException.
//         assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(String.class, null, String.class));
//     }

//     @Test
//     void testGetGenericSupertype_withNullSupertype() {
//         // Test when supertype is null, expecting a NullPointerException.
//         assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(String.class, Object.class, null));
//     }

    @Test
    void testGetGenericSupertype_withAllNullParameters() {
        // Test when all parameters are null, expecting a NullPointerException.
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(null, null, null));
    }
}