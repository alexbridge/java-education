# Agent Guidelines for `java-28`

Instructions for AI agents implementing code and build recipes in this directory.

## Project Scope
Training and practice ground for modern Java features (Java 25 through 28).

---

## Code Style & Conventions

- **Prefer `var`**: Use `var` for local variable declarations wherever possible to minimize visual verbosity.
- **Source Layout**: Place source files in `src/java/edu/java28/<FeatureName>.java` with package `edu.java28;`.
- **Class Javadoc**: Every class must include class-level Javadoc stating:
  - Feature name and status (`[final]` or `[preview]`).
  - Bulleted summary of key mechanisms and concepts demonstrated.
- **Runnable Main**: Every class must contain a self-contained `public static void main(String[] args)` method demonstrating working, illustrative examples.
- **Modern Idioms**: Use current Java language constructs (records, switch expressions, pattern matching, text blocks) instead of legacy equivalents.

---

## Makefile Rules

- **Recipe Per Class**: Every new class must have a matching `run-<feature-kebab-case>` recipe in `Makefile`.
- **Tab Indentation**: Always use standard TAB characters (never spaces) for recipe execution lines.
- **Output Directory**: Compile targets to `out/`.
- **Preview Flags**: Include `--enable-preview` and appropriate `--source` flags if compiling preview or incubator APIs.
- **Phony & All Targets**: Always register new recipes in `.PHONY` and append them to `run-all`.

---

## Documentation Synchronization

- Whenever adding or renaming a class, update `README.md` with:
  - Feature name, status (`[final]` / `[preview]`).
  - Clickable link to source file.
  - One-line terse description.
