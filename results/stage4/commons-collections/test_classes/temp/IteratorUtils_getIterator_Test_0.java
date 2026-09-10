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

    private List<Integer> testList;

    private Iterator<Integer> testIterator;

    private Object testObject;

    // Custom classes for testing purposes
    private static class CustomIterable implements Iterable<String> {

        @Override
        public Iterator<String> iterator() {
            return Arrays.asList("custom").iterator();
        }
    }

    private static class ObjectWithNoIterator {
        // No iterator method
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the test list and iterator before each test
        testList = Arrays.asList(1, 2, 3, 4, 5);
        testIterator = testList.iterator();
        // Example object to be tested
        testObject = new ArrayList<>(testList);
    }

    private NodeList createNodeList(final Node[] nodes) {
        return new NodeList() {

            @Override
            public int getLength() {
                return nodes.length;
            }

            @Override
            public Node item(final int index) {
                return nodes[index];
            }
        };
    }

    @Test
    void testGetIteratorWithNull() {
        // Test case where the input is null. The method should return an empty iterator.
        Iterator<?> iterator = IteratorUtils.getIterator(null);
        assertSame(IteratorUtils.EMPTY_ITERATOR, iterator);
    }

    @Test
    void testGetIteratorWithIterator() {
        // Test case where the input is an Iterator. The method should return the same iterator.
        Iterator<?> iterator = IteratorUtils.getIterator(testIterator);
        assertSame(testIterator, iterator);
    }

    @Test
    void testGetIteratorWithIterable() {
        // Test case where the input is an Iterable. The method should return an iterator over its elements.
        Iterator<?> iterator = IteratorUtils.getIterator(testObject);
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
    }

    @Test
    void testGetIteratorWithArray() {
        // Test case where the input is an Object array. The method should return an ObjectArrayIterator.
        Object[] array = { 1, 2, 3 };
        Iterator<?> iterator = IteratorUtils.getIterator(array);
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
    }

    @Test
    void testGetIteratorWithEnumeration() {
        // Test case where the input is an Enumeration. The method should return an EnumerationIterator.
        Enumeration<Integer> enumeration = Collections.enumeration(testList);
        Iterator<?> iterator = IteratorUtils.getIterator(enumeration);
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
    }

    @Test
    void testGetIteratorWithMap() {
        // Test case where the input is a Map. The method should return an iterator over the values of the map.
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        Iterator<?> iterator = IteratorUtils.getIterator(map);
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
    }

    @Test
    void testGetIteratorWithNodeList() {
        // Test case where the input is a NodeList. The method should return a NodeListIterator.
        NodeList nodeList = createNodeList(new Node[0]);
        Iterator<?> iterator = IteratorUtils.getIterator(nodeList);
        assertFalse(iterator.hasNext());
    }

    @Test
    void testGetIteratorWithNode() {
        // Test case where the input is a Node. The method should return a NodeListIterator.
        Node node = mock(Node.class);
        Iterator<?> iterator = IteratorUtils.getIterator(node);
        // Ensure that it returns a NodeListIterator
        assertNotNull(iterator);
    }

    @Test
    void testGetIteratorWithDictionary() {
        // Test case where the input is a Dictionary. The method should return an EnumerationIterator.
        Dictionary<String, String> dictionary = new Hashtable<>();
        dictionary.put("key", "value");
        Iterator<?> iterator = IteratorUtils.getIterator(dictionary);
        assertTrue(iterator.hasNext());
        assertEquals("value", iterator.next());
    }

    @Test
    void testGetIteratorWithArrayInput() {
        // Test case where the input is an array. The method should return an ArrayIterator.
        int[] array = { 1, 2, 3 };
        Iterator<?> iterator = IteratorUtils.getIterator(array);
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
    }

    @Test
    void testGetIteratorWithCustomIterable() {
        // Test case where the input is an object with an iterator method. The method should invoke the iterator method and return the result.
        CustomIterable customIterable = new CustomIterable();
        Iterator<?> iterator = IteratorUtils.getIterator(customIterable);
        assertTrue(iterator.hasNext());
        assertEquals("custom", iterator.next());
    }

    @Test
    void testGetIteratorWithReflectiveException() {
        // Test case where the input causes a reflective operation exception. The method should return a singleton iterator for the object.
        ObjectWithNoIterator obj = new ObjectWithNoIterator();
        Iterator<?> iterator = IteratorUtils.getIterator(obj);
        assertSame(IteratorUtils.singletonIterator(obj), iterator);
    }

    @Test
    void testGetIteratorWithEmptyIterable() {
        // Test case where the input is an empty Iterable. The method should return an iterator that has no elements.
        Iterator<?> iterator = IteratorUtils.getIterator(Collections.emptyList());
        assertFalse(iterator.hasNext());
    }
}
