
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentHashMap;

@ExtendWith(MockitoExtension.class)
class ConstructorConstructor_newDefaultImplementationConstructor_Test {

    private ConstructorConstructor constructorConstructor;

    @BeforeEach
    void setupBeforeEach() {
        Map<Type, InstanceCreator<?>> instanceCreators = Collections.emptyMap();
        boolean useJdkUnsafe = true;
        List<ReflectionAccessFilter> reflectionFilters = Collections.emptyList();
        constructorConstructor = new ConstructorConstructor(instanceCreators, useJdkUnsafe, reflectionFilters);
    }

    @Test
    void testNewDefaultImplementationConstructorForList() {
        // Given
        Type type = TypeToken.get(List.class).getType();
        Class<? super List> rawType = List.class;
        // When
        ObjectConstructor<List> constructor = constructorConstructor.newDefaultImplementationConstructor(type, rawType);
        // Then
        assertNotNull(constructor);
        List listInstance = constructor.construct();
        assertTrue(listInstance instanceof ArrayList);
    }

    @Test
    void testNewDefaultImplementationConstructorForMap() {
        // Given
        Type type = TypeToken.get(Map.class).getType();
        Class<? super Map> rawType = Map.class;
        // When
        ObjectConstructor<Map> constructor = constructorConstructor.newDefaultImplementationConstructor(type, rawType);
        // Then
        assertNotNull(constructor);
        Map mapInstance = constructor.construct();
        assertTrue(mapInstance instanceof LinkedTreeMap);
    }

    @Test
    void testNewDefaultImplementationConstructorForSortedSet() {
        // Given
        Type type = TypeToken.get(SortedSet.class).getType();
        Class<? super SortedSet> rawType = SortedSet.class;
        // When
        ObjectConstructor<SortedSet> constructor = invokeNewDefaultImplementationConstructor(type, rawType);
        // Then
        assertNotNull(constructor);
        SortedSet setInstance = constructor.construct();
        assertTrue(setInstance instanceof TreeSet);
    }

    @Test
    void testNewDefaultImplementationConstructorForSet() {
        // Given
        Type type = TypeToken.get(Set.class).getType();
        Class<? super Set> rawType = Set.class;
        // When
        ObjectConstructor<Set> constructor = invokeNewDefaultImplementationConstructor(type, rawType);
        // Then
        assertNotNull(constructor);
        Set setInstance = constructor.construct();
        assertTrue(setInstance instanceof LinkedHashSet);
    }

    @Test
    void testNewDefaultImplementationConstructorForQueue() {
        // Given
        Type type = TypeToken.get(Queue.class).getType();
        Class<? super Queue> rawType = Queue.class;
        // When
        ObjectConstructor<Queue> constructor = invokeNewDefaultImplementationConstructor(type, rawType);
        // Then
        assertNotNull(constructor);
        Queue queueInstance = constructor.construct();
        assertTrue(queueInstance instanceof ArrayDeque);
    }

    @Test
    void testNewDefaultImplementationConstructorForConcurrentNavigableMap() {
        // Given
        Type type = TypeToken.get(ConcurrentNavigableMap.class).getType();
        Class<? super ConcurrentNavigableMap> rawType = ConcurrentNavigableMap.class;
        // When
        ObjectConstructor<ConcurrentNavigableMap> constructor = invokeNewDefaultImplementationConstructor(type, rawType);
        // Then
        assertNotNull(constructor);
        ConcurrentNavigableMap mapInstance = constructor.construct();
        assertTrue(mapInstance instanceof ConcurrentSkipListMap);
    }

    @Test
    void testNewDefaultImplementationConstructorForConcurrentMap() {
        // Given
        Type type = TypeToken.get(ConcurrentMap.class).getType();
        Class<? super ConcurrentMap> rawType = ConcurrentMap.class;
        // When
        ObjectConstructor<ConcurrentMap> constructor = invokeNewDefaultImplementationConstructor(type, rawType);
        // Then
        assertNotNull(constructor);
        ConcurrentMap mapInstance = constructor.construct();
        assertTrue(mapInstance instanceof ConcurrentHashMap);
    }

