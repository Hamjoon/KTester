package org.apache.commons.collections4.trie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AbstractPatriciaTrie_removeInternalEntry_Test {

    private AbstractPatriciaTrie<String, String> trie;

    private AbstractPatriciaTrie.TrieEntry<String, String> internalEntry;

    private AbstractPatriciaTrie.TrieEntry<String, String> parentEntry;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the trie with a mock KeyAnalyzer, and create entries for testing
        KeyAnalyzer<String> keyAnalyzer = mock(KeyAnalyzer.class);
        // Using the constructor with KeyAnalyzer
        trie = new AbstractPatriciaTrie<>(keyAnalyzer);
        // Setup an internal TrieEntry and its parent for testing
        parentEntry = new AbstractPatriciaTrie.TrieEntry<>("parentKey", "parentValue", 0);
        internalEntry = new AbstractPatriciaTrie.TrieEntry<>("childKey", "childValue", 1);
        // Setting up the parent-child relationship
        internalEntry.parent = parentEntry;
        // Assuming child is the left child
        parentEntry.left = internalEntry;
        // Setting predecessor to itself for testing
        parentEntry.predecessor = parentEntry;
    }

    @Test
    void testRemoveInternalEntry() {
        // Given: an internal entry to remove
        // Invoke the method that calls removeInternalEntry
        trie.removeEntry(internalEntry);
        // Then: verify the internal state of the trie and the parent entry
        // Assuming the child was removed
        assertNull(parentEntry.left);
        // Additional assertions to verify the state can be added here
    }

    @Test
    void testRemoveRootEntryThrowsException() {
        // Given: an entry that is the root (for this example, we assume root is set in the trie)
        // Simulating root entry
        trie.root = new AbstractPatriciaTrie.TrieEntry<>(null, null, -1);
        // Then: verify that the exception is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            trie.removeEntry(trie.root);
        }, "Cannot delete root Entry!");
    }

    @Test
    void testRemoveNonInternalEntryThrowsException() {
        // Given: a TrieEntry that is not internal (e.g., a leaf node)
        AbstractPatriciaTrie.TrieEntry<String, String> leafEntry = new AbstractPatriciaTrie.TrieEntry<>("leafKey", "leafValue", -1);
        // Then: verify that the exception is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            // This should throw an exception
            trie.removeEntry(leafEntry);
        }, leafEntry + " is not an internal Entry!");
    }

    @Test
    void testRemoveValidInternalEntryUpdatesPredecessorAndParent() {
        // Given: a valid internal entry to remove
        // Invoke the method that calls removeInternalEntry
        trie.removeEntry(internalEntry);
        // Then: verify the internal state of the trie and the parent entry
        // Assuming the child was removed
        assertNull(parentEntry.left);
        // Check if predecessor is updated correctly
        assertEquals(parentEntry, internalEntry.predecessor);
    }

    @Test
    void testRemoveEntryWithLoopingPredecessor() {
        // Given: an internal entry with itself as predecessor
        // Set predecessor to itself
        internalEntry.predecessor = internalEntry;
        // Invoke the method to remove
        trie.removeEntry(internalEntry);
        // Then: verify that the predecessor now points to its parent
        assertEquals(parentEntry, internalEntry.predecessor);
    }

    @Test
    void testRemoveEntryWithChildrenUpdatesChildrenParent() {
        // Given: an internal entry with children
        AbstractPatriciaTrie.TrieEntry<String, String> childEntry1 = new AbstractPatriciaTrie.TrieEntry<>("child1Key", "child1Value", 2);
        AbstractPatriciaTrie.TrieEntry<String, String> childEntry2 = new AbstractPatriciaTrie.TrieEntry<>("child2Key", "child2Value", 3);
        internalEntry.left = childEntry1;
        internalEntry.right = childEntry2;
        // Invoke the method to remove
        trie.removeEntry(internalEntry);
        // Then: verify that the children have their parent set to the correct new parent
        assertEquals(parentEntry, childEntry1.parent);
        assertEquals(parentEntry, childEntry2.parent);
    }

    @Test
    void testRemoveEntryThatIsOnlyChildOfParent() {
        // Given: an internal entry that is the only child of its parent
        // Set as the only child
        parentEntry.left = internalEntry;
        // Invoke the method to remove
        trie.removeEntry(internalEntry);
        // Then: verify that the parent's reference to that child is set to null
        assertNull(parentEntry.left);
    }

    @Test
    void testRemoveEntryWithComplexTreeStructure() {
        // Given: a complex tree structure
        AbstractPatriciaTrie.TrieEntry<String, String> childEntry1 = new AbstractPatriciaTrie.TrieEntry<>("child1Key", "value", 2);
        AbstractPatriciaTrie.TrieEntry<String, String> childEntry2 = new AbstractPatriciaTrie.TrieEntry<>("child2Key", "value", 3);
        internalEntry.left = childEntry1;
        internalEntry.right = childEntry2;
        // Invoke the method to remove
        trie.removeEntry(internalEntry);
        // Then: verify that the tree structure remains valid
        assertEquals(parentEntry, childEntry1.parent);
        assertEquals(parentEntry, childEntry2.parent);
    }

    @Test
    void testRemoveEntryWithDeepHierarchy() {
        // Given: a deep hierarchy
        AbstractPatriciaTrie.TrieEntry<String, String> deepChild = new AbstractPatriciaTrie.TrieEntry<>("deepChildKey", "value", 4);
        internalEntry.left = deepChild;
        // Invoke the method to remove
        trie.removeEntry(internalEntry);
        // Then: verify that the hierarchy remains intact
        assertEquals(parentEntry, deepChild.parent);
    }
}
