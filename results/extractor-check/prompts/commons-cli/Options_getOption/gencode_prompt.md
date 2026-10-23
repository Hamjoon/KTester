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

api document of class org.apache.commons.cli.Options: 

 * Main entry-point into the library.
 * <p>
 * Options represents a collection of {@link Option} objects, which describe the possible options for a command-line.
 * </p>
 * <p>
 * It may flexibly parse long and short options, with or without values. Additionally, it may parse only a portion of a
 * command-line, allowing for flexible multi-stage parsing.
 * </p>
 *
 * @see org.apache.commons.cli.CommandLine
 

api document of method getOption(String): 

     * Gets the {@link Option} matching the long or short name specified.
     * <p>
     * The leading hyphens in the name are ignored (up to 2).
     * </p>
     *
     * @param opt short or long name of the {@link Option}.
     * @return The option represented by opt.
     

return type: 
org.apache.commons.cli.Option

dependent classes: 
class `org.apache.commons.cli.Options`:
	dependent fields:
		java.util.Map<java.lang.String, org.apache.commons.cli.Option> org.apache.commons.cli.Options.longOpts;
		java.util.Map<java.lang.String, org.apache.commons.cli.Option> org.apache.commons.cli.Options.shortOpts;
	related functions:
		method `public Options addOption(Option)` returns `org.apache.commons.cli.Options`, related with `org.apache.commons.cli.Options.getOption(String)`, api document: 
     * Adds an option instance.
     *
     * @param opt The option that is to be added.
     * @return The resulting Options instance.
     ;
		method `public boolean hasOption(String)` returns `boolean`, related with `org.apache.commons.cli.Options.getOption(String)`, api document: 
     * Tests whether the named {@link Option} is a member of this {@link Options}.
     *
     * @param opt short or long name of the {@link Option}.
     * @return true if the named {@link Option} is a member of this {@link Options}.
     ;
		method `public OptionGroup getOptionGroup(Option)` returns `org.apache.commons.cli.OptionGroup`, related with `org.apache.commons.cli.Options.getOption(String)`, api document: 
     * Gets the OptionGroup the {@code opt} belongs to.
     *
     * @param option The option whose OptionGroup is being queried.
     * @return The OptionGroup if {@code opt} is part of an OptionGroup, otherwise return null.
     ;
		method `public boolean hasLongOption(String)` returns `boolean`, related with `org.apache.commons.cli.Options.getOption(String)`, api document: 
     * Tests whether the named {@link Option} is a member of this {@link Options}.
     *
     * @param opt long name of the {@link Option}.
     * @return true if the named {@link Option} is a member of this {@link Options}.
     * @since 1.3
     ;
		method `public List<String> getMatchingOptions(String)` returns `java.util.List<java.lang.String>`, related with `org.apache.commons.cli.Options.getOption(String)`, api document: 
     * Gets the options with a long name starting with the name specified.
     *
     * @param opt The partial name of the option, may be {@code null}.
     * @return A list of matching long option names, or an empty list if no matches were found.
     * @since 1.3
     ;
class `org.apache.commons.cli.DefaultParser`:
	related functions:
		method `private String getLongPrefix(String)` returns `java.lang.String`, related with `org.apache.commons.cli.Options.getOption(String)`, api document: 
     * Searches for a prefix that is the long name of an option (-Xmx512m).
     *
     * @param token The command line token to test.
     ;



@output{test class}: output the code wrapped in a Markdown code block ```java at the beginning and ``` at the end.