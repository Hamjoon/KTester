# Upstream survey

Invocation for every value below: `python3 docker/stage0_upstream.py` (repository root). Full clones use `git clone` without depth/filter; default branch uses `git symbolic-ref refs/remotes/origin/HEAD`; HEAD/date/count use `rev-parse`, `show --format=%cI`, `rev-list --first-parent --count`; allocated clone size uses `du -sk`. Remote observation is the clone time, not a historical cutoff.

| Project | Upstream | Default branch | HEAD | Commit date | First-parent commits | Clone KiB |
|---|---|---|---|---|---:|---:|
| batch-processing-gateway | apple/batch-processing-gateway | main | 0372006ce00a50c565e947d608928b77aafc8575 | 2026-05-06T16:08:21-07:00 | 33 | 2564 |
| commons-cli | apache/commons-cli | master | 58c64b52a8fb1c38c4f4b2d41db0349241475f95 | 2026-10-15T08:31:58-04:00 | 1964 | 6996 |
| commons-codec | apache/commons-codec | master | a3d9418f2d9cd2b8b012af4280ec0bb87ec33303 | 2026-10-15T09:19:44-04:00 | 3445 | 12916 |
| commons-collections | apache/commons-collections | master | 0b545e2f21ee8f2e8269201b5754465d76db70c7 | 2026-10-15T09:19:46-04:00 | 5186 | 36580 |
| commons-csv | apache/commons-csv | master | e07e3dfed1a73ed2001ce5ef545651ad3b198685 | 2026-10-15T09:19:57-04:00 | 2780 | 85888 |
| datafaker | datafaker-net/datafaker | main | b785d61246311cef8cbe9cb48666dc4fb6c74d87 | 2026-10-14T09:47:09-04:00 | 2473 | 32096 |
| gson | google/gson | main | e2292f9c9f2e07a4791827e8eea34aa7d80545d6 | 2026-10-11T14:06:38-07:00 | 2059 | 23368 |
| jdom2 | hunterhacker/jdom | master | c6065f684bdff5cebf5f23b2859c5e4c89fd89db | 2021-10-20T14:50:34-07:00 | 1618 | 66804 |
| ruler | aws/event-ruler | main | ced786201c1f43ec73482b78f3d1843aa4bd2f89 | 2026-09-21T14:32:18-07:00 | 195 | 113152 |
| windward | Flmelody/windward | main | e3badb29860fad688eebd664b835714370c2bd68 | 2024-12-22T09:57:45+08:00 | 248 | 1344 |

## Snapshot identity evidence

- batch-processing-gateway: Snapshot omits own repository URL; candidate from survey instructions, verified by source comparison in Step 2 (identity provisional here).
- commons-cli: url: https://commons.apache.org/proper/commons-cli/; scm/url: https://gitbox.apache.org/repos/asf?p=commons-cli.git; scm/connection: scm:git:https://gitbox.apache.org/repos/asf/commons-cli.git; snapshot remote: https://github.com/apache/commons-cli.git
- commons-codec: url: https://commons.apache.org/proper/commons-codec/; scm/url: https://github.com/apache/commons-codec; scm/connection: scm:git:https://gitbox.apache.org/repos/asf/commons-codec
- commons-collections: url: https://commons.apache.org/proper/commons-collections/; scm/url: https://gitbox.apache.org/repos/asf?p=commons-collections.git; scm/connection: scm:git:http://gitbox.apache.org/repos/asf/commons-collections.git
- commons-csv: url: https://commons.apache.org/proper/commons-csv/; scm/url: https://gitbox.apache.org/repos/asf?p=commons-csv.git; scm/connection: scm:git:http://gitbox.apache.org/repos/asf/commons-csv.git
- datafaker: url: https://www.datafaker.net; scm/url: https://github.com/datafaker-net/datafaker; scm/connection: scm:git:https://github.com/datafaker-net/datafaker.git
- gson: url: https://github.com/google/gson; scm/url: https://github.com/google/gson/; scm/connection: scm:git:https://github.com/google/gson.git
- jdom2: Snapshot omits own repository URL; candidate from survey instructions, verified by source comparison in Step 2 (identity provisional here).
- ruler: url: https://github.com/aws/event-ruler/; scm/url: https://github.com/aws/event-ruler.git
- windward: url: https://windward.flmelody.org; scm/url: https://github.com/Flmelody/windward; scm/connection: scm:git:git://github.com/Flmelody/windward

Apache gitbox SCM URLs refer to the same Apache projects as their GitHub mirrors. Preserve the snapshot SCM URL above; GitHub mirror is used for requested GitHub history.
