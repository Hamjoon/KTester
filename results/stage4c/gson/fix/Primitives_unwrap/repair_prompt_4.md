


You are an experienced Java developer tasked with fixing a piece of code that contains execution errors. Your task is to resolve these issues after analyzing the code and its execution feedback.

Use the following steps to guide your analysis and correction process:
1. Review the provided Java code for any logical errors.
2. Analyze the execution feedback to understand the specific errors and their locations within the code.
3. Apply necessary corrections to the code based on the feedback, and output the compelete correct code.
The Java code you need to fix is provided below:
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Primitives_unwrap_Test {

    class CustomClass {
    }

//     @Test
//     void testUnwrapInteger() {
//         // given
//         Class<Integer> input = Integer.class;
//         // when
//         Class<?> result = Primitives.unwrap(input);
//         // then
//         assertEquals(int.class, result);
//     }

//     @Test
//     void testUnwrapFloat() {
//         // given
//         Class<Float> input = Float.class;
//         // when
//         Class<?> result = Primitives.unwrap(input);
//         // then
//         assertEquals(float.class, result);
//     }

//     @Test
//     void testUnwrapByte() {
//         // given
//         Class<Byte> input = Byte.class;
//         // when
//         Class<?> result = Primitives.unwrap(input);
//         // then
//         assertEquals(byte.class, result);
//     }

//     @Test
//     void testUnwrapDouble() {
//         // given
//         Class<Double> input = Double.class;
//         // when
//         Class<?> result = Primitives.unwrap(input);
//         // then
//         assertEquals(double.class, result);
//     }

//     @Test
//     void testUnwrapLong() {
//         // given
//         Class<Long> input = Long.class;
//         // when
//         Class<?> result = Primitives.unwrap(input);
//         // then
//         assertEquals(long.class, result);
//     }

//     @Test
//     void testUnwrapCharacter() {
//         // given
//         Class<Character> input = Character.class;
//         // when
//         Class<?> result = Primitives.unwrap(input);
//         // then
//         assertEquals(char.class, result);
//     }

//     @Test
//     void testUnwrapBoolean() {
//         // given
//         Class<Boolean> input = Boolean.class;
//         // when
//         Class<?> result = Primitives.unwrap(input);
//         // then
//         assertEquals(boolean.class, result);
//     }

//     @Test
//     void testUnwrapShort() {
//         // given
//         Class<Short> input = Short.class;
//         // when
//         Class<?> result = Primitives.unwrap(input);
//         // then
//         assertEquals(short.class, result);
//     }

//     @Test
//     void testUnwrapVoid() {
//         // given
//         Class<Void> input = Void.class;
//         // when
//         Class<?> result = Primitives.unwrap(input);
//         // then
//         assertEquals(void.class, result);
//     }

//     @Test
//     void testUnwrapNonWrapper() {
//         // given
//         Class<String> input = String.class;
//         // when
//         Class<?> result = Primitives.unwrap(input);
//         // then
//         assertEquals(String.class, result);
//     }

//     @Test
//     void testUnwrapObject() {
//         // given
//         Class<Object> input = Object.class;
//         // when
//         Class<?> result = Primitives.unwrap(input);
//         // then
//         assertEquals(Object.class, result);
//     }

//     @Test
//     void testUnwrapPrimitiveInt() {
//         // given
//         Class<Integer> input = Integer.class;
//         // when
//         Class<?> result = Primitives.unwrap(input);
//         // then
//         assertEquals(int.class, result);
//     }

//     @Test
//     void testUnwrapPrimitiveFloat() {
//         // given
//         Class<Float> input = Float.class;
//         // when
//         Class<?> result = Primitives.unwrap(input);
//         // then
//         assertEquals(float.class, result);
//     }

//     @Test
//     void testUnwrapNull() {
//         // given
//         Class<?> input = null;
//         // when / then
//         assertThrows(NullPointerException.class, () -> Primitives.unwrap(input));
//     }

//     @Test
//     void testUnwrapCustomClass() {
//         // given
//         Class<CustomClass> input = CustomClass.class;
//         // when
//         Class<?> result = Primitives.unwrap(input);
//         // then
//         assertEquals(CustomClass.class, result);
//     }
}
```

Corresponding execution feedback:
```
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
org.junit.platform.commons.JUnitException: TestEngine with ID 'junit-jupiter' failed to discover tests
	at org.junit.platform.launcher.core.EngineDiscoveryOrchestrator.discoverEngineRoot(EngineDiscoveryOrchestrator.java:160)
	at org.junit.platform.launcher.core.EngineDiscoveryOrchestrator.discoverSafely(EngineDiscoveryOrchestrator.java:134)
	at org.junit.platform.launcher.core.EngineDiscoveryOrchestrator.discover(EngineDiscoveryOrchestrator.java:108)
	at org.junit.platform.launcher.core.EngineDiscoveryOrchestrator.discover(EngineDiscoveryOrchestrator.java:80)
	at org.junit.platform.launcher.core.DefaultLauncher.discover(DefaultLauncher.java:110)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:86)
	at org.junit.platform.launcher.core.DefaultLauncherSession$DelegatingLauncher.execute(DefaultLauncherSession.java:86)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:53)
	at org.junit.platform.console.tasks.ConsoleTestExecutor.executeTests(ConsoleTestExecutor.java:66)
	at org.junit.platform.console.tasks.ConsoleTestExecutor.lambda$execute$0(ConsoleTestExecutor.java:58)
	at org.junit.platform.console.tasks.CustomContextClassLoaderExecutor.invoke(CustomContextClassLoaderExecutor.java:33)
	at org.junit.platform.console.tasks.ConsoleTestExecutor.execute(ConsoleTestExecutor.java:58)
	at org.junit.platform.console.ConsoleLauncher.executeTests(ConsoleLauncher.java:120)
	at org.junit.platform.console.ConsoleLauncher.execute(ConsoleLauncher.java:82)
	at org.junit.platform.console.ConsoleLauncher.execute(ConsoleLauncher.java:55)
	at org.junit.platform.console.ConsoleLauncher.execute(ConsoleLauncher.java:48)
	at org.junit.platform.console.ConsoleLauncher.main(ConsoleLauncher.java:42)
