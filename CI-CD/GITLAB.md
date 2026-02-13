# GITLAB

Guidance for AI agents implementing and reviewing GitLab CI/CD pipelines.

## Scope
- Define GitLab pipeline design and quality-gate constraints.
- Apply this file to `.gitlab-ci.yml`, reusable templates, and release jobs.

## Semantic Dependencies
- Inherit CI/CD baseline from `CI-CD/CI-CD.md`.
- Inherit build/security/testing constraints from
  `BUILD_TOOLS/**`, `SECURITY/SECURITY.md`, and `TEST/TEST.md`.
- Inherit VCS workflow requirements from `CORE/VERSION_CONTROL_SYSTEM.md`.

## Pipeline Defaults
- Keep pipelines deterministic and fast-fail.
- Separate stages clearly (lint, build, test, security, package, deploy).
- Keep job boundaries explicit and cache/artifact strategy intentional.
- Keep pipeline behavior branch/tag-aware with explicit rules.

## Quality Gates
- Treat lint, tests, and security scans as merge/release gates.
- Keep required gates non-optional for protected branches.
- Publish test and coverage reports for review visibility.
- Fail pipeline on critical dependency/security findings per policy.

## Release Pipeline Rules
- Release pipelines are triggered by semantic version tags
  (for example `vMAJOR.MINOR.PATCH`).
- Release pipelines must run full build/test/security checks.
- Release artifacts and reports must be reproducible from tag alone.
- Keep release jobs immutable and auditable.
- Keep rollback and re-run strategy documented.

## Secrets and Security
- Use masked/protected CI variables for credentials.
- Do not echo secrets in job logs.
- Restrict deployment jobs to protected branches/tags and required approvals.
- Pin container images used by CI jobs where possible.

## Caching and Artifacts
- Use caches for dependency acceleration with safe keys
  (lockfiles/runtime versions).
- Use artifacts for reproducible stage handoff, not implicit workspace state.
- Keep artifact retention policy explicit and cost-aware.

## Observability and Debuggability
- Keep job logs actionable and concise.
- Emit clear failure context (what failed, where, likely next action).
- Track pipeline duration/flakiness trends.
- Keep flaky tests quarantined and actively remediated.

## High-Risk Pitfalls
1. Optional quality gates on protected branches.
2. Non-deterministic build/install in CI.
3. Secret leakage through logs or committed CI config.
4. Release tags bypassing full validation pipeline.
5. Fragile cache keys causing stale/corrupt build state.
6. Overly broad job rules running deploy jobs unexpectedly.

## Do / Don't Examples
### 1. Deterministic Install
```text
Don't: floating dependency install in release jobs.
Do:    use lockfile-frozen install mode.
```

### 2. Secret Handling
```text
Don't: echo $DEPLOY_TOKEN in script output.
Do:    keep token masked and pass only to required command flags/env.
```

### 3. Release Safety
```text
Don't: release job runs without tests/security stage dependencies.
Do:    enforce full gate chain before publish/deploy.
```

## Code Review Checklist for GitLab CI
- Are stages/gates explicit and complete?
- Are merge/release quality gates enforced?
- Are branch/tag rules and protected-job constraints correct?
- Are secrets managed via masked/protected variables?
- Are cache/artifact strategies deterministic and safe?
- Is release reproducibility from tag alone guaranteed?

## Testing Guidance
- Lint CI configuration in CI itself.
- Run pipeline dry-runs/validation on MR changes.
- Test release pipeline on pre-release tags in staging.
- Test failure scenarios (missing secrets, failing tests, vulnerability gates).
- Track and reduce flaky job/test incidence.

## Override Notes
- Project-specific delivery policies may add stricter approvals/compliance gates,
  but deterministic quality-gated pipelines and secret hygiene remain mandatory.
