# CIRCUIT_BREAKER

Guidance for AI agents implementing and reviewing circuit breaker patterns.

## Scope
- Define when and how to apply circuit breakers to remote dependencies.
- Apply this file to service-to-service calls and external IO boundaries.

## Semantic Dependencies
- Inherit architecture baseline from `ARCHITECTURE/ARCHITECTURE.md`.
- Inherit cross-cutting defaults from
  `SECURITY/SECURITY.md`, `TEST/TEST.md`, `CORE/LOGGING.md`.
- `ARCHITECTURE/MICROSERVICE.md` and
  `ARCHITECTURE/EVENT_DRIVEN_ARCHITECTURE.md` may specialize breaker usage for
  their communication patterns.

## Placement Rules
- Place circuit breakers at remote dependency boundaries (HTTP, gRPC, DB,
  message broker clients as appropriate).
- Do not apply circuit breakers to in-memory pure operations.
- Keep breaker scope aligned with dependency blast radius.

## Configuration Defaults
- Configure timeout, failure-rate threshold, sliding window size, and half-open
  probe behavior explicitly.
- Keep retry policy coordinated with breaker settings to avoid storm loops.
- Use per-dependency configuration; avoid one global profile for all
  integrations.
- Keep defaults conservative and tune from production telemetry.

## Fallback Strategy
- Use fallback only when correctness is preserved.
- Prefer explicit degraded mode responses over silent stale/incorrect data.
- Avoid fallback chains that hide systemic failures.
- Record fallback activation as observable event.

## State Semantics
- Closed: normal operation.
- Open: short-circuit calls for cooldown period.
- Half-open: allow limited probes to test recovery.
- Keep transitions observable in logs/metrics/events.

## Observability Requirements
- Emit metrics for call outcomes, breaker state transitions, and short-circuit
  counts.
- Log contextual events on open/close transitions with dependency identity.
- Alert on sustained open state and high fallback rates.
- Track user-facing degradation tied to breaker events.

## High-Risk Pitfalls
1. Circuit breaker applied too broadly, masking healthy dependencies.
2. Misaligned retry + breaker causing traffic amplification.
3. Fallbacks returning incorrect business data.
4. Missing timeout configuration rendering breaker ineffective.
5. No monitoring for open-state duration.
6. Shared breaker instance for unrelated dependencies.

## Do / Don't Examples
### 1. Boundary Placement
```text
Don't: wrap internal pure function with circuit breaker.
Do:    wrap external payment API client boundary.
```

### 2. Fallback Correctness
```text
Don't: on failure, return fabricated "success" response.
Do:    return explicit degraded/unavailable outcome with traceable code.
```

### 3. Retry Coordination
```text
Don't: high retry count + short timeout + tight loop.
Do:    bounded retries with jitter and breaker-aware policy.
```

## Code Review Checklist for Circuit Breakers
- Is breaker applied only to true remote dependency boundaries?
- Are timeout/threshold/window settings explicit and justified?
- Are retries coordinated to avoid amplification?
- Are fallback behaviors correctness-preserving and explicit?
- Are state transitions observable via logs/metrics/alerts?
- Is breaker scope isolated per dependency/domain risk?

## Testing Guidance
- Add tests for open/half-open/closed transitions.
- Add chaos/failure-injection tests for downstream outage behavior.
- Test fallback correctness and user-visible degradation semantics.
- Test retry-breaker interaction under sustained failure.
- Test recovery behavior after dependency returns healthy.

## Override Notes
- Library/framework docs may prescribe implementation API (for example
  Resilience4j annotations), but placement, correctness, and observability
  constraints in this file remain mandatory.
