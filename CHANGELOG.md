# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

Sub-heading template:
- Unreleased section: `## Unreleased`
- Released versions: `## [vX.Y.Z] - YYYY-MM-DD`

## Unreleased

## [v4.7.0] - 2026-02-15
- Strengthened `AI-RULES/UPDATE.md` with explicit structure-adaptation guidance
  for baseline and downstream override directory/file changes, requiring ad-hoc
  migration planning from preflight findings instead of a fixed checklist.
- Added required update completion gates for structure alignment, git-subtree
  location validation (git mode), and stale-reference cleanup in entry/ignore
  files.

## [v4.6.0] - 2026-02-15
- Changed the default downstream base directory placeholder from `docs/ai` to
  `ai` across setup/template/update guidance.
- Hardened shared workflow policy to require plan-first execution, dedicated
  issue branch + PR/MR flow, and explicit completion status reporting fields.
- Added repository PR-loop preconditions to consume shared plan/VCS rules
  without redefining downstream-project guidance scope.
- Standardized release-note sub-heading formatting guidance in
  `AI-RULES/RELEASE.md` to enforce a single canonical heading template.
- Fixed duplicated/triplicated release-note top headings by deduplicating
  existing GitHub release-note sub-headings.

## [v4.5.0] - 2026-02-15
- Added session entry preferences and execution controls in
  `AI-RULES/PR-REVIEW-LOOP.md` for review-loop execution, plan-to-implementation
  autonomy, and optional merge-after-clean-loop behavior.
- Expanded `PLAN/PLAN.md` with stronger planning dependencies, architecture-first
  focus, and explicit research requirements to reduce shallow planning.
- Clarified inherited constraint resolution in `PROGRAMMING/PROGRAMMING.md` and
  `REVIEW/CODE_REVIEW.md` by explicitly requiring index-doc and leaf-doc
  traversal across language, design, architecture, framework, library, build,
  infrastructure, and CI/CD guidance.
- Updated repository security policy in `SECURITY.md` to support only the latest
  tagged release and to use best-effort triage timing (no fixed SLA).
- Removed outdated private-repository access guidance from
  `AGENTS_TEMPLATE.md`.

## [v4.4.0] - 2026-02-14
- Added repository-level security disclosure guidance in `SECURITY.md` for
  supported versions and coordinated vulnerability reporting.
- Added design guidance for cognitive complexity and early returns in
  `DESIGN/COGNITIVE_COMPLEXITY.md` and `DESIGN/EARLY_RETURN.md`, and linked both
  in design/dependency docs.
- Hardened `AI-RULES/PR-REVIEW-LOOP.md` merge gates and replaced placeholder
  review-trigger guidance with an executable `requestReviewsByLogin` GraphQL
  mutation flow.
- Updated downstream-project path guidance to keep project lessons learned and
  ADRs under `<AI_PROJECT_PATH>/...` across setup/update/template docs.
- Expanded language/design quality guidance, including semantic type preference
  in `LANGUAGE/CONVENTIONS.md` and exact numeric handling in
  `LANGUAGE/JAVA/JAVA.md`.
- Added explicit deep-doc contract sections in `FRAMEWORK/ANGULAR.md` and
  `FRAMEWORK/REACT.md`.
- Added `Do / Don't Examples` sections to `SECURITY/SECURITY.md` and
  `TEST/TEST.md` to align with deep technical doc requirements.

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
- Added downstream-project ADR guidance under `<AI_PROJECT_PATH>/DECISIONS/` and
  clarified downstream-project terminology.
- Clarified scope boundaries between repository governance files and
  downstream-project operational guidance.

## [v3.0.0] - 2026-02-05
- Breaking: flattened repo layout with `AI.md` at the repo root and subtree prefix
  guidance updated to `<AI_RULES_PATH>`.
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
