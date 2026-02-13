# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

## Unreleased

## [v4.3.0] - 2026-02-13
- Added architecture guidance for CQRS in `ARCHITECTURE/CQRS.md` and linked it
  from the architecture index.
- Added downstream override guidance in `AI-RULES/DOWNSTREAM-OVERRIDES.md`,
  including deterministic markdown reachability validation for project-owned AI
  extensions.
- Added a dedicated PR review loop guide in `AI-RULES/PR-REVIEW-LOOP.md` and
  repository Copilot instructions entrypoint guidance.
- Expanded lessons learned with guidance for GitHub issue newline escaping,
  avoiding `@copilot` mentions in PR comments, and retriggering Copilot
  re-review after fix pushes.
- Clarified TypeScript class member ordering for JSDoc blocks and decorators.

## [v4.2.0] - 2026-02-08
- Added a canonical semantic dependency tree and explicit layer contracts across
  core/language/framework/library/build-tools/infrastructure/CI-CD/design/review
  guidance.
- Expanded baseline language and cross-cutting rules into deep, agent-focused
  guidance (including JavaScript, TypeScript, Java, HTML, CSS, SQL, YAML, Shell,
  security, testing, and logging).
- Added and expanded deep guidance for architecture, framework, library, build,
  and infrastructure topics, including framework-specific Angular/React/Svelte
  guardrails and extensive Java ecosystem/library coverage.
- Refined documentation quality and consistency through broad Copilot review
  backfills, schema/interop clarifications, terminology cleanup, and encoding
  fixes.

## [v4.1.0] - 2026-02-07
- Added deterministic `REF` resolution/validation guidance for setup/update
  flows, including explicit fallback behavior.
- Added a target-version compatibility preflight that compares current vs target
  version and adapts update behavior using target docs.
- Hardened setup mode detection to combine tracked-subtree and local-hint
  signals with explicit ambiguous-state handling.
- Added clean-working-tree preconditions and explicit push confirmation after
  git-mode updates.
- Hardened local-mode update and mode-switch safety with missing-safe untracking,
  `.git/info/exclude` backup/restore requirements, and recovery verification.
- Added explicit PR/MR review-comment handling workflow guidance (validity
  judgment, response, fix-or-follow-up, and resolution ownership checks).

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
