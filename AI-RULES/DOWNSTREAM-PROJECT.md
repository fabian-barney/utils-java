# DOWNSTREAM-PROJECT

Guidance for downstream-projects that vendor ai-rules.
In this documentation, "downstream-project" is the primary term.
Legacy wording "consuming project" means the same thing.

## Goals
- Keep the ai-rules subtree replaceable by updates.
- Store project-specific guidance and lessons learned outside the vendor path.

## Path Placeholders
Default placeholder mapping for downstream guidance:
- `<AI_ROOT_PATH>` = `docs/ai`
- `<AI_RULES_PATH>` = `<AI_ROOT_PATH>/AI-RULES`
- `<AI_PROJECT_PATH>` = `<AI_ROOT_PATH>/PROJECT`

## Execution Guidance
- For planning, see [PLAN/PLAN.md](../PLAN/PLAN.md).
- For implementation, see [PROGRAMMING/PROGRAMMING.md](../PROGRAMMING/PROGRAMMING.md).
- For review, see [REVIEW/REVIEW.md](../REVIEW/REVIEW.md).
- For downstream extension authoring workflow, see
  [DOWNSTREAM-OVERRIDES.md](DOWNSTREAM-OVERRIDES.md).

## Recommended Layout
- Vendor ai-rules under `<AI_RULES_PATH>/`.
- Baseline entry point: `<AI_RULES_PATH>/AI.md`.
- Keep project-specific AI extensions outside `<AI_RULES_PATH>/` under
  `<AI_PROJECT_PATH>/`.
- Downstream extension entry point: `<AI_PROJECT_PATH>/AI.md`.
- Store project lessons learned under `<AI_ROOT_PATH>/LESSONS_LEARNED/` so updates do not overwrite them.
- Store project ADRs under `<AI_ROOT_PATH>/DECISIONS/` so architecture decisions stay
  with project-owned AI docs, not the vendored subtree.

## Lessons Learned (project-specific)
- Create `<AI_ROOT_PATH>/LESSONS_LEARNED/LESSONS_LEARNED.md` with an index and keep entries as
  `YYYY-MM-DD-short-title.md`.
- Keep the scope limited to the downstream-project and update existing entries
  when the issue repeats.

## Architecture Decision Records (project-specific)
- Create `<AI_ROOT_PATH>/DECISIONS/DECISIONS.md` as an ADR index.
- Store individual ADRs as `<AI_ROOT_PATH>/DECISIONS/ADR-0001-TITLE.md`,
  `<AI_ROOT_PATH>/DECISIONS/ADR-0002-TITLE.md`, and so on.

## Entry Points
- `AGENTS.md` should reference the baseline entry point and downstream extension
  entry point such as `<AI_PROJECT_PATH>/AI.md`.
- If you use `<AI_ROOT_PATH>/LESSONS_LEARNED/`, reference it from
  `<AI_PROJECT_PATH>/AI.md` or other local guidance.
- Verify transitive reachability for all markdown files under
  `<AI_PROJECT_PATH>` using the deterministic docs-only method in
  [`AI-RULES/DOWNSTREAM-OVERRIDES.md`](DOWNSTREAM-OVERRIDES.md).
