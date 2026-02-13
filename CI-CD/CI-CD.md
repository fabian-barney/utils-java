# CI-CD

CI/CD-layer contract for automation of build, test, verification, and delivery
pipelines.

## Role in the Ruleset
- CI/CD docs specialize how quality gates and delivery workflows are automated
  across environments.
- CI/CD guidance inherits cross-cutting, language, build-tool, and
  infrastructure constraints before adding pipeline-specific rules.
- Global precedence and override behavior are defined in
  `CORE/RULE_DEPENDENCY_TREE.md`.

## Scope Boundary
CI/CD includes:
- Pipeline stage structure and gating strategy.
- Artifact/report publishing and release automation constraints.
- CI secret handling and pipeline-level observability expectations.

CI/CD does not include:
- Source-level language/framework/library coding rules.
- Deep dependency manager behavior details.
- Runtime platform deployment semantics.

Those belong in `LANGUAGE/**`, `FRAMEWORK/**`, `LIBRARY/**`,
`BUILD_TOOLS/**`, and `INFRASTRUCTURE/**`.

## Specialization Contract
- Provider-specific CI/CD docs may narrow parent CI/CD guidance where platform
  semantics require it.
- CI/CD docs must not weaken inherited security/compliance/test constraints
  without explicit, reviewed rationale.
- Build-tool and infrastructure docs remain authoritative for their own layers;
  CI/CD docs should orchestrate them rather than redefine them.

## Files
- [GITLAB.md](GITLAB.md) - GitLab CI/CD pipeline guidance.

## Authoring Notes
- Keep this file index-level and boundary-focused.
- Put deep pipeline implementation behavior in provider-specific child docs.
- When adding a CI/CD provider doc, update this index and align semantic
  dependencies in `CORE/RULE_DEPENDENCY_TREE.md`.
