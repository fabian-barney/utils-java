# 2026-02-08-react-example-robustness

## Scope
- Applies only to this repository.
- Do not copy these rules into downstream-projects.

## Issue
React guidance examples were updated in multiple passes and initially missed
copy/paste robustness details (duplicate component names in paired snippets,
environment-specific abort handling assumptions, and browser globals read during
render in "good" examples).

## Prevention
- In paired Do/Don't snippets, use unique function names (`*Bad` / `*Good`) so
  each snippet is independently copy/paste compilable.
- Use cross-environment-safe patterns in "good" examples (for example
  `error instanceof Error && error.name === "AbortError"`).
- If SSR/hydration may exist, avoid browser-global reads during render in "good"
  examples; initialize safely and read browser globals in effects.
- In fire-and-forget async effect tasks, avoid rethrowing inside `catch`; handle
  non-abort errors explicitly with state/reporting so examples do not model
  unhandled promise rejections.
- In async fetch examples, handle non-OK responses explicitly and clear stale
  render state when request context changes, so failed loads do not leave stale
  data visible.
- In "Don't" examples, add one short reason comment so non-experts understand
  the specific failure mode, not only the pattern label.
