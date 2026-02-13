# BUN

Guidance for AI agents using Bun as package manager/runtime tooling.

## Scope
- Define Bun-specific dependency/install behavior and compatibility controls.
- Apply this file when Bun is chosen for package management or script runtime.

## Semantic Dependencies
- Inherit baseline Node dependency controls from `BUILD_TOOLS/NPM.md`.
- Inherit security/compliance constraints from
  `SECURITY/SECURITY.md` and `COMPLIANCE/LICENSES.md`.
- This file specializes Bun-specific behavior and caveats.

## Defaults
- Use one package manager per repository; do not mix lockfiles.
- Commit the Bun lockfile for reproducible installs (format/name depends on Bun
  version, for example `bun.lock` or `bun.lockb`).
- Keep Bun version pinned in project/tooling config.
- Validate Bun compatibility with required ecosystem tooling before adoption.

## Install and Lockfile Behavior
- Use deterministic install behavior in CI.
- Prefer `bun install --frozen-lockfile` (or equivalent reproducible mode) for
  release pipelines.
- Use `bun install --lockfile-only` only when intentionally updating lock
  metadata.
- Keep node_modules excluded from VCS.

## Lifecycle Script and Trust Model
- Bun dependency lifecycle script behavior differs from npm ecosystem defaults.
- Configure trusted dependencies explicitly in `bunfig.toml` via
  `trustedDependencies` when lifecycle scripts are required.
- Do not blanket-trust all dependency scripts.
- Validate build/install outcome for packages requiring postinstall steps.

## Compatibility Guardrails
- Verify package manager features needed by monorepo/workspace setup.
- Validate CI images/runners include expected Bun version.
- Keep fallback path documented if Bun compatibility blocks delivery.
- Avoid mixing Bun runtime assumptions into scripts intended for Node-only
  environments without explicit checks.

## Security and Credential Handling
- Never commit registry credentials/tokens.
- Use CI secret injection for registry auth.
- Scan dependencies and lockfile for vulnerabilities.
- Keep trusted dependency policy minimal and auditable.

## VCS Ignore Additions
Add these when using Bun (if not already covered by baseline ignore rules):
- `node_modules/`

## High-Risk Pitfalls
1. Mixing Bun lockfile with npm/pnpm/yarn lockfiles.
2. Assuming npm lifecycle script behavior without validation.
3. Unpinned Bun version leading to CI/local drift.
4. Trusting dependency scripts broadly.
5. Runtime/script incompatibilities hidden until deployment.
6. Registry credentials committed in config files.

## Do / Don't Examples
### 1. Lockfile Discipline
```text
Don't: keep Bun and npm lockfiles together in one repository.
Do:    keep only the Bun lockfile appropriate for the project's pinned Bun
       version.
```

### 2. Script Trust
```text
Don't: trust all postinstall scripts globally.
Do:    explicitly allow trusted dependencies only.
```

### 3. CI Reproducibility
```text
Don't: run floating bun install mode in release CI.
Do:    use frozen lockfile mode with pinned Bun version.
```

## Code Review Checklist for Bun
- Is Bun selected intentionally and consistently?
- Is lockfile policy deterministic and enforced in CI?
- Are lifecycle script trust decisions explicit and minimal?
- Are compatibility constraints tested for required tooling/workspaces?
- Are credentials/tokens excluded from committed config?
- Are dependency security checks integrated?

## Testing Guidance
- Test clean install on CI runner and local dev baseline.
- Test package install behavior for deps requiring scripts.
- Run full build/test workflow under Bun in CI.
- Add regression checks when upgrading Bun major/minor versions.

## Override Notes
- If project must interoperate with npm-specific workflows, document explicit
  compatibility boundaries and keep deterministic lockfile/security controls.
