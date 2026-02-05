# AGENTS.md - AI Rules Setup Template

## Purpose
This file bootstraps the ai-rules baseline into a new project. Copy this file
into your project root as `AGENTS.md` and ask your AI agent to run the setup.

## Setup (run once)
Keep this template content in place until the final step so the instructions are
still available if the setup is interrupted.

### Choose a setup mode
Follow the mode requested by the user:
- "setup ai-rules" or "setup ai-rules local" => Local-only (no commits, no push)
- "setup ai-rules git" => Git subtree (tracked in repo)
If the user did not specify a mode, default to local (do not ask).
To switch modes later, use "mode ai-rules local" or "mode ai-rules git".

### Vendor path
ai-rules is vendored under `docs/ai/AI-RULES/`.
Define `AI_RULES_PATH` as `docs/ai/AI-RULES`.

### Mode: local (no commits, no push)
1. Add the ai-rules subtree (creates a local commit):
   `git subtree add --prefix AI_RULES_PATH https://github.com/fabian-barney/ai-rules.git REF --squash`
   - Use the requested version tag; otherwise use the latest tagged release.
     If no tags exist, use `main`.
   - If Git requires an author identity, set it locally:
     git config --local user.name "Your Name"
     git config --local user.email "you@example.com"
   - Windows line ending note (important):
     If `git subtree add` fails with "fatal: working tree has modifications. Cannot add."
     but your IDE looks clean, it is usually CRLF/LF normalization noise. Fix locally:

     ```
     git config --local core.autocrlf true
     git add --renormalize .
     git status --porcelain
     ```

     If `git status --porcelain` is empty, continue. If not, stash/commit any
     real work first. If there are no real changes, you may discard them only
     after explicit confirmation (for example, `git reset --hard`).
2. Undo the subtree commit but keep files:
   git reset --mixed HEAD~1
3. Add local-only excludes to `.git/info/exclude`:
   /docs/ai/AI-RULES/
   /AGENTS.md
   /AI_PROJECT.md
   /CLAUDE.md
   /.github/copilot-instructions.md
4. Baseline entry point (after subtree add):
   AI_RULES_PATH/AI.md
5. Create a local overlay for project-specific rules (recommended):
   AI_PROJECT.md
   Note: keep this outside `docs/ai/` so subtree updates do not overwrite it.
6. Create a project lessons learned area (recommended):
   docs/ai/LESSONS_LEARNED/LESSONS_LEARNED.md
   See `AI-RULES/CONSUMING_PROJECT.md` for the template.
7. Create entry points for other AI tools:
   - `CLAUDE.md` (Claude Code)
   - `.github/copilot-instructions.md` (GitHub Copilot)
8. Replace this template content in `AGENTS.md` with the final references:
   - Baseline: AI_RULES_PATH/AI.md
   - Overlay: AI_PROJECT.md

Local-only update note:
- Follow the local-mode update steps in `AI-RULES/UPDATE.md`.
  Do not run `git subtree add` on top of an existing `AI_RULES_PATH` directory.
  If `AI_RULES_PATH` already exists locally, remove it only after confirming
  there is no real work in that directory.

### Mode: git (tracked in repo)
1. Add the ai-rules subtree:
   `git subtree add --prefix AI_RULES_PATH https://github.com/fabian-barney/ai-rules.git REF --squash`
   - Use the requested version tag; otherwise use the latest tagged release.
     If no tags exist, use `main`.
   - If Git requires an author identity, set it locally:
     git config --local user.name "Your Name"
     git config --local user.email "you@example.com"
   - Windows line ending note (important):
     If `git subtree add` fails with "fatal: working tree has modifications. Cannot add."
     but your IDE looks clean, it is usually CRLF/LF normalization noise. Fix locally:

     ```
     git config --local core.autocrlf true
     git add --renormalize .
     git status --porcelain
     ```

     If `git status --porcelain` is empty, continue. If not, stash/commit any
     real work first. If there are no real changes, you may discard them only
     after explicit confirmation (for example, `git reset --hard`).
2. Baseline entry point (after subtree add):
   AI_RULES_PATH/AI.md
3. Create a local overlay for project-specific rules (recommended):
   AI_PROJECT.md
   Note: keep this outside `docs/ai/` so subtree updates do not overwrite it.
4. Create a project lessons learned area (recommended):
   docs/ai/LESSONS_LEARNED/LESSONS_LEARNED.md
   See `AI-RULES/CONSUMING_PROJECT.md` for the template.
5. Create entry points for other AI tools:
   - `CLAUDE.md` (Claude Code)
   - `.github/copilot-instructions.md` (GitHub Copilot)
6. Replace this template content in `AGENTS.md` with the final references:
   - Baseline: AI_RULES_PATH/AI.md
   - Overlay: AI_PROJECT.md
7. Commit and push the changes.

Git update note:
- Use `git subtree pull --prefix AI_RULES_PATH https://github.com/fabian-barney/ai-rules.git REF --squash`
  and commit the update.

## Entry Point Templates
Note: Replace `AI_RULES_PATH` with `docs/ai/AI-RULES`.

### CLAUDE.md
```
# CLAUDE

Use the shared AI rules located at:
- Baseline: AI_RULES_PATH/AI.md
- Overlay: AI_PROJECT.md
```

### .github/copilot-instructions.md
```
# Copilot Instructions

Use the shared AI rules located at:
- Baseline: AI_RULES_PATH/AI.md
- Overlay: AI_PROJECT.md
```

> Note: The Copilot PR Review bot draws context from repository instructions.
> Keeping `.github/copilot-instructions.md` present and pointed at the baseline
> increases the chance it uses the same rules.

## Access
This repository is private; ensure your GitHub account has access.
