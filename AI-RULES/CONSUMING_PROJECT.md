# CONSUMING_PROJECT

Guidance for downstream projects that vendor ai-rules.

## Goals
- Keep the ai-rules subtree replaceable by updates.
- Store project-specific guidance and lessons learned outside the vendor path.

## Recommended Layout
- Vendor ai-rules under `docs/ai/AI-RULES/`.
- Baseline entry point: `docs/ai/AI-RULES/AI.md`.
- Keep project-specific AI files outside `docs/ai/AI-RULES/` (for example `AI_PROJECT.md`).
- Store project lessons learned under `docs/ai/LESSONS_LEARNED/` so updates do not overwrite them.

## Lessons Learned (project-specific)
- Create `docs/ai/LESSONS_LEARNED/LESSONS_LEARNED.md` with an index and keep entries as
  `YYYY-MM-DD-short-title.md`.
- Keep the scope limited to the consuming project and update existing entries
  when the issue repeats.

## Entry Points
- `AGENTS.md` should reference the baseline entry point and any local overlay
  such as `AI_PROJECT.md`.
- If you use `docs/ai/LESSONS_LEARNED/`, reference it from `AI_PROJECT.md` or other
  local guidance.
