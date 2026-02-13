# SQL

Guidance for AI agents implementing and reviewing SQL.

## Scope
- Define SQL correctness, safety, and performance baseline.
- Apply this file for handwritten SQL, generated SQL review, and database
  migration scripts.

## Semantic Dependencies
- Inherit security constraints from `SECURITY/SECURITY.md`.
- Inherit testing expectations from `TEST/TEST.md`.
- Inherit naming/readability constraints from
  `LANGUAGE/CONVENTIONS.md` and `LANGUAGE/READABILITY.md`.
- Inherit architecture guidance from `ARCHITECTURE/N_PLUS_1.md` where relevant.
- Library docs (JPA/jOOQ) may specialize query construction but must preserve
  this baseline.

## Defaults
- Parameterize all external values; avoid string-concatenated SQL.
- Keep queries explicit and readable; avoid hidden implicit behavior.
- Select only needed columns; avoid `SELECT *` in production paths.
- Use clear aliasing and deterministic ordering when order matters.
- Keep transaction scope minimal and explicit.

## Data Modeling and Schema Evolution
- Use explicit primary keys and foreign key constraints unless there is a
  documented reason not to.
- Keep migrations forward-only and idempotent where feasible.
- Prefer additive schema changes for compatibility.
- Backfill large data in batches to avoid long locks.

## Query Performance Rules
- Design indexes for real access patterns, not assumptions.
- Validate query plans for hot-path queries.
- Avoid N+1 query patterns; batch or join intentionally.
- Use pagination for large result sets.
- Be explicit with join cardinality and filtering predicates.

## Transaction and Consistency Rules
- Keep transactions short; avoid user/network waits inside transactions.
- Pick isolation levels intentionally based on correctness needs.
- Handle deadlocks and transient failures with bounded retries.
- Keep write ordering deterministic when consistency depends on it.

## Security Rules
- Never interpolate untrusted input into SQL strings.
- Minimize granted DB privileges by role.
- Avoid returning sensitive columns unless required.
- Treat migration scripts as security-sensitive artifacts.

## High-Risk Pitfalls
1. SQL injection from dynamic string concatenation.
2. Unbounded queries and full scans on large tables.
3. Missing indexes for frequent filter/join predicates.
4. N+1 query patterns hidden behind ORM abstractions.
5. Long-running transactions causing lock contention.
6. Breaking schema changes without compatibility window.
7. Non-deterministic ordering in pagination queries.

## Do / Don't Examples
### 1. Parameterization
```text
Don't: build SQL in application code using string interpolation/concatenation.
Example: `"... WHERE email = '" + input + "'"`.
```

```sql
-- Do: parameterized query with explicit column list
SELECT id, email FROM users WHERE email = :email;
```

### 2. Deterministic Pagination
```sql
-- Don't: no stable ordering
SELECT id, name FROM orders LIMIT 50 OFFSET 100;

-- Do: stable ordering key
SELECT id, name
FROM orders
ORDER BY created_at DESC, id DESC
LIMIT :limit OFFSET :offset;
```

### 3. N+1 Avoidance
```sql
-- Don't: one query per parent row in application loop

-- Do: batch children by parent ids with query-builder-expanded placeholders
SELECT order_id, sku, quantity
FROM order_items
WHERE order_id IN (
  -- expanded by query builder from a collection bind like :orderIds
  :orderId1, :orderId2, :orderId3
);
```

Note: the expanded placeholders above are illustrative.
Generate collection bindings/placeholders through the DB driver/query builder;
never string-interpolate `IN (...)` values.

## Code Review Checklist for SQL
- Are all dynamic values parameterized?
- Are selected columns minimal and intentional?
- Is ordering explicit where result order matters?
- Is pagination/indexing appropriate for expected cardinality?
- Are transaction boundaries and isolation choices explicit?
- Are migration scripts backward-compatible and operationally safe?
- Are query plans acceptable for hot paths?

## Testing Guidance for SQL
- Add integration tests for non-trivial queries and edge-case filters.
- Test migration upgrade paths on realistic dataset sizes.
- Test transaction rollback/consistency behavior under failure.
- Add performance regression checks for critical queries.
- Test authorization/role constraints for sensitive operations.

## Override Notes
- ORM/framework docs may adjust implementation style but must preserve
  parameterization, transaction safety, and query-plan awareness defined here.
