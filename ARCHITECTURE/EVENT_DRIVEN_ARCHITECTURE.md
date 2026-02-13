# EVENT_DRIVEN_ARCHITECTURE

Guidance for AI agents implementing and reviewing event-driven systems.

## Scope
- Define event-driven architecture constraints for reliability, consistency, and
  operability.
- Apply this file to producers, consumers, and event contract evolution.

## Semantic Dependencies
- Inherit architecture baseline from `ARCHITECTURE/ARCHITECTURE.md` and
  `ARCHITECTURE/CLEAN_ARCHITECTURE.md`.
- Inherit resilience constraints from `ARCHITECTURE/CIRCUIT_BREAKER.md`.
- Inherit cross-cutting constraints from
  `SECURITY/SECURITY.md`, `TEST/TEST.md`, `CORE/LOGGING.md`.

## Event Contract Design
- Model events as facts about completed domain actions.
- Use stable, domain-driven event names.
- Keep payloads explicit and minimal; avoid leaking internal persistence shape.
- Include metadata for tracing and diagnostics
  (`eventId`, `occurredAt`, `producer`, `correlationId`, `traceId`).
- Keep contracts versioned and backward-compatible by default.

## Delivery and Processing Semantics
- Assume at-least-once delivery unless infrastructure guarantees a stronger
  delivery model.
- Make handlers idempotent.
- Define deduplication strategy for repeated deliveries.
- Treat ordering guarantees as explicit contracts, not assumptions.
- Avoid hard dependency on global ordering unless required and documented.

## Reliability Patterns
- Use retries for transient failures with backoff/jitter.
- Route poison messages to dead-letter queues/topics.
- Keep retry limits bounded and observable.
- Separate business rejection from technical retryable failure.

## Consistency and Workflow Design
- Use eventual consistency intentionally for cross-boundary workflows.
- Document consistency expectations and SLA (propagation delays, compensation).
- Use sagas/compensations for multi-step distributed workflows.
- Avoid hidden coupling via undocumented event dependencies.

## Security and Governance
- Validate event payloads at producer and consumer boundaries.
- Do not include secrets in event payloads.
- Enforce producer/consumer access controls per topic/stream.
- Keep schema registry or equivalent governance controls where available.

## Observability
- Correlate event flow with `traceId`/`correlationId`.
- Monitor throughput, lag, retry count, DLQ volume, and processing latency.
- Log consumer failures with event identity and attempt metadata.
- Alert on sustained DLQ growth and lag thresholds.

## High-Risk Pitfalls
1. Non-idempotent consumers producing duplicate side effects.
2. Breaking payload changes without compatibility strategy.
3. Infinite retry loops with no DLQ escape.
4. Assuming ordering guarantees not provided by broker topology.
5. Hidden synchronous dependencies in supposedly async flow.
6. Missing lag/DLQ monitoring until production incidents occur.
7. Sensitive data leakage in event payloads.

## Do / Don't Examples
### 1. Idempotency
```text
Don't: apply payment on every delivery without dedupe check.
Do:    track processed eventId and short-circuit duplicates.
```

### 2. Version Evolution
```text
Don't: remove payload field consumed by old consumers immediately.
Do:    introduce additive field, deprecate old field, migrate consumers.
```

### 3. Retry/DLQ
```text
Don't: retry forever on non-recoverable validation failure.
Do:    send invalid message to DLQ with failure reason metadata.
```

## Code Review Checklist for Event-Driven Systems
- Are event contracts domain-oriented, explicit, and version-safe?
- Are handlers idempotent under duplicate delivery?
- Are retry and DLQ policies explicit and bounded?
- Are ordering assumptions documented and valid?
- Is consistency model explicit (eventual vs immediate guarantees)?
- Are payload validation and access controls enforced?
- Are observability signals sufficient for operations?

## Testing Guidance
- Add contract tests for event schema compatibility.
- Add idempotency tests with duplicate deliveries.
- Add retry/DLQ behavior tests for transient and permanent failures.
- Add integration tests for end-to-end event flow and eventual consistency.
- Add load/lag tests for throughput-sensitive consumers.

## Override Notes
- Broker-specific library docs may change implementation details, but idempotency,
  compatibility, retry safety, and observability constraints here remain
  mandatory.
