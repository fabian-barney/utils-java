# EVENT_DRIVEN_ARCHITECTURE

Guidance for event-driven architecture (EDA).

## Principles
- Use events to decouple producers and consumers.
- Model events as facts about something that happened.
- Prefer immutable event payloads.

## Event Design
- Use clear, domain-based event names.
- Version events with backward-compatible evolution.
- Avoid leaking internal domain models directly into events.

## Reliability
- Design for at-least-once delivery; ensure handlers are idempotent.
- Handle retries and dead-letter paths for failures.
- Monitor consumer lag and failure rates.

## Boundaries
- Keep event-driven flows for cross-boundary integration.
- Avoid using events as a replacement for direct, synchronous queries when
  consistency or immediate feedback is required.

## Testing
- Use integration tests for end-to-end event flows.
