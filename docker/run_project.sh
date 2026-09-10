#!/usr/bin/env bash
# Runs the full KTester pipeline for ONE dataset project and archives the result.
# Usage (inside the container): bash /work/docker/run_project.sh <project-name>
set -uo pipefail
p="$1"
cd /work/code
# settings.py is gitignored and ours, so editing PROJECTS in place is acceptable here
sed -i "s/^    PROJECTS = .*/    PROJECTS = ['$p']/" settings.py
grep -n "^    PROJECTS" settings.py
start=$(date -u +%FT%TZ)
python generate_unit_test.py -F /work/evaluation/logs/stage4-$p-generate.log
gen_rc=$?
python evaluation.py --operation coverage -F /work/evaluation/logs/stage4-$p-coverage.log
cov_rc=$?
end=$(date -u +%FT%TZ)
mkdir -p /work/results/stage4/$p
for d in context+prompts responses fix test_classes reports; do
  [ -d /work/evaluation/$p/$d ] && cp -r /work/evaluation/$p/$d /work/results/stage4/$p/
done
cp /work/evaluation/logs/stage4-$p-*.log /work/results/stage4/$p/
echo "{\"project\":\"$p\",\"start\":\"$start\",\"end\":\"$end\",\"generate_rc\":$gen_rc,\"coverage_rc\":$cov_rc}" > /work/results/stage4/$p/run.json
echo "DONE $p generate_rc=$gen_rc coverage_rc=$cov_rc $start -> $end"
