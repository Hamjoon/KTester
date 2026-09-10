```java
package net.datafaker.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;

@ExtendWith(MockitoExtension.class)
class FakeValuesService_bothify_Test {

    private FakeValuesService fakeValuesService;
    private FakerContext context;

    @Mock
    private RandomService randomService;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here if needed
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the FakeValuesService and FakerContext before each test
        fakeValuesService = new FakeValuesService();
        context = new FakerContext(new Locale("en"), randomService);
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup after each test if needed
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here if needed
    }

    @Test
    void testBothifyWithAllFlagsTrue() {
        // Test bothify with input containing '#' and '?' with isUpper, numerify, and letterify set to true.
        when(randomService.nextInt(10)).thenReturn(1); // Mock behavior for digit replacement
        when(randomService.nextInt(26)).thenReturn(0); // Mock behavior for letter replacement
        String input = "Hello #?";
        String result = invokePrivateBothify(input, context, true, true, true);
        assertEquals("Hello 1A", result); // Expected output with uppercase letters and digits replacing # and ?
    }

    @Test
    void testBothifyWithLowerCaseAndAllFlagsTrue() {
        // Test bothify with input containing '#' and '?' with isUpper set to false, and numerify and letterify set to true.
        when(randomService.nextInt(10)).thenReturn(2); // Mock behavior for digit replacement
        when(randomService.nextInt(26)).thenReturn(1); // Mock behavior for letter replacement
        String input = "Hello #?";
        String result = invokePrivateBothify(input, context, false, true, true);
        assertEquals("Hello 2b", result); // Expected output with lowercase letters and digits replacing # and ?
    }

    @Test
    void testBothifyWithUpperCaseAndLetterifyOnly() {
        // Test bothify with input containing '#' and '?' with isUpper set to true, numerify set to false, and letterify set to true.
        when(randomService.nextInt(26)).thenReturn(3); // Mock behavior for letter replacement
        String input = "Hello #?";
        String result = invokePrivateBothify(input, context, true, false, true);
        assertEquals("Hello 3D", result); // Expected output with uppercase letters and letters replacing # and ?
    }

    @Test
    void testBothifyWithUpperCaseAndNumerifyOnly() {
        // Test bothify with input containing '#' and '?' with isUpper and numerify set to true, and letterify set to false.
        when(randomService.nextInt(10)).thenReturn(4); // Mock behavior for digit replacement
        String input = "Hello #?";
        String result = invokePrivateBothify(input, context, true, true, false);
        assertEquals("Hello 4?", result); // Expected output with uppercase letters and digits replacing # and letters remaining unchanged
    }

    @Test
    void testBothifyWithLowerCaseAndLetterifyOnly() {
        // Test bothify with input containing '#' and '?' with isUpper set to false, numerify set to false, and letterify set to true.
        when(randomService.nextInt(26)).thenReturn(5); // Mock behavior for letter replacement
        String input = "Hello #?";
        String result = invokePrivateBothify(input, context, false, false, true);
        assertEquals("Hello 5f", result); // Expected output with lowercase letters and letters replacing # and ?
    }

    @Test
    void testBothifyWithNoReplacements() {
        // Test bothify with input containing '#' and '?' with all boolean flags set to false.
        String input = "Hello #?";
        String result = invokePrivateBothify(input, context, false, false, false);
        assertEquals("Hello #?", result); // Expected output with letters remaining unchanged and no replacements
    }

    @Test
    void testBothifyWithOnlyDigits() {
        // Test bothify with input consisting of three '#' characters with numerify set to true and letterify set to false.
        when(randomService.nextInt(10)).thenReturn(6); // Mock behavior for digit replacement
        String input = "###";
        String result = invokePrivateBothify(input, context, false, true, false);
        assertEquals("666", result); // Expected output with digits replacing all '#' characters
    }

    @Test
    void testBothifyWithEmptyInput() {
        // Test bothify with an empty input string, expecting an empty output.
        String input = "";
        String result = invokePrivateBothify(input, context, false, false, false);
        assertEquals("", result); // Expected output should be empty
    }

    @Test
    void testBothifyWithNoPlaceholders() {
        // Test bothify with input containing no placeholders, expecting the same string as output.
        String input = "No replacements here";
        String result = invokePrivateBothify(input, context, false, false, false);
        assertEquals("No replacements here", result); // Expected output should be unchanged
    }

    @Test
    void testBothifyWithNullInput() {
        // Test bothify with null input string, expecting a NullPointerException.
        assertThrows(NullPointerException.class, () -> {
            invokePrivateBothify(null, context, true, true, true);
        });
    }

    @Test
    void testBothifyWithNullContext() {
        // Test bothify with null context, expecting a NullPointerException.
        assertThrows(NullPointerException.class, () -> {
            invokePrivateBothify("Hello #?", null, true, true, true);
        });
    }

    private String invokePrivateBothify(String input, FakerContext context, boolean isUpper, boolean numerify, boolean letterify) {
        try {
            Method method = FakeValuesService.class.getDeclaredMethod("bothify", String.class, FakerContext.class, boolean.class, boolean.class, boolean.class);
            method.setAccessible(true);
            return (String) method.invoke(fakeValuesService, input, context, isUpper, numerify, letterify);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
```