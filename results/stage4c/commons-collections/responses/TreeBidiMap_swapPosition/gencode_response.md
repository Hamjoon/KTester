```java
package org.apache.commons.collections4.bidimap;

import static org.apache.commons.collections4.bidimap.TreeBidiMap.DataElement.KEY;
import static org.apache.commons.collections4.bidimap.TreeBidiMap.DataElement.VALUE;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

class TreeBidiMap_swapPosition_Test {

    private TreeBidiMap<Integer, String> treeBidiMap;
    private TreeBidiMap.Node<Integer, String> node1;
    private TreeBidiMap.Node<Integer, String> node2;

    @BeforeAll
    static void setupBeforeAll() {
        // Initialize any static resources if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        treeBidiMap = new TreeBidiMap<>();
        node1 = new TreeBidiMap.Node<>(1, "A");
        node2 = new TreeBidiMap.Node<>(2, "B");

        // Set up initial state if needed
        node1.setLeft(node2, KEY);
        node2.setParent(node1, KEY);
    }

    @Test
    void testSwapPosition_NonParentChildNodes() {
        // Given
        TreeBidiMap.Node<Integer, String> node3 = new TreeBidiMap.Node<>(3, "C");
        TreeBidiMap.Node<Integer, String> node4 = new TreeBidiMap.Node<>(4, "D");

        // Set initial relationships
        node3.setLeft(node4, VALUE);
        node4.setParent(node3, VALUE);

        // When
        treeBidiMap.swapPosition(node3, node4, VALUE);

        // Then
        assertEquals(node3, node4.getLeft(VALUE));
        assertEquals(node4, node3.getParent(VALUE));
    }

    @Test
    void testSwapPosition_SameNode() {
        // Given
        TreeBidiMap.Node<Integer, String> node = new TreeBidiMap.Node<>(1, "A");

        // When
        treeBidiMap.swapPosition(node, node, KEY);

        // Then
        // No change expected
        assertNull(node.getParent(KEY));
        assertNull(node.getLeft(KEY));
        assertNull(node.getRight(KEY));
    }

    @Test
    void testSwapPosition_DifferentValues() {
        // Given
        TreeBidiMap.Node<Integer, String> node3 = new TreeBidiMap.Node<>(3, "C");
        TreeBidiMap.Node<Integer, String> node4 = new TreeBidiMap.Node<>(4, "D");

        // Set initial relationships
        node3.setLeft(node4, VALUE);
        node4.setParent(node3, VALUE);

        // When
        treeBidiMap.swapPosition(node3, node4, VALUE);

        // Then
        assertEquals(node3, node4.getLeft(VALUE));
        assertEquals(node4, node3.getParent(VALUE));
    }

    @Test
    void testSwapPosition_XIsYParent() {
        // Given
        TreeBidiMap.Node<Integer, String> node3 = new TreeBidiMap.Node<>(3, "C");

        node1.setLeft(node3, KEY);
        node3.setParent(node1, KEY);

        // When
        treeBidiMap.swapPosition(node1, node3, KEY);

        // Then
        assertEquals(node1, node3.getLeft(KEY));
        assertEquals(node3, node1.getParent(KEY));
    }

    @Test
    void testSwapPosition_YIsXParent() {
        // Given
        TreeBidiMap.Node<Integer, String> node3 = new TreeBidiMap.Node<>(3, "C");

        node3.setLeft(node1, KEY);
        node1.setParent(node3, KEY);

        // When
        treeBidiMap.swapPosition(node3, node1, KEY);

        // Then
        assertEquals(node3, node1.getLeft(KEY));
        assertEquals(node1, node3.getParent(KEY));
    }

    @Test
    void testSwapPosition_RootNodeChanges() {
        // Given
        TreeBidiMap.Node<Integer, String> rootNode = new TreeBidiMap.Node<>(0, "Root");
        treeBidiMap.rootNode = new TreeBidiMap.Node[]{rootNode, null};

        // When
        treeBidiMap.swapPosition(rootNode, node1, KEY);

        // Then
        assertEquals(node1, treeBidiMap.rootNode[KEY.ordinal()]);
    }

    @Test
    void testSwapPosition_NullX() {
        // Given
        TreeBidiMap.Node<Integer, String> node = new TreeBidiMap.Node<>(2, "B");

        // Expect NullPointerException
        assertThrows(NullPointerException.class, () -> treeBidiMap.swapPosition(null, node, KEY));
    }

    @Test
    void testSwapPosition_NullY() {
        // Given
        TreeBidiMap.Node<Integer, String> node = new TreeBidiMap.Node<>(1, "A");

        // Expect NullPointerException
        assertThrows(NullPointerException.class, () -> treeBidiMap.swapPosition(node, null, KEY));
    }

    @Test
    void testSwapPosition_NullDataElement() {
        // Given
        TreeBidiMap.Node<Integer, String> nodeA = new TreeBidiMap.Node<>(1, "A");
        TreeBidiMap.Node<Integer, String> nodeB = new TreeBidiMap.Node<>(2, "B");

        // Expect NullPointerException
        assertThrows(NullPointerException.class, () -> treeBidiMap.swapPosition(nodeA, nodeB, null));
    }

    @Test
    void testSwapPosition_InvalidDataElement() {
        // Given
        TreeBidiMap.Node<Integer, String> nodeA = new TreeBidiMap.Node<>(1, "A");
        TreeBidiMap.Node<Integer, String> nodeB = new TreeBidiMap.Node<>(2, "B");

        // Mock an invalid DataElement
        DataElement invalidDataElement = Mockito.mock(DataElement.class);
        when(invalidDataElement.ordinal()).thenThrow(IllegalArgumentException.class);

        // Expect IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> treeBidiMap.swapPosition(nodeA, nodeB, invalidDataElement));
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources if needed
    }

    @AfterAll
    static void teardownAfterAll() {
        // Clean up any static resources if needed
    }
}
```