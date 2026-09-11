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
Here is the code of focal method, `handlePrimitivesInArray(Class<?>, Object)`, with the focal class `SqlTransformer_handlePrimitivesInArray_Test`:
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


@input{context information}

constructors for class `net.datafaker.transformations.sql.SqlTransformer`: 
params: net.datafaker.transformations.sql.SqlDialect dialect 
net.datafaker.transformations.sql.Casing casing 
net.datafaker.transformations.sql.SqlTransformer.Case keywordCase 
body:
```java
    private SqlTransformer(String schemaName, String tableName, char quote, SqlDialect dialect, String sqlIdentifier,
                           Casing casing, boolean withBatchMode, int batchSize, Case keywordCase, boolean forceSqlQuoteIdentifierUsage) {
        this.schemaName = schemaName;
        this.quote = quote;
        this.dialect = dialect;
        this.openSqlIdentifier = sqlIdentifier.charAt(0);
        this.closeSqlIdentifier = sqlIdentifier.length() == 1 ? sqlIdentifier.charAt(0) : sqlIdentifier.charAt(1);
        this.tableName = tableName;
        this.casing = casing;
        this.withBatchMode = withBatchMode;
        this.batchSize = batchSize;
        this.keywordCase = keywordCase;
        this.forceSqlQuoteIdentifierUsage = forceSqlQuoteIdentifierUsage;
    }

```

parameters in constructors and focal method: 
class `net.datafaker.transformations.sql.SqlDialect`:
constructor:
```java
    SqlDialect(String sqlQuoteIdentifier, Casing casing, TriFunction<Supplier<String>, Supplier<String>, SqlTransformer.Case, String> batchFirstRow,
               TriFunction<Supplier<String>, Supplier<String>, SqlTransformer.Case, String> batchOtherRows, Function<SqlTransformer.Case, String> lastBatchRow) {
        this.sqlQuoteIdentifier = sqlQuoteIdentifier;
        this.unquotedCasing = casing;
        this.batchFirstRow = batchFirstRow;
        this.batchOtherRows = batchOtherRows;
        this.lastBatchRow = lastBatchRow;
    }

    SqlDialect(String sqlQuoteIdentifier, TriFunction<Supplier<String>, Supplier<String>, SqlTransformer.Case, String> batchFirstRow,
               TriFunction<Supplier<String>, Supplier<String>, SqlTransformer.Case, String> batchOtherRows, Function<SqlTransformer.Case, String> lastBatchRow) {
        this(sqlQuoteIdentifier, DEFAULT_CASING, batchFirstRow, batchOtherRows, lastBatchRow);
    }

    SqlDialect(String sqlQuoteIdentifier, Casing casing) {
        this(sqlQuoteIdentifier, casing,
            DEFAULT_FIRST_ROW,
            DEFAULT_OTHER_ROWS,
            s -> "");
    }

    SqlDialect(String sqlQuoteIdentifier) {
        this(sqlQuoteIdentifier, DEFAULT_CASING);
    }

```
class `net.datafaker.transformations.sql.Casing`:
constructor:
```java
```
class `net.datafaker.transformations.sql.SqlTransformer.Case`:
constructor:
```java
```

