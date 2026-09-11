You are tasked with creating a framework for a test class in Java. Now analyze the following information carefully:

1. The method that needs to be tested and its parameters.
2. The constructor of the target class and its parameters.
3. Any dependencies or external resources used by the target class.

Based on this analysis, complete the test class template in the provided structure:

1. Initialize necessary fields and dependencies within setupBeforeAll() and setupBeforeEach().
2. Handle any required cleanup in teardownAfterEach() and teardownAfterAll().
3. Make sure not to invoke any private methods or fields of the target class and dependent class.
4. Refer to the "given" phase in the existing test class for guidance.
5. Add necessary import statements and ensure there are no syntax errors in the generated code.

@input{focal method and target class}
Here is the code of focal method, `getGenericSupertype(Type, Class<?>, Class<?>)`, with the focal class `$Gson$Types_getGenericSupertype_Test`:
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


@input{context information}

api document of class com.google.gson.internal.$Gson$Types: 

 * Static methods for working with types.
 *
 * @author Bob Lee
 * @author Jesse Wilson
 

constructors for class `com.google.gson.internal.$Gson$Types`: 
params: 
body:
```java
  private $Gson$Types() {
    throw new UnsupportedOperationException();
  }

```

invoke examples of focal method: 
example 1:
```java
  private static Type getGenericSupertype(Type context, Class<?> rawType, Class<?> supertype) {
    if (supertype == rawType) {
    if (supertype.isInterface()) {
      Class<?>[] interfaces = rawType.getInterfaces();
      for (int i = 0, length = interfaces.length; i < length; i++) {
        if (interfaces[i] == supertype) {
        } else if (supertype.isAssignableFrom(interfaces[i])) {
          return getGenericSupertype(rawType.getGenericInterfaces()[i], interfaces[i], supertype);
    if (!rawType.isInterface()) {
      while (rawType != Object.class) {
        Class<?> rawSupertype = rawType.getSuperclass();
        if (rawSupertype == supertype) {
        } else if (supertype.isAssignableFrom(rawSupertype)) {
          return getGenericSupertype(rawType.getGenericSuperclass(), rawSupertype, supertype);
  }
  private static Type getGenericSupertype(Type context, Class<?> rawType, Class<?> supertype) {
    if (supertype == rawType) {
    if (supertype.isInterface()) {
      Class<?>[] interfaces = rawType.getInterfaces();
      for (int i = 0, length = interfaces.length; i < length; i++) {
        if (interfaces[i] == supertype) {
        } else if (supertype.isAssignableFrom(interfaces[i])) {
          return getGenericSupertype(rawType.getGenericInterfaces()[i], interfaces[i], supertype);
    if (!rawType.isInterface()) {
      while (rawType != Object.class) {
        Class<?> rawSupertype = rawType.getSuperclass();
        if (rawSupertype == supertype) {
        } else if (supertype.isAssignableFrom(rawSupertype)) {
          return getGenericSupertype(rawType.getGenericSuperclass(), rawSupertype, supertype);
  }
```
example 2:
```java
  public static Type getCollectionElementType(Type context, Class<?> contextRawType) {
    Type collectionType = getSupertype(context, contextRawType, Collection.class);
  }
  private static Type getSupertype(Type context, Class<?> contextRawType, Class<?> supertype) {
    if (context instanceof WildcardType) {
    checkArgument(supertype.isAssignableFrom(contextRawType));
    return resolve(context, contextRawType,
        $Gson$Types.getGenericSupertype(context, contextRawType, supertype));
  }
```
example 3:
```java
  public static Type[] getMapKeyAndValueTypes(Type context, Class<?> contextRawType) {
    /*
     * Work around a problem with the declaration of java.util.Properties. That
     * class should extend Hashtable<String, String>, but it's declared to
     * extend Hashtable<Object, Object>.
     */
    if (context == Properties.class) {
    Type mapType = getSupertype(context, contextRawType, Map.class);
  }
  private static Type getSupertype(Type context, Class<?> contextRawType, Class<?> supertype) {
    if (context instanceof WildcardType) {
    checkArgument(supertype.isAssignableFrom(contextRawType));
    return resolve(context, contextRawType,
        $Gson$Types.getGenericSupertype(context, contextRawType, supertype));
  }
```



@input{test class template}
```java
package com.google.gson.internal;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class $Gson$Types_getGenericSupertype_Test {
    @BeforeAll
    static void setupBeforeAll() {
    }

    @BeforeEach
    void setupBeforeEach() {
    }

    @AfterEach
    void teardownAfterEach() {
    }

    @AfterAll
    static void teardownAfterAll() {
    }
}
```

@output{initial test class}: now consider the invoke examples output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.