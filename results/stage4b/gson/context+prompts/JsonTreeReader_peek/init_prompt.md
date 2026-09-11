You are tasked with creating a framework for a test class in Java. Now analyze the following information carefully:

1. The method that needs to be tested and its parameters.
2. The constructor of the target class and its parameters.
3. Any dependencies or external resources used by the target class.

Based on this analysis, complete the test class template in the provided structure:

1. Initialize necessary fields and dependencies within setupBeforeAll() and setupBeforeEach().
2. Handle any required cleanup in teardownAfterEach() and teardownAfterAll().
3. Make sure not to invoke any private methods or fields of the target class and dependent class.
4. Refer to the "given" phase in the existing test class for guidance.
5. Add necessary import statements and ensure there are no syntax errors in the generated code.

@input{focal method and target class}
Here is the code of focal method, `peek()`, with the focal class `JsonTreeReader_peek_Test`:
```Java
package com.google.gson.internal.bind;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.Reader;
import com.google.gson.JsonNull;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonToken;
import java.util.Iterator;
import com.google.gson.JsonPrimitive;
import java.util.Map;
import java.util.Arrays;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.MalformedJsonException;
public final class JsonTreeReader extends JsonReader {
    private static final Object SENTINEL_CLOSED = new Object();
    private Object[] stack = new Object[32];
    private int stackSize = 0;
    @Override
    public JsonToken peek() throws IOException {
        if (stackSize == 0) {
            return JsonToken.END_DOCUMENT;
        }
        Object o = peekStack();
        if (o instanceof Iterator) {
            boolean isObject = stack[stackSize - 2] instanceof JsonObject;
            Iterator<?> iterator = ((Iterator<?>) (o));
            if (iterator.hasNext()) {
                if (isObject) {
                    return JsonToken.NAME;
                } else {
                    push(iterator.next());
                    return peek();
                }
            } else {
                return isObject ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
            }
        } else if (o instanceof JsonObject) {
            return JsonToken.BEGIN_OBJECT;
        } else if (o instanceof JsonArray) {
            return JsonToken.BEGIN_ARRAY;
        } else if (o instanceof JsonPrimitive) {
            JsonPrimitive primitive = ((JsonPrimitive) (o));
            if (primitive.isString()) {
                return JsonToken.STRING;
            } else if (primitive.isBoolean()) {
                return JsonToken.BOOLEAN;
            } else if (primitive.isNumber()) {
                return JsonToken.NUMBER;
            } else {
                throw new AssertionError();
            }
        } else if (o instanceof JsonNull) {
            return JsonToken.NULL;
        } else if (o == SENTINEL_CLOSED) {
            throw new IllegalStateException("JsonReader is closed");
        } else {
            throw new MalformedJsonException(("Custom JsonElement subclass " + o.getClass().getName()) + " is not supported");
        }
    }
@Override
public void beginArray() throws IOException;
    @Override
public void beginObject() throws IOException;
    @Override
public void close() throws IOException;
    @Override
public void endArray() throws IOException;
    @Override
public void endObject() throws IOException;
    @Override
public String getPath();
    @Override
public String getPreviousPath();
    @Override
public boolean hasNext() throws IOException;
    @Override
public boolean nextBoolean() throws IOException;
    @Override
public double nextDouble() throws IOException;
    @Override
public int nextInt() throws IOException;
    JsonElement nextJsonElement() throws IOException;
    @Override
public long nextLong() throws IOException;
    @Override
public String nextName() throws IOException;
    @Override
public void nextNull() throws IOException;
    @Override
public String nextString() throws IOException;
    private Object peekStack();
    public void promoteNameToValue() throws IOException;
    private void push(Object newTop);
    @Override
public void skipValue() throws IOException;
    @Override
public String toString();
}
```


@input{context information}

api document of class com.google.gson.internal.bind.JsonTreeReader: 

 * This reader walks the elements of a JsonElement as if it was coming from a
 * character stream.
 *
 * @author Jesse Wilson
 

constructors for class `com.google.gson.internal.bind.JsonTreeReader`: 
params: com.google.gson.JsonElement element 
body:
```java
  public JsonTreeReader(JsonElement element) {
    super(UNREADABLE_READER);
    push(element);
  }

```

parameters in constructors and focal method: 
class `com.google.gson.JsonElement`:
api document : 
 * A class representing an element of JSON. It could either be a {@link JsonObject}, a
 * {@link JsonArray}, a {@link JsonPrimitive} or a {@link JsonNull}.
 *
 * @author Inderjeet Singh
 * @author Joel Leitch
 
