package net.datafaker.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Locale;
import net.datafaker.internal.helper.SingletonLocale;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@ExtendWith(MockitoExtension.class)
class FakeValuesService_javaNameToYamlName_Test {

    private FakeValuesService fakeValuesService;

    @BeforeEach
    void setupBeforeEach() {
        fakeValuesService = new FakeValuesService();
    }

    // Utility method to invoke the private method
    private String invokeJavaNameToYamlName(String expression) {
        try {
            Method method = FakeValuesService.class.getDeclaredMethod("javaNameToYamlName", String.class);
            method.setAccessible(true);
            return (String) method.invoke(fakeValuesService, expression);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testJavaNameToYamlName_WithUpperCaseLetters() {
        // Given
        String expression = "TestExpression";
        // When
        String result = invokeJavaNameToYamlName(expression);
        // Then
        assertEquals("test_expression", result);
    }

    @Test
    void testJavaNameToYamlName_AllLowerCase() {
        // Given
        String expression = "testexpression";
        // When
        String result = invokeJavaNameToYamlName(expression);
        // Then
        assertEquals("testexpression", result);
    }

    @Test
    void testJavaNameToYamlName_SingleUpperCase() {
        // Given
        String expression = "T";
        // When
        String result = invokeJavaNameToYamlName(expression);
        // Then
        assertEquals("t", result);
    }

    @Test
    void testJavaNameToYamlName_EmptyString() {
        // Given
        String expression = "";
        // When
        String result = invokeJavaNameToYamlName(expression);
        // Then
        assertEquals("", result);
    }

    @Test
    void testJavaNameToYamlName_NullInput() {
        // Given
        String expression = null;
        // When / Then
        assertThrows(NullPointerException.class, () -> invokeJavaNameToYamlName(expression));
    }

    @Test
    void testJavaNameToYamlName_AlreadyYamlFormat() {
        // Given
        String expression = "already_yaml";
        // When
        String result = invokeJavaNameToYamlName(expression);
        // Then
        assertEquals("already_yaml", result);
    }

    @Test
    void testJavaNameToYamlName_CamelCase() {
        // Given
        String expression = "simpleTest";
        // When
        String result = invokeJavaNameToYamlName(expression);
        // Then
        assertEquals("simple_test", result);
    }

    @Test
    void testJavaNameToYamlName_MultipleUpperCase() {
        // Given
        String expression = "XMLSimple";
        // When
        String result = invokeJavaNameToYamlName(expression);
        // Then
        assertEquals("x_m_l_simple", result);
    }

    @Test
    void testJavaNameToYamlName_MultipleConsecutiveUpperCase() {
        // Given
        String expression = "SimpleXML";
        // When
        String result = invokeJavaNameToYamlName(expression);
        // Then
        assertEquals("simple_x_m_l", result);
    }
}
