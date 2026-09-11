package com.google.gson.internal;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.WildcardType;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.GenericDeclaration;

@ExtendWith(MockitoExtension.class)
class $Gson$Types_equals_Test {

    private Type typeA;

    private Type typeB;

    private Type typeC;

    // Mocking TypeVariable for testing purpose
    private static class TypeVariableImpl implements TypeVariable<Class<?>> {

        private final String name;

        TypeVariableImpl(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Type[] getBounds() {
            return new Type[0];
        }

        @Override
        public GenericDeclaration getGenericDeclaration() {
            // Return null or a proper GenericDeclaration if needed
            return null;
        }

        @Override
        public Type[] getAnnotatedBounds() {
            // Implementing the required method
            return new Type[0];
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize types for testing
        typeA = String.class;
        typeB = String.class;
        typeC = Integer.class;
    }

    @Test
    void testEquals_SameType_ShouldReturnTrue() {
        Type a = typeA;
        Type b = typeB;
        boolean result = $Gson$Types.equals(a, b);
        assertTrue(result, "Expected true when both types are the same.");
    }

    @Test
    void testEquals_DifferentType_ShouldReturnFalse() {
        Type a = typeA;
        Type b = typeC;
        boolean result = $Gson$Types.equals(a, b);
        assertFalse(result, "Expected false when types are different.");
    }

    @Test
    void testEquals_NullTypes_ShouldReturnTrue() {
        Type a = null;
        Type b = null;
        boolean result = $Gson$Types.equals(a, b);
        assertTrue(result, "Expected true when both types are null.");
    }

    @Test
    void testEquals_NullAndNonNullType_ShouldReturnFalse() {
        Type a = typeA;
        Type b = null;
        boolean result = $Gson$Types.equals(a, b);
        assertFalse(result, "Expected false when one type is null and the other is non-null.");
    }

    @Test
    void testEquals_ParameterizedType_Same_ShouldReturnTrue() {
        ParameterizedType listStringA = (ParameterizedType) new ArrayList<String>() {
        }.getClass().getGenericSuperclass();
        ParameterizedType listStringB = (ParameterizedType) new ArrayList<String>() {
        }.getClass().getGenericSuperclass();
        boolean result = $Gson$Types.equals(listStringA, listStringB);
        assertTrue(result, "Expected true when both ParameterizedTypes are the same.");
    }

    @Test
    void testEquals_ParameterizedType_DifferentArguments_ShouldReturnFalse() {
        ParameterizedType listString = (ParameterizedType) new ArrayList<String>() {
        }.getClass().getGenericSuperclass();
        ParameterizedType listInteger = (ParameterizedType) new ArrayList<Integer>() {
        }.getClass().getGenericSuperclass();
        boolean result = $Gson$Types.equals(listString, listInteger);
        assertFalse(result, "Expected false when ParameterizedTypes have different actual type arguments.");
    }

    @Test
    void testEquals_GenericArrayType_Same_ShouldReturnTrue() {
        GenericArrayType stringArrayA = (GenericArrayType) java.lang.reflect.Array.newInstance(String.class, 0).getClass();
        GenericArrayType stringArrayB = (GenericArrayType) java.lang.reflect.Array.newInstance(String.class, 0).getClass();
        boolean result = $Gson$Types.equals(stringArrayA, stringArrayB);
        assertTrue(result, "Expected true when both GenericArrayTypes are the same.");
    }

    @Test
    void testEquals_GenericArrayType_DifferentComponentTypes_ShouldReturnFalse() {
        GenericArrayType stringArray = (GenericArrayType) java.lang.reflect.Array.newInstance(String.class, 0).getClass();
        GenericArrayType integerArray = (GenericArrayType) java.lang.reflect.Array.newInstance(Integer.class, 0).getClass();
        boolean result = $Gson$Types.equals(stringArray, integerArray);
        assertFalse(result, "Expected false when GenericArrayTypes have different component types.");
    }

    @Test
    void testEquals_WildcardType_Same_ShouldReturnTrue() {
        WildcardType wildcardA = $Gson$Types.subtypeOf(Number.class);
        WildcardType wildcardB = $Gson$Types.subtypeOf(Number.class);
        boolean result = $Gson$Types.equals(wildcardA, wildcardB);
        assertTrue(result, "Expected true when both WildcardTypes are the same.");
    }

    @Test
    void testEquals_WildcardType_DifferentBounds_ShouldReturnFalse() {
        WildcardType wildcardA = $Gson$Types.subtypeOf(Number.class);
        WildcardType wildcardB = $Gson$Types.subtypeOf(Integer.class);
        boolean result = $Gson$Types.equals(wildcardA, wildcardB);
        assertFalse(result, "Expected false when WildcardTypes have different upper bounds.");
    }

    @Test
    void testEquals_TypeVariable_Same_ShouldReturnTrue() {
        TypeVariable<?> typeVarA = new TypeVariableImpl("T");
        TypeVariable<?> typeVarB = new TypeVariableImpl("T");
        boolean result = $Gson$Types.equals(typeVarA, typeVarB);
        assertTrue(result, "Expected true when both TypeVariables are the same.");
    }

    @Test
    void testEquals_TypeVariable_DifferentNames_ShouldReturnFalse() {
        TypeVariable<?> typeVarA = new TypeVariableImpl("T");
        TypeVariable<?> typeVarB = new TypeVariableImpl("S");
        boolean result = $Gson$Types.equals(typeVarA, typeVarB);
        assertFalse(result, "Expected false when TypeVariables have different names.");
    }
}
