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
Here is the code of focal method, `getValueType(char)`, with the focal class `PatternOptionBuilder_getValueType_Test`:
```Java
package org.apache.commons.cli;
import java.net.URL;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
public class PatternOptionBuilder {
    public static final Class<String> STRING_VALUE = String.class;
    public static final Class<Object> OBJECT_VALUE = Object.class;
    public static final Class<Number> NUMBER_VALUE = Number.class;
    public static final Class<Date> DATE_VALUE = Date.class;
    public static final Class<?> CLASS_VALUE = Class.class;
    public static final Class<FileInputStream> EXISTING_FILE_VALUE = FileInputStream.class;
    public static final Class<File> FILE_VALUE = File.class;
    public static final Class<File[]> FILES_VALUE = File[].class;
    public static final Class<URL> URL_VALUE = URL.class;
    static final Converter<?, UnsupportedOperationException> NOT_IMPLEMENTED = s -> {
    throw new UnsupportedOperationException("Not yet implemented");
};
    public static Class<?> getValueType(final char ch) {
        switch (ch) {
            case '@' :
                return PatternOptionBuilder.OBJECT_VALUE;
            case ':' :
                return PatternOptionBuilder.STRING_VALUE;
            case '%' :
                return PatternOptionBuilder.NUMBER_VALUE;
            case '+' :
                return PatternOptionBuilder.CLASS_VALUE;
            case '#' :
                return PatternOptionBuilder.DATE_VALUE;
            case '<' :
                return PatternOptionBuilder.EXISTING_FILE_VALUE;
            case '>' :
                return PatternOptionBuilder.FILE_VALUE;
            case '*' :
                return PatternOptionBuilder.FILES_VALUE;
            case '/' :
                return PatternOptionBuilder.URL_VALUE;
        }
        return null;
    }
public static boolean isValueCode(final char ch);
    public static Options parsePattern(final String pattern);
    public static void registerTypes();
}
```


@input{context information}

api document of class org.apache.commons.cli.PatternOptionBuilder: 

 * Allows Options to be created from a single String. The pattern contains various single character flags and via an
 * optional punctuation character, their expected type.
 *
 * <table border="1">
 * <caption>Overview of PatternOptionBuilder patterns</caption>
 * <tr>
 * <td>a</td>
 * <td>-a flag</td>
 * </tr>
 * <tr>
 * <td>b@</td>
 * <td>-b [class name]</td>
 * </tr>
 * <tr>
 * <td>c&gt;</td>
 * <td>-c [file name]</td>
 * </tr>
 * <tr>
 * <td>d+</td>
 * <td>-d [class name] (creates object via empty constructor)</td>
 * </tr>
 * <tr>
 * <td>e%</td>
 * <td>-e [number] (creates Double/Long instance depending on existing of a '.')</td>
 * </tr>
 * <tr>
 * <td>f/</td>
 * <td>-f [URL]</td>
 * </tr>
 * <tr>
 * <td>g:</td>
 * <td>-g [string]</td>
 * </tr>
 * </table>
 *
 * <p>
 * For example, the following allows command line flags of '-v -p string-value -f /dir/file'. The exclamation mark
 * precede a mandatory option.
 * </p>
 *
 * <pre>
 * Options options = PatternOptionBuilder.parsePattern("vp:!f/");
 * </pre>
 *
 * <p>
 * TODO These need to break out to OptionType and also to be pluggable.
 * </p>
 

