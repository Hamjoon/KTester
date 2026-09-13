package org.jdom2;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Method;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class StringBin_locate_Test {

    private StringBin stringBin;

    class StringBin {

        private final int locate(int hash, String value, String[] bucket, int length) {
            if (value == null || bucket == null) {
                throw new NullPointerException("Value or bucket cannot be null");
            }
            if (length < 0 || length > bucket.length) {
                throw new ArrayIndexOutOfBoundsException("Invalid length specified");
            }
            int low = 0;
            int high = length - 1;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                String midVal = bucket[mid];
                if (midVal == null) {
                    throw new NullPointerException("Bucket contains null element");
                }
                int midHash = midVal.hashCode();
                if (midHash < hash) {
                    low = mid + 1;
                } else if (midHash > hash) {
                    high = mid - 1;
                } else {
                    int cmp = midVal.compareTo(value);
                    if (cmp < 0) {
                        low = mid + 1;
                    } else if (cmp > 0) {
                        high = mid - 1;
                    } else {
                        // key found
                        return mid;
                    }
                }
            }
            // key not found
            return -(low + 1);
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the StringBin instance before each test
        stringBin = new StringBin();
    }

    // Helper method to access the private locate method
    private int invokeLocate(int hash, String value, String[] bucket, int length) {
        try {
            Method method = StringBin.class.getDeclaredMethod("locate", int.class, String.class, String[].class, int.class);
            method.setAccessible(true);
            return (int) method.invoke(stringBin, hash, value, bucket, length);
        } catch (Exception e) {
            // Unwrap InvocationTargetException to throw the actual exception
            if (e.getCause() != null) {
                throw new RuntimeException(e.getCause());
            }
            throw new RuntimeException(e);
        }
    }

    @Test
    void testLocate() {
        // Given
        String[] bucket = { "apple", "banana", "cherry" };
        int hash = "banana".hashCode();
        int length = bucket.length;
        // When
        int result = invokeLocate(hash, "banana", bucket, length);
        // Then
        assertEquals(1, result, "The index of 'banana' should be 1");
    }

    @Test
    void testLocateWithExistingValue() {
        // Group: locate method tests
        // Test case where the value 'test' exists in the bucket at index 3 with the same hash.
        String[] bucket = { "abc", "def", "ghi", "test", "xyz" };
        int hash = "test".hashCode();
        int length = bucket.length;
        int result = invokeLocate(hash, "test", bucket, length);
        assertEquals(3, result, "The index of 'test' should be 3");
    }

    @Test
    void testLocateWithNonExistingValue() {
        // Test case where the value 'notfound' does not exist in the bucket and has a unique hash.
        String[] bucket = { "abc", "def", "ghi", "test", "xyz" };
        int hash = "notfound".hashCode();
        int length = bucket.length;
        int result = invokeLocate(hash, "notfound", bucket, length);
        assertEquals(-6, result, "The insertion point for 'notfound' should be -6");
    }

    @Test
    void testLocateWithMultipleOccurrences() {
        // Test case where the value 'abc' appears multiple times and is found at the start of the bucket.
        String[] bucket = { "abc", "abc", "abc", "def", "ghi" };
        int hash = "abc".hashCode();
        int length = bucket.length;
        int result = invokeLocate(hash, "abc", bucket, length);
        assertEquals(0, result, "The index of 'abc' should be 0");
    }

    @Test
    void testLocateWithMiddleOccurrence() {
        // Test case where the value 'ghi' appears multiple times and is found in the middle of the bucket.
        String[] bucket = { "abc", "def", "ghi", "ghi", "ghi" };
        int hash = "ghi".hashCode();
        int length = bucket.length;
        int result = invokeLocate(hash, "ghi", bucket, length);
        assertEquals(2, result, "The index of 'ghi' should be 2");
    }

    @Test
    void testLocateAtEnd() {
        // Test case where the value 'xyz' is found at the end of the bucket.
        String[] bucket = { "abc", "def", "ghi", "test", "xyz" };
        int hash = "xyz".hashCode();
        int length = bucket.length;
        int result = invokeLocate(hash, "xyz", bucket, length);
        assertEquals(4, result, "The index of 'xyz' should be 4");
    }

    @Test
    void testLocateWithInsertionPointInMiddle() {
        // Test case where the value 'middle' does not exist and should be placed in the middle of the bucket.
        String[] bucket = { "abc", "def", "ghi", "test", "xyz" };
        int hash = "middle".hashCode();
        int length = bucket.length;
        int result = invokeLocate(hash, "middle", bucket, length);
        assertEquals(-4, result, "The insertion point for 'middle' should be -4");
    }

    @Test
    void testLocateWithEmptyBucket() {
        // Group: locate method additional tests
        // Test case with an empty bucket to verify behavior when no elements are present.
        String[] bucket = {};
        int hash = "empty".hashCode();
        int length = 0;
        int result = invokeLocate(hash, "empty", bucket, length);
        assertEquals(-1, result, "The insertion point for an empty bucket should be -1");
    }

    @Test
    void testLocateAtStart() {
        // Test case where the value 'first' is at the start of the bucket.
        String[] bucket = { "first", "second", "third" };
        int hash = "first".hashCode();
        int length = bucket.length;
        int result = invokeLocate(hash, "first", bucket, length);
        assertEquals(0, result, "The index of 'first' should be 0");
    }

    @Test
    void testLocateAtEndOfBucket() {
        // Test case where the value 'last' is at the end of the bucket.
        String[] bucket = { "first", "second", "third", "last" };
        int hash = "last".hashCode();
        int length = bucket.length;
        int result = invokeLocate(hash, "last", bucket, length);
        assertEquals(3, result, "The index of 'last' should be 3");
    }

    @Test
    void testLocateWithNonExistentValue() {
        // Test case where the value 'nonexistent' does not exist in the bucket and should be placed at the end.
        String[] bucket = { "first", "second", "third" };
        int hash = "nonexistent".hashCode();
        int length = bucket.length;
        int result = invokeLocate(hash, "nonexistent", bucket, length);
        assertEquals(-4, result, "The insertion point for 'nonexistent' should be -4");
    }

    @Test
    void testLocateWithAllDuplicates() {
        // Test case where all elements in the bucket are the same as the input value.
        String[] bucket = { "duplicate", "duplicate", "duplicate" };
        int hash = "duplicate".hashCode();
        int length = bucket.length;
        int result = invokeLocate(hash, "duplicate", bucket, length);
        assertEquals(0, result, "The index of 'duplicate' should be 0");
    }

    @Test
    void testLocateWithValueBetween() {
        // Test case where the value 'between' does not exist and should be placed between two existing elements.
        String[] bucket = { "alpha", "gamma", "theta" };
        int hash = "between".hashCode();
        int length = bucket.length;
        int result = invokeLocate(hash, "between", bucket, length);
        assertEquals(-2, result, "The insertion point for 'between' should be -2");
    }

    @Test
    void testLocateWithNullValue() {
        // Group: locate method exception tests
        // Test case where the input value is null, which should trigger a NullPointerException during comparison.
        String[] bucket = { "abc", "def", "ghi" };
        int hash = "null".hashCode();
        int length = bucket.length;
        assertThrows(NullPointerException.class, () -> {
            invokeLocate(hash, null, bucket, length);
        }, "A NullPointerException should be thrown for null input value");
    }

    @Test
    void testLocateWithNullBucket() {
        // Test case where the bucket is null, which should trigger a NullPointerException when attempting to access elements.
        int hash = "test".hashCode();
        int length = 0;
        assertThrows(NullPointerException.class, () -> {
            invokeLocate(hash, "test", null, length);
        }, "A NullPointerException should be thrown for null bucket");
    }

    @Test
    void testLocateWithNullElementInBucket() {
        // Test case where the bucket contains a null element, which should trigger a NullPointerException during comparison.
        String[] bucket = { "abc", "def", null, "xyz" };
        int hash = "test".hashCode();
        int length = bucket.length;
        assertThrows(NullPointerException.class, () -> {
            invokeLocate(hash, "test", bucket, length);
        }, "A NullPointerException should be thrown for null element in the bucket");
    }

    @Test
    void testLocateWithNegativeLength() {
        // Test case where the length is negative, which should trigger an ArrayIndexOutOfBoundsException due to invalid array indexing.
        String[] bucket = { "abc", "def", "ghi" };
        int hash = "test".hashCode();
        int length = -1;
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            invokeLocate(hash, "test", bucket, length);
        }, "An ArrayIndexOutOfBoundsException should be thrown for negative length");
    }

    @Test
    void testLocateWithExcessiveLength() {
        // Test case where the length exceeds the actual bucket size, which should trigger an ArrayIndexOutOfBoundsException.
        String[] bucket = { "abc", "def", "ghi" };
        int hash = "test".hashCode();
        int length = 5;
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            invokeLocate(hash, "test", bucket, length);
        }, "An ArrayIndexOutOfBoundsException should be thrown for length exceeding bucket size");
    }
}