invoke examples of focal method: 
example 1:
```java
    @Override
    public CharSequence apply(IN input, Schema<IN, ?> schema, int rowId) {
        //noinspection unchecked
        Field<?, ? extends CharSequence>[] fields = (Field<?, ? extends CharSequence>[]) schema.getFields();
        if (fields.length == 0) {
        StringBuilder sb = new StringBuilder();
        if (withBatchMode) {
            if (rowId == 0 || batchSize > 0 && rowId % batchSize == 0) {
                sb.append(
                    SqlDialect.getFirstRow(
                        dialect, () -> appendTableInfo(fields), () -> addValues(input, fields), keywordCase));
                sb.append(",")
                    .append(LINE_SEPARATOR)
                    .append(
                        SqlDialect.getOtherRow(
                            dialect, () -> appendTableInfo(fields), () -> addValues(input, fields), keywordCase));
            sb.append(INSERT_INTO.getValue(keywordCase))
                .append(" ")
                .append(appendTableInfo(fields))
                .append(" ")
                .append(VALUES.getValue(keywordCase))
                .append(" ")
                .append(addValues(input, fields));
    }
    private String addValues(IN input, Field<?, ? extends CharSequence>[] fields) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i] instanceof SimpleField) {
                Object value = ((SimpleField<Object, ? extends CharSequence>) fields[i]).transform(input);
                Class<?> clazz = value == null ? null : value.getClass();
                if (value == null
                    || value instanceof Number
                    || value instanceof Boolean
                    || clazz.isPrimitive()) {
                } else if (clazz.isArray()) {
                    result.append(ARRAY.getValue(keywordCase)).append("[");
                    final Class<?> componentType = clazz.getComponentType();
                    result.append(componentType.isPrimitive()
                        ? handlePrimitivesInArray(componentType, value)
                        : handleObjectInArray(value));
    }
```
example 2:
```java
    @Override
    public CharSequence apply(IN input, Schema<IN, ?> schema, int rowId) {
        //noinspection unchecked
        Field<?, ? extends CharSequence>[] fields = (Field<?, ? extends CharSequence>[]) schema.getFields();
        if (fields.length == 0) {
        StringBuilder sb = new StringBuilder();
        if (withBatchMode) {
            if (rowId == 0 || batchSize > 0 && rowId % batchSize == 0) {
                sb.append(
                    SqlDialect.getFirstRow(
                        dialect, () -> appendTableInfo(fields), () -> addValues(input, fields), keywordCase));
                sb.append(",")
                    .append(LINE_SEPARATOR)
                    .append(
                        SqlDialect.getOtherRow(
                            dialect, () -> appendTableInfo(fields), () -> addValues(input, fields), keywordCase));
            sb.append(INSERT_INTO.getValue(keywordCase))
                .append(" ")
                .append(appendTableInfo(fields))
                .append(" ")
                .append(VALUES.getValue(keywordCase))
                .append(" ")
                .append(addValues(input, fields));
    }
    private String addValues(IN input, Field<?, ? extends CharSequence>[] fields) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i] instanceof SimpleField) {
                Object value = ((SimpleField<Object, ? extends CharSequence>) fields[i]).transform(input);
                Class<?> clazz = value == null ? null : value.getClass();
                if (value == null
                    || value instanceof Number
                    || value instanceof Boolean
                    || clazz.isPrimitive()) {
                } else if (clazz.isArray()) {
                } else if (value instanceof Collection) {
                    result.append(handleObject(value));
    }
    private String handleObject(Object value) {
        StringBuilder result = new StringBuilder();
        if (value == null) {
            if (value.getClass().isArray()) {
                result.append(ARRAY.getValue(keywordCase)).append("[");
                final Class<?> componentType = value.getClass().getComponentType();
                result.append(componentType.isPrimitive()
                    ? handlePrimitivesInArray(componentType, value)
                    : handleObjectInArray(value));
    }
```
example 3:
```java
    private String handleObject(Object value) {
        StringBuilder result = new StringBuilder();
        if (value == null) {
            if (value.getClass().isArray()) {
                result.append(ARRAY.getValue(keywordCase)).append("[");
                final Class<?> componentType = value.getClass().getComponentType();
                result.append(componentType.isPrimitive()
                    ? handlePrimitivesInArray(componentType, value)
                    : handleObjectInArray(value));
    }
    private String handleObjectInArray(Object value) {
        StringBuilder result = new StringBuilder();
        Object[] array = (Object[]) value;
        for (int j = 0; j < array.length; j++) {
            result.append(handleObject(array[j]));
    }
    private String handleObject(Object value) {
        StringBuilder result = new StringBuilder();
        if (value == null) {
            if (value.getClass().isArray()) {
                result.append(ARRAY.getValue(keywordCase)).append("[");
                final Class<?> componentType = value.getClass().getComponentType();
                result.append(componentType.isPrimitive()
                    ? handlePrimitivesInArray(componentType, value)
                    : handleObjectInArray(value));
    }
```



@input{test class template}
```java
package net.datafaker.transformations.sql;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class SqlTransformer_handlePrimitivesInArray_Test {
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