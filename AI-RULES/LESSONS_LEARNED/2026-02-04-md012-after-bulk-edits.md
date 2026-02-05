# 2026-02-04-md012-after-bulk-edits

## Scope
- Applies only to this repository.
- Do not copy these rules into consuming projects.

## Issue
Markdownlint MD012 failures (multiple blank lines) appeared across many files
after bulk refactors, breaking CI.

## Prevention
- After large doc or path changes, run a repo-wide check for multiple blank lines
  and fix MD012 before pushing.
