# Snapshot match

Invocation for all numbers: `python3 docker/stage0_match_snapshot.py`. File contents are read using persistent `git cat-file --batch` (same blobs as `git show SHA:path`), with CRLF→LF and trailing whitespace stripped per line. Candidates: known CLI commit, release-tag neighborhoods, matching project/Apache-parent versions, adjacent tag intervals, then complete first-parent history. Stop at first exact source match; this does not establish a unique original commit. Dates are committer dates. Tags must point directly to the first-parent line; nearest before includes the matched commit.

| Project | Commit | Date | Exact / Δ changed, snapshot-only, commit-only | Tag at/before | Tag after | Candidates checked |
|---|---|---|---|---|---|---:|
| batch-processing-gateway | 2cc812924998ff0e8bd86415f0831fafa406d76d | 2024-04-17T16:09:09-07:00 | Δ 2,0,0 |  |  | 33 |
| commons-cli | 90b3f2e35a13720cd9eca45ca585d984398a1198 | 2024-02-26T21:10:03-05:00 | exact | commons-cli-1.5.0, commons-cli-1.5.0-RC1 | commons-cli-1.7.0-RC1, rel/commons-cli-1.7.0 | 1 |
| commons-codec | cd16988dd46ac7533771eb6856a5f56c7aeaffba | 2024-05-16T14:47:23-04:00 | Δ 2,0,0 | commons-codec-1.16.1-RC1, rel/commons-codec-1.16.1 | commons-codec-1.17.1-RC1, rel/commons-codec-1.17.1 | 3445 |
| commons-collections | 2f8305d6a268d51e4b41e183f6d8b61c3c1ef99b | 2024-03-31T16:00:27Z | exact | commons-collections-4.5.0-M1-RC1, rel/commons-collections-4.5.0-M1 | commons-collections-4.5.0-M3-RC1, rel/commons-collections-4.5.0-M3 | 1 |
| commons-csv | 5b99c860986a87f9da664bf47a3a7c0026673123 | 2023-01-28T12:44:29-05:00 | Δ 2,0,0 | commons-csv-1.10.0-RC2, rel/commons-csv-1.10.0 | commons-csv-1.11.0-RC1, rel/commons-csv-1.11.0 | 2780 |
| datafaker | a48488965b4a95988855dee11968a12287f3eb58 | 2023-02-12T11:48:26+11:00 | Δ 10,3,0 | v1.8.0 | v2.0.0 | 2473 |
| gson | 2ce6a6106c22bf0cce2e39f68b0da2f117d6e66b | 2023-01-06T07:42:31-08:00 | Δ 2,0,0 | gson-parent-2.10.1 | gson-parent-2.11.0 | 2059 |
| jdom2 | 91d8b6bd0ff1112c5a6acfea141b1ede96f7626f | 2015-11-14T08:27:11-05:00 | literal Δ 0,146,0; relocated Δ 8,0,6 | JDOM-2.0.4 | JDOM-2.0.6.1 | 1618 |
| ruler | 7d588b1bfa5218259dbbf940baf2ee9cb2c8fcbb | 2023-03-31T14:42:48-07:00 | Δ 0,52,0 | v1.2.1 | v1.2.2 | 195 |
| windward | edafa0cd601c432151f6aac1723e5ab4fb1e6a09 | 2024-04-22T22:23:46+08:00 | exact | 1.5.0-RELEASE | 1.5.1-RELEASE | 14 |

## Non-exact cases

### batch-processing-gateway

Closest normalized source tree; no exact match in searched history.

- differing (2): src/main/java/com/apple/spark/core/ApplicationSubmissionHelper.java, src/main/java/com/apple/spark/rest/ApplicationSubmissionRest.java
- snapshot_only (0): 
- commit_only (0): 
### commons-codec

Closest normalized source tree; no exact match in searched history.

- differing (2): src/main/java/org/apache/commons/codec/binary/Base32.java, src/main/java/org/apache/commons/codec/binary/Base64.java
- snapshot_only (0): 
- commit_only (0): 
### commons-csv

Closest normalized source tree; no exact match in searched history.

