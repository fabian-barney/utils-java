# GRAPHQL

Guidance for AI agents implementing and reviewing GraphQL APIs.

## Scope
- Define GraphQL schema and resolver architecture constraints.
- Apply this file to GraphQL server API design and implementation review.

## Semantic Dependencies
- Inherit cross-cutting constraints from:
  `SECURITY/SECURITY.md`, `TEST/TEST.md`, `CORE/LOGGING.md`.
- Inherit architecture constraints from `ARCHITECTURE/ARCHITECTURE.md` and
  `ARCHITECTURE/N_PLUS_1.md`.
- Framework/library docs may specialize implementation details but should keep
  GraphQL contract and query-safety constraints.

## Schema Design Defaults
- Prefer schema-first design with explicit contracts.
- Use clear domain naming for types, fields, and operations.
- Keep type nullability intentional; avoid accidental nullable sprawl.
- Separate input and output models explicitly.
- Avoid exposing persistence-internal shape directly in schema.

## Evolution and Compatibility
- Prefer additive changes.
- Deprecate fields before removal and document migration timelines.
- Avoid semantic redefinition of existing fields.
- Version at graph/domain boundary only when compatibility cannot be preserved.

## Resolver Architecture
- Keep resolvers thin orchestration layers.
- Move business logic to services/use cases, not resolver glue code.
- Keep per-field resolvers side-effect-free for query operations.
- Validate and authorize at resolver boundaries consistently.

## Query Safety and Performance
- Prevent N+1 with batching/data loaders.
- Enforce query depth and complexity limits.
- Apply rate limiting and cost controls for abusive queries.
- Paginate list fields by default where cardinality can grow.
- Keep expensive fields explicit and documented.

## Caching and Consistency
- Define cacheability and invalidation strategy per field/resource class.
- Avoid caching staleness-sensitive data without clear TTL/invalidation rules.
- Keep mutation side effects explicit for downstream cache invalidation.

## Error Handling
- Keep GraphQL errors machine-readable via `extensions.code` (or equivalent).
- Distinguish authorization, validation, and internal failures.
- Avoid leaking sensitive internals in error messages.
- Ensure partial-data-with-errors behavior is intentional and documented.

## Security Baseline
- Enforce auth/authz consistently across query and mutation paths.
- Validate input payloads before domain execution.
- Prevent introspection abuse in production when policy requires restriction.
- Monitor query patterns for scraping/amplification behavior.

## High-Risk Pitfalls
1. N+1 resolver query explosions.
2. Unbounded query complexity without cost controls.
3. Breaking field changes without deprecation path.
4. Authorization inconsistencies between top-level and nested resolvers.
5. Ambiguous nullability contracts causing client/runtime bugs.
6. Leaking internal exception details in GraphQL errors.
7. Mutations with hidden side effects and no clear contract.

## Do / Don't Examples
### 1. N+1 Pattern
```text
Don't: query database once per parent row in field resolver.
Do:    batch child loads with DataLoader per request scope.
```

### 2. Schema Evolution
```text
Don't: rename existing field without transition.
Do:    add new field, deprecate old field, publish migration window.
```

### 3. Error Extensions
```json
// Don't
{"errors":[{"message":"failed"}]}

// Do
{"errors":[{"message":"Validation failed",
             "extensions":{"code":"VALIDATION_ERROR"}}]}
```

## Code Review Checklist for GraphQL
- Is schema design explicit and domain-oriented?
- Are compatibility/deprecation policies respected?
- Are resolvers thin and business logic extracted?
- Is N+1 prevention implemented for nested fields?
- Are depth/complexity limits and pagination present?
- Are auth/authz checks consistent on nested and top-level paths?
- Are error payloads safe and machine-readable?

## Testing Guidance for GraphQL
- Add schema contract tests for key queries/mutations.
- Test authorization on nested and top-level resolvers.
- Test complexity/depth guard behavior for abusive queries.
- Test N+1-sensitive queries with query-count assertions.
- Test deprecation compatibility for legacy clients.

## Override Notes
- Framework-specific GraphQL libraries may change wiring patterns, but schema
  compatibility, query-safety, and authorization rules here remain mandatory.
