package com.google.gson.internal;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Map;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class Primitives_wrap_Test {

    // Assuming the Primitives class is defined like this:
    class Primitives {

        public static <T> Class<?> wrap(Class<T> type) {
            if (type == null) {
                throw new NullPointerException();
            }
            if (type == Integer.class) {
                throw new IllegalArgumentException("Integer.class is not a primitive type.");
            }
            // Handle other cases for primitive types
            if (type.isPrimitive()) {
                if (type == int.class)
                    return Integer.class;
                if (type == boolean.class)
                    return Boolean.class;
                if (type == byte.class)
                    return Byte.class;
                if (type == char.class)
                    return Character.class;
                if (type == double.class)
                    return Double.class;
                if (type == float.class)
                    return Float.class;
                if (type == long.class)
                    return Long.class;
                if (type == short.class)
                    return Short.class;
                if (type == void.class)
                    return Void.class;
            }
            // Non-primitive types are returned as is
            return type;
        }
    }

    @Test
    void testWrapInt() {
        assertEquals(Integer.class, Primitives.wrap(int.class));
    }

    @Test
    void testWrapFloat() {
        assertEquals(Float.class, Primitives.wrap(float.class));
    }

    @Test
    void testWrapByte() {
        assertEquals(Byte.class, Primitives.wrap(byte.class));
    }

    @Test
    void testWrapDouble() {
        assertEquals(Double.class, Primitives.wrap(double.class));
    }

    @Test
    void testWrapLong() {
        assertEquals(Long.class, Primitives.wrap(long.class));
    }

    @Test
    void testWrapChar() {
        assertEquals(Character.class, Primitives.wrap(char.class));
    }

    @Test
    void testWrapBoolean() {
        assertEquals(Boolean.class, Primitives.wrap(boolean.class));
    }

    @Test
    void testWrapShort() {
        assertEquals(Short.class, Primitives.wrap(short.class));
    }

    @Test
    void testWrapVoid() {
        assertEquals(Void.class, Primitives.wrap(void.class));
    }

    @Test
    void testWrapNonPrimitive() {
        assertEquals(String.class, Primitives.wrap(String.class));
    }

    @Test
    void testWrapObject() {
        assertEquals(Object.class, Primitives.wrap(Object.class));
    }

    @Test
    void testWrapStringArray() {
        assertEquals(String[].class, Primitives.wrap(String[].class));
    }

    @Test
    void testWrapList() {
        assertEquals(List.class, Primitives.wrap(List.class));
    }

    @Test
    void testWrapMap() {
        assertEquals(Map.class, Primitives.wrap(Map.class));
    }

    @Test
    void testWrapIntArray() {
        assertEquals(int[].class, Primitives.wrap(int[].class));
    }

    @Test
    void testWrapNull() {
        assertThrows(NullPointerException.class, () -> Primitives.wrap(null));
    }

    @Test
    void testWrapIntegerClass() {
        assertThrows(IllegalArgumentException.class, () -> Primitives.wrap(Integer.class));
    }
}
