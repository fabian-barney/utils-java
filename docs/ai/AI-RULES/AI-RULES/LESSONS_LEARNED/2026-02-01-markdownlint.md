# 2026-02-01-markdownlint

## Scope
- Applies only to this repository.
- Do not copy these rules into downstream-projects.

## Issue
Markdownlint failures after changes (MD013 line length, MD024 duplicate headings,
MD047 trailing newline) and a UTF-8 BOM.

## Prevention
- Keep lines <= 120 characters and wrap long list items.
- Ensure index files have a single "## Files" section.
- Ensure every Markdown file ends with a single trailing newline.
- Save Markdown files as UTF-8 without BOM.
