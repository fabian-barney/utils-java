# UPDATE

Instructions for AI agents to update ai-rules in a downstream-project.

## Prompt Examples
- "update ai-rules"
- "update ai-rules v4.3.0"

## REF Determination Rules
Use these rules whenever a setup/update/mode-switch flow needs a `REF`.
- If the user specifies a tag (for example `v4.3.0`), validate it first:
  `git ls-remote --exit-code --refs --tags https://github.com/fabian-barney/ai-rules.git "refs/tags/<TAG>"`
  - If the command fails, stop and ask for a valid tag.
  - If it succeeds, set `REF=<TAG>`.
- If the user explicitly asks for a branch, validate it first:
  `git ls-remote --exit-code --heads https://github.com/fabian-barney/ai-rules.git "refs/heads/<BRANCH>"`
  - If the command fails, stop and ask for a valid branch.
  - If it succeeds, set `REF=<BRANCH>`.
- Otherwise resolve the latest tagged release:
  `git ls-remote --refs --tags --sort="version:refname" https://github.com/fabian-barney/ai-rules.git "v*"`
  - If at least one `v*` tag exists, set `REF` to the last tag in the sorted output.
  - If no tags exist, set `REF=main`.
- Before any subtree command, echo the resolved ref to the user:
  Using ai-rules REF: `<REF>`

## Update Steps (run when requested)
1. Locate the vendored ai-rules path and entry point from `AGENTS.md` or README.
   - Set `<AI_ROOT_PATH>` to the repo-relative ai docs root (for example
     `docs/ai`).
   - Set `<AI_RULES_PATH>` to the baseline path (for example
     `<AI_ROOT_PATH>/AI-RULES`).
   - Set `<AI_PROJECT_PATH>` to the downstream extension path (for example
     `<AI_ROOT_PATH>/PROJECT`).
   - Every time this guide shows `<AI_RULES_PATH>`, replace it with that real path.
   - Every time this guide shows `<AI_PROJECT_PATH>`, replace it with that real
     path.
   - For `.git/info/exclude`, the matching directory entry is `/<AI_RULES_PATH>/`
     (for example `/docs/ai/AI-RULES/`).
   - For downstream extensions in local mode, the matching directory entry is
     `/<AI_PROJECT_PATH>/` (for example `/docs/ai/PROJECT/`).
2. Enforce a clean-working-tree precondition before subtree operations:
   - Run `git status --porcelain`.
   - If output is empty, continue.
   - If output is not empty, do not run any `git subtree` command yet.
   - Resolve the dirty state first by one of:
     - Commit relevant work.
     - Stash local work.
     - Abort the ai-rules update.
3. Determine the setup mode (local or git):
   - If the user explicitly specifies a mode, use it.
   - Otherwise auto-detect using both repository signals:
     - `TRACKED_SUBTREE=true` if `git ls-files -- "<AI_RULES_PATH>/AI.md"` returns a tracked file.
     - `LOCAL_HINT=true` if `.git/info/exclude` contains the subtree directory
       entry `/<AI_RULES_PATH>/` (replace with the real path; example:
       `/docs/ai/AI-RULES/`).
       Companion excludes (for example `/AGENTS.md`, `/<AI_PROJECT_PATH>/`,
       `/CLAUDE.md`, `/.github/copilot-instructions.md`) are optional and do not
       affect `LOCAL_HINT`.
   - Resolve mode from combined signals:
     - `TRACKED_SUBTREE=true` and `LOCAL_HINT=false` => git mode.
     - `TRACKED_SUBTREE=false` and `LOCAL_HINT=true` => local mode.
     - `TRACKED_SUBTREE=true` and `LOCAL_HINT=true` => ambiguous
       (for example stale excludes after a previous mode switch); ask which mode to use.
     - `TRACKED_SUBTREE=false` and `LOCAL_HINT=false` => ambiguous; ask which mode to use.
4. Determine the currently installed ai-rules version (`CURRENT_VERSION`):
   - If the downstream-project explicitly tracks the installed ai-rules version,
     reuse that value.
   - Otherwise inspect `<AI_RULES_PATH>/CHANGELOG.md` and read the first
     released version heading (`## [vX.Y.Z]`) as `CURRENT_VERSION`.
   - If no version can be determined, set `CURRENT_VERSION=unknown`.
5. Determine the target version (`TARGET_VERSION`):
   - If the user specifies a tag, use it.
   - If the user explicitly asks for a branch, use it.
   - Otherwise, use the latest tagged release.
