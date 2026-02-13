# MAPSTRUCT

Guidance for AI agents implementing and reviewing MapStruct mappings.

## Scope
- Define MapStruct mapping rules for explicit, type-safe DTO/entity conversion.
- Apply this file to mapping-layer implementation and review.

## Semantic Dependencies
- Inherit Java baseline from `LANGUAGE/JAVA/JAVA.md`.
- Inherit architecture boundary constraints from
  `ARCHITECTURE/CLEAN_ARCHITECTURE.md`.

## Defaults
- Prefer MapStruct for repetitive structural mapping.
- Keep mapping logic explicit and compile-time verified.
- Keep mappers focused per bounded context/boundary.
- Keep business logic out of mappers.

## Mapping Policy
- Prefer explicit field mappings when names differ.
- Configure unmapped target policy intentionally (fail fast for unexpected gaps).
- Keep update mappings (`@MappingTarget`) explicit about null handling.
- Keep nested/collection mappings readable and test-covered.

## Null and Default Handling
- Define null value strategy intentionally.
- Avoid surprising defaults that hide missing source data.
- Keep partial-update semantics explicit and predictable.

## Composition and Reuse
- Split large mappers into cohesive units.
- Reuse helper mappers for shared value transformations.
- Avoid circular mapper dependencies.
- Keep generated code reviewable via explicit annotations/config.

## High-Risk Pitfalls
1. Silent unmapped fields after DTO evolution.
2. Business rules embedded in mapping expressions.
3. Unclear null/update semantics causing data loss.
4. Massive mapper interfaces with mixed domain boundaries.
5. Hidden conversion logic hard to test/debug.

## Do / Don't Examples
### 1. Unmapped Fields
```text
Don't: ignore newly added target fields accidentally.
Do:    configure strict unmapped target policy and handle explicitly.
```

### 2. Business Logic Placement
```text
Don't: complex domain rules in @Mapping(expression = ...).
Do:    keep mapper structural; perform domain rules in service/use-case layer.
```

### 3. Update Semantics
```text
Don't: overwrite existing target fields with null unintentionally.
Do:    define null-value property mapping strategy explicitly.
```

## Code Review Checklist for MapStruct
- Are mapper boundaries cohesive and domain-aligned?
- Are field mappings explicit where required?
- Are unmapped-field policies strict enough?
- Are null/update semantics intentional and safe?
- Is business logic kept outside mapping layer?
- Are nested/collection mappings readable and tested?

## Testing Guidance
- Add mapper unit tests for representative DTO/entity pairs.
- Add tests for null and partial-update semantics.
- Add regression tests when source/target models evolve.
- Validate compile-time mapping failures are surfaced in CI.

## Override Notes
- Project-specific mapping standards may require stricter mapper granularity,
  but explicitness and business-logic separation here remain mandatory.