    @Test
    void testNewDefaultImplementationConstructorForSortedMap() {
        // Given
        Type type = TypeToken.get(SortedMap.class).getType();
        Class<? super SortedMap> rawType = SortedMap.class;
        // When
        ObjectConstructor<SortedMap> constructor = invokeNewDefaultImplementationConstructor(type, rawType);
        // Then
        assertNotNull(constructor);
        SortedMap mapInstance = constructor.construct();
        assertTrue(mapInstance instanceof TreeMap);
    }

    @Test
    void testNewDefaultImplementationConstructorForParameterizedMap() {
        // Given
        Type type = TypeToken.getParameterized(Map.class, Integer.class, String.class).getType();
        Class<? super Map> rawType = Map.class;
        // When
        ObjectConstructor<Map> constructor = invokeNewDefaultImplementationConstructor(type, rawType);
        // Then
        assertNotNull(constructor);
        Map mapInstance = constructor.construct();
        assertTrue(mapInstance instanceof LinkedHashMap);
    }

    @Test
    void testNewDefaultImplementationConstructorForNonCollectionOrMap() {
        // Given
        Type type = TypeToken.get(Random.class).getType();
        Class<? super Random> rawType = Random.class;
        // When
        ObjectConstructor<Random> constructor = invokeNewDefaultImplementationConstructor(type, rawType);
        // Then
        assertNull(constructor);
    }

