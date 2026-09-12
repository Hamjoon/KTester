
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
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
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:4: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:14: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:24: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:34: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:44: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:54: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:64: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:74: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:84: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:94: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:104: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:114: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:124: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:134: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:142: error: cannot find symbol
    @Test
     ^
  symbol:   class Test
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:9: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:11: error: cannot find symbol
        assertEquals(int.class, result);
        ^
  symbol:   method assertEquals(Class<Integer>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:19: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:21: error: cannot find symbol
        assertEquals(float.class, result);
        ^
  symbol:   method assertEquals(Class<Float>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:29: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:31: error: cannot find symbol
        assertEquals(byte.class, result);
        ^
  symbol:   method assertEquals(Class<Byte>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:39: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:41: error: cannot find symbol
        assertEquals(double.class, result);
        ^
  symbol:   method assertEquals(Class<Double>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:49: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:51: error: cannot find symbol
        assertEquals(long.class, result);
        ^
  symbol:   method assertEquals(Class<Long>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:59: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:61: error: cannot find symbol
        assertEquals(char.class, result);
        ^
  symbol:   method assertEquals(Class<Character>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:69: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:71: error: cannot find symbol
        assertEquals(boolean.class, result);
        ^
  symbol:   method assertEquals(Class<Boolean>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:79: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:81: error: cannot find symbol
        assertEquals(short.class, result);
        ^
  symbol:   method assertEquals(Class<Short>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:89: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:91: error: cannot find symbol
        assertEquals(void.class, result);
        ^
  symbol:   method assertEquals(Class<Void>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:99: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:101: error: cannot find symbol
        assertEquals(String.class, result);
        ^
  symbol:   method assertEquals(Class<String>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:109: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:111: error: cannot find symbol
        assertEquals(Object.class, result);
        ^
  symbol:   method assertEquals(Class<Object>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:119: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:121: error: cannot find symbol
        assertEquals(int.class, result);
        ^
  symbol:   method assertEquals(Class<Integer>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:129: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:131: error: cannot find symbol
        assertEquals(float.class, result);
        ^
  symbol:   method assertEquals(Class<Float>,Class<CAP#1>)
  location: class Primitives_unwrap_Test
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:139: error: cannot find symbol
        assertThrows(NullPointerException.class, () -> Primitives.unwrap(input));
        ^
  symbol:   method assertThrows(Class<NullPointerException>,()->Primit[...]nput))
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:139: error: cannot find symbol
        assertThrows(NullPointerException.class, () -> Primitives.unwrap(input));
                                                       ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:148: error: cannot find symbol
        Class<?> result = Primitives.unwrap(input);
                          ^
  symbol:   variable Primitives
  location: class Primitives_unwrap_Test
src/test/java/com/google/gson/internal/Primitives_unwrap_Test.java:150: error: cannot find symbol
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


