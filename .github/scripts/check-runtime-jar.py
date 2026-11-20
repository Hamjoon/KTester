"""Check the runtime jar from the repository root after compiling into a clean directory."""

from pathlib import Path
import re
import subprocess
import sys
from zipfile import BadZipFile, ZipFile

SRC = "code/Java/project-info-process/src"
JAR = "code/Java/project-info-process.jar"
# Keep these roots even if a package loses its last source file.
OWN_ROOTS = {"codegraph", "editcode", "extractor", "infostructure", "temp"}


def git(*args):
    return subprocess.check_output(["git", *args])


def jar_blob(rev):
    return git("ls-tree", "--format=%(objectname)", rev, "--", JAR)


def check(base, classes):
    errors = []
    changed = git("diff", "--no-renames", "--name-only", "-z", base, "HEAD", "--", SRC)
    if changed and jar_blob(base) == jar_blob("HEAD"):
        paths = changed.rstrip(b"\0").decode(errors="backslashreplace").split("\0")
        errors.append(f"Rule (a): {JAR} is unchanged despite source changes: {', '.join(paths)}")

    expected = {p.relative_to(classes).as_posix() for p in classes.rglob("*.class")}
    packages = {name.rpartition("/")[0] for name in expected}
    actual = set()
    try:
        with ZipFile(JAR) as jar:
            for entry in jar.infolist():
                # A multi-release variant has the same class name as its base entry.
                name = re.sub(r"^META-INF/versions/[0-9]+/", "", entry.filename)
                if not name.endswith(".class") or name == "module-info.class":
                    continue
                package = name.rpartition("/")[0]
                if package and package.split("/")[0] not in OWN_ROOTS and package not in packages:
                    continue

                actual.add(name)
                with jar.open(entry) as stream:
                    header = stream.read(8)
                location = f"{JAR}!/{entry.filename}"
                if len(header) != 8 or header[:4] != b"\xca\xfe\xba\xbe":
                    errors.append(f"Rule (b): invalid class header in {location}")
                elif (major := int.from_bytes(header[6:8], "big")) > 61:
                    errors.append(f"Rule (b): {location} has class-file major {major}, maximum 61")
    except (OSError, BadZipFile) as exc:
        errors.append(f"Rules (b/c): cannot inspect {JAR}: {exc}")
        return errors

    for name in sorted(expected - actual):
        errors.append(f"Rule (c): {classes / name} was compiled but is missing from {JAR}")
    for name in sorted(actual - expected):
        errors.append(f"Rule (c): {JAR}!/{name} has no matching class in {classes}")
    if not errors:
        print(f"Jar guard passed: {len(actual)} tool class names match the build.")
    return errors


if __name__ == "__main__":
    problems = check(sys.argv[1], Path(sys.argv[2]))
    if problems:
        sys.exit("\n".join(problems))
