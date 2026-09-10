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
Here is the code of focal method, `javaNameToYamlName(String)`, with the focal class `FakeValuesService`:
```Java
package net.datafaker.service;
import java.util.Locale;
import net.datafaker.transformations.Schema;
import java.util.HashMap;
import net.datafaker.internal.helper.SingletonLocale;
import net.datafaker.providers.base.Address;
import java.net.MalformedURLException;
import java.util.IdentityHashMap;
import java.util.ArrayList;
import net.datafaker.providers.base.Name;
import net.datafaker.providers.base.BaseFaker;
import java.util.logging.Logger;
import net.datafaker.transformations.CsvTransformer;
import net.datafaker.transformations.Field;
import java.net.URL;
import java.nio.file.Files;
import net.datafaker.providers.base.ProviderRegistration;
import java.util.List;
import java.util.function.Supplier;
import net.datafaker.transformations.JsonTransformer;
import java.util.Collections;
import java.util.stream.Collectors;
import java.nio.file.Path;
import net.datafaker.providers.base.AbstractProvider;
import java.lang.reflect.Constructor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.lang.reflect.InvocationTargetException;
import com.mifmif.common.regex.Generex;
import java.lang.reflect.Array;
import static net.datafaker.transformations.Field.field;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Objects;
import net.datafaker.transformations.SimpleField;
import java.util.logging.Level;
import java.util.Map;
import java.util.Arrays;
import java.util.WeakHashMap;
public class FakeValuesService {
    public static final SingletonLocale DEFAULT_LOCALE = SingletonLocale.get(Locale.ENGLISH);
    private final Map<String, String> name2yaml = new WeakHashMap<>();
    private String javaNameToYamlName(String expression) {
        String result = name2yaml.get(expression);
        if (result != null) {
            return result;
        }
        final int length = expression.length();
        final boolean firstLetterUpperCase = (length > 0) && Character.isUpperCase(expression.charAt(0));
        int cnt = (firstLetterUpperCase) ? 1 : 0;
        for (int i = 1; i < length; i++) {
            if (Character.isUpperCase(expression.charAt(i))) {
                cnt++;
            }
        }
        if (cnt == 0) {
            name2yaml.put(expression, expression);
            return expression;
        }
        final char[] res = new char[length + (firstLetterUpperCase ? cnt - 1 : cnt)];
        int pos = 0;
        for (int i = 0; i < length; i++) {
            final char c = expression.charAt(i);
            if (cnt > 0) {
                if (Character.isUpperCase(c)) {
                    if (pos > 0) {
                        res[pos++] = '_';
                    }
                    res[pos++] = Character.toLowerCase(c);
                    cnt--;
                } else {
                    res[pos++] = c;
                }
            } else {
                res[pos++] = c;
            }
        }
        result = new String(res);
        name2yaml.put(expression, result);
        return result;
    }
public void addPath(Locale locale, Path path);
    public void addUrl(Locale locale, URL url);
    public String bothify(String string, FakerContext context);
    public String bothify(String input, FakerContext context, boolean isUpper);
    public String csv(int limit, String... columnExpressions);
    public String csv(String delimiter, char quote, boolean withHeader, int limit, String... columnExpressions);
    public String examplify(String example, FakerContext context);
    public String expression(String expression, BaseFaker faker, FakerContext context);
    public Object fetch(String key, FakerContext context);
    public Object fetchObject(String key, FakerContext context);
    public String fetchString(String key, FakerContext context);
    public String fileExpression(Path path, BaseFaker faker, FakerContext context);
    public String json(String... fieldExpressions);
    public String jsona(String... fieldExpressions);
    public String letterify(String letterString, FakerContext context);
    public String letterify(String letterString, FakerContext context, boolean isUpper);
    public String numerify(String numberString, FakerContext context);
    public static Class<?> primitiveToWrapper(final Class<?> cls);
    public String regexify(String regex, FakerContext context);
    public String resolve(String key, Object current, BaseFaker root, FakerContext context);
    public String resolve(String key, Object current, ProviderRegistration root, Supplier<String> exceptionMessage, FakerContext context);
    public String resolve(String key, AbstractProvider<?> provider, FakerContext context);
    protected String resolveExpression(String expression, Object current, ProviderRegistration root, FakerContext context);
    @SuppressWarnings("unchecked")
public String safeFetch(String key, FakerContext context, String defaultIfNull);
    public String templatify(String letterString, char char2replace, FakerContext context, String... options);
    public String templatify(String letterString, Map<Character, String[]> optionsMap, FakerContext context);
    public void updateFakeValuesInterfaceMap(List<SingletonLocale> locales);
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

api document of method javaNameToYamlName(String): 

     * @return a yaml style name like 'phone_number' from a java style name like 'PhoneNumber'
     

dependent classes: 
class `net.datafaker.service.FakeValuesService`:
	dependent fields:
		java.util.Map<java.lang.String, java.lang.String> net.datafaker.service.FakeValuesService.name2yaml;
	related functions:
		method `private String removeUnderscoreChars(String)` returns `java.lang.String`, related with `net.datafaker.service.FakeValuesService.javaNameToYamlName(String)`;
		method `private String[] split(String)` returns `java.lang.String[]`, related with `net.datafaker.service.FakeValuesService.javaNameToYamlName(String)`;
		method `private List<String> splitExpressions(String, int, int)` returns `java.util.List<java.lang.String>`, related with `net.datafaker.service.FakeValuesService.javaNameToYamlName(String)`;
		method `private String[] splitArguments(String)` returns `java.lang.String[]`, related with `net.datafaker.service.FakeValuesService.javaNameToYamlName(String)`;
class `net.datafaker.service.FakeValues`:
	related functions:
		method `private static String toJavaNames(String, boolean)` returns `java.lang.String`, related with `net.datafaker.service.FakeValuesService.javaNameToYamlName(String)`;
class `net.datafaker.transformations.sql.SqlTransformer`:
	related functions:
		method `private boolean isSqlQuoteIdentifierRequiredFor(String)` returns `boolean`, related with `net.datafaker.service.FakeValuesService.javaNameToYamlName(String)`;
class `net.datafaker.providers.base.BaseFaker`:
	related functions:
		method `public static AP getProvider(Class<AP>, Function<PR, AP>, PR)` returns `AP`, related with `net.datafaker.service.FakeValuesService.javaNameToYamlName(String)`;
class `net.datafaker.idnumbers.EsMXIdNumber`:
	related functions:
		method `private int getChecksum(String)` returns `int`, related with `net.datafaker.service.FakeValuesService.javaNameToYamlName(String)`, api document: 
     * Gets the Checksum.
     *
     * @param str input string
     * @return Checksum.
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.