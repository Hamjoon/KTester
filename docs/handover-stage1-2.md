# KTester replication handover: Stages 1–2

Completed on 2026-09-10 on branch `experiment/2026-09-week2-ktester-replication`. The full dataset and `generate_unit_test.py` were not run. `code/settings.py` remains ignored and contains the Stage 3 placeholder `REPLACE_BEFORE_STAGE3`.

## Docker environment

- Image: `ktester-replication:latest`
- Base image: `python:3.13-slim-bookworm`
- CPU architecture: `aarch64`
- Java:

  ```text
  openjdk version "17.0.20.1" 2026-08-18
  OpenJDK Runtime Environment (build 17.0.20.1+1-1-deb12u1-Debian)
  OpenJDK 64-Bit Server VM (build 17.0.20.1+1-1-deb12u1-Debian, mixed mode, sharing)
  ```

- Maven: `Apache Maven 3.9.9 (8e857f1c4d881e10e299e70c0d1de9d4993f9024)`
- Python: `Python 3.13.15`
- JPype: `1.6.0`
- `JAVA_HOME`: `/usr/lib/jvm/java-17`
- JVM library: `/usr/lib/jvm/java-17/lib/server/libjvm.so`
- JPype default JVM: `/usr/lib/jvm/java-17-openjdk-arm64/lib/server/libjvm.so`

## Commits

- `d026ca0` — Add Docker environment for Linux replication (Java 17, Maven 3.9.9, Python 3.13)
- `c81c0ee` — Reset dataset test workspaces before Linux replication
- `15d0808` — Add TimeRecord settings missing from template; guard first-time key in time_agent
- `43b0295` — workspace_preparation: run Maven with cwd instead of shell list; use os.pathsep in dependencies.txt
- `41f216e` — execute_test: drop shell=True (cwd instead), os.pathsep for test classpath
- `b0e09e4` — post_process: match javac error paths with os.sep
- `f8ef542` — Use os.pathsep in JPype classpath
- Current handover commit — Document Stage 1 and Stage 2 replication handover

## Step 5: workspace preparation

`python preparation.py -W -F /work/evaluation/logs/prep-workspace.log` completed and continued past the Gson failure as instructed.

| Project | Compiled classes | Semicolons | Colons | Result |
|---|---:|---:|---:|---|
| batch-processing-gateway | 118 | 0 | 340 | Pass |
| commons-cli | 31 | 0 | 14 | Pass |
| commons-codec | 114 | 0 | 17 | Pass |
| commons-collections | 582 | 0 | 29 | Pass |
| commons-csv | 19 | 0 | 19 | Pass |
| datafaker | 325 | 0 | 38 | Pass |
| gson/gson | 213 | 14 | 0 | Maven failure; `dependencies.txt` was not rewritten |
| jdom2 | 189 | 0 | 19 | Pass |
| ruler | 65 | 0 | 18 | Pass |
| windward | 103 | 0 | 76 | Pass |

Gson's direct `mvn compiler:compile` does not run the lifecycle phase that generates `GsonBuildConfig`:

```text
[ERROR] COMPILATION ERROR :
[ERROR] /work/data/projects/gson/gson/src/main/java/com/google/gson/Gson.java:[21,32] cannot find symbol
  symbol:   class GsonBuildConfig
  location: package com.google.gson.internal
[ERROR] /work/data/projects/gson/gson/src/main/java/com/google/gson/internal/reflect/ReflectionHelper.java:[4,32] cannot find symbol
  symbol:   class GsonBuildConfig
  location: package com.google.gson.internal
[ERROR] /work/data/projects/gson/gson/src/main/java/com/google/gson/Gson.java:[571,53] cannot find symbol
  symbol:   variable GsonBuildConfig
  location: class com.google.gson.Gson
[ERROR] /work/data/projects/gson/gson/src/main/java/com/google/gson/Gson.java:[846,58] cannot find symbol
  symbol:   variable GsonBuildConfig
  location: class com.google.gson.Gson
[ERROR] /work/data/projects/gson/gson/src/main/java/com/google/gson/Gson.java:[949,58] cannot find symbol
  symbol:   variable GsonBuildConfig
  location: class com.google.gson.Gson
[ERROR] /work/data/projects/gson/gson/src/main/java/com/google/gson/Gson.java:[1243,58] cannot find symbol
  symbol:   variable GsonBuildConfig
  location: class com.google.gson.Gson
[ERROR] /work/data/projects/gson/gson/src/main/java/com/google/gson/internal/reflect/ReflectionHelper.java:[153,86] cannot find symbol
  symbol:   variable GsonBuildConfig
  location: class com.google.gson.internal.reflect.ReflectionHelper
[ERROR] /work/data/projects/gson/gson/src/main/java/com/google/gson/internal/reflect/ReflectionHelper.java:[163,27] cannot find symbol
  symbol:   variable GsonBuildConfig
  location: class com.google.gson.internal.reflect.ReflectionHelper
```

