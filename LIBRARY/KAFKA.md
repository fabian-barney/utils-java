# KAFKA

Guidance for AI agents implementing and reviewing Apache Kafka usage.

## Scope
- Define topic, producer, and consumer design constraints for reliable event
  streaming.
- Apply this file to Kafka client integration and operational design.

## Semantic Dependencies
- Inherit event-driven architecture constraints from
  `ARCHITECTURE/EVENT_DRIVEN_ARCHITECTURE.md`.
- Inherit security and observability baselines from
  `SECURITY/SECURITY.md` and `CORE/LOGGING.md`.

## Defaults
- Use clear domain-based topic naming and ownership.
- Keep schema evolution backward-compatible by default.
- Design consumers for at-least-once delivery semantics.
- Keep producer/consumer configs explicit and version-controlled.

## Topic and Schema Rules
- Keep topic partitioning strategy aligned with throughput/order requirements.
- Keep retention/compaction policy intentional.
- Use schema registry/contract governance where available.
- Avoid leaking internal model churn into public event contracts.

## Producer Rules
- Keep key strategy intentional for partition affinity/order semantics.
- Enable idempotent producer settings (`enable.idempotence` with compatible
  `acks`/retry configuration) where retry-duplicate suppression is required.
- Treat producer idempotence as a producer-session guarantee only; handle
  end-to-end deduplication/idempotency at consumer/workflow boundaries.
- Handle send failures with clear retry/error policy.
- Avoid fire-and-forget publishing without observability.

## Consumer Rules
- Keep handlers idempotent and retry-safe.
- Distinguish transient vs permanent processing failures.
- Route poison messages to DLQ with context.
- Keep offset commit strategy aligned with processing semantics.

## Observability and Operations
- Monitor lag, throughput, retry rates, and DLQ volume.
- Track rebalance frequency and consumer health.
- Log processing failures with topic/partition/offset context.
- Alert on sustained lag and retry storms.

## High-Risk Pitfalls
1. Non-idempotent consumers producing duplicate side effects.
2. Incompatible schema changes breaking downstream consumers.
3. Missing key strategy causing hot partitions/order issues.
4. Infinite retries with no DLQ path.
5. Silent producer send failure handling.
6. Weak observability of lag and failure metrics.

## Do / Don't Examples
### 1. Idempotency
```text
Don't: process event twice with duplicate side effects.
Do:    detect duplicate eventId and short-circuit safely.
```

### 2. Schema Evolution
```text
Don't: remove field consumers still use.
Do:    additive changes + deprecation window.
```

### 3. Failure Handling
```text
Don't: retry forever on non-recoverable payload.
Do:    send to DLQ with reason metadata.
```

## Code Review Checklist for Kafka
- Is topic/schema ownership and versioning clear?
- Are producer settings aligned with durability/idempotency needs?
- Are consumers idempotent with safe commit/retry strategy?
- Is DLQ/error handling explicit and bounded?
- Are observability metrics/logs sufficient for operations?
- Are partition/key choices aligned with ordering and scale goals?

## Testing Guidance
- Add integration tests with test Kafka for producer/consumer paths.
- Add schema compatibility tests.
- Add duplicate/retry/DLQ behavior tests.
- Add load/lag tests for throughput-sensitive consumers.

## Override Notes
- Broker/platform-specific settings may vary, but contract compatibility,
  idempotency, and observability constraints here remain mandatory.
