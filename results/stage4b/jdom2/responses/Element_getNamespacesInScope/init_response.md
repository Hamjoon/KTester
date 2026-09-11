Based on the provided information regarding the `Element` class and its method `getNamespacesInScope()`, as well as the necessary setup for the test class, here's the completed test class template. This template includes the initialization of necessary fields and dependencies, as well as the test methods that will invoke the `getNamespacesInScope()` method.

```java
package org.jdom2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class Element_getNamespacesInScope_Test {
    
    private Element element;
    private Namespace namespace;

    @BeforeAll
    static void setupBeforeAll() {
        // Code that runs once before all tests, if necessary
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the Namespace and Element instances before each test
        namespace = Namespace.getNamespace("prefix", "http://example.com");
        element = new Element("exampleElement", namespace);
    }

    @AfterEach
    void teardownAfterEach() {
        // Clean up after each test, if necessary
    }

    @AfterAll
    static void teardownAfterAll() {
        // Code that runs once after all tests, if necessary
    }

    @Test
    void testGetNamespacesInScope() {
        // Given
        Namespace additionalNamespace = Namespace.getNamespace("ns2", "http://example2.com");
        element.addNamespaceDeclaration(additionalNamespace);
        
        // When
        List<Namespace> namespacesInScope = element.getNamespacesInScope();
        
        // Then
        assertNotNull(namespacesInScope);
        assertTrue(namespacesInScope.contains(namespace), "Should contain the main namespace");
        assertTrue(namespacesInScope.contains(additionalNamespace), "Should contain the additional namespace");
    }

    // Additional test cases can be added here to cover more scenarios
}
```

### Explanation:
1. **Imports**: The necessary imports are included for JUnit and Mockito.
2. **Class Declaration**: The test class is annotated with `@ExtendWith(MockitoExtension.class)` to enable Mockito functionalities.
3. **Fields**: Two fields, `element` and `namespace`, are declared to hold the instances of the `Element` and `Namespace` classes.
4. **Setup Methods**:
   - `setupBeforeAll()`: Placeholder for code that runs once before all tests.
   - `setupBeforeEach()`: Initializes the `Element` and `Namespace` instances before each test.
5. **Teardown Methods**: Placeholder methods for any necessary cleanup after each test and after all tests.
6. **Test Method**: 
   - `testGetNamespacesInScope()`: This method tests the `getNamespacesInScope()` functionality, checking that it returns the correct namespaces. It creates an additional namespace and verifies that both the main and additional namespaces are present in the result.

This structure allows for easy expansion with additional test cases to further cover the functionality of the `getNamespacesInScope()` method.