package org.apache.commons.collections4.map;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class Flat3Map_get_Test {

    private Flat3Map<Integer, String> flat3Map;

    @BeforeEach
    void setupBeforeEach() {
        flat3Map = new Flat3Map<>();
    }

    @Test
    void testGetWithNullKeyWhenEmpty() {
        // Test case when the map is empty and key is null. The method should return null.
        assertNull(flat3Map.get(null));
    }

    @Test
    void testGetWithNullKeyWhenOneElement() {
        // Test case when key is null and size is 1. The method should return null as key1 is not null.
        flat3Map.put(1, "One");
        String result = flat3Map.get(null);
        assertNull(result);
    }

    @Test
    void testGetWithNullKeyWhenTwoElements() {
        // Test case when key is null and size is 2. The method should return null since key2 is not null.
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        String result = flat3Map.get(null);
        assertNull(result);
    }

    @Test
    void testGetWithNullKeyWhenThreeElements() {
        // Test case when key is null and size is 3. The method should return null since key3 is not null.
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");
        String result = flat3Map.get(null);
        assertNull(result);
    }

    @Test
    void testGetWithExistingKey() {
        // Test case when key is non-null and exists in the map. The method should return the corresponding value.
        flat3Map.put(1, "One");
        String result = flat3Map.get(1);
        assertEquals("One", result);
    }

    @Test
    void testGetWithNonExistingKey() {
        // Test case when key does not match any of the keys in the map. The method should return null.
        flat3Map.put(1, "One");
        String result = flat3Map.get(2);
        assertNull(result);
    }

    @Test
    void testGetWithNullKeyWhenDelegateMode() {
        // Test case when the map switches to delegate mode. The method should return null since we are in delegate mode.
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");
        // This should switch to delegate mode
        flat3Map.put(4, "Four");
        String result = flat3Map.get(null);
        // Expecting null since we are in delegate mode
        assertNull(result);
    }

    @Test
    void testGetWithDelegateMap() {
        // Test case when delegateMap is not null. The method should return the value associated with the key from the delegateMap.
        // Mocking the delegateMap
        AbstractHashedMap<Integer, String> mockDelegateMap = mock(AbstractHashedMap.class);
        // Use reflection to access the private delegateMap field
        try {
            java.lang.reflect.Field delegateMapField = Flat3Map.class.getDeclaredField("delegateMap");
            delegateMapField.setAccessible(true);
            delegateMapField.set(flat3Map, mockDelegateMap);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        when(mockDelegateMap.get("someKey")).thenReturn("value returned by delegateMap.get(someKey)");
        // When
        String result = flat3Map.get("someKey");
        // Then
        assertEquals("value returned by delegateMap.get(someKey)", result);
    }
}
