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

    @Mock
    private RandomService randomService;

    private FakeValuesService fakeValuesService;

    private FakerContext context;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the context with a mocked RandomService and a test Locale
        context = new FakerContext(new Locale("test"), randomService);
        fakeValuesService = new FakeValuesService();
        // Mock the RandomService to return predictable values
        when(randomService.nextInt(anyInt())).thenReturn(0);
    }

    // Helper method to invoke the private bothify method using reflection
    private String invokeBothify(String input, FakerContext context, boolean isUpper, boolean numerify, boolean letterify) {
        try {
            Method bothifyMethod = FakeValuesService.class.getDeclaredMethod("bothify", String.class, FakerContext.class, boolean.class, boolean.class, boolean.class);
            bothifyMethod.setAccessible(true);
            return (String) bothifyMethod.invoke(fakeValuesService, input, context, isUpper, numerify, letterify);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testBothifyWithNumerify() {
        String input = "###";
        String result = invokeBothify(input, context, false, true, false);
        assertEquals("000", result);
    }

    @Test
    void testBothifyWithLetterify() {
        String input = "???";
        String result = invokeBothify(input, context, false, false, true);
        // Assuming 'a' is returned by the mocked RandomService
        assertEquals("aaa", result);
    }

    @Test
    void testBothifyWithUppercase() {
        String input = "???";
        String result = invokeBothify(input, context, true, false, true);
        // Assuming 'A' is returned by the mocked RandomService
        assertEquals("AAA", result);
    }

    @Test
    void testBothifyWithMixedNumerifyAndLetterify() {
        // Test with mixed '#' and '?' characters, numerify and letterify enabled, lowercase letters.
        String input = "###???";
        // For digits
        when(randomService.nextInt(10)).thenReturn(0);
        // For letters ('a')
        when(randomService.nextInt(26)).thenReturn(0);
        String result = invokeBothify(input, context, false, true, true);
        assertEquals("000aaa", result);
    }

    @Test
    void testBothifyWithMixedNumerifyAndLetterifyUppercase() {
        // Test with mixed '#' and '?' characters, numerify and letterify enabled, uppercase letters.
        String input = "###???";
        // For digits
        when(randomService.nextInt(10)).thenReturn(0);
        // For letters ('A')
        when(randomService.nextInt(26)).thenReturn(0);
        String result = invokeBothify(input, context, true, true, true);
        assertEquals("000AAA", result);
    }

    @Test
    void testBothifyWithOnlyNumerify() {
        // Test with only '#' characters, numerify enabled, no letterify.
        String input = "###";
        // For digits
        when(randomService.nextInt(10)).thenReturn(5);
        String result = invokeBothify(input, context, false, true, false);
        assertEquals("555", result);
    }

    @Test
    void testBothifyWithOnlyLetterify() {
        // Test with mixed '#' and '?' characters, only letterify enabled, no digit conversion.
        String input = "###???";
        // For letters ('a')
        when(randomService.nextInt(26)).thenReturn(0);
        String result = invokeBothify(input, context, false, false, true);
        assertEquals("###aaa", result);
    }

    @Test
    void testBothifyWithNoNumerifyOrLetterify() {
        // Test with mixed '#' and '?' characters, neither numerify nor letterify enabled, should return input unchanged.
        String input = "###???";
        String result = invokeBothify(input, context, false, false, false);
        assertEquals("###???", result);
    }

    @Test
    void testBothifyWithAlternatingCharacters() {
        // Test with alternating '#' and '?' characters, numerify and letterify enabled, lowercase letters.
        String input = "##??##";
        // For digits
        when(randomService.nextInt(10)).thenReturn(9);
        // For letters ('z')
        when(randomService.nextInt(26)).thenReturn(25);
        String result = invokeBothify(input, context, false, true, true);
        assertEquals("99zz99", result);
    }

    @Test
    void testBothifyWithOnlyLetterifyUppercase() {
        // Test with only '?' characters, letterify enabled with uppercase, no numerify.
        String input = "?????";
        // For letters ('M')
        when(randomService.nextInt(26)).thenReturn(12);
        String result = invokeBothify(input, context, true, false, true);
        assertEquals("MMMMM", result);
    }

//     @Test
//     void testBothifyWithOnlyNumerify() {
//         // Test with only '#' characters, numerify enabled, no letterify.
//         String input = "###";
//         // For digits
//         when(randomService.nextInt(10)).thenReturn(5);
//         String result = invokeBothify(input, context, false, true, false);
//         assertEquals("555", result);
//     }

    @Test
    void testBothifyWithNonPlaceholderCharacters() {
        // Test with non-placeholder characters mixed with '?', only letterify enabled, lowercase letters.
        String input = "abc???xyz";
        // For letters ('k')
        when(randomService.nextInt(26)).thenReturn(10);
        String result = invokeBothify(input, context, false, false, true);
        assertEquals("abckkkxyz", result);
    }

    @Test
    void testBothifyWithEmptyInput() {
        // Test with empty input string, neither numerify nor letterify enabled, should return empty string.
        String input = "";
        String result = invokeBothify(input, context, false, false, false);
        assertEquals("", result);
    }

    @Test
    void testBothifyWithNullInput() {
        // Test with null input string to ensure method handles null values appropriately.
        assertThrows(NullPointerException.class, () -> {
            invokeBothify(null, context, false, false, false);
        });
    }

    @Test
    void testBothifyWithNullContext() {
        // Test with null context to ensure method handles null context appropriately.
        String input = "###???";
        assertThrows(NullPointerException.class, () -> {
            invokeBothify(input, null, false, true, true);
        });
    }

    @Test
    void testBothifyWithRandomServiceException() {
        // Test with context's RandomService throwing an exception to ensure method handles exceptions from RandomService.
        String input = "###???";
        when(randomService.nextInt(anyInt())).thenThrow(new RuntimeException("RandomService exception"));
        assertThrows(RuntimeException.class, () -> {
            invokeBothify(input, context, false, true, true);
        });
    }

    @Test
    void testBothifyWithOnlyNumerifyMixed() {
        // Test with mixed '#' and '?' characters, only numerify enabled, no letter conversion.
        String input = "###???";
        // For digits
        when(randomService.nextInt(10)).thenReturn(0);
        String result = invokeBothify(input, context, false, true, false);
        assertEquals("000???", result);
    }

    @Test
    void testBothifyWithOnlyNumerifyDifferent() {
        // Test with only '#' characters, numerify enabled, no letterify.
        String input = "###";
        // For digits
        when(randomService.nextInt(10)).thenReturn(5);
        String result = invokeBothify(input, context, false, true, false);
        assertEquals("555", result);
    }
}