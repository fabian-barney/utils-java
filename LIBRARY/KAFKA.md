# KAFKA

Guidance for Apache Kafka usage.

## Defaults
- Treat Kafka as an append-only log and event backbone.
- Prefer clear topic naming with ownership and domain context.

## Topics and Schemas
- Version message schemas; prefer backward-compatible evolution.
- Keep payloads stable and documented.
- Avoid leaking internal domain models directly into messages.

## Consumers and Producers
- Keep producers idempotent where possible.
- Handle retries and dead-letter queues for poison messages.
- Ensure consumer groups are monitored for lag.

## Reliability
- Define retention, partitions, and replication based on SLA.
- Use exactly-once semantics only when required and well-tested.

## Testing
- Use integration tests for producers/consumers with embedded or test Kafka.
