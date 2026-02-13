# LESSONS_LEARNED

Compact, actionable notes to prevent repeat mistakes.

## Scope
- Applies only to this repository.
- Do not copy these rules into downstream-projects.
- Avoid duplicate lessons; update an existing entry if the issue matches.
- Keep each lesson focused on a single concern. If multiple issues occur, split
  them into separate files.

## Template
Copy and fill in for each lesson learned:
```
# YYYY-MM-DD-short-title

## Scope
- Applies only to this repository.
- Do not copy these rules into downstream-projects.

## Issue
What went wrong and why it mattered.

## Prevention
Concrete steps or checks that would have avoided the issue.
```

## Files
- [2026-02-13-retrigger-copilot-review-after-fix-pushes.md](2026-02-13-retrigger-copilot-review-after-fix-pushes.md)
  - Re-trigger Copilot review after every fix push before merge.
- [2026-02-13-no-copilot-mentions-in-pr-comments.md](2026-02-13-no-copilot-mentions-in-pr-comments.md)
  - Never mention `@copilot` in PR comments.
- [2026-02-13-github-issue-newline-escaping.md](2026-02-13-github-issue-newline-escaping.md)
  - Prevent scrambled issue bodies by using `--body-file` for create/edit.
- [2026-02-08-react-example-robustness.md](2026-02-08-react-example-robustness.md)
  - Keep React guidance examples copy/paste compilable and cross-environment robust.
- [2026-02-07-pr-issue-linking.md](2026-02-07-pr-issue-linking.md)
  - Link each PR to its issue so merged PRs close issues automatically.
- [2026-02-04-md012-after-bulk-edits.md](2026-02-04-md012-after-bulk-edits.md)
  - Prevent MD012 regressions after bulk edits.
- [2026-02-04-review-comments-resolution.md](2026-02-04-review-comments-resolution.md)
  - Resolve review comments instead of deleting them.
- [2026-02-03-markdownlint-angle-brackets.md](2026-02-03-markdownlint-angle-brackets.md)
  - Avoid inline HTML from placeholder tokens.
- [2026-02-01-markdownlint.md](2026-02-01-markdownlint.md) - Markdownlint and BOM prevention checklist.
- [2026-02-01-release-version-examples.md](2026-02-01-release-version-examples.md)
  - Update versioned examples before tagging.
