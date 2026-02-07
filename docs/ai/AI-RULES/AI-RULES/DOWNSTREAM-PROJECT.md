# DOWNSTREAM-PROJECT

Guidance for downstream-projects that vendor ai-rules.
In this documentation, "downstream-project" is the primary term.
Legacy wording "consuming project" means the same thing.

## Goals
- Keep the ai-rules subtree replaceable by updates.
- Store project-specific guidance and lessons learned outside the vendor path.

## Execution Guidance
- For planning, see [PLAN/PLAN.md](../PLAN/PLAN.md).
- For implementation, see [PROGRAMMING/PROGRAMMING.md](../PROGRAMMING/PROGRAMMING.md).
- For review, see [REVIEW/REVIEW.md](../REVIEW/REVIEW.md).

## Recommended Layout
- Vendor ai-rules under `docs/ai/AI-RULES/`.
- Baseline entry point: `docs/ai/AI-RULES/AI.md`.
- Keep project-specific AI files outside `docs/ai/AI-RULES/` (for example `AI_PROJECT.md`).
- Store project lessons learned under `docs/ai/LESSONS_LEARNED/` so updates do not overwrite them.
- Store project ADRs under `docs/ai/DECISIONS/` so architecture decisions stay
  with project-owned AI docs, not the vendored subtree.

## Lessons Learned (project-specific)
- Create `docs/ai/LESSONS_LEARNED/LESSONS_LEARNED.md` with an index and keep entries as
  `YYYY-MM-DD-short-title.md`.
- Keep the scope limited to the downstream-project and update existing entries
  when the issue repeats.

## Architecture Decision Records (project-specific)
- Create `docs/ai/DECISIONS/DECISIONS.md` as an ADR index.
- Store individual ADRs as `docs/ai/DECISIONS/ADR-0001-TITLE.md`,
  `docs/ai/DECISIONS/ADR-0002-TITLE.md`, and so on.

## Entry Points
- `AGENTS.md` should reference the baseline entry point and any local overlay
  such as `AI_PROJECT.md`.
- If you use `docs/ai/LESSONS_LEARNED/`, reference it from `AI_PROJECT.md` or other
  local guidance.
