```java
package net.datafaker.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Locale;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

@ExtendWith(MockitoExtension.class)
class FakeValuesService_bothify_Test {

    private FakeValuesService fakeValuesService;

    private FakerContext context;

    @Mock
    private RandomService randomService;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the FakeValuesService and FakerContext before each test
        fakeValuesService = new FakeValuesService();
        context = new FakerContext(new Locale("en"), randomService);
    }

    private String invokePrivateBothify(String input, FakerContext context, boolean isUpper, boolean numerify, boolean letterify) {
        try {
            Method method = FakeValuesService.class.getDeclaredMethod("bothify", String.class, FakerContext.class, boolean.class, boolean.class, boolean.class);
            method.setAccessible(true);
            return (String) method.invoke(fakeValuesService, input, context, isUpper, numerify, letterify);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // Rethrow the cause if it's a NullPointerException
            if (e.getCause() instanceof NullPointerException) {
                throw (NullPointerException) e.getCause();
            }
            throw new RuntimeException(e);
        }
    }

    @Test
    void testBothifyWithNumerify() {
        // Mock behavior
        when(randomService.nextInt(anyInt())).thenReturn(0);
        String input = "My phone number is ###-###-####";
        String result = invokePrivateBothify(input, context, false, true, false);
        assertNotNull(result);
        // Add expected output
        assertEquals("My phone number is 000-000-0000", result);
    }

    @Test
    void testBothifyWithLetterify() {
        // Mock behavior
        when(randomService.nextInt(anyInt())).thenReturn(0);
        String input = "Your code is ???";
        String result = invokePrivateBothify(input, context, true, false, true);
        assertNotNull(result);
        // Add expected output
        assertEquals("Your code is AAA", result);
    }

    @Test
    void testBothifyWithAllFlagsTrue() {
        // Test bothify with input containing '#' and '?' with isUpper, numerify, and letterify set to true.
        // Mock behavior for digit replacement
        when(randomService.nextInt(10)).thenReturn(1);
        // Mock behavior for letter replacement
        when(randomService.nextInt(26)).thenReturn(0);
        String input = "Hello #?";
        String result = invokePrivateBothify(input, context, true, true, true);
        // Expected output with uppercase letters and digits replacing # and ?
        assertEquals("Hello 1A", result);
    }

    @Test
    void testBothifyWithLowerCaseAndAllFlagsTrue() {
        // Test bothify with input containing '#' and '?' with isUpper set to false, and numerify and letterify set to true.
        // Mock behavior for digit replacement
        when(randomService.nextInt(10)).thenReturn(2);
        // Mock behavior for letter replacement
        when(randomService.nextInt(26)).thenReturn(1);
        String input = "Hello #?";
        String result = invokePrivateBothify(input, context, false, true, true);
        // Expected output with lowercase letters and digits replacing # and ?
        assertEquals("Hello 2b", result);
    }

    @Test
    void testBothifyWithUpperCaseAndLetterifyOnly() {
        // Test bothify with input containing '#' and '?' with isUpper set to true, numerify set to false, and letterify set to true.
        // Mock behavior for letter replacement
        when(randomService.nextInt(26)).thenReturn(3);
        String input = "Hello #?";
        String result = invokePrivateBothify(input, context, true, false, true);
        // Expected output with uppercase letters and letters replacing # and ?
        assertEquals("Hello D", result);
    }

    @Test
    void testBothifyWithUpperCaseAndNumerifyOnly() {
        // Test bothify with input containing '#' and '?' with isUpper and numerify set to true, and letterify set to false.
        // Mock behavior for digit replacement
        when(randomService.nextInt(10)).thenReturn(4);
        String input = "Hello #?";
        String result = invokePrivateBothify(input, context, true, true, false);
        // Expected output with uppercase letters and digits replacing # and letters remaining unchanged
        assertEquals("Hello 4?", result);
    }

    @Test
    void testBothifyWithLowerCaseAndLetterifyOnly() {
        // Test bothify with input containing '#' and '?' with isUpper set to false, numerify set to false, and letterify set to true.
        // Mock behavior for letter replacement
        when(randomService.nextInt(26)).thenReturn(5);
        String input = "Hello #?";
        String result = invokePrivateBothify(input, context, false, false, true);
        // Expected output with lowercase letters and letters replacing # and ?
        assertEquals("Hello f", result);
    }

    @Test
    void testBothifyWithNoReplacements() {
        // Test bothify with input containing '#' and '?' with all boolean flags set to false.
        String input = "Hello #?";
        String result = invokePrivateBothify(input, context, false, false, false);
        // Expected output with letters remaining unchanged and no replacements
        assertEquals("Hello #?", result);
    }

    @Test
    void testBothifyWithOnlyDigits() {
        // Test bothify with input consisting of three '#' characters with numerify set to true and letterify set to false.
        // Mock behavior for digit replacement
        when(randomService.nextInt(10)).thenReturn(6);
        String input = "###";
        String result = invokePrivateBothify(input, context, false, true, false);
        // Expected output with digits replacing all '#' characters
        assertEquals("666", result);
    }

    @Test
    void testBothifyWithEmptyInput() {
        // Test bothify with an empty input string, expecting an empty output.
        String input = "";
        String result = invokePrivateBothify(input, context, false, false, false);
        // Expected output should be empty
        assertEquals("", result);
    }

    @Test
    void testBothifyWithNoPlaceholders() {
        // Test bothify with input containing no placeholders, expecting the same string as output.
        String input = "No replacements here";
        String result = invokePrivateBothify(input, context, false, false, false);
        // Expected output should be unchanged
        assertEquals("No replacements here", result);
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
}
```