Caused by: org.junit.platform.commons.JUnitException: ClassSelector [className = 'com.google.gson.internal.Primitives_unwrap_Test', classLoader = null] resolution failed
	at org.junit.platform.launcher.listeners.discovery.AbortOnFailureLauncherDiscoveryListener.selectorProcessed(AbortOnFailureLauncherDiscoveryListener.java:39)
	at org.junit.platform.engine.support.discovery.EngineDiscoveryRequestResolution.resolveCompletely(EngineDiscoveryRequestResolution.java:103)
	at org.junit.platform.engine.support.discovery.EngineDiscoveryRequestResolution.run(EngineDiscoveryRequestResolution.java:83)
	at org.junit.platform.engine.support.discovery.EngineDiscoveryRequestResolver.resolve(EngineDiscoveryRequestResolver.java:113)
	at org.junit.jupiter.engine.discovery.DiscoverySelectorResolver.resolveSelectors(DiscoverySelectorResolver.java:48)
	at org.junit.jupiter.engine.JupiterTestEngine.discover(JupiterTestEngine.java:69)
	at org.junit.platform.launcher.core.EngineDiscoveryOrchestrator.discoverEngineRoot(EngineDiscoveryOrchestrator.java:152)
	... 16 more
Caused by: org.junit.platform.commons.PreconditionViolationException: Could not load class with name: com.google.gson.internal.Primitives_unwrap_Test
	at org.junit.platform.engine.discovery.ClassSelector.lambda$getJavaClass$0(ClassSelector.java:98)
	at org.junit.platform.commons.function.Try$Failure.getOrThrow(Try.java:335)
	at org.junit.platform.engine.discovery.ClassSelector.getJavaClass(ClassSelector.java:97)
	at org.junit.jupiter.engine.discovery.ClassSelectorResolver.resolve(ClassSelectorResolver.java:66)
	at org.junit.platform.engine.support.discovery.EngineDiscoveryRequestResolution.lambda$resolve$2(EngineDiscoveryRequestResolution.java:135)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1602)
	at java.base/java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:129)
	at java.base/java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:527)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:513)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.FindOps$FindOp.evaluateSequential(FindOps.java:150)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.base/java.util.stream.ReferencePipeline.findFirst(ReferencePipeline.java:647)
	at org.junit.platform.engine.support.discovery.EngineDiscoveryRequestResolution.resolve(EngineDiscoveryRequestResolution.java:189)
	at org.junit.platform.engine.support.discovery.EngineDiscoveryRequestResolution.resolve(EngineDiscoveryRequestResolution.java:126)
	at org.junit.platform.engine.support.discovery.EngineDiscoveryRequestResolution.resolveCompletely(EngineDiscoveryRequestResolution.java:92)
	... 21 more
Caused by: java.lang.ClassNotFoundException: com.google.gson.internal.Primitives_unwrap_Test
	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:641)
	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:188)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:525)
	at java.base/java.lang.Class.forName0(Native Method)
	at java.base/java.lang.Class.forName(Class.java:469)
	at org.junit.platform.commons.util.ReflectionUtils.lambda$tryToLoadClass$10(ReflectionUtils.java:890)
	at org.junit.platform.commons.function.Try.lambda$call$0(Try.java:57)
	at org.junit.platform.commons.function.Try.of(Try.java:93)
	at org.junit.platform.commons.function.Try.call(Try.java:57)
	at org.junit.platform.commons.util.ReflectionUtils.tryToLoadClass(ReflectionUtils.java:853)
	at org.junit.platform.commons.util.ReflectionUtils.tryToLoadClass(ReflectionUtils.java:790)
	at org.junit.platform.engine.discovery.ClassSelector.getJavaClass(ClassSelector.java:95)
	... 35 more


Usage: ConsoleLauncher [OPTIONS]
Launches the JUnit Platform for test discovery and execution.
      [@<filename>...]       One or more argument files containing options.

COMMANDS

  -h, --help                 Display help information.
      --list-engines         List all observable test engines.

