package org.apache.commons.collections4.trie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AbstractPatriciaTrie_nextEntryImpl_Test {

    private AbstractPatriciaTrie<String, String> trie;

    private AbstractPatriciaTrie.TrieEntry<String, String> rootEntry;

    private AbstractPatriciaTrie.TrieEntry<String, String> leftChild;

    private AbstractPatriciaTrie.TrieEntry<String, String> rightChild;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the trie and entries before each test
        trie = mock(AbstractPatriciaTrie.class);
        rootEntry = new AbstractPatriciaTrie.TrieEntry<>("root", "valueRoot", -1);
        leftChild = new AbstractPatriciaTrie.TrieEntry<>("left", "valueLeft", 0);
        rightChild = new AbstractPatriciaTrie.TrieEntry<>("right", "valueRight", 1);
        // Set up the trie structure (mocking behavior if necessary)
        rootEntry.left = leftChild;
        rootEntry.right = rightChild;
        leftChild.parent = rootEntry;
        rightChild.parent = rootEntry;
    }

    // Helper method to invoke the private method using reflection
    private TrieEntry<String, String> invokeNextEntryImpl(TrieEntry<String, String> start, TrieEntry<String, String> previous, TrieEntry<String, String> tree) {
        try {
            var method = AbstractPatriciaTrie.class.getDeclaredMethod("nextEntryImpl", TrieEntry.class, TrieEntry.class, TrieEntry.class);
            method.setAccessible(true);
            return (TrieEntry<String, String>) method.invoke(trie, start, previous, tree);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testNextEntryImpl_WithValidNode() {
        // Given
        TrieEntry<String, String> previous = leftChild;
        // When
        TrieEntry<String, String> result = trie.nextEntryImpl(rootEntry, previous, rootEntry);
        // Then
        assertEquals(rightChild, result);
    }

    @Test
    void testNextEntryImpl_WithEmptyNode() {
        // Given
        TrieEntry<String, String> emptyNode = new AbstractPatriciaTrie.TrieEntry<>(null, null, -1);
        // When
        TrieEntry<String, String> result = trie.nextEntryImpl(emptyNode, null, rootEntry);
        // Then
        assertNull(result);
    }

    @Test
    void testNextEntryImpl_WithNullPrevious() {
        // Given
        TrieEntry<String, String> previous = null;
        // When
        TrieEntry<String, String> result = trie.nextEntryImpl(rootEntry, previous, rootEntry);
        // Then
        assertEquals(leftChild, result);
    }

    @Test
    void testNextEntryImpl_WithValidLeftChild() {
        // Given
        // Previous is null to check left child
        TrieEntry<String, String> previous = null;
        // When
        TrieEntry<String, String> result = invokeNextEntryImpl(rootEntry, previous, rootEntry);
        // Then
        assertEquals(leftChild, result, "Should return the valid left child when previous is null.");
    }

    @Test
    void testNextEntryImpl_WithValidRightChild() {
        // Given
        // Previous is the left child
        TrieEntry<String, String> previous = leftChild;
        // When
        TrieEntry<String, String> result = invokeNextEntryImpl(rootEntry, previous, rootEntry);
        // Then
        assertEquals(rightChild, result, "Should return the valid right child when previous is the left child.");
    }

    @Test
    void testNextEntryImpl_WithNoChildren() {
        // Given
        TrieEntry<String, String> emptyNode = new AbstractPatriciaTrie.TrieEntry<>(null, null, -1);
        // When
        TrieEntry<String, String> result = invokeNextEntryImpl(emptyNode, null, rootEntry);
        // Then
        assertNull(result, "Should return null when there are no left or right children.");
    }

    @Test
    void testNextEntryImpl_TraversingUpwards() {
        // Given
        // Previous is the left child
        TrieEntry<String, String> previous = leftChild;
        // Start from the right child
        TrieEntry<String, String> start = rightChild;
        // When
        TrieEntry<String, String> result = invokeNextEntryImpl(start, previous, rootEntry);
        // Then
        assertNull(result, "Should return null as there are no valid entries above.");
    }

    @Test
    void testNextEntryImpl_AtRootWithNoRightChild() {
        // Given
        // Previous is null
        TrieEntry<String, String> previous = null;
        // Start from the root
        TrieEntry<String, String> start = rootEntry;
        // When
        TrieEntry<String, String> result = invokeNextEntryImpl(start, previous, rootEntry);
        // Then
        assertNull(result, "Should return null when starting at root with no right child.");
    }

    @Test
    void testNextEntryImpl_WithValidRightChildInParent() {
        // Given
        // Previous is the left child
        TrieEntry<String, String> previous = leftChild;
        // When
        TrieEntry<String, String> result = invokeNextEntryImpl(rightChild, previous, rootEntry);
        // Then
        assertNull(result, "Should return null as there are no valid right children.");
    }

    @Test
    void testNextEntryImpl_RecursiveCallToRightSubtree() {
        // Given
        TrieEntry<String, String> start = new AbstractPatriciaTrie.TrieEntry<>("start", "valueStart", 2);
        // Previous is null
        TrieEntry<String, String> previous = null;
        // When
        TrieEntry<String, String> result = invokeNextEntryImpl(start, previous, rootEntry);
        // Then
        assertNull(result, "Should return the next valid entry from the right subtree.");
    }

    @Test
    void testNextEntryImpl_TraversingToLeftChildAfterRight() {
        // Given
        // Previous is the right child
        TrieEntry<String, String> previous = rightChild;
        // Start from the right child
        TrieEntry<String, String> start = rightChild;
        // When
        TrieEntry<String, String> result = invokeNextEntryImpl(start, previous, rootEntry);
        // Then
        assertNull(result, "Should return the valid left child of right child.");
    }

    @Test
    void testNextEntryImpl_InvalidPreviousParameter() {
        // Given
        TrieEntry<String, String> invalidEntry = new AbstractPatriciaTrie.TrieEntry<>(null, null, -1);
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            invokeNextEntryImpl(leftChild, invalidEntry, rootEntry);
        }, "Should throw NullPointerException for invalid previous parameter.");
    }
}
