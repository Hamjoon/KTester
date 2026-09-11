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
    }

    @Test
    void testRemoveKeyThatExists() {
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");
        assertEquals("One", flat3Map.remove(1));
        assertNull(flat3Map.get(1));
        assertEquals(2, flat3Map.size());
    }

    @Test
    void testRemoveKeyThatDoesNotExist() {
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        // Key 3 does not exist
        assertNull(flat3Map.remove(3));
        assertEquals(2, flat3Map.size());
    }

    @Test
    void testRemoveNullKeyWhenSizeIsThree() {
        flat3Map.put(null, "NullValue");
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        assertEquals("NullValue", flat3Map.remove(null));
        assertNull(flat3Map.get(null));
        assertEquals(2, flat3Map.size());
    }

    @Test
    void testRemoveNullKeyWhenSizeIsLessThanThree() {
        flat3Map.put(null, "NullValue");
        assertEquals("NullValue", flat3Map.remove(null));
        assertNull(flat3Map.get(null));
        assertEquals(0, flat3Map.size());
    }

    @Test
    void testRemoveKeyWhenSizeExceedsThree() {
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");
        // Size exceeds three
        flat3Map.put(4, "Four");
        assertEquals("One", flat3Map.remove(1));
        assertNull(flat3Map.get(1));
        // Size should be 3 now
        assertEquals(3, flat3Map.size());
    }

    @Test
    void testRemoveWithDelegateMap() {
        // Mock the delegateMap to return a specific value when remove is called
        AbstractHashedMap<Integer, String> mockDelegateMap = mock(AbstractHashedMap.class);
        // Use reflection to set the private delegateMap
        java.lang.reflect.Field field = Flat3Map.class.getDeclaredField("delegateMap");
        field.setAccessible(true);
        field.set(flat3Map, mockDelegateMap);
        Integer key = 1;
        String expectedValue = "value associated with 'someKey' in delegateMap";
        when(mockDelegateMap.remove(key)).thenReturn(expectedValue);
        // Perform the remove operation
        String result = flat3Map.remove(key);
        // Validate the result and interaction with the mock
        assertEquals(expectedValue, result);
        verify(mockDelegateMap).remove(key);
    }

    @Test
    void testRemoveWhenSizeIsZero() {
        // Attempt to remove a key when the map size is 0
        String result = flat3Map.remove(1);
        assertNull(result, "Expected null when removing from an empty map");
    }

    @Test
    void testRemoveWhenKeyIsNullAndSizeIsThree() {
        // Populate the map with three entries, including a null key
        flat3Map.put(null, "value3");
        flat3Map.put(1, "value1");
        flat3Map.put(2, "value2");
        // Test removing null key when size is 3
        assertEquals("value3", flat3Map.remove(null), "Expected to return value3");
        assertNull(flat3Map.get(null), "Expected null after removing null key");
        assertEquals(2, flat3Map.size(), "Expected size to be 2 after removal");
    }

    @Test
    void testRemoveWhenKeyIsNullAndSizeIsTwo() {
        // Populate the map with two entries, including a null key
        flat3Map.put(null, "value2");
        flat3Map.put(1, "value1");
        // Test removing null key when size is 2
        assertEquals("value2", flat3Map.remove(null), "Expected to return value2");
        assertNull(flat3Map.get(null), "Expected null after removing null key");
        assertEquals(1, flat3Map.size(), "Expected size to be 1 after removal");
    }

    @Test
    void testRemoveWhenKeyIsNullAndSizeIsOne() {
        // Populate the map with one entry, which is a null key
        flat3Map.put(null, "value1");
        // Test removing null key when size is 1
        assertEquals("value1", flat3Map.remove(null), "Expected to return value1");
        assertNull(flat3Map.get(null), "Expected null after removing null key");
        assertEquals(0, flat3Map.size(), "Expected size to be 0 after removal");
    }

    @Test
    void testRemoveWithValidKeysWhenSizeIsThree() {
        // Populate the map with three entries
        flat3Map.put(1, "value1");
        flat3Map.put(2, "value2");
        flat3Map.put(3, "value3");
        // Test removing each valid key
        assertEquals("value3", flat3Map.remove(3), "Expected to return value3");
        assertEquals(2, flat3Map.size(), "Expected size to be 2 after removal");
        assertEquals("value2", flat3Map.remove(2), "Expected to return value2");
        assertEquals(1, flat3Map.size(), "Expected size to be 1 after removal");
        assertEquals("value1", flat3Map.remove(1), "Expected to return value1");
        assertEquals(0, flat3Map.size(), "Expected size to be 0 after removal");
    }

    @Test
    void testRemoveNonExistingKey() {
        // Populate the map with two entries
        flat3Map.put(1, "value1");
        flat3Map.put(2, "value2");
        // Attempt to remove a non-existing key
        assertNull(flat3Map.remove(3), "Expected null when removing a non-existing key");
        assertEquals(2, flat3Map.size(), "Expected size to remain 2 after removal");
    }
}
