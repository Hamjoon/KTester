#!/usr/bin/env bash
# gson/gson only: run the generate-sources phase the authors' compile goal skips,
# then write dependencies.txt the same way workspace_preparation.py does.
set -euo pipefail
cd /work/data/projects/gson/gson
mvn -q generate-sources compiler:compile
python - <<'PY'
import os
libs = sorted(os.listdir("libs"))
open("dependencies.txt", "w").write(os.pathsep.join(["target/classes"] + [f"libs/{l}" for l in libs]))
print("dependencies.txt entries:", 1 + len(libs))
PY
