You are a professional Java software testing expert. Your task is to generate high-quality unit test code for a focal method, using **JUnit5** and **Mockito** libraries. 

You'll be provided with:
1. The focal method to test.
2. The target class's structure (fields and methods).
3. A pre-defined test class framework with `@Before`/`@After` methods.
4. Test cases in JSON format.
5. Any relevant context.

**Requirements:**

Code Quality:
- Include clear comments explaining each test scenario.
- Ensure code is clean, understandable, and maintainable.
- Do not directly access private methods or fields. **Use reflection if the focal method is private.**
- Include all necessary imports; code must be free of syntax errors.

**Test Structure:**
- Follow the given test class framework; do not modify `@Before`/`@After` methods.
- Create one test function per test case group, ensuring all cases are covered.
- Use parameterized tests where appropriate.
- Reinitialize variables inside test functions if `@Setup` values don't fit.
- Properly mock and set up dependencies.

@input{focal method and target class}
Here is the code of focal method, `getGenericSupertype(Type, Class<?>, Class<?>)`, with the focal class `$Gson$Types`:
```Java
package com.google.gson.internal;
import java.util.HashMap;
import static java.util.Objects.requireNonNull;
import static com.google.gson.internal.$Gson$Preconditions.checkArgument;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.util.Properties;
import java.lang.reflect.Modifier;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Map;
import java.io.Serializable;
import java.util.Arrays;
public final class $Gson$Types {
    static final Type[] EMPTY_TYPE_ARRAY = new Type[]{  };
    private static Type getGenericSupertype(Type context, Class<?> rawType, Class<?> supertype) {
        if (supertype == rawType) {
            return context;
        }
        // we skip searching through interfaces if unknown is an interface
        if (supertype.isInterface()) {
            Class<?>[] interfaces = rawType.getInterfaces();
            for (int i = 0, length = interfaces.length; i < length; i++) {
                if (interfaces[i] == supertype) {
                    return rawType.getGenericInterfaces()[i];
                } else if (supertype.isAssignableFrom(interfaces[i])) {
                    return getGenericSupertype(rawType.getGenericInterfaces()[i], interfaces[i], supertype);
                }
            }
        }
        // check our supertypes
        if (!rawType.isInterface()) {
            while (rawType != Object.class) {
                Class<?> rawSupertype = rawType.getSuperclass();
                if (rawSupertype == supertype) {
                    return rawType.getGenericSuperclass();
                } else if (supertype.isAssignableFrom(rawSupertype)) {
                    return getGenericSupertype(rawType.getGenericSuperclass(), rawSupertype, supertype);
                }
                rawType = rawSupertype;
            } 
        }
        // we can't resolve this further
        return supertype;
    }
public static GenericArrayType arrayOf(Type componentType);
    public static Type canonicalize(Type type);
    static void checkNotPrimitive(Type type);
    public static boolean equals(Type a, Type b);
    public static Type getArrayComponentType(Type array);
    public static Type getCollectionElementType(Type context, Class<?> contextRawType);
    public static Type[] getMapKeyAndValueTypes(Type context, Class<?> contextRawType);
    public static Class<?> getRawType(Type type);
    public static ParameterizedType newParameterizedTypeWithOwner(Type ownerType, Type rawType, Type... typeArguments);
    public static Type resolve(Type context, Class<?> contextRawType, Type toResolve);
    public static WildcardType subtypeOf(Type bound);
    public static WildcardType supertypeOf(Type bound);
    public static String typeToString(Type type);
}
```

@input{initial test class framework}
```Java
<initial_class>
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```


@input{context information}

api document of class com.google.gson.internal.$Gson$Types: 

 * Static methods for working with types.
 *
 * @author Bob Lee
 * @author Jesse Wilson
 

api document of method getGenericSupertype(Type, Class<?>, Class<?>): 

   * Returns the generic supertype for {@code supertype}. For example, given a class {@code
   * IntegerSet}, the result for when supertype is {@code Set.class} is {@code Set<Integer>} and the
   * result when the supertype is {@code Collection.class} is {@code Collection<Integer>}.
   

dependent classes: 
class `com.google.gson.internal.$Gson$Types`:
	related functions:
		method `private static Type resolve(Type, Class<?>, Type, Map<TypeVariable<?>, Type>)` returns `java.lang.reflect.Type`, related with `com.google.gson.internal.$Gson$Types.getGenericSupertype(Type, Class<?>, Class<?>)`;
		method `public static Class<?> getRawType(Type)` returns `java.lang.Class<?>`, related with `com.google.gson.internal.$Gson$Types.getGenericSupertype(Type, Class<?>, Class<?>)`;
		method `private static Type getSupertype(Type, Class<?>, Class<?>)` returns `java.lang.reflect.Type`, related with `com.google.gson.internal.$Gson$Types.getGenericSupertype(Type, Class<?>, Class<?>)`, api document: 
   * Returns the generic form of {@code supertype}. For example, if this is {@code
   * ArrayList<String>}, this returns {@code Iterable<String>} given the input {@code
   * Iterable.class}.
   *
   * @param supertype a superclass of, or interface implemented by, this.
   ;
class `com.google.gson.reflect.TypeToken`:
	related functions:
		method `private static boolean isAssignableFrom(Type, ParameterizedType, Map<String, Type>)` returns `boolean`, related with `com.google.gson.internal.$Gson$Types.getGenericSupertype(Type, Class<?>, Class<?>)`, api document: 
   * Private recursive helper function to actually do the type-safe checking
   * of assignability.
   ;
		method `public static TypeToken<?> getParameterized(Type, Type[])` returns `com.google.gson.reflect.TypeToken<?>`, related with `com.google.gson.internal.$Gson$Types.getGenericSupertype(Type, Class<?>, Class<?>)`, api document: 
   * Gets a type literal for the parameterized type represented by applying {@code typeArguments} to
   * {@code rawType}. This is mainly intended for situations where the type arguments are not
   * available at compile time. The following example shows how a type token for {@code Map<K, V>}
   * can be created:
   * <pre>{@code
   * Class<K> keyClass = ...;
   * Class<V> valueClass = ...;
   * TypeToken<?> mapTypeToken = TypeToken.getParameterized(Map.class, keyClass, valueClass);
   * }</pre>
   * As seen here the result is a {@code TypeToken<?>}; this method cannot provide any type safety,
   * and care must be taken to pass in the correct number of type arguments.
   *
   * @throws IllegalArgumentException
   *   If {@code rawType} is not of type {@code Class}, or if the type arguments are invalid for
   *   the raw type
   ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.