constructor:
```java
```

existing test class: 
```java
/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gson.internal.bind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.common.MoreAsserts;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

@SuppressWarnings("resource")
public class JsonTreeReaderTest {
  @Test
  public void testSkipValue_emptyJsonObject() throws IOException {
    JsonTreeReader in = new JsonTreeReader(new JsonObject());
    in.skipValue();
    assertEquals(JsonToken.END_DOCUMENT, in.peek());
    assertEquals("$", in.getPath());
  }

  @Test
  public void testSkipValue_filledJsonObject() throws IOException {
    JsonObject jsonObject = new JsonObject();
    JsonArray jsonArray = new JsonArray();
    jsonArray.add('c');
    jsonArray.add("text");
    jsonObject.add("a", jsonArray);
    jsonObject.addProperty("b", true);
    jsonObject.addProperty("i", 1);
    jsonObject.add("n", JsonNull.INSTANCE);
    JsonObject jsonObject2 = new JsonObject();
    jsonObject2.addProperty("n", 2L);
    jsonObject.add("o", jsonObject2);
    jsonObject.addProperty("s", "text");
    JsonTreeReader in = new JsonTreeReader(jsonObject);
    in.skipValue();
    assertEquals(JsonToken.END_DOCUMENT, in.peek());
    assertEquals("$", in.getPath());
  }

  @Test
  public void testSkipValue_name() throws IOException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("a", "value");
    JsonTreeReader in = new JsonTreeReader(jsonObject);
    in.beginObject();
    in.skipValue();
    assertEquals(JsonToken.STRING, in.peek());
    assertEquals("$.<skipped>", in.getPath());
    assertEquals("value", in.nextString());
  }

  @Test
  public void testSkipValue_afterEndOfDocument() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonObject());
    reader.beginObject();
    reader.endObject();
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());

    assertEquals("$", reader.getPath());
    reader.skipValue();
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
    assertEquals("$", reader.getPath());
  }

  @Test
  public void testSkipValue_atArrayEnd() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonArray());
    reader.beginArray();
    reader.skipValue();
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
    assertEquals("$", reader.getPath());
  }

  @Test
......
    reader.beginObject();
    reader.skipValue();
    assertEquals(JsonToken.END_DOCUMENT, reader.peek());
    assertEquals("$", reader.getPath());
  }

  @Test
  public void testHasNext_endOfDocument() throws IOException {
    JsonTreeReader reader = new JsonTreeReader(new JsonObject());
    reader.beginObject();
    reader.endObject();
    assertFalse(reader.hasNext());
  }

  @Test
  public void testCustomJsonElementSubclass() throws IOException {
    @SuppressWarnings("deprecation") // superclass constructor
    class CustomSubclass extends JsonElement {
      @Override
      public JsonElement deepCopy() {
        return this;
      }
    }

    JsonArray array = new JsonArray();
    array.add(new CustomSubclass());

    JsonTreeReader reader = new JsonTreeReader(array);
    reader.beginArray();
    try {
      // Should fail due to custom JsonElement subclass
      reader.peek();
      fail();
    } catch (MalformedJsonException expected) {
      assertEquals("Custom JsonElement subclass " + CustomSubclass.class.getName() + " is not supported",
          expected.getMessage());
    }
  }

  /**
   * {@link JsonTreeReader} effectively replaces the complete reading logic of {@link JsonReader} to
   * read from a {@link JsonElement} instead of a {@link Reader}. Therefore all relevant methods of
   * {@code JsonReader} must be overridden.
   */
  @Test
  public void testOverrides() {
    List<String> ignoredMethods = Arrays.asList("setLenient(boolean)", "isLenient()");
    MoreAsserts.assertOverridesMethods(JsonReader.class, JsonTreeReader.class, ignoredMethods);
  }
}
```

invoke examples of focal method: 
example 1:
```java
  @Override public boolean hasNext() throws IOException {
    JsonToken token = peek();
  }
```
example 2:
```java
  @Override public String nextString() throws IOException {
    JsonToken token = peek();
  }
```
example 3:
```java
  @Override public double nextDouble() throws IOException {
    JsonToken token = peek();
  }
```



@input{test class template}
```java
package com.google.gson.internal.bind;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class JsonTreeReader_peek_Test {
    @BeforeAll
    static void setupBeforeAll() {
    }

    @BeforeEach
    void setupBeforeEach() {
    }

    @AfterEach
    void teardownAfterEach() {
    }

    @AfterAll
    static void teardownAfterAll() {
    }
}
```

@output{initial test class}: now consider the invoke examples output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.