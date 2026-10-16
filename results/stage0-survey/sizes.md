# Developer suite and production population at t

All counts: `python3 docker/stage0_count_methods.py`. Parser installed with `python3 -m pip install --target scratch/python tree-sitter==0.25.2 tree-sitter-java==0.23.5`. Counts use Java syntax trees from clean `git archive` exports. Constructors, private methods and bodyless methods are excluded; nested/local/anonymous method declarations are included. Classes count named class declarations including nested/local classes; types additionally include interfaces/enums/annotations/records. Test classes are a heuristic: class containing a test annotation/test-prefixed method, or extending a Test-named class. JUnit-3-style method and suite() counts are signals, not executed counts. Annotations in comments/strings are excluded. JDOM counts use its actual upstream roots (`core/src/java`, `test/src/java`); literal src/main/java and src/test/java are absent.

| Project | Test Java files / test classes | @Test | @ParameterizedTest / @RepeatedTest / @TestFactory | Abstract test classes / suite() | JUnit imports | Non-private methods (public/protected/package) | Production classes / types | Focal classes present / dataset | Parse-error files |
|---|---|---:|---|---|---|---|---|---|---:|
| batch-processing-gateway | 22 / 22 | 70 | 0 / 0 / 0 | 0 / 0 | TestNG | 728 (712/16/0) | 105 / 108 | 3 / 3 | 0 |
| commons-cli | 33 / 33 | 322 | 3 / 0 / 0 | 1 / 0 | JUnit 4,JUnit 5 | 234 (205/19/10) | 25 / 27 | 2 / 2 | 0 |
| commons-codec | 75 / 69 | 834 | 38 / 0 / 0 | 3 / 0 | JUnit 5 | 631 (568/22/41) | 85 / 97 | 12 / 12 | 0 |
| commons-collections | 270 / 291 | 1763 | 17 / 0 / 3 | 45 / 1 | JUnit 3,JUnit 5 | 3509 (3147/288/74) | 459 / 512 | 5 / 5 | 0 |
| commons-csv | 36 / 33 | 466 | 4 / 0 / 0 | 0 / 0 | JUnit 5 | 207 (172/0/35) | 12 / 16 | 3 / 3 | 0 |
| datafaker | 274 / 271 | 567 | 55 / 213 / 0 | 0 / 0 | JUnit 5 | 1648 (1637/5/6) | 290 / 318 | 3 / 3 | 0 |
| gson | 119 / 115 | 1249 | 0 / 0 / 0 | 4 / 0 | JUnit 4 | 633 (598/4/31) | 78 / 99 | 10 / 10 | 0 |
| jdom2 | 109 / 97 | 1257 | 0 / 0 / 0 | 9 / 0 | JUnit 4 | 1354 (1250/84/20) | 145 / 178 | 15 / 15 | 0 |
| ruler | 33 / 32 | 479 | 0 / 0 / 0 | 0 / 0 | JUnit 3,JUnit 4 | 301 (183/0/118) | 52 / 59 | 6 / 6 | 0 |
| windward | 4 / 4 | 8 | 0 / 0 / 0 | 0 / 0 | JUnit 3,JUnit 4 | 251 (243/8/0) | 74 / 102 | 2 / 2 | 0 |

Abstract classes, inherited tests and suite factories (especially commons-collections), JUnit 3 naming and parameterized tests make annotation counts unsuitable as executed-test counts. Full import evidence and JUnit-3-style method counts are in sizes.json.

Ruler uses src/main and src/test directly, as declared by its upstream POM; counts include those roots. Its src/main/java directory is absent upstream.
