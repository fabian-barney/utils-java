# INFRASTRUCTURE

Infrastructure-layer contract for runtime environment, deployment topology, and
platform operations behavior.

## Role in the Ruleset
- INFRASTRUCTURE docs specialize how applications are packaged, deployed, and
  operated in target environments.
- Infrastructure guidance inherits cross-cutting, language, architecture,
  framework, library, and build-tool constraints before adding
  platform-specific rules.
- Global precedence and override behavior are defined in
  `CORE/RULE_DEPENDENCY_TREE.md`.

## Scope Boundary
INFRASTRUCTURE includes:
- Container/runtime platform configuration and deployment strategies.
- Cluster/service-mesh operational constraints.
- Infrastructure-level reliability, observability, and security posture.

INFRASTRUCTURE does not include:
- Framework lifecycle/state/rendering guidance.
- Library API usage behavior.
- Source-level language coding conventions.
- Build-time dependency/artifact workflow behavior.
- CI/CD delivery-automation behavior.

Those belong in `FRAMEWORK/**`, `LIBRARY/**`, `LANGUAGE/**`,
`BUILD_TOOLS/**`, and `CI-CD/**`.

## Specialization Contract
- Infrastructure child docs may narrow parent guidance where runtime semantics
  require it.
- Infrastructure docs must not weaken inherited security/compliance/test
  constraints without explicit, reviewed rationale.
- CI/CD docs may specialize delivery automation around infrastructure rules but
  must keep this layer authoritative for runtime platform behavior.

## Files
- [DOCKER.md](DOCKER.md) - Containerization guidance.
- [KUBERNETES.md](KUBERNETES.md) - Kubernetes deployment guidance.
- [HELM.md](HELM.md) - Helm chart practices.
- [ISTIO.md](ISTIO.md) - Service mesh guidance.

## Authoring Notes
- Keep this file index-level and boundary-focused.
- Put deep platform behavior in child infrastructure docs.
- When adding a new infrastructure doc, update this index and align semantic
  dependencies in `CORE/RULE_DEPENDENCY_TREE.md`.
