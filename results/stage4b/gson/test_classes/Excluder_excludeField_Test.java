package com.google.gson.internal;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Field;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.Until;
import com.google.gson.annotations.Since;
import com.google.gson.FieldAttributes;
import com.google.gson.ExclusionStrategy;
import java.lang.reflect.Modifier;

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

        public String syntheticField;

        @Expose(serialize = false)
        public String exposeNotAllowingSerialization;

        public String innerClassField;

        public String staticNestedClassField;

        public String anonymousClassField;
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Excluder instance before each test
        excluder = new Excluder().withVersion(1.0).withExclusionStrategy(new ExclusionStrategy() {

            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                // Adjust logic to handle synthetic fields and inner/anonymous classes
                try {
                    Field field = TestClass.class.getDeclaredField(f.getName());
                    if (Modifier.isSynthetic(field.getModifiers())) {
                        // Exclude synthetic fields
                        return true;
                    }
                } catch (NoSuchFieldException e) {
                    // Handle exception if field is not found
                }
                if (f.getDeclaringClass().isMemberClass() && !Modifier.isStatic(f.getDeclaringClass().getModifiers())) {
                    // Exclude inner class fields if inner class serialization is disabled
                    return true;
                }
                if (f.getDeclaringClass().isAnonymousClass()) {
                    // Exclude anonymous class fields
                    return true;
                }
                // Default behavior
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                // Default behavior
                return false;
            }
        }, true, true);
    }

    @Test
    void testExcludeField_withTransientModifier() throws NoSuchFieldException {
        Field field = TestClass.class.getDeclaredField("transientField");
        boolean serialize = true;
        boolean result = excluder.excludeField(field, serialize);
        assertTrue(result, "Field with transient modifier should be excluded");
    }

    @Test
    void testExcludeField_withExposeAnnotation() throws NoSuchFieldException {
        Field field = TestClass.class.getDeclaredField("exposedField");
        boolean serialize = true;
        boolean result = excluder.excludeField(field, serialize);
        assertFalse(result, "Field with Expose annotation should not be excluded");
    }

    @Test
    void testExcludeField_withVersionCheck() throws NoSuchFieldException {
        Field fieldWithSince = TestClass.class.getDeclaredField("fieldWithSince");
        Field fieldWithUntil = TestClass.class.getDeclaredField("fieldWithUntil");
        boolean serialize = true;
        boolean resultSince = excluder.excludeField(fieldWithSince, serialize);
        boolean resultUntil = excluder.excludeField(fieldWithUntil, serialize);
        assertFalse(resultSince, "Field with Since annotation should not be excluded if version is not ignored");
        assertTrue(resultUntil, "Field with Until annotation should be excluded if version is not ignored");
    }

    @Test
    void testExcludeField_withSyntheticField() throws NoSuchFieldException {
        Field syntheticField = TestClass.class.getDeclaredField("syntheticField");
        boolean serialize = true;
        boolean result = excluder.excludeField(syntheticField, serialize);
        assertTrue(result, "Synthetic field should be excluded");
    }

    @Test
    void testExcludeField_withExposeAnnotationNotAllowingSerialization() throws NoSuchFieldException {
        Field field = TestClass.class.getDeclaredField("exposeNotAllowingSerialization");
        boolean serialize = true;
        boolean result = excluder.excludeField(field, serialize);
        assertTrue(result, "Field with Expose annotation that does not allow serialization should be excluded");
    }

    @Test
    void testExcludeField_withInnerClassField() throws NoSuchFieldException {
        Field innerClassField = TestClass.class.getDeclaredField("innerClassField");
        boolean serialize = true;
        boolean result = excluder.excludeField(innerClassField, serialize);
        assertTrue(result, "Inner class field should be excluded if inner class serialization is disabled");
    }

    @Test
    void testExcludeField_withStaticNestedClassField() throws NoSuchFieldException {
        Field staticNestedClassField = TestClass.class.getDeclaredField("staticNestedClassField");
        boolean serialize = true;
        boolean result = excluder.excludeField(staticNestedClassField, serialize);
        assertFalse(result, "Static nested class field should not be excluded");
    }

    @Test
    void testExcludeField_withAnonymousClassField() throws NoSuchFieldException {
        Field anonymousClassField = TestClass.class.getDeclaredField("anonymousClassField");
        boolean serialize = true;
        boolean result = excluder.excludeField(anonymousClassField, serialize);
        assertTrue(result, "Anonymous class field should be excluded");
    }
}
