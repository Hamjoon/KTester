#!/usr/bin/env python3
"""Compare Stage 4 with the five published KTester repetitions."""

from __future__ import annotations

import csv
import json
from pathlib import Path
from statistics import mean

from aggregate_stage4 import METRICS, load_json, percent, task_key


REPO = Path(__file__).resolve().parent.parent
DATASET = load_json(REPO / "data" / "dataset_info.json")
OURS_ROOT = REPO / "results" / "stage4"
AUTHORS_ROOT = REPO / "evaluation_results" / "RQ1-ktester" / "RQ1" / "KTester-gpt4o"
AUTHORS_TIME = (
    REPO
    / "evaluation_results"
    / "RQ1-ktester"
    / "RQ1"
    / "time_records"
    / "time_record_KTester.json"
)
OUTPUT = REPO / "results" / "stage5"
FIELDS = (
    "test_cases",
    "passed_cases",
    "inst_cov",
    "bran_cov",
    "correct_inst_cov",
    "correct_bran_cov",
)


def summary_path(root: Path, project: str) -> Path:
    candidates = (
        root / project / "reports" / "gpt4o" / "summary.json",
        root / project / "reports" / "summary.json",
    )
    for path in candidates:
        if path.exists():
            return path
    raise FileNotFoundError(f"No summary for {project} under {root}")


def test_class_dir(root: Path, project: str) -> Path:
    nested = root / project / "test_classes" / "gpt4o"
    return nested if nested.exists() else root / project / "test_classes"


def item_value(item: dict, field: str) -> float:
    value = item.get(field, 0)
    return float(value) if isinstance(value, (int, float)) else 0.0


def aggregate_items(items: list[dict], class_count: int) -> dict:
    """Use the same case and coverage rules as aggregate_stage4.py."""
    tasks = len(items)
    test_cases = sum(item["test_cases"] for item in items)
    passed_cases = sum(item["passed_cases"] for item in items)
    compiled_cases = sum(
        item["test_cases"]
        for item in items
        if item.get("error_type") != "compile error"
    )
    compiled_tasks = sum(item.get("error_type") != "compile error" for item in items)
    all_pass_tasks = sum(
        item["test_cases"] > 0 and item["passed_cases"] == item["test_cases"]
        for item in items
    )
    any_pass_tasks = sum(item["passed_cases"] > 0 for item in items)
    return {
        "tasks": tasks,
        "test_classes": class_count,
        "test_cases": test_cases,
        "passed_cases": passed_cases,
        "compiled_cases": compiled_cases,
        "test_case_compile_rate": compiled_cases / test_cases if test_cases else 0.0,
        "test_case_execution_rate": passed_cases / test_cases if test_cases else 0.0,
        "task_compile_count": compiled_tasks,
        "task_compile_rate": compiled_tasks / tasks if tasks else 0.0,
        "task_all_pass_count": all_pass_tasks,
        "task_all_pass_rate": all_pass_tasks / tasks if tasks else 0.0,
        "task_any_pass_count": any_pass_tasks,
        "task_any_pass_rate": any_pass_tasks / tasks if tasks else 0.0,
        "mean_instruction_coverage": sum(item_value(x, "inst_cov") for x in items) / tasks,
        "mean_branch_coverage": sum(item_value(x, "bran_cov") for x in items) / tasks,
        "mean_correct_instruction_coverage": sum(
            item_value(x, "correct_inst_cov") for x in items
        )
        / tasks,
        "mean_correct_branch_coverage": sum(
            item_value(x, "correct_bran_cov") for x in items
        )
        / tasks,
        "mean_test_cases_per_class": test_cases / class_count if class_count else 0.0,
    }


def load_run(root: Path, authors: bool) -> tuple[dict, dict]:
    projects = {}
    task_rows = {}
    all_items = []
    class_total = 0
    for project, info in DATASET.items():
        path = summary_path(root, project) if authors else root / project / "reports" / "summary.json"
        summary = load_json(path)
        items_by_key = {key: value for key, value in summary.items() if key not in METRICS}
        expected = {task_key(task) for task in info["focal-methods"]}
        if set(items_by_key) != expected:
            raise ValueError(
                f"{path}: missing={sorted(expected - set(items_by_key))}, "
                f"extra={sorted(set(items_by_key) - expected)}"
            )
        if authors:
            class_dir = test_class_dir(root, project)
        else:
            class_dir = root / project / "test_classes"
        class_ids = {p.name.removesuffix("_Test.java") for p in class_dir.glob("*_Test.java")}
        expected_ids = {task["id"] for task in info["focal-methods"]}
        class_count = len(class_ids & expected_ids)
        items = []
        for task in info["focal-methods"]:
            item = items_by_key[task_key(task)]
            items.append(item)
            task_rows[task["id"]] = {
                "project": project,
                **{field: item_value(item, field) for field in FIELDS},
                "compiles": item.get("error_type") != "compile error",
                "error_type": item.get("error_type"),
            }
        projects[project] = aggregate_items(items, class_count)
        all_items.extend(items)
        class_total += class_count
    return {"projects": projects, "total": aggregate_items(all_items, class_total)}, task_rows


