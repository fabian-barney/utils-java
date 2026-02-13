# NPM

Guidance for AI agents managing Node.js dependencies (npm, Yarn, pnpm).

## Scope
- Define reproducible dependency-management and script-execution rules.
- Apply this file to Node package manager configuration and CI integration.

## Semantic Dependencies
- Inherit security constraints from `SECURITY/SECURITY.md`.
- Inherit compliance constraints from `COMPLIANCE/LICENSES.md`.
- Inherit CI pipeline constraints from `CI-CD/CI-CD.md`.
- Tool-specific alternatives (for example Bun) may specialize behavior but must
  preserve reproducibility/supply-chain controls.

## Defaults
- Use one package manager per repository.
- Keep a single authoritative lockfile committed.
- Pin Node runtime version (`.nvmrc`, `engines`, toolchain config).
- Use deterministic install mode in CI (`npm ci`, `pnpm install --frozen-lockfile`,
  etc.).
- Keep scripts explicit and side-effect aware.

## Dependency Policy
- Prefer stable, maintained packages with compatible licenses.
- Minimize runtime dependencies; avoid redundant utility overlap.
- Pin major versions deliberately; upgrade with changelog review.
- Use overrides/resolutions intentionally and document rationale.

## Script and Workspace Rules
- Keep package scripts composable and predictable.
- Avoid scripts that depend on implicit global tools.
- For monorepos, keep workspace boundaries explicit.
- Avoid cross-package script coupling without clear contracts.

## Supply-Chain and Security Guardrails
- Do not commit auth tokens in `.npmrc`.
- Use secret-injection in CI for registry credentials.
- Enable dependency vulnerability scanning in CI.
- Verify integrity/signature mechanisms where supported.
- Avoid automatic postinstall script trust for unknown packages.

## Performance and Reliability
- Use caching in CI keyed by lockfile and runtime version.
- Avoid unnecessary reinstall churn.
- Keep node_modules out of VCS.
- Keep install/build logs actionable and fail fast on dependency errors.

## VCS Ignore Additions
Add these when using Node tooling (if not already in baseline ignore list):
- `node_modules/`
- `npm-debug.log*`, `yarn-debug.log*`, `yarn-error.log*`, `pnpm-debug.log*`
- `.npm/`, `.yarn/`, `.pnpm-store/`
- `.pnp.*` (Yarn Plug'n'Play)

## High-Risk Pitfalls
1. Mixing lockfiles/package managers in one repository.
2. Installing with non-frozen mode in CI and drifting dependencies.
3. Committing registry tokens in `.npmrc`.
4. Unreviewed dependency upgrades causing runtime regressions.
5. Hidden script side effects across workspace packages.
6. Ignoring transitive license/security risk.

## Do / Don't Examples
### 1. Reproducible CI
```text
Don't: npm install in CI for release builds.
Do:    npm ci with committed lockfile.
```

### 2. Package Manager Consistency
```text
Don't: package-lock.json and pnpm-lock.yaml in same repo.
Do:    choose one manager and enforce one lockfile.
```

### 3. Credential Safety
```text
Don't: commit .npmrc with auth token.
Do:    inject registry auth at CI runtime only.
```

## Code Review Checklist for Node Package Management
- Is exactly one package manager/lockfile used?
- Is runtime version pinned and CI install deterministic?
- Are dependency additions justified and license/security-reviewed?
- Are scripts explicit and side-effect aware?
- Are registry credentials kept out of source control?
- Are workspace boundaries and dependency graph changes intentional?

## Testing Guidance
- Run install in clean environment to validate lockfile determinism.
- Run vulnerability/license checks in CI.
- Run relevant build/test scripts from fresh install.
- Test workspace script execution for changed packages.

## Override Notes
- `BUILD_TOOLS/BUN.md` may specialize Bun behavior, but lockfile discipline,
  deterministic installs, and secret/supply-chain safety here remain mandatory.
