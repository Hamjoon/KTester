```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Assuming the Primitives class is part of the com.google.gson.internal package
import com.google.gson.internal.Primitives;

class Primitives_unwrap_Test {
    
    class CustomClass {}

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
        Class<Integer> input = Integer.class;
        // when
        Class<?> result = Primitives.unwrap(input);
        // then
        assertEquals(int.class, result);
    }

    @Test
    void testUnwrapPrimitiveFloat() {
        // given
        Class<Float> input = Float.class;
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
        Class<CustomClass> input = CustomClass.class;
        // when
        Class<?> result = Primitives.unwrap(input);
        // then
        assertEquals(CustomClass.class, result);
    }
}
```

### Explanation of Corrections:
1. **Imports for JUnit**: The `@Test` annotation and assertions like `assertEquals` and `assertThrows` are part of JUnit, specifically JUnit 5 in this case. The necessary imports from `org.junit.jupiter.api` and `org.junit.jupiter.api.Assertions` are added.
   
2. **Import for Primitives**: The `Primitives` class is part of the `com.google.gson.internal` package, so it was imported accordingly.

3. **Corrected Use of `Primitives`**: The `Primitives` class is assumed to have a static method `unwrap`, which is being called correctly in the test methods.

4. **Test Methods**: Each test method is annotated with `@Test` to be recognized by JUnit as a test case.

5. **CustomClass**: The `CustomClass` is defined once outside of the test methods, avoiding redefinition within the test method.

These corrections should resolve the compilation errors and allow the tests to run correctly.