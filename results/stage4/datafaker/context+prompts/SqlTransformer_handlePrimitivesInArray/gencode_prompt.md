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
Here is the code of focal method, `handlePrimitivesInArray(Class<?>, Object)`, with the focal class `SqlTransformer`:
```Java
package net.datafaker.transformations.sql;
import net.datafaker.sequence.FakeSequence;
import net.datafaker.transformations.Transformer;
import java.util.stream.Collectors;
import static net.datafaker.transformations.sql.SqlTransformer.SQLKeyWords.ARRAY;
import net.datafaker.transformations.CompositeField;
import net.datafaker.transformations.Schema;
import net.datafaker.sequence.FakeStream;
import java.util.ArrayList;
import static net.datafaker.transformations.sql.SqlTransformer.SQLKeyWords.INSERT_INTO;
import static net.datafaker.transformations.sql.SqlTransformer.SQLKeyWords.NULL;
import net.datafaker.transformations.Field;
import static net.datafaker.transformations.sql.SqlTransformer.SQLKeyWords.MULTISET;
import java.util.Collection;
import static net.datafaker.transformations.sql.SqlTransformer.SQLKeyWords.ROW;
import net.datafaker.transformations.SimpleField;
import java.util.List;
import java.util.stream.Stream;
import static net.datafaker.transformations.sql.SqlTransformer.SQLKeyWords.VALUES;
import java.util.StringJoiner;
public class SqlTransformer<IN> implements Transformer<IN, CharSequence> {
    
    private String handlePrimitivesInArray(Class<?> componentType, Object value) {
        StringJoiner joiner = new StringJoiner(", ");
        if (componentType == byte.class) {
            byte[] array = ((byte[]) (value));
            for (int j = 0; j < array.length; j++) {
                joiner.add(String.valueOf(array[j]));
            }
        }
        if (componentType == short.class) {
            short[] array = ((short[]) (value));
            for (int j = 0; j < array.length; j++) {
                joiner.add(String.valueOf(array[j]));
            }
        }
        if (componentType == boolean.class) {
            boolean[] array = ((boolean[]) (value));
            for (int j = 0; j < array.length; j++) {
                joiner.add(String.valueOf(array[j]));
            }
        } else if (componentType == int.class) {
            int[] array = ((int[]) (value));
            for (int j = 0; j < array.length; j++) {
                joiner.add(String.valueOf(array[j]));
            }
        } else if (componentType == long.class) {
            long[] array = ((long[]) (value));
            for (int j = 0; j < array.length; j++) {
                joiner.add(String.valueOf(array[j]));
            }
        } else if (componentType == float.class) {
            float[] array = ((float[]) (value));
            for (int j = 0; j < array.length; j++) {
                joiner.add(String.valueOf(array[j]));
            }
        } else if (componentType == double.class) {
            double[] array = ((double[]) (value));
            for (int j = 0; j < array.length; j++) {
                joiner.add(String.valueOf(array[j]));
            }
        }
        return joiner.toString();
    }
@Override
public CharSequence apply(IN input, Schema<IN, ?> schema);
    @Override
public CharSequence apply(IN input, Schema<IN, ?> schema, int rowId);
    public static <IN> SqlTransformer.SqlTransformerBuilder<IN> builder();
    @Override
public String generate(Iterable<IN> input, Schema<IN, ?> schema);
    @Override
public String generate(Schema<IN, ?> schema, int limit);
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

dependent classes: 
class `net.datafaker.providers.base.Finance`:
	related functions:
		method `private static String calculateIbanChecksum(String, String)` returns `java.lang.String`, related with `net.datafaker.transformations.sql.SqlTransformer.handlePrimitivesInArray(Class<?>, Object)`;
class `net.datafaker.providers.base.Internet`:
	related functions:
		method `public String image()` returns `java.lang.String`, related with `net.datafaker.transformations.sql.SqlTransformer.handlePrimitivesInArray(Class<?>, Object)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.