- differing (2): src/main/java/org/apache/commons/csv/CSVFormat.java, src/main/java/org/apache/commons/csv/CSVParser.java
- snapshot_only (0): 
- commit_only (0): 
### datafaker

Closest normalized source tree; no exact match in searched history.

- differing (10): src/main/java/net/datafaker/providers/base/Address.java, src/main/java/net/datafaker/providers/base/BaseFaker.java, src/main/java/net/datafaker/providers/base/Drone.java, src/main/java/net/datafaker/providers/base/Locality.java, src/main/java/net/datafaker/providers/base/Mbti.java, src/main/java/net/datafaker/providers/base/ProviderRegistration.java, src/main/java/net/datafaker/providers/base/Text.java, src/main/java/net/datafaker/providers/entertainment/OscarMovie.java, src/main/java/net/datafaker/service/FakeValues.java, src/main/java/net/datafaker/service/FakeValuesService.java
- snapshot_only (3): src/main/java/net/datafaker/annotations/Deterministic.java, src/main/java/net/datafaker/annotations/FakeForSchema.java, src/main/java/net/datafaker/annotations/FakeResolver.java
- commit_only (0): 
### gson

Closest normalized source tree; no exact match in searched history.

- differing (2): gson/src/main/java/com/google/gson/internal/Primitives.java, gson/src/main/java/com/google/gson/internal/bind/ObjectTypeAdapter.java
- snapshot_only (0): 
- commit_only (0): 
### jdom2

No literal src/main tree in upstream: authors relocated core/src/java to src/main/java and supplied a Maven pom. Counts shown separately for relocated source comparison; never an exact literal tree match.

- differing (8): org/jdom2/Element.java, org/jdom2/Verifier.java, org/jdom2/input/sax/TextBuffer.java, org/jdom2/output/Format.java, org/jdom2/output/XMLOutputter.java, org/jdom2/output/support/AbstractStAXStreamProcessor.java, org/jdom2/output/support/StAXStreamProcessor.java, org/jdom2/util/NamespaceStack.java
- snapshot_only (0): 
- commit_only (6): org/jdom2/input/StAXStreamWriter.java, org/jdom2/output/StAXStreamReader.java, org/jdom2/output/support/AbstractStAXStreamReader.java, org/jdom2/output/support/AbstractStAXStreamReaderProcessor.java, org/jdom2/output/support/StAXStreamReaderProcessor.java, org/jdom2/util/JDOMNamespaceContext.java
### ruler

Closest normalized source tree; no exact match in searched history.

- differing (0): 
- snapshot_only (52): src/main/java/software/amazon/event/ruler/ACFinder.java, src/main/java/software/amazon/event/ruler/ACStep.java, src/main/java/software/amazon/event/ruler/ACTask.java, src/main/java/software/amazon/event/ruler/AnythingBut.java, src/main/java/software/amazon/event/ruler/AnythingButEqualsIgnoreCase.java, src/main/java/software/amazon/event/ruler/ArrayMembership.java, src/main/java/software/amazon/event/ruler/ByteMachine.java, src/main/java/software/amazon/event/ruler/ByteMap.java, src/main/java/software/amazon/event/ruler/ByteMatch.java, src/main/java/software/amazon/event/ruler/ByteState.java, src/main/java/software/amazon/event/ruler/ByteTransition.java, src/main/java/software/amazon/event/ruler/CIDR.java, src/main/java/software/amazon/event/ruler/ComparableNumber.java, src/main/java/software/amazon/event/ruler/CompositeByteTransition.java, src/main/java/software/amazon/event/ruler/CompoundByteTransition.java, src/main/java/software/amazon/event/ruler/Constants.java, src/main/java/software/amazon/event/ruler/Event.java, src/main/java/software/amazon/event/ruler/Field.java, src/main/java/software/amazon/event/ruler/Finder.java, src/main/java/software/amazon/event/ruler/GenericMachine.java
- commit_only (0): 

Ruler retains an exact upstream `src/main/software` tree plus a second snapshot-only copy under `src/main/java/software`; the reported snapshot-only files are that added copy. The upstream production root for subsequent size counts is `src/main/software`, and its tests use `src/test/software`.
