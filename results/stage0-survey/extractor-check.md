# Commons CLI extractor check

Verdict: partial rebuild only. JSON, control-flow and calling-graph artifacts and a Lucene index were produced; invoke-pattern extraction fails reproducibly with NetworkXNoPath. No pipeline source was patched.

Invocation for all measurements: from docker/, `docker compose run --rm ktester bash -c 'python /work/docker/stage0_build_index.py > /work/results/stage0-survey/extractor-run.txt 2>&1'`. Report rendering and supplementary counts: `python3 docker/stage0_reports.py extractor`. The first failed run is preserved in extractor-first-failure.txt. The second run catches the same invoke failure only to complete independent indexing/comparison; no third attempt was made. Full errors are retained in extractor-check.json and both logs.

Measured second-run extraction/index wall time: 5.847 seconds (comparison time excluded).

## Inputs and settings established by source inspection

Read: code/Java/project-info-process/src/PreProcessor.java, code/procedure/preprocess_project.py, code/preparation.py, the FileStructure configuration in code/settings.py, CodeInfoExtractor.java, and IndexBuilder.java. Only the two packaged Java jars and procedure.preprocess_project are loaded by the driver; no LLM module or settings object is instantiated.

- Fresh upstream commons-cli at 90b3f2e is exported into scratch/extract-check/projects/commons-cli. Snapshot libs/, dependencies.txt, and src/test-original/ are copied there. dataset_info.json initially contains the unchanged commons-cli entry.
- PreProcessor enumerates its input directory as projects and reads each project’s src/main/java, src/test-original/java, and libs/*.jar. It appends json/ and codegraph/ to its output root. Thus the driver passes projects/ and project_index/, correcting the stale path wiring shown in preparation.py, without editing it.
- The Python graph stages need only DATASET_PATH (containing dataset_info.json) and CODE_INFO_PATH (containing json/ and codegraph/). A SimpleNamespace provides those attributes. dependencies.txt is copied for compatibility but is not read by these stages.
- IndexBuilder receives group, project_index/json, project_index/lucene. Its code indexes production methods under source, not test methods. Indexed identifiers below are class_fqn#signature.
- Library inputs lack the JUnit 4 Assert class referenced by test-original sources; the extractor logs unresolved org.junit.Assert symbols. That warning is distinct from the fatal graph reachability error.

## Normalized comparisons

JSON comparison sorts object keys and normalizes CRLF to LF; it preserves list order and Windows/Linux path separators so those differences remain visible.

| Artifact | Author / generated top-level entries | Differing JSON leaf paths |
|---|---|---:|
| json/commons-cli.json | 4 / 4 | 348 |
| codegraph/commons-cli_callgraph.json | 22 / 22 | 106 |
| codegraph/commons-cli_controlflow.json | 27 / 27 | 4926 |
| codegraph/commons-cli_invoke.json |  /  | missing generated file |

Structure detail (same report invocation):
- source: author 22, generated 22 entries.
- test: author 38, generated 38 entries.
- import_dict: author 371, generated 87 entries.
- Project JSON differences by root key: import_dict=288, source=22, test=38.
- codegraph/commons-cli_callgraph.json differing final path components: {'0': 44, '1': 9, '2': 8, '3': 1, 'sig': 44}.
- codegraph/commons-cli_controlflow.json differing final path components: {'id': 1700, 'source': 1613, 'target': 1613}.
- Test identifiers from project JSON: author 354, generated 354, author-only 0, generated-only 0. This is separate from the production-method Lucene index.
- Lucene document counts: author 223, generated 223; full identifier sets equal: True.

Differences include Windows versus Unix relative file paths, a much larger author import dictionary, caller ordering, and control-flow node identifiers. Counts do not establish semantic graph differences: raw node IDs/list order were deliberately not relabeled. Full differing paths and Lucene identifier sets are preserved in extractor-check.json.

## Fatal error and missing-input diagnosis

```text
Traceback (most recent call last):
  File "/work/docker/stage0_build_index.py", line 40, in <module>
    try:PP.extract_invoke_patterns(fs)
        ~~~~~~~~~~~~~~~~~~~~~~~~~~^^^^
  File "/work/code/procedure/preprocess_project.py", line 330, in extract_invoke_patterns
    invoke_patterns = extractor.extract_invoke_pattern()
  File "/work/code/procedure/preprocess_project.py", line 312, in extract_invoke_pattern
    path_lines = self.extract_code_private(call_chains)
  File "/work/code/procedure/preprocess_project.py", line 280, in extract_code_private
    result = self._get_lines_from_method(class_fqn, method_sig, target_lines)
  File "/work/code/procedure/preprocess_project.py", line 251, in _get_lines_from_method
    visited = self._get_lines_from_cfg(method_cfg, target_lines)
  File "/work/code/procedure/preprocess_project.py", line 205, in _get_lines_from_cfg
    for path in paths:
                ^^^^^
  File "/usr/local/lib/python3.13/site-packages/networkx/algorithms/shortest_paths/generic.py", line 690, in _build_paths_from_predecessors
    raise nx.NetworkXNoPath(f"Target {target} cannot be reached from given sources")
networkx.exception.NetworkXNoPath: Target 2253 cannot be reached from given sources
```

No missing file is identified by the fatal exception: a control-flow target is unreachable from the chosen source node. All required project inputs exist. The absent JUnit Assert dependency is a separate warning; adding it has not been tested and is not claimed to fix graph reachability. The full invoke-pattern rebuild remains unresolved.
