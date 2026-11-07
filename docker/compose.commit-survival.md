# Commit-survival container mount

From this directory:

```sh
docker compose -f compose.yml -f compose.commit-survival.yml run --rm ktester bash -c 'ls /repo/experiments/ktester-commit-survival && mvn -v'
```

The override adds only the commons-codec bind mount at `/repo`. The base `/work` bind, `ktester-m2:/root/.m2` cache, environment, and service settings remain unchanged. The repository path is the local path fixed by the experiment instructions.

Verification exited 0: the experiment directory was listed; Maven 3.9.9 and Java 17.0.20.1 were reported. Full command, merged-config comparison, and output are recorded in commons-codec under `experiments/ktester-commit-survival/results/container-wiring.json` and `container-wiring.txt`.
