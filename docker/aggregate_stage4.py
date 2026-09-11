#!/usr/bin/env python3
"""Build the Stage 4 aggregate report from archived KTester results."""

from __future__ import annotations

import json
import sys
from pathlib import Path


REPO = Path(__file__).resolve().parent.parent
DATASET_PATH = REPO / "data" / "dataset_info.json"
METRICS = {
    "compile_pass_rate",
    "execution_pass_rate",
    "average_instruction_coverage",
    "average_branch_coverage",
    "average_correct_instruction_coverage",
    "average_correct_branch_coverage",
}


def load_json(path: Path):
    with path.open(encoding="utf-8") as handle:
        return json.load(handle)


def percent(value: float) -> str:
    return f"{value * 100:.2f}%"


def task_key(task: dict) -> str:
    return f"{task['class']}#{task['method-name']}"


def main() -> None:
    stage = sys.argv[1] if len(sys.argv) > 1 else "stage4"
    if len(sys.argv) > 2:
        raise SystemExit(f"Usage: {Path(sys.argv[0]).name} [results-stage]")
    results = REPO / "results" / stage
    time_path = results / "time" / "time_records.json"
    dataset = load_json(DATASET_PATH)
    time_records = load_json(time_path)["details"]
    rows = []
    all_items = []
    all_times = []
    total_classes = 0
    missing_by_project = {}
    compile_failures_by_project = {}

    for project, info in dataset.items():
        tasks = info["focal-methods"]
        summary = load_json(results / project / "reports" / "summary.json")
        items = {key: value for key, value in summary.items() if key not in METRICS}
        expected_keys = {task_key(task) for task in tasks}
        if set(items) != expected_keys:
            missing = sorted(expected_keys - set(items))
            extra = sorted(set(items) - expected_keys)
            raise ValueError(f"{project}: summary mismatch; missing={missing}, extra={extra}")

        test_dir = results / project / "test_classes"
        class_ids = {
            path.name.removesuffix("_Test.java")
            for path in test_dir.glob("*_Test.java")
        }
        task_ids = {task["id"] for task in tasks}
        missing_classes = sorted(task_ids - class_ids)
        compile_failures = [
            task["id"]
            for task in tasks
            if items[task_key(task)].get("error_type") == "compile error"
        ]

        project_times = []
        for task in tasks:
            record = time_records.get(task["id"])
            if record is None:
                raise ValueError(f"{project}: no time record for {task['id']}")
            project_times.append(sum(record.values()))

        total_cases = sum(item["test_cases"] for item in items.values())
        passed_cases = sum(item["passed_cases"] for item in items.values())
        compiled_cases = sum(
            item["test_cases"]
            for item in items.values()
            if item.get("error_type") != "compile error"
        )
        rows.append(
            (
                project,
                len(tasks),
                len(class_ids & task_ids),
                compiled_cases,
                passed_cases,
                total_cases,
                summary,
                sum(project_times) / len(project_times),
            )
        )
        all_items.extend(items.values())
        all_times.extend(project_times)
        total_classes += len(class_ids & task_ids)
        missing_by_project[project] = missing_classes
        compile_failures_by_project[project] = compile_failures

    total_cases = sum(item["test_cases"] for item in all_items)
    total_passed = sum(item["passed_cases"] for item in all_items)
    total_compiled = sum(
        item["test_cases"]
        for item in all_items
        if item.get("error_type") != "compile error"
    )

    headers = (
        "Project",
        "Tasks",
        "Test classes",
        "Compile pass",
        "Execution pass",
        "Mean instruction coverage",
        "Mean branch coverage",
        "Mean correct instruction coverage",
        "Mean correct branch coverage",
        "Mean time/task (s)",
    )
    print("| " + " | ".join(headers) + " |")
    print("| " + " | ".join(["---"] + ["---:"] * (len(headers) - 1)) + " |")
    for project, tasks, classes, compiled, passed, cases, summary, mean_time in rows:
        print(
            "| "
            + " | ".join(
                [
                    project,
                    str(tasks),
                    str(classes),
                    f"{compiled}/{cases} ({percent(summary['compile_pass_rate'])})",
                    f"{passed}/{cases} ({percent(summary['execution_pass_rate'])})",
                    percent(summary["average_instruction_coverage"]),
                    percent(summary["average_branch_coverage"]),
                    percent(summary["average_correct_instruction_coverage"]),
                    percent(summary["average_correct_branch_coverage"]),
                    f"{mean_time:.2f}",
                ]
            )
            + " |"
        )

    task_total = len(all_items)
    coverage_fields = (
        "inst_cov",
        "bran_cov",
        "correct_inst_cov",
        "correct_bran_cov",
    )
    total_coverage = []
    for field in coverage_fields:
        value = sum(
            item.get(field, 0) if isinstance(item.get(field, 0), (int, float)) else 0
            for item in all_items
        ) / task_total
        total_coverage.append(percent(value))
    print(
        "| **Total** | "
        + " | ".join(
            [
                str(task_total),
                str(total_classes),
                f"{total_compiled}/{total_cases} ({percent(total_compiled / total_cases)})",
                f"{total_passed}/{total_cases} ({percent(total_passed / total_cases)})",
                *total_coverage,
                f"{sum(all_times) / len(all_times):.2f}",
            ]
        )
        + " |"
    )

    print("\n### Missing test classes")
    for project in dataset:
        missing = missing_by_project[project]
        print(f"- {project}: {', '.join(missing) if missing else 'none'}")

    print("\n### Final classes that did not compile")
    for project in dataset:
        failures = compile_failures_by_project[project]
        print(f"- {project}: {', '.join(failures) if failures else 'none'}")


if __name__ == "__main__":
    main()
