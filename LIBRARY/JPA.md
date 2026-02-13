# JPA

Guidance for AI agents implementing and reviewing JPA-based persistence.

## Scope
- Define JPA mapping and query usage rules for correctness and performance.
- Apply this file to entity modeling, repository logic, and transaction design.

## Semantic Dependencies
- Inherit Java baseline from `LANGUAGE/JAVA/JAVA.md`.
- Inherit SQL and N+1 constraints from
  `LANGUAGE/SQL/SQL.md` and `ARCHITECTURE/N_PLUS_1.md`.
- Inherit framework boundary constraints from `FRAMEWORK/SPRING_BOOT.md`.
- Cross-cutting baselines are inherited transitively via the language/framework
  parents above.

## Defaults
- Keep entities persistence-focused; keep business workflows in services.
- Prefer explicit mappings for non-trivial columns/relations.
- Use `@Enumerated(EnumType.STRING)` for enums.
- Prefer LAZY associations by default; fetch explicitly per use case.
- Keep transaction boundaries explicit and use-case aligned.

## Mapping and Entity Design
- Keep equals/hashCode on entities identity-safe.
- Avoid exposing mutable collections directly.
- Keep cascade rules minimal and intentional.
- Avoid bidirectional relationships unless they add real query/navigation value.
- Keep embeddables/value objects for cohesive grouped fields.

## Query and Fetch Strategy
- Avoid N+1 via fetch joins/entity graphs/DTO projections.
- Prefer explicit queries for non-trivial reads.
- Avoid large object graph loading when only subset fields are needed.
- Validate generated SQL for critical queries.

## Transaction and Consistency
- Keep transactions short; avoid remote IO inside transaction scope.
- Be explicit about locking strategy for concurrency-sensitive writes.
- Handle optimistic lock exceptions intentionally.
- Avoid Open Session in View dependence for core business behavior.

## High-Risk Pitfalls
1. N+1 queries from lazy relations in loops/serialization.
2. Over-cascading relations causing accidental writes/deletes.
3. Entity leakage into API boundaries.
4. Long transactions with remote calls.
5. Improper equals/hashCode breaking persistence behavior.
6. Blind eager fetching causing memory/perf spikes.

## Do / Don't Examples
### 1. Enum Mapping
```java
// Don't
@Enumerated(EnumType.ORDINAL)
private Status status;

// Do
@Enumerated(EnumType.STRING)
private Status status;
```

### 2. Query Strategy
```text
Don't: iterate orders and lazily load items per row.
Do:    fetch required relations/projection explicitly for endpoint use case.
```

### 3. Transaction Scope
```text
Don't: call external payment API inside DB transaction.
Do:    isolate external call from persistence transaction boundary.
```

## Code Review Checklist for JPA
- Are entity mappings explicit and identity-safe?
- Is fetch strategy intentional and N+1-safe?
- Are transaction boundaries short and explicit?
- Are cascade/orphan rules minimal and justified?
- Are entities kept out of external API contracts?
- Are locking/concurrency behaviors handled intentionally?

## Testing Guidance
- Add integration tests for mappings and custom queries.
- Add query-count/performance checks on hot paths.
- Test optimistic locking/conflict scenarios.
- Test transaction rollback behavior on failures.
- Test serialization boundaries to avoid lazy-loading surprises.

## Override Notes
- jOOQ or custom SQL may be preferred for complex query/reporting scenarios.
- JPA-specific convenience should not override query predictability and
  transaction safety constraints.
