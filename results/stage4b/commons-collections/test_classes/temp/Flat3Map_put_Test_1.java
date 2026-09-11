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
class Flat3Map_put_Test {

    private Flat3Map<Integer, String> flat3Map;

    @BeforeEach
    void setupBeforeEach() {
        flat3Map = new Flat3Map<>();
    }

    @AfterEach
    void teardownAfterEach() {
        // This method can be used to clear or reset any state after each test
        flat3Map.clear();
    }

    @Test
    void testPutWithNullKey() {
        // Test with null key when size is 0
        String value1 = "valueForNullKey";
        String result = flat3Map.put(null, value1);
        assertNull(result);
        assertEquals(value1, flat3Map.get(null));
        assertEquals(1, flat3Map.size());
        // Test with null key when size is 1 and key1 is null
        flat3Map.put(null, "anotherValue");
        result = flat3Map.put(null, value1);
        assertNull(result);
        assertEquals(value1, flat3Map.get(null));
        assertEquals(1, flat3Map.size());
        // Test with null key when size is 2 and key2 is null
        flat3Map.put(null, "value2");
        result = flat3Map.put(null, value1);
        assertNull(result);
        assertEquals(value1, flat3Map.get(null));
        assertEquals(1, flat3Map.size());
        // Test with null key when size is 3 and key3 is null
        flat3Map.put(null, "value3");
        result = flat3Map.put(null, value1);
        assertEquals("value3", result);
        assertEquals(value1, flat3Map.get(null));
        assertEquals(1, flat3Map.size());
    }

    @Test
    void testPutWithNonNullKey() {
        // Test putting a key-value pair when size is 0
        String key1 = "key1";
        String value1 = "value1";
        String result = flat3Map.put(key1, value1);
        assertNull(result);
        assertEquals(value1, flat3Map.get(key1));
        assertEquals(1, flat3Map.size());
        // Test putting a key-value pair when size is 1 and key1 is not equal to key2
        String key2 = "key2";
        String value2 = "value2";
        result = flat3Map.put(key2, value2);
        assertNull(result);
        assertEquals(value1, flat3Map.get(key1));
        assertEquals(value2, flat3Map.get(key2));
        assertEquals(2, flat3Map.size());
        // Test putting a key-value pair when size is 2 and neither key1 nor key2 match key3
        String key3 = "key3";
        String value3 = "value3";
        result = flat3Map.put(key3, value3);
        assertNull(result);
        assertEquals(value1, flat3Map.get(key1));
        assertEquals(value2, flat3Map.get(key2));
        assertEquals(value3, flat3Map.get(key3));
        assertEquals(3, flat3Map.size());
        // Test putting a value for an existing key (key1) when size is 3
        String newValue1 = "newValue1";
        result = flat3Map.put(key1, newValue1);
        assertEquals(value1, result);
        assertEquals(newValue1, flat3Map.get(key1));
        assertEquals(3, flat3Map.size());
    }

    @Test
    void testPutOverCapacity() {
        // Given
        flat3Map.put("key1", "One");
        flat3Map.put("key2", "Two");
        flat3Map.put("key3", "Three");
        // When
        String result = flat3Map.put("key4", "Four");
        // Then
        assertNull(result);
        assertEquals("One", flat3Map.get("key1"));
        assertEquals("Two", flat3Map.get("key2"));
        assertEquals("Three", flat3Map.get("key3"));
        assertEquals("Four", flat3Map.get("key4"));
        assertEquals(4, flat3Map.size());
    }

    @Test
    void testPutReplaceValue() {
        // Given
        flat3Map.put("key1", "One");
        flat3Map.put("key1", "One Updated");
        // When
        String result = flat3Map.get("key1");
        // Then
        assertEquals("One Updated", result);
        assertEquals(1, flat3Map.size());
    }

    @Test
    void testPutWithDelegateMap() {
        // Given
        // Assuming delegateMap is accessible for testing purposes
        flat3Map.delegateMap = mock(AbstractHashedMap.class);
        String key = "someKey";
        String value = "someValue";
        when(flat3Map.delegateMap.put(key, value)).thenReturn(null);
        // When
        String result = flat3Map.put(key, value);
        // Then
        assertEquals(null, result);
        verify(flat3Map.delegateMap).put(key, value);
    }
}
