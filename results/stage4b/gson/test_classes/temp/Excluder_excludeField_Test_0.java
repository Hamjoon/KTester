package com.google.gson.internal;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Field;

// Add other imports if necessary
@ExtendWith(MockitoExtension.class)
class Excluder_excludeField_Test {

    private Excluder excluder;

    static class TestClass {

        @Expose
        public String exposedField;

        public transient String transientField;

        @Since(1.0)
        public String fieldWithSince;

        @Until(1.0)
        public String fieldWithUntil;

        // Assume this is synthetic for the test
        public String syntheticField;

        @Expose(serialize = false)
        public String exposeNotAllowingSerialization;

        // Assume this is an inner class field
        public String innerClassField;

        // Assume this is a static nested class field
        public String staticNestedClassField;

        // Assume this is an anonymous class field
        public String anonymousClassField;
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Excluder instance before each test
        excluder = new Excluder();
    }

    @Test
    void testExcludeField_withTransientModifier() throws NoSuchFieldException {
        // Given
        Field field = TestClass.class.getDeclaredField("transientField");
        boolean serialize = true;
        // When
        boolean result = excluder.excludeField(field, serialize);
        // Then
        assertTrue(result, "Field with transient modifier should be excluded");
    }

    @Test
    void testExcludeField_withExposeAnnotation() throws NoSuchFieldException {
        // Given
        Field field = TestClass.class.getDeclaredField("exposedField");
        boolean serialize = true;
        // When
        boolean result = excluder.excludeField(field, serialize);
        // Then
        assertFalse(result, "Field with Expose annotation should not be excluded");
    }

    @Test
    void testExcludeField_withVersionCheck() throws NoSuchFieldException {
        // Given
        Field fieldWithSince = TestClass.class.getDeclaredField("fieldWithSince");
        Field fieldWithUntil = TestClass.class.getDeclaredField("fieldWithUntil");
        boolean serialize = true;
        // When
        boolean resultSince = excluder.excludeField(fieldWithSince, serialize);
        boolean resultUntil = excluder.excludeField(fieldWithUntil, serialize);
        // Then
        assertFalse(resultSince, "Field with Since annotation should not be excluded if version is not ignored");
        assertTrue(resultUntil, "Field with Until annotation should be excluded if version is not ignored");
    }

    @Test
    void testExcludeField_withSyntheticField() throws NoSuchFieldException {
        // Given
        Field syntheticField = TestClass.class.getDeclaredField("syntheticField");
        boolean serialize = true;
        // When
        boolean result = excluder.excludeField(syntheticField, serialize);
        // Then
        assertTrue(result, "Synthetic field should be excluded");
    }

    @Test
    void testExcludeField_withExposeAnnotationNotAllowingSerialization() throws NoSuchFieldException {
        // Given
        Field field = TestClass.class.getDeclaredField("exposeNotAllowingSerialization");
        boolean serialize = true;
        // When
        boolean result = excluder.excludeField(field, serialize);
        // Then
        assertTrue(result, "Field with Expose annotation that does not allow serialization should be excluded");
    }

    @Test
    void testExcludeField_withInnerClassField() throws NoSuchFieldException {
        // Given
        Field innerClassField = TestClass.class.getDeclaredField("innerClassField");
        boolean serialize = true;
        // When
        boolean result = excluder.excludeField(innerClassField, serialize);
        // Then
        assertTrue(result, "Inner class field should be excluded if inner class serialization is disabled");
    }

    @Test
    void testExcludeField_withStaticNestedClassField() throws NoSuchFieldException {
        // Given
        Field staticNestedClassField = TestClass.class.getDeclaredField("staticNestedClassField");
        boolean serialize = true;
        // When
        boolean result = excluder.excludeField(staticNestedClassField, serialize);
        // Then
        assertFalse(result, "Static nested class field should not be excluded");
    }

    @Test
    void testExcludeField_withAnonymousClassField() throws NoSuchFieldException {
        // Given
        Field anonymousClassField = TestClass.class.getDeclaredField("anonymousClassField");
        boolean serialize = true;
        // When
        boolean result = excluder.excludeField(anonymousClassField, serialize);
        // Then
        assertTrue(result, "Anonymous class field should be excluded");
    }
}
