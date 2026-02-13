# N_PLUS_1

Guidance for AI agents preventing and detecting N+1 query patterns.

## Scope
- Define anti-N+1 rules across ORM, SQL, and GraphQL/resolver-based systems.
- Apply this file to data access implementation and review.

## Semantic Dependencies
- Inherit cross-cutting constraints from
  `SECURITY/SECURITY.md`, `TEST/TEST.md`, `CORE/LOGGING.md`.
- Inherit SQL/query baseline from `LANGUAGE/SQL/SQL.md`.
- Inherit architecture constraints from `ARCHITECTURE/ARCHITECTURE.md`.
- Inherit framework/library specifics from:
  `LIBRARY/JPA.md`, `LIBRARY/JOOQ.md`, and `ARCHITECTURE/GRAPHQL.md` where
  applicable.

## Core Rule
- Avoid per-entity follow-up queries in loops or repeated resolver calls.
- Batch related data fetches per logical level of the response graph.

## Detection Heuristics
- Watch for query execution inside loops over parent entities.
- Watch for lazy-loaded relation access in serialization/rendering loops.
- In GraphQL, watch per-field resolver DB calls without DataLoader/batching.
- Use query counters and SQL logs in test/profiling environments.

## Prevention Strategy
- Query parents first.
- Collect parent keys.
- Fetch children/related data in one batch (`IN`, joins, dedicated loaders).
- Reconstruct response graph in memory.
- Keep batch size and pagination controls explicit.

## ORM-Specific Guardrails
- Prefer explicit fetch joins/entity graphs/projections for known access paths.
- Avoid broad eager loading by default; tune fetch plans per use case.
- Keep lazy loading away from view/serialization layers.
- Validate generated SQL for non-trivial ORM queries.

## GraphQL/Resolver Guardrails
- Use per-request DataLoader or equivalent batching abstraction.
- Ensure loaders are request-scoped to prevent cache leakage across users.
- Batch by field/resource shape with deterministic key mapping.
- Avoid nested resolver-side queries without loader mediation.

## Performance and Correctness Tradeoffs
- Prefer predictable query count over highly dynamic implicit loading.
- Avoid giant fetch joins that explode row multiplicity without pagination.
- Keep response payload size aligned with use-case requirements.
- Combine pagination with batching to bound memory and query costs.

## High-Risk Pitfalls
1. Query in loop over parent records.
2. Lazy relation access during JSON serialization.
3. Missing DataLoader in GraphQL nested fields.
4. Over-eager joins creating huge Cartesian-like payloads.
5. Query-count regressions not covered by tests.
6. Repository abstractions hiding repeated DB calls.

## Do / Don't Examples
### 1. Application Loop Query
```text
Don't:
for each order -> query order_items by order_id

Do:
query orders
collect ids
query order_items where order_id in (:ids)
map in memory
```

### 2. GraphQL Resolver
```text
Don't: resolver loads user profile with DB call per row.
Do:    resolver uses request-scoped DataLoader keyed by userId.
```

### 3. ORM Serialization
```text
Don't: return entities with lazy relations and let serializer trigger queries.
Do:    project explicitly to DTO with preloaded required fields.
```

## Code Review Checklist for N+1 Risk
- Are there queries inside loops over records/resolvers?
- Are ORM fetch plans explicit for endpoint/query use case?
- Are GraphQL loaders present and request-scoped where needed?
- Is query count predictable and bounded under pagination?
- Are joins/batches chosen to avoid both N+1 and row explosion?
- Are query-count regressions covered in tests?

## Testing Guidance
- Add query-count assertions for critical endpoints/resolvers.
- Add integration tests for large parent sets and pagination behavior.
- Test GraphQL nested query performance characteristics.
- Track and alert on unexpected query count growth in performance tests.

## Override Notes
- Framework/library docs may define API-specific mechanisms, but anti-N+1
  guarantees and query-count predictability in this file remain mandatory.
