# CLEAN_CODE

Guidance for AI agents applying clean-code principles in implementation and
review.

## Scope
- Define practical code-quality rules for maintainability and clarity.
- Apply this file across languages/frameworks as a minimum baseline.
- Specialized docs may add stricter/idiomatic constraints; explicit overrides
  must be documented and justified.

## Semantic Dependencies
- Inherit naming baseline from `LANGUAGE/CONVENTIONS.md`.
- `LANGUAGE/READABILITY.md` is a companion guideline, not a semantic parent.
- Inherit design constraints from `DESIGN/SOLID.md`.

## Core Principles
- Prioritize clarity over cleverness.
- Keep code intent obvious for future maintainers.
- Keep responsibilities cohesive and boundaries explicit.
- Prefer incremental improvement over large speculative rewrites.

## Naming and Intent
- Use precise, domain-driven names.
- Avoid ambiguous abbreviations.
- Keep naming consistent across module boundaries.
- Rename related symbols/comments when intent changes.

## Function and Method Design
- Keep functions focused on one responsibility.
- Avoid long parameter lists; group related data into value objects.
- Prefer guard clauses over deep nested conditionals.
- Keep side effects explicit.

## Error Handling
- Fail fast on invalid input/invariants.
- Use meaningful exception/error types.
- Do not swallow errors silently.
- Keep error paths observable and actionable.

## Duplication and Abstraction
- Remove duplication that increases maintenance risk.
- Avoid premature abstraction with unclear reuse value.
- Extract shared behavior when duplication is stable and semantic.
- Keep abstractions aligned to domain concepts, not accidental code shape.

## Module and Dependency Hygiene
- Keep modules cohesive and dependency direction clear.
- Avoid cyclic dependencies.
- Keep public APIs minimal and stable.
- Avoid hidden coupling through globals/static mutable state.

## High-Risk Pitfalls
1. Clever compact code that hides intent.
2. God classes/functions with mixed responsibilities.
3. Catch-all exception handling with no context.
4. Premature abstraction increasing complexity.
5. Naming drift across module boundaries.
6. Refactors without regression safety tests.

## Do / Don't Examples
### 1. Function Cohesion
```text
Don't: one function validates, persists, sends email, and logs analytics.
Do:    separate orchestration from focused operations.
```

### 2. Error Context
```text
Don't: throw a generic error with no context ("failed").
Do:    throw domain-specific error with contextual identifiers.
```

### 3. Duplication Strategy
```text
Don't: abstract two near-identical lines into complex helper prematurely.
Do:    wait for stable repeated pattern, then extract meaningfully.
```

## Code Review Checklist for Clean Code
- Is intent clear without decoding implementation details?
- Are responsibilities cohesive at function/class/module levels?
- Are names domain-meaningful and consistent?
- Are error paths explicit and actionable?
- Is abstraction level justified by real reuse/change patterns?
- Are side effects and dependencies explicit?

## Testing Guidance
- Add regression tests before significant refactors.
- Keep tests aligned to behavior contracts, not incidental structure.
- Add tests for error/edge paths introduced by cleanup changes.
- Ensure refactors keep existing behavior stable unless intentionally changed.

## Override Notes
- Framework/language docs may prescribe idiomatic structures, but clean-code
  clarity and cohesion constraints remain mandatory.
