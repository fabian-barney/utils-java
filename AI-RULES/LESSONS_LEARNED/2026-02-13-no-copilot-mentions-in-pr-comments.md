# 2026-02-13-no-copilot-mentions-in-pr-comments

## Scope
- Applies only to this repository.
- Do not copy these rules into downstream-projects.

## Issue
Mentioning `@copilot` in PR comments can trigger unintended Copilot behavior,
including autonomous implementation attempts that interfere with the intended
issue/branch/PR ownership and review loop.

## Prevention
- Do not mention `@copilot` in PR comments.
- Use passive polling for Copilot review status instead of comment mentions.
- If a re-review is needed, use the repository PR review loop workflow without
  `@copilot` mentions.
