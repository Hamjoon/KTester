package org.jdom2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StringBin_locate_Test {

    private StringBin stringBin;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the StringBin instance before each test
        stringBin = new StringBin();
    }

    private int invokeLocateMethod(int hash, String value, String[] bucket, int length) {
        // Use reflection to invoke the private locate method
        try {
            java.lang.reflect.Method method = StringBin.class.getDeclaredMethod("locate", int.class, String.class, String[].class, int.class);
            method.setAccessible(true);
            return (int) method.invoke(stringBin, hash, value, bucket, length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testLocateWithExistingValue() {
        // Given
        String[] bucket = { "apple", "banana", "cherry" };
        int hash = "banana".hashCode();
        int length = bucket.length;
        // When
        int result = invokeLocateMethod(hash, "banana", bucket, length);
        // Then
        // Expecting index 1 for "banana"
        assertEquals(1, result);
    }

    @Test
    void testLocateWithNonExistingValue() {
        // Given
        String[] bucket = { "apple", "banana", "cherry" };
        int hash = "date".hashCode();
        int length = bucket.length;
        // When
        int result = invokeLocateMethod(hash, "date", bucket, length);
        // Then
        // Expecting a negative insertion point
        assertTrue(result < 0);
    }

    @Test
    void testLocateWithNullValue() {
        // Given
        String[] bucket = { "apple", "banana", "cherry" };
        // This will throw a NullPointerException
        int hash = null.hashCode();
        int length = bucket.length;
        // When & Then
        assertThrows(NullPointerException.class, () -> {
            invokeLocateMethod(hash, null, bucket, length);
        });
    }

    @Test
    void testLocateWithEmptyBucket() {
        // Given
        String[] bucket = {};
        int hash = "apple".hashCode();
        int length = bucket.length;
        // When
        int result = invokeLocateMethod(hash, "apple", bucket, length);
        // Then
        // Expecting -1 for empty bucket
        assertEquals(-1, result);
    }

    @Test
    void testLocateWithValueAtEnd() {
        // Given
        String[] bucket = { "banana", "cherry", "date", "fig", "grape" };
        int hash = "grape".hashCode();
        int length = bucket.length;
        // When
        int result = invokeLocateMethod(hash, "grape", bucket, length);
        // Then
        // Expecting index 4 for "grape"
        assertEquals(4, result);
    }

    @Test
    void testLocateWithDuplicateValues() {
        // Given
        String[] bucket = { "banana", "banana", "banana", "cherry", "date" };
        int hash = "banana".hashCode();
        int length = bucket.length;
        // When
        int result = invokeLocateMethod(hash, "banana", bucket, length);
        // Then
        // Expecting index 0 for "banana"
        assertEquals(0, result);
    }

    @Test
    void testLocateWithInsertionPoint() {
        // Given
        String[] bucket = { "banana", "banana", "banana", "cherry", "date" };
        int hash = "fig".hashCode();
        int length = bucket.length;
        // When
        int result = invokeLocateMethod(hash, "fig", bucket, length);
        // Then
        // Expecting -5 as insertion point for "fig"
        assertEquals(-5, result);
    }

    @Test
    void testLocateWithHashMismatch() {
        // Given
        String[] bucket = { "apple", "cherry", "date", "fig", "grape" };
        // Hash does not match any element
        int hash = "banana".hashCode();
        int length = bucket.length;
        // When
        int result = invokeLocateMethod(hash, "banana", bucket, length);
        // Then
        // Expecting -1 since "banana" is not in the bucket
        assertEquals(-1, result);
    }
}
