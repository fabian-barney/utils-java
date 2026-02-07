# 2026-02-03-markdownlint-angle-brackets

## Scope
- Applies only to this repository.
- Do not copy these rules into downstream-projects.

## Issue
Markdownlint failed because placeholder tokens wrapped in angle brackets (for
example, `REF` with angle brackets) were treated as inline HTML (MD033),
breaking CI.

## Prevention
- Wrap placeholder commands in backticks or fenced code blocks.
- Avoid raw angle-bracket tokens in prose; use code formatting instead.
- Run markdownlint (or scan for angle-bracket tokens) before pushing docs changes.
