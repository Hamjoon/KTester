```java
package org.apache.commons.collections4.bidimap;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TreeBidiMap_swapPosition_Test {
    private TreeBidiMap<Integer, String> treeBidiMap;
    private TreeBidiMap.Node<Integer, String> nodeX;
    private TreeBidiMap.Node<Integer, String> nodeY;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the TreeBidiMap and nodes before each test
        treeBidiMap = new TreeBidiMap<>();
        nodeX = new TreeBidiMap.Node<>(1, "One");
        nodeY = new TreeBidiMap.Node<>(2, "Two");
        
        // Setup initial state of the map or nodes if necessary
        // This may include adding the nodes to the treeBidiMap
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here if needed
    }

    @Test
    void testSwapPosition_ParentOfChild() {
        // Given: nodeX is the parent of nodeY
        nodeX.setLeft(nodeY, TreeBidiMap.DataElement.KEY);
        nodeY.setParent(nodeX, TreeBidiMap.DataElement.KEY);

        // When: Call the method under test
        treeBidiMap.swapPosition(nodeX, nodeY, TreeBidiMap.DataElement.KEY);

        // Then: Verify parent-child relationships are swapped correctly
        assertEquals(nodeY, nodeX.getParent(TreeBidiMap.DataElement.KEY));
        assertEquals(nodeX, nodeY.getParent(TreeBidiMap.DataElement.KEY));
    }

    @Test
    void testSwapPosition_ChildOfParent() {
        // Given: nodeY is the parent of nodeX
        nodeY.setLeft(nodeX, TreeBidiMap.DataElement.VALUE);
        nodeX.setParent(nodeY, TreeBidiMap.DataElement.VALUE);

        // When: Call the method under test
        treeBidiMap.swapPosition(nodeY, nodeX, TreeBidiMap.DataElement.VALUE);

        // Then: Verify parent-child relationships are swapped correctly
        assertEquals(nodeX, nodeY.getParent(TreeBidiMap.DataElement.VALUE));
        assertEquals(nodeY, nodeX.getParent(TreeBidiMap.DataElement.VALUE));
    }

    @Test
    void testSwapPosition_Siblings() {
        // Given: nodeX and nodeY are siblings
        nodeX.setParent(new TreeBidiMap.Node<>(0, "Root"), TreeBidiMap.DataElement.KEY);
        nodeY.setParent(nodeX.getParent(TreeBidiMap.DataElement.KEY), TreeBidiMap.DataElement.KEY);
        
        // When: Call the method under test
        treeBidiMap.swapPosition(nodeX, nodeY, TreeBidiMap.DataElement.KEY);

        // Then: Verify parent-child relationships are swapped without changing root
        assertEquals(nodeY.getParent(TreeBidiMap.DataElement.KEY), nodeX.getParent(TreeBidiMap.DataElement.KEY));
        assertEquals(nodeX.getParent(TreeBidiMap.DataElement.KEY), nodeY.getParent(TreeBidiMap.DataElement.KEY));
    }

    @Test
    void testSwapPosition_UnrelatedNodes() {
        // Given: nodeX and nodeY are not related
        nodeX.setParent(new TreeBidiMap.Node<>(0, "Root1"), TreeBidiMap.DataElement.KEY);
        nodeY.setParent(new TreeBidiMap.Node<>(0, "Root2"), TreeBidiMap.DataElement.KEY);
        
        // When: Call the method under test
        treeBidiMap.swapPosition(nodeX, nodeY, TreeBidiMap.DataElement.VALUE);

        // Then: Verify the swap does not affect unrelated nodes
        assertNotEquals(nodeY.getParent(TreeBidiMap.DataElement.VALUE), nodeX);
        assertNotEquals(nodeX.getParent(TreeBidiMap.DataElement.VALUE), nodeY);
    }

    @Test
    void testSwapPosition_RootNode() {
        // Given: nodeX is the root node
        nodeX.setParent(null, TreeBidiMap.DataElement.KEY);
        treeBidiMap.rootNode = new TreeBidiMap.Node[]{nodeX, null}; // Assuming KEY is at index 0

        // When: Call the method under test
        treeBidiMap.swapPosition(nodeX, nodeY, TreeBidiMap.DataElement.KEY);

        // Then: Verify the root node is updated correctly
        assertEquals(nodeY, treeBidiMap.rootNode[TreeBidiMap.DataElement.KEY.ordinal()]);
    }

    @Test
    void testSwapPosition_NullNodeX() {
        // Given: x is null
        TreeBidiMap.Node<Integer, String> nullNode = null;

        // When: Call the method under test
        Exception exception = assertThrows(NullPointerException.class, () -> {
            treeBidiMap.swapPosition(nullNode, nodeY, TreeBidiMap.DataElement.KEY);
        });

        // Then: Verify NullPointerException is thrown
        assertEquals("Node x cannot be null", exception.getMessage());
    }

    @Test
    void testSwapPosition_NullNodeY() {
        // Given: y is null
        TreeBidiMap.Node<Integer, String> nullNode = null;

        // When: Call the method under test
        Exception exception = assertThrows(NullPointerException.class, () -> {
            treeBidiMap.swapPosition(nodeX, nullNode, TreeBidiMap.DataElement.VALUE);
        });

        // Then: Verify NullPointerException is thrown
        assertEquals("Node y cannot be null", exception.getMessage());
    }

    @Test
    void testSwapPosition_InvalidDataElement() {
        // Given: Invalid DataElement
        TreeBidiMap.DataElement invalidDataElement = null; // Assuming null is invalid

        // When: Call the method under test
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            treeBidiMap.swapPosition(nodeX, nodeY, invalidDataElement);
        });

        // Then: Verify IllegalArgumentException is thrown
        assertEquals("Invalid DataElement provided", exception.getMessage());
    }
}
```