    // Helper method to invoke the private method using reflection
    @SuppressWarnings("unchecked")
    private <T> ObjectConstructor<T> invokeNewDefaultImplementationConstructor(Type type, Class<? super T> rawType) {
        try {
            java.lang.reflect.Method method = ConstructorConstructor.class.getDeclaredMethod("newDefaultImplementationConstructor", Type.class, Class.class);
            method.setAccessible(true);
            return (ObjectConstructor<T>) method.invoke(null, type, rawType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/com/google/gson/internal/ConstructorConstructor_newDefaultImplementationConstructor_Test.java:38: error: <T>newDefaultImplementationConstructor(Type,Class<? super T>) has private access in ConstructorConstructor
        ObjectConstructor<List> constructor = constructorConstructor.newDefaultImplementationConstructor(type, rawType);
                                                                    ^
  where T is a type-variable:
    T extends Object declared in method <T>newDefaultImplementationConstructor(Type,Class<? super T>)
src/test/java/com/google/gson/internal/ConstructorConstructor_newDefaultImplementationConstructor_Test.java:51: error: <T>newDefaultImplementationConstructor(Type,Class<? super T>) has private access in ConstructorConstructor
        ObjectConstructor<Map> constructor = constructorConstructor.newDefaultImplementationConstructor(type, rawType);
                                                                   ^
  where T is a type-variable:
    T extends Object declared in method <T>newDefaultImplementationConstructor(Type,Class<? super T>)
2 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class com.google.gson.internal.ConstructorConstructor': '\n * Returns a function that can construct an instance of a requested type.\n ', 'api document of method newDefaultImplementationConstructor(Type, Class<? super T>)': '\n   * Constructors for common interface types like Map and List and their\n   * subtypes.\n   ', 'return type': 'com.google.gson.internal.ObjectConstructor<T>', 'dependent classes': 'class `com.google.gson.internal.ConstructorConstructor`:\n\trelated functions:\n\t\tmethod `private static ObjectConstructor<T> newSpecialCollectionConstructor(Type, Class<? super T>)` returns `com.google.gson.internal.ObjectConstructor<T>`, related with `com.google.gson.internal.ConstructorConstructor.newDefaultImplementationConstructor(Type, Class<? super T>)`, api document: \n   * Creates constructors for special JDK collection types which do not have a public no-args constructor.\n   ;\n\t\tmethod `private static ObjectConstructor<T> newDefaultImplementationConstructor(Type, Class<? super T>)` returns `com.google.gson.internal.ObjectConstructor<T>`, related with `com.google.gson.internal.ConstructorConstructor.newDefaultImplementationConstructor(Type, Class<? super T>)`, api document: \n   * Constructors for common interface types like Map and List and their\n   * subtypes.\n   ;\nclass `com.google.gson.internal.bind.CollectionTypeAdapterFactory`:\n\trelated functions:\n\t\tmethod `public TypeAdapter<T> create(Gson, TypeToken<T>)` returns `com.google.gson.TypeAdapter<T>`, related with `com.google.gson.internal.ConstructorConstructor.newDefaultImplementationConstructor(Type, Class<? super T>)`;\nclass `com.google.gson.internal.bind.MapTypeAdapterFactory`:\n\trelated functions:\n\t\tmethod `public TypeAdapter<T> create(Gson, TypeToken<T>)` returns `com.google.gson.TypeAdapter<T>`, related with `com.google.gson.internal.ConstructorConstructor.newDefaultImplementationConstructor(Type, Class<? super T>)`;\nclass `com.google.gson.internal.bind.ObjectTypeAdapter`:\n\trelated functions:\n\t\tmethod `private Object tryBeginNesting(JsonReader, JsonToken)` returns `java.lang.Object`, related with `com.google.gson.internal.ConstructorConstructor.newDefaultImplementationConstructor(Type, Class<? super T>)`, api document: \n   * Tries to begin reading a JSON array or JSON object, returning {@code null} if\n   * the next element is neither of those.\n   ;\nclass `com.google.gson.internal.bind.TreeTypeAdapter.SingleTypeFactory`:\n\trelated functions:\n\t\tmethod `public TypeAdapter<T> create(Gson, TypeToken<T>)` returns `com.google.gson.TypeAdapter<T>`, related with `com.google.gson.internal.ConstructorConstructor.newDefaultImplementationConstructor(Type, Class<? super T>)`;\nclass `com.google.gson.internal.bind.ReflectiveTypeAdapterFactory`:\n\trelated functions:\n\t\tmethod `private Map<String, BoundField> getBoundFields(Gson, TypeToken<?>, Class<?>, boolean, boolean)` returns `java.util.Map<java.lang.String, com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField>`, related with `com.google.gson.internal.ConstructorConstructor.newDefaultImplementationConstructor(Type, Class<? super T>)`;\n\t\tmethod `public TypeAdapter<T> create(Gson, TypeToken<T>)` returns `com.google.gson.TypeAdapter<T>`, related with `com.google.gson.internal.ConstructorConstructor.newDefaultImplementationConstructor(Type, Class<? super T>)`;\nclass `com.google.gson.internal.bind.TypeAdapters`:\n\trelated functions:\n\t\tmethod `public static TypeAdapterFactory newTypeHierarchyFactory(Class<T1>, TypeAdapter<T1>)` returns `com.google.gson.TypeAdapterFactory`, related with `com.google.gson.internal.ConstructorConstructor.newDefaultImplementationConstructor(Type, Class<? super T>)`, api document: \n   * Returns a factory for all subtypes of {@code typeAdapter}. We do a runtime check to confirm\n   * that the deserialized type matches the type requested.\n   ;\nclass `com.google.gson.Gson`:\n\trelated functions:\n\t\tmethod `public T fromJson(String, Type)` returns `T`, related with `com.google.gson.internal.ConstructorConstructor.newDefaultImplementationConstructor(Type, Class<? super T>)`, api document: \n   * This method deserializes the specified JSON into an object of the specified type. This method\n   * is useful if the specified object is a generic type. For non-generic objects, use\n   * {@link #fromJson(String, Class)} instead. If you have the JSON in a {@link Reader} instead of\n   * a String, use {@link #fromJson(Reader, Type)} instead.\n   *\n   * <p>Since {@code Type} is not parameterized by T, this method is not type-safe and\n   * should be used carefully. If you are creating the {@code Type} from a {@link TypeToken},\n   * prefer using {@link #fromJson(String, TypeToken)} instead since its return type is based\n   * on the {@code TypeToken} and is therefore more type-safe.\n   *\n   * <p>An exception is thrown if the JSON string has multiple top-level JSON elements,\n   * or if there is trailing data. Use {@link #fromJson(JsonReader, Type)} if this behavior is\n   * not desired.\n   *\n   * @param <T> the type of the desired object\n   * @param json the string from which the object is to be deserialized\n   * @param typeOfT The specific genericized type of src\n   * @return an object of type T from the string. Returns {@code null} if {@code json} is {@code null}\n   * or if {@code json} is empty.\n   * @throws JsonSyntaxException if json is not a valid representation for an object of type typeOfT\n   *\n   * @see #fromJson(Reader, Type)\n   * @see #fromJson(String, Class)\n   * @see #fromJson(String, TypeToken)\n   ;'}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


