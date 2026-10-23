# Invoke-pattern comparison

Only file_path: replace backslashes with forward slashes; preserve example ordering and line ranges.

Evidence: `invoke-compare-junit.json`. Example positions are zero-based; full author/generated line ranges are retained there.

| Category | Method keys |
|---|---:|
| identical | 48 |
| only in author | 0 |
| only in generated | 0 |
| differs | 93 |

## All non-identical keys

- `org.apache.commons.cli.CommandLine#CommandLine()`: differs.
- `org.apache.commons.cli.CommandLine#addArg(String)`: differs.
- `org.apache.commons.cli.CommandLine#getOptionValues(Option)`: differs.
- `org.apache.commons.cli.CommandLine#hasOption(String)`: differs.
- `org.apache.commons.cli.CommandLine#processPropertiesFromValues(Properties, List)`: differs.
- `org.apache.commons.cli.DefaultParser#checkRequiredArgs()`: differs.
- `org.apache.commons.cli.DefaultParser#checkRequiredOptions()`: differs.
- `org.apache.commons.cli.DefaultParser#getLongPrefix(String)`: differs.
- `org.apache.commons.cli.DefaultParser#getMatchingLongOptions(String)`: differs.
- `org.apache.commons.cli.DefaultParser#handleConcatenatedOptions(String)`: differs.
- `org.apache.commons.cli.DefaultParser#handleLongOption(String)`: differs.
- `org.apache.commons.cli.DefaultParser#handleLongOptionWithEqual(String)`: differs.
- `org.apache.commons.cli.DefaultParser#handleLongOptionWithoutEqual(String)`: differs.
- `org.apache.commons.cli.DefaultParser#handleOption(Option)`: differs.
- `org.apache.commons.cli.DefaultParser#handleProperties(Properties)`: differs.
- `org.apache.commons.cli.DefaultParser#handleShortAndLongOption(String)`: differs.
- `org.apache.commons.cli.DefaultParser#handleToken(String)`: differs.
- `org.apache.commons.cli.DefaultParser#handleUnknownToken(String)`: differs.
- `org.apache.commons.cli.DefaultParser#isArgument(String)`: differs.
- `org.apache.commons.cli.DefaultParser#isJavaProperty(String)`: differs.
- `org.apache.commons.cli.DefaultParser#isLongOption(String)`: differs.
- `org.apache.commons.cli.DefaultParser#isNegativeNumber(String)`: differs.
- `org.apache.commons.cli.DefaultParser#isOption(String)`: differs.
- `org.apache.commons.cli.DefaultParser#isShortOption(String)`: differs.
- `org.apache.commons.cli.DefaultParser#stripLeadingAndTrailingQuotesDefaultOff(String)`: differs.
- `org.apache.commons.cli.DefaultParser#stripLeadingAndTrailingQuotesDefaultOn(String)`: differs.
- `org.apache.commons.cli.DefaultParser#updateRequiredOptions(Option)`: differs.
- `org.apache.commons.cli.HelpFormatter#appendOption(StringBuffer, Option, boolean)`: differs.
- `org.apache.commons.cli.HelpFormatter#appendOptionGroup(StringBuffer, OptionGroup)`: differs.
- `org.apache.commons.cli.HelpFormatter#createPadding(int)`: differs.
- `org.apache.commons.cli.HelpFormatter#findWrapPos(String, int, int)`: differs.
- `org.apache.commons.cli.HelpFormatter#getArgName()`: differs.
- `org.apache.commons.cli.HelpFormatter#getDescPadding()`: differs.
- `org.apache.commons.cli.HelpFormatter#getLeftPadding()`: differs.
- `org.apache.commons.cli.HelpFormatter#getLongOptPrefix()`: differs.
- `org.apache.commons.cli.HelpFormatter#getNewLine()`: differs.
- `org.apache.commons.cli.HelpFormatter#getOptPrefix()`: differs.
- `org.apache.commons.cli.HelpFormatter#getOptionComparator()`: differs.
- `org.apache.commons.cli.HelpFormatter#getSyntaxPrefix()`: differs.
- `org.apache.commons.cli.HelpFormatter#printHelp(PrintWriter, int, String, String, Options, int, int, String, boolean)`: differs.
- `org.apache.commons.cli.HelpFormatter#printOptions(PrintWriter, int, Options, int, int)`: differs.
- `org.apache.commons.cli.HelpFormatter#printUsage(PrintWriter, int, String)`: differs.
- `org.apache.commons.cli.HelpFormatter#printUsage(PrintWriter, int, String, Options)`: differs.
- `org.apache.commons.cli.HelpFormatter#printWrapped(PrintWriter, int, String)`: differs.
- `org.apache.commons.cli.HelpFormatter#printWrapped(PrintWriter, int, int, String)`: differs.
- `org.apache.commons.cli.HelpFormatter#renderOptions(StringBuffer, int, Options, int, int)`: differs.
- `org.apache.commons.cli.HelpFormatter#renderWrappedText(StringBuffer, int, int, String)`: differs.
- `org.apache.commons.cli.HelpFormatter#renderWrappedTextBlock(StringBuffer, int, int, String)`: differs.
- `org.apache.commons.cli.HelpFormatter#rtrim(String)`: differs.
- `org.apache.commons.cli.Option#Option(Builder)`: differs.
- `org.apache.commons.cli.Option#acceptsArg()`: differs.
- `org.apache.commons.cli.Option#add(String)`: differs.
- `org.apache.commons.cli.Option#addValueForProcessing(String)`: differs.
- `org.apache.commons.cli.Option#builder(String)`: differs.
- `org.apache.commons.cli.Option#equals(Object)`: differs.
- `org.apache.commons.cli.Option#getArgName()`: differs.
- `org.apache.commons.cli.Option#getDescription()`: differs.
- `org.apache.commons.cli.Option#getLongOpt()`: differs.
- `org.apache.commons.cli.Option#getOpt()`: differs.
- `org.apache.commons.cli.Option#getValueSeparator()`: differs.
- `org.apache.commons.cli.Option#getValues()`: differs.
- `org.apache.commons.cli.Option#getValuesList()`: differs.
- `org.apache.commons.cli.Option#hasArg()`: differs.
- `org.apache.commons.cli.Option#hasArgs()`: differs.
- `org.apache.commons.cli.Option#hasLongOpt()`: differs.
- `org.apache.commons.cli.Option#hasValueSeparator()`: differs.
- `org.apache.commons.cli.Option#isRequired()`: differs.
- `org.apache.commons.cli.Option#processValue(String)`: differs.
- `org.apache.commons.cli.Option#setRequired(boolean)`: differs.
- `org.apache.commons.cli.Option.Builder#build()`: differs.
- `org.apache.commons.cli.Option.Builder#converter(Converter)`: differs.
- `org.apache.commons.cli.Option.Builder#hasArg(boolean)`: differs.
- `org.apache.commons.cli.Option.Builder#required(boolean)`: differs.
- `org.apache.commons.cli.Option.Builder#type(Class)`: differs.
- `org.apache.commons.cli.OptionGroup#getOptions()`: differs.
- `org.apache.commons.cli.OptionGroup#getSelected()`: differs.
- `org.apache.commons.cli.OptionGroup#isRequired()`: differs.
- `org.apache.commons.cli.OptionGroup#setSelected(Option)`: differs.
- `org.apache.commons.cli.OptionValidator#isValidChar(char)`: differs.
- `org.apache.commons.cli.OptionValidator#isValidOpt(char)`: differs.
- `org.apache.commons.cli.OptionValidator#search(char[], char)`: differs.
- `org.apache.commons.cli.Options#addOption(Option)`: differs.
- `org.apache.commons.cli.Options#getOption(String)`: differs.
- `org.apache.commons.cli.Options#getOptionGroup(Option)`: differs.
- `org.apache.commons.cli.Options#getOptionGroups()`: differs.
- `org.apache.commons.cli.Options#getOptions()`: differs.
- `org.apache.commons.cli.Options#hasLongOption(String)`: differs.
- `org.apache.commons.cli.Options#hasShortOption(String)`: differs.
- `org.apache.commons.cli.Options#helpOptions()`: differs.
- `org.apache.commons.cli.ParseException#ParseException(Throwable)`: differs.
- `org.apache.commons.cli.PatternOptionBuilder#getValueType(char)`: differs.
- `org.apache.commons.cli.PatternOptionBuilder#isValueCode(char)`: differs.
- `org.apache.commons.cli.TypeHandler#getConverter(Class)`: differs.
