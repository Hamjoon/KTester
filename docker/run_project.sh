#!/usr/bin/env bash
# Runs the full KTester pipeline for ONE dataset project and archives the result.
# Usage (inside the container): bash /work/docker/run_project.sh <project-name> [results-stage]
set -uo pipefail
p="$1"
stage="${2:-stage4}"
log_prefix="$stage-$p"
cd /work/code
# settings.py is gitignored and ours, so editing PROJECTS in place is acceptable here
sed -i "s/^    PROJECTS = .*/    PROJECTS = ['$p']/" settings.py
grep -n "^    PROJECTS" settings.py
start=$(date -u +%FT%TZ)
python generate_unit_test.py -F /work/evaluation/logs/$log_prefix-generate.log
gen_rc=$?
python evaluation.py --operation coverage -F /work/evaluation/logs/$log_prefix-coverage.log
cov_rc=$?
end=$(date -u +%FT%TZ)
mkdir -p /work/results/$stage/$p
for d in context+prompts responses fix test_classes reports; do
  [ -d /work/evaluation/$p/$d ] && cp -r /work/evaluation/$p/$d /work/results/$stage/$p/
done
cp /work/evaluation/logs/$log_prefix-*.log /work/results/$stage/$p/
echo "{\"project\":\"$p\",\"start\":\"$start\",\"end\":\"$end\",\"generate_rc\":$gen_rc,\"coverage_rc\":$cov_rc}" > /work/results/$stage/$p/run.json
echo "DONE $p generate_rc=$gen_rc coverage_rc=$cov_rc $start -> $end"
