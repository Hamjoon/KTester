# Independent verification and release-tag diagnostics

All values: `python3 docker/stage0_verify.py`. It independently exports each selected commit with git archive and recomputes normalized path/content differences, checking against the batch-object matcher. It also inspects release tags matching the snapshot version even when the tag lies off the default first-parent line. The selected t remains the best commit on that line, as required for subsequent first-parent traversal.

| Project | Independent match check | Release tag | On first-parent line | Δ changed / snapshot-only / commit-only |
|---|---|---|---|---|
| batch-processing-gateway | passed | no matching release-version tag probed | | |
| commons-cli | passed | no matching release-version tag probed | | |
| commons-codec | passed | no matching release-version tag probed | | |
| commons-collections | passed | commons-collections-4.5.0-M1-RC1 | True | 0 / 0 / 0 |
| commons-collections | passed | rel/commons-collections-4.5.0-M1 | True | 0 / 0 / 0 |
| commons-csv | passed | commons-csv-1.10.0-RC1 | True | 4 / 0 / 0 |
| commons-csv | passed | commons-csv-1.10.0-RC2 | True | 2 / 0 / 0 |
| commons-csv | passed | rel/commons-csv-1.10.0 | True | 2 / 0 / 0 |
| datafaker | passed | v1.9.0 | False | 1 / 0 / 0 |
| gson | passed | gson-parent-2.10.1 | True | 2 / 0 / 0 |
| jdom2 | passed | JDOM-2.0.6 | False | 2 / 0 / 0 |
| ruler | passed | v1.2.1 | True | 0 / 52 / 0 |
| windward | passed | no matching release-version tag probed | | |

JDOM counts above compare relocated Java source, not literal src/main paths. A release on a side branch may be closer than any commit on the default first-parent line; that does not make it an exact first-parent snapshot match. Full tag SHAs are in verification.json.

History category totals balance and all Java inputs parse without syntax-error nodes. The tracked diff against the starting branch is empty for code/, data/projects/ and data/project_index/. Ignored snapshots were accessed read-only; this Git check alone cannot prove the state of ignored files.
