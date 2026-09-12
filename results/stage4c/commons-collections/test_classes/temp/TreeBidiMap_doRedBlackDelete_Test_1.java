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
        nodeToDelete = treeBidiMap.new Node<>(2, "Two");
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
        // Given: a node with both left and right children
        TreeBidiMap.Node<Integer, String> nodeWithBothChildren = spy(treeBidiMap.new Node<>(2, "Two"));
        when(nodeWithBothChildren.getLeft(TreeBidiMap.DataElement.KEY)).thenReturn(treeBidiMap.new Node<>(1, "One"));
        when(nodeWithBothChildren.getRight(TreeBidiMap.DataElement.KEY)).thenReturn(treeBidiMap.new Node<>(3, "Three"));
        // When: the doRedBlackDelete method is invoked using reflection
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete", TreeBidiMap.Node.class);
        doRedBlackDeleteMethod.setAccessible(true);
        doRedBlackDeleteMethod.invoke(treeBidiMap, nodeWithBothChildren);
        // Then: the node should be swapped with the next greater node and tree properties maintained
        verify(nodeWithBothChildren, atLeastOnce()).getLeft(TreeBidiMap.DataElement.KEY);
        verify(nodeWithBothChildren, atLeastOnce()).getRight(TreeBidiMap.DataElement.KEY);
    }

    @Test
    void testDoRedBlackDelete_NodeWithOnlyLeftChild() {
        // Given: a node with only a left child
        TreeBidiMap.Node<Integer, String> nodeWithLeftChild = spy(treeBidiMap.new Node<>(2, "Two"));
        when(nodeWithLeftChild.getLeft(TreeBidiMap.DataElement.KEY)).thenReturn(treeBidiMap.new Node<>(1, "One"));
        // When: the doRedBlackDelete method is invoked using reflection
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete", TreeBidiMap.Node.class);
        doRedBlackDeleteMethod.setAccessible(true);
        doRedBlackDeleteMethod.invoke(treeBidiMap, nodeWithLeftChild);
        // Then: the left child should replace the deleted node
        verify(nodeWithLeftChild, atLeastOnce()).getLeft(TreeBidiMap.DataElement.KEY);
    }

    @Test
    void testDoRedBlackDelete_NodeWithOnlyRightChild() {
        // Given: a node with only a right child
        TreeBidiMap.Node<Integer, String> nodeWithRightChild = spy(treeBidiMap.new Node<>(2, "Two"));
        when(nodeWithRightChild.getRight(TreeBidiMap.DataElement.KEY)).thenReturn(treeBidiMap.new Node<>(3, "Three"));
        // When: the doRedBlackDelete method is invoked using reflection
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete", TreeBidiMap.Node.class);
        doRedBlackDeleteMethod.setAccessible(true);
        doRedBlackDeleteMethod.invoke(treeBidiMap, nodeWithRightChild);
        // Then: the right child should replace the deleted node
        verify(nodeWithRightChild, atLeastOnce()).getRight(TreeBidiMap.DataElement.KEY);
    }

    @Test
    void testDoRedBlackDelete_LeafNode() {
        // Given: a leaf node (no children)
        TreeBidiMap.Node<Integer, String> leafNode = spy(treeBidiMap.new Node<>(2, "Two"));
        // When: the doRedBlackDelete method is invoked using reflection
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete", TreeBidiMap.Node.class);
        doRedBlackDeleteMethod.setAccessible(true);
        doRedBlackDeleteMethod.invoke(treeBidiMap, leafNode);
        // Then: the leaf node should be removed and tree properties maintained
        verify(leafNode, never()).getLeft(TreeBidiMap.DataElement.KEY);
        verify(leafNode, never()).getRight(TreeBidiMap.DataElement.KEY);
    }

    @Test
    void testDoRedBlackDelete_RootNodeWithNoChildren() {
        // Given: a root node with no children
        TreeBidiMap.Node<Integer, String> rootNode = spy(treeBidiMap.new Node<>(2, "Two"));
        when(rootNode.getParent(TreeBidiMap.DataElement.KEY)).thenReturn(null);
        // When: the doRedBlackDelete method is invoked using reflection
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete", TreeBidiMap.Node.class);
        doRedBlackDeleteMethod.setAccessible(true);
        doRedBlackDeleteMethod.invoke(treeBidiMap, rootNode);
        // Then: the tree should become empty
        assertNull(treeBidiMap.get(2), "The tree should be empty after deleting the root node with no children");
    }

    @Test
    void testDoRedBlackDelete_BlackNodeWithNoChildren() {
        // Given: a black node with no children
        TreeBidiMap.Node<Integer, String> blackNode = spy(treeBidiMap.new Node<>(2, "Two"));
        when(blackNode.isBlack(TreeBidiMap.DataElement.KEY)).thenReturn(true);
        // When: the doRedBlackDelete method is invoked using reflection
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete", TreeBidiMap.Node.class);
        doRedBlackDeleteMethod.setAccessible(true);
        doRedBlackDeleteMethod.invoke(treeBidiMap, blackNode);
        // Then: red-black fixup should be performed to maintain tree properties
        verify(blackNode, atLeastOnce()).isBlack(TreeBidiMap.DataElement.KEY);
    }

    @Test
    void testDoRedBlackDelete_RedNodeWithNoChildren() {
        // Given: a red node with no children
        TreeBidiMap.Node<Integer, String> redNode = spy(treeBidiMap.new Node<>(2, "Two"));
        when(redNode.isRed(TreeBidiMap.DataElement.KEY)).thenReturn(true);
        // When: the doRedBlackDelete method is invoked using reflection
        Method doRedBlackDeleteMethod = TreeBidiMap.class.getDeclaredMethod("doRedBlackDelete", TreeBidiMap.Node.class);
        doRedBlackDeleteMethod.setAccessible(true);
        doRedBlackDeleteMethod.invoke(treeBidiMap, redNode);
        // Then: the node should be simply removed without needing fixup
        verify(redNode, atLeastOnce()).isRed(TreeBidiMap.DataElement.KEY);
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
