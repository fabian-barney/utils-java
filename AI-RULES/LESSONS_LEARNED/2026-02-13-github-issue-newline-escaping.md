# 2026-02-13-github-issue-newline-escaping

## Scope
- Applies only to this repository.
- Do not copy these rules into downstream-projects.

## Issue
Some GitHub issue bodies were posted with literal `\n` sequences, so GitHub
rendered them as a single scrambled paragraph instead of formatted Markdown.
The root cause was submitting an escaped string payload instead of raw Markdown
with real newline characters.

## Prevention
- Prefer `gh issue create --body-file <path>` with a raw UTF-8 Markdown file.
- For updates, use `gh issue edit <id> --body-file <path>` instead of passing
  escaped inline strings.
- If content comes from JSON, decode once before posting (for example, with
  `jq -r`).
- Add a preflight check that blocks publish when the body contains literal
  `\n` markers.
- After creating or editing an issue, verify rendered formatting in GitHub for
  headings and bullet lists.
