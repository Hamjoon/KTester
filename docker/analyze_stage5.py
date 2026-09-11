#!/usr/bin/env python3
"""Generate the detailed Stage 5 gap, failure, and prompt analyses."""

from __future__ import annotations

import csv
import json
from collections import Counter
from pathlib import Path
from statistics import mean

from aggregate_stage4 import percent


REPO = Path(__file__).resolve().parent.parent
RESULTS = REPO / "results" / "stage5"


def load_json(path: Path):
    with path.open(encoding="utf-8") as handle:
        return json.load(handle)


DATASET = load_json(REPO / "data" / "dataset_info.json")


def fmt(value: float) -> str:
    return f"{value:.2f}"


def generate_gap_analysis() -> None:
    with (RESULTS / "comparison_task_table.csv").open(encoding="utf-8") as handle:
        rows = list(csv.DictReader(handle))
    for row in rows:
        for key in list(row):
            if key not in {"project", "id"}:
                row[key] = float(row[key])
        row["gap"] = row["ours_correct_inst_cov"] - row["authors_mean_correct_inst_cov"]
        row["range_position"] = (
            "inside"
            if row["authors_min_correct_inst_cov"]
            <= row["ours_correct_inst_cov"]
            <= row["authors_max_correct_inst_cov"]
            else "below"
            if row["ours_correct_inst_cov"] < row["authors_min_correct_inst_cov"]
            else "above"
        )

    def ranking_table(title: str, selected: list[dict]) -> list[str]:
        lines = [
            f"### {title}",
            "",
            "| Rank | Project | Task | Ours | Authors mean | Authors min | Authors max | Gap | In range? |",
            "| ---: | --- | --- | ---: | ---: | ---: | ---: | ---: | --- |",
        ]
        for rank, row in enumerate(selected, 1):
            lines.append(
                f"| {rank} | {row['project']} | {row['id']} | "
                f"{fmt(row['ours_correct_inst_cov'])} | "
                f"{fmt(row['authors_mean_correct_inst_cov'])} | "
                f"{fmt(row['authors_min_correct_inst_cov'])} | "
                f"{fmt(row['authors_max_correct_inst_cov'])} | "
                f"{row['gap']:+.2f} | {'yes' if row['range_position'] == 'inside' else 'no'} |"
            )
        return lines

    positions = Counter(row["range_position"] for row in rows)
    lines = [
        "# Stage 5 gap analysis",
        "",
        "## Correct instruction coverage range position",
        "",
        f"- Inside the authors' five-repetition range: {positions['inside']} of {len(rows)} tasks.",
        f"- Below the authors' range: {positions['below']} of {len(rows)} tasks.",
        f"- Above the authors' range: {positions['above']} of {len(rows)} tasks.",
        "",
    ]
    lines.extend(ranking_table("Ten worst gaps", sorted(rows, key=lambda row: row["gap"])[:10]))
    lines.append("")
    lines.extend(ranking_table("Ten best gaps", sorted(rows, key=lambda row: row["gap"], reverse=True)[:10]))

    ours = load_json(RESULTS / "aggregate_ours.json")
    author_reps = [load_json(RESULTS / f"aggregate_authors_rep{i}.json") for i in range(1, 6)]
    authors = load_json(RESULTS / "aggregate_authors_mean.json")
    metrics = (
        ("test_case_compile_rate", "TC compile"),
        ("test_case_execution_rate", "TC execution"),
        ("mean_correct_instruction_coverage", "Correct inst"),
        ("mean_correct_branch_coverage", "Correct branch"),
    )
    lines.extend(
        [
            "",
            "## Project-level gaps and repetition spread",
            "",
            "A flag means the absolute gap between ours and the authors' five-repetition mean is larger than the authors' maximum-minus-minimum spread.",
            "",
            "| Project | Metric | Ours | Authors mean | Authors min | Authors max | Gap | Authors spread | Flag |",
            "| --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | --- |",
        ]
    )
    flagged = Counter()
    for project in ours["projects"]:
        for key, label in metrics:
            our_value = ours["projects"][project][key]
            values = [rep["projects"][project][key] for rep in author_reps]
            author_mean = authors["projects"][project][key]
            gap = our_value - author_mean
            spread = max(values) - min(values)
            is_flagged = abs(gap) > spread
            if is_flagged:
                flagged[project] += 1
            lines.append(
                f"| {project} | {label} | {percent(our_value)} | {percent(author_mean)} | "
                f"{percent(min(values))} | {percent(max(values))} | {gap * 100:+.2f} pp | "
                f"{spread * 100:.2f} pp | {'yes' if is_flagged else 'no'} |"
            )
    lines.extend(
        [
            "",
            "### Projects flagged",
            "",
        ]
    )
    for project in ours["projects"]:
        lines.append(f"- {project}: {flagged[project]} of {len(metrics)} metrics flagged.")
    (RESULTS / "gap_analysis.md").write_text("\n".join(lines) + "\n", encoding="utf-8")