def mean_aggregates(reps: list[dict]) -> dict:
    result = {"projects": {}}
    for project in DATASET:
        result["projects"][project] = {
            key: mean(rep["projects"][project][key] for rep in reps)
            for key in reps[0]["projects"][project]
        }
    result["total"] = {
        key: mean(rep["total"][key] for rep in reps) for key in reps[0]["total"]
    }
    return result


def add_times(aggregate: dict, path: Path) -> None:
    details = load_json(path)["details"]
    all_times = []
    for project, info in DATASET.items():
        times = [sum(details[task["id"]].values()) for task in info["focal-methods"]]
        aggregate["projects"][project]["mean_time_per_task_seconds"] = mean(times)
        all_times.extend(times)
    aggregate["total"]["mean_time_per_task_seconds"] = mean(all_times)


def write_inventory(author_reps: list[dict]) -> None:
    inventory = {"archive_layout_note": [], "repetitions": {}, "time_records": {}}
    inventory["archive_layout_note"] = [
        "rep_1 batch-processing-gateway uses reports/, test_classes/, and fix/ directly",
        "rep_1 other projects use a gpt4o/ level under reports, test_classes, and fix",
        "rep_2 through rep_5 use the shallower directories without gpt4o/",
    ]
    for rep in range(1, 6):
        rep_root = AUTHORS_ROOT / f"rep_{rep}"
        projects = {}
        for project, info in DATASET.items():
            path = summary_path(rep_root, project)
            summary = load_json(path)
            keys = set(summary) - METRICS
            expected = {task_key(task) for task in info["focal-methods"]}
            class_dir = test_class_dir(rep_root, project)
            fix_root = rep_root / project / "fix"
            if (fix_root / "gpt4o").exists():
                fix_root = fix_root / "gpt4o"
            projects[project] = {
                "summary_path": str(path.relative_to(REPO)),
                "summary_exists": True,
                "task_count": len(keys),
                "missing_task_keys": sorted(expected - keys),
                "extra_task_keys": sorted(keys - expected),
                "test_class_count": len(list(class_dir.glob("*_Test.java"))),
                "fix_file_count": sum(path.is_file() for path in fix_root.glob("**/*")) if fix_root.exists() else 0,
            }
        inventory["repetitions"][f"rep_{rep}"] = projects
    time_data = load_json(AUTHORS_TIME)
    inventory["time_records"] = {
        "path": str(AUTHORS_TIME.relative_to(REPO)),
        "detail_count": len(time_data["details"]),
        "statistics": time_data["statistics"],
    }
    (OUTPUT / "extraction_inventory.json").write_text(
        json.dumps(inventory, indent=2) + "\n", encoding="utf-8"
    )
    lines = [
        "# Stage 5 extraction inventory",
        "",
        "| Repetition | Project | Summary | Tasks | Missing | Extra | Test classes | Fix files |",
        "| --- | --- | --- | ---: | ---: | ---: | ---: | ---: |",
    ]
    for rep, projects in inventory["repetitions"].items():
        for project, value in projects.items():
            lines.append(
                f"| {rep} | {project} | {'yes' if value['summary_exists'] else 'no'} | "
                f"{value['task_count']} | {len(value['missing_task_keys'])} | "
                f"{len(value['extra_task_keys'])} | {value['test_class_count']} | "
                f"{value['fix_file_count']} |"
            )
    time = inventory["time_records"]
    lines.extend(
        [
            "",
            "## Layout and timing",
            "",
            *[f"- {note}." for note in inventory["archive_layout_note"]],
            f"- KTester time-record files found: 1.",
            f"- KTester time-record task details: {time['detail_count']}.",
            f"- Published mean time per task: {time['statistics']['average_total']:.2f} seconds.",
        ]
    )
    (OUTPUT / "extraction_inventory.md").write_text("\n".join(lines) + "\n", encoding="utf-8")


def write_task_table(ours_tasks: dict, author_tasks: list[dict]) -> None:
    columns = ["project", "id"]
    for field in FIELDS:
        columns.extend(
            [
                f"ours_{field}",
                f"authors_mean_{field}",
                f"authors_min_{field}",
                f"authors_max_{field}",
            ]
        )
    with (OUTPUT / "comparison_task_table.csv").open("w", newline="", encoding="utf-8") as handle:
        writer = csv.DictWriter(handle, fieldnames=columns)
        writer.writeheader()
        for project, info in DATASET.items():
            for task in info["focal-methods"]:
                task_id = task["id"]
                row = {"project": project, "id": task_id}
                for field in FIELDS:
                    values = [rep[task_id][field] for rep in author_tasks]
                    row[f"ours_{field}"] = ours_tasks[task_id][field]
                    row[f"authors_mean_{field}"] = mean(values)
                    row[f"authors_min_{field}"] = min(values)
                    row[f"authors_max_{field}"] = max(values)
                writer.writerow(row)


