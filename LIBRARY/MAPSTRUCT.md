# MAPSTRUCT

MapStruct is recommended for type-safe, compile-time mapping between DTOs and
entities. It avoids reflection at runtime and keeps mapping logic explicit.

## Preferred Usage
- Define mapper interfaces and generate implementations at compile time.
- Keep mappings explicit; avoid hidden or implicit conversions.
- Use dedicated mappers per domain boundary (API, persistence, integration).

## Guidance
- Keep mapping logic thin; complex transformations should live in services.
- Prefer constructor-based or builder-based mappings for immutable types.
- Add tests for non-trivial mappings.

## Avoid
- Embedding business logic in mapping layers.
- Overusing expression-based mappings when a named method is clearer.
