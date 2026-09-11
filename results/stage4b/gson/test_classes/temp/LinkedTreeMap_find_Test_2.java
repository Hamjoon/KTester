package com.google.gson.internal;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LinkedTreeMap_find_Test {

    private LinkedTreeMap<String, String> map;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the LinkedTreeMap before each test
        map = new LinkedTreeMap<>();
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up after each test if necessary
        map.clear();
    }

    // Helper method to invoke the private find method using reflection
    private LinkedTreeMap.Node<String, String> invokeFind(String key, boolean create) {
        try {
            var method = LinkedTreeMap.class.getDeclaredMethod("find", Object.class, boolean.class);
            method.setAccessible(true);
            return (LinkedTreeMap.Node<String, String>) method.invoke(map, key, create);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testFindExistingKey() {
        map.put("key1", "value1");
        // This indirectly tests find method
        assertNotNull(map.findByObject("key1"));
    }

    @Test
    void testFindNonExistingKey() {
        // This indirectly tests find method
        assertNull(map.findByObject("nonExistingKey"));
    }

    @Test
    void testFindWithCreateTrue() {
        var createdNode = map.findByObject("newKey");
        // Node should be created
        assertNotNull(createdNode);
        // Ensure the key is correct
        assertEquals("newKey", createdNode.key);
        // Size should increase
        assertEquals(1, map.size());
    }

    @Test
    void testFindWithCreateFalse() {
        map.put("existingKey", "value");
        // Should return null
        assertNull(map.findByObject("nonExistingKey"));
    }

    @Test
    void testFindWithNullKey() {
        try {
            map.findByObject(null);
            fail("Expected NullPointerException for null key");
        } catch (NullPointerException expected) {
            // Test passes
        }
    }

    @Test
    void testFindExistingKeyWithCreateFalse() {
        // Arrange: Put an existing key in the map
        map.put("existingKey", "value");
        // Act: Attempt to find the existing key with create set to false
        var foundNode = invokeFind("existingKey", false);
        // Assert: The node should not be null and should be associated with the correct key
        assertNotNull(foundNode);
        assertEquals("existingKey", foundNode.key);
    }

    @Test
    void testFindNonExistingKeyWithCreateFalse() {
        // Act: Attempt to find a non-existing key with create set to false
        var foundNode = invokeFind("nonExistingKey", false);
        // Assert: Should return null since the key does not exist
        assertNull(foundNode);
    }

    @Test
    void testFindNonExistingKeyWithCreateTrue() {
        // Act: Attempt to find a non-existing key with create set to true
        var createdNode = invokeFind("nonExistingKey", true);
        // Assert: Node should be created
        assertNotNull(createdNode);
        assertEquals("nonExistingKey", createdNode.key);
        // Size should increase
        assertEquals(1, map.size());
    }

    @Test
    void testFindWithNullKeyAndCreateTrue() {
        // Act: Attempt to find a null key with create set to true
        var createdNode = invokeFind(null, true);
        // Assert: Node should be created for null key
        assertNotNull(createdNode);
        // Ensure the key is null
        assertNull(createdNode.key);
        // Size should increase
        assertEquals(1, map.size());
    }

    @Test
    void testFindWithNonComparableKeyAndCreateTrue() {
        // Act: Attempt to find a non-comparable key with create set to true
        Exception exception = assertThrows(ClassCastException.class, () -> {
            invokeFind("nonComparableKey", true);
        });
        // Assert: Should throw ClassCastException
        assertNotNull(exception);
    }

    @Test
    void testFindExistingKeyWithCreateTrue() {
        // Arrange: Put an existing key in the map
        map.put("existingKey", "value");
        // Act: Attempt to find the existing key with create set to true
        var foundNode = invokeFind("existingKey", true);
        // Assert: The same node should be returned without creating a new one
        assertNotNull(foundNode);
        assertEquals("existingKey", foundNode.key);
        // Size should remain the same
        assertEquals(1, map.size());
    }

    @Test
    void testFindWithNullKeyAndCreateFalse() {
        // Act: Attempt to find a null key with create set to false
        var foundNode = invokeFind(null, false);
        // Assert: Should return null as no node can be found for a null key
        assertNull(foundNode);
    }

    @Test
    void testFindExistingKeyWithCreateFalseOnEmptyMap() {
        // Act: Attempt to find a key in an empty map with create set to false
        var foundNode = invokeFind("existingKey", false);
        // Assert: Should return null since the key does not exist
        assertNull(foundNode);
    }

    @Test
    void testFindExistingKeyWithCreateTrueOnEmptyMap() {
        // Act: Attempt to find a key in an empty map with create set to true
        var createdNode = invokeFind("existingKey", true);
        // Assert: Node should be created
        assertNotNull(createdNode);
        assertEquals("existingKey", createdNode.key);
        // Size should increase
        assertEquals(1, map.size());
    }

    @Test
    void testFindExistingKeyWithDuplicate() {
        // Arrange: Put a key in the map
        map.put("duplicateKey", "value");
        // Act: Attempt to find the existing key with create set to true
        var foundNode = invokeFind("duplicateKey", true);
        // Assert: The same node should be returned without creating a new one
        assertNotNull(foundNode);
        assertEquals("duplicateKey", foundNode.key);
        // Size should remain the same
        assertEquals(1, map.size());
    }

    @Test
    void testFindWithNonComparableKeyAndCreateFalse() {
        // Act: Attempt to find a non-comparable key with create set to false
        var foundNode = invokeFind("nonComparableKey", false);
        // Assert: Should return null as the key cannot be compared
        assertNull(foundNode);
    }

    @Test
    void testFindWithNonComparableKeyAndCreateTrue() {
        // Act: Attempt to find a non-comparable key with create set to true
        Exception exception = assertThrows(ClassCastException.class, () -> {
            invokeFind("nonComparableKey", true);
        });
        // Assert: Should throw ClassCastException
        assertNotNull(exception);
    }
}
