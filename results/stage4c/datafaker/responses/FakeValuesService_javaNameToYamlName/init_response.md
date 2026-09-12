```java
package net.datafaker.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;
import net.datafaker.internal.helper.SingletonLocale;

@ExtendWith(MockitoExtension.class)
class FakeValuesService_javaNameToYamlName_Test {

    private FakeValuesService fakeValuesService;

    @BeforeAll
    static void setupBeforeAll() {
        // Any necessary setup before all tests
    }

    @BeforeEach
    void setupBeforeEach() {
        fakeValuesService = new FakeValuesService();
    }

    @AfterEach
    void teardownAfterEach() {
        // Any necessary cleanup after each test
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any necessary cleanup after all tests
    }

    @Test
    void testJavaNameToYamlName_WithUpperCaseLetters() {
        // Given
        String expression = "TestExpression";

        // When
        String result = fakeValuesService.javaNameToYamlName(expression);

        // Then
        assertEquals("test_expression", result);
    }

    @Test
    void testJavaNameToYamlName_AllLowerCase() {
        // Given
        String expression = "testexpression";

        // When
        String result = fakeValuesService.javaNameToYamlName(expression);

        // Then
        assertEquals("testexpression", result);
    }

    @Test
    void testJavaNameToYamlName_SingleUpperCase() {
        // Given
        String expression = "T";

        // When
        String result = fakeValuesService.javaNameToYamlName(expression);

        // Then
        assertEquals("t", result);
    }

    @Test
    void testJavaNameToYamlName_EmptyString() {
        // Given
        String expression = "";

        // When
        String result = fakeValuesService.javaNameToYamlName(expression);

        // Then
        assertEquals("", result);
    }
}
```