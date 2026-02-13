# RELEASE

Release process for the ai-rules repository (not downstream-projects).

## Scope
- Applies only to this repository.
- Do not copy these rules into downstream-projects.

## AI-Prompted Release
Example prompts:
- "release ai-rules"
- "release ai-rules v4.3.0"

Version selection:
- If the user specifies a version, use it (must be `vMAJOR.MINOR.PATCH`).
- Otherwise, choose the next version by inspecting changes since the latest tag:
  - Major: breaking changes, removals, renames, or entry point changes.
  - Minor: new categories or substantial new guidance without breaking changes.
  - Patch: clarifications, formatting, or minor fixes.
  - If multiple types apply, choose the highest bump.
  - If there are no user-visible changes, do not release.

## Steps
1. Ensure `main` is up to date and all release PRs are merged.
2. Determine the target version (explicit or computed as above).
3. Update `CHANGELOG.md`:
   - Add a new version section with today's date.
   - Summarize user-visible changes.
4. Update versioned examples to the new tag (for example, prompts in `README.md`).
5. Commit the changelog and example updates with a release prep message.
6. Create an annotated tag (for example `v4.3.0`).
7. Push `main` and the tag to GitHub.
8. Create a GitHub Release using the changelog notes.
9. Verify the release page and tag exist.

## Notes
- Prefer tagged releases; do not release from feature branches.
- Keep release notes concise and aligned with the changelog.
