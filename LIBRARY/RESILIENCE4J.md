# RESILIENCE4J

Guidance for AI agents implementing and reviewing Resilience4j policies.

## Scope
- Define safe usage of resilience primitives for remote dependency protection.
- Apply this file to retry/circuit-breaker/bulkhead/rate-limiter/time-limiter
  configuration and usage.

## Semantic Dependencies
- Inherit architecture resilience constraints from
  `ARCHITECTURE/CIRCUIT_BREAKER.md` and `ARCHITECTURE/MICROSERVICE.md`.
- Inherit observability requirements from `CORE/LOGGING.md`.

## Defaults
- Keep resilience policy per dependency/use case, not one global profile.
- Keep policies explicit and externally configurable where practical.
- Start conservative and tune using production telemetry.
- Keep fallback behavior correctness-preserving.

## Policy Composition Rules
- Retry only transient failures.
- Circuit breaker for failing/slow remote dependencies.
- Time limiter for bounded response latency.
- Bulkhead for resource isolation.
- Rate limiter for downstream protection and fair usage.
- Coordinate policies to avoid amplification loops.

## Configuration Guardrails
- Keep retry attempts bounded with backoff + jitter.
- Avoid retrying non-retryable business exceptions.
- Keep circuit-breaker thresholds/window sizes aligned with traffic profile.
- Keep bulkhead pool/queue settings aligned with capacity planning.
- Keep rate-limit and timeout values documented per dependency SLA.

## Observability and Operations
- Export metrics for policy activations and outcomes.
- Log state transitions and fallback activations with dependency context.
- Alert on sustained breaker open states, retry storms, and bulkhead rejection
  spikes.

## High-Risk Pitfalls
1. Retrying non-idempotent operations.
2. Retry + short timeout causing request storms.
3. Fallbacks returning incorrect business outcomes.
4. One shared policy for heterogeneous dependencies.
5. Missing metrics/alerts for policy behavior.
6. Swallowing failures behind broad fallback defaults.

## Do / Don't Examples
### 1. Retry Scope
```text
Don't: retry validation errors or permanent 4xx responses.
Do:    retry transient network/timeouts with bounded attempts.
```

### 2. Fallback Correctness
```text
Don't: return synthetic success on payment failure.
Do:    return explicit degraded/unavailable outcome.
```

### 3. Policy Tuning
```text
Don't: copy same thresholds to every dependency.
Do:    tune per dependency latency/error profile.
```

## Code Review Checklist for Resilience4j
- Are resilience primitives selected appropriately per failure mode?
- Are retry policies bounded and idempotency-safe?
- Are fallback paths correctness-preserving?
- Are timeout/circuit/bulkhead/rate policies coordinated?
- Are metrics/logging/alerts sufficient for operation?
- Is policy scope dependency-specific and documented?

## Testing Guidance
- Add failure-injection tests for downstream outages and latency spikes.
- Test retry idempotency safety.
- Test circuit-breaker state transitions.
- Test bulkhead saturation/rejection behavior.
- Test fallback outcomes for correctness.

## Override Notes
- Framework integrations may vary (annotations, decorators, functional APIs),
  but policy correctness and observability constraints here remain mandatory.
