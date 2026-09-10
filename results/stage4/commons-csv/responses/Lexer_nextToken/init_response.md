Based on the provided information and requirements, here is the completed test class template for testing the `nextToken(Token token)` method of the `Lexer` class. This class follows the structure you provided, initializing necessary fields and dependencies in the setup methods and handling cleanup in the teardown methods.

```java
package org.apache.commons.csv;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.io.StringReader;

@ExtendWith(MockitoExtension.class)
class Lexer_nextToken_Test {
    private CSVFormat formatWithEscaping;
    private Lexer lexer;
    private ExtendedBufferedReader reader;
    private Token token;

    @BeforeAll
    static void setupBeforeAll() {
        // Any global setup can be done here
    }

    @BeforeEach
    void setupBeforeEach() {
        formatWithEscaping = CSVFormat.DEFAULT.withEscape('\\');
        // Initialize the token to be passed to nextToken
        token = new Token();
    }

    @AfterEach
    void teardownAfterEach() {
        // Cleanup resources after each test if necessary
        if (lexer != null) {
            try {
                lexer.close();
            } catch (IOException e) {
                // Handle exception
            }
        }
    }

    @AfterAll
    static void teardownAfterAll() {
        // Any global cleanup can be done here
    }

    // Test cases for nextToken method
    @Test
    void testNextTokenWithEscaping() throws IOException {
        final String code = "a,\\,,b\\\\\n\\,,\\\nc,d\\\r\ne";
        lexer = new Lexer(formatWithEscaping, new ExtendedBufferedReader(new StringReader(code)));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "a"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, ","));
        assertThat(lexer.nextToken(token), matches(Token.Type.EORECORD, "b\\"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, ","));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "\nc"));
        assertThat(lexer.nextToken(token), matches(Token.Type.EORECORD, "d\r"));
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, "e"));
    }

    @Test
    void testNextTokenWithoutEscaping() throws IOException {
        final String code = "a,\\,,b\\\n\\,,";
        lexer = new Lexer(CSVFormat.DEFAULT, new ExtendedBufferedReader(new StringReader(code)));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "a"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "\\"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, ""));
        assertThat(lexer.nextToken(token), matches(Token.Type.EORECORD, "b\\"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "\\"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, ""));
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
    }

    @Test
    void testNextTokenWithSurroundingSpaces() throws IOException {
        final String code = " noSpaces ,  leadingSpaces , trailingSpaces  ,  surroundingSpaces  ,  ,, ";
        lexer = new Lexer(CSVFormat.DEFAULT.withIgnoreSurroundingSpaces(), new ExtendedBufferedReader(new StringReader(code)));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "noSpaces"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "leadingSpaces"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "trailingSpaces"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, "surroundingSpaces"));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, ""));
        assertThat(lexer.nextToken(token), matches(Token.Type.TOKEN, ""));
        assertThat(lexer.nextToken(token), matches(Token.Type.EOF, ""));
    }

    // Additional test cases can be added as needed
}
```

### Explanation:
1. **Imports**: Necessary imports are included, including JUnit and Mockito for testing.
2. **Setup Methods**: 
   - `setupBeforeAll()` can be used for any global initialization.
   - `setupBeforeEach()` initializes the CSVFormat and Token before each test.
3. **Teardown Methods**: 
   - `teardownAfterEach()` ensures that the `lexer` is closed after each test to prevent resource leaks.
4. **Test Cases**: 
   - Various test cases are provided to check the behavior of the `nextToken()` method with escaping enabled and disabled, as well as handling surrounding spaces.
5. **Assertions**: The assertions use the `matches` method to check the expected token type and content.

Feel free to add more test cases as necessary to cover additional scenarios and edge cases for the `nextToken()` method.