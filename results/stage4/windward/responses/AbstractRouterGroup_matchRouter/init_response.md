Based on the provided information about the `AbstractRouterGroup` class and its `matchRouter` method, here is a complete test class template for testing the `matchRouter` method. The test class will include necessary initialization, setup, and teardown methods, while adhering to the guidelines provided.

```java
package org.flmelody.core;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

// Extend with Mockito to use mocking features
@ExtendWith(MockitoExtension.class)
class AbstractRouterGroup_matchRouter_Test {
    
    // Mock dependencies
    private AbstractRouterGroup<Windward> routerGroup;
    private M manager; // Assuming M is a type you have defined or imported

    @BeforeAll
    static void setupBeforeAll() {
        // Code to run once before all tests (e.g., initializing static resources)
    }

    @BeforeEach
    void setupBeforeEach() {
        // Initialize the routerGroup with a mock manager and a group path
        manager = mock(M.class); // Replace M with the actual type if necessary
        routerGroup = Mockito.spy(new AbstractRouterGroup<>(manager, "/test"));
        
        // Setting up routers for testing
        HashMap<String, Object> getRouter = new HashMap<>();
        getRouter.put("GET", new FunctionMetaInfo<>()); // Replace with actual FunctionMetaInfo setup
        routerGroup.routers.put("/test/resource", getRouter);
    }

    @AfterEach
    void teardownAfterEach() {
        // Code to run after each test (e.g., resetting mocks)
        Mockito.reset(routerGroup);
    }

    @AfterAll
    static void teardownAfterAll() {
        // Code to run once after all tests (e.g., cleaning up static resources)
    }

    @Test
    void testMatchRouter_ValidPath() {
        // Given a valid relative path and method
        String relativePath = "/test/resource";
        String method = "GET";

        // When matching the router
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then the result should not be null and should match expected behavior
        assertNotNull(result);
        // Additional assertions based on expected behavior
    }

    @Test
    void testMatchRouter_InvalidPath() {
        // Given an invalid relative path
        String relativePath = "/invalid/resource";
        String method = "GET";

        // When matching the router
        FunctionMetaInfo<?> result = routerGroup.matchRouter(relativePath, method);

        // Then the result should be null
        assertNull(result);
    }

    // Additional test cases can be added here
}
```

### Explanation:
- **Imports:** The necessary imports are included for the JUnit and Mockito libraries.
- **Mocks:** The `AbstractRouterGroup` is mocked with a generic type `M`, which should be replaced with the actual type as needed.
- **Setup Methods:** The `setupBeforeAll`, `setupBeforeEach`, `teardownAfterEach`, and `teardownAfterAll` methods are provided for proper test lifecycle management.
- **Test Cases:** Two example test cases (`testMatchRouter_ValidPath` and `testMatchRouter_InvalidPath`) are provided to demonstrate how to test valid and invalid paths. You can add more test cases as necessary based on your testing requirements.
- **Assertions:** The assertions check the expected outcomes of the `matchRouter` method.

Make sure to adapt the test cases to fit the actual behavior and expectations of your `matchRouter` method and the `FunctionMetaInfo` class.