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
Here is the code of focal method, `matchRouter(String, String)`, with the focal class `AbstractRouterGroup`:
```Java
package org.flmelody.core;
import org.flmelody.core.ws.WebSocketWindwardContext;
import java.util.Set;
import java.util.HashMap;
import java.util.regex.Matcher;
import org.flmelody.core.context.support.SseWindwardContext;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.flmelody.core.context.SimpleWindwardContext;
import java.util.function.Consumer;
import org.flmelody.core.context.WindwardContext;
import java.util.List;
import java.util.function.Supplier;
import org.flmelody.support.FunctionDefinition;
import java.util.Collections;
import java.util.regex.Pattern;
import org.flmelody.core.wind.event.RouterBindEvent;
import org.flmelody.util.UrlUtil;
import org.flmelody.core.context.ResourceWindwardContext;
import org.flmelody.core.context.EnhancedWindwardContext;
import java.util.StringTokenizer;
import org.flmelody.support.EnhancedFunction;
import org.flmelody.core.exception.WindwardException;
import org.flmelody.core.context.support.HttpKind;
import java.util.Map;
import org.flmelody.util.AntPathMatcher;
import org.flmelody.core.plugin.resource.ResourcePluginProxy;
import org.flmelody.core.exception.RouterMappingException;
public abstract class AbstractRouterGroup<M> implements RouterGroup<M> {
    private String groupPath;
    private final Map<String, Map<String, ? super Object>> routers = Collections.synchronizedMap(new LinkedHashMap<>(2 << 3));
    private final AntPathMatcher antPathMatcher = AntPathMatcher.newBuild().build();
    private final Map<String, Boolean> matchedRouter = new ConcurrentHashMap<>();
    protected boolean resourceRouter;
    @Override
    public <R> R matchRouter(String relativePath, String method) {
        if (!relativePath.startsWith(groupPath)) {
            return null;
        }
        if (relativePath.endsWith(UrlUtil.SLASH) && (!UrlUtil.SLASH.equals(relativePath))) {
            relativePath = relativePath.replaceFirst("/$", "");
        }
        relativePath = relativePath.replaceAll("[{}]", "");
        if (!routers.containsKey(relativePath)) {
            Set<String> routerKeys = routers.keySet();
            for (String routerKey : routerKeys) {
                Pattern compiledPattern = Pattern.compile("\\{(.*?)}");
                Matcher compiledMatcher = compiledPattern.matcher(routerKey);
                if (!compiledMatcher.find()) {
                    // If it's a GET request, try to match it again using Ant-style path patterns
                    if (HttpMethod.GET.name().equalsIgnoreCase(method) && resourceRouter) {
                        Boolean result = matchedRouter.get(relativePath);
                        if (Boolean.TRUE.equals(result)) {
                            // noinspection unchecked
                            return ((R) (routers.get(routerKey).get(method)));
                        } else {
                            boolean matched = antPathMatcher.isMatch(routerKey, relativePath);
                            matchedRouter.put(relativePath, matched);
                            if (matched) {
                                // noinspection unchecked
                                return ((R) (routers.get(routerKey).get(method)));
                            }
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
                String routerRegex = routerKey.replaceAll("\\{(.*?)}", "(.+)");
                if (relativePath.matches(routerRegex)) {
                    // Expected router
                    int pathCount = new StringTokenizer(relativePath, UrlUtil.SLASH).countTokens();
                    int routerCount = new StringTokenizer(routerRegex, UrlUtil.SLASH).countTokens();
                    if (pathCount != routerCount) {
                        return null;
                    }
                    FunctionMetaInfo<?> functionMetaInfo = ((FunctionMetaInfo<?>) (routers.get(routerKey).get(method)));
                    if (functionMetaInfo == null) {
                        return null;
                    }
                    Map<String, Object> pathVariables = functionMetaInfo.getPathVariables();
                    List<String> keys = new ArrayList<>(pathVariables.keySet());
                    Pattern pattern = Pattern.compile(routerRegex);
                    Matcher matcher = pattern.matcher(relativePath);
                    int groupCount = matcher.groupCount();
                    while (matcher.find()) {
                        for (int j = 0; j < Math.min(groupCount, keys.size()); j++) {
                            pathVariables.put(keys.get(j), matcher.group(j + 1));
                        }
                    } 
                    // noinspection unchecked
                    return ((R) (functionMetaInfo));
                }
            }
            return null;
        }
        // noinspection unchecked
        return ((R) (routers.get(relativePath).get(method)));
    }
@Override
public RouterGroup<M> delete(String relativePath, Consumer<SimpleWindwardContext> consumer);
    @Override
public <R> RouterGroup<M> delete(String relativePath, Supplier<R> supplier);
    @Override
public <C extends EnhancedWindwardContext & HttpKind> RouterGroup<M> delete(String relativePath, EnhancedFunction<C, ?> function);
    @Override
public M end();
    @Override
public RouterGroup<M> get(String relativePath, Consumer<SimpleWindwardContext> consumer);
    @Override
public <R> RouterGroup<M> get(String relativePath, Supplier<R> supplier);
    @Override
public <C extends EnhancedWindwardContext & HttpKind> RouterGroup<M> get(String relativePath, EnhancedFunction<C, ?> function);
    @Override
public RouterGroup<M> http(HttpMethod httpMethod, String relativePath, Consumer<SimpleWindwardContext> consumer);
    @Override
public <R> RouterGroup<M> http(HttpMethod httpMethod, String relativePath, Supplier<R> supplier);
    @Override
public <C extends EnhancedWindwardContext & HttpKind> RouterGroup<M> http(HttpMethod httpMethod, String relativePath, EnhancedFunction<C, ?> function);
    @Override
public RouterGroup<M> post(String relativePath, Consumer<SimpleWindwardContext> consumer);
    @Override
public <R> RouterGroup<M> post(String relativePath, Supplier<R> supplier);
    @Override
public <C extends EnhancedWindwardContext & HttpKind> RouterGroup<M> post(String relativePath, EnhancedFunction<C, ?> function);
    @Override
public RouterGroup<M> put(String relativePath, Consumer<SimpleWindwardContext> consumer);
    @Override
public <R> RouterGroup<M> put(String relativePath, Supplier<R> supplier);
    @Override
public <C extends EnhancedWindwardContext & HttpKind> RouterGroup<M> put(String relativePath, EnhancedFunction<C, ?> function);
    @Override
public RouterGroup<M> resources(String staticResourceLocation, String... pathPatterns);
    protected void setGroupPath(String groupPath);
    @Override
public RouterGroup<M> sse(String relativePath, EnhancedFunction<SseWindwardContext, SseEjector> function);
    @Override
public RouterGroup<M> ws(String relativePath, Consumer<WebSocketWindwardContext> consumer);
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

api document of class org.flmelody.core.AbstractRouterGroup: 

 * @author esotericman
 

return type: 
R

dependent classes: 
class `org.flmelody.util.UrlUtil`:
	dependent fields:
		java.lang.String org.flmelody.util.UrlUtil.SLASH;
	related functions:
		method `public static String buildUrl(String[])` returns `java.lang.String`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;
class `org.flmelody.core.AbstractRouterGroup`:
	dependent fields:
		java.util.Map<java.lang.String, java.util.Map<java.lang.String, ? super java.lang.Object>> org.flmelody.core.AbstractRouterGroup.routers;
		java.util.Map<java.lang.String, java.lang.Boolean> org.flmelody.core.AbstractRouterGroup.matchedRouter;
		boolean org.flmelody.core.AbstractRouterGroup.resourceRouter;
		java.lang.String org.flmelody.core.AbstractRouterGroup.groupPath;
		org.flmelody.util.AntPathMatcher org.flmelody.core.AbstractRouterGroup.antPathMatcher;
	related functions:
		method `public RouterGroup<M> get(String, Supplier<R>)` returns `org.flmelody.core.RouterGroup<M>`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;
		method `private Map<String, Object> checkPlaceholder(String)` returns `java.util.Map<java.lang.String, java.lang.Object>`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;
		method `public RouterGroup<M> resources(String, String[])` returns `org.flmelody.core.RouterGroup<M>`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;
		method `private void registerRouter(String, String, I, Class<? extends WindwardContext>)` returns `void`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;
		method `public RouterGroup<M> ws(String, Consumer<WebSocketWindwardContext>)` returns `org.flmelody.core.RouterGroup<M>`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;
		method `protected void setGroupPath(String)` returns `void`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;
class `org.flmelody.core.HttpMethod`:
	dependent fields:
		org.flmelody.core.HttpMethod org.flmelody.core.HttpMethod.GET;
	
class `org.flmelody.core.plugin.resource.FixedStaticResourcePlugin`:
	related functions:
		method `protected String findResource(String, StaticResource)` returns `java.lang.String`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;
class `org.flmelody.core.netty.handler.HttpServerHandler`:
	related functions:
		method `private Map<String, List<String>> prepareHeaders(HttpHeaders)` returns `java.util.Map<java.lang.String, java.util.List<java.lang.String>>`, related with `org.flmelody.core.AbstractRouterGroup.matchRouter(String, String)`;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.