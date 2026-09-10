package org.apache.commons.collections4.map;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.NoSuchElementException;

@ExtendWith(MockitoExtension.class)
class Flat3Map_containsKey_Test {

    private Flat3Map<Integer, String> flat3Map;

    @BeforeEach
    void setupBeforeEach() {
        flat3Map = new Flat3Map<>();
        // Adding some initial values to the map for testing
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources if needed
        flat3Map.clear();
    }

    // Helper method to set the private delegateMap field using reflection
    private void setDelegateMap(Flat3Map<Integer, String> map, AbstractHashedMap<Integer, String> delegateMap) {
        try {
            java.lang.reflect.Field field = Flat3Map.class.getDeclaredField("delegateMap");
            field.setAccessible(true);
            field.set(map, delegateMap);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to access delegateMap field", e);
        }
    }

    @Test
    void testContainsKey_withExistingKey() {
        // Given
        Integer key = 1;
        // When
        boolean result = flat3Map.containsKey(key);
        // Then
        assertTrue(result, "The map should contain the key: " + key);
    }

    @Test
    void testContainsKey_withNonExistingKey() {
        // Given
        Integer key = 4;
        // When
        boolean result = flat3Map.containsKey(key);
        // Then
        assertFalse(result, "The map should not contain the key: " + key);
    }

    @Test
    void testContainsKey_withNullKey() {
        // Given
        Integer key = null;
        // When
        flat3Map.put(null, "NullValue");
        boolean result = flat3Map.containsKey(key);
        // Then
        assertTrue(result, "The map should contain the null key");
    }

    @Test
    void testContainsKey_withDelegateMapPresent() {
        // Mocking the delegateMap
        AbstractHashedMap<Integer, String> mockDelegateMap = mock(AbstractHashedMap.class);
        // Use reflection to set the private field
        setDelegateMap(flat3Map, mockDelegateMap);
        // Case 1: Delegate map contains the key
        when(mockDelegateMap.containsKey(1)).thenReturn(true);
        boolean result1 = flat3Map.containsKey(1);
        assertTrue(result1, "The method should return true when the delegateMap contains the key.");
        // Case 2: Delegate map does not contain the key
        when(mockDelegateMap.containsKey(4)).thenReturn(false);
        boolean result2 = flat3Map.containsKey(4);
        assertFalse(result2, "The method should return false when the delegateMap does not contain the key.");
    }

    @Test
    void testContainsKey_withNullKeyHandling() {
        // Test if the method returns true when the key is null and all keys are null
        // Resetting the map
        flat3Map.clear();
        flat3Map.put(null, "NullValue1");
        flat3Map.put(null, "NullValue2");
        flat3Map.put(null, "NullValue3");
        assertTrue(flat3Map.containsKey(null), "The method should return true when key is null and key3 is also null.");
        // Test if the method returns false when the key is null and all keys are non-null
        flat3Map.clear();
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");
        assertFalse(flat3Map.containsKey(null), "The method should return false when key is null and all keys are non-null.");
    }

    @Test
    void testContainsKey_withNonNullKeyHandling() {
        // Test if the method returns true when the key matches key1, key2, and key3
        assertTrue(flat3Map.containsKey(1), "The method should return true when the key matches key1.");
        assertTrue(flat3Map.containsKey(2), "The method should return true when the key matches key2.");
        assertTrue(flat3Map.containsKey(3), "The method should return true when the key matches key3.");
        // Test if the method returns false when the key does not match any of the keys
        assertFalse(flat3Map.containsKey(4), "The method should return false when the key does not match any of the keys.");
    }

    @Test
    void testContainsKey_withSizeBoundaryConditions() {
        // Test with size 1
        flat3Map.clear();
        flat3Map.put(1, "One");
        assertTrue(flat3Map.containsKey(1), "The method should return true when the map size is 1 and the key matches key1.");
        // Test with size 2
        flat3Map.clear();
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        assertTrue(flat3Map.containsKey(2), "The method should return true when the map size is 2 and the key matches key2.");
        // Test with size 3
        flat3Map.clear();
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");
        assertTrue(flat3Map.containsKey(3), "The method should return true when the map size is 3 and the key matches key3.");
    }

    @Test
    void testContainsKey_withExceptionScenarios() {
        // Test if the method throws NoSuchElementException when delegateMap is null and size is 0
        flat3Map.clear();
        // Ensure delegateMap is null
        setDelegateMap(flat3Map, null);
        assertThrows(NoSuchElementException.class, () -> flat3Map.containsKey(1), "The method should throw NoSuchElementException when delegateMap is null and size is 0.");
    }
}
