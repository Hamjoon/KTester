# KTester Replication Report

Replication of *Knowledge Matters: Injecting Project and Testing Knowledge into LLM-based Unit Test Generation* (Li et al., ICSE 2026; tool: KTester) using the authors' released code, data and result archive.
Date: 2026-09-13 (revises the 2026-09-11 version with a third run). Full details: `ktester-replication-report-full.md`.

## 1. Replication question

Can the main experiment of the paper (Table 2, KTester row) be re-run from the released artifacts, and do the numbers it produces match the paper?

## 2. What was replicated

- Code: github.com/SYSUSELab/KTester (commit `fce83f1`), released prompts, static-analysis outputs and Lucene indexes.
- Dataset: the paper's 10 Java projects and 111 focal methods (the paper text says 110; the released data and the authors' own result archive both contain 111).
- Model: gpt-4o-mini, temperature 0.5, as stated in the paper. Calls routed through OpenRouter, pinned to OpenAI's endpoint.
- The authors' published results (`RQ1.7z`): 5 repetitions of the full experiment, used as the reference for comparison.

Three full runs were made. Run A used `FIX_TRIES = 3` (the repair budget in the released `settings.py.template`) and run B used `FIX_TRIES = 5` (the value stated in the paper), both with gpt-4o-mini. Run C kept the paper's configuration and changed only the model to gpt-4o, to test whether the model explains the gap seen in A and B. The model actually served was confirmed from the API response and the OpenRouter activity log.

## 3. Protocol

The released pipeline was run unchanged except for six small changes needed to execute on Linux (the authors developed on Windows): a missing settings class, shell invocation, classpath separators, a path-separator bug in the compile-error parser, an `lxml` dependency, and OpenRouter provider pinning. None of these touches prompts, model parameters or scoring. All runs happened in a Docker image (Java 17, Maven 3.9.9, Python 3.13) that is part of the archive. Every generated prompt, response, repair round, test class and coverage report is committed per project.

Prompts were compared with the authors' archived prompts for 30 tasks in 4 projects: the case-generation and code-generation prompts are identical apart from list ordering. The only difference found is a `@Timeout(600)` annotation present in the authors' initial-structure prompt and absent from the released template.

Metrics are computed from the tool's own `summary.json` with the same script for our runs and for the authors' archive, so both sides use identical definitions.

## 4. Results

### 4.1 The paper's numbers are recoverable from the authors' archive

Aggregating the authors' five archived repetitions reproduces Table 2 exactly for CPR (100%), LC (63.94%) and BC (55.46%). Two clarifications follow from this: the paper's "line coverage" is the instruction coverage field emitted by KTester's JaCoCo post-processing, and CPR/EPR are effectively test-case-level rates. EPR (paper 76.41%) is not exactly recoverable; the closest definition gives 78.81%. The number of tests per class is 8.06 in the archive versus 7.33 in the paper.

### 4.2 Our runs versus the authors' archive (identical definitions, 111 tasks)

| | Compile pass (test cases) | Execution pass (test cases) | Instruction cov. | Branch cov. | Correct instr. cov. | Correct branch cov. | Tests / class | Time / task |
|---|---:|---:|---:|---:|---:|---:|---:|---:|
| Paper, Table 2 | 100.00 | 76.41 | 63.94 | 55.46 | – | – | 7.33 | 152.7 s |
| Authors' archive, mean of 5 | 100.00 | 78.81 | 63.94 | 55.46 | 55.52 | 47.21 | 8.06 | 152.7 s |
| Ours, run A (gpt-4o-mini, FIX_TRIES = 3) | 82.19 | 51.80 | 47.77 | 43.40 | 37.59 | 33.95 | 10.52 | 84.0 s |
| Ours, run B (gpt-4o-mini, FIX_TRIES = 5) | 85.71 | 47.25 | 44.27 | 39.30 | 32.26 | 27.90 | 10.47 | 150.0 s |
| Ours, run C (gpt-4o, FIX_TRIES = 5) | 94.18 | 63.98 | 52.83 | 47.96 | 45.77 | 40.60 | 12.53 | 194.4 s |

"Correct" coverage counts only passing tests. Values are percentages. Final test classes that did not compile: 18 of 111 in run A, 16 in run B, 5 in run C, 0 in every authors' repetition.

