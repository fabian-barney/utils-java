# CQRS

Guidance for AI agents implementing and reviewing Command Query
Responsibility Segregation (CQRS) architectures.

## Scope
- Define rules for separating write behavior (commands) and read behavior
  (queries) when domain complexity or scale justifies it.
- Apply this file when introducing or reviewing CQRS-style service/module
  boundaries.

## Semantic Dependencies
- Inherit architecture baseline from `ARCHITECTURE/ARCHITECTURE.md` and
  `ARCHITECTURE/CLEAN_ARCHITECTURE.md`.
- Inherit event integration constraints from
  `ARCHITECTURE/EVENT_DRIVEN_ARCHITECTURE.md` when projections are event-fed.
- Inherit cross-cutting constraints from
  `SECURITY/SECURITY.md`, `TEST/TEST.md`, `CORE/LOGGING.md`.

## CQRS Decision Rules
- Use CQRS only when command and query concerns have materially different
  scaling, consistency, or model complexity.
- Do not apply CQRS by default for simple CRUD domains.
- Document why separation is needed before introducing additional moving parts.

## Command-Side Rules
- Commands represent intent to change state and enforce domain invariants.
- Keep command handlers transactional and explicit about side effects.
- Validate authorization and invariants before state mutation.
- Ensure command handling is idempotent for retry-prone paths.

## Query-Side Rules
- Queries must be read-only and must not mutate domain state.
- Optimize read models for consumer needs; do not force write-model shape on
  read use cases.
- Keep query contracts explicit and stable.
- Make staleness expectations explicit when using eventually consistent reads.

## Consistency and Projection Rules
- Treat eventual consistency as an explicit product contract.
- Define projection update strategy and recovery behavior (replay/rebuild).
- Keep projection handlers idempotent and replay-safe.
- Track projection lag and expose operational signals for stale reads.

## Reliability and Operability
- Use correlation IDs to trace command-to-projection flow.
- Bound retries and route poison events/messages appropriately.
- Emit structured logs for command execution and projection failures.
- Alert on sustained projection lag, retry storms, and replay failures.

## High-Risk Pitfalls
1. Adopting CQRS without concrete scaling/complexity need.
2. Command handlers bypassing invariants for convenience.
3. Query handlers mutating state or triggering hidden side effects.
4. Undocumented eventual consistency leading to UX/data confusion.
5. Non-idempotent projection handlers causing duplicate read-state effects.
6. Missing replay/rebuild plan for corrupted read models.
7. No observability for lag and projection failure modes.

## Do / Don't Examples
### 1. Read/Write Separation
```text
Don't: one handler both updates payment status and returns dashboard data.
Do:    command handler updates state; separate query endpoint serves dashboard.
```

### 2. Consistency Contract
```text
Don't: claim immediate consistency when read model is async projected.
Do:    document eventual consistency and expose "lastUpdatedAt"/lag indicators.
```

### 3. Projection Idempotency
```text
Don't: increment counters on every duplicate event delivery.
Do:    dedupe by eventId/version and apply projection updates once.
```

## Code Review Checklist for CQRS
- Is CQRS justified with concrete domain/scaling reasons?
- Are command and query responsibilities strictly separated?
- Do command handlers enforce invariants and idempotency?
- Are query models optimized for reads without write-model leakage?
- Is consistency model explicit and user-visible where needed?
- Are projection/replay failure modes handled and observable?
- Are security constraints enforced on both command and query boundaries?

## Testing Guidance
- Add command-side tests for invariants, authorization, and idempotency.
- Add query-side tests for read-model shape and staleness behavior.
- Add projection tests for duplicate events and replay/rebuild flows.
- Add integration tests for command-to-read propagation paths.
- Add operational tests/alerts for lag thresholds and failure recovery.

## Override Notes
- Framework/library docs may define CQRS implementation mechanics, but command/
  query separation, consistency contracts, and operability constraints in this
  file remain mandatory.
