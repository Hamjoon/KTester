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
Here is the code of focal method, `getOption(String)`, with the focal class `Options`:
```Java
package org.apache.commons.cli;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Collections;
public class Options implements Serializable {
    private final Map<String, Option> shortOpts = new LinkedHashMap<>();
    private final Map<String, Option> longOpts = new LinkedHashMap<>();
    public Option getOption(final String opt) {
        final String clean = Util.stripLeadingHyphens(opt);
        final Option option = shortOpts.get(clean);
        return option != null ? option : longOpts.get(clean);
    }
public Options addOption(final String opt, final boolean hasArg, final String description);
    public Options addOption(final String opt, final String description);
    public Options addOption(final String opt, final String longOpt, final boolean hasArg, final String description);
    public Options addOption(final Option opt);
    public Options addOptionGroup(final OptionGroup optionGroup);
    public Options addOptions(final Options options);
    public Options addRequiredOption(final String opt, final String longOpt, final boolean hasArg, final String description);
    public List<String> getMatchingOptions(final String opt);
    public OptionGroup getOptionGroup(final Option option);
    Collection<OptionGroup> getOptionGroups();
    public Collection<Option> getOptions();
    public List<?> getRequiredOptions();
    public boolean hasLongOption(final String opt);
    public boolean hasOption(final String opt);
    public boolean hasShortOption(final String opt);
    List<Option> helpOptions();
    @Override
public String toString();
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: