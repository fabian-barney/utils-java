# MICROSERVICE

Guidance for microservice architectures.

## Principles
- Prefer modular monoliths unless clear scaling or autonomy needs exist.
- Define service boundaries around business capabilities.
- Keep services independently deployable.

## Communication
- Prefer asynchronous communication for decoupling when appropriate.
- Use synchronous calls only when immediate consistency is required.
- Keep APIs stable and versioned.

## Data Ownership
- Each service owns its data; avoid shared databases.
- Use events for cross-service data propagation.

## Reliability
- Use timeouts, retries, and circuit breakers for service calls.
- Design for partial failure and graceful degradation.

## Testing
- Use contract tests for service boundaries.
- Keep end-to-end tests limited and focused.
