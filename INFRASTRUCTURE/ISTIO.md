# ISTIO

Guidance for AI agents implementing and reviewing Istio service mesh policies.

## Scope
- Define Istio traffic, security, and observability policy defaults.
- Apply this file to VirtualService, DestinationRule, Gateway, and security
  policy design.

## Semantic Dependencies
- Inherit Kubernetes baseline from `INFRASTRUCTURE/KUBERNETES.md`.
- Inherit resilience constraints from `ARCHITECTURE/CIRCUIT_BREAKER.md`.
- Inherit security constraints from `SECURITY/SECURITY.md`.

## Defaults
- Keep traffic policies explicit and versioned.
- Keep retries/timeouts bounded and aligned with service SLOs.
- Use progressive rollout patterns for risky changes.
- Keep policy scope minimal (namespace/service targeted).

## Traffic Management Rules
- Define clear routing intent in VirtualService rules.
- Avoid conflicting route matches and overlapping wildcard policies.
- Coordinate retries, timeouts, and outlier detection to avoid traffic storms.
- Use canary/weighted rollout for high-risk changes.
- Keep gateway ingress exposure tightly controlled.

## Security Rules
- Enforce mTLS according to environment policy.
- Use AuthorizationPolicy with least privilege.
- Keep trust-domain and identity assumptions explicit.
- Avoid permissive policies as long-term defaults.

## Observability and Operations
- Ensure mesh telemetry is enabled and consumed meaningfully.
- Track latency, error rate, retry count, and outlier ejection behavior.
- Alert on policy-induced failures (5xx spikes, route blackholes).
- Keep config changes auditable and rollback-ready.

## High-Risk Pitfalls
1. Overlapping route rules creating nondeterministic behavior.
2. Retry/timeouts misconfiguration causing cascading load.
3. Permissive security policies left in place.
4. Mesh policy drift across namespaces/environments.
5. Canary rollout without objective success/failure gates.
6. Blindly applying global policies to heterogeneous workloads.

## Do / Don't Examples
### 1. Traffic Safety
```text
Don't: global retry policy with high retries and long timeout everywhere.
Do:    per-service tuned retry/timeout aligned with dependency behavior.
```

### 2. Security Policy
```text
Don't: broad allow-all AuthorizationPolicy in production.
Do:    explicit principal/path/method-scoped allow rules.
```

### 3. Rollout Control
```text
Don't: immediate 100% route switch for major backend change.
Do:    weighted canary progression with observability gates.
```

## Code Review Checklist for Istio
- Are traffic rules explicit, non-overlapping, and deterministic?
- Are timeout/retry/circuit policies aligned and bounded?
- Is mTLS/authz policy least-privilege and intentional?
- Is rollout strategy safe and reversible?
- Are telemetry and alerting implications considered?
- Is policy scope targeted to intended workloads only?

## Testing Guidance
- Validate manifest syntax/schema and policy lint in CI.
- Run staged canary tests with rollback drills.
- Test authz and mTLS enforcement paths.
- Test resilience behavior under dependency failure/latency injection.
- Monitor mesh metrics during rollout validation.

## Override Notes
- Platform governance may enforce stricter mesh controls, but explicit traffic
  safety, least privilege, and observability constraints here remain mandatory.
