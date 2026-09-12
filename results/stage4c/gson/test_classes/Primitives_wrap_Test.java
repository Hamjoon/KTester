package com.google.gson.internal;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// Add other imports if necessary
class Primitives_wrap_Test {

    @Test
    void testWrapInt() {
        // given
        Class<?> primitiveType = int.class;
        // when
        Class<?> result = Primitives.wrap(primitiveType);
        // then
        assertEquals(Integer.class, result);
    }

    @Test
    void testWrapFloat() {
        // given
        Class<?> primitiveType = float.class;
        // when
        Class<?> result = Primitives.wrap(primitiveType);
        // then
        assertEquals(Float.class, result);
    }

    @Test
    void testWrapByte() {
        // given
        Class<?> primitiveType = byte.class;
        // when
        Class<?> result = Primitives.wrap(primitiveType);
        // then
        assertEquals(Byte.class, result);
    }

    @Test
    void testWrapDouble() {
        // given
        Class<?> primitiveType = double.class;
        // when
        Class<?> result = Primitives.wrap(primitiveType);
        // then
        assertEquals(Double.class, result);
    }

    @Test
    void testWrapLong() {
        // given
        Class<?> primitiveType = long.class;
        // when
        Class<?> result = Primitives.wrap(primitiveType);
        // then
        assertEquals(Long.class, result);
    }

    @Test
    void testWrapChar() {
        // given
        Class<?> primitiveType = char.class;
        // when
        Class<?> result = Primitives.wrap(primitiveType);
        // then
        assertEquals(Character.class, result);
    }

    @Test
    void testWrapBoolean() {
        // given
        Class<?> primitiveType = boolean.class;
        // when
        Class<?> result = Primitives.wrap(primitiveType);
        // then
        assertEquals(Boolean.class, result);
    }

    @Test
    void testWrapShort() {
        // given
        Class<?> primitiveType = short.class;
        // when
        Class<?> result = Primitives.wrap(primitiveType);
        // then
        assertEquals(Short.class, result);
    }

    @Test
    void testWrapVoid() {
        // given
        Class<?> primitiveType = void.class;
        // when
        Class<?> result = Primitives.wrap(primitiveType);
        // then
        assertEquals(Void.class, result);
    }

    @Test
    void testWrapNonPrimitive() {
        // given
        Class<?> nonPrimitiveType = String.class;
        // when
        Class<?> result = Primitives.wrap(nonPrimitiveType);
        // then
        assertEquals(String.class, result);
    }

    @Test
    void testWrapObject() {
        // given
        Class<?> nonPrimitiveType = Object.class;
        // when
        Class<?> result = Primitives.wrap(nonPrimitiveType);
        // then
        assertEquals(Object.class, result);
    }

    @Test
    void testWrapWithNullInput() {
        // given
        Class<?> nullType = null;
        // when & then
        assertThrows(NullPointerException.class, () -> Primitives.wrap(nullType));
    }

    @Test
    void testWrapWrapperType() {
        // given
        Class<?> wrapperType = Integer.class;
        // when
        Class<?> result = Primitives.wrap(wrapperType);
        // then
        assertEquals(Integer.class, result);
    }

    @Test
    void testWrapEnumType() {
        // given
        Class<?> enumType = Enum.class;
        // when
        Class<?> result = Primitives.wrap(enumType);
        // then
        assertEquals(Enum.class, result);
    }

    @Test
    void testWrapArrayOfPrimitive() {
        // given
        Class<?> arrayType = int[].class;
        // when
        Class<?> result = Primitives.wrap(arrayType);
        // then
        assertEquals(int[].class, result);
    }
}
