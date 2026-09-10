# Stage 3 handover: commons-cli trial run

## Run identity and scope

- Branch: `experiment/2026-09-week2-ktester-replication`
- Configured model: `openai/gpt-4o-mini`
- Model string returned by the JSON-mode smoke request: `openai/gpt-4o-mini`
- Temperature: `0.5`
- Project list: `['commons-cli']`
- Generation start: `2026-09-10T10:32:51+00:00`
- Generation end: `2026-09-10T10:34:30+00:00`
- Generation elapsed wall time: about 99 seconds
- Generation runs: one; no pipeline rerun was needed
- Saved pipeline response files: 13 (`10` normal `*_response.md` files and `3` repair response files). This is a lower bound on LLM calls and excludes the separate API smoke request.
- OpenRouter dashboard usage: not provided. The separate smoke request reported 26 prompt tokens, 15 completion tokens, 41 total tokens, and cost `1.29e-05`.
- JSON mode: passed; the smoke response was valid JSON with `"ok": true` and `"lang": "java"`.

The archived output is in `results/stage3-commons-cli/`. It was 4.2 MB before commit. The generated JaCoCo HTML directories were copied but remain ignored by the Stage 1 rule; prompts, responses, fixes, Java tests, CSV and JSON reports, timing data, and logs were committed.

## Per-task results

KTester reports focal-method instruction coverage (`inst_cov`) rather than line coverage in `reports/summary.json`, so the coverage columns below use the metric actually emitted by the tool.

| Task | Compiled | Execution | Tests | Repair responses | Instruction coverage | Branch coverage | Correct instruction / branch | Total time |
|---|---:|---:|---:|---:|---:|---:|---:|---:|
| `HelpFormatter_renderOptions` | yes | 1/8 passed | 8 | 3 | 0.93 | 0.69 | 0.20 / 0.11 | 97.01 s |
| `PatternOptionBuilder_getValueType` | yes | 3/3 passed | 3 | 0 | 1.00 | 1.00 | 1.00 / 1.00 | 33.30 s |

The project compile pass rate was `1.0`, and the execution pass rate was `4/11 = 0.363636...`. Average raw instruction/branch coverage was `0.965/0.845`; average correct instruction/branch coverage was `0.600/0.555`.

For `HelpFormatter_renderOptions`, the initial class had two compile errors: a missing `Comparator` import and an invalid chained `Options.setArgName(...)` call. KTester added the import mechanically. The three LLM repair responses kept producing the invalid chained call; after the second and third repair attempts, the parser identified and removed the affected test at lines `[82, [78, 88]]` and `[129, [126, 135]]`. The resulting class compiled. Its remaining failures were incorrect expected spacing in six tests and an incorrect expectation that a null `StringBuffer` would throw. Only the empty-options test passed.

`PatternOptionBuilder_getValueType` compiled without repair and all three generated test methods passed. One method covers all nine recognized value-code characters, and the other two cover invalid characters.

## Saved prompt and response inventory

Each task has `init_context.json`, `usage_context.json`, and these five prompts: `init_prompt.md`, `condition4case_prompt.md`, `io4case_prompt.md`, `exception4case_prompt.md`, and `gencode_prompt.md`.

| Task | init context | usage context | init prompt | condition prompt | I/O prompt | exception prompt | generation prompt |
|---|---:|---:|---:|---:|---:|---:|---:|
| `HelpFormatter_renderOptions` | 8,027 B | 5,940 B | 16,122 B | 8,293 B | 8,392 B | 8,064 B | 13,922 B |
| `PatternOptionBuilder_getValueType` | 8,774 B | 3,491 B | 12,154 B | 3,605 B | 3,704 B | 3,376 B | 6,824 B |

Each normal response directory contains `init_response.md`, `condition4case_response.md`, `io4case_response.md`, `exception4case_response.md`, `gencode_response.md`, and the derived `cases.json`. `HelpFormatter_renderOptions` additionally has three repair prompt/response pairs and four temporary Java snapshots.

## Verbatim first test-case prompt

The following is the complete `PatternOptionBuilder_getValueType/condition4case_prompt.md` sent for conditional-branch test-case generation:

````text
As a professional Java software testing expert, your task is to generate test cases for a focal method.

You will be provided with:
1. The focal method that needs testing.
2. Basic information of the target class to which the focus method belongs, including fields and methods.

Ensure the generated test cases meet the following requirements:

Test Coverage Requirements:
- Create test cases to cover all conditional branches in the target method, including `if`, `else if`, `else`, `switch`, and `case` statements.

Output Format:
- Organize the test cases by its functions logically.
- Include input parameters, expected output, and a description for each test case.
- Use empty list `[]` for the input when a test case does not require input parameters.
- Provide an expected return value for each test case, or specify the behavior that should be verified if there's no return value.
- Clearly describe the intention of each test case in the description.

