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
        ObjectConstructor<List> constructor = invokeNewDefaultImplementationConstructor(type, rawType);
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
        ObjectConstructor<Map> constructor = invokeNewDefaultImplementationConstructor(type, rawType);
        // Then
        assertNotNull(constructor);
        Map mapInstance = constructor.construct();
        assertTrue(mapInstance instanceof LinkedHashMap);
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
            return (ObjectConstructor<T>) method.invoke(constructorConstructor, type, rawType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
