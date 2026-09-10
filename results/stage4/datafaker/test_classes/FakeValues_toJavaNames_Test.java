package net.datafaker.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Locale;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@ExtendWith(MockitoExtension.class)
class FakeValues_toJavaNames_Test {

    private FakeValues fakeValues;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize FakeValues with a default Locale for testing
        fakeValues = new FakeValues(Locale.ENGLISH, "address.yml", "address");
    }

    private String invokeToJavaNames(String input, boolean isMethod) {
        try {
            var method = FakeValues.class.getDeclaredMethod("toJavaNames", String.class, boolean.class);
            method.setAccessible(true);
            return (String) method.invoke(fakeValues, input, isMethod);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static Stream<Arguments> toJavaNamesProvider() {
        return Stream.of(// Test with null string input, should return null.
        Arguments.of(null, false, null), // Test with empty string input, should return empty string.
        Arguments.of("", false, ""), // Test with standard camel case string.
        Arguments.of("helloWorld", false, "HelloWorld"), // Test with snake case string.
        Arguments.of("hello_world", false, "HelloWorld"), // Test with snake case string and isMethod true.
        Arguments.of("hello_world", true, "helloWorld"), // Test with already formatted camel case string.
        Arguments.of("HelloWorld", false, "HelloWorld"), // Test with multiple underscores in snake case.
        Arguments.of("hello_world_test", false, "HelloWorldTest"), // Test with multiple underscores in snake case and isMethod true.
        Arguments.of("hello_world_test", true, "helloWorldTest"), // Test with leading underscore.
        Arguments.of("_leadingUnderscore", false, "LeadingUnderscore"), // Test with leading underscore and isMethod true.
        Arguments.of("_leadingUnderscore", true, "leadingUnderscore"), // Test with already formatted camel case string and isMethod true.
        Arguments.of("alreadyCamelCase", true, "alreadyCamelCase"), // Test with a single word input.
        Arguments.of("singleword", false, "Singleword"), // Test with all uppercase letters in snake case.
        Arguments.of("SINGLE_WORD", false, "SingleWord"), // Test with multiple words in snake case.
        Arguments.of("another_example_here", false, "AnotherExampleHere"), // Test with multiple words in snake case and isMethod true.
        Arguments.of("another_example_here", true, "anotherExampleHere"), // Test with string containing numbers.
        Arguments.of("withNumbers123", false, "WithNumbers123"), // Test with snake case containing numbers.
        Arguments.of("with_numbers_123", false, "WithNumbers123"), // Test with snake case containing numbers and isMethod true.
        Arguments.of("with_numbers_123", true, "withNumbers123"), // Test with a single character input.
        Arguments.of("a", false, "A"), // Test with a single character input and isMethod true.
        Arguments.of("a", true, "a"), // Test with mixed case and snake case.
        Arguments.of("mixed_Case_Example", false, "MixedCaseExample"), // Test with mixed case and snake case and isMethod true.
        Arguments.of("mixed_Case_Example", true, "mixedCaseExample"));
    }

    // Parameterized test to validate the toJavaNames method
    @ParameterizedTest
    @MethodSource("toJavaNamesProvider")
    void testToJavaNames(String input, boolean isMethod, String expected) {
        // Using reflection to access the private method
        String result = invokeToJavaNames(input, isMethod);
        assertEquals(expected, result);
    }
}