The fallback `mvn clean install` generated the class and compiled the main sources, but failed later because the reset workspace intentionally has no original test classes in `src/test`:

```text
[ERROR] Failed to execute goal com.coderplus.maven.plugins:copy-rename-maven-plugin:1.0.1:rename (pre-obfuscate-class) on project gson: sourceFile /work/data/projects/gson/gson/target/test-classes/com/google/gson/functional/EnumWithObfuscatedTest.class does not exist -> [Help 1]
Error: Failed to build project /work/code/../data/projects/gson/gson.
```

No Gson POM or source file was changed. Its 213 main classes are present from the fallback lifecycle before that later failure, but its `dependencies.txt` remains the original Windows-formatted file.

## Step 6: JVM and indexes

```text
Picked up _JAVA_OPTIONS: -Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8
PreProcessor: <java class 'PreProcessor'>
IndexBuilder: <java class 'IndexBuilder'>
CodeSearcher: <java class 'CodeSearcher'>
10
```

All three Java classes load through JPype, and all ten Lucene index directories are present.

## Step 7: single-class smoke check

The self-contained JUnit 5 and Mockito test compiled and both tests passed through `JavaRunner`:

```text
compile: (True, '')
run: True
['[         2 tests successful      ]', '[         0 tests failed          ]']
report: True
/tmp/jacoco-html/index.html
JaCoCo Coverage Report,org.apache.commons.cli,Option,486,60,65,5,111,17,77,9,44,7
```

The temporary `Option_smoke_Test.java`, `target/test-classes`, and `target/jacoco.exec` were removed. The Step 2 verification returned zero files for every dataset project's `src/test` directory.

## Additional findings and warnings

- The original Docker verification used `bash -lc`; Debian's login profile reset `PATH` and hid `/opt/maven/bin`. The corrected workflow uses `bash -c`, and the image also links `/usr/local/bin/mvn` to `/opt/maven/bin/mvn`.
- With `_JAVA_OPTIONS` set, `java -version 2>&1 | head -1` prints the `Picked up _JAVA_OPTIONS` notice instead of the version. The full Java output above was recorded without `head`.
- Gson sibling modules `gson/proto` and `gson/extras` contain ten test files outside the dataset path. They were left unchanged; workspace-empty checks were scoped to the ten dataset paths.
- The first smoke-test draft used the original JUnit 4 `OptionTest`, which was incompatible with the regenerated JUnit 5 classpath and referenced another test class. The final smoke uses a self-contained generated-style JUnit 5 test.
- Running `/tmp/smoke.py` requires `PYTHONPATH=/work/code` because Python places `/tmp`, rather than the shell working directory, first on the import path for a script file.
- The Docker build emitted pip's standard warning about installing packages as root.
- Maven repeatedly prints the `_JAVA_OPTIONS` notice. JDOM also warned that file encoding was not explicitly configured and that the system module path was not set with `-source 11`. Several projects reported existing deprecation or unchecked-operation warnings.
- The portability sweep intentionally leaves five baseline-only hits:
  - `code/evaluations/baseline_scripts.py:59` — `shell=True`
  - `code/evaluations/baseline_scripts.py:182` — semicolon classpath
  - `code/evaluations/extracrt_baseline_result.py:106` — semicolon classpath
  - `code/evaluations/extracrt_baseline_result.py:112` — `shell=True`
  - `code/evaluations/extracrt_baseline_result.py:147` — semicolon classpath

## Open questions for Gary

1. How should Stage 3 prepare `gson/gson` while keeping `src/test` empty? It needs the template-generated `GsonBuildConfig`, but its full install lifecycle later assumes an original test class exists.
2. Should `gson/gson/dependencies.txt` be regenerated by a separate approved preparation step before Stage 3, given that the current fallback returns before writing it?
3. Should the two baseline evaluation utilities be ported before any later baseline replication, or remain out of scope until that stage?
