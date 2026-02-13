# Contributing

Thank you for improving ai-rules. This repo is the single source of truth for
shared AI guidance, so changes should be deliberate and well-reviewed.

## Workflow
- Start from an issue. If none exists, create one before implementation.
- Use one concern per issue branch and PR.
- For each new concern/issue, create a new branch from `main`.
- Make focused changes per PR.
- Open a dedicated PR per issue branch and link it with a closing keyword
  (for example `Closes #123`) so merge auto-closes the issue.
- Update the relevant directory entry file (e.g., `DESIGN/DESIGN.md`).
- Keep `AI.md` as the single top-level entry point.
- Avoid quoting copyrighted sources; summarize in your own words.

## Review Expectations
- Use PRs for all changes.
- Verify AI-generated content for correctness and alignment with repo goals.
- If a change affects downstream-project policy, update related AI-RULES docs
  and templates in the same PR.

## Versioning and Releases
- Tag releases (e.g., `v0.2.0`).
- Update `CHANGELOG.md` with a short summary for each release.

## Docs Hygiene
- Keep filenames UPPERCASE at the repo root.
- Keep line endings consistent (Windows CRLF is acceptable here).
- Ensure links and markdown pass CI checks.
