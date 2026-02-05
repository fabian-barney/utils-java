# Contributing

Thank you for improving ai-rules. This repo is the single source of truth for
shared AI guidance, so changes should be deliberate and well-reviewed.

## Workflow
- Make focused changes per PR.
- Update the relevant directory entry file (e.g., `DESIGN/DESIGN.md`).
- Keep `AI.md` as the single top-level entry point.
- Avoid quoting copyrighted sources; summarize in your own words.

## Review Expectations
- Use PRs for all changes.
- Verify AI-generated content for correctness and alignment with repo goals.
- If a change affects multiple areas or changes policy, add an ADR in
  `docs/decisions/`.

## Versioning and Releases
- Tag releases (e.g., `v0.2.0`).
- Update `CHANGELOG.md` with a short summary for each release.

## Docs Hygiene
- Keep filenames UPPERCASE at the repo root.
- Keep line endings consistent (Windows CRLF is acceptable here).
- Ensure links and markdown pass CI checks.
