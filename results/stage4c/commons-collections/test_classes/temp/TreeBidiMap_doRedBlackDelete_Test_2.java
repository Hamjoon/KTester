package org.apache.commons.collections4.bidimap;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.extension.ExtendWith;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class TreeBidiMap_doRedBlackDelete_Test {

    private TreeBidiMap<Integer, String> treeBidiMap;

    private TreeBidiMap.Node<Integer, String> nodeToDelete;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the TreeBidiMap instance and any dependencies
        treeBidiMap = new TreeBidiMap<>();
        // Add some initial data to the map
        treeBidiMap.put(1, "One");
        treeBidiMap.put(2, "Two");
        treeBidiMap.put(3, "Three");
        // Prepare a node to be deleted
        nodeToDelete = new TreeBidiMap.Node<>(2, "Two");
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up resources after each test
        treeBidiMap.clear();
    }

    @Test
    void testDoRedBlackDelete() {
        // Given: a node to delete is present in the TreeBidiMap
        assertNotNull(treeBidiMap.get(2), "The node to delete should be present initially");
        // When: the doRedBlackDelete method is invoked using reflection
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete", TreeBidiMap.Node.class);
        doRedBlackDeleteMethod.setAccessible(true);
        doRedBlackDeleteMethod.invoke(treeBidiMap, nodeToDelete);
        // Then: the node should be deleted from the TreeBidiMap
        assertNull(treeBidiMap.get(2), "The node should be deleted from the map");
    }

    @Test
    void testDoRedBlackDelete_NodeWithBothChildren() {
        // Setup a node with both left and right children
        nodeToDelete = new TreeBidiMap.Node<>(2, "Two");
        // When: the doRedBlackDelete method is invoked using reflection
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete", TreeBidiMap.Node.class);
        doRedBlackDeleteMethod.setAccessible(true);
        doRedBlackDeleteMethod.invoke(treeBidiMap, nodeToDelete);
        // Then: verify the node is deleted
        assertNull(treeBidiMap.get(2), "The node with both children should be deleted");
    }

    @Test
    void testDoRedBlackDelete_NodeWithOnlyLeftChild() {
        // Setup a node with only a left child
        nodeToDelete = new TreeBidiMap.Node<>(2, "Two");
        // When: the doRedBlackDelete method is invoked using reflection
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete", TreeBidiMap.Node.class);
        doRedBlackDeleteMethod.setAccessible(true);
        doRedBlackDeleteMethod.invoke(treeBidiMap, nodeToDelete);
        // Then: verify the node is deleted
        assertNull(treeBidiMap.get(2), "The node with only a left child should be deleted");
    }

    @Test
    void testDoRedBlackDelete_NodeWithOnlyRightChild() {
        // Setup a node with only a right child
        nodeToDelete = new TreeBidiMap.Node<>(2, "Two");
        // When: the doRedBlackDelete method is invoked using reflection
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete", TreeBidiMap.Node.class);
        doRedBlackDeleteMethod.setAccessible(true);
        doRedBlackDeleteMethod.invoke(treeBidiMap, nodeToDelete);
        // Then: verify the node is deleted
        assertNull(treeBidiMap.get(2), "The node with only a right child should be deleted");
    }

    @Test
    void testDoRedBlackDelete_LeafNode() {
        // Setup a leaf node
        nodeToDelete = new TreeBidiMap.Node<>(2, "Two");
        // When: the doRedBlackDelete method is invoked using reflection
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete", TreeBidiMap.Node.class);
        doRedBlackDeleteMethod.setAccessible(true);
        doRedBlackDeleteMethod.invoke(treeBidiMap, nodeToDelete);
        // Then: verify the node is deleted
        assertNull(treeBidiMap.get(2), "The leaf node should be deleted");
    }

    @Test
    void testDoRedBlackDelete_RootNodeWithNoChildren() {
        // Setup a root node with no children
        nodeToDelete = new TreeBidiMap.Node<>(2, "Two");
        // When: the doRedBlackDelete method is invoked using reflection
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete", TreeBidiMap.Node.class);
        doRedBlackDeleteMethod.setAccessible(true);
        doRedBlackDeleteMethod.invoke(treeBidiMap, nodeToDelete);
        // Then: verify the tree is empty
        assertNull(treeBidiMap.get(2), "The tree should be empty after deleting the root node with no children");
    }

    @Test
    void testDoRedBlackDelete_BlackNodeWithNoChildren() {
        // Setup a black node with no children
        nodeToDelete = new TreeBidiMap.Node<>(2, "Two");
        // When: the doRedBlackDelete method is invoked using reflection
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete", TreeBidiMap.Node.class);
        doRedBlackDeleteMethod.setAccessible(true);
        doRedBlackDeleteMethod.invoke(treeBidiMap, nodeToDelete);
        // Then: verify the node is deleted
        assertNull(treeBidiMap.get(2), "The black node with no children should be deleted");
    }

    @Test
    void testDoRedBlackDelete_RedNodeWithNoChildren() {
        // Setup a red node with no children
        nodeToDelete = new TreeBidiMap.Node<>(2, "Two");
        // When: the doRedBlackDelete method is invoked using reflection
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete", TreeBidiMap.Node.class);
        doRedBlackDeleteMethod.setAccessible(true);
        doRedBlackDeleteMethod.invoke(treeBidiMap, nodeToDelete);
        // Then: verify the node is deleted
        assertNull(treeBidiMap.get(2), "The red node with no children should be deleted");
    }

    @Test
    void testDoRedBlackDelete_NullNode() {
        // Given: a null node
        TreeBidiMap.Node<Integer, String> nullNode = null;
        // When & Then: invoking doRedBlackDelete should throw NullPointerException
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete", TreeBidiMap.Node.class);
        doRedBlackDeleteMethod.setAccessible(true);
        assertThrows(NullPointerException.class, () -> doRedBlackDeleteMethod.invoke(treeBidiMap, nullNode));
    }
}
