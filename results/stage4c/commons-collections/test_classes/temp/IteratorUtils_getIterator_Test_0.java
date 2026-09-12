package org.apache.commons.collections4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class IteratorUtils_getIterator_Test {

    private List<Integer> list;

    private Map<String, Integer> map;

    private Integer[] array;

    private Enumeration<Integer> enumeration;

    private NodeList nodeList;

    private Node node;

    private Dictionary<String, Integer> dictionary;

    class CustomObjectWithIterator {

        public Iterator<Integer> iterator() {
            return list.iterator();
        }
    }

    class CustomObjectWithoutIterator {
    }

    class CustomIterableWithoutIteratorMethod implements Iterable<Integer> {

        @Override
        public Iterator<Integer> iterator() {
            return list.iterator();
        }
    }

    class ObjectWithFaultyIteratorMethod {

        public Iterator<Integer> iterator() throws Exception {
            throw new Exception("Faulty iterator method");
        }
    }

    class CustomObjectWithIterator {

        public Iterator<Integer> iterator() {
            return list.iterator();
        }
    }

    class CustomObjectWithoutIterator {
    }

    class CustomIterableWithoutIteratorMethod implements Iterable<Integer> {

        @Override
        public Iterator<Integer> iterator() {
            return list.iterator();
        }
    }

    class ObjectWithFaultyIteratorMethod {

        public Iterator<Integer> iterator() throws Exception {
            throw new Exception("Faulty iterator method");
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the test data before each test
        list = Arrays.asList(1, 2, 3);
        map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        array = new Integer[] { 1, 2, 3 };
        enumeration = Collections.enumeration(Arrays.asList(1, 2, 3));
        nodeList = mock(NodeList.class);
        node = mock(Node.class);
        dictionary = new Hashtable<>();
        dictionary.put("one", 1);
    }

    @Test
    void testGetIteratorWithNull() {
        // Test with null input to return an empty iterator.
        assertSame(IteratorUtils.EMPTY_ITERATOR, IteratorUtils.getIterator(null));
    }

    @Test
    void testGetIteratorWithIterator() {
        // Test with an Iterator input to return the same iterator.
        Iterator<Integer> iterator = list.iterator();
        assertSame(iterator, IteratorUtils.getIterator(iterator));
    }

    @Test
    void testGetIteratorWithIterable() {
        // Test with an Iterable input to return its iterator.
        assertEquals(list.iterator(), IteratorUtils.getIterator(list));
    }

    @Test
    void testGetIteratorWithArray() {
        // Test with Object array input to return an ObjectArrayIterator.
        assertTrue(IteratorUtils.getIterator(array) instanceof ObjectArrayIterator);
    }

    @Test
    void testGetIteratorWithEnumeration() {
        // Test with Enumeration input to return an EnumerationIterator.
        assertTrue(IteratorUtils.getIterator(enumeration) instanceof EnumerationIterator);
    }

    @Test
    void testGetIteratorWithMap() {
        // Test with Map input to return an iterator over its values.
        assertEquals(map.values().iterator(), IteratorUtils.getIterator(map));
    }

    @Test
    void testGetIteratorWithNodeList() {
        // Test with NodeList input to return a NodeListIterator.
        assertTrue(IteratorUtils.getIterator(nodeList) instanceof NodeListIterator);
    }

    @Test
    void testGetIteratorWithNode() {
        // Test with Node input to return a NodeListIterator.
        assertTrue(IteratorUtils.getIterator(node) instanceof NodeListIterator);
    }

    @Test
    void testGetIteratorWithDictionary() {
        // Test with Dictionary input to return an EnumerationIterator over its elements.
        assertTrue(IteratorUtils.getIterator(dictionary) instanceof EnumerationIterator);
    }

    @Test
    void testGetIteratorWithArrayInstance() {
        // Test with primitive array input to return an ArrayIterator.
        Integer[][] array2D = { { 1, 2 }, { 3, 4 } };
        assertTrue(IteratorUtils.getIterator(array2D) instanceof ArrayIterator);
    }

    @Test
    void testGetIteratorWithObject() {
        // Test with an object that does not match any condition to return a SingletonIterator.
        Object obj = "test";
        assertTrue(IteratorUtils.getIterator(obj) instanceof SingletonIterator);
    }

    @Test
    void testGetIteratorWithCustomObjectWithIterator() {
        // Test with an object that has a public 'iterator' method returning an Iterator.
        class CustomObjectWithIterator {

            public Iterator<Integer> iterator() {
                return list.iterator();
            }
        }
        CustomObjectWithIterator obj = new CustomObjectWithIterator();
        assertEquals(list.iterator(), IteratorUtils.getIterator(obj));
    }

    @Test
    void testGetIteratorWithCustomObjectWithoutIterator() {
        // Test with an object that has no 'iterator' method to ensure it returns a SingletonIterator.
        class CustomObjectWithoutIterator {
        }
        CustomObjectWithoutIterator obj = new CustomObjectWithoutIterator();
        assertTrue(IteratorUtils.getIterator(obj) instanceof SingletonIterator);
    }

    @Test
    void testGetIteratorWithEmptyObjectArray() {
        // Test with an empty Object array to ensure it returns an ObjectArrayIterator.
        Object[] emptyArray = new Object[0];
        assertTrue(IteratorUtils.getIterator(emptyArray) instanceof ObjectArrayIterator);
    }

    @Test
    void testGetIteratorWithEmptyPrimitiveArray() {
        // Test with an empty primitive array to ensure it returns an ArrayIterator.
        int[] emptyArray = new int[0];
        assertTrue(IteratorUtils.getIterator(emptyArray) instanceof ArrayIterator);
    }

    @Test
    void testGetIteratorWithEmptyVector() {
        // Test with an empty Vector to ensure it returns an EnumerationIterator.
        Vector<Integer> vector = new Vector<>();
        assertTrue(IteratorUtils.getIterator(vector.elements()) instanceof EnumerationIterator);
    }

    @Test
    void testGetIteratorWithMapEntrySetIterator() {
        // Test with a Map's entrySet iterator to ensure it returns the same iterator.
        Iterator<Map.Entry<String, Integer>> entrySetIterator = map.entrySet().iterator();
        assertSame(entrySetIterator, IteratorUtils.getIterator(entrySetIterator));
    }

    @Test
    void testGetIteratorWithEmptyNodeList() {
        // Test with an empty NodeList to ensure it returns a NodeListIterator.
        NodeList emptyNodeList = mock(NodeList.class);
        when(emptyNodeList.getLength()).thenReturn(0);
        assertTrue(IteratorUtils.getIterator(emptyNodeList) instanceof NodeListIterator);
    }

    @Test
    void testGetIteratorWithEmptyNode() {
        // Test with a single Node to ensure it returns a NodeListIterator.
        Node emptyNode = mock(Node.class);
        assertTrue(IteratorUtils.getIterator(emptyNode) instanceof NodeListIterator);
    }

    @Test
    void testGetIteratorWithCustomIterableWithoutIteratorMethod() {
        // Test with an object that implements Iterable but does not have an iterator method.
        class CustomIterableWithoutIteratorMethod implements Iterable<Integer> {

            @Override
            public Iterator<Integer> iterator() {
                return list.iterator();
            }
        }
        CustomIterableWithoutIteratorMethod obj = new CustomIterableWithoutIteratorMethod();
        assertEquals(list.iterator(), IteratorUtils.getIterator(obj));
    }

    @Test
    void testGetIteratorWithObjectWithFaultyIteratorMethod() {
        // Test with an object that has a public 'iterator' method which throws an exception.
        class ObjectWithFaultyIteratorMethod {

            public Iterator<Integer> iterator() throws Exception {
                throw new Exception("Faulty iterator method");
            }
        }
        ObjectWithFaultyIteratorMethod obj = mock(ObjectWithFaultyIteratorMethod.class);
        try {
            when(obj.iterator()).thenThrow(new Exception("Faulty iterator method"));
        } catch (Exception e) {
            fail("Setup of mock failed.");
        }
        assertTrue(IteratorUtils.getIterator(obj) instanceof SingletonIterator);
    }
}