FAILURE_CLASSIFICATIONS = {
    ("batch-processing-gateway", "SubmissionSummary_copyFrom"): (
        "H1",
        "import and helper",
        "References a non-existent com.apple.spark.operator.Metadata type.",
    ),
    ("batch-processing-gateway", "ApplicationSubmissionHelper_getExecutorSpec"): (
        "S",
        "import and field",
        "Imports AppConfig.SparkCluster from the wrong package, leaving SparkCluster unresolved.",
    ),
    ("commons-codec", "MurmurHash3_hash128x64Internal"): (
        "S",
        "@Test methods",
        "Uses decimal long literals larger than Long.MAX_VALUE.",
    ),
    ("commons-collections", "SequencesComparator_getMiddleSnake"): (
        "H2",
        "helper and @Test methods",
        "Uses the existing private Snake type directly and also references an undefined helper parameter.",
    ),
    ("commons-collections", "AbstractPatriciaTrie_nextEntryImpl"): (
        "S",
        "helper",
        "Uses the nested TrieEntry type without its AbstractPatriciaTrie qualifier.",
    ),
    ("commons-csv", "CSVParser_createHeaders"): (
        "H1",
        "helper and @Test methods",
        "Treats Headers as a collection and calls non-existent Headers and CSVFormat members.",
    ),
    ("datafaker", "SqlTransformer_handlePrimitivesInArray"): (
        "H1",
        "setup",
        "Uses non-existent DEFAULT enum constants; the same setup also misuses a private constructor.",
    ),
    ("gson", "LinkedTreeMap_find"): (
        "S",
        "helper",
        "Imports org.w3c.dom.Node, so the generic LinkedTreeMap.Node return type resolves incorrectly.",
    ),
    ("gson", "LinkedTreeMap_rebalance"): (
        "S",
        "field and helper",
        "Imports org.w3c.dom.Node, so generic field and helper parameter types resolve incorrectly.",
    ),
    ("jdom2", "XPathHelper_getSingleStep"): (
        "H2",
        "@Test methods",
        "Stubs Parent and Content with incompatible return types and calls methods absent from Parent.",
    ),
    ("jdom2", "StAXStreamBuilder_processPrunableElement"): (
        "H1",
        "@Test methods",
        "Calls a non-existent JDOMFactory.createElement(String) method.",
    ),
    ("jdom2", "AbstractXMLOutputProcessor_printContent"): (
        "H2",
        "helper and @Test methods",
        "Calls protected output methods through a base-typed field; one helper also lacks the Arrays import.",
    ),
    ("jdom2", "WalkerTRIM_analyzeMultiText"): (
        "H2",
        "field and setup",
        "Uses the non-static inner MultiText type without an enclosing instance and calls the wrong FormatStack constructor.",
    ),
    ("ruler", "Ruler_matches"): (
        "H2",
        "@Test method",
        "Calls the existing AnythingButEqualsIgnoreCase constructor with an extra boolean argument.",
    ),
    ("ruler", "ByteMachine_addRangePattern"): (
        "H2",
        "setup and @Test methods",
        "Passes double arrays where the existing Range constructor requires scalar doubles.",
    ),
    ("ruler", "RuleCompiler_processMatchExpression"): (
        "H1",
        "@Test methods",
        "Calls a non-existent Patterns.getPatternValue() member.",
    ),
    ("ruler", "ByteMachine_findMatchPattern"): (
        "H1",
        "helper class",
        "Invents an overridable getValue() method and a non-existent InputCharacterType.CHARACTER constant.",
    ),
    ("windward", "AbstractRouterGroup_matchRouter"): (
        "H1",
        "field",
        "Declares a field with the non-existent type variable M.",
    ),
}


