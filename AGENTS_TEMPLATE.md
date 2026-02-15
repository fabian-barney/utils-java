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

### Paths
Use these placeholders consistently:
- `<AI_ROOT_PATH>` = `ai`
- `<AI_RULES_PATH>` = `<AI_ROOT_PATH>/AI-RULES`
- `<AI_PROJECT_PATH>` = `<AI_ROOT_PATH>/PROJECT`

By default, ai-rules is vendored under `<AI_RULES_PATH>`.

### Precondition: clean working tree
Before any `git subtree add`/`git subtree pull` command:
- Run `git status --porcelain`.
- If output is empty, continue.
- If output is not empty, do not run subtree commands yet.
- Resolve the dirty state first by committing/stashing the current work, or
  abort setup/update.

### Resolve REF (deterministic)
Use this before any `git subtree add/pull` command:
- If the user specifies a tag (for example `v4.7.0`), validate it:
  `git ls-remote --exit-code --refs --tags https://github.com/fabian-barney/ai-rules.git "refs/tags/<TAG>"`
  - If valid, set `REF=<TAG>`.
  - If invalid, stop and ask for a valid tag.
- If the user explicitly asks for a branch, validate it:
  `git ls-remote --exit-code --heads https://github.com/fabian-barney/ai-rules.git "refs/heads/<BRANCH>"`
  - If valid, set `REF=<BRANCH>`.
  - If invalid, stop and ask for a valid branch.
- Otherwise resolve the latest tagged release:
  `git ls-remote --refs --tags --sort="version:refname" https://github.com/fabian-barney/ai-rules.git "v*"`
  - If at least one `v*` tag exists, set `REF` to the last tag in the sorted output.
  - If no tags exist, set `REF=main`.
- Before executing subtree commands, echo:
  Using ai-rules REF: `<REF>`

### Target-version preflight (required)
Before running any setup/update subtree command, inspect the target ai-rules
version and adapt behavior as needed:
- Use the same `REF` that will be passed to `git subtree add`/`git subtree pull`.
- Read target-version docs:
  - `CHANGELOG.md`
  - `AI-RULES/UPDATE.md`
  - `AGENTS_TEMPLATE.md`
- Inspect these files at `REF` using any reliable method (for example repository
  web view, `git show`, or a temporary local checkout).
- If the target version includes breaking or behavior-changing setup/update
  guidance, follow the target-version guidance even if it differs from this
  template.
- Summarize the detected differences and planned command changes before running
  `git subtree add`/`git subtree pull`.

### Mode: local (no commits, no push)
1. Add the ai-rules subtree (creates a local commit):
   `git subtree add --prefix <AI_RULES_PATH> https://github.com/fabian-barney/ai-rules.git <REF> --squash`
   - Resolve `REF` using the deterministic rules above.
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
   /<AI_RULES_PATH>/
   /<AI_PROJECT_PATH>/
   /AGENTS.md
   /CLAUDE.md
   /.github/copilot-instructions.md
4. Baseline entry point (after subtree add):
   `<AI_RULES_PATH>/AI.md`
5. Create a local extension entry for project-specific AI rules (recommended):
   `<AI_PROJECT_PATH>/AI.md`
   In local mode, keep `<AI_PROJECT_PATH>/` local-only (excluded from VCS).
   Verify transitive reachability for markdown files under `<AI_PROJECT_PATH>`
   using `AI-RULES/DOWNSTREAM-OVERRIDES.md`.
6. Create a project lessons learned area (recommended):
   `<AI_PROJECT_PATH>/LESSONS_LEARNED/LESSONS_LEARNED.md`
   See `AI-RULES/DOWNSTREAM-PROJECT.md` and
   `AI-RULES/DOWNSTREAM-OVERRIDES.md` for guidance.
7. Create a project ADR area (recommended):
   `<AI_PROJECT_PATH>/DECISIONS/DECISIONS.md`
   `<AI_PROJECT_PATH>/DECISIONS/ADR-0001-TITLE.md`
   See `AI-RULES/DOWNSTREAM-PROJECT.md` for guidance.
8. Create entry points for other AI tools:
   - `CLAUDE.md` (Claude Code)
   - `.github/copilot-instructions.md` (GitHub Copilot)
9. Replace this template content in `AGENTS.md` with the final references:
   - Baseline: `<AI_RULES_PATH>/AI.md`
   - Downstream extension: `<AI_PROJECT_PATH>/AI.md`

Local-only update note:
- Follow the local-mode update steps in `AI-RULES/UPDATE.md`.
  Do not run `git subtree add` on top of an existing `<AI_RULES_PATH>` directory.
  If `<AI_RULES_PATH>` already exists locally, remove it only after confirming
  there is no real work in that directory.

### Mode: git (tracked in repo)
1. Add the ai-rules subtree:
   `git subtree add --prefix <AI_RULES_PATH> https://github.com/fabian-barney/ai-rules.git <REF> --squash`
   - Resolve `REF` using the deterministic rules above.
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
   `<AI_RULES_PATH>/AI.md`
3. Create a project extension entry for project-specific rules (recommended):
   `<AI_PROJECT_PATH>/AI.md`
   In git mode, keep `<AI_PROJECT_PATH>/` tracked so the team shares the
   extensions.
   Verify transitive reachability for markdown files under `<AI_PROJECT_PATH>`
   using `AI-RULES/DOWNSTREAM-OVERRIDES.md`.
4. Create a project lessons learned area (recommended):
   `<AI_PROJECT_PATH>/LESSONS_LEARNED/LESSONS_LEARNED.md`
   See `AI-RULES/DOWNSTREAM-PROJECT.md` and
   `AI-RULES/DOWNSTREAM-OVERRIDES.md` for guidance.
5. Create a project ADR area (recommended):
   `<AI_PROJECT_PATH>/DECISIONS/DECISIONS.md`
   `<AI_PROJECT_PATH>/DECISIONS/ADR-0001-TITLE.md`
   See `AI-RULES/DOWNSTREAM-PROJECT.md` for guidance.
6. Create entry points for other AI tools:
   - `CLAUDE.md` (Claude Code)
   - `.github/copilot-instructions.md` (GitHub Copilot)
7. Replace this template content in `AGENTS.md` with the final references:
   - Baseline: `<AI_RULES_PATH>/AI.md`
   - Downstream extension: `<AI_PROJECT_PATH>/AI.md`
8. Commit and push the changes.

Git update note:
- Use `git subtree pull --prefix <AI_RULES_PATH> https://github.com/fabian-barney/ai-rules.git <REF> --squash`
  and commit the update.

## Entry Point Templates
Note: Replace placeholders with actual paths:
- `<AI_ROOT_PATH>` => `ai`
- `<AI_RULES_PATH>` => `ai/AI-RULES`
- `<AI_PROJECT_PATH>` => `ai/PROJECT`

### CLAUDE.md
```
# CLAUDE

Use the shared AI rules located at:
- Baseline: <AI_RULES_PATH>/AI.md
- Downstream extension: <AI_PROJECT_PATH>/AI.md
```

### .github/copilot-instructions.md
```
# Copilot Instructions

Use the shared AI rules located at:
- Baseline: <AI_RULES_PATH>/AI.md
- Downstream extension: <AI_PROJECT_PATH>/AI.md
```

> Note: The Copilot PR Review bot draws context from repository instructions.
> Keeping `.github/copilot-instructions.md` present and pointed at the baseline
> increases the chance it uses the same rules.
