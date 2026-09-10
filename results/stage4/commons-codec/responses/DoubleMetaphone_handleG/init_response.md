Based on the provided information, here is the completed test class template for testing the `handleG` method in the `DoubleMetaphone` class. The test class includes setup and teardown methods, and it uses the `DoubleMetaphoneResult` constructor to create instances for testing. The test cases will be structured to validate the behavior of the `handleG` method indirectly through the public methods of the `DoubleMetaphone` class.

```java
package org.apache.commons.codec.language;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DoubleMetaphone_handleG_Test {
    private DoubleMetaphone doubleMetaphone;
    private DoubleMetaphone.DoubleMetaphoneResult result;

    @BeforeAll
    static void setupBeforeAll() {
        // This method can be used for one-time setup before all tests, if needed.
    }

    @BeforeEach
    void setupBeforeEach() {
        doubleMetaphone = new DoubleMetaphone();
        result = doubleMetaphone.new DoubleMetaphoneResult(doubleMetaphone.getMaxCodeLen());
    }

    @AfterEach
    void teardownAfterEach() {
        // This method can be used for cleanup after each test, if needed.
    }

    @AfterAll
    static void teardownAfterAll() {
        // This method can be used for one-time cleanup after all tests, if needed.
    }

    @Test
    void testHandleGWithGH() {
        int index = 0;
        String value = "GHOST";
        boolean slavoGermanic = false;

        index = doubleMetaphone.handleG(value, result, index, slavoGermanic);
        
        assertEquals(2, index);
        assertEquals("K", result.getPrimary());
        assertEquals("", result.getAlternate());
    }

    @Test
    void testHandleGWithKN() {
        int index = 1; // Start at index 1 to simulate "KN"
        String value = "KNIGHT";
        boolean slavoGermanic = false;

        index = doubleMetaphone.handleG(value, result, index, slavoGermanic);
        
        assertEquals(2, index);
        assertEquals("N", result.getPrimary());
        assertEquals("KN", result.getAlternate());
    }

    @Test
    void testHandleGWithLI() {
        int index = 0; // Start at index 0 to simulate "LI"
        String value = "LIGER";
        boolean slavoGermanic = false;

        index = doubleMetaphone.handleG(value, result, index, slavoGermanic);
        
        assertEquals(2, index);
        assertEquals("KL", result.getPrimary());
        assertEquals("L", result.getAlternate());
    }

    @Test
    void testHandleGWithBeginning() {
        int index = 0; // Start at index 0 for the beginning case
        String value = "GEORGE";
        boolean slavoGermanic = false;

        index = doubleMetaphone.handleG(value, result, index, slavoGermanic);
        
        assertEquals(2, index);
        assertEquals("K", result.getPrimary());
        assertEquals("J", result.getAlternate());
    }

    // Additional test cases can be added here to cover more scenarios
}
```

### Explanation of the Test Class:
1. **Setup and Teardown Methods**: The `setupBeforeEach` method initializes a new instance of `DoubleMetaphone` and its `DoubleMetaphoneResult` for each test, ensuring isolation between tests. The `@BeforeAll` and `@AfterAll` methods are placeholders for any setup or cleanup that might be required at the class level.

2. **Test Cases**: Each test case uses the `handleG` method indirectly by passing different strings that represent various scenarios (e.g., `GH`, `KN`, `LI`, and beginning of the word). The tests check the expected index returned and the contents of the primary and alternate results after invoking `handleG`.

3. **Assertions**: The assertions verify that the method behaves as expected for the given inputs, checking both the index and the results stored in the `DoubleMetaphoneResult`.

This structure allows for comprehensive testing of the `handleG` method's functionality while adhering to the guidelines provided.