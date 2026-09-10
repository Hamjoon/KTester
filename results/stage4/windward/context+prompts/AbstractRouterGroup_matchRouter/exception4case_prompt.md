You are a professional software tester. Write test cases for a focal method in a target class.

**Instructions:**
1. Analyze method behavior and identify ALL possible exception scenarios
2. Design test cases that trigger each identified exception
3. Follow existing test case structure, Avoid duplication and create new test cases for uncovered exception scenarios

Test Case Format:
1. Group test cases by test function name
2. Each test case MUST include:
   - Input parameters with values (use empty list `[]` if none)
   - Expected exception or observable behavior
   - Clear description of test scenario
3. Return empty array if no exception scenarios found

Output Schema (STRICT JSON format):
```json
[
    {
        "group": "test name",
        "cases": [
            {
                "input": [
                    {
                        "parameter": "param name",
                        "value": "param value"
                    }
                ],
                "expected": "expected exception or behavior",
                "description": "test scenario description"
            }
        ]
    },
    {...}
]
```

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
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: