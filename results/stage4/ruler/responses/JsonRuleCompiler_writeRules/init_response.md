Based on the provided information, here is the completed test class template for `JsonRuleCompiler_writeRules_Test`. The test class is structured to include the necessary setup and teardown methods, and it prepares to test the `writeRules` method while adhering to the guidelines provided.

```java
package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.StringReader;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class JsonRuleCompiler_writeRules_Test {

    private static JsonFactory jsonFactory;
    private JsonParser jsonParser;
    private List<Map<String, List<Patterns>>> rules;

    @BeforeAll
    static void setupBeforeAll() {
        jsonFactory = new JsonFactory();
    }

    @BeforeEach
    void setupBeforeEach() throws Exception {
        rules = new ArrayList<>();
        String jsonString = "{ \"source\": [ \"aws.cloudwatch\" ], \"$or\": [ { \"metricName\": [ \"CPUUtilization\", \"ReadLatency\" ] }, { \"metricType\": [ \"MetricType\" ], \"namespace\": [ \"AWS/EC2\", \"AWS/ES\" ] }, { \"scope\": [ \"Service\" ] } ] } }";
        jsonParser = jsonFactory.createParser(new StringReader(jsonString));
    }

    @AfterEach
    void teardownAfterEach() throws Exception {
        if (jsonParser != null) {
            jsonParser.close();
        }
    }

    @AfterAll
    static void teardownAfterAll() {
        jsonFactory = null;
    }

    @Test
    void testWriteRulesWithValidInput() throws Exception {
        boolean withQuotes = true;

        // Invoke the method to be tested
        JsonRuleCompiler.writeRules(rules, "source", jsonParser, withQuotes);

        // Assertions to check the expected output
        assertFalse(rules.isEmpty(), "Rules should not be empty after invocation.");
        assertEquals(3, rules.size(), "There should be 3 match branches.");
        // Additional assertions can be added here to validate the contents of the rules
    }

    @Test
    void testWriteRulesWithEmptyArray() {
        boolean withQuotes = true;

        // Attempt to invoke the method with an empty parser
        assertThrows(IOException.class, () -> {
            JsonRuleCompiler.writeRules(rules, "emptySource", jsonFactory.createParser("[]"), withQuotes);
        }, "Expected IOException for empty array.");
    }
}
```

### Explanation of the Test Class Components:
1. **Imports**: Necessary classes are imported, including JUnit and Mockito components.
2. **Setup Methods**:
   - `setupBeforeAll()`: Initializes the `JsonFactory` once before all tests.
   - `setupBeforeEach()`: Initializes a new list for rules and a `JsonParser` for each test.
3. **Teardown Methods**:
   - `teardownAfterEach()`: Closes the `JsonParser` after each test to prevent resource leaks.
   - `teardownAfterAll()`: Cleans up any static resources.
4. **Test Methods**:
   - `testWriteRulesWithValidInput()`: Tests the `writeRules` method with valid JSON input and checks that the expected rules are generated.
   - `testWriteRulesWithEmptyArray()`: Tests the behavior of the method when an empty array is passed, expecting an `IOException`.

This structure adheres to the guidelines provided and ensures that the tests are clear and maintainable.