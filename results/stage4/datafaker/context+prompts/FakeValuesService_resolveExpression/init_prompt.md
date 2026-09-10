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
Here is the code of focal method, `resolveExpression(String, String[], Object, ProviderRegistration, FakerContext)`, with the focal class `FakeValuesService_resolveExpression_Test`:
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


@input{context information}

constructors for class `net.datafaker.service.FakeValuesService`: 
params: 
body:
```java
    public FakeValuesService() {
    }

```

parameters in constructors and focal method: 
class `net.datafaker.providers.base.ProviderRegistration`:
constructor:
```java
```
class `net.datafaker.service.FakerContext`:
api document : 
 * Contains the changeable Faker's part which currently contains {@link Locale} and {@link RandomService}.
 
constructor:
```java
    public FakerContext(Locale locale, RandomService randomService) {
        this.sLocale = SingletonLocale.get(locale);
        this.randomService = randomService;
        setCurrentLocale(locale);
    }

```

existing test class: 
```java
package net.datafaker.service;

import net.datafaker.AbstractFakerTest;
import net.datafaker.internal.helper.SingletonLocale;
import net.datafaker.providers.base.BaseFaker;
import net.datafaker.providers.base.Superhero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FakeValuesServiceTest extends AbstractFakerTest {

    private static final Long MILLIS_IN_AN_HOUR = 1000 * 60 * 60L;
    private static final Long MILLIS_IN_A_DAY = MILLIS_IN_AN_HOUR * 24;

    @Spy
    private BaseFaker mockedFaker;

    @Mock
    private RandomService randomService;

    private FakeValuesService fakeValuesService;
    private FakerContext context;

    @BeforeEach
    protected void before() {
        super.before();

        // always return the first element
        when(randomService.nextInt(anyInt())).thenReturn(0);
        context = new FakerContext(new Locale("test"), randomService);
        when(mockedFaker.getContext()).thenReturn(context);

        fakeValuesService = Mockito.spy(new FakeValuesService());
        fakeValuesService.updateFakeValuesInterfaceMap(context.getLocaleChain());
    }

    @Test
    void fetchStringShouldReturnValue() {
        assertThat(fakeValuesService.fetchString("property.dummy", mockedFaker.getContext())).isEqualTo("x");
    }

    @Test
    void fetchShouldReturnValue() {
        assertThat(fakeValuesService.fetch("property.dummy", mockedFaker.getContext())).isEqualTo("x");
    }

    @Test
    void fetchObjectShouldReturnValue() {
        assertThat((Iterable<?>) fakeValuesService.fetchObject("property.dummy", mockedFaker.getContext())).isEqualTo(Arrays.asList("x", "y", "z"));
    }

    @Test
    void safeFetchShouldReturnValueInList() {
        doReturn(0).when(randomService).nextInt(Mockito.anyInt());
        assertThat(fakeValuesService.safeFetch("property.dummy", mockedFaker.getContext(), null)).isEqualTo("x");
    }

    @Test
    void safeFetchShouldReturnSimpleList() {
        assertThat(fakeValuesService.safeFetch("property.simple", mockedFaker.getContext(), null)).isEqualTo("hello");
    }

    @Test
    void safeFetchShouldReturnEmptyStringWhenPropertyDoesntExist() {
        assertThat(fakeValuesService.safeFetch("property.dummy2", mockedFaker.getContext(), "")).isEmpty();
    }

    @Test
    void bothify2Args() {
        final DummyService dummy = mock(DummyService.class);

        BaseFaker f = new BaseFaker();

        String value = fakeValuesService.resolve("property.bothify_2", dummy, f, context);
......
    }

    /**
     * Two things are important here:
     * 1) the message in the exception should be USEFUL
     * 2) a {@link RuntimeException} should be thrown.
     * <p>
     * if the message changes, it's ok to update the test provided
     * the two conditions above are still true.
     */
    @Test
    void expressionCompletelyUnresolvable() {
        expressionShouldFailWithContaining("#{x}", "Unable to resolve #{x} directive");
    }

    private void expressionShouldFailWithContaining(String expression, String errorMessagePattern) {
        assertThatThrownBy(() -> fakeValuesService.expression(expression, faker, context))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining(errorMessagePattern);
    }

    @Test
    void resolveUsingTheSameKeyTwice() {
        // #{hello} -> DummyService.hello

        // given
        final DummyService dummy = mock(DummyService.class);
        when(dummy.hello()).thenReturn("1").thenReturn("2");

        // when
        final String actual = fakeValuesService.resolve("property.sameResolution", dummy, mockedFaker, mockedFaker.getContext());

        // then
        assertThat(actual).isEqualTo("1 2");
    }

    public static class DummyService {
        public String firstName() {
            return "John";
        }

        public String lastName() {
            return "Smith";
        }

        public String hello() {
            return "Hello";
        }
    }
}
```

invoke examples of focal method: 
example 1:
```java
    protected String resolveExpression(String expression, Object current, ProviderRegistration root, FakerContext context) {
        int cnt = 0;
        final int expressionLength = expression.length();
        for (int i = 0; i < expressionLength; i++) {
        if (cnt == 0) {
        final List<String> expressions = splitExpressions(expression, cnt, expressionLength);
        final StringBuilder result = new StringBuilder(expressions.size() * expressionLength);
        for (int i = 0; i < expressions.size(); i++) {
            final String expr = expressions.get(i);
            if (i % 2 == 0) {
            int j = 0;
            final int length = expr.length();
            while (j < length && !Character.isWhitespace(expr.charAt(j))) j++;
            String directive = expr.substring(0, j);
            while (j < length && Character.isWhitespace(expr.charAt(j))) j++;
            final String arguments = j == length ? "" : expr.substring(j);
            final String[] args = splitArguments(arguments);
            final Object resolved = resolveExpression(directive, args, current, root, context);
    }
```



@input{test class template}
```java
package net.datafaker.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class FakeValuesService_resolveExpression_Test {
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