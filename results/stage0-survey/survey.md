# Commit-survival project survey

Every table value is rendered by `python3 docker/stage0_handover.py` from the measured JSON artifacts. Measurement invocations: `python3 docker/stage0_upstream.py` (repository/HEAD); `python3 docker/stage0_match_snapshot.py` (snapshot); `python3 docker/stage0_history.py` (history/tags); `python3 docker/stage0_count_methods.py` (annotations/methods); from docker/, `docker compose run --rm ktester bash -c 'python /work/docker/stage0_build_tests.py'` (builds/tests); `python3 docker/stage0_verify.py` (independent matches and off-line release probes). Exact per-project commands and environments are in the corresponding reports and JSON.

t is an exact or best normalized source match on the default first-parent line. This is a survey anchor, not proof of the authors’ original checkout. Δ lists changed/snapshot-only/commit-only files. Tags in the main table point directly to that line and before includes t. Date is the committer date. Test totals include skipped tests and, for Gson, all reactor modules. Blank executed-test cells mean no run/evidence. No project is selected.

| Project | Upstream repo | Snapshot match (SHA, date, exact or Δ) | Nearest tag before / after | First-parent commits after t | Change src/main | Release tags after t | Dev @Test at t | Dev tests executed | Non-private methods | Build at t (JDK) | Dev test wall seconds | Notes |
|---|---|---|---|---:|---:|---:|---:|---:|---:|---|---:|---|
| batch-processing-gateway | apple/batch-processing-gateway | 2cc812924998, 2024-04-17, Δ 2/0/0 | none / none | 12 | 2 | 0 | 70 |  | 728 | not attempted |  | @Test annotations are TestNG |
| commons-cli | apache/commons-cli | 90b3f2e35a13, 2024-02-26, exact | commons-cli-1.5.0, commons-cli-1.5.0-RC1 / commons-cli-1.7.0-RC1, rel/commons-cli-1.7.0 | 746 | 247 | 2 | 322 |  | 234 | not attempted |  |  |
| commons-codec | apache/commons-codec | cd16988dd46a, 2024-05-16, Δ 2/0/0 | commons-codec-1.16.1-RC1, rel/commons-codec-1.16.1 / commons-codec-1.17.1-RC1, rel/commons-codec-1.17.1 | 830 | 327 | 2 | 834 | 1715 | 631 | ok / JDK 17 | 17.65 | repeat tests passed |
| commons-collections | apache/commons-collections | 2f8305d6a268, 2024-03-31, exact | commons-collections-4.5.0-M1-RC1, rel/commons-collections-4.5.0-M1 / commons-collections-4.5.0-M3-RC1, rel/commons-collections-4.5.0-M3 | 1201 | 547 | 2 | 1763 | 6528 | 3509 | ok / JDK 17 | 26.668 | Abstract/inherited and factory tests expand execution count; repeat tests passed |
| commons-csv | apache/commons-csv | 5b99c860986a, 2023-01-28, Δ 2/0/0 | commons-csv-1.10.0-RC2, rel/commons-csv-1.10.0 / commons-csv-1.11.0-RC1, rel/commons-csv-1.11.0 | 909 | 210 | 3 | 466 |  | 207 | not attempted |  |  |
| datafaker | datafaker-net/datafaker | a48488965b4a, 2023-02-12, Δ 10/3/0 | v1.8.0 / v2.0.0 | 1110 | 422 | 21 | 567 |  | 1648 | not attempted |  | Off-line v1.9.0 release is closer (verification.md); repeated/parameterized tests |
| gson | google/gson | 2ce6a6106c22, 2023-01-06, Δ 2/0/0 | gson-parent-2.10.1 / gson-parent-2.11.0 | 477 | 142 | 7 | 1249 | 1328 | 633 | ok / JDK 17 | 8.028 | Sizes cover gson module; executed count covers full reactor; repeat tests passed |
| jdom2 | hunterhacker/jdom | 91d8b6bd0ff1, 2015-11-14, relocated Δ 8/0/6; literal Δ 0/146/0 | JDOM-2.0.4 / JDOM-2.0.6.1 | 11 | 0 | 1 | 1257 |  | 1354 | fail / JDK 17 | 0.831 | Ant upstream; actual core/src/java changes: 8; off-line release JDOM-2.0.6 is closer (verification.md); Maven fails: no root POM |
| ruler | aws/event-ruler | 7d588b1bfa52, 2023-03-31, Δ 0/52/0 | v1.2.1 / v1.2.2 | 142 | 41 | 19 | 479 | 469 | 301 | ok / JDK 17 | 28.287 | Snapshot adds duplicate Java source tree; upstream roots src/main and src/test; repeat tests passed |
| windward | Flmelody/windward | edafa0cd601c, 2024-04-22, exact | 1.5.0-RELEASE / 1.5.1-RELEASE | 49 | 27 | 3 | 8 |  | 251 | not attempted |  |  |

Facts (same rendering invocation and underlying commands above):

- Exact normalized first-parent source matches: 3 — commons-cli, commons-collections, windward.
- Commons-collections executes 6528 tests versus 1763 @Test annotations (3.70×); both runs pass.
- The Maven builds and repeated developer tests pass for commons-codec, commons-collections, Gson and ruler on the installed JDK. JDOM has no upstream root POM and requires an Ant-specific setup.
- Datafaker v1.9.0 differs in 1 source file and JDOM-2.0.6 in 2 relocated source files; both tags are off the default first-parent line, so those anchors would require a different traversal definition.
- The index rebuild stops at invoke-pattern graph reachability; independent Lucene identifiers match. DatasetPrepare reproduces existing commons-cli entries after line-ending normalization and adds another method successfully.
