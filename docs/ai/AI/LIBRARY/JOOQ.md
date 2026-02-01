# JOOQ

Guidance for jOOQ usage.

## Defaults
- Use jOOQ for complex queries and reporting.
- Keep SQL logic close to data-access boundaries.
- Prefer generated types for compile-time safety.

## Query Design
- Keep queries readable; extract common parts into reusable methods.
- Avoid embedding large SQL strings in services.
- Favor explicit joins over implicit or hidden behavior.

## Transactions
- Use the same transaction boundaries as the surrounding service logic.
- Avoid mixing jOOQ and JPA writes in the same transaction unless required and
  well-understood.

## Testing
- Cover non-trivial queries with integration tests.
