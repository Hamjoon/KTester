
You are an experienced Java developer tasked with fixing a piece of code that contains compilation errors. Your task is to resolve these issues after analyzing the code and its compilation feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any syntactical or logical errors.
2. Analyze the compilation feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
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
```

Corresponding compilation feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:29: error: cannot select a static class from a parameterized type
        nodeToDelete = treeBidiMap.new Node<>(2, "Two");
                                           ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:29: error: qualified new of static class
        nodeToDelete = treeBidiMap.new Node<>(2, "Two");
                       ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:53: error: cannot select a static class from a parameterized type
        TreeBidiMap.Node<Integer, String> nodeWithBothChildren = spy(treeBidiMap.new Node<>(2, "Two"));
                                                                                         ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:53: error: qualified new of static class
        TreeBidiMap.Node<Integer, String> nodeWithBothChildren = spy(treeBidiMap.new Node<>(2, "Two"));
                                                                     ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:54: error: cannot select a static class from a parameterized type
        when(nodeWithBothChildren.getLeft(TreeBidiMap.DataElement.KEY)).thenReturn(treeBidiMap.new Node<>(1, "One"));
                                                                                                       ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:54: error: getLeft(DataElement) has private access in Node
        when(nodeWithBothChildren.getLeft(TreeBidiMap.DataElement.KEY)).thenReturn(treeBidiMap.new Node<>(1, "One"));
                                 ^
  where K,V are type-variables:
    K extends Comparable<K> declared in class Node
    V extends Comparable<V> declared in class Node
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:54: error: qualified new of static class
        when(nodeWithBothChildren.getLeft(TreeBidiMap.DataElement.KEY)).thenReturn(treeBidiMap.new Node<>(1, "One"));
                                                                                   ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:55: error: cannot select a static class from a parameterized type
        when(nodeWithBothChildren.getRight(TreeBidiMap.DataElement.KEY)).thenReturn(treeBidiMap.new Node<>(3, "Three"));
                                                                                                        ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:55: error: getRight(DataElement) has private access in Node
        when(nodeWithBothChildren.getRight(TreeBidiMap.DataElement.KEY)).thenReturn(treeBidiMap.new Node<>(3, "Three"));
                                 ^
  where K,V are type-variables:
    K extends Comparable<K> declared in class Node
    V extends Comparable<V> declared in class Node
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:55: error: qualified new of static class
        when(nodeWithBothChildren.getRight(TreeBidiMap.DataElement.KEY)).thenReturn(treeBidiMap.new Node<>(3, "Three"));
                                                                                    ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:61: error: getLeft(DataElement) has private access in Node
        verify(nodeWithBothChildren, atLeastOnce()).getLeft(TreeBidiMap.DataElement.KEY);
                                                   ^
  where K,V are type-variables:
    K extends Comparable<K> declared in class Node
    V extends Comparable<V> declared in class Node
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:62: error: getRight(DataElement) has private access in Node
        verify(nodeWithBothChildren, atLeastOnce()).getRight(TreeBidiMap.DataElement.KEY);
                                                   ^
  where K,V are type-variables:
    K extends Comparable<K> declared in class Node
    V extends Comparable<V> declared in class Node
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:68: error: cannot select a static class from a parameterized type
        TreeBidiMap.Node<Integer, String> nodeWithLeftChild = spy(treeBidiMap.new Node<>(2, "Two"));
                                                                                      ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:68: error: qualified new of static class
        TreeBidiMap.Node<Integer, String> nodeWithLeftChild = spy(treeBidiMap.new Node<>(2, "Two"));
                                                                  ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:69: error: cannot select a static class from a parameterized type
        when(nodeWithLeftChild.getLeft(TreeBidiMap.DataElement.KEY)).thenReturn(treeBidiMap.new Node<>(1, "One"));
                                                                                                    ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:69: error: getLeft(DataElement) has private access in Node
        when(nodeWithLeftChild.getLeft(TreeBidiMap.DataElement.KEY)).thenReturn(treeBidiMap.new Node<>(1, "One"));
                              ^
  where K,V are type-variables:
    K extends Comparable<K> declared in class Node
    V extends Comparable<V> declared in class Node
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:69: error: qualified new of static class
        when(nodeWithLeftChild.getLeft(TreeBidiMap.DataElement.KEY)).thenReturn(treeBidiMap.new Node<>(1, "One"));
                                                                                ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:75: error: getLeft(DataElement) has private access in Node
        verify(nodeWithLeftChild, atLeastOnce()).getLeft(TreeBidiMap.DataElement.KEY);
                                                ^
  where K,V are type-variables:
    K extends Comparable<K> declared in class Node
    V extends Comparable<V> declared in class Node
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:81: error: cannot select a static class from a parameterized type
        TreeBidiMap.Node<Integer, String> nodeWithRightChild = spy(treeBidiMap.new Node<>(2, "Two"));
                                                                                       ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:81: error: qualified new of static class
        TreeBidiMap.Node<Integer, String> nodeWithRightChild = spy(treeBidiMap.new Node<>(2, "Two"));
                                                                   ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:82: error: cannot select a static class from a parameterized type
        when(nodeWithRightChild.getRight(TreeBidiMap.DataElement.KEY)).thenReturn(treeBidiMap.new Node<>(3, "Three"));
                                                                                                      ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:82: error: getRight(DataElement) has private access in Node
        when(nodeWithRightChild.getRight(TreeBidiMap.DataElement.KEY)).thenReturn(treeBidiMap.new Node<>(3, "Three"));
                               ^
  where K,V are type-variables:
    K extends Comparable<K> declared in class Node
    V extends Comparable<V> declared in class Node
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:82: error: qualified new of static class
        when(nodeWithRightChild.getRight(TreeBidiMap.DataElement.KEY)).thenReturn(treeBidiMap.new Node<>(3, "Three"));
                                                                                  ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:88: error: getRight(DataElement) has private access in Node
        verify(nodeWithRightChild, atLeastOnce()).getRight(TreeBidiMap.DataElement.KEY);
                                                 ^
  where K,V are type-variables:
    K extends Comparable<K> declared in class Node
    V extends Comparable<V> declared in class Node
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:94: error: cannot select a static class from a parameterized type
        TreeBidiMap.Node<Integer, String> leafNode = spy(treeBidiMap.new Node<>(2, "Two"));
                                                                             ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:94: error: qualified new of static class
        TreeBidiMap.Node<Integer, String> leafNode = spy(treeBidiMap.new Node<>(2, "Two"));
                                                         ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:100: error: getLeft(DataElement) has private access in Node
        verify(leafNode, never()).getLeft(TreeBidiMap.DataElement.KEY);
                                 ^
  where K,V are type-variables:
    K extends Comparable<K> declared in class Node
    V extends Comparable<V> declared in class Node
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:101: error: getRight(DataElement) has private access in Node
        verify(leafNode, never()).getRight(TreeBidiMap.DataElement.KEY);
                                 ^
  where K,V are type-variables:
    K extends Comparable<K> declared in class Node
    V extends Comparable<V> declared in class Node
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:107: error: cannot select a static class from a parameterized type
        TreeBidiMap.Node<Integer, String> rootNode = spy(treeBidiMap.new Node<>(2, "Two"));
                                                                             ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:107: error: qualified new of static class
        TreeBidiMap.Node<Integer, String> rootNode = spy(treeBidiMap.new Node<>(2, "Two"));
                                                         ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:108: error: getParent(DataElement) has private access in Node
        when(rootNode.getParent(TreeBidiMap.DataElement.KEY)).thenReturn(null);
                     ^
  where K,V are type-variables:
    K extends Comparable<K> declared in class Node
    V extends Comparable<V> declared in class Node
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:120: error: cannot select a static class from a parameterized type
        TreeBidiMap.Node<Integer, String> blackNode = spy(treeBidiMap.new Node<>(2, "Two"));
                                                                              ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:120: error: qualified new of static class
        TreeBidiMap.Node<Integer, String> blackNode = spy(treeBidiMap.new Node<>(2, "Two"));
                                                          ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:121: error: isBlack(DataElement) has private access in Node
        when(blackNode.isBlack(TreeBidiMap.DataElement.KEY)).thenReturn(true);
                      ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:127: error: isBlack(DataElement) has private access in Node
        verify(blackNode, atLeastOnce()).isBlack(TreeBidiMap.DataElement.KEY);
                                        ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:133: error: cannot select a static class from a parameterized type
        TreeBidiMap.Node<Integer, String> redNode = spy(treeBidiMap.new Node<>(2, "Two"));
                                                                            ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:133: error: qualified new of static class
        TreeBidiMap.Node<Integer, String> redNode = spy(treeBidiMap.new Node<>(2, "Two"));
                                                        ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:134: error: isRed(DataElement) has private access in Node
        when(redNode.isRed(TreeBidiMap.DataElement.KEY)).thenReturn(true);
                    ^
src/test/java/org/apache/commons/collections4/bidimap/TreeBidiMap_doRedBlackDelete_Test.java:140: error: isRed(DataElement) has private access in Node
        verify(redNode, atLeastOnce()).isRed(TreeBidiMap.DataElement.KEY);
                                      ^
39 errors

```