def author_summary(rep: int, project: str) -> dict:
    base = REPO / "evaluation_results" / "RQ1-ktester" / "RQ1" / "KTester-gpt4o" / f"rep_{rep}" / project / "reports"
    nested = base / "gpt4o" / "summary.json"
    return load_json(nested if nested.exists() else base / "summary.json")


def generate_compile_failures() -> None:
    detected = set()
    task_lookup = {}
    for project, info in DATASET.items():
        summary = load_json(REPO / "results" / "stage4" / project / "reports" / "summary.json")
        for task in info["focal-methods"]:
            key = f"{task['class']}#{task['method-name']}"
            task_lookup[(project, task["id"])] = key
            if summary[key].get("error_type") == "compile error":
                detected.add((project, task["id"]))
    if detected != set(FAILURE_CLASSIFICATIONS):
        raise ValueError(
            f"Classification mismatch: missing={detected - set(FAILURE_CLASSIFICATIONS)}, "
            f"extra={set(FAILURE_CLASSIFICATIONS) - detected}"
        )

    category_counts = Counter()
    outside_test_count = 0
    author_failure_total = 0
    table_rows = []
    for project, task_id in sorted(detected):
        category, location, description = FAILURE_CLASSIFICATIONS[(project, task_id)]
        category_counts[category] += 1
        if location != "@Test method" and location != "@Test methods":
            outside_test_count += 1
        key = task_lookup[(project, task_id)]
        author_failures = sum(
            author_summary(rep, project)[key].get("error_type") == "compile error"
            for rep in range(1, 6)
        )
        author_failure_total += author_failures
        prompts = list(
            (REPO / "results" / "stage4" / project / "fix" / task_id).glob("repair_prompt_*.md")
        )
        last_prompt = max(prompts, key=lambda path: int(path.stem.rsplit("_", 1)[1]))
        table_rows.append(
            (project, task_id, category, location, description, last_prompt.name, author_failures)
        )

    lines = [
        "# Stage 5 compile-failure classification",
        "",
        "The category describes the final class and its last compiler failure. Location records whether the remaining error was removable by KTester's rule-based deletion of a failing `@Test` method.",
        "",
        "| Project | Task | Category | Failing location | Final remaining error | Last repair prompt | Authors non-compile reps |",
        "| --- | --- | --- | --- | --- | --- | ---: |",
    ]
    for project, task_id, category, location, description, prompt, author_failures in table_rows:
        lines.append(
            f"| {project} | {task_id} | {category} | {location} | {description} | "
            f"{prompt} | {author_failures}/5 |"
        )
    lines.extend(
        [
            "",
            "## Counts",
            "",
            f"- Total non-compiling final classes: {len(table_rows)}.",
            *[
                f"- {category}: {category_counts[category]}."
                for category in ("H1", "H2", "G", "S", "O")
            ],
            f"- Classes with at least one remaining error outside an `@Test` method: {outside_test_count} of {len(table_rows)}.",
            f"- Authors' non-compiling outcomes for these same task/repetition pairs: {author_failure_total} of {len(table_rows) * 5}.",
            "",
            "All final coverage-time compiler feedback included `_JAVA_OPTIONS=-Duser.language=en -Duser.country=US -Dfile.encoding=UTF-8`. The two batch-processing-gateway failures also emitted annotation-processor source-level warnings, but those warnings were not the compile failures.",
            "",
            "The final coverage compile output differs from some `repair_prompt_2.md` feedback because KTester applies `repair_response_2.md` after creating that prompt. Classifications above use the archived final class and the coverage evaluator's compile result; the last prompt was also inspected as required.",
        ]
    )
    (RESULTS / "compile_failures.md").write_text("\n".join(lines) + "\n", encoding="utf-8")


