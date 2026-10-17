# Part 2 pre-run: stopped before generation

Date: 2026-10-17. Both repositories are on experiment/2026-10-week3-ktester-commit-survival.

Model openai/gpt-oss-120b; FIX_TRIES=5; TEMPERATURE=0.5; MAX_WORKERS=8. API key: present (not printed or copied). Released PROMPT_LIST and CASE_THEN_CODE are unchanged; exact safe settings are in pre-run-status.json.

DATASET_PATH = "../data/commit-survival/commons-codec"
CODE_INFO_PATH = "../data/project_index"

Only DATASET_PATH was edited in ignored settings.py, as specifically authorized by Step 1.2. PROJECTS remains ['jdom2']; the prompt-only helper overrides it in memory. The generation script would set it to commons-codec. CASES_LIST remains []. The population contains 66 unique IDs, project-url is commons-codec, and the ignored symlink resolves to the authors' data/projects/commons-codec snapshot. Docker Desktop was available.

Cleanup removed 6,664 files and no directories: context+prompts 126; responses 108; fix 156; test_classes 116; reports 6,140; src/test *_Test.java 18. pre-run-cleanup.json records each path, size and pre-removal SHA-256. Only the authorized five evaluation subdirectories and generated test source files were cleaned. The evaluation root .DS_Store remains. No reset or preparation command was used.

The helper docker/commit_survival_prompt_check.py invokes the released prompt-assembly functions, with the released case-then-code prompt-name transformation, and writes only to evaluation/commit-survival-dry-check/. It imports no model generation code and makes no model calls. It failed on both attempts (07:44:28 and 07:45:19 UTC) with:

    ValueError: Method `isArrayByteBase64(byte[])` not found in class `org.apache.commons.codec.binary.Base64`

The second traceback (trailing whitespace normalized) is preserved in prompt-check-attempt2.txt. The first attempt returned the same traceback in the tool output. The source snapshot contains that method, but the unchanged authors' JSON index does not. A read-only audit applying CodeSearcher._get_method_info's matching predicate finds 52 of 66 entries match and 14 do not; index-compatibility.json lists all affected IDs and available signatures.

Ground rule 6 requires stopping after two failures for the same reason. No generation round was started; no model calls or provider metadata exist; no generated tests, evaluator measurements, three-run validation, de-duplication, or denominator freeze were produced. The developer denominator remains 143; the LLM denominator is not established. Existing replication archives and unrelated untracked files were left untouched.

Resume requires Gary's decision on how to resolve index/population compatibility while respecting the unchanged-index and unchanged-algorithm requirements. Do not silently reduce the 66-method population, rebuild or patch the index, or alter prompts or code. Re-run the prompt-only preflight after an approved resolution, then resume Step 1.5 / Step 2.

## 2026-10-17 — Part 2 addendum: 52-method preflight

Step A is complete and committed: KTester a21fe8e; commons-codec 126cf005. Previous 66-method files are preserved under population-66/ in both repositories. Source annotations confirm all 14 missing methods are deprecated in both the snapshot and upstream cd16988; none of the 52 retained methods is deprecated. All retained signatures match the authors' unchanged index. Class counts: Base32 5, Base64 22, Md5Crypt 10, MurmurHash3 9, Sha2Crypt 6.

DatasetPrepare used the unmodified jars and existing snapshot symlink. Raw output has 39 unique IDs; the existing renumbering algorithm yields 52 unique IDs, changing 13 and preserving 39. Every previously unique entry, all non-naming fields and the five original focal entries are preserved. Code fields are nonempty and class/path naming checks pass. Validation and exact exclusions are in data/commit-survival/commons-codec/.

The first reduction-helper invocation stopped before any dataset mutation because a whole-file equality assertion was too strict: Base32/Base64 contain comment differences. The corrected check compares every parsed method declaration after comment removal and verifies deprecation annotations in both sources. The second invocation passed. Source, index, author dataset, jars and settings hashes were unchanged through extraction. No Java or pipeline code was edited.

Step B passed: all 52 tasks assembled five prompts each (260 prompt files), without a model call. See prompt-check.json and prompt-check-52.txt. Dry-check outputs are isolated from the clean generation output directories. Fixed settings still match; DATASET_PATH and CODE_INFO_PATH are unchanged from the prior preflight. CASES_LIST remains [].

