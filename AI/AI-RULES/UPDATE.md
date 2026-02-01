# UPDATE

Instructions for AI agents to update ai-rules in a downstream repository.

## Prompt Examples
- "update ai-rules"
- "update ai-rules to version v2.0.0"
- "update ai-rules to the latest tagged release"

## Update Steps (run when requested)
1. Locate the vendored ai-rules path and entry point from `AGENTS.md` or README.
2. Determine the target version:
   - If the user specifies a tag, use it.
   - Otherwise, use the latest tagged release.
3. Update the vendor path using the repository's method:
   - If it is a git subtree:
     `git subtree pull --prefix <path> https://github.com/fabian-barney/ai-rules.git <ref> --squash`
   - If it is not a subtree, follow the repository's documented update process.
4. Verify the baseline entry point still resolves (e.g., `<path>/AI/AI.md`).
5. Preserve local overlays and any project-specific rules outside the vendor path.
6. Record the updated version in the destination repository if it tracks versions.
7. Summarize changes and open a PR.

## Expectations
- Prefer tagged releases unless explicitly asked for a branch.
- Do not overwrite local overlays or custom rules.
