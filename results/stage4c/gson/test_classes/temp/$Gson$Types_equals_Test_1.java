package com.google.gson.internal;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class $Gson$Types_equals_Test {

    @Test
    void testEqualsWithSameType() {
        // given
        Type type = String.class;
        // when
        boolean result = $Gson$Types.equals(type, type);
        // then
        assertTrue(result, "Expected types to be equal when both are the same instance");
    }

    @Test
    void testEqualsWithDifferentClasses() {
        // given
        Type type1 = String.class;
        Type type2 = Integer.class;
        // when
        boolean result = $Gson$Types.equals(type1, type2);
        // then
        assertFalse(result, "Expected types to be not equal when they are different classes");
    }

    @Test
    void testEqualsWithParameterizedType() {
        // given
        Type type1 = new TypeToken<List<String>>() {
        }.getType();
        Type type2 = new TypeToken<List<String>>() {
        }.getType();
        // when
        boolean result = $Gson$Types.equals(type1, type2);
        // then
        assertTrue(result, "Expected parameterized types to be equal when they are the same");
    }

    @Test
    void testEqualsWithGenericArrayType() {
        // given
        Type type1 = new TypeToken<String[]>() {
        }.getType();
        Type type2 = new TypeToken<String[]>() {
        }.getType();
        // when
        boolean result = $Gson$Types.equals(type1, type2);
        // then
        assertTrue(result, "Expected generic array types to be equal when they are the same");
    }

    @Test
    void testEqualsWithWildcardType() {
        // given
        Type type1 = new TypeToken<List<?>>() {
        }.getType();
        Type type2 = new TypeToken<List<?>>() {
        }.getType();
        // when
        boolean result = $Gson$Types.equals(type1, type2);
        // then
        assertTrue(result, "Expected wildcard types to be equal when they are the same");
    }

    @Test
    void testEqualsWithTypeVariable() {
        // given
        TypeVariable<?> type1 = (TypeVariable<?>) List.class.getTypeParameters()[0];
        TypeVariable<?> type2 = (TypeVariable<?>) List.class.getTypeParameters()[0];
        // when
        boolean result = $Gson$Types.equals(type1, type2);
        // then
        assertTrue(result, "Expected type variables to be equal when they are the same");
    }

    @Test
    void testEqualsWithNullTypes() {
        // given
        Type type1 = null;
        Type type2 = null;
        // when
        boolean result = $Gson$Types.equals(type1, type2);
        // then
        assertTrue(result, "Expected types to be equal when both are null");
    }

    @Test
    void testEqualsWithOneNullType() {
        // given
        Type type1 = null;
        Type type2 = String.class;
        // when
        boolean result = $Gson$Types.equals(type1, type2);
        // then
        assertFalse(result, "Expected types to be not equal when one is null and the other is a Class instance");
    }

    @Test
    void testEqualsWithDifferentParameterizedTypes() {
        // given
        Type type1 = new TypeToken<List<String>>() {
        }.getType();
        Type type2 = new TypeToken<List<Integer>>() {
        }.getType();
        // when
        boolean result = $Gson$Types.equals(type1, type2);
        // then
        assertFalse(result, "Expected parameterized types to be not equal when they have different type arguments");
    }

    @Test
    void testEqualsWithDifferentGenericArrayTypes() {
        // given
        Type type1 = new TypeToken<String[]>() {
        }.getType();
        Type type2 = new TypeToken<Integer[]>() {
        }.getType();
        // when
        boolean result = $Gson$Types.equals(type1, type2);
        // then
        assertFalse(result, "Expected generic array types to be not equal when they have different component types");
    }

    @Test
    void testEqualsWithDifferentWildcardTypes() {
        // given
        Type type1 = new TypeToken<List<? extends Number>>() {
        }.getType();
        Type type2 = new TypeToken<List<? extends String>>() {
        }.getType();
        // when
        boolean result = $Gson$Types.equals(type1, type2);
        // then
        assertFalse(result, "Expected wildcard types to be not equal when they have different upper bounds");
    }

    @Test
    void testEqualsWithDifferentTypeVariables() {
        // given
        TypeVariable<?> type1 = (TypeVariable<?>) List.class.getTypeParameters()[0];
        TypeVariable<?> type2 = (TypeVariable<?>) List.class.getTypeParameters()[0];
        // when
        boolean result = $Gson$Types.equals(type1, type2);
        // then
        assertTrue(result, "Expected type variables to be equal when they have the same name and generic declaration");
    }
}
