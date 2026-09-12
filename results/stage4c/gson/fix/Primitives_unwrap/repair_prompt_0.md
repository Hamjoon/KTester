
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
// Add other imports if necessary
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

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:5: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:15: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:25: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:35: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:45: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:55: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:65: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:75: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:85: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:95: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:105: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:115: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:125: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:135: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:143: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:10: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:12: error: cannot find symbol
        assertEquals(int.class, result);
        ^
  symbol:   method assertEquals(Class<Integer>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:20: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:22: error: cannot find symbol
        assertEquals(float.class, result);
        ^
  symbol:   method assertEquals(Class<Float>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:30: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:32: error: cannot find symbol
        assertEquals(byte.class, result);
        ^
  symbol:   method assertEquals(Class<Byte>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:40: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:42: error: cannot find symbol
        assertEquals(double.class, result);
        ^
  symbol:   method assertEquals(Class<Double>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:50: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:52: error: cannot find symbol
        assertEquals(long.class, result);
        ^
  symbol:   method assertEquals(Class<Long>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:60: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:62: error: cannot find symbol
        assertEquals(char.class, result);
        ^
  symbol:   method assertEquals(Class<Character>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:70: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:72: error: cannot find symbol
        assertEquals(boolean.class, result);
        ^
  symbol:   method assertEquals(Class<Boolean>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:80: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:82: error: cannot find symbol
        assertEquals(short.class, result);
        ^
  symbol:   method assertEquals(Class<Short>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:90: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:92: error: cannot find symbol
        assertEquals(void.class, result);
        ^
  symbol:   method assertEquals(Class<Void>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:100: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:102: error: cannot find symbol
        assertEquals(String.class, result);
        ^
  symbol:   method assertEquals(Class<String>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:110: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:112: error: cannot find symbol
        assertEquals(Object.class, result);
        ^
  symbol:   method assertEquals(Class<Object>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:120: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:122: error: cannot find symbol
        assertEquals(int.class, result);
        ^
  symbol:   method assertEquals(Class<Integer>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:130: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:132: error: cannot find symbol
        assertEquals(float.class, result);
        ^
  symbol:   method assertEquals(Class<Float>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:140: error: cannot find symbol
        assertThrows(NullPointerException.class, () -> Primitives.unwrap(input));
        ^
  symbol:   method assertThrows(Class<NullPointerException>,()->Primit[...]nput))
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:140: error: cannot find symbol
        assertThrows(NullPointerException.class, () -> Primitives.unwrap(input));
                                                       ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:149: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:151: error: cannot find symbol
        assertEquals(CustomClass.class, result);
        ^
  symbol:   method assertEquals(Class<CustomClass>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
45 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class com.google.gson.internal.Primitives': '\n * Contains static utility methods pertaining to primitive types and their\n * corresponding wrapper types.\n *\n * @author Kevin Bourrillion\n ', 'api document of method unwrap(Class<T>)': '\n   * Returns the corresponding primitive type of {@code type} if it is a\n   * wrapper type; otherwise returns {@code type} itself. Idempotent.\n   * <pre>\n   *     unwrap(Integer.class) == int.class\n   *     unwrap(int.class) == int.class\n   *     unwrap(String.class) == String.class\n   * </pre>\n   ', 'dependent classes': 'class `com.google.gson.FieldAttributes`:\n\trelated functions:\n\t\tmethod `public Class<?> getDeclaringClass()` returns `java.lang.Class<?>`, related with `com.google.gson.internal.Primitives.unwrap(Class<T>)`, api document: \n   * @return the declaring class that contains this field\n   ;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


