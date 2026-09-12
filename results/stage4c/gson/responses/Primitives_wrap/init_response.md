```java
package com.google.gson.internal;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
// Add other imports if necessary

class Primitives_wrap_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global setup required for this static utility class
    }

    @BeforeEach
    void setupBeforeEach() {
        // No instance-specific setup required for this static utility class
    }

    @AfterEach
    void teardownAfterEach() {
        // No instance-specific teardown required for this static utility class
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global teardown required for this static utility class
    }

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
}
```