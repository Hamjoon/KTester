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
