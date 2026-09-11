package net.datafaker.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Locale;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        context = new FakerContext(new Locale("test"), randomService);
        // Setup mock behavior
        when(randomService.nextInt(anyInt())).thenReturn(0);
        // For '?'
        when(randomService.nextInt(1, 9)).thenReturn(1);
    }

    // Helper method to invoke the private bothify method using reflection
    private String invokeBothify(String input, FakerContext context, boolean isUpper, boolean numerify, boolean letterify) {
        try {
            Method method = FakeValuesService.class.getDeclaredMethod("bothify", String.class, FakerContext.class, boolean.class, boolean.class, boolean.class);
            method.setAccessible(true);
            return (String) method.invoke(fakeValuesService, input, context, isUpper, numerify, letterify);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Reflection failed", e);
        }
    }

    @Test
    void testBothifyWithUpperCase() {
        String input = "Hello #?";
        // Assuming the first character is replaced
        String expectedOutput = "Hello 0A";
        String result = invokeBothify(input, context, true, true, true);
        assertEquals(expectedOutput, result);
    }

    @Test
    void testBothifyWithLowerCase() {
        String input = "Hello #?";
        // Assuming the first character is replaced
        String expectedOutput = "Hello 0a";
        String result = invokeBothify(input, context, false, true, true);
        assertEquals(expectedOutput, result);
    }

    @Test
    void testBothifyWithNumerify() {
        String input = "My number is #";
        // Assuming the first character is replaced
        String expectedOutput = "My number is 0";
        String result = invokeBothify(input, context, false, true, false);
        assertEquals(expectedOutput, result);
    }

    @Test
    void testBothifyWithLetterify() {
        String input = "My char is ?";
        // Assuming the first character is replaced
        String expectedOutput = "My char is A";
        String result = invokeBothify(input, context, false, false, true);
        assertEquals(expectedOutput, result);
    }

    @Test
    void testBothifyWithAllFlagsSetToTrue() {
        // Test case to verify that bothify replaces '#' with a digit and '?' with an uppercase letter.
        String input = "Hello #?";
        // Assuming the first character is replaced
        String expectedOutput = "Hello 0A";
        String result = invokeBothify(input, context, true, true, true);
        assertEquals(expectedOutput, result);
    }

    @Test
    void testBothifyWithOnlyNumerifySetToTrue() {
        // Test case to check that bothify replaces '#' with a digit and leaves '?' unchanged when only numerify is true.
        String input = "Hello #?";
        // Assuming '#' is replaced with '0' and '?' remains unchanged
        String expectedOutput = "Hello 0?";
        String result = invokeBothify(input, context, false, true, false);
        assertEquals(expectedOutput, result);
    }

    @Test
    void testBothifyWithOnlyLetterifySetToTrue() {
        // Test case to verify that bothify replaces '?' with a lowercase letter and leaves '#' unchanged when only letterify is true.
        String input = "Hello #?";
        // Assuming '?' is replaced with 'a' and '#' remains unchanged
        String expectedOutput = "Hello #a";
        String result = invokeBothify(input, context, false, false, true);
        assertEquals(expectedOutput, result);
    }

    @Test
    void testBothifyWithNoReplacements() {
        // Test case to ensure that bothify returns the input unchanged when all flags are false.
        String input = "Hello World";
        String expectedOutput = "Hello World";
        String result = invokeBothify(input, context, false, false, false);
        assertEquals(expectedOutput, result);
    }

    @Test
    void testBothifyWithUpperCaseSetting() {
        // Test case to verify that bothify replaces '?' with an uppercase letter when isUpper is true.
        String input = "Hello ?";
        // Assuming '?' is replaced with 'A'
        String expectedOutput = "Hello A";
        String result = invokeBothify(input, context, true, false, true);
        assertEquals(expectedOutput, result);
    }

    @Test
    void testBothifyWithNumerifyOnQuestionMark() {
        // Test case to ensure that bothify replaces '?' with a digit when numerify is true.
        String input = "Your code is ?";
        // Assuming '?' is replaced with '1'
        String expectedOutput = "Your code is 1";
        String result = invokeBothify(input, context, false, true, false);
        assertEquals(expectedOutput, result);
    }

    @Test
    void testBothifyWithOnlyLetterifySetToTrueAndUpperCase() {
        // Test case to verify that bothify replaces '?' with an uppercase letter when isUpper is true and letterify is also true.
        String input = "Hello ?";
        // Assuming '?' is replaced with 'A'
        String expectedOutput = "Hello A";
        String result = invokeBothify(input, context, true, false, true);
        assertEquals(expectedOutput, result);
    }

    @Test
    void testBothifyWithOnlyNumerifySetToTrueAndUpperCase() {
        // Test case to verify that bothify replaces '#' with a digit and '?' with an uppercase letter when both numerify and isUpper are true.
        String input = "Code #?";
        // Assuming '#' is replaced with '0' and '?' with 'A'
        String expectedOutput = "Code 0A";
        String result = invokeBothify(input, context, true, true, false);
        assertEquals(expectedOutput, result);
    }

    @Test
    void testBothifyWithOnlyNumerifySetToTrueAndLetterifySetToTrue() {
        // Test case to check that bothify replaces '#' with a digit and '?' with a lowercase letter when both numerify and letterify are true.
        String input = "#?";
        // Assuming '#' is replaced with '0' and '?' is replaced with 'a'
        String expectedOutput = "0a";
        String result = invokeBothify(input, context, false, true, true);
        assertEquals(expectedOutput, result);
    }

    @Test
    void testBothifyWithBoundaryInput() {
        // Test case to ensure that bothify returns an empty string when the input is empty.
        String input = "";
        String expectedOutput = "";
        String result = invokeBothify(input, context, false, false, false);
        assertEquals(expectedOutput, result);
        // Test case to verify that bothify replaces all '#' with digits when numerify is true.
        input = "###";
        // Assuming all '#' are replaced with '0'
        expectedOutput = "000";
        String resultWithDigits = invokeBothify(input, context, false, true, false);
        assertEquals(expectedOutput, resultWithDigits);
    }

    @Test
    void testBothifyExceptionScenarios() {
        // Test case to verify that bothify throws a NullPointerException when the input string is null.
        assertThrows(NullPointerException.class, () -> {
            invokeBothify(null, context, false, false, false);
        });
        // Test case to verify that bothify throws a NullPointerException when the FakerContext is null.
        assertThrows(NullPointerException.class, () -> {
            invokeBothify("Hello #?", null, false, false, false);
        });
        // Test case to verify that bothify throws an IllegalArgumentException when the input string contains invalid characters (if applicable).
        // Assuming there's a check for invalid characters in the actual implementation
        String input = "Hello @?";
        assertThrows(IllegalArgumentException.class, () -> {
            invokeBothify(input, context, false, false, true);
        });
    }
}
