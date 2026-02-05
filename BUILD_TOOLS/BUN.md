# BUN

Guidance for Bun as a package manager.

## General
- Use a single JavaScript package manager per repo; do not mix lockfiles.
- `bun install` writes a `bun.lock` lockfile; commit it when lockfiles are tracked.
- Bun does not run dependency lifecycle scripts by default; use `trustedDependencies`
  for packages that require them.
- Bun installs dependencies into `node_modules/`; keep it out of version control.

## Lockfile
- Generate a lockfile without installing to `node_modules`:
  `bun install --lockfile-only`
- Install without creating a lockfile only when explicitly requested:
  `bun install --no-save`
- Bun supports a text `bun.lock` and older binary `bun.lockb`; prefer `bun.lock`
  when available.

## bunfig.toml
- `bunfig.toml` is optional and only needed for Bun-specific settings.
- Add it to the project root when you need project-local configuration.
- Bun also supports `.npmrc`, but migrating to `bunfig.toml` is recommended.

## VCS Ignore Additions
Add these when using Bun (if not already covered by the baseline ignore list):
- `node_modules/`

## .npmrc Handling
- Do not commit `.npmrc` if it contains credentials or auth tokens; add it to `.gitignore`.
- If `.npmrc` is required, commit a sanitized version and consider `.npmrc.example`.
