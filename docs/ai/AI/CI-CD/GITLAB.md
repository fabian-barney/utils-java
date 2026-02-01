# GITLAB

Guidance for GitLab CI/CD usage.

## Defaults
- Keep pipelines fast and deterministic.
- Fail fast on lint, build, and test stages.

## Structure
- Use separate stages for lint, build, test, and deploy.
- Cache dependencies to speed up builds.

## Release Pipelines
- Release pipelines are triggered by git version tags matching `v*` (e.g., `v1.0.0`).
- Release pipelines must run the full build and test suite.
- Release pipelines must run dependency checks and generate vulnerability reports.
- Release pipelines must publish release artifacts and vulnerability reports to the artifactory.
- A git version tag must be sufficient to produce a releasable build.

## Security
- Avoid storing secrets in CI logs.
- Use masked variables and protected branches.

## Quality
- Run static analysis and dependency checks where feasible.
- Publish test reports and artifacts.
