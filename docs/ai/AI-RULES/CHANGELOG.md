# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

## Unreleased

## [v4.0.0] - 2026-02-07
- Breaking: renamed downstream guidance from
  `AI-RULES/CONSUMING_PROJECT.md` to `AI-RULES/DOWNSTREAM-PROJECT.md` and
  updated references.
- Standardized terminology to `downstream-project` / `downstream-projects`
  across guidance.
- Added VCS workflow guidance for branch-per-concern, pushing working
  intermediate states, non-working-code exception handling, and PR/MR
  suggestion timing.
- Added execution guidance links from `AI-RULES/DOWNSTREAM-PROJECT.md` to
  `PLAN/PLAN.md`, `PROGRAMMING/PROGRAMMING.md`, and `REVIEW/REVIEW.md`.
- Added PR/MR and issue-tracker summary rules with template snippets in
  `CORE/VERSION_CONTROL_SYSTEM.md`, plus a delivery pointer in
  `PROGRAMMING/PROGRAMMING.md`.
- Added dependency lock-file policy in `CORE/VERSION_CONTROL_SYSTEM.md`.
- Added multiline parameter/argument formatting guidance in
  `LANGUAGE/CONVENTIONS.md`.
- Added commit-message guidance requiring issue/ticket identifier plus title.
- Standardized `<AI_RULES_PATH>` placeholder usage in setup/update docs.

## [v3.0.1] - 2026-02-06
- Clarified update/setup path placeholder handling, including copy-paste-safe
  `.git/info/exclude` examples.
- Added readability guidance forbidding nested/chained ternary expressions.
- Added downstream-project ADR guidance under `docs/ai/DECISIONS/` and
  clarified downstream-project terminology.
- Clarified scope boundaries between repository governance files and
  downstream-project operational guidance.

## [v3.0.0] - 2026-02-05
- Breaking: flattened repo layout with `AI.md` at the repo root and subtree prefix
  guidance updated to `docs/ai/AI-RULES`.
- Added PROGRAMMING, PLAN, and CODE_REVIEW guidance with stricter review expectations.
- Added downstream-project guidance for lessons learned placement and maintenance rules.
- Added test fixture separation guidance and markdownlint hygiene updates.

## [v2.2.0] - 2026-02-04
- Added dual setup modes (local default) with mode switching and auto-detected updates.
- Added Windows subtree CRLF troubleshooting with renormalize guidance.
- Added Bun package manager guidance and Jest testing guidance (now in Library).
- Added N+1 query prevention rules under Architecture.
- Added Apache License 2.0 and a markdownlint lesson learned for placeholder tokens.
- Set unit test coverage expectations (100% line coverage by default).

## [v2.1.1] - 2026-02-01
- Improved README with clearer Getting Started section and formatted example prompts.
- Streamlined setup template.

## [v2.1.0] - 2026-02-01
- Added AI-prompted release guidance for streamlined version releases.
- Enhanced README and AI index with structure descriptions.

## [v2.0.0] - 2026-02-01
- Added AI-RULES meta guidance, including structure, formatting, update workflow, and lessons learned.
- Introduced BUILD_TOOLS category with Maven, Gradle, and npm guidance.
- Added VCS rules (commit message expectations and ignore-file baseline) and logging guidance.
- Expanded language guidance with TypeScript and Java VCS ignore additions.
- Improved index usability with one-line descriptions and updated repository structure docs.

## [v1.0.1] - 2026-01-31
- Updated docs-check workflow to use markdownlint configuration.
- Added markdownlint configuration to align CI with repository style.
- Updated lychee-action to 2.0.2 via Dependabot.

## [v1.0.0] - 2026-01-31
- Initial baseline ruleset and directory structure.
- Expanded design, architecture, compliance, and library guidance.
- Added CI/CD and infrastructure guidance.
- Added framework and library specific guidance.
