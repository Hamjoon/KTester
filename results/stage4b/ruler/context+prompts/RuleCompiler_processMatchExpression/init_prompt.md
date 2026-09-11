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
Here is the code of focal method, `processMatchExpression(JsonParser)`, with the focal class `RuleCompiler_processMatchExpression_Test`:
```Java
package software.amazon.event.ruler;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashMap;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonFactory;
import java.util.Deque;
import com.fasterxml.jackson.core.JsonParseException;
import java.io.Reader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
public final class RuleCompiler {
    
    private static Patterns processMatchExpression(final JsonParser parser) throws IOException {
        final JsonToken matchTypeToken = parser.nextToken();
        if (matchTypeToken != JsonToken.FIELD_NAME) {
            barf(parser, "Match expression name not found");
        }
        final String matchTypeName = parser.getCurrentName();
        if (Constants.EXACT_MATCH.equals(matchTypeName)) {
            final JsonToken prefixToken = parser.nextToken();
            if (prefixToken != JsonToken.VALUE_STRING) {
                barf(parser, "exact match pattern must be a string");
            }
            final Patterns pattern = Patterns.exactMatch(('"' + parser.getText()) + '"');
            if (parser.nextToken() != JsonToken.END_OBJECT) {
                barf(parser, "Only one key allowed in match expression");
            }
            return pattern;
        } else if (Constants.PREFIX_MATCH.equals(matchTypeName)) {
            final JsonToken prefixToken = parser.nextToken();
            if (prefixToken != JsonToken.VALUE_STRING) {
                barf(parser, "prefix match pattern must be a string");
            }
            final Patterns pattern = Patterns.prefixMatch('"' + parser.getText());// note no trailing quote
    
            if (parser.nextToken() != JsonToken.END_OBJECT) {
                barf(parser, "Only one key allowed in match expression");
            }
            return pattern;
        } else if (Constants.SUFFIX_MATCH.equals(matchTypeName)) {
            final JsonToken suffixToken = parser.nextToken();
            if (suffixToken != JsonToken.VALUE_STRING) {
                barf(parser, "suffix match pattern must be a string");
            }
            final Patterns pattern = Patterns.suffixMatch(parser.getText() + '"');// note no beginning quote
    
            if (parser.nextToken() != JsonToken.END_OBJECT) {
                barf(parser, "Only one key allowed in match expression");
            }
            return pattern;
        } else if (Constants.NUMERIC.equals(matchTypeName)) {
            final JsonToken numericalExpressionToken = parser.nextToken();
            if (numericalExpressionToken != JsonToken.START_ARRAY) {
                barf(parser, ("Value of " + Constants.NUMERIC) + " must be an array.");
            }
            Patterns range = processNumericMatchExpression(parser);
            if (parser.nextToken() != JsonToken.END_OBJECT) {
                tooManyElements(parser);
            }
            return range;
        } else if (Constants.ANYTHING_BUT_MATCH.equals(matchTypeName)) {
            boolean isIgnoreCase = false;
            JsonToken anythingButExpressionToken = parser.nextToken();
            if (anythingButExpressionToken == JsonToken.START_OBJECT) {
                // there are a limited set of things we can apply Anything-But to
                final JsonToken anythingButObject = parser.nextToken();
                if (anythingButObject != JsonToken.FIELD_NAME) {
                    barf(parser, "Anything-But expression name not found");
                }
                final String anythingButObjectOp = parser.getCurrentName();
                final boolean isPrefix = Constants.PREFIX_MATCH.equals(anythingButObjectOp);
                final boolean isSuffix = Constants.SUFFIX_MATCH.equals(anythingButObjectOp);
                isIgnoreCase = Constants.EQUALS_IGNORE_CASE.equals(anythingButObjectOp);
                if (!isIgnoreCase) {
                    if ((!isPrefix) && (!isSuffix)) {
                        barf(parser, "Unsupported anything-but pattern: " + anythingButObjectOp);
                    }
                    final JsonToken anythingButParamType = parser.nextToken();
                    if (anythingButParamType != JsonToken.VALUE_STRING) {
                        barf(parser, "prefix/suffix match pattern must be a string");
                    }
                    final String text = parser.getText();
                    if (text.isEmpty()) {
                        barf(parser, "Null prefix/suffix not allowed");
                    }
                    if (parser.nextToken() != JsonToken.END_OBJECT) {
                        barf(parser, "Only one key allowed in match expression");
                    }
                    if (parser.nextToken() != JsonToken.END_OBJECT) {
                        barf(parser, "Only one key allowed in match expression");
                    }
                    if (isPrefix) {
                        return Patterns.anythingButPrefix('"' + text);// note no trailing quote
    
                    } else {
                        return Patterns.anythingButSuffix(text + '"');// note no leading quote
    
                    }
                } else {
                    // Step into the anything-but's ignore-case
                    anythingButExpressionToken = parser.nextToken();
                }
            }
            if ((((anythingButExpressionToken != JsonToken.START_ARRAY) && (anythingButExpressionToken != JsonToken.VALUE_STRING)) && (anythingButExpressionToken != JsonToken.VALUE_NUMBER_FLOAT)) && (anythingButExpressionToken != JsonToken.VALUE_NUMBER_INT)) {
                barf(parser, ("Value of " + Constants.ANYTHING_BUT_MATCH) + " must be an array or single string/number value.");
            }
            Patterns anythingBut;
            if (anythingButExpressionToken == JsonToken.START_ARRAY) {
                if (isIgnoreCase) {
                    anythingBut = processAnythingButEqualsIgnoreCaseListMatchExpression(parser);
                } else {
                    anythingBut = processAnythingButListMatchExpression(parser);
                }
            } else if (isIgnoreCase) {
                anythingBut = processAnythingButEqualsIgnoreCaseMatchExpression(parser, anythingButExpressionToken);
            } else {
                anythingBut = processAnythingButMatchExpression(parser, anythingButExpressionToken);
            }
            if (parser.nextToken() != JsonToken.END_OBJECT) {
                tooManyElements(parser);
            }
            // If its an ignore-case, we have another
            // object end to consume...
            if (isIgnoreCase && (parser.nextToken() != JsonToken.END_OBJECT)) {
                tooManyElements(parser);
            }
            return anythingBut;
        } else if (Constants.EXISTS_MATCH.equals(matchTypeName)) {
            return processExistsExpression(parser);
        } else if (Constants.CIDR.equals(matchTypeName)) {
            final JsonToken cidrToken = parser.nextToken();
            if (cidrToken != JsonToken.VALUE_STRING) {
                barf(parser, "prefix match pattern must be a string");
            }
            final Range cidr = CIDR.cidr(parser.getText());
            if (parser.nextToken() != JsonToken.END_OBJECT) {
                barf(parser, "Only one key allowed in match expression");
            }
            return cidr;
        } else if (Constants.EQUALS_IGNORE_CASE.equals(matchTypeName)) {
            final JsonToken equalsIgnoreCaseToken = parser.nextToken();
            if (equalsIgnoreCaseToken != JsonToken.VALUE_STRING) {
                barf(parser, "equals-ignore-case match pattern must be a string");
            }
            final Patterns pattern = Patterns.equalsIgnoreCaseMatch(('"' + parser.getText()) + '"');
            if (parser.nextToken() != JsonToken.END_OBJECT) {
                barf(parser, "Only one key allowed in match expression");
            }
            return pattern;
        } else if (Constants.WILDCARD.equals(matchTypeName)) {
            final JsonToken wildcardToken = parser.nextToken();
            if (wildcardToken != JsonToken.VALUE_STRING) {
                barf(parser, "wildcard match pattern must be a string");
            }
            final String parserText = parser.getText();
            final Patterns pattern = Patterns.wildcardMatch(('"' + parserText) + '"');
            if (parser.nextToken() != JsonToken.END_OBJECT) {
                barf(parser, "Only one key allowed in match expression");
            }
            return pattern;
        } else {
            barf(parser, "Unrecognized match type " + matchTypeName);
            return null;// unreachable statement, but java can't see that?
    
        }
    }
private static void barf(final JsonParser parser, final String message) throws JsonParseException;
    public static String check(final byte[] source);
    public static String check(final InputStream source);
    public static String check(final Reader source);
    public static String check(final String source);
    public static Map<String, List<Patterns>> compile(final byte[] source) throws IOException;
    public static Map<String, List<Patterns>> compile(final InputStream source) throws IOException;
    public static Map<String, List<Patterns>> compile(final Reader source) throws IOException;
    public static Map<String, List<Patterns>> compile(final String source) throws IOException;
    private static Patterns processAnythingButEqualsIgnoreCaseListMatchExpression(JsonParser parser) throws JsonParseException;
    private static Patterns processAnythingButEqualsIgnoreCaseMatchExpression(JsonParser parser, JsonToken anythingButExpressionToken) throws IOException;
    private static Patterns processAnythingButListMatchExpression(JsonParser parser) throws JsonParseException;
    private static Patterns processAnythingButMatchExpression(JsonParser parser, JsonToken anythingButExpressionToken) throws IOException;
    private static Patterns processExistsExpression(final JsonParser parser) throws IOException;
    private static Patterns processNumericMatchExpression(final JsonParser parser) throws IOException;
    private static void tooManyElements(final JsonParser parser) throws JsonParseException;
}
```


