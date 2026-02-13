# AI-RULES

Guidance for maintaining this ai-rules repository.

## Terminology
- "ai-rules" refers to this repository's baseline ruleset and its published tags.
- "downstream-project" means a project/repository that vendors and uses
  ai-rules.
- "consuming project" is a legacy synonym for "downstream-project".

## Files
- [STRUCTURE.md](STRUCTURE.md) - Structure rules for this repository only.
- [FORMATTING.md](FORMATTING.md) - Formatting rules for this repository only.
- [RELEASE.md](RELEASE.md) - Release checklist for this repository.
- [PR-REVIEW-LOOP.md](PR-REVIEW-LOOP.md) - Standard Copilot review loop for
  repository PRs.
- [DOWNSTREAM-OVERRIDES.md](DOWNSTREAM-OVERRIDES.md) - Authoring model for
  downstream-project extension rules and conflict precedence.
- [UPDATE.md](UPDATE.md) - Update workflow and prompt examples for downstream-projects.
- [DOWNSTREAM-PROJECT.md](DOWNSTREAM-PROJECT.md) - downstream-project layout and lessons learned guidance.
- [LESSONS_LEARNED/LESSONS_LEARNED.md](LESSONS_LEARNED/LESSONS_LEARNED.md) - Index of lessons
  to prevent repeat mistakes.

## Maintenance
Additional maintenance rules:
- Avoid redundancy across AI-RULES docs. If a new focused file supersedes content here, remove the overlap
  and link to the single source of truth instead.
- If a task requires a second attempt, add a short lesson under
  `AI-RULES/LESSONS_LEARNED/` and link it.
- Before final response, run a quick AI-RULES self-check (structure, formatting, and relevant lessons learned).
  Do not respond until the self-check passes.
