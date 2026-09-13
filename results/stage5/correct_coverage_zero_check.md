# Correct-coverage zero check (evaluator selector bug)

Symptom: a task has `passed_cases > 0` and `inst_cov > 0` but `correct_inst_cov == 0`. Mechanism: see `docs/handover-stage4c.md`, "Windward correct-coverage anomaly". Counted from `reports/summary.json` of each run and of each authors' repetition (rep_1 uses `reports/gpt4o/summary.json` except batch-processing-gateway).

| Run | Affected tasks | Of 111 |
| --- | ---: | ---: |
| Ours, gpt-4o-mini, FIX_TRIES=3 | 2 | 1.8% |
| Ours, gpt-4o-mini, FIX_TRIES=5 | 0 | 0.0% |
| Ours, gpt-4o, FIX_TRIES=5 | 6 | 5.4% |
| Authors rep_1 | 0 | 0.0% |
| Authors rep_2 | 4 | 3.6% |
| Authors rep_3 | 0 | 0.0% |
| Authors rep_4 | 5 | 4.5% |
| Authors rep_5 | 2 | 1.8% |
| Authors mean of 5 | 2.2 | 2.0% |

Affected tasks, ours gpt-4o (passed cases, inst_cov): commons-codec DoubleMetaphone_doubleMetaphone (30, 0.92); commons-collections AbstractPatriciaTrie_nextEntryImpl (9, 0.38); jdom2 AbstractStAXStreamProcessor_printDocument (4, 0.28); ruler ByteMachine_findMatchPattern (3, 0.23); ruler GenericMachine_deleteStep (1, 0.17); windward AntPathMatcher_isMatch (23, 0.86).

Upper-bound correction (replace each affected task's correct_inst_cov by its inst_cov; the true value is at most this, since correct coverage counts passing tests only):

| Side | Mean correct inst cov | Corrected upper bound |
| --- | ---: | ---: |
| Ours, gpt-4o, FIX_TRIES=5 | 45.77 | 48.33 |
| Authors rep_1 | 54.73 | 54.73 |
| Authors rep_2 | 53.03 | 54.71 |
| Authors rep_3 | 55.70 | 55.70 |
| Authors rep_4 | 54.93 | 57.02 |
| Authors rep_5 | 59.19 | 60.99 |
| Authors mean of 5 | 55.52 | 56.63 |

Gap ours vs authors mean: 9.75 pp as measured, 8.30 pp after correcting both sides. The bug does not explain the gap.
