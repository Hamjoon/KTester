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
Here is the code of focal method, `resolveExpression(String, String[], Object, ProviderRegistration, FakerContext)`, with the focal class `FakeValuesService`:
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
    private Object resolveExpression(String directive, String[] args, Object current, ProviderRegistration root, FakerContext context) {
        // name.name (resolve locally)
        // Name.first_name (resolve to faker.name().firstName())
        if (directive.isEmpty()) {
            return directive;
        }
        final int dotIndex = getDotIndex(directive);
        Object resolved;
        if (args.length == 0) {
            // resolve method references on CURRENT object like #{number_between '1','10'} on Number or
            // #{ssn_valid} on IdNumber
            if (dotIndex == (-1)) {
                if (current instanceof AbstractProvider) {
                    final Method method = BaseFaker.getMethod(((AbstractProvider<?>) (current)), directive);
                    if (method != null) {
                        try {
                            return method.invoke(current);
                        } catch (Exception e) {
                            throw new RuntimeException((e + " ") + Arrays.toString(args));
                        }
                    }
                }
                Supplier<Object> supplier = resolveFromMethodOn(current, directive, args);
                if ((supplier != null) && ((resolved = supplier.get()) != null)) {
                    // expression2function.put(expression, supplier);
                    return resolved;
                }
            }
            if (dotIndex > 0) {
                final AbstractProvider<?> ap = BaseFaker.getProvider(directive.substring(0, dotIndex), context);
                final Method method = BaseFaker.getMethod(ap, directive.substring(dotIndex + 1));
                if (method != null) {
                    try {
                        return method.invoke(ap);
                    } catch (Exception e) {
                        throw new RuntimeException((e + " ") + Arrays.toString(args));
                    }
                }
            }
        }
        final String simpleDirective = ((dotIndex >= 0) || (current == null)) ? directive : (classNameToYamlName(current) + ".") + directive;
        // simple fetch of a value from the yaml file. the directive may have been mutated
        // such that if the current yml object is car: and directive is #{wheel} then
        // car.wheel will be looked up in the YAML file.
        Supplier<Object> supplier = () -> safeFetch(simpleDirective, context, null);
        resolved = supplier.get();
        if (resolved != null) {
            // expression2function.put(expression, supplier);
            return resolved;
        }
        // resolve method references on faker object like #{regexify '[a-z]'}
        if (((dotIndex == (-1)) && (root != null)) && ((current == null) || (root.getClass() != current.getClass()))) {
            supplier = resolveFromMethodOn(root, directive, args);
            if ((supplier != null) && ((resolved = supplier.get()) != null)) {
                // expression2function.put(expression, supplier);
                return resolved;
            }
        }
        // Resolve Faker Object method references like #{ClassName.method_name}
        if (dotIndex >= 0) {
            supplier = resolveFakerObjectAndMethod(root, directive, dotIndex, args);
            if ((supplier != null) && ((resolved = supplier.get()) != null)) {
                // expression2function.put(expression, supplier);
                return resolved;
            }
        }
        // last ditch effort.  Due to Ruby's dynamic nature, something like 'Address.street_title' will resolve
        // because 'street_title' is a dynamic method on the Address object.  We can't do this in Java so we go
        // thru the normal resolution above, but if we will can't resolve it, we once again do a 'safeFetch' as we
        // did first but FIRST we change the Object reference Class.method_name with a yml style internal reference ->
        // class.method_name (lowercase)
        if (dotIndex >= 0) {
            supplier = () -> safeFetch(javaNameToYamlName(simpleDirective), context, null);
            resolved = supplier.get();
        }
        return resolved;
    }
public void addPath(Locale locale, Path path);
    public void addUrl(Locale locale, URL url);
    public String bothify(String string, FakerContext context);
    public String bothify(String input, FakerContext context, boolean isUpper);
    private String classNameToYamlName(Object current);
    public String csv(int limit, String... columnExpressions);
    public String csv(String delimiter, char quote, boolean withHeader, int limit, String... columnExpressions);
    public String examplify(String example, FakerContext context);
    public String expression(String expression, BaseFaker faker, FakerContext context);
    public Object fetch(String key, FakerContext context);
    public Object fetchObject(String key, FakerContext context);
    public String fetchString(String key, FakerContext context);
    public String fileExpression(Path path, BaseFaker faker, FakerContext context);
    private int getDotIndex(String directive);
    private String javaNameToYamlName(String expression);
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
    private Supplier<Object> resolveFakerObjectAndMethod(ProviderRegistration faker, String key, int dotIndex, String[] args);
    private Supplier<Object> resolveFromMethodOn(Object obj, String directive, String[] args);
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

api document of method resolveExpression(String, String[], Object, ProviderRegistration, FakerContext): 

     * Search Order
     * <ul>
     *  <li>Search for methods on the current object</li>
     *  <li>local keys in Yaml File</li>
     *  <li>Search for methods on faker child objects</li>
     *   <li>Search for keys in yaml file by transforming object reference to yaml reference</li>
     * </ul>
     *
     * @return null if unable to resolve
     

dependent classes: 
class `net.datafaker.service.FakerContext`:
	
class `net.datafaker.providers.base.Finance`:
	related functions:
		method `private static String calculateIbanChecksum(String, String)` returns `java.lang.String`, related with `net.datafaker.service.FakeValuesService.resolveExpression(String, String[], Object, ProviderRegistration, FakerContext)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.