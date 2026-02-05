# N_PLUS_1

Rules to prevent N+1 query patterns in data access.

## Core Rule
- Use one query per level and batch fetch at each level.

## Guidance
- Do not execute queries inside loops over entities or collections.
- Fetch parent rows first, then collect IDs and batch fetch children with `IN` or
  join strategies.
- Prefer explicit projections or joins over implicit lazy loading when iterating.
- For GraphQL or resolver-style APIs, use batching (for example, DataLoader) to
  collapse per-entity queries into a single batch per field.
- Keep query counts predictable; log and review query totals for hot paths.

## Example Pattern
1. Query root entities.
2. Collect their IDs.
3. Query related entities in one batch.
4. Map results in memory (by ID) to build the response.

## ORMs
- Use fetch joins, entity graphs, or explicit DTO projections when needed.
- If lazy loading is used, access relationships only after batch loading.
