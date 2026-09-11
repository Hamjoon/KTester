package org.apache.commons.collections4.map;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Flat3Map_containsKey_Test {

    private Flat3Map<Integer, String> flat3Map;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Flat3Map before each test
        flat3Map = new Flat3Map<>();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
        // Clear the map after each test
        flat3Map.clear();
    }

    @Test
    void testContainsKeyWithNullKey() {
        // Test when size is 3 and key3 is null
        flat3Map.put("key1", "value1");
        flat3Map.put("key2", "value2");
        flat3Map.put("key3", null);
        assertTrue(flat3Map.containsKey(null), "Map should contain null key when key3 is null");
        // Test when size is 2 and key2 is null
        flat3Map.clear();
        flat3Map.put("key1", "value1");
        flat3Map.put("key2", null);
        assertTrue(flat3Map.containsKey(null), "Map should contain null key when key2 is null");
        // Test when size is 1 and key1 is null
        flat3Map.clear();
        flat3Map.put(null, "value1");
        assertTrue(flat3Map.containsKey(null), "Map should contain null key when key1 is null");
        // Test when size is 0
        flat3Map.clear();
        assertFalse(flat3Map.containsKey(null), "Map should not contain null key when empty");
    }

    @Test
    void testContainsKeyWithExistingKey() {
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        assertTrue(flat3Map.containsKey(1), "Map should contain key 1");
        assertTrue(flat3Map.containsKey(2), "Map should contain key 2");
    }

    @Test
    void testContainsKeyWithNonExistingKey() {
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        assertFalse(flat3Map.containsKey(3), "Map should not contain key 3");
    }

    @Test
    void testContainsKeyWithMoreThanThreeKeys() {
        flat3Map.put(1, "One");
        flat3Map.put(2, "Two");
        flat3Map.put(3, "Three");
        // This will switch to delegate mode
        flat3Map.put(4, "Four");
        assertTrue(flat3Map.containsKey(1), "Map should contain key 1 after switching to delegate mode");
        assertTrue(flat3Map.containsKey(4), "Map should contain key 4 after switching to delegate mode");
    }

    @Test
    void testContainsKeyWithDelegateMap() {
        // Mocking the delegateMap to test containsKey functionality
        AbstractHashedMap<String, String> mockDelegateMap = mock(AbstractHashedMap.class);
        flat3Map.delegateMap = mockDelegateMap;
        // Test when delegateMap contains the key
        when(mockDelegateMap.containsKey("someKey")).thenReturn(true);
        assertTrue(flat3Map.containsKey("someKey"), "Delegate map should contain 'someKey'");
        // Test when delegateMap does not contain the key
        when(mockDelegateMap.containsKey("someKey")).thenReturn(false);
        assertFalse(flat3Map.containsKey("someKey"), "Delegate map should not contain 'someKey'");
    }

    @Test
    void testContainsKeyWithNonNullKey() {
        // Test when size is 3, hash3 matches and key3 equals the provided key
        flat3Map.put("key1", "value1");
        flat3Map.put("key2", "value2");
        flat3Map.put("key3", "value3");
        flat3Map.hash3 = "key3".hashCode();
        assertTrue(flat3Map.containsKey("key3"), "Map should contain 'key3'");
        // Test when size is 2, hash2 matches and key2 equals the provided key
        flat3Map.clear();
        flat3Map.put("key1", "value1");
        flat3Map.put("key2", "value2");
        flat3Map.hash2 = "key2".hashCode();
        assertTrue(flat3Map.containsKey("key2"), "Map should contain 'key2'");
        // Test when size is 1, hash1 matches and key1 equals the provided key
        flat3Map.clear();
        flat3Map.put("key1", "value1");
        flat3Map.hash1 = "key1".hashCode();
        assertTrue(flat3Map.containsKey("key1"), "Map should contain 'key1'");
        // Test when size is 3, hash3 does not match or key3 does not equal the provided key
        flat3Map.hash3 = "differentKey".hashCode();
        assertFalse(flat3Map.containsKey("key3"), "Map should not contain 'key3'");
        // Test when size is 2, hash2 does not match or key2 does not equal the provided key
        flat3Map.hash2 = "differentKey".hashCode();
        assertFalse(flat3Map.containsKey("key2"), "Map should not contain 'key2'");
        // Test when size is 1, hash1 does not match or key1 does not equal the provided key
        flat3Map.hash1 = "differentKey".hashCode();
        assertFalse(flat3Map.containsKey("key1"), "Map should not contain 'key1'");
    }

    @Test
    void testContainsKeyWithEmptyFlat3Map() {
        // Test when the Flat3Map is empty (size is 0)
        flat3Map.clear();
        assertFalse(flat3Map.containsKey("anyKey"), "Map should not contain any keys when empty");
    }

    @Test
    void testContainsKeyWithSize1AndNullKey() {
        // Test when size is 1 and key1 is null
        flat3Map.put(null, "value1");
        assertTrue(flat3Map.containsKey(null), "Map should contain null key when size is 1 and key1 is null");
    }

    @Test
    void testContainsKeyWithSize2AndNonNullKeys() {
        // Test when size is 2, hash1 matches and key1 equals the provided key
        flat3Map.put("key1", "value1");
        flat3Map.put("key2", "value2");
        flat3Map.hash1 = "key1".hashCode();
        assertTrue(flat3Map.containsKey("key1"), "Map should contain 'key1'");
        // Test when size is 2, hash2 matches and key2 equals the provided key
        flat3Map.hash2 = "key2".hashCode();
        assertTrue(flat3Map.containsKey("key2"), "Map should contain 'key2'");
        // Test when size is 2, hash1 does not match or key1 does not equal the provided key
        flat3Map.hash1 = "differentKey".hashCode();
        assertFalse(flat3Map.containsKey("key1"), "Map should not contain 'key1'");
        // Test when size is 2, hash2 does not match or key2 does not equal the provided key
        flat3Map.hash2 = "differentKey".hashCode();
        assertFalse(flat3Map.containsKey("key2"), "Map should not contain 'key2'");
    }

    @Test
    void testContainsKeyWithSize3AndNonNullKeys() {
        // Test when size is 3, hash1 matches and key1 equals the provided key
        flat3Map.put("key1", "value1");
        flat3Map.put("key2", "value2");
        flat3Map.put("key3", "value3");
        flat3Map.hash1 = "key1".hashCode();
        assertTrue(flat3Map.containsKey("key1"), "Map should contain 'key1'");
        // Test when size is 3, hash2 matches and key2 equals the provided key
        flat3Map.hash2 = "key2".hashCode();
        assertTrue(flat3Map.containsKey("key2"), "Map should contain 'key2'");
        // Test when size is 3, hash3 matches and key3 equals the provided key
        flat3Map.hash3 = "key3".hashCode();
        assertTrue(flat3Map.containsKey("key3"), "Map should contain 'key3'");
        // Test when size is 3, hash1 does not match or key1 does not equal the provided key
        flat3Map.hash1 = "differentKey".hashCode();
        assertFalse(flat3Map.containsKey("key1"), "Map should not contain 'key1'");
        // Test when size is 3, hash2 does not match or key2 does not equal the provided key
        flat3Map.hash2 = "differentKey".hashCode();
        assertFalse(flat3Map.containsKey("key2"), "Map should not contain 'key2'");
        // Test when size is 3, hash3 does not match or key3 does not equal the provided key
        flat3Map.hash3 = "differentKey".hashCode();
        assertFalse(flat3Map.containsKey("key3"), "Map should not contain 'key3'");
    }
}