And you can use the following related information to assist you in your analysis and correction:
```
{'api document of class org.apache.commons.collections4.bidimap.TreeBidiMap': "\n * Red-Black tree-based implementation of BidiMap where all objects added\n * implement the {@code Comparable} interface.\n * <p>\n * This class guarantees that the map will be in both ascending key order\n * and ascending value order, sorted according to the natural order for\n * the key's and value's classes.\n * </p>\n * <p>\n * This Map is intended for applications that need to be able to look\n * up a key-value pairing by either key or value, and need to do so\n * with equal efficiency.\n * </p>\n * <p>\n * While that goal could be accomplished by taking a pair of TreeMaps\n * and redirecting requests to the appropriate TreeMap (e.g.,\n * containsKey would be directed to the TreeMap that maps values to\n * keys, containsValue would be directed to the TreeMap that maps keys\n * to values), there are problems with that implementation.\n * If the data contained in the TreeMaps is large, the cost of redundant\n * storage becomes significant. The {@link DualTreeBidiMap} and\n * {@link DualHashBidiMap} implementations use this approach.\n * </p>\n * <p>\n * This solution keeps minimizes the data storage by holding data only once.\n * The red-black algorithm is based on {@link java.util.TreeMap}, but has been modified\n * to simultaneously map a tree node by key and by value. This doubles the\n * cost of put operations (but so does using two TreeMaps), and nearly doubles\n * the cost of remove operations (there is a savings in that the lookup of the\n * node to be removed only has to be performed once). And since only one node\n * contains the key and value, storage is significantly less than that\n * required by two TreeMaps.\n * </p>\n * <p>\n * The Map.Entry instances returned by the appropriate methods will\n * not allow setValue() and will throw an\n * UnsupportedOperationException on attempts to call that method.\n * </p>\n *\n * @param <K> the type of the keys in this map\n * @param <V> the type of the values in this map\n *\n * @since 3.0 (previously DoubleOrderedMap v2.0)\n ", 'api document of method doRedBlackDelete(Node<K, V>)': "\n     * Complicated red-black delete stuff. Based on Sun's TreeMap\n     * implementation, though it's barely recognizable anymore.\n     *\n     * @param deletedNode the node to be deleted\n     ", 'dependent classes': "class `org.apache.commons.collections4.bidimap.TreeBidiMap`:\n\tdependent fields:\n\t\torg.apache.commons.collections4.bidimap.TreeBidiMap.Node<K, V>[] org.apache.commons.collections4.bidimap.TreeBidiMap.rootNode;\n\trelated functions:\n\t\tmethod `private void doPut(K, V)` returns `void`, related with `org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(Node<K, V>)`, api document: \n     * Puts logic.\n     *\n     * @param key  the key, always the main map key\n     * @param value  the value, always the main map value\n     ;\n\t\tmethod `private void doRedBlackInsert(Node<K, V>, DataElement)` returns `void`, related with `org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(Node<K, V>)`, api document: \n     * Complicated red-black insert stuff. Based on Sun's TreeMap\n     * implementation, though it's barely recognizable anymore.\n     *\n     * @param insertedNode the node to be inserted\n     * @param dataElement  the KEY or VALUE int\n     ;\n\t\tmethod `private void swapPosition(Node<K, V>, Node<K, V>, DataElement)` returns `void`, related with `org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(Node<K, V>)`, api document: \n     * Swaps two nodes (except for their content), taking care of\n     * special cases where one is the other's parent ... hey, it\n     * happens.\n     *\n     * @param x one node\n     * @param y another node\n     * @param dataElement  the KEY or VALUE int\n     ;\n\t\tmethod `private void insertValue(Node<K, V>)` returns `void`, related with `org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(Node<K, V>)`, api document: \n     * Inserts a node by its value.\n     *\n     * @param newNode the node to be inserted\n     *\n     * @throws IllegalArgumentException if the node already exists\n     *                                     in the value mapping\n     ;\n\t\tmethod `private Node<K, V> lookup(Object, DataElement)` returns `org.apache.commons.collections4.bidimap.TreeBidiMap.Node<K, V>`, related with `org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(Node<K, V>)`, api document: \n     * Does the actual lookup of a piece of data.\n     *\n     * @param data the key or value to be looked up\n     * @param dataElement  either the {@link DataElement#KEY key}\n     *                     or the {@link DataElement#VALUE value}.\n     * @return the desired Node, or null if there is no mapping of the\n     *         specified data\n     ;\n\t\tmethod `private void rotateLeft(Node<K, V>, DataElement)` returns `void`, related with `org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(Node<K, V>)`, api document: \n     * Does a rotate left. standard fare in the world of balanced trees.\n     *\n     * @param node the node to be rotated\n     * @param dataElement  either the {@link DataElement#KEY key}\n     *                     or the {@link DataElement#VALUE value}.\n     ;\n\t\tmethod `private void doRedBlackDeleteFixup(Node<K, V>, DataElement)` returns `void`, related with `org.apache.commons.collections4.bidimap.TreeBidiMap.doRedBlackDelete(Node<K, V>)`, api document: \n     * Complicated red-black delete stuff. Based on Sun's TreeMap\n     * implementation, though it's barely recognizable anymore. This\n     * rebalances the tree (somewhat, as red-black trees are not\n     * perfectly balanced -- perfect balancing takes longer)\n     *\n     * @param replacementNode the node being replaced\n     * @param dataElement  the KEY or VALUE int\n     ;"}
```

**Note**: If the method-to-test in the @Test function is a private method, you can use **reflection** to call it.

Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 


