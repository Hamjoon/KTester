package net.datafaker.service;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Locale;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class FakeValues_toJavaNames_Test {

    private FakeValues fakeValues;

    class FakeValues {

        private Locale locale;

        private String resource;

        private String key;

        public FakeValues(Locale locale, String resource, String key) {
            this.locale = locale;
            this.resource = resource;
            this.key = key;
        }

        private static String toJavaNames(String input, boolean isMethod) {
            if (input == null || input.isEmpty()) {
                return input;
            }
            StringBuilder result = new StringBuilder();
            boolean capitalizeNext = !isMethod;
            for (int i = 0; i < input.length(); i++) {
                char currentChar = input.charAt(i);
                if (currentChar == '_') {
                    capitalizeNext = true;
                } else {
                    if (capitalizeNext) {
                        result.append(Character.toUpperCase(currentChar));
                        capitalizeNext = false;
                    } else {
                        result.append(isMethod ? Character.toLowerCase(currentChar) : currentChar);
                    }
                }
            }
            return result.toString();
        }
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the FakeValues instance before each test
        fakeValues = new FakeValues(Locale.ENGLISH, "address.yml", "address");
    }

    private String invokeToJavaNames(String string, boolean isMethod) {
        // This helper method is used to invoke the private static method toJavaNames
        // using reflection, since it is not directly accessible.
        try {
            java.lang.reflect.Method method = FakeValues.class.getDeclaredMethod("toJavaNames", String.class, boolean.class);
            method.setAccessible(true);
            return (String) method.invoke(null, string, isMethod);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testToJavaNames_WithNullString() {
        String result = invokeToJavaNames(null, true);
        assertNull(result, "Expected null when input string is null");
    }

    @Test
    void testToJavaNames_WithEmptyString() {
        String result = invokeToJavaNames("", true);
        assertEquals("", result, "Expected empty string when input string is empty");
    }

    @Test
    void testToJavaNames_WithUnderscoreAndMethodFlagTrue() {
        String result = invokeToJavaNames("test_value", true);
        assertEquals("testValue", result, "Expected camel case conversion with method flag true");
    }

    @Test
    void testToJavaNames_WithUnderscoreAndMethodFlagFalse() {
        String result = invokeToJavaNames("test_value", false);
        assertEquals("TestValue", result, "Expected Pascal case conversion with method flag false");
    }

    @Test
    void testToJavaNames_WithNoUnderscoreAndMethodFlagTrue() {
        String result = invokeToJavaNames("testValue", true);
        assertEquals("testValue", result, "Expected no change with method flag true and no underscores");
    }

    @Test
    void testToJavaNames_WithNoUnderscoreAndMethodFlagFalse() {
        String result = invokeToJavaNames("TestValue", false);
        assertEquals("TestValue", result, "Expected no change with method flag false and no underscores");
    }

    @Test
    void testToJavaNames_WithLeadingUnderscoreAndMethodFlagTrue() {
        String result = invokeToJavaNames("_leading_underscore", true);
        assertEquals("LeadingUnderscore", result, "Expected leading underscore to be ignored and capitalize next letter");
    }

    @Test
    void testToJavaNames_WithTrailingUnderscoreAndMethodFlagFalse() {
        String result = invokeToJavaNames("trailing_underscore_", false);
        assertEquals("TrailingUnderscore", result, "Expected trailing underscore to be ignored");
    }

    @Test
    void testToJavaNames_WithMultipleConsecutiveUnderscoresAndMethodFlagTrue() {
        String result = invokeToJavaNames("MULTIPLE__UNDERSCORES", true);
        assertEquals("multipleUnderscores", result, "Expected multiple underscores to be ignored in camelCase conversion");
    }

    @Test
    void testToJavaNames_WithAllUppercaseAndMethodFlagTrue() {
        String result = invokeToJavaNames("ALLCAPITALS", true);
        assertEquals("allcapitals", result, "Expected first letter to be lowercase with all uppercase input");
    }

    @Test
    void testToJavaNames_WithAllLowercaseAndMethodFlagFalse() {
        String result = invokeToJavaNames("all_lowercase", false);
        assertEquals("AllLowercase", result, "Expected first letter to be uppercase and camelCase the rest");
    }

    @Test
    void testToJavaNames_WithMixedCaseAndUnderscoresAndMethodFlagTrue() {
        String result = invokeToJavaNames("Mixed_Case_With_Underscores", true);
        assertEquals("mixedCaseWithUnderscores", result, "Expected camelCase conversion with lowercase first letter");
    }

    @Test
    void testToJavaNames_WithSingleWordAndMethodFlagFalse() {
        String result = invokeToJavaNames("single", false);
        assertEquals("Single", result, "Expected single word to have first letter capitalized");
    }

    @Test
    void testToJavaNames_WithSingleWordAndMethodFlagTrue() {
        String result = invokeToJavaNames("single", true);
        assertEquals("single", result, "Expected single word to remain unchanged");
    }

    @Test
    void testToJavaNames_WithUnderscoreAtEndAndMethodFlagTrue() {
        String result = invokeToJavaNames("underscore_at_end_", true);
        assertEquals("underscoreAtEnd", result, "Expected trailing underscore to be ignored and camelCase the rest");
    }

    @Test
    void testToJavaNames_WithUnderscoreAtStartAndMethodFlagFalse() {
        String result = invokeToJavaNames("_underscore_at_start", false);
        assertEquals("UnderscoreAtStart", result, "Expected leading underscore to be ignored and capitalize next letter");
    }

    @Test
    void testToJavaNames_WithSingleCharacterAndMethodFlagTrue() {
        String result = invokeToJavaNames("a", true);
        assertEquals("a", result, "Expected single character to remain unchanged");
    }

    @Test
    void testToJavaNames_WithSingleUppercaseCharacterAndMethodFlagFalse() {
        String result = invokeToJavaNames("A", false);
        assertEquals("A", result, "Expected single uppercase character to remain unchanged");
    }
}
