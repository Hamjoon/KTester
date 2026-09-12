```java
package com.google.gson.internal;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class Primitives_unwrap_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // No global setup required for this test class
    }

    @BeforeEach
    void setupBeforeEach() {
        // No specific setup required before each test
    }

    @AfterEach
    void teardownAfterEach() {
        // No specific teardown required after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // No global teardown required for this test class
    }

    @Test
    void testUnwrapInteger() {
        // given
        Class<Integer> input = Integer.class;

        // when
        Class<?> result = Primitives.unwrap(input);

        // then
        assertEquals(int.class, result);
    }

    @Test
    void testUnwrapFloat() {
        // given
        Class<Float> input = Float.class;

        // when
        Class<?> result = Primitives.unwrap(input);

        // then
        assertEquals(float.class, result);
    }

    @Test
    void testUnwrapByte() {
        // given
        Class<Byte> input = Byte.class;

        // when
        Class<?> result = Primitives.unwrap(input);

        // then
        assertEquals(byte.class, result);
    }

    @Test
    void testUnwrapDouble() {
        // given
        Class<Double> input = Double.class;

        // when
        Class<?> result = Primitives.unwrap(input);

        // then
        assertEquals(double.class, result);
    }

    @Test
    void testUnwrapLong() {
        // given
        Class<Long> input = Long.class;

        // when
        Class<?> result = Primitives.unwrap(input);

        // then
        assertEquals(long.class, result);
    }

    @Test
    void testUnwrapCharacter() {
        // given
        Class<Character> input = Character.class;

        // when
        Class<?> result = Primitives.unwrap(input);

        // then
        assertEquals(char.class, result);
    }

    @Test
    void testUnwrapBoolean() {
        // given
        Class<Boolean> input = Boolean.class;

        // when
        Class<?> result = Primitives.unwrap(input);

        // then
        assertEquals(boolean.class, result);
    }

    @Test
    void testUnwrapShort() {
        // given
        Class<Short> input = Short.class;

        // when
        Class<?> result = Primitives.unwrap(input);

        // then
        assertEquals(short.class, result);
    }

    @Test
    void testUnwrapVoid() {
        // given
        Class<Void> input = Void.class;

        // when
        Class<?> result = Primitives.unwrap(input);

        // then
        assertEquals(void.class, result);
    }

    @Test
    void testUnwrapNonWrapper() {
        // given
        Class<String> input = String.class;

        // when
        Class<?> result = Primitives.unwrap(input);

        // then
        assertEquals(String.class, result);
    }

    @Test
    void testUnwrapObject() {
        // given
        Class<Object> input = Object.class;

        // when
        Class<?> result = Primitives.unwrap(input);

        // then
        assertEquals(Object.class, result);
    }

    @Test
    void testUnwrapPrimitiveInt() {
        // given
        Class<Integer> input = int.class;

        // when
        Class<?> result = Primitives.unwrap(input);

        // then
        assertEquals(int.class, result);
    }

    @Test
    void testUnwrapPrimitiveFloat() {
        // given
        Class<Float> input = float.class;

        // when
        Class<?> result = Primitives.unwrap(input);

        // then
        assertEquals(float.class, result);
    }

    @Test
    void testUnwrapNull() {
        // given
        Class<?> input = null;

        // when / then
        assertThrows(NullPointerException.class, () -> Primitives.unwrap(input));
    }

    @Test
    void testUnwrapCustomClass() {
        // given
        class CustomClass {}
        Class<CustomClass> input = CustomClass.class;

        // when
        Class<?> result = Primitives.unwrap(input);

        // then
        assertEquals(CustomClass.class, result);
    }
}
```