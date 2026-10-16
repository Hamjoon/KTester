# History after the matched commit

All values: `python3 docker/stage0_history.py`. Commits are first-parent descendants through the HEAD frozen in upstream.json; changes compare each commit to its first parent (including merges). Release tags point directly to that line, contain a digit, and exclude RC/alpha/beta names. Test/docs/build-only uses the explicit path classifier in the script; all remaining non-src/main commits are reported separately, not silently treated as test-only. Milestone index counts all commits through the specified source-changing commit.

| Project | After t | Date span | Change src/main | Only tests/docs/build | Other non-src | Release tags (first five) | 100th source change: index/date | 200th: index/date |
|---|---:|---|---:|---:|---:|---|---|---|
| batch-processing-gateway | 12 | 2024-06-25T22:40:27-07:00 → 2026-05-06T16:08:21-07:00 | 2 | 6 | 4 | 0:  | not reached | not reached |
| commons-cli | 746 | 2024-02-28T08:21:10-05:00 → 2026-10-15T08:31:58-04:00 | 247 | 496 | 3 | 2: rel/commons-cli-1.7.0, rel/commons-cli-1.11.0 | 274 / 2024-10-16T16:07:10-04:00 | 552 / 2025-11-08T11:40:14-05:00 |
| commons-codec | 830 | 2024-05-16T16:44:32-04:00 → 2026-10-15T09:19:44-04:00 | 327 | 499 | 4 | 2: rel/commons-codec-1.17.1, rel/commons-codec-1.23.0 | 338 / 2025-07-18T21:51:03-04:00 | 569 / 2026-02-16T18:19:19-05:00 |
| commons-collections | 1201 | 2024-04-02T19:45:49Z → 2026-10-15T09:19:46-04:00 | 547 | 651 | 3 | 2: rel/commons-collections-4.5.0-M3, rel/commons-collections-4.5.0 | 182 / 2024-06-23T16:04:12-04:00 | 327 / 2024-08-25T16:31:55-04:00 |
| commons-csv | 909 | 2023-02-02T07:50:41-05:00 → 2026-10-15T09:19:57-04:00 | 210 | 692 | 7 | 3: rel/commons-csv-1.11.0, rel/commons-csv-1.14.0, rel/commons-csv-1.14.1 | 486 / 2025-01-13T10:58:11-05:00 | 866 / 2026-07-23T07:43:16-04:00 |
| datafaker | 1110 | 2023-02-12T19:42:38+11:00 → 2026-10-14T09:47:09-04:00 | 422 | 677 | 11 | 21: v2.0.0, v2.0.1, v2.0.2, v2.1.0, v2.2.0 | 229 / 2023-09-17T13:53:40-04:00 | 511 / 2024-08-10T07:00:03-04:00 |
| gson | 477 | 2023-01-06T07:42:33-08:00 → 2026-10-11T14:06:38-07:00 | 142 | 314 | 21 | 7: gson-parent-2.11.0, gson-parent-2.12.0, gson-parent-2.12.1, gson-parent-2.13.0, gson-parent-2.13.1 | 383 / 2025-11-24T12:42:13-08:00 | not reached |
| jdom2 | 11 | 2015-11-14T08:32:22-05:00 → 2021-10-20T14:50:34-07:00 | 0 | 0 | 11 | 1: JDOM-2.0.6.1 | not reached | not reached |
| ruler | 142 | 2023-04-10T08:48:00-07:00 → 2026-09-21T14:32:18-07:00 | 41 | 100 | 1 | 19: v1.2.2, v1.4.0, v1.5.0, v1.6.0, v1.7.0 | not reached | not reached |
| windward | 49 | 2024-05-05T22:14:06+08:00 → 2024-12-22T09:57:45+08:00 | 27 | 22 | 0 | 3: 1.5.1-RELEASE, 1.5.2-RELEASE, 1.5.3-RELEASE | not reached | not reached |

JDOM has no upstream src/main. Its actual `core/src/java` changes in 8 commits (same invocation); literal src/main counts above must not be interpreted as no production evolution.