SELECTORS

      --scan-classpath, --scan-class-path[=PATH]
                             Scan all directories on the classpath or explicit classpath
                               roots. Without arguments, only directories on the system
                               classpath as well as additional classpath entries supplied via
                               -cp (directories and JAR files) are scanned. Explicit classpath
                               roots that are not on the classpath will be silently ignored.
                               This option can be repeated.
      --scan-modules         EXPERIMENTAL: Scan all resolved modules for test discovery.
  -u, --select-uri=URI       Select a URI for test discovery. This option can be repeated.
  -f, --select-file=FILE     Select a file for test discovery. This option can be repeated.
  -d, --select-directory=DIR Select a directory for test discovery. This option can be
                               repeated.
  -o, --select-module=NAME   EXPERIMENTAL: Select single module for test discovery. This
                               option can be repeated.
  -p, --select-package=PKG   Select a package for test discovery. This option can be repeated.
  -c, --select-class=CLASS   Select a class for test discovery. This option can be repeated.
  -m, --select-method=NAME   Select a method for test discovery. This option can be repeated.
  -r, --select-resource=RESOURCE
                             Select a classpath resource for test discovery. This option can
                               be repeated.
  -i, --select-iteration=TYPE:VALUE[INDEX(..INDEX)?(,INDEX(..INDEX)?)*]
                             Select iterations for test discovery (e.g. method:com.acme.Foo#m()
                               [1..2]). This option can be repeated.

FILTERS

  -n, --include-classname=PATTERN
                             Provide a regular expression to include only classes whose fully
                               qualified names match. To avoid loading classes unnecessarily,
                               the default pattern only includes class names that begin with
                               "Test" or end with "Test" or "Tests". When this option is
                               repeated, all patterns will be combined using OR semantics.
                               Default: ^(Test.*|.+[.$]Test.*|.*Tests?)$
  -N, --exclude-classname=PATTERN
                             Provide a regular expression to exclude those classes whose fully
                               qualified names match. When this option is repeated, all
                               patterns will be combined using OR semantics.
      --include-package=PKG  Provide a package to be included in the test run. This option can
                               be repeated.
      --exclude-package=PKG  Provide a package to be excluded from the test run. This option
                               can be repeated.
  -t, --include-tag=TAG      Provide a tag or tag expression to include only tests whose tags
                               match. When this option is repeated, all patterns will be
                               combined using OR semantics.
  -T, --exclude-tag=TAG      Provide a tag or tag expression to exclude those tests whose tags
                               match. When this option is repeated, all patterns will be
                               combined using OR semantics.
  -e, --include-engine=ID    Provide the ID of an engine to be included in the test run. This
                               option can be repeated.
  -E, --exclude-engine=ID    Provide the ID of an engine to be excluded from the test run.
                               This option can be repeated.

RUNTIME CONFIGURATION

      -cp, --classpath, --class-path=PATH
                             Provide additional classpath entries -- for example, for adding
                               engines and their dependencies. This option can be repeated.
      --config=KEY=VALUE     Set a configuration parameter for test discovery and execution.
                               This option can be repeated.

REPORTING

      --fail-if-no-tests     Fail and return exit status code 2 if no tests are found.
      --reports-dir=DIR      Enable report output into a specified local directory (will be
                               created if it does not exist).

CONSOLE OUTPUT

      --disable-ansi-colors  Disable ANSI colors in output (not supported by all terminals).
      --color-palette=FILE   Specify a path to a properties file to customize ANSI style of
                               output (not supported by all terminals).
      --single-color         Style test output using only text attributes, no color (not
                               supported by all terminals).
      --disable-banner       Disable print out of the welcome message.
      --details=MODE         Select an output details mode for when tests are executed. Use
                               one of: none, summary, flat, tree, verbose. If 'none' is
                               selected, then only the summary and test failures are shown.
                               Default: tree.
      --details-theme=THEME  Select an output details tree theme for when tests are executed.
                               Use one of: ascii, unicode. Default is detected based on
                               default character encoding.

For more information, please refer to the JUnit User Guide at
https://junit.org/junit5/docs/current/user-guide/

```


And you can use the following related information to assist you in your analysis and correction:

api document of class com.google.gson.internal.Primitives: 

 * Contains static utility methods pertaining to primitive types and their
 * corresponding wrapper types.
 *
 * @author Kevin Bourrillion
 

api document of method unwrap(Class<T>): 

   * Returns the corresponding primitive type of {@code type} if it is a
   * wrapper type; otherwise returns {@code type} itself. Idempotent.
   * <pre>
   *     unwrap(Integer.class) == int.class
   *     unwrap(int.class) == int.class
   *     unwrap(String.class) == String.class
   * </pre>
   

dependent classes: 
class `com.google.gson.FieldAttributes`:
	related functions:
		method `public Class<?> getDeclaringClass()` returns `java.lang.Class<?>`, related with `com.google.gson.internal.Primitives.unwrap(Class<T>)`, api document: 
   * @return the declaring class that contains this field
   ;



Now you should output the corrected Java code wrapped in a Markdown code block ```java at the beginning and ``` at the end: 
