package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.ToNumberPolicy;
import com.google.gson.ToNumberStrategy;
import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.StringReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ObjectTypeAdapter_read_Test {

    private ObjectTypeAdapter objectTypeAdapter;

    private Gson gson;

    private ToNumberStrategy toNumberStrategy;

    @BeforeEach
    void setupBeforeEach() {
        // Initialize Gson and ToNumberStrategy
        gson = new Gson();
        toNumberStrategy = ToNumberPolicy.DOUBLE;
        // Create an instance of ObjectTypeAdapter with the required dependencies
        objectTypeAdapter = new ObjectTypeAdapter(gson, toNumberStrategy);
    }

    @Test
    void testRead_withValidJson() throws IOException {
        // Given
        String json = "{\"key\":\"value\"}";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        // When
        Object result = objectTypeAdapter.read(jsonReader);
        // Then
        assertTrue(result instanceof Map);
        assertEquals("value", ((Map<?, ?>) result).get("key"));
    }

    @Test
    void testRead_emptyJsonArray() throws IOException {
        // Given
        String json = "[]";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        // When
        Object result = objectTypeAdapter.read(jsonReader);
        // Then
        assertTrue(result instanceof List);
        assertTrue(((List<?>) result).isEmpty());
    }

    @Test
    void testRead_emptyJsonObject() throws IOException {
        // Given
        String json = "{}";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        // When
        Object result = objectTypeAdapter.read(jsonReader);
        // Then
        assertTrue(result instanceof Map);
        assertTrue(((Map<?, ?>) result).isEmpty());
    }

    @Test
    void testRead_jsonArrayOfIntegers() throws IOException {
        // Given
        String json = "[1, 2, 3]";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        // When
        Object result = objectTypeAdapter.read(jsonReader);
        // Then
        assertTrue(result instanceof List);
        assertEquals(List.of(1.0, 2.0, 3.0), result);
    }

    @Test
    void testRead_simpleJsonObject() throws IOException {
        // Given
        String json = "{\"key1\": \"value1\", \"key2\": \"value2\"}";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        // When
        Object result = objectTypeAdapter.read(jsonReader);
        // Then
        assertTrue(result instanceof Map);
        Map<?, ?> resultMap = (Map<?, ?>) result;
        assertEquals("value1", resultMap.get("key1"));
        assertEquals("value2", resultMap.get("key2"));
    }

    @Test
    void testRead_jsonArrayWithNestedObject() throws IOException {
        // Given
        String json = "[{\"nestedKey\": \"nestedValue\"}]";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        // When
        Object result = objectTypeAdapter.read(jsonReader);
        // Then
        assertTrue(result instanceof List);
        List<?> resultList = (List<?>) result;
        assertTrue(resultList.get(0) instanceof Map);
        assertEquals("nestedValue", ((Map<?, ?>) resultList.get(0)).get("nestedKey"));
    }

    @Test
    void testRead_jsonObjectWithNestedArray() throws IOException {
        // Given
        String json = "{\"outerKey\": [\"listItem1\", \"listItem2\"]}";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        // When
        Object result = objectTypeAdapter.read(jsonReader);
        // Then
        assertTrue(result instanceof Map);
        Map<?, ?> resultMap = (Map<?, ?>) result;
        assertTrue(resultMap.get("outerKey") instanceof List);
        assertEquals(List.of("listItem1", "listItem2"), resultMap.get("outerKey"));
    }

    @Test
    void testRead_jsonArrayWithMixedTypes() throws IOException {
        // Given
        String json = "[{\"key\": [null, true, false, \"string\", 456.78]}]";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        // When
        Object result = objectTypeAdapter.read(jsonReader);
        // Then
        assertTrue(result instanceof List);
        List<?> resultList = (List<?>) result;
        assertTrue(resultList.get(0) instanceof Map);
        List<?> nestedList = (List<?>) ((Map<?, ?>) resultList.get(0)).get("key");
        assertNull(nestedList.get(0));
        assertTrue((Boolean) nestedList.get(1));
        assertFalse((Boolean) nestedList.get(2));
        assertEquals("string", nestedList.get(3));
        assertEquals(456.78, nestedList.get(4));
    }

    @Test
    void testRead_jsonBooleanValue() throws IOException {
        // Given
        String json = "true";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        // When
        Object result = objectTypeAdapter.read(jsonReader);
        // Then
        assertTrue(result instanceof Boolean);
        assertEquals(true, result);
    }

    @Test
    void testRead_jsonNullValue() throws IOException {
        // Given
        String json = "null";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        // When
        Object result = objectTypeAdapter.read(jsonReader);
        // Then
        assertNull(result);
    }

    @Test
    void testRead_jsonStringValue() throws IOException {
        // Given
        String json = "\"stringValue\"";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        // When
        Object result = objectTypeAdapter.read(jsonReader);
        // Then
        assertTrue(result instanceof String);
        assertEquals("stringValue", result);
    }

    @Test
    void testRead_jsonNumberValue() throws IOException {
        // Given
        String json = "123";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        // When
        Object result = objectTypeAdapter.read(jsonReader);
        // Then
        assertTrue(result instanceof Double);
        assertEquals(123.0, result);
    }

    @Test
    void testRead_malformedJsonObject() {
        // Given
        String json = "{\"key\": \"value\"";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        // Then
        assertThrows(IOException.class, () -> {
            // When
            objectTypeAdapter.read(jsonReader);
        });
    }

    @Test
    void testRead_malformedJsonArray() {
        // Given
        String json = "[1, 2, 3";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        // Then
        assertThrows(IOException.class, () -> {
            // When
            objectTypeAdapter.read(jsonReader);
        });
    }

    @Test
    void testRead_malformedNestedJsonArray() {
        // Given
        String json = "[{\"key\": [1, 2, 3}";
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        // Then
        assertThrows(IOException.class, () -> {
            // When
            objectTypeAdapter.read(jsonReader);
        });
    }
}
