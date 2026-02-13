# JAXB

Guidance for AI agents implementing and reviewing JAXB XML binding.

## Scope
- Define JAXB usage rules for safe and stable XML serialization/deserialization.
- Apply this file to XML contract-bound integrations.

## Semantic Dependencies
- Inherit Java baseline from `LANGUAGE/JAVA/JAVA.md`.
- Inherit security constraints from `SECURITY/SECURITY.md`.
- Inherit compliance/testing constraints from `COMPLIANCE/**` and `TEST/TEST.md`.

## Defaults
- Use JAXB when XML contract interop requires it.
- Keep JAXB DTOs separate from core domain models.
- Prefer explicit annotations over implicit mapping defaults.
- Keep schema and binding models versioned and documented.

## Mapping and Contract Rules
- Keep field names/types aligned with external schema contract.
- Avoid leaking internal domain semantics into XML contract types.
- Keep namespace handling explicit and consistent.
- Preserve backward compatibility when evolving XML contracts.

## Validation and Parsing Safety
- Validate untrusted XML against schema where feasible.
- Fail fast on invalid payloads with actionable errors.
- Configure XML parser securely (XXE/entity expansion protections).
- Avoid permissive parsing that masks malformed input.

## High-Risk Pitfalls
1. Mixing JAXB DTOs directly into domain/business logic.
2. Contract drift between code and XSD.
3. Insecure XML parser settings (XXE risk).
4. Silent parsing failures with partial object state.
5. Breaking XML compatibility without migration strategy.

## Do / Don't Examples
### 1. Boundary Separation
```text
Don't: expose JAXB-annotated classes across service core.
Do:    map JAXB DTOs to domain models at adapter boundary.
```

### 2. Contract Evolution
```text
Don't: remove required XML elements abruptly.
Do:    add new optional fields and deprecate with migration plan.
```

### 3. Parser Security
```text
Don't: enable external entity resolution on untrusted input.
Do:    use hardened parser configuration and schema validation.
```

## Code Review Checklist for JAXB
- Are JAXB models isolated from domain core?
- Are annotations explicit and contract-aligned?
- Is XML parser configuration secure?
- Is schema compatibility/evolution strategy documented?
- Are invalid XML paths handled safely and observably?

## Testing Guidance
- Add round-trip serialization/deserialization tests.
- Add schema-validation tests for representative fixtures.
- Add negative tests for malformed/unsafe XML input.
- Add compatibility regression tests for contract evolution.

## Override Notes
- If alternative XML libraries are used for specific needs, maintain the same
  contract compatibility and parser security constraints.