Per task, our correct instruction coverage falls inside the authors' five-repetition range for 80 of 111 tasks (run A), 75 (run B) and 68 (run C). The tasks outside the range are distributed differently: runs A and B lie almost only below it (24 below vs 7 above; 33 vs 3), while run C is nearly symmetric (23 below, 20 above). All four headline totals of run C (execution pass, instruction coverage, correct instruction coverage, tests per class) are still outside the authors' five-repetition range.

## 5. Observations

1. The pipeline is executable and its behaviour matches the paper's description: the same prompts, the same three-perspective case generation, the same compile-execute-repair loop. The released code needed a Linux port but no algorithmic change.
2. The released configuration does not match the paper. `settings.py.template` sets 3 repair iterations; the paper says 5, and the authors' archive shows five rounds. Raising the budget to 5 (run B) moved compile pass by 3.5 points and lowered execution pass and coverage. Run B's time per task (150 s) matches the paper's (153 s), which confirms 5 rounds is what the authors ran. The repair budget explains the time difference, not the quality gap.
3. The model is the main factor in the gap. With gpt-4o (run C) every quality metric moved roughly halfway to the authors' mean (37–69% of the run-B gap removed), compile pass rose by 8.5 points and execution pass by 16.7 points, non-compiling classes fell from 16–18 to 5, and the one-sided per-task bias disappeared. The authors' archive is labelled `KTester-gpt4o` while the paper says gpt-4o-mini.
4. The model alone does not account for the archive. Run C's execution pass is still 14.8 points below the authors' mean and outside their range, five classes still do not compile against the archive's zero, and tests per class moved away from the authors (12.53 vs 8.06; gpt-4o-mini gave 10.5). If the archive had come from gpt-4o under this pipeline, tests per class would be expected to approach 8, not recede from it. The evidence supports "a stronger model than gpt-4o-mini explains most of the gap" but not "the archive was produced by gpt-4o".
5. The authors' evaluation code has a defect that reports correct coverage as zero for tasks whose generated tests use nested test classes, even when most tests pass. It appears in our runs (2, 0 and 6 tasks in A, B, C) and in the authors' own archive (0 to 5 tasks per repetition, mean 2.2), so the published figures contain the same zeros and the comparison is fair. Correcting both sides by the most generous bound narrows the correct-coverage gap from 9.75 to 8.30 points. It does not explain the gap.
6. Where our runs fail, the failures are of the kind the repair loop cannot fix. In all three runs the remaining compile errors are invented or misused project-local types and members in helper code, fields or setup, outside any `@Test` method and out of reach of KTester's rule that deletes failing test methods. Resource-exhausting tests (10-minute timeouts, JVM out-of-memory) likewise persist through every repair round with either model; changing the model moved these from commons-codec to gson without reducing their number.

## 6. Limitations

- One run per configuration; the authors report five. Some run-to-run differences are within sampling noise.
- Two environment effects lower execution and coverage numbers but not compile rates: the 600-second single-test timeout (12 firings in run B, 12 in run C, in different projects) and JVM out-of-memory errors (9 in run B, 14 in run C). Two variables around them are uncontrolled: the authors' `@Timeout(600)` annotation, absent from the released template, and the JVM heap ceiling, which neither side sets explicitly and which depends on machine memory. We do not claim the affected tasks would pass with more memory.
- The correct-coverage defect (observation 5) means correct-coverage figures on both sides are slight underestimates.
- EPR (76.41 vs 78.81) and the task count (110 vs 111) could not be reconciled with the paper text. The provenance of the archive (which model produced it) remains unknown.

## 7. What this replication gives our own work

The replication stops here; further runs would narrow the remaining uncertainty but are not needed for our purpose. What we take from it: the prompts and static-analysis inputs as actually sent to the model, a concrete picture of the repair loop (one LLM call per round, otherwise scripted deletion and commenting of failing tests, best-of-rounds selection), and a failure class that repair iteration does not fix regardless of budget or model: hallucinated project-local APIs in non-test code and resource-exhausting tests. Both bear directly on the repair stage of our stale-test experiments.

Repository: branch `experiment/2026-09-week2-ktester-replication` in the KTester fork (handovers under `docs/`, artifacts under `results/`).