Pre-generation routing conflict found by source inspection: code/tools/llm_api.py:51 hard-codes extra_body={"provider": {"order": ["OpenAI"], "allow_fallbacks": False}}. This conflicts with Part 2's unpinned-provider requirement, while ground rule 2 forbids changes under code/. Gary has been asked whether to permit removing only that routing argument. Generation is not started pending that decision; no round allowance is consumed.

## 2026-10-17 — authorized provider unpin

Gary explicitly authorized removal of only the extra_body provider-routing argument in code/tools/llm_api.py, originally introduced locally in September commit 9c055e13. Commit b61d042e8348a1c8296e4300efecfa43c06ab520 (Unpin OpenRouter provider for gpt-oss-120b) removes exactly that line. No prompt, model, algorithm, temperature, repair, concurrency or scoring setting changed. This resolves the previously recorded routing blocker and is the sole authorized pipeline-code exception. Provider routing is now unpinned. Generation settings, key presence and the clean output directories were rechecked before Step 2.

## 2026-10-17 — round 1 measured; round 2 workspace prepared

Round 1 ran once from 08:16:22 to 09:21:32 UTC (3,910 seconds) and produced all 52 class files, so no targeted rerun was eligible. Archive commit: 87c7fc3; generated-source commit in commons-codec: 624e36ed. Canonical archive: results/commit-survival/round1/commons-codec; prescribed stage path is retained as a relative symlink.

Five tasks completed full case generation. The other 47 hit missing per-task response directories; post-processing later stopped at Base32_builder's missing fix directory. The run was left unchanged until it ended. There were five logged JSON parsing errors and no recorded 600-second timeouts or OOMs. Providers were not logged by the released client.

The reference evaluator executed tests but exited 1 when its JaCoCo parser tried converting branch coverage 'n/a' to float at Base32.getLineSeparator. Its final summary is unavailable. The raw log dictionary was recovered without changing scoring: 24 compiled files, 153 executed test cases, 128 passed cases. Full evidence and traceback are archived.

Independent validation at t: 52 files, 24 compiled, 151 listed methods, 126 pass all three runs, zero duplicate bodies, 126 unique methods versus 143 developer methods (shortfall 17). Per-round measurement commit in commons-codec: 199261b3. The denominator has not been frozen because another round is required by the matching rule.

The next-round workspace preparation removed 9,857 authorized output files and zero directories, then created 121 missing directories matching preparation.set_file_structure. All 52 task directory sets were verified. Source/test files already copied to generated/round1/ and canonical round 1 archives remain intact. Details are in round2-workspace.json. No round 2 model call has been made.

Gary has been asked whether to treat only the known reference-evaluator n/a parsing failure as nonblocking if it recurs. Ground rule 6 otherwise requires stopping after repeated command failure, while the matching rule requires round 2 and the author's evaluator is reference-only. No evaluation code or scoring change is proposed. Await that decision before starting round 2.

## 2026-10-17 — authorized reference-evaluator exception

Gary explicitly authorized treating the reference evaluator's JaCoCo branch-coverage `n/a` parsing failure as nonblocking for the rest of Part 2, including repeated occurrences. Record each occurrence in the handover and continue independent three-run validation, cumulative de-duplication and matching. Evaluator code and scoring remain unchanged. This overrides ground rule 6 only for this specific reference-evaluator failure. Round 2 proceeds with the prescribed full 52-task invocation and fixed settings.

## 2026-10-17 — round 2 generation archived

Round 2 ran once from 09:38:45 to 10:42:30 UTC (3,825 seconds), with generate_rc=0 and coverage_rc=1. All 52 frameworks and 52 case-generation tasks completed, and all 52 source files exist. No targeted rerun is eligible. No missing output directories recurred. Eight model JSON parsing errors were logged. Post-processing made five repair attempts before the released pipeline caught a Spoon NullPointerException (sourceMethod null during prettyprint); this is a separate deviation, preserved without modifying source, algorithm or scoring.

The reference evaluator again failed to parse branch coverage 'n/a', this time at Base64.decodeInteger. Per Gary's explicit exception this is nonblocking. Its final summary/rates are unavailable; the raw execution dictionary records 23 compiled files, 369 executed test cases and 263 passed cases. No recorded 600-second timeouts or OOMs; the slow SHA-256 class returned failures after about 497 seconds, before its timeout. Archive: results/commit-survival/round2/commons-codec. Provider identity remains unavailable in released logs. Independent three-run validation is next; no denominator is frozen yet.
