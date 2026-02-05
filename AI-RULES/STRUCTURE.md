# STRUCTURE

Rules for organizing the ai-rules repository itself (not downstream projects).

## Scope
- Applies only to this repository.
- Do not copy these rules into consuming projects.

## Directory Layout
- Keep top-level categories at the repository root and list them in `AI.md`.
- Each category must have an index file named after the directory (e.g., `CORE/CORE.md`).
- Category index files should only link one level down.
- In index files, each "## Files" entry should include a short one-line description when helpful.
- Do not add headings that only link to another file; use the "## Files" list instead.
- In `AI.md`, add a one-line description for every link to help navigation.

## AI-RULES Area
- Meta-guidance lives under `AI-RULES/`.
- Link AI-RULES files from `AI-RULES/AI-RULES.md`.
- Keep bootstrap definitions in `AGENTS_TEMPLATE.md` in sync with terminology
  in `AI-RULES/AI-RULES.md`.
