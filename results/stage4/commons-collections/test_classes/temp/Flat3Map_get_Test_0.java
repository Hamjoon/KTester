package org.apache.commons.collections4.map;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class Flat3Map_get_Test {

    private Flat3Map<Integer, String> flat3Map;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Flat3Map before each test
        flat3Map = new Flat3Map<>();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
        flat3Map.clear();
    }

    @Test
    void testGetWithNullKey() {
        // Given: Flat3Map with keys and values
        flat3Map.put("key1", "value1");
        flat3Map.put("key2", "value2");
        flat3Map.put("key3", "value3");
        // This will switch to delegateMap
        flat3Map.put("key4", "value4");
        // When: Getting values with a null key
        String result1 = flat3Map.get(null);
        // test multiple calls
        String result2 = flat3Map.get(null);
        // Then: Expecting null since the map is now in delegate mode
        assertNull(result1);
        assertNull(result2);
    }

    @Test
    void testGetWithExistingKey() {
        // Given: Flat3Map with keys and values
        flat3Map.put("key1", "value1");
        flat3Map.put("key2", "value2");
        flat3Map.put("key3", "value3");
        // When: Getting values with existing keys
        String result1 = flat3Map.get("key1");
        String result2 = flat3Map.get("key2");
        String result3 = flat3Map.get("key3");
        // Then: Expecting corresponding values
        assertEquals("value1", result1);
        assertEquals("value2", result2);
        assertEquals("value3", result3);
    }

    @Test
    void testGetWithNonExistingKey() {
        // Given: Flat3Map with some keys and values
        flat3Map.put("key1", "value1");
        flat3Map.put("key2", "value2");
        // When: Getting a value with a non-existing key
        String result = flat3Map.get("key3");
        // Then: Expecting null since key3 is not present
        assertNull(result);
    }

    @Test
    void testGetWithDelegateMap() {
        // Given: Setting up delegateMap by adding more than 3 entries
        flat3Map.put("key1", "value1");
        flat3Map.put("key2", "value2");
        flat3Map.put("key3", "value3");
        // Now it should use delegateMap
        flat3Map.put("key4", "value4");
        // Mocking the delegateMap behavior
        AbstractHashedMap<String, String> delegateMapMock = mock(AbstractHashedMap.class);
        // Use reflection if needed
        flat3Map.delegateMap = delegateMapMock;
        // Mocking the expected behavior
        when(delegateMapMock.get("someKey")).thenReturn("value from delegateMap");
        when(delegateMapMock.get("nonExistentKey")).thenReturn(null);
        // When: Getting values from delegateMap
        String result1 = flat3Map.get("someKey");
        String result2 = flat3Map.get("nonExistentKey");
        // Then: Expecting the mocked values
        assertEquals("value from delegateMap", result1);
        assertNull(result2);
    }

    @Test
    void testGetWithNonNullKeyAndHashCodeMismatch() {
        // Given: Flat3Map with keys and values
        flat3Map.put("key1", "value1");
        flat3Map.put("key2", "value2");
        flat3Map.put("key3", "value3");
        // When: Getting values with keys that have hash code mismatch
        // Should not match any key
        String result1 = flat3Map.get("someKey");
        // Should not match any key
        String result2 = flat3Map.get("anotherKey");
        // Should not match any key
        String result3 = flat3Map.get("yetAnotherKey");
        // Then: Expecting null since keys do not match
        assertNull(result1);
        assertNull(result2);
        assertNull(result3);
    }
}