existing test class: 
```java
/*
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test case for the PatternOptionBuilder class.
 */
@SuppressWarnings("deprecation") // tests some deprecated classes
public class PatternOptionBuilderTest {
    @BeforeClass
    public static void setup() {
        PatternOptionBuilder.registerTypes();
    }

    @Test
    public void testClassPattern() throws Exception {
        final Options options = PatternOptionBuilder.parsePattern("c+d+");
        final CommandLineParser parser = new PosixParser();
        final CommandLine line = parser.parse(options, new String[] {"-c", "java.util.Calendar", "-d", "System.DateTime"});

        assertEquals("c value", Calendar.class, line.getOptionObject("c"));
        assertNull("d value", line.getOptionObject("d"));
    }

    @Test
    public void testEmptyPattern() {
        final Options options = PatternOptionBuilder.parsePattern("");
        assertTrue(options.getOptions().isEmpty());
    }

    @Test
    public void testExistingFilePattern() throws Exception {
        final Options options = PatternOptionBuilder.parsePattern("g<");
        final CommandLineParser parser = new PosixParser();
        final CommandLine line = parser.parse(options, new String[] {"-g", "src/test/resources/org/apache/commons/cli/existing-readable.file"});

        final Object parsedReadableFileStream = line.getOptionObject("g");

        assertNotNull("option g not parsed", parsedReadableFileStream);
        assertTrue("option g not FileInputStream", parsedReadableFileStream instanceof FileInputStream);
    }

    @Test
    public void testExistingFilePatternFileNotExist() throws Exception {
        final Options options = PatternOptionBuilder.parsePattern("f<");
        final CommandLineParser parser = new PosixParser();
        final CommandLine line = parser.parse(options, new String[] {"-f", "non-existing.file"});

        assertNull("option f parsed", line.getOptionObject("f"));
    }

    @Test
    public void testNumberPattern() throws Exception {
        final Options options = PatternOptionBuilder.parsePattern("n%d%x%");
        final CommandLineParser parser = new PosixParser();
        // 3,5 fails validation.
        //assertThrows(ParseException.class, () -> parser.parse(options, new String[] {"-n", "1", "-d", "2.1", "-x", "3,5"}));

        final CommandLine line = parser.parse(options, new String[] {"-n", "1", "-d", "2.1", "-x", "3,5"});
        assertEquals("n object class", Long.class, line.getOptionObject("n").getClass());
        assertEquals("n value", Long.valueOf(1), line.getOptionObject("n"));

        assertEquals("d object class", Double.class, line.getOptionObject("d").getClass());
        assertEquals("d value", Double.valueOf(2.1), line.getOptionObject("d"));

        assertNull("x object", line.getOptionObject("x"));
    }

......
        assertEquals("number flag n", Double.valueOf(4.5), line.getOptionObject("n"));
        assertEquals("url flag t", new URL("https://commons.apache.org"), line.getOptionObject("t"));

        // tests the char methods of CommandLine that delegate to the String methods
        assertEquals("flag a", "foo", line.getOptionValue('a'));
        assertEquals("string flag a", "foo", line.getOptionObject('a'));
        assertEquals("object flag b", new Vector<>(), line.getOptionObject('b'));
        assertTrue("boolean true flag c", line.hasOption('c'));
        assertFalse("boolean false flag d", line.hasOption('d'));
        assertEquals("file flag e", new File("build.xml"), line.getOptionObject('e'));
        assertEquals("class flag f", Calendar.class, line.getOptionObject('f'));
        assertEquals("number flag n", Double.valueOf(4.5), line.getOptionObject('n'));
        assertEquals("url flag t", new URL("https://commons.apache.org"), line.getOptionObject('t'));

        // FILES NOT SUPPORTED YET
        try {
            assertEquals("files flag m", new File[0], line.getOptionObject('m'));
            fail("Multiple files are not supported yet, should have failed");
        } catch (final UnsupportedOperationException uoe) {
            // expected
        }

        assertEquals("date flag z", new Date(1023400137000L), line.getOptionObject('z'));

    }

    @Test
    public void testUntypedPattern() throws Exception {
        final Options options = PatternOptionBuilder.parsePattern("abc");
        final CommandLineParser parser = new PosixParser();
        final CommandLine line = parser.parse(options, new String[] {"-abc"});

        assertTrue(line.hasOption('a'));
        assertNull("value a", line.getOptionObject('a'));
        assertTrue(line.hasOption('b'));
        assertNull("value b", line.getOptionObject('b'));
        assertTrue(line.hasOption('c'));
        assertNull("value c", line.getOptionObject('c'));
    }

    @Test
    public void testURLPattern() throws Exception {
        final Options options = PatternOptionBuilder.parsePattern("u/v/");
        final CommandLineParser parser = new PosixParser();
        final CommandLine line = parser.parse(options, new String[] {"-u", "https://commons.apache.org", "-v", "foo://commons.apache.org"});

        assertEquals("u value", new URL("https://commons.apache.org"), line.getOptionObject("u"));
        assertNull("v value", line.getOptionObject("v"));
    }
}
```

invoke examples of focal method: 
example 1:
```java
    public static Options parsePattern(final String pattern) {
        char opt = ' ';
        boolean required = false;
        Class<?> type = null;
        Converter<?, ?> converter = Converter.DEFAULT;
        final Options options = new Options();
        for (int i = 0; i < pattern.length(); i++) {
            final char ch = pattern.charAt(i);
            if (!isValueCode(ch)) {
            } else if (ch == '!') {
                type = getValueType(ch);
                converter = TypeHandler.getConverter(getValueType(ch));
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
class PatternOptionBuilder_getValueType_Test {
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