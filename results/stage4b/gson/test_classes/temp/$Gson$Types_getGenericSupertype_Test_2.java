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
import java.util.HashMap;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.LinkedList;

@ExtendWith(MockitoExtension.class)
class $Gson$Types_getGenericSupertype_Test {

    private Type context;

    private Class<?> rawType;

    private Class<?> supertype;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize test data
        context = new ArrayList<String>() {
        }.getGenericSuperclass();
        rawType = ArrayList.class;
        supertype = List.class;
    }

    // Helper method to use reflection to invoke the private method
    private Type invokeGetGenericSupertype(Type context, Class<?> rawType, Class<?> supertype) {
        try {
            java.lang.reflect.Method method = $Gson$Types.class.getDeclaredMethod("getGenericSupertype", Type.class, Class.class, Class.class);
            method.setAccessible(true);
            return (Type) method.invoke(null, context, rawType, supertype);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGetGenericSupertype() {
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        assertNotNull(result);
        assertEquals(context, result);
    }

    @Test
    void testGetGenericSupertype_SupertypeSameAsRawType() {
        context = String.class;
        rawType = String.class;
        supertype = String.class;
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        assertEquals(context, result);
    }

    @Test
    void testGetGenericSupertype_RawTypeSubclassOfSupertype() {
        context = new ArrayList<String>() {
        }.getGenericSuperclass();
        rawType = ArrayList.class;
        supertype = List.class;
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        assertEquals(context, result);
    }

    @Test
    void testGetGenericSupertype_RawTypeSubclassOfInterfaceSupertype() {
        context = new HashMap<String, String>() {
        }.getGenericSuperclass();
        rawType = HashMap.class;
        supertype = Map.class;
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        assertEquals(context, result);
    }

    @Test
    void testGetGenericSupertype_RawTypeImplementsSupertype() {
        context = Serializable.class;
        rawType = ArrayList.class;
        supertype = Serializable.class;
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        assertEquals(context, result);
    }

    @Test
    void testGetGenericSupertype_RawTypeDoesNotImplementSupertype() {
        context = Object.class;
        rawType = ArrayList.class;
        supertype = Cloneable.class;
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        assertEquals(supertype, result);
    }

    @Test
    void testGetGenericSupertype_ContextIsNull() {
        context = null;
        rawType = String.class;
        supertype = Object.class;
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        assertEquals(supertype, result);
    }

    @Test
    void testGetGenericSupertype_ContextAndRawTypeAreNull() {
        context = null;
        rawType = null;
        supertype = Object.class;
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(context, rawType, supertype));
    }

    @Test
    void testGetGenericSupertype_SupertypeIsNull() {
        context = null;
        rawType = Object.class;
        supertype = null;
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(context, rawType, supertype));
    }

    @Test
    void testGetGenericSupertype_RawTypeSubclassOfSupertypeWithDifferentType() {
        context = CharSequence.class;
        rawType = StringBuilder.class;
        supertype = CharSequence.class;
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        assertEquals(supertype, result);
    }

    @Test
    void testGetGenericSupertype_RawTypeSubclassOfCollectionSupertype() {
        context = new ArrayList<String>() {
        }.getGenericSuperclass();
        rawType = LinkedList.class;
        supertype = Collection.class;
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        assertEquals(context, result);
    }

    @Test
    void testGetGenericSupertype_RawTypeImplementsListSupertype() {
        context = new ArrayList<String>() {
        }.getGenericSuperclass();
        rawType = ArrayList.class;
        supertype = List.class;
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        assertEquals(context, result);
    }

    @Test
    void testGetGenericSupertype_ContextIsNullRawTypeValid() {
        context = null;
        rawType = ArrayList.class;
        supertype = List.class;
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        assertEquals(supertype, result);
    }

    @Test
    void testGetGenericSupertype_RawTypeSubclassOfSerializableSupertype() {
        context = new HashMap<String, String>() {
        }.getGenericSuperclass();
        rawType = HashMap.class;
        supertype = Serializable.class;
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        assertEquals(context, result);
    }

    @Test
    void testGetGenericSupertype_ContextAndRawTypeAreNullButSupertypeValid() {
        context = null;
        rawType = null;
        supertype = Cloneable.class;
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(context, rawType, supertype));
    }

    @Test
    void testGetGenericSupertype_RawTypeSubclassOfCollectionSupertypeAgain() {
        context = new ArrayList<String>() {
        }.getGenericSuperclass();
        rawType = ArrayList.class;
        supertype = Collection.class;
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        assertEquals(context, result);
    }

    @Test
    void testGetGenericSupertype_AllParametersNull() {
        context = null;
        rawType = null;
        supertype = null;
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(context, rawType, supertype));
    }

    @Test
    void testGetGenericSupertype_SupertypeIsNullWithValidContextAndRawType() {
        context = String.class;
        rawType = String.class;
        supertype = null;
        assertThrows(NullPointerException.class, () -> invokeGetGenericSupertype(context, rawType, supertype));
    }

    @Test
    void testGetGenericSupertype_ContextIsNullRawTypeValidSupertypeValid() {
        context = null;
        rawType = String.class;
        supertype = String.class;
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        assertEquals(supertype, result);
    }

    @Test
    void testGetGenericSupertype_AllObject() {
        context = Object.class;
        rawType = Object.class;
        supertype = Object.class;
        Type result = invokeGetGenericSupertype(context, rawType, supertype);
        assertEquals(context, result);
    }
}
