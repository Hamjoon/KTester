package org.apache.commons.codec.digest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MurmurHash3_hash128x64Internal_Test {

    // Default seed
    private static final long SEED = 104729;

    private static final byte[] TEST_DATA = {/* Add test byte data here */
    };

    private static final int OFFSET = 0;

    private static final int LENGTH = TEST_DATA.length;

    // Helper method to invoke the private focal method using reflection
    private long[] invokeHash128x64Internal(byte[] data, int offset, int length, long seed) {
        try {
            java.lang.reflect.Method method = MurmurHash3.class.getDeclaredMethod("hash128x64Internal", byte[].class, int.class, int.class, long.class);
            method.setAccessible(true);
            return (long[]) method.invoke(null, data, offset, length, seed);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testHash128x64Internal() {
        long[] expectedHash = new long[] {/* expected hash values */
        };
        long[] actualHash = invokeHash128x64Internal(TEST_DATA, OFFSET, LENGTH, SEED);
        assertArrayEquals(expectedHash, actualHash);
    }

    @Test
    void testHash128x64Internal_completeBlock() {
        byte[] data = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
        long[] expectedHash = new long[] {/* expectedH1, expectedH2 */
        };
        long[] actualHash = invokeHash128x64Internal(data, OFFSET, data.length, SEED);
        assertArrayEquals(expectedHash, actualHash);
    }

    @Test
    void testHash128x64Internal_lessThanOneBlock() {
        byte[] data = new byte[] { 1, 2, 3 };
        long[] expectedHash = new long[] {/* expectedH1, expectedH2 */
        };
        long[] actualHash = invokeHash128x64Internal(data, OFFSET, data.length, SEED);
        assertArrayEquals(expectedHash, actualHash);
    }

    @Test
    void testHash128x64Internal_singleByteInput() {
        byte[] data = new byte[] { 0 };
        long[] expectedHash = new long[] {/* expectedH1, expectedH2 */
        };
        long[] actualHash = invokeHash128x64Internal(data, OFFSET, data.length, SEED);
        assertArrayEquals(expectedHash, actualHash);
    }

    @Test
    void testHash128x64Internal_emptyArray() {
        byte[] data = new byte[] {};
        // Should match the expected output for an empty array
        long[] expectedHash = new long[] {/* expectedH1, expectedH2 */
        };
        long[] actualHash = invokeHash128x64Internal(data, OFFSET, 0, SEED);
        assertArrayEquals(expectedHash, actualHash);
    }

    @Test
    void testHash128x64Internal_15Bytes() {
        byte[] data = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        long[] expectedHash = new long[] {/* expectedH1, expectedH2 */
        };
        long[] actualHash = invokeHash128x64Internal(data, OFFSET, data.length, SEED);
        assertArrayEquals(expectedHash, actualHash);
    }

    @Test
    void testHash128x64Internal_exactlyOneBlock() {
        byte[] data = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        long[] expectedHash = new long[] {/* expectedH1, expectedH2 */
        };
        long[] actualHash = invokeHash128x64Internal(data, OFFSET, data.length, SEED);
        assertArrayEquals(expectedHash, actualHash);
    }

    @Test
    void testHash128x64Internal_exceedsOneBlock() {
        byte[] data = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 };
        long[] expectedHash = new long[] {/* expectedH1, expectedH2 */
        };
        long[] actualHash = invokeHash128x64Internal(data, OFFSET, data.length, SEED);
        assertArrayEquals(expectedHash, actualHash);
    }

    @Test
    void testHash128x64Internal_negativeOffset() {
        byte[] data = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            invokeHash128x64Internal(data, -1, 8, SEED);
        });
    }

    @Test
    void testHash128x64Internal_offsetExceedsArrayLength() {
        byte[] data = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 };
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            invokeHash128x64Internal(data, 9, 1, SEED);
        });
    }

    @Test
    void testHash128x64Internal_lengthExceedsArraySize() {
        byte[] data = new byte[] { 1, 2, 3, 4 };
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            invokeHash128x64Internal(data, 0, 5, SEED);
        });
    }
}
