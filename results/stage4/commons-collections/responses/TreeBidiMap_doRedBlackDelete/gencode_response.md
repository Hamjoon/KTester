```java
package org.apache.commons.collections4.bidimap;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class TreeBidiMap_doRedBlackDelete_Test {
    private TreeBidiMap<Integer, String> treeBidiMap;
    private TreeBidiMap.Node<Integer, String> nodeToDelete;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here, if necessary
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the TreeBidiMap before each test
        treeBidiMap = new TreeBidiMap<>();
        // Create a node to delete (this should be a valid node)
        nodeToDelete = new TreeBidiMap.Node<>(1, "One");
        // Add the node to the map to prepare for deletion
        treeBidiMap.put(nodeToDelete.getKey(), nodeToDelete.getValue());
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up after each test if necessary
        treeBidiMap.clear();
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global teardown can be done here, if necessary
    }

    @Test
    void testDoRedBlackDeleteWithBothChildren() throws Exception {
        // Setup a node with both left and right children
        TreeBidiMap.Node<Integer, String> leftChild = new TreeBidiMap.Node<>(0, "Zero");
        TreeBidiMap.Node<Integer, String> rightChild = new TreeBidiMap.Node<>(2, "Two");
        nodeToDelete.setLeft(leftChild, TreeBidiMap.DataElement.KEY);
        nodeToDelete.setRight(rightChild, TreeBidiMap.DataElement.KEY);
        leftChild.setParent(nodeToDelete, TreeBidiMap.DataElement.KEY);
        rightChild.setParent(nodeToDelete, TreeBidiMap.DataElement.KEY);
        
        // Perform the deletion
        invokePrivateMethod("doRedBlackDelete", nodeToDelete);

        // Assertions to verify the state of the tree after deletion
        assertNull(treeBidiMap.get(nodeToDelete.getKey()), "Node should be deleted");
        // Verify the next greater node has been swapped in place
        // Assuming the next greater node is rightChild
        assertNotNull(treeBidiMap.get(rightChild.getKey()), "Replacement node should still exist");
    }

    @Test
    void testDoRedBlackDeleteWithOnlyLeftChild() throws Exception {
        // Setup a node with only left child
        TreeBidiMap.Node<Integer, String> leftChild = new TreeBidiMap.Node<>(0, "Zero");
        nodeToDelete.setLeft(leftChild, TreeBidiMap.DataElement.KEY);
        leftChild.setParent(nodeToDelete, TreeBidiMap.DataElement.KEY);

        // Perform the deletion
        invokePrivateMethod("doRedBlackDelete", nodeToDelete);

        // Assertions to verify the state of the tree after deletion
        assertNull(treeBidiMap.get(nodeToDelete.getKey()), "Node should be deleted");
        assertNotNull(treeBidiMap.get(leftChild.getKey()), "Left child should still exist");
    }

    @Test
    void testDoRedBlackDeleteWithOnlyRightChild() throws Exception {
        // Setup a node with only right child
        TreeBidiMap.Node<Integer, String> rightChild = new TreeBidiMap.Node<>(2, "Two");
        nodeToDelete.setRight(rightChild, TreeBidiMap.DataElement.KEY);
        rightChild.setParent(nodeToDelete, TreeBidiMap.DataElement.KEY);

        // Perform the deletion
        invokePrivateMethod("doRedBlackDelete", nodeToDelete);

        // Assertions to verify the state of the tree after deletion
        assertNull(treeBidiMap.get(nodeToDelete.getKey()), "Node should be deleted");
        assertNotNull(treeBidiMap.get(rightChild.getKey()), "Right child should still exist");
    }

    @Test
    void testDoRedBlackDeleteWithNoChildren() throws Exception {
        // Test the deletion of a node with no children
        invokePrivateMethod("doRedBlackDelete", nodeToDelete);

        // Assertions to verify the state of the tree after deletion
        assertNull(treeBidiMap.get(nodeToDelete.getKey()), "Node should be deleted");
        assertTrue(treeBidiMap.isEmpty(), "Tree should be empty after deletion");
    }

    @Test
    void testDoRedBlackDeleteWithRootNode() throws Exception {
        // Setup a root node with children
        TreeBidiMap.Node<Integer, String> leftChild = new TreeBidiMap.Node<>(0, "Zero");
        TreeBidiMap.Node<Integer, String> rightChild = new TreeBidiMap.Node<>(2, "Two");
        nodeToDelete.setLeft(leftChild, TreeBidiMap.DataElement.KEY);
        nodeToDelete.setRight(rightChild, TreeBidiMap.DataElement.KEY);
        leftChild.setParent(nodeToDelete, TreeBidiMap.DataElement.KEY);
        rightChild.setParent(nodeToDelete, TreeBidiMap.DataElement.KEY);

        // Perform the deletion
        invokePrivateMethod("doRedBlackDelete", nodeToDelete);

        // Assertions to verify the root has been replaced
        assertNull(treeBidiMap.get(nodeToDelete.getKey()), "Root node should be deleted");
        assertNotNull(treeBidiMap.get(rightChild.getKey()), "Replacement node should still exist");
    }

    @Test
    void testDoRedBlackDeleteWithNullNode() throws Exception {
        // Perform deletion on a null node (not in the map)
        TreeBidiMap.Node<Integer, String> nullNode = null;

        // Perform the deletion
        invokePrivateMethod("doRedBlackDelete", nullNode);

        // Assertions to verify no changes made to the tree
        assertTrue(treeBidiMap.isEmpty(), "Tree should remain empty");
    }

    // Helper method to invoke the private method doRedBlackDelete using reflection
    private void invokePrivateMethod(String methodName, TreeBidiMap.Node<Integer, String> node) throws Exception {
        Method method = TreeBidiMap.class.getDeclaredMethod(methodName, TreeBidiMap.Node.class);
        method.setAccessible(true);
        method.invoke(treeBidiMap, node);
    }
}
```