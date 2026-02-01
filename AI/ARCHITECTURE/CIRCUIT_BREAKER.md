# CIRCUIT_BREAKER

Guidance for circuit breaker usage.

## Purpose
- Protect services from cascading failures when downstream systems are unhealthy.
- Fail fast to preserve resources and improve overall stability.

## Usage
- Use circuit breakers on network calls and external dependencies.
- Configure sensible timeouts and failure thresholds.
- Use fallback behavior only when it preserves correctness.

## States
- Closed: normal operation.
- Open: block calls after failures to allow recovery.
- Half-open: probe for recovery with limited traffic.

## Monitoring
- Track open/close events and failure rates.
- Alert on sustained open states.
