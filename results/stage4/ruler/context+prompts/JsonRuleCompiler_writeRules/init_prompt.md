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
Here is the code of focal method, `writeRules(List<Map<String, List<Patterns>>>, String, JsonParser, boolean)`, with the focal class `JsonRuleCompiler_writeRules_Test`:
```Java
package software.amazon.event.ruler;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.HashMap;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import java.io.Reader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
public class JsonRuleCompiler {
    
    private static void writeRules(final List<Map<String, List<Patterns>>> rules, final String name, final JsonParser parser, final boolean withQuotes) throws IOException {
        JsonToken token;
        final List<Patterns> values = new ArrayList<>();
        while ((token = parser.nextToken()) != JsonToken.END_ARRAY) {
            switch (token) {
                case START_OBJECT :
                    values.add(processMatchExpression(parser));
                    break;
                case VALUE_STRING :
                    final String toMatch = parser.getText();
                    final Range ipRange = CIDR.ipToRangeIfPossible(toMatch);
                    if (ipRange != null) {
                        values.add(ipRange);
                    } else if (withQuotes) {
                        values.add(Patterns.exactMatch(('"' + toMatch) + '"'));
                    } else {
                        values.add(Patterns.exactMatch(toMatch));
                    }
                    break;
                case VALUE_NUMBER_FLOAT :
                case VALUE_NUMBER_INT :
                    /* If the rule specifies a match to a number, we'll insert matchers for both the
                     literal expression and the ComparableNumber form. But the number might not
                     be representable as a ComparableNumber, for example an AWS account number,
                     so make that condition survivable.
                     */
                    try {
                        values.add(Patterns.numericEquals(parser.getDoubleValue()));
                    } catch (Exception e) {
                        // no-op
                    }
                    values.add(Patterns.exactMatch(parser.getText()));
                    break;
                case VALUE_NULL :
                case VALUE_TRUE :
                case VALUE_FALSE :
                    values.add(Patterns.exactMatch(parser.getText()));
                    break;
                default :
                    barf(parser, "Match value must be String, number, true, false, or null");
            }
        } 
        if (values.isEmpty()) {
            barf(parser, "Empty arrays are not allowed");
        }
        // If the rules list is empty, add the first rule
        if (rules.isEmpty()) {
            rules.add(new HashMap<>());
        }
        rules.forEach(rule -> rule.put(name, values));
    }
private static void barf(final JsonParser parser, final String message) throws JsonParseException;
    public static String check(final byte[] source);
    public static String check(final InputStream source);
    public static String check(final Reader source);
    public static String check(final String source);
    public static List<Map<String, List<Patterns>>> compile(final byte[] source) throws IOException;
    public static List<Map<String, List<Patterns>>> compile(final InputStream source) throws IOException;
    public static List<Map<String, List<Patterns>>> compile(final Reader source) throws IOException;
    public static List<Map<String, List<Patterns>>> compile(final String source) throws IOException;
    private static Patterns processMatchExpression(final JsonParser parser) throws IOException;
}
```


@input{context information}

