#!/usr/bin/env bash
# Empties src/test in every dataset project and removes stale build output.
set -euo pipefail
cd /work/data/projects
for p in batch-processing-gateway commons-cli commons-codec commons-collections commons-csv datafaker gson/gson jdom2 ruler windward; do
  [ -d "$p/src/test-original" ] || { echo "missing $p/src/test-original"; exit 1; }
  rm -rf "$p/src/test" "$p/target"
  mkdir -p "$p/src/test/java"
  echo "reset $p"
done
