# GRAPHQL

Guidance for GraphQL APIs.

## Schema Design
- Use schema-first design.
- Avoid leaking internal domain models directly into the schema.
- Use clear naming and consistent conventions.

## Queries and Performance
- Avoid N+1 queries with batching or dataloaders.
- Enforce query depth/complexity limits.
- Use pagination via connections or cursor-based patterns.

## Evolution
- Prefer additive changes.
- Deprecate fields before removal and document timelines.

## Security
- Validate inputs and enforce auth at resolver boundaries.
- Monitor for abusive queries and set rate limits.
