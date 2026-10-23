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
Here is the code of focal method, `getRequiredOptions()`, with the focal class `Options_getRequiredOptions_Test`:
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
    private final List<Object> requiredOpts = new ArrayList<>();
    public List<?> getRequiredOptions() {
        return Collections.unmodifiableList(requiredOpts);
    }
public Options addOption(final String opt, final boolean hasArg, final String description);
    public Options addOption(final String opt, final String description);
    public Options addOption(final String opt, final String longOpt, final boolean hasArg, final String description);
    public Options addOption(final Option opt);
    public Options addOptionGroup(final OptionGroup optionGroup);
    public Options addOptions(final Options options);
    public Options addRequiredOption(final String opt, final String longOpt, final boolean hasArg, final String description);
    public List<String> getMatchingOptions(final String opt);
    public Option getOption(final String opt);
    public OptionGroup getOptionGroup(final Option option);
    Collection<OptionGroup> getOptionGroups();
    public Collection<Option> getOptions();
    public boolean hasLongOption(final String opt);
    public boolean hasOption(final String opt);
    public boolean hasShortOption(final String opt);
    List<Option> helpOptions();
    @Override
public String toString();
}
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
 

constructors for class `org.apache.commons.cli.Options`: 
params: 
body:
```java
    public Options() {
        // empty
    }

```

existing test class: 
```java
/*
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

@SuppressWarnings("deprecation") // tests some deprecated classes
class OptionsTest {

    private void assertToStrings(final Option option) {
        // Should never throw.
        // Should return a String, not null.
        assertNotNull(option.toString());
        assertNotNull(option.toDeprecatedString());
    }

    @Test
    void testAddConflictingOptions() {
        final Options options1 = new Options();
        final OptionGroup optionGroup1 = new OptionGroup();
        optionGroup1.addOption(Option.builder("a").get());
        optionGroup1.addOption(Option.builder("b").get());
        options1.addOptionGroup(optionGroup1);
        options1.addOption(Option.builder("x").get());
        options1.addOption(Option.builder("y").get());
        final Options options2 = new Options();
        final OptionGroup optionGroup2 = new OptionGroup();
        optionGroup2.addOption(Option.builder("x").type(Integer.class).get());
        optionGroup2.addOption(Option.builder("b").type(Integer.class).get());
        options2.addOptionGroup(optionGroup2);
        options2.addOption(Option.builder("c").get());
        assertThrows(IllegalArgumentException.class, () -> options1.addOptions(options2));
    }

    @Test
    void testAddNonConflictingOptions() {
        final Options options1 = new Options();
        final OptionGroup optionGroup1 = new OptionGroup();
        optionGroup1.addOption(Option.builder("a").get());
        optionGroup1.addOption(Option.builder("b").get());
        options1.addOptionGroup(optionGroup1);
        options1.addOption(Option.builder("x").get());
        options1.addOption(Option.builder("y").get());

        final Options options2 = new Options();
        final OptionGroup group2 = new OptionGroup();
        group2.addOption(Option.builder("c").type(Integer.class).get());
        group2.addOption(Option.builder("d").type(Integer.class).get());
        options2.addOptionGroup(group2);
        options1.addOption(Option.builder("e").get());
        options1.addOption(Option.builder("f").get());

        final Options underTest = new Options();
        underTest.addOptions(options1);
        underTest.addOptions(options2);

        final List<OptionGroup> expected = Arrays.asList(optionGroup1, group2);
        assertTrue(expected.size() == underTest.getOptionGroups().size() && expected.containsAll(underTest.getOptionGroups()));
        final Set<Option> expectOpt = new HashSet<>(options1.getOptions());
        expectOpt.addAll(options2.getOptions());
        assertEquals(8, expectOpt.size());
        assertTrue(expectOpt.size() == underTest.getOptions().size() && expectOpt.containsAll(underTest.getOptions()));
    }

    @Test
    void testAddOptions() {
        final Options options = new Options();

        final OptionGroup optionGroup1 = new OptionGroup();
        optionGroup1.addOption(Option.builder("a").get());
        optionGroup1.addOption(Option.builder("b").get());
......
        final MissingOptionException e = assertThrows(MissingOptionException.class, () -> new PosixParser().parse(options, new String[0]));
        assertEquals("Missing required option: f", e.getMessage());
    }

    @Test
    void testMissingOptionsException() throws ParseException {
        final Options options = new Options();
        OptionBuilder.isRequired();
        options.addOption(OptionBuilder.create("f"));
        OptionBuilder.isRequired();
        options.addOption(OptionBuilder.create("x"));
        final MissingOptionException e = assertThrows(MissingOptionException.class, () -> new PosixParser().parse(options, new String[0]));
        assertEquals("Missing required options: f, x", e.getMessage());
    }

    @Test
    void testRequiredOptionInGroupShouldNotBeInRequiredList() {
        final String key = "a";
        final Option option = new Option(key, "along", false, "Option A");
        option.setRequired(true);
        final Options options = new Options();
        options.addOption(option);
        assertTrue(options.getRequiredOptions().contains(key));
        final OptionGroup optionGroup = new OptionGroup();
        optionGroup.addOption(option);
        options.addOptionGroup(optionGroup);
        assertFalse(options.getOption(key).isRequired());
        assertFalse(options.getRequiredOptions().contains(key), "Option in group shouldn't be in required options list.");
    }

    @Test
    void testSimple() {
        final Options options = new Options();
        options.addOption("a", false, "toggle -a");
        options.addOption("b", true, "toggle -b");
        assertTrue(options.hasOption("a"));
        assertTrue(options.hasOption("b"));
    }

    @Test
    void testToString() {
        final Options options = new Options();
        options.addOption("f", "foo", true, "Foo");
        options.addOption("b", "bar", false, "Bar");
        final String s = options.toString();
        assertNotNull(s, "null string returned");
        assertTrue(s.toLowerCase().contains("foo"), "foo option missing");
        assertTrue(s.toLowerCase().contains("bar"), "bar option missing");
    }
}
```

invoke examples of focal method: 
example 1:
```java
    public CommandLine parse(final Options options, final Properties properties, final NonOptionAction nonOptionAction, final String... arguments)
            throws ParseException {
        this.options = Objects.requireNonNull(options, "options");
        this.nonOptionAction = nonOptionAction;
        skipParsing = false;
        currentOption = null;
        expectedOpts = new ArrayList<>(options.getRequiredOptions());
    }
```



@input{test class template}
```java
package org.apache.commons.cli;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
// Add other imports if necessary

@ExtendWith(MockitoExtension.class)
class Options_getRequiredOptions_Test {
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