# Build and test at t

All numbers: from `docker/`, `docker compose run --rm ktester bash -c 'python /work/docker/stage0_build_tests.py'`, followed by `docker compose run --rm ktester bash -c 'python /work/docker/stage0_build_tests.py --repeat-existing'`. Render with `python3 docker/stage0_reports.py build`. Commands, environment, wall times and counts are in build-at-t.json and build/<project>.json; complete stdout/stderr logs are in build/*.txt.

Fresh `git archive` exports were built under scratch/build. Shared Maven cache: compose volume ktester-m2 mounted at /root/.m2. Zero Surefire XML files means no executed-test evidence, not an empty suite. Two projects ran concurrently; wall times include contention. Each project was capped at a combined 30 minutes, with each test command limited to remaining time. The four successful projects were tested again; two passes cannot establish absence of flakiness.

| Project | Package exit / seconds | Test exit / seconds | Tests / failures / errors / skipped | XML files | Repeat exit / seconds / same counts |
|---|---|---|---|---:|---|
| commons-codec | 0 / 25.992 | 0 / 17.65 | 1715 / 0 / 0 / 2 | 65 | 0 / 23.511 / True |
| commons-collections | 0 / 24.242 | 0 / 26.668 | 6528 / 0 / 0 / 28 | 215 | 0 / 28.271 / True |
| gson | 0 / 13.452 | 0 / 8.028 | 1328 / 0 / 0 / 19 | 122 | 0 / 7.176 / True |
| jdom2 | 1 / 0.669 | 1 / 0.831 | 0 / 0 / 0 / 0 | 0 | not attempted after repeated Maven failure |
| ruler | 0 / 6.94 | 0 / 28.287 | 469 / 0 / 0 / 0 | 31 | 0 / 23.855 / True |

## Build configuration and loop implications

Environment (recorded by driver): OpenJDK 17.0.20.1 and Maven 3.9.9. No compiler-release overrides or extra toolchains were needed for successful builds. Exact package command: `mvn -q -DskipTests -Drat.skip -Dcheckstyle.skip -Dspotbugs.skip -Dpmd.skip -Dmaven.javadoc.skip package`; exact test command: `mvn -q test`. Skip properties are harmless where undeclared; plain test commands use normal lifecycle bindings.

POM/build inspection: `rg -n "source>|target>|release>|jdkToolchain|artifactId>|phase>|sourceDirectory>|testSourceDirectory>|target name=" scratch/at-t/{commons-codec,commons-collections,gson,ruler}/pom.xml scratch/at-t/jdom2/build.xml`.

- commons-codec and commons-collections target Java 8 and build on the installed JDK. Their parent-driven quality/coverage plugins and collections test-resource generation should be retained or deliberately controlled during later loops.
- Gson sets release 7 and accepts a compiler toolchain version [11,); the installed JDK satisfies it. Its reactor includes extras and a ProGuard build/test module. The recorded suite total covers all reactor Surefire reports, not only gson/src/test/java.
- Ruler targets Java 8 and explicitly sets sourceDirectory=src/main and testSourceDirectory=src/test. Checkstyle and SpotBugs are configured in its POM; seeded randomized tests print reproduction seeds in the retained logs.
- JDOM upstream is an Ant project with build.xml and Maven publishing templates under maven/, not a root Maven project. Both package and test fail with MissingProjectException because pom.xml is absent. This is not evidence of JDK incompatibility. Per the repeated-failure rule, no third Maven attempt or transplanted snapshot POM was used. An Ant-specific experiment setup remains open.
- Initial builds may populate the shared dependency/plugin cache. Quiet logs do not prove which artifacts were newly downloaded. Successful repeat test runs reuse the same cache.
- commons-codec: 0 individual tests and 0 suites exceeded 60 seconds in the first run (Surefire XML, same driver invocation).
- commons-collections: 0 individual tests and 0 suites exceeded 60 seconds in the first run (Surefire XML, same driver invocation).
- gson: 0 individual tests and 0 suites exceeded 60 seconds in the first run (Surefire XML, same driver invocation).
- ruler: 0 individual tests and 0 suites exceeded 60 seconds in the first run (Surefire XML, same driver invocation).
