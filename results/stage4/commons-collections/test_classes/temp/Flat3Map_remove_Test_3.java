package org.apache.commons.collections4.map;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Flat3Map_remove_Test {

    private Flat3Map<Integer, String> flat3Map;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Flat3Map before each test
        flat3Map = new Flat3Map<>();
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up after each test if necessary
        flat3Map.clear();
    }

    @Test
    void testRemoveExistingKey() {
        // Given
        Integer keyToRemove = 2;
        // When
        String removedValue = flat3Map.remove(keyToRemove);
        // Then
        assertEquals("Two", removedValue);
        assertEquals(2, flat3Map.size());
        assertFalse(flat3Map.containsKey(keyToRemove));
    }

    @Test
    void testRemoveNonExistingKey() {
        // Given
        Integer keyToRemove = 4;
        // When
        String removedValue = flat3Map.remove(keyToRemove);
        // Then
        assertNull(removedValue);
        assertEquals(3, flat3Map.size());
    }

    @Test
    void testRemoveNullKey() {
        // Given
        flat3Map.put(null, "NullValue");
        // When
        String removedValue = flat3Map.remove(null);
        // Then
        assertEquals("NullValue", removedValue);
        assertEquals(2, flat3Map.size());
        assertFalse(flat3Map.containsKey(null));
    }

    @Test
    void testRemoveKeyWhenSizeIsOne() {
        // Given
        flat3Map.remove(1);
        flat3Map.remove(2);
        // When
        String removedValue = flat3Map.remove(3);
        // Then
        assertEquals("Three", removedValue);
        assertEquals(0, flat3Map.size());
        assertFalse(flat3Map.containsKey(3));
    }

    @Test
    void testRemoveWithDelegateMapNotNull() {
        // Given: Mocking a delegateMap and setting it in flat3Map
        AbstractHashedMap<Integer, String> delegateMap = mock(AbstractHashedMap.class);
        // Use reflection to set the delegateMap since it's private
        try {
            java.lang.reflect.Field field = Flat3Map.class.getDeclaredField("delegateMap");
            field.setAccessible(true);
            field.set(flat3Map, delegateMap);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to set delegateMap: " + e.getMessage());
        }
        when(delegateMap.remove("someKey")).thenReturn("value associated with 'someKey'");
        when(delegateMap.remove("anotherKey")).thenReturn("value associated with 'anotherKey'");
        // When: Removing a key when delegateMap is not null
        String removedValue1 = flat3Map.remove("someKey");
        String removedValue2 = flat3Map.remove("anotherKey");
        // Then
        assertEquals("value associated with 'someKey'", removedValue1);
        assertEquals("value associated with 'anotherKey'", removedValue2);
    }

    @Test
    void testRemoveWhenSizeIs0() {
        // Given: Clear the map to set size to 0
        flat3Map.clear();
        // When: Trying to remove any key
        String removedValue = flat3Map.remove("anyKey");
        // Then: Should return null since size is 0
        assertNull(removedValue);
    }

    @Test
    void testRemoveWithKeyNullAndSize3() {
        // Given: Simulating a size of 3 with null keys
        flat3Map.put(null, "value3");
        flat3Map.put(2, "value2");
        flat3Map.put(3, "value1");
        // When: Removing with key null
        String removedValue = flat3Map.remove(null);
        // Then: Should return value3 and reduce size to 2
        assertEquals("value3", removedValue);
        assertEquals(2, flat3Map.size());
    }

    @Test
    void testRemoveWithKeyNullAndSize1() {
        // Given: Set size to 1 with one null key
        flat3Map.put(null, "value1");
        flat3Map.remove(1);
        // When: Removing with key null
        String removedValue = flat3Map.remove(null);
        // Then: Should return value1 and reduce size to 0
        assertEquals("value1", removedValue);
        assertEquals(0, flat3Map.size());
    }

    @Test
    void testRemoveWithNonExistentKey() {
        // Given: A key that does not exist
        Integer keyToRemove = 4;
        // When: Trying to remove a non-existent key
        String removedValue = flat3Map.remove(keyToRemove);
        // Then: Should return null
        assertNull(removedValue);
    }
}