@input{context information}

api document of class software.amazon.event.ruler.RuleCompiler: 

 * Compiles Rules, expressed in JSON, for use in Ruler.
 * There are two flavors of compilation:
 * 1. Compile a JSON-based Rule into Map of String to List of Patterns which can be used in rulesForEvent,
 *    and has a "check" variant that just checks rules for syntactic accuracy
 * 2. Starting in ListBasedRuleCompiler, does the same thing but expresses field names as List ofString
 *    rather than "."-separated strings for use in the Ruler class, which does not use state machines and
 *    needs to step into the event field by field.
 *
 * Is public so clients can call the check() method to syntax-check filters
 

constructors for class `software.amazon.event.ruler.RuleCompiler`: 
params: 
body:
```java
    private RuleCompiler() {
      throw new UnsupportedOperationException("You can't create instance of utility class.");
    }

```

invoke examples of focal method: 
example 1:
```java
    private static void parseObject(final Map<String, List<Patterns>> rule,
                                    final Path path,
                                    final JsonParser parser,
                                    final boolean withQuotes) throws IOException {

        boolean fieldsPresent = false;
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            fieldsPresent = true;
            final String stepName = parser.getCurrentName();
            switch (parser.nextToken()) {
            case START_OBJECT:
                path.push(stepName);
                parseObject(rule, path, parser, withQuotes);
    }
    private static void parseObject(final Map<String, List<Patterns>> rule,
                                    final Path path,
                                    final JsonParser parser,
                                    final boolean withQuotes) throws IOException {

        boolean fieldsPresent = false;
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            fieldsPresent = true;
            final String stepName = parser.getCurrentName();
            switch (parser.nextToken()) {
            case START_ARRAY:
                writeRules(rule, path.extendedName(stepName), parser, withQuotes);
    }
    private static void writeRules(final Map<String, List<Patterns>> rule,
                                   final String name,
                                   final JsonParser parser,
                                   final boolean withQuotes) throws IOException {
        JsonToken token;
        final List<Patterns> values = new ArrayList<>();
        while ((token = parser.nextToken()) != JsonToken.END_ARRAY) {
            switch (token) {
            case START_OBJECT:
                values.add(processMatchExpression(parser));
    }
```
example 2:
```java
        private static void parseRuleObject(final Map<List<String>, List<Patterns>> rule,
                                            final Deque<String> stack,
                                            final JsonParser parser,
                                            final boolean withQuotes) throws IOException {

            boolean fieldsPresent = false;
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                fieldsPresent = true;
                final String stepName = parser.getCurrentName();
                switch (parser.nextToken()) {
                    case START_OBJECT:
                        stack.addLast(stepName);
                        parseRuleObject(rule, stack, parser, withQuotes);
        }
        private static void parseRuleObject(final Map<List<String>, List<Patterns>> rule,
                                            final Deque<String> stack,
                                            final JsonParser parser,
                                            final boolean withQuotes) throws IOException {

            boolean fieldsPresent = false;
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                fieldsPresent = true;
                final String stepName = parser.getCurrentName();
                switch (parser.nextToken()) {
                    case START_ARRAY:
                        writeRules(rule, rulePathname(stack, stepName), parser, withQuotes);
        }
        private static void writeRules(final Map<List<String>, List<Patterns>> rule,
                                       final List<String> name,
                                       final JsonParser parser,
                                       final boolean withQuotes) throws IOException {
            JsonToken token;
            final List<Patterns> values = new ArrayList<>();
            while ((token = parser.nextToken()) != JsonToken.END_ARRAY) {
                switch (token) {
                    case START_OBJECT:
                        values.add(processMatchExpression(parser));
        }
```
example 3:
```java
    public static String check(final Reader source) {
        try {
            doCompile(JSON_FACTORY.createParser(source));
    }
    private static Map<String, List<Patterns>> doCompile(final JsonParser parser) throws IOException {
        final Path path = new Path();
        final Map<String, List<Patterns>> rule = new HashMap<>();
        if (parser.nextToken() != JsonToken.START_OBJECT) {
        parseObject(rule, path, parser, true);
    }
    private static void parseObject(final Map<String, List<Patterns>> rule,
                                    final Path path,
                                    final JsonParser parser,
                                    final boolean withQuotes) throws IOException {

        boolean fieldsPresent = false;
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            fieldsPresent = true;
            final String stepName = parser.getCurrentName();
            switch (parser.nextToken()) {
            case START_ARRAY:
                writeRules(rule, path.extendedName(stepName), parser, withQuotes);
    }
    private static void writeRules(final Map<String, List<Patterns>> rule,
                                   final String name,
                                   final JsonParser parser,
                                   final boolean withQuotes) throws IOException {
        JsonToken token;
        final List<Patterns> values = new ArrayList<>();
        while ((token = parser.nextToken()) != JsonToken.END_ARRAY) {
            switch (token) {
            case START_OBJECT:
                values.add(processMatchExpression(parser));
    }
```



@input{test class template}
```java
package software.amazon.event.ruler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class RuleCompiler_processMatchExpression_Test {
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