def format_project_table(ours: dict, authors: dict) -> str:
    headers = (
        "Project",
        "Side",
        "Tasks",
        "TC compile",
        "TC execution",
        "Task compile",
        "Task all pass",
        "Task any pass",
        "Mean inst cov",
        "Mean branch cov",
        "Mean correct inst cov",
        "Mean correct branch cov",
        "Tests/class",
    )
    lines = [
        "| " + " | ".join(headers) + " |",
        "| " + " | ".join(["---", "---"] + ["---:"] * (len(headers) - 2)) + " |",
    ]
    for project in [*DATASET, "Total"]:
        key = "total" if project == "Total" else "projects"
        for label, data in (("Ours", ours), ("Authors mean", authors)):
            value = data[key] if key == "total" else data[key][project]
            lines.append(
                "| "
                + " | ".join(
                    [
                        project,
                        label,
                        f"{value['tasks']:.0f}",
                        percent(value["test_case_compile_rate"]),
                        percent(value["test_case_execution_rate"]),
                        percent(value["task_compile_rate"]),
                        percent(value["task_all_pass_rate"]),
                        percent(value["task_any_pass_rate"]),
                        percent(value["mean_instruction_coverage"]),
                        percent(value["mean_branch_coverage"]),
                        percent(value["mean_correct_instruction_coverage"]),
                        percent(value["mean_correct_branch_coverage"]),
                        f"{value['mean_test_cases_per_class']:.2f}",
                    ]
                )
                + " |"
            )

    paper_epr = 0.7641
    total = authors["total"]
    variants = {
        "test-case execution": total["test_case_execution_rate"],
        "task all-pass execution": total["task_all_pass_rate"],
        "task any-pass execution": total["task_any_pass_rate"],
    }
    closest = min(variants, key=lambda name: abs(variants[name] - paper_epr))
    lines.extend(
        [
            "",
            "### Definition comparison with paper Table 2",
            "",
            f"- Paper: CPR 100.00%, EPR 76.41%, LC 63.94%, BC 55.46%, tests/class 7.33.",
            f"- Authors' summaries, test-case definition: CPR {percent(total['test_case_compile_rate'])}, EPR {percent(total['test_case_execution_rate'])}.",
            f"- Authors' summaries, task definition with all tests passing: CPR {percent(total['task_compile_rate'])}, EPR {percent(total['task_all_pass_rate'])}.",
            f"- Authors' summaries, task definition with at least one test passing: CPR {percent(total['task_compile_rate'])}, EPR {percent(total['task_any_pass_rate'])}.",
            f"- Authors' summary coverage fields: instruction {percent(total['mean_instruction_coverage'])}, branch {percent(total['mean_branch_coverage'])}, correct instruction {percent(total['mean_correct_instruction_coverage'])}, correct branch {percent(total['mean_correct_branch_coverage'])}, tests/class {total['mean_test_cases_per_class']:.2f}.",
            f"- Closest EPR definition: {closest} at {percent(variants[closest])}; its absolute gap from 76.41% is {abs(variants[closest] - paper_epr) * 100:.2f} percentage points.",
        ]
    )
    return "\n".join(lines) + "\n"


def main() -> None:
    OUTPUT.mkdir(parents=True, exist_ok=True)
    ours, ours_tasks = load_run(OURS_ROOT, authors=False)
    add_times(ours, OURS_ROOT / "time" / "time_records.json")
    author_reps = []
    author_tasks = []
    for rep in range(1, 6):
        aggregate, tasks = load_run(AUTHORS_ROOT / f"rep_{rep}", authors=True)
        author_reps.append(aggregate)
        author_tasks.append(tasks)
        (OUTPUT / f"aggregate_authors_rep{rep}.json").write_text(
            json.dumps(aggregate, indent=2) + "\n", encoding="utf-8"
        )
    authors_mean = mean_aggregates(author_reps)
    add_times(authors_mean, AUTHORS_TIME)
    (OUTPUT / "aggregate_ours.json").write_text(
        json.dumps(ours, indent=2) + "\n", encoding="utf-8"
    )
    (OUTPUT / "aggregate_authors_mean.json").write_text(
        json.dumps(authors_mean, indent=2) + "\n", encoding="utf-8"
    )
    write_inventory(author_reps)
    write_task_table(ours_tasks, author_tasks)
    (OUTPUT / "comparison_project_table.md").write_text(
        format_project_table(ours, authors_mean), encoding="utf-8"
    )


if __name__ == "__main__":
    main()
