# KUBERNETES

Guidance for AI agents implementing and reviewing Kubernetes manifests.

## Scope
- Define Kubernetes workload, deployment, and runtime safety defaults.
- Apply this file to manifests, controllers, and cluster deployment reviews.

## Semantic Dependencies
- Inherit container baseline from `INFRASTRUCTURE/DOCKER.md`.
- Inherit YAML safety rules from `LANGUAGE/YAML/YAML.md`.
- Inherit security constraints from `SECURITY/SECURITY.md`.

## Defaults
- Define `requests` and `limits` for CPU/memory explicitly.
- Configure `readinessProbe` and `livenessProbe` meaningfully.
- Keep deployments declarative and idempotent.
- Keep labels/annotations consistent and queryable.
- Use namespaces and RBAC intentionally.

## Workload and Rollout Rules
- Prefer rolling updates with controlled surge/unavailable values.
- Keep replica counts and autoscaling policy explicit.
- Avoid mutable image tags (`latest`) in production.
- Use `PodDisruptionBudget` for critical services.
- Keep rollout/rollback strategy documented for critical apps.

## Security Baseline
- Run containers as non-root where possible.
- Use explicit `securityContext` fields (`runAsNonRoot`,
  `readOnlyRootFilesystem`, `allowPrivilegeEscalation`,
  `capabilities`, `seccompProfile`) as applicable.
- Set safe values intentionally (for example
  `allowPrivilegeEscalation: false`, drop unnecessary capabilities).
- Apply least privilege RBAC for service accounts.
- Keep secrets in dedicated secret resources, not plain config maps.
- Restrict network reachability with network policies where applicable.

## Configuration and Secrets
- Separate config by environment with controlled overlays.
- Keep config maps/secrets versioned and auditable.
- Avoid coupling runtime behavior to undocumented env vars.

## Observability and Operations
- Expose metrics/logging in a platform-compatible way.
- Track pod restarts, crash loops, and probe failures.
- Monitor resource saturation and eviction risk.
- Keep alerting tied to service SLO indicators.

## High-Risk Pitfalls
1. Missing resource requests causing noisy-neighbor instability.
2. Misconfigured probes causing restart loops.
3. Over-privileged service accounts and broad RBAC roles.
4. Mutable image tags causing unpredictable deployments.
5. Secret leakage via plain env/config or logs.
6. Rollout strategy that can drop all replicas.

## Do / Don't Examples
### 1. Resource Governance
```text
Don't: omit resources section.
Do:    define cpu/memory requests and limits per workload.
```

### 2. Image Pinning
```text
Don't: image: my-service:latest
Do:    image: my-service:v1.4.2 (or digest pin)
```

### 3. Probe Semantics
```text
Don't: liveness probe that fails during normal warm-up.
Do:    tune startup/readiness/liveness thresholds to app behavior.
```

## Code Review Checklist for Kubernetes
- Are resources/replicas/probes explicitly configured and sane?
- Is rollout strategy safe for availability goals?
- Are security contexts and RBAC least-privilege?
- Are images pinned and pull policy intentional?
- Are secrets/config separated and safely handled?
- Are observability and failure signals sufficient for operations?

## Testing Guidance
- Validate manifests with schema/policy checks in CI.
- Run server-side dry-run and diff checks before deployment
  (for example `kubectl apply --dry-run=server`, `kubectl diff`).
- Test rollout and rollback behavior in staging.
- Test probe behavior under startup/slow dependency conditions.
- Test autoscaling/resource behavior under representative load.

## Override Notes
- Helm/Istio docs may define additional layer-specific constraints, but
  Kubernetes runtime safety and security controls here remain mandatory.
