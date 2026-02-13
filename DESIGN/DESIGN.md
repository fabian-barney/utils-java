# DESIGN

Design-principles layer contract for maintainability, cohesion, coupling, and
long-term code quality.

## Role in the Ruleset
- DESIGN docs define principle-level constraints that shape implementation
  choices across languages, frameworks, and libraries.
- Design guidance is part of the cross-cutting baseline and applies alongside
  the other cross-cutting constraints before downstream specialization.
- Global precedence and override behavior are defined in
  `CORE/RULE_DEPENDENCY_TREE.md`.

## Scope Boundary
DESIGN includes:
- Principle-level guidance for structure, readability, and complexity control.
- Reusable pattern guidance independent of specific frameworks/tools.
- Tradeoff guidance for modularity, extensibility, and maintainability.

DESIGN does not include:
- Framework lifecycle/runtime behavior.
- Library API-specific usage details.
- Build/deployment/runtime platform procedures.

Those belong in `FRAMEWORK/**`, `LIBRARY/**`, `BUILD_TOOLS/**`,
`INFRASTRUCTURE/**`, and `CI-CD/**`.

## Specialization Contract
- Child design docs may narrow design principles for specific contexts
  (for example SOLID, GoF patterns, AOP boundaries).
- Design docs must not weaken inherited security/compliance/test constraints
  without explicit, reviewed rationale.
- Framework/library docs may apply design patterns concretely, but design
  principles remain authoritative unless explicitly specialized.

## Files
- [AOP.md](AOP.md) - Aspect-oriented programming guidance.
- [CLEAN_CODE.md](CLEAN_CODE.md) - Clean code practices.
- [GOF_DESIGN_PATTERNS.md](GOF_DESIGN_PATTERNS.md) - Gang of Four design pattern guidance.
- [SOLID.md](SOLID.md) - SOLID principles guidance.

## Authoring Notes
- Keep this file index-level and boundary-focused.
- Put deep principle and pattern behavior in child design docs.
- When adding a design doc, update this index and align semantic dependencies
  in `CORE/RULE_DEPENDENCY_TREE.md`.
