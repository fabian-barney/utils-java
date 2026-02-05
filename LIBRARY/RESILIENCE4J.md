# RESILIENCE4J

Guidance for Resilience4j usage.

## Defaults
- Use Resilience4j for resilience patterns (circuit breaker, retry, bulkhead,
  rate limiter, time limiter).
- Prefer configuration via properties for consistency across services.

## Circuit Breaker
- Use sensible failure thresholds and sliding windows.
- Avoid opening the breaker for business exceptions that are expected.

## Retry
- Retry only for transient failures.
- Use exponential backoff with jitter.

## Bulkhead and Rate Limiting
- Isolate critical resources with bulkheads.
- Use rate limiting to protect downstreams.

## Observability
- Export metrics and monitor open/half-open states.
- Alert on sustained failure conditions.