Follow the JSON format provided here for the output:
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
Here is the code of focal method, `getValueType(char)`, with the focal class `PatternOptionBuilder`:
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

@output{test cases}: output the test cases in the JSON format:
````

## Comparison with the authors' RQ1 artifacts

`RQ1.7z` is a 1.5 GB archive rooted at `RQ1/`. It contains multiple tools (`ChatTester`, `ChatUniTest`, `HITS`, `KTester-gpt4o`, and `UTGen`) plus time records. The KTester results are under `RQ1/KTester-gpt4o/rep_1` through `rep_5`. For this comparison, only the small prompt, response, fix, test-class, summary, and CSV paths under `RQ1/KTester-gpt4o/rep_1/commons-cli` were extracted to `evaluation_results/RQ1-commons-cli`; JaCoCo HTML was not extracted.

After removing the authors' extra carriage returns and blank-line artifacts, the condition, I/O, and exception prompts are substantively identical for both tasks. The initialization contexts are byte-content equivalent after newline normalization. The current initialization prompts omit the authors' `@Timeout(600)` annotation. Generation prompts contain the same information, with only ordering changes among dependency members: `appendOption` moves within the HelpFormatter related-method list, and several PatternOptionBuilder fields move within the field list. These order changes appear to come from unordered collection traversal; no instruction, source context, API documentation, or example is added or removed.

The generated tests differ as expected from model nondeterminism and from the model version: the archive is labeled `KTester-gpt4o`, while this trial used `gpt-4o-mini`.

| Task | This trial | Authors' repetition 1 | Comparison |
|---|---|---|---|
| `HelpFormatter_renderOptions` | 8 tests, 3 repair responses, 1/8 passed, raw coverage 0.93/0.69, correct coverage 0.20/0.11 | 6 tests, 5 repair responses, 2/6 passed, raw coverage 0.87/0.65, correct coverage 0.70/0.46 | This trial generated more tests and slightly higher raw coverage, but substantially lower correct coverage because seven assertions failed. |
| `PatternOptionBuilder_getValueType` | 3 tests, no repairs, 3/3 passed, coverage 1.00/1.00 | 10 tests, no repair artifacts, 10/10 passed, coverage 1.00/1.00 | This trial grouped cases into fewer test methods; both runs covered and passed all cases. |

The authors' summary records compile pass rate `1.0`, execution pass rate `0.75`, average raw instruction/branch coverage `0.935/0.825`, and average correct instruction/branch coverage `0.85/0.73`. Both archived final classes therefore compiled; the per-task pass counts above come directly from their summary.

## Unexpected behavior and mechanical command fixes

1. The host `/tmp/api_check.py` was not visible in the container because Compose mounts the repository at `/work` but does not mount host `/tmp`. The exact script was rerun with the one-off volume `-v /tmp/api_check.py:/tmp/api_check.py:ro`.
2. The first coverage attempt completed test execution, then raised `bs4.exceptions.FeatureNotFound` because `code/requirements.txt` installs BeautifulSoup but does not install the `lxml` parser requested by `execute_test.py`. Coverage was rerun in an ephemeral container after `pip install lxml` installed version `6.1.3`. No repository or KTester code was changed.
3. The next coverage attempt calculated the complete report, then failed while writing `/work/evaluation/summary-openai/gpt-4o-mini.json`; the configured model's slash became a path separator. Creating `/work/evaluation/summary-openai` allowed the unchanged reporting code to finish. The archived coverage log contains all three attempts, with the successful report last.
4. Generated prompts contain mixed or unusual line endings inside embedded source excerpts, so `git diff --check` reports trailing whitespace for these verbatim artifacts. They were preserved rather than normalized.
5. The pipeline accepted JSON-mode responses. There were no API failures or generation tracebacks, and `generate_unit_test.py` was run only once and only for `commons-cli`.
6. The scoring summary exposes instruction and branch coverage, although Step 8 asked for line and branch coverage. The CSV files contain class-level line counts, but the focal-method extractor does not emit focal-method line coverage.

The runtime repair behavior is also worth reviewing against the intended pipeline description: after compilation succeeded, KTester requested another repair because the HelpFormatter tests failed at runtime, but after its repair budget it retained a class with seven failing assertions. The final summary correctly discounts those failures through the `correct_*` coverage fields.

## Open questions

1. Should `lxml` be added to the Docker image or pinned in `code/requirements.txt` so coverage works without an ephemeral install?
2. Should model names be sanitized before constructing summary filenames, or should the evaluator create parent directories automatically?
3. Should later experiments compare all five published KTester repetitions rather than repetition 1 only?
4. Is instruction coverage the intended metric for future handovers, or should focal-method line coverage also be extracted?
5. OpenRouter dashboard usage for this run was not supplied. If exact tokens and cost are needed, use the generation window above to retrieve them.
