#!/usr/bin/env bash
set -uo pipefail
for p in commons-cli windward commons-csv datafaker batch-processing-gateway commons-collections ruler commons-codec gson jdom2; do
  echo "=== START $p $(date -u +%FT%TZ)"
  bash /work/docker/run_project.sh "$p"
done
echo "=== ALL DONE $(date -u +%FT%TZ)"
