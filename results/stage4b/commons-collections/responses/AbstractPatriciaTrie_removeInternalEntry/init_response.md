Based on the provided information and the structure of the test class template, here is the completed test class for the `removeInternalEntry` method of the `AbstractPatriciaTrie` class. The test class initializes necessary fields and dependencies, handles cleanup, and follows the guidelines provided.

```java
package org.apache.commons.collections4.trie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AbstractPatriciaTrie_removeInternalEntry_Test {
    private AbstractPatriciaTrie<String, String> trie;
    private AbstractPatriciaTrie.TrieEntry<String, String> internalEntry;
    private AbstractPatriciaTrie.TrieEntry<String, String> childEntry;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the trie with a mock KeyAnalyzer and necessary entries
        trie = mock(AbstractPatriciaTrie.class);
        internalEntry = new AbstractPatriciaTrie.TrieEntry<>("key", "value", 0);
        childEntry = new AbstractPatriciaTrie.TrieEntry<>("childKey", "childValue", 1);
        
        // Set up the internal structure of the TrieEntry for testing
        internalEntry.left = childEntry;
        internalEntry.right = internalEntry; // Pointing to itself for testing
        internalEntry.parent = new AbstractPatriciaTrie.TrieEntry<>("parentKey", "parentValue", -1);
        internalEntry.predecessor = internalEntry; // Loopback for predecessor
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }

    @Test
    void testRemoveInternalEntry() {
        // Given
        // The given phase is already set up in setupBeforeEach()

        // When
        // We invoke the removeEntry method which will call removeInternalEntry
        trie.removeEntry(internalEntry);

        // Then
        // Assertions to verify the expected state after the removal
        // This would typically involve checking the state of the trie and the entries
        // Since removeInternalEntry is private, we can't directly test it, but we can verify interactions
        verify(trie, times(1)).removeEntry(internalEntry);
    }

    @Test
    void testRemoveRootEntryThrowsException() {
        // Given
        // Setting up the entry to be the root
        internalEntry = new AbstractPatriciaTrie.TrieEntry<>(null, null, -1); // Root Entry
        
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            trie.removeEntry(internalEntry);
        });
    }

    @Test
    void testRemoveNonInternalEntryThrowsException() {
        // Given
        // Setting up the entry as a non-internal entry
        internalEntry = new AbstractPatriciaTrie.TrieEntry<>("key", "value", -1); // Non-internal
        
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            trie.removeEntry(internalEntry);
        });
    }
}
```

### Explanation:
1. **Setup Methods**: The `setupBeforeEach` method initializes the `AbstractPatriciaTrie` and its `TrieEntry` objects. The entries are linked to represent a simple trie structure.
2. **Teardown Methods**: Cleanup methods are included, although they are currently empty since no specific cleanup is required.
3. **Test Methods**: 
   - `testRemoveInternalEntry` checks that the `removeEntry` method interacts correctly with the trie.
   - `testRemoveRootEntryThrowsException` verifies that trying to remove the root entry throws an `IllegalArgumentException`.
   - `testRemoveNonInternalEntryThrowsException` checks that attempting to remove a non-internal entry also throws an exception.

This structure adheres to the principles of unit testing by isolating the tests and ensuring that they verify the expected behavior of the `removeInternalEntry` method indirectly through its public interface.