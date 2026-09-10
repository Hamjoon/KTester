package org.apache.commons.cli;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PatternOptionBuilder_getValueType_Test {

    @BeforeAll
    static void setupBeforeAll() {
        // This method can be used to register types or perform any setup needed before all tests.
        PatternOptionBuilder.registerTypes();
    }

    private static Object[][] provideValidCharacterCases() {
        return new Object[][] { { '@', PatternOptionBuilder.OBJECT_VALUE, "Expected OBJECT_VALUE for input '@'" }, { ':', PatternOptionBuilder.STRING_VALUE, "Expected STRING_VALUE for input ':'" }, { '%', PatternOptionBuilder.NUMBER_VALUE, "Expected NUMBER_VALUE for input '%'" }, { '+', PatternOptionBuilder.CLASS_VALUE, "Expected CLASS_VALUE for input '+'" }, { '#', PatternOptionBuilder.DATE_VALUE, "Expected DATE_VALUE for input '#'" }, { '<', PatternOptionBuilder.EXISTING_FILE_VALUE, "Expected EXISTING_FILE_VALUE for input '<'" }, { '>', PatternOptionBuilder.FILE_VALUE, "Expected FILE_VALUE for input '>'" }, { '*', PatternOptionBuilder.FILES_VALUE, "Expected FILES_VALUE for input '*'" }, { '/', PatternOptionBuilder.URL_VALUE, "Expected URL_VALUE for input '/'" } };
    }

    private static Object[][] provideInvalidCharacterCases() {
        return new Object[][] { { 'A', "Expected null for unknown input 'A'" }, { ' ', "Expected null for space character input" }, { '9', "Expected null for numeric character '9'" }, { '!', "Expected null for invalid character '!'" }, { '\0', "Expected null for empty character input" } };
    }

    @Test
    void testGetValueType() {
        // Given: a character representing a type
        // Example input for the test
        char charInput = '@';
        // When: calling getValueType method
        Class<?> result = PatternOptionBuilder.getValueType(charInput);
        // Then: verify the expected result
        assertEquals(PatternOptionBuilder.OBJECT_VALUE, result, "Expected OBJECT_VALUE for input '@'");
    }

    @Test
    void testGetValueTypeForString() {
        // Given: a character representing a string type
        // Example input for the test
        char charInput = ':';
        // When: calling getValueType method
        Class<?> result = PatternOptionBuilder.getValueType(charInput);
        // Then: verify the expected result
        assertEquals(PatternOptionBuilder.STRING_VALUE, result, "Expected STRING_VALUE for input ':'");
    }

    @Test
    void testGetValueTypeForNumber() {
        // Given: a character representing a number type
        // Example input for the test
        char charInput = '%';
        // When: calling getValueType method
        Class<?> result = PatternOptionBuilder.getValueType(charInput);
        // Then: verify the expected result
        assertEquals(PatternOptionBuilder.NUMBER_VALUE, result, "Expected NUMBER_VALUE for input '%'");
    }

    @Test
    void testGetValueTypeForClass() {
        // Given: a character representing a class type
        // Example input for the test
        char charInput = '+';
        // When: calling getValueType method
        Class<?> result = PatternOptionBuilder.getValueType(charInput);
        // Then: verify the expected result
        assertEquals(PatternOptionBuilder.CLASS_VALUE, result, "Expected CLASS_VALUE for input '+'");
    }

    @Test
    void testGetValueTypeForDate() {
        // Given: a character representing a date type
        // Example input for the test
        char charInput = '#';
        // When: calling getValueType method
        Class<?> result = PatternOptionBuilder.getValueType(charInput);
        // Then: verify the expected result
        assertEquals(PatternOptionBuilder.DATE_VALUE, result, "Expected DATE_VALUE for input '#'");
    }

    @Test
    void testGetValueTypeForExistingFile() {
        // Given: a character representing an existing file type
        // Example input for the test
        char charInput = '<';
        // When: calling getValueType method
        Class<?> result = PatternOptionBuilder.getValueType(charInput);
        // Then: verify the expected result
        assertEquals(PatternOptionBuilder.EXISTING_FILE_VALUE, result, "Expected EXISTING_FILE_VALUE for input '<'");
    }

    @Test
    void testGetValueTypeForFile() {
        // Given: a character representing a file type
        // Example input for the test
        char charInput = '>';
        // When: calling getValueType method
        Class<?> result = PatternOptionBuilder.getValueType(charInput);
        // Then: verify the expected result
        assertEquals(PatternOptionBuilder.FILE_VALUE, result, "Expected FILE_VALUE for input '>'");
    }

    @Test
    void testGetValueTypeForFiles() {
        // Given: a character representing multiple files type
        // Example input for the test
        char charInput = '*';
        // When: calling getValueType method
        Class<?> result = PatternOptionBuilder.getValueType(charInput);
        // Then: verify the expected result
        assertEquals(PatternOptionBuilder.FILES_VALUE, result, "Expected FILES_VALUE for input '*'");
    }

    @Test
    void testGetValueTypeForURL() {
        // Given: a character representing a URL type
        // Example input for the test
        char charInput = '/';
        // When: calling getValueType method
        Class<?> result = PatternOptionBuilder.getValueType(charInput);
        // Then: verify the expected result
        assertEquals(PatternOptionBuilder.URL_VALUE, result, "Expected URL_VALUE for input '/'");
    }

    @Test
    void testGetValueTypeForUnknownCharacter() {
        // Given: a character that does not represent a known type
        // Example input for the test
        char charInput = 'x';
        // When: calling getValueType method
        Class<?> result = PatternOptionBuilder.getValueType(charInput);
        // Then: verify the result is null
        assertNull(result, "Expected null for unknown input 'x'");
    }

    @ParameterizedTest
    @MethodSource("provideValidCharacterCases")
    void testGetValueTypeForValidCharacters(char charInput, Class<?> expectedType, String description) {
        // When: calling getValueType method
        Class<?> result = PatternOptionBuilder.getValueType(charInput);
        // Then: verify the expected result
        assertEquals(expectedType, result, description);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidCharacterCases")
    void testGetValueTypeForInvalidCharacters(char charInput, String description) {
        // When: calling getValueType method
        Class<?> result = PatternOptionBuilder.getValueType(charInput);
        // Then: verify the result is null
        assertNull(result, description);
    }
}
