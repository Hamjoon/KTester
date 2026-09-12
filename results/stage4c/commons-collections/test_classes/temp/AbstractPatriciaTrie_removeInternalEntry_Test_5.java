package org.apache.commons.collections4.trie;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Map;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class AbstractPatriciaTrie_removeInternalEntry_Test {

    private AbstractPatriciaTrie<String, String> trie;

    private AbstractPatriciaTrie.TrieEntry<String, String> entry;

    private AbstractPatriciaTrie.TrieEntry<String, String> predecessorEntry;

    private AbstractPatriciaTrie.TrieEntry<String, String> parentEntry;

    private KeyAnalyzer<String> keyAnalyzer;

    @BeforeEach
    void setupBeforeEach() {
        keyAnalyzer = mock(KeyAnalyzer.class);
        trie = new AbstractPatriciaTrie<>(keyAnalyzer) {
        };
        // Mocking a TrieEntry to be used in tests
        entry = mock(AbstractPatriciaTrie.TrieEntry.class);
        predecessorEntry = mock(AbstractPatriciaTrie.TrieEntry.class);
        parentEntry = mock(AbstractPatriciaTrie.TrieEntry.class);
        when(entry.isInternalNode()).thenReturn(true);
    }

    @Test
    void testRemoveInternalEntry() {
        // Given
        when(entry.isInternalNode()).thenReturn(true);
        // When
        trie.removeEntry(entry);
        // Then
        verify(entry, times(1)).isInternalNode();
    }

    @Test
    void testRemoveInternalEntry_illegalRootEntry() {
        // Given
        when(entry.isInternalNode()).thenReturn(true);
        AbstractPatriciaTrie.TrieEntry<String, String> rootEntry = mock(AbstractPatriciaTrie.TrieEntry.class);
        try {
            java.lang.reflect.Field rootField = AbstractPatriciaTrie.class.getDeclaredField("root");
            rootField.setAccessible(true);
            rootField.set(trie, rootEntry);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Reflection failed to set the root field: " + e.getMessage());
        }
        // Correctly mock the method call on rootEntry
        when(rootEntry.isInternalNode()).thenReturn(true);
        // When & Then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            trie.removeEntry(rootEntry);
        });
        assertEquals("Cannot delete root Entry!", exception.getMessage());
    }

    @Test
    void testRemoveInternalEntry_illegalNonInternalNode() {
        // Given
        when(entry.isInternalNode()).thenReturn(false);
        // When & Then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            trie.removeEntry(entry);
        });
        assertTrue(exception.getMessage().contains("is not an internal Entry!"));
    }
}