api document of class software.amazon.event.ruler.JsonRuleCompiler: 

 * Represents a updated compiler comparing to RuleCompiler class, it parses a rule described by a JSON string into
 * a list of Map which is composed of field Patterns, each Map represents one dedicated match branch in the rule.
 * By default, fields in the rule will be interpreted as "And" relationship implicitly and will result into one Map with
 * fields of Patterns, but once it comes across the "$or" relationship which is explicitly decorated by "$or" primitive
 * array, for each object in "$or" array, this Compiler will fork a new Map to include all fields of patterns in
 * that "or" branch, and when it goes over all branches in "$or" relationship, it will end up with the list of Map
 * and each Map represents a match branch composed of fields of patterns.
 * i.e.: a rule which describe the effect of ("source" AND ("metricName" OR ("metricType AND "namespace") OR "scope"))
 * its JSON format String is:
 *   {
 *     "source": [ "aws.cloudwatch" ],
 *     "$or": [
 *       { "metricName": [ "CPUUtilization", "ReadLatency" ] },
 *       {
 *         "metricType": [ "MetricType" ] ,
 *         "namespace": [ "AWS/EC2", "AWS/ES" ]
 *       },
 *       { "scope": [ "Service" ] }
 *     ]
 *   }
 * It will be parsed to a list of match branches composed by a Map of fields patterns, e.g. for above rule, there will
 * result 3 match branches:
 * [
 *   {source=[\"aws.cloudwatch\"], metricName=[\"CPUUtilization\",\"ReadLatency\"]},
 *   {namespace=[\"AWS/EC2\", \"AWS/ES\"], metricType=[\"MetricType\"], source=[\"aws.cloudwatch\"]},
 *   {source=[\"aws.cloudwatch\"], scope=[\"Service\"]}
 * ]
 

constructors for class `software.amazon.event.ruler.JsonRuleCompiler`: 
params: 
body:
```java
    private JsonRuleCompiler() { }

```

invoke examples of focal method: 
example 1:
```java
    private static void parseObject(final List<Map<String, List<Patterns>>> rules,
                                    final Path path,
                                    final JsonParser parser,
                                    final boolean withQuotes) throws IOException {

        boolean fieldsPresent = false;
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            fieldsPresent = true;
            final String stepName = parser.getCurrentName();
            if (stepName.equals(Constants.OR_RELATIONSHIP_KEYWORD)) {
            switch (parser.nextToken()) {
            case START_OBJECT:
                path.push(stepName);
                parseObject(rules, path, parser, withQuotes);
    }
    private static void parseObject(final List<Map<String, List<Patterns>>> rules,
                                    final Path path,
                                    final JsonParser parser,
                                    final boolean withQuotes) throws IOException {

        boolean fieldsPresent = false;
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            fieldsPresent = true;
            final String stepName = parser.getCurrentName();
            if (stepName.equals(Constants.OR_RELATIONSHIP_KEYWORD)) {
            switch (parser.nextToken()) {
            case START_ARRAY:
                writeRules(rules, path.extendedName(stepName), parser, withQuotes);
    }
```
example 2:
```java
    private static void parseObjectInsideOrRelationship(final List<Map<String, List<Patterns>>> rules,
                                                        final Path path,
                                                        final JsonParser parser,
                                                        final boolean withQuotes) throws IOException {

        boolean fieldsPresent = false;
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            fieldsPresent = true;
            final String stepName = parser.getCurrentName();
            if (stepName.equals(Constants.OR_RELATIONSHIP_KEYWORD)) {
            if (Constants.RESERVED_FIELD_NAMES_IN_OR_RELATIONSHIP.contains(stepName)) {
            switch (parser.nextToken()) {
                case START_OBJECT:
                    path.push(stepName);
                    parseObjectInsideOrRelationship(rules, path, parser, withQuotes);
    }
    private static void parseObjectInsideOrRelationship(final List<Map<String, List<Patterns>>> rules,
                                                        final Path path,
                                                        final JsonParser parser,
                                                        final boolean withQuotes) throws IOException {

        boolean fieldsPresent = false;
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            fieldsPresent = true;
            final String stepName = parser.getCurrentName();
            if (stepName.equals(Constants.OR_RELATIONSHIP_KEYWORD)) {
            if (Constants.RESERVED_FIELD_NAMES_IN_OR_RELATIONSHIP.contains(stepName)) {
            switch (parser.nextToken()) {
                case START_ARRAY:
                    writeRules(rules, path.extendedName(stepName), parser, withQuotes);
    }
```
example 3:
```java
    public static String check(final Reader source) {
        try {
            doCompile(JSON_FACTORY.createParser(source));
    }
    private static List<Map<String, List<Patterns>>> doCompile(final JsonParser parser) throws IOException {
        final Path path = new Path();
        final List<Map<String, List<Patterns>>> rules = new ArrayList<>();
        if (parser.nextToken() != JsonToken.START_OBJECT) {
        parseObject(rules, path, parser, true);
    }
    private static void parseObject(final List<Map<String, List<Patterns>>> rules,
                                    final Path path,
                                    final JsonParser parser,
                                    final boolean withQuotes) throws IOException {

        boolean fieldsPresent = false;
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            fieldsPresent = true;
            final String stepName = parser.getCurrentName();
            if (stepName.equals(Constants.OR_RELATIONSHIP_KEYWORD)) {
            switch (parser.nextToken()) {
            case START_ARRAY:
                writeRules(rules, path.extendedName(stepName), parser, withQuotes);
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
class JsonRuleCompiler_writeRules_Test {
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