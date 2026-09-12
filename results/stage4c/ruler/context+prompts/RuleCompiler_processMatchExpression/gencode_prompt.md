You are a professional Java software testing expert. Your task is to generate high-quality unit test code for a focal method, using **JUnit5** and **Mockito** libraries. 

You'll be provided with:
1. The focal method to test.
2. The target class's structure (fields and methods).
3. A pre-defined test class framework with `@Before`/`@After` methods.
4. Test cases in JSON format.
5. Any relevant context.

**Requirements:**

Code Quality:
- Include clear comments explaining each test scenario.
- Ensure code is clean, understandable, and maintainable.
- Do not directly access private methods or fields. **Use reflection if the focal method is private.**
- Include all necessary imports; code must be free of syntax errors.

**Test Structure:**
- Follow the given test class framework; do not modify `@Before`/`@After` methods.
- Create one test function per test case group, ensuring all cases are covered.
- Use parameterized tests where appropriate.
- Reinitialize variables inside test functions if `@Setup` values don't fit.
- Properly mock and set up dependencies.

@input{focal method and target class}
Here is the code of focal method, `processMatchExpression(JsonParser)`, with the focal class `RuleCompiler`:
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

@input{initial test class framework}
```Java
<initial_class>
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
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
 

return type: 
software.amazon.event.ruler.Patterns

dependent classes: 
class `software.amazon.event.ruler.Constants`:
	dependent fields:
		java.lang.String software.amazon.event.ruler.Constants.NUMERIC;
		java.lang.String software.amazon.event.ruler.Constants.CIDR;
		java.lang.String software.amazon.event.ruler.Constants.ANYTHING_BUT_MATCH;
		java.lang.String software.amazon.event.ruler.Constants.EQUALS_IGNORE_CASE;
		java.lang.String software.amazon.event.ruler.Constants.SUFFIX_MATCH;
		java.lang.String software.amazon.event.ruler.Constants.EXISTS_MATCH;
		java.lang.String software.amazon.event.ruler.Constants.PREFIX_MATCH;
		java.lang.String software.amazon.event.ruler.Constants.EXACT_MATCH;
		java.lang.String software.amazon.event.ruler.Constants.WILDCARD;
	
class `software.amazon.event.ruler.JsonRuleCompiler`:
	related functions:
		method `private static void parseObject(List<Map<String, List<Patterns>>>, Path, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.RuleCompiler.processMatchExpression(JsonParser)`;
		method `private static Patterns processMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.RuleCompiler.processMatchExpression(JsonParser)`;
		method `private static void writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.RuleCompiler.processMatchExpression(JsonParser)`;
class `software.amazon.event.ruler.RuleCompiler.ListBasedRuleCompiler`:
	related functions:
		method `private static void writeRules(Map<List<String>, List<Patterns>>, List<String>, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.RuleCompiler.processMatchExpression(JsonParser)`;
class `software.amazon.event.ruler.RuleCompiler`:
	related functions:
		method `private static Patterns processAnythingButListMatchExpression(JsonParser)` returns `software.amazon.event.ruler.Patterns`, related with `software.amazon.event.ruler.RuleCompiler.processMatchExpression(JsonParser)`;
		method `private static void writeRules(Map<String, List<Patterns>>, String, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.RuleCompiler.processMatchExpression(JsonParser)`;
		method `private static void parseObject(Map<String, List<Patterns>>, Path, JsonParser, boolean)` returns `void`, related with `software.amazon.event.ruler.RuleCompiler.processMatchExpression(JsonParser)`;
class `software.amazon.event.ruler.Event`:
	related functions:
		method `private void traverseObject(JsonParser, TreeMap<String, List<Value>>, Progress)` returns `void`, related with `software.amazon.event.ruler.RuleCompiler.processMatchExpression(JsonParser)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.