def normalized_prompt_lines(path: Path) -> list[str]:
    text = path.read_text(encoding="utf-8", errors="replace").replace("\r\n", "\n").replace("\r", "\n")
    return [line.rstrip() for line in text.split("\n") if line.strip()]


def generate_prompt_drift() -> None:
    authors_root = REPO / "evaluation_results" / "RQ1-ktester" / "RQ1" / "KTester-gpt4o" / "rep_1"
    ours_root = REPO / "results" / "stage4"
    projects = ("windward", "commons-csv", "gson")
    prompt_names = (
        "condition4case_prompt.md",
        "io4case_prompt.md",
        "exception4case_prompt.md",
        "gencode_prompt.md",
    )
    records = []
    timeout_records = []
    for project in projects:
        author_project = authors_root / project / "context+prompts"
        for task_dir in sorted(path for path in author_project.iterdir() if path.is_dir()):
            ours_task = ours_root / project / "context+prompts" / task_dir.name
            for prompt_name in prompt_names:
                author_lines = normalized_prompt_lines(task_dir / prompt_name)
                our_lines = normalized_prompt_lines(ours_task / prompt_name)
                if author_lines == our_lines:
                    status = "identical"
                elif Counter(author_lines) == Counter(our_lines):
                    status = "order-only differences"
                else:
                    status = "substantive differences"
                records.append((project, task_dir.name, prompt_name, status))
            timeout_records.append(
                (
                    project,
                    task_dir.name,
                    "@Timeout" in (task_dir / "init_prompt.md").read_text(errors="replace"),
                    "@Timeout" in (ours_task / "init_prompt.md").read_text(errors="replace"),
                )
            )

    counts = Counter(record[3] for record in records)
    lines = [
        "# Stage 5 prompt drift",
        "",
        "Line endings were normalized and blank lines were removed before comparison. Files with the same multiset of normalized lines were classified as order-only differences.",
        "",
        "## Summary",
        "",
        f"- Projects: {len(projects)}.",
        f"- Tasks: {len(timeout_records)}.",
        f"- Prompt files compared: {len(records)}.",
        f"- Identical: {counts['identical']}.",
        f"- Order-only differences: {counts['order-only differences']}.",
        f"- Substantive differences: {counts['substantive differences']}.",
        f"- Authors' init prompts containing `@Timeout`: {sum(x[2] for x in timeout_records)} of {len(timeout_records)}.",
        f"- Our init prompts containing `@Timeout`: {sum(x[3] for x in timeout_records)} of {len(timeout_records)}.",
        "",
        "All conditional, I/O, and exception prompts were identical. Every order-only difference was in a generation prompt and preserved the exact normalized line multiset, consistent with ordering changes inside dependency/member lists. There were no substantive differences to quote.",
        "",
        "## Per-file results",
        "",
        "| Project | Task | File | Result |",
        "| --- | --- | --- | --- |",
    ]
    for project, task, prompt_name, status in records:
        lines.append(f"| {project} | {task} | {prompt_name} | {status} |")
    lines.extend(
        [
            "",
            "## Init-prompt timeout annotation",
            "",
            "| Project | Task | Authors `@Timeout` | Ours `@Timeout` |",
            "| --- | --- | --- | --- |",
        ]
    )
    for project, task, author_timeout, our_timeout in timeout_records:
        lines.append(
            f"| {project} | {task} | {'yes' if author_timeout else 'no'} | {'yes' if our_timeout else 'no'} |"
        )
    (RESULTS / "prompt_drift.md").write_text("\n".join(lines) + "\n", encoding="utf-8")


