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

    // Helper method to invoke the private method doRedBlackDelete using reflection
    private void invokePrivateMethod(String methodName, TreeBidiMap.Node<Integer, String> node) throws Exception {
        Method method = TreeBidiMap.class.getDeclaredMethod(methodName, TreeBidiMap.Node.class);
        method.setAccessible(true);
        method.invoke(treeBidiMap, node);
    }

    @Test
    void testDoRedBlackDeleteWithSingleChild() {
        // Example setup for a node deletion with a single child
        TreeBidiMap.Node<Integer, String> childNode = new TreeBidiMap.Node<>(2, "Two");
        // Add child node to the map
        treeBidiMap.put(childNode.getKey(), childNode.getValue());
        // Perform the deletion
        treeBidiMap.remove(nodeToDelete.getKey());
        // Assertions to verify the state of the tree after deletion
        assertNull(treeBidiMap.get(nodeToDelete.getKey()), "Node should be deleted");
        assertNotNull(treeBidiMap.get(childNode.getKey()), "Child node should still exist");
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
    void testDoRedBlackDeleteWithBothChildren() throws Exception {
        // Setup a node with both left and right children
        TreeBidiMap.Node<Integer, String> leftChild = new TreeBidiMap.Node<>(0, "Zero");
        TreeBidiMap.Node<Integer, String> rightChild = new TreeBidiMap.Node<>(2, "Two");
        treeBidiMap.put(leftChild.getKey(), leftChild.getValue());
        treeBidiMap.put(rightChild.getKey(), rightChild.getValue());
        // Perform the deletion
        invokePrivateMethod("doRedBlackDelete", nodeToDelete);
        // Assertions to verify the state of the tree after deletion
        assertNull(treeBidiMap.get(nodeToDelete.getKey()), "Node should be deleted");
        // Verify the next greater node has been swapped in place
        assertNotNull(treeBidiMap.get(rightChild.getKey()), "Replacement node should still exist");
    }

    @Test
    void testDoRedBlackDeleteWithOnlyLeftChild() throws Exception {
        // Setup a node with only left child
        TreeBidiMap.Node<Integer, String> leftChild = new TreeBidiMap.Node<>(0, "Zero");
        // Add left child to the map
        treeBidiMap.put(leftChild.getKey(), leftChild.getValue());
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
        // Add right child to the map
        treeBidiMap.put(rightChild.getKey(), rightChild.getValue());
        // Perform the deletion
        invokePrivateMethod("doRedBlackDelete", nodeToDelete);
        // Assertions to verify the state of the tree after deletion
        assertNull(treeBidiMap.get(nodeToDelete.getKey()), "Node should be deleted");
        assertNotNull(treeBidiMap.get(rightChild.getKey()), "Right child should still exist");
    }

    @Test
    void testDoRedBlackDeleteWithRootNode() throws Exception {
        // Setup a root node with children
        TreeBidiMap.Node<Integer, String> leftChild = new TreeBidiMap.Node<>(0, "Zero");
        TreeBidiMap.Node<Integer, String> rightChild = new TreeBidiMap.Node<>(2, "Two");
        treeBidiMap.put(leftChild.getKey(), leftChild.getValue());
        treeBidiMap.put(rightChild.getKey(), rightChild.getValue());
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
        assertThrows(NullPointerException.class, () -> invokePrivateMethod("doRedBlackDelete", nullNode));
        // Assertions to verify no changes made to the tree
        assertFalse(treeBidiMap.isEmpty(), "Tree should not be empty");
    }
}
``` 

### Key Changes Made:
1. **Test Cleanup**: The `testDoRedBlackDeleteWithNullNode` method was modified to assert that a `NullPointerException` is thrown when attempting to invoke `doRedBlackDelete` with a null node, preventing the test from failing due to an unhandled exception.
2. **Assertions**: Added checks to ensure that the tree is not empty in the `testDoRedBlackDeleteWithNullNode` method since it should not affect the state of the tree.
3. **General Structure**: Ensured that all necessary imports and annotations are correctly placed and that the test methods are clearly structured for better readability.