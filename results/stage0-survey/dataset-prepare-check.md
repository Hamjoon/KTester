# DatasetPrepare round trip and population extension

Verdict: successful for the existing commons-cli methods and for an additional public Options method. No generated tests or LLM calls were involved.

All measured values: from docker/, `docker compose run --rm ktester bash -c 'python /work/docker/stage0_dataset_prepare.py > /work/results/stage0-survey/dataset-prepare-run.txt 2>&1'`. Render with `python3 docker/stage0_reports.py dataset`. Full field comparisons: dataset-prepare-check.json; generated entries: dataset-prepare-existing.json and dataset-prepare-enlarged.json.

## Exact metadata contract

Source read in full: code/Java/project-info-process/src/DatasetPrepare.java; overload matching checked in extractor/SpoonExtractor.java. The expected JSON is an array of objects:

```json
[
  {
    "project_name": "commons-cli",
    "put_path": "/root/experiments/puts/commons-cli",
    "method_name_to_idx": {
      "org.apache.commons.cli.HelpFormatter.renderOptions(StringBuffer, int, Options, int, int)": 0,
      "org.apache.commons.cli.PatternOptionBuilder.getValueType(char)": 1
    }
  }
]
```

Every consumed field is shown. The input field is **put_path**, not project_url. Its literal /root/experiments/puts/ prefix is removed; the resulting string becomes output project-url and is resolved relative to the dataset directory. It is a local source path, not a GitHub URL, and nothing is cloned by DatasetPrepare. The scratch commons-cli symlink points to projects/commons-cli so the original project-url value is retained.

method_name_to_idx is an object mapping full method signatures to arbitrary JSON values; only keys are read, and numeric values are ignored. The class is identified by fully qualified name and the method by name plus ordered argument types. Parameters must be comma-space separated. Generic type arguments are removed. Matching requires equal arity and checks whether each Spoon qualified type ends with the supplied type after periods are changed to dollar signs; simple type names work, fully qualified parameter types may therefore fail. No-argument methods use (). Constructors are not selected. Private methods are rejected.

The generated test ID uses the simple class name plus method name; overloads receive numeric suffixes. The source updates the suffixed key rather than the base counter on collision, so repeated overloads beyond the first collision may reuse an ID. Avoid assuming arbitrary overloaded populations are safe without an additional check. Missing/unresolved methods may yield empty bodies instead of aborting; this check validates nonempty bodies.

The extractor reads src/main/java and libs under the resolved local project, uses Spoon at Java compliance level 11, and writes dataset_info.json. It does not read test sources; test-class and test-path are synthesized naming conventions, not tests it found or generated.

## Existing entry comparison

Original methods: 2; regenerated methods: 2; enlarged population: 3; combined driver wall time: 2.483 seconds.

| Existing method | focal-method | class-code | test-class | test-path | source-path |
|---|---|---|---|---|---|
| HelpFormatter_renderOptions | line-endings only | line-endings only | identical | identical | identical |
| PatternOptionBuilder_getValueType | line-endings only | line-endings only | identical | identical | identical |

All other method identity fields and both project fields match exactly (same driver invocation). Differences in focal-method and class-code are CRLF versus LF only, not source content changes.

## Additional method

- Options_hasOption: hasOption(String); focal body nonempty=True; class context nonempty=True.

scratch/extract-check/dataset_meta.json and dataset_info.json retain the enlarged population. The committed existing-entry result preserves the earlier round trip.
