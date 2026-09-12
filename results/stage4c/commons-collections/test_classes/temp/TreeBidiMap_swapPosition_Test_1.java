package org.apache.commons.collections4.bidimap;

import static org.apache.commons.collections4.bidimap.TreeBidiMap.DataElement.KEY;
import static org.apache.commons.collections4.bidimap.TreeBidiMap.DataElement.VALUE;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class TreeBidiMap_swapPosition_Test {

    private TreeBidiMap<Integer, String> treeBidiMap;

    private TreeBidiMap.Node<Integer, String> node1;

    private TreeBidiMap.Node<Integer, String> node2;

    @BeforeEach
    void setupBeforeEach() {
        treeBidiMap = new TreeBidiMap<>();
        node1 = new TreeBidiMap.Node<>(1, "one");
        node2 = new TreeBidiMap.Node<>(2, "two");
        // Set up initial state using reflection
        setLeft(node1, node2, KEY);
        setParent(node2, node1, KEY);
    }

    // Reflection helper methods
    private void setLeft(TreeBidiMap.Node<Integer, String> node, TreeBidiMap.Node<Integer, String> child, TreeBidiMap.DataElement dataElement) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = TreeBidiMap.Node.class.getDeclaredMethod("setLeft", TreeBidiMap.Node.class, TreeBidiMap.DataElement.class);
        method.setAccessible(true);
        method.invoke(node, child, dataElement);
    }

    private void setParent(TreeBidiMap.Node<Integer, String> node, TreeBidiMap.Node<Integer, String> parent, TreeBidiMap.DataElement dataElement) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = TreeBidiMap.Node.class.getDeclaredMethod("setParent", TreeBidiMap.Node.class, TreeBidiMap.DataElement.class);
        method.setAccessible(true);
        method.invoke(node, parent, dataElement);
    }

    private TreeBidiMap.Node<Integer, String> getLeft(TreeBidiMap.Node<Integer, String> node, TreeBidiMap.DataElement dataElement) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = TreeBidiMap.Node.class.getDeclaredMethod("getLeft", TreeBidiMap.DataElement.class);
        method.setAccessible(true);
        return (TreeBidiMap.Node<Integer, String>) method.invoke(node, dataElement);
    }

    private TreeBidiMap.Node<Integer, String> getParent(TreeBidiMap.Node<Integer, String> node, TreeBidiMap.DataElement dataElement) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = TreeBidiMap.Node.class.getDeclaredMethod("getParent", TreeBidiMap.DataElement.class);
        method.setAccessible(true);
        return (TreeBidiMap.Node<Integer, String>) method.invoke(node, dataElement);
    }

    private TreeBidiMap.Node<Integer, String> getRight(TreeBidiMap.Node<Integer, String> node, TreeBidiMap.DataElement dataElement) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = TreeBidiMap.Node.class.getDeclaredMethod("getRight", TreeBidiMap.DataElement.class);
        method.setAccessible(true);
        return (TreeBidiMap.Node<Integer, String>) method.invoke(node, dataElement);
    }

    private void swapPosition(TreeBidiMap<Integer, String> map, TreeBidiMap.Node<Integer, String> x, TreeBidiMap.Node<Integer, String> y, TreeBidiMap.DataElement dataElement) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = TreeBidiMap.class.getDeclaredMethod("swapPosition", TreeBidiMap.Node.class, TreeBidiMap.Node.class, TreeBidiMap.DataElement.class);
        method.setAccessible(true);
        method.invoke(map, x, y, dataElement);
    }

    private void setRootNode(TreeBidiMap<Integer, String> map, TreeBidiMap.Node<Integer, String>[] rootNode) throws NoSuchFieldException, IllegalAccessException {
        Field field = TreeBidiMap.class.getDeclaredField("rootNode");
        field.setAccessible(true);
        field.set(map, rootNode);
    }

    private TreeBidiMap.Node<Integer, String>[] getRootNode(TreeBidiMap<Integer, String> map) throws NoSuchFieldException, IllegalAccessException {
        Field field = TreeBidiMap.class.getDeclaredField("rootNode");
        field.setAccessible(true);
        return (TreeBidiMap.Node<Integer, String>[]) field.get(map);
    }

    @Test
    void testSwapPosition() {
        // Given
        assertEquals(node2, getLeft(node1, KEY));
        assertEquals(node1, getParent(node2, KEY));
        // When
        swapPosition(treeBidiMap, node1, node2, KEY);
        // Then
        assertEquals(node1, getLeft(node2, KEY));
        assertEquals(node2, getParent(node1, KEY));
    }

    @Test
    void testSwapPosition_NonParentChildNodes() {
        // Given
        TreeBidiMap.Node<Integer, String> node3 = new TreeBidiMap.Node<>(3, "C");
        TreeBidiMap.Node<Integer, String> node4 = new TreeBidiMap.Node<>(4, "D");
        // Set initial relationships using reflection
        setLeft(node3, node4, VALUE);
        setParent(node4, node3, VALUE);
        // When
        swapPosition(treeBidiMap, node3, node4, VALUE);
        // Then
        assertEquals(node3, getLeft(node4, VALUE));
        assertEquals(node4, getParent(node3, VALUE));
    }

    @Test
    void testSwapPosition_SameNode() {
        // Given
        TreeBidiMap.Node<Integer, String> node = new TreeBidiMap.Node<>(1, "A");
        // When
        swapPosition(treeBidiMap, node, node, KEY);
        // Then
        // No change expected
        assertNull(getParent(node, KEY));
        assertNull(getLeft(node, KEY));
        assertNull(getRight(node, KEY));
    }

    @Test
    void testSwapPosition_DifferentValues() {
        // Given
        TreeBidiMap.Node<Integer, String> node3 = new TreeBidiMap.Node<>(3, "C");
        TreeBidiMap.Node<Integer, String> node4 = new TreeBidiMap.Node<>(4, "D");
        // Set initial relationships using reflection
        setLeft(node3, node4, VALUE);
        setParent(node4, node3, VALUE);
        // When
        swapPosition(treeBidiMap, node3, node4, VALUE);
        // Then
        assertEquals(node3, getLeft(node4, VALUE));
        assertEquals(node4, getParent(node3, VALUE));
    }

    @Test
    void testSwapPosition_XIsYParent() {
        // Given
        TreeBidiMap.Node<Integer, String> node3 = new TreeBidiMap.Node<>(3, "C");
        setLeft(node1, node3, KEY);
        setParent(node3, node1, KEY);
        // When
        swapPosition(treeBidiMap, node1, node3, KEY);
        // Then
        assertEquals(node1, getLeft(node3, KEY));
        assertEquals(node3, getParent(node1, KEY));
    }

    @Test
    void testSwapPosition_YIsXParent() {
        // Given
        TreeBidiMap.Node<Integer, String> node3 = new TreeBidiMap.Node<>(3, "C");
        setLeft(node3, node1, KEY);
        setParent(node1, node3, KEY);
        // When
        swapPosition(treeBidiMap, node3, node1, KEY);
        // Then
        assertEquals(node3, getLeft(node1, KEY));
        assertEquals(node1, getParent(node3, KEY));
    }

    @Test
    void testSwapPosition_RootNodeChanges() {
        // Given
        TreeBidiMap.Node<Integer, String> rootNode = new TreeBidiMap.Node<>(0, "Root");
        setRootNode(treeBidiMap, new TreeBidiMap.Node[] { rootNode, null });
        // When
        swapPosition(treeBidiMap, rootNode, node1, KEY);
        // Then
        assertEquals(node1, getRootNode(treeBidiMap)[KEY.ordinal()]);
    }

    @Test
    void testSwapPosition_NullX() {
        // Given
        TreeBidiMap.Node<Integer, String> node = new TreeBidiMap.Node<>(2, "B");
        // Expect NullPointerException
        assertThrows(NullPointerException.class, () -> {
            try {
                swapPosition(treeBidiMap, null, node, KEY);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void testSwapPosition_NullY() {
        // Given
        TreeBidiMap.Node<Integer, String> node = new TreeBidiMap.Node<>(1, "A");
        // Expect NullPointerException
        assertThrows(NullPointerException.class, () -> {
            try {
                swapPosition(treeBidiMap, node, null, KEY);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void testSwapPosition_NullDataElement() {
        // Given
        TreeBidiMap.Node<Integer, String> nodeA = new TreeBidiMap.Node<>(1, "A");
        TreeBidiMap.Node<Integer, String> nodeB = new TreeBidiMap.Node<>(2, "B");
        // Expect NullPointerException
        assertThrows(NullPointerException.class, () -> {
            try {
                swapPosition(treeBidiMap, nodeA, nodeB, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void testSwapPosition_InvalidDataElement() {
        // Given
        TreeBidiMap.Node<Integer, String> nodeA = new TreeBidiMap.Node<>(1, "A");
        TreeBidiMap.Node<Integer, String> nodeB = new TreeBidiMap.Node<>(2, "B");
        // Mock an invalid DataElement
        TreeBidiMap.DataElement invalidDataElement = Mockito.mock(TreeBidiMap.DataElement.class);
        Mockito.when(invalidDataElement.ordinal()).thenThrow(IllegalArgumentException.class);
        // Expect IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            try {
                swapPosition(treeBidiMap, nodeA, nodeB, invalidDataElement);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
