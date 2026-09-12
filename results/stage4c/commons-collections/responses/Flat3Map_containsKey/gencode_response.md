```java
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

class Flat3Map_containsKey_Test {

    private Flat3Map<Integer, String> flat3Map;

    @BeforeAll
    static void setupBeforeAll() {
        // Setup any shared resources if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        flat3Map = new Flat3Map<>();
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources if needed
    }

    @AfterAll
    static void teardownAfterAll() {
        // Clean up any shared resources if needed
    }

    @Test
    void testContainsKeyWhenKeyExists() {
        // given
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");

        // when
        boolean containsKey1 = flat3Map.containsKey(1);
        boolean containsKey2 = flat3Map.containsKey(2);
        boolean containsKey3 = flat3Map.containsKey(3);

        // then
        assertTrue(containsKey1);
        assertTrue(containsKey2);
        assertTrue(containsKey3);
    }

    @Test
    void testContainsKeyWhenKeyDoesNotExist() {
        // given
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");

        // when
        boolean containsKey = flat3Map.containsKey(3);

        // then
        assertFalse(containsKey);
    }

    @Test
    void testContainsKeyInDelegateMap() {
        // given
        AbstractHashedMap<Integer, String> delegateMap = Mockito.mock(AbstractHashedMap.class);
        Mockito.when(delegateMap.containsKey(4)).thenReturn(true);
        Mockito.when(delegateMap.containsKey(5)).thenReturn(true);

        flat3Map = new Flat3Map<>();
        // Use reflection to set the delegateMap field
        setDelegateMap(flat3Map, delegateMap);

        // when
        boolean containsKey4 = flat3Map.containsKey(4);
        boolean containsKey5 = flat3Map.containsKey(5);

        // then
        assertTrue(containsKey4);
        assertTrue(containsKey5);
    }

    @Test
    void testContainsKeyWithNullKey() {
        // given
        flat3Map.put(null, "NullValue");

        // when
        boolean containsNullKey = flat3Map.containsKey(null);

        // then
        assertTrue(containsNullKey);
    }

    @Test
    void testContainsKeyWithNullKeyWhenNoNullKeyExists() {
        // given
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");

        // when
        boolean containsNullKey = flat3Map.containsKey(null);

        // then
        assertFalse(containsNullKey);
    }

    @Test
    void testContainsKeyWithNonExistingKey() {
        // given
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");

        // when
        boolean containsKey = flat3Map.containsKey(4);

        // then
        assertFalse(containsKey);
    }

    @Test
    void testContainsKeyWhenSizeIsZero() {
        // when
        boolean containsKey = flat3Map.containsKey(1);

        // then
        assertFalse(containsKey);
    }

    private void setDelegateMap(Flat3Map<Integer, String> map, AbstractHashedMap<Integer, String> delegateMap) {
        try {
            java.lang.reflect.Field field = Flat3Map.class.getDeclaredField("delegateMap");
            field.setAccessible(true);
            field.set(map, delegateMap);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to set delegateMap field via reflection", e);
        }
    }
}
```