6. Determine `REF` using [REF Determination Rules](#ref-determination-rules):
   - Set `REF=<TARGET_VERSION>`.
   - Validate `REF` with the matching rule (tag or branch).
7. Run a compatibility preflight before any subtree command:
   - Load and review target-version docs at `REF` (the same ref used for subtree commands):
     - [CHANGELOG.md](../CHANGELOG.md)
     - [AI-RULES/UPDATE.md](UPDATE.md)
     - [AGENTS_TEMPLATE.md](../AGENTS_TEMPLATE.md)
   - Use any reliable method to inspect these files at `REF` (for example the
     repository web view, `git show`, or a temporary local checkout).
   - Review changelog entries:
     - If `CURRENT_VERSION` is known, review all versions after
       `CURRENT_VERSION` up to and including `TARGET_VERSION` (including
       intermediate versions).
     - If `CURRENT_VERSION=unknown`, review all changelog entries up to and
       including `TARGET_VERSION`.
   - Identify breaking or behavior-changing entries (for example path renames,
     entry-point changes, setup/update workflow changes, mode semantics changes).
   - Adapt the setup/update commands and file operations before execution.
   - Summarize the detected changes and adapted execution plan before proceeding.
   - If the target-version docs cannot be inspected, stop and ask the user how
     to proceed instead of guessing.
8. Update based on mode:
   - If it is git (tracked subtree):
     `git subtree pull --prefix "<AI_RULES_PATH>" https://github.com/fabian-barney/ai-rules.git <REF> --squash`
     Commit the update.
     Ask the user whether to push this commit, and only push if they explicitly confirm.
   - If it is local (no commits, no push):
     - Before editing `.git/info/exclude`, create a backup copy.
     - Temporarily remove the ai-rules entries from `.git/info/exclude`.
     - If `<AI_RULES_PATH>` already exists locally, remove it only after confirming there is no real work in it.
     - Ensure Git has a local author identity configured (required for subtree add). If needed:
       `git config --local user.name "Your Name"`
       `git config --local user.email "you@example.com"`
     - Run:
        `git subtree add --prefix "<AI_RULES_PATH>" https://github.com/fabian-barney/ai-rules.git <REF> --squash`
        (This creates a commit.)
     - Undo the commit but keep files:
        `git reset --mixed HEAD~1`
     - Always restore `.git/info/exclude` from the backup copy, even if subtree
        commands fail. Use try/finally behavior so restore runs on both success
        and failure paths.
     - When ensuring required entries after restore, add only missing lines and
        keep all other existing exclude rules unchanged.
     - Required local exclude entries after restore:
        /<AI_RULES_PATH>/
        /<AI_PROJECT_PATH>/
        /AGENTS.md
        /CLAUDE.md
        /.github/copilot-instructions.md
     - Verify `.git/info/exclude` was restored from backup and required exclude
        entries are present.
     - Recovery checklist (if update fails or restore verification fails):
        - Restore `.git/info/exclude` from the backup copy.
        - Re-check required local exclude entries.
        - Run `git status --short` and confirm local-only files are not staged.
        - Stop and ask the user before retrying subtree commands.
9. Verify the baseline entry point still resolves (e.g., `<AI_RULES_PATH>/AI.md`).
10. Verify the downstream extension entry point still resolves
    (e.g., `<AI_PROJECT_PATH>/AI.md`) when used.
    - Verify transitive reachability for markdown files under
      `<AI_PROJECT_PATH>` using the deterministic docs-only method in
      [AI-RULES/DOWNSTREAM-OVERRIDES.md](DOWNSTREAM-OVERRIDES.md).
11. Run cleanup for stale legacy references:
    - Remove or update references that still point to `AI_PROJECT.md`.
    - Ensure generated/final references point to `<AI_PROJECT_PATH>/AI.md`.
12. Preserve local extensions and any project-specific rules outside the vendor
    path, including `<AI_ROOT_PATH>/LESSONS_LEARNED/` if used.
13. Record the updated version in the destination repository if it tracks versions.
14. Summarize changes.

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
      ```
      git rm -r --cached --ignore-unmatch -- "<AI_RULES_PATH>" "<AI_PROJECT_PATH>" \
        "AGENTS.md" "CLAUDE.md" ".github/copilot-instructions.md"
      ```
      This must succeed even when optional entry-point files are absent.
      Note: `<AI_PROJECT_PATH>/` is shared/tracked in git mode. Switching to
      local intentionally converts it to local-only (untracked).
   - Add the local excludes to `.git/info/exclude` (keep the file intact):
     (Replace `/<AI_RULES_PATH>/` with the real path; example: `/docs/ai/AI-RULES/`.)
     /<AI_RULES_PATH>/
     /<AI_PROJECT_PATH>/
     /AGENTS.md
     /CLAUDE.md
     /.github/copilot-instructions.md
   - Verify switch result:
     - `git ls-files -- "<AI_RULES_PATH>/AI.md"` should return no tracked file.
     - `git status --short` should show only the intended mode-switch index changes.
   - Commit the removal. Ask the user whether to push this commit, and only push
     if they explicitly confirm.
3. If switching to git:
   - If already git, stop.
   - Remove the ai-rules entries from `.git/info/exclude` (keep the file intact):
     (Replace `/<AI_RULES_PATH>/` with the real path; example: `/docs/ai/AI-RULES/`.)
     /<AI_RULES_PATH>/
     /<AI_PROJECT_PATH>/
     /AGENTS.md
     /CLAUDE.md
     /.github/copilot-instructions.md
   - If `<AI_RULES_PATH>` exists locally, remove it only after confirming there is no real work in it.
   - Determine `REF` using [REF Determination Rules](#ref-determination-rules).
     - If the local ai-rules copy documents a version/tag (for example in `AGENTS.md`),
       treat it as user-specified and validate it before reuse.
     - If no reusable version is documented, resolve `REF` with the default path
       in REF Determination Rules (latest tagged release, fallback `main`).
   - Run:
     `git subtree add --prefix "<AI_RULES_PATH>" https://github.com/fabian-barney/ai-rules.git <REF> --squash`
   - Create any missing entry points (for example `AGENTS.md` final references,
     `<AI_PROJECT_PATH>/AI.md`, `CLAUDE.md`, and
     `.github/copilot-instructions.md`), ensure they are tracked, then commit
     and push.

## Expectations
- Prefer tagged releases unless explicitly asked for a branch.
- Do not overwrite local extensions or custom rules.
- In local mode, keep `<AI_PROJECT_PATH>/` local-only and excluded from VCS.
- In git mode, keep `<AI_PROJECT_PATH>/` tracked so the team can share it.
