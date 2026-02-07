# UPDATE

Instructions for AI agents to update ai-rules in a downstream-project.

## Prompt Examples
- "update ai-rules"
- "update ai-rules v4.0.0"

## Update Steps (run when requested)
1. Locate the vendored ai-rules path and entry point from `AGENTS.md` or README.
   - Set `<AI_RULES_PATH>` to the repo-relative path (for example `docs/ai/AI-RULES`).
   - Every time this guide shows `<AI_RULES_PATH>`, replace it with that real path.
   - For `.git/info/exclude`, the matching directory entry is `/<AI_RULES_PATH>/`
     (for example `/docs/ai/AI-RULES/`).
2. Determine the setup mode (local or git):
   - If the user explicitly specifies a mode, use it.
   - Otherwise auto-detect from the repository:
     - If `.git/info/exclude` contains any of these entries, treat this as local mode
       (replace `/<AI_RULES_PATH>/` with the real path; example: `/docs/ai/AI-RULES/`):
       /<AI_RULES_PATH>/
       /AGENTS.md
       /AI_PROJECT.md
       /CLAUDE.md
       /.github/copilot-instructions.md
     - If `git ls-files -- "<AI_RULES_PATH>/AI.md"` returns a tracked file, treat this as git mode.
     - If it is ambiguous, ask which mode to use.
3. Determine the target version:
   - If the user specifies a tag, use it.
   - Otherwise, use the latest tagged release.
4. Update based on mode:
   - If it is git (tracked subtree):
     `git subtree pull --prefix "<AI_RULES_PATH>" https://github.com/fabian-barney/ai-rules.git REF --squash`
     Commit the update.
   - If it is local (no commits, no push):
     - Temporarily remove the ai-rules entries from `.git/info/exclude`.
     - If `<AI_RULES_PATH>` already exists locally, remove it only after confirming there is no real work in it.
     - Ensure Git has a local author identity configured (required for subtree add). If needed:
       `git config --local user.name "Your Name"`
       `git config --local user.email "you@example.com"`
     - Run:
       `git subtree add --prefix "<AI_RULES_PATH>" https://github.com/fabian-barney/ai-rules.git REF --squash`
       (This creates a commit.)
     - Undo the commit but keep files:
       `git reset --mixed HEAD~1`
     - Re-add the exclude entries to `.git/info/exclude`.
5. Verify the baseline entry point still resolves (e.g., `<AI_RULES_PATH>/AI.md`).
6. Preserve local overlays and any project-specific rules outside the vendor path,
   including `docs/ai/LESSONS_LEARNED/` if used.
7. Record the updated version in the destination repository if it tracks versions.
8. Summarize changes.

## Mode Switch (when requested)
Prompt Examples:
- "mode ai-rules local"
- "mode ai-rules git"

Steps:
1. Detect the current mode using the same rules as the update flow.
2. If switching to local:
   - If already local, stop.
   - If currently git mode, confirm the user wants to remove ai-rules from version
     control for this repo. This requires a commit.
   - Remove tracked ai-rules paths but keep files:
     `git rm -r --cached -- "<AI_RULES_PATH>" "AGENTS.md" "AI_PROJECT.md" "CLAUDE.md" ".github/copilot-instructions.md"`
     Note: This treats `AI_PROJECT.md` as local-only too. If the user wants it
     shared, confirm before removing it.
   - Add the local excludes to `.git/info/exclude` (keep the file intact):
     (Replace `/<AI_RULES_PATH>/` with the real path; example: `/docs/ai/AI-RULES/`.)
     /<AI_RULES_PATH>/
     /AGENTS.md
     /AI_PROJECT.md
     /CLAUDE.md
     /.github/copilot-instructions.md
   - Commit the removal. Ask the user whether to push this commit, and only push
     if they explicitly confirm.
3. If switching to git:
   - If already git, stop.
   - Remove the ai-rules entries from `.git/info/exclude` (keep the file intact):
     (Replace `/<AI_RULES_PATH>/` with the real path; example: `/docs/ai/AI-RULES/`.)
     /<AI_RULES_PATH>/
     /AGENTS.md
     /AI_PROJECT.md
     /CLAUDE.md
     /.github/copilot-instructions.md
   - If `<AI_RULES_PATH>` exists locally, remove it only after confirming there is no real work in it.
   - Determine `REF`:
     - If the local ai-rules copy documents a version/tag (for example in `AGENTS.md`),
       reuse that tag.
     - Otherwise, use the latest tagged release.
   - Run:
     `git subtree add --prefix "<AI_RULES_PATH>" https://github.com/fabian-barney/ai-rules.git REF --squash`
   - Create any missing entry points (for example `AGENTS.md` final references,
     `AI_PROJECT.md`, `CLAUDE.md`, and `.github/copilot-instructions.md`), ensure
     they are tracked, then commit and push.

## Expectations
- Prefer tagged releases unless explicitly asked for a branch.
- Do not overwrite local overlays or custom rules.
