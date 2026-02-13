# LOGGING

Guidance for AI agents implementing and reviewing logging behavior.

## Scope
- Define logging rules that maximize debuggability without leaking sensitive
  data or creating observability cost explosions.
- Apply this guidance when generating code, refactoring logging paths, and
  reviewing changes that alter emitted logs.

## Semantic Dependencies
- Inherit security constraints from `SECURITY/SECURITY.md`.
- Inherit testing expectations from `TEST/TEST.md`.
- Inherit naming/readability expectations from `LANGUAGE/CONVENTIONS.md` and
  `LANGUAGE/READABILITY.md`.
- If a framework/library doc adds stricter logging behavior, follow the
  specialized rule and keep this baseline as minimum.

## Defaults
- Use structured logs (JSON or stable key-value pairs), not free-form strings.
- Emit one log entry per meaningful event; avoid duplicate logs for the same
  failure path.
- Always include stable context keys:
  `timestamp`, `level`, `service`, `component`, `operation`, `traceId`
  (or equivalent correlation key).
- Keep message text concise and action-oriented; put variable context in fields.
- Keep logging side-effect free: logging must not change business behavior.

## Log Level Policy
- `TRACE`: high-frequency diagnostic details only for short-lived debugging.
- `DEBUG`: developer diagnostics that are safe but too noisy for production
  defaults.
- `INFO`: meaningful state transitions and successful business-relevant events.
- `WARN`: degraded or unexpected states where execution can continue.
- `ERROR`: failed operations requiring investigation or user-visible fallback.
- `FATAL` (if supported): unrecoverable startup/runtime failure before process
  termination.

Level selection rules:
- Do not log expected domain outcomes as `ERROR` (for example validation
  failures caused by user input).
- Do not log the same exception at multiple layers unless each layer adds
  distinct value.
- If an exception is rethrown unchanged, log it at one boundary only.

## Event Design Rules
- Prefer event names that describe domain intent (`invoice.paid`,
  `auth.login.failed`) over technical noise.
- Keep key names stable over time; treat field renames as compatibility
  changes for dashboards/alerts.
- Include explicit outcome fields (`status`, `result`, `errorCode`) to support
  machine-readable alerting.
- For high-volume events, include sampling metadata (`sampleRate`,
  `sampled=true/false`).

## Sensitive Data and Privacy Guardrails
- Never log secrets: passwords, API keys, tokens, private keys, session
  secrets, connection strings with credentials.
- Do not log full personal data payloads; log minimal identifiers only when
  needed for supportability and allowed by policy.
- Redact or hash sensitive identifiers before emission.
- If uncertain whether data is sensitive, treat it as sensitive and redact.
- Review logging in error handlers carefully; stack traces often contain
  sensitive request payload fragments.

## Cardinality and Volume Control
- Avoid unbounded/high-cardinality fields in indexed logs:
  raw user input, full URLs with query strings, full stack traces as labels,
  random IDs as metric labels.
- Put high-cardinality details in non-indexed payload fields when required.
- Apply rate limiting or sampling for repetitive failures and noisy warnings.
- Never log inside tight loops at `INFO`/`WARN`/`ERROR` without throttling.
- Ensure retries do not multiply identical log storms.

## Error Logging and Exception Boundaries
- Log enough context to reproduce failure:
  operation name, stable identifiers, dependency target, timeout/retry status.
- Preserve original exception cause chains when wrapping exceptions.
- Distinguish transient from permanent failures in fields
  (`retryable=true/false`).
- For async/background processing, include job/task identifiers and attempt
  numbers.

## Correlation and Distributed Tracing
- Propagate and log correlation IDs across sync and async boundaries.
- Align logging context with tracing context (`traceId`, `spanId`) where
  available.
- For message-driven systems, include message key/id and consumer group context.
- Ensure correlation context survives thread switching/reactive pipelines.

## Operational Reliability
- Logging failures must never crash core request handling.
- Use async/non-blocking appenders carefully; define backpressure/drop policy.
- Prefer bounded queues over unbounded memory growth in logging pipelines.
- Validate log formatter behavior under malformed payloads.

## High-Risk Pitfalls
1. Logging secrets or raw personal payloads.
2. Logging the same failure repeatedly at multiple layers.
3. High-cardinality fields causing indexing/storage cost spikes.
4. Missing correlation IDs across service boundaries.
5. Logging side effects that alter business logic timing/outcomes.
6. Silent logger initialization failure with no fallback behavior.
7. Catch-and-log-without-rethrow where callers need failure semantics.

## Do / Don't Examples
```text
# Don't: duplicate exception logging across layers.
controller: ERROR "save failed" ex=...
service:    ERROR "save failed" ex=...
repository: ERROR "save failed" ex=...

# Do: log once at the handling boundary with context.
controller: ERROR "invoice save failed" invoiceId=... traceId=... ex=...
```

```jsonc
// Don't: free-form string with sensitive payload.
{"level":"ERROR","message":"login failed for user=alice password=secret"}

// Do: structured, redacted, machine-readable fields.
{"level":"WARN","event":"auth.login.failed","userIdHash":"...",
 "reason":"invalid_credentials","traceId":"..."}
```

## Code Review Checklist for Logging
- Is structured logging used consistently with stable keys?
- Are `ERROR` logs reserved for real failures, not expected user behavior?
- Are secrets/PII excluded or redacted in all success and error paths?
- Is correlation context (`traceId`/`requestId`) present at boundaries?
- Are duplicate logs for the same exception path avoided?
- Are high-cardinality fields avoided in indexed labels/tags?
- Are retry loops and hot paths protected from log storms?
- Does logging remain non-blocking and failure-tolerant?
- Are event names/messages actionable for operators?

## Testing Guidance for Logging Behavior
- Test sensitive-data redaction paths explicitly.
- Test representative error paths to ensure one boundary log, not many.
- Test correlation propagation across async/reactive/message boundaries.
- Test sampling/rate-limit behavior for repeated failures.
- Test malformed payload handling in log formatting.
- If alerting depends on fields, test field presence and schema stability.

## Override Notes
- Framework/library docs may require additional fields or stricter logging
  behavior (for example HTTP-specific request IDs, Kafka offsets).
- Such specializations may narrow this baseline but must not weaken
  confidentiality or reliability constraints defined here.
