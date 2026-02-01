# JPA

Guidance for JPA usage.

## Defaults
- Keep entities persistence-focused; avoid embedding business workflows.
- Prefer constructor injection and immutable value objects where possible.

## Mapping
- Use explicit column mappings for non-trivial fields.
- Prefer `@Enumerated(EnumType.STRING)` for enums.
- Avoid bidirectional relationships unless necessary.

## Fetching
- Use LAZY by default; fetch eagerly only with a clear reason.
- Avoid N+1 queries; use fetch joins or DTO projections when needed.

## Transactions
- Keep transactional boundaries explicit and small.
- Avoid performing external calls inside transactions.

## Testing
- Use integration tests for entity mappings and queries.
