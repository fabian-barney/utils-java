# NPM

Guidance for Node.js package managers (npm, Yarn, pnpm).

## General
- Use an LTS Node version and document it (for example via `engines` or `.nvmrc`).
- Use one package manager consistently and avoid mixing lockfiles.
- Follow repository policy on whether lockfiles are committed.

## VCS Ignore Additions
Add these when using Node tooling (if not already covered by the baseline ignore list):
- `node_modules/`
- `npm-debug.log*`, `yarn-debug.log*`, `yarn-error.log*`, `pnpm-debug.log*`
- `.npm/`, `.yarn/`, `.pnpm-store/`
- `.pnp.*` (Yarn Plug'n'Play)

## .npmrc Handling
- Do not commit `.npmrc` if it contains credentials or auth tokens; add it to `.gitignore`.
- If `.npmrc` is required, commit a sanitized version (no secrets) and consider a `.npmrc.example`.
