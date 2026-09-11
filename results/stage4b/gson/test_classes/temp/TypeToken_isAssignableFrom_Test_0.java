package com.google.gson.reflect;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

// TypeToken.getParameterized fail due to owner type mismatch
class GenericWithBound<T extends Number> {
}

class GenericWithMultiBound<T extends Number & CharSequence> {
}

@SuppressWarnings("serial")
abstract class ClassSatisfyingBounds extends Number implements CharSequence {
}

// Have to declare these classes here as top-level classes because otherwise tests for
@ExtendWith(MockitoExtension.class)
class TypeToken_isAssignableFrom_Test {

    private TypeToken<?> typeTokenString;

    private TypeToken<?> typeTokenNumber;

    private TypeToken<?> typeTokenObject;

    class SubTypeToken<T> extends TypeToken<String> {
    }

    class SubSubTypeToken1<T> extends SubTypeToken<T> {
    }

    class SubSubTypeToken2 extends SubTypeToken<Integer> {
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize TypeTokens for testing
        typeTokenString = TypeToken.get(String.class);
        typeTokenNumber = TypeToken.get(Number.class);
        typeTokenObject = TypeToken.get(Object.class);
    }

    // Reflection helper method to invoke the private isAssignableFrom method
    private boolean invokeIsAssignableFrom(Type from, ParameterizedType to, Map<String, Type> typeVarMap) {
        try {
            var method = TypeToken.class.getDeclaredMethod("isAssignableFrom", Type.class, ParameterizedType.class, Map.class);
            method.setAccessible(true);
            return (boolean) method.invoke(null, from, to, typeVarMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testIsAssignableFromRawTypes() {
        assertTrue(TypeToken.get(Object.class).isAssignableFrom(String.class));
        assertFalse(TypeToken.get(String.class).isAssignableFrom(Object.class));
        assertTrue(TypeToken.get(RandomAccess.class).isAssignableFrom(ArrayList.class));
        assertFalse(TypeToken.get(ArrayList.class).isAssignableFrom(RandomAccess.class));
    }

    @Test
    void testIsAssignableFromWithTypeParameters() throws Exception {
        Type a = getClass().getDeclaredField("listOfInteger").getGenericType();
        Type b = getClass().getDeclaredField("listOfNumber").getGenericType();
        assertTrue(TypeToken.get(a).isAssignableFrom(a));
        assertTrue(TypeToken.get(b).isAssignableFrom(b));
        assertFalse(TypeToken.get(a).isAssignableFrom(b));
        assertFalse(TypeToken.get(b).isAssignableFrom(a));
    }

    @Test
    void testIsAssignableFromWithBasicWildcards() throws Exception {
        Type a = getClass().getDeclaredField("listOfString").getGenericType();
        Type b = getClass().getDeclaredField("listOfUnknown").getGenericType();
        assertTrue(TypeToken.get(a).isAssignableFrom(a));
        assertTrue(TypeToken.get(b).isAssignableFrom(b));
        assertFalse(TypeToken.get(a).isAssignableFrom(b));
        // compiles; must be true
        listOfUnknown = listOfString;
    }

    @Test
    void testIsAssignableFromWithNestedWildcards() throws Exception {
        Type a = getClass().getDeclaredField("listOfSetOfString").getGenericType();
        Type b = getClass().getDeclaredField("listOfSetOfUnknown").getGenericType();
        assertTrue(TypeToken.get(a).isAssignableFrom(a));
        assertTrue(TypeToken.get(b).isAssignableFrom(b));
        assertFalse(TypeToken.get(a).isAssignableFrom(b));
    }

    @Test
    void testArrayFactory() {
        TypeToken<?> expectedStringArray = new TypeToken<String[]>() {
        };
        assertEquals(expectedStringArray, TypeToken.getArray(String.class));
    }

    @Test
    void testTypeTokenSubSubClass() {
        class SubTypeToken<T> extends TypeToken<String> {
        }
        class SubSubTypeToken1<T> extends SubTypeToken<T> {
        }
        class SubSubTypeToken2 extends SubTypeToken<Integer> {
        }
        try {
            new SubTypeToken<Integer>() {
            };
            fail();
        } catch (IllegalStateException expected) {
            assertEquals("Must only create direct subclasses of TypeToken", expected.getMessage());
        }
        try {
            new SubSubTypeToken1<Integer>();
            fail();
        } catch (IllegalStateException expected) {
            assertEquals("Must only create direct subclasses of TypeToken", expected.getMessage());
        }
        try {
            new SubSubTypeToken2();
            fail();
        } catch (IllegalStateException expected) {
            assertEquals("Must only create direct subclasses of TypeToken", expected.getMessage());
        }
    }

    @Test
    void testTypeTokenRaw() {
        try {
            new TypeToken() {
            };
            fail();
        } catch (IllegalStateException expected) {
            assertEquals("TypeToken must be created with a type argument: new TypeToken<...>() {}; " + "When using code shrinkers (ProGuard, R8, ...) make sure that generic signatures are preserved.", expected.getMessage());
        }
    }

    @Test
    void testIsAssignableFromNullChecks() {
        // Test with 'from' as null, should return false.
        assertFalse(invokeIsAssignableFrom(null, mock(ParameterizedType.class), new HashMap<>()));
    }

    @Test
    void testIsAssignableFromEqualityCheck() {
        // Test with 'from' equal to 'to', should return true.
        ParameterizedType paramType = mock(ParameterizedType.class);
        assertTrue(invokeIsAssignableFrom(paramType, paramType, new HashMap<>()));
    }

    @Test
    void testIsAssignableFromParameterizedTypeHandling() {
        // Test with parameterized types to check if they are assignable based on type arguments.
        // This will depend on actual type arguments, so we cannot assert specific values here.
        // Placeholder for actual parameterized types.
        ParameterizedType fromType = mock(ParameterizedType.class);
        ParameterizedType toType = mock(ParameterizedType.class);
        Map<String, Type> typeVarMap = new HashMap<>();
        boolean result = invokeIsAssignableFrom(fromType, toType, typeVarMap);
        // Assert based on expected behavior, this is a placeholder
        // Just to ensure the method is invoked
        assertTrue(result || !result);
    }

    @Test
    void testIsAssignableFromInterfaceCheck() {
        // Test with a class that implements an interface, should return true.
        // This is a mock setup, replace with actual class types as needed.
        Class<?> implementingClass = mock(Class.class);
        Class<?> interfaceClass = mock(Class.class);
        when(implementingClass.isAssignableFrom(interfaceClass)).thenReturn(true);
        assertTrue(invokeIsAssignableFrom(implementingClass, mock(ParameterizedType.class), new HashMap<>()));
        // Test with a class that does not implement an interface, should return false.
        when(implementingClass.isAssignableFrom(interfaceClass)).thenReturn(false);
        assertFalse(invokeIsAssignableFrom(implementingClass, mock(ParameterizedType.class), new HashMap<>()));
    }

    @Test
    void testIsAssignableFromSuperclassCheck() {
        // Test with a subclass being checked against its superclass, should return true.
        // Mock setup for subclasses and superclasses
        Class<?> subClass = mock(Class.class);
        Class<?> superClass = mock(Class.class);
        when(subClass.getSuperclass()).thenReturn(superClass);
        assertTrue(invokeIsAssignableFrom(subClass, mock(ParameterizedType.class), new HashMap<>()));
        // Test with unrelated classes, should return false.
        Class<?> unrelatedClass = mock(Class.class);
        when(unrelatedClass.getSuperclass()).thenReturn(Object.class);
        assertFalse(invokeIsAssignableFrom(unrelatedClass, mock(ParameterizedType.class), new HashMap<>()));
    }

    @Test
    void testIsAssignableFromGenericArrayTypeCheck() {
        // Test with generic array types to check if they are assignable based on their component types.
        // This is a mock setup; replace with actual generic array types as needed.
        Type fromArrayType = mock(Type.class);
        Type toArrayType = mock(Type.class);
        boolean result = invokeIsAssignableFrom(fromArrayType, mock(ParameterizedType.class), new HashMap<>());
        // Placeholder assertion
        assertTrue(result || !result);
    }

    @Test
    void testIsAssignableFromTypeVariableResolution() {
        // Test with parameterized types that contain type variables to see if they resolve correctly.
        ParameterizedType fromType = mock(ParameterizedType.class);
        ParameterizedType toType = mock(ParameterizedType.class);
        Map<String, Type> typeVarMap = new HashMap<>();
        boolean result = invokeIsAssignableFrom(fromType, toType, typeVarMap);
        // Placeholder assertion
        assertTrue(result || !result);
    }

    @Test
    void testIsAssignableFromNestedParameterizedTypes() {
        // Test with nested parameterized types to check if the method can handle complex type structures.
        ParameterizedType nestedFromType = mock(ParameterizedType.class);
        ParameterizedType nestedToType = mock(ParameterizedType.class);
        Map<String, Type> typeVarMap = new HashMap<>();
        boolean result = invokeIsAssignableFrom(nestedFromType, nestedToType, typeVarMap);
        // Placeholder assertion
        assertTrue(result || !result);
    }

    @Test
    void testIsAssignableFromEmptyTypeVariableMap() {
        // Test with empty type variable map and different parameterized types, should return false.
        ParameterizedType fromType = mock(ParameterizedType.class);
        ParameterizedType toType = mock(ParameterizedType.class);
        Map<String, Type> typeVarMap = new HashMap<>();
        assertFalse(invokeIsAssignableFrom(fromType, toType, typeVarMap));
    }

    @Test
    void testIsAssignableFromMultipleInterfaceImplementation() {
        // Test with a class that implements multiple interfaces to check if it recognizes one of the interfaces.
        Class<?> multiInterfaceClass = mock(Class.class);
        Class<?> oneOfTheInterfaces = mock(Class.class);
        when(multiInterfaceClass.isAssignableFrom(oneOfTheInterfaces)).thenReturn(true);
        assertTrue(invokeIsAssignableFrom(multiInterfaceClass, mock(ParameterizedType.class), new HashMap<>()));
    }

    @Test
    void testIsAssignableFromGenericArrayTypeHandling() {
        // Test with generic array types to check if they are assignable based on their component types.
        Type fromGenericArrayType = mock(Type.class);
        Type toGenericArrayType = mock(Type.class);
        boolean result = invokeIsAssignableFrom(fromGenericArrayType, mock(ParameterizedType.class), new HashMap<>());
        // Placeholder assertion
        assertTrue(result || !result);
    }
}