def generate_environment_notes() -> None:
    final_feedback = []
    for project, task_id in FAILURE_CLASSIFICATIONS:
        log_path = REPO / "results" / "stage4" / project / f"stage4-{project}-coverage.log"
        lines = log_path.read_text(errors="replace").splitlines()
        filename = f"{task_id}_Test.java"
        starts = [i for i, line in enumerate(lines) if filename in line and "javac -cp" in line]
        start = starts[-1]
        ends = [i for i in range(start + 1, len(lines)) if " - INFO - javac -cp " in lines[i]]
        final_feedback.append("\n".join(lines[start : ends[0] if ends else len(lines)]))

    author_fix = REPO / "evaluation_results" / "RQ1-ktester" / "RQ1" / "KTester-gpt4o"
    author_java_option_reps = 0
    author_source_warning_reps = 0
    author_fifth_repair_tasks = 0
    for rep in range(1, 6):
        texts = []
        for path in (author_fix / f"rep_{rep}").glob("*/fix/**/repair_prompt_*.md"):
            texts.append(path.read_text(errors="replace"))
        joined = "\n".join(texts)
        author_java_option_reps += "Picked up _JAVA_OPTIONS" in joined
        author_source_warning_reps += "less than -source '17'" in joined
        author_fifth_repair_tasks += len(list((author_fix / f"rep_{rep}").glob("*/fix/**/repair_prompt_4.md")))

    our_third_repair_tasks = len(list((REPO / "results" / "stage4").glob("*/fix/*/repair_prompt_2.md")))
    our_fifth_repair_tasks = len(list((REPO / "results" / "stage4").glob("*/fix/*/repair_prompt_4.md")))

    junit_missing = sum(
        "package org.junit" in feedback and "does not exist" in feedback for feedback in final_feedback
    )
    mockito_missing = sum(
        "package org.mockito" in feedback and "does not exist" in feedback for feedback in final_feedback
    )
    our_source_warnings = sum("less than -source '17'" in feedback for feedback in final_feedback)
    lines = [
        "# Stage 5 environment-effect check",
        "",
        f"- Our final compiler feedback includes `_JAVA_OPTIONS` in {sum('Picked up _JAVA_OPTIONS' in x for x in final_feedback)} of {len(final_feedback)} non-compiling tasks.",
        f"- Authors' repair prompts include the same `_JAVA_OPTIONS` text in {author_java_option_reps} of 5 repetitions.",
        f"- Our final failures caused by a missing JUnit package: {junit_missing} of {len(final_feedback)}.",
        f"- Our final failures caused by a missing Mockito package: {mockito_missing} of {len(final_feedback)}.",
        f"- Our final failures carrying the Java 17 annotation-processor source warning: {our_source_warnings} of {len(final_feedback)}.",
        f"- Authors' repair prompts carrying the same Java 17 source warning: {author_source_warning_reps} of 5 repetitions.",
        f"- Our tasks reaching a third repair prompt (`repair_prompt_2.md`): {our_third_repair_tasks}; tasks reaching a fifth repair prompt: {our_fifth_repair_tasks}.",
        f"- Authors' task/repetition pairs reaching a fifth repair prompt (`repair_prompt_4.md`): {author_fifth_repair_tasks}.",
        "- The final 18 compile failures point to generated source errors involving project-local types, members, imports, visibility, constructors, or literals. The extracted archive does not provide evidence that a dependency available to the authors was missing from our classpath.",
        "- The repair-budget difference is an experiment-configuration effect that can improve the authors' final compile rate independently of the initial model response.",
        "- Runtime is environment-sensitive: the archived author timing record and our timing record came from different runs and potentially different hardware, API latency, and concurrency conditions, so their time difference cannot be assigned solely to the model.",
        "- The authors' `@Timeout` annotation and our omission are prompt/tool-output differences. They could change handling of long-running tests, but they do not explain the 18 compilation failures.",
    ]
    (RESULTS / "environment_effects.md").write_text("\n".join(lines) + "\n", encoding="utf-8")


def main() -> None:
    generate_gap_analysis()
    generate_compile_failures()
    generate_prompt_drift()
    generate_environment_notes()


if __name__ == "__main